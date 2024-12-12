package controller;

import entity.*;
import service.AccountService;
import service.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@WebServlet(name = "productController",value="/products")
public class ProductController extends HttpServlet {
    private final ProductService productService = new ProductService();
    private final AccountService accountService = new AccountService();

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
            case "login":
                showLogin(req,resp);
                break;
            case "order":
                showOrder(req,resp);
                break;
            case "cart":
                showCart(req,resp);
                break;
            case "signup":
                showSignup(req,resp);
                break;
            default:
                showHome(req, resp);
                break;

        }
    }

    private void showSignup(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/style/page/login/signup.jsp").forward(req,resp);
    }

    private void showCart(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getParameter("remove") != null) {
            int removeId = Integer.parseInt(request.getParameter("remove"));
            HttpSession session = request.getSession();
            Order order = (Order) session.getAttribute("cart");
            if (order != null) {
                List<Item> itemList = order.getItems();
                itemList.removeIf(item -> item.getProduct().getId() == removeId);
                session.setAttribute("cart", order);
            }
            response.sendRedirect("/cart");
            return;
        }

        HttpSession session = request.getSession();
        Order order = (Order) session.getAttribute("cart");
        request.setAttribute("order", order);
        request.getRequestDispatcher("/style/page/cart/cart.jsp").forward(request, response);
    }

    private void showOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int quantity = 1;
        int id;

        if (request.getParameter("id") != null) {
            id = Integer.parseInt(request.getParameter("id"));
            Product product = productService.findById(id);

            if (product != null) {
                if (request.getParameter("quantity") != null) {
                    quantity = Integer.parseInt(request.getParameter("quantity"));
                }

                HttpSession session = request.getSession();

                if (session.getAttribute("cart") == null) {
                    Order order = new Order();
                    List<Item> itemList = new ArrayList<>();
                    Item item = new Item();
                    item.setQuantity(quantity);
                    item.setProduct(product);
                    item.setPrice(product.getPrice());
                    itemList.add(item);
                    order.setItems(itemList);
                    session.setAttribute("cart", order);
                } else {

                    Order order = (Order) session.getAttribute("cart");
                    List<Item> itemList = order.getItems();
                    boolean isProductInCart = false;
//                    double totalPrice = order.getTotalPrice();
//                    session.setAttribute("totalPrice", totalPrice);
                    for (Item item : itemList) {
                        if (item.getProduct().getId() == product.getId()) {
                            item.setQuantity(item.getQuantity() + quantity);
                            isProductInCart = true;
                            break;
                        }
                    }

                    if (!isProductInCart) {
                        Item item = new Item();
                        item.setQuantity(quantity);
                        item.setProduct(product);
                        item.setPrice(product.getPrice());
                        itemList.add(item);
                    }

                    session.setAttribute("cart", order);
                }
                Order cart = (Order) session.getAttribute("cart");
                if (cart != null) {
                    int totalQuantity = cart.getItems().stream()
                            .mapToInt(Item::getQuantity)
                            .sum();
                    session.setAttribute("totalQuantity", totalQuantity);
                }
            }


            response.sendRedirect(request.getContextPath() + "/products");
        } else {
            response.sendRedirect(request.getContextPath() + "/products");
        }
    }

    private void showLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/style/page/login/login.jsp").forward(req,resp);
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
        List<Categories> categories = productService.getAllCategories();
        req.setAttribute("listCC",categories);
        List<Product> products =productService.findByString(keyword,keyword);
        if (products == null || products.isEmpty()) {
            req.setAttribute("mess", "Không có sản phẩm tìm kiếm");
        } else {
            req.setAttribute("mess", null); // Xóa thông báo nếu có sản phẩm
        }
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
            case "signup":
                signup(req,resp);
                break;
            case "login":
                login(req,resp);
                break;
            default:
                showError(req,resp);
                break;
        }
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String username = req.getParameter("user");
        String password = req.getParameter("pass");
        System.out.println(username);
        System.out.println(password);
        Account account = accountService.login(username, password);
        if (account == null) {
            req.setAttribute("mess", "Wrong user or pass");
            req.getRequestDispatcher("/style/page/login/login.jsp").forward(req, resp);
        } else {
            HttpSession session = req.getSession();
            session.setAttribute("acc", account);
            session.setMaxInactiveInterval(10);
//            req.getRequestDispatcher("index.jsp").forward(req,resp);
            javax.servlet.http.Cookie userCookie = new javax.servlet.http.Cookie("username", account.getUser());
            userCookie.setMaxAge(7 * 24 * 60 * 60);
            resp.addCookie(userCookie);

            if ("admin".equals(account.getRole())) {
                resp.sendRedirect("/admin");
            } else {
                resp.sendRedirect("/products");

            }

        }
    }

    private void signup(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user = req.getParameter("user");
        String pass = req.getParameter("pass");
        String re_pass = req.getParameter("repass");
        if (!pass.equals(re_pass)){
            req.setAttribute("mess" , "Password does not match Repeat Password");
            req.getRequestDispatcher("/style/page/login/signup.jsp").forward(req,resp);
        } else {
            Account account = accountService.checkAccountExist(user);
            if (account == null){
                accountService.signup(user,pass);
                resp.sendRedirect("/products");
            }else {
                req.setAttribute("mess" , "Username has existed ");
                req.getRequestDispatcher("/style/page/login/signup.jsp").forward(req,resp);
            }
        }
    }


}
