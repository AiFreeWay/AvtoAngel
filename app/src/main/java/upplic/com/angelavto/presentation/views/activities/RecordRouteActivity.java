package upplic.com.angelavto.presentation.views.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;

import com.balysv.materialmenu.MaterialMenuDrawable;
import com.balysv.materialmenu.extras.toolbar.MaterialMenuIconToolbar;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import upplic.com.angelavto.R;
import upplic.com.angelavto.domain.models.Record;
import upplic.com.angelavto.presentation.view_controllers.AcRecordRouteCtrl;


public class RecordRouteActivity extends BaseActivity<AcRecordRouteCtrl> {

    public static final String RECORD_TAG = "record";
    private final Calendar CALENDAR = new GregorianCalendar();

    @BindView(R.id.ac_records_route_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.ac_records_route_mv_map)
    MapView mMvMap;

    private GoogleMap mMap;
    private MaterialMenuIconToolbar mMenuDrawer;
    private Record mRecord;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_record_route);
        ButterKnife.bind(this);
        mRecord = (Record) getIntent().getSerializableExtra(RECORD_TAG);
        mViewController = new AcRecordRouteCtrl(this);
        mMvMap.onCreate(savedInstanceState);
        mMvMap.getMapAsync(this::startMap);
        initToolbar();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
        try {
            Date date = formatter.parse(mRecord.getTimeStart());
            formatter.applyPattern("dd-MM-yyyy в HH:mm");
            formatter.setTimeZone(CALENDAR.getTimeZone());
            getSupportActionBar().setTitle(formatter.format(date));
        } catch (ParseException e) {

        }
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
        mMvMap.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMvMap.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMvMap.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMvMap.onLowMemory();
    }

    public int getRecordId() {
        return mRecord.getId();
    }

    public GoogleMap getMap() {
        return mMap;
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);
        mToolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white));
        mToolbar.setNavigationOnClickListener(v -> finish());
        mMenuDrawer = new MaterialMenuIconToolbar(this, Color.WHITE, MaterialMenuDrawable.Stroke.THIN) {
            @Override
            public int getToolbarViewId() {
                return R.id.ac_records_route_toolbar;
            }
        };
        mMenuDrawer.setState(MaterialMenuDrawable.IconState.ARROW);
    }

    private void startMap(GoogleMap map) {
        mMap = map;
        mViewController.start();
    }
}
