package upplic.com.angelavto.data.net_store.response_entityes;


public class PingResponse extends BaseResponse {

    private String result;

    public PingResponse() {
    }

    public PingResponse(int id, String result, String jsonrpc) {
        super(id ,jsonrpc);
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
