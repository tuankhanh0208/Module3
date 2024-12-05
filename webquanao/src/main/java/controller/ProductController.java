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
            case "search":
                showSearch(req,resp);
                break;
            case "category":
                showCategory(req,resp);
                break;
            case "searchSlide":
                showSearchSlide(req,resp);
                break;
            case "detail":
                showDetail(req,resp);
                break;
            default:
                showHome(req, resp);
                break;

        }
    }

    private void showDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Product product = productService.findById(id);
        List<Categories> categories = productService.getAllCategories();
        req.setAttribute("products",product);
        req.setAttribute("listCC",categories);
        req.getRequestDispatcher("style/page/home/detail.jsp").forward(req,resp);
    }

    private void showSearchSlide(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String minPrice = req.getParameter("minPrice");
        String maxPrice = req.getParameter("maxPrice");

        if (minPrice == null || maxPrice ==null){
            resp.sendRedirect("style/page/errors/error.jsp");
            return;
        }
        double min = Double.parseDouble(minPrice);
        double max = Double.parseDouble(maxPrice);

        List<Product> products = productService.getProductByPriceRange(min,max);
        List<Categories> categories = productService.getAllCategories();
        req.setAttribute("listCC",categories);
        req.setAttribute("list",products);
        req.getRequestDispatcher("style/page/home/index.jsp").forward(req,resp);
    }

    private void showCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cateID = req.getParameter("cid");
        List<Product> products = productService.getProductByCID(cateID);
        List<Categories> categories = productService.getAllCategories();
        req.setAttribute("listCC",categories);
        req.setAttribute("list",products);
        req.setAttribute("tag",cateID);
        req.getRequestDispatcher("style/page/home/index.jsp").forward(req,resp);
    }


    private void showSearch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        String keyword =req.getParameter("keyword");
        List<Product> products =productService.findByString(keyword,keyword);
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
        req.getRequestDispatcher("style/page/home/index.jsp").forward(req,resp);
    }
    private void showHome(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> list = productService.getAll();
        List<Categories> categories = productService.getAllCategories();
        req.setAttribute("listCC",categories);
        req.setAttribute("list" ,list);
        RequestDispatcher dispatcher = req.getRequestDispatcher("style/page/home/index.jsp");
        dispatcher.forward(req,resp);
    }

    private void showError(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("errorMessage","Bad Request - 404");
        RequestDispatcher dispatcher = req.getRequestDispatcher("style/page/errors/error.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path =req.getParameter("path");
        if(path == null){
            path ="";
        }
        switch (path){
            default:
                showError(req,resp);
                break;
        }
    }


}
