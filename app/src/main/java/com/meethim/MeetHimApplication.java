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
public class MeetHimApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        initLeancloud();
    }

    private void initLeancloud() {
        // 初始化参数依次为 this, AppId, AppKey
        AVOSCloud.initialize(this,Constants.LEAN_CLOUD_APPID,Constants.LEAN_CLOUD_CLIENT_KEY);
        AVOSCloud.setDebugLogEnabled(true);
    }


}
