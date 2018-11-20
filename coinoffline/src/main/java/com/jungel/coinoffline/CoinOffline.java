package com.jungel.coinoffline;

import android.content.Context;
import android.text.TextUtils;

import com.jungel.coinoffline.eos.eospocket.utils.Utils;
import com.jungel.coinoffline.eth.ETHUrlHelper;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jFactory;
import org.web3j.protocol.http.HttpService;

public class CoinOffline {

    private static final String KEY_PIN_CODE = "key_pin_code";

    private boolean mIsShowPin = true;
    private Boolean mHasPinCode = null;

    private static Context mContext;

    public static Context getContext() {
        if (mContext == null) {
            throw new RuntimeException("must call init first");
        }
        return mContext;
    }

    public static void init(Context context) {
        mContext = context;
        Utils.init(context);
    }

    public synchronized boolean hasPinCode() {
        if (mHasPinCode == null) {
            String pinCode = Utils.getSpUtils().getString(KEY_PIN_CODE);
            if (!TextUtils.isEmpty(pinCode)) {
                mHasPinCode = true;
            } else {
                mHasPinCode = false;
            }
        }
        return mHasPinCode;
    }

    public synchronized void savePinCode(String pinCode) {
        Utils.getSpUtils().put(KEY_PIN_CODE, pinCode);
    }

    public synchronized boolean isShowPin() {
        return mIsShowPin;
    }

    public synchronized void setShowPin(boolean showPin) {
        mIsShowPin = showPin;
    }
}
