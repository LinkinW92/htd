package com.skeqi.mes.pojo.chenj.srm.req;

 /**
 * @author ChenJ
 * @date 2021/6/10
 * @Classname CSrmContractH
 * @Description ${Description}
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;

/**
 * 合同头表
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CSrmContractHReq {


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
     * 创建时间
     */
    private String createTime;


    /**
     * 操作标识(1.创建2.修改3.变更状态)
     */
    private String operationSign;


    /**
     * 标的对象值
     */
    private ContractObjectReq[] contractObject;

    /**
     * 采购合作伙伴信息对象值
     */
    private CSrmPurPartnerInfoRReq[] partnerOrClause;

    /**
     * 合作伙伴与条款对象值
     */
    private PartnerOrClauseReq[] clauseData;


    /**
     * 标的对象值--删除
     */
    private Integer[] delTargetData;

    /**
     * 采购合作伙伴信息对象值--删除
     */
    private Integer[] delCooPratIveData;

    /**
     * 合作伙伴与条款对象值--删除
     */
    private Integer[] delAgreementData;


    /**
     * 页码
     */
    private Integer pageNum=1;
    /**
     * 每页数量
     */
    private Integer pageSize=10;


    /**
     * 页码--合同标的
     */
    private Integer pageNumH=1;
    /**
     * 每页数量--合同标的
     */
    private Integer pageSizeH=10;

    /**
     * 页码--采购合同业务
     */
    private Integer pageNumY=1;
    /**
     * 每页数量--采购合同业务
     */
    private Integer pageSizeY=10;

    /**
     * 页码--采购合作伙伴
     */
    private Integer pageNumB=1;
    /**
     * 每页数量--采购合作伙伴
     */
    private Integer pageSizeB=10;


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


    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
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

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
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

    public String getDateOfTermination() {
        return dateOfTermination;
    }

    public void setDateOfTermination(String dateOfTermination) {
        this.dateOfTermination = dateOfTermination;
    }

    public String getUsingTemplateNumbers() {
        return usingTemplateNumbers;
    }

    public Integer[] getDelTargetData() {
        return delTargetData;
    }

    public void setDelTargetData(Integer[] delTargetData) {
        this.delTargetData = delTargetData;
    }

    public Integer[] getDelCooPratIveData() {
        return delCooPratIveData;
    }

    public void setDelCooPratIveData(Integer[] delCooPratIveData) {
        this.delCooPratIveData = delCooPratIveData;
    }

    public Integer[] getDelAgreementData() {
        return delAgreementData;
    }

    public void setDelAgreementData(Integer[] delAgreementData) {
        this.delAgreementData = delAgreementData;
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

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }


    public String getOperationSign() {
        return operationSign;
    }

    public void setOperationSign(String operationSign) {
        this.operationSign = operationSign;
    }

    public Integer getPageNumH() {
        return pageNumH;
    }

    public void setPageNumH(Integer pageNumH) {
        this.pageNumH = pageNumH;
    }

    public Integer getPageSizeH() {
        return pageSizeH;
    }

    public void setPageSizeH(Integer pageSizeH) {
        this.pageSizeH = pageSizeH;
    }

    public Integer getPageNumY() {
        return pageNumY;
    }

    public void setPageNumY(Integer pageNumY) {
        this.pageNumY = pageNumY;
    }

    public Integer getPageSizeY() {
        return pageSizeY;
    }

    public void setPageSizeY(Integer pageSizeY) {
        this.pageSizeY = pageSizeY;
    }

    public Integer getPageNumB() {
        return pageNumB;
    }

    public CSrmPurPartnerInfoRReq[] getPartnerOrClause() {
        return partnerOrClause;
    }

    public void setPartnerOrClause(CSrmPurPartnerInfoRReq[] partnerOrClause) {
        this.partnerOrClause = partnerOrClause;
    }

    public PartnerOrClauseReq[] getClauseData() {
        return clauseData;
    }

    public void setClauseData(PartnerOrClauseReq[] clauseData) {
        this.clauseData = clauseData;
    }

    public void setPageNumB(Integer pageNumB) {
        this.pageNumB = pageNumB;
    }

    public Integer getPageSizeB() {
        return pageSizeB;
    }

    public void setPageSizeB(Integer pageSizeB) {
        this.pageSizeB = pageSizeB;
    }

    public ContractObjectReq[] getContractObject() {
        return contractObject;
    }

    public void setContractObject(ContractObjectReq[] contractObject) {
        this.contractObject = contractObject;
    }


    @Override
    public String toString() {
        return "CSrmContractHReq{" +
                "contractNo='" + contractNo + '\'' +
                ", contractName='" + contractName + '\'' +
                ", creator='" + creator + '\'' +
                ", contractCharacter='" + contractCharacter + '\'' +
                ", companyCode='" + companyCode + '\'' +
                ", supplierCode='" + supplierCode + '\'' +
                ", effectiveDateOfContract='" + effectiveDateOfContract + '\'' +
                ", dateOfTermination='" + dateOfTermination + '\'' +
                ", usingTemplateNumbers='" + usingTemplateNumbers + '\'' +
                ", status='" + status + '\'' +
                ", createTime='" + createTime + '\'' +
                ", operationSign='" + operationSign + '\'' +
                ", contractObject=" + Arrays.toString(contractObject) +
                ", partnerOrClause=" + Arrays.toString(partnerOrClause) +
                ", clauseData=" + Arrays.toString(clauseData) +
                ", delTargetData=" + Arrays.toString(delTargetData) +
                ", delCooPratIveData=" + Arrays.toString(delCooPratIveData) +
                ", delAgreementData=" + Arrays.toString(delAgreementData) +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", pageNumH=" + pageNumH +
                ", pageSizeH=" + pageSizeH +
                ", pageNumY=" + pageNumY +
                ", pageSizeY=" + pageSizeY +
                ", pageNumB=" + pageNumB +
                ", pageSizeB=" + pageSizeB +
                ", buyer='" + buyer + '\'' +
                ", masterContract='" + masterContract + '\'' +
                ", sourceOfTheContract='" + sourceOfTheContract + '\'' +
                ", contractRental='" + contractRental + '\'' +
                ", purchasingOrganization='" + purchasingOrganization + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
