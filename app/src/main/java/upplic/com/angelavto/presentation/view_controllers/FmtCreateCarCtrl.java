package upplic.com.angelavto.presentation.view_controllers;


import android.widget.Toast;

import javax.inject.Inject;
import javax.inject.Named;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import upplic.com.angelavto.R;
import upplic.com.angelavto.domain.interactors.BeaconsInteractor;
import upplic.com.angelavto.domain.interactors.CarsInteractor;
import upplic.com.angelavto.domain.models.Car;
import upplic.com.angelavto.domain.models.UpsertCarResult;
import upplic.com.angelavto.presentation.di.modules.ActivityModule;
import upplic.com.angelavto.presentation.utils.Logger;
import upplic.com.angelavto.presentation.views.fragments.CreateCarFragment;

public class FmtCreateCarCtrl extends ViewController<CreateCarFragment> {

    @Inject @Named(ActivityModule.CARS)
    CarsInteractor mCarsInteractor;
    @Inject @Named(ActivityModule.BEACONS)
    BeaconsInteractor mBeaconsInteractor;

    public FmtCreateCarCtrl(CreateCarFragment view) {
        super(view);
        mRootView.getActivityComponent()
                .inject(this);
    }

    @Override
    public void start() {
        mBeaconsInteractor.getBeacons()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(mRootView::showStartLoad)
                .subscribe(products -> {mRootView.loadData(products);
                            mRootView.showSuccesLoad();},
                        e -> { mRootView.showDeniedLoad();
                            Logger.logError(e);});
    }

    public void addCar(Car car) {
        mCarsInteractor.upsertCar(car)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(mRootView::showStartLoad)
                .subscribe(upsertCarResult -> {
                            mRootView.showSuccesLoad();
                            String message;
                            if (upsertCarResult.isSuccess()) {
                                mRootView.truncateFields();
                                message = mRootView.getString(R.string.create_car_success);
                            } else
                                message = generateErrorMessageFromUpsertCarErrorType(upsertCarResult.getErrorType());
                            Toast.makeText(mRootView.getContext(), message, Toast.LENGTH_SHORT).show();},
                        e -> { mRootView.showDeniedLoad();
                            Logger.logError(e);});
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
