package com.smparkworld.androidpractice14.dao;

import com.smparkworld.androidpractice14.dto.Memo;

import java.util.ArrayList;

public interface MemoDAO {
    ArrayList<Memo> readList();
    int create(Memo m);
}
