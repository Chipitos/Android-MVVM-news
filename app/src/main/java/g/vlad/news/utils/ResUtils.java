package g.vlad.news.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ResUtils {
    private Context context;

    @Inject
    ResUtils(Context context) {
        this.context = context;
    }

    public String getString(int id) {
        return context.getResources().getString(id);
    }

    public Drawable getDrawable(int id) {
        return ResourcesCompat.getDrawable(context.getResources(), id, null);
    }

}
