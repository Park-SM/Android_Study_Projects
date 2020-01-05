package com.smparkworld.androidpractice15.Fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.smparkworld.androidpractice15.MemoModel;
import com.smparkworld.androidpractice15.R;

import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class FragmentB extends Fragment {

    private EditText etWriter;
    private EditText etTimeDate;
    private EditText etTimeTime;
    private EditText etContext;
    private CheckBox cbCurrentTime;
    private FragmentManager fragManager;
    private boolean firstFlag;

    public FragmentB(FragmentManager fragManager) {
        this.fragManager = fragManager;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_b, container, false);

        this.etWriter = v.findViewById(R.id.etNewWriter);
        this.etTimeDate = v.findViewById(R.id.etNewTimeData);
        this.etTimeTime = v.findViewById(R.id.etNewTimeTime);
        this.etContext = v.findViewById(R.id.etNewContext);
        this.cbCurrentTime = v.findViewById(R.id.cbCurrentTime);
        this.firstFlag = true;

        final DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                etTimeDate.setText(year + "." + monthOfYear + "." + dayOfMonth);
            }
        };

        final TimePickerDialog.OnTimeSetListener timeListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                etTimeTime.setText(hourOfDay + ":" + minute);
            }
        };

        v.findViewById(R.id.btnNewTimeData).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(getContext(), dateListener, 2020, 1, 1);
                dialog.show();
            }
        });

        v.findViewById(R.id.btnNewTimeTime).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog dialog = new TimePickerDialog(getContext(), timeListener, 0, 0, true);
                dialog.show();
            }
        });

        v.findViewById(R.id.btnNewSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cbCurrentTime.isChecked()) {
                    if (checkValue(etWriter, etContext)) {
                        SimpleDateFormat format = new SimpleDateFormat("yy.MM.dd HH:mm");
                        Date now = new Date();

                        MemoModel newMemo = new MemoModel(
                                etWriter.getText().toString(),
                                format.format(now),
                                etContext.getText().toString()
                        );
                        if (firstFlag) {
                            firstFlag = false;
                            new ServerTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, newMemo);
                        }
                    }
                } else {
                    if (checkValue(etWriter, etTimeDate, etTimeTime, etContext)) {
                        MemoModel newMemo = new MemoModel(
                                etWriter.getText().toString(),
                                etTimeDate.getText().toString() + " " + etTimeTime.getText().toString(),
                                etContext.getText().toString()
                        );
                        if (firstFlag) {
                            firstFlag = false;
                            new ServerTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, newMemo);
                        }
                    }
                }
            }
        });

        return v;
    }

    private boolean checkValue(EditText... args) {

        for (EditText v : args) {
            if (v.getText().toString().equals("")) {
                Toast.makeText(getContext(), "값을 입력해주세요", Toast.LENGTH_SHORT).show();
                v.requestFocus();
                return false;
            }
        }

        return true;
    }

    private class ServerTask extends AsyncTask<MemoModel, Void, String> {

        @Override
        protected String doInBackground(MemoModel... args) {

            String result = null;
            MemoModel m = args[0];

            try {
                String link = "http://smparkworld.com/AndroidMemoTest_insert.php";
                String data = URLDecoder.decode("writer", "UTF-8") + "=" + URLDecoder.decode(m.getWriter(), "UTF-8")
                        + "&" + URLDecoder.decode("time", "UTF-8") + "=" + URLDecoder.decode(m.getTime(), "UTF-8")
                        + "&" + URLDecoder.decode("context", "UTF-8") + "=" + URLDecoder.decode(m.getContext(), "UTF-8");

                URL url = new URL(link);
                URLConnection conn = url.openConnection();
                conn.setDoOutput(true);

                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
                osw.write(data);
                osw.close();

                Scanner sc = new Scanner(conn.getInputStream());
                result = sc.nextLine();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                return result;
            }

        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null && result.equals("Success")) {
                Toast.makeText(getContext(), "성공적으로 작성했습니다!", Toast.LENGTH_SHORT).show();
                fragManager.beginTransaction().replace(R.id.flMainFragLayout, new FragmentA()).commitAllowingStateLoss();
            }
        }

    }
}
