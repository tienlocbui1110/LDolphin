package vn.edu.hcmus.ldolphin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import uk.co.senab.photoview.PhotoView;

public class ImageUpload extends AppCompatActivity {


    private final int PICK_IMAGE_REQUEST = 1;
    static final int REQUEST_IMAGE_CAPTURE = 2;

    private EditText edtTitle;
    private Button btnUpload;
    private ImageView imgUpload;
    private PhotoView mBackground;

    private boolean isTaker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_upload);

        // get data extras
        Intent intent = getIntent();
        isTaker = intent.getBooleanExtra("taker", false);

        findView();

        setupSetting();

        setListener();

    }

    private void setupSetting() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        // get setting value
        boolean isModern = preferences.getBoolean(getString(R.string.key_is_modern_layout), false);

        // custom layout
        if (isModern) {
            Glide.with(this).load(R.drawable.modern_1).into(mBackground);
        } else {
            Glide.with(this).load(R.drawable.default_backgrround_1).into(mBackground);
        }

        mBackground.setZoomable(false);

        if (!isTaker) {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
        } else {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            } else {
                Toast.makeText(this, "Can not start activity.", Toast.LENGTH_LONG).show();
            }
        }
    }

    void findView() {
        edtTitle = findViewById(R.id.edt_title);
        btnUpload = findViewById(R.id.btn_upload);
        imgUpload = findViewById(R.id.img_upload);
        mBackground = findViewById(R.id.iv_background);
    }

    void setListener() {
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValidate()) {
                    //TODO:  Upload Image HTTP
                }
            }
        });

    }

    boolean isValidate() {
        return edtTitle.getText().toString().compareTo("") != 0;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK) {
            if (data == null || data.getData() == null)
                Toast.makeText(this, "Error when try to get data.", Toast.LENGTH_LONG).show();
            else {

                Glide.with(this).load(data.getData()).into(imgUpload);
            }
        } else if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            if (data == null )
                Toast.makeText(this, "Error when try to get data.", Toast.LENGTH_LONG).show();
            else {
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");

                Glide.with(this).load(imageBitmap).into(imgUpload);
            }
        } else {
            finish();
        }
    }
}
