package com.demo.app.myecash.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.demo.app.myecash.Adaptors.moreAdaptor;
import com.demo.app.myecash.Adaptors.photoAdaptor;
import com.demo.app.myecash.ListViews.moreListView;
import com.demo.app.myecash.R;

import java.util.ArrayList;
import java.util.Collections;

public class PhotoProofsActivity extends AppCompatActivity {

    public PhotoProofsActivity() {
        // Required empty public constructor
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_proofs);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ArrayList items = new ArrayList();

        //Add fake data to our list - notice unsorted order
        items.add(new moreListView("ic_profile.png", "Salary Slip", "BANK PROOF", 0));
        items.add(new moreListView("ic_profile.png", "Bank Statements", "BANK PROOF", 1));
        items.add(new moreListView("ic_profile.png", "Additional Proofs", "RESIDENTIAL PROOF", 5));
        items.add(new moreListView("ic_profile.png", "Photo", "IDENTITY PROOF", 2));
        items.add(new moreListView("ic_profile.png", "PAN Card", "IDENTITY PROOF", 3));
        items.add(new moreListView("ic_profile.png", "Employee Badge", "IDENTITY PROOF", 4));

        ListView list = (ListView) findViewById(R.id.list_photos);

        items = sortAndAddSections(items);

        photoAdaptor adapter = new photoAdaptor(this, items);
        list.setAdapter(adapter);
    }

    private ArrayList sortAndAddSections(ArrayList itemList) {

        ArrayList tempList = new ArrayList();
        //First we sort the array
        Collections.sort(itemList);

        //Loops thorugh the list and add a section before each sectioncell start
        String header = "";
        for (int i = 0; i < itemList.size(); i++) {
            moreListView obj = (moreListView) itemList.get(i);

            //If it is the start of a new section we create a new moreListView and add it to our array
            if (header != obj.getCategory()) {
                moreListView sectionCell = new moreListView(obj.getImage(), obj.getCategory(), null, obj.getID());
                sectionCell.setToSectionHeader();
                tempList.add(sectionCell);
                header = obj.getCategory();
            }
            tempList.add(itemList.get(i));
        }

        return tempList;
    }


}
