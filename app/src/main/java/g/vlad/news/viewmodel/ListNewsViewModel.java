package g.vlad.news.viewmodel;

import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import g.vlad.news.App;
import g.vlad.news.model.Article;
import g.vlad.news.utils.Utils;
import g.vlad.news.view.activities.BaseInjectActivity;

public class ListNewsViewModel extends BaseViewModel<Article> {

    public ListNewsViewModel(BaseInjectActivity activity) {
        super(activity);
    }

    @Override
    public void init(Article item) {
        this.item = item;
        notifyChange();
    }

    @Bindable
    public String getTitleText() {
        return item.getTitle();
    }

    @Bindable
    public String getAuthorText() {
        return item.getAuthor();
    }

    @Bindable
    public String getDescriptionText() {
        return item.getDescription();
    }

    @Bindable
    public String getImageUrl() {
        return item.getUrlToImage();
    }

    @Bindable
    public String getPublishedAt() {
        return Utils.getTimeFormatText(item.getPublishedAt());
    }

    @Bindable
    public boolean isFavorite() {
        return item.isFavorite();
    }

    @BindingAdapter({"newsUrl"})
    public static void bindNewsUrl(ImageView view, String url) {
        if (url != null && !url.isEmpty())
            Picasso.with(App.getComponent().getContext()).load(url).into(view);
    }
}
