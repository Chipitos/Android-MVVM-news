package g.vlad.news.viewmodel;

import android.databinding.BaseObservable;

import javax.inject.Inject;

import g.vlad.news.App;
import g.vlad.news.utils.ResUtils;

public abstract class BaseInjectViewModel extends BaseObservable {
    @Inject
    ResUtils resUtils;

    BaseInjectViewModel() {
        App.getComponent().inject(this);
    }
}
