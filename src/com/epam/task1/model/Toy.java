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

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 31 * hash + price;
        hash = 31 * hash + (null == type ? 0 : type.hashCode());
        hash = 31 * hash + (null == size ? 0 : size.hashCode());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){return true;}
        if (null == obj){return false;}
        if (!(obj instanceof Toy)){return false;}
        Toy toy = (Toy) obj;
        if (price != toy.price){return false;}

        if (null == type && null == size){
            return (type == toy.type && size == toy.size);
        }
        if (null != type && null == size){
            return (type.equals(toy.type) && size == toy.size);
        }
        if (null == type && null != size){
            return (type == toy.type && size.equals(toy.size));
        }
        return (type.equals(toy.type) && size.equals(toy.size));
    }

    /**
     * @return example - Toy@1234:BIG#BALL#10
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getClass().getName());
        builder.append('@');
        builder.append(Integer.toHexString(hashCode()));
        builder.append(':');
        builder.append(size);
        builder.append('#');
        builder.append(type);
        builder.append('#');
        builder.append(price);
        return builder.toString();
    }
}

