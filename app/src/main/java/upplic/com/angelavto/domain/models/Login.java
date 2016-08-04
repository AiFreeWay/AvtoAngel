package upplic.com.angelavto.domain.models;


public class Login {

    private String number;

    public Login() {
    }

    public Login(String number) {
        this.number = cleanNumber(number);
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = cleanNumber(number);
    }

    private String cleanNumber(String number) {
        return number.replaceAll("[^0-9]", "");
    }
}
