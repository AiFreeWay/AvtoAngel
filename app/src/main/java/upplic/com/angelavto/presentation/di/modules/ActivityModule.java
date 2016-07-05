package upplic.com.angelavto.presentation.di.modules;


import java.util.List;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import upplic.com.angelavto.domain.executors.GetProducts;
import upplic.com.angelavto.domain.interactors.Interactor0;
import upplic.com.angelavto.domain.models.Product;
import upplic.com.angelavto.presentation.utils.FragmentRouter;
import upplic.com.angelavto.presentation.utils.FragmentsFactory;
import upplic.com.angelavto.presentation.views.activities.BaseActivity;

@Module
public class ActivityModule {

    public static final String GET_PRODUCTS = "getproducts";

    private FragmentRouter.RouterBilder mRouterBilder;
    private FragmentsFactory mFragmentsFactory;

    public ActivityModule(BaseActivity activity) {
        mRouterBilder = new FragmentRouter.RouterBilder(activity.getSupportFragmentManager());
        mFragmentsFactory = new FragmentsFactory();
    }

    @Provides
    public FragmentRouter.RouterBilder provideRouterBilder() {
        return mRouterBilder;
    }

    @Provides
    public FragmentsFactory provideFragmentsFactory() {
        return mFragmentsFactory;
    }

    @Provides
    @Named(GET_PRODUCTS)
    public Interactor0<List<Product>> provideGetProducts(GetProducts getProducts) {
        return getProducts;
    }
}
