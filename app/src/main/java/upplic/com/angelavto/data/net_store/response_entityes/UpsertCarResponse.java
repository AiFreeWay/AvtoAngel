package upplic.com.angelavto.data.net_store.response_entityes;


import upplic.com.angelavto.domain.models.UpsertCarResult;

public class UpsertCarResponse extends BaseResponse {

    private UpsertCarResult result;

    public UpsertCarResponse() {
    }

    public UpsertCarResponse(int id, UpsertCarResult result, String jsonrpc) {
        super(id ,jsonrpc);
        this.result = result;
    }

    public UpsertCarResult getResult() {
        return result;
    }

    public void setResult(UpsertCarResult result) {
        this.result = result;
    }
}
