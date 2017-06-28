package com.gaos.dialoglayout.customdialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.gaos.dialoglayout.R;

/**
 * Author:　Created by benjamin
 * DATE :  2017/6/28 19:15
 * versionCode:　v2.2
 */

public class CustomDialog extends Dialog {

    private ImageView ivSample;
    private Animation enterAnimation;
    private Animation exitAnimation;
    private ViewGroup mRootView;

    public CustomDialog(Context context) {
        this(context, -1);
    }

    public CustomDialog(Context context, int themeResId) {
        super(context, R.style.Theme_AppCompat_Dialog_CustomDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customdialog_sample);

        WindowManager.LayoutParams attributes =
                getWindow().getAttributes();
        attributes.width = getContext().getResources().getDisplayMetrics().widthPixels;
        attributes.height = getContext().getResources().getDisplayMetrics().heightPixels;
        getWindow().setAttributes(attributes);

        initView();
    }

    private void initView() {
        ivSample = (ImageView) findViewById(R.id.iv_sample);
        enterAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.window_enter);
        exitAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.window_exit);

        mRootView = (LinearLayout) findViewById(R.id.ll_rootview);
//        mRootView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dismiss();
//            }
//        });
    }


    @Override
    public void show() {
        super.show();

        if (ivSample != null) {

            ivSample.clearAnimation();
            ivSample.setAnimation(enterAnimation);

            enterAnimation.start();

        }
    }

    @Override
    public void dismiss() {
        if (ivSample != null) {

            ivSample.clearAnimation();
            ivSample.setAnimation(exitAnimation);
            exitAnimation.start();
            exitAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    realDimiss();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        }
    }

    public void realDimiss() {
        super.dismiss();
    }

    public void setWindowBackground(Bitmap bg) {

        if (bg != null) {

            getWindow().setBackgroundDrawable(new BitmapDrawable(getContext().getResources(), bg));
        }
    }

    public ViewGroup getRootView() {

        return mRootView;
    }
}
