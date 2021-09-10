package com.skeqi.finance.service.basicinformation.base;

import com.skeqi.common.core.domain.AjaxResult;
import com.skeqi.finance.domain.TBdExecuteLog;
import com.skeqi.finance.pojo.vo.basicinformation.base.TBdExecuteLogVo;
import com.skeqi.finance.pojo.bo.basicinformation.explanation.TBdExecuteLogQueryBo;
import com.skeqi.finance.pojo.bo.basicinformation.explanation.TBdExecuteLogAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.explanation.TBdExecuteLogEditBo;
import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 执行业务日志记录Service接口
 *
 * @author toms
 * @date 2021-08-07
 */
public interface ITBdExecuteLogService extends IServicePlus<TBdExecuteLog> {
	/**
	 * 查询单个
	 * @return
	 */
	TBdExecuteLogVo queryById(Integer id);

	/**
	 * 查询列表
	 */
    TableDataInfo<TBdExecuteLogVo> queryPageList(TBdExecuteLogQueryBo bo);

	/**
	 * 查询列表
	 */
	List<TBdExecuteLogVo> queryList(TBdExecuteLogQueryBo bo);

	/**
	 * 根据新增业务对象插入执行业务日志记录
	 * @param bo 执行业务日志记录新增业务对象
	 * @return
	 */
	AjaxResult<TBdExecuteLog> insertByAddBo(TBdExecuteLogAddBo bo);

	/**
	 * 根据编辑业务对象修改执行业务日志记录
	 * @param bo 执行业务日志记录编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TBdExecuteLogEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid);
}
