package com.jungel.coinoffline.eos;

public class HDUrlHelper {

    public static final String TRANSFER = "transfer";
    public static final String NEW_ACCOUNT = "newaccount";

    public static final String TRANSACTION_CODE = "eosio.token";
    public static final String ACCOUNT_CODE = "eosio";

    public static final String EOS = " EOS";

    //    public static final String TEST_CREATOR = "123jungel123";
    //    public static final String TEST_CREATOR = "kew123451kew";
    public static final String TEST_CREATOR = "apengoffice1";

    public static final long TIME_EXP = 60l;
    public static final long COUNT_BUY_RAM = 8000l;

    public static final String CREATOR_ACCOUNT = "123jungel123";
    public static final String CREATOR_KEY = "5JCvpCqyVwvjDCeDhS2MNAkBZgEbh7GuRkiH1aKt1yxq5ibrc4Y";


    //    public static final String HD_MAIN_URL = "https://mainnet-eos.token.im";
    public static final String HD_MAIN_URL = "http://jungle.cryptolions.io:18888";
    public static final String HD_WALLET_URL = "http://jungle.cryptolions.io:8899";

    /**
     * 序列化新建账号的json
     **/
    public static final String HD_URL_ABI_JSON_TO_BIN = "/v1/chain/abi_json_to_bin";

    /**
     * 签名新建账号的交易
     **/
    public static final String HD_URL_SIGN_TRANSACTION = "/v1/wallet/sign_transaction";

    /**
     * 把签名后的交易push 推送到 EOS 系统中，即新建账号完成
     **/
    public static final String HD_URL_PUSH_TRANSACTION = "/v1/chain/push_transaction";

    /**
     * 获取 EOS 区块链的最新区块号
     **/
    public static final String HD_URL_GET_INFO = "/v1/chain/get_info";

    /**
     * 获取最新区块的具体信息
     **/
    public static final String HD_URL_GET_BLOCK = "/v1/chain/get_block";

    /**
     * 根据账户名获取账户详情
     **/
    public static final String HD_URL_GET_ACCOUNT = "/v1/chain/get_account";

    /**
     * 指定代币合约，获取账户中该代币的余额
     **/
    public static final String HD_URL_GET_BALANCE = "/v1/chain/get_currency_balance";

    /**
     * 获取某种资产的详情
     **/
    public static final String HD_URL_GET_STATS = "/v1/chain/get_currency_stats";

    /**
     * 获取交易记录
     */
    public static final String HD_URL_TRADE_HISTORY = "/v1/history/get_actions";

    /**
     * 根据公钥查询账户
     **/
    public static final String HD_URL_QUERY_ACCOUNT = "/v1/history/get_key_accounts";

    /**
     * 解锁钱包，签名交易前，需要解锁账号所在的钱包
     **/
    public static final String HD_URL_UNLOCK_WALLET = "/v1/wallet/unlock";
}
