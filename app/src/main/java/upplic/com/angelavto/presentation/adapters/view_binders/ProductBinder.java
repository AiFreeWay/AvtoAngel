package upplic.com.angelavto.presentation.adapters.view_binders;


import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import butterknife.ButterKnife;
import upplic.com.angelavto.R;
import upplic.com.angelavto.domain.models.Product;
import upplic.com.angelavto.presentation.view_controllers.FmtShopCtrl;

public class ProductBinder implements AbstractBinder<Product> {

    private FmtShopCtrl mViewController;
    private ListView mParent;
    private LayoutInflater mLayoutInflater;

    public ProductBinder(FmtShopCtrl controller) {
        mViewController = controller;
        mParent = mViewController.getRootView().getLvProducts();
        mLayoutInflater = mViewController.getLayoutInflater();
    }

    @Override
    public View bind(View view, Product data) {
        if (view == null)
            view = mLayoutInflater.inflate(R.layout.v_shop, mParent, false);
        ButterKnife.bind(this, view);
        return view;
    }
}
