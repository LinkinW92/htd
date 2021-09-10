package com.skeqi.mes.mapper.chenj.srm;


import com.skeqi.mes.pojo.chenj.srm.CSrmAccessory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ChenJ
 * @date 2021/7/18
 * @Classname CSrmAccessoryMapper
 * @Description ${Description}
 */

public interface CSrmAccessoryMapper {
    int insertSelective(CSrmAccessory record);

    CSrmAccessory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CSrmAccessory record);

    int updateBatchSelective(List<CSrmAccessory> list);

    int batchInsert(@Param("list") List<CSrmAccessory> list);

    // 查询供应商代码是否存在
    CSrmAccessory selectSupplierCode(String supplierCode);
}
