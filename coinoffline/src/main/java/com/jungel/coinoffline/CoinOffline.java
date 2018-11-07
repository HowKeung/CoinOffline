package com.jungel.coinoffline;

import android.content.Context;

public class CoinOffline {

    private static Context mContext;

    public static Context getContext() {
        if (mContext == null) {
            throw new RuntimeException("must call init first");
        }
        return mContext;
    }

    public static void init(Context context) {
        mContext = context;
    }
}
