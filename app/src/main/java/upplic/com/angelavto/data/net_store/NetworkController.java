package upplic.com.angelavto.data.net_store;

import java.util.HashMap;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import upplic.com.angelavto.data.mappers.LoginMapper;
import upplic.com.angelavto.domain.models.Login;
import upplic.com.angelavto.domain.models.SendCodeRequestResult;

public class NetworkController {

    public static final String METHOD_KEY = "method";
    public static final String PARAMS_KEY = "params";
    private static HashMap<String, Object> sConfigMap;

    private final String BASE_URL = "http://188.120.233.126/api/v1/";
    private final String POST_REQUEST_METHOD_TYPE = "jsonrpc";
    private final String METHOD_VERSION = "2.0";
    private final String SERVER_ID = "id";
    private final int ID = 1;

    private ApiController mApiController;

    public NetworkController() {
        sConfigMap = new HashMap<>();
        sConfigMap.put(POST_REQUEST_METHOD_TYPE, METHOD_VERSION);
        sConfigMap.put(SERVER_ID, ID);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mApiController = retrofit.create(ApiController.class);
    }

    public static HashMap<String, Object> getConfigMap() {
        return sConfigMap;
    }

    public Observable registration(Login login) {
        return mApiController.registration(LoginMapper.mapLogin(login));
    }
}
