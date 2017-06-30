package com.gaos.dialoglayout;

import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gaos.dialoglayout.customdialog.CustomDialog;
import com.vansuita.gaussianblur.GaussianBlur;

public class MainActivityStep2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        View decorView = getWindow().getDecorView();
//        decorView.setSystemUiVisibility(View.GONE);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main_step2);
        TextView tvShow3 = (TextView) findViewById(R.id.tv_show);
        final CustomDialog customDialog = new CustomDialog(MainActivityStep2.this);
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

                        /**
                         * Activity finish 时会将 dialog一同销毁。
                         * 不会发生所谓的dialog无attach Activity 而导致的崩溃异常发生。
                         *
                         */

                        customDialog.dismiss();
//                        finish();
                        DialogFlags.isActivityDestroy = true;
                    }
                });

                customDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0xffffffff));
            }
        });

        final TextView tvDestory = (TextView) findViewById(R.id.tv_destroy);
        tvDestory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
