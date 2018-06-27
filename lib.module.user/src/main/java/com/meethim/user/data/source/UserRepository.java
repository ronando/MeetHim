package com.meethim.user.data.source;

import android.support.annotation.NonNull;

/**
 * $desc$
 *
 * @author wuzhao
 * @Date 2018/6/28
 * @mail： wuzhao@in66.com
 */
public class UserRepository implements UserDataSource {
    private UserDataSource mUserLocalDataSource;
    private UserDataSource mUserRemoteDataSource;


    private static UserRepository INSTANCE;


    private UserRepository(UserDataSource local, UserDataSource remote) {
        this.mUserLocalDataSource = local;
        this.mUserRemoteDataSource = remote;
    }


    public static UserRepository getInstance(@NonNull UserDataSource local, @NonNull UserDataSource remote) {
        if (INSTANCE == null) {
            INSTANCE = new UserRepository(local, remote);
        }

        return INSTANCE;
    }


    public static void destroyInstance() {
        INSTANCE = null;
    }




}
