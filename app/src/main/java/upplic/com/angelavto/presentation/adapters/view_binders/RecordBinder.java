package upplic.com.angelavto.presentation.adapters.view_binders;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import upplic.com.angelavto.R;
import upplic.com.angelavto.domain.models.Record;
import upplic.com.angelavto.presentation.view_controllers.AcRecordsCtrl;

public class RecordBinder implements AbstractBinder<Record> {

    @BindView(R.id.v_record_tv_title)
    TextView mTvTitle;

    private AcRecordsCtrl mViewController;
    private ListView mParent;
    private LayoutInflater mLayoutInflater;

    public RecordBinder(AcRecordsCtrl controller) {
        mViewController = controller;
        mParent = mViewController.getRootView().getLvRecords();
        mLayoutInflater = mViewController.getLayoutInflater();
    }

    @Override
    public View bind(View view, Record data) {
        if (view == null)
            view = mLayoutInflater.inflate(R.layout.v_record, mParent, false);
        ButterKnife.bind(this, view);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
        try {
            Date date = formatter.parse(data.getTimeStart());
            formatter.applyPattern("dd-MM-yyyy в HH:mm");
            mTvTitle.setText(formatter.format(date));
        } catch (ParseException e) {

        }
        view.setOnClickListener(v -> mViewController.openRecordReoute(data));
        return view;
    }
}
