package ru.croc.task17.POJO;

import java.util.Objects;

public class User {
    private final int orderNumber;

    private final String userLogin;

    public String getUserLogin() {
        return userLogin;
    }
    public int getOrderNumber() {
        return orderNumber;
    }

    public User(int num, String login){
        this.orderNumber = num;
        this.userLogin = login;
    }

    @Override
    public String toString() {
        return orderNumber+"; "+userLogin+"\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return orderNumber == user.orderNumber && Objects.equals(userLogin, user.userLogin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderNumber, userLogin);
    }
}
