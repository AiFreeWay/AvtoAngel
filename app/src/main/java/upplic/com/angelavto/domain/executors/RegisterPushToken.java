package upplic.com.angelavto.domain.executors;

import com.google.firebase.iid.FirebaseInstanceId;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import upplic.com.angelavto.domain.interactors.Interactor0;
import upplic.com.angelavto.domain.repositories.Repository;


public class RegisterPushToken implements Interactor0<String> {

    private Repository mRepository;

    @Inject
    public RegisterPushToken(Repository repository){
        mRepository = repository;
    }

    @Override
    public Observable<String> execute() {
        Observable.OnSubscribe<String> observer = subscriber -> {
            try {
                String token = FirebaseInstanceId.getInstance().getToken();
                subscriber.onNext(token);
            } catch (Exception e) {
                subscriber.onError(e);
            } finally {
                subscriber.onCompleted();
            }
        };
        return Observable.create(observer)
                .flatMap(token -> mRepository.sendGcmToken(token));
    }
}
