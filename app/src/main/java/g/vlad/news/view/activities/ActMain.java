package g.vlad.news.view.activities;

import android.os.Bundle;

import g.vlad.news.R;
import g.vlad.news.databinding.ActMainBinding;

public class ActMain extends BaseBindingActivity<ActMainBinding> {

    @Override
    protected int initLayout() {
        return R.layout.act_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navigator.showFrgNewsContainer(false);
    }
}
