package upplic.com.angelavto.presentation.view_controllers;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import upplic.com.angelavto.domain.interactors.Interactor;
import upplic.com.angelavto.domain.interactors.Interactor0;
import upplic.com.angelavto.domain.models.Car;
import upplic.com.angelavto.domain.models.Product;
import upplic.com.angelavto.presentation.app.AngelAvto;
import upplic.com.angelavto.presentation.di.modules.ActivityModule;
import upplic.com.angelavto.presentation.utils.AppMenuFactory;
import upplic.com.angelavto.presentation.views.fragments.ShopFragment;


public class FmtShopCtrl extends ViewController<ShopFragment> {

    @Inject @Named(ActivityModule.GET_PRODUCTS)
    Interactor0<List<Product>> mGetProducts;

    @Inject @Named(ActivityModule.CREATE_OR_UPDATE_CARS)
    Interactor<Car> mCreateOrUpdateCars;

    private LayoutInflater mLayoutInflater;

    public FmtShopCtrl(ShopFragment view) {
        super(view);
        mLayoutInflater = (LayoutInflater) mRootView.getBaseActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mRootView.getActivityComponent()
                .inject(this);
    }

    @Override
    public void start() {
        mGetProducts.execute()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(mRootView::showStartLoad)
                .subscribe(products -> {mRootView.loadData(products);
                    mRootView.showSuccesLoad();},
                        e -> { Log.e(AngelAvto.UNIVERSAL_ERROR_TAG, "FmtShopCtrl: start error"+e.toString());
                        mRootView.showDeniedLoad();});
    }

    public LayoutInflater getLayoutInflater() {
        return  mLayoutInflater;
    }

    public void hundleProductItemClick(Product data) {

    }
}
