package g.vlad.news.server.response;


import com.google.gson.annotations.SerializedName;

import java.util.List;

import g.vlad.news.model.Article;


public class NewsResponse extends BaseResponse {

    @SerializedName("articles")
    private List<Article> response;

    @SerializedName("source")
    private String source;

    public String getSource() {
        return source;
    }

    public List<Article> getResponse() {
        return response;
    }
}
