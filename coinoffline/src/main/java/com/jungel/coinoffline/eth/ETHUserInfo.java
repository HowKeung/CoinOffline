package com.jungel.coinoffline.eth;

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
}
