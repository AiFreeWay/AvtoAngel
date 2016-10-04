package upplic.com.angelavto.presentation.views.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import upplic.com.angelavto.R;
import upplic.com.angelavto.presentation.views.activities.MainActivity;

public class AboutFragment extends BaseFragment {

    @BindView(R.id.fmt_about_tv_description)
    TextView mTvDescription;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fmt_about, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mTvDescription.setText("");
        SpannableString colorText = new SpannableString("Приложение разработано IT компанией");
        colorText.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getContext(), R.color.slate_gray)), 0 ,colorText.length() , Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        mTvDescription.append(colorText);
        SpannableString colorText2 = new SpannableString(" Upplic");
        colorText2.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getContext(), R.color.green_jungle_krayola)), 0 ,colorText2.length() , Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        mTvDescription.append(colorText2);
        SpannableString colorText3 = new SpannableString(" www.upplic.com");
        colorText3.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getContext(), R.color.slate_gray)), 0 ,colorText3.length() , Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        mTvDescription.append(colorText3);

    }

    @Override
    public void onStart() {
        super.onStart();
        try {
            getBaseActivity().getSupportActionBar().setTitle(R.string.about);
            ((MainActivity) getBaseActivity()).getToolbar().getMenu().clear();
        } catch (NullPointerException e) {

        }
    }
}
