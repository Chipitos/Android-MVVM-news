package g.vlad.news.view.navigator;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import javax.inject.Inject;

import g.vlad.news.R;
import g.vlad.news.view.fragments.FrgFavorites;
import g.vlad.news.view.fragments.FrgNewsContainer;


public class Navigator {
    private FragmentManager fragmentManager;

    @Inject
    Navigator(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public void showFrgNewsContainer(boolean addToBackStack) {
        showFragment(FrgNewsContainer.newInstance(), addToBackStack);
    }

    public void showFrgFavorites(boolean addToBackStack) {
        showFragmentWithoutReplace(FrgFavorites.newInstance(), addToBackStack);
    }

    private void showFragment(Fragment frg, boolean addToBackStack) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.fragment_container, frg, frg.getClass().getSimpleName());
        if (addToBackStack)
            ft.addToBackStack(frg.getClass().getSimpleName());
        ft.commitAllowingStateLoss();
    }

    private void showFragmentWithoutReplace(Fragment frg, boolean addToBackStack) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(R.id.fragment_container, frg, frg.getClass().getSimpleName());
        if (addToBackStack)
            ft.addToBackStack(frg.getClass().getSimpleName());
        ft.commitAllowingStateLoss();
    }

    public void finish() {
        fragmentManager.popBackStack();
    }

}
