package upplic.com.angelavto.data.net_store.requests_entityes;


public class LoginRequest extends BaseRequest {

    private String method = "auth";
    private Params params;

    public LoginRequest() {
    }

    public LoginRequest(String number, String code) {
        params = new Params(number, code);
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

        public String number;
        public String code;

        public Params(String number, String code) {
            this.number = number;
            this.code = code;
        }
    }
}
