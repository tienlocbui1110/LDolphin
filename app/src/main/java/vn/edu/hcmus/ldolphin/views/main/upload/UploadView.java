package vn.edu.hcmus.ldolphin.views.main.upload;

import android.support.annotation.DrawableRes;
import android.view.View;

import vn.edu.hcmus.ldolphin.views.base.MVPView;

interface UploadView extends MVPView, View.OnClickListener {
    void setImageBackground(@DrawableRes int drawableRes);
}
