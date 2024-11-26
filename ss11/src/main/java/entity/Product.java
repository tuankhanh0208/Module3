package entity;

public class Product {
    private int id;
    private String name;
    private double price;
    private String description;
    private String author;

    public Product(int id, String name, double price, String description, String author) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.author = author;
    }

    public Product(String name, double price, String description, String author) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.author = author;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
