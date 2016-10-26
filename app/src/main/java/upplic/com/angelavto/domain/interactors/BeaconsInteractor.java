package upplic.com.angelavto.domain.interactors;


import java.util.List;

import rx.Observable;
import upplic.com.angelavto.domain.models.Beacon;

public interface BeaconsInteractor {
    Observable<List<Beacon>> getBeacons();
}
