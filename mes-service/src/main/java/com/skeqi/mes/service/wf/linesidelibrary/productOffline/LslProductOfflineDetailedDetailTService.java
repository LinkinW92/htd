package com.skeqi.mes.service.wf.linesidelibrary.productOffline;

import com.skeqi.mes.pojo.wf.linesidelibrary.productOffline.LslProductOfflineDetailedDetailT;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LslProductOfflineDetailedDetailTService {

    List<LslProductOfflineDetailedDetailT> selectAll(LslProductOfflineDetailedDetailT detailedDetailT);

    Integer insertByList(List<LslProductOfflineDetailedDetailT> detailedDetailTS);

    LslProductOfflineDetailedDetailT selectByPrimaryKey(String number);

    Integer deleteDetailedDetailByOfflineNumber(String number);

}
