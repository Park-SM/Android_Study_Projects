package com.example.androidpractice11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.net.Uri;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private ImageView ivContext;
    private Button btnGallery, btnSend;
    private static final int GET_FROM_GALLERY = 4146;
    private static String imagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.imagePath = null;
        this.ivContext = (ImageView)findViewById(R.id.ivContext);
        this.ivContext.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        this.btnGallery = (Button)findViewById(R.id.btnGallery);
        this.btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_PICK);
                i.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                i.setType("image/*");
                startActivityForResult(i, GET_FROM_GALLERY);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch(requestCode) {
                case GET_FROM_GALLERY:

                    this.imagePath = getRealPathFromURI(data.getData());

                    int degree = getExifOrientation(this.imagePath);
                    BitmapFactory.Options ops = new BitmapFactory.Options();
                    Bitmap newBitmap = BitmapFactory.decodeFile(this.imagePath, ops);
                    newBitmap = Resizing(newBitmap, 512, 512);
                    newBitmap = getRotatedBitmap(newBitmap, degree);


                    this.ivContext.setImageBitmap(newBitmap);

                    Toast.makeText(this, "Successfully set the image.\nPATH:: " + this.imagePath, Toast.LENGTH_LONG).show();
                    break;
            }
        }
    }

    public void SendImage(View view) {
        if (this.imagePath != null) {
            SubmitService ss = new SubmitService(MainActivity.this);
            ss.execute(this.imagePath.toString());
        } else Toast.makeText(this, "Failed To send to the server.", Toast.LENGTH_SHORT).show();
    }

    public Bitmap Resizing(Bitmap targetBitmap, int reqWidth, int reqHeight) {
        int width = targetBitmap.getWidth();
        int height = targetBitmap.getHeight();


        if (width > reqWidth || height > reqHeight) {
            while (width / 2 > reqWidth && height / 2 > reqHeight) {
                width /= 2;
                height /= 2;
            }
        }

        return Bitmap.createScaledBitmap(targetBitmap, width, height, true);
    }

    private int getExifOrientation(String filePath) {
        ExifInterface exif = null;

        try {
            exif = new ExifInterface(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (exif != null) {
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);

            if (orientation != -1) {
                switch (orientation) {
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        return 90;

                    case ExifInterface.ORIENTATION_ROTATE_180:
                        return 180;

                    case ExifInterface.ORIENTATION_ROTATE_270:
                        return 270;
                }
            }
        }

        return 0;
    }

    private Bitmap getRotatedBitmap(Bitmap bitmap, int degree) {
        if (degree != 0 && bitmap != null) {
            Matrix matrix = new Matrix();
            matrix.setRotate(degree, (float) bitmap.getWidth() / 2, (float) bitmap.getHeight() / 2);

            try {
                Bitmap tmpBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);

                if (bitmap != tmpBitmap) {
                    bitmap.recycle();
                    bitmap = tmpBitmap;
                }
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
            }
        }

        return bitmap;
    }



    private String getRealPathFromURI(Uri contentURI) {
        String result = "Result";
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentURI, proj, null, null, null);
        if (cursor == null) {
            result = contentURI.getPath();
        }
        int Column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        result = cursor.getString(Column_index);


        return result;
    }
}
