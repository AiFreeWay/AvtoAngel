package upplic.com.angelavto.presentation.models;


import java.io.Serializable;

public class Alarm implements Serializable {

    private int carId;
    private AlarmTypes status;
    private String title;

    public Alarm() {
    }

    public Alarm(int carId, AlarmTypes status, String title) {
        this.carId = carId;
        this.status = status;
        this.title = title;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public AlarmTypes getStatus() {
        return status;
    }

    public void setStatus(AlarmTypes status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public enum AlarmTypes {
        NOT_SIGNAL(0),
        CAR_MOVES(1);

        public int id;

        AlarmTypes(int id) {
            this.id = id;
        }
    }
}
