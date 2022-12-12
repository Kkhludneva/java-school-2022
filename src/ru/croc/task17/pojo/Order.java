package ru.croc.task17.pojo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order {
    private final int number;
    private final String userLogin;
    private final List<String> productCodes;

    public int getNumber() {
        return number;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public List<String> getProductCodes() {
        return productCodes;
    }

    public Order(int n, String userLogin, List<String> prCodes){
        this.number = n;
        this.userLogin = userLogin;
        this.productCodes = new ArrayList<>(prCodes);
    }

    @Override
    public String toString() {
        return number+": "+ productCodes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order order)) return false;
        return number == order.number && Objects.equals(productCodes, order.productCodes);
    }

}
