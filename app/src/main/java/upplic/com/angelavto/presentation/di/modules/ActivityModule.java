package upplic.com.angelavto.presentation.di.modules;


import java.util.List;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import upplic.com.angelavto.domain.executors.CreateOrUpdateCars;
import upplic.com.angelavto.domain.executors.GetCarById;
import upplic.com.angelavto.domain.executors.GetCars;
import upplic.com.angelavto.domain.executors.GetProducts;
import upplic.com.angelavto.domain.interactors.Interactor;
import upplic.com.angelavto.domain.interactors.Interactor0;
import upplic.com.angelavto.domain.interactors.Interactor1;
import upplic.com.angelavto.domain.models.Car;
import upplic.com.angelavto.domain.models.Product;
import upplic.com.angelavto.presentation.factories.AppMenuFactory;
import upplic.com.angelavto.presentation.factories.AvtoViewPagerFactory;
import upplic.com.angelavto.presentation.utils.FragmentRouter;
import upplic.com.angelavto.presentation.factories.FragmentsFactory;
import upplic.com.angelavto.presentation.views.activities.BaseActivity;

@Module
public class ActivityModule {

    public static final String GET_PRODUCTS = "getproducts";
    public static final String GET_CARS = "getcars";
    public static final String CREATE_OR_UPDATE_CARS = "createorupdatecars";
    public static final String GET_CAR_BY_ID = "getcarbyid";

    private FragmentRouter.RouterBilder mRouterBilder;
    private FragmentsFactory mFragmentsFactory;
    private AppMenuFactory mAppMenuFactory;
    private AvtoViewPagerFactory.Builder mAvtoViewPagerFactoryBuilder;

    public ActivityModule(BaseActivity activity) {
        mRouterBilder = new FragmentRouter.RouterBilder(activity.getSupportFragmentManager());
        mFragmentsFactory = new FragmentsFactory();
        mAppMenuFactory = new AppMenuFactory(activity);
        mAvtoViewPagerFactoryBuilder = new AvtoViewPagerFactory.Builder();
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
    public AvtoViewPagerFactory.Builder provideAvtoViewPagerFactoryBuilder() {
        return mAvtoViewPagerFactoryBuilder;
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

    @Provides
    @Named(GET_CAR_BY_ID)
    public Interactor1<Car, Integer> provideGetCarById(GetCarById getCarById) {
        return getCarById;
    }
}
