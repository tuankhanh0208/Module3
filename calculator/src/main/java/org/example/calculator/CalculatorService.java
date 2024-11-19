package org.example.calculator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "calculatorService" ,value = "/calculate")
public class CalculatorService extends HttpServlet {
    private Calculator calculator = new Calculator();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        double a = Double.parseDouble(req.getParameter("a"));
        double b = Double.parseDouble(req.getParameter("b"));
        String path = req.getParameter("operator");
        String message ="";
        double result=0;
        switch (path){
            case "+":
                result = calculator.add(a,b);
                break;
            case "-":
                result = calculator.sub(a,b);
                break;
            case "*":
                result = calculator.mul(a,b);
                break;
            case "/":
                result =calculator.div(a,b);
                break;
            default:
                message="Toán tử không hợp lệ";
        }
        req.setAttribute("result" ,result);
        req.setAttribute("message" ,message);
        req.getRequestDispatcher("index.jsp").forward(req,resp);
    }
}
