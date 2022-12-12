package ru.croc.task17.DAO;

import ru.croc.task17.ShopDatabaseConnector;
import ru.croc.task17.POJO.User;

import java.sql.*;

public class UserDAO {
    public static void create(User user) {
        try (Connection conn = DriverManager.getConnection(ShopDatabaseConnector.URL)) {
            String sql = "INSERT INTO users values(?,?)";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setInt(1, user.getOrderNumber());
                statement.setString(2, user.getUserLogin());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void selectAll() {
        try (Connection conn = DriverManager.getConnection(ShopDatabaseConnector.URL)) {
            Statement selectStatement = conn.createStatement();
            System.out.println("-----Table: users------");
            boolean hasRslt = selectStatement.execute("select * from users");
            if (hasRslt){
                try (ResultSet result = selectStatement.getResultSet()) {
                    while (result.next()) {
                        int id = result.getInt("orderNumber");
                        String login = result.getString("login");
                        System.out.println(id+" | "+login);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
