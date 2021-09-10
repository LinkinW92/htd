package com.skeqi.mes.service.chenj.srm;

import com.skeqi.mes.pojo.chenj.srm.CSrmFileUploading;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmFileUploadingReq;
import com.skeqi.mes.pojo.chenj.srm.rsp.CSrmFileUploadingRsp;

import java.util.List;

/**
 * @author ChenJ
 * @date 2021/8/10
 * @Classname CSrmFileUploadingService
 * @Description ${Description}
 */

public interface CSrmFileUploadingService{


    int deleteByPrimaryKey(List<Integer> id);

    int insertSelective(CSrmFileUploading record);

    List<CSrmFileUploadingRsp> selectByPrimaryKey(CSrmFileUploadingReq record);

    int updateByPrimaryKeySelective(CSrmFileUploading record);

    int updateBatchSelective(List<CSrmFileUploading> list);

    int batchInsert(List<CSrmFileUploading> list);

}
