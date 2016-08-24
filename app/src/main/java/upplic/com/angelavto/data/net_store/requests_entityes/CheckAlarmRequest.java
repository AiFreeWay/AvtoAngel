package upplic.com.angelavto.data.net_store.requests_entityes;


public class CheckAlarmRequest extends BaseRequest {

    private String method = "checkAlarm";
    private Params params;

    public CheckAlarmRequest() {
    }

    public CheckAlarmRequest(String key) {
        params = new Params(key);
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Params getParams() {
        return params;
    }

    public void setParams(Params params) {
        this.params = params;
    }

    public static class Params {

        public String key;

        public Params(String key) {
            this.key = key;
        }
    }
}
