package org.example.ss9;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/display-discount")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        String description = request.getParameter("description");
//        double price = Double.parseDouble(request.getParameter("price"));
//        int percent = Integer.parseInt(request.getParameter("percent"));
//
//        double discountAmount = price*percent/100;
//        double discountPrice = price -discountAmount;
//
//        response.getWriter().println("<html><body>");
//        response.getWriter().println("<h1>Discount Calculator Result</h1>");
//        response.getWriter().println("<p><strong>Product Description:</strong> " + description + "</p>");
//        response.getWriter().println("<p><strong>List Price:</strong> $" + price + "</p>");
//        response.getWriter().println("<p><strong>Discount Percent:</strong> " + percent + "%</p>");
//        response.getWriter().println("<p><strong>Discount Amount:</strong> $" + discountAmount + "</p>");
//        response.getWriter().println("<p><strong>Discount Price:</strong> $" + discountPrice + "</p>");
//        response.getWriter().println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        int percent = Integer.parseInt(request.getParameter("percent"));

        double discountAmount = price*percent*0.01;
        double discountPrice = price -discountAmount;

        response.getWriter().println("<html><body>");
        response.getWriter().println("<h1>Discount Calculator Result</h1>");
        response.getWriter().println("<p><strong>Product Description:</strong> " + description + "</p>");
        response.getWriter().println("<p><strong>List Price:</strong> $" + price + "</p>");
        response.getWriter().println("<p><strong>Discount Percent:</strong> " + percent + "%</p>");
        response.getWriter().println("<p><strong>Discount Amount:</strong> $" + discountAmount + "</p>");
        response.getWriter().println("<p><strong>Discount Price:</strong> $" + discountPrice + "</p>");
        response.getWriter().println("</body></html>");
    }
}