package vn.edu.hcmus.ldolphin.views.base.articleload;

import java.util.List;

import vn.edu.hcmus.ldolphin.data.Article;
import vn.edu.hcmus.ldolphin.views.base.MVPView;

interface ArticleHolderProgressBarView extends MVPView {
    void addArticles(List<Article> articleList);
}
