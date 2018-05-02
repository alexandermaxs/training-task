package com.epam.task1.model;

import java.util.ArrayList;
import java.util.List;

public class Playground {
    private List<Toy> toys = new ArrayList<>();
    private int fixedSum;

    public Playground(int fixedSum) {
        this.fixedSum = fixedSum;
    }

    public void addToy(Toy toy){
        toys.add(toy);
    }

    public int getFixedSum() {
        return fixedSum;
    }

    public void setFixedSum(int fixedSum) {
        this.fixedSum = fixedSum;
    }

    public List<Toy> getToys() {
        return toys;
    }

    public void setToys(List<Toy> toys) {
        this.toys = toys;
    }
}
