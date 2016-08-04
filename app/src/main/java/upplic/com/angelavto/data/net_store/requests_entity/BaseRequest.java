package upplic.com.angelavto.data.net_store.requests_entity;


public abstract class BaseRequest<T> {

    protected String jsonrpc = "2.0";
    protected T params;
    protected int id = 1;
}
