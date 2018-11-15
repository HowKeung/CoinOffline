package com.jungel.coinoffline.eth;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.jungel.coinoffline.eth.bean.ERCTransaction;
import com.jungel.coinoffline.eth.net.StaticValue;
import com.jungel.coinoffline.eth.net.TestOKHttp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;

public class ERCUtil {


    public static List<ERCTransaction.Result> getErcTransactionList(String symbol, String address) {
        List<ERCTransaction.Result> results = new ArrayList<>();
        if (TextUtils.isEmpty(symbol) || TextUtils.isEmpty(address)) {
            return results;
        }
        String url = StaticValue.TRANSACTION_ALL + "address=" + address +
                "&startblock=0&endblock=999999999&sort=asc&" + "apikey=" + StaticValue.API_KEY;
        ResponseBody responseBody = TestOKHttp.testGetClient(url);
        try {
            ERCTransaction transaction = new Gson().fromJson(responseBody.string(),
                    ERCTransaction.class);
            for (ERCTransaction.Result result : transaction.getResult()) {
                if (result.getTokenSymbol().equalsIgnoreCase(symbol)) {
                    results.add(result);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return results;
    }
}
