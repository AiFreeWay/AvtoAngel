package upplic.com.angelavto.data.net_store;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import upplic.com.angelavto.data.mappers.LoginMapper;
import upplic.com.angelavto.data.mappers.RegistrationMapper;
import upplic.com.angelavto.domain.models.LoginDomain;
import upplic.com.angelavto.domain.models.LoginResult;
import upplic.com.angelavto.domain.models.RegistrationDomain;
import upplic.com.angelavto.domain.models.RegistrationResult;


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

    public Observable<RegistrationResult> registration(RegistrationDomain registrationDomain) {
        return mApiController.registration(RegistrationMapper.mapRegistration(registrationDomain));
    }

    public Observable<LoginResult> login(LoginDomain loginDomain) {
        return mApiController.login(LoginMapper.mapLogin(loginDomain));
    }
}
