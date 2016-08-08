package upplic.com.angelavto.domain.models;


public class RegistrationResult {

    private int id;
    private String result;
    private String jsonrpc;

    public RegistrationResult() {
    }

    public RegistrationResult(int id, String result, String jsonrpc) {
        this.id = id;
        this.result = result;
        this.jsonrpc = jsonrpc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getJsonrpc() {
        return jsonrpc;
    }

    public void setJsonrpc(String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }
}
