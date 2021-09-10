package com.skeqi.mes.pojo.wf.linesidelibrary;

import java.io.Serializable;
import java.util.List;

/**
 * 确认出库物料数据要料返回请求信息提交参数类
 * @author Lenovo
 */
public class MaterialResponseParams implements Serializable {
    private Integer requestDetailId;

    private String materialNo;

    private Integer tracesType;

    private List<RLslMaterialResponseT> details;

    public Integer getRequestDetailId() {
        return requestDetailId;
    }

    public void setRequestDetailId(Integer requestId) {
        this.requestDetailId = requestId;
    }

    public String getMaterialNo() {
        return materialNo;
    }

    public void setMaterialNo(String materialNo) {
        this.materialNo = materialNo;
    }

    public Integer getTracesType() {
        return tracesType;
    }

    public void setTracesType(Integer tracesType) {
        this.tracesType = tracesType;
    }

    public List<RLslMaterialResponseT> getDetails() {
        return details;
    }

    public void setDetails(List<RLslMaterialResponseT> details) {
        this.details = details;
    }


    @Override
    public String toString() {
        return "MaterialResponseParams{" +
                "requestDetailId=" + requestDetailId +
                ", materialNo=" + materialNo +
                ", tracesType=" + tracesType +
                ", details=" + details +
                '}';
    }
}
