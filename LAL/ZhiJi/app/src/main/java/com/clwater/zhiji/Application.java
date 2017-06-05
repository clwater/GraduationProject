package com.clwater.zhiji;

/**
 * Created by yszsyf on 2017/5/22.
 */

public class Application extends android.app.Application {

    public static Application instance;

    public static Application getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

}
