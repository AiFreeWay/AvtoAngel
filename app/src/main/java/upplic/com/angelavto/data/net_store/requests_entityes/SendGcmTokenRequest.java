package upplic.com.angelavto.data.net_store.requests_entityes;

/**
 * Created by root on 23.09.16.
 */

public class SendGcmTokenRequest extends BaseRequest {

    private String method = "sendGcmToken";
    private SendGcmTokenRequest.Params params;

    public SendGcmTokenRequest() {
    }

    public SendGcmTokenRequest(String key, String token) {
        params = new SendGcmTokenRequest.Params(key, token);
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public SendGcmTokenRequest.Params getParams() {
        return params;
    }

    public void setParams(SendGcmTokenRequest.Params params) {
        this.params = params;
    }

    public static class Params {

        public String token;
        public String key;

        public Params(String key, String token) {
            this.key = key;
            this.token = token;
        }
    }
}
