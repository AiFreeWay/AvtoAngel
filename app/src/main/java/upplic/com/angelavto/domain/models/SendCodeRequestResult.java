package upplic.com.angelavto.domain.models;


public class SendCodeRequestResult {

    private static final String sSuccessValue = "ok";

    private int id;
    private String result;
    private String jsonrpc;

    public SendCodeRequestResult() {
    }

    public SendCodeRequestResult(int id, String result, String jsonrpc) {
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

    public boolean isSuccess() {
        if (result != null)
            return result.equals(sSuccessValue);
        return false;
    }
}
