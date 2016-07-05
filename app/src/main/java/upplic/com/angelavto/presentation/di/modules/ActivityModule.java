package upplic.com.angelavto.presentation.di.modules;


import dagger.Module;
import dagger.Provides;
import upplic.com.angelavto.presentation.utils.FragmentRouter;
import upplic.com.angelavto.presentation.utils.FragmentsFactory;
import upplic.com.angelavto.presentation.views.activities.BaseActivity;

@Module
public class ActivityModule {

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
}
