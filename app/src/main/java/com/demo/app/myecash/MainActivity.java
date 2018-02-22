package com.demo.app.myecash;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.appevents.AppEventsLogger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.util.AttributeSet;
import android.util.Base64;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;

import com.crashlytics.android.Crashlytics;
import com.demo.app.myecash.Fragments.GetCasheFragment;
import com.demo.app.myecash.Fragments.MoreFragment;
import com.demo.app.myecash.Fragments.MyCasheFragment;
import com.demo.app.myecash.Fragments.ProfileFragment;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;

import io.fabric.sdk.android.Fabric;

import android.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private final DialogHelper progressDialogHelper = new DialogHelper(this);

    Toolbar toolbar;
    private int exitVariable = 0;


    private int[] tabIcons = {
            R.drawable.ic_getcashe,
            R.drawable.ic_mycashe,
            R.drawable.ic_profile,
            R.drawable.ic_more
    };

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            return fragmentSelection(item.getItemId());

        }

    };

    private boolean fragmentSelection(int idVal) {
        android.app.Fragment fragment = null;
        String tagname = "";

        switch (idVal) {
            case R.id.navigation_getcashe:
            case R.id.nav_getcashe:
                fragment = new GetCasheFragment();
                tagname = "GetCashe";
                break;
            case R.id.navigation_mycashe:
            case R.id.nav_mycashe:
                fragment = new MyCasheFragment();
                tagname = "MyCashe";
                break;
            case R.id.navigation_profile:
            case R.id.nav_myprofile:
                fragment = new ProfileFragment();
                tagname = "Profile";
                break;
            case R.id.navigation_more:
                fragment = new MoreFragment();
                tagname = "More";
                break;
        }

        if (fragment != null) {
            loadFragment(fragment, tagname);
            return true;
        }

        return false;

    }

    public void loadFragment(android.app.Fragment fragment, String name) {
        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, fragment, name);
        ft.commit();

        toolbar.setTitle(name);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());

        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        disableShiftMode(navigation);

        fragmentSelection(R.id.nav_getcashe);


    }

    private void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                item.setShiftingMode(false);
                // set once again checked value, so view will be updated
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            Log.e("BNVHelper", "Unable to get shift mode field", e);
        } catch (IllegalAccessException e) {
            Log.e("BNVHelper", "Unable to change value of shift mode", e);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            exitVariable++;

            if (exitVariable >= 2) {
                finish();
            } else
                Toast.makeText(this, R.string.exittext, Toast.LENGTH_SHORT).show();
//            super.onBackPressed();

        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        fragmentSelection(item.getItemId());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public static boolean isEmpty(Context c,EditText value) {
        boolean val = false;
        if (value.getText().toString().length() <= 0) {
            showToast(c,value.getHint().toString() + " cannot be empty");
            val = true;
        }
        return val;
    }

    public static void showToast(Context c,String text) {
        Toast.makeText(c, text, Toast.LENGTH_SHORT).show();

    }

    public static boolean isValidMail(Context c,String email) {
        boolean valid = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
        if (valid != true)
            showToast(c, "Not a valid email address");
        return valid;
    }

    public static boolean isValidMobile(Context c,String phone) {
        boolean valid = android.util.Patterns.PHONE.matcher(phone).matches();
        if (valid != true)
            MainActivity.showToast(c, "Not a valid mobile number");
        return valid;
    }

    public static boolean isValidDate(Context c,String date) {
        DateValidator dateValidator = new DateValidator();
        boolean validity = dateValidator.validate(date);
        if (validity == false)
            MainActivity.showToast(c, "Invalid DOB");
        return validity;
    }

    public static String getDataForKey(String reqID) {

        String serverURL = "";

        serverURL = "http://ec2-54-158-110-110.compute-1.amazonaws.com:9090/";

        String data = "";
        switch (reqID) {
            case Constants.CREATE_CUSTOMER:
                data = serverURL + "cust";
                break;
            case Constants.UPDATE_BANK_DETAILS:
                data = serverURL + "bankDetails/17";
                break;
            case Constants.UPATE_PROFESSION_DETAILS:
                data = serverURL + "profession/18";
                break;
            default:
                break;
        }

        return data;

    }


}
