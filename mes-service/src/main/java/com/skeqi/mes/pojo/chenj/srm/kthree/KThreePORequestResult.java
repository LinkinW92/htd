
package com.skeqi.mes.pojo.chenj.srm.kthree;

import java.util.List;

/**
 * @author ChenJ
 * @date 2021/7/28
 * @Classname KThreeResult
 * @Description 采购申请接口-查询参数处理
 */
public class KThreePORequestResult {

    private String status;
    private String message;
    private List<KThreePORequest> data;
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

    public void setData(List<KThreePORequest> data) {
         this.data = data;
     }
     public List<KThreePORequest> getData() {
         return data;
     }

}
