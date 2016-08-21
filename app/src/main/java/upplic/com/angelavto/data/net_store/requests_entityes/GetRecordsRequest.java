package upplic.com.angelavto.data.net_store.requests_entityes;


public class GetRecordsRequest extends BaseRequest {

    private String method = "getTracks";
    private Params params;

    public GetRecordsRequest() {
    }

    public GetRecordsRequest(String key, int id) {
        params = new Params(key, id);
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
        public int carId;

        public Params(String key, int carId) {
            this.key = key;
            this.carId = carId;
        }
    }
}
