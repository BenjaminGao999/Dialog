package com.gaos.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

/**
 * Author:　Created by benjamin
 * DATE :  2017/6/28 16:30
 * versionCode:　v2.2
 */

public class CustomBgDialog extends Dialog {

    private Animation enterAnim;
    private Animation exitAnim;

    public CustomBgDialog(Context context) {
        this(context, -1);
    }

    public CustomBgDialog(Context context, int themeResId) {
        super(context, R.style.Theme_AppCompat_Dialog_CustomTheme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.dialog_example);

        WindowManager.LayoutParams attributes =
                getWindow().getAttributes();
        attributes.gravity = Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL;
        DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();
        attributes.width = metrics.widthPixels;
        attributes.height = metrics.heightPixels;
    }
}
