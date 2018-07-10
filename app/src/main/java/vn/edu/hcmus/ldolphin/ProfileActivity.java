package vn.edu.hcmus.ldolphin;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoView;
import vn.edu.hcmus.ldolphin.data.Article;
import vn.edu.hcmus.ldolphin.data.ProfileAdapter;
import vn.edu.hcmus.ldolphin.data.Utils;

public class ProfileActivity extends AppCompatActivity {
    private List<Article> mArticles;
    private RecyclerView recyclerView;
    private ProfileAdapter mAdapter;
    private PhotoView pvBackground;

    private TextView cantLoad;
    private ProgressBar pbLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Find view
        Toolbar toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recycler_profile);
        cantLoad = findViewById(R.id.tv_cant_load_data);
        pbLoading = findViewById(R.id.pb_waiting_for_loading);
        pvBackground = findViewById(R.id.iv_background);

        // setting toolbar
        setSupportActionBar(toolbar);
        cantLoad.setVisibility(View.GONE);
        pbLoading.setVisibility(View.VISIBLE);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        // setupSetting
        setupSetting();
        settingRecyclerView();

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void setupSetting() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        // get setting value
        boolean isModern = preferences.getBoolean(getString(R.string.key_is_modern_layout), false);

        // custom layout
        if (isModern) {
            Glide.with(this).load(R.drawable.modern_2).into(pvBackground);
        } else {
            Glide.with(this).load(R.drawable.default_backgrround_2).into(pvBackground);
        }

        pvBackground.setZoomable(false);
    }

    private void settingRecyclerView() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mArticles = new ArrayList<>();
                            Utils.prepareData(mArticles);
                            mAdapter = new ProfileAdapter(ProfileActivity.this, mArticles);

                            // Setting RecyclerView
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                            recyclerView.setLayoutManager(mLayoutManager);
                            recyclerView.setItemAnimator(new DefaultItemAnimator());
                            recyclerView.setNestedScrollingEnabled(true);
                            recyclerView.setAdapter(mAdapter);

                            pbLoading.setVisibility(View.GONE);
                        }
                    });
                } catch (InterruptedException e) {
                    e.getStackTrace();
                }
            }
        });

        thread.start();
    }
}
