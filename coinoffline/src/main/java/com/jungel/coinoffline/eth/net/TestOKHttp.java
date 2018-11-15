package com.jungel.coinoffline.eth.net;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class TestOKHttp {

    /**
     * okhttp get 请求
     */
    public static ResponseBody testGetClient(String url) {
        OkHttpClient okClient = new OkHttpClient();

        Request request = new Request.Builder()
                .get().url(url).build();
        ResponseBody resBody = null;
        try {

            Response response = okClient.newCall(request).execute();
            resBody = response.body();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resBody;
    }


    public static String getResponseMessageGet(String url) {
        ResponseBody body = null;
        String string = null;
        body = testGetClient(url);
        if (checkEmpty(body)) return null;
        try {
            string = body.string();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return string;

    }

    public static boolean checkEmpty(Object obj) {
        if (null != obj && !obj.toString().trim().equals("")) {
            return false;
        }
        return true;

    }
}
