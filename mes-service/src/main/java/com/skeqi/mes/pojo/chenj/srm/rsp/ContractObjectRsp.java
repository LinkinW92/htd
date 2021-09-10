package com.skeqi.mes.pojo.chenj.srm.rsp;

/**
 * @author ChenJ
 * @date 2021/6/23
 * @Classname contractObjectReq
 * @Description 标的入参
 */
public class ContractObjectRsp {
    public String getLineItemNo() {
        return lineItemNo;
    }

    public void setLineItemNo(String lineItemNo) {
        this.lineItemNo = lineItemNo;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(String taxRate) {
        this.taxRate = taxRate;
    }

    @Override
    public String toString() {
        return "contractObjectReq{" +
                "lineItemNo='" + lineItemNo + '\'' +
                ", materialCode='" + materialCode + '\'' +
                ", count='" + count + '\'' +
                ", unit='" + unit + '\'' +
                ", currency='" + currency + '\'' +
                ", taxRate='" + taxRate + '\'' +
                '}';
    }

    /**
     * 行项目号
     */
    private String lineItemNo;

    /**
     * 物料编码
     */
    private String materialCode;

    /**
     * 数量
     */
    private String count;

    /**
     * 单位
     */
    private String unit;

    /**
     * 币种
     */
    private String currency;

    /**
     * 税率
     */
    private String taxRate;


}
