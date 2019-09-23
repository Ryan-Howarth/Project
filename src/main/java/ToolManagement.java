import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class ToolManagement {
    public static void addTool(Integer toolID, String toolName) {
        try {
            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Tools(ToolID, ToolName) VALUES (?, ?)");
            ps.setInt(1, toolID);
            ps.setString(2, toolName);
            ps.executeUpdate();

        } catch (Exception exception) {
            System.out.println("Database disconnection error: " + exception.getMessage());
        }
    }

    public static void readTool() {
        try {

            PreparedStatement ps = Main.db.prepareStatement("SELECT ToolName FROM Tools");
            ResultSet results = ps.executeQuery();
            while (results.next()) {
                String toolName = results.getString(1);
                System.out.println(toolName);
            }


        } catch (Exception exception) {
            System.out.println("Database disconnection error: " + exception.getMessage());
        }
    }

    public static void updateTool(String toolName, Integer toolID) {
        try {
            PreparedStatement ps = Main.db.prepareStatement("UPDATE Tools SET ToolName = ? WHERE ToolID = ?");
            ps.setString(1, toolName);
            ps.setInt(2, toolID);
            ps.executeUpdate();

        } catch (Exception exception) {
            System.out.println("Database disconnection error: " + exception.getMessage());
        }
    }
    public static void deleteTool(Integer toolID) {
        try {
            PreparedStatement ps = Main.db.prepareStatement("DELETE FROM Tools WHERE ToolID = ?");
            ps.setInt(1, toolID);
            ps.executeUpdate();

        } catch (Exception exception) {
            System.out.println("Database disconnection error: " + exception.getMessage());
        }
    }
}
