package com.skeqi.mes.pojo.qh;

/**
 * 不良原因
 * @ 创建人 FQZ
 * @ 创建时间   2021/2/19 14:04
 */
public class CMesBadReasonT {

    private Integer id;
    private String badReason;
    private String badDesc;
    private String badCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBadReason() {
        return badReason;
    }

    public void setBadReason(String badReason) {
        this.badReason = badReason;
    }

    public String getBadDesc() {
        return badDesc;
    }

    public void setBadDesc(String badDesc) {
        this.badDesc = badDesc;
    }

    public String getBadCode() {
        return badCode;
    }

    public void setBadCode(String badCode) {
        this.badCode = badCode;
    }

    public CMesBadReasonT() {
    }

    public CMesBadReasonT(Integer id, String badReason, String badDesc, String badCode) {
        this.id = id;
        this.badReason = badReason;
        this.badDesc = badDesc;
        this.badCode = badCode;
    }
}
