package com.jungel.coinoffline.eos;

import android.text.TextUtils;

public class BalanceUtil {

    public static double getCash(String balance) {
        if (TextUtils.isEmpty(balance)) {
            return 0;
        }
        String[] balances = balance.split("EOS");
        double cash = 0;
        if (balances != null && balances.length > 0) {
            try {
                cash = Double.parseDouble(balances[0]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return cash;
    }
}
