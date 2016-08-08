package upplic.com.angelavto.data.net_store;

import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;
import upplic.com.angelavto.data.net_store.requests_entityes.LoginRequest;
import upplic.com.angelavto.data.net_store.requests_entityes.RegistrationRequest;
import upplic.com.angelavto.data.net_store.response_entityes.LoginResponse;
import upplic.com.angelavto.data.net_store.response_entityes.RegistrationResponse;


public interface ApiController {

    @Headers( "Content-Type: application/json" )
    @POST(NetworkController.API_EXTENSIONS)
    Observable<RegistrationResponse> registration(@Body RegistrationRequest body);

    @Headers( "Content-Type: application/json" )
    @POST(NetworkController.API_EXTENSIONS)
    Observable<LoginResponse> login(@Body LoginRequest body);
}
