package com.gaos.dialoglayout;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gaos.dialoglayout.customdialog.CustomDialog;
import com.vansuita.gaussianblur.GaussianBlur;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tvShow3 = (TextView) findViewById(R.id.tv_show);
        final CustomDialog customDialog = new CustomDialog(MainActivity.this);
        tvShow3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.square, null);
//                if (bitmap != null) {
//                    customDialog.setWindowBackground(bitmap);
//                }

                Bitmap screenShot = takeScreenShot();
                if (screenShot != null) {
                    customDialog.setWindowBackground(screenShot);
                }
                customDialog.show();

                ((LinearLayout) customDialog.getRootView()).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        customDialog.dismiss();
                    }
                });

                customDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0xffffffff));
            }
        });
    }

    public Bitmap takeScreenShot() {

        View decorView = getWindow().getDecorView();

        decorView.setDrawingCacheEnabled(true);
        decorView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);
        decorView.buildDrawingCache();

        Bitmap drawingCache = Bitmap.createBitmap(decorView.getDrawingCache());
        if (drawingCache == null) return null;

        decorView.setDrawingCacheEnabled(false);
        decorView.destroyDrawingCache();


        Bitmap render = GaussianBlur
                .with(this)
                .size(600)
                .radius(10)
                .render(drawingCache);


        return render;
    }
}
