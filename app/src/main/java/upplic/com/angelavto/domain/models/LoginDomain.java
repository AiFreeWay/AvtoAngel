package upplic.com.angelavto.domain.models;


public class LoginDomain extends RegistrationDomain {

    private String code;

    public LoginDomain() {
    }

    public LoginDomain(String number, String code) {
        super(number);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
