package upplic.com.angelavto.presentation.utils;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import java.io.IOException;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import upplic.com.angelavto.AngelAvto;


public class LocationAdressManager {

    private Geocoder mGeocoder;
    private boolean mIsFree = true;
    private DoOnGetAdress mGetAdressListener;

    public LocationAdressManager(Context context, DoOnGetAdress doOnGetAdress) {
        mGeocoder = new Geocoder(context);
        mGetAdressListener = doOnGetAdress;
    }

    public void start(double lat, double lon) {
        if (mIsFree)
            getLocationAdresses(lat, lon)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe(() -> mIsFree = false)
                    .subscribe(addresses -> {
                                mGetAdressListener.doOnGetAdress(addresses);
                                mIsFree = true;},
                            e -> {
                                mIsFree = true;
                                Logger.logError(e);});
    }

    private Observable<List<Address>> getLocationAdresses(double lat, double lon) {
        Observable.OnSubscribe<List<Address>> observer = subscriber -> {
            try {
                List<Address> adresses = mGeocoder.getFromLocation(lat, lon, 3);
                subscriber.onNext(adresses);
            } catch (IOException e) {
                subscriber.onError(e);
            }
        };
        return Observable.create(observer);
    }

    public interface DoOnGetAdress {

        void doOnGetAdress(List<Address> addresses);
    }
}
