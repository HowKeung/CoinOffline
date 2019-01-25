package com.jungel.coinoffline.vns.bean;

import com.jungel.coinoffline.eth.NumberMathUtil;

import java.math.BigDecimal;
import java.util.List;

public class VNSTransaction {

    private String page;
    private int total;
    private List<Rows> rows;

    public void setPage(String page) {
        this.page = page;
    }

    public String getPage() {
        return page;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal() {
        return total;
    }

    public void setRows(List<Rows> rows) {
        this.rows = rows;
    }

    public List<Rows> getRows() {
        return rows;
    }

    /**
     * Auto-generated: 2018-11-30 12:21:32
     *
     * @author bejson.com (i@bejson.com)
     * @website http://www.bejson.com/java2pojo/
     */
    public static class Rows {

        private int currentPage;
        private int pageSize;
        private String search;
        private String beginDate;
        private String endDate;
        private long id;
        private String hash;
        private String blockHash;
        private long blockNumber;
        private String creates;
        private String fromAddress;
        private String toAddress;
        private String txValue;
        private String txValueStr;
        private String gas;
        private String gasPrice;
        private String gasPriceStr;
        private String input;
        private String nonce;
        private String publicKey;
        private String raw;
        private String r;
        private String s;
        private int v;
        private String transactionIndex;
        private String contractAddress;
        private int transactionType;
        private long transactionTime;
        private long transactionPoundage;
        private String transactionPoundageStr;
        private String tokenHash;
        private int blockTime;
        private String blockTimeStr;
        private String tokenEnName;
        private String gasUsed;
        private String status;
        private String userAddress;
        private int findType;
        private int beginIndex;

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public int getCurrentPage() {
            return currentPage;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setSearch(String search) {
            this.search = search;
        }

        public String getSearch() {
            return search;
        }

        public void setBeginDate(String beginDate) {
            this.beginDate = beginDate;
        }

        public String getBeginDate() {
            return beginDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setId(long id) {
            this.id = id;
        }

        public long getId() {
            return id;
        }

        public void setHash(String hash) {
            this.hash = hash;
        }

        public String getHash() {
            return hash;
        }

        public void setBlockHash(String blockHash) {
            this.blockHash = blockHash;
        }

        public String getBlockHash() {
            return blockHash;
        }

        public void setBlockNumber(long blockNumber) {
            this.blockNumber = blockNumber;
        }

        public long getBlockNumber() {
            return blockNumber;
        }

        public void setCreates(String creates) {
            this.creates = creates;
        }

        public String getCreates() {
            return creates;
        }

        public void setFromAddress(String fromAddress) {
            this.fromAddress = fromAddress;
        }

        public String getFromAddress() {
            return fromAddress;
        }

        public void setToAddress(String toAddress) {
            this.toAddress = toAddress;
        }

        public String getToAddress() {
            return toAddress;
        }

        public void setTxValue(String txValue) {
            this.txValue = txValue;
        }

        public String getTxValue() {
            return txValue;
        }

        public void setTxValueStr(String txValueStr) {
            this.txValueStr = txValueStr;
        }

        public String getTxValueStr() {
            return txValueStr;
        }

        public void setGas(String gas) {
            this.gas = gas;
        }

        public String getGas() {
            return gas;
        }

        public void setGasPrice(String gasPrice) {
            this.gasPrice = gasPrice;
        }

        public String getGasPrice() {
            return gasPrice;
        }

        public void setGasPriceStr(String gasPriceStr) {
            this.gasPriceStr = gasPriceStr;
        }

        public String getGasPriceStr() {
            return gasPriceStr;
        }

        public void setInput(String input) {
            this.input = input;
        }

        public String getInput() {
            return input;
        }

        public void setNonce(String nonce) {
            this.nonce = nonce;
        }

        public String getNonce() {
            return nonce;
        }

        public void setPublicKey(String publicKey) {
            this.publicKey = publicKey;
        }

        public String getPublicKey() {
            return publicKey;
        }

        public void setRaw(String raw) {
            this.raw = raw;
        }

        public String getRaw() {
            return raw;
        }

        public void setR(String r) {
            this.r = r;
        }

        public String getR() {
            return r;
        }

        public void setS(String s) {
            this.s = s;
        }

        public String getS() {
            return s;
        }

        public void setV(int v) {
            this.v = v;
        }

        public int getV() {
            return v;
        }

        public void setTransactionIndex(String transactionIndex) {
            this.transactionIndex = transactionIndex;
        }

        public String getTransactionIndex() {
            return transactionIndex;
        }

        public void setContractAddress(String contractAddress) {
            this.contractAddress = contractAddress;
        }

        public String getContractAddress() {
            return contractAddress;
        }

        public void setTransactionType(int transactionType) {
            this.transactionType = transactionType;
        }

        public int getTransactionType() {
            return transactionType;
        }

        public void setTransactionTime(long transactionTime) {
            this.transactionTime = transactionTime;
        }

        public long getTransactionTime() {
            return transactionTime;
        }

        public void setTransactionPoundage(long transactionPoundage) {
            this.transactionPoundage = transactionPoundage;
        }

        public long getTransactionPoundage() {
            return transactionPoundage;
        }

        public void setTransactionPoundageStr(String transactionPoundageStr) {
            this.transactionPoundageStr = transactionPoundageStr;
        }

        public String getTransactionPoundageStr() {
            return transactionPoundageStr;
        }

        public void setTokenHash(String tokenHash) {
            this.tokenHash = tokenHash;
        }

        public String getTokenHash() {
            return tokenHash;
        }

        public void setBlockTime(int blockTime) {
            this.blockTime = blockTime;
        }

        public int getBlockTime() {
            return blockTime;
        }

        public void setBlockTimeStr(String blockTimeStr) {
            this.blockTimeStr = blockTimeStr;
        }

        public String getBlockTimeStr() {
            return blockTimeStr;
        }

        public void setTokenEnName(String tokenEnName) {
            this.tokenEnName = tokenEnName;
        }

        public String getTokenEnName() {
            return tokenEnName;
        }

        public void setGasUsed(String gasUsed) {
            this.gasUsed = gasUsed;
        }

        public String getGasUsed() {
            return gasUsed;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getStatus() {
            return status;
        }

        public void setUserAddress(String userAddress) {
            this.userAddress = userAddress;
        }

        public String getUserAddress() {
            return userAddress;
        }

        public void setFindType(int findType) {
            this.findType = findType;
        }

        public int getFindType() {
            return findType;
        }

        public void setBeginIndex(int beginIndex) {
            this.beginIndex = beginIndex;
        }

        public int getBeginIndex() {
            return beginIndex;
        }

    }

    public static void main(String[] args) {
        BigDecimal decimal = new BigDecimal("1.0E19");
        System.out.println(NumberMathUtil.weiToEth(decimal));
    }
}
