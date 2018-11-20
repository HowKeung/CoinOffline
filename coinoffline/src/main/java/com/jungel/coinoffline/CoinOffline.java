package com.jungel.coinoffline;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.jungel.coinoffline.eos.eospocket.utils.Utils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.https.HttpsUtils;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.lzy.okgo.model.HttpHeaders;

import org.consenlabs.tokencore.wallet.Identity;
import org.consenlabs.tokencore.wallet.KeystoreStorage;
import org.consenlabs.tokencore.wallet.WalletManager;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import okhttp3.OkHttpClient;

public class CoinOffline implements KeystoreStorage {

    private static final String KEY_PIN_CODE = "key_pin_code";

    private boolean mIsShowPin = true;
    private Boolean mHasPinCode = null;

    private Context mContext;
    private static CoinOffline mInstance;

    public synchronized static CoinOffline getInstance() {
        if (mInstance == null) {
            mInstance = new CoinOffline();
        }
        return mInstance;
    }

    public synchronized Context getContext() {
        if (mContext == null) {
            throw new RuntimeException("must call init first");
        }
        return mContext;
    }

    public synchronized void init(Application context) {
        mContext = context.getApplicationContext();
        try {
            initOkGo(context);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Utils.init(context);
        WalletManager.storage = this;
        WalletManager.scanWallets();
    }

    public synchronized boolean hasPinCode() {
        if (mHasPinCode == null) {
            String pinCode = Utils.getSpUtils().getString(KEY_PIN_CODE);
            if (!TextUtils.isEmpty(pinCode)) {
                mHasPinCode = true;
            } else {
                mHasPinCode = false;
            }
        }
        return mHasPinCode;
    }

    public synchronized void savePinCode(String pinCode) {
        Utils.getSpUtils().put(KEY_PIN_CODE, pinCode);
    }

    public synchronized boolean isShowPin() {
        return mIsShowPin;
    }

    public synchronized void setShowPin(boolean showPin) {
        mIsShowPin = showPin;
    }

    public synchronized boolean hasCreateAccount() {
        if (Identity.getCurrentIdentity() == null) {
            return false;
        }
        return true;
    }

    @Override
    public File getKeystoreDir() {
        return mContext.getFilesDir();
    }

    public void initOkGo(Application context) throws IOException {
        HttpHeaders headers = new HttpHeaders();

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //log相关
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkHttp");
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
        //log打印级别，决定了log显示的详细程度
        loggingInterceptor.setColorLevel(Level.INFO);
        //log颜色级别，决定了log在控制台显示的颜色
        builder.addInterceptor(loggingInterceptor);
        //添加OkGo默认debug日志
        //超时时间设置
        builder.readTimeout(10000, TimeUnit.MILLISECONDS);      //全局的读取超时时间
        builder.writeTimeout(10000, TimeUnit.MILLISECONDS);     //全局的写入超时时间
        builder.connectTimeout(10000, TimeUnit.MILLISECONDS);   //全局的连接超时时间
        //使用sp保持cookie，如果cookie不过期，则一直有效


        //        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(getAssets()
        // .open("server" +
        //                ".cer"));
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory();
        builder.sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager);
        //        //配置https的域名匹配规则，使用不当会导致https握手失败
        builder.hostnameVerifier(HttpsUtils.UnSafeHostnameVerifier);

        // 其他统一的配置
        OkGo.getInstance().init(context)                           //必须调用初始化
                .setOkHttpClient(builder.build())               //必须设置OkHttpClient
                .setCacheMode(CacheMode.NO_CACHE)               //全局统一缓存模式，默认不使用缓存，可以不传
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   //全局统一缓存时间，默认永不过期，可以不传
                .setRetryCount(3)          //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
                .addCommonHeaders(headers);              //全局公共头
        //                .addCommonParams(httpParams);                       //全局公共参数

    }
}
