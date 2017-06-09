package g.vlad.news.server.enums;

public enum ESortBy {
    TOP("top"),
    LATEST("latest"),
    POPULAR("popular");

    public String text;

    ESortBy(String text) {
        this.text = text;
    }
}
