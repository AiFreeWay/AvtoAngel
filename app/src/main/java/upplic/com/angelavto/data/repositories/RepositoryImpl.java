package upplic.com.angelavto.data.repositories;


import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import upplic.com.angelavto.data.mock_store.MockStore;
import upplic.com.angelavto.domain.models.Product;
import upplic.com.angelavto.domain.repositories.Repository;

@Singleton
public class RepositoryImpl implements Repository {

    private MockStore mMockStore;

    @Inject
    public RepositoryImpl() {
        mMockStore = new MockStore();
    }

    @Override
    public List<Product> getProducts() throws Exception {
        return mMockStore.getProducts();
    }
}
