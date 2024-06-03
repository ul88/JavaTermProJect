package org.ul88.object;

public class MoneyObject {
    private int amount; //돈 양
    private int remaining; // 남은 돈

    public MoneyObject(int amount, int remaining) {
        this.amount = amount;
        this.remaining = remaining;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getRemaining() {
        return remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }
}
