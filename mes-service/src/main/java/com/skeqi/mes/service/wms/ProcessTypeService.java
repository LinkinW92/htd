package com.skeqi.mes.service.wms;

import java.util.List;

import com.skeqi.mes.pojo.wms.ProcessType;

/**
 * @date 2020年3月5日
 * @author yinp
 * @ 审批类型
 */
public interface ProcessTypeService {

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
	 * 通过id查询
	 * @param dx
	 * @return
	 */
	ProcessType findProcessTypeById(Integer processTypeId);

	/**
	 * 通过name查询
	 * @param dx
	 * @return
	 */
	ProcessType findProcessTypeByName(String processTypeName);

	/**
	 * 新增
	 * @param dx
	 * @return
	 */
	boolean addProcessType(ProcessType dx);

	/**
	 * 更新
	 * @param dx
	 * @return
	 */
	boolean updateProcessType(ProcessType dx);

	/**
	 * 删除
	 * @param processTypeId
	 * @return
	 */
	boolean deleteProcessType(Integer processTypeId);

}
