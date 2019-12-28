package com.smparkworld.androidpractice14.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.smparkworld.androidpractice14.R;
import com.smparkworld.androidpractice14.dao.MemoAsynTask;
import com.smparkworld.androidpractice14.dto.Memo;

public class WriteActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        final EditText etName = findViewById(R.id.etName);
        final EditText etAddress = findViewById(R.id.etAddress);
        final EditText etMemo = findViewById(R.id.etMemo);

        Button btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (confirmData(etName, etAddress, etMemo)) {
                    Memo model = new Memo(
                            etName.getText().toString(),
                            etAddress.getText().toString(),
                            etMemo.getText().toString()
                    );

                    new MemoAsynTask(WriteActivity.this)
                            .executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "create", model);
                }
            }
        });

    }

    private boolean confirmData(EditText etName, EditText etAddress, EditText etMemo) {

        if (etName.getText().toString().equals("")) {
            Toast.makeText(this, "작성자를 입력해주세요.", Toast.LENGTH_SHORT).show();
            etName.requestFocus();
            return false;

        } else if (etAddress.getText().toString().equals("")) {
            Toast.makeText(this, "주소를 입력해주세요.", Toast.LENGTH_SHORT).show();
            etAddress.requestFocus();
            return false;

        } else if (etMemo.getText().toString().equals("")) {
            Toast.makeText(this, "메모를 입력해주세요.", Toast.LENGTH_SHORT).show();
            etMemo.requestFocus();
            return false;

        }

        return true;
    }

    /*
    private boolean confirmData(EditText... args) {

        for (EditText e : args) {
            if (e.getText().equals("")) {
                Toast.makeText(this, "값을 입력해주세요.", Toast.LENGTH_SHORT).show();
                e.requestFocus();
                return false;
            }
        }

        return true;
    }
    */
}
