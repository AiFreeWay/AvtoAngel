package upplic.com.angelavto.domain.repositories;


import java.util.List;

import upplic.com.angelavto.domain.models.Product;

public interface Repository {

    List<Product> getProducts() throws Exception;
}
