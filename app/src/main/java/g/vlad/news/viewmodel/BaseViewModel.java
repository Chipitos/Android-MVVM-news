package g.vlad.news.viewmodel;


public abstract class BaseViewModel<T> extends BaseInjectViewModel {
    T item;

    protected void init(T item) {}
}
