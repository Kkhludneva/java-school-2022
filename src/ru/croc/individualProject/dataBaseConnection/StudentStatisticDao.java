package ru.croc.individualProject.dataBaseConnection;

import java.sql.*;
import java.time.LocalDateTime;

public class StudentStatisticDao {

    private final Connection connection;

    public StudentStatisticDao(Connection connection) {
        this.connection = connection;
    }

    public void create(StudentStatistic studentStatistic) {

        String sql = "INSERT INTO statistics values(?,?,?,?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, studentStatistic.getUsername());
            statement.setTimestamp(2, Timestamp.valueOf(studentStatistic.getStartedTest().withNano(0)));
            statement.setTimestamp(3, Timestamp.valueOf(studentStatistic.getFinishedTest().withNano(0)));
            statement.setInt(4, studentStatistic.getTotalScore());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void printAll() {
        try {
            Statement selectStatement = connection.createStatement();
            System.out.println("-----Table: statistics------");
            boolean hasRslt = selectStatement.execute("select * from statistics");
            if (hasRslt) {
                try (ResultSet result = selectStatement.getResultSet()) {
                    while (result.next()) {
                        String userName = result.getString("userName");
                        LocalDateTime startedTest = result.getTimestamp("startedTest").toLocalDateTime();
                        LocalDateTime finishedTest = result.getTimestamp("finishedTest").toLocalDateTime();
                        int totalScore = result.getInt("totalScore");
                        System.out.println(userName + " | " + startedTest.toLocalTime() + " | " + finishedTest.toLocalTime() + " | " + totalScore);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
