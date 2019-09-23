import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class UserManagement {
    public static void addUser(Integer userID, String firstName, String lastName, String userName, String password) {
        try {
            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Users(UserID, FirstName, LastName, UserName, Password) VALUES (?, ?, ?, ?, ?)");
            ps.setInt(1, userID);
            ps.setString(2, firstName);
            ps.setString(3, lastName);
            ps.setString(4, userName);
            ps.setString(5, password);
            ps.executeUpdate();

        } catch (Exception exception) {
            System.out.println("Database disconnection error: " + exception.getMessage());
        }
    }

    public static void readUser() {
        try {

            PreparedStatement ps = Main.db.prepareStatement("SELECT FirstName FROM Users");
            ResultSet results = ps.executeQuery();
            while (results.next()) {
                String firstname = results.getString(1);
                System.out.println(firstname);
            }


        } catch (Exception exception) {
            System.out.println("Database disconnection error: " + exception.getMessage());
        }
    }

    public static void updateUser(String firstName, Integer userID) {
        try {
            PreparedStatement ps = Main.db.prepareStatement("UPDATE Users SET FirstName = ? WHERE UserID = ?");
            ps.setString(1, firstName);
            ps.setInt(2, userID);
            ps.executeUpdate();

        } catch (Exception exception) {
            System.out.println("Database disconnection error: " + exception.getMessage());
        }
    }
    public static void deleteUser(Integer userID) {
        try {
            PreparedStatement ps = Main.db.prepareStatement("DELETE FROM Users WHERE UserID = ?");
            ps.setInt(1, userID);
            ps.executeUpdate();

        } catch (Exception exception) {
            System.out.println("Database disconnection error: " + exception.getMessage());
        }
    }
}
