package com.jungel.coinoffline.eos.eos4j.utils;

/**
 * Hex
 *
 * @author espritblock http://eblock.io
 */
public class Hex {

    /**
     * toBytes
     *
     * @param hex
     * @return
     */
    public static byte[] toBytes(String hex) {
        if (hex == null || hex.length() % 2 != 0) {
            throw new EException("args_eroor", "args is error");
        }
        char[] hbyte = hex.toCharArray();
        int length = hbyte.length / 2;
        byte[] raw = new byte[length];
        for (int i = 0; i < length; i++) {
            int high = Character.digit(hbyte[i * 2], 16);
            int low = Character.digit(hbyte[i * 2 + 1], 16);
            if (high < 0 || low < 0) {
                throw new RuntimeException("Invalid hex digit " + hbyte[i * 2] + hbyte[i * 2 + 1]);
            }
            int value = (high << 4) | low;
            if (value > 127)
                value -= 256;
            raw[i] = (byte) value;
        }
        return raw;
    }

    /**
     * bytesToHexString
     *
     * @param src
     * @return
     */
    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * hexStringToBytes
     *
     * @param hexString
     * @return
     */
    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }


    public static void main(String[] args) {
        test();
    }

    public static void test() {
        String s = "038f4b0fc8ff18a4f0842a8f0564611f6e96e8535901dd45e43ac8691a1c4dca";
        System.out.println(s.length());
        byte[] bytes = hexStringToBytes(s);
        for (byte b : bytes) {
            System.out.print(b);
        }
        System.out.println("");

        String s2 = "1540348304";
        System.out.println(s.length());
        byte[] bytes2 = hexStringToBytes(s2);
        for (byte b : bytes2) {
            System.out.print(b);
        }
    }

}
