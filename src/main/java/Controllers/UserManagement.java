package Controllers;

import Server.Main;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

@Path ("Users/")
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
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)


    public String readUser() {
        System.out.println("thing/list");
        JSONArray list = new JSONArray();

        try {

            PreparedStatement ps = Main.db.prepareStatement("SELECT FirstName FROM Users");
            ResultSet results = ps.executeQuery();
            while (results.next()) {
                JSONObject item = new JSONObject();
                item.put("name", results.getString(1));
                list.add(item);
            }
            return list.toString();


        } catch (Exception exception) {
            System.out.println("Database disconnection error: " + exception.getMessage());
            return "{\"error\": \"Unable to list items, please see server console for more info.\"}";

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
