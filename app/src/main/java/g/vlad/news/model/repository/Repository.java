package g.vlad.news.model.repository;

import java.util.List;

public interface Repository<T> {

    void add(T item);

    void add(List<T> item);

    void delete(T item);

    List<T> query();

    List<T> copyQuery();

    interface RepositoryCallback {
        void onDBSuccess();
    }

}
