package upplic.com.angelavto.data.net_store.response_entityes;

import upplic.com.angelavto.domain.models.Beacon;


public class BeaconsResponse extends BaseResponse {

    private Beacon[] result;

    public BeaconsResponse() {
    }

    public BeaconsResponse(int id, String jsonrpc, Beacon[] result) {
        super(id, jsonrpc);
        this.result = result;
    }

    public Beacon[] getResult() {
        return result;
    }

    public void setResult(Beacon[] result) {
        this.result = result;
    }
}


