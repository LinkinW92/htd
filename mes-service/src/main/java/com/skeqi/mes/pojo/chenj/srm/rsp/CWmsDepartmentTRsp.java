package com.skeqi.mes.pojo.chenj.srm.rsp;

/**
 * @author ChenJ
 * @date 2021/7/26
 * @Classname CWmsDepartmentTReq
 * @Description
 */
public class CWmsDepartmentTRsp {
    /**
     * 部门编码
     */
    private String deNumber;

    /**
     * 部门名称
     */
    private String deName;

    public String getDeNumber() {
        return deNumber;
    }

    public void setDeNumber(String deNumber) {
        this.deNumber = deNumber;
    }

    public String getDeName() {
        return deName;
    }

    public void setDeName(String deName) {
        this.deName = deName;
    }

    @Override
    public String toString() {
        return "CWmsDepartmentTReq{" +
                ", deNumber='" + deNumber + '\'' +
                ", deName='" + deName + '\'' +
                '}';
    }
}
