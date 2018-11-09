package com.jungel.coinoffline.btc.rpc;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CoinBtcService {

    private String url = "https://coinfaucet.eu/en/btc-testnet/";
    private String username = "u";
    private String password = "p";

    private final static String RESULT = "result";
    private final static String METHOD_SEND_TO_ADDRESS = "sendtoaddress";
    private final static String METHOD_GET_BLOCK = "getblock";
    private final static String METHOD_GET_BLOCK_HASH = "getblockhash";
    private final static String METHOD_GET_TRANSACTION = "gettransaction";
    private final static String METHOD_GET_BLOCK_COUNT = "getblockcount";
    private final static String METHOD_NEW_ADDRESS = "getnewaddress";
    private final static String METHOD_GET_BALANCE = "getbalance";
    private final static int MIN_CONFIRMATION = 6;

    //前四个参数在BTC钱包conf文件中设置
    //钱包密码PASSWORD打开钱包后设置的密码

    /***
     * 取得钱包相关信息
     * 若获取失败，result为空，error信息为错误信息的编码
     * */
    public JSONObject getInfo() throws Exception {
        return doRequest("getinfo");
    }

    /**
     * 获取块链信息
     *
     * @return
     * @throws Exception
     */
    public JSONObject getBlockChainInfo() throws Exception {
        return doRequest("getblockchaininfo");
    }


    /**
     * BTC产生地址
     *
     * @return
     */
    public String getNewAddress() {
        JSONObject json = doRequest(METHOD_NEW_ADDRESS);
        if (isError(json)) {
            System.out.println("获取BTC余额:" + json.get("error"));
            return "";
        }
        return json.getString(RESULT);
    }

    /**
     * BTC查询余额
     *
     * @return
     */
    public double getBalance() {
        JSONObject json = doRequest(METHOD_GET_BALANCE);
        if (isError(json)) {
            System.out.println("获取BTC余额:" + json.get("error"));
            return 0;
        }
        return json.getDouble(RESULT);
    }

    /**
     * BTC转帐
     *
     * @param addr
     * @param value
     * @return
     */
    public String send(String addr, double value) {
        if (vailedAddress(addr)) {
            JSONObject json = doRequest(METHOD_SEND_TO_ADDRESS, addr, value);
            if (isError(json)) {
                System.out.println("BTC 转帐给 : " + addr + " value : " + value + "  失败 ：" + json.get("error"));
                return "";
            } else {
                System.out.println("BTC 转币给 : " + addr + " value:" + value + " 成功");
                return json.getString(RESULT);
            }
        } else {
            System.out.println("BTC接受地址不正确");
            return "";
        }
    }

    /**
     * 验证地址的有效性
     *
     * @param address
     * @return
     * @throws Exception
     */
    public boolean vailedAddress(String address) {
        JSONObject json = doRequest("validateaddress", address);

        if (isError(json)) {
            System.out.println("BTC验证地址失败:" + json.get("error"));
            return false;
        } else {
            return json.getJSONObject(RESULT).getBoolean("isvalid");
        }
    }


    /**
     * 区块高度
     *
     * @return
     */
    public int getBlockCount() {
        JSONObject json = null;
        try {
            json = doRequest(METHOD_GET_BLOCK_COUNT);
            if (!isError(json)) {
                return json.getInteger("result");
            } else {
                System.out.println(json.toString());
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public boolean parseBlock(int index) {
        JSONObject jsonBlockHash = doRequest(METHOD_GET_BLOCK_HASH, index);
        if (isError(jsonBlockHash)) {
            System.out.println("访问BTC出错");
            return false;
        }

        String hash = jsonBlockHash.getString(RESULT);
        JSONObject jsonBlock = doRequest(METHOD_GET_BLOCK, hash);
        if (isError(jsonBlock)) {
            System.out.println("访问BTC出错");
            return false;
        }
        JSONObject jsonBlockResult = jsonBlock.getJSONObject(RESULT);
        int confirm = jsonBlockResult.getInteger("confirmations");
        if (confirm >= MIN_CONFIRMATION) {
            JSONArray jsonArrayTx = jsonBlockResult.getJSONArray("tx");
            if (jsonArrayTx == null || jsonArrayTx.size() == 0) {
                //没有交易
                return true;
            }
            Iterator<Object> iteratorTxs = jsonArrayTx.iterator();
            while (iteratorTxs.hasNext()) {
                String txid = (String) iteratorTxs.next();
                parseTx(txid, confirm, null);
            }
            return true;
        } else {
            return false;
        }
    }

    public void parseTx(String txid, int coinfirm, List<UserCoinAddressEntity> userList) {
        JSONObject jsonTransaction = doRequest(METHOD_GET_TRANSACTION, txid);
        if (isError(jsonTransaction)) {
            //System.out.println("处理BTC tx出错");
            return;
        }
        JSONObject jsonTransactionResult = jsonTransaction.getJSONObject(RESULT);
        JSONArray jsonArrayVout = jsonTransactionResult.getJSONArray("details");
        if (jsonArrayVout == null || jsonArrayVout.size() == 0) {
            return;
        }
        Iterator<Object> iteratorVout = jsonArrayVout.iterator();
        while (iteratorVout.hasNext()) {
            JSONObject jsonVout = (JSONObject) iteratorVout.next();
            double value = jsonVout.getDouble("amount");
            String category = jsonVout.getString("category");
            if (value > 0 && "receive".equals(category)) {
                String address = jsonVout.getString("address");
                for (UserCoinAddressEntity addressModel : userList) {
                    //如果有地址是分配给用记的地址， 则说明用户在充值
                    if (address.equals(addressModel.getAddress())) {
                        //添加充值记录
                        System.out.println("用户充值");

                    }
                }
            }
        }
    }


    private boolean isError(JSONObject json) {
        if (json == null || (StringUtils.isNotEmpty(json.getString("error")) && json.get("error") != "null")) {
            return true;
        }
        return false;
    }


    private JSONObject doRequest(String method, Object... params) {
        JSONObject param = new JSONObject();
        param.put("id", System.currentTimeMillis() + "");
        param.put("jsonrpc", "2.0");
        param.put("method", method);
        if (params != null) {
            param.put("params", params);
        }
        String creb = Base64.encodeBase64String((username + ":" + password).getBytes());
        Map<String, String> headers = new HashMap<>(2);
        headers.put("Authorization", "Basic " + creb);
        String resp = "";
        if (METHOD_GET_TRANSACTION.equals(method)) {
            try {
                resp = HttpUtil.jsonPost(url, headers, param.toString());
            } catch (Exception e) {
                if (e instanceof IOException) {
                    resp = "{}";
                }
            }
        } else {
            resp = HttpUtil.jsonPost(url, headers, param.toString());
        }
        return JSON.parseObject(resp);
    }

    public static void main(String args[]) throws Exception {
        CoinBtcService btcUtils = new CoinBtcService();
        System.out.println("getNewAddress : "+btcUtils.getNewAddress());
    }
}
