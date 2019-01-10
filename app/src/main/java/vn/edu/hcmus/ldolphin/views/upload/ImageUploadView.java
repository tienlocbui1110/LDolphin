package vn.edu.hcmus.ldolphin.views.upload;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.DrawableRes;
import android.view.View;

import vn.edu.hcmus.ldolphin.views.base.MVPView;

interface ImageUploadView extends MVPView, View.OnClickListener, View.OnFocusChangeListener {
    void setImageBackground(@DrawableRes int drawableRes);

    void finish();

    String getImageDescription();

    void setImagePreviewer(Bitmap image);

    Intent getIntent();

    void startActivityForResult(Intent intent, int requestCode);
}
