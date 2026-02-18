import java.sql.Connection;

public class tempmain {
    public static void main(String[] args) {
        try {
            Connection conn = DBConnection.getConnection();
            System.out.println("Connected Successfully!");
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
