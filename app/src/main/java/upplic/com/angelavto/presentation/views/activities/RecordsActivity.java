package upplic.com.angelavto.presentation.views.activities;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.balysv.materialmenu.MaterialMenuDrawable;
import com.balysv.materialmenu.extras.toolbar.MaterialMenuIconToolbar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import upplic.com.angelavto.R;
import upplic.com.angelavto.domain.models.Record;
import upplic.com.angelavto.presentation.adapters.MultyListViewAdapter;
import upplic.com.angelavto.presentation.adapters.view_binders.RecordBinder;
import upplic.com.angelavto.presentation.view_controllers.AcRecordsCtrl;

public class RecordsActivity extends BaseActivity<AcRecordsCtrl> {

    @BindView(R.id.ac_records_lv_records)
    ListView mLvRecords;
    @BindView(R.id.ac_records_toolbar)
    Toolbar mToolbar;

    private MultyListViewAdapter<Record> mAdapter;
    private MaterialMenuIconToolbar mMenuDrawer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_records);
        ButterKnife.bind(this);
        mAdapter = new MultyListViewAdapter<Record>(new RecordBinder(mViewController));
        initToolbar();
        getSupportActionBar().setTitle(R.string.record_log);
        mViewController.start();
    }


    public void loadData(List<Record> records) {
        mAdapter.loadData(records);
    }

    public ListView getLvRecords() {
        return mLvRecords;
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);
        mToolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white));
        mToolbar.setNavigationOnClickListener(v -> finish());
        mMenuDrawer = new MaterialMenuIconToolbar(this, Color.WHITE, MaterialMenuDrawable.Stroke.THIN) {
            @Override
            public int getToolbarViewId() {
                return R.id.ac_edit_avto_toolbar;
            }
        };
        mMenuDrawer.setState(MaterialMenuDrawable.IconState.ARROW);
    }
}
