package upplic.com.angelavto.data.mappers;

import java.util.Arrays;
import java.util.List;

import upplic.com.angelavto.data.net_store.requests_entityes.UpsertCarRequest;
import upplic.com.angelavto.data.net_store.response_entityes.DeleteCarResponse;
import upplic.com.angelavto.data.net_store.response_entityes.GetCarDetailResponse;
import upplic.com.angelavto.data.net_store.response_entityes.GetCarsResponse;
import upplic.com.angelavto.data.net_store.response_entityes.UpsertCarResponse;
import upplic.com.angelavto.domain.models.Car;
import upplic.com.angelavto.domain.models.DeleteCarResult;
import upplic.com.angelavto.domain.models.UpsertCarResult;

public class CarMapper {

    public static List<Car> mapCarsFromNetwork(GetCarsResponse getCarsResponse) {
        return Arrays.asList(getCarsResponse.getResult());
    }

    public static UpsertCarRequest mapCarsToNetwork(String key, Car car) {
        return new UpsertCarRequest(key, car);
    }

    public static Car mapCarDetailFromNetwork(GetCarDetailResponse getCarDetailResponse) {
        return getCarDetailResponse.getResult();
    }

    public static DeleteCarResult mapDeleteCarNetwork(DeleteCarResponse deleteCarResponse) {
        return new DeleteCarResult(deleteCarResponse.getResult());
    }
}
