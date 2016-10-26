package upplic.com.angelavto.domain.executors;

import com.google.firebase.iid.FirebaseInstanceId;

import javax.inject.Inject;

import rx.Observable;
import upplic.com.angelavto.domain.interactors.AuthInteractor;
import upplic.com.angelavto.domain.models.LoginDomain;
import upplic.com.angelavto.domain.models.LoginResult;
import upplic.com.angelavto.domain.models.RegistrationDomain;
import upplic.com.angelavto.domain.models.RegistrationResult;
import upplic.com.angelavto.domain.repositories.Repository;


public class AuthInteractorImpl implements AuthInteractor {

    private Repository mRepository;

    @Inject
    public AuthInteractorImpl(Repository repository){
        mRepository = repository;
    }

    @Override
    public Observable<RegistrationResult> registrationPhone(RegistrationDomain data) {
        return mRepository.registration(data);
    }

    @Override
    public Observable<LoginResult> auth(LoginDomain data) {
        return mRepository.login(data);
    }

    @Override
    public Observable<Boolean> checkKey() {
        return mRepository.checkKey();
    }

    @Override
    public Observable<String> registerPushToken() {
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
