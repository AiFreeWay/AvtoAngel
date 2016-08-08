package upplic.com.angelavto.domain.models;


public class LoginResult {

    private String key;

    public LoginResult() {
    }

    public LoginResult(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
