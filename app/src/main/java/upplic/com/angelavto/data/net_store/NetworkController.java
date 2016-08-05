package upplic.com.angelavto.data.net_store;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import upplic.com.angelavto.data.mappers.LoginMapper;
import upplic.com.angelavto.domain.models.Login;
import upplic.com.angelavto.domain.models.SendCodeRequestResult;


public class NetworkController {

    public static final String API_EXTENSIONS = "api/v1/";

    private final String BASE_URL = "http://188.120.233.126/";

    private ApiController mApiController;

    public NetworkController() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mApiController = retrofit.create(ApiController.class);
    }

    public Observable<SendCodeRequestResult> registration(Login login) {
        return mApiController.registration(LoginMapper.mapLogin(login));
    }
}
