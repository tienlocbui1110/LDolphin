package vn.edu.hcmus.ldolphin.views.main.upload;

import android.graphics.drawable.Drawable;

import com.bumptech.glide.request.target.Target;

import vn.edu.hcmus.ldolphin.views.base.Presenter;

interface UploadPresenter extends Presenter<UploadView> {
    void chooseImage();

    void takePicture();
}
