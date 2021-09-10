package com.skeqi.finance.service.basicinformation.accountingsystem;

import com.skeqi.finance.domain.basicinformation.accountingsystem.TOrgAcctSysEntry;
import com.skeqi.finance.pojo.vo.basicinformation.accountingsystem.TOrgAcctSysEntryVo;
import com.skeqi.finance.pojo.bo.basicinformation.accountingsystem.TOrgAcctSysEntryQueryBo;
import com.skeqi.finance.pojo.bo.basicinformation.accountingsystem.TOrgAcctSysEntryAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.accountingsystem.TOrgAcctSysEntryEditBo;
import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 会计核算体系之会计主体Service接口
 *
 * @author toms
 * @date 2021-07-09
 */
public interface ITOrgAcctSysEntryService extends IServicePlus<TOrgAcctSysEntry> {
	/**
	 * 查询单个
	 * @return
	 */
	TOrgAcctSysEntryVo queryById(Integer fId);

	/**
	 * 查询列表
	 */
    TableDataInfo<TOrgAcctSysEntryVo> queryPageList(TOrgAcctSysEntryQueryBo bo);

	/**
	 * 查询列表
	 */
	List<TOrgAcctSysEntryVo> queryList(TOrgAcctSysEntryQueryBo bo);

	/**
	 * 根据新增业务对象插入会计核算体系之会计主体
	 * @param bo 会计核算体系之会计主体新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(TOrgAcctSysEntryAddBo bo);

	/**
	 * 根据编辑业务对象修改会计核算体系之会计主体
	 * @param bo 会计核算体系之会计主体编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TOrgAcctSysEntryEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid);

	/**
	 * 校验并批量添加数据
	 * @param entryAddBoList
	 * @return
	 */
    Boolean insertByAddByList(List<TOrgAcctSysEntryAddBo> entryAddBoList);


	/**
	 * 校验并批量编辑数据
	 * @param entryAddBoList
	 * @return
	 */
	Boolean updateByEditBoList(List<TOrgAcctSysEntryEditBo> entryAddBoList);

	/**
	 * 按核算体系id查询会计主体列表
	 * @param fAcctsystemId
	 * @return
	 */
	List<TOrgAcctSysEntryVo> queryListByFAcctsystemId(Integer fAcctsystemId);
}
