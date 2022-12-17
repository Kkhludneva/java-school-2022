package ru.croc.task18.dao;


import ru.croc.task17.pojo.Product;

import java.sql.*;

public class ProductDAO {
    private final Connection dbConnection;

    public ProductDAO(Connection connection) {
        this.dbConnection = connection;
    }

    public Product createProduct(Product product) throws java.sql.SQLException {
        String sql = "INSERT  INTO  product values(?,?,?)";
        Product p = null;
        try (PreparedStatement statement = dbConnection.prepareStatement(sql)) {
            statement.setString(1, product.getCode());
            statement.setString(2, product.getName());
            statement.setInt(3, product.getPrice());
            statement.executeUpdate();

            //ищем по первичному ключу, что добавилось
            String findUpdates = "select * from product where product_code = ? ;";
            PreparedStatement statementFindUpdates = dbConnection.prepareStatement(findUpdates);
            statementFindUpdates.setString(1,product.getCode());
            statementFindUpdates.executeQuery();
            boolean hasRslt = statementFindUpdates.execute();
            //если что-то добавилось, обновим p
            if (hasRslt) {
                try (ResultSet result = statementFindUpdates.getResultSet()) {
                    while (result.next()) {
                        String productCode = result.getString("product_code");
                        String productName = result.getString("product_name");
                        int productPrice = result.getInt("price");
                        p = new Product(productCode,productName,productPrice);
                    }
                }
            }
        }
        return p;
    }

    public Product findProduct(String productCode) {
        Product p = null;
        String sql = "SELECT * FROM product WHERE product_code = ?";
        try (PreparedStatement statement = dbConnection.prepareStatement(sql)) {
            statement.setString(1, productCode);
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    String code = result.getString("product_code");
                    String productName = result.getString("product_name");
                    int price = result.getInt("price");
                    p = new Product(code, productName, price);
                }
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        return p;
    }

    public Product updateProduct(Product product) {
        String sql = "UPDATE product SET product_name =? , price = ? WHERE product_code = ? ; ";
        Product p = null;
        try {
            PreparedStatement statement1 = dbConnection.prepareStatement(sql);
            statement1.setString(1, product.getName());
            statement1.setInt(2, product.getPrice());
            statement1.setString(3, product.getCode());
            statement1.executeUpdate();

            //ищем по первичному ключу, что добавилось
            String findUpdates = "select * from product where product_code = ? ;";
            PreparedStatement statementFindUpdates = dbConnection.prepareStatement(findUpdates);
            statementFindUpdates.setString(1,product.getCode());
            statementFindUpdates.executeQuery();
            boolean hasRslt = statementFindUpdates.execute();
            //если что-то добавилось, обновим p
            if (hasRslt) {
                try (ResultSet result = statementFindUpdates.getResultSet()) {
                    while (result.next()) {
                        String productCode = result.getString("product_code");
                        String productName = result.getString("product_name");
                        int productPrice = result.getInt("price");
                        p = new Product(productCode,productName,productPrice);
                    }
                }
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        return p;
    }

    public void deleteProduct(String productCode) {
        String sqlForProducts = "DELETE FROM product WHERE product_code = ? ; ";
        //из таблицы Order записи удалятся каскадно за счёт параметров внешнего ключа
        try (PreparedStatement statement = dbConnection.prepareStatement(sqlForProducts)) {
            statement.setString(1, productCode);
            statement.executeUpdate();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    public void printAll() {
        try {
            Statement selectStatement = dbConnection.createStatement();
            System.out.println("-----Table: product------");
            boolean hasRslt = selectStatement.execute("select * from product order by product_code");
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
