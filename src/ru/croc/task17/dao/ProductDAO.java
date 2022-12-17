package ru.croc.task17.dao;

import ru.croc.task17.pojo.Product;

import java.sql.*;

public class ProductDAO {
    private final Connection dbConnection;

    public ProductDAO(Connection connection) {
        this.dbConnection = connection;
    }

    public void create(Product product) {
        String sql = "INSERT INTO product values(?,?,?)";
        try (PreparedStatement statement = dbConnection.prepareStatement(sql)) {
            statement.setString(1, product.getCode());
            statement.setString(2, product.getName());
            statement.setInt(3, product.getPrice());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public  void printAll() {
       try {
           Statement selectStatement = dbConnection.createStatement();
           System.out.println("-----Table: product------");
           boolean hasRslt = selectStatement.execute("select * from product");
           if (hasRslt) {
               try (ResultSet result = selectStatement.getResultSet()) {
                   while (result.next()) {
                       String productCode = result.getString("product_code");
                       String productName = result.getString("product_name");
                       int price = result.getInt("price");
                       System.out.println(productCode + " | " + productName + " | " + price);
                   }
               }
           }
       }
         catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
