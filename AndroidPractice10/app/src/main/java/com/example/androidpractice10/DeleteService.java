package com.example.androidpractice10;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Scanner;

public class DeleteService extends AsyncTask<String, Void, String> {

    private Context context;

    public DeleteService(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... args) {
        try {
            String no = (String)args[0];
            String link = "http://smparkworld.com/AndroidPostTest_delete.php";
            String data = URLEncoder.encode("no", "UTF-8") + "=" + URLEncoder.encode(no, "UTF-8");

            URL url = new URL(link);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);

            OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
            osw.write(data);
            osw.flush();

            Scanner sc = new Scanner(conn.getInputStream());
            String result = sc.nextLine();

            return result;
        } catch (Exception e) {
            return new String("Error: " + e.getMessage());
        }
    }

    @Override
    protected void onPostExecute(String result) {
        if(result.equals("Success"))
            Toast.makeText(this.context, "Successfully deleted", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this.context, result, Toast.LENGTH_LONG).show();
    }
}
