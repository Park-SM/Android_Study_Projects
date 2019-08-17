package com.example.androidpractice05;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class SignInActivity extends AsyncTask<String, Void, String> {
    private Context context;
    private TextView tvLogStatus;
    private TextView tvRoleStatus;

    public SignInActivity(Context context, TextView tvLogStatus, TextView tvRoleStatus) {
        this.context = context;
        this.tvLogStatus = tvLogStatus;
        this.tvRoleStatus = tvRoleStatus;
    }

    @Override
    protected void onPreExecute() {}

    @Override
    protected void onProgressUpdate(Void... values) { }

    @Override
    protected String doInBackground(String... args) {
        try {
            String userName = args[0];
            String userPass = args[1];
            String link = "http://smparkworld.com/AndroidTest.php";
            String data = URLEncoder.encode("userName", "UTF-8") + "=" + URLEncoder.encode(userName, "UTF-8");
            data += "&" + URLEncoder.encode("userPass", "UTF-8") + "=" + URLEncoder.encode(userPass, "UTF-8");

            URL url = new URL(link);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);

            OutputStreamWriter ow = new OutputStreamWriter(conn.getOutputStream());
            ow.write(data);
            ow.flush();

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            StringBuffer sb = new StringBuffer("");
            String line = "";

            while ((line = br.readLine()) != null) {
                sb.append(line);
                //break;
            }

            br.close();
            return sb.toString();


        } catch (Exception e) {
            return new String("Error:" + e.getMessage());
        }
    }

    @Override
    protected void onPostExecute(String result) {
        if (!result.equals("")) {
            this.tvLogStatus.setText("Login Success");
            this.tvRoleStatus.setText(result);
        } else {
            this.tvLogStatus.setText("Login fail");
            this.tvRoleStatus.setText("No sign");
        }
    }
}
