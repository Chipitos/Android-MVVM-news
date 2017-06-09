package g.vlad.news.server.enums;

public enum ENewsSource {
    BLOOMBERG("bloomberg"),
    BBC_NEWS("bbc-news"),
    CNN_NEWS("cnn");

    public String text;

    ENewsSource(String text) {
        this.text = text;
    }
}
