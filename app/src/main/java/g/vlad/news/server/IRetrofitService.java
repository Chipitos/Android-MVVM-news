package g.vlad.news.server;

import g.vlad.news.BuildConfig;
import g.vlad.news.server.response.NewsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface IRetrofitService {

    @GET(BuildConfig.API_URL)
    Call<NewsResponse> getNews(@Query("source") String source, @Query("sortBy") String sortBy);
}
