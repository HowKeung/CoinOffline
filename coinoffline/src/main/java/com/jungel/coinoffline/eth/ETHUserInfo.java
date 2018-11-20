package com.jungel.coinoffline.eth;

import android.text.TextUtils;

import com.jungel.coinoffline.CoinOffline;
import com.jungel.coinoffline.eth.net.TestUtil;

public class ETHUserInfo {

    private static ETHUserInfo instance;

    public static synchronized ETHUserInfo getInstance() {
        if (instance == null) {
            instance = new ETHUserInfo();
        }
        return instance;
    }

    private ETHUserInfo() {

    }

    public static boolean hasAddress() {
        String mnemonic = (String) SpfHelper.getParam(CoinOffline.getContext(), ETHUrlHelper
                .KEY_WALLET_MNEMONIC, "");
        return TextUtils.isEmpty(mnemonic);
    }
}
