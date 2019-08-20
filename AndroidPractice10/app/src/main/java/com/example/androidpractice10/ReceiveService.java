package com.example.androidpractice10;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class ReceiveService extends AsyncTask<Void, Void, String> {

    private Context context;
    private ArrayList<ItemModel> ItemList;
    private ListView lvResult;
    private String ErrorMessage;

    public ReceiveService(Context context, ArrayList<ItemModel> itemList, ListView lvResult) {
        this.context = context;
        this.ItemList = itemList;
        this.lvResult = lvResult;
        this.ErrorMessage = null;
    }

    @Override
    protected String doInBackground(Void... args) {
        try {
            String link = "http://smparkworld.com/AndroidPostTest_list.php";

            URL url = new URL(link);
            URLConnection conn = url.openConnection();

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            StringBuffer sb = new StringBuffer("");
            String line = "";

            while((line = br.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }

            br.close();
            return sb.toString();

        } catch (Exception e) {
            this.ErrorMessage = e.getMessage();
            return "Error";
        }
    }

    @Override
    protected void onPostExecute(String result) {
        if(!result.equals("Error")) {
            int fieldLine = 0;
            String fNo = null, fName = null, fContext = null;
            for(String item : result.split("_END_LINE_PSM_")) {
                for(String field : item.split("_DIVISION_FIELD_PSM_")) {
                    switch(fieldLine++) {
                        case 0: fNo = field; break;
                        case 1: fName = field; break;
                        case 2: fContext = field; break;
                    }
                }
                fieldLine = 0;
                this.ItemList.add(new ItemModel(fNo, fName, fContext));
            }
            ItemAdapter ia = new ItemAdapter(this.context, this.ItemList);
            Toast.makeText(this.context, "Successfully connect to the server.", Toast.LENGTH_SHORT).show();
            this.lvResult.setAdapter(ia);
        } else Toast.makeText(this.context, this.ErrorMessage, Toast.LENGTH_LONG).show();

    }
}
