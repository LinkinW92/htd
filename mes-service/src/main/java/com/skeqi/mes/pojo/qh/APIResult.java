package com.skeqi.mes.pojo.qh;

/**
 * @ 创建人 FQZ
 * @ 创建时间   2021/1/27 14:11
 */
public class APIResult {

    private String code;
    private String errMsg;
    private String isSuccess;

    public APIResult() {
    }

    @Override
    public String toString() {
        return "APIResult{" +
                "code='" + code + '\'' +
                ", errMsg='" + errMsg + '\'' +
                ", isSuccess='" + isSuccess + '\'' +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(String isSuccess) {
        this.isSuccess = isSuccess;
    }

    public APIResult(String code, String errMsg, String isSuccess) {
        this.code = code;
        this.errMsg = errMsg;
        this.isSuccess = isSuccess;
    }
}
