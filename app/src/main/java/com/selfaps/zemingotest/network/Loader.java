package com.selfaps.zemingotest.network;


import com.selfaps.zemingotest.model.ResultListener;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Loader {
    Thread thread;

    public Loader(String url, ResultListener listener) {
        thread = new Thread(new StringLoader(url, listener));
    }

    public void load() {
        thread.start();
    }

    public void interupt(){
        if(!thread.isInterrupted())
            thread.interrupt();
    }

    class StringLoader implements Runnable {
        private URL url;
        private ResultListener listener;

        public StringLoader(String url, ResultListener listener) {
            try {
                this.listener = listener;
                this.url = new URL(url);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            StringBuffer stringBuffer = new StringBuffer();
            HttpURLConnection connection = null;
            try {
                connection = (HttpURLConnection) url.openConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }


            try {

                String line;

                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = in.readLine()) != null) {
                    stringBuffer.append(line).append('\n');
               }
                String res = stringBuffer.toString();
                listener.onSucsess(res);
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                connection.disconnect();
            }

//            try  {
//                BufferedReader r = new BufferedReader(new InputStream(connection.getInputStream()));
//                String line;
//                while ((line = r.readLine()) != null) {
//                    stringBuffer.append(line).append('\n');
//                }
//                listener.onSucsess(stringBuffer.toString());
//                r.close();
//            } catch (IOException e) {
//                listener.onError(e);
//            }
        }
    }
}

