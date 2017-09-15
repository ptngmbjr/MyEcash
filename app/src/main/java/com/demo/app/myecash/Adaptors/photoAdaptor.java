package com.demo.app.myecash.Adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.app.myecash.ListViews.moreListView;
import com.demo.app.myecash.R;

import java.util.ArrayList;

public class photoAdaptor extends ArrayAdapter {

    LayoutInflater inflater;

    public photoAdaptor(Context context, ArrayList items) {
        super(context, 0, items);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        moreListView cell = (moreListView) getItem(position);

        //If the cell is a section header we inflate the header layout
        if (cell.isSectionHeader()) {
            v = inflater.inflate(R.layout.photo_section_header, null);

            v.setClickable(false);

            TextView header = (TextView) v.findViewById(R.id.section_header);
            header.setText(cell.getName());


        } else {
            v = inflater.inflate(R.layout.photo_item_cell, null);
            TextView name = (TextView) v.findViewById(R.id.name);
            ImageView icon = (ImageView) v.findViewById(R.id.icon_image);

//            TextView category = (TextView) v.findViewById(R.id.category);

            name.setText(cell.getName());
            icon.setImageResource(
                    getContext().getResources().getIdentifier(
                            "com.demo.app.myecash:drawable/" + cell.getImage()
                            , null, null));


//            category.setText(cell.getCategory());

        }
        return v;
    }
}