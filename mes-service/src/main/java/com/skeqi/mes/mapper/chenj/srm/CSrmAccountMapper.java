package com.skeqi.mes.mapper.chenj.srm;


import com.skeqi.mes.pojo.chenj.srm.CSrmAccount;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author ChenJ
 * @date 2021/6/18
 * @Classname CSrmAccountMapper
 * @Description ${Description}
 */

public interface CSrmAccountMapper {

    int insertOrUpdateSelective(CSrmAccount record);

    int insertSelective(CSrmAccount record);

    CSrmAccount selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CSrmAccount record);

    int updateByPrimaryKey(CSrmAccount record);

    int updateBatch(List<CSrmAccount> list);

    int updateBatchSelective(List<CSrmAccount> list);

    int batchInsert(@Param("list") List<CSrmAccount> list);
}
