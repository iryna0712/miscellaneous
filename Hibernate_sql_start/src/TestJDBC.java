import java.sql.Connection;
import java.sql.DriverManager;

public class TestJDBC {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/hb_student_tracker?useSSL=false";
        String user = "hbstudent";
        String pass = "hbstudent";

        try {
            Connection testConnection = DriverManager.getConnection(jdbcUrl, user, pass);
            System.out.println("Connection successful");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
