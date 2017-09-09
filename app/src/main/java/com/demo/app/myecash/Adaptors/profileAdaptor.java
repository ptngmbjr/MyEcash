package com.demo.app.myecash.Adaptors;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.app.myecash.ListViews.profileListView;
import com.demo.app.myecash.R;

import java.util.ArrayList;

/**
 * Created by 123 on 8/30/2017.
 */

public class profileAdaptor extends BaseAdapter implements View.OnClickListener {

    /*********** Declare Used Variables *********/
    private Activity activity;
    private ArrayList data;
    private static LayoutInflater inflater = null;
    public Resources res;
    profileListView tempValues = null;
    int i = 0;

    /*************  CustomAdapter Constructor *****************/
    public profileAdaptor(Activity a, ArrayList d, Resources resLocal) {

        /********** Take passed values **********/
        activity = a;
        data = d;
        res = resLocal;

        /***********  Layout inflator to call external xml layout () ***********/
        inflater = (LayoutInflater) activity.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    /******** What is the size of Passed Arraylist Size ************/
    public int getCount() {

        if (data.size() <= 0)
            return 1;
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    /********* Create a holder Class to contain inflated xml file elements *********/
    public static class ViewHolder {

        public ImageView icon_img;
        public TextView title;
        public TextView completeness;

    }

    /****** Depends upon data size called for each row , Create each ListView row *****/
    public View getView(int position, View convertView, ViewGroup parent) {

        View vi = convertView;
        ViewHolder holder;

        if (convertView == null) {

            /****** Inflate tabitem.xml file for each row ( Defined below ) *******/
            vi = inflater.inflate(R.layout.profilecell, null);

            /****** View Holder Object to contain tabitem.xml file elements ******/

            holder = new ViewHolder();
            holder.icon_img = (ImageView) vi.findViewById(R.id.icon_image);
            holder.title = (TextView) vi.findViewById(R.id.text_profile_title);
            holder.completeness = (TextView) vi.findViewById(R.id.text_profile_completness);

            /************  Set holder with LayoutInflater ************/
            vi.setTag(holder);
        } else
            holder = (ViewHolder) vi.getTag();

        if (data.size() <= 0) {
            holder.title.setText("No Data");

        } else {
            /***** Get each Model object from Arraylist ********/
            tempValues = null;
            tempValues = (profileListView) data.get(position);

            /************  Set Model values in Holder elements ***********/

          //  holder.icon_img.setImageDrawable(activity.getResources().getDrawable(R.drawable.ic_profile));
            holder.title.setText(tempValues.getProfile_title());
            holder.completeness.setText(tempValues.getProfile_completeness());

            holder.icon_img.setImageResource(
                    res.getIdentifier(
                            "com.demo.app.myecash:drawable/" + tempValues.getProfile_image()
                            , null, null));

            /******** Set Item Click Listner for LayoutInflater for each row *******/

            vi.setOnClickListener(new OnItemClickListener(position));
        }
        return vi;
    }

    @Override
    public void onClick(View v) {
        Log.v("CustomAdapter", "=====Row button clicked=====");
    }

    /********* Called when Item click in ListView ************/
    private class OnItemClickListener implements View.OnClickListener {
        private int mPosition;

        OnItemClickListener(int position) {
            mPosition = position;
        }

        @Override
        public void onClick(View arg0) {

//
//            CustomListViewAndroidExample sct = (CustomListViewAndroidExample) activity;
//
//            /****  Call  onItemClick Method inside CustomListViewAndroidExample Class ( See Below )****/
//
//            sct.onItemClick(mPosition);
        }
    }
}

