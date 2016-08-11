package upplic.com.angelavto.data.db_store.tables;

import android.os.Parcelable;

import io.requery.Entity;
import io.requery.Generated;
import io.requery.Key;
import io.requery.Persistable;

@Entity
public interface CarOptionsTable extends Parcelable, Persistable {

    @Key
    int getId();

    String getTitle();
    boolean getNotification();
    long getEditDate();
}
