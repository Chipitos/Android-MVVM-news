package g.vlad.news.di.module;


import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import dagger.Module;
import dagger.Provides;
import g.vlad.news.server.RetrofitClient;
import retrofit2.Retrofit;

@Module
public class ActivityModule {
    private AppCompatActivity activity;

    public ActivityModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Provides
    Activity provideActivity() {
        return activity;
    }

    @Provides
    RetrofitClient.RetrofitBuilder provideRetrofitBuilder(Retrofit retrofit) {
        return new RetrofitClient.RetrofitBuilder(retrofit);
    }

    @Provides
    FragmentManager provideFragmentManager(){
        return activity.getSupportFragmentManager();
    }
}
