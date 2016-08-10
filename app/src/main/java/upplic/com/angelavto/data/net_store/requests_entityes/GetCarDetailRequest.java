package upplic.com.angelavto.data.net_store.requests_entityes;


public class GetCarDetailRequest extends BaseRequest {

    private String method = "getCar";
    private Params params;

    public GetCarDetailRequest() {
    }

    public GetCarDetailRequest(String key, int id) {
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
        public int id;

        public Params(String key, int id) {
            this.key = key;
            this.id = id;
        }
    }
}
