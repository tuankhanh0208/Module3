package controller;

import entity.Account;
import service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "login-controller", value = "/login")
public class LoginController extends HttpServlet {
    private final AccountService accountService = new AccountService();

    public LoginController() throws SQLException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/style/page/login/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
}
