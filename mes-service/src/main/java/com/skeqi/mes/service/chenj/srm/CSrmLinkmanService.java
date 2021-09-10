package com.skeqi.mes.service.chenj.srm;


import com.skeqi.mes.pojo.chenj.srm.CSrmLinkman;

import java.util.List;

/**
 * @author ChenJ
 * @date 2021/7/18
 * @Classname CSrmLinkmanService
 * @Description ${Description}
 */

public interface CSrmLinkmanService {


    int insertSelective(CSrmLinkman record);

    CSrmLinkman selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CSrmLinkman record);

    int updateBatchSelective(List<CSrmLinkman> list);

    int batchInsert(List<CSrmLinkman> list);

}



