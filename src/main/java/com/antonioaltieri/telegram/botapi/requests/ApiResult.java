package com.antonioaltieri.telegram.botapi.requests;

import com.google.gson.annotations.SerializedName;

public class ApiResult<T> {

    @SerializedName("ok")
    private boolean ok;

    @SerializedName("description")
    private String description;

    @SerializedName("error_code")
    private int errorCode;

    @SerializedName("result")
    private T result;

    public ApiResult() {
    }

    public boolean isOk() {
        return ok;
    }

    public String getDescription() {
        return description;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public T getResult() {
        return result;
    }
}
