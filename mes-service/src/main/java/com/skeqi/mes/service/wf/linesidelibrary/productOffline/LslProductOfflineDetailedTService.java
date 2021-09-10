package com.skeqi.mes.service.wf.linesidelibrary.productOffline;

import com.skeqi.mes.pojo.wf.linesidelibrary.productOffline.LslProductOfflineDetailedT;

import java.util.List;

public interface LslProductOfflineDetailedTService{


    List<LslProductOfflineDetailedT> selectAll(LslProductOfflineDetailedT detailedT);

    LslProductOfflineDetailedT selectByPrimaryKey(String number);

    Integer insertByList(List<LslProductOfflineDetailedT> detailedTList);

    Integer deleteDetailedByOfflineNumber(String number);
}
