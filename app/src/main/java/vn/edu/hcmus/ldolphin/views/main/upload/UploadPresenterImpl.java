package vn.edu.hcmus.ldolphin.views.main.upload;

import android.content.Context;
import android.content.Intent;

import vn.edu.hcmus.ldolphin.R;
import vn.edu.hcmus.ldolphin.views.upload.ImageUpload;

class UploadPresenterImpl implements UploadPresenter {
    private UploadView mView;
    private Context mContext;

    UploadPresenterImpl(UploadView view) {
        setView(view);
        init();
    }

    private void init() {
        // TODO: Custom layout
        mView.setImageBackground(R.drawable.default_backgrround_2);
    }

    @Override
    public void setView(UploadView view) {
        mView = view;
        mContext = mView.getContext();
    }

    @Override
    public void chooseImage() {
        Intent i = new Intent(mContext, ImageUpload.class);
        i.putExtra("taker", false);
        mContext.startActivity(i);
    }

    @Override
    public void takePicture() {
        Intent i = new Intent(mContext, ImageUpload.class);
        i.putExtra("taker", true);
        mContext.startActivity(i);
    }
}
