package upplic.com.angelavto.domain.models;


public class LoginResult {

    private int id;
    private Result result;
    private String jsonrpc;

    public LoginResult() {
    }

    public LoginResult(int id, Result result, String jsonrpc) {
        this.id = id;
        this.result = result;
        this.jsonrpc = jsonrpc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getJsonrpc() {
        return jsonrpc;
    }

    public void setJsonrpc(String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }

    public static class Result {

        private String key;

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
