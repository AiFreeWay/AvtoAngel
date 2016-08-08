package upplic.com.angelavto.domain.models;


public class RegistrationDomain {

    protected String number;

    public RegistrationDomain() {
    }

    public RegistrationDomain(String number) {
        this.number = cleanNumber(number);
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = cleanNumber(number);
    }

    protected String cleanNumber(String number) {
        return number.replaceAll("[^0-9]", "");
    }
}
