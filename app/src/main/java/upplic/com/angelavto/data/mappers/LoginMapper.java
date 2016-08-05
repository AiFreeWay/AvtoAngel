package upplic.com.angelavto.data.mappers;

import upplic.com.angelavto.data.net_store.requests_entityes.LoginRequest;
import upplic.com.angelavto.domain.models.Login;


public class LoginMapper {

    public static LoginRequest mapLogin(Login login) {
        return new LoginRequest(login.getNumber());
    }
}
