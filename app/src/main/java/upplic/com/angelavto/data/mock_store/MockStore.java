package upplic.com.angelavto.data.mock_store;


import java.util.LinkedList;
import java.util.List;

import upplic.com.angelavto.domain.models.Product;

public class MockStore {

    private List<Product> mProducts;

    public MockStore() {
        mProducts = new LinkedList<Product>();
        mProducts.add(new Product("Тип №1", "Харакстеристики", "1700 руб.", ""));
        mProducts.add(new Product("Тип №1", "Харакстеристики", "1700 руб.", ""));
        mProducts.add(new Product("Тип №1", "Харакстеристики", "1700 руб.", ""));
    }

    public List<Product> getProducts() {
        return mProducts;
    }
}
