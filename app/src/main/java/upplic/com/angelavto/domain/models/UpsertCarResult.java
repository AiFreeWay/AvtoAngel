package upplic.com.angelavto.domain.models;


public class UpsertCarResult {


    public static final int CAR_TITLE_EXISTS_ERROR_TYPE = 0;
    public static final int IMEI_EXISTS_ERROR_TYPE = 1;
    public static final int BEACON_SIM_NUMBER_EXISTS_ERROR_TYPE = 2;

    private boolean is_success;
    private int error_type;

    public UpsertCarResult() {
    }

    public UpsertCarResult(boolean is_success, int error_type) {
        this.is_success = is_success;
        this.error_type = error_type;
    }

    public boolean isSuccess() {
        return is_success;
    }

    public void setIsSuccess(boolean is_success) {
        this.is_success = is_success;
    }

    public int getErrorType() {
        return error_type;
    }

    public void setErrorType(int error_type) {
        this.error_type = error_type;
    }
}
