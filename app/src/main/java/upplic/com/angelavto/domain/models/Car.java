package upplic.com.angelavto.domain.models;


import java.io.Serializable;

public class Car implements Serializable {

    public static final int STATE_LOCK = 0;
    public static final int STATE_UNLOCK = 1;

    public static final int NOTIFICATION_ON = 2;
    public static final int NOTIFICATION_OFF = 3;

    private int id;
    private String title;
    private int sequrityState;
    private int notificationState;
    private String phone;

    public Car() {
    }

    public Car(int id, String title, int sequrityState, int notificationState, String phone) {
        this.id = id;
        this.title = title;
        this.sequrityState = sequrityState;
        this.notificationState = notificationState;
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

    public int getSequrityState() {
        return sequrityState;
    }

    public void setSequrityState(int sequrityState) {
        this.sequrityState = sequrityState;
    }

    public int getNotificationState() {
        return notificationState;
    }

    public void setNotificationState(int notificationState) {
        this.notificationState = notificationState;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
