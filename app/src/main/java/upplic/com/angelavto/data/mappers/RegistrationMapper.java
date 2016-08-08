package upplic.com.angelavto.data.mappers;

import upplic.com.angelavto.data.net_store.requests_entityes.RegistrationRequest;
import upplic.com.angelavto.domain.models.RegistrationDomain;


public class RegistrationMapper {

    public static RegistrationRequest mapRegistration(RegistrationDomain registrationDomain) {
        return new RegistrationRequest(registrationDomain.getNumber());
    }
}
