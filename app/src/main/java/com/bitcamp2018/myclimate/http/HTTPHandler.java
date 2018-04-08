package com.bitcamp2018.myclimate.http;

import android.util.JsonWriter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.PrivilegedActionException;

public class HTTPHandler  {

    // stuff for the HTTP requests
    private String API_KEY = "472bfa078574c632"; // our WUnderground API key
    private String REQUEST_URL_PREAMBLE_STRING_PRIMARY = "http://api.wunderground.com/api/";
    private String REQUEST_URL_PREAMBLE_STRING_SECONDARY = "/conditions/q/";
    private String FORMAT_EXTENSION = ".json";

    HttpURLConnection client;

    public HTTPHandler() {}

    public String makeRequest(String city, String state) throws IOException {

        String msg = new JSONObject().toString();

        try {
            URL url = new URL(REQUEST_URL_PREAMBLE_STRING_PRIMARY + API_KEY + REQUEST_URL_PREAMBLE_STRING_SECONDARY + "/" + state + "/" + city + FORMAT_EXTENSION);
            client = (HttpURLConnection) url.openConnection();
            client.setReadTimeout(10000);
            client.setConnectTimeout(15000);
            client.setRequestMethod("POST");
            client.setRequestProperty("Content-Type", "application/json");
            client.setRequestProperty("Accept","application/json");
            client.setDoOutput(true);
            client.connect();
        } catch (ProtocolException e1) {
            //Toast.makeText(this, e1.printStackTrace(), 10000);
        } catch (MalformedURLException e1) {
            //Toast.makeText(this, e1.printStackTrace(), 10000);
        } catch (IOException e1) {
            //Toast.makeText(this, e1.printStackTrace(), 10000);
        }

        OutputStream outputStream = client.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
        System.out.println("Connected");
        writer.write(msg);
        writer.flush();
        outputStream.close();
        writer.close();
        return msg;
    }

    public Object[] parseWUJson(JSONObject jo) {
        return null;
    }

    // Safe disconnect method
    private void disconnect(HttpURLConnection cli) {
        if(cli != null) {
            cli.disconnect();
        }
    }
}
