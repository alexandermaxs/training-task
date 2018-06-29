package com.epam.task1.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import com.epam.task1.model.*;
import org.apache.log4j.Logger;

/**
 * Represents the creation of playground,
 * filling it with different toys according to summary price.
 * Provides structured search for several criteria.
 * @author {@linktourl https://github.com/alexandermaxs}
 */
public class Starter {

    private static Playground playground;

    private static final Logger LOGGER = Logger.getLogger(Starter.class);

    public static void main(String[] args) {
        LOGGER.info("Starting new program instance!");
        Scanner scanner = new Scanner(System.in);
        LOGGER.info("Please, enterArgument the offered sum for the playground (in BYN):");
        int fixedSum = enterArgument(scanner, "fixedSum");
        playground = new Playground(fixedSum);
        fill(playground, fixedSum);
        playground.setToys(sortByPrice(playground.getToys()));
        LOGGER.info("Descending price-list of all toys has been created.\nStarting the search...");
        LOGGER.info("Enter the bottom price border:");
        int botBorder = enterArgument(scanner, "botBorder");
        LOGGER.info("Enter the top price border:");
        int topBorder = enterArgument(scanner, "topBorder");
        LOGGER.info("Search output:");
        print(findPrice(botBorder, topBorder));
        LOGGER.info("END.");

    }

    /**
     * Matches user input to variable initialisation.
     * @param scanner casts through to avoid creation of several Scanner objects.
     * @param name is needed simply to create appropriate logging marks.
     * @exception IllegalArgumentException
     * @return int value.
     */
    public static int enterArgument(Scanner scanner, String name){
        StringBuilder message = new StringBuilder();
        int arg = 0;
        try {
            arg = scanner.nextInt();
            message.append("Argument ");
            message.append(name);
            message.append(" is ");
            message.append(arg);
            LOGGER.debug(message);
        }
        catch(Exception e){
            message.append("user entered illegal argument for ");
            message.append(name);
            message.append(": ");
            LOGGER.error(message, e);
        }
        return arg;
    }

    /**
     * Random based parameter filling algorithm.
     * First two arguments correlate with constants.
     * @see ToyType
     * @see ToySize
     * @return Toy object with non-zero parameters.
     */
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

    /**
     * Cycle of making efficient toy list.
     * Casts list into Playground object parameter.
     * @param fixedSum is pre-determined by user-input.
     * The last generated toy breaks the border to stop the cycle,
     * but never contains in the list.
     */
    public static void fill(Playground playground, int fixedSum){
        for(int i=0; i<=fixedSum;){
            Toy toy = generateToy();
            i = i + toy.getPrice();
            if (i<=fixedSum){
                playground.addToy(toy);
                playground.setFixedSum(i);
            }
            else {
                LOGGER.info("The playground successfully filled with toys");
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

    /**
     * Establishes the border range for the search.
     * @return Toy collection of suitable criteria.
     */
    public static List<Toy> findPrice(int bottom, int top){
        List<Toy> rightPrices = new ArrayList<>();
        for (Toy toy: playground.getToys()){
            if (toy.getPrice()>=bottom && toy.getPrice()<=top){
                rightPrices.add(toy);
            }
        }
        return rightPrices;
    }

    /**
     * Receives the list-collection of Toy objects.
     * Through the iteration provides Toy parameters info to the LOGGER.
     */
    public static void print(List<Toy> toys){
        for (Toy toy: toys){
            StringBuilder builder = new StringBuilder();
            builder.append(toy.getSize());
            builder.append(" ");
            builder.append(toy.getType());
            builder.append(" ");
            builder.append(toy.getPrice());
            builder.append(" BYN");
            LOGGER.info(builder);
        }
    }
}
