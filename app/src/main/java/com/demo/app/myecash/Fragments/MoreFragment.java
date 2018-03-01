package com.demo.app.myecash.Fragments;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.demo.app.myecash.Adaptors.getCasheAdaptor;
import com.demo.app.myecash.Adaptors.moreAdaptor;
import com.demo.app.myecash.ListViews.moreListView;
import com.demo.app.myecash.R;

import java.util.ArrayList;
import java.util.Collections;

import static android.R.id.list;

public class MoreFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.activity_more, null);


//        /******** Take some data in Arraylist ( CustomListViewValuesArr ) ***********/
//        setListData();
//
//        Resources res = getResources();
//        list = (ListView) v.findViewById(R.id.list);  // List defined in XML ( See Below )
//
//        /**************** Create Custom Adapter *********/
//        adapter = new getCasheAdaptor(inflater, this, CustomListViewValuesArr, res);
//        list.setAdapter(adapter);
//        return v;

        ArrayList items = new ArrayList();

        //Add fake data to our list - notice unsorted order
        items.add(new moreListView("ic_profile.png", "HELP DESK", "Actions", -1));
        items.add(new moreListView("ic_profile.png", "REFER & EARN", "Actions", -1));
        items.add(new moreListView("ic_profile.png", "ABOUT", "Info", -1));
        items.add(new moreListView("ic_profile.png", "HOW TO REPAY", "Info", -1));
        items.add(new moreListView("ic_profile.png", "SEE HOW CASHe WORKS?", "Info", -1));
        items.add(new moreListView("ic_profile.png", "LOGOUT", "Info", -1));

        ListView list = (ListView) v.findViewById(R.id.list_more);

        items = sortAndAddSections(items);

        moreAdaptor adapter = new moreAdaptor(inflater, this.getActivity().getApplicationContext(), items);
        list.setAdapter(adapter);

        return v;

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
                moreListView sectionCell = new moreListView(obj.getImage(), obj.getCategory(), null,obj.getID());
                sectionCell.setToSectionHeader();
                tempList.add(sectionCell);
                header = obj.getCategory();
            }
            tempList.add(itemList.get(i));
        }

        return tempList;
    }
}
