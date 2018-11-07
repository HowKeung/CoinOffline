package com.jungel.coinoffline.eos.eos4j;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.jungel.coinoffline.eos.eos4j.api.vo.SignParam;
import com.jungel.coinoffline.eos.eos4j.api.vo.transaction.push.Tx;
import com.jungel.coinoffline.eos.eos4j.api.vo.transaction.push.TxAction;
import com.jungel.coinoffline.eos.eos4j.api.vo.transaction.push.TxRequest;
import com.jungel.coinoffline.eos.eos4j.api.vo.transaction.push.TxSign;
import com.jungel.coinoffline.eos.eos4j.ecc.EccTool;
import com.jungel.coinoffline.eos.eos4j.ese.Action;
import com.jungel.coinoffline.eos.eos4j.ese.DataParam;
import com.jungel.coinoffline.eos.eos4j.ese.DataType;
import com.jungel.coinoffline.eos.eos4j.ese.Ese;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

/**
 * @author espritblock http://eblock.io
 */
public class OfflineSign {

    SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    public OfflineSign() {
        System.out.println("TEST-- TimeZone.getTimeZone : " + TimeZone.getTimeZone("UTC"));
        dateFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    /**
     * @param compression
     * @param pushTransaction
     * @param signatures
     * @return
     * @throws Exception
     */
    public String pushTransaction(String compression, Tx pushTransaction, String[] signatures)
            throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String mapJakcson = mapper.writeValueAsString(new TxRequest(compression, pushTransaction,
                signatures));
        return mapJakcson;
    }

    /**
     * 离线签名转账
     *
     * @param signParam
     * @param pk
     * @param contractAccount
     * @param from
     * @param to
     * @param quantity
     * @param memo
     * @return
     * @throws Exception
     */
    public String transfer(SignParam signParam, String pk, String contractAccount, String from,
                           String to,
                           String quantity, String memo) throws Exception {
        Tx tx = new Tx();
        tx.setExpiration(signParam.getHeadBlockTime().getTime() / 1000 + signParam.getExp());
        //        tx.setExpiration(TimeUtil.getMinllisByTime(signParam.getHeadBlockTime(),
        //                TimeUtil.FORMATE_NATION_TIME) + signParam.getExp() * 1000);
        System.out.println("TEST-- tx.getExpiration1 : " + tx.getExpiration());
        tx.setRef_block_num(signParam.getLastIrreversibleBlockNum());
        tx.setRef_block_prefix(signParam.getRefBlockPrefix());
        tx.setNet_usage_words(0l);
        tx.setMax_cpu_usage_ms(0l);
        tx.setDelay_sec(0l);
        // actions
        List<TxAction> actions = new ArrayList<>();
        // data
        Map<String, Object> dataMap = new LinkedHashMap<>();
        dataMap.put("from", from);
        dataMap.put("to", to);
        dataMap.put("quantity", new DataParam(quantity, DataType.asset, Action.transfer).getValue
                ());
        dataMap.put("memo", memo);
        // action
        TxAction action = new TxAction(from, contractAccount, "transfer", dataMap);
        actions.add(action);
        tx.setActions(actions);
        // sgin
        String sign = Ecc.signTransaction(pk, new TxSign(signParam.getChainId(), tx), EccTool
                .TYPE_TRANSFER);
        System.out.println("TEST-- sign : " + sign);
        // data parse
        String data = Ecc.parseTransferData(from, to, quantity, memo);
        System.out.println("TEST-- data : " + data);
        // reset data
        action.setData(data);
        // reset expiration
        tx.setExpiration(dateFormatter.format(new Date(Long.parseLong(tx.getExpiration().toString
                ()) * 1000)));
        System.out.println("TEST-- tx.getExpiration2 : " + tx.getExpiration());
        //        tx.setExpiration(TimeUtil.getTimeByMillis(Long.parseLong(tx.getExpiration()
        //                .toString()), TimeUtil.FORMATE_NATION_TIME));
        return pushTransaction("none", tx, new String[]{sign});
    }

