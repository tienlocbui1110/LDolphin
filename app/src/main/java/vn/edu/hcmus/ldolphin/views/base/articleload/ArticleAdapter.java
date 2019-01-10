package vn.edu.hcmus.ldolphin.views.base.articleload;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import vn.edu.hcmus.ldolphin.R;
import vn.edu.hcmus.ldolphin.data.Article;

public class ArticleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_ARTICLE = 1;
    private static final int VIEW_TYPE_PROGRESS = 2;
    private List<Article> mArticleList;
    private LayoutInflater mLayoutInflater;
    private Context mContext;

    private ArticlePresenter mPresenter;

    public ArticleAdapter(Context context, List<Article> articleList, ArticlePresenter presenter) {
        this.mArticleList = articleList;
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
        mPresenter = presenter;
    }

    class ArticleViewHolder extends RecyclerView.ViewHolder implements ArticleHolderView {
        private TextView name, time, description;
        private ImageView img, avatar;
        private ImageButton imgButtonMenu;


        private ArticleViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.text_name);
            time = itemView.findViewById(R.id.text_time);
            description = itemView.findViewById(R.id.text_title);
            img = itemView.findViewById(R.id.image_picture);
            avatar = itemView.findViewById(R.id.image_avatar);
            imgButtonMenu = itemView.findViewById(R.id.image_button_menu);
        }

        @Override
        public Context getContext() {
            return mContext;
        }

        @Override
        public void setName(String name) {
            this.name.setText(name);
        }

        @Override
        public void setTime(String time) {
            this.time.setText(time);
        }

        @Override
        public void setDescription(String description) {
            this.description.setText(description);
        }

        @Override
        public void setImagePicture(Drawable image) {
            this.img.setImageDrawable(image);
        }

        @Override
        public void setAvatarPicture(Drawable avatar) {
            this.avatar.setImageDrawable(avatar);
        }

        @Override
        public void onClick(View v) {
            int id = v.getId();
            if (id == img.getId()) {
                mPresenter.onImageClick();
            }
        }
    }

    class ViewHolderProgressBar extends RecyclerView.ViewHolder implements ArticleHolderProgressBarView {
        private ProgressBar progressBar;

        ViewHolderProgressBar(View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progress_bar);
        }

        @Override
        public Context getContext() {
            return mContext;
        }

        @Override
        public void addArticles(List<Article> articleList) {
            mArticleList.addAll(articleList);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position != mArticleList.size()) {
            return VIEW_TYPE_ARTICLE;
        } else {
            return VIEW_TYPE_PROGRESS;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item;
        switch (viewType) {
            case VIEW_TYPE_ARTICLE:
                item = mLayoutInflater.inflate(R.layout.article_layout, parent, false);
                return new ArticleViewHolder(item);
            default:
                item = mLayoutInflater.inflate(R.layout.progress_bar, parent, false);
                return new ArticleAdapter.ViewHolderProgressBar(item);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        switch (holder.getItemViewType()) {
            case VIEW_TYPE_ARTICLE:
                ArticleViewHolder articleViewHolder = (ArticleViewHolder) holder;
                Article article = mArticleList.get(position);
                mPresenter.onBindArticle(articleViewHolder, article);
                articleViewHolder.img.setOnClickListener(articleViewHolder);
                break;
            case VIEW_TYPE_PROGRESS:
                ViewHolderProgressBar viewHolderProgressBar = (ArticleAdapter.ViewHolderProgressBar) holder;
                mPresenter.onBindProgressBar(viewHolderProgressBar);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mArticleList.size() + 1;
    }
}
