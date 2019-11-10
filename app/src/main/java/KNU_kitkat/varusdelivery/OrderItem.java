package KNU_kitkat.varusdelivery;

import java.util.Date;

public class OrderItem {
    private int status, id;
    private double price;
    private Date date;

    public OrderItem(int id, int status, Date date, double price) {
        this.id = id;
        this.status = status;
        this.date = date;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
