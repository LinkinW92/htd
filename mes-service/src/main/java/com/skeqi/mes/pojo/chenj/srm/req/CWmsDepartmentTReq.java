package com.skeqi.mes.pojo.chenj.srm.req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author ChenJ
 * @date 2021/7/26
 * @Classname CWmsDepartmentTReq
 * @Description
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CWmsDepartmentTReq {


    /**
     * 当前页码
     */
    private Integer pageNum;

    /**
     * 显示条数
     */
    private Integer pageSize;


    /**
     * 部门编码
     */
    private String deNumber;

    /**
     * 部门名称
     */
    private String deName;


    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

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
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", deNumber='" + deNumber + '\'' +
                ", deName='" + deName + '\'' +
                '}';
    }
}
