
package com.skeqi.mes.pojo.chenj.srm.kthree;

import java.util.List;

/**
 * @author ChenJ
 * @date 2021/7/28
 * @Classname KThreeResult
 * @Description 供应商接口-查询参数处理
 */
public class KThreeSupplierResult {

    private String status;
    private String message;
    private List<KThreeSupplier> data;
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

    public void setData(List<KThreeSupplier> data) {
         this.data = data;
     }
     public List<KThreeSupplier> getData() {
         return data;
     }

    @Override
    public String toString() {
        return "KThreeSupplierResult{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
