package KNU_kitkat.varusdelivery.ui;

public class Item {
    private int id;
    private String name, img;


    public Item(int id, String name, String img) {
        this.id = id;
        this.name = name;
        this.img = img;
    }

    public Item() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
