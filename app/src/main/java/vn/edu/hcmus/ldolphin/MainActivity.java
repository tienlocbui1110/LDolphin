package vn.edu.hcmus.ldolphin;

import android.annotation.SuppressLint;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenu;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import vn.edu.hcmus.ldolphin.data.Article;
import vn.edu.hcmus.ldolphin.data.ArticleAdapter;
import vn.edu.hcmus.ldolphin.data.Utils;

public class MainActivity extends AppCompatActivity {
    private List<Article> mArticles;
    private RecyclerView recyclerView;
    private ArticleAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_view);
        BottomNavigationViewHelper.removeShiftMode(bottomNavigationView);

        // Prepare Data
        // Will remove later
        mArticles = new ArrayList<>();
        Utils.prepareData(mArticles);

        // Set recycle Adapter
        recyclerView = (RecyclerView) findViewById(R.id.recycler_articles);
        mAdapter = new ArticleAdapter(this,mArticles);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(mAdapter);

    }

}
