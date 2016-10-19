package upplic.com.angelavto.data.models;


public class AlarmData {

    private String carId;
    private String status;
    private String title;

    public AlarmData() {
    }

    public AlarmData(String carId, String status, String title) {
        this.carId = carId;
        this.status = status;
        this.title = title;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
