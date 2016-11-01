package upplic.com.angelavto.data.net_store;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.RequestBody;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import upplic.com.angelavto.data.net_store.requests_entityes.BeaconsRequest;
import upplic.com.angelavto.data.net_store.requests_entityes.PingRequest;
import upplic.com.angelavto.data.net_store.response_entityes.PingResponse;

public class StandAloneNetworkController {

    public static final String API_EXTENSIONS = "api/v1/";

    private final String BASE_URL = "http://188.120.233.126/";
    private final BeaconsRequest BEACONS_REQUEST = new BeaconsRequest();

    private StandAloneApiController mApiController;

    public StandAloneNetworkController() {
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
        mApiController = retrofit.create(StandAloneApiController.class);
    }

    public Call<PingResponse> ping() {
        return mApiController.ping(new PingRequest());
    }
}
