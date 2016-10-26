package upplic.com.angelavto.domain.interactors;


import java.util.List;

import rx.Observable;
import upplic.com.angelavto.domain.models.Car;
import upplic.com.angelavto.domain.models.DeleteCarResult;
import upplic.com.angelavto.domain.models.UpsertCarResult;

public interface CarsInteractor {

    Observable<UpsertCarResult> createCar(Car data);
    Observable<DeleteCarResult> deleteCar(Car data);
    Observable<List<Car>> getCarsSubject();
    Observable<List<Car>> getCars();
    Observable<UpsertCarResult> updateCar(Car data);
    Observable<Car> getCarDetail(Integer data);
}
