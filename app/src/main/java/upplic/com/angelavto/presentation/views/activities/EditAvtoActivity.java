package upplic.com.angelavto.presentation.views.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import com.balysv.materialmenu.MaterialMenuDrawable;
import com.balysv.materialmenu.extras.toolbar.MaterialMenuIconToolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import upplic.com.angelavto.R;
import upplic.com.angelavto.domain.models.Car;
import upplic.com.angelavto.presentation.view_controllers.AcEditAvtoCtrl;


public class EditAvtoActivity extends BaseActivity<AcEditAvtoCtrl> {

    public static final String CAR_TAG = "car";

    @BindView(R.id.ac_edit_avto_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.ac_edit_avto_et_car_title)
    EditText mEtCarTitle;
    @BindView(R.id.ac_edit_avto_et_phone)
    EditText mEtPhone;

    private Car mCar;
    private MaterialMenuIconToolbar mMenuDrawer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_edit_avto);
        ButterKnife.bind(this);
        mCar = (Car) getIntent().getSerializableExtra(CAR_TAG);
        initToolbar();
        mViewController = new AcEditAvtoCtrl(this);
        getSupportActionBar().setTitle(R.string.edit);
        mEtCarTitle.setText(mCar.getTitle());
        mEtPhone.setText(mCar.getPhone());
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
