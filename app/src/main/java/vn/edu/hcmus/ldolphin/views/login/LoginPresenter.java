package vn.edu.hcmus.ldolphin.views.login;

import android.widget.EditText;

import vn.edu.hcmus.ldolphin.views.base.Presenter;

interface LoginPresenter extends Presenter<LoginView> {
    void onLogin();

    void onRegister();

    void onFocusChangeEdittext(EditText editText, boolean hasFocus);
}
