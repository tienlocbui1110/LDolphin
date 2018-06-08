package vn.edu.hcmus.ldolphin.ViewPagerFragment;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import vn.edu.hcmus.ldolphin.BottomNavigationViewHelper;
import vn.edu.hcmus.ldolphin.R;
import vn.edu.hcmus.ldolphin.data.Article;
import vn.edu.hcmus.ldolphin.data.ArticleAdapter;
import vn.edu.hcmus.ldolphin.data.Utils;

public class ArticleFragment extends Fragment {
    private List<Article> mArticles;
    private RecyclerView recyclerView;
    private ArticleAdapter mAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_article, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Do something
        // Find Id
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_articles);
        // Prepare Data
        // Will remove later
        mArticles = new ArrayList<>();
        Utils.prepareData(mArticles);

        // Set recycle Adapter
        mAdapter = new ArticleAdapter(view.getContext(),mArticles);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(mAdapter);


    }


}
