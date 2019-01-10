package vn.edu.hcmus.ldolphin.views.main.setting;

import vn.edu.hcmus.ldolphin.views.base.Presenter;

interface MenuPresenter extends Presenter<MenuView> {
    void goToProfile();

    void goToSetting();
}
