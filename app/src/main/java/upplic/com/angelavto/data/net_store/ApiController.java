package upplic.com.angelavto.data.net_store;


import java.util.HashMap;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

public interface ApiController {

    @POST
    Observable registration(@Body HashMap<String, Object> body);
}
