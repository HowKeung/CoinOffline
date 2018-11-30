package com.jungel.coinoffline.vns.bean;

import java.util.List;

public class VNSContractTransaction {

    private String jsonrpc;
    private int id;
    private List<Result> result;
    public void setJsonrpc(String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }
    public String getJsonrpc() {
        return jsonrpc;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }
    public List<Result> getResult() {
        return result;
    }

    /**
     * Auto-generated: 2018-11-30 21:9:6
     *
     * @author bejson.com (i@bejson.com)
     * @website http://www.bejson.com/java2pojo/
     */
    public static class Result {

        private String address;
        private List<String> topics;
        private String data;
        private String blockNumber;
        private String transactionHash;
        private String transactionIndex;
        private String blockHash;
        private String logIndex;
        private boolean removed;
        public void setAddress(String address) {
            this.address = address;
        }
        public String getAddress() {
            return address;
        }

        public void setTopics(List<String> topics) {
            this.topics = topics;
        }
        public List<String> getTopics() {
            return topics;
        }

        public void setData(String data) {
            this.data = data;
        }
        public String getData() {
            return data;
        }

        public void setBlockNumber(String blockNumber) {
            this.blockNumber = blockNumber;
        }
        public String getBlockNumber() {
            return blockNumber;
        }

        public void setTransactionHash(String transactionHash) {
            this.transactionHash = transactionHash;
        }
        public String getTransactionHash() {
            return transactionHash;
        }

        public void setTransactionIndex(String transactionIndex) {
            this.transactionIndex = transactionIndex;
        }
        public String getTransactionIndex() {
            return transactionIndex;
        }

        public void setBlockHash(String blockHash) {
            this.blockHash = blockHash;
        }
        public String getBlockHash() {
            return blockHash;
        }

        public void setLogIndex(String logIndex) {
            this.logIndex = logIndex;
        }
        public String getLogIndex() {
            return logIndex;
        }

        public void setRemoved(boolean removed) {
            this.removed = removed;
        }
        public boolean getRemoved() {
            return removed;
        }

    }
}
