package g.vlad.news.view.adapters;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import g.vlad.news.BR;
import g.vlad.news.viewmodel.BaseViewModel;
import io.realm.RealmObject;

abstract class BaseRecyclerAdapter<B extends ViewDataBinding, V extends BaseViewModel, H extends BaseRecyclerAdapter.BaseViewHolder, T extends RealmObject> extends RecyclerView.Adapter<H> {
    B binding;
    List<T> item;

    protected abstract
    @LayoutRes
    int initLayout();

    public abstract V initViewModel();

    public abstract H initHolder();

    public List<T> getItemList() {
        return item;
    }

    public T getItem(int position) {
        return item.get(position);
    }

    BaseRecyclerAdapter(List<T> item) {
        this.item = item;
    }

    @Override
    public H onCreateViewHolder(ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), initLayout(), parent, false);
        V viewModel = initViewModel();
        H holder = initHolder();
        binding.setVariable(BR.vm, viewModel);
        return holder;
    }


    class BaseViewHolder extends RecyclerView.ViewHolder {

        BaseViewHolder(View itemView) {
            super(itemView);
            DataBindingUtil.bind(itemView);
        }
    }

}