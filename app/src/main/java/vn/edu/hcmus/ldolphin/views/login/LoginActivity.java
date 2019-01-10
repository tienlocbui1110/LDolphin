package vn.edu.hcmus.ldolphin.views.login;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import uk.co.senab.photoview.PhotoView;
import vn.edu.hcmus.ldolphin.R;

public class LoginActivity extends AppCompatActivity implements LoginView {

    private EditText edtEmailLogin, edtPasswordLogin;
    private Button btnLogin;
    private TextView tvRegister;
    private PhotoView mBackground;
    private LoginPresenter mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtEmailLogin = findViewById(R.id.edt_email_login);
        edtPasswordLogin = findViewById(R.id.edt_password_login);
        btnLogin = findViewById(R.id.btn_login);
        tvRegister = findViewById(R.id.tv_register);
        mBackground = findViewById(R.id.iv_background);

        mPresenter = new LoginPresenterImpl(this);
        tvRegister.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        edtEmailLogin.setOnFocusChangeListener(this);
        edtPasswordLogin.setOnFocusChangeListener(this);
        mBackground.setZoomable(false);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == tvRegister.getId()) {
            mPresenter.onRegister();
        } else if (id == btnLogin.getId()) {
            mPresenter.onLogin();
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (v instanceof EditText) {
            mPresenter.onFocusChangeEdittext((EditText) v, hasFocus);
        }
    }

    // --- UI --------------------------------------------------------------------------------------

    @Override
    public Context getContext() {
        return this;
    }
}
