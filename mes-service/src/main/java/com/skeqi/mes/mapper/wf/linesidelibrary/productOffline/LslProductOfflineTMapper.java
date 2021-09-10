package com.skeqi.mes.mapper.wf.linesidelibrary.productOffline;

import com.skeqi.mes.pojo.wf.linesidelibrary.productOffline.LslProductOfflineT;

import java.util.List;

public interface LslProductOfflineTMapper {
    int insertSelective(LslProductOfflineT record);

    LslProductOfflineT selectByPrimaryKey(String number);

    List<LslProductOfflineT> selectAll(LslProductOfflineT offlineT);

    Integer deleteByNumber(String number);
}
