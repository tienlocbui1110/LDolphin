package vn.edu.hcmus.ldolphin.views.base.articleload;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.ArrayList;

import vn.edu.hcmus.ldolphin.R;
import vn.edu.hcmus.ldolphin.data.Article;
import vn.edu.hcmus.ldolphin.utils.SimpleThreading;
import vn.edu.hcmus.ldolphin.views.fullscreen.PhotoFullscreen;

class ArticlePresenterImpl implements ArticlePresenter {
    private ArticleView mView;
    private Context mContext;
    private ArticleHolderProgressBarView mProgressBarView;

    ArticlePresenterImpl(ArticleView view) {
        setView(view);
        init();
    }

    // --- Handling --------------------------------------------------------------------------------

    private void init() {
        // TODO: Layout
        mView.setImageBackground(R.drawable.default_backgrround_1);
        mView.setRecyclerViewLayoutManager(new LinearLayoutManager(mContext));
        ArticleLoadingData loadingData = new ArticleLoadingData(this, 500);
        SimpleThreading.run(loadingData);
    }

    @Override
    public void onBindArticle(final ArticleHolderView view, Article article) {
        view.setName(article.getName());
        view.setTime(article.getTime());
        view.setDescription(article.getDescription());
        Glide.with(mContext).asDrawable().load(R.drawable.avatar).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                view.setAvatarPicture(resource);
            }
        });
        Glide.with(mContext).asDrawable().load(R.drawable.beautiful).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                view.setImagePicture(resource);
            }
        });
    }

    @Override
    public void onBindProgressBar(ArticleHolderProgressBarView view) {
        mProgressBarView = view;
        SimpleThreading.run(new ArticleLoadingData(this, 1000));
    }

    @Override
    public void onImageClick() {
        Intent fullscreen = new Intent(mContext, PhotoFullscreen.class);
        fullscreen.putExtra("resourceId", R.drawable.beautiful);
        mContext.startActivity(fullscreen);
    }

    // --- UI --------------------------------------------------------------------------------------

    @Override
    public void setView(ArticleView view) {
        mView = view;
        mContext = mView.getContext();
    }

    @Override
    public void onBeforeArticleLoading() {
        if (!mView.hasAdapter())
            mView.onLoadingData();
    }

    @Override
    public void onArticleLoaded(ArrayList<Article> articles) {
        if (mView.hasAdapter()) {
            if (mProgressBarView != null)
                mProgressBarView.addArticles(articles);
            return;
        }

        ArticleAdapter adapter = new ArticleAdapter(mContext, articles, this);
        mView.onLoadedData();
        mView.setRecyclerViewAdapter(adapter);
    }
}
