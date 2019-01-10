package vn.edu.hcmus.ldolphin.views.upload;

import android.content.Intent;
import android.graphics.Bitmap;
import android.widget.EditText;

import com.bumptech.glide.request.target.SimpleTarget;

import java.lang.annotation.Target;

import vn.edu.hcmus.ldolphin.views.base.Presenter;

interface ImageUploadPresenter extends Presenter<ImageUploadView> {
    void onGetPicture(int requestCode, int resultCode, Intent data);

    void onSubmit();

    void onDescriptionFocusChange(EditText descriptionText, boolean hasFocus);
}
