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

@WebServlet(name = "forgot-password-controller", value = "/forgotPassword")
public class ForgotPasswordController extends HttpServlet {
    private final AccountService accountService = new AccountService();

    public ForgotPasswordController() throws SQLException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("user");
        Account checkaccount = accountService.checkAccountExist(username);
        if (checkaccount != null) {
            req.setAttribute("user", username);
            req.getRequestDispatcher("forgotPassword.jsp").forward(req, resp);
        } else {
            req.setAttribute("mess", "Tài khoản không tồn tại.");
            req.getRequestDispatcher("forgotPassword.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("user");
        String newPassword = req.getParameter("newPassword");
        String confirmPassword = req.getParameter("confirmPassword");
        System.out.println("Username: " + username);
        System.out.println("New Password: " + newPassword);
        System.out.println("Confirm Password: " + confirmPassword);

        if (!confirmPassword.equals(newPassword)) {
            req.setAttribute("mess", "Mật khẩu không khớp. Vui lòng nhập lại.");
            req.getRequestDispatcher("forgotPassword.jsp").forward(req, resp);
        } else {
            boolean isPasswordReset = accountService.resetPassword(username, newPassword);
            if (isPasswordReset) {
                req.setAttribute("mess", "Mật khẩu đã được thay đổi thành công.");
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            } else {
                req.setAttribute("mess", "Có lỗi xảy ra, vui lòng thử lại.");
                req.getRequestDispatcher("forgotPassword.jsp").forward(req, resp);
            }
        }
    }
}
