package com.jungel.coinoffline.eos.eospocket.bean;

public class BlockNumber extends ApiError {
    
    private String server_version;
    private String chain_id;
    private String head_block_num;
    private Long last_irreversible_block_num;
    private String last_irreversible_block_id;
    private String head_block_id;
    private String head_block_time;
    private String head_block_producer;
    private String virtual_block_cpu_limit;
    private String virtual_block_net_limit;
    private String block_cpu_limit;
    private String block_net_limit;
    private String server_version_string;

    public String getServer_version() {
        return server_version;
    }

    public void setServer_version(String server_version) {
        this.server_version = server_version;
    }

    public String getChain_id() {
        return chain_id;
    }

    public void setChain_id(String chain_id) {
        this.chain_id = chain_id;
    }

    public String getHead_block_num() {
        return head_block_num;
    }

    public void setHead_block_num(String head_block_num) {
        this.head_block_num = head_block_num;
    }

    public Long getLast_irreversible_block_num() {
        return last_irreversible_block_num;
    }

    public void setLast_irreversible_block_num(Long last_irreversible_block_num) {
        this.last_irreversible_block_num = last_irreversible_block_num;
    }

    public String getLast_irreversible_block_id() {
        return last_irreversible_block_id;
    }

    public void setLast_irreversible_block_id(String last_irreversible_block_id) {
        this.last_irreversible_block_id = last_irreversible_block_id;
    }

    public String getHead_block_id() {
        return head_block_id;
    }

    public void setHead_block_id(String head_block_id) {
        this.head_block_id = head_block_id;
    }

    public String getHead_block_time() {
        return head_block_time;
    }

    public void setHead_block_time(String head_block_time) {
        this.head_block_time = head_block_time;
    }

    public String getHead_block_producer() {
        return head_block_producer;
    }

    public void setHead_block_producer(String head_block_producer) {
        this.head_block_producer = head_block_producer;
    }

    public String getVirtual_block_cpu_limit() {
        return virtual_block_cpu_limit;
    }

    public void setVirtual_block_cpu_limit(String virtual_block_cpu_limit) {
        this.virtual_block_cpu_limit = virtual_block_cpu_limit;
    }

    public String getVirtual_block_net_limit() {
        return virtual_block_net_limit;
    }

    public void setVirtual_block_net_limit(String virtual_block_net_limit) {
        this.virtual_block_net_limit = virtual_block_net_limit;
    }

    public String getBlock_cpu_limit() {
        return block_cpu_limit;
    }

    public void setBlock_cpu_limit(String block_cpu_limit) {
        this.block_cpu_limit = block_cpu_limit;
    }

    public String getBlock_net_limit() {
        return block_net_limit;
    }

    public void setBlock_net_limit(String block_net_limit) {
        this.block_net_limit = block_net_limit;
    }

    public String getServer_version_string() {
        return server_version_string;
    }

    public void setServer_version_string(String server_version_string) {
        this.server_version_string = server_version_string;
    }
}
