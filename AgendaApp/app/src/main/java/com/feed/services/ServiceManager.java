package com.feed.services;

import org.json.JSONArray;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.feed.services.UrlBuilder.ServiceMethod;

/**
 * ServiceManager is a class manager using for perform a call (request) to server by using a method , list of parameters , url of called resource and a callback
 */
public class ServiceManager {

    private Activity activity;
    private DownloadData download;

    public ServiceManager(Activity activity) {
        this.activity = activity;
    }

    public void call(ServiceMethod m, String urlOfResource, Object[] param, Callback c) {

        String url = m.getUrl(urlOfResource, param);
        download = new DownloadData(c, setConnection());
        download.execute(url);
    }

    private boolean setConnection() {
        ConnectivityManager connMgr = (ConnectivityManager) activity
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    public JSONArray getResult() {
        return download.getJSONObject();
    }
}