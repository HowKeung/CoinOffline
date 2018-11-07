/**
 * Copyright 2018 bejson.com
 */
package com.jungel.coinoffline.eos.eospocket.bean;

import java.util.List;

/**
 * Auto-generated: 2018-10-18 15:7:40
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class AccountInfo extends ApiError{

    private String account_name;
    private long head_block_num;
    private String head_block_time;
    private boolean privileged;
    private String last_code_update;
    private String created;
    private String core_liquid_balance;
    private int ram_quota;
    private int net_weight;
    private int cpu_weight;
    private Net_limit net_limit;
    private Cpu_limit cpu_limit;
    private int ram_usage;
    private List<Permissions> permissions;
    private Total_resources total_resources;
    private Self_delegated_bandwidth self_delegated_bandwidth;
    private String refund_request;
    private Voter_info voter_info;
    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }
    public String getAccount_name() {
        return account_name;
    }

    public void setHead_block_num(long head_block_num) {
        this.head_block_num = head_block_num;
    }
    public long getHead_block_num() {
        return head_block_num;
    }

    public void setHead_block_time(String head_block_time) {
        this.head_block_time = head_block_time;
    }
    public String getHead_block_time() {
        return head_block_time;
    }

    public void setPrivileged(boolean privileged) {
        this.privileged = privileged;
    }
    public boolean getPrivileged() {
        return privileged;
    }

    public void setLast_code_update(String last_code_update) {
        this.last_code_update = last_code_update;
    }
    public String getLast_code_update() {
        return last_code_update;
    }

    public void setCreated(String created) {
        this.created = created;
    }
    public String getCreated() {
        return created;
    }

    public void setCore_liquid_balance(String core_liquid_balance) {
        this.core_liquid_balance = core_liquid_balance;
    }
    public String getCore_liquid_balance() {
        return core_liquid_balance;
    }

    public void setRam_quota(int ram_quota) {
        this.ram_quota = ram_quota;
    }
    public int getRam_quota() {
        return ram_quota;
    }

    public void setNet_weight(int net_weight) {
        this.net_weight = net_weight;
    }
    public int getNet_weight() {
        return net_weight;
    }

    public void setCpu_weight(int cpu_weight) {
        this.cpu_weight = cpu_weight;
    }
    public int getCpu_weight() {
        return cpu_weight;
    }

    public void setNet_limit(Net_limit net_limit) {
        this.net_limit = net_limit;
    }
    public Net_limit getNet_limit() {
        return net_limit;
    }

    public void setCpu_limit(Cpu_limit cpu_limit) {
        this.cpu_limit = cpu_limit;
    }
    public Cpu_limit getCpu_limit() {
        return cpu_limit;
    }

    public void setRam_usage(int ram_usage) {
        this.ram_usage = ram_usage;
    }
    public int getRam_usage() {
        return ram_usage;
    }

    public void setPermissions(List<Permissions> permissions) {
        this.permissions = permissions;
    }
    public List<Permissions> getPermissions() {
        return permissions;
    }

    public void setTotal_resources(Total_resources total_resources) {
        this.total_resources = total_resources;
    }
    public Total_resources getTotal_resources() {
        return total_resources;
    }

    public void setSelf_delegated_bandwidth(Self_delegated_bandwidth self_delegated_bandwidth) {
        this.self_delegated_bandwidth = self_delegated_bandwidth;
    }
    public Self_delegated_bandwidth getSelf_delegated_bandwidth() {
        return self_delegated_bandwidth;
    }

    public void setRefund_request(String refund_request) {
        this.refund_request = refund_request;
    }
    public String getRefund_request() {
        return refund_request;
    }

    public void setVoter_info(Voter_info voter_info) {
        this.voter_info = voter_info;
    }
    public Voter_info getVoter_info() {
        return voter_info;
    }


    /**
     * Auto-generated: 2018-10-18 15:7:40
     *
     * @author bejson.com (i@bejson.com)
     * @website http://www.bejson.com/java2pojo/
     */
    public static class Net_limit {

        private int used;
        private long available;
        private long max;
        public void setUsed(int used) {
            this.used = used;
        }
        public int getUsed() {
            return used;
        }

        public void setAvailable(long available) {
            this.available = available;
        }
        public long getAvailable() {
            return available;
        }

        public void setMax(long max) {
            this.max = max;
        }
        public long getMax() {
            return max;
        }

    }

    /**
     * Auto-generated: 2018-10-18 15:7:40
     *
     * @author bejson.com (i@bejson.com)
     * @website http://www.bejson.com/java2pojo/
     */
    public static class Cpu_limit {

        private int used;
        private long available;
        private long max;
        public void setUsed(int used) {
            this.used = used;
        }
        public int getUsed() {
            return used;
        }

        public void setAvailable(long available) {
            this.available = available;
        }
        public long getAvailable() {
            return available;
        }

        public void setMax(long max) {
            this.max = max;
        }
        public long getMax() {
            return max;
        }
    }

    /**
     * Auto-generated: 2018-10-18 15:7:40
     *
     * @author bejson.com (i@bejson.com)
     * @website http://www.bejson.com/java2pojo/
     */
    public static class Keys {

        private String key;
        private int weight;
        public void setKey(String key) {
            this.key = key;
        }
        public String getKey() {
            return key;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }
        public int getWeight() {
            return weight;
        }
    }

    /**
     * Auto-generated: 2018-10-18 15:7:40
     *
     * @author bejson.com (i@bejson.com)
     * @website http://www.bejson.com/java2pojo/
     */
    public static class Required_auth {

        private int threshold;
        private List<Keys> keys;
        private List<String> accounts;
        private List<String> waits;
        public void setThreshold(int threshold) {
            this.threshold = threshold;
        }
        public int getThreshold() {
            return threshold;
        }

        public void setKeys(List<Keys> keys) {
            this.keys = keys;
        }
        public List<Keys> getKeys() {
            return keys;
        }

        public void setAccounts(List<String> accounts) {
            this.accounts = accounts;
        }
        public List<String> getAccounts() {
            return accounts;
        }

        public void setWaits(List<String> waits) {
            this.waits = waits;
        }
        public List<String> getWaits() {
            return waits;
        }
    }

    /**
     * Auto-generated: 2018-10-18 15:7:40
     *
     * @author bejson.com (i@bejson.com)
     * @website http://www.bejson.com/java2pojo/
     */
    public static class Permissions {

        private String perm_name;
        private String parent;
        private Required_auth required_auth;
        public void setPerm_name(String perm_name) {
            this.perm_name = perm_name;
        }
        public String getPerm_name() {
            return perm_name;
        }

        public void setParent(String parent) {
            this.parent = parent;
        }
        public String getParent() {
            return parent;
        }

        public void setRequired_auth(Required_auth required_auth) {
            this.required_auth = required_auth;
        }
        public Required_auth getRequired_auth() {
            return required_auth;
        }
    }

    /**
     * Auto-generated: 2018-10-18 15:7:40
     *
     * @author bejson.com (i@bejson.com)
     * @website http://www.bejson.com/java2pojo/
     */
    public static class Total_resources {

        private String owner;
        private String net_weight;
        private String cpu_weight;
        private int ram_bytes;
        public void setOwner(String owner) {
            this.owner = owner;
        }
        public String getOwner() {
            return owner;
        }

        public void setNet_weight(String net_weight) {
            this.net_weight = net_weight;
        }
        public String getNet_weight() {
            return net_weight;
        }

        public void setCpu_weight(String cpu_weight) {
            this.cpu_weight = cpu_weight;
        }
        public String getCpu_weight() {
            return cpu_weight;
        }

        public void setRam_bytes(int ram_bytes) {
            this.ram_bytes = ram_bytes;
        }
        public int getRam_bytes() {
            return ram_bytes;
        }
    }

    /**
     * Auto-generated: 2018-10-18 15:7:40
     *
     * @author bejson.com (i@bejson.com)
     * @website http://www.bejson.com/java2pojo/
     */
    public static class Self_delegated_bandwidth {

        private String from;
        private String to;
        private String net_weight;
        private String cpu_weight;
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

        public void setNet_weight(String net_weight) {
            this.net_weight = net_weight;
        }
        public String getNet_weight() {
            return net_weight;
        }

        public void setCpu_weight(String cpu_weight) {
            this.cpu_weight = cpu_weight;
        }
        public String getCpu_weight() {
            return cpu_weight;
        }
    }

    /**
     * Auto-generated: 2018-10-18 15:7:40
     *
     * @author bejson.com (i@bejson.com)
     * @website http://www.bejson.com/java2pojo/
     */
    public static class Voter_info {

        private String owner;
        private String proxy;
        private List<String> producers;
        private int staked;
        private String last_vote_weight;
        private String proxied_vote_weight;
        private int is_proxy;
        public void setOwner(String owner) {
            this.owner = owner;
        }
        public String getOwner() {
            return owner;
        }

        public void setProxy(String proxy) {
            this.proxy = proxy;
        }
        public String getProxy() {
            return proxy;
        }

        public void setProducers(List<String> producers) {
            this.producers = producers;
        }
        public List<String> getProducers() {
            return producers;
        }

        public void setStaked(int staked) {
            this.staked = staked;
        }
        public int getStaked() {
            return staked;
        }

        public void setLast_vote_weight(String last_vote_weight) {
            this.last_vote_weight = last_vote_weight;
        }
        public String getLast_vote_weight() {
            return last_vote_weight;
        }

        public void setProxied_vote_weight(String proxied_vote_weight) {
            this.proxied_vote_weight = proxied_vote_weight;
        }
        public String getProxied_vote_weight() {
            return proxied_vote_weight;
        }

        public void setIs_proxy(int is_proxy) {
            this.is_proxy = is_proxy;
        }
        public int getIs_proxy() {
            return is_proxy;
        }
    }
}