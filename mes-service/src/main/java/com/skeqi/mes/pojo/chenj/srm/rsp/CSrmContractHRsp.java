package com.skeqi.mes.pojo.chenj.srm.rsp;

 /**
 * @author ChenJ
 * @date 2021/6/10
 * @Classname CSrmContractH
 * @Description ${Description}
 */

import com.github.pagehelper.PageInfo;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmPurPartnerInfoRReq;
import com.skeqi.mes.pojo.chenj.srm.req.ContractObjectReq;
import com.skeqi.mes.pojo.chenj.srm.req.PartnerOrClauseReq;


/**
 * 合同编辑出参
 */
public class CSrmContractHRsp {

    /**
     * 合同编号
     */
    private String contractNo;

    /**
     * 合同名称
     */
    private String contractName;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 合同性质(1.普通合同2.附件合同)
     */
    private String contractCharacter;

    /**
     * 公司代码
     */
    private String companyCode;

    /**
     * 供应商代码
     */
    private String supplierCode;

    /**
     * 供应商名称
     */
    private String supplierName;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 合同生效日期
     */
    private String effectiveDateOfContract;

    /**
     * 合同终止日期
     */
    private String dateOfTermination;

    /**
     * 使用模板编号
     */
    private String usingTemplateNumbers;

    /**
     * 状态(1.新建2.待审批3.待签署4.待存档5.已存档)
     */
    private String status;




    /**
     * 采购员
     */
    private String buyer;
    /**
     * 主合同
     */
    private String masterContract;
    /**
     * 合同来源(1.手动创建2.采购申请转换3.寻源结果引用)
     */
    private String sourceOfTheContract;
    /**
     * 合同总额
     */
    private String contractRental;

    /**
     * 采购组织
     */
    private String purchasingOrganization;

    /**
     * 备注
     */
    private String remark;




    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 修改时间
     */
    private String updateTime;

    /**
     * 标的对象值
     */
    private PageInfo<ContractObjectReq> contractObject;

    /**
     * 采购合作伙伴信息对象值
     */
    private PageInfo<CSrmPurPartnerInfoRReq> cSrmPurPartnerInfoRReqs;

    /**
     * 合作伙伴与条款对象值
     */
    private PageInfo<PartnerOrClauseReq> partnerOrClause;

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getContractName() {
        return contractName;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getMasterContract() {
        return masterContract;
    }

    public void setMasterContract(String masterContract) {
        this.masterContract = masterContract;
    }

    public String getSourceOfTheContract() {
        return sourceOfTheContract;
    }

    public void setSourceOfTheContract(String sourceOfTheContract) {
        this.sourceOfTheContract = sourceOfTheContract;
    }

    public String getContractRental() {
        return contractRental;
    }

    public void setContractRental(String contractRental) {
        this.contractRental = contractRental;
    }

    public String getPurchasingOrganization() {
        return purchasingOrganization;
    }

    public void setPurchasingOrganization(String purchasingOrganization) {
        this.purchasingOrganization = purchasingOrganization;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getContractCharacter() {
        return contractCharacter;
    }

    public void setContractCharacter(String contractCharacter) {
        this.contractCharacter = contractCharacter;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getEffectiveDateOfContract() {
        return effectiveDateOfContract;
    }

    public void setEffectiveDateOfContract(String effectiveDateOfContract) {
        this.effectiveDateOfContract = effectiveDateOfContract;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getDateOfTermination() {
        return dateOfTermination;
    }

    public void setDateOfTermination(String dateOfTermination) {
        this.dateOfTermination = dateOfTermination;
    }

    public String getUsingTemplateNumbers() {
        return usingTemplateNumbers;
    }

    public void setUsingTemplateNumbers(String usingTemplateNumbers) {
        this.usingTemplateNumbers = usingTemplateNumbers;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public PageInfo<ContractObjectReq> getContractObject() {
        return contractObject;
    }

    public void setContractObject(PageInfo<ContractObjectReq> contractObject) {
        this.contractObject = contractObject;
    }

    public PageInfo<CSrmPurPartnerInfoRReq> getcSrmPurPartnerInfoRReqs() {
        return cSrmPurPartnerInfoRReqs;
    }

    public void setcSrmPurPartnerInfoRReqs(PageInfo<CSrmPurPartnerInfoRReq> cSrmPurPartnerInfoRReqs) {
        this.cSrmPurPartnerInfoRReqs = cSrmPurPartnerInfoRReqs;
    }

    public PageInfo<PartnerOrClauseReq> getPartnerOrClause() {
        return partnerOrClause;
    }

    public void setPartnerOrClause(PageInfo<PartnerOrClauseReq> partnerOrClause) {
        this.partnerOrClause = partnerOrClause;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "CSrmContractHRsp{" +
                "contractNo='" + contractNo + '\'' +
                ", contractName='" + contractName + '\'' +
                ", creator='" + creator + '\'' +
                ", contractCharacter='" + contractCharacter + '\'' +
                ", companyCode='" + companyCode + '\'' +
                ", supplierCode='" + supplierCode + '\'' +
                ", supplierName='" + supplierName + '\'' +
                ", companyName='" + companyName + '\'' +
                ", effectiveDateOfContract='" + effectiveDateOfContract + '\'' +
                ", dateOfTermination='" + dateOfTermination + '\'' +
                ", usingTemplateNumbers='" + usingTemplateNumbers + '\'' +
                ", status='" + status + '\'' +
                ", buyer='" + buyer + '\'' +
                ", masterContract='" + masterContract + '\'' +
                ", sourceOfTheContract='" + sourceOfTheContract + '\'' +
                ", contractRental='" + contractRental + '\'' +
                ", purchasingOrganization='" + purchasingOrganization + '\'' +
                ", remark='" + remark + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", contractObject=" + contractObject +
                ", cSrmPurPartnerInfoRReqs=" + cSrmPurPartnerInfoRReqs +
                ", partnerOrClause=" + partnerOrClause +
                '}';
    }
}
