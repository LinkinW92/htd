package com.skeqi.mes.mapper.chenj.srm;


import com.skeqi.mes.pojo.chenj.srm.CSrmFileUploading;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmFileUploadingReq;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmPurchaseDemandHRListReq;
import com.skeqi.mes.pojo.chenj.srm.rsp.CSrmFileUploadingRsp;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
* @author ChenJ
* @date 2021/8/10
* @Classname CSrmFileUploadingMapper
* @Description ${Description}
*/

public interface CSrmFileUploadingMapper {
   int deleteByPrimaryKey(List<Integer> id);

   int insertSelective(CSrmFileUploading record);

   List<CSrmFileUploadingRsp> selectByPrimaryKey(CSrmFileUploadingReq record);

	/**
	 * 根据文件名称获取订单号
	 */
	List<CSrmFileUploadingRsp> selectByPrimaryOrderNumberList(CSrmPurchaseDemandHRListReq record);

	int updateByPrimaryKeySelective(CSrmFileUploading record);

   int updateBatchSelective(List<CSrmFileUploading> list);

   int batchInsert(@Param("list") List<CSrmFileUploading> list);


	/**
	 *  校验当前单号中是否有重复文件名
	 */
	List<CSrmFileUploadingRsp>  findRepetitionFileNameList(CSrmPurchaseDemandHRListReq reqList);

}
