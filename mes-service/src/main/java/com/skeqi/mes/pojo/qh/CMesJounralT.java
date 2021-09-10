package com.skeqi.mes.pojo.qh;

/**
 * @ 创建人 FQZ
 * @ 创建时间   2021/2/26 11:08
 */
public class CMesJounralT {

    private Integer id;
    private String dt,userName,content,str;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public CMesJounralT() {
    }

    public CMesJounralT(Integer id, String dt, String userName, String content, String str) {
        this.id = id;
        this.dt = dt;
        this.userName = userName;
        this.content = content;
        this.str = str;
    }
}
