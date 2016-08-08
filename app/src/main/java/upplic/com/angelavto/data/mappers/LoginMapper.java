package upplic.com.angelavto.data.mappers;


import upplic.com.angelavto.data.net_store.requests_entityes.LoginRequest;
import upplic.com.angelavto.data.net_store.response_entityes.LoginResponse;
import upplic.com.angelavto.domain.models.LoginDomain;
import upplic.com.angelavto.domain.models.LoginResult;

public class LoginMapper {

    public static LoginRequest mapLogin(LoginDomain loginDomain) {
        return new LoginRequest(loginDomain.getNumber(), loginDomain.getCode());
    }

    public static LoginResult mapLogin(LoginResponse loginResponse) {
        return new LoginResult(loginResponse.getResult().getKey());
    }
}
