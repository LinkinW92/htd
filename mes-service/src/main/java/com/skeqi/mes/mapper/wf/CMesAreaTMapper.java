package com.skeqi.mes.mapper.wf;

import com.skeqi.mes.pojo.qh.CMesAreaT;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CMesAreaTMapper {

    List<CMesAreaT> findAreaAll(CMesAreaT cMesAreaT);

    List<CMesAreaT> selectAreaByCode(@Param("areaCode")String areaCode);

    Integer addArea(CMesAreaT areaT);

    Integer editArea(CMesAreaT areaT);

    List<CMesAreaT> selectAreaById(@Param("id")Integer id);

    Integer delAreaByIdAndCode(CMesAreaT areaT);

    List<CMesAreaT> selectAreaByFactoryCode(String factoryCode);

    Integer editAreaById(Map<String, Object> map);

    List<CMesAreaT> selectAreaByCompanyCode(@Param("companyCode")String companyCode);

    Integer delAreaByCompanyCode(@Param("companyCode")String companyCode);
}
