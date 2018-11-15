package com.jungel.coinoffline;

import android.content.Context;

import com.jungel.coinoffline.eth.ETHUrlHelper;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jFactory;
import org.web3j.protocol.http.HttpService;

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

    public static void main(String[] args) {

    }
}
