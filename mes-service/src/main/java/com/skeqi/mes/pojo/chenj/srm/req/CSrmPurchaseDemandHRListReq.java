package com.skeqi.mes.pojo.chenj.srm.req;


/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmPurchaseDemandH
 * @Description ${Description}
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * 采购需求单转换入参
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CSrmPurchaseDemandHRListReq {

    /**
     * 申请单号
     */
    private String requestCode;

    /**
     * 状态(1.新建2.审批中3.待分配4.已分配5.已删除)
     */
    private String status;

    /**
     * 申请人
     */
    private String proposer;

    /**
     * 申请日期
     */
    private String applicationDate;

    /**
     * 项目号
     */
    private String projectCode;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 申请部门
     */
    private String applyForDepartment;

    /**
     * 采购员
     */
    private String buyer;

    /**
     * 采购需求单号集合
     */
    private List<String> reqList;

//    /**
//     * 行项目号
//     */
//    private String rowProjectNumber;
//
//    /**
//     * 物料编码
//     */
//    private String materialCode;
//
//    /**
//     * 物料名称
//     */
//    private String materialName;
//
//    /**
//     * 数量
//     */
//    private String count;
//
//    /**
//     * 单位
//     */
//    private String unit;
//
//    /**
//     * 需求日期
//     */
//    private String requiredDate;
//
//    /**
//     * 收货地点
//     */
//    private String placeOfReceipt;
//


//    /**
//     * 操作标识(1.采购需求单转询价)
//     */
//    private String operationSign;


    /**
     * 当前页码
     */
    private Integer pageNum;
    /**
     * 条数
     */
    private Integer pageSize;


    /**
     * 文件名
     */
    private String fileName;


    public String getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(String requestCode) {
        this.requestCode = requestCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProposer() {
        return proposer;
    }

    public void setProposer(String proposer) {
        this.proposer = proposer;
    }

    public String getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(String applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectName() {
        return projectName;
    }

    public List<String> getReqList() {
        return reqList;
    }

    public void setReqList(List<String> reqList) {
        this.reqList = reqList;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getApplyForDepartment() {
        return applyForDepartment;
    }

    public void setApplyForDepartment(String applyForDepartment) {
        this.applyForDepartment = applyForDepartment;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "CSrmPurchaseDemandHRListReq{" +
                "requestCode='" + requestCode + '\'' +
                ", status='" + status + '\'' +
                ", proposer='" + proposer + '\'' +
                ", applicationDate='" + applicationDate + '\'' +
                ", projectCode='" + projectCode + '\'' +
                ", projectName='" + projectName + '\'' +
                ", applyForDepartment='" + applyForDepartment + '\'' +
                ", buyer='" + buyer + '\'' +
                ", reqList=" + reqList +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}
