package com.jungel.coinoffline.eos.eospocket.utils;


import com.jungel.coinoffline.eos.eospocket.blockchain.cypto.ec.EosPrivateKey;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pocketEos on 2018/2/3.
 */

public class PublicAndPrivateKeyUtils {

    public static EosPrivateKey[] getPrivateKey(int count) {
        EosPrivateKey[] retKeys = new EosPrivateKey[count];
        for (int i = 0; i < count; i++) {
            retKeys[i] = new EosPrivateKey();
        }

        return retKeys;
    }

    public static EosPrivateKey getPublicKey(String privateKey) {
        try {
            EosPrivateKey eosPrivateKey = new EosPrivateKey(privateKey);
            return eosPrivateKey;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) {
        EosPrivateKey[] privateKeys = getPrivateKey(1);
        System.out.println("privateKey1 : " + privateKeys[0].toString());
        System.out.println("publicKey1 : " + privateKeys[0].getPublicKey());

        EosPrivateKey eosPrivateKey = new EosPrivateKey(privateKeys[0].toString());

        System.out.println("privateKey2 : " + eosPrivateKey.toString());
        System.out.println("publicKey2 : " + eosPrivateKey.getPublicKey());

        System.out.println("privateKey equals : " + eosPrivateKey.toString().equals
                (privateKeys[0].toString()));
        System.out.println("publicKey equals : " + eosPrivateKey.getPublicKey().equals
                (privateKeys[0].getPublicKey()));

        EosPrivateKey eosPrivateKey3 = new EosPrivateKey
                ("5JCvpCqyVwvjDCeDhS2MNAkBZgEbh7GuRkiH1aKt1yxq5ibrc4Y");

        System.out.println("privateKey3 : " + eosPrivateKey3.toString());
        System.out.println("publicKey3 : " + eosPrivateKey3.getPublicKey());
    }
}
