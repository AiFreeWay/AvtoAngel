package upplic.com.angelavto.presentation.view_controllers;

import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import javax.inject.Inject;
import javax.inject.Named;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import upplic.com.angelavto.R;
import upplic.com.angelavto.domain.interactors.Interactor1;
import upplic.com.angelavto.domain.models.Record;
import upplic.com.angelavto.domain.models.RoutePoint;
import upplic.com.angelavto.presentation.app.AngelAvto;
import upplic.com.angelavto.presentation.di.modules.ActivityModule;
import upplic.com.angelavto.presentation.views.activities.RecordRouteActivity;


public class AcRecordRouteCtrl extends ViewController<RecordRouteActivity> {

    @Inject
    @Named(ActivityModule.GET_RECORD_DETAIL)
    Interactor1<Record, Integer> mGetRecordDetail;

    public AcRecordRouteCtrl(RecordRouteActivity view) {
        super(view);
        mRootView.getActivityComponent()
                .inject(this);
    }

    @Override
    public void start() {
        mGetRecordDetail.execute(mRootView.getRecordId())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::createRote,
                        e -> Log.e(AngelAvto.UNIVERSAL_ERROR_TAG, "AcRecordRouteCtrl: start error "+e.toString()));
    }

    private void createRote(Record record) {
        PolylineOptions route = new PolylineOptions();
        LatLng lastLatLng = null;
        for (RoutePoint point : record.getCoords()) {
            lastLatLng = new LatLng(point.getLat(), point.getLon());
            route.add(lastLatLng);
        }

        route.color(ContextCompat.getColor(getRootView(), R.color.marron));
        route.width(3);
        mRootView.getMap().addPolyline(route);
        if (lastLatLng != null) {
            mRootView.getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(lastLatLng, 14));
            mRootView.getMap().addMarker(new MarkerOptions()
                    .title(getRootView().getString(R.string.enr_route))
                    .position(lastLatLng)
                    .draggable(false));
        }
    }
}
