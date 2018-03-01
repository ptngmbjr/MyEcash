package com.demo.app.myecash.Activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.demo.app.myecash.R;

public class UploadPicturesActivity extends AppCompatActivity implements View.OnClickListener {

    EditText uploadBox;
    ImageView image;
    LinearLayout saveCancelLL;

    Button save, cancel;

    int FILE_CHOOSER = 100;

    private int PROOF;
    public static final int SALARY_SLIP = 0;
    public static final int BANK_STATEMENT = 1;
    public static final int PHOTO = 2;
    public static final int PAN_CARD = 3;
    public static final int EMPLOYEE_BADGE = 4;
    public static final int ADDITIONAL_PROOFS = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        PROOF = getIntent().getExtras().getInt("PROOF_TYPE");

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getIntent().getExtras().getString("PROOF_NAME"));

        setContentView(R.layout.activity_upload_pictures);

        saveCancelLL = (LinearLayout) findViewById(R.id.saveCancelLL);

        image = (ImageView) findViewById(R.id.image);
        uploadBox = (EditText) findViewById(R.id.uploadBox);
        save = (Button) findViewById(R.id.saveFile);
        cancel = (Button) findViewById(R.id.cancelFileUpload);

        uploadBox.setOnClickListener(this);
        save.setOnClickListener(this);
        cancel.setOnClickListener(this);


        getWindow().getDecorView().findViewById(android.R.id.content).setOnClickListener(this);


    }


    private void openFileDialog() {
        Intent intent = new Intent()
                .setType("*/*")
                .setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(Intent.createChooser(intent, "Select a file"), FILE_CHOOSER);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FILE_CHOOSER && resultCode == RESULT_OK) {
            addFileToImage(data.getData()); //The uri with the location of the file
        }
    }

    private void addFileToImage(Uri fileName) {

        image.setImageURI(null);
        image.setImageURI(fileName);
        saveCancelLL.setVisibility(View.VISIBLE);
    }

    private void onSave() {
        finish();
    }

    private void onCancel() {
        finish();
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();

        switch (id) {
            case R.id.uploadBox:
                openFileDialog();
                break;
            case R.id.saveFile:
                onSave();
                break;
            case R.id.cancelFileUpload:
                onCancel();
                break;
        }

    }
}
