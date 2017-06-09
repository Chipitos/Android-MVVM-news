package g.vlad.news.view.activities;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import g.vlad.news.server.IRequestCallback;
import g.vlad.news.server.response.BaseResponse;
import okhttp3.ResponseBody;


public abstract class BaseBindingActivity<B extends ViewDataBinding> extends BaseInjectActivity implements IRequestCallback {
    B binding;

    protected abstract
    @LayoutRes
    int initLayout();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (binding == null) {
            if (initLayout() == 0) {
                throw new IllegalStateException("Layout must not be null and should be implemented via initLayout");
            }
            binding = DataBindingUtil.setContentView(this, initLayout());
        }
    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void onSuccessResult(BaseResponse response) {

    }

    @Override
    public void onErrorResult(ResponseBody error) {

    }
}


