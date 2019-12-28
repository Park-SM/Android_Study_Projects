package com.smparkworld.androidpractice14.dao;

import android.util.Log;

import com.smparkworld.androidpractice14.dto.Memo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Scanner;

public class MemoDAOImpl extends DAOImpl implements MemoDAO {

    private URLConnection conn = null;

    @Override
    public ArrayList<Memo> readList() {

        ArrayList<Memo> modelList = null;

        try {
            conn = getConnection("http://smparkworld.com/AndroidTest_memo_list.php");

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            StringBuffer sb = new StringBuffer();
            String line = null;

            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            JSONArray result = new JSONArray(sb.toString());

            modelList = new ArrayList<Memo>();
            for (int i = 0; i < result.length(); i++) {
                JSONObject model = result.getJSONObject(i);
                Memo newMemo = new Memo(
                        model.getString("name"),
                        model.getString("address"),
                        model.getString("memo")
                );
                modelList.add(newMemo);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException je) {
            je.printStackTrace();
        }

        return modelList;
    }

    @Override
    public int create(Memo m) {
        int ret = 0;

        try {
            conn = getConnection("http://smparkworld.com/AndroidTest_memo_insert.php");
            String data = URLDecoder.decode("name", "UTF-8") + "=" + URLDecoder.decode(m.getName(), "UTF-8")
                    + "&" +  URLDecoder.decode("address", "UTF-8") + "=" + URLDecoder.decode(m.getAddress(), "UTF-8")
                    + "&" +  URLDecoder.decode("memo", "UTF-8") + "=" + URLDecoder.decode(m.getMemo(), "UTF-8");

            conn.setDoOutput(true);
            OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
            osw.write(data);
            osw.close();

            /*
            Scanner sc = new Scanner(conn.getInputStream());
            Log.v("Test:", "Return string-" + sc.nextLine());
            */

            ret = 1;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ret;
    }
}
