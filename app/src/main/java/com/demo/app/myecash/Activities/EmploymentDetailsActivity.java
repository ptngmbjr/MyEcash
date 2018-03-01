package com.demo.app.myecash.Activities;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.ArrayMap;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.demo.app.myecash.Enums.Constants;
import com.demo.app.myecash.Helpers.DialogHelper;
import com.demo.app.myecash.R;
import com.demo.app.myecash.Helpers.ServerCalls;

import org.json.JSONObject;

import java.util.Calendar;

public class EmploymentDetailsActivity extends AppCompatActivity implements ServerCalls.OnTaskCompleted,
        View.OnClickListener, DatePickerDialog.OnDateSetListener {

    Toolbar toolbar;

    private final DialogHelper progressDialogHelper = new DialogHelper(this);

    private final String UPATE_PROFESSION_DETAILS = "UPATE_PROFESSION_DETAILS";

    EditText employerName, landLine, email, workingSince, designation, netSalary, officeAddress;

    private ServerCalls.postApiCall postObject = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Employment Details");

        setContentView(R.layout.activity_employment_details);

        employerName = (EditText) findViewById(R.id.et_employer_name);
        landLine = (EditText) findViewById(R.id.et_landline_no);
        email = (EditText) findViewById(R.id.et_official_email);
        workingSince = (EditText) findViewById(R.id.et_working_since);
        designation = (EditText) findViewById(R.id.et_designation);
        netSalary = (EditText) findViewById(R.id.et_net_sal);
        officeAddress = (EditText) findViewById(R.id.et_office_address);

        workingSince.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_done, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_menu_done:
                postEmploymentDetails();
                break;

        }
        return true;
    }

    private void postEmploymentDetails() {

        if (canProcess() != true)
            return;

        postObject = new ServerCalls().new postApiCall(this);

        progressDialogHelper.showProgressDialog(UPATE_PROFESSION_DETAILS);

//        //Pass URL,REQUEST_ID,HEADER,NAME_VAL_PAIR
        ArrayMap<String, String> map = new ArrayMap<String, String>();
        map.put("URL", MainActivity.getDataForKey(Constants.UPATE_PROFESSION_DETAILS));
        map.put("REQUEST_ID", Constants.UPATE_PROFESSION_DETAILS);
        map.put("HEADER1", "content-type#~#application/json");
        map.put("HEADER2", "accept#~#application/json");
        map.put("NAME_VAL_PAIR1", "employerName#~#" + employerName.getText().toString());
        map.put("NAME_VAL_PAIR2", "workingSince#~#" + workingSince.getText().toString());
        map.put("NAME_VAL_PAIR3", "netmonthlySalary#~#" + netSalary.getText().toString());
        map.put("NAME_VAL_PAIR4", "landNumber#~#" + landLine.getText().toString());
        map.put("NAME_VAL_PAIR5", "email#~#" + email.getText().toString());
        map.put("NAME_VAL_PAIR6", "designation#~#" + designation.getText().toString());
        map.put("NAME_VAL_PAIR7", "workAddress#~#" + officeAddress.getText().toString());

        postObject.execute(map);

    }

    private boolean canProcess() {
        boolean val = true;

        if (MainActivity.isEmpty(this, employerName) || MainActivity.isEmpty(this, landLine) ||
                MainActivity.isEmpty(this, email) || MainActivity.isEmpty(this, workingSince) ||
                MainActivity.isEmpty(this, designation) || MainActivity.isEmpty(this, netSalary) ||
                MainActivity.isEmpty(this, officeAddress)) {
            val = false;
        }

        if (MainActivity.isValidMail(this, email.getText().toString()) != true)
            val = false;

        return val;
    }

    private void updateBankDetailsResult(JSONObject result, boolean responseCode) {
        progressDialogHelper.dismissProgressDialog(UPATE_PROFESSION_DETAILS);

        boolean posted = false;

        if (responseCode == true) {
            posted = true;
            finish();

        }

        String mesg = posted == false ? "Failure updating Professional details" : "Professional details updated successfully";
        MainActivity.showToast(this, mesg);

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
        workingSince.setText(dayOfMonth + "/" + monthOfYear + "/" + year);
    }

    @Override
    public void onTaskCompleted(JSONObject result, String requestID, boolean responseCode) {

        switch (requestID) {
            case Constants.UPATE_PROFESSION_DETAILS:
                updateBankDetailsResult(result, responseCode);
                break;
            default:
                break;

        }

    }
}
