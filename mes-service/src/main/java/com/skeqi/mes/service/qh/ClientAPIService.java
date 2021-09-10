package com.skeqi.mes.service.qh;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.Exception.util.ServicesException;

import java.util.List;

/**
 * @ 创建人 FQZ
 * @ 创建时间   2021/2/21 15:46
 */
public interface ClientAPIService {

    /* *
     * 返回用户可操作的工位
     * @author FQZ
     * @date 2021/2/22 8:41
     * @Param userName:
     * @Param password:
     * @return: java.util.List<java.lang.String>
     */
    JSONObject findUserStation(String userName, String password) throws ServicesException;
}
