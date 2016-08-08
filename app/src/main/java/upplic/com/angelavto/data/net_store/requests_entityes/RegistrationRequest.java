package upplic.com.angelavto.data.net_store.requests_entityes;


public class RegistrationRequest extends BaseRequest {

    private String method = "requestCode";
    private Params params;

    public RegistrationRequest() {
    }

    public RegistrationRequest(String number) {
        params = new Params(number);
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

        public Params(String number) {
            this.number = number;
        }
    }
}
