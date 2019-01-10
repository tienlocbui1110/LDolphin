package vn.edu.hcmus.ldolphin.views.register;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.Toast;

import vn.edu.hcmus.ldolphin.utils.KeyBoardUtil;
import vn.edu.hcmus.ldolphin.utils.StringUtils;
import vn.edu.hcmus.ldolphin.views.login.LoginActivity;

class RegisterPresenterImpl implements RegisterPresenter {
    private RegisterView mView;
    private Context mContext;

    RegisterPresenterImpl(RegisterView view) {
        setView(view);
    }

    @Override
    public void setView(RegisterView view) {
        mView = view;
        mContext = view.getContext();
    }

    @Override
    public void onLogin() {
        Intent intent = new Intent(mContext, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mContext.startActivity(intent);
    }

    @Override
    public void onRegister() {
        // TODO: Server
    }

    @Override
    public void onFocusChangeEdittext(EditText editText, boolean hasFocus) {
        if (!hasFocus)
            KeyBoardUtil.hideKeyboard(mContext, editText);
    }

    // TODO: use later
    /*
    private boolean isValidate() {
        // check email
        if (!StringUtils.isValidEmail(edtEmail.getText().toString())) {
            Toast.makeText(this, "Please enter valid email address.", Toast.LENGTH_SHORT).show();
            return false;
        }
        // check password larger or equal than 8 characters.
        if (edtPassword.getText().toString().length() < 8) {
            Toast.makeText(this, "Password must be larger or equal 8 characters.", Toast.LENGTH_SHORT).show();
            return false;
        }
        // check if password dont match
        if (edtPassword.getText().toString().compareTo(edtRetypePassword.getText().toString()) != 0) {
            Toast.makeText(this, "Password does not match.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    */

}
