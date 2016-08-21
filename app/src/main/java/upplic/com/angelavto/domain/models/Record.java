package upplic.com.angelavto.domain.models;


import java.io.Serializable;

public class Record implements Serializable {

    private int id;
    private String timeStart;
    private RoutePoint[] coords;

    public Record() {
    }

    public Record(int id, String timeStart) {
        this.id = id;
        this.timeStart = timeStart;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public RoutePoint[] getCoords() {
        return coords;
    }

    public void setCoords(RoutePoint[] coords) {
        this.coords = coords;
    }
}
