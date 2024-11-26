package entity;

public class Product {
    private int produtct_id;
    private String name;
    private String description;
    private double price;
    private String title;

    public Product(int produtct_id, String name, String description, double price, String title) {
        this.produtct_id = produtct_id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.title = title;
    }

    public Product(String name, String description, double price, String title) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.title = title;
    }

    public int getProdutct_id() {
        return produtct_id;
    }

    public void setProdutct_id(int produtct_id) {
        this.produtct_id = produtct_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