    /**
     * 离线签名创建账户
     *
     * @param signParam
     * @param pk
     * @param creator
     * @param newAccount
     * @param owner
     * @param active
     * @param buyRam
     * @return
     * @throws Exception
     */
    public String createAccount(SignParam signParam, String pk, String creator, String
            newAccount, String owner, String active, Long buyRam) throws Exception {
        Tx tx = new Tx();
        tx.setExpiration(signParam.getHeadBlockTime().getTime() / 1000 + signParam.getExp());
        //        tx.setExpiration(TimeUtil.getMinllisByTime(signParam.getHeadBlockTime(),
        //                TimeUtil.FORMATE_NATION_TIME) / 1000 + signParam.getExp());
        tx.setRef_block_num(signParam.getLastIrreversibleBlockNum());
        tx.setRef_block_prefix(signParam.getRefBlockPrefix());
        tx.setNet_usage_words(0l);
        tx.setMax_cpu_usage_ms(0l);
        tx.setDelay_sec(0l);
        // actions
        List<TxAction> actions = new ArrayList<>();
        tx.setActions(actions);
        // create
        Map<String, Object> createMap = new LinkedHashMap<>();
        createMap.put("creator", creator);
        createMap.put("name", newAccount);
        createMap.put("owner", owner);
        createMap.put("active", active);
        TxAction createAction = new TxAction(creator, "eosio", "newaccount", createMap);
        actions.add(createAction);
        // buyrap
        Map<String, Object> buyMap = new LinkedHashMap<>();
        buyMap.put("payer", creator);
        buyMap.put("receiver", newAccount);
        buyMap.put("bytes", buyRam);
        TxAction buyAction = new TxAction(creator, "eosio", "buyrambytes", buyMap);
        actions.add(buyAction);
        // sgin
        String sign = Ecc.signTransaction(pk, new TxSign(signParam.getChainId(), tx), EccTool
                .TYPE_ACCOUNT);
        // data parse
        String accountData = Ese.parseAccountData(creator, newAccount, owner, active);
        createAction.setData(accountData);
        // data parse
        String ramData = Ese.parseBuyRamData(creator, newAccount, buyRam);
        buyAction.setData(ramData);
        // reset expiration
        tx.setExpiration(dateFormatter.format(new Date(1000 * Long.parseLong(tx.getExpiration()
                .toString()))));
        return pushTransaction("none", tx, new String[]{sign});
    }

    public void testSign() {
        Tx tx = new Tx();
        tx.setExpiration(formate("2018-11-05T03:35:15.500").getTime());
        //        tx.setExpiration(TimeUtil.getMinllisByTime(signParam.getHeadBlockTime(),
        //                TimeUtil.FORMATE_NATION_TIME) + signParam.getExp() * 1000);
        tx.setRef_block_num(22875990l);
        tx.setRef_block_prefix(2428356325l);
        tx.setNet_usage_words(0l);
        tx.setMax_cpu_usage_ms(0l);
        tx.setDelay_sec(0l);
        // actions
        List<TxAction> actions = new ArrayList<>();
        // data
        Map<String, Object> dataMap = new LinkedHashMap<>();
        dataMap.put("from", "apengoffice1");
        dataMap.put("to", "123jungel123");
        dataMap.put("quantity", new DataParam("1 EOS", DataType.asset, Action.transfer)
                .getValue());
        dataMap.put("memo", "te");
        //        TxData txData = new TxData();
        //        txData.setFrom("123jungel123");
        //        txData.setTo("apengoffice1");
        //        txData.setQuantity(new DataParam("1.0000 EOS", DataType.asset, Action.transfer)
        // .getValue());
        //        txData.setMemo("TestMemo");
        // action
        TxAction action = new TxAction("apengoffice1", "eosio.token", "transfer", dataMap);
        actions.add(action);
        tx.setActions(actions);
        // sgin
        String sign = Ecc.signTransaction("5JLmhxZUePs3xruJhVKcmHMXvv2AWuF9srfwkp7td37SagSzgkx",
                new TxSign("038f4b0fc8ff18a4f0842a8f0564611f6e96e8535901dd45e43ac8691a1c4dca",
                        tx), EccTool.TYPE_TRANSFER);

        String data = Ecc.parseTransferData("apengoffice1", "123jungel123", "1 EOS",
                "te");

        System.out.println("TEST-- sign : " + sign);
        System.out.println("TEST-- data : " + data);
    }

    public void testAccount() {
        Tx tx = new Tx();
        tx.setExpiration(1540348304);
        //        tx.setExpiration(TimeUtil.getMinllisByTime(signParam.getHeadBlockTime(),
        //                TimeUtil.FORMATE_NATION_TIME) / 1000 + signParam.getExp());
        tx.setRef_block_num(20943242l);
        tx.setRef_block_prefix(1648284106l);
        tx.setNet_usage_words(0l);
        tx.setMax_cpu_usage_ms(0l);
        tx.setDelay_sec(0l);
        // actions
        List<TxAction> actions = new ArrayList<>();
        tx.setActions(actions);
        // create
        Map<String, Object> createMap = new LinkedHashMap<>();
        createMap.put("creator", "123jungel123");
        createMap.put("name", "apengoffice1");
        createMap.put("owner", "EOS86EisqYsPkLzsY6gVbFvjCN5bk1eis7YgFeLqtHvug4qrtHZqF");
        createMap.put("active", "EOS86EisqYsPkLzsY6gVbFvjCN5bk1eis7YgFeLqtHvug4qrtHZqF");
        TxAction createAction = new TxAction("123jungel123", "eosio", "newaccount", createMap);
        actions.add(createAction);
        // buyrap
        Map<String, Object> buyMap = new LinkedHashMap<>();
        buyMap.put("payer", "123jungel123");
        buyMap.put("receiver", "apengoffice1");
        buyMap.put("bytes", 8000l);
        TxAction buyAction = new TxAction("123jungel123", "eosio", "buyrambytes", buyMap);
        actions.add(buyAction);
        // sgin
        String sign = Ecc.signTransaction("5JCvpCqyVwvjDCeDhS2MNAkBZgEbh7GuRkiH1aKt1yxq5ibrc4Y", new TxSign("038f4b0fc8ff18a4f0842a8f0564611f6e96e8535901dd45e43ac8691a1c4dca", tx), EccTool
                .TYPE_OTHER);
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
