package com.jungel.coinoffline.eos.eospocket.bean;

/**
 * @author espritblock http://eblock.io
 */
public class ApiError {

    private String message;

    private String code;

    private Error error;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }


    public static class Error {
        private String code;
        private String name;
        private String what;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getWhat() {
            return what;
        }

        public void setWhat(String what) {
            this.what = what;
        }
    }
}
