package service;

import connect.ConnectDatabase;
import entity.Room;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class RoomService implements IRoomService<Room> {
    private Connection connection;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    public RoomService () throws SQLException {
        this.connection = ConnectDatabase.getConnection();
    }
    @Override
    public void add(Room room) {
        String sql = "INSERT INTO tenant ( username, phone, dateStart, paymentMethod, note) VALUES ( ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, room.getUsername());
            preparedStatement.setString(2,room.getPhone());
            preparedStatement.setDate(3, room.getDateStart());
            preparedStatement.setInt(4,room.getPaymentMethod());
            preparedStatement.setString(5,room.getNote());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Room> getAll() {
        List<Room> rooms = new ArrayList<>();
        String sql = "select  * from tenant";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Room room = new Room(
                        resultSet.getString("idRoom"),
                        resultSet.getString("username"),
                        resultSet.getString("phone"),
                        resultSet.getDate("dateStart"),
                        resultSet.getInt("paymentMethod"),
                        resultSet.getString("note")
                );
                rooms.add(room);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rooms;
    }


    @Override
    public void update(String id, Room room)  {
        String sql = "UPDATE tenant SET username=?, phone=?, dateStart=?, paymentMethod=?, note=? WHERE idRoom=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, room.getUsername());
            preparedStatement.setString(2, room.getPhone());
            preparedStatement.setDate(3, room.getDateStart());

            preparedStatement.setInt(4,room.getPaymentMethod());
            preparedStatement.setString(5, room.getNote());
            preparedStatement.setString(6, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(String id) {
        String sql = "DELETE FROM tenant WHERE idRoom=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Xóa thành công ID: " + id);
            } else {
                System.out.println("Không tìm thấy ID để xóa");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Room findById(String id) {
        System.out.println(id);
        String sql = "select * from tenant where idRoom=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                return  new Room(
                        resultSet.getString("idRoom"),
                        resultSet.getString("username"),
                        resultSet.getString("phone"),
                        resultSet.getDate("dateStart"),
                        resultSet.getInt("paymentMethod"),
                        resultSet.getString("note")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    @Override
    public List<Room> findBySearch(String username, String phone, String idRoom){
        List<Room> rooms = new ArrayList<>();
        String sql = "select * from tenant where idRoom like lower (?) or phone like lower(?) or username like lower(?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + idRoom +"%");
            preparedStatement.setString(2, "%" + phone +"%");
            preparedStatement.setString(3, "%" + username +"%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Room room = new Room(
                        resultSet.getString("idRoom"),
                        resultSet.getString("username"),
                        resultSet.getString("phone"),
                        resultSet.getDate("dateStart"),
                        resultSet.getInt("paymentMethod"),
                        resultSet.getString("note")
                );
                rooms.add(room);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rooms;
    }
}
