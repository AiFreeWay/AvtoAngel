package upplic.com.angelavto.data.net_store.response_entityes;


public class DeleteCarResponse extends BaseResponse {

    private String result;

    public DeleteCarResponse() {
    }

    public DeleteCarResponse(int id, String result, String jsonrpc) {
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