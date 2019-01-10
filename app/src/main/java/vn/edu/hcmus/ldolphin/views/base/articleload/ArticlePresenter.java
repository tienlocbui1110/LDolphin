package vn.edu.hcmus.ldolphin.views.base.articleload;

import java.util.ArrayList;

import vn.edu.hcmus.ldolphin.data.Article;
import vn.edu.hcmus.ldolphin.views.base.Presenter;

public interface ArticlePresenter extends Presenter<ArticleView> {
    void onBeforeArticleLoading();

    void onArticleLoaded(ArrayList<Article> articles);

    void onBindArticle(ArticleHolderView view, Article article);

    void onBindProgressBar(ArticleHolderProgressBarView view);

    void onImageClick();
}
