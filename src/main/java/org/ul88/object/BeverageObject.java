package org.ul88.object;

public class BeverageObject {
    private String name; // 이름
    private int price; // 가격
    private int remaining; // 개수

    public BeverageObject(String name, int price, int remaining) {
        this.name = name;
        this.price = price;
        this.remaining = remaining;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRemaining() {
        return remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }
}
