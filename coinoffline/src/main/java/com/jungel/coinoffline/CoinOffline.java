package com.jungel.coinoffline;

import android.content.Context;
import android.text.TextUtils;

import com.jungel.coinoffline.eos.eospocket.utils.Utils;

import org.consenlabs.tokencore.wallet.Identity;
import org.consenlabs.tokencore.wallet.KeystoreStorage;
import org.consenlabs.tokencore.wallet.WalletManager;

import java.io.File;

public class CoinOffline implements KeystoreStorage {

    private static final String KEY_PIN_CODE = "key_pin_code";

    private boolean mIsShowPin = true;
    private Boolean mHasPinCode = null;

    private Context mContext;
    private static CoinOffline mInstance;

    public synchronized static CoinOffline getInstance() {
        if (mInstance == null) {
            mInstance = new CoinOffline();
        }
        return mInstance;
    }

    public synchronized Context getContext() {
        if (mContext == null) {
            throw new RuntimeException("must call init first");
        }
        return mContext;
    }

    public synchronized void init(Context context) {
        mContext = context;
        Utils.init(context);
        WalletManager.storage = this;
        WalletManager.scanWallets();
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

    public synchronized boolean hasCreateAccount() {
        if (Identity.getCurrentIdentity() == null) {
            return false;
        }
        return true;
    }

    @Override
    public File getKeystoreDir() {
        return mContext.getFilesDir();
    }
}
