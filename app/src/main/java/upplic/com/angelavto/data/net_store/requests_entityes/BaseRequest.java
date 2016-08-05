package upplic.com.angelavto.data.net_store.requests_entityes;


public abstract class BaseRequest {

    private int id = 1;
    private String jsonrpc = "2.0";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJsonrpc() {
        return jsonrpc;
    }

    public void setJsonrpc(String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }
}
