package vn.edu.hcmus.ldolphin.data;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import vn.edu.hcmus.ldolphin.PhotoFullscreen;
import vn.edu.hcmus.ldolphin.R;

public class ProfileAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_PROFILE = 0;
    private static final int VIEW_TYPE_ARTICLE = 1;
    private static final int VIEW_TYPE_PROGRESS = 2;

    private List<Article> mArticleList;
    private LayoutInflater mLayoutInflater;
    private Activity mContext;

    private ViewHolderProfile viewHolderProfile;
    private ViewHolderArticle viewHolderArticle;
    private ViewHolderProgressBar viewHolderProgressBar;

    public ProfileAdapter(Context context, List<Article> articleList) {
        this.mArticleList = articleList;
        mLayoutInflater = LayoutInflater.from(context);
        mContext = (Activity) context;
    }

    class ViewHolderProfile extends RecyclerView.ViewHolder {

        TextView tvName;
        ImageView ivAvatar;

        public ViewHolderProfile(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.text_user_name);
            ivAvatar = itemView.findViewById(R.id.image_avatar);
        }
    }

    class ViewHolderProgressBar extends RecyclerView.ViewHolder {

        ProgressBar progressBar;

        public ViewHolderProgressBar(View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progress_bar);
        }
    }

    class ViewHolderArticle extends RecyclerView.ViewHolder {

        private TextView name, time, title;
        private ImageView img, avatar;
        private ImageButton imgButtonMenu;

        ViewHolderArticle(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.text_name);
            time = itemView.findViewById(R.id.text_time);
            title = itemView.findViewById(R.id.text_title);
            img = itemView.findViewById(R.id.image_picture);
            avatar = itemView.findViewById(R.id.image_avatar);
            imgButtonMenu = itemView.findViewById(R.id.image_button_menu);
        }
    }

    @Override
    public int getItemViewType(int position) {
        // Just as an example, return 0 or 2 depending on position
        // Note that unlike in ListView adapters, types don't have to be contiguous
        if (position == 0) {
            return VIEW_TYPE_PROFILE;

        } else if (position != mArticleList.size() + 1) {
            return VIEW_TYPE_ARTICLE;
        } else {
            return VIEW_TYPE_PROGRESS;
        }
    }

    @Override
    public int getItemCount() {
        return mArticleList.size() + 2;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item;
        switch (viewType) {
            case VIEW_TYPE_PROFILE:
                item = mLayoutInflater.inflate(R.layout.user_profile_layout, parent, false);
                return new ViewHolderProfile(item);
            case VIEW_TYPE_ARTICLE:
                item = mLayoutInflater.inflate(R.layout.article_layout, parent, false);
                return new ViewHolderArticle(item);
            default:
                item = mLayoutInflater.inflate(R.layout.progress_bar, parent, false);
                return new ViewHolderProgressBar(item);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        switch (holder.getItemViewType()) {
            case VIEW_TYPE_PROFILE:
                ViewHolderProfile viewHolderProfile = (ViewHolderProfile) holder;
                viewHolderProfile.tvName.setText("Nguyễn Văn A");
                Glide.with(mContext).load(R.drawable.avatar).into(viewHolderProfile.ivAvatar);
                break;

            case VIEW_TYPE_ARTICLE:
                viewHolderArticle = (ViewHolderArticle) holder;
                Article article = mArticleList.get(position - 1);
                viewHolderArticle.name.setText(article.getName());
                viewHolderArticle.time.setText(article.getTime());
                viewHolderArticle.title.setText(article.getDescription());
                Glide.with(mContext).load(R.drawable.avatar).into(viewHolderArticle.avatar);
                Glide.with(mContext).load(R.drawable.beautiful).into(viewHolderArticle.img);
                viewHolderArticle.img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent fullscreen = new Intent(mContext, PhotoFullscreen.class);
                        fullscreen.putExtra("resourceId", R.drawable.beautiful);
                        mContext.startActivity(fullscreen);
                    }
                });
                break;
            case VIEW_TYPE_PROGRESS:
                viewHolderProgressBar = (ViewHolderProgressBar) holder;
                // Do with
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                            mContext.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    List<Article> tmp = new ArrayList<>();
                                    Utils.prepareDataProfile(tmp);
                                    mArticleList.addAll(tmp);
                                    notifyDataSetChanged();
                                }
                            });
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
                thread.start();
                break;
        }
    }
}
