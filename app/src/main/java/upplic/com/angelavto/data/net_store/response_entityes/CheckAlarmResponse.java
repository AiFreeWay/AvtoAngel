package upplic.com.angelavto.data.net_store.response_entityes;


import upplic.com.angelavto.data.models.AlarmData;

public class CheckAlarmResponse extends BaseResponse {

    private AlarmData[] result;

    public CheckAlarmResponse() {
    }

    public CheckAlarmResponse(int id, String jsonrpc, AlarmData[] result) {
        super(id, jsonrpc);
        this.result = result;
    }

    public AlarmData[] getResult() {
        return result;
    }

    public void setResult(AlarmData[] result) {
        this.result = result;
    }
}
