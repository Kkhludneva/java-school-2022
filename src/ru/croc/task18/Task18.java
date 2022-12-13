package ru.croc.task18;
import ru.croc.task17.FileToObjectsParser;
import ru.croc.task17.pojo.Product;
import ru.croc.task18.dao.OrderDAO;
import ru.croc.task18.dao.ProductDAO;

import java.io.File;
import java.util.Arrays;

public class Task18 {
    public static void main(String[] args) {
        String url = String.join(" ", args);
        File storeData = new File(url);

        ShopDatabaseConnector connector = new ShopDatabaseConnector(new FileToObjectsParser(storeData));
        System.out.println("Изначальный вид таблиц БД:");
        connector.createShopDatabase();
        connector.showShopDatabase();

        System.out.println("\nНайдём товар с артикулом 'Т2'");
        System.out.println(ProductDAO.findProduct("Т2"));

        System.out.println("\nОбновим сведения от товаре T1;Монитор;500 \nОбновлённая таблица товаров:");
        ProductDAO.updateProduct(new Product("Т1","МониторНовый",1000));
        ProductDAO.printAll();


        System.out.println("\nДобавим новый товар Т6;Планшет;5000\nОбновлённая таблица товаров:");

        try{
            ProductDAO.createProduct(new Product("Т6","Планшет",5000));
        }catch (java.sql.SQLException e){
            System.err.println("Невозможно добавить товар");
            throw new RuntimeException();
        }
        ProductDAO.printAll();

        System.out.println("\nУдалим товар с артикулом Т6\nОбновлённая таблица товаров:");
        ProductDAO.deleteProduct("Т6");
        ProductDAO.printAll();

        System.out.println("\nДобавим новый заказ\nОбновлённая таблицы заказов:");
        OrderDAO.createOrder("kate", Arrays.asList(new Product("Т1","Монитор!",501),
                new Product("Т2","Мышь",50)));
        OrderDAO.printAll();

    }
}
