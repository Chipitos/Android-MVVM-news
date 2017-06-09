package g.vlad.news.viewmodel;

import android.databinding.Bindable;
import android.graphics.drawable.Drawable;

import g.vlad.news.R;


public class FrgNewsContainerViewModel extends BaseViewModel {

    private int position;

    public void setPosition(int position) {
        this.position = position;
        notifyChange();
    }

    @Bindable
    public String getHeaderText() {
        switch (position) {
            case 0: {
                return resUtils.getString(R.string.bbc_news);
            }
            case 1: {
                return resUtils.getString(R.string.bloomberg_news);
            }
            case 2: {
                return resUtils.getString(R.string.cnn_news);
            }
            default:
                return "";
        }
    }

    @Bindable
    public Drawable getHeaderDrawable() {
        switch (position) {
            case 0: {
                return resUtils.getDrawable(R.drawable.bbc_news);
            }
            case 1: {
                return resUtils.getDrawable(R.drawable.bloomberg);
            }
            case 2: {
                return resUtils.getDrawable(R.drawable.cnn);
            }
            default:
                return null;
        }
    }
}
