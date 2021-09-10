package com.skeqi.mes.service.wf.linesidelibrary.productOffline;

import com.alibaba.fastjson.JSONArray;
import com.skeqi.mes.pojo.wf.linesidelibrary.productOffline.LslProductOfflineDetailedDetailT;
import com.skeqi.mes.pojo.wf.linesidelibrary.productOffline.LslProductOfflineT;

import java.util.List;

public interface LslProductOfflineTService{

    int insertSelective(LslProductOfflineT record, List<LslProductOfflineDetailedDetailT> detailTList) throws Exception;

    LslProductOfflineT selectByPrimaryKey(String number);

    List<LslProductOfflineT> selectAll(LslProductOfflineT offlineT);

    Integer deleteByNumber(String number) throws Exception;
}
