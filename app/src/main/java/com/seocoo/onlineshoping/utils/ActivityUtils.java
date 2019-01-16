package com.seocoo.onlineshoping.utils;

import android.app.Activity;

import java.util.Stack;

/**
 * desc:
 *
 * @author Bian
 * create at 2018/12/18
 */
public class ActivityUtils {

    private static ActivityUtils activityUtils;

    public synchronized static ActivityUtils getInstance() {
        if (activityUtils == null) {
            synchronized (ActivityUtils.class) {
                if (activityUtils == null) {
                    activityUtils = new ActivityUtils();
                }
            }
        }
        return activityUtils;
    }

    /**
     * 存储ActivityStack
     */
    private Stack<Activity> activityStack;

    /**
     * 将Act纳入推栈集合中
     *
     * @param act Act对象
     */
    public void addActivity(Activity act) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.add(act);
    }

    /**
     * 堆栈中移除
     *
     * @param act 指定Act对象
     */
    public void removeActivity(Activity act) {
        if (activityStack != null) {
            activityStack.remove(act);
        }
    }
    /**
     * 关闭界面并艺术
     *
     * @param act 指定Act对象
     */
    public void finishActivity(Activity act) {
        if (null != act && activityStack.contains(act)) {
            act.finish();
            activityStack.remove(act);
        }
    }

    /**
     * 除登录界面全部关闭
     */
    public void finishAllWithOutClass(Class<?> clazz) {
        for (int i = 0; i < activityStack.size(); i++) {
            if (activityStack.get(i).getClass().equals(clazz)) {
                continue;
            }
            activityStack.get(i).finish();
            removeActivity(activityStack.get(i));
            i--;
        }
    }
}
