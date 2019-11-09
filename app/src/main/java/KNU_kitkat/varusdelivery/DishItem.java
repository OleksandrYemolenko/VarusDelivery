package KNU_kitkat.varusdelivery;

import KNU_kitkat.varusdelivery.ui.Item;

public class DishItem extends Item {
    private int id, category;
    private double price;
    private String name, img, description;

    public DishItem(int id, String name, String img, int id1, int category, double price, String name1, String img1, String description) {
        super(id, name, img);
        this.id = id1;
        this.category = category;
        this.price = price;
        this.name = name1;
        this.img = img1;
        this.description = description;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
