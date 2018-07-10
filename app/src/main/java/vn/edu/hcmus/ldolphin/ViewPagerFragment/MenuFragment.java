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

import com.bumptech.glide.Glide;

import uk.co.senab.photoview.PhotoView;
import vn.edu.hcmus.ldolphin.ProfileActivity;
import vn.edu.hcmus.ldolphin.R;
import vn.edu.hcmus.ldolphin.SettingActivity;

public class MenuFragment extends Fragment {
    PhotoView mBackground;
    View vProfile, vSetting, vLogout;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Load Settings
        settingFragment(view);

        // Set all listener to view

        vProfile.setOnClickListener(new View.OnClickListener() {
            /**
             *
             * This method to open ProfileActivity Activity to show user page.
             */
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ProfileActivity.class);
                startActivity(intent);
            }
        });

        vSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SettingActivity.class);
                startActivity(intent);
            }
        });

    }

    public void settingFragment(@NonNull View view) {
        // Find view
        findNeedView(view);

        // Config

        setupSetting();

        mBackground.setZoomable(false);
    }

    private void findNeedView(@NonNull View view) {
        mBackground = view.findViewById(R.id.iv_background);
        vProfile = view.findViewById(R.id.linear_profile);
        vSetting = view.findViewById(R.id.linear_setting);
        vLogout = view.findViewById(R.id.linear_logout);
    }
    private void setupSetting() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        // get setting value
        boolean isModern = preferences.getBoolean(getString(R.string.key_is_modern_layout), false);

        // custom layout
        if (isModern) {
            Glide.with(this).load(R.drawable.modern_3).into(mBackground);
        } else {
            Glide.with(this).load(R.drawable.default_backgrround_3).into(mBackground);
        }

    }
}
