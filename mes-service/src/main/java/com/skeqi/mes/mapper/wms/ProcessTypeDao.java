package com.skeqi.mes.mapper.wms;

import java.util.List;

import com.skeqi.mes.pojo.wms.ProcessType;

/**
 * @date 2020年3月5日
 * @author yinp
 * @ 审批类型
 */
public interface ProcessTypeDao {

	/**
	 * 查询
	 * @param dx
	 * @return
	 */
	List<ProcessType> findProcessTypeList(ProcessType dx);

	/**
	 * 查询所有Id、NAME
	 * @return
	 */
	List<ProcessType> findProcessTypeAll();

	/**
	 * 新增
	 * @param dx
	 * @return
	 */
	Integer addProcessType(ProcessType dx);

	/**
	 * 更新
	 * @param dx
	 * @return
	 */
	Integer updateProcessType(ProcessType dx);

	/**
	 * 删除
	 * @param processTypeId
	 * @return
	 */
	Integer deleteProcessType(Integer processTypeId);

}
