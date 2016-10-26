package upplic.com.angelavto.domain.interactors;

import rx.Observable;
import upplic.com.angelavto.domain.models.LoginDomain;
import upplic.com.angelavto.domain.models.LoginResult;
import upplic.com.angelavto.domain.models.RegistrationDomain;
import upplic.com.angelavto.domain.models.RegistrationResult;


public interface AuthInteractor {

    Observable<RegistrationResult> registrationPhone(RegistrationDomain data);
    Observable<LoginResult> auth(LoginDomain data);
    Observable<Boolean> checkKey();
    Observable<String> registerPushToken();
}
