package controller;

import entity.Product;
import service.IProductService;
import service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
@WebServlet(name = "product-controller",value = "/products")
public class ProductController extends HttpServlet {
    private final IProductService<Product> iProductService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getParameter("path");
        if (path==null){
            path="";
        }
        switch (path){
            case "add":
                showAdd(req,resp);
                break;
            case "edit":
                showEdit(req,resp);
                break;
            case "remove":
                showDelete(req,resp);
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
        Product product = iProductService.findById(index);
        req.setAttribute("product" ,product);
        req.getRequestDispatcher("detail.jsp").forward(req,resp);
    }

    private void showSearch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keyword = req.getParameter("keyword");
        List<Product> products = iProductService.findByString(keyword);
        if (keyword.isEmpty() || (products.isEmpty() || products == null)){
            req.setAttribute("mess","Khong tim thay san pham");

        }
        req.setAttribute("list", products);
        req.getRequestDispatcher("home.jsp").forward(req, resp);
    }

    private void showEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Product product = iProductService.findById(id);
        req.setAttribute("product",product);
        req.getRequestDispatcher("edit.jsp").forward(req,resp);
    }

    private void showDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        iProductService.delete(id);
        resp.sendRedirect("/products");
    }

    private void showAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("add.jsp").forward(req,resp);
    }

    private void showAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = iProductService.getAll();
        req.setAttribute("list",products);
        req.getRequestDispatcher("home.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getParameter("path");
        if (path==null){
            path="";
        }
        switch (path){
            case "add":
                add(req,resp);
                break;
            case "edit":
                edit(req,resp);
                break;
            default:
                showAll(req, resp);
        }
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));
        String description = req.getParameter("description");
        String author = req.getParameter("author");
        Product product = new Product(name,price,description,author);
        iProductService.update(id,product);
        resp.sendRedirect("/products");
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));
        String description = req.getParameter("description");
        String author = req.getParameter("author");
        Product product = new Product(id,name,price,description,author);
        iProductService.add(product);
        resp.sendRedirect("/products");
    }
}
