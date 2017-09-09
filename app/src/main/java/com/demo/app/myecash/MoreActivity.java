package com.demo.app.myecash;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.demo.app.myecash.Adaptors.moreAdaptor;
import com.demo.app.myecash.ListViews.moreListView;

import java.util.ArrayList;
import java.util.Collections;

public class MoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        ArrayList items = new ArrayList();

        //Add fake data to our list - notice unsorted order
        items.add(new moreListView("ic_profile.png","HELP DESK", "Actions"));
        items.add(new moreListView("ic_profile.png","REFER & EARN", "Actions"));
        items.add(new moreListView("ic_profile.png","ABOUT", "Info"));
        items.add(new moreListView("ic_profile.png","HOW TO REPAY", "Info"));
        items.add(new moreListView("ic_profile.png","SEE HOW CASHe WORKS?", "Info"));
        items.add(new moreListView("ic_profile.png","LOGOUT", "Info"));

        ListView list = (ListView) findViewById(R.id.list_more);

        items = sortAndAddSections(items);

        moreAdaptor adapter = new moreAdaptor(this, items);
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
                moreListView sectionCell = new moreListView(obj.getImage(),obj.getCategory(), null);
                sectionCell.setToSectionHeader();
                tempList.add(sectionCell);
                header = obj.getCategory();
            }
            tempList.add(itemList.get(i));
        }

        return tempList;
    }
}
