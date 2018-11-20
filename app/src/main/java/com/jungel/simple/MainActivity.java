package com.jungel.simple;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.jungel.coinoffline.eos.eospocket.blockchain.cypto.digest.Sha256;
import com.jungel.coinoffline.eth.ETHUrlHelper;

import org.bitcoinj.core.Base58;
import org.consenlabs.tokencore.wallet.Identity;
import org.consenlabs.tokencore.wallet.KeystoreStorage;
import org.consenlabs.tokencore.wallet.Wallet;
import org.consenlabs.tokencore.wallet.WalletManager;
import org.consenlabs.tokencore.wallet.keystore.Keystore;
import org.consenlabs.tokencore.wallet.model.BIP44Util;
import org.consenlabs.tokencore.wallet.model.ChainId;
import org.consenlabs.tokencore.wallet.model.ChainType;
import org.consenlabs.tokencore.wallet.model.Metadata;
import org.consenlabs.tokencore.wallet.model.MnemonicAndPath;
import org.consenlabs.tokencore.wallet.model.Network;
import org.consenlabs.tokencore.wallet.transaction.EthereumSign;
import org.consenlabs.tokencore.wallet.transaction.EthereumTransaction;
import org.consenlabs.tokencore.wallet.transaction.TxSignResult;
import org.web3j.crypto.Bip39Wallet;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jFactory;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGasPrice;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements KeystoreStorage {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.textEth).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TestEthActivity.class));
            }
        });
        findViewById(R.id.textEos).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TestEosActivity.class));
            }
        });
        findViewById(R.id.textBtc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TestBtcActivity.class));
            }
        });

        WalletManager.storage = this;
        WalletManager.scanWallets();

        Identity identity = Identity.createIdentity("MyFirstIdentity", "",
                "", Network.TESTNET, Metadata.P2WPKH);

        Wallet ethereumWallet0 = identity.getWallets().get(0);
        Wallet bitcoinWallet0 = identity.getWallets().get(1);

        Log.d(TAG, "ETH getAddress0 : " + ethereumWallet0.getAddress());
        Log.d(TAG, "ETH getEncXPub0 : " + ethereumWallet0.exportPrivateKey(""));

        Log.d(TAG, "BTC getAddress0 : " + bitcoinWallet0.getAddress());
        //        Log.d(TAG, "BTC getEncXPub : " + bitcoinWallet0.exportPrivateKey(""));

        Wallet ethereumWallet = WalletManager.importWalletFromPrivateKey(new Metadata
                        (ChainType.ETHEREUM, Network.KOVAN, "ETH", ""),
                "dc1c142d2cce9847a0a7d7311fdfee6bfb3968c583f06a6bccadd3497eed3b25",
                "", true);


        String mnemonic2 = "mystery thank evidence fever hub desk chef zoo nation surface skull " +
                "type";

        Wallet ethereumWallet1 = WalletManager.importWalletFromMnemonic(new Metadata
                (ChainType.ETHEREUM, Network.KOVAN, "ETH", ""), mnemonic2, BIP44Util
                .ETHEREUM_PATH, "", true);

        Log.d(TAG, "ETH getAddress1 : " + ethereumWallet1.getAddress());
        Log.d(TAG, "ETH exportPrivateKey1 : " + ethereumWallet1.exportPrivateKey(""));


        Wallet bitcoinWallet = WalletManager.importWalletFromPrivateKey(new Metadata(ChainType
                        .BITCOIN, Network.MAINNET, "BTC", ""),
                "Kz1aQfdiPZRPMCQU2aUBZLJmqDSHfu4T9NQU5BDrxXY4ysBHNhjL",
                "", true);

        Log.d(TAG, "ETH getAddress : " + ethereumWallet.getAddress());
        //        Log.d(TAG, "ETH getEncXPub : " + ethereumWallet.exportPrivateKey(""));

        Log.d(TAG, "BTC getAddress : " + bitcoinWallet.getAddress());
        //        Log.d(TAG, "BTC getEncXPub : " + bitcoinWallet.exportPrivateKey(""));
        Web3j web3j = Web3jFactory.build(new HttpService(ETHUrlHelper.LOCAL_WALLET_URL));


        try {
            EthGetTransactionCount ethGetTransactionCount = web3j.ethGetTransactionCount
                    ("0xc52618f62B67978aAa8bf432029Fde4940381400",
                            DefaultBlockParameterName.LATEST).sendAsync().get();
            EthGasPrice ethGasPrice = web3j.ethGasPrice().sendAsync().get();
            Log.d(TAG, "ETH getGasPrice : " + ethGasPrice.getGasPrice().toString(16));
            TxSignResult sign = new EthereumTransaction(
                    ethGetTransactionCount.getTransactionCount(),
                    ethGasPrice.getGasPrice(),
                    BigInteger.valueOf(21000),
                    "0x333a601c181CF9Dcb5893C1dDEafbFA96872F6c4",
                    BigInteger.valueOf(1000), ""
            ).signTransaction(String.valueOf(ChainId.ETHEREUM_KOVAN), "", ethereumWallet);

            Log.d(TAG, "ETH getSignedTx : " + sign.getSignedTx());

            EthSendTransaction sendTransaction = web3j.ethSendRawTransaction("0x" + sign
                    .getSignedTx())
                    .sendAsync().get();
            Log.d(TAG, "ETH getTransactionHash : " + sendTransaction.getTransactionHash());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    @Override
    public File getKeystoreDir() {
        Log.d(TAG, "getFilesDir : " + getFilesDir());
        return this.getFilesDir();
    }

    public static void main(String[] args) {
        String mnemonic0 = "addict drip toy cannon fork acquire twenty liberty voice steel middle" +
                " tray";
        String mnemonic1 = "abandon abandon abandon abandon abandon abandon abandon abandon " +
                "abandon abandon abandon about";
        String mnemonic2 = "mystery thank evidence fever hub desk chef zoo nation surface skull " +
                "type";
        Credentials credentials0 = WalletUtils.loadBip39Credentials("", mnemonic0);
        Credentials credentials1 = WalletUtils.loadBip39Credentials("", mnemonic1);
        Credentials credentials2 = WalletUtils.loadBip39Credentials("", mnemonic2);

        System.out.println("address0 : " + credentials0.getAddress());
        System.out.println("getPrivateKey0 : " + credentials0.getEcKeyPair().getPrivateKey()
                .toString(16));
        //        System.out.println("getPrivateKey : " + toWif(credentials0.getEcKeyPair()
        // .getPrivateKey()));
        //        System.out.println("getPublicKey : " + toWif(credentials0.getEcKeyPair()
        // .getPublicKey()));

        System.out.println("address1 : " + credentials1.getAddress());
        System.out.println("getPrivateKey1 : " + credentials1.getEcKeyPair().getPrivateKey()
                .toString(16));
        //        System.out.println("getPrivateKey : " + toWif(credentials1.getEcKeyPair()
        // .getPrivateKey()));
        //        System.out.println("getPublicKey : " + toWif(credentials1.getEcKeyPair()
        // .getPublicKey()));

        System.out.println("address2 : " + credentials2.getAddress());
        System.out.println("getPrivateKey2 : " + credentials2.getEcKeyPair().getPrivateKey()
                .toString(16));
        //        System.out.println("getPrivateKey : " + toWif(credentials2.getEcKeyPair()
        // .getPrivateKey()));
        //        System.out.println("getPublicKey : " + toWif(credentials2.getEcKeyPair()
        // .getPublicKey()));
        String s = Base58.decodeToBigInteger
                ("L5BJCReU2JCn28MV8HD51oWwNQywg437Cr2zJNCT5A3kpfV91uCp").toString(16);
        System.out.println("s : " + s);

    }

    public static String toWif(BigInteger bigInteger) {
        byte[] rawPrivKey = getBytes(bigInteger);
        byte[] resultWIFBytes = new byte[37];
        resultWIFBytes[0] = -128;
        System.arraycopy(rawPrivKey, rawPrivKey.length > 32 ? 1 : 0, resultWIFBytes, 1, 32);
        Sha256 hash = Sha256.doubleHash(resultWIFBytes, 0, 33);
        System.arraycopy(hash.getBytes(), 0, resultWIFBytes, 33, 4);
        return com.jungel.coinoffline.eos.eospocket.blockchain.cypto.util.Base58.encode
                (resultWIFBytes);
    }

    public static byte[] getBytes(BigInteger value) {
        byte[] result = new byte[32];
        byte[] bytes = value.toByteArray();
        if (bytes.length <= result.length) {
            System.arraycopy(bytes, 0, result, result.length - bytes.length, bytes.length);
        } else {
            assert bytes.length == 33 && bytes[0] == 0;

            System.arraycopy(bytes, 1, result, 0, bytes.length - 1);
        }

        return result;
    }
}
