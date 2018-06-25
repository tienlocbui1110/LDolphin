package vn.edu.hcmus.ldolphin.data;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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
    private Context mContext;

    private ViewHolderProfile viewHolderProfile;
    private ViewHolderArticle viewHolderArticle;
    private ViewHolderProgressBar viewHolderProgressBar;

    public ProfileAdapter(Context context, List<Article> articleList) {
        this.mArticleList = articleList;
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
    }

    class ViewHolderProfile extends RecyclerView.ViewHolder {

        TextView tvName, tvDescription;
        ImageView ivAvatar;
        ImageButton ibEditName;
        ImageButton ibEditDescription;

        public ViewHolderProfile(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.text_user_name);
            tvDescription = itemView.findViewById(R.id.text_description);
            ivAvatar = itemView.findViewById(R.id.image_avatar);
            ibEditName = itemView.findViewById(R.id.edit_name);
            ibEditDescription = itemView.findViewById(R.id.edit_description);
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

        private TextView name, time, description;
        private ImageView img, avatar;
        private ImageButton imgButtonMenu;

        ViewHolderArticle(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.text_name);
            time = itemView.findViewById(R.id.text_time);
            description = itemView.findViewById(R.id.text_description);
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

        } else if (position != mArticleList.size()+1) {
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
                viewHolderProfile.tvDescription.setText("HAHHAHhhahahhah");
                Glide.with(mContext).load(R.drawable.avatar).into(viewHolderProfile.ivAvatar);
                break;

            case VIEW_TYPE_ARTICLE:
                viewHolderArticle = (ViewHolderArticle) holder;
                Article article = mArticleList.get(position-1);
                viewHolderArticle.name.setText(article.getName());
                viewHolderArticle.time.setText(article.getTime());
                viewHolderArticle.description.setText(article.getDescription());
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
                // Set dropdown menu
                viewHolderArticle.imgButtonMenu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        PopupMenu popup = new PopupMenu(mContext, viewHolderArticle.imgButtonMenu);
                        popup.getMenuInflater().inflate(R.menu.article_menu, popup.getMenu());
                        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            public boolean onMenuItemClick(MenuItem item) {
                                Toast.makeText(mContext, "You Clicked : " + item.getTitle(), Toast.LENGTH_SHORT).show();
                                return true;
                            }
                        });
                        popup.show();
                    }
                });
                break;
            case VIEW_TYPE_PROGRESS:
                viewHolderProgressBar = (ViewHolderProgressBar) holder;
                // Do with
                List<Article> tmp = new ArrayList<>();
                Utils.prepareData(tmp);
                mArticleList.addAll(tmp);
                break;
        }
    }
}
