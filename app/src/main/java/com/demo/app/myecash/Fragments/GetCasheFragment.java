package com.demo.app.myecash.Fragments;

import android.app.Fragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.demo.app.myecash.ListViews.getCasheListView;
import com.demo.app.myecash.Adaptors.getCasheAdaptor;
import com.demo.app.myecash.R;

import java.util.ArrayList;

public class GetCasheFragment extends Fragment {

    ListView list;
    getCasheAdaptor adapter;
    public ArrayList<getCasheListView> CustomListViewValuesArr = new ArrayList<getCasheListView>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.activity_get_cashe, null);


        /******** Take some data in Arraylist ( CustomListViewValuesArr ) ***********/
        setListData();

        Resources res = getResources();
        list = (ListView) v.findViewById(R.id.list);  // List defined in XML ( See Below )

        /**************** Create Custom Adapter *********/
        adapter = new getCasheAdaptor(inflater, this, CustomListViewValuesArr, res);
        list.setAdapter(adapter);
        return v;
    }

    /****** Function to set data in ArrayList *************/
    public void setListData() {

        for (int i = 0; i < 11; i++) {

            final getCasheListView sched = new getCasheListView();

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
        getCasheListView tempValues = (getCasheListView) CustomListViewValuesArr.get(mPosition);


        // SHOW ALERT

//        Toast.makeText(CustomListView,
//                "" + tempValues.getTitle(), Toast.LENGTH_LONG)
//                .show();
    }
}
