package ru.croc.task17;

import ru.croc.task17.POJO.Order;
import ru.croc.task17.POJO.Product;
import ru.croc.task17.POJO.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FileToObjectsParser {

    private Set<User> users = new TreeSet<>(Comparator.comparingInt(User::getOrderNumber));
    private Set<Product> products = new HashSet<>();
    private Set<Order> ordersHistory = new TreeSet<>(Comparator.comparingInt(Order::getNumber));

    public Set<User> getUsers() {
        return users;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public Set <Order> getOrdersHistory() {
        return ordersHistory;
    }

    public FileToObjectsParser(File f){
        Map <Integer, List<String> > orders = new HashMap<>();
        try (Scanner scanner = new Scanner(f)) {
            while (scanner.hasNextLine()) {
                String[] s = scanner.nextLine().split(";");
                this.users.add(new User(Integer.valueOf(s[0]), s[1]));
                this.products.add(new Product(s[2], s[3], Integer.valueOf(s[4])));
                orders.computeIfAbsent(Integer.valueOf(s[0]), k -> new ArrayList<>()).add(s[2]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (Integer orderNum: orders.keySet()) {
            this.ordersHistory.add(new Order(orderNum,orders.get(orderNum)));
        }

    }


}
