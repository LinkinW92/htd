package com.skeqi.mes.pojo.chenj.srm.kthree;

import java.util.List;

/**
 * @author ChenJ
 * @date 2021/8/24
 * @Classname KThreePOInStockResult
 * @Description 采购收料通知接口-查询参数处理
 */
public class KThreePOReceiveResult {

    private String status;
    private String message;
    private List<KThreePOReceiveHReq> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<KThreePOReceiveHReq> getData() {
        return data;
    }

    public void setData(List<KThreePOReceiveHReq> data) {
        this.data = data;
    }
}
