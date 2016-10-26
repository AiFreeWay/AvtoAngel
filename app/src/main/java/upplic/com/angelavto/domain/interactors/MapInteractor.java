package upplic.com.angelavto.domain.interactors;


import java.util.List;

import rx.Observable;
import upplic.com.angelavto.domain.models.Record;

public interface MapInteractor {

    Observable<Record> getRecordDetail(Integer recordId);
    Observable<List<Record>> getRecords(Integer carId);
}
