package vn.edu.hcmus.ldolphin.views.base.articleload;

import android.graphics.drawable.Drawable;
import android.view.View;

import vn.edu.hcmus.ldolphin.views.base.MVPView;

interface ArticleHolderView extends MVPView, View.OnClickListener {

    void setName(String name);

    void setTime(String time);

    void setDescription(String description);

    void setImagePicture(Drawable image);

    void setAvatarPicture(Drawable avatar);
}
