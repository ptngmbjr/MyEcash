package com.demo.app.myecash;

import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.demo.app.myecash.Adaptors.profileAdaptor;
import com.demo.app.myecash.ListViews.profileListView;

import java.util.ArrayList;

public class ProfileActivity extends Fragment {

    ListView list;
    profileAdaptor adapter;
    public ProfileActivity CustomListView = null;
    public ArrayList<profileListView> CustomListViewValuesArr = new ArrayList<profileListView>();

    public ProfileActivity() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_profile, container, false);
    }


//        @Override
//    protected void onCreate(Bundle savedInstanceState) {
//
//
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_profile);
//
//        CustomListView = this;
//
//        /******** Take some data in Arraylist ( CustomListViewValuesArr ) ***********/
//        setListData();
//
//        Resources res = getResources();
//        list = (ListView) findViewById(R.id.list_profile);  // List defined in XML ( See Below )
//
//        /**************** Create Custom Adapter *********/
//        adapter = new profileAdaptor(CustomListView, CustomListViewValuesArr, res);
//        list.setAdapter(adapter);
//
//    }
//

    /****** Function to set data in ArrayList *************/

    public void setListData() {

        for (int i = 0; i < 11; i++) {

            final profileListView sched = new profileListView();

            /******* Firstly take data in model object ******/

            sched.setProfile_image("ic_profile.png");
            sched.setProfile_title("PERSONAL DETAILS");
            sched.setProfile_completeness("50% COMPLETE");

            /******** Take Model Object in ArrayList **********/
            CustomListViewValuesArr.add(sched);
        }

    }


    /*****************  This function used by adapter ****************/
    public void onItemClick(int mPosition) {
        profileListView tempValues = (profileListView) CustomListViewValuesArr.get(mPosition);

//        Toast.makeText(CustomListView,
//                "" + tempValues.getProfile_title(), Toast.LENGTH_LONG)
//                .show();
    }
}
