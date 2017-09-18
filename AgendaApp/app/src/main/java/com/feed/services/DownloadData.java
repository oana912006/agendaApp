package com.feed.services;

import android.os.AsyncTask;
import android.util.Log;

import com.feed.utils.FeedConstants;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;

/**
 * AsyncTask class that is used for downloading data
 */
public class DownloadData extends AsyncTask<String, Void, StringBuilder> {

    private final static String LINE_SEPARATOR = System.getProperty("line.separator");
    private JSONArray jsonObject;
    private Callback c;
    private boolean connection;

    /**
     * Specific error for no internet connection
     */
    public final static String error100 = "No internet connection !";

    public DownloadData(Callback c, boolean connection) {
        this.c = c;
        this.connection = connection;
    }

    @Override
    protected StringBuilder doInBackground(String... urls) {
        if (connection) {
            StringBuilder stringBuilder = new StringBuilder();

            try {


                HttpsURLConnection httpUrlConnection = null;
                HostnameVerifier verifier = new HostnameVerifier() {
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                };
                httpUrlConnection.setDefaultHostnameVerifier(verifier);
                SSLContext context = SSLContext.getInstance("TLS");
                context.init(null, new X509TrustManager[]{new X509TrustManager() {
                    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    }

                    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    }

                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[0];
                    }
                }}, new SecureRandom());
                httpUrlConnection.setDefaultSSLSocketFactory(context.getSocketFactory());


                URL url = new URL(urls[0]);
                httpUrlConnection = (HttpsURLConnection) url.openConnection();

                httpUrlConnection.setRequestMethod("GET");
                httpUrlConnection.setRequestProperty("Connection", "Keep-Alive");
                httpUrlConnection.setRequestProperty("Cache-Control", "no-cache");


                InputStream responseStream = new
                        BufferedInputStream(httpUrlConnection.getInputStream());

                BufferedReader responseStreamReader =
                        new BufferedReader(new InputStreamReader(responseStream));

                String line = "";

                while ((line = responseStreamReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                    System.out.println(line);
                }

                responseStreamReader.close();
                responseStream.close();
                httpUrlConnection.disconnect();

            } catch (KeyManagementException e) {
                Log.e(FeedConstants.DEBUG_TAG, e.getMessage());

            } catch (NoSuchAlgorithmException e) {
                Log.e(FeedConstants.DEBUG_TAG, e.getMessage());

            } catch (MalformedURLException e) {
                Log.e(FeedConstants.DEBUG_TAG, e.getMessage());

            } catch (IOException e) {
                Log.e(FeedConstants.DEBUG_TAG, e.getMessage());

            }

            return stringBuilder;

        }
        return null;
    }

    @Override
    protected void onPostExecute(StringBuilder result) {
        try {
            if (!connection) {
                c.onError(new Exception(error100));
            } else {
                String newResult = result.toString();
                if (result.charAt(0) != '[') {
                    newResult = "[".concat(result.toString()).concat("]");
                }
                jsonObject = new JSONArray(newResult);
                c.finished(newResult);
            }
        } catch (JSONException e) {

            //handle JSONException
            Log.e(FeedConstants.DEBUG_TAG, e.getMessage());
        }
    }

    public JSONArray getJSONObject() {
        return this.jsonObject;
    }
}
