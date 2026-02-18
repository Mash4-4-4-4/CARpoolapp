import java.sql.*;

public class UserDAO {

    // 🔎 Check if user exists
    public User findByEmailAndPassword(String email, String password) {

        try (Connection conn = DBConnection.getConnection()) {

            String sql = "SELECT * FROM users WHERE email = ?AND password= ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getInt("user_id")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // 📝 Register new user
    public User register(String name, String email, String password) {

        try (Connection conn = DBConnection.getConnection()) {

            String sql = "INSERT INTO users(name, email, password) VALUES (?, ?, ?) RETURNING user_id";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("user_id");
                return new User(name, email, password, id);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
