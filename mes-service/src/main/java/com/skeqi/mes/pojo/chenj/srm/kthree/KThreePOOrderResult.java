
package com.skeqi.mes.pojo.chenj.srm.kthree;

import java.util.List;

/**
 * @author ChenJ
 * @date 2021/7/28
 * @Classname KThreeResult
 * @Description 采购订单接口-查询参数处理
 */
public class KThreePOOrderResult {

    private String status;
    private String message;
    private List<KThreePOOrder> data;
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

    public void setData(List<KThreePOOrder> data) {
         this.data = data;
     }
     public List<KThreePOOrder> getData() {
         return data;
     }

}
