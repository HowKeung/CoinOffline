package com.jungel.coinoffline.eos.eospocket.bean;

import java.util.List;

public class AccountList extends ApiError {
    /**
     * Auto-generated: 2018-10-18 19:53:24
     *
     * @author bejson.com (i@bejson.com)
     * @website http://www.bejson.com/java2pojo/
     */
    private List<String> account_names;

    public void setAccount_names(List<String> account_names) {
        this.account_names = account_names;
    }

    public List<String> getAccount_names() {
        return account_names;
    }
}
