package com.example.androidpractice12;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class RecevieService extends AsyncTask<Integer, Void, String> {

    private Context context;
    private ListView lvContext;
    private ArrayList<Bitmap> list;

    public RecevieService(Context context, ListView lvContext, ArrayList<Bitmap> list) {
        this.context = context;
        this.lvContext = lvContext;
        this.list = list;
    }

    @Override
    protected String doInBackground(Integer... args) {
        try {
            int NumOfImage = (int)args[0];

            for (int i = 0; i < NumOfImage; i++) {
                String link = "http://smparkworld.com/Android_images/" + i + ".jpg";

                URL url = new URL(link);
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                conn.setDoInput(true);

                InputStream is = conn.getInputStream();
                Bitmap tempBit = BitmapFactory.decodeStream(is);
                this.list.add(tempBit);
            }

            return new String("Success: Receive images");
        } catch (Exception e) {
            return new String("Error: " + e.getMessage());
        }
    }

    @Override
    protected void onPostExecute(String result) {
        ItemAdapter la = new ItemAdapter(this.context, this.list);
        this.lvContext.setAdapter(la);
        Toast.makeText(this.context, result, Toast.LENGTH_LONG).show();
    }

}
