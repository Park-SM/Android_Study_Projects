package com.example.androidpractice12;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

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
                URLConnection conn = url.openConnection();

                Bitmap tempBit = BitmapFactory.decodeStream(conn.getInputStream());
                this.list.add(tempBit);
            }

            return new String("Success: Receive images");
        } catch (Exception e) {
            return new String("Error: " + e.getMessage());
        }
    }

    @Override
    protected void onPostExecute(String result) {
        this.list = ListReSizing(this.list, 512, 512);
        ItemAdapter la = new ItemAdapter(this.context, this.list);
        this.lvContext.setAdapter(la);
        Toast.makeText(this.context, result, Toast.LENGTH_SHORT).show();
    }

    public ArrayList<Bitmap> ListReSizing(ArrayList<Bitmap> list, int uWidth, int uHeight) {
        ArrayList<Bitmap> imageList = new ArrayList<Bitmap>();
        int targetWidth = 0;
        int targetHeight = 0;

        for (int i = 0; i < list.size(); i++) {
            targetWidth = list.get(i).getWidth();
            targetHeight = list.get(i).getHeight();
            while (targetWidth > uWidth && targetHeight > uHeight) {
                targetWidth /= 2;
                targetHeight /= 2;
            }
            imageList.add(Bitmap.createScaledBitmap(list.get(i), targetWidth, targetHeight, true));
        }

        return imageList;
    }

}
