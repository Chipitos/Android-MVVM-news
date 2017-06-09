package g.vlad.news.view.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import g.vlad.news.App;
import g.vlad.news.di.component.ActivityComponent;
import g.vlad.news.di.component.DaggerActivityComponent;
import g.vlad.news.di.module.ActivityModule;
import g.vlad.news.server.RetrofitClient;
import g.vlad.news.view.navigator.Navigator;


public abstract class BaseInjectActivity extends AppCompatActivity {
    @Inject
    RetrofitClient.RetrofitBuilder builder;
    @Inject
    Navigator navigator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buildComponent().inject(this);
    }

    public ActivityComponent buildComponent() {
        return DaggerActivityComponent.builder()
                .appComponent(App.getComponent())
                .activityModule(new ActivityModule(this))
                .build();
    }
}


