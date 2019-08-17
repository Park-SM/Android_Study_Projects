package com.example.androidpractice04;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

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
    protected String doInBackground(String... args) {
        try {
            String userName = args[0];
            String userPass = args[1];
            String link = "http://smparkworld.com/AndroidTest.php?userName=" + userName + "&userPass=" + userPass;

            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            request.setURI(new URI(link));
            HttpResponse response = client.execute(request);

            BufferedReader bf = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            StringBuffer sb = new StringBuffer("");
            String line = "";

            while ((line = bf.readLine()) != null) {
                sb.append(line);
                //break;
            }

            bf.close();
            return sb.toString();

        } catch (Exception e){
            return new String("Error: " + e.getMessage());
        }

    }

    @Override
    public void onPostExecute(String result) {
        if (!result.equals("")) {
            this.tvLogStatus.setText("Login Success");
            this.tvRoleStatus.setText(result);
        } else {
            this.tvLogStatus.setText("Login fail");
            this.tvRoleStatus.setText("No sign");
        }
    }

}
