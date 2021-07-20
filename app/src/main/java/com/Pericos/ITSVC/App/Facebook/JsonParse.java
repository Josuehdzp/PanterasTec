package com.Pericos.ITSVC.App.Facebook;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

public class JsonParse {

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try  {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            return new JSONObject(jsonText);
        } finally {
            is.close();
        }
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String[] url) throws IOException, JSONException {
        InputStream is = new URL("https://graph.facebook.com/v3.2/me?fields=feed&access_token=EAACpeSbpCtABAA7csRLoAIqHquNpA5xWqK4JvUOnO5s0zff2DQH3lR4gkU7FQZBQZBfJp9IQlAmzMkLLQPEzGcMZAR7c3AZCvZCZCi9ZAFznVPHJZCRc9mi3W7FLb4ogMHc1PuZCI4sAPOSIELuwDP71ZBDtXRjg8d4ZCfkp6XLKmVQ5voCuIQ25igP1VMiyrjuhT248Az6WwD70AZDZD")
                .openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    public static void main(String[] args) throws IOException, JSONException {
        JSONObject json = readJsonFromUrl("https://graph.facebook.com/v3.2/me?fields=feed&access_token=EAACpeSbpCtABAA7csRLoAIqHquNpA5xWqK4JvUOnO5s0zff2DQH3lR4gkU7FQZBQZBfJp9IQlAmzMkLLQPEzGcMZAR7c3AZCvZCZCi9ZAFznVPHJZCRc9mi3W7FLb4ogMHc1PuZCI4sAPOSIELuwDP71ZBDtXRjg8d4ZCfkp6XLKmVQ5voCuIQ25igP1VMiyrjuhT248Az6WwD70AZDZD");
        System.out.println(json.toString());
        System.out.println(json.get("id"));
    }

}