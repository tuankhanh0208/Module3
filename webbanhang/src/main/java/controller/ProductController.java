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
            default:
                showHome(req, resp);

        }
    }

    private void showAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("add.jsp").forward(req,resp);
    }

    private void showSearch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keyword =req.getParameter("keyword");
        List<Product> products =productService.findByString(keyword);
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
        RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
        req.setAttribute("list" ,list);
        req.setAttribute("listCC",categories);
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
                showError(req,resp);
                break;
        }
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        double price = Double.parseDouble(req.getParameter("price"));
        String title = req.getParameter("title");
        String image = req.getParameter("image");

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
