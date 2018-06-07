package vn.edu.hcmus.ldolphin.data;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import vn.edu.hcmus.ldolphin.MainActivity;
import vn.edu.hcmus.ldolphin.PhotoFullscreen;
import vn.edu.hcmus.ldolphin.R;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.MyViewHolder> {

    private List<Article> mArticleList;
    private LayoutInflater mLayoutInflater;
    private Activity mContext;

    public ArticleAdapter(Context context, List<Article> articleList) {
        this.mArticleList = articleList;
        mLayoutInflater = LayoutInflater.from(context);
        mContext = (Activity) context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView name, time, description;
        private ImageView img, avatar;
        private ImageButton imgButtonMenu;


        private MyViewHolder(View itemView) {
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
    @NonNull
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = mLayoutInflater.inflate(R.layout.article_layout, parent, false);
        return new MyViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull final ArticleAdapter.MyViewHolder holder, int position) {
        Article article = mArticleList.get(position);
        holder.name.setText(article.getName());
        holder.time.setText(article.getTime());
        holder.description.setText(article.getDescription());
        Glide.with(mContext).load(R.drawable.avatar).into(holder.avatar);
        Glide.with(mContext).load(R.drawable.beautiful).into(holder.img);
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fullscreen = new Intent(mContext, PhotoFullscreen.class);
                fullscreen.putExtra("resourceId", R.drawable.beautiful);
                mContext.startActivity(fullscreen);
            }
        });
        // Set dropdown menu
        holder.imgButtonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(mContext, holder.imgButtonMenu);
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
    }

    @Override
    public int getItemCount() {
        return mArticleList.size();
    }
}
