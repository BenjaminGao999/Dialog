package com.gaos.dialog;

import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }


    private void initView() {

        Button btnCus = (Button) findViewById(R.id.btn_custom_dialog);

        final CustomBgDialog customBgDialog = new CustomBgDialog(MainActivity.this);
        btnCus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                customBgDialog.show();
                customBgDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {

                        Log.e(TAG, "onDismiss: ");
                    }
                });
            }
        });

    }
}
