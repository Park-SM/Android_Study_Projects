package com.example.androidpractice06;

import android.content.Context;
import android.os.AsyncTask;
import android.text.Layout;
import android.view.Gravity;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class ReceiveActivity extends AsyncTask<Void, Void, String> {

    private LinearLayout ll;
    private Context context;

    public ReceiveActivity(LinearLayout ll, Context context) {
        this.ll = ll;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {}

    @Override
    protected String doInBackground(Void... values) {

        try {
            String link = "http://smparkworld.com/AndroidTest_list.php";

            URL url = new URL(link);
            URLConnection conn = url.openConnection();

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            StringBuffer sb = new StringBuffer("");
            String line = "";
            while ((line = br.readLine()) != null) sb.append(line);

            br.close();
            return sb.toString();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        if (!result.isEmpty()) {
            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            param.topMargin = 2;
            for (String t : result.split("<br>")) {
                TextView tvItem = new TextView(this.context);
                tvItem.setLayoutParams(param);
                tvItem.setWidth(ll.getWidth());
                tvItem.setText(t);
                tvItem.setGravity(Gravity.CENTER_VERTICAL);
                tvItem.setBackgroundResource(R.drawable.edge);
                ll.addView(tvItem);
            }
        }
    }
}
