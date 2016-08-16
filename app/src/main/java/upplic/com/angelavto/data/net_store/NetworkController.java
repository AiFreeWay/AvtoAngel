package upplic.com.angelavto.data.net_store;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import upplic.com.angelavto.data.mappers.CarMapper;
import upplic.com.angelavto.data.mappers.LoginMapper;
import upplic.com.angelavto.data.mappers.RegistrationMapper;
import upplic.com.angelavto.data.net_store.requests_entityes.BeaconsRequest;
import upplic.com.angelavto.data.net_store.requests_entityes.CheckKeyRequest;
import upplic.com.angelavto.data.net_store.requests_entityes.DeleteCarRequest;
import upplic.com.angelavto.data.net_store.requests_entityes.GetCarDetailRequest;
import upplic.com.angelavto.data.net_store.requests_entityes.GetCarsRequest;
import upplic.com.angelavto.data.net_store.response_entityes.BeaconsResponse;
import upplic.com.angelavto.data.net_store.response_entityes.CheckKeyResponse;
import upplic.com.angelavto.data.net_store.response_entityes.DeleteCarResponse;
import upplic.com.angelavto.data.net_store.response_entityes.GetCarDetailResponse;
import upplic.com.angelavto.data.net_store.response_entityes.UpsertCarResponse;
import upplic.com.angelavto.data.net_store.response_entityes.GetCarsResponse;
import upplic.com.angelavto.data.net_store.response_entityes.LoginResponse;
import upplic.com.angelavto.data.net_store.response_entityes.RegistrationResponse;
import upplic.com.angelavto.domain.models.Car;
import upplic.com.angelavto.domain.models.LoginDomain;
import upplic.com.angelavto.domain.models.RegistrationDomain;


public class NetworkController {

    public static final String API_EXTENSIONS = "api/v1/";

    private final String BASE_URL = "http://188.120.233.126/";
    private final BeaconsRequest BEACONS_REQUEST = new BeaconsRequest();

    private ApiController mApiController;

    public NetworkController() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build();
        mApiController = retrofit.create(ApiController.class);
    }

    public Observable<RegistrationResponse> registration(RegistrationDomain registrationDomain) {
        return mApiController.registration(RegistrationMapper.mapRegistration(registrationDomain));
    }

    public Observable<LoginResponse> login(LoginDomain loginDomain) {
        return mApiController.login(LoginMapper.mapLogin(loginDomain));
    }

    public Observable<BeaconsResponse> getBeacons() {
        return mApiController.getBeacons(BEACONS_REQUEST);
    }

    public Observable<GetCarsResponse> getCars(String key) {
        return mApiController.getCars(new GetCarsRequest(key));
    }

    public Observable<UpsertCarResponse> upsertCar(String key, Car car) {
        return mApiController.upsertCar(CarMapper.mapCarsToNetwork(key, car));
    }

    public Observable<GetCarDetailResponse> getCarDetail(String key, int id) {
        return mApiController.getCarDetail(new GetCarDetailRequest(key, id));
    }

    public Observable<DeleteCarResponse> deleteCar(String key, int id) {
        return mApiController.deleteCar(new DeleteCarRequest(key, id));
    }

    public Observable<CheckKeyResponse> checkKey(String key) {
        return mApiController.checkKey(new CheckKeyRequest(key));
    }
}
