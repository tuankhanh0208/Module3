package controller;

import entity.Categories;
import entity.Product;
import service.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
@WebServlet(name = "productController",value="/products")
public class ProductController extends HttpServlet {
    private final ProductService productService = new ProductService();

    public ProductController() throws SQLException {
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getParameter("path");
        if(path == null){
            path ="";
        }
        switch (path) {
            case "add":
                showAdd(req,resp);
                break;
            case "delete":
                showDelete(req, resp);
                break;
            case "edit":
                showEdit(req,resp);
            case "search":
                showSearch(req,resp);
                break;
            case "category":
                showCategory(req,resp);
                break;
            case "searchSlide":
                showSearchSlide(req,resp);
                break;
            default:
                showHome(req, resp);
//                showCategory(req,resp);
                break;

        }
    }

    private void showSearchSlide(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String minPrice = req.getParameter("minPrice");
        String maxPrice = req.getParameter("maxPrice");

        if (minPrice == null || maxPrice ==null){
            resp.sendRedirect("error.jsp");
            return;
        }
        double min = Double.parseDouble(minPrice);
        double max = Double.parseDouble(maxPrice);

        List<Product> products = productService.getProductByPriceRange(min,max);
        List<Categories> categories = productService.getAllCategories();
        req.setAttribute("listCC",categories);
        req.setAttribute("list",products);
        req.getRequestDispatcher("index.jsp").forward(req,resp);
    }

    private void showCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cateID = req.getParameter("cid");
        List<Product> products = productService.getProductByCID(cateID);
        List<Categories> categories = productService.getAllCategories();
        req.setAttribute("listCC",categories);
        req.setAttribute("list",products);
        req.setAttribute("tag",cateID);
        req.getRequestDispatcher("index.jsp").forward(req,resp);
    }

    private void showAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("add.jsp").forward(req,resp);
    }

    private void showSearch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        String keyword =req.getParameter("keyword");
        List<Product> products =productService.findByString(keyword);
//        PrintWriter out = resp.getWriter();
//        for (Product product : products){
//           out.println("<div class=\"card\" style=\"width: 18rem; margin-top: 20px\">\n" +
//                    "                            <img src=\""+product.getImage()+"\" class=\"card-img-top\" alt=\"...\"/>\n" +
//                    "                            <div class=\"card-body\">\n" +
//                    "                                <h4 class=\"card-title\">"+product.getTitle()+"</h4>\n" +
//                    "                                <h3 class=\"card-title\">$ "+product.getPrice()+"</h3>\n" +
//                    "                                <p class=\"card-text\">"+product.getDescription()+"</p>\n" +
//                    "                                <p class=\"card-text\"><small class=\"text-body-secondary\">Last updated 3 mins ago</small></p>\n" +
//                    "                            </div>\n" +
//                    "                            <div style=\"display: flex; justify-content: space-between;\">\n" +
//                    "                                <a href=\"http://localhost:8080/products?path=edit&id=${item.id}\"\n" +
//                    "                                   class=\"btn btn-primary edit\">Sửa</a>\n" +
//                    "                                <label for=\"delete-${loop.index}\" class=\"btn btn-primary red\">Xóa</label>\n" +
//                    "                                <input type=\"checkbox\" id=\"delete-${loop.index}\" class=\"confirm-checkbox\"/>\n" +
//                    "                                <div class=\"confirm-modal\">\n" +
//                    "                                    <div class=\"modal-content\">\n" +
//                    "                                        <p>Bạn có chắc chắn muốn xóa?</p>\n" +
//                    "                                        <a href=\"http://localhost:8080/products?path=delete&id=${item.id}\" class=\"btn btn-danger\">Xác nhận</a>\n" +
//                    "                                        <label for=\"delete-${loop.index}\" class=\"btn btn-secondary\">Hủy</label>\n" +
//                    "                                    </div>\n" +
//                    "                                </div>\n" +
//                    "                            </div>\n" +
//                    "                        </div>");
//        }
        req.setAttribute("list" ,products);
        req.getRequestDispatcher("index.jsp").forward(req,resp);
    }

    private void showEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idIndex = Integer.parseInt(req.getParameter("id"));
        Product product = productService.findById(idIndex);
        req.setAttribute("product",product);
        req.getRequestDispatcher("edit.jsp").forward(req,resp);
    }

    private void showDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int idIndex = Integer.parseInt(req.getParameter("id"));
        productService.delete(idIndex);
        resp.sendRedirect("/products?path=home");
    }

    private void showHome(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> list = productService.getAll();
        List<Categories> categories = productService.getAllCategories();
        req.setAttribute("listCC",categories);
        req.setAttribute("list" ,list);
        RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
        dispatcher.forward(req,resp);
    }

    private void showError(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("errorMessage","Bad Request - 404");
        RequestDispatcher dispatcher = req.getRequestDispatcher("/error.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path =req.getParameter("path");
        if(path == null){
            path ="";
        }
        switch (path){
            case "add":
                add(req,resp);
                break;
            case "edit":
                edit(req,resp);
                break;
            case "error":
                showError(req,resp);
                break;
            default:
                showHome(req,resp);
                break;
        }
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        double price = Double.parseDouble(req.getParameter("price"));
        String title = req.getParameter("title");
        String image = req.getParameter("image");

//        boolean hasError = false;
//        if (name == null || !name.matches("^[a-zA-Z\\s]{5,50}$") ){
//            req.setAttribute(" nameError","Name cannot be empty");
//            hasError = true;
//        }
//        if (description == null || description.trim().isEmpty()) {
//           req.setAttribute( "descriptionError" , "Description cannot be empty");
//            hasError = true;
//        }
//        if (title == null || title.trim().isEmpty()) {
//           req.setAttribute( "titleError" ,"Title cannot be empty");
//            hasError = true;
//        }
//
//        try {
//            if (price <= 0) {
//               req.setAttribute( "priceError" ,"Price must be greater than 0");
//                hasError = true;
//            }
//        } catch (NumberFormatException e) {
//            req.setAttribute( "priceError" ,"Invalid price format");
//            hasError = true;
//        }
//
//        if (image == null || image.trim().isEmpty()) {
//            req.setAttribute("imageError", "Image cannot be empty");
//            hasError = true;
//        } else if (!image.endsWith(".jpg") && !image.endsWith(".jpeg")) {
//            req.setAttribute("imageError", "Image must be a .jpg or .jpeg file");
//            hasError = true;
//        }
//
//        if (hasError) {
//            req.getRequestDispatcher("add.jsp").forward(req, resp);
//            return;
//        }
        Product product = new Product(name,description,title,price,image);
        productService.add(product);
        resp.sendRedirect("/products");
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String title = req.getParameter("title");
        double price = Double.parseDouble(req.getParameter("price"));
        String image = req.getParameter("image");
        Product product = new Product(name,description,title,price,image);
        productService.update(id,product);
        resp.sendRedirect("/products");
    }


}
