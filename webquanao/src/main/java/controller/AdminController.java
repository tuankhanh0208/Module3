package controller;

import entity.Product;
import service.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "AdminController", value = "/admin")
public class AdminController extends HttpServlet {
    private final ProductService productService = new ProductService();

    public AdminController() throws SQLException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getParameter("path");
        if (path == null) {
            path = "";
        }
        switch (path) {
            case "add":
                showAdd(request, response);
                break;
            case "edit":
                showEdit(request, response);
                break;
            case "confirm":
                showConfirmDelete(request, response);
                break;
            case "search":
                showSearch(request,response);
                break;
            default:
                showHomeAdmin(request, response);
//                showError(request,response);
                break;
        }
    }

    private void showSearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        String keyword =request.getParameter("keyword");
        List<Product> products =productService.findByString(keyword,keyword);
        request.setAttribute("list" ,products);
        request.getRequestDispatcher("style/page/admin/home.jsp").forward(request,response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String[] productId = request.getParameterValues("selectedProduct");
        if (productId != null && productId.length > 0) {
            for (String s : productId) {
                int id = Integer.parseInt(s);
                productService.delete(id);
                System.out.println("Thành công");
            }
        }
        response.sendRedirect("/admin");
    }

    private void showConfirmDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] productId = request.getParameterValues("selectedProduct");

        if (productId != null && productId.length > 0) {
            request.setAttribute("selectedProductIds", Arrays.asList(productId));
            request.getRequestDispatcher("style/page/admin/confirm.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Bạn chưa chọn sản phẩm nào để xóa.");
            request.getRequestDispatcher("style/page/admin/home.jsp").forward(request, response);
        }

    }

    private void showEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idIndex = Integer.parseInt(request.getParameter("id"));
        Product product = productService.findById(idIndex);
        request.setAttribute("product",product);
        request.getRequestDispatcher("style/page/editProduct/edit.jsp").forward(request,response);
    }

    private void showAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("style/page/addProduct/add.jsp").forward(request,response);
    }

    private void showHomeAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> list = productService.getAll();
        request.setAttribute("list" ,list);
        RequestDispatcher dispatcher = request.getRequestDispatcher("style/page/admin/home.jsp");
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getParameter("path");
        if (path == null){
            path="";
        }
        switch (path){
            case "add":
                add(request,response);
                break;
            case "confirm":
                showConfirmDelete(request,response);
                break;
            case "delete":
                delete(request, response);
                break;
            case "edit":
                edit(request,response);
                break;
            default:
                showError(request,response);
                break;
        }
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String title = request.getParameter("title");
        double price = Double.parseDouble(request.getParameter("price"));
        String image = request.getParameter("image");
        int idCategory = Integer.parseInt(request.getParameter("categoryMethod"));
        Product product = new Product(name,description,title,price,image,idCategory);
        productService.update(id,product);
        response.sendRedirect("/admin");
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        String title = request.getParameter("title");
        String image = request.getParameter("image");
        int idCategory = Integer.parseInt(request.getParameter("categoryMethod"));
        Product product = new Product(name,description,title,price,image,idCategory);
        productService.add(product);
        response.sendRedirect("/admin");
    }

    private void showError(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("errorMessage","Bad Request - 404");
        RequestDispatcher dispatcher = request.getRequestDispatcher("style/page/errors/error.jsp");
        dispatcher.forward(request,response);
        
    }
}