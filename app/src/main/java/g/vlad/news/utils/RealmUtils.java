package g.vlad.news.utils;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;

public class RealmUtils {
    public static Realm getRealmInstance() {
        return Realm.getDefaultInstance();
    }

    public static <T extends RealmObject> void writeListToDB(final List<T> object, final DBSuccessCallback callback) {
        getRealmInstance().executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                bgRealm.insertOrUpdate(object);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                if (callback != null)
                    callback.onDBWriteSuccess();
            }
        });
    }

    public static <T extends RealmObject> void writeToDB(final T object) {
        getRealmInstance().executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                bgRealm.insertOrUpdate(object);
            }
        });
    }

    public static <T extends RealmObject> T readFromDB(Class<T> c) {
        return getRealmInstance().where(c).findFirst();
    }

    public static <T extends RealmObject> List<T> readAllWithoutSort(Class<T> c) {
        return getRealmInstance().where(c).findAll();
    }

    public interface DBSuccessCallback {
        void onDBWriteSuccess();

    }
}
