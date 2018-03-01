package com.demo.app.myecash.Helpers;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.demo.app.myecash.R;

public class MyLoadingDialog extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setCancelable(false);
        return inflater.inflate(R.layout.progress_dialog, container, false);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    public static MyLoadingDialog newInstance(String title, String message) {

        Bundle args = new Bundle();

        MyLoadingDialog fragment = new MyLoadingDialog();
        args.putString("title", title);
        args.putString("message", message);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView title = (TextView) view.findViewById(R.id.progresstitle);
        TextView desc = (TextView) view.findViewById(R.id.progressmessage);

        String titlestr = getArguments().getString("title");
        String msgstr = getArguments().getString("message");

        title.setText(titlestr);
        desc.setText(msgstr);
    }
}

