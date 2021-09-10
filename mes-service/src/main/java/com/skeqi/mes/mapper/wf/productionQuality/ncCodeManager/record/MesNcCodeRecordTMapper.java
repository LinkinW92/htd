package com.skeqi.mes.mapper.wf.productionQuality.ncCodeManager.record;

import com.skeqi.mes.pojo.wf.productionQuality.ncCodeManager.record.MesNcCodeRecordT;

import java.util.List;

public interface MesNcCodeRecordTMapper {
    int deleteByPrimaryKey(String number);

    int insertSelective(MesNcCodeRecordT record);

    MesNcCodeRecordT selectByPrimaryKey(String number);

    int updateByStatus(MesNcCodeRecordT record);

    List<MesNcCodeRecordT> selectAll(MesNcCodeRecordT mesNcCodeRecordT);

    Integer addNcCodeRecordByList(List<MesNcCodeRecordT> ncCodeRecordTList);
}
