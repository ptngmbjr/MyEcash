package com.demo.app.myecash;

import android.os.AsyncTask;
import android.util.ArrayMap;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ap250532 on 12/22/2017.
 */

public class ServerCalls {

    private OnTaskCompleted listener;
    private String requestID;
    private boolean responseCodeResult;


    public interface OnTaskCompleted {
        void onTaskCompleted(JSONObject result, String requestID, boolean responseCode);
    }


    public class getApiCall extends AsyncTask<ArrayMap, Void, JSONObject> {


        public getApiCall(OnTaskCompleted listener1) {
            listener = listener1;
        }

        @Override
        protected JSONObject doInBackground(ArrayMap... params) {
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet();

            ArrayMap<String, String> map = params[0];

            try {

                request.setURI(new URI(map.get("URL").toString()));

                for (Map.Entry<String, String> entry : map.entrySet()) {

                    String key = entry.getKey();

                    if (key.equals("URL"))
                        continue;

                    if (key.equals("REQUEST_ID")) {
                        requestID = entry.getValue();
                        continue;
                    }

                    String value = entry.getValue();

                    request.addHeader(key, value);

                }

            } catch (URISyntaxException e) {

            }

            JSONObject jObject = null;

            try {

                HttpResponse response = client.execute(request);

                responseCodeResult = checkServerRespose(response.getStatusLine().getStatusCode());

                final HttpEntity responseEntity = response.getEntity();
                if (responseEntity != null) {

                    final String _response = "" + EntityUtils.toString(responseEntity); // content will be consume only once

                    Log.d("resp2", _response);

                    try {
                        jObject = new JSONObject(_response);

                    } catch (JSONException e) {

                    }
                }

            } catch (UnsupportedEncodingException e) {

            } catch (IOException e) {

            }

            return jObject;
        }

        protected void onPostExecute(JSONObject jObject) {
            listener.onTaskCompleted(jObject, requestID, responseCodeResult);
        }
    }

    public class postApiCall extends AsyncTask<ArrayMap, Void, JSONObject> {


        public postApiCall(OnTaskCompleted listener1) {
            listener = listener1;
        }

        @Override
        protected JSONObject doInBackground(ArrayMap... params) {

            //Pass URL,REQUEST_ID,HEADER,NAME_VAL_PAIR
            HttpClient httpclient = new DefaultHttpClient();

            ArrayMap<String, String> map = params[0];

            HttpPost httppost = new HttpPost(map.get("URL").toString());

            JSONObject jObject = null;

            JSONObject rawReq = new JSONObject();


            for (Map.Entry<String, String> entry : map.entrySet()) {

                String key = entry.getKey();

                if (key.equals("REQUEST_ID")) {
                    requestID = entry.getValue();
                    continue;
                }


                if (key.startsWith("HEADER")) {
                    String[] headerArray = entry.getValue().split("#~#", 2);
                    httppost.addHeader(headerArray[0], headerArray[1]);
                    continue;
                }


                if (key.startsWith("NAME_VAL_PAIR")) {
                    String[] nameValArray = entry.getValue().split("#~#", 2);
                    try {
                        rawReq.put(nameValArray[0], nameValArray[1]);
                    } catch (Exception ex) {

                    }
//                        nameValuePairs.add(new BasicNameValuePair(nameValArray[0], nameValArray[1]));
                    continue;
                }
            }


            try {

                httppost.setEntity(new StringEntity(rawReq.toString(), "UTF8"));

                HttpResponse response = httpclient.execute(httppost);

                responseCodeResult = checkServerRespose(response.getStatusLine().getStatusCode());

                final HttpEntity responseEntity = response.getEntity();
                if (responseEntity != null) {

                    String _response = EntityUtils.toString(responseEntity); // content will be consume only once

                    try {
                        jObject = new JSONObject(_response);

                    } catch (JSONException e) {

                    }
                }

            } catch (UnsupportedEncodingException e) {

                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return jObject;
        }

        protected void onPostExecute(JSONObject jObject) {
            listener.onTaskCompleted(jObject, requestID, responseCodeResult);
        }
    }

    private boolean checkServerRespose(int code) {
        boolean res = false;

        switch (code) {
            case HttpURLConnection.HTTP_OK:
            case HttpURLConnection.HTTP_CREATED:
                res = true;
                break;
            default:
                break;
        }

        return res;
    }

}

