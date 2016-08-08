package upplic.com.angelavto.data.net_store.response_entityes;


public class LoginResponse extends BaseResponse {

    private Result result;

    public LoginResponse() {
    }

    public LoginResponse(int id, Result result, String jsonrpc) {
        super(id, jsonrpc);
        this.result = result;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public static class Result {

        private String key;

        public Result() {
        }

        public Result(String key) {
            this.key = key;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
    }
}
