package com.demo.app.myecash.Fragments;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.demo.app.myecash.Adaptors.profileAdaptor;
import com.demo.app.myecash.BankDetailsActivity;
import com.demo.app.myecash.EmploymentDetailsActivity;
import com.demo.app.myecash.Enums.profileEnum;
import com.demo.app.myecash.ListViews.profileListView;
import com.demo.app.myecash.PersonalDetailsActivity;
import com.demo.app.myecash.PhotoProofsActivity;
import com.demo.app.myecash.R;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {

    ListView list;
    profileAdaptor adapter;
    public ProfileFragment CustomListView = null;
    public ArrayList<profileListView> CustomListViewValuesArr = new ArrayList<profileListView>();

    public ProfileFragment() {
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

        final profileListView sched = new profileListView();

        /******* Firstly take data in model object ******/

        sched.setProfile_image("ic_profile.png");
        sched.setProfile_title("PERSONAL DETAILS");
        sched.setProfile_completeness("50% COMPLETE");
        sched.setId(profileEnum.PERSONAL_DETAILS);

        /******** Take Model Object in ArrayList **********/
        CustomListViewValuesArr.add(sched);

        sched.setProfile_image("ic_business.png");
        sched.setProfile_title("EMPLOYMENT DETAILS");
        sched.setProfile_completeness("66% COMPLETE");
        sched.setId(profileEnum.EMPLOYEMENT_DETAILS);

        /******** Take Model Object in ArrayList **********/
        CustomListViewValuesArr.add(sched);

        sched.setProfile_image("ic_getcashe.png");
        sched.setProfile_title("BANK DETAILS");
        sched.setProfile_completeness("0% COMPLETE");
        sched.setId(profileEnum.BANK_DETAILS);

        /******** Take Model Object in ArrayList **********/
        CustomListViewValuesArr.add(sched);

        sched.setProfile_image("ic_photo.png");
        sched.setProfile_title("PHOTO PROOFS");
        sched.setProfile_completeness("20% COMPLETE");
        sched.setId(profileEnum.PHOTO_PROOFS);

        /******** Take Model Object in ArrayList **********/
        CustomListViewValuesArr.add(sched);

    }


    /*****************  This function used by adapter ****************/
    public void onItemClick(int mPosition) {
        profileListView tempValues = (profileListView) CustomListViewValuesArr.get(mPosition);

        Intent intent = null;

        switch (tempValues.getId()) {
            case PERSONAL_DETAILS:
                intent = new Intent(this.getContext(), PersonalDetailsActivity.class);
                break;

            case EMPLOYEMENT_DETAILS:
                intent = new Intent(this.getContext(), EmploymentDetailsActivity.class);
                break;

            case BANK_DETAILS:
                intent = new Intent(this.getContext(), BankDetailsActivity.class);
                break;

            case PHOTO_PROOFS:
                intent = new Intent(this.getContext(), PhotoProofsActivity.class);
                break;
        }
        if (intent != null)
            startActivity(intent);
    }
}
