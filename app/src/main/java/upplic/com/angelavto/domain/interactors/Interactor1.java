package upplic.com.angelavto.domain.interactors;

import rx.Observable;


public interface Interactor1<O, I> {

    Observable<O> execute(I data);
}
