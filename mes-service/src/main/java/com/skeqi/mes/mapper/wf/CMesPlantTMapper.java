package com.skeqi.mes.mapper.wf;

import com.skeqi.mes.pojo.qh.CMesPlantT;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CMesPlantTMapper {

    List<CMesPlantT> findPlantAll(CMesPlantT cMesPlantT);

    List<CMesPlantT> selectPlantByCode(@Param("plantCode")String plantCode);

    Integer addPlant(CMesPlantT plantT);

    Integer editPlant(CMesPlantT plantT);

    Integer editPlantByAreaCode(Map<String, Object> map);

    Integer delPlantByIdAndCode(CMesPlantT plantT);

    List<CMesPlantT> selectPlantByAreaCode(@Param("areaCode")String areaCode);

    List<CMesPlantT> selectPlantByCompanyCode(@Param("companyCode")String companyCode);

    Integer delPlantByCompanyCode(@Param("companyCode")String companyCode);
}
