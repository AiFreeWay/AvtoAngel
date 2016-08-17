package upplic.com.angelavto.data.net_store.requests_entityes;

import upplic.com.angelavto.domain.models.Status;


public class SetStatusRequest extends BaseRequest {

    private String method = "setStatus";
    private Params params;

    public SetStatusRequest() {
    }

    public SetStatusRequest(String key, Status status) {
        params = new Params(key, status.getId(), status.isStatus(), status.isRecord());
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Params getParams() {
        return params;
    }

    public void setParams(Params params) {
        this.params = params;
    }

    public static class Params {

        public String key;
        public int id;
        public boolean status;
        public boolean record;

        public Params(String key, int id, boolean status, boolean record) {
            this.key = key;
            this.id = id;
            this.status = status;
            this.record = record;
        }
    }
}
