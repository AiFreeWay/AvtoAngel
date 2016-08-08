package upplic.com.angelavto.data.net_store.response_entityes;


public abstract class BaseResponse {

    private int id;
    private String jsonrpc;

    public BaseResponse() {
    }

    public BaseResponse(int id, String jsonrpc) {
        this.id = id;
        this.jsonrpc = jsonrpc;
    }

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
