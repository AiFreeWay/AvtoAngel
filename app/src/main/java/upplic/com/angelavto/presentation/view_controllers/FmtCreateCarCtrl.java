package upplic.com.angelavto.presentation.view_controllers;


import android.util.Log;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import upplic.com.angelavto.R;
import upplic.com.angelavto.domain.interactors.Interactor0;
import upplic.com.angelavto.domain.interactors.Interactor1;
import upplic.com.angelavto.domain.models.Beacon;
import upplic.com.angelavto.domain.models.Car;
import upplic.com.angelavto.domain.models.UpsertCarResult;
import upplic.com.angelavto.presentation.app.AngelAvto;
import upplic.com.angelavto.presentation.di.modules.ActivityModule;
import upplic.com.angelavto.presentation.views.fragments.CreateCarFragment;

public class FmtCreateCarCtrl extends ViewController<CreateCarFragment> {

    @Inject @Named(ActivityModule.CREATE_CAR)
    Interactor1<UpsertCarResult, Car> mCreateCar;
    @Inject @Named(ActivityModule.GET_BEACONS)
    Interactor0<List<Beacon>> mGetBeacons;

    public FmtCreateCarCtrl(CreateCarFragment view) {
        super(view);
        mRootView.getActivityComponent()
                .inject(this);
    }

    @Override
    public void start() {
        mGetBeacons.execute()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(mRootView::showStartLoad)
                .subscribe(products -> {mRootView.loadData(products);
                            mRootView.showSuccesLoad();},
                        e -> { mRootView.showDeniedLoad(R.string.cant_load_data);
                            Log.e(AngelAvto.UNIVERSAL_ERROR_TAG, "FmtCreateCarCtrl: start error "+e.toString());});
    }

    public void addCar(Car car) {
        mCreateCar.execute(car)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(mRootView::showStartLoad)
                .subscribe(upsertCarResult -> {
                            mRootView.showSuccesLoad();
                            if (upsertCarResult.isSuccess()) {
                                mRootView.truncateFields();
                                Toast.makeText(mRootView.getContext(), R.string.create_car_success, Toast.LENGTH_SHORT).show();
                            } else
                                Toast.makeText(mRootView.getContext(), R.string.car_exists, Toast.LENGTH_SHORT).show();},
                        e -> { mRootView.showDeniedLoad(R.string.cant_final_execute);
                            Log.e(AngelAvto.UNIVERSAL_ERROR_TAG, "FmtCreateCarCtrl: addCar error "+e.toString());});
    }
}
