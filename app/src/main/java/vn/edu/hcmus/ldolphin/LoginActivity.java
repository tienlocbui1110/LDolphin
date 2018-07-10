package vn.edu.hcmus.ldolphin;

import android.app.Activity;
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

public class LoginActivity extends AppCompatActivity {

    private EditText edtEmailLogin, edtPasswordLogin, edtServerIp;
    private Button btnLogin;
    private TextView tvRegister;
    private OkHttpClient client = new OkHttpClient();
    private Activity activity =  this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findView();

        setListener();
        final SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
        final String cookie = pref.getString("cookie", null);
        if (cookie != null) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    // try to login
                    Request request = new Request.Builder()
                            .url("http://" + "10.0.2.2:3000" + "/login")
                            .addHeader("Cookie", cookie)
                            .build();

                    try {
                        Response response = client.newCall(request).execute();
                        if (response.code() == 200) {
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    } catch (IOException e) {
                    }
                }
            });
            thread.start();
        }
    }

    void findView() {
        edtEmailLogin = findViewById(R.id.edt_email_login);
        edtPasswordLogin = findViewById(R.id.edt_password_login);
        edtServerIp = findViewById(R.id.edt_server_ip);
        btnLogin = findViewById(R.id.btn_login);
        tvRegister = findViewById(R.id.tv_register);
    }

    void setListener() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValidate()) {
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            RequestBody formBody = new FormBody.Builder()
                                    .add("email", edtEmailLogin.getText().toString())
                                    .add("password", edtPasswordLogin.getText().toString())
                                    .build();

                            Request request = new Request.Builder()
                                    .url("http://" + edtServerIp.getText().toString() + "/login")
                                    .post(formBody)
                                    .build();

                            try {
                                Response response = client.newCall(request).execute();
                                if (response.code() == 401) {
                                } else if (response.code() == 200) {
                                    SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this).edit();
                                    editor.putString("cookie", response.header("set-cookie"));
                                    editor.apply();
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                }
                            } catch (IOException e) {

                            }
                        }
                    });

                    thread.start();
                }
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Intent to Register Activity
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    boolean isValidate() {

        // check email
        if (!isValidEmail(edtEmailLogin.getText().toString())) {
            Toast.makeText(this, "Please enter valid email address.", Toast.LENGTH_LONG).show();
            return false;
        }
        // check password larger or equal than 8 characters.
        if (edtPasswordLogin.getText().toString().length() < 8) {
            Toast.makeText(this, "Password must be larger or equal 8 characters.", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

}
