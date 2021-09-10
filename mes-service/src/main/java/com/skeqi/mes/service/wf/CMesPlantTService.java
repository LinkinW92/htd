package com.skeqi.mes.service.wf;

import com.skeqi.mes.pojo.qh.CMesPlantT;
import com.skeqi.mes.util.Rjson;

import java.util.List;

public interface CMesPlantTService{
    List<CMesPlantT> findPlantAll(CMesPlantT cMesPlantT);

    Rjson addPlant(CMesPlantT plantT) throws Exception;

    Rjson editPlant(CMesPlantT plantT) throws Exception;

    Rjson delPlantByIdAndCode(CMesPlantT plantT) throws Exception;
}
