package g.vlad.news.server;

import javax.inject.Inject;
import javax.inject.Singleton;

import g.vlad.news.server.response.NewsResponse;
import retrofit2.Call;
import retrofit2.Retrofit;


public class RetrofitClient {
    private IRequestCallback callback;
    private IRetrofitService service;


    private RetrofitClient(IRequestCallback callback, IRetrofitService service) {
        this.callback = callback;
        this.service = service;
        callback.showProgress();
    }


    public void getNews(String source, String sortBy) {
        Call<NewsResponse> call = service.getNews(source, sortBy);
        call.enqueue(new CommonCallback<NewsResponse>(callback));
    }

    @Singleton
    public static class RetrofitBuilder {
        private IRequestCallback callback;
        private Retrofit retrofit;

        @Inject
        public RetrofitBuilder(Retrofit retrofit) {
            this.retrofit = retrofit;
        }

        public RetrofitBuilder setCallback(IRequestCallback callback) {
            this.callback = callback;
            return this;
        }

        public RetrofitClient build() {
            IRetrofitService service = retrofit.create(IRetrofitService.class);
            return new RetrofitClient(callback, service);
        }
    }

}
