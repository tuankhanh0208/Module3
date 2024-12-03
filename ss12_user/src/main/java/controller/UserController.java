package controller;

import entity.User;
import service.IUserService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
@WebServlet(name = "user-controller", value = "/users")
public class UserController extends HttpServlet {
    private final IUserService<User> userService = new UserService();

    public UserController() throws SQLException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getParameter("path");
        String sortOrder = req.getParameter("sortOrder");
        System.out.println(path);
        if (path == null) {
            path = "";
        }
        switch (path){
            case "add":
                showAdd(req,resp);
                break;
            case "edit":
                showEdit(req,resp);
                break;
            case "remove":
                showRemove(req,resp);
                break;
            case"search":
                showSearch(req,resp);
                break;
            case "sort":
                showArrange(req,resp,sortOrder);
                break;
            default:
                showAll(req,resp);
                break;
        }
    }

    private void showArrange(HttpServletRequest req, HttpServletResponse resp ,String sortOrder) throws ServletException, IOException {
//        String sortOrder = req.getParameter("sortOrder");
        if (sortOrder  == null){
            sortOrder  = "asc";
        }
        List<User> users = userService.arrangeUser(sortOrder );
        req.setAttribute("users" ,users);
        req.setAttribute("sortOrder",sortOrder );
        req.getRequestDispatcher("index.jsp").forward(req,resp);
    }

    private void showSearch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String country = req.getParameter("keyword");
        List<User> users = userService.findByCountry(country);
        req.setAttribute("users",users);
        req.getRequestDispatcher("index.jsp").forward(req,resp);

    }

    private void showEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int index = Integer.parseInt(req.getParameter("id"));
//        User users =userService.findById(index);
        User users = userService.getUserById(index);
        req.setAttribute("users",users);

        req.getRequestDispatcher("edit.jsp").forward(req,resp);
    }

    private void showRemove(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int index = Integer.parseInt(req.getParameter("id"));
        userService.delete(index);
        resp.sendRedirect("/users");
    }

    private void showAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users",resp);
        req.getRequestDispatcher("add.jsp").forward(req,resp);
    }

    private void showAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = userService.getAll();
        req.setAttribute("users",users);
        req.getRequestDispatcher("index.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getParameter("path");
        System.out.println(path);
        if (path == null) {
            path = "";
        }
        switch (path){
            case "add":
                add(req,resp);
                break;
            case "edit":
                edit(req,resp);
                break;
            default:
                showAll(req,resp);
                break;
        }
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String country = req.getParameter("country");
        User  user = new User(id,name,email,country);
//        userService.update(id,user);
        userService.insertUser(user);
        resp.sendRedirect("/users/add.jsp");
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String country = req.getParameter("country");
        User  user = new User(name,email,country);
        userService.add(user);
        resp.sendRedirect("/users");
    }
}
