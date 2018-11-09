package com.jungel.coinoffline.btc.bitcoinj;

public class BtcData {
    private String address;
    private String balance;

    public BtcData(String address, String balance) {
        this.address = address;
        this.balance = balance;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
