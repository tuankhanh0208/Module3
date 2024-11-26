package service;

import entity.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductService implements IProductService<Product> {
      private final   List<Product> productList = new ArrayList<>();
    public ProductService() {
        productList.add(new Product(1, "Bánh", 123, "Bánh này rất ngọt", "Khanh"));
        productList.add(new Product(2, "Kẹo", 456, "Kẹo này rất ngọt", "Khanh"));
        productList.add(new Product(3, "Sữa", 789, "Sữa này rất ngon", "Khanh"));
        productList.add(new Product(4, "Nước", 321, "Nước này rất mát", "Khanh"));
        productList.add(new Product(5, "Trái cây", 654, "Trái cây này rất tươi", "Khanh"));
    }
    @Override
    public void add(Product product) {
        this.productList.add(product);
    }

    @Override
    public void delete(int id) {
        int index = findById(id).getId();
        productList.remove(index);

    }

    @Override
    public void update(int id, Product product) {
        int index = findById(id).getId();
        productList.set(index,product);

    }

    @Override
    public List<Product> getAll() {
        return productList;
    }

    @Override
    public List<Product> findByString(String name) {
        List<Product> products = new ArrayList<>();
        String lowerCaseName = name.toLowerCase();
        for (Product product : productList) {
            if (product.getName().toLowerCase().contains(lowerCaseName)) {
                products.add(product);
            }
        }
        return products;
    }

    @Override
    public Product findById(int id) {
        for (Product product : productList){
            if(product.getId() == id){
                return product;
            }
        }
        return null;
    }
}
