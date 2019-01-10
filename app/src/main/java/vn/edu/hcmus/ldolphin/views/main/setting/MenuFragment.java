package vn.edu.hcmus.ldolphin.views.main.setting;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import uk.co.senab.photoview.PhotoView;
import vn.edu.hcmus.ldolphin.R;

public class MenuFragment extends Fragment implements MenuView {
    private PhotoView mBackground;
    private View vProfile, vSetting, vLogout;
    private MenuPresenter mPresenter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mBackground = view.findViewById(R.id.iv_background);
        vProfile = view.findViewById(R.id.linear_profile);
        vSetting = view.findViewById(R.id.linear_setting);
        vLogout = view.findViewById(R.id.linear_logout);

        mPresenter = new MenuPresenterImpl(this);
        vProfile.setOnClickListener(this);
        vSetting.setOnClickListener(this);
        mBackground.setZoomable(false);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == vProfile.getId()) {
            mPresenter.goToProfile();
        } else if (id == vSetting.getId()) {
            mPresenter.goToSetting();
        }
    }

    @Override
    public void setImageBackground(@DrawableRes int drawableRes) {
        mBackground.setImageResource(drawableRes);
    }
}
