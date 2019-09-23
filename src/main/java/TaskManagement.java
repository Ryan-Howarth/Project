import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class TaskManagement {
    public static void addTask(Integer taskID, String task, String answer) {
        try {
            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Tasks(TaskID, Task, CorrectAnswer) VALUES (?, ?, ?)");
            ps.setInt(1, taskID);
            ps.setString(2, task);
            ps.setString(3, answer);
            ps.executeUpdate();

        } catch (Exception exception) {
            System.out.println("Database disconnection error: " + exception.getMessage());
        }
    }

    public static void readTask() {
        try {

            PreparedStatement ps = Main.db.prepareStatement("SELECT Task FROM Tasks");
            ResultSet results = ps.executeQuery();
            while (results.next()) {
                String task = results.getString(1);
                System.out.println(task);
            }


        } catch (Exception exception) {
            System.out.println("Database disconnection error: " + exception.getMessage());
        }
    }

    public static void updateTask(String task, Integer taskID) {
        try {
            PreparedStatement ps = Main.db.prepareStatement("UPDATE Tasks SET Task = ? WHERE TaskID = ?");
            ps.setString(1, task);
            ps.setInt(2, taskID);
            ps.executeUpdate();

        } catch (Exception exception) {
            System.out.println("Database disconnection error: " + exception.getMessage());
        }
    }
    public static void deleteTask(Integer taskID) {
        try {
            PreparedStatement ps = Main.db.prepareStatement("DELETE FROM Tasks WHERE TaskID = ?");
            ps.setInt(1, taskID);
            ps.executeUpdate();

        } catch (Exception exception) {
            System.out.println("Database disconnection error: " + exception.getMessage());
        }
    }
}
