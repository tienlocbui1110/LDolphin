package vn.edu.hcmus.ldolphin;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Color;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;

import java.lang.reflect.Field;

import vn.edu.hcmus.ldolphin.ViewPagerFragment.MyPagerAdapter;

public class BottomNavigationViewHelper {
    private static ViewPager viewPager;
    private static Fragment currentFragment;
    @SuppressLint("RestrictedApi")
    public static void init(Activity activity, AHBottomNavigation bottomNavigation) {
        // Create items
        AHBottomNavigationItem item1 =
                new AHBottomNavigationItem("NEWS", R.drawable.ic_fiber_new_black_24dp);
        AHBottomNavigationItem item2 =
                new AHBottomNavigationItem("UPLOAD", R.drawable.ic_fiber_new_black_24dp);
        AHBottomNavigationItem item3 =
                new AHBottomNavigationItem("NEWS", R.drawable.ic_fiber_new_black_24dp);

        // Add items
        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);

        // Set background color
        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#FEFEFE"));

        // Change colors
        bottomNavigation.setAccentColor(Color.parseColor("#F63D2B"));
        bottomNavigation.setInactiveColor(Color.parseColor("#747474"));

        // Setting with viewpager
        viewPager = activity.findViewById(R.id.view_pager);
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                viewPager.setCurrentItem(position);
                return true;
            }
        });
    }
}

