package g.vlad.news.events;


import g.vlad.news.model.Article;

public class DeleteFavoriteEvent {
    private Article article;
    private int size;

    public DeleteFavoriteEvent(Article article, int size) {
        this.article = article;
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public Article getArticle() {
        return article;
    }
}
