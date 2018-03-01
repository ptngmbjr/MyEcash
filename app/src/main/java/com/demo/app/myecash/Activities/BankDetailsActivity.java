package com.demo.app.myecash.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.ArrayMap;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.demo.app.myecash.Enums.Constants;
import com.demo.app.myecash.Helpers.DialogHelper;
import com.demo.app.myecash.R;
import com.demo.app.myecash.Helpers.ServerCalls;

import org.json.JSONObject;

public class BankDetailsActivity extends AppCompatActivity implements ServerCalls.OnTaskCompleted {

    Toolbar toolbar;

    private final DialogHelper progressDialogHelper = new DialogHelper(this);

    private final String UPATE_BANK_DETAILS = "UPATE_BANK_DETAILS";

    EditText accountNo, confirmAcNo, ifsc, bankName;

    private ServerCalls.postApiCall postObject = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Bank Details");

        setContentView(R.layout.activity_bank_details);


        accountNo = (EditText) findViewById(R.id.et_account_no);
        confirmAcNo = (EditText) findViewById(R.id.et_confirm_ac_no);
        ifsc = (EditText) findViewById(R.id.et_ifsc);
        bankName = (EditText) findViewById(R.id.et_bank_name);

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
                postBankDetails();
                break;

        }
        return true;
    }

    private void postBankDetails() {

        if (canProcess() != true)
            return;

        postObject = new ServerCalls().new postApiCall(this);

        progressDialogHelper.showProgressDialog(UPATE_BANK_DETAILS);

//        //Pass URL,REQUEST_ID,HEADER,NAME_VAL_PAIR
        ArrayMap<String, String> map = new ArrayMap<String, String>();
        map.put("URL", MainActivity.getDataForKey(Constants.UPDATE_BANK_DETAILS));
        map.put("REQUEST_ID", Constants.UPDATE_BANK_DETAILS);
        map.put("HEADER1", "content-type#~#application/json");
        map.put("HEADER2", "accept#~#application/json");
        map.put("NAME_VAL_PAIR1", "bankName#~#" + bankName.getText().toString());
        map.put("NAME_VAL_PAIR2", "accountNumber#~#" + accountNo.getText().toString());
        map.put("NAME_VAL_PAIR3", "confirmAccountNumber#~#" + confirmAcNo.getText().toString());
        map.put("NAME_VAL_PAIR4", "ifscCode#~#" + ifsc.getText().toString());

        postObject.execute(map);

    }

    private boolean canProcess() {
        boolean val = true;

        if (MainActivity.isEmpty(this, bankName) || MainActivity.isEmpty(this, accountNo) ||
                MainActivity.isEmpty(this, confirmAcNo) || MainActivity.isEmpty(this, ifsc)) {
            val = false;
        }

        if (accountNo.getText().toString().equals(confirmAcNo.getText().toString()) != true) {
            MainActivity.showToast(this, "Account numbers doens't match");
            val = false;
        }

        return val;
    }

    private void updateBankDetailsResult(JSONObject result, boolean responseCode) {
        progressDialogHelper.dismissProgressDialog(UPATE_BANK_DETAILS);

        boolean posted = false;

        if (responseCode == true) {
            posted = true;
            finish();

        }

        String mesg = posted == false ? "Failure updating bank details" : "Bank details updated successfully";
        MainActivity.showToast(this, mesg);

    }


    @Override
    public void onTaskCompleted(JSONObject result, String requestID, boolean responseCode) {

        switch (requestID) {
            case Constants.UPDATE_BANK_DETAILS:
                updateBankDetailsResult(result, responseCode);
                break;
            default:
                break;

        }

    }

    private void showToast(String text) {
        Toast.makeText(this.getApplicationContext(), text, Toast.LENGTH_SHORT).show();

    }


}
