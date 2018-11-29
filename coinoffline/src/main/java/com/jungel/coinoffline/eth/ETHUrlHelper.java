package com.jungel.coinoffline.eth;

public class ETHUrlHelper {
    public static final String HD_MAIN_URL = "https://mainnet.infura.io";

    public static final String HD_TEST_URL = "https://ropsten.infura.io";

    public static final String PATH_HD_WALLET = "/wallet";
    public static final String KEY_WALLET_MNEMONIC = "key_wallet_mnemonic";

    public static final String ETH_API_KEY = "AA4NZ17KY4WX1D84NIEAV554H555CYXQKD";

    public static final String HD_MAIN_HISTORY_URL = "https://api-ropsten.etherscan.io";
    public static final String HD_TEST_HISTORY_URL = "https://api-ropsten.etherscan.io";

    public static final String HD_URL_ETH_HISTORY =
            "/api?module=account&action=txlist&sort=desc&apikey=" + ETH_API_KEY;

    public static final String HD_URL_ERC_HISTORY =
            "/api?module=account&action=tokentx&sort=desc&apikey=" + ETH_API_KEY;
}
