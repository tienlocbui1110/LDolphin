package vn.edu.hcmus.ldolphin.views.main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import vn.edu.hcmus.ldolphin.R;

public class MainActivity extends AppCompatActivity implements MainView {
    private static final String TAG = MainActivity.class.getName();

    private AHBottomNavigation bottomNavigation;
    private EditText edtSearch;
    private ImageButton ibSearch;
    private ViewPager viewPager;
    private MainPresenter presenter;

    // --- Activity Lifecycle ----------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // find view
        bottomNavigation = findViewById(R.id.bottom_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        edtSearch = findViewById(R.id.text_input_search);
        ibSearch = findViewById(R.id.image_button_search);
        viewPager = findViewById(R.id.view_pager);

        // Init action bar
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }

        // Init default presenter and listener
        presenter = new MainPresenterImpl(this);
        ibSearch.setOnClickListener(this);
        viewPager.addOnPageChangeListener(this);
        bottomNavigation.setOnTabSelectedListener(this);
    }

    // --- Listener --------------------------------------------------------------------------------

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == ibSearch.getId()) {
            Log.d(TAG, "Clicked Searching Button on toolbar");
            presenter.onToolbarSearchButtonClick(view);
        }
    }

    @Override
    public boolean onTabSelected(int position, boolean wasSelected) {
        return presenter.onBottomNavigationTabSelected(position, wasSelected);
    }

    @Override
    public void onPageSelected(int position) {
        presenter.onPageSelected(position);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        // TODO: Reseach what function mean
//        appBarLayout.setExpanded(true, true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        // DO NOTHING FOR NOW
    }


    // --- Update UI -------------------------------------------------------------------------------

    @Override
    public void setViewPagerAdapter(PagerAdapter adapter) {
        this.viewPager.setAdapter(adapter);
    }

    @Override
    public void addBottomNavigationItems(AHBottomNavigationItem... items) {
        for (AHBottomNavigationItem item : items) {
            bottomNavigation.addItem(item);
        }
    }

    @Override
    public void setBottomNavigationTheme(@ColorInt int defaultBackgroundColor,
                                         @ColorInt int accentColor,
                                         @ColorInt int inactiveColor,
                                         int inactiveTextSize,
                                         int activeTextSize) {
        bottomNavigation.setDefaultBackgroundColor(defaultBackgroundColor);
        bottomNavigation.setAccentColor(accentColor);
        bottomNavigation.setInactiveColor(inactiveColor);
        bottomNavigation.setTitleTextSize(activeTextSize, inactiveTextSize);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void setViewpagerPosition(int position) {
        viewPager.setCurrentItem(position);
    }

    @Override
    public void setBottomNavigationPosition(int position) {
        bottomNavigation.setCurrentItem(position);
    }

    @Override
    public String getSearchingText() {
        return edtSearch.getText().toString();
    }
}
