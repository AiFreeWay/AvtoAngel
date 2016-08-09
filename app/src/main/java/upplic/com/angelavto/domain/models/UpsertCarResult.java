package upplic.com.angelavto.domain.models;


public class UpsertCarResult {

    private static final String UPSERT_SUCCESS_RESULT = "ok";

    private String result;

    public UpsertCarResult() {
    }

    public UpsertCarResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public boolean isSuccess() {
        return result != null && result.equals(UPSERT_SUCCESS_RESULT);
    }
}
