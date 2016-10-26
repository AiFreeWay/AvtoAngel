package upplic.com.angelavto.presentation.view_controllers;


import android.widget.Toast;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import upplic.com.angelavto.R;
import upplic.com.angelavto.domain.interactors.CarsInteractor;
import upplic.com.angelavto.domain.models.Car;
import upplic.com.angelavto.domain.models.UpsertCarResult;
import upplic.com.angelavto.presentation.di.modules.ActivityModule;
import upplic.com.angelavto.presentation.utils.Logger;
import upplic.com.angelavto.presentation.views.activities.EditAvtoActivity;

public class AcEditAvtoCtrl extends ViewController<EditAvtoActivity> {

    @Inject @Named(ActivityModule.CARS)
    CarsInteractor mCarsInteractor;

    public AcEditAvtoCtrl(EditAvtoActivity view) {
        super(view);
        mRootView.getActivityComponent()
                .inject(this);
    }

    public void updateCar(Car car) {
        Observable<UpsertCarResult> carUpdateObservable;
        if (mRootView.getCar().getTitle().equals(car.getTitle()))
            carUpdateObservable = mCarsInteractor.updateCar(car);
        else
            carUpdateObservable = mCarsInteractor.upsertCar(car);

        carUpdateObservable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(upsertCarResult -> {
                    String message;
                    if (upsertCarResult.isSuccess()) {
                        mRootView.setCar(car);
                        message = "Настройки для '" + car.getTitle() + "' изменены.";
                    } else
                        message = generateErrorMessageFromUpsertCarErrorType(upsertCarResult.getErrorType());
                Toast.makeText(getRootView(), message, Toast.LENGTH_SHORT).show();},
                        Logger::logError);
    }

    public void deleteCar(Car car) {
        mCarsInteractor.deleteCar(car)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(deleteCarResult -> backToMainActivity(),
                        Logger::logError);
    }

    private void backToMainActivity() {
        mRootView.finish();
    }

    private String generateErrorMessageFromUpsertCarErrorType(int errorType) {
        String message = null;
        if (errorType == UpsertCarResult.CAR_TITLE_EXISTS_ERROR_TYPE)
            message = mRootView.getString(R.string.car_exists);
        else if (errorType == UpsertCarResult.IMEI_EXISTS_ERROR_TYPE)
            message = mRootView.getString(R.string.imei_exists);
        else if (errorType == UpsertCarResult.BEACON_SIM_NUMBER_EXISTS_ERROR_TYPE)
            message = mRootView.getString(R.string.phone_exists);
        return message;
    }
}
