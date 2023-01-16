package com.example.franquenstack.llamadasApi;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public abstract class LlamarApi {
    private String stringUrl;

    public String getStringUrl() {
        return stringUrl;
    }

    public void setStringUrl(String stringUrl) {
        this.stringUrl = stringUrl;
    }

}
