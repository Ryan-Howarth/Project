package Controllers;

import Server.Main;

import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class ScoresManagement {
    public static void addScore(Integer scoreID, Integer userID, Integer toolID, Integer score) {
        try {
            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Scores(ScoreID, UserID, ToolID, Score) VALUES (?, ?, ?, ?)");
            ps.setInt(1, scoreID);
            ps.setInt(2, userID);
            ps.setInt(3, toolID);
            ps.setInt(4, score);
            ps.executeUpdate();

        } catch (Exception exception) {
            System.out.println("Database disconnection error: " + exception.getMessage());
        }
    }

    public static void readScore() {
        try {

            PreparedStatement ps = Main.db.prepareStatement("SELECT Score FROM Scores");
            ResultSet results = ps.executeQuery();
            while (results.next()) {
                Integer score = results.getInt(1);
                System.out.println(score);
            }


        } catch (Exception exception) {
            System.out.println("Database disconnection error: " + exception.getMessage());
        }
    }

    public static void updateScore(String score, Integer scoreID) {
        try {
            PreparedStatement ps = Main.db.prepareStatement("UPDATE Scores SET Score = ? WHERE ScoreID = ?");
            ps.setString(1, score);
            ps.setInt(2, scoreID);
            ps.executeUpdate();

        } catch (Exception exception) {
            System.out.println("Database disconnection error: " + exception.getMessage());
        }
    }
    public static void deleteScore(Integer scoreID) {
        try {
            PreparedStatement ps = Main.db.prepareStatement("DELETE FROM Scores WHERE ScoreID = ?");
            ps.setInt(1, scoreID);
            ps.executeUpdate();

        } catch (Exception exception) {
            System.out.println("Database disconnection error: " + exception.getMessage());
        }
    }
}
