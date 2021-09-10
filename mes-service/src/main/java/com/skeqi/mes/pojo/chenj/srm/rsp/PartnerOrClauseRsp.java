package com.skeqi.mes.pojo.chenj.srm.rsp;

/**
 * @author ChenJ
 * @date 2021/6/23
 * @Classname partnerOrClauseReq
 * @Description 合作伙伴与条款入参
 */
public class PartnerOrClauseRsp {


    public String getFirstParty() {
        return firstParty;
    }

    public void setFirstParty(String firstParty) {
        this.firstParty = firstParty;
    }

    public String getSecondParty() {
        return secondParty;
    }

    public void setSecondParty(String secondParty) {
        this.secondParty = secondParty;
    }

    public String getObjectOfContractOrValue() {
        return objectOfContractOrValue;
    }

    public void setObjectOfContractOrValue(String objectOfContractOrValue) {
        this.objectOfContractOrValue = objectOfContractOrValue;
    }

    @Override
    public String toString() {
        return "partnerOrClauseReq{" +
                "firstParty='" + firstParty + '\'' +
                ", secondParty='" + secondParty + '\'' +
                ", objectOfContractOrValue='" + objectOfContractOrValue + '\'' +
                '}';
    }

    /**
     * 甲方
     */
    private String firstParty;

    /**
     * 乙方
     */
    private String secondParty;

    /**
     * 合同条款对象及对象值
     */
    private String objectOfContractOrValue;
}
