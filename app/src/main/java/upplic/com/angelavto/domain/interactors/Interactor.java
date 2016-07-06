package upplic.com.angelavto.domain.interactors;


import rx.Observable;

public interface Interactor<I> {

    Observable<Void> execute(I data);
}
