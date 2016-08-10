package upplic.com.angelavto.data.net_store.response_entityes;


import upplic.com.angelavto.domain.models.Car;

public class GetCarDetailResponse extends BaseResponse {

    private Car result;

    public GetCarDetailResponse() {
    }

    public GetCarDetailResponse(int id, String jsonrpc, Car result) {
        super(id, jsonrpc);
        this.result = result;
    }

    public Car getResult() {
        return result;
    }

    public void setResult(Car result) {
        this.result = result;
    }
}
