package vn.edu.hcmus.ldolphin;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoView;
import vn.edu.hcmus.ldolphin.ViewPagerFragment.MyPagerAdapter;
import vn.edu.hcmus.ldolphin.data.Article;
import vn.edu.hcmus.ldolphin.data.ArticleAdapter;
import vn.edu.hcmus.ldolphin.data.ProfileAdapter;
import vn.edu.hcmus.ldolphin.data.Utils;

public class ProfileActivity extends AppCompatActivity {
    private List<Article> mArticles;
    private RecyclerView recyclerView;
    private ProfileAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Find view
        Toolbar toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recycler_profile);
        // setting toolbar
        setSupportActionBar(toolbar);

        settingRecyclerView();

    }

    private void settingRecyclerView() {
        mArticles = new ArrayList<>();
        Utils.prepareData(mArticles);
        mAdapter = new ProfileAdapter(getApplicationContext(), mArticles);

        // Setting RecyclerView
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setAdapter(mAdapter);
    }
}
