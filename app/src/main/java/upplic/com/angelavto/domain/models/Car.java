package upplic.com.angelavto.domain.models;


import java.io.Serializable;

public class Car implements Serializable {

    public static final int STATE_LOCK = 0;
    public static final int STATE_UNLOCK = 1;

    private int id;
    private String title;
    private int state;
    private String phone;

    public Car() {
    }

    public Car(int id, String title, int state, String phone) {
        this.id = id;
        this.title = title;
        this.state = state;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
