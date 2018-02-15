package com.udacity.sandwichclub;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Sebastian Witasik on 15.02.2018.
 * E-mail: Sebastian Witasik
 * All rights reserved & copyright ©
 */

public class ImageDownloaderTask extends AsyncTask<String, Void, Bitmap> {

    private Callback mCallback = null;
    private int mStatusCode = -1;
    private Exception mException = null;

    public ImageDownloaderTask setCallback(Callback callback){
        mCallback = callback;
        return this;
    }

    @Override
    public Bitmap doInBackground(String... params) {
        return downloadBitmap(params[0]);
    }

    private Bitmap downloadBitmap(String src) {
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(src);
            urlConnection = (HttpURLConnection) url.openConnection();

            mStatusCode = urlConnection.getResponseCode();

            InputStream inputStream = urlConnection.getInputStream();
            if (inputStream != null) {
                return BitmapFactory.decodeStream(inputStream);
            } else {
                mException = new InvalidInputStreamException();
            }
        } catch (Exception e) {
            mException = e;
            Log.d(this.getClass().getName(), e.toString());
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            Log.w(this.getClass().getName(), "Error downloading image from " + src);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return null;
    }

    protected void onPostExecute(Bitmap result) {
        if(mException ==null) {
            mCallback.downloadFinished(result, mStatusCode);
        } else {
            mCallback.downloadFinished(mException);
        }
    }

    public interface Callback {
        void downloadFinished(Bitmap bm, int statusCode);
        void downloadFinished(Exception exception);
    }
}