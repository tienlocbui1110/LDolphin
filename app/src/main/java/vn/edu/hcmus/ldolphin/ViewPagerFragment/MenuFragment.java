package vn.edu.hcmus.ldolphin.ViewPagerFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.gabrielsamojlo.keyboarddismisser.KeyboardDismisser;

import uk.co.senab.photoview.PhotoView;
import vn.edu.hcmus.ldolphin.R;

public class MenuFragment extends Fragment {
    PhotoView mBackground;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Load Settings
        settingFragment(view);

        // Do something

    }

    public void settingFragment(@NonNull View view) {
        mBackground = view.findViewById(R.id.iv_background);
        mBackground.setZoomable(false);

        // Load background
        Glide.with(view.getContext()).load(R.drawable.default_backgrround_3).into(mBackground);
    }
}
