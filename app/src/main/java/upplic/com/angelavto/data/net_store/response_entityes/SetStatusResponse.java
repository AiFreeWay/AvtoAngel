package upplic.com.angelavto.data.net_store.response_entityes;

import upplic.com.angelavto.domain.models.Status;


public class SetStatusResponse extends BaseResponse {

    private Status result;

    public SetStatusResponse() {
    }

    public SetStatusResponse(int id, String jsonrpc, Status result) {
        super(id, jsonrpc);
        this.result = result;
    }

    public Status getResult() {
        return result;
    }

    public void setResult(Status result) {
        this.result = result;
    }
}
