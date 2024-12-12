package controller;

import entity.Room;
import service.RoomService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@WebServlet(name = "room-controller" , value = "/rooms")
public class RoomController extends HttpServlet {
    private final RoomService roomService = new RoomService();

    public RoomController() throws SQLException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getParameter("path");
        if (path == null) {
            path = "";
        }
        switch (path) {
            case "add":
                showAdd(req, resp);
                break;
//            case "delete":
//                delete(req, resp);
//                break;
            case "edit":
                showEdit(req, resp);
                break;
            case "confirm":
                showConfirmDelete(req,resp);
                break;
            case "search":
                showSearch(req, resp);
                break;
            default:
                showAll(req, resp);
                break;

        }

    }

    private void showSearch(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String keyword = req.getParameter("keyword");

        List<Room> rooms = roomService.findBySearch(keyword, keyword, keyword);
        req.setAttribute("list", rooms);
        req.getRequestDispatcher("home.jsp").forward(req, resp);
        System.out.println(keyword);
    }


   private void showEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String roomId = req.getParameter("idRoom");
        Room room = roomService.findById(roomId);
        System.out.println(roomId);
        req.setAttribute("rooms", room);
        req.getRequestDispatcher("edit.jsp").forward(req, resp);
//        req.setAttribute("list", resp);
    }

    private void showAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Room> rooms = roomService.getAll();
        req.setAttribute("list", rooms);
        req.getRequestDispatcher("home.jsp").forward(req, resp);
    }

    private void showAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("add.jsp").forward(req, resp);
//        req.setAttribute("list" ,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getParameter("path");
        if (path == null) {
            path = "";
        }
        switch (path) {
            case "add":
                add(req, resp);
                break;
            case "delete":
                delete(req, resp);
                break;
            case "confirm":
                showConfirmDelete(req,resp);
                break;
            case "edit":
                    edit(req, resp);
                break;
            case "search":
                showSearch(req, resp);
                break;
            default:
                showError(req, resp);
                break;
        }
    }


    private void showConfirmDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String[] roomIds = req.getParameterValues("selectedRooms");

        // Check if there are any selected room IDs
        if (roomIds != null && roomIds.length > 0) {
            req.setAttribute("selectedRoomIds", Arrays.asList(roomIds));
            req.getRequestDispatcher("confirm.jsp").forward(req, resp);
        } else {
            // No room selected, handle accordingly (optional)
            resp.sendRedirect("/rooms");
        }
    }

    private void showError(HttpServletRequest req, HttpServletResponse resp) {

    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String roomId = req.getParameter("idRoom");
        String username = req.getParameter("username");
        String phone = req.getParameter("phone");
        String dateStart = req.getParameter("dateStart");

        // Định dạng ngày người dùng nhập vào là dd/MM/yyyy
        SimpleDateFormat sfdInput = new SimpleDateFormat("dd/MM/yyyy");
        // Định dạng ngày chuẩn cho Date.valueOf() là yyyy-MM-dd
        SimpleDateFormat sfdOutput = new SimpleDateFormat("yyyy-MM-dd");

        Date date = null;
        try {
            String formattedDate = sfdOutput.format(sfdInput.parse(dateStart));
            date = Date.valueOf(formattedDate);  // Sử dụng Date.valueOf() với định dạng yyyy-MM-dd
        } catch (ParseException e) {

            req.setAttribute("error", "Ngày tháng không hợp lệ.");
            req.getRequestDispatcher("edit.jsp").forward(req, resp);
            return;
        }

        int paymentMethod = Integer.parseInt(req.getParameter("paymentMethod"));
        String note = req.getParameter("note");

        Room room = new Room(username, phone, date, paymentMethod, note);
        roomService.update(roomId, room);

        req.getRequestDispatcher("/rooms").forward(req, resp);
    }



    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String[] roomIds = req.getParameterValues("selectedRooms");
        if (roomIds != null && roomIds.length > 0) {
            for (String roomId : roomIds) {
                roomService.delete(roomId);  // Delete each selected room by its ID
                System.out.println("Deleted room: " + roomId);
            }
        }
        resp.sendRedirect("/rooms");
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String username = req.getParameter("username");
        String phone = req.getParameter("phone");
        Date dateStart = null;
        boolean hasError = false;
        String dateInput = req.getParameter("dateStart");

        if (dateInput == null || dateInput.trim().isEmpty()) {
            req.setAttribute("dateError", "Vui lòng nhập ngày bắt đầu thuê.");
            hasError = true;
        } else {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                java.util.Date formatDate = dateFormat.parse(dateInput);

                Date sqlDate = new Date(formatDate.getTime());

                if (sqlDate.before(new Date(System.currentTimeMillis()))) {
                    req.setAttribute("dateError", "Ngày bắt đầu thuê không được là ngày quá khứ.");
                    hasError = true;
                } else {
                    dateStart = sqlDate;
                }
            } catch (ParseException e) {
                req.setAttribute("dateError", "Định dạng ngày không hợp lệ. Vui lòng nhập ngày theo định dạng dd-MM-yyyy.");
                hasError = true;
            }
        }

        int paymentMethod = Integer.parseInt(req.getParameter("paymentMethod"));
        String note = req.getParameter("note");




        if (username == null || !username.matches("^[a-zA-Z\\s]{5,50}$")) {
            req.setAttribute("usernameError", "Tên người thuê trọ phải có từ 5 đến 50 kí tự và không chứa số hoặc kí tự đặc biệt.");
            hasError = true;
        }

        if (phone == null || !phone.matches("^\\d{10}$")) {
            req.setAttribute("phoneError", "Số điện thoại phải là 10 chữ số.");
            hasError = true;
        }

//        if (dateStart.before(new Date(System.currentTimeMillis())) || (dateStart == "")) {
//            req.setAttribute("dateError", "Ngày bắt đầu thuê không được là ngày quá khứ.");
//            hasError = true;
//        }
        if (note != null && note.length()>200){
            req.setAttribute("noteError","Ghi chú không được quá 200 kí tự.");
            hasError = true;
        }
        if (hasError) {
            req.getRequestDispatcher("add.jsp").forward(req, resp);
            return;
        }


        Room room = new Room(username, phone, dateStart, paymentMethod, note);
        roomService.add(room);
        System.out.println("Them thanh cong");
        resp.sendRedirect("/rooms");
    }

}
