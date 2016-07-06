package upplic.com.angelavto.domain.models;


public class Car {

    public static final int STATE_LOCK = 0;
    public static final int STATE_UNLOCK = 1;

    private int id;
    private String title;
    private int state;

    public Car() {
    }

    public Car(int id, String title, int state) {
        this.id = id;
        this.title = title;
        this.state = state;
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
}
