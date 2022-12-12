package ru.croc.task17.POJO;


import java.util.Objects;

public class Product {
    private final String code;
    private final String name;
    private final int price;

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }



    public Product(String code, String name, int price){
        this.code = code;
        this.name = name;
        this.price =price;
    }

    @Override
    public String toString() {
        return code+"; "+name+"; "+price+"\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return price == product.price && Objects.equals(code, product.code) && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, name, price);
    }
}
