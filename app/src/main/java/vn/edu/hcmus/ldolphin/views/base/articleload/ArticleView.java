package vn.edu.hcmus.ldolphin.views.base.articleload;

import android.support.annotation.DrawableRes;
import android.support.v7.widget.RecyclerView;

import vn.edu.hcmus.ldolphin.views.base.MVPView;

public interface ArticleView extends MVPView {
    void setImageBackground(@DrawableRes int drawableRes);

    void onCannotLoadData();

    void onLoadingData();

    void onLoadedData();

    void setRecyclerViewAdapter(RecyclerView.Adapter adapter);

    void setRecyclerViewLayoutManager(RecyclerView.LayoutManager layoutManager);

    boolean hasAdapter();
}
