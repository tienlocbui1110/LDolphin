package vn.edu.hcmus.ldolphin.views.login;

import android.content.Context;
import android.content.Intent;
import android.widget.EditText;

import vn.edu.hcmus.ldolphin.utils.KeyBoardUtil;
import vn.edu.hcmus.ldolphin.views.register.RegisterActivity;

class LoginPresenterImpl implements LoginPresenter {
    private LoginView mView;
    private Context mContext;

    public LoginPresenterImpl(LoginView view) {
        setView(view);
    }

    @Override
    public void onLogin() {
        //TODO: Request to server
    }

    @Override
    public void onRegister() {
        Intent intent = new Intent(mContext, RegisterActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        mContext.startActivity(intent);
    }

    @Override
    public void onFocusChangeEdittext(EditText editText, boolean hasFocus) {
        if (!hasFocus)
            KeyBoardUtil.hideKeyboard(mContext, editText);
    }

    @Override
    public void setView(LoginView view) {
        mView = view;
        mContext = view.getContext();
    }
}
