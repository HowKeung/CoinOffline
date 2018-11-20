package com.jungel.util;

import java.math.BigDecimal;
import java.math.BigInteger;

public class TestUtil {
    /**
     * 转换成符合 decimal 的数值
     *
     * @param decimal
     * @param integer
     * @return
     */
    public static String toDecimal(int decimal, BigInteger integer) {
        //		String substring = str.substring(str.length() - decimal);
        StringBuffer sbf = new StringBuffer("1");
        for (int i = 0; i < decimal; i++) {
            sbf.append("0");
        }
        String balance = new BigDecimal(integer).divide(new BigDecimal(sbf.toString()), 18,
                BigDecimal.ROUND_DOWN).toPlainString();
        return balance;
    }
}
