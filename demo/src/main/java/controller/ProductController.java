package controller;

import entity.Product;
import service.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "productController" ,value = "/products")
public class ProductController extends HttpServlet {
private final ProductService productService = new ProductService();

    public ProductController() throws SQLException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path =req.getParameter("path");
        switch (path){
            case "home":
                showHome(req,resp);
                break;
            case "add":
                showAdd(req,resp );
                break;
            case "delete":
                showDelete(req,resp);
                break;
            case "edit":
                showEdit(req,resp);
                break;
            case "error":
                showError(req,resp);
                break;
            case "search":
                showSearch(req,resp);
                break;
            default:
                showError(req,resp);
        }
    }

    private void showSearch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keyword = req.getParameter("keyword");
        List<Product> products = productService.findByString(keyword);
        req.setAttribute("list", products);
        req.getRequestDispatcher("/product-view/home.jsp").forward(req, resp);
    }

    private void showError(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("errorMessage","Bad Request - 404");
        RequestDispatcher dispatcher = req.getRequestDispatcher("/product-view/error.jsp");
        dispatcher.forward(req,resp);
    }

    private void showEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idEdit = Integer.parseInt(req.getParameter("id"));
        Product product = productService.findById(idEdit);
        req.setAttribute("product",product);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/product-view/edit.jsp");
        dispatcher.forward(req,resp);
        req.setAttribute("list",resp);
    }

    private void showDelete(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        int idIndex = Integer.parseInt(req.getParameter("id"));
        productService.delete(idIndex);
        resp.sendRedirect("/products?path=home");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path =req.getParameter("path");
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

    private void edit(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
       int id = Integer.parseInt(req.getParameter("id"));
        double price = Double.parseDouble(req.getParameter("price"));
        String name = req.getParameter("name");
        String img = req.getParameter("img");
        Product product = new Product(name,price,img);

        productService.update(id,product);
        resp.sendRedirect("/products?path=home");
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        int id = Integer.parseInt(req.getParameter("id"));
        double price = Double.parseDouble(req.getParameter("price"));
        String name = req.getParameter("name");
        String img = req.getParameter("img");

        Product product = new Product(name,price,img);
        productService.add(product);
        resp.sendRedirect("/products?path=home");
    }

    private void showAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/product-view/add.jsp");
        dispatcher.forward(req,resp);
        req.setAttribute("list",resp);

    }


    private void showHome(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        List<Product> list = productService.getAll();
        RequestDispatcher dispatcher = req.getRequestDispatcher("/product-view/home.jsp");
        req.setAttribute("list",list);
        dispatcher.forward(req,resp);
    }
}
