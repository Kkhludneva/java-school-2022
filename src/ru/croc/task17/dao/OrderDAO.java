package ru.croc.task17.dao;

import ru.croc.task17.pojo.Order;
import ru.croc.task17.pojo.Product;

import java.sql.*;

public class OrderDAO {
    private final Connection dbConnection;

    public OrderDAO(Connection connection) {
        this.dbConnection = connection;
    }

    public void create(Order order) {
        String sql = "INSERT INTO \"order\" values(?,?,?)";
        for (Product product : order.getProductCodes()) {
            try (PreparedStatement statement = dbConnection.prepareStatement(sql)) {
                statement.setInt(1, order.getNumber());
                statement.setString(2, order.getUserLogin());
                statement.setString(3, product.getCode());
                statement.executeUpdate();
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void printAll() {
        try{
            Statement selectStatement = dbConnection.createStatement();
            System.out.println("-----Table: order------");
            boolean hasRslt = selectStatement.execute("select * from \"order\"");
            if (hasRslt) {
                try (ResultSet result = selectStatement.getResultSet()) {
                    while (result.next()) {
                        int id = result.getInt("order_number");
                        String userLogin = result.getString("user_login");
                        String productCode = result.getString("product_code");
                        System.out.println(id + " | " + userLogin + " | " + productCode);
                    }
                }
            }
        } catch (SQLException e) {
           e.printStackTrace();
        }
    }
}
