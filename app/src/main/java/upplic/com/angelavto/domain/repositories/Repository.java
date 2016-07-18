package upplic.com.angelavto.domain.repositories;


import java.util.List;

import rx.subjects.PublishSubject;
import rx.subjects.ReplaySubject;
import upplic.com.angelavto.domain.models.Car;
import upplic.com.angelavto.domain.models.Product;

public interface Repository {

    List<Product> getProducts() throws Exception;
    ReplaySubject<List<Car>> getCars();
    void createOrUdateCar(Car car) throws Exception;
    Car getCarById(int id) throws Exception;
}
