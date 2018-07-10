package vn.edu.hcmus.ldolphin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoView;
import vn.edu.hcmus.ldolphin.data.Article;
import vn.edu.hcmus.ldolphin.data.SearchNameAdapter;
import vn.edu.hcmus.ldolphin.data.SearchTitleAdapter;
import vn.edu.hcmus.ldolphin.data.Utils;

public class SearchingActivity extends AppCompatActivity {
    private List<Article> mArticles;
    private RecyclerView recyclerViewName, recyclerViewTitle;
    private SearchNameAdapter mNameAdapter;
    private SearchTitleAdapter mTitleAdapter;
    private PhotoView pvBackground;
    private Toolbar toolbar;
    private ImageButton home;
    private TextView cantLoad;
    private TextView tvSearchByName, tvSearchByTitle;
    private String searchData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searching);

        Intent intent = getIntent();
        searchData = intent.getStringExtra("key");

        findView();

        // setting toolbar
        setSupportActionBar(toolbar);
        cantLoad.setVisibility(View.GONE);


        // setupSetting
        setupSetting();
        setListener();
        settingNameRecyclerView();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void findView() {
        // Find view
        toolbar = findViewById(R.id.toolbar);
        recyclerViewName = findViewById(R.id.recycler_searching_name);
        recyclerViewTitle = findViewById(R.id.recycler_searching_title);

        cantLoad = findViewById(R.id.tv_cant_load_data);
        pvBackground = findViewById(R.id.pvBackground);
        home = findViewById(R.id.image_button_home);
        tvSearchByName = findViewById(R.id.tv_search_by_name);
        tvSearchByTitle = findViewById(R.id.tv_search_by_title);
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

        // setting recycler view
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getApplicationContext());
        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(getApplicationContext());
        recyclerViewName.setLayoutManager(mLayoutManager1);
        recyclerViewName.setItemAnimator(new DefaultItemAnimator());
        recyclerViewName.setNestedScrollingEnabled(true);
        recyclerViewTitle.setLayoutManager(mLayoutManager2);
        recyclerViewTitle.setItemAnimator(new DefaultItemAnimator());
        recyclerViewTitle.setNestedScrollingEnabled(true);
    }

    private void setListener() {

        tvSearchByName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settingNameRecyclerView();
            }
        });

        tvSearchByTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settingTitleRecyclerView();
            }
        });

    }

    private void settingNameRecyclerView() {
        recyclerViewTitle.setVisibility(View.GONE);
        recyclerViewName.setVisibility(View.VISIBLE);
        if (mNameAdapter != null)
            mNameAdapter.clear();
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
                            mNameAdapter = new SearchNameAdapter(SearchingActivity.this, mArticles);
                            recyclerViewName.setAdapter(mNameAdapter);
                        }
                    });
                } catch (InterruptedException e) {
                    e.getStackTrace();
                }
            }
        });

        thread.start();
    }

    private void settingTitleRecyclerView() {
        recyclerViewName.setVisibility(View.GONE);
        recyclerViewTitle.setVisibility(View.VISIBLE);
        if (mTitleAdapter != null)
            mTitleAdapter.clear();
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
                            mTitleAdapter = new SearchTitleAdapter(SearchingActivity.this, mArticles);
                            recyclerViewTitle.setAdapter(mTitleAdapter);
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
