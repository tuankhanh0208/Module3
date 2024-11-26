package controller;

import entity.Product;
import service.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
@WebServlet(name = "productController" , value = "/products")
public class ProductController extends HttpServlet {
    private final ProductService productService= new ProductService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getParameter("path");
        if (path == null) {
            path = ""; // Set default value to avoid null
        }
        switch (path){
            case "remove":
                showRemove(req,resp);
                System.out.println(path);
                break;
            case "edit":
                showEdit(req,resp);
                break;
            case "error":
//                showError(req,resp);
                break;
            case "search":
                showSearch(req,resp);
                break;
            case "detail":
                showDetail(req,resp);
                break;
            default:
                showAll(req,resp);
                break;
        }

    }

    private void showDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int index = Integer.parseInt(req.getParameter("id"));
        Product product = productService.findById(index);
        req.setAttribute("product" ,product);
        req.getRequestDispatcher("detail.jsp").forward(req,resp);

    }

    private void showSearch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keyword = req.getParameter("keyword");
        List<Product> products = productService.findByString(keyword);
        if (keyword.isEmpty() || (products.isEmpty() || products == null)){
            req.setAttribute("mess","Khong tim thay san pham");

        }
        req.setAttribute("list", products);
        req.getRequestDispatcher("home.jsp").forward(req, resp);
    }

    private void showEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int index = Integer.parseInt(req.getParameter("id"));
        Product product = productService.findById(index);
        req.setAttribute("product",product);
         req.getRequestDispatcher("edit.jsp").forward(req,resp);;
//        req.setAttribute("list",resp);
    }

    private void showAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> list = productService.getAll();
        RequestDispatcher dispatcher = req.getRequestDispatcher("home.jsp");
        req.setAttribute("list",list);
        dispatcher.forward(req,resp);
    }

    private void showRemove(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int idIndex = Integer.parseInt(req.getParameter("id"));
        productService.delete(idIndex);
        resp.sendRedirect("/products");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getParameter("path");
        if (path == null) {
            path = ""; // Set default value to avoid null
        }
        switch (path){
            case "add":
                add(req,resp);
                break;
            case "edit":
                edit(req,resp);
                break;
        }


    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));
        String description = req.getParameter("description");
        String author = req.getParameter("author");
        Product product = new Product(name,price,description,author);
        productService.update(id,product);
        resp.sendRedirect("/products");
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));
        String description = req.getParameter("description");
        String author = req.getParameter("author");
        Product product = new Product(id,name,price,description,author);
        productService.add(product);
        resp.sendRedirect("/products");
        System.out.println("them san pham thanh cong");
    }
}
