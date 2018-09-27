package com.meethim.user.data.source.remote;

import android.app.Activity;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.avos.sns.SNS;
import com.avos.sns.SNSBase;
import com.avos.sns.SNSCallback;
import com.avos.sns.SNSException;
import com.avos.sns.SNSType;

/**
 * 登录辅助类，前置条件：需要现在application init青云
 * 登录结果返回：SNS.onActivityResult(requestCode, resultCode, data, type);
 *
 * @author wuzhao
 * @Date 2018/6/26
 * @mail： wuzhao@in66.com
 */
public class LoginSNS {
    public static void loginWithSina(Activity activity) {
        try {
            SNS.setupPlatform(SNSType.AVOSCloudSNSSinaWeibo,
                    "https://leancloud.cn/1.1/sns/goto/70uczc8byq0bgchy");
      /*
       * 这个url是在app下对应的组件中的SNS页面，在保存相应平台后的appId和app SecretId之后生成的登录URL
       * 同时，你需要将生成的回调URL，填写到相应平台的回调地址中间去
       */

            SNS.loginWithCallback(activity, SNSType.AVOSCloudSNSSinaWeibo,
                    new SNSCallback() {

                        @Override
                        public void done(SNSBase base, SNSException error) {
                            if (error == null) {
                                SNS.loginWithAuthData(base.userInfo(), new LogInCallback<AVUser>() {
                                    @Override
                                    public void done(AVUser user, AVException error) {

                                    }
                                });
                            }
                        }
                    });
        } catch (AVException e) {
            e.printStackTrace();
        }
    }

    public static void loginWithQq(final Activity activity) {
        try {
            SNS.setupPlatform(SNSType.AVOSCloudSNSQQ,
                    "https://leancloud.cn/1.1/sns/goto/kspiihdtpzn4186e");
      /*
       * 这个url是在app下对应的组件中的SNS页面，在保存相应平台后的appId和app SecretId之后生成的登录URL
       * 同时，你需要将生成的回调URL，填写到相应平台的回调地址中间去
       */

            SNS.loginWithCallback(activity, SNSType.AVOSCloudSNSQQ,
                    new SNSCallback() {

                        @Override
                        public void done(SNSBase base, SNSException error) {
                            if (error == null) {
                                SNS.loginWithAuthData(base.userInfo(), new LogInCallback<AVUser>() {
                                    @Override
                                    public void done(AVUser user, AVException error) {
                                        Toast.makeText(activity, "登录qq-done()", Toast.LENGTH_SHORT);
                                    }
                                });
                            }
                        }
                    });
        } catch (AVException e) {
            e.printStackTrace();
        }
    }

    public static void loginWithSinaSso(Activity activity) {
        try {
            SNS.setupPlatform(activity, SNSType.AVOSCloudSNSSinaWeibo, "YOUR_SINA_WEIBO_APP_ID",
                    "YOUR_SINA_WEIBO_APP_KEY",
                    "YOUR_SINA_WEIBO_CALLBACK_URL");

            SNS.loginWithCallback(activity, SNSType.AVOSCloudSNSSinaWeibo,
                    new SNSCallback() {

                        @Override
                        public void done(SNSBase base, SNSException error) {
                            if (error == null) {
                                SNS.loginWithAuthData(base.userInfo(), new LogInCallback<AVUser>() {
                                    @Override
                                    public void done(AVUser user, AVException error) {

                                    }
                                });
                            }
                        }
                    });
        } catch (AVException e) {
            e.printStackTrace();
        }
    }

    public static void loginWithQqSso(Activity activity) {
        try {
            SNS.setupPlatform(activity, SNSType.AVOSCloudSNSQQ, "YOUR_QQ_APP_ID",
                    "YOUR_QQ_APP_KEY",
                    "YOUR_QQ_CALLBACK_URL");

            SNS.loginWithCallback(activity, SNSType.AVOSCloudSNSQQ,
                    new SNSCallback() {

                        @Override
                        public void done(SNSBase base, SNSException error) {
                            if (error == null) {
                                SNS.loginWithAuthData(base.userInfo(), new LogInCallback<AVUser>() {
                                    @Override
                                    public void done(AVUser user, AVException error) {

                                    }
                                });
                            }
                        }
                    });
        } catch (AVException e) {
            e.printStackTrace();
        }
    }

}
