package vn.edu.hcmus.ldolphin.ViewPagerFragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

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
    private TextView cantLoad;
    private ProgressBar pbLoading;
    private  PhotoView mBackground;

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

    private void settingFragment(@NonNull View view) {

        // Find Id
        findLayoutId(view);

        // setup setting
        setupSetting();

        mBackground.setZoomable(false);

        cantLoad.setVisibility(View.GONE);
        pbLoading.setVisibility(View.VISIBLE);

        prettyLoadingUi(view);
    }

    private void findLayoutId(@NonNull View view) {
        mBackground = view.findViewById(R.id.pv_background);
        recyclerView = view.findViewById(R.id.recycler_articles);
        cantLoad = view.findViewById(R.id.tv_cant_load_data);
        pbLoading = view.findViewById(R.id.pb_waiting_for_loading);
    }

    private void prettyLoadingUi(final View view) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                    // Prepare Data
                    mArticles = new ArrayList<>();
                    Utils.prepareData(mArticles);
                    mAdapter = new ArticleAdapter(getActivity(), mArticles);

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // Setting RecyclerView
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(view.getContext());
                            recyclerView.setLayoutManager(mLayoutManager);
                            recyclerView.setItemAnimator(new DefaultItemAnimator());
                            recyclerView.setNestedScrollingEnabled(true);
                            recyclerView.setAdapter(mAdapter);
                            pbLoading.setVisibility(View.GONE);
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }

    private void setupSetting() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        // get setting value
        boolean isModern = preferences.getBoolean(getString(R.string.key_is_modern_layout), false);

        // custom layout
        if (isModern) {
            Glide.with(this).load(R.drawable.modern_1).into(mBackground);
        } else {
            Glide.with(this).load(R.drawable.default_backgrround_1).into(mBackground);
        }
    }
}
