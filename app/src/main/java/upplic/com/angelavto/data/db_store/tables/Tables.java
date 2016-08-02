package upplic.com.angelavto.data.db_store.tables;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import io.requery.meta.EntityModel;
import io.requery.meta.NotMappedException;
import io.requery.meta.Type;


public class Tables implements EntityModel {

    public static final String TABLE_NAME = "avto_angel_base";

    private Map<Class, Type<?>> mTables;

    public Tables() {
        mTables = new HashMap<>();
        mTables.put(CarTableEntity.class, CarTableEntity.$TYPE);
    }

    @Override
    public String getName() {
        return TABLE_NAME;
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
