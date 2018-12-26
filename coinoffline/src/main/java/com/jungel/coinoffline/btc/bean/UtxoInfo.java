package com.jungel.coinoffline.btc.bean;

import java.util.List;

public class UtxoInfo {

    private String notice;
    private List<Outputs> unspent_outputs;

    public void setNotice(String notice) {
        this.notice = notice;
    }
    public String getNotice() {
        return notice;
    }

    public void setUnspent_outputs(List<Outputs> unspent_outputs) {
        this.unspent_outputs = unspent_outputs;
    }
    public List<Outputs> getUnspent_outputs() {
        return unspent_outputs;
    }

    /**
     * Auto-generated: 2018-12-26 16:7:22
     *
     * @author bejson.com (i@bejson.com)
     * @website http://www.bejson.com/java2pojo/
     */
    public static class Outputs {

        private String tx_hash;
        private String tx_hash_big_endian;
        private long tx_index;
        private int tx_output_n;
        private String script;
        private long value;
        private String value_hex;
        private long confirmations;
        public void setTx_hash(String tx_hash) {
            this.tx_hash = tx_hash;
        }
        public String getTx_hash() {
            return tx_hash;
        }

        public void setTx_hash_big_endian(String tx_hash_big_endian) {
            this.tx_hash_big_endian = tx_hash_big_endian;
        }
        public String getTx_hash_big_endian() {
            return tx_hash_big_endian;
        }

        public void setTx_index(long tx_index) {
            this.tx_index = tx_index;
        }
        public long getTx_index() {
            return tx_index;
        }

        public void setTx_output_n(int tx_output_n) {
            this.tx_output_n = tx_output_n;
        }
        public int getTx_output_n() {
            return tx_output_n;
        }

        public void setScript(String script) {
            this.script = script;
        }
        public String getScript() {
            return script;
        }

        public void setValue(long value) {
            this.value = value;
        }
        public long getValue() {
            return value;
        }

        public void setValue_hex(String value_hex) {
            this.value_hex = value_hex;
        }
        public String getValue_hex() {
            return value_hex;
        }

        public void setConfirmations(long confirmations) {
            this.confirmations = confirmations;
        }
        public long getConfirmations() {
            return confirmations;
        }

    }
}
