package com.skeqi.mes.mapper.wf.linesidelibrary.productOffline;

import com.skeqi.mes.pojo.wf.linesidelibrary.productOffline.LslProductOfflineDetailedT;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LslProductOfflineDetailedTMapper {
    List<LslProductOfflineDetailedT> selectAll(LslProductOfflineDetailedT detailedT);

    LslProductOfflineDetailedT selectByPrimaryKey(String number);

    Integer insertByList(@Param("detailedTList") List<LslProductOfflineDetailedT> detailedTList);

    Integer deleteDetailedByOfflineNumber(String number);
}
