package upplic.com.angelavto.data.net_store.response_entityes;


public class UpsertCarResponse extends BaseResponse {

    private String result;

    public UpsertCarResponse() {
    }

    public UpsertCarResponse(int id, String result, String jsonrpc) {
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
