package controller;

import entity.Categories;
import entity.Product;
import service.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
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
            case "login":
                showLogin(request,response);
                break;
            case "signup":
                showSignup(request,response);
                break;
            default:
                showHomeAdmin(request, response);
//                showError(request,response);
                break;
        }
    }

    private void showSignup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/style/page/login/signup.jsp").forward(request,response);
    }

    private void showLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/style/page/login/login.jsp").forward(request,response);
    }

    private void showSearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        String keyword =request.getParameter("keyword");
        System.out.println(keyword);
        String price = request.getParameter("keyword");
        double v = 0;
        try {
            if (price != null && !price.isEmpty()){
                v=Double.parseDouble(price);
            }
        }catch (NumberFormatException e){
            v=0;
        }
        List<Product> products =productService.findByTest(keyword,v);
        if (products == null || products.isEmpty()) {
            request.setAttribute("mess", "Không có sản phẩm tìm kiếm");
        } else {
            request.setAttribute("mess", null);
        }
        request.setAttribute("list" ,products);
        request.setAttribute("txt" ,keyword);
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
        List<Categories> categoriesList = productService.getAllCategories();
        request.setAttribute("categoriesList", categoriesList);
        request.setAttribute("product",product);
        request.getRequestDispatcher("style/page/editProduct/edit.jsp").forward(request,response);
    }

    private void showAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Categories> categoriesList = productService.getAllCategories();
        request.setAttribute("categoriesList", categoriesList);
        request.getRequestDispatcher("style/page/addProduct/add.jsp").forward(request,response);
    }

    private void showHomeAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> list = productService.getAllAdmin();
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
            case "login":
                showLogin(request,response);
                break;
            case "signup":
                signup(request,response);
                break;
            default:
                showError(request,response);
                break;
        }
    }

    private void signup(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("/admin?path=login");
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String title = request.getParameter("title");
        double price = Double.parseDouble(request.getParameter("price"));
        String image = request.getParameter("image");
        int idCategory = Integer.parseInt(request.getParameter("categoryMethod"));
        Categories categories = new Categories(idCategory);
        System.out.println(id+ "" +idCategory);
        Product product = new Product(name,description,title,price,image,categories);
        productService.update(id,product);
        response.sendRedirect("/admin");
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        String title = request.getParameter("title");
        String image = request.getParameter("image");
        String categoryMethod = request.getParameter("categoryMethod");
        Categories categories;

        if ("other".equals(categoryMethod)) {
            String otherCategory = request.getParameter("otherCategory");

            categories = new Categories(otherCategory);
            int newCategoryId = productService.addCategory(categories);
            categories.setCid(newCategoryId);
        } else {
            int idCategory = Integer.parseInt(categoryMethod);
            categories = new Categories(idCategory);
        }

        Product product = new Product(name, description, title, price, image, categories);
        productService.add(product);
        response.sendRedirect("/admin");
    }


    private void showError(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("errorMessage","Bad Request - 404");
        RequestDispatcher dispatcher = request.getRequestDispatcher("style/page/errors/error.jsp");
        dispatcher.forward(request,response);
        
    }
}