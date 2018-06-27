package com.meethim;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;

/**
 * Project application
 *
 * @author wuzhao
 * @Date 2018/6/20
 * @mail： wuzhao@in66.com
 */
public class MeetHimApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initLeancloud();
    }

    private void initLeancloud() {
        // 初始化参数依次为 this, AppId, AppKey
        AVOSCloud.initialize(this, com.meethim.leanclound.Constants.LEAN_CLOUD_APPID, com.meethim.leanclound.Constants.LEAN_CLOUD_CLIENT_KEY);
        AVOSCloud.setDebugLogEnabled(Constants.DEBUG);
    }


}
