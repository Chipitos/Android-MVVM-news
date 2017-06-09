package g.vlad.news.view.fragments;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import g.vlad.news.App;
import g.vlad.news.BR;
import g.vlad.news.server.IRequestCallback;
import g.vlad.news.server.response.BaseResponse;
import g.vlad.news.view.activities.BaseInjectActivity;
import g.vlad.news.view.dialogs.DlgProgress;
import g.vlad.news.viewmodel.BaseViewModel;
import okhttp3.ResponseBody;

public abstract class BaseBindingFragment<B extends ViewDataBinding, V extends BaseViewModel> extends BaseInjectFragment implements IRequestCallback {
    private DlgProgress dlgProgress = DlgProgress.newInstance();
    protected B binding;
    protected V viewModel;

    public abstract
    @LayoutRes
    int initLayout();

    public abstract V initViewModel();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (binding == null) {
            int layout = initLayout();
            viewModel = initViewModel();
            if (viewModel == null) {
                throw new IllegalStateException("viewModel must not be null and should be injected via getViewModel");
            }
            if (layout == 0) {
                throw new IllegalStateException("layout must not be null and should be injected via getLayout");
            }
            binding = DataBindingUtil.inflate(inflater, layout, container, false);
            binding.setVariable(BR.vm, viewModel);
        }
        return binding.getRoot();
    }

    protected BaseInjectActivity getBaseActivity() {
        return (BaseInjectActivity) getActivity();
    }

    protected LinearLayoutManager getLayoutManager() {
        return new LinearLayoutManager(App.getComponent().getContext());
    }

    @Override
    public void onSuccessResult(BaseResponse response) {

    }

    @Override
    public void onErrorResult(ResponseBody error) {

    }

    @Override
    public void showProgress() {
        if (!dlgProgress.isAdded())
            dlgProgress.show(getChildFragmentManager(), "Progress");
    }

    @Override
    public void hideProgress() {
        if (dlgProgress.isVisible()) {
            dlgProgress.dismissAllowingStateLoss();
            dlgProgress = DlgProgress.newInstance();
        }
    }
}
