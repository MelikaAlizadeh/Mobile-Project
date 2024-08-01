package com.example.mobileproj;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtil {

    private static final String TAG = "NetworkUtils";

    public interface OnFetchCompleteListener {
        void onFetchComplete(JSONObject result);
        void onFetchFailed(Exception e);
    }

    public static void fetchJson(String urlString, OnFetchCompleteListener listener) {
        new FetchTask(listener).execute(urlString);
    }

    private static class FetchTask extends AsyncTask<String, Void, JSONObject> {
        private Exception exception;
        private final OnFetchCompleteListener listener;

        public FetchTask(OnFetchCompleteListener listener) {
            this.listener = listener;
        }

        @Override
        protected JSONObject doInBackground(String... params) {
            String urlString = params[0];
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(urlString);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");

                reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                return new JSONObject(response.toString());
            } catch (Exception e) {
                exception = e;
                Log.e(TAG, "Error during network operation", e);
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (Exception e) {
                        Log.e(TAG, "Error closing BufferedReader", e);
                    }
                }
            }
        }

        @Override
        protected void onPostExecute(JSONObject result) {
            if (exception != null) {
                listener.onFetchFailed(exception);
            } else {
                listener.onFetchComplete(result);
            }
        }
    }
}

