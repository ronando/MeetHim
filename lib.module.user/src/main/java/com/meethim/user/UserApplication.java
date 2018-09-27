package com.meethim.user;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;
import com.meethim.leanclound.Constants;
//TODO: should remove when merge modules into project
/**
 * $desc$
 *
 * @author wuzhao
 * @Date 2018/6/27
 * @mail： wuzhao@in66.com
 */

public class UserApplication extends Application {
    public static final boolean DEBUG = true;
    @Override
    public void onCreate() {
        super.onCreate();
        initLeancloud();
    }

    private void initLeancloud() {
        // 初始化参数依次为 this, AppId, AppKey
        AVOSCloud.initialize(this, Constants.LEAN_CLOUD_APPID, Constants.LEAN_CLOUD_CLIENT_KEY);
        AVOSCloud.setDebugLogEnabled(DEBUG);
    }

}
