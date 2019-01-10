package vn.edu.hcmus.ldolphin.views.upload;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import vn.edu.hcmus.ldolphin.R;
import vn.edu.hcmus.ldolphin.utils.KeyBoardUtil;

import static android.app.Activity.RESULT_OK;

class ImageUploadPresenterImpl implements ImageUploadPresenter {
    private ImageUploadView mView;
    private Context mContext;

    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int REQUEST_IMAGE_CAPTURE = 2;

    ImageUploadPresenterImpl(ImageUploadView view) {
        setView(view);
        init();
    }

    private void init() {
        mView.setImageBackground(R.drawable.default_backgrround_1);
        Intent intent = mView.getIntent();
        boolean isTaker = intent.getBooleanExtra("taker", false);
        if (isTaker) {
            takePic();
        } else {
            getPic();
        }
    }

    private void takePic() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(mContext.getPackageManager()) != null) {
            mView.startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        } else {
            Toast.makeText(mContext, "Can not start activity.", Toast.LENGTH_LONG).show();
        }
    }

    private void getPic() {
        Intent selectorIntent = new Intent();
        selectorIntent.setType("image/*");
        selectorIntent.setAction(Intent.ACTION_GET_CONTENT);
        mView.startActivityForResult(Intent.createChooser(selectorIntent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    public void onGetPicture(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK) {
            if (data == null || data.getData() == null)
                Toast.makeText(mContext, "Error when try to get data.", Toast.LENGTH_SHORT).show();
            else {
                Glide.with(mContext).asBitmap().load(data.getData()).into(new Target());
                return;
            }
        } else if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            if (data == null)
                Toast.makeText(mContext, "Error when try to get data.", Toast.LENGTH_SHORT).show();
            else {
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                Glide.with(mContext).asBitmap().load(imageBitmap).into(new Target());
                return;
            }
        }
        mView.finish();
    }

    @Override
    public void onSubmit() {
        //TODO: Nothing to do right now.
    }

    @Override
    public void onDescriptionFocusChange(EditText descriptionText, boolean hasFocus) {
        if (!hasFocus) {
            KeyBoardUtil.hideKeyboard(mContext, descriptionText);
        }
    }

    // --- UI --------------------------------------------------------------------------------------
    @Override
    public void setView(ImageUploadView view) {
        mView = view;
        mContext = mView.getContext();
    }

    class Target extends SimpleTarget<Bitmap> {
        @Override
        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
            mView.setImagePreviewer(resource);
        }
    }
}
