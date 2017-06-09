package g.vlad.news.view.adapters;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.view.View;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import g.vlad.news.R;
import g.vlad.news.databinding.ListNewsBinding;
import g.vlad.news.events.DeleteFavoriteEvent;
import g.vlad.news.model.Article;
import g.vlad.news.model.repository.ArticlesRepository;
import g.vlad.news.model.repository.Repository;
import g.vlad.news.view.handlers.Handlers;
import g.vlad.news.viewmodel.ListNewsViewModel;


public class NewsAdapter extends BaseRecyclerAdapter<ListNewsBinding, ListNewsViewModel, NewsAdapter.NewsViewHolder, Article> implements Handlers.ListNewsHandlers {
    private boolean isFromFavorites;
    private ItemClickListener listener;
    private Repository<Article> repository = new ArticlesRepository();

    public NewsAdapter(List<Article> item, boolean isFromFavorites, ItemClickListener listener) {
        super(item);
        this.isFromFavorites = isFromFavorites;
        this.listener = listener;
    }


    @Override
    public int initLayout() {
        return R.layout.list_news;
    }

    @Override
    public ListNewsViewModel initViewModel() {
        return new ListNewsViewModel();
    }

    @Override
    public NewsViewHolder initHolder() {
        return new NewsViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        holder.getBinding().getVm().init(getItem(position));
        holder.getBinding().ivFavorite.setTag(position);
        holder.getBinding().tvReadMore.setTag(position);
        holder.getBinding().setHandlers(this);
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    @Override
    public void onFavoriteClick(View v) {
        int position = (int) v.getTag();
        getItem(position).setFavorite(!getItem(position).isFavorite());
        repository.add(getItem(position));
        if (isFromFavorites) {
            EventBus.getDefault().post(new DeleteFavoriteEvent(getItem(position), getItemList().size()));
            item.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeRemoved(position, getItemList().size());
        } else
            notifyItemChanged(position);
    }

    @Override
    public void onItemClick(View v) {
        int position = (int) v.getTag();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(item.get(position).getUrl()));
        listener.onItemClick(intent);

    }

    class NewsViewHolder extends BaseRecyclerAdapter.BaseViewHolder {
        private ListNewsBinding binding;

        NewsViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        ListNewsBinding getBinding() {
            return binding;
        }
    }

    public interface ItemClickListener {
        void onItemClick(Intent intent);
    }
}
