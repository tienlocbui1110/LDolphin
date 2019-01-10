package vn.edu.hcmus.ldolphin.views.register;

import android.view.View;

import vn.edu.hcmus.ldolphin.views.base.MVPView;

interface RegisterView extends MVPView, View.OnClickListener, View.OnFocusChangeListener {
    void finish();
}
