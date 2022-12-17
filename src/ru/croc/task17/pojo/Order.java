package ru.croc.task17.pojo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order {
    private final int number;
    private final String userLogin;
    private final List<Product> products;

    public int getNumber() {
        return number;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public List<Product> getProductCodes() {
        return products;
    }

    public Order(int n, String userLogin, List<Product> prCodes){
        this.number = n;
        this.userLogin = userLogin;
        this.products = new ArrayList<>(prCodes);
    }

    @Override
    public String toString() {
        return number+": "+ products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order order)) return false;
        return number == order.number && Objects.equals(products, order.products);
    }

}
