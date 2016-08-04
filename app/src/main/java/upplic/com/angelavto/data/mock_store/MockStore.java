package upplic.com.angelavto.data.mock_store;

import java.util.LinkedList;
import java.util.List;

import upplic.com.angelavto.domain.models.Beacon;


public class MockStore {

    private List<Beacon> mBeacons;

    public MockStore() {
        mBeacons = new LinkedList<Beacon>();
        mBeacons.add(new Beacon("Тип №1", "Харакстеристики", "1700 руб.", ""));
        mBeacons.add(new Beacon("Тип №1", "Харакстеристики", "1700 руб.", ""));
        mBeacons.add(new Beacon("Тип №1", "Харакстеристики", "1700 руб.", ""));
    }

    public List<Beacon> getGetBeacons() {
        return mBeacons;
    }
}
