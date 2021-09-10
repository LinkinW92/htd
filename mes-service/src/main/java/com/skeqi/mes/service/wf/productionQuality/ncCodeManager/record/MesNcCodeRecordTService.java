package com.skeqi.mes.service.wf.productionQuality.ncCodeManager.record;

import com.skeqi.mes.pojo.wf.productionQuality.ncCodeManager.record.MesNcCodeRecordT;
import com.skeqi.mes.util.Rjson;

import java.util.List;

public interface MesNcCodeRecordTService{

    Rjson deleteByPrimaryKey(String number) throws Exception;

    Rjson insertSelective(MesNcCodeRecordT record) throws Exception;

    MesNcCodeRecordT selectByPrimaryKey(String number);

    Rjson updateByStatus(MesNcCodeRecordT record) throws Exception;

    List<MesNcCodeRecordT> selectAll(MesNcCodeRecordT mesNcCodeRecordT);

    Integer addNcCodeRecordByList(List<MesNcCodeRecordT> ncCodeRecordTList);
}
