package KNU_kitkat.varusdelivery;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class OrderItem implements Serializable {
    private ArrayList<DishItem> list = new ArrayList<>();
    private int status, id, method, idNumber, period;
    private double price;
    private Date date;

    public OrderItem(int id, int status, Date date, double price, int method, int idNumber, int period, ArrayList<DishItem> list) {
        this.id = id;
        this.status = status;
        this.date = date;
        this.price = price;
        this.method = method;
        this.idNumber = idNumber;
        this.period = period;
        this.list = list;
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

    public ArrayList<DishItem> getList() {
        return list;
    }

    public void setList(ArrayList<DishItem> list) {
        this.list = list;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMethod() {
        return method;
    }

    public void setMethod(int method) {
        this.method = method;
    }

    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }
}
