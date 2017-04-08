package com.example.simplelife;
import java.util.Stack; 
/*
 *作用：将应用程序所有启动的Activity都添加到堆栈，最终退出应用程序时全部释放掉Activity 
 *在每个Activity中的onCreate()方法中添加该Activity到堆栈
 *在onDestory()方法中将该Activity从堆栈中移除*/
 
import android.app.Activity; 
import android.app.ActivityManager; 
import android.content.Context;
public class AppManager {

	private static Stack<Activity> activityStack; 
    private static AppManager instance; 
     
    private AppManager(){} 
    /**
     * 单一实例
     */ 
    public static AppManager getAppManager(){ 
        if(instance==null){ 
            instance=new AppManager(); 
        } 
        return instance; 
    } 
    /**
     * 添加Activity到堆栈
     */ 
    public void addActivity(Activity activity){ 
        if(activityStack==null){ 
            activityStack=new Stack<Activity>(); 
        } 
        activityStack.add(activity); 
    } 
    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */ 
    public Activity currentActivity(){ 
        Activity activity=activityStack.lastElement(); 
        return activity; 
    } 
    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */ 
    public void finishActivity(){ 
        Activity activity=activityStack.lastElement(); 
        if(activity!=null){ 
            activity.finish(); 
            activity=null; 
        } 
    } 
    /**
     * 结束指定的Activity
     */ 
    public void finishActivity(Activity activity){ 
        if(activity!=null){ 
            activityStack.remove(activity); 
            activity.finish(); 
            activity=null; 
        } 
    } 
    /**
     * 结束指定类名的Activity
     */ 
    public void finishActivity(Class<?> cls){ 
        for (Activity activity : activityStack) { 
            if(activity.getClass().equals(cls) ){ 
                finishActivity(activity); 
            } 
        } 
    } 
    /**
     * 结束所有Activity
     */ 
    public void finishAllActivity(){ 
        for (int i = 0, size = activityStack.size(); i < size; i++){ 
            if (null != activityStack.get(i)){ 
                activityStack.get(i).finish(); 
            } 
        } 
        activityStack.clear(); 
    } 
    /**
     * 退出应用程序
     */ 
    @SuppressWarnings("deprecation")
	public void AppExit(Context context) { 
        try { 
            finishAllActivity(); 
            ActivityManager activityMgr= (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE); 
            activityMgr.restartPackage(context.getPackageName()); 
            System.exit(0); 
        } catch (Exception e) { } 
    }
	

}
