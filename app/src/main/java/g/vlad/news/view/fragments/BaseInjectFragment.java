package g.vlad.news.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import javax.inject.Inject;

import g.vlad.news.server.RetrofitClient;
import g.vlad.news.view.activities.BaseInjectActivity;
import g.vlad.news.view.navigator.Navigator;

public abstract class BaseInjectFragment extends Fragment {
    @Inject
    RetrofitClient.RetrofitBuilder builder;

    @Inject
    Navigator navigator;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BaseInjectActivity activity = (BaseInjectActivity) getActivity();
        activity.buildComponent().inject(this);
        setRetainInstance(true);
    }
}
