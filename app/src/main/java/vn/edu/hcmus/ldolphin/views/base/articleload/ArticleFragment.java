package vn.edu.hcmus.ldolphin.views.base.articleload;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import uk.co.senab.photoview.PhotoView;
import vn.edu.hcmus.ldolphin.R;

public class ArticleFragment extends Fragment implements ArticleView {
    private RecyclerView recyclerView;
    private TextView tvWhenNotLoading;
    private ProgressBar pbLoading;
    private PhotoView mBackground;
    private ArticlePresenter mPresenter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_article, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mBackground = view.findViewById(R.id.pv_background);
        recyclerView = view.findViewById(R.id.recycler_articles);
        tvWhenNotLoading = view.findViewById(R.id.tv_cant_load_data);
        pbLoading = view.findViewById(R.id.pb_waiting_for_loading);


        mPresenter = new ArticlePresenterImpl(this);
        mBackground.setZoomable(false);
        recyclerView.setNestedScrollingEnabled(true);
    }

    // --- UI --------------------------------------------------------------------------------------

    @Override
    public void setImageBackground(@DrawableRes int drawableRes) {
        mBackground.setImageResource(drawableRes);
    }

    @Override
    public void onCannotLoadData() {
        tvWhenNotLoading.setVisibility(View.VISIBLE);
        pbLoading.setVisibility(View.GONE);
    }

    @Override
    public void onLoadingData() {
        tvWhenNotLoading.setVisibility(View.GONE);
        pbLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLoadedData() {
        tvWhenNotLoading.setVisibility(View.GONE);
        pbLoading.setVisibility(View.GONE);
    }

    @Override
    public void setRecyclerViewAdapter(RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setRecyclerViewLayoutManager(RecyclerView.LayoutManager layoutManager) {
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public boolean hasAdapter() {
        return recyclerView.getAdapter() != null;
    }
}
