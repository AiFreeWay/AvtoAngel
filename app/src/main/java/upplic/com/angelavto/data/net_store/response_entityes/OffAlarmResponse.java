package upplic.com.angelavto.data.net_store.response_entityes;


public class OffAlarmResponse extends BaseResponse {

    private String result;

    public OffAlarmResponse() {
    }

    public OffAlarmResponse(int id, String result, String jsonrpc) {
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
