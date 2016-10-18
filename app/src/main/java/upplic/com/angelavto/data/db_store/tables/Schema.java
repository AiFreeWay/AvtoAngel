package upplic.com.angelavto.data.db_store.tables;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import io.requery.meta.EntityModel;
import io.requery.meta.NotMappedException;
import io.requery.meta.Type;


public class Schema implements EntityModel {

    public static final String DATA_BASE_NAME = "avto_angel_base";

    private Map<Class, Type<?>> mTables;

    public Schema() {
        mTables = new HashMap<>();
        mTables.put(CarOptionsTableEntity.class, CarOptionsTableEntity.$TYPE);
        mTables.put(AlarmTableEntity.class, AlarmTableEntity.$TYPE);
    }

    @Override
    public String getName() {
        return DATA_BASE_NAME;
    }

    @Override
    public <T> Type<T> typeOf(Class<? extends T> entityClass) throws NotMappedException {
        return (Type<T>) mTables.get(entityClass);
    }

    @Override
    public <T> boolean containsTypeOf(Class<? extends T> entityClass) {
        return mTables.containsKey(entityClass);
    }

    @Override
    public Set<Type<?>> getTypes() {
        return new HashSet(mTables.values());
    }
}
