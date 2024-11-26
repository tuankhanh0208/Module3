package org.example.ss10_dskh;

import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.util.ArrayList;
import java.util.List;
@WebServlet(name = "helloServlet", value = "/customer")
public class HelloServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer("Tran Van A", "1990-05-15", "Ho Chi Minh", "https://th.bing.com/th/id/OIP.Nia8NcZLgly5CjOqGkokZgHaJO?rs=1&pid=ImgDetMain"));
        customers.add(new Customer("Tran Van A", "1990-05-15", "Ho Chi Minh", "https://th.bing.com/th/id/OIP.Nia8NcZLgly5CjOqGkokZgHaJO?rs=1&pid=ImgDetMain"));
        customers.add(new Customer("Tran Van A", "1990-05-15", "Ho Chi Minh", "https://th.bing.com/th/id/OIP.Nia8NcZLgly5CjOqGkokZgHaJO?rs=1&pid=ImgDetMain"));
        customers.add(new Customer("Tran Van A", "1990-05-15", "Ho Chi Minh", "https://th.bing.com/th/id/OIP.Nia8NcZLgly5CjOqGkokZgHaJO?rs=1&pid=ImgDetMain"));
        System.out.println("Customer List: " + customers);
        request.setAttribute("listCustomer" ,customers);
       request.getRequestDispatcher("index.jsp").forward(request,response);
//        System.out.println(customers + "Hello");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer("Tran Van A", "1990-05-15", "Ho Chi Minh", "https://th.bing.com/th/id/OIP.Nia8NcZLgly5CjOqGkokZgHaJO?rs=1&pid=ImgDetMain"));
        customers.add(new Customer("Tran Van A", "1990-05-15", "Ho Chi Minh", "https://th.bing.com/th/id/OIP.Nia8NcZLgly5CjOqGkokZgHaJO?rs=1&pid=ImgDetMain"));
        customers.add(new Customer("Tran Van A", "1990-05-15", "Ho Chi Minh", "https://th.bing.com/th/id/OIP.Nia8NcZLgly5CjOqGkokZgHaJO?rs=1&pid=ImgDetMain"));
        customers.add(new Customer("Tran Van A", "1990-05-15", "Ho Chi Minh", "https://th.bing.com/th/id/OIP.Nia8NcZLgly5CjOqGkokZgHaJO?rs=1&pid=ImgDetMain"));
        System.out.println("Customer List: " + customers);
        req.setAttribute("listCustomer" ,customers);
        req.getRequestDispatcher("index.jsp").forward(req,resp);
    }
}