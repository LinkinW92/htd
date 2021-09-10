package com.skeqi.mes.pojo.chenj.srm.req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
    * 工厂表入参
    */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CMesFactoryTReq {
    /**
    * 工厂编号
    */
    private String factoryCode;

    /**
    * 工厂名称
    */
    private String factoryName;

    /**
     * 当前页码
     */
    private Integer pageNum;


    /**
     * 条数
     */
    private Integer pageSize;

    public String getFactoryCode() {
        return factoryCode;
    }

    public void setFactoryCode(String factoryCode) {
        this.factoryCode = factoryCode;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

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

    @Override
    public String toString() {
        return "CMesFactoryTReq{" +
                "factoryCode='" + factoryCode + '\'' +
                ", factoryName='" + factoryName + '\'' +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }
}
