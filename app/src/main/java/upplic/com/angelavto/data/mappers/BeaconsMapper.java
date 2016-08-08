package upplic.com.angelavto.data.mappers;

import java.util.Arrays;
import java.util.List;

import upplic.com.angelavto.data.net_store.response_entityes.BeaconsResponse;
import upplic.com.angelavto.domain.models.Beacon;


public class BeaconsMapper {

    public static List<Beacon> mapBeacons(BeaconsResponse beaconsResponse) {
        return Arrays.asList(beaconsResponse.getResult());
    }
}
