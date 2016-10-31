package upplic.com.angelavto.presentation.view_controllers;

import android.support.v4.content.ContextCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;

import javax.inject.Inject;
import javax.inject.Named;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import upplic.com.angelavto.R;
import upplic.com.angelavto.domain.interactors.MapInteractor;
import upplic.com.angelavto.domain.models.Record;
import upplic.com.angelavto.domain.models.RoutePoint;
import upplic.com.angelavto.presentation.di.modules.ActivityModule;
import upplic.com.angelavto.presentation.utils.Logger;
import upplic.com.angelavto.presentation.views.activities.RecordRouteActivity;


public class AcRecordRouteCtrl extends ViewController<RecordRouteActivity> {

    @Inject @Named(ActivityModule.MAP)
    MapInteractor mMapInteractor;

    private Comparator<RoutePoint> mRouteComparator = new Comparator<RoutePoint>() {

        private SimpleDateFormat mFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        @Override
        public int compare(RoutePoint point, RoutePoint point2) {
            try {
                long time = mFormatter.parse(point.getTime()).getTime();
                long time2 = mFormatter.parse(point2.getTime()).getTime();

                if (time > time2)
                    return 1;
                else if (time == time2)
                    return 0;
                else if (time < time2)
                    return -1;
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return 0;
        }
    };

    public AcRecordRouteCtrl(RecordRouteActivity view) {
        super(view);
        mRootView.getActivityComponent()
                .inject(this);
    }

    @Override
    public void start() {
        mMapInteractor.getRecordDetail(mRootView.getRecordId())
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::createRote,
                        Logger::logError);
    }

    private void createRote(Record record) {
        PolylineOptions route = new PolylineOptions();
        LatLng lastLatLon = null;
        for (RoutePoint point : record.getCoords()) {
            lastLatLon = generateLatLngFromPoint(point);
            route.add(lastLatLon);
        }

        route.color(ContextCompat.getColor(getRootView(), R.color.marron));
        route.width(3);
        mRootView.getMap().addPolyline(route);
        if (lastLatLon != null) {
            mRootView.getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(lastLatLon, 17));
            mRootView.getMap().addMarker(new MarkerOptions()
                    .title(getRootView().getString(R.string.enr_route))
                    .position(lastLatLon)
                    .draggable(false));
        }
    }

    private LatLng generateLatLngFromPoint(RoutePoint point) {
        if (point != null)
            return new LatLng(point.getLat(), point.getLon());
        return null;
    }
}
