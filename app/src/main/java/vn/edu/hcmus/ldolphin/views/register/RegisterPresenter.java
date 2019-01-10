package vn.edu.hcmus.ldolphin.views.register;

import android.widget.EditText;

import vn.edu.hcmus.ldolphin.views.base.Presenter;

interface RegisterPresenter extends Presenter<RegisterView> {
    void onLogin();

    void onRegister();

    void onFocusChangeEdittext(EditText editText, boolean hasFocus);
}
