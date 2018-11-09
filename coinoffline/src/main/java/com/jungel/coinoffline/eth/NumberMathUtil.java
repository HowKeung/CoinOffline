/*
 * Copyright (c) 2018-2018. 蝌蚪 All Rights Reserved.
 * Author: Domon.Xie
 * Date: 18-6-21 上午10:36
 */

package com.jungel.coinoffline.eth;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

public final class NumberMathUtil {

    public final static BigDecimal MAX_VALUE = new BigDecimal("9999999999999999.99999999");

    public final static BigDecimal MIN_VALUE = BigDecimal.ZERO;

    public final static String HEX_ZERO = "0x0";

    private final static String HEX_PREFIX = "0x";

    private final static BigDecimal ETH_WEI = new BigDecimal("1000000000000000000");

    private final static BigDecimal ETH_GWEI = new BigDecimal("1000000000");

    private final static MathContext DEFAULT_MATH_CONTEXT = new MathContext(24, RoundingMode.FLOOR);

    /**
     * 16进制转10进制
     *
     * @param hex 16进制
     * @return 10进制
     */
    public static BigDecimal hexConvertToNumber(String hex) {
        return new BigDecimal(new BigInteger(hex.startsWith(HEX_PREFIX) ? hex.substring(2) : hex, 16));
    }

    /**
     * 10进制转16进制
     *
     * @param number 10进制
     * @return 16进制
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String numberConvertToHex(long number) {
        return String.join("", HEX_PREFIX, Long.toHexString(number));
    }

    /**
     * ETH单位换算
     *
     * @param wei wei
     * @return eth
     */
    public static BigDecimal weiToEth(BigDecimal wei) {
        return wei.divide(ETH_WEI, 8, RoundingMode.DOWN);
    }

    /**
     * ETH单位换算
     *
     * @param eth eth
     * @return wei
     */
    public static BigDecimal ethToWei(BigDecimal eth) {
        return eth.multiply(ETH_WEI);
    }

    /**
     * ETH代币数转换
     *
     * @param amount   原始数据
     * @param decimals 比例
     * @return 代币数
     */
    public static BigDecimal amountToEthToken(BigDecimal amount, int decimals) {
        return amount.divide(BigDecimal.TEN.pow(decimals), 8, RoundingMode.DOWN);
    }

    /**
     * ETH代币数转换
     *
     * @param token    代币数
     * @param decimals 比例
     * @return 原始数据
     */
    public static BigDecimal ethTokenToAmount(BigDecimal token, int decimals) {
        return token.multiply(BigDecimal.TEN.pow(decimals));
    }

    /**
     * 字符串转数字
     *
     * @param val          字符串
     * @param defaultValue 默认值
     * @return 数字
     */
    public static int toInt(String val, int defaultValue) {
        if (val == null) {
            return defaultValue;
        } else {
            try {
                return Integer.parseInt(val);
            } catch (NumberFormatException e) {
                return defaultValue;
            }
        }
    }

    /**
     * 数字是否合法
     *
     * @param decimal 数字
     * @return 是否合法
     */
    public static boolean isOk(BigDecimal decimal) {
        return decimal != null && decimal.compareTo(MIN_VALUE) >= 0 && decimal.compareTo(MAX_VALUE) <= 0;
    }

    /**
     * 数字是否合法
     *
     * @param num 数字
     * @return 是否合法
     */
    public static boolean isOk(Number num) {
        return num != null && isOk(new BigDecimal(num.toString()));
    }

    /**
     * 获取最大数字，为null时默认为0
     *
     * @param decimals 数字
     * @return 最大数字
     */
    public static BigDecimal max(BigDecimal... decimals) {
        BigDecimal max = BigDecimal.ZERO;
        for (BigDecimal decimal : decimals) {
            max = max.max(decimal == null ? BigDecimal.ZERO : decimal);
        }
        return max;
    }

