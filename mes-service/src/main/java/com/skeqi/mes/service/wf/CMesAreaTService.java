package com.skeqi.mes.service.wf;

import com.skeqi.mes.pojo.qh.CMesAreaT;
import com.skeqi.mes.util.Rjson;

import java.util.List;

public interface CMesAreaTService{

    List<CMesAreaT> findAreaAll(CMesAreaT cMesAreaT);

    Rjson addArea(CMesAreaT areaT) throws Exception;

    Rjson editArea(CMesAreaT areaT) throws Exception;

    Rjson delAreaByIdAndCode(CMesAreaT areaT) throws Exception;
}
