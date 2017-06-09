package g.vlad.news.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.view.View;

import g.vlad.news.R;
import g.vlad.news.databinding.FrgNewsContainerBinding;
import g.vlad.news.server.IRequestCallback;
import g.vlad.news.server.enums.ENewsSource;
import g.vlad.news.view.adapters.ViewPagerAdapter;
import g.vlad.news.view.handlers.Handlers;
import g.vlad.news.viewmodel.FrgNewsContainerViewModel;

public class FrgNewsContainer extends BaseBindingFragment<FrgNewsContainerBinding, FrgNewsContainerViewModel> implements IRequestCallback, TabLayout.OnTabSelectedListener, Handlers.FrgNewsContainerHandlers {


    public static FrgNewsContainer newInstance() {
        return new FrgNewsContainer();
    }

    @Override
    public int initLayout() {
        return R.layout.frg_news_container;
    }

    @Override
    public FrgNewsContainerViewModel initViewModel() {
        return new FrgNewsContainerViewModel();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.setHandlers(this);
        initViewPager();
    }


    private void initViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(FrgNews.newInstance(ENewsSource.BBC_NEWS.text), getString(R.string.bbc_news));
        adapter.addFragment(FrgNews.newInstance(ENewsSource.BLOOMBERG.text), getString(R.string.bloomberg_news));
        adapter.addFragment(FrgNews.newInstance(ENewsSource.CNN_NEWS.text), getString(R.string.cnn_news));
        binding.vpMain.setAdapter(adapter);
        binding.vpMain.setOffscreenPageLimit(3);
        binding.tabs.setupWithViewPager(binding.vpMain);
        binding.tabs.addOnTabSelectedListener(this);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewModel.setPosition(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onFavoritesClick(View v) {
        navigator.showFrgFavorites(true);
    }
}
