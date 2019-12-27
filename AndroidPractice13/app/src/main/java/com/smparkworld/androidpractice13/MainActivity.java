package com.smparkworld.androidpractice13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.model.Image;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final int REQUEST_CODE_PICKER = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnShowImagePicker = findViewById(R.id.btnShowImagePicker);

        btnShowImagePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // this is simple image picker.
                //ImagePicker.create(MainActivity.this)
                        //.start(REQUEST_CODE_PICKER);

                // this is detail image picker.
                ImagePicker.create(MainActivity.this)
                        .returnAfterFirst(true) // set whether pick or camera action should return immediate result or not. For pick image only work on single mode
                        .folderMode(true) // folder mode (false by default)
                        .folderTitle("앨범을 선택해주세요") // folder selection title
                        .imageTitle("사진을 선택해주세요") // image selection title
                        .single() // single mode
                        .multi() // multi mode (default mode)
                        .limit(10) // max images can be selected (99 by default)
                        .showCamera(true) // show camera or not (true by default)
                        .imageDirectory("Camera") // directory name for captured image ("Camera" folder by default)
                        .start(REQUEST_CODE_PICKER); // start image picker activity with request code
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_PICKER && resultCode == RESULT_OK && data != null) {
            ArrayList<Image> images = (ArrayList<Image>) ImagePicker.getImages(data);

            Image targetImg = images.get(0);

            int degree = getExifOrientation(targetImg.getPath());
            Bitmap bit = BitmapFactory.decodeFile(targetImg.getPath());
            bit = resize(bit, 3000, 3000);
            bit = getRotatedBitmap(bit, degree);

            String name = images.get(0).getName();

            ImageView ivMainImage = findViewById(R.id.ivMainImage);
            ivMainImage.setImageBitmap(bit);

            TextView tvImagePath = findViewById(R.id.tvImagePath);
            tvImagePath.setText(name);

        }
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

    public Bitmap resize(Bitmap bit, int uWidth, int uHeight) {
        int cWidth = bit.getWidth();
        int cHeight = bit.getHeight();

        while (uWidth < cWidth || uHeight < cHeight) {
            cWidth /= 2;
            cHeight /= 2;
        }

        return Bitmap.createScaledBitmap(bit, cWidth, cHeight, true);
    }

}
