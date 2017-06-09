package g.vlad.news.di.component;


import dagger.Component;
import g.vlad.news.PerActivity;
import g.vlad.news.di.module.ActivityModule;
import g.vlad.news.di.module.RetrofitModule;
import g.vlad.news.view.activities.BaseInjectActivity;
import g.vlad.news.view.fragments.BaseInjectFragment;
import g.vlad.news.viewmodel.BaseInjectViewModel;

@PerActivity
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class, RetrofitModule.class})

public interface ActivityComponent {

    void inject(BaseInjectActivity activity);

    void inject(BaseInjectFragment fragment);

    void inject(BaseInjectViewModel viewModel);
}
