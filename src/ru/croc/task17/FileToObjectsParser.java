package ru.croc.task17;

import ru.croc.task17.pojo.Order;
import ru.croc.task17.pojo.Product;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FileToObjectsParser {

    private final Set<Product> products = new HashSet<>();
    private final Set<Order> ordersHistory = new TreeSet<>(Comparator.comparingInt(Order::getNumber));


    public Set<Product> getProducts() {
        return products;
    }

    public Set <Order> getOrdersHistory() {
        return ordersHistory;
    }

    public FileToObjectsParser(File f){
        Map <Integer, List<Product> > orderItems = new HashMap<>();
        Map <Integer, String> orderOwner = new HashMap<>();
        try (Scanner scanner = new Scanner(f)) {
            while (scanner.hasNextLine()) {
                String[] s = scanner.nextLine().split(",");
                this.products.add(new Product(s[2], s[3], Integer.valueOf(s[4])));
                orderItems.computeIfAbsent(Integer.valueOf(s[0]), k -> new ArrayList<>()).add(new Product(s[2], s[3], Integer.valueOf(s[4])));
                orderOwner.put(Integer.valueOf(s[0]),s[1]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (Integer orderNum: orderItems.keySet()) {
            this.ordersHistory.add(new Order(orderNum,orderOwner.get(orderNum),orderItems.get(orderNum)));
        }

    }


}
