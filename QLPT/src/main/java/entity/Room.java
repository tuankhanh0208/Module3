package entity;

import java.sql.Date;

public class Room {
    private String idRoom;
    private String username;
    private String phone;
    private Date dateStart;
    private int paymentMethod;
    private String note;

    public Room(String idRoom, String username, String phone, Date dateStart, int paymentMethod, String note) {
        this.idRoom = idRoom;
        this.username = username;
        this.phone = phone;
        this.dateStart = dateStart;
        this.paymentMethod = paymentMethod;
        this.note = note;
    }

    public Room(String username, String phone, Date dateStart, int paymentMethod, String note) {
        this.username = username;
        this.phone = phone;
        this.dateStart = dateStart;
        this.paymentMethod = paymentMethod;
        this.note = note;
    }

    public String getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(String idRoom) {
        this.idRoom = idRoom;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public int getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(int paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
