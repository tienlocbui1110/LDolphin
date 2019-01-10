package vn.edu.hcmus.ldolphin.views.base.articleload;

import java.util.ArrayList;

import vn.edu.hcmus.ldolphin.data.Article;
import vn.edu.hcmus.ldolphin.data.Utils;
import vn.edu.hcmus.ldolphin.utils.SimpleThreading;

public class ArticleLoadingData extends SimpleThreading {
    private ArticlePresenter presenter;
    private ArrayList<Article> mArticles;
    private int waitingTime;

    public ArticleLoadingData(ArticlePresenter presenter, int waitingTime) {
        this.presenter = presenter;
        this.waitingTime = waitingTime;
    }

    @Override
    protected void init() {
        presenter.onBeforeArticleLoading();
    }

    @Override
    public void doInBackground() {
        try {
            Thread.sleep(waitingTime);
            mArticles = new ArrayList<>();
            Utils.prepareData(mArticles);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void done() {
        presenter.onArticleLoaded(mArticles);
    }
}
