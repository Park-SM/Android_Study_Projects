package com.example.androidpractice09;

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
    private ArrayList<ContextItem> list;
    private ListView lvContext;
    private String eMessage;

    public ReceiveService(Context context, ArrayList<ContextItem> list, ListView lvContext) {
        this.context = context;
        this.list = list;
        this.lvContext = lvContext;
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

            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }

            br.close();
            return sb.toString();

        } catch (Exception e) {
            this.eMessage = e.getMessage();
            return "Error";
        }
    }

    @Override
    protected void onPostExecute(String result) {
        if(!result.equals("Error")) {
            int i = 1;
            for (String tempStr : result.split("_END_LINE_PSM_")) {
                this.list.add(new ContextItem(Integer.toString(i++) + ": ", tempStr));
            }
            Toast.makeText(this.context, "Success for load data from the server", Toast.LENGTH_SHORT).show();
            this.lvContext.setAdapter(new ItemAdapter(this.context, this.list));

        } else Toast.makeText(this.context, "Error: " + this.eMessage, Toast.LENGTH_LONG).show();
    }

}
