package upplic.com.angelavto.data.net_store;

import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;
import upplic.com.angelavto.data.net_store.requests_entityes.LoginRequest;
import upplic.com.angelavto.data.net_store.requests_entityes.RegistrationRequest;
import upplic.com.angelavto.domain.models.LoginResult;
import upplic.com.angelavto.domain.models.RegistrationResult;


public interface ApiController {

    @Headers( "Content-Type: application/json" )
    @POST(NetworkController.API_EXTENSIONS)
    Observable<RegistrationResult> registration(@Body RegistrationRequest body);

    @Headers( "Content-Type: application/json" )
    @POST(NetworkController.API_EXTENSIONS)
    Observable<LoginResult> login(@Body LoginRequest body);
}
