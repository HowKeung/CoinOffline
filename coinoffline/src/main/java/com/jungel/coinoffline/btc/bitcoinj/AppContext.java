package com.jungel.coinoffline.btc.bitcoinj;

import com.jungel.coinoffline.CoinOffline;

import org.bitcoinj.kits.WalletAppKit;

public class AppContext {

    public static final WalletAppKit walletAppKit = new WalletAppKit(BitUtil.getParams(),
            CoinOffline.getContext().getCacheDir(), Constant.WALLET_NAME);
}
