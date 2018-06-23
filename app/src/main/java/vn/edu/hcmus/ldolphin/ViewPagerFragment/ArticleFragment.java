package vn.edu.hcmus.ldolphin.ViewPagerFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.bumptech.glide.Glide;
import com.gabrielsamojlo.keyboarddismisser.KeyboardDismisser;

import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoView;
import vn.edu.hcmus.ldolphin.R;
import vn.edu.hcmus.ldolphin.data.Article;
import vn.edu.hcmus.ldolphin.data.ArticleAdapter;
import vn.edu.hcmus.ldolphin.data.Utils;

public class ArticleFragment extends Fragment {
    private List<Article> mArticles;
    private RecyclerView recyclerView;
    private ArticleAdapter mAdapter;
    PhotoView mBackground;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_article, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Setting Layout
        settingFragment(view);
    }

    public void settingFragment(@NonNull View view) {

        // Find Id
        findLayoutId(view);

        // Prepare Data
        mArticles = new ArrayList<>();
        Utils.prepareData(mArticles);
        mAdapter = new ArticleAdapter(view.getContext(), mArticles);

        // Load background image
        Glide.with(view.getContext()).load(R.drawable.default_backgrround_1).into(mBackground);
        mBackground.setZoomable(false);

        // Setting RecyclerView
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setAdapter(mAdapter);
    }

    public void findLayoutId(@NonNull View view) {
        mBackground = view.findViewById(R.id.pv_background);
        recyclerView = view.findViewById(R.id.recycler_articles);
    }

}
