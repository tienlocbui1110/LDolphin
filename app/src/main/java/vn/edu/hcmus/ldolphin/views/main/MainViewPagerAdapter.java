package vn.edu.hcmus.ldolphin.views.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import vn.edu.hcmus.ldolphin.views.base.articleload.ArticleFragment;
import vn.edu.hcmus.ldolphin.views.main.setting.MenuFragment;
import vn.edu.hcmus.ldolphin.views.main.upload.UploadFragment;

class MainViewPagerAdapter extends FragmentPagerAdapter {
    MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ArticleFragment();
            case 1:
                return new UploadFragment();
            case 2:
                return new MenuFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
