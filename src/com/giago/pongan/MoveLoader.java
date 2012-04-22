package com.giago.pongan;

import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

public class MoveLoader extends AsyncTaskLoader<Void> {
    private static final int CONNECTION_TIMEOUT = 100;
    private static final int READ_TIMEOUT = 100;
    String url;

    public MoveLoader(Context context, String url) {
        super(context);
        this.url = url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public Void loadInBackground() {
        sendMove(url);
        return null;
    }

    private void sendMove(String url) {
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setConnectTimeout(CONNECTION_TIMEOUT);
            conn.setReadTimeout(READ_TIMEOUT);
            conn.setRequestMethod("GET");
            // conn.setDoInput(true);
            conn.connect();
            conn.getResponseCode();
        } catch (Throwable fnfe) {
            fnfe.printStackTrace();
        } finally {
           
            if (conn != null)
                conn.disconnect();
        }
    }

}
