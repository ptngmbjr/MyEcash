package com.demo.app.myecash.Adaptors;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.demo.app.myecash.ListViews.getCasheLV;
import com.demo.app.myecash.R;

import java.util.ArrayList;

/**
 * Created by 123 on 8/30/2017.
 */

public class getCasheAdaptor extends BaseAdapter implements View.OnClickListener {

    /*********** Declare Used Variables *********/
    private Activity activity;
    private ArrayList data;
    private static LayoutInflater inflater = null;
    public Resources res;
    getCasheLV tempValues = null;
    int i = 0;

    /*************  CustomAdapter Constructor *****************/
    public getCasheAdaptor(Activity a, ArrayList d, Resources resLocal) {

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

        public LinearLayout headerLayout;
        public TextView titleTV;
        public Button applyBtn;
        public TextView amountDurationTV;
        public TextView amountTV;
        public TextView interestTV;
        public TextView tandcTV;
    }

    /****** Depends upon data size called for each row , Create each ListView row *****/
    public View getView(int position, View convertView, ViewGroup parent) {

        View vi = convertView;
        ViewHolder holder;

        if (convertView == null) {

            /****** Inflate tabitem.xml file for each row ( Defined below ) *******/
            vi = inflater.inflate(R.layout.cashecell, null);

            /****** View Holder Object to contain tabitem.xml file elements ******/

            holder = new ViewHolder();
            holder.titleTV = (TextView) vi.findViewById(R.id.titleTV);
            holder.amountDurationTV = (TextView) vi.findViewById(R.id.durationTV);
            holder.amountTV = (TextView) vi.findViewById(R.id.amountTV);
            holder.interestTV = (TextView) vi.findViewById(R.id.interestTV);
            holder.tandcTV = (TextView) vi.findViewById(R.id.tandcTV);

            /************  Set holder with LayoutInflater ************/
            vi.setTag(holder);
        } else
            holder = (ViewHolder) vi.getTag();

        if (data.size() <= 0) {
            holder.titleTV.setText("No Data");

        } else {
            /***** Get each Model object from Arraylist ********/
            tempValues = null;
            tempValues = (getCasheLV) data.get(position);

            /************  Set Model values in Holder elements ***********/

            holder.titleTV.setText(tempValues.getTitle());
            holder.amountDurationTV.setText(tempValues.getamtDuration());
            holder.amountTV.setText(tempValues.getAmount());
            holder.interestTV.setText(tempValues.getInterest());
            holder.tandcTV.setText(tempValues.getTandc());

//            holder.image.setImageResource(
//                    res.getIdentifier(
//                            "com.androidexample.customlistview:drawable/" + tempValues.getImage()
//                            , null, null));

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

