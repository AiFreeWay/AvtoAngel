package upplic.com.angelavto.data.mappers;


import java.util.HashMap;

import upplic.com.angelavto.data.net_store.NetworkController;
import upplic.com.angelavto.domain.models.Login;

public class LoginMapper {

    private static final String METHOD_VALUE = "requestCode";
    private static final String NUMBER = "number";

    public static HashMap<String, Object> mapLogin(Login login) {
        HashMap<String, Object> requestDataMap = new HashMap<>();
        requestDataMap.putAll(NetworkController.getConfigMap());
        requestDataMap.put(NetworkController.METHOD_KEY, METHOD_VALUE);
        requestDataMap.put(NetworkController.PARAMS_KEY, parseModelToParams(login));
        return requestDataMap;
    }

    public static HashMap<String, Object> parseModelToParams(Login login) {
        HashMap<String, Object> params = new HashMap<>();
        params.put(NUMBER, login.getNumber());
        return params;
    }
}
