package com.skeqi.finance.service.account;

import com.skeqi.common.core.domain.AjaxResult;
import com.skeqi.finance.domain.TBdAccountCalendar;
import com.skeqi.finance.pojo.vo.TBdAccount.TBdAccountCalendarVo;
import com.skeqi.finance.pojo.bo.basicinformation.calendar.TBdAccountCalendarQueryBo;
import com.skeqi.finance.pojo.bo.basicinformation.calendar.TBdAccountCalendarAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.calendar.TBdAccountCalendarEditBo;
import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 会计日历Service接口
 *
 * @author toms
 * @date 2021-07-14
 */
public interface ITBdAccountCalendarService extends IServicePlus<TBdAccountCalendar> {
	/**
	 * 查询单个
	 * @return
	 */
	TBdAccountCalendarVo queryById(Integer fId);

	/**
	 * 查询列表
	 */
    TableDataInfo<TBdAccountCalendarVo> queryPageList(TBdAccountCalendarQueryBo bo);

	/**
	 * 查询列表
	 */
	List<TBdAccountCalendarVo> queryList(TBdAccountCalendarQueryBo bo);

	/**
	 * 根据新增业务对象插入会计日历
	 * @param bo 会计日历新增业务对象
	 * @return
	 */
	AjaxResult insertByAddBo(TBdAccountCalendarAddBo bo);

	/**
	 * 根据编辑业务对象修改会计日历
	 * @param bo 会计日历编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TBdAccountCalendarEditBo bo);

	/**
	 * 审核
	 * @param ids
	 * @return
	 */
	Boolean audit(Collection<Integer> ids);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid);
}
