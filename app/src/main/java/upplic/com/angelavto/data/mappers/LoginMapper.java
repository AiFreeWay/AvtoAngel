package upplic.com.angelavto.data.mappers;


import upplic.com.angelavto.data.net_store.requests_entityes.LoginRequest;
import upplic.com.angelavto.domain.models.LoginDomain;

public class LoginMapper {

    public static LoginRequest mapLogin(LoginDomain loginDomain) {
        return new LoginRequest(loginDomain.getNumber(), loginDomain.getCode());
    }
}
