package vn.edu.hcmus.ldolphin.views.main;

import android.view.View;

import vn.edu.hcmus.ldolphin.views.base.Presenter;

interface MainPresenter extends Presenter<MainView> {
    void onPageSelected(int position);

    boolean onBottomNavigationTabSelected(int position, boolean wasSelected);

    void onToolbarSearchButtonClick(View view);
}
