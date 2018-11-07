package com.jungel.coinoffline.eos.eos4j.api.vo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class KeyAccounts {

    public KeyAccounts() {

    }

    @JsonProperty("account_names")
    private List<String> accountNames;

    public List<String> getAccountNames() {
        return accountNames;
    }

    public void setAccountNames(List<String> accountNames) {
        this.accountNames = accountNames;
    }
}
