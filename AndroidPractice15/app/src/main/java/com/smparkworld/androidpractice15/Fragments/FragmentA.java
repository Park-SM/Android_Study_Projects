package com.smparkworld.androidpractice15.Fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.smparkworld.androidpractice15.MainAdapter;
import com.smparkworld.androidpractice15.MemoModel;
import com.smparkworld.androidpractice15.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class FragmentA extends Fragment {

    private ListView lvMemoList;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_a, container, false);

        this.lvMemoList = v.findViewById(R.id.lvMemoList);
        new ServerTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        return v;
    }

    private class ServerTask extends AsyncTask<Void, Void, ArrayList<MemoModel>> {

        @Override
        protected ArrayList<MemoModel> doInBackground(Void... args) {

            ArrayList<MemoModel> modelList = null;

            try {
                String link = "http://smparkworld.com/AndroidMemoTest_list.php";

                URL url = new URL(link);
                URLConnection conn = url.openConnection();

                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuffer sb = new StringBuffer();
                String line = null;

                while ((line = br.readLine()) != null) sb.append(line);
                if (sb.toString().equals("Error")) return null;

                modelList = new ArrayList<MemoModel>();
                JSONArray receiveData = new JSONArray(sb.toString());
                for (int i = 0; i < receiveData.length(); i++) {
                    JSONObject target = (JSONObject) receiveData.get(i);

                    modelList.add(new MemoModel(
                            target.getString("writer"),
                            target.getString("time"),
                            target.getString("context")
                    ));
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException je) {
                je.printStackTrace();
            } finally {
                return modelList;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<MemoModel> result) {
            if (result != null) {
                lvMemoList.setAdapter(new MainAdapter(getContext(), result));
            } else {
                Toast.makeText(getContext(), "Error: Failed to connect to server", Toast.LENGTH_SHORT).show();
            }
        }

    }

}
