package com.skeqi.mes.service.wf.productionQuality.ncCodeManager.config;

import com.skeqi.mes.pojo.wf.productionQuality.ncCodeManager.config.MesNcCodeConfigT;
import com.skeqi.mes.util.Rjson;

import java.util.List;

public interface MesNcCodeConfigTService {


    Rjson deleteByPrimaryKey(Integer id) throws Exception;

    Rjson insertSelective(MesNcCodeConfigT record) throws Exception;

    MesNcCodeConfigT selectByPrimaryKey(Integer id);

    Rjson updateByPrimaryKeySelective(MesNcCodeConfigT record) throws Exception;

    List<MesNcCodeConfigT> selectAll(MesNcCodeConfigT mesNcCodeConfigT);

    Rjson verifySn(String sn) throws Exception;
}
