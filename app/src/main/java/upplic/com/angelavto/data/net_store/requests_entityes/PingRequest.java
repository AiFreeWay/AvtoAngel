package upplic.com.angelavto.data.net_store.requests_entityes;


public class PingRequest extends BaseRequest {

    private String method = "ping";

    public PingRequest() {
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
