package logic.store;

import java.util.Objects;

public class Item {
    private String name;
    private int price;
    private int amount;

    public Item(String name, int price, int amount) {
        this.setName(name);
        this.setPrice(price);
        this.setAmount(amount);
    }

    public Item(Item templateItem, int amount) {
        this.setName(templateItem.getName());
        this.setPrice(templateItem.getPrice());
        this.setAmount(amount);
    }

    @Override
    public String toString() {
        return this.getName() + ": x" + this.getAmount();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.isBlank()) {
            name = "UnknownItem";
        }
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        if (price < 0) {
            price = 0;
        }
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        if (amount < 0) {
            amount = 0;
        }
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(getName(), item.getName());
    }

}
