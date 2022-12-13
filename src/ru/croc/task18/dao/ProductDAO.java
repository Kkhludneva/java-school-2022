package ru.croc.task18.dao;


import ru.croc.task17.pojo.Product;
import ru.croc.task18.ShopDatabaseConnector;

import java.sql.*;

public class ProductDAO {
    public static Product createProduct(Product product) throws java.sql.SQLException{
        try (Connection conn = DriverManager.getConnection(ShopDatabaseConnector.URL)) {
            String sql = "INSERT  INTO  products (price,productName,productCode) values(?,?,?)";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(3, product.getCode());
                statement.setString(2, product.getName());
                statement.setInt(1,product.getPrice());
                statement.executeUpdate();
            }
        }
        return product;
    }

    public static Product findProduct(String productCode) {
        Product p = null;
        try (Connection conn = DriverManager.getConnection(ShopDatabaseConnector.URL)) {
            String sql = "SELECT * FROM products WHERE productCode = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
               statement.setString(1, productCode);
                try (ResultSet result = statement.executeQuery()) {
                    while (result.next()) {
                        String Code = result.getString("productCode");
                        String productName = result.getString("productName");
                        int price = result.getInt("price");
                        p = new Product(Code, productName, price);
                    }
                }
            }
        }catch (java.sql.SQLException e){
            throw new RuntimeException();
        }
        return p;
    }
    public static Product updateProduct(Product product){
        Product p = null;
        try (Connection conn = DriverManager.getConnection(ShopDatabaseConnector.URL)) {
            String sql = "UPDATE products SET productName =? , price = ? WHERE productCode = ? ; ";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(3, product.getCode());
                statement.setString(1, product.getName());
                statement.setInt(2, product.getPrice());
                statement.executeUpdate();
                p = new Product(product.getCode(),product.getName(),product.getPrice());
            }
        }catch (java.sql.SQLException e){
            throw new RuntimeException();
        }
        return p;
    }

    public static void deleteProduct(String productCode){
        try (Connection conn = DriverManager.getConnection(ShopDatabaseConnector.URL)) {
            String sqlForProducts = "DELETE FROM products WHERE productCode = ? ; ";
            try (PreparedStatement statement = conn.prepareStatement(sqlForProducts)) {
                statement.setString(1, productCode);
                statement.executeUpdate();
            }
            String sqlForOrders = "DELETE FROM orders WHERE productCode = ? ; ";
            try (PreparedStatement statement = conn.prepareStatement(sqlForOrders)) {
                statement.setString(1, productCode);
                statement.executeUpdate();
            }
        }catch (java.sql.SQLException e){
            throw new RuntimeException();
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
