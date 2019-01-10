package vn.edu.hcmus.ldolphin.views.main.setting;

import android.content.Context;
import android.content.Intent;

import vn.edu.hcmus.ldolphin.R;
import vn.edu.hcmus.ldolphin.views.profile.ProfileActivity;
import vn.edu.hcmus.ldolphin.views.setting.SettingActivity;

class MenuPresenterImpl implements MenuPresenter {
    private MenuView mView;
    private Context mContext;

    public MenuPresenterImpl(MenuView view) {
        setView(view);
    }

    @Override
    public void setView(MenuView view) {
        mView = view;
        mContext = view.getContext();
        init();
    }

    private void init() {
        //TODO: Layout setting
        mView.setImageBackground(R.drawable.default_backgrround_3);
    }

    @Override
    public void goToProfile() {
        Intent intent = new Intent(mContext, ProfileActivity.class);
        mContext.startActivity(intent);
    }

    @Override
    public void goToSetting() {
        Intent intent = new Intent(mContext, SettingActivity.class);
        mContext.startActivity(intent);
    }
}
