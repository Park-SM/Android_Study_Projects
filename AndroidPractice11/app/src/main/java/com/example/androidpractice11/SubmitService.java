package com.example.androidpractice11;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class SubmitService extends AsyncTask<String, Void, String> {

    private Context context;

    public SubmitService(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... args) {
        try {
            String targetPath = (String) args[0];
            String link = "http://smparkworld.com/AndroidViewerTest_insertIMG.php";
            String lineEnd = "\n";
            String twoHyphens = "--";
            String boundary = "*****";

            File targetImage = new File(targetPath);
            FileInputStream fis = new FileInputStream(targetImage);

            URL url = new URL(link);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("ENCTYPE", "multipart/form-data");
            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
            conn.setRequestProperty("imageName", targetPath);

            DataOutputStream dos = new DataOutputStream(conn.getOutputStream());

            dos.writeBytes(twoHyphens + boundary + lineEnd);
            dos.writeBytes("Content-Disposition: form-data; name=\"imageName\";filename=\"" + targetPath + "\"" + lineEnd);
            dos.writeBytes(lineEnd);


            final int MAXBUF = 1 * 1024 * 1024;
            byte[] buffer = new byte[MAXBUF];
            int ReadSize, BufferSize, TargetSize;

            do {
                TargetSize = fis.available();
                BufferSize = Math.min(TargetSize, MAXBUF);
                ReadSize = fis.read(buffer, 0, BufferSize);
                dos.write(buffer, 0, BufferSize);
            } while(ReadSize > 0);
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

            Scanner sc = new Scanner(conn.getInputStream());
            String resString = sc.nextLine();

            return new String("Success: " + resString);
        } catch (Exception e) {
            return new String("Error: " + e.getMessage());
        }
    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
    }

}
