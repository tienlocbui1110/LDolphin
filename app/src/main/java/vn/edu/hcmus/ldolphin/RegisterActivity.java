package vn.edu.hcmus.ldolphin;

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

public class RegisterActivity extends AppCompatActivity {

    private EditText edtEmail, edtPassword, edtRetypePassword, edtName, edtPhone, edtCMND, edtIp;
    private Button btnRegister;
    private TextView tvLogin;
    private OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        findView();

        setListener();

        // setting first time

        // check if already login

    }

    void findView() {
        edtEmail = findViewById(R.id.edt_email_register);
        edtPassword = findViewById(R.id.edt_password_register);
        edtRetypePassword = findViewById(R.id.edt_password_retype);
        edtName = findViewById(R.id.edtName_register);
        edtPhone = findViewById(R.id.edt_phone_register);
        edtCMND = findViewById(R.id.edt_cmnd);
        edtIp = findViewById(R.id.edt_server_ip);
        btnRegister = findViewById(R.id.btn_register);
        tvLogin = findViewById(R.id.tv_login);

    }

    void setListener() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValidate()) {
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            RequestBody formBody = new FormBody.Builder()
                                    .add("email", edtEmail.getText().toString())
                                    .add("password", edtPassword.getText().toString())
                                    .add("name",edtName.getText().toString())
                                    .add("phoneNumber",edtPhone.getText().toString())
                                    .add("CMND",edtCMND.getText().toString())
                                    .build();

                            Request request = new Request.Builder()
                                    .url("http://" + edtIp.getText().toString() + "/signup")
                                    .post(formBody)
                                    .build();

                            try {
                                Response response = client.newCall(request).execute();
                                if (response.code() == 401) {
                                    Toast.makeText(RegisterActivity.this, "exsisting user.", Toast.LENGTH_LONG).show();
                                } else if (response.code() == 200) {
                                    SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(RegisterActivity.this).edit();
                                    editor.putString("cookie", response.header("set-cookie"));
                                    editor.apply();
                                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                    startActivity(intent);
                                }
                            } catch (IOException e) {
                                Toast.makeText(RegisterActivity.this, "unknown", Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                    thread.start();
                }
            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
//                startActivity(intent);

                // this activity must be called in Login, so we just need finish this activity
                RegisterActivity.this.finish();
            }
        });
    }

    boolean isValidate() {

        // check email
        if (!isValidEmail(edtEmail.getText().toString())) {
            Toast.makeText(this, "Please enter valid email address.", Toast.LENGTH_LONG).show();
            return false;
        }
        // check password larger or equal than 8 characters.
        if (edtPassword.getText().toString().length() < 8) {
            Toast.makeText(this, "Password must be larger or equal 8 characters.", Toast.LENGTH_LONG).show();
            return false;
        }
        // check if password dont match
        if (edtPassword.getText().toString().compareTo(edtRetypePassword.getText().toString()) != 0) {
            Toast.makeText(this, "Password does not match.", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }


    void settingFirstTime() {

    }

    void checkLoggedIn() {
        
    }
}
