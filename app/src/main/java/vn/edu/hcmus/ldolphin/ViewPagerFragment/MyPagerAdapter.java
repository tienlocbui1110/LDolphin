package vn.edu.hcmus.ldolphin.ViewPagerFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

public class MyPagerAdapter extends FragmentPagerAdapter {

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment frag=null;
        switch (position){
            case 0:
                frag = new ArticleFragment();
                break;
            case 1:
                frag = new UploadFragment();
                break;
            case 2:
                frag = new MenuFragment();
                break;
        }
        return frag;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
