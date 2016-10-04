package upplic.com.angelavto.data.net_store.requests_entityes;


import upplic.com.angelavto.domain.models.Car;

public class UpsertCarRequest extends BaseRequest {

    private String method = "updateCar";
    private Params params;

    public UpsertCarRequest() {
    }

    public UpsertCarRequest(String key, Car car) {
        car.setTrackerPhone(cleanNumber(car.getTrackerPhone()));
        params = new Params(key, car);
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

    private String cleanNumber(String number) {
        return number.replaceAll("[^0-9]", "");
    }

    public static class Params {

        public String key;
        public Car car;

        public Params(String key, Car car) {
            this.key = key;
            this.car = car;
        }
    }
}
