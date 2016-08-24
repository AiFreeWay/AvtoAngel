package upplic.com.angelavto.data.net_store.response_entityes;


import upplic.com.angelavto.domain.models.Alarm;

public class CheckAlarmResponse extends BaseResponse {

    private Alarm[] result;

    public CheckAlarmResponse() {
    }

    public CheckAlarmResponse(int id, String jsonrpc, Alarm[] result) {
        super(id, jsonrpc);
        this.result = result;
    }

    public Alarm[] getResult() {
        return result;
    }

    public void setResult(Alarm[] result) {
        this.result = result;
    }
}
