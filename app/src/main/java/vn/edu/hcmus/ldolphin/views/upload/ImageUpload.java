package vn.edu.hcmus.ldolphin.views.upload;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import uk.co.senab.photoview.PhotoView;
import vn.edu.hcmus.ldolphin.R;

public class ImageUpload extends AppCompatActivity implements ImageUploadView {


    private EditText edtDescription;
    private Button btnUpload;
    private ImageView mImagePreviewer;
    private PhotoView mBackground;

    private ImageUploadPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_upload);

        edtDescription = findViewById(R.id.edt_title);
        btnUpload = findViewById(R.id.btn_upload);
        mImagePreviewer = findViewById(R.id.img_upload);
        mBackground = findViewById(R.id.iv_background);

        mPresenter = new ImageUploadPresenterImpl(this);
        btnUpload.setOnClickListener(this);
        edtDescription.setOnFocusChangeListener(this);
        mBackground.setZoomable(false);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == btnUpload.getId()) {
            mPresenter.onSubmit();
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        int id = v.getId();
        if (id == edtDescription.getId()) {
            mPresenter.onDescriptionFocusChange((EditText) v, hasFocus);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mPresenter.onGetPicture(requestCode, resultCode, data);
    }

    // --- UI --------------------------------------------------------------------------------------

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public String getImageDescription() {
        return edtDescription.getText().toString();
    }

    @Override
    public void setImageBackground(@DrawableRes int drawableRes) {
        mBackground.setImageResource(drawableRes);
    }

    @Override
    public void setImagePreviewer(Bitmap image) {
        mImagePreviewer.setImageBitmap(image);
    }


}
