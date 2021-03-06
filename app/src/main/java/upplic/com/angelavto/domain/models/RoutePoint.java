package upplic.com.angelavto.domain.models;


import java.io.Serializable;

public class RoutePoint implements Serializable {

    private String time;
    private double lat;
    private double lon;

    public RoutePoint() {
    }

    public RoutePoint(String time, double lat, double lon) {
        this.time = time;
        this.lat = lat;
        this.lon = lon;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}
