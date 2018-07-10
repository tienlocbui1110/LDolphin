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

public class SearchTitleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_ARTICLE = 1;
    private static final int VIEW_TYPE_PROGRESS = 2;
    private List<Article> mArticleList;
    private LayoutInflater mLayoutInflater;
    private Activity activity;
    private Context mContext;

    private MyViewHolder myViewHolder;
    private ViewHolderProgressBar viewHolderProgressBar;

    public SearchTitleAdapter(Activity activity, List<Article> articleList) {
        this.mArticleList = articleList;
        mLayoutInflater = LayoutInflater.from(activity);
        this.activity = activity;
        mContext = (Context) activity;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView name, time, description;
        private ImageView img, avatar;
        private ImageButton imgButtonMenu;


        private MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.text_name);
            time = itemView.findViewById(R.id.text_time);
            description = itemView.findViewById(R.id.text_title);
            img = itemView.findViewById(R.id.image_picture);
            avatar = itemView.findViewById(R.id.image_avatar);
            imgButtonMenu = itemView.findViewById(R.id.image_button_menu);
        }
    }

    class ViewHolderProgressBar extends RecyclerView.ViewHolder {

        ProgressBar progressBar;

        public ViewHolderProgressBar(View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progress_bar);
        }
    }

    @Override
    public int getItemViewType(int position) {
        // Just as an example, return 0 or 2 depending on position
        // Note that unlike in ListView adapters, types don't have to be contiguous
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
                return new MyViewHolder(item);
            default:
                item = mLayoutInflater.inflate(R.layout.progress_bar, parent, false);
                return new SearchTitleAdapter.ViewHolderProgressBar(item);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        switch (holder.getItemViewType()) {
            case VIEW_TYPE_ARTICLE:
                myViewHolder = (MyViewHolder) holder;

                Article article = mArticleList.get(position);
                myViewHolder.name.setText(article.getName());
                myViewHolder.time.setText(article.getTime());
                myViewHolder.description.setText(article.getDescription());
                Glide.with(mContext).load(R.drawable.avatar).into(myViewHolder.avatar);
                Glide.with(mContext).load(R.drawable.beautiful).into(myViewHolder.img);
                myViewHolder.img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent fullscreen = new Intent(mContext, PhotoFullscreen.class);
                        fullscreen.putExtra("resourceId", R.drawable.beautiful);
                        mContext.startActivity(fullscreen);
                    }
                });
                break;
            case VIEW_TYPE_PROGRESS:
                viewHolderProgressBar = (SearchTitleAdapter.ViewHolderProgressBar) holder;
                // Do with
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    List<Article> tmp = new ArrayList<>();
                                    Utils.prepareData(tmp);
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

    @Override
    public int getItemCount() {
        return mArticleList.size() + 1;
    }

    public void clear() {
        mArticleList.clear();
        notifyDataSetChanged();
    }
}
