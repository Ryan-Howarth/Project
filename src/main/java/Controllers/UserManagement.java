package Controllers;

import Server.Main;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

@Path ("Users/")
public class UserManagement {

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
    @POST
    @Path("updateUser")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String updateUser(@FormDataParam("firstname") String firstName, @FormDataParam("userID")Integer userID) {
        try {
            if (firstName == null || userID == null) {
                throw new Exception("One or more form data parameters are missing in the HTTP request.");
            }

            PreparedStatement ps = Main.db.prepareStatement("UPDATE Users SET FirstName = ? WHERE UserID = ?");
            ps.setString(1, firstName);
            ps.setInt(2, userID);
            ps.executeUpdate();
            return "{\"status\": \"OK\"}";


        } catch (Exception exception) {
            System.out.println("Database disconnection error: " + exception.getMessage());
            return "{\"error\": \"Unable to update item, please see server console for more info.\"}";
        }
    }
    @POST
    @Path("deleteUser")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)

    public String deleteUser(@FormDataParam("userID")Integer userID) {
        try {
            if (userID == null) {
                throw new Exception("One or more form data parameters are missing in the HTTP request.");
            }

            PreparedStatement ps = Main.db.prepareStatement("DELETE FROM Users WHERE UserID = ?");
            ps.setInt(1, userID);
            ps.executeUpdate();
            return "{\"status\": \"OK\"}";


        } catch (Exception exception) {
            System.out.println("Database disconnection error: " + exception.getMessage());
            return "{\"error\": \"Unable to delete item, please see server console for more info.\"}";

        }
    }
}
