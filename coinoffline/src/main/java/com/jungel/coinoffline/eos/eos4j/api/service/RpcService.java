package com.jungel.coinoffline.eos.eos4j.api.service;

import com.jungel.coinoffline.eos.EOSUrlHelper;
import com.jungel.coinoffline.eos.eos4j.api.vo.Block;
import com.jungel.coinoffline.eos.eos4j.api.vo.ChainInfo;
import com.jungel.coinoffline.eos.eos4j.api.vo.HistoryAction;
import com.jungel.coinoffline.eos.eos4j.api.vo.KeyAccounts;
import com.jungel.coinoffline.eos.eos4j.api.vo.TableRows;
import com.jungel.coinoffline.eos.eos4j.api.vo.TableRowsReq;
import com.jungel.coinoffline.eos.eos4j.api.vo.account.Account;
import com.jungel.coinoffline.eos.eos4j.api.vo.transaction.Transaction;
import com.jungel.coinoffline.eos.eos4j.api.vo.transaction.push.TxRequest;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * @author espritblock http://eblock.io
 */
public interface RpcService {

    @GET(EOSUrlHelper.HD_URL_GET_INFO)
    Call<ChainInfo> getChainInfo();

    @POST(EOSUrlHelper.HD_URL_GET_BLOCK)
    Call<Block> getBlock(@Body Map<String, String> requestFields);

    @POST(EOSUrlHelper.HD_URL_GET_ACCOUNT)
    Call<Account> getAccount(@Body Map<String, String> requestFields);

    @POST(EOSUrlHelper.HD_URL_PUSH_TRANSACTION)
    Call<Transaction> pushTransaction(@Body TxRequest request);

    @POST("/v1/chain/get_table_rows")
    Call<TableRows> getTableRows(@Body TableRowsReq request);

    @POST(EOSUrlHelper.HD_URL_TRADE_HISTORY)
    Call<HistoryAction> getHistoryAction(@Body Map<String, String> requestFields);

    @POST(EOSUrlHelper.HD_URL_QUERY_ACCOUNT)
    Call<KeyAccounts> getKeyAccounts(@Body Map<String, String> requestFields);

    @POST(EOSUrlHelper.HD_URL_GET_BALANCE)
    Call<String[]> getBalance(@Body Map<String, String> requestFields);

}
