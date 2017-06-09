package g.vlad.news.di.component;


import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import g.vlad.news.di.module.AppModule;
import g.vlad.news.utils.ResUtils;

@Singleton
@Component(modules = AppModule.class)

public interface AppComponent {
    void inject(Application app);

    Context getContext();

    ResUtils getResUtils();
}
