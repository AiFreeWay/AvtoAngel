package upplic.com.angelavto.domain.repositories;

import java.util.List;

import rx.Observable;
import upplic.com.angelavto.data.db_store.tables.CarOptionsTableEntity;
import upplic.com.angelavto.domain.models.Beacon;
import upplic.com.angelavto.domain.models.Car;
import upplic.com.angelavto.domain.models.CarOptions;
import upplic.com.angelavto.domain.models.DeleteCarResult;
import upplic.com.angelavto.domain.models.LoginDomain;
import upplic.com.angelavto.domain.models.LoginResult;
import upplic.com.angelavto.domain.models.RegistrationDomain;
import upplic.com.angelavto.domain.models.RegistrationResult;
import upplic.com.angelavto.domain.models.UpsertCarResult;


public interface Repository {

    Observable<List<Beacon>> getBeacons();
    Observable<RegistrationResult> registration(RegistrationDomain registrationDomain);
    Observable<LoginResult> login(LoginDomain loginDomain);


    Observable<CarOptions> upsertCarDB(Car car);
    Observable<List<CarOptions>> getCarsDB();
    void deleteCarDB(Car car);
    void updateCarDBFromNetwork(List<Car> cars);

    Observable<List<Car>> getCarsNetworkEmit();
    Observable<List<Car>> getCarsNetwork();
    Observable<UpsertCarResult> upsertCarNetwork(Car car);
    Observable<DeleteCarResult> deleteCarNetwork(Car car);
    Observable<Car> getCarDetailNetwork(int id);
}
