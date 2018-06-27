package com.meethim.user.data.source.remote;

import android.support.annotation.NonNull;
import android.util.Log;

import com.avos.avoscloud.AVAnonymousUtils;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVMobilePhoneVerifyCallback;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.avos.avoscloud.RequestMobileCodeCallback;
import com.avos.avoscloud.RequestPasswordResetCallback;
import com.avos.avoscloud.SignUpCallback;
import com.avos.avoscloud.UpdatePasswordCallback;
import com.meethim.user.data.source.UserDataSource;

/**
 * User 相关服务端api ， 基于lean cloud
 *
 * @author wuzhao
 * @Date 2018/6/28
 * @mail： wuzhao@in66.com
 */
public class UserRemoteDataSource implements UserDataSource {

    public static final String TAG = "UserRemoteDataSource";

    public AVUser getCurrentUser() {
        return AVUser.getCurrentUser();
    }

    public void logout() {
        AVUser user = AVUser.getCurrentUser();
        if (user != null) {
            AVUser.logOut();
        }
    }


    public void deleteCurrentUser() throws AVException {
        AVUser user = AVUser.getCurrentUser();
        if (user != null) {
            user.delete();
        }
    }

    public void signUp(@NonNull String username, @NonNull String password, @NonNull SignUpCallback callback) throws Exception {
        AVUser.logOut();
        final AVUser user = new AVUser();
        user.setUsername(username);
        user.setPassword(password);
        user.signUpInBackground(callback);
    }


    public void login(String username, String password, LogInCallback<AVUser> callback) {
        AVUser.logOut();
        AVUser.logInInBackground(username, password, callback);
    }

    public void updatePassword(String oldPsw, String newPsw) throws AVException {
        AVUser user = AVUser.getCurrentUser();
        if (user == null) {
            throw new AVException(AVException.NOT_INITIALIZED, "need login first!!!");
        }
        user.updatePassword(oldPsw, newPsw);
    }

    public void registerWithPhoneNumber(String fakeName, String fakePsw, String phoneNum, SignUpCallback callback) {
        // 请在网站勾选 "验证注册用户手机号码" 选项，否则不会发送验证短信
        final AVUser user = new AVUser();
        user.setUsername(fakeName);
        user.setPassword(fakePsw);
        user.setMobilePhoneNumber(phoneNum);
        user.signUpInBackground(callback);
    }

    public void verifyPhoneCode(String code, AVMobilePhoneVerifyCallback callback) {
        AVUser.verifyMobilePhoneInBackground(code, callback);
    }

    public void loginPhoneNumberAndPassword(String photoNumber, String password) {
        AVUser.loginByMobilePhoneNumberInBackground(photoNumber, password, new LogInCallback<AVUser>() {
            @Override
            public void done(AVUser avUser, AVException e) {
                if (filterException(e)) {
                    Log.d(TAG, "登录成功, user:" + avUser);
                }
            }
        });
    }

    public void loginPhoneNumberAndCode(String phoneNumber) {
        AVUser.requestLoginSmsCodeInBackground(phoneNumber, new RequestMobileCodeCallback() {
            @Override
            public void done(AVException e) {
                if (filterException(e)) {
                    Log.d(TAG, "验证码已发送，请输入验证码");
                }
            }
        });
    }

    public void verifySmsCode(String phoneNumber, String code) {
        AVUser.loginBySMSCodeInBackground(phoneNumber, code, new LogInCallback<AVUser>() {
            @Override
            public void done(AVUser avUser, AVException e) {
                if (filterException(e)) {
                    Log.d(TAG, "登录成功, user: " + avUser);
                }
            }
        });
    }


    public void resetPasswordBySmsCode(final String smsCode, final String newPassword) {
        AVUser.resetPasswordBySmsCodeInBackground(smsCode, newPassword, new UpdatePasswordCallback() {
            @Override
            public void done(AVException e) {
                if (filterException(e)) {
                    Log.d(TAG, "密码更改成功，新密码 " + newPassword);
                    Log.d(TAG, "试着用手机号和新密码登录吧");
                }
            }
        });
    }


    public void loginEmail(String email, String psw) {
        AVUser.logInInBackground(email, psw, new LogInCallback<AVUser>() {
            @Override
            public void done(AVUser avUser, AVException e) {
                if (filterException(e)) {
                    Log.d(TAG, "登录成功 user:" + avUser);
                }
            }
        });
    }

    public void resetPasswordEmail(final String email) {
        AVUser.requestPasswordResetInBackground(email, new RequestPasswordResetCallback() {
            @Override
            public void done(AVException e) {
                if (filterException(e)) {
                    Log.d(TAG, "重置密码的邮件已发送到邮箱 " + email);
                }
            }
        });
    }

    public void loginAnonymous() {
        AVAnonymousUtils.logIn(new LogInCallback<AVUser>() {
            @Override
            public void done(AVUser avUser, AVException e) {
                if (filterException(e)) {
                    Log.d(TAG, "创建了一个匿名用户并登录，user:" + avUser);
                }
            }
        });
    }

    private boolean filterException(Exception e) {
        if (e == null) {
            return true;
        } else {
            return false;
        }
    }

}
