package upplic.com.angelavto.data.net_store;

import com.squareup.okhttp.RequestBody;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import upplic.com.angelavto.data.net_store.requests_entityes.PingRequest;
import upplic.com.angelavto.data.net_store.response_entityes.PingResponse;


public interface StandAloneApiController {

    @Headers( "Content-Type: application/json" )
    @POST(NetworkController.API_EXTENSIONS)
    Call<PingResponse> ping(@Body PingRequest body);
}
