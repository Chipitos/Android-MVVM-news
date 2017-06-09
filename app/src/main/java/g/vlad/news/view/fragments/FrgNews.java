package g.vlad.news.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import g.vlad.news.R;
import g.vlad.news.databinding.FrgNewsBinding;
import g.vlad.news.events.DeleteFavoriteEvent;
import g.vlad.news.model.Article;
import g.vlad.news.model.repository.ArticlesRepository;
import g.vlad.news.model.repository.Repository;
import g.vlad.news.server.RetrofitClient;
import g.vlad.news.server.enums.ESortBy;
import g.vlad.news.server.response.BaseResponse;
import g.vlad.news.server.response.NewsResponse;
import g.vlad.news.utils.RealmUtils;
import g.vlad.news.view.adapters.NewsAdapter;
import g.vlad.news.viewmodel.DummyModel;
import okhttp3.ResponseBody;

public class FrgNews extends BaseBindingFragment<FrgNewsBinding, DummyModel> implements Repository.RepositoryCallback, NewsAdapter.ItemClickListener {
    private ArticlesRepository repository = new ArticlesRepository(this);
    private static final String SOURCE_KEY = "sourceKey";
    private NewsAdapter adapter;

    public static FrgNews newInstance(String source) {
        FrgNews frg = new FrgNews();
        Bundle args = new Bundle();
        args.putString(SOURCE_KEY, source);
        frg.setArguments(args);
        return frg;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RetrofitClient client = builder.
                setCallback(this).
                build();
        client.getNews(getArguments().getString(SOURCE_KEY), ESortBy.TOP.text);
    }

    @Override
    public int initLayout() {
        return R.layout.frg_news;
    }

    @Override
    public DummyModel initViewModel() {
        return new DummyModel();
    }

    @Override
    public void onSuccessResult(BaseResponse response) {
        if (response instanceof NewsResponse) {
            NewsResponse newsResponse = (NewsResponse) response;
            List<Article> articles = newsResponse.getResponse();
            for (Article article : articles) {
                article.setId(article.getUrl().hashCode());
                article.setSource(newsResponse.getSource());
            }
            repository.add(articles);
        }
    }

    @Override
    public void onErrorResult(ResponseBody error) {
        initRecyclerView();
    }

    @Override
    public void onDBSuccess() {
        initRecyclerView();
    }

    private void initRecyclerView() {
        List<Article> articles = RealmUtils.getRealmInstance().copyFromRealm(repository.getBySources(getArguments().getString(SOURCE_KEY)));
        adapter = new NewsAdapter(articles, false, this, getBaseActivity());
        binding.newsRv.setLayoutManager(getLayoutManager());
        binding.newsRv.setAdapter(adapter);
    }

    @Override
    public void onItemClick(Intent intent) {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(DeleteFavoriteEvent event) {
        if (adapter.getItemList().indexOf(event.getArticle()) == -1)
            return;
        adapter.getItemList().get(adapter.getItemList().indexOf(event.getArticle())).setFavorite(false);
        adapter.notifyDataSetChanged();
        repository.add(event.getArticle());
    }
}
