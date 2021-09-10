package com.skeqi.mes.pojo.chenj.srm.req;


/**
 * @author ChenJ
 * @date 2021/6/8
 * @Classname CSrmSupplierH
 * @Description ${Description}
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * 供应商升降级申请头表
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CSrmSupplierHRDelReq {

    /**
     * 申请单号
     */
    private String requestCode;

    /**
     * 行号
     */
    private List<Integer> lineNumber;

    /**
     * 当前页码
     * @return
     */
    private String pageNum;

    /**
     * 显示条数
     * @return
     */
    private String pageSize;

    public String getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(String requestCode) {
        this.requestCode = requestCode;
    }

    public List<Integer> getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(List<Integer> lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "CSrmSupplierHRDelReq{" +
                "requestCode='" + requestCode + '\'' +
                ", lineNumber=" + lineNumber +
                ", pageNum='" + pageNum + '\'' +
                ", pageSize='" + pageSize + '\'' +
                '}';
    }
}
