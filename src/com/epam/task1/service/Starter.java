package com.epam.task1.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.epam.task1.model.*;

public class Starter {

    private static Playground playground;

    public static void main(String[] args) throws Exception{
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, enter the offered sum for the playground (in BYN):");
        int fixedSum = scanner.nextInt();

        playground = new Playground(fixedSum);
        fill(playground, fixedSum);

        playground.setToys(sortByPrice(playground.getToys()));
        System.out.println("Descending price-list of all toys has been created.\nStarting the search...");

        System.out.println("Enter the bottom price border:");
        int botBorder = scanner.nextInt();
        System.out.println("Enter the top price border:");
        int topBorder = scanner.nextInt();
        System.out.println("Search output:");

        print(findPrice(botBorder, topBorder));

    }

    public static Toy generateToy(){
        int typeIndex = (int) (Math.random() * 4);
        int sizeIndex = (int) (Math.random() * 3);
        int priceRange = (3 + (int) (Math.random() * 3));
        Toy toy = new Toy();
        toy.setType(ToyType.values()[typeIndex].toString());
        toy.setSize(ToySize.values()[sizeIndex].toString());
        toy.setPrice(priceRange*(sizeIndex+1));
        return toy;
    }

    public static void fill(Playground playground, int fixedSum){
        for(int i=0; i<=fixedSum;){
            Toy toy = generateToy();
            i = i + toy.getPrice();
            if (i<=fixedSum){
                playground.addToy(toy);
                playground.setFixedSum(i);
            }
            else {
                System.out.println("The playground successfully filled with toys");
            }
        }
    }

    public static List<Toy> sortByPrice(List<Toy> toys){
        Collections.sort(toys);
        Collections.reverse(toys);
        return toys;
    }

    public static List<Toy> findSize(String size){
        List<Toy> rightSizes = new ArrayList<>();
        for (Toy toy: playground.getToys()){
            if (toy.getSize().equals(size)){
                rightSizes.add(toy);
            }
        }
        return rightSizes;
    }

    public static List<Toy> findType(String type){
        List<Toy> rightTypes = new ArrayList<>();
        for (Toy toy: playground.getToys()){
            if (toy.getType().equals(type)){
                rightTypes.add(toy);
            }
        }
        return rightTypes;
    }

    public static List<Toy> findPrice(int bottom, int top){
        List<Toy> rightPrices = new ArrayList<>();
        for (Toy toy: playground.getToys()){
            if (toy.getPrice()>=bottom && toy.getPrice()<=top){
                rightPrices.add(toy);
            }
        }
        return rightPrices;
    }

    public static void print(List<Toy> toys){
        for (Toy toy: toys){
            StringBuilder builder = new StringBuilder();
            builder.append(toy.getSize());
            builder.append(" ");
            builder.append(toy.getType());
            builder.append(" ");
            builder.append(toy.getPrice());
            builder.append(" BYN");
            System.out.println(builder);
        }
    }

}
