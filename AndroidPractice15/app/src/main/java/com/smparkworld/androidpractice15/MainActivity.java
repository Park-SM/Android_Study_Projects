package com.smparkworld.androidpractice15;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.smparkworld.androidpractice15.Fragments.FragmentA;
import com.smparkworld.androidpractice15.Fragments.FragmentB;

public class MainActivity extends AppCompatActivity {

    private Fragment memoListFrag;
    private Fragment memoNewFrag;
    private FragmentManager fragManager;
    private FragmentTransaction fragAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.fragManager = getSupportFragmentManager();
        this.fragAction = this.fragManager.beginTransaction();

        this.memoListFrag = new FragmentA();
        this.memoNewFrag = new FragmentB(this.fragManager);

        this.fragAction.replace(R.id.flMainFragLayout, this.memoListFrag).commitAllowingStateLoss();
    }

    public void clickEvent(View v) {

        this.fragAction = this.fragManager.beginTransaction();

        switch(v.getId()) {
            case R.id.btnMainList:
                this.fragAction.replace(R.id.flMainFragLayout, this.memoListFrag).commitAllowingStateLoss();
                break;

            case R.id.btnMainNew:
                this.fragAction.replace(R.id.flMainFragLayout, this.memoNewFrag).commitAllowingStateLoss();
                break;
        }
    }
}
