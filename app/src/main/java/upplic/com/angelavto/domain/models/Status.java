package upplic.com.angelavto.domain.models;


public class Status {

    private int id;
    private boolean status;
    private boolean record;

    public Status() {
    }

    public Status(int id, boolean status, boolean record) {
        this.id = id;
        this.status = status;
        this.record = record;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
