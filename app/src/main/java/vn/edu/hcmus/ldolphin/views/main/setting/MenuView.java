package vn.edu.hcmus.ldolphin.views.main.setting;

import android.support.annotation.DrawableRes;
import android.view.View;

import vn.edu.hcmus.ldolphin.views.base.MVPView;

interface MenuView extends MVPView, View.OnClickListener {
    void setImageBackground(@DrawableRes int drawableRes);
}
