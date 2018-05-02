package com.epam.task1.model;

public class Toy implements Comparable<Toy>{
    private int price;
    private String size;
    private String type;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int compareTo(Toy other) {
        return Integer.compare(this.price, other.price);
    }

}

