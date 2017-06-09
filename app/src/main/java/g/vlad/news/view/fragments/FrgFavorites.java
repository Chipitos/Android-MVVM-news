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
import g.vlad.news.databinding.FrgFavoritesBinding;
import g.vlad.news.events.DeleteFavoriteEvent;
import g.vlad.news.model.Article;
import g.vlad.news.model.repository.ArticlesRepository;
import g.vlad.news.utils.RealmUtils;
import g.vlad.news.view.adapters.NewsAdapter;
import g.vlad.news.view.handlers.Handlers;
import g.vlad.news.viewmodel.DummyModel;

public class FrgFavorites extends BaseBindingFragment<FrgFavoritesBinding, DummyModel> implements Handlers.FrgFavoritesHandlers, NewsAdapter.ItemClickListener {
    private ArticlesRepository repository = new ArticlesRepository();

    public static FrgFavorites newInstance() {
        return new FrgFavorites();
    }

    @Override
    public int initLayout() {
        return R.layout.frg_favorites;
    }

    @Override
    public DummyModel initViewModel() {
        return new DummyModel();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        binding.setHandlers(this);
        initRecyclerView();
    }

    private void initRecyclerView() {
        List<Article> articles = RealmUtils.getRealmInstance().copyFromRealm(repository.getAllFavorites());
        binding.favoritesRv.setLayoutManager(getLayoutManager());
        binding.favoritesRv.setAdapter(new NewsAdapter(articles, true, this));
        binding.setSize(articles.size());
    }

    @Override
    public void onBackPressed(View v) {
        navigator.finish();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(DeleteFavoriteEvent event) {
        binding.setSize(event.getSize() - 1);
    }

    @Override
    public void onItemClick(Intent intent) {
        startActivity(intent);
    }
}
