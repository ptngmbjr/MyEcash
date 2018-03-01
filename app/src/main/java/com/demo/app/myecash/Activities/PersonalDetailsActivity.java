package com.demo.app.myecash.Activities;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.ArrayMap;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.demo.app.myecash.Enums.Constants;
import com.demo.app.myecash.Helpers.DialogHelper;
import com.demo.app.myecash.R;
import com.demo.app.myecash.Helpers.ServerCalls;

import org.json.JSONObject;

import java.util.Calendar;

public class PersonalDetailsActivity extends AppCompatActivity implements ServerCalls.OnTaskCompleted,
        View.OnClickListener, DatePickerDialog.OnDateSetListener {

    Toolbar toolbar;
    private final DialogHelper progressDialogHelper = new DialogHelper(this);

    private final String CREATE_CUSTOMER_PROGRESS_DIALOG = "CREATE_CUSTOMER";

    EditText fullName, mobileNo, email, dob, panCard, education, landLine, currentAddress, permanentAddress;
    RadioGroup radioGroup_gender;

    private ServerCalls.postApiCall postObject = null;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Personal Details");

        setContentView(R.layout.activity_personal_details);

        fullName = (EditText) findViewById(R.id.et_full_name);

        mobileNo = (EditText) findViewById(R.id.et_mobile_no);
        email = (EditText) findViewById(R.id.et_email);
        dob = (EditText) findViewById(R.id.et_dob);
        panCard = (EditText) findViewById(R.id.et_pancard);
        education = (EditText) findViewById(R.id.et_education);
        landLine = (EditText) findViewById(R.id.et_landline);
        currentAddress = (EditText) findViewById(R.id.et_current_address);
        permanentAddress = (EditText) findViewById(R.id.et_permanent_address);

        radioGroup_gender = (RadioGroup) findViewById(R.id.radioGroup_gender);


        dob.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        final Calendar calendar = Calendar.getInstance();
        int yy = calendar.get(Calendar.YEAR);
        int mm = calendar.get(Calendar.MONTH);
        int dd = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(this, this, yy, mm, dd);
        dialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        dob.setText(dayOfMonth + "/" + monthOfYear + "/" + year);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_done, menu);
        return true;
    }

    private void postCustomerDetails() {

        if (canProcess() != true)
            return;

        postObject = new ServerCalls().new postApiCall(this);

        progressDialogHelper.showProgressDialog(CREATE_CUSTOMER_PROGRESS_DIALOG);

//        //Pass URL,REQUEST_ID,HEADER,NAME_VAL_PAIR
        ArrayMap<String, String> map = new ArrayMap<String, String>();
        map.put("URL", MainActivity.getDataForKey(Constants.CREATE_CUSTOMER));
        map.put("REQUEST_ID", Constants.CREATE_CUSTOMER);
        map.put("HEADER1", "content-type#~#application/json");
        map.put("HEADER2", "accept#~#application/json");
        map.put("NAME_VAL_PAIR1", "name#~#" + fullName.getText().toString());
        map.put("NAME_VAL_PAIR2", "gender#~#" + getGenderSelectedString());
        map.put("NAME_VAL_PAIR3", "mobileNumber#~#" + mobileNo.getText().toString());
        map.put("NAME_VAL_PAIR4", "landNumber#~#" + landLine.getText().toString());
        map.put("NAME_VAL_PAIR5", "email#~#" + email.getText().toString());
        map.put("NAME_VAL_PAIR6", "dob#~#" + dob.getText().toString());
        map.put("NAME_VAL_PAIR7", "panNumber#~#" + panCard.getText().toString());
        map.put("NAME_VAL_PAIR8", "educationQualification#~#" + education.getText().toString());
        map.put("NAME_VAL_PAIR9", "currentAddress#~#" + currentAddress.getText().toString());
        map.put("NAME_VAL_PAIR10", "permAddress#~#" + permanentAddress.getText().toString());

        postObject.execute(map);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_menu_done:
                postCustomerDetails();
                break;

        }
        return true;
    }

    private boolean canProcess() {
        boolean val = true;

        if (MainActivity.isEmpty(this, fullName) || MainActivity.isEmpty(this, mobileNo) ||
                MainActivity.isEmpty(this, email) || MainActivity.isEmpty(this, dob) ||
                MainActivity.isEmpty(this, panCard) || MainActivity.isEmpty(this, currentAddress) ||
                MainActivity.isEmpty(this, permanentAddress)) {
            val = false;
        }

        if (MainActivity.isValidMobile(this,mobileNo.getText().toString()) != true ||
                MainActivity.isValidMail(this,email.getText().toString()) != true ||
                MainActivity.isValidDate(this,dob.getText().toString()) != true ||
                genderChecked() != true)
            val = false;

        return val;
    }


    private void createCustomerResult(JSONObject result, boolean responseCode) {
        progressDialogHelper.dismissProgressDialog(CREATE_CUSTOMER_PROGRESS_DIALOG);

        boolean posted = false;

        if (responseCode == true) {
            posted = true;
            finish();

        }

        String mesg = posted == false ? "Customer creation failure" : "Customer created successfully";
        MainActivity.showToast(this, mesg);

    }




    private boolean genderChecked() {
        boolean isChecked = true;
        int genid = radioGroup_gender.getCheckedRadioButtonId();
        if (genid == -1) {
            MainActivity.showToast(this, "Please select Gender Type");
            isChecked = false;
        }

        return isChecked;
    }

    private String getGenderSelectedString() {
        int genid = radioGroup_gender.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) findViewById(genid);
        String gender = radioButton.getText().toString();
        return gender.equalsIgnoreCase("male") ? "m" : "f";
    }

    @Override
    public void onTaskCompleted(JSONObject result, String requestID, boolean responseCode) {

        switch (requestID) {
            case Constants.CREATE_CUSTOMER:
                createCustomerResult(result, responseCode);
                break;
            default:
                break;

        }

    }


}
