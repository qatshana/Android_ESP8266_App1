package com.example.abdel.esp8266_app1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.content.ContentValues.TAG;




public class MainActivity extends Activity {
    private TextView textView;
    String TAG="Trace 1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.TextView01);
    }

    private class DownloadWebPageTask extends AsyncTask<String, Void, String>{


        @Override
        protected String doInBackground(String... urls) {
            OkHttpClient client = new OkHttpClient();
            Request request=
                    new Request.Builder()
                            .url(urls[0])
                            .build();
            try {
                Response response= client.newCall(request).execute();
                return response.body().string();
            } catch (IOException e) {
                //return "Download Failed";
                e.printStackTrace();
            }

            return "Download Failed";
        }

        @Override
        protected void onPostExecute(String result) {
            textView.setText(result);
        }
    }

    public void onClickOff(View view) {
        Log.i(TAG,"Button Pressed");

        String url="http://192.168.4.1/OFF";
        DownloadWebPageTask task = new DownloadWebPageTask();
        task.execute(new String[] { url, "http://www.vogella.com/index.html" });

    }

    public void onClickOn(View view) {
        Log.i(TAG,"Button Pressed");
      
        String url="http://192.168.4.1/ON";
        DownloadWebPageTask task = new DownloadWebPageTask();
        task.execute(new String[] { url, "http://www.vogella.com/index.html" });

    }


}
