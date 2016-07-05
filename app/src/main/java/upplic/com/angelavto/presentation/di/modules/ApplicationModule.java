package upplic.com.angelavto.presentation.di.modules;


import android.content.Context;

import java.util.List;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import upplic.com.angelavto.data.repositories.RepositoryImpl;
import upplic.com.angelavto.domain.executors.GetProducts;
import upplic.com.angelavto.domain.interactors.Interactor0;
import upplic.com.angelavto.domain.models.Product;
import upplic.com.angelavto.domain.repositories.Repository;
import upplic.com.angelavto.presentation.app.AngelAvto;

@Module
public class ApplicationModule {

    private AngelAvto mApplication;

    public ApplicationModule(AngelAvto angelAvto) {
        mApplication = angelAvto;
    }

    @Provides
    public Context provideContext() {
        return mApplication;
    }

    @Provides
    public Repository provideRepository(RepositoryImpl repository) {
        return repository;
    }
}
