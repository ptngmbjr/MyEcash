package com.demo.app.myecash;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.data;

public class BankDetailsActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView bankName;
    TextView accountNumber;
    TextView BankName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Bank Details");

        setContentView(R.layout.activity_bank_details);



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
                Toast.makeText(this, "Done clicked", Toast.LENGTH_LONG).show();

                break;

        }
        return true;
    }

//    private void saveBankDetails() {
//        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>;
//
//        nameValuePairs.add(new BasicNameValuePair("bankName", ));
//        nameValuePairs.add(new BasicNameValuePair("username", data[0]));
//        nameValuePairs.add(new BasicNameValuePair("password", data[1]));
//        nameValuePairs.add(new BasicNameValuePair("scope", "openid"));
//
//    }
}
