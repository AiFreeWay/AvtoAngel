package upplic.com.angelavto.data.net_store.response_entityes;


import upplic.com.angelavto.domain.models.LoginResult.Result;

public class LoginResponse {

    private int id;
    private Result result;
    private String jsonrpc;

    public LoginResponse() {
    }

    public LoginResponse(int id, Result result, String jsonrpc) {
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

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getJsonrpc() {
        return jsonrpc;
    }

    public void setJsonrpc(String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }
}
