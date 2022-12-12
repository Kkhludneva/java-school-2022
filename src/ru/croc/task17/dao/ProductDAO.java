package ru.croc.task17.dao;

import ru.croc.task17.ShopDatabaseConnector;
import ru.croc.task17.pojo.Product;

import java.sql.*;

public class ProductDAO {
    public static void create(Product product) {
        try (Connection conn = DriverManager.getConnection(ShopDatabaseConnector.URL)) {
            String sql = "INSERT INTO products values(?,?,?)";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, product.getCode());
                statement.setString(2, product.getName());
                statement.setInt(3,product.getPrice());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void printAll() {
        try (Connection conn = DriverManager.getConnection(ShopDatabaseConnector.URL)) {
            Statement selectStatement = conn.createStatement();
            System.out.println("-----Table: products------");
            boolean hasRslt = selectStatement.execute("select * from products");
            if (hasRslt){
                try (ResultSet result = selectStatement.getResultSet()) {
                    while (result.next()) {
                        String productCode = result.getString("productCode");
                        String productName = result.getString("productName");
                        int price = result.getInt("price");
                        System.out.println(productCode+" | "+productName+ " | "+price);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
