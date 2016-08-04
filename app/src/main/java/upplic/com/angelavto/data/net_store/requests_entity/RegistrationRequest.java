package upplic.com.angelavto.data.net_store.requests_entity;


public class RegistrationRequest extends BaseRequest {

    private String method = "requestCode";
    private String number;

    public RegistrationRequest() {
    }

    public RegistrationRequest(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
