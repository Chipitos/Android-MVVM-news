package g.vlad.news.viewmodel;

import g.vlad.news.view.activities.BaseInjectActivity;

public abstract class BaseViewModel<T> extends BaseInjectViewModel {
    T item;

    BaseViewModel(){};

    BaseViewModel(BaseInjectActivity activity) {
        super(activity);
    }

    protected void init(T item) {

    }
}
