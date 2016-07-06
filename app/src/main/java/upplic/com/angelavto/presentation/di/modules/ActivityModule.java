package upplic.com.angelavto.presentation.di.modules;


import java.util.List;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import upplic.com.angelavto.domain.executors.CreateOrUpdateCars;
import upplic.com.angelavto.domain.executors.GetCars;
import upplic.com.angelavto.domain.executors.GetProducts;
import upplic.com.angelavto.domain.interactors.Interactor;
import upplic.com.angelavto.domain.interactors.Interactor0;
import upplic.com.angelavto.domain.models.Car;
import upplic.com.angelavto.domain.models.Product;
import upplic.com.angelavto.presentation.utils.AppMenuFactory;
import upplic.com.angelavto.presentation.utils.FragmentRouter;
import upplic.com.angelavto.presentation.utils.FragmentsFactory;
import upplic.com.angelavto.presentation.views.activities.BaseActivity;

@Module
public class ActivityModule {

    public static final String GET_PRODUCTS = "getproducts";
    public static final String GET_CARS = "getcars";
    public static final String CREATE_OR_UPDATE_CARS = "createorupdatecars";

    private FragmentRouter.RouterBilder mRouterBilder;
    private FragmentsFactory mFragmentsFactory;
    private AppMenuFactory mAppMenuFactory;

    public ActivityModule(BaseActivity activity) {
        mRouterBilder = new FragmentRouter.RouterBilder(activity.getSupportFragmentManager());
        mFragmentsFactory = new FragmentsFactory();
        mAppMenuFactory = new AppMenuFactory();
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
    public AppMenuFactory provideAppMenuFactory() {
        return mAppMenuFactory;
    }

    @Provides
    @Named(GET_PRODUCTS)
    public Interactor0<List<Product>> provideGetProducts(GetProducts getProducts) {
        return getProducts;
    }

    @Provides
    @Named(GET_CARS)
    public Interactor0<List<Car>> provideGetCars(GetCars getCars) {
        return getCars;
    }

    @Provides
    @Named(CREATE_OR_UPDATE_CARS)
    public Interactor<Car> provideCreateOrUpdateCars(CreateOrUpdateCars createOrUpdateCars) {
        return createOrUpdateCars;
    }
}
