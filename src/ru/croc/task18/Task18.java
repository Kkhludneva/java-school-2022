package ru.croc.task18;
import ru.croc.task17.FileToObjectsParser;
import ru.croc.task17.pojo.Product;
import ru.croc.task18.dao.OrderDAO;
import ru.croc.task18.dao.ProductDAO;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;

public class Task18 {
    public static void main(String[] args) {
        String url = String.join(" ", args);
        File storeData = new File(url);

        try {
            Connection dbConnector = DriverManager.getConnection("jdbc:h2:mem:~/shop");
            ShopDatabaseConnector connector = new ShopDatabaseConnector(new FileToObjectsParser(storeData),dbConnector);
            System.out.println("Изначальный вид таблиц БД:");
            connector.createShopDatabase();
            connector.showShopDatabase();

            ProductDAO productDAO = new ProductDAO(dbConnector);
            OrderDAO orderDAO = new OrderDAO(dbConnector);

            System.out.println("\nНайдём товар с артикулом 'Т2'");
            System.out.println(productDAO.findProduct("Т2"));

            System.out.println("\nОбновим сведения от товаре T1;Монитор;500 \nОбновлённая таблица товаров:");
            productDAO.updateProduct(new Product("Т1","МониторНовый",1000));
            productDAO.printAll();


            System.out.println("\nДобавим новый товар Т6;Планшет;5000\nОбновлённая таблица товаров:");

            try{
                productDAO.createProduct(new Product("Т6","Планшет",5000));
            }catch (java.sql.SQLException e){
                System.err.println("Невозможно добавить товар");
                throw new RuntimeException();
            }
            productDAO.printAll();

            System.out.println("\nУдалим товар с артикулом Т5\nОбновлённая БД:");
            productDAO.deleteProduct("Т5");
            connector.showShopDatabase();

            System.out.println("\nДобавим новый заказ\nОбновлённая таблицы заказов:");
            orderDAO.createOrder("kate", Arrays.asList(new Product("Т1","Монитор!",501),
                    new Product("Т2","Мышь",50)));
            orderDAO.printAll();

        } catch (SQLException e) {
           e.printStackTrace();
        }


    }
}
