package vn.edu.hcmus.ldolphin.ViewPagerFragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bumptech.glide.Glide;

import uk.co.senab.photoview.PhotoView;
import vn.edu.hcmus.ldolphin.ImageUpload;
import vn.edu.hcmus.ldolphin.R;

public class UploadFragment extends Fragment {

    PhotoView mBackground;
    Button mPicker, mTaker;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_upload, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Load Settings
        settingFragment(view);

        // Do something

        setListener();
    }

    private void setListener() {
        mPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), ImageUpload.class);
                i.putExtra("taker", false);
                startActivity(i);
            }
        });
        mTaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), ImageUpload.class);
                i.putExtra("taker", true);
                startActivity(i);
            }
        });
    }

    public void settingFragment(@NonNull View view) {
        findView(view);
        // setup setting

        setupSetting();

        mBackground.setZoomable(false);
    }

    private void findView(@NonNull View view) {
        mBackground = view.findViewById(R.id.iv_background);
        mPicker = view.findViewById(R.id.btn_picker_image);
        mTaker = view.findViewById(R.id.btn_take_pic);
    }

    private void setupSetting() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        // get setting value
        boolean isModern = preferences.getBoolean(getString(R.string.key_is_modern_layout), false);

        // custom layout
        if (isModern) {
            Glide.with(this).load(R.drawable.modern_2).into(mBackground);
        } else {
            Glide.with(this).load(R.drawable.default_backgrround_2).into(mBackground);
        }
    }


}
