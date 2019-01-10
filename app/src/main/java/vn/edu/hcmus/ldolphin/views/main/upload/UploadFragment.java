package vn.edu.hcmus.ldolphin.views.main.upload;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import uk.co.senab.photoview.PhotoView;
import vn.edu.hcmus.ldolphin.R;

public class UploadFragment extends Fragment implements UploadView {

    private PhotoView mBackground;
    private Button mBtnPicker, mBtnTaker;
    private UploadPresenter mPresenter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_upload, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mBackground = view.findViewById(R.id.iv_background);
        mBtnPicker = view.findViewById(R.id.btn_picker_image);
        mBtnTaker = view.findViewById(R.id.btn_take_pic);

        mPresenter = new UploadPresenterImpl(this);
        mBtnPicker.setOnClickListener(this);
        mBtnTaker.setOnClickListener(this);
        mBackground.setZoomable(false);
    }

    // --- Listener --------------------------------------------------------------------------------

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == mBtnPicker.getId()) {
            mPresenter.chooseImage();
        } else if (id == mBtnTaker.getId()) {
            mPresenter.takePicture();
        }
    }

    // --- UI --------------------------------------------------------------------------------------

    @Override
    public void setImageBackground(@DrawableRes int drawableRes) {
        mBackground.setImageResource(drawableRes);
    }
}
