package upplic.com.angelavto.domain.models;


import java.io.Serializable;

public class CarOptions implements Serializable {

    private int id;
    private String title;
    private boolean notification;
    private long editDate;

    public CarOptions() {
    }

    public CarOptions(int id, String title, boolean notification, long editDate) {
        this.id = id;
        this.title = title;
        this.notification = notification;
        this.editDate = editDate;
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

    public boolean isNotification() {
        return notification;
    }

    public void setNotification(boolean notification) {
        this.notification = notification;
    }

    public long getEditDate() {
        return editDate;
    }

    public void setEditDate(long editDate) {
        this.editDate = editDate;
    }
}
