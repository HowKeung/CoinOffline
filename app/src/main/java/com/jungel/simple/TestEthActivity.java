package com.jungel.simple;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.TextView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.jungel.bean.ERCTransaction;
import com.jungel.coinoffline.eth.AndroidWalletUtils;
import com.jungel.coinoffline.eth.NumberMathUtil;
import com.jungel.util.StaticValue;
import com.jungel.util.TestOKHttp;
import com.jungel.util.TestUtil;

import org.consenlabs.tokencore.wallet.model.ChainId;
import org.consenlabs.tokencore.wallet.transaction.EthereumSign;
import org.consenlabs.tokencore.wallet.transaction.EthereumTransaction;
import org.web3j.crypto.Bip39Wallet;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jFactory;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.DefaultBlockParameterNumber;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.EthCall;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.EthGetCode;
import org.web3j.protocol.core.methods.response.EthTransaction;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Numeric;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;

import okhttp3.ResponseBody;

public class TestEthActivity extends BaseActivity {

    private static final String TAG = "TestEthActivity";

    private TextView textInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eth);
        textInfo = findViewById(R.id.textInfo);
        testWeb3j();
    }

    private void testWeb3j() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //                String url = "http://10.0.2.2:8545";
                //                String url = "https://kovan.infura.io/yXDUNwlNOcx0UJCWjzNr";
                //                String url = "https://kovan.infura.io/<your-token>";
                //                String url = "https://mainnet.infura.io/<your-token>";
                //                String url = "https://rinkeby.infura.io/<your-token>";
                //                String url = "https://ropsten.infura.io/<your-token>";
                //                String url = "http://120.55.57.177:12170";
                String url = "https://kovan.infura.io/d847f0b158ce4aa08ff5a5c7278a5884kovan" +
                        ".infura" +
                        ".io/v3/d847f0b158ce4aa08ff5a5c7278a5884";
                final StringBuilder stringBuilder = new StringBuilder();
                try {
                    Web3j web3 = Web3jFactory.build(new HttpService(url));  // defaults to
                    // http://localhost:8545/
                    Web3ClientVersion web3ClientVersion = web3.web3ClientVersion().sendAsync()
                            .get();

                    String clientVersion = web3ClientVersion.getWeb3ClientVersion();
                    stringBuilder.append("clientVersion : " + clientVersion + "\n\n");

                    EthBlockNumber ethBlockNumber = web3.ethBlockNumber().sendAsync().get();//
                    // 获取最新的区块高度
                    stringBuilder.append("number : " + ethBlockNumber.getBlockNumber() + "\n\n");

                    requestPermission();
                    //                    String filePath = Environment
                    // .getExternalStorageDirectory().toString() + "/Pictures";
                    //                    String fileName = WalletUtils.generateNewWalletFile
                    // ("password", new File(filePath),
                    // false);
                    //                    Credentials credentials = WalletUtils.loadCredentials
                    // ("password", filePath + "/" +
                    // fileName);
                    //                    stringBuilder.append("getAddress : " + credentials
                    // .getAddress() + "\n\n");
                    //                    stringBuilder.append("getPrivateKey : " + credentials
                    // .getEcKeyPair().getPrivateKey()
                    // .toString() + "\n\n");

                    String filePath1 = Environment.getExternalStorageDirectory().toString() +
                            "/Pictures/bip39";
                    File tmp = new File(filePath1);
                    if (!tmp.exists()) {
                        tmp.mkdir();
                    }
                    Bip39Wallet bip39Wallet = AndroidWalletUtils.generateBip39Wallet("password",
                            new File(filePath1));
                    stringBuilder.append("getFilename : " + bip39Wallet.getFilename() + "\n\n");
                    stringBuilder.append("getMnemonic : " + bip39Wallet.getMnemonic() + "\n\n");
                    Log.d(TAG, "getMnemonic : " + bip39Wallet.getMnemonic());
                    Credentials credentials1 = WalletUtils.loadBip39Credentials("password",
                            bip39Wallet.getMnemonic());
                    stringBuilder.append("getAddress : " + credentials1.getAddress() + "\n\n");
                    stringBuilder.append("getPrivateKey : " + credentials1.getEcKeyPair()
                            .getPrivateKey().toString() + "\n\n");

                    String[] mnemonics = bip39Wallet.getMnemonic().split(" ");
                    String mnemonic = "";

                    for (int i = 0; i < mnemonics.length; i++) {
                        if (i == 0) {
                            mnemonic = mnemonics[i];
                        } else {
                            mnemonic += " " + mnemonics[i];
                        }
                    }

                    mnemonic = "abandon abandon abandon abandon abandon abandon abandon abandon " +
                            "abandon abandon abandon about";

                    stringBuilder.append("mnemonic2 : " + mnemonic + "\n\n");
                    //                    Credentials credentials2 = WalletUtils
                    // .loadBip39Credentials("password", mnemonic);
                    Credentials credentials2 = WalletUtils.loadBip39Credentials("TREZOR", mnemonic);
                    stringBuilder.append("getAddress2 : " + credentials2.getAddress() + "\n\n");
                    stringBuilder.append("getPrivateKey2 : " + credentials2.getEcKeyPair()
                            .getPrivateKey().toString() + "\n\n");
                    stringBuilder.append("getPrivateKeyHex : " + strTo16
                            (credentials2.getEcKeyPair().getPrivateKey().toString()
                            ) + "\n\n");

                    stringBuilder.append("isSame : " + credentials2.getAddress().equals
                            (credentials1.getAddress()) + "\n\n");

                    String addr = "0x9a64b3384eb387e116ce845ac00567029df6bd23";
                    //                    EthGetBalance ethGetBalance = web3.ethGetBalance
                    // (credentials.getAddress(),
                    // DefaultBlockParameterName.LATEST).sendAsync().get();
                    EthGetBalance ethGetBalance = web3.ethGetBalance(addr,
                            DefaultBlockParameterName.LATEST).sendAsync().get();
                    stringBuilder.append("getBalance : " + NumberMathUtil.weiToEth(new BigDecimal
                            (ethGetBalance.getBalance()))
                            .toPlainString() + "\n\n");

                    EthGetCode ethGetCode = web3.ethGetCode(addr, DefaultBlockParameterName
                            .LATEST).sendAsync().get();
                    stringBuilder.append("getCode : " + ethGetCode.getCode() + "\n\n");

                    //                    EthGetTransactionCount ethGetTransactionCount =
                    // web3.ethGetTransactionCount(addr,
                    // DefaultBlockParameterName.LATEST).sendAsync().get();
                    //                    stringBuilder.append("getTransactionCount1 : " +
                    // ethGetTransactionCount
                    // .getTransactionCount() + "\n\n");
                    //
                    //                    EthGetBlockTransactionCountByNumber
                    // ethGetBlockTransactionCountByNumber =
                    // web3.ethGetBlockTransactionCountByNumber(DefaultBlockParameterName.LATEST)
                    // .sendAsync().get();
                    //                    stringBuilder.append("getTransactionCount2 : " +
                    // ethGetBlockTransactionCountByNumber
                    // .getTransactionCount() + "\n\n");
                    //                    for (int i = 0; i < ethGetBlockTransactionCountByNumber
                    // .getTransactionCount()
                    // .longValue(); i++) {
                    //                        BigInteger index = new BigInteger(String.valueOf(i));
                    //                        EthTransaction ethTransaction =
                    // web3.ethGetTransactionByBlockNumberAndIndex
                    // (DefaultBlockParameterName.LATEST, index).sendAsync().get();
                    //                        if (ethTransaction.getTransaction() == null) {
                    //                            continue;
                    //                        }
                    //                        if (addr.equals(ethTransaction.getTransaction()
                    // .getFrom()) || addr.equals
                    // (ethTransaction.getTransaction().getTo())) {
                    //                            stringBuilder.append("index : " + i + "\n");
                    //                            stringBuilder.append("getFrom : " +
                    // ethTransaction.getTransaction().getFrom()
                    // + "\n");
                    //                            stringBuilder.append("getTo : " +
                    // ethTransaction.getTransaction().getTo() +
                    // "\n");
                    //                            stringBuilder.append("getGas : " +
                    // ethTransaction.getTransaction().getGas() +
                    // "\n\n");
                    //                        }
                    //                    }
                    long number = 6181168l;
                    BigInteger index = new BigInteger(String.valueOf(140));
                    EthTransaction ethTransaction = web3.ethGetTransactionByBlockNumberAndIndex
                            (new DefaultBlockParameterNumber
                                    (number), index).sendAsync().get();
                    if (addr.equals(ethTransaction.getTransaction().getFrom()) || addr.equals
                            (ethTransaction.getTransaction()
                                    .getTo())) {
                        stringBuilder.append("getFrom : " + ethTransaction.getTransaction()
                                .getFrom() + "\n");
                        stringBuilder.append("getTo : " + ethTransaction.getTransaction().getTo()
                                + "\n");
                        stringBuilder.append("getGas : " + ethTransaction.getTransaction().getGas
                                () + "\n");
                        stringBuilder.append("getCreates : " + ethTransaction.getTransaction()
                                .getCreates() + "\n");
                        stringBuilder.append("getBlockHash : " + ethTransaction.getTransaction()
                                .getBlockHash() + "\n");
                        stringBuilder.append("getNonce : " + ethTransaction.getTransaction()
                                .getNonce() + "\n");
                        stringBuilder.append("getR : " + ethTransaction.getTransaction().getR() +
                                "\n");
                        stringBuilder.append("getS : " + ethTransaction.getTransaction().getS() +
                                "\n");
                        stringBuilder.append("getValue : " + ethTransaction.getTransaction()
                                .getValue() + "\n");
                        stringBuilder.append("getV : " + ethTransaction.getTransaction().getV() +
                                "\n");
                        stringBuilder.append("getInput : " + ethTransaction.getTransaction()
                                .getInput() + "\n");
                    }

                    EthCall ethCall = web3.ethCall(Transaction.createEthCallTransaction(
                            "0x0092bebe0ea0a47cCca053773564d92622FeDDC2",
                            "0x0092bebe0ea0a47ccca053773564d92622feddc2",
                            "0x70a082310000000000000000000000000x0092bebe0ea0a47cCca053773564d92622FeDDC2"),
                            DefaultBlockParameterName.PENDING).sendAsync().get();
                    stringBuilder.append("ethCall : " + ethCall.getValue() + "\n");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (CipherException e) {
                    e.printStackTrace();
                }
                //                catch (InvalidAlgorithmParameterException e) {
                //                    e.printStackTrace();
                //                } catch (NoSuchAlgorithmException e) {
                //                    e.printStackTrace();
                //                } catch (NoSuchProviderException e) {
                //                    e.printStackTrace();
                //                }
                updateUI(new Runnable() {
                    @Override
                    public void run() {
                        textInfo.setText(stringBuilder);
                    }
                });
            }
        }).start();
    }

    private void requestPermission() {
        int REQUEST_EXTERNAL_STORAGE = 1;
        String[] PERMISSIONS_STORAGE = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };
        int permission = ActivityCompat.checkSelfPermission(this, Manifest.permission
                .WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    this,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    /**
     * 字符串转化成为16进制字符串
     *
     * @param s
     * @return
     */
    public static String strTo16(String s) {
        String str = "";
        for (int i = 0; i < s.length(); i++) {
            int ch = (int) s.charAt(i);
            String s4 = Integer.toHexString(ch);
            str = str + s4;
        }
        return str;
    }

    public static void main(String[] args) {
        String url = "https://kovan.infura.io/d847f0b158ce4aa08ff5a5c7278a5884kovan.infura" +
                ".io/v3/d847f0b158ce4aa08ff5a5c7278a5884";
        Web3j web3 = Web3jFactory.build(new HttpService(url));  // defaults to
        // http://localhost:8545/
        try {
            EthGetBalance ethGetBalance = web3.ethGetBalance
                    ("0x0092bebe0ea0a47cCca053773564d92622FeDDC2",
                            DefaultBlockParameterName.LATEST).sendAsync().get();

            System.out.println("balance : " + NumberMathUtil.weiToEth(
                    new BigDecimal(ethGetBalance.getBalance())).toPlainString());

            EthCall ethCall = web3.ethCall(Transaction.createEthCallTransaction(
                    "0x0092bebe0ea0a47cCca053773564d92622FeDDC2",
                    "0xf5f63a468117d28890f41075d36955720339524d",
                    "0x70a08231000000000000000000000000" +
                            "0092bebe0ea0a47cCca053773564d92622FeDDC2"),
                    DefaultBlockParameterName.PENDING).sendAsync().get();
            System.out.println("ethCall : " + ethCall.getValue());
            String balance =
                    /*"0x00000000000000000000000000000000000000000000000aca4fde3059fd1150
                    "*/ethCall.getValue();
            BigInteger bigInteger = new BigInteger(balance.substring(2), 16);
            System.out.println("balance : " + NumberMathUtil.weiToEth(new BigDecimal(bigInteger)));

            //            Admin admin = AdminFactory.build(new HttpService(url));
            //            admin.personalListAccounts();

            String testUrl = "http://api-kovan.etherscan.io/api?module=account&action=tokentx&" +
                    "address=" + "0x0092bebe0ea0a47cCca053773564d92622FeDDC2" +
                    "&startblock=0&endblock=999999999&sort=asc&" +
                    "apikey=" + "TEC16QY2UBR74HEB49N949GDJDEX773NHP";

            ResponseBody responseBody = TestOKHttp.testGetClient(testUrl);

            try {
                ERCTransaction transaction = new Gson().fromJson(responseBody.string(),
                        ERCTransaction.class);

                List<ERCTransaction.Result> results = new ArrayList<>();
                for (ERCTransaction.Result result : transaction.getResult()) {
                    if (result.getTokenSymbol().equalsIgnoreCase("TKN")) {
                        results.add(result);
                    }
                }
                for (ERCTransaction.Result result : results) {
                    System.out.println("getBlockHash : " + result.getHash());
                    System.out.println("getValue : " + result.getValue());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }



        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
