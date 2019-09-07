import org.sqlite.SQLiteConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main {
    public static Connection db = null;
    public static void main(String[] args) {
        openDatabase("Database.db");
        try {
            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Users(UserID, FirstName, LastName) VALUES (?, ?, ?)");
            ps.setInt(1, 88);
            ps.setString(2, "Jeff");
            ps.setString(3, "Jones");
            ps.executeUpdate();






        } catch (Exception exception) {
            System.out.println("Database disconnection error: " + exception.getMessage());
        }
        closeDatabase();
    }
    private static void openDatabase(String dbFile) {
        try {
            Class.forName("org.sqlite.JDBC");
            SQLiteConfig config = new SQLiteConfig();
            config.enforceForeignKeys(true);
            db = DriverManager.getConnection("jdbc:sqlite:resources/" + dbFile, config.toProperties());
            System.out.println("Database connection successfully established.");
        } catch (Exception exception) {
            System.out.println("Database connection error: " + exception.getMessage());
        }
    }
    private static void closeDatabase(){
        try {
            db.close();
            System.out.println("Disconnected from database.");
        } catch (Exception exception) {
            System.out.println("Database disconnection error: " + exception.getMessage());
        }
    }
}
