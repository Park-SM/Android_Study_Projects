package com.smparkworld.androidpractice14.dao;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class DAOImpl implements DAO {

    private URLConnection conn = null;

    @Override
    public URLConnection getConnection(String link) {
        try {

            URL url = new URL(link);
            this.conn = url.openConnection();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return this.conn;
    }

}
