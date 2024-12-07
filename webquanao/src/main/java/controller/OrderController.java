package controller;

import entity.Item;
import entity.Order;
import entity.Product;
import service.IProductService;
import service.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "OrderController", value = "/order")
public class OrderController extends HttpServlet {
    private final IProductService<Product> productService = new ProductService();

    public OrderController() throws SQLException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("remove") != null) {
            int removeId = Integer.parseInt(request.getParameter("remove"));
            HttpSession session = request.getSession();
            Order order = (Order) session.getAttribute("cart");
            if (order != null) {
                List<Item> itemList = order.getItems();
//                double totalPrice = order.getTotalPrice();
//                session.setAttribute("totalPrice", totalPrice);
                itemList.removeIf(item -> item.getProduct().getId() == removeId);
                session.setAttribute("cart", order);
            }
            Order cart = (Order) session.getAttribute("cart");
            if (cart != null) {
                int totalQuantity = cart.getItems().stream()
                        .mapToInt(Item::getQuantity)
                        .sum();
                session.setAttribute("totalQuantity", totalQuantity);
            }
            response.sendRedirect("/style/page/card/card.jsp");
            return;
        }

        HttpSession session = request.getSession();
        Order order = (Order) session.getAttribute("cart");
        request.setAttribute("order", order);
        request.getRequestDispatcher("/cart.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}