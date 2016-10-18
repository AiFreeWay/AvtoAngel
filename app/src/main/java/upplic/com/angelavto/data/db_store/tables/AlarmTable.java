package upplic.com.angelavto.data.db_store.tables;

import android.os.Parcelable;

import io.requery.Entity;
import io.requery.Key;
import io.requery.Persistable;

@Entity
public interface AlarmTable extends Parcelable, Persistable {

    @Key
    int getId();

    String getTitle();
    int getStatus();
}
