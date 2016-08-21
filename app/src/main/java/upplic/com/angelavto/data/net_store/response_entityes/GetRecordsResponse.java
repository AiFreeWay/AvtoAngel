package upplic.com.angelavto.data.net_store.response_entityes;

import upplic.com.angelavto.domain.models.Record;


public class GetRecordsResponse extends BaseResponse {

    private Record[] result;

    public GetRecordsResponse() {
    }

    public GetRecordsResponse(int id, String jsonrpc, Record[] result) {
        super(id, jsonrpc);
        this.result = result;
    }

    public Record[] getResult() {
        return result;
    }

    public void setResult(Record[] result) {
        this.result = result;
    }
}
