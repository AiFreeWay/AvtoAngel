package upplic.com.angelavto.domain.models;


import java.io.Serializable;

public class Car implements Serializable {

    private int id;
    private String title;
    private boolean status;
    private boolean record;
    private String trackerPhone;
    private String trackerImei;
    private int trackerType;
    private double lat;
    private double lon;

    public Car() {
    }

    public Car(int id, String title, boolean status, String trackerPhone, String trackerImei, int trackerType) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.trackerPhone = trackerPhone;
        this.trackerImei = trackerImei;
        this.trackerType = trackerType;
    }

    public Car(int id, String title, boolean status, boolean record, String trackerPhone, String trackerImei, int trackerType) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.record = record;
        this.trackerPhone = trackerPhone;
        this.trackerImei = trackerImei;
        this.trackerType = trackerType;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isRecord() {
        return record;
    }

    public void setRecord(boolean record) {
        this.record = record;
    }

    public String getTrackerPhone() {
        return trackerPhone;
    }

    public void setTrackerPhone(String trackerPhone) {
        this.trackerPhone = trackerPhone;
    }

    public String getTrackerImei() {
        return trackerImei;
    }

    public void setTrackerImei(String trackerImei) {
        this.trackerImei = trackerImei;
    }

    public int getTrackerType() {
        return trackerType;
    }

    public void setTrackerType(int trackerType) {
        this.trackerType = trackerType;
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
