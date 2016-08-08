package upplic.com.angelavto.data.net_store.requests_entityes;


public class BeaconsRequest extends BaseRequest {

    private String method = "getTrackerTypes";

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
