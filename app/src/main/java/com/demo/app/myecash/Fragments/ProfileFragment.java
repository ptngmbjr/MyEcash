package com.demo.app.myecash.Fragments;

import android.app.Fragment;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
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
    public ArrayList<profileListView> CustomListViewValuesArr = new ArrayList<profileListView>();

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity_profile, container, false);

        setListData();

        Resources res = getResources();
        list = (ListView) v.findViewById(R.id.list_profile);  // List defined in XML ( See Below )

        /**************** Create Custom Adapter *********/
        adapter = new profileAdaptor(this, inflater, CustomListViewValuesArr, res);
        list.setAdapter(adapter);

        return v;

    }


    public void setListData() {
        createDataObject("ic_profile.png", "PERSONAL DETAILS", "50% COMPLETE", profileEnum.PERSONAL_DETAILS);
        createDataObject("ic_business.png", "EMPLOYMENT DETAILS", "66% COMPLETE", profileEnum.EMPLOYEMENT_DETAILS);
        createDataObject("ic_getcashe.png", "BANK DETAILS", "0% COMPLETE", profileEnum.BANK_DETAILS);
        createDataObject("ic_photo.png", "PHOTO PROOFS", "20% COMPLETE", profileEnum.PHOTO_PROOFS);
    }

    private void createDataObject(String image, String title, String completeness, profileEnum id) {
        profileListView sched = new profileListView();

        /******* Firstly take data in model object ******/

        sched.setProfile_image(image);
        sched.setProfile_title(title);
        sched.setProfile_completeness(completeness);
        sched.setId(id);

        CustomListViewValuesArr.add(sched);

    }

    /*****************  This function used by adapter ****************/
    public void onItemClick(int mPosition) {
        profileListView tempValues = (profileListView) CustomListViewValuesArr.get(mPosition);

        Intent intent = null;

        switch (tempValues.getId()) {
            case PERSONAL_DETAILS:
                intent = new Intent(this.getActivity().getApplicationContext(), PersonalDetailsActivity.class);
                break;

            case EMPLOYEMENT_DETAILS:
                intent = new Intent(this.getActivity().getApplicationContext(), EmploymentDetailsActivity.class);
                break;

            case BANK_DETAILS:
                intent = new Intent(this.getActivity().getApplicationContext(), BankDetailsActivity.class);
                break;

            case PHOTO_PROOFS:
                intent = new Intent(this.getActivity().getApplicationContext(), PhotoProofsActivity.class);
                break;
        }
        if (intent != null)
            startActivity(intent);
    }

}
