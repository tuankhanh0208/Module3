package org.example.demo;

import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = "Khanh";
        request.setAttribute("name",name);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/my-severlet");
        dispatcher.forward(request,response);
    }


}