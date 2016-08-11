package upplic.com.angelavto.data.repositories;

import android.content.Context;

import com.orhanobut.hawk.Hawk;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.subjects.ReplaySubject;
import upplic.com.angelavto.data.db_store.SqliteController;
import upplic.com.angelavto.data.db_store.table_controllers.CarDBController;
import upplic.com.angelavto.data.db_store.tables.CarOptionsTableEntity;
import upplic.com.angelavto.data.mappers.BeaconsMapper;
import upplic.com.angelavto.data.mappers.CarMapper;
import upplic.com.angelavto.data.mappers.CarOptionsMapper;
import upplic.com.angelavto.data.mappers.LoginMapper;
import upplic.com.angelavto.data.mappers.RegistrationMapper;
import upplic.com.angelavto.data.net_store.NetworkController;
import upplic.com.angelavto.domain.models.Beacon;
import upplic.com.angelavto.domain.models.Car;
import upplic.com.angelavto.domain.models.CarOptions;
import upplic.com.angelavto.domain.models.DeleteCarResult;
import upplic.com.angelavto.domain.models.LoginDomain;
import upplic.com.angelavto.domain.models.LoginResult;
import upplic.com.angelavto.domain.models.RegistrationDomain;
import upplic.com.angelavto.domain.models.RegistrationResult;
import upplic.com.angelavto.domain.models.UpsertCarResult;
import upplic.com.angelavto.domain.repositories.Repository;
import upplic.com.angelavto.presentation.views.activities.LoginActivity;


@Singleton
public class RepositoryImpl implements Repository {

    private NetworkController mNetworkController;
    private ReplaySubject<List<Car>> mCarSubject;
    private CarDBController mCarDBController;

    @Inject
    public RepositoryImpl(Context context) {
        SqliteController dBStore = new SqliteController(context);
        mCarDBController = dBStore.getCarDBController();
        mNetworkController = new NetworkController();
        mCarSubject = ReplaySubject.create();
    }

    @Override
    public Observable<List<Beacon>> getBeacons() {
        return mNetworkController.getBeacons()
                .flatMap(beaconsResponse -> Observable.just(BeaconsMapper.mapBeacons(beaconsResponse)));
    }

    @Override
    public Observable<UpsertCarResult> upsertCarNetwork(Car car) {
        return mNetworkController.upsertCar(getToken(), car)
                .map(upsertCarResponse1 -> {
                    getCarsNetworkEmit().subscribe();
                    return upsertCarResponse1;
                })
                .flatMap(upsertCarResponse -> Observable.just(CarMapper.mapUpsertCarNetwork(upsertCarResponse)));
    }

    @Override
    public Observable<DeleteCarResult> deleteCarNetwork(Car car) {
        return mNetworkController.deleteCar(getToken(), car.getId())
                .flatMap(deleteCarResponse -> Observable.just(CarMapper.mapDeleteCarNetwork(deleteCarResponse)));
    }

    @Override
    public Observable<Car> getCarDetailNetwork(int id) {
        return mNetworkController.getCarDetail(getToken(), id)
                .flatMap(getCarDetailResponse -> Observable.just(CarMapper.mapCarDetailFromNetwork(getCarDetailResponse)));
    }

    @Override
    public Observable<CarOptions> upsertCarDB(Car car) {
        return mCarDBController.upsertCar(car)
                .flatMap(carOptionsTableEntity -> Observable.just(CarOptionsMapper.mapCarOptions(carOptionsTableEntity)));
    }

    @Override
    public Observable<List<CarOptions>> getCarsDB() {
        return Observable.just(CarOptionsMapper.mapCarOptions(mCarDBController.getCars()));
    }

    @Override
    public void deleteCarDB(Car car) {
        mCarDBController.deleteCar(car);
    }

    @Override
    public void updateCarDBFromNetwork(List<Car> cars) {
        mCarDBController.updateCarDBFromNetwork(cars);
    }

    @Override
    public Observable<List<Car>> getCarsNetworkEmit() {
        return mNetworkController.getCars(getToken())
                .flatMap(getCarsResponse -> {
                    mCarSubject.onNext(CarMapper.mapCarsFromNetwork(getCarsResponse));
                    return mCarSubject;
                });
    }

    @Override
    public Observable<List<Car>> getCarsNetwork() {
        return mNetworkController.getCars(getToken())
                .flatMap(getCarsResponse -> Observable.just(CarMapper.mapCarsFromNetwork(getCarsResponse)));
    }

    @Override
    public Observable<RegistrationResult> registration(RegistrationDomain registrationDomain) {
        return mNetworkController.registration(registrationDomain)
                .flatMap(registrationResponse -> Observable.just(RegistrationMapper.mapRegistration(registrationResponse)));
    }

    @Override
    public Observable<LoginResult> login(LoginDomain loginDomain) {
        return mNetworkController.login(loginDomain)
                .flatMap(loginResponse -> Observable.just(LoginMapper.mapLogin(loginResponse)));
    }

    private String getToken() {
        return Hawk.<String>get(LoginActivity.API_KEY_TAG);
    }
}
