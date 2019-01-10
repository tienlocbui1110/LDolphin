package vn.edu.hcmus.ldolphin.views.main;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import vn.edu.hcmus.ldolphin.R;
import vn.edu.hcmus.ldolphin.data.ThemeConfig;
import vn.edu.hcmus.ldolphin.utils.StringUtils;
import vn.edu.hcmus.ldolphin.views.searching.SearchingActivity;

class MainPresenterImpl implements MainPresenter {
    private MainView mView;
    private Context mContext;

    MainPresenterImpl(MainView view) {
        setView(view);
        init();
    }

    @Override
    public void setView(MainView view) {
        mView = view;
        mContext = mView.getContext();
    }

    private void init() {
        FragmentManager manager = mView.getSupportFragmentManager();
        PagerAdapter adapter = new MainViewPagerAdapter(manager);
        mView.setViewPagerAdapter(adapter);

        // Init item in bottom navigation
        AHBottomNavigationItem newsItem =
                new AHBottomNavigationItem("NEWS", R.drawable.ic_fiber_new_black_24dp);
        AHBottomNavigationItem uploadItem =
                new AHBottomNavigationItem("UPLOAD", R.drawable.ic_file_upload_black_24dp);
        AHBottomNavigationItem menuItem =
                new AHBottomNavigationItem("MENU", R.drawable.ic_menu_black_24dp);
        mView.addBottomNavigationItems(newsItem, uploadItem, menuItem);

        // ThemeConfig bottom navigation theme
        int defaultBackground = ThemeConfig.BOTTOM_BACKGROUND_COLOR();
        int accentColor = ThemeConfig.BOTTOM_ACCENT_COLOR();
        int inactiveColor = ThemeConfig.BOTTOM_INACTIVE_COLOR();
        int activeTextSize = ThemeConfig.BOTTOM_ACTIVE_SIZE();
        int inactiveTextSize = ThemeConfig.BOTTOM_INACTIVE_SIZE();
        mView.setBottomNavigationTheme(defaultBackground, accentColor, inactiveColor, inactiveTextSize, activeTextSize);
    }

    @Override
    public void onPageSelected(int position) {
        mView.setBottomNavigationPosition(position);
    }

    @Override
    public boolean onBottomNavigationTabSelected(int position, boolean wasSelected) {
        if (wasSelected)
            return false;
        mView.setViewpagerPosition(position);
        return true;
    }

    @Override
    public void onToolbarSearchButtonClick(View view) {
        String searchText = mView.getSearchingText();
        if (StringUtils.isEmpty(searchText)) {
            Toast.makeText(mContext, "Search field is null.", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(mContext, SearchingActivity.class);
        intent.putExtra("key", searchText);
        mContext.startActivity(intent);
    }
}
