package upplic.com.angelavto.data.db_store.tables;

import android.os.Parcelable;

import io.requery.Entity;
import io.requery.Generated;
import io.requery.Key;
import io.requery.Persistable;

@Entity
public interface CarTable extends Parcelable, Persistable {

    @Key @Generated
    int getId();

    String getTitle();
    int getTrackerType();
    String getTrackerNumber();
    int getNotificationState();
    int getSecuritynState();
}