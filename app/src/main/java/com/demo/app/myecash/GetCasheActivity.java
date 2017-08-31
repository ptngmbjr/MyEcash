package com.demo.app.myecash;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.demo.app.myecash.ListViews.getCasheLV;
import com.demo.app.myecash.Adaptors.getCasheAdaptor;

import java.util.ArrayList;
import java.util.List;

public class getCasheActivity extends Activity {

    ListView list;
    getCasheAdaptor adapter;
    public getCasheActivity CustomListView = null;
    public ArrayList<getCasheLV> CustomListViewValuesArr = new ArrayList<getCasheLV>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_cashe);

        CustomListView = this;

        /******** Take some data in Arraylist ( CustomListViewValuesArr ) ***********/
        setListData();

        Resources res = getResources();
        list = (ListView) findViewById(R.id.list);  // List defined in XML ( See Below )

        /**************** Create Custom Adapter *********/
        adapter = new getCasheAdaptor(CustomListView, CustomListViewValuesArr, res);
        list.setAdapter(adapter);

    }

    /****** Function to set data in ArrayList *************/
    public void setListData() {

        for (int i = 0; i < 11; i++) {

            final getCasheLV sched = new getCasheLV();

            /******* Firstly take data in model object ******/

            sched.setTitle("CASHe " + i * 10);
            sched.setAmount((i * 10000) + "");
            sched.setamtDuration((i * 10) + "");
            sched.setInterest((i * 150 / 100) + "");
            sched.setTandc("This is all sample data for " + i * 10);
            /******** Take Model Object in ArrayList **********/
            CustomListViewValuesArr.add(sched);
        }

    }


    /*****************  This function used by adapter ****************/
    public void onItemClick(int mPosition) {
        getCasheLV tempValues = (getCasheLV) CustomListViewValuesArr.get(mPosition);


        // SHOW ALERT

        Toast.makeText(CustomListView,
                "" + tempValues.getTitle(), Toast.LENGTH_LONG)
                .show();
    }
}
