package com.skeqi.mes.mapper.chenj.srm;

import com.skeqi.mes.pojo.chenj.srm.CSrmAlteration;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmAlterationMapper
 * @Description ${Description}
 */

public interface CSrmAlterationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CSrmAlteration record);

    int insertOrUpdate(CSrmAlteration record);

    int insertOrUpdateSelective(CSrmAlteration record);

    int insertSelective(CSrmAlteration record);

    CSrmAlteration selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CSrmAlteration record);

    int updateByPrimaryKey(CSrmAlteration record);

    int updateBatch(List<CSrmAlteration> list);

    int updateBatchSelective(List<CSrmAlteration> list);

    int batchInsert(@Param("list") List<CSrmAlteration> list);
}
