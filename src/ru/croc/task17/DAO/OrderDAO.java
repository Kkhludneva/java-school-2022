package ru.croc.task17.DAO;

import ru.croc.task17.ShopDatabaseConnector;
import ru.croc.task17.POJO.Order;

import java.sql.*;

public class OrderDAO {
    public static void create(Order order) {
        try (Connection conn = DriverManager.getConnection(ShopDatabaseConnector.URL)) {
            String sql = "INSERT INTO orders values(?,?)";
            for (String product : order.getProductCodes()) {
                try (PreparedStatement statement = conn.prepareStatement(sql)) {
                    statement.setInt(1, order.getNumber());
                    statement.setString(2, product);
                    statement.executeUpdate();
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void selectAll() {
        try (Connection conn = DriverManager.getConnection(ShopDatabaseConnector.URL)) {
            Statement selectStatement = conn.createStatement();
            System.out.println("-----Table: orders------");
            boolean hasRslt = selectStatement.execute("select * from orders");
            if (hasRslt){
                try (ResultSet result = selectStatement.getResultSet()) {
                    while (result.next()) {
                        int id = result.getInt("orderNumber");
                        String productCode = result.getString("productCode");
                        System.out.println(id+" | "+productCode);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
