package g.vlad.news.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;

import javax.inject.Inject;

import g.vlad.news.utils.ResUtils;
import g.vlad.news.view.activities.BaseInjectActivity;

public abstract class BaseInjectViewModel extends BaseObservable {
    @Inject
    ResUtils resUtils;

    Context context;

    BaseInjectViewModel(){};

    BaseInjectViewModel(BaseInjectActivity activity){
        activity.buildComponent().inject(this);
    }
}
