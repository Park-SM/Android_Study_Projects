package com.example.androidpractice09;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class SubmitService extends AsyncTask<String, Void, String> {

    private Context context;
    private EditText etContext;

    public SubmitService(Context context, EditText etContext) {
        this.context = context;
        this.etContext = etContext;
    }

    @Override
    protected void onPreExecute() {}

    @Override
    protected String doInBackground(String... args) {
        try {
            String userContext = (String) args[0];
            String link = "http://smparkworld.com/AndroidTest_insert.php";
            String data = URLEncoder.encode("context", "UTF-8") + "=" + URLEncoder.encode(userContext, "UTF-8");

            URL url = new URL(link);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);

            OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
            osw.write(data);
            osw.flush();

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            StringBuffer sb = new StringBuffer("");
            String line = "";

            while((line = br.readLine()) != null) sb.append(line);

            osw.close();
            br.close();
            return sb.toString();

        } catch (Exception e) {
            return new String("Error: " + e.getMessage());
        }

    }

    @Override
    protected void onPostExecute(String result) {
        if (result.equals("Success")) {
            this.etContext.setText(null);
            Toast.makeText(this.context, "Successfully submit", Toast.LENGTH_SHORT).show();
        } else Toast.makeText(this.context, "Failed to submit", Toast.LENGTH_SHORT).show();
    }
}
