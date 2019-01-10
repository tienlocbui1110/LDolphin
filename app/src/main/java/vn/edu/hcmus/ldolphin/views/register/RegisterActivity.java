package vn.edu.hcmus.ldolphin.views.register;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import uk.co.senab.photoview.PhotoView;
import vn.edu.hcmus.ldolphin.R;
import vn.edu.hcmus.ldolphin.views.main.MainActivity;

public class RegisterActivity extends AppCompatActivity implements RegisterView {

    private EditText edtEmail, edtPassword, edtRetypePassword, edtName;
    private Button btnRegister;
    private TextView tvLogin;
    private PhotoView mBackground;
    private RegisterPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtEmail = findViewById(R.id.edt_email_register);
        edtPassword = findViewById(R.id.edt_password_register);
        edtRetypePassword = findViewById(R.id.edt_password_retype);
        edtName = findViewById(R.id.edtName_register);
        btnRegister = findViewById(R.id.btn_register);
        tvLogin = findViewById(R.id.tv_login);
        mBackground = findViewById(R.id.iv_background);

        mPresenter = new RegisterPresenterImpl(this);
        edtEmail.setOnFocusChangeListener(this);
        edtPassword.setOnFocusChangeListener(this);
        edtRetypePassword.setOnFocusChangeListener(this);
        edtName.setOnFocusChangeListener(this);
        tvLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        mBackground.setZoomable(false);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == tvLogin.getId()) {
            mPresenter.onLogin();
        } else if (id == btnRegister.getId()) {
            mPresenter.onRegister();
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (v instanceof EditText) {
            mPresenter.onFocusChangeEdittext((EditText) v, hasFocus);
        }
    }

    @Override
    public Context getContext() {
        return this;
    }
}
