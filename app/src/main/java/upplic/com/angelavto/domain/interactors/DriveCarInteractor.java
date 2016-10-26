package upplic.com.angelavto.domain.interactors;


import java.util.List;

import rx.Observable;
import upplic.com.angelavto.domain.models.CarOptions;
import upplic.com.angelavto.domain.models.Status;

public interface DriveCarInteractor {

    Observable<Integer> deleteAllCarOptions();
    Observable<List<CarOptions>> getCarsOptions();
    Observable<CarOptions> updateCarOptions(CarOptions data);
    Observable<Status> setStatus(Status data);
}
