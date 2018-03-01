package com.demo.app.myecash.Helpers;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.demo.app.myecash.R;

import java.util.ArrayList;

/**
 * Created by Rolf Smit on 23-Aug-16.
 */
public class DialogHelper {

    private ProgressDialog progressDialog;
    private MyLoadingDialog myLoadingDialog;

    private final ArrayList<String> progressDialogKeys = new ArrayList<String>();
    private final ArrayList<String> myLoadingDialogKeys = new ArrayList<String>();

    private final Activity activity;

    public DialogHelper(Activity activity){
        this.activity = activity;
    }


    /**
     * Show a message dialog, with the specified properties. This will be done
     * on the UI-thread, so it can be called from another thread.
     *
     * @param titleResId
     * @param messageResId
     * @param buttonResId
     */
    public void showMessageDialog(final int titleResId, final int messageResId,
                                  final int buttonResId) {
        activity.runOnUiThread(new Runnable() {
            public void run() {
                new AlertDialog.Builder(activity)
                        .setTitle(titleResId)
                        .setMessage(messageResId)
                        .setNegativeButton(
                                activity.getString(buttonResId), null)
                        .show();
            }
        });
    }

    /**
     * Show a message dialog, with the specified properties. This will be done
     * on the UI-thread, so it can be called from another thread.
     *
     * @param title
     * @param message
     * @param buttonResId
     */
    public void showMessageDialog(final String title, final String message, final int buttonResId) {
        activity.runOnUiThread(new Runnable() {
            public void run() {
//                new AlertDialog.Builder(activity)
//                        .setTitle(title)
//                        .setMessage(message)
//                        .setNegativeButton(
//                                activity.getString(buttonResId), null)
//                        .show();

                final Dialog dialog = new Dialog(activity);
                dialog.setContentView(R.layout.activity_popup);
//                dialog.setTitle(title);

                // set the custom dialog components - text, image and button
                TextView titletext = (TextView) dialog.findViewById(R.id.title);
                titletext.setText(title);

                TextView messagetext = (TextView) dialog.findViewById(R.id.message);
                messagetext.setText(message);


                Button buttontxt = (Button) dialog.findViewById(R.id.dialogButton);
                buttontxt.setText(activity.getString(buttonResId));
                buttontxt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.setCancelable(false);

                dialog.show();


            }
        });
    }



    /**
     * Show a progress dialog, to indicate the app is loading something.This
     * will be done on the UI-thread, so it can be called from another thread.
     */
    public void showProgressDialog(final int titleResId, final int messageResId, final String key) {
        showProgressDialog(activity.getString(titleResId), activity.getString(messageResId), key);
    }

    /**
     * Show a progress dialog, to indicate the app is loading something.This
     * will be done on the UI-thread, so it can be called from another thread.
     */
    public void showProgressDialog(final String key) {
        activity.runOnUiThread(new Runnable() {
            public void run() {
                if (!progressDialogKeys.contains(key)) {
                    progressDialogKeys.add(key);

                    if (progressDialog == null) {
                        progressDialog = ProgressDialog.show(
                                activity, activity.getString(R.string.lidProgressTitle), activity.getString(R.string.lidProgressMessage),
                                true, false);
                    }
                }
            }
        });
    }

    /**
     * Dismiss the progressDialog shown with <code>showProgressDialog()</code>.
     * This will be done on the UI-thread, so it can be called from another
     * thread.
     */

//    public void showProgressDialog(final String title, final String message, final String key) {
//        activity.runOnUiThread(new Runnable() {
//            public void run() {
//                if (!progressDialogKeys.contains(key)) {
//                    progressDialogKeys.add(key);
//
//                    if (progressDialog == null) {
//                        progressDialog = ProgressDialog.show(
//                                activity, title, message,
//                                true, false);
//                    }
//                }
//            }
//        });
//    }
//
//
    public void showProgressDialog(final String title, final String message, final String key) {
        activity.runOnUiThread(new Runnable() {
            public void run() {
                if(!myLoadingDialogKeys.contains(key)){

                    myLoadingDialogKeys.add(key);
                    if (myLoadingDialog == null) {
                        myLoadingDialog=MyLoadingDialog.newInstance(title,message);

                        myLoadingDialog.show(activity.getFragmentManager(), "dialog");
                    }

                }
            }
        });
    }


    //    public void dismissProgressDialog(final String key) {
//        activity.runOnUiThread(new Runnable() {
//            public void run() {
//                if (progressDialogKeys.contains(key)) {
//                    progressDialogKeys.remove(key);
//
//                    if (progressDialog != null
//                            && progressDialogKeys.size() <= 0) {
//                        progressDialog.dismiss();
//                        progressDialog = null;
//                    }
//                }
//            }
//        });
//    }

    public void dismissProgressDialog(final String key) {
        activity.runOnUiThread(new Runnable() {
            public void run() {
                if(myLoadingDialogKeys.contains(key)) {
                    myLoadingDialogKeys.remove(key);
                    if (myLoadingDialog != null) {
                        myLoadingDialog.dismiss();
                        myLoadingDialog = null;
                    }
                }
                if (progressDialogKeys.contains(key)) {
                    progressDialogKeys.remove(key);

                    if (progressDialog != null
                            && progressDialogKeys.size() <= 0) {
                        progressDialog.dismiss();
                        progressDialog = null;
                    }
                }
            }
        });
    }



}
