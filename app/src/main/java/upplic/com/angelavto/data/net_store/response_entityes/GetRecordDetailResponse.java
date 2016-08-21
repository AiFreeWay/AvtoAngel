package upplic.com.angelavto.data.net_store.response_entityes;


import upplic.com.angelavto.domain.models.Record;

public class GetRecordDetailResponse extends BaseResponse {

    private Record result;

    public GetRecordDetailResponse() {
    }

    public GetRecordDetailResponse(int id, String jsonrpc, Record result) {
        super(id, jsonrpc);
        this.result = result;
    }

    public Record getResult() {
        return result;
    }

    public void setResult(Record result) {
        this.result = result;
    }
}
