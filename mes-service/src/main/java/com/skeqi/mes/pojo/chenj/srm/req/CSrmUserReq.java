package com.skeqi.mes.pojo.chenj.srm.req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author ChenJ
 * @date 2021/6/12
 * @Classname CSrmUserReq
 * @Description 登录入参
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CSrmUserReq {
    private String userName;
    private String passWord;

    @Override
    public String toString() {
        return "CSrmUserReq{" +
                "userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
