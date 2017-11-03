package com.demo.app.myecash;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ap250532 on 10/4/2017.
 */

public class ApiCalls {

    private String API_URL = "http://121.241.245.37:8080/rest/api/";

    public class PostJsonRequests extends AsyncTask<List<NameValuePair>, Void, String> {
        @Override
        protected String doInBackground(List<NameValuePair>... nameValuePairs) {
            HttpClient httpclient = new DefaultHttpClient();

            HttpPost httppost = new HttpPost(API_URL);
            httppost.addHeader("content-type", "application/json");

            try {

                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs[0]));

                HttpResponse response = httpclient.execute(httppost);

                final HttpEntity responseEntity = response.getEntity();
                if (responseEntity != null) {

                    String _response = EntityUtils.toString(responseEntity); // content will be consume only once

                    Log.d("", "Accesstoken : " + _response);

                    return _response;
                }

            } catch (UnsupportedEncodingException e) {

            } catch (IOException e) {

            }

            return null;
        }

        protected void onPostExecute(String result) {

            String message = "An error has occurred.";

            boolean flag = true;

            if (result != null) {

                try {

                    final JSONObject jObject = new JSONObject(result);

                    if (jObject.has("access_token")) {
                        Log.e("XXX", " Json data " + jObject.getString("access_token"));
                        flag = false;
                    } else if (jObject.has("error_description"))
                        message = "The e-mail or password that was entered is incorrect. Please try again.";

                } catch (JSONException e) {

                }
            }

            if (flag == true) {

                final String errorMesg = message;

            }

        }
    }

}
