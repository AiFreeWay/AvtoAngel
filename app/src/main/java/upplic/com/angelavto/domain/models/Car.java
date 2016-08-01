package upplic.com.angelavto.domain.models;


import java.io.Serializable;

public class Car implements Serializable {

    public static final int STATE_LOCK = 0;
    public static final int STATE_UNLOCK = 1;

    public static final int NOTIFICATION_ON = 2;
    public static final int NOTIFICATION_OFF = 3;

    private int id;
    private String title;
    private int state;
    private int notification;
    private String phone;

    public Car() {
    }

    public Car(int id, String title, int state, int notification, String phone) {
        this.id = id;
        this.title = title;
        this.state = state;
        this.notification = notification;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getNotification() {
        return notification;
    }

    public void setNotification(int notification) {
        this.notification = notification;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
