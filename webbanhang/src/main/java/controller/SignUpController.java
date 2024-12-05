package controller;

import entity.Account;
import service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "signup-controller",value = "/signup")
public class SignUpController extends HttpServlet {
    private final  AccountService accountService = new AccountService();

    public SignUpController() throws SQLException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("style/page/login/signup.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user = req.getParameter("user");
        String pass = req.getParameter("pass");
        String re_pass = req.getParameter("repass");
        if (!pass.equals(re_pass)){
            req.setAttribute("mess" , "Password does not match Repeat Password");
            req.getRequestDispatcher("style/page/login/signup.jsp").forward(req,resp);
        } else {
            Account account = accountService.checkAccountExist(user);
            if (account == null){
                accountService.signup(user,pass);
                resp.sendRedirect("style/page/home/index.jsp");
            }else {
                req.setAttribute("mess" , "Username has existed ");
                req.getRequestDispatcher("style/page/login/signup.jsp").forward(req,resp);
            }
        }
    }
}
