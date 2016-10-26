package upplic.com.angelavto.presentation.view_controllers;


import android.util.Log;
import android.widget.Toast;

import javax.inject.Inject;
import javax.inject.Named;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import upplic.com.angelavto.domain.interactors.CarsInteractor;
import upplic.com.angelavto.domain.models.Car;
import upplic.com.angelavto.presentation.app.AngelAvto;
import upplic.com.angelavto.presentation.di.modules.ActivityModule;
import upplic.com.angelavto.presentation.views.activities.EditAvtoActivity;

public class AcEditAvtoCtrl extends ViewController<EditAvtoActivity> {

    @Inject @Named(ActivityModule.CARS)
    CarsInteractor mCarsInteractor;

    public AcEditAvtoCtrl(EditAvtoActivity view) {
        super(view);
        mRootView.getActivityComponent()
                .inject(this);
    }

    @Override
    public void start() {

    }

    public void updateCar(Car car) {
        mCarsInteractor.updateCar(car)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnCompleted(() -> {
                    String message = "Настройки для '"+car.getTitle()+"' изменены.";
                    Toast.makeText(getRootView(), message, Toast.LENGTH_SHORT).show();})
                .subscribe(aVoid -> {},
                        e -> Log.e(AngelAvto.UNIVERSAL_ERROR_TAG, "FmtGarageCtrl: hundleClick error "+e.toString()));
    }

    public void deleteCar(Car car) {
        mCarsInteractor.deleteCar(car)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(deleteCarResult -> backToMainActivity(),
                        e -> Log.e(AngelAvto.UNIVERSAL_ERROR_TAG, "FmtGarageCtrl: hundleClick error "+e.toString()));
    }

    private void backToMainActivity() {
        mRootView.finish();
    }
}
