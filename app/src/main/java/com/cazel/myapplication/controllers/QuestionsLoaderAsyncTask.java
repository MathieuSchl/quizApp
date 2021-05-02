package com.cazel.myapplication.controllers;

import android.os.AsyncTask;
import android.util.Log;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

public class QuestionsLoaderAsyncTask extends AsyncTask <String, Void,JSONObject> {


    public QuestionsLoaderAsyncTask(QuestionsLoaderAsyncInterface aListener) {
        this.aListener = aListener;
    }

    private QuestionsLoaderAsyncInterface aListener;


    @Override
    protected JSONObject doInBackground(String... strings) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(strings[0])
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject json = null;
        try {
            json = new JSONObject(response.body().string());
        } catch (JSONException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return json;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        super.onPostExecute(jsonObject);
        this.aListener.onFinish(jsonObject);
    }
}
