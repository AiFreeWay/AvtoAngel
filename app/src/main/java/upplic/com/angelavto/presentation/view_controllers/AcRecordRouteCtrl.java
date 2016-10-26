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
import upplic.com.angelavto.domain.interactors.MapInteractor;
import upplic.com.angelavto.domain.models.Record;
import upplic.com.angelavto.domain.models.RoutePoint;
import upplic.com.angelavto.AngelAvto;
import upplic.com.angelavto.presentation.di.modules.ActivityModule;
import upplic.com.angelavto.presentation.utils.Logger;
import upplic.com.angelavto.presentation.views.activities.RecordRouteActivity;


public class AcRecordRouteCtrl extends ViewController<RecordRouteActivity> {

    @Inject @Named(ActivityModule.MAP)
    MapInteractor mMapInteractor;

    public AcRecordRouteCtrl(RecordRouteActivity view) {
        super(view);
        mRootView.getActivityComponent()
                .inject(this);
    }

    @Override
    public void start() {
        mMapInteractor.getRecordDetail(mRootView.getRecordId())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::createRote,
                        Logger::logError);
    }

    private void createRote(Record record) {
        PolylineOptions route = new PolylineOptions();
        LatLng firstLatLng = generateLatLngFromPoint(record.getCoords()[0]);
        for (RoutePoint point : record.getCoords())
            route.add(generateLatLngFromPoint(point));

        route.color(ContextCompat.getColor(getRootView(), R.color.marron));
        route.width(3);
        mRootView.getMap().addPolyline(route);
        if (firstLatLng != null) {
            mRootView.getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(firstLatLng, 14));
            mRootView.getMap().addMarker(new MarkerOptions()
                    .title(getRootView().getString(R.string.enr_route))
                    .position(firstLatLng)
                    .draggable(false));
        }
    }

    private LatLng generateLatLngFromPoint(RoutePoint point) {
        if (point != null)
            return new LatLng(point.getLat(), point.getLon());
        return null;
    }
}
