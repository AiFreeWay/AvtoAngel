package upplic.com.angelavto.data.net_store.response_entityes;


public class CheckKeyResponse extends BaseResponse {

    private boolean result;

    public CheckKeyResponse() {
    }

    public CheckKeyResponse(int id, boolean result, String jsonrpc) {
        super(id ,jsonrpc);
        this.result = result;
    }

    public boolean getResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