    /**
     * 加法
     *
     * @param num1 加数
     * @param num2 被加数
     * @return 结果
     */
    public static BigDecimal add(Number num1, Number num2) {
        return add(new BigDecimal(num1.toString()), new BigDecimal(num2.toString()), DEFAULT_MATH_CONTEXT);
    }

    /**
     * 加法
     *
     * @param decimal1 被加数
     * @param decimal2 加数
     * @return 结果
     */
    @TargetApi(Build.VERSION_CODES.O)
    public static BigDecimal add(BigDecimal decimal1, BigDecimal decimal2, MathContext mathContext) {
        BigDecimal result = decimal1.add(decimal2, mathContext);
        if (!isOk(result)) {
            throw new NumberFormatException(String.join("", "计算结果超过最大值 -> ", decimal1.toString(), " * ", decimal2.toString(), " = ", result.toPlainString()));
        }
        return result;
    }

    /**
     * 减法
     *
     * @param num1 被减数
     * @param num2 减数
     * @return 结果
     */
    public static BigDecimal subtract(Number num1, Number num2) {
        return subtract(new BigDecimal(num1.toString()), new BigDecimal(num2.toString()), DEFAULT_MATH_CONTEXT);
    }

    /**
     * 减法
     *
     * @param decimal1 被减数
     * @param decimal2 减数
     * @return 结果
     */
    @TargetApi(Build.VERSION_CODES.O)
    public static BigDecimal subtract(BigDecimal decimal1, BigDecimal decimal2, MathContext mathContext) {
        BigDecimal result = decimal1.subtract(decimal2, mathContext);
        if (!isOk(result)) {
            throw new NumberFormatException(String.join("", "计算结果超过最大值 -> ", decimal1.toString(), " * ", decimal2.toString(), " = ", result.toPlainString()));
        }
        return result;
    }

    /**
     * 乘法
     *
     * @param num1 被乘数
     * @param num2 乘数
     * @return 结果
     */
    public static BigDecimal multiply(Number num1, Number num2) {
        return multiply(new BigDecimal(num1.toString()), new BigDecimal(num2.toString()), DEFAULT_MATH_CONTEXT);
    }

    /**
     * 乘法
     *
     * @param decimal1 被乘数
     * @param decimal2 乘数
     * @return 结果
     */
    @TargetApi(Build.VERSION_CODES.O)
    public static BigDecimal multiply(BigDecimal decimal1, BigDecimal decimal2, MathContext mathContext) {
        BigDecimal result = decimal1.multiply(decimal2, mathContext);
        if (!isOk(result)) {
            throw new NumberFormatException(String.join("", "计算结果超过最大值 -> ", decimal1.toString(), " * ", decimal2.toString(), " = ", result.toPlainString()));
        }
        return result;
    }

    public static BigDecimal multiply(BigDecimal decimal1, BigDecimal... decimals) {
        if (decimals == null || decimals.length <= 0) {
            return decimal1;
        }
        BigDecimal result = decimal1;
        for (BigDecimal decimal : decimals) {
            result = multiply(result, decimal);
        }
        return result;
    }

    /**
     * 除法
     *
     * @param num1 被除数
     * @param num2 除数
     * @return 结果
     */
    public static BigDecimal divide(Number num1, Number num2) {
        return divide(new BigDecimal(num1.toString()), new BigDecimal(num2.toString()), DEFAULT_MATH_CONTEXT);
    }

    /**
     * 除法
     *
     * @param decimal1 被除数
     * @param decimal2 除数
     * @return 结果
     */
    @TargetApi(Build.VERSION_CODES.O)
    public static BigDecimal divide(BigDecimal decimal1, BigDecimal decimal2, MathContext mathContext) {
        BigDecimal result = decimal1.divide(decimal2, mathContext);
        if (!isOk(result)) {
            throw new NumberFormatException(String.join("", "计算结果超过最大值 -> ", decimal1.toString(), " / ", decimal2.toString(), " = ", result.toPlainString()));
        }
        return result;
    }

    public static void main(String[] args) {
        int a = 0x0;
        System.out.println(Integer.valueOf(0x0).toString());
    }
}
