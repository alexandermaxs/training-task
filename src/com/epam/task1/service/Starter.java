package com.epam.task1.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import com.epam.task1.model.*;
import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;
import org.apache.log4j.xml.DOMConfigurator;

public class Starter {

    private static Playground playground;

    static Logger logger = Logger.getLogger(Starter.class);

    static {
        new DOMConfigurator().doConfigure("log4j.xml", LogManager.getLoggerRepository());
    }

    public static void main(String[] args) throws Exception{
        logger.info("Starting new program instance!");
        Scanner scanner = new Scanner(System.in);
        logger.info("Please, enter the offered sum for the playground (in BYN):");
        int fixedSum = enter(scanner, "fixedSum");
        playground = new Playground(fixedSum);
        fill(playground, fixedSum);
        playground.setToys(sortByPrice(playground.getToys()));
        logger.info("Descending price-list of all toys has been created.\nStarting the search...");
        logger.info("Enter the bottom price border:");
        int botBorder = enter(scanner, "botBorder");
        logger.info("Enter the top price border:");
        int topBorder = enter(scanner, "topBorder");
        logger.info("Search output:");
        print(findPrice(botBorder, topBorder));
        logger.info("END.");

    }

    public static int enter(Scanner scanner, String name){
        StringBuilder message = new StringBuilder();
        int arg = 0;
        try {
            arg = scanner.nextInt();
            message.append("Argument ");
            message.append(name);
            message.append(" is ");
            message.append(arg);
            logger.debug(message);
        }
        catch(Exception e){
            message.append("user entered illegal argument for ");
            message.append(name);
            message.append(": ");
            logger.error(message, e);
        }
        return arg;
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
                logger.info("The playground successfully filled with toys");
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
            logger.info(builder);
        }
    }

}
