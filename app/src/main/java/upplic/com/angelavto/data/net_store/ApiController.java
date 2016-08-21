package upplic.com.angelavto.data.net_store;


import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;
import upplic.com.angelavto.data.net_store.requests_entityes.BeaconsRequest;
import upplic.com.angelavto.data.net_store.requests_entityes.CheckKeyRequest;
import upplic.com.angelavto.data.net_store.requests_entityes.DeleteCarRequest;
import upplic.com.angelavto.data.net_store.requests_entityes.GetCarDetailRequest;
import upplic.com.angelavto.data.net_store.requests_entityes.GetRecordDetailRequest;
import upplic.com.angelavto.data.net_store.requests_entityes.GetRecordsRequest;
import upplic.com.angelavto.data.net_store.requests_entityes.SetStatusRequest;
import upplic.com.angelavto.data.net_store.requests_entityes.UpsertCarRequest;
import upplic.com.angelavto.data.net_store.requests_entityes.GetCarsRequest;
import upplic.com.angelavto.data.net_store.requests_entityes.LoginRequest;
import upplic.com.angelavto.data.net_store.requests_entityes.RegistrationRequest;
import upplic.com.angelavto.data.net_store.response_entityes.BeaconsResponse;
import upplic.com.angelavto.data.net_store.response_entityes.CheckKeyResponse;
import upplic.com.angelavto.data.net_store.response_entityes.DeleteCarResponse;
import upplic.com.angelavto.data.net_store.response_entityes.GetCarDetailResponse;
import upplic.com.angelavto.data.net_store.response_entityes.GetRecordDetailResponse;
import upplic.com.angelavto.data.net_store.response_entityes.GetRecordsResponse;
import upplic.com.angelavto.data.net_store.response_entityes.SetStatusResponse;
import upplic.com.angelavto.data.net_store.response_entityes.UpsertCarResponse;
import upplic.com.angelavto.data.net_store.response_entityes.GetCarsResponse;
import upplic.com.angelavto.data.net_store.response_entityes.LoginResponse;
import upplic.com.angelavto.data.net_store.response_entityes.RegistrationResponse;


public interface ApiController {

    @Headers( "Content-Type: application/json" )
    @POST(NetworkController.API_EXTENSIONS)
    Observable<RegistrationResponse> registration(@Body RegistrationRequest body);

    @Headers( "Content-Type: application/json" )
    @POST(NetworkController.API_EXTENSIONS)
    Observable<LoginResponse> login(@Body LoginRequest body);

    @Headers( "Content-Type: application/json" )
    @POST(NetworkController.API_EXTENSIONS)
    Observable<BeaconsResponse> getBeacons(@Body BeaconsRequest body);

    @Headers( "Content-Type: application/json" )
    @POST(NetworkController.API_EXTENSIONS)
    Observable<GetCarsResponse> getCars(@Body GetCarsRequest body);

    @Headers( "Content-Type: application/json" )
    @POST(NetworkController.API_EXTENSIONS)
    Observable<UpsertCarResponse> upsertCar(@Body UpsertCarRequest body);

    @Headers( "Content-Type: application/json" )
    @POST(NetworkController.API_EXTENSIONS)
    Observable<GetCarDetailResponse> getCarDetail(@Body GetCarDetailRequest body);

    @Headers( "Content-Type: application/json" )
    @POST(NetworkController.API_EXTENSIONS)
    Observable<DeleteCarResponse> deleteCar(@Body DeleteCarRequest body);

    @Headers( "Content-Type: application/json" )
    @POST(NetworkController.API_EXTENSIONS)
    Observable<CheckKeyResponse> checkKey(@Body CheckKeyRequest body);

    @Headers( "Content-Type: application/json" )
    @POST(NetworkController.API_EXTENSIONS)
    Observable<SetStatusResponse> setStatus(@Body SetStatusRequest body);

    @Headers( "Content-Type: application/json" )
    @POST(NetworkController.API_EXTENSIONS)
    Observable<GetRecordsResponse> getRecords(@Body GetRecordsRequest body);

    @Headers( "Content-Type: application/json" )
    @POST(NetworkController.API_EXTENSIONS)
    Observable<GetRecordDetailResponse> getRecordDetail(@Body GetRecordDetailRequest body);
}
