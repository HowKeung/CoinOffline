package com.jungel.coinoffline.eos.eospocket.bean;

import java.util.List;

public class TradeHistory extends ApiError {

    private List<Actions> actions;
    private long last_irreversible_block;

    public void setActions(List<Actions> actions) {
        this.actions = actions;
    }

    public List<Actions> getActions() {
        return actions;
    }

    public void setLast_irreversible_block(long last_irreversible_block) {
        this.last_irreversible_block = last_irreversible_block;
    }

    public long getLast_irreversible_block() {
        return last_irreversible_block;
    }

    /**
     * Auto-generated: 2018-10-18 21:12:46
     *
     * @author bejson.com (i@bejson.com)
     * @website http://www.bejson.com/java2pojo/
     */
    public static class Receipt {

        private String receiver;
        private String act_digest;
        private long global_sequence;
        private int recv_sequence;
        private List<List<String>> auth_sequence;
        private int code_sequence;
        private int abi_sequence;

        public void setReceiver(String receiver) {
            this.receiver = receiver;
        }

        public String getReceiver() {
            return receiver;
        }

        public void setAct_digest(String act_digest) {
            this.act_digest = act_digest;
        }

        public String getAct_digest() {
            return act_digest;
        }

        public void setGlobal_sequence(long global_sequence) {
            this.global_sequence = global_sequence;
        }

        public long getGlobal_sequence() {
            return global_sequence;
        }

        public void setRecv_sequence(int recv_sequence) {
            this.recv_sequence = recv_sequence;
        }

        public int getRecv_sequence() {
            return recv_sequence;
        }

        public void setAuth_sequence(List<List<String>> auth_sequence) {
            this.auth_sequence = auth_sequence;
        }

        public List<List<String>> getAuth_sequence() {
            return auth_sequence;
        }

        public void setCode_sequence(int code_sequence) {
            this.code_sequence = code_sequence;
        }

        public int getCode_sequence() {
            return code_sequence;
        }

        public void setAbi_sequence(int abi_sequence) {
            this.abi_sequence = abi_sequence;
        }

        public int getAbi_sequence() {
            return abi_sequence;
        }
    }

    /**
     * Auto-generated: 2018-10-18 21:12:46
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
     * Auto-generated: 2018-10-18 21:12:46
     *
     * @author bejson.com (i@bejson.com)
     * @website http://www.bejson.com/java2pojo/
     */
    public static class Data {

        private String from;
        private String to;
        private String quantity;
        private String memo;

        public void setFrom(String from) {
            this.from = from;
        }

        public String getFrom() {
            return from;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public String getTo() {
            return to;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public String getMemo() {
            return memo;
        }
    }

    /**
     * Auto-generated: 2018-10-18 21:12:46
     *
     * @author bejson.com (i@bejson.com)
     * @website http://www.bejson.com/java2pojo/
     */
    public static class Act {

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

    public static class Account_ram_deltas {
        private String account;
        private int delta;

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public int getDelta() {
            return delta;
        }

        public void setDelta(int delta) {
            this.delta = delta;
        }
    }

    /**
     * Auto-generated: 2018-10-18 21:12:46
     *
     * @author bejson.com (i@bejson.com)
     * @website http://www.bejson.com/java2pojo/
     */
    public static class Action_trace {

        private Receipt receipt;
        private Act act;
        private boolean context_free;
        private int elapsed;
        private int cpu_usage;
        private String console;
        private int total_cpu_usage;
        private String trx_id;
        private long block_num;
        private String block_time;
        private String producer_block_id;
        private List<Account_ram_deltas> account_ram_deltas;
//        private List<String> inline_traces;

        public void setReceipt(Receipt receipt) {
            this.receipt = receipt;
        }

        public Receipt getReceipt() {
            return receipt;
        }

        public void setAct(Act act) {
            this.act = act;
        }

        public Act getAct() {
            return act;
        }

        public void setContext_free(boolean context_free) {
            this.context_free = context_free;
        }

        public boolean getContext_free() {
            return context_free;
        }

        public void setElapsed(int elapsed) {
            this.elapsed = elapsed;
        }

        public int getElapsed() {
            return elapsed;
        }

        public void setCpu_usage(int cpu_usage) {
            this.cpu_usage = cpu_usage;
        }

        public int getCpu_usage() {
            return cpu_usage;
        }

        public void setConsole(String console) {
            this.console = console;
        }

        public String getConsole() {
            return console;
        }

        public void setTotal_cpu_usage(int total_cpu_usage) {
            this.total_cpu_usage = total_cpu_usage;
        }

        public int getTotal_cpu_usage() {
            return total_cpu_usage;
        }

        public void setTrx_id(String trx_id) {
            this.trx_id = trx_id;
        }

        public String getTrx_id() {
            return trx_id;
        }

        public void setBlock_num(long block_num) {
            this.block_num = block_num;
        }

        public long getBlock_num() {
            return block_num;
        }

        public void setBlock_time(String block_time) {
            this.block_time = block_time;
        }

        public String getBlock_time() {
            return block_time;
        }

        public void setProducer_block_id(String producer_block_id) {
            this.producer_block_id = producer_block_id;
        }

        public String getProducer_block_id() {
            return producer_block_id;
        }

        public void setAccount_ram_deltas(List<Account_ram_deltas> account_ram_deltas) {
            this.account_ram_deltas = account_ram_deltas;
        }

        public List<Account_ram_deltas> getAccount_ram_deltas() {
            return account_ram_deltas;
        }

//        public void setInline_traces(List<String> inline_traces) {
//            this.inline_traces = inline_traces;
//        }
//
//        public List<String> getInline_traces() {
//            return inline_traces;
//        }

    }

    /**
     * Auto-generated: 2018-10-18 21:12:46
     *
     * @author bejson.com (i@bejson.com)
     * @website http://www.bejson.com/java2pojo/
     */
    public class Actions {

        private long global_action_seq;
        private int account_action_seq;
        private long block_num;
        private String block_time;
        private Action_trace action_trace;

        public void setGlobal_action_seq(long global_action_seq) {
            this.global_action_seq = global_action_seq;
        }

        public long getGlobal_action_seq() {
            return global_action_seq;
        }

        public void setAccount_action_seq(int account_action_seq) {
            this.account_action_seq = account_action_seq;
        }

        public int getAccount_action_seq() {
            return account_action_seq;
        }

        public void setBlock_num(long block_num) {
            this.block_num = block_num;
        }

        public long getBlock_num() {
            return block_num;
        }

        public void setBlock_time(String block_time) {
            this.block_time = block_time;
        }

        public String getBlock_time() {
            return block_time;
        }

        public void setAction_trace(Action_trace action_trace) {
            this.action_trace = action_trace;
        }

        public Action_trace getAction_trace() {
            return action_trace;
        }

    }
}
