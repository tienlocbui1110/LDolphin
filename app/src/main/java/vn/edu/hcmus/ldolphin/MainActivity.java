package vn.edu.hcmus.ldolphin;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;

import java.util.Objects;

import vn.edu.hcmus.ldolphin.ViewPagerFragment.MyPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private AHBottomNavigation bottomNavigation;
    private AppBarLayout appBarLayout;
    private EditText edtSearch;
    private View root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // find view
        appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        bottomNavigation = findViewById(R.id.bottom_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        edtSearch = findViewById(R.id.text_input_search);
        root = findViewById(R.id.main_activity_root);

        edtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                // Identifier of the action. This will be either the identifier you supplied,
                // or EditorInfo.IME_NULL if being called due to the enter key being pressed.
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    edtSearch.clearFocus();
                    return true;
                }
                // Return true if you have consumed the action, else false.
                return false;
            }
        });

        edtSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null)
                    imm.hideSoftInputFromWindow(root.getWindowToken(), 0);
            }
        });

        // setting toolbar
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

        // Bottom Navigation
        addControl();
        BottomNavigationViewHelper.init(this, bottomNavigation);

    }

    private void addControl() {
        ViewPager pager = findViewById(R.id.view_pager);
        FragmentManager manager = getSupportFragmentManager();
        MyPagerAdapter adapter = new MyPagerAdapter(manager);
        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                appBarLayout.setExpanded(true, true);
            }

            @Override
            public void onPageSelected(int position) {
                bottomNavigation.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
}
