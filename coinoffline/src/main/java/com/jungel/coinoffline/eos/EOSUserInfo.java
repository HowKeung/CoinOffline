package com.jungel.coinoffline.eos;

import android.text.TextUtils;

import com.jungel.coinoffline.eos.eospocket.blockchain.cypto.ec.EosPrivateKey;
import com.jungel.coinoffline.eos.eospocket.utils.Utils;
import com.jungel.coinoffline.utils.MD5Util;

public class EOSUserInfo {

    private static final String KEY_PUBLIC_KEY_1 = MD5Util.MD5Encode("publicKey1", "UTF-8");
    private static final String KEY_PUBLIC_KEY_2 = MD5Util.MD5Encode("publicKey2", "UTF-8");
    private static final String KEY_PRIVATE_KEY_1 = MD5Util.MD5Encode("privateKey1", "UTF-8");
    private static final String KEY_PRIVATE_KEY_2 = MD5Util.MD5Encode("privateKey2", "UTF-8");
    private static final String KEY_ACCOUNT_NAME = "accountName";

    private static EOSUserInfo instance;


    private Boolean mHasKey = null;

    public static synchronized EOSUserInfo getInstance() {
        if (instance == null) {
            instance = new EOSUserInfo();
        }
        return instance;
    }

    private EOSUserInfo() {

    }

    public synchronized boolean hasKey() {
        if (mHasKey == null) {
            String publicKey1 = Utils.getSpUtils().getString(KEY_PUBLIC_KEY_1);
            String publicKey2 = Utils.getSpUtils().getString(KEY_PUBLIC_KEY_2);
            if (!TextUtils.isEmpty(publicKey1) && !TextUtils.isEmpty(publicKey2)) {
                mHasKey = true;
            } else {
                mHasKey = false;
            }
        }
        return mHasKey;
    }

    public synchronized String getPublicKey(int pos) {
        if (pos == 0) {
            String publicKey1 = Utils.getSpUtils().getString(KEY_PUBLIC_KEY_1);
            return publicKey1;
        } else {
            String publicKey2 = Utils.getSpUtils().getString(KEY_PUBLIC_KEY_2);
            return publicKey2;
        }
    }

    public synchronized String getPrivateKey(int pos) {
        if (pos == 0) {
            String publicKey1 = Utils.getSpUtils().getString(KEY_PRIVATE_KEY_1);
            return publicKey1;
        } else {
            String publicKey2 = Utils.getSpUtils().getString(KEY_PRIVATE_KEY_2);
            return publicKey2;
        }
    }

    public synchronized void saveKey(EosPrivateKey[] privateKeys) {
        Utils.getSpUtils().put(KEY_PUBLIC_KEY_1, privateKeys[0].getPublicKey().toString());
        Utils.getSpUtils().put(KEY_PUBLIC_KEY_2, privateKeys[0].getPublicKey().toString());
        Utils.getSpUtils().put(KEY_PRIVATE_KEY_1, privateKeys[0].toString());
        Utils.getSpUtils().put(KEY_PRIVATE_KEY_2, privateKeys[0].toString());
    }

    public synchronized void saveAccountName(String accountName) {
        Utils.getSpUtils().put(KEY_ACCOUNT_NAME, accountName);
    }

    public synchronized String getAccountName() {
        String account = Utils.getSpUtils().getString(KEY_ACCOUNT_NAME);
        return account;
    }

    public synchronized boolean hasAccount() {
        String accountName = Utils.getSpUtils().getString(KEY_ACCOUNT_NAME);
        return !TextUtils.isEmpty(accountName);
    }
}
