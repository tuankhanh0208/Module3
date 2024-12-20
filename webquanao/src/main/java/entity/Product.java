package entity;

public class Product {
    private int id;
    private String name;
    private String description;
    private String title;
    private double price;
    private String image;
    private Categories idCategory ;

    public Product() {
    }

    public Product( String name, String description, String title, double price, String image, Categories idCategory) {
        this.name = name;
        this.description = description;
        this.title = title;
        this.price = price;
        this.image = image;
        this.idCategory = idCategory;
    }

    public Product(String name, String description, String title, double price, String image) {
        this.name = name;
        this.description = description;
        this.title = title;
        this.price = price;
        this.image = image;
    }

    public Product(int id, String name, String description, String title, double price, String image,Categories idCategory) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.title = title;
        this.price = price;
        this.image = image;
        this.idCategory=idCategory;
    }

    public Categories getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Categories idCategory) {
        this.idCategory = idCategory;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}