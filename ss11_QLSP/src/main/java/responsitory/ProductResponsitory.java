package responsitory;

import entity.Product;
import service.IProductService;
import service.ProductService;

import java.util.ArrayList;
import java.util.List;

public class ProductResponsitory implements IProductResponsitory<Product> {
//    private final IProductService<Product> iProductService = new ProductService();
    private final List<Product> productList = new ArrayList<>();
    public ProductResponsitory(){
        productList.add(new Product( 1,"Bánh", 123, "Bánh này rất ngọt", "Khanh"));
        productList.add(new Product(2, "Kẹo", 456, "Kẹo này rất ngọt", "Khanh"));
        productList.add(new Product( 3,"Sữa", 789, "Sữa này rất ngon", "Khanh"));
        productList.add(new Product(4, "Nước", 321, "Nước này rất mát", "Khanh"));
        productList.add(new Product(5, "Trái cây", 654, "Trái cây này rất tươi", "Khanh"));
    }
    @Override
    public List<Product> getAll() {
        return productList;
    }

    @Override
    public void add(Product product) {
        productList.add(product);
    }

    @Override
    public void delete(int id) {
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getId() == id) {
                productList.remove(i);
                return;
            }
        }
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

    @Override
    public void update(int id, Product product) {
        int index = findById(id).getId();
        productList.set(index,product);
    }

    @Override
    public List<Product> findByString(String keyword) {
        List<Product> products = new ArrayList<>();
        String lowerCaseName = keyword.toLowerCase();
        for (Product product : productList) {
            if (product.getName().toLowerCase().contains(lowerCaseName)) {
                products.add(product);
            }
        }
        return products;
    }

}
