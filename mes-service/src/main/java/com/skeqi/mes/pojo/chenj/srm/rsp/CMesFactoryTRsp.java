package com.skeqi.mes.pojo.chenj.srm.rsp;

/**
    * 工厂表出参
    */
public class CMesFactoryTRsp {
    /**
    * 工厂编号
    */
    private String factoryCode;

    /**
    * 工厂名称
    */
    private String factoryName;

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

    @Override
    public String toString() {
        return "CMesFactoryTReq{" +
                "factoryCode='" + factoryCode + '\'' +
                ", factoryName='" + factoryName + '\'' +
                '}';
    }
}
