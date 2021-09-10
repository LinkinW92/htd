package com.skeqi.mes.mapper.wf.linesidelibrary.productOffline;

import com.skeqi.mes.pojo.wf.linesidelibrary.productOffline.LslProductOfflineDetailedDetailT;

import java.util.List;

public interface LslProductOfflineDetailedDetailTMapper {

    List<LslProductOfflineDetailedDetailT> selectAll(LslProductOfflineDetailedDetailT detailedT);

    Integer insertByList(List<LslProductOfflineDetailedDetailT> detailTList);

    LslProductOfflineDetailedDetailT selectByPrimaryKey(String number);

    Integer deleteDetailedDetailByOfflineNumber(String number);
}
