package com.young.java.examples.images.similarity;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.io.IOUtils;

/**
 * @author shazam
 * @DATE 2018/4/24
 */
public class HttpDownloader {

    private static final int HTTP_OK = 200;

    public static InputStream download(String urlPath) {
        try {
            URL url = new URL(urlPath);
            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setConnectTimeout(5000);
            urlConnection.setReadTimeout(5000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            int code = urlConnection.getResponseCode();
            if (code == HTTP_OK) {
                return urlConnection.getInputStream();
            } else {
                throw new IOException("get url error code is " + code);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) throws IOException {
        String url = "https://img3.doubanio.com/view/photo/l/public/p2219237286.webp";
        IOUtils.copy(HttpDownloader.download(url),new FileOutputStream("1.jpg"));
    }
}
