package com.jungel.simple;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.bitcoinj.core.Address;
import org.bitcoinj.core.BlockChain;
import org.bitcoinj.core.CheckpointManager;
import org.bitcoinj.core.Coin;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.InsufficientMoneyException;
import org.bitcoinj.core.Peer;
import org.bitcoinj.core.PeerAddress;
import org.bitcoinj.core.PeerGroup;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.core.listeners.PeerConnectedEventListener;
import org.bitcoinj.core.listeners.PeerDisconnectedEventListener;
import org.bitcoinj.core.listeners.PeerDiscoveredEventListener;
import org.bitcoinj.net.discovery.MultiplexingDiscovery;
import org.bitcoinj.net.discovery.PeerDiscovery;
import org.bitcoinj.net.discovery.PeerDiscoveryException;
import org.bitcoinj.params.TestNet3Params;
import org.bitcoinj.store.BlockStoreException;
import org.bitcoinj.store.SPVBlockStore;
import org.bitcoinj.utils.MonetaryFormat;
import org.bitcoinj.wallet.SendRequest;
import org.bitcoinj.wallet.Wallet;
import org.bitcoinj.wallet.listeners.WalletCoinsReceivedEventListener;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static com.google.common.net.HttpHeaders.USER_AGENT;
import static com.jungel.coinoffline.btc.bitcoinj.BitUtil.getECKeyFromPriKey;

public class TestBtcActivity extends BaseActivity {

    private static final String TAG = "TestBtcActivity";

    private TextView textInfo;
    private PeerGroup peerGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btc);
        textInfo = findViewById(R.id.textInfo);


        new Thread(new Runnable() {
            @Override
            public void run() {
                TestNet3Params params = TestNet3Params.get();

                //创建区块链文件
                File blockChainFile = new File(getDir("blockstore", Context.MODE_PRIVATE),
                        "blockchain");
                //创建SPVBlockStore，管理区块数据
                SPVBlockStore blockStore = null;
                try {
                    blockStore = new SPVBlockStore(params, blockChainFile);
                } catch (BlockStoreException e) {
                    e.printStackTrace();
                }
                //加载检查点
                InputStream checkpointsInputStream = null;
                try {
                    checkpointsInputStream = getAssets().open("checkpoints-testnet.txt");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    CheckpointManager.checkpoint(params, checkpointsInputStream, blockStore,
                            864000l);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (BlockStoreException e) {
                    e.printStackTrace();
                }

                ECKey ecKey = getECKeyFromPriKey
                        ("53451c9af1debe2fcc2348310aa994e050692d99e4428bccc4c78b744615a760");
                List<ECKey> list = new ArrayList<>();
                list.add(ecKey);
                Wallet wallet = Wallet.fromKeys(params, list);
                //创建区块链对象
                try {
                    BlockChain blockChain = new BlockChain(params, wallet, blockStore);
                    startup(blockChain, wallet);
                } catch (BlockStoreException e) {
                    e.printStackTrace();
                }
                //监听比特币接受事件
                wallet.addCoinsReceivedEventListener(new WalletCoinsReceivedEventListener() {
                    @Override
                    public void onCoinsReceived(Wallet wallet, Transaction tx, Coin prevBalance,
                                                Coin newBalance) {
                        Log.d(TAG, "balance : " + newBalance.value);
                    }
                });
                //刷新余额
                Coin balance = wallet.getBalance(Wallet.BalanceType.ESTIMATED);

                Log.d(TAG, "balance : " + balance.value);

                String to = "2NF7TVrKRmBjvMXcoHfZ5fyzQgrR83xSv95";

                Address address = Address.fromBase58(params, to);
                //转账金额，以mBTC为单位
                String amount = "0.1";
                Coin coin = MonetaryFormat.MBTC.parse(amount);
                //创建请求
                SendRequest sendRequest = SendRequest.to(address, coin);
                try {
                    //创建Transaction
                    Transaction transaction = wallet.sendCoinsOffline(sendRequest);
                    peerGroup.broadcastTransaction(transaction);
                    //                    }
                } catch (InsufficientMoneyException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void startup(BlockChain blockChain, Wallet wallet) {
        Log.d(TAG, "startup: ");

        TestNet3Params params = TestNet3Params.get();

        peerGroup = new PeerGroup(params, blockChain);
        peerGroup.setDownloadTxDependencies(0); // recursive implementation causes
        // StackOverflowError
        peerGroup.addWallet(wallet);//设置钱包，重要
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(),
                    PackageManager.GET_ACTIVITIES);
            peerGroup.setUserAgent(USER_AGENT, packageInfo.versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        peerGroup.setMaxConnections(8);
        int connectTimeout = (int) (15 * DateUtils.SECOND_IN_MILLIS);
        peerGroup.setConnectTimeoutMillis(connectTimeout);
        int discoveryTimeout = (int) (10 * DateUtils.SECOND_IN_MILLIS);
        peerGroup.addConnectedEventListener(new PeerConnectedEventListener() {
            @Override
            public void onPeerConnected(Peer peer, int peerCount) {

            }
        });
        peerGroup.addDisconnectedEventListener(new PeerDisconnectedEventListener() {
            @Override
            public void onPeerDisconnected(Peer peer, int peerCount) {

            }
        });
        peerGroup.addDiscoveredEventListener(new PeerDiscoveredEventListener() {
            @Override
            public void onPeersDiscovered(Set<PeerAddress> peerAddresses) {

            }
        });
        peerGroup.setPeerDiscoveryTimeoutMillis(discoveryTimeout);

        //添加节点探索器，重要
        peerGroup.addPeerDiscovery(new PeerDiscovery() {
            private final PeerDiscovery normalPeerDiscovery = MultiplexingDiscovery
                    .forServices(params, 0);

            @Override
            public InetSocketAddress[] getPeers(final long services, final long timeoutValue,
                                                final TimeUnit timeoutUnit) throws
                    PeerDiscoveryException {
                return normalPeerDiscovery.getPeers(services, timeoutValue, timeoutUnit);
            }

            @Override
            public void shutdown() {
                normalPeerDiscovery.shutdown();
            }
        });
        peerGroup.startAsync();
        peerGroup.startBlockChainDownload(null);
    }
}
