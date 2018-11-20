package com.jungel.coinoffline.eos.eospocket;

import com.jungel.coinoffline.CoinOffline;
import com.jungel.coinoffline.eos.eospocket.net.HttpUtils;
import com.jungel.coinoffline.eos.eospocket.net.ResponseBean;
import com.jungel.coinoffline.eos.eospocket.net.callbck.JsonCallback;
import com.lzy.okgo.model.Response;

import java.util.HashMap;

public class Test {

    public static void main(String[] args1) {

        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("pos", String.valueOf(1));
        hashMap.put("offset", String.valueOf(20));
        hashMap.put("account_name", "kew123451kew");
        HttpUtils.postRequest("https://api.helloeos.com.cn//v1/history/get_actions",
                CoinOffline.getInstance().getContext(), hashMap,
                new JsonCallback<ResponseBean<String>>() {
                    @Override
                    public void onSuccess(Response<ResponseBean<String>> response) {

                    }
                });
    }
}