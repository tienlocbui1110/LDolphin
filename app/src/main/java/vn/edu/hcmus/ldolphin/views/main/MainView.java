package vn.edu.hcmus.ldolphin.views.main;

import android.support.annotation.ColorInt;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import vn.edu.hcmus.ldolphin.views.base.MVPView;

interface MainView extends MVPView, View.OnClickListener, ViewPager.OnPageChangeListener, AHBottomNavigation.OnTabSelectedListener {
    void setViewPagerAdapter(PagerAdapter adapter);

    FragmentManager getSupportFragmentManager();

    void addBottomNavigationItems(AHBottomNavigationItem... items);

    void setBottomNavigationTheme(@ColorInt int defaultBackgroundColor,
                                  @ColorInt int accentColor,
                                  @ColorInt int inactiveColor,
                                  int inactiveTextSize,
                                  int activeTextSize);

    void setViewpagerPosition(int position);

    void setBottomNavigationPosition(int position);

    String getSearchingText();
}
