package com.example.androidpractice11;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

public class SubmitService extends AsyncTask<String, Void, Void> {

    private Context context;

    public SubmitService(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(String... args) {



        return null;
    }

    @Override
    protected void onPostExecute(Void values) {
        Toast.makeText(context,"Test toast", Toast.LENGTH_SHORT).show();
    }

}
