package g.vlad.news.model.repository;

import java.util.ArrayList;
import java.util.List;

import g.vlad.news.model.Article;
import g.vlad.news.utils.RealmUtils;

import static g.vlad.news.utils.RealmUtils.getRealmInstance;

public class ArticlesRepository implements RealmUtils.DBSuccessCallback, Repository<Article> {
    private RepositoryCallback callback;

    public ArticlesRepository(RepositoryCallback callback) {
        this.callback = callback;
    }

    public ArticlesRepository() {
    }

    @Override
    public void add(Article item) {
        RealmUtils.writeToDB(item);
    }

    @Override
    public void add(List<Article> item) {
        List<Article> fromDb = RealmUtils.readAllWithoutSort(Article.class);
        if (fromDb.size() == 0) {
            RealmUtils.writeListToDB(item, this);
            return;
        }
        List<Article> toDb = new ArrayList<>();
        for (Article article : fromDb) {
            if (!item.contains(article)) {
                toDb.add(article);
            }
        }
        RealmUtils.writeListToDB(toDb, this);
    }

    @Override
    public void delete(Article item) {

    }

    @Override
    public List<Article> query() {
        return RealmUtils.readAllWithoutSort(Article.class);
    }

    @Override
    public List<Article> copyQuery() {
        return getRealmInstance().copyFromRealm(query());
    }

    public List<Article> getBySources(String source) {
        return getRealmInstance().where(Article.class).equalTo("source", source).findAll();
    }

    public List<Article> getAllFavorites() {
        return getRealmInstance().where(Article.class).equalTo("isFavorite", true).findAll();
    }

    @Override
    public void onDBWriteSuccess() {
        callback.onDBSuccess();
    }
}
