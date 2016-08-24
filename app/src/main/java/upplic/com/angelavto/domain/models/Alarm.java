package upplic.com.angelavto.domain.models;


public class Alarm {

    private int id;
    private double lat;
    private double lon;
    private String alarmType;

    public Alarm() {
    }

    public Alarm(int id, double lat, double lon, String alarmType) {
        this.id = id;
        this.lat = lat;
        this.lon = lon;
        this.alarmType = alarmType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType;
    }
}
