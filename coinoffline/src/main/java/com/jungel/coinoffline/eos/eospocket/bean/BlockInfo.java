package com.jungel.coinoffline.eos.eospocket.bean;

import java.util.List;

public class BlockInfo extends ApiError {


    private String timestamp;
    private String producer;
    private String confirmed;
    private String previous;
    private String transaction_mroot;
    private String action_mroot;
    private String schedule_version;
    private String new_producers;
    private List<String> header_extensions;
    private String producer_signature;
    private List<Transactions> transactions;
    private List<String> block_extensions;
    private String id;
    private String block_num;
    private Long ref_block_prefix;

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getProducer() {
        return producer;
    }

    public void setConfirmed(String confirmed) {
        this.confirmed = confirmed;
    }

    public String getConfirmed() {
        return confirmed;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public String getPrevious() {
        return previous;
    }

    public void setTransaction_mroot(String transaction_mroot) {
        this.transaction_mroot = transaction_mroot;
    }

    public String getTransaction_mroot() {
        return transaction_mroot;
    }

    public void setAction_mroot(String action_mroot) {
        this.action_mroot = action_mroot;
    }

    public String getAction_mroot() {
        return action_mroot;
    }

    public void setSchedule_version(String schedule_version) {
        this.schedule_version = schedule_version;
    }

    public String getSchedule_version() {
        return schedule_version;
    }

    public void setNew_producers(String new_producers) {
        this.new_producers = new_producers;
    }

    public String getNew_producers() {
        return new_producers;
    }

    public void setHeader_extensions(List<String> header_extensions) {
        this.header_extensions = header_extensions;
    }

    public List<String> getHeader_extensions() {
        return header_extensions;
    }

    public void setProducer_signature(String producer_signature) {
        this.producer_signature = producer_signature;
    }

    public String getProducer_signature() {
        return producer_signature;
    }

    public void setTransactions(List<Transactions> transactions) {
        this.transactions = transactions;
    }

    public List<Transactions> getTransactions() {
        return transactions;
    }

    public void setBlock_extensions(List<String> block_extensions) {
        this.block_extensions = block_extensions;
    }

    public List<String> getBlock_extensions() {
        return block_extensions;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setBlock_num(String block_num) {
        this.block_num = block_num;
    }

    public String getBlock_num() {
        return block_num;
    }

    public void setRef_block_prefix(Long ref_block_prefix) {
        this.ref_block_prefix = ref_block_prefix;
    }

    public Long getRef_block_prefix() {
        return ref_block_prefix;
    }


    /**
     * Auto-generated: 2018-10-22 20:39:38
     *
     * @author bejson.com (i@bejson.com)
     * @website http://www.bejson.com/java2pojo/
     */
    public static class Authorization {

        private String actor;
        private String permission;

        public void setActor(String actor) {
            this.actor = actor;
        }

        public String getActor() {
            return actor;
        }

        public void setPermission(String permission) {
            this.permission = permission;
        }

        public String getPermission() {
            return permission;
        }

    }

    /**
     * Auto-generated: 2018-10-22 20:39:38
     *
     * @author bejson.com (i@bejson.com)
     * @website http://www.bejson.com/java2pojo/
     */
    public static class Data {

        private String from;
        private String receiver;
        private String stake_net_quantity;
        private String stake_cpu_quantity;
        private String transfer;

        public void setFrom(String from) {
            this.from = from;
        }

        public String getFrom() {
            return from;
        }

        public void setReceiver(String receiver) {
            this.receiver = receiver;
        }

        public String getReceiver() {
            return receiver;
        }

        public void setStake_net_quantity(String stake_net_quantity) {
            this.stake_net_quantity = stake_net_quantity;
        }

        public String getStake_net_quantity() {
            return stake_net_quantity;
        }

        public void setStake_cpu_quantity(String stake_cpu_quantity) {
            this.stake_cpu_quantity = stake_cpu_quantity;
        }

        public String getStake_cpu_quantity() {
            return stake_cpu_quantity;
        }

        public void setTransfer(String transfer) {
            this.transfer = transfer;
        }

        public String getTransfer() {
            return transfer;
        }

    }

    /**
     * Auto-generated: 2018-10-22 20:39:38
     *
     * @author bejson.com (i@bejson.com)
     * @website http://www.bejson.com/java2pojo/
     */
    public static class Actions {

        private String account;
        private String name;
        private List<Authorization> authorization;
        private Data data;
        private String hex_data;

        public void setAccount(String account) {
            this.account = account;
        }

        public String getAccount() {
            return account;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setAuthorization(List<Authorization> authorization) {
            this.authorization = authorization;
        }

        public List<Authorization> getAuthorization() {
            return authorization;
        }

        public void setData(Data data) {
            this.data = data;
        }

        public Data getData() {
            return data;
        }

        public void setHex_data(String hex_data) {
            this.hex_data = hex_data;
        }

        public String getHex_data() {
            return hex_data;
        }

    }

    /**
     * Auto-generated: 2018-10-22 20:39:38
     *
     * @author bejson.com (i@bejson.com)
     * @website http://www.bejson.com/java2pojo/
     */
    public static class Transaction {

        private String expiration;
        private String ref_block_num;
        private String ref_block_prefix;
        private String max_net_usage_words;
        private String max_cpu_usage_ms;
        private String delay_sec;
        private List<String> context_free_actions;
        private List<Actions> actions;
        private List<String> transaction_extensions;

        public void setExpiration(String expiration) {
            this.expiration = expiration;
        }

        public String getExpiration() {
            return expiration;
        }

        public void setRef_block_num(String ref_block_num) {
            this.ref_block_num = ref_block_num;
        }

        public String getRef_block_num() {
            return ref_block_num;
        }

        public void setRef_block_prefix(String ref_block_prefix) {
            this.ref_block_prefix = ref_block_prefix;
        }

        public String getRef_block_prefix() {
            return ref_block_prefix;
        }

        public void setMax_net_usage_words(String max_net_usage_words) {
            this.max_net_usage_words = max_net_usage_words;
        }

        public String getMax_net_usage_words() {
            return max_net_usage_words;
        }

        public void setMax_cpu_usage_ms(String max_cpu_usage_ms) {
            this.max_cpu_usage_ms = max_cpu_usage_ms;
        }

        public String getMax_cpu_usage_ms() {
            return max_cpu_usage_ms;
        }

        public void setDelay_sec(String delay_sec) {
            this.delay_sec = delay_sec;
        }

        public String getDelay_sec() {
            return delay_sec;
        }

        public void setContext_free_actions(List<String> context_free_actions) {
            this.context_free_actions = context_free_actions;
        }

        public List<String> getContext_free_actions() {
            return context_free_actions;
        }

        public void setActions(List<Actions> actions) {
            this.actions = actions;
        }

        public List<Actions> getActions() {
            return actions;
        }

        public void setTransaction_extensions(List<String> transaction_extensions) {
            this.transaction_extensions = transaction_extensions;
        }

        public List<String> getTransaction_extensions() {
            return transaction_extensions;
        }

    }

    /**
     * Auto-generated: 2018-10-22 20:39:38
     *
     * @author bejson.com (i@bejson.com)
     * @website http://www.bejson.com/java2pojo/
     */
    public static class Trx {

        private String id;
        private List<String> signatures;
        private String compression;
        private String packed_context_free_data;
        private List<String> context_free_data;
        private String packed_trx;
        private Transaction transaction;

        public void setId(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

        public void setSignatures(List<String> signatures) {
            this.signatures = signatures;
        }

        public List<String> getSignatures() {
            return signatures;
        }

        public void setCompression(String compression) {
            this.compression = compression;
        }

        public String getCompression() {
            return compression;
        }

        public void setPacked_context_free_data(String packed_context_free_data) {
            this.packed_context_free_data = packed_context_free_data;
        }

        public String getPacked_context_free_data() {
            return packed_context_free_data;
        }

        public void setContext_free_data(List<String> context_free_data) {
            this.context_free_data = context_free_data;
        }

        public List<String> getContext_free_data() {
            return context_free_data;
        }

        public void setPacked_trx(String packed_trx) {
            this.packed_trx = packed_trx;
        }

        public String getPacked_trx() {
            return packed_trx;
        }

        public void setTransaction(Transaction transaction) {
            this.transaction = transaction;
        }

        public Transaction getTransaction() {
            return transaction;
        }

    }

    /**
     * Auto-generated: 2018-10-22 20:39:38
     *
     * @author bejson.com (i@bejson.com)
     * @website http://www.bejson.com/java2pojo/
     */
    public static class Transactions {

        private String status;
        private int cpu_usage_us;
        private int net_usage_words;
        private Trx trx;

        public void setStatus(String status) {
            this.status = status;
        }

        public String getStatus() {
            return status;
        }

        public void setCpu_usage_us(int cpu_usage_us) {
            this.cpu_usage_us = cpu_usage_us;
        }

        public int getCpu_usage_us() {
            return cpu_usage_us;
        }

        public void setNet_usage_words(int net_usage_words) {
            this.net_usage_words = net_usage_words;
        }

        public int getNet_usage_words() {
            return net_usage_words;
        }

        public void setTrx(Trx trx) {
            this.trx = trx;
        }

        public Trx getTrx() {
            return trx;
        }

    }
}
