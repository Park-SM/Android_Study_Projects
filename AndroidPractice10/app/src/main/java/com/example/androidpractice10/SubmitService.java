package com.example.androidpractice10;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.Toast;

import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Scanner;

public class SubmitService extends AsyncTask<String, Void, String> {

    private Context context;
    private EditText etName;
    private EditText etContext;

    public SubmitService(Context context, EditText etName, EditText etContext) {
        this.context = context;
        this.etName = etName;
        this.etContext = etContext;
    }

    @Override
    protected String doInBackground(String... args) {
        try {
            String dataName = (String) args[0];
            String dataContext = (String) args[1];
            String link = "http://smparkworld.com/AndroidPostTest_insert.php";
            String data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(dataName, "UTF-8")
                    + "&" + URLEncoder.encode("context", "UTF-8") + "=" + URLEncoder.encode(dataContext, "UTF-8");

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
        if (result.equals("Success")) {
            Toast.makeText(this.context, "Successfully submit to the server.", Toast.LENGTH_SHORT).show();
            this.etContext.setText(null);
            this.etName.setText(null);
        } else Toast.makeText(this.context, result, Toast.LENGTH_LONG).show();
    }
}
