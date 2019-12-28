package com.smparkworld.androidpractice14.dao;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import com.smparkworld.androidpractice14.dto.Memo;
import com.smparkworld.androidpractice14.dto.MemoAdpater;

import java.util.ArrayList;

public class MemoAsynTask extends AsyncTask<Object, Void, String> {

    Context context = null;
    MemoDAOImpl dao = null;
    ArrayList<Memo> modelList = null;
    ListView lvMainList = null;

    public MemoAsynTask (Context context) {
        this.context = context;
    }

    public MemoAsynTask (Context context, ListView lvMainList) {
        this.context = context;
        this.lvMainList = lvMainList;
    }

    @Override
    public void onPreExecute() {
        this.dao = new MemoDAOImpl();
    }

    @Override
    public String doInBackground(Object... args) {

        String method = (String)args[0];

        if (method == null) return null;

        if (method.equals("readList")) {
            this.modelList = dao.readList();
        } else if (method.equals("create")) {
            dao.create((Memo)args[1]);
        }

        return method;
    }

    @Override
    public void onPostExecute(String method) {
        if (method != null && method.equals("readList") && this.modelList != null) {
            this.lvMainList.setAdapter(new MemoAdpater(this.context, this.modelList));
        } else if (method != null && method.equals("create")) {
            ((Activity)this.context).finish();
        }
    }
}
