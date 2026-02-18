#!/bin/bash

# CarPooling Application - Quick Setup Script
# This script helps you compile and deploy the application

echo "=================================="
echo "CarPooling App - Quick Setup"
echo "=================================="
echo ""

# Step 1: Set Tomcat path
echo "Step 1: Setting up paths..."
read -p "Enter your Tomcat installation path (e.g., /opt/apache-tomcat-9.0.xx): " TOMCAT_PATH

if [ ! -d "$TOMCAT_PATH" ]; then
    echo "ERROR: Tomcat directory not found!"
    exit 1
fi

echo "Tomcat found at: $TOMCAT_PATH"
echo ""

# Step 2: Set PostgreSQL JDBC path
read -p "Enter path to PostgreSQL JDBC jar (e.g., /usr/share/java/postgresql.jar): " POSTGRES_JAR

if [ ! -f "$POSTGRES_JAR" ]; then
    echo "ERROR: PostgreSQL jar not found!"
    exit 1
fi

echo "PostgreSQL JDBC found at: $POSTGRES_JAR"
echo ""

# Step 3: Update database credentials
echo "Step 3: Database configuration..."
read -p "Database name (default: ridesys): " DB_NAME
DB_NAME=${DB_NAME:-ridesys}

read -p "Database user (default: postgres): " DB_USER
DB_USER=${DB_USER:-postgres}

read -sp "Database password: " DB_PASSWORD
echo ""

# Update DBConnection.java
cat > WEB-INF/classes/DBConnection.java << EOF
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/$DB_NAME";
    private static final String USER = "$DB_USER";
    private static final String PASSWORD = "$DB_PASSWORD";

    public static Connection getConnection() throws Exception {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
EOF

echo "Database configuration updated!"
echo ""

# Step 4: Compile Java files
echo "Step 4: Compiling Java files..."
cd WEB-INF/classes

javac -cp ".:$TOMCAT_PATH/lib/servlet-api.jar:$POSTGRES_JAR" *.java

if [ $? -eq 0 ]; then
    echo "✓ Compilation successful!"
else
    echo "✗ Compilation failed! Check errors above."
    exit 1
fi

cd ../..
echo ""

# Step 5: Copy PostgreSQL driver
echo "Step 5: Copying PostgreSQL driver..."
mkdir -p WEB-INF/lib
cp "$POSTGRES_JAR" WEB-INF/lib/
echo "✓ PostgreSQL driver copied!"
echo ""

# Step 6: Deploy to Tomcat
echo "Step 6: Deploying to Tomcat..."
APP_NAME="carpooling"

# Stop Tomcat
echo "Stopping Tomcat..."
$TOMCAT_PATH/bin/shutdown.sh 2>/dev/null
sleep 3

# Remove old deployment
if [ -d "$TOMCAT_PATH/webapps/$APP_NAME" ]; then
    echo "Removing old deployment..."
    rm -rf "$TOMCAT_PATH/webapps/$APP_NAME"
fi

# Copy new deployment
echo "Copying application to Tomcat..."
cp -r . "$TOMCAT_PATH/webapps/$APP_NAME"

# Start Tomcat
echo "Starting Tomcat..."
$TOMCAT_PATH/bin/startup.sh

echo ""
echo "=================================="
echo "Setup Complete!"
echo "=================================="
echo ""
echo "Access your application at:"
echo "http://localhost:8080/$APP_NAME/"
echo ""
echo "Check logs at:"
echo "$TOMCAT_PATH/logs/catalina.out"
echo ""
echo "To view logs in real-time, run:"
echo "tail -f $TOMCAT_PATH/logs/catalina.out"
echo ""
