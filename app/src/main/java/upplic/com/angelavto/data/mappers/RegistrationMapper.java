package upplic.com.angelavto.data.mappers;

import upplic.com.angelavto.data.net_store.requests_entityes.RegistrationRequest;
import upplic.com.angelavto.data.net_store.response_entityes.RegistrationResponse;
import upplic.com.angelavto.domain.models.RegistrationDomain;
import upplic.com.angelavto.domain.models.RegistrationResult;


public class RegistrationMapper {

    public static RegistrationRequest mapRegistration(RegistrationDomain registrationDomain) {
        return new RegistrationRequest(registrationDomain.getNumber());
    }

    public static RegistrationResult mapRegistration(RegistrationResponse registrationResponse) {
        return new RegistrationResult(registrationResponse.getResult());
    }
}
