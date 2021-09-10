
package com.skeqi.mes.pojo.chenj.srm.kthree;

import java.util.List;

/**
 * @author ChenJ
 * @date 2021/7/28
 * @Classname KThreeResult
 * @Description 采购发票接口-查询参数处理
 */
public class KThreePOInvoiceResult {

    private String status;
    private String message;
    private List<KThreePOInvoice> data;
    public void setStatus(String status) {
         this.status = status;
     }
     public String getStatus() {
         return status;
     }

    public void setMessage(String message) {
         this.message = message;
     }
     public String getMessage() {
         return message;
     }

    public void setData(List<KThreePOInvoice> data) {
         this.data = data;
     }
     public List<KThreePOInvoice> getData() {
         return data;
     }

    @Override
    public String toString() {
        return "KThreePOInvoiceResult{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
