package g.vlad.news;

import android.app.Application;

import g.vlad.news.di.component.AppComponent;
import g.vlad.news.di.component.DaggerAppComponent;
import g.vlad.news.di.module.AppModule;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class App extends Application {
    private static AppComponent component;
    public static AppComponent getComponent() {
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(getApplicationContext());
        RealmConfiguration config = new RealmConfiguration.Builder().schemaVersion(0).build();
        Realm.setDefaultConfiguration(config);
        component = buildComponent();
        component.inject(this);
    }

    private AppComponent buildComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

}
