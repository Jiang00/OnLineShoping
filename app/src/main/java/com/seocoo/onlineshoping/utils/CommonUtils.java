package com.seocoo.onlineshoping.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;


import com.seocoo.onlineshoping.base.BaseApp;
import com.seocoo.onlineshoping.constants.Constants;

import java.io.File;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * desc:
 *
 * @author Bian
 * create at 2018/12/17 17:00
 */
public class CommonUtils {


    /**
     * 将每三个数字加上逗号处理（通常使用金额方面的编辑）
     *
     * @param str 需要处理的字符串
     * @return 处理完之后的字符串
     */
    public static String formatMoney(String str) {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        return decimalFormat.format(Double.parseDouble(str));
    }

    /**
     * 2为有效数字
     */
    public static String formatDecimal(String str) {
        return new DecimalFormat("0.00").format(new BigDecimal(str));
    }

    public static String judgeKeyboardNum(EditText mTvMoney, String num) {
        String currentNumber = mTvMoney.getText().toString();
        if (currentNumber.contains(".") && num.equals(".")) {
            return null;
        }
        currentNumber = currentNumber + num;
        if (currentNumber.contains(".")) {
            if (currentNumber.length() - 1 - currentNumber.indexOf(".") > 2) {
                currentNumber = currentNumber.subSequence(0, currentNumber.indexOf(".") + 2 + 1).toString();
            }
        }
        if (currentNumber.startsWith("0") && currentNumber.length() > 1) {
            if (!".".equals(currentNumber.substring(1, 2))) {
                currentNumber = currentNumber.subSequence(0, 1).toString();
            }
        }
        return currentNumber;
    }

    public static boolean canInsertKeyboardNum(EditText mTvMoney, String num) {
        String currentNumber = mTvMoney.getText().toString();
        if (currentNumber.contains(".") && ".".equals(num)) {
            return false;
        }
        currentNumber = currentNumber + num;
        if (currentNumber.contains(".")) {
            if (currentNumber.length() - 1 - currentNumber.indexOf(".") > 2) {
                return false;
            }
        }
        if (currentNumber.startsWith("0") && currentNumber.length() > 1) {
            if (!".".equals(currentNumber.substring(1, 2))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 隐藏动画
     *
     * @param view 控件
     */
    public static void setGoneTrans(View view) {
        TranslateAnimation animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                1.0f);
        animation.setDuration(300);
        view.startAnimation(animation);
        view.setVisibility(View.GONE);
    }

    /**
     * 显示动画
     *
     * @param view 控件
     */
    public static void setVisibleTrans(View view) {
        TranslateAnimation animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        animation.setDuration(500);
        view.startAnimation(animation);
        view.setVisibility(View.VISIBLE);
    }

    /**
     * 检查是否有可用网络
     */
    static boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) BaseApp.getInstance().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        return connectivityManager.getActiveNetworkInfo() != null;
    }

    /**
     * 获取版本号
     */
    public static String getVersionName(Context context) {
        String verName = "";
        try {
            verName = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return verName;
    }

    /**
     * 获得屏幕宽度
     */
    public static int getScreenWidth(Context context) {
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        if (windowManager != null) {
            windowManager.getDefaultDisplay().getMetrics(metrics);
        }
        return metrics.widthPixels;
    }

    /**
     * 获得屏幕高度
     */
    public static int getScreenHeight(Context context) {
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        if (windowManager != null) {
            windowManager.getDefaultDisplay().getMetrics(metrics);
        }
        return metrics.heightPixels;
    }


    public static File getParentFile() {
        final File file = new File(Constants.DOWNLOAD_FILEPATH);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }


    /**
     * 安装Apk
     */
    public static void installApk(Context context, File apkFile) {
        if (apkFile != null) {
            Intent install = new Intent(Intent.ACTION_VIEW);
            Uri apkUri = null;
            //判断Android版本是否是Android7.0以上
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                install.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                apkUri = FileProvider.getUriForFile(context, "com.seocoo.retail.manager.provider", apkFile);
            } else {
                apkUri = Uri.fromFile(apkFile);
            }
            install.setDataAndType(apkUri, "application/vnd.android.package-archive");
            install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(install);
        }
    }

    public static String data2String(Date date, boolean isStart) {
        //获取默认选中的日期的年月日星期的值，并赋值
//        2018-12-14 00:00:00
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM", Locale.getDefault());
        String yearMon = simpleDateFormat.format(date);
        if (isStart) {
            return yearMon + "/01 00:00:00";
        } else {
            return yearMon + "/31 23:59:59";
        }
    }

    private final static String DEFAULT_FORMAT = "yyyy-MM-dd";

    public static String dateFormat(long time) {
        return toString(time, DEFAULT_FORMAT);
    }

    /**
     * 根据给定的格式化参数，将日期转换为字符串
     *
     * @param time       时间
     * @param dateFormat 格式
     * @return String
     */
    public static String toString(long time, String dateFormat) {
        DateFormat sdf = new SimpleDateFormat(dateFormat, Locale.getDefault());
        return sdf.format(time);
    }

}
