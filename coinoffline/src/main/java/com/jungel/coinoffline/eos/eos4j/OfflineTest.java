package com.jungel.coinoffline.eos.eos4j;

import com.jungel.coinoffline.eos.eos4j.api.exception.ApiException;
import com.jungel.coinoffline.eos.eos4j.api.vo.SignParam;
import com.jungel.coinoffline.eos.eos4j.api.vo.transaction.Transaction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author espritblock http://eblock.io
 */
public class OfflineTest {

    public static void main(String[] args) {
        //                testOfflineCreate();
                testOfflineTransfer();
//        testOfflineSign();
    }


    public static void testOfflineCreate() {
        Rpc rpc = new Rpc("http://jungle.cryptolions.io:18888");
        // 获取离线签名参数
        SignParam params = rpc.getOfflineSignParams(60l);
        // 离线签名
        OfflineSign sign = new OfflineSign();
        // 交易信息
        String content = "";
        try {
            content = sign.createAccount(params,
                    "5JCvpCqyVwvjDCeDhS2MNAkBZgEbh7GuRkiH1aKt1yxq5ibrc4Y", "123jungel123",
                    "555555555554", "EOS86EisqYsPkLzsY6gVbFvjCN5bk1eis7YgFeLqtHvug4qrtHZqF",
                    "EOS86EisqYsPkLzsY6gVbFvjCN5bk1eis7YgFeLqtHvug4qrtHZqF", 8000l);
            System.out.println(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 广播交易
        //        try {
        //            Transaction tx = rpc.pushTransaction(content);
        //            System.out.println(tx.getTransactionId());
        //        } catch (ApiException ex) {
        //            System.out.println(ex.getError().getCode());
        //        } catch (Exception e) {
        //            e.printStackTrace();
        //        }
    }

    public static void testOfflineTransfer() {
        Rpc rpc = new Rpc("http://jungle.cryptolions.io:18888");
        // 获取离线签名参数
        SignParam params = rpc.getOfflineSignParams(60l);
        // 离线签名
        OfflineSign sign = new OfflineSign();
        // 交易信息
        String content = "";
        try {
            content = sign.transfer(params,
                    "5JCvpCqyVwvjDCeDhS2MNAkBZgEbh7GuRkiH1aKt1yxq5ibrc4Y", "eosio.token",
                    "123jungel123", "apengoffice1", "10.0000 EOS", "test");
            System.out.println(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        testOfflineTransfer(content);
        //        sign.testSign();
        //        testOfflineTransfer(content);
        //        // 广播交易
        //        try {
        //            Transaction tx = rpc.pushTransaction(content);
        //            System.out.println(tx.getTransactionId());
        //        } catch (ApiException ex) {
        //            ex.printStackTrace();
        //        } catch (Exception e) {
        //            e.printStackTrace();
        //        }
    }

    public static void testOfflineTransfer(String content) {
        Rpc rpc = new Rpc("http://jungle.cryptolions.io:18888");
        // 广播交易
        try {
            Transaction tx = rpc.pushTransaction(content);
            System.out.println(tx.getTransactionId());
        } catch (ApiException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testOfflineSign() {
        // 离线签名
        new OfflineSign().testSign();
        //        new OfflineSign().testAccount();
    }

    private static Date formate(String time) {
        SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd'T'HH:mm:ss.SSS");
        try {
            return format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
