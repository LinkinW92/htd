package com.skeqi.finance.service.basicinformation.accountingsystem;

import com.skeqi.finance.domain.basicinformation.accountingsystem.TOrgAcctSysDetail;
import com.skeqi.finance.pojo.vo.basicinformation.accountingsystem.TOrgAcctSysDetailVo;
import com.skeqi.finance.pojo.bo.basicinformation.accountingsystem.TOrgAcctSysDetailQueryBo;
import com.skeqi.finance.pojo.bo.basicinformation.accountingsystem.TOrgAcctSysDetailAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.accountingsystem.TOrgAcctSysDetailEditBo;
import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 会计核算体系之下级组织Service接口
 *
 * @author toms
 * @date 2021-07-09
 */
public interface ITOrgAcctSysDetailService extends IServicePlus<TOrgAcctSysDetail> {
	/**
	 * 查询单个
	 * @return
	 */
	TOrgAcctSysDetailVo queryById(Integer fId);

	/**
	 * 查询列表
	 */
    TableDataInfo<TOrgAcctSysDetailVo> queryPageList(TOrgAcctSysDetailQueryBo bo);

	/**
	 * 查询列表
	 */
	List<TOrgAcctSysDetailVo> queryList(TOrgAcctSysDetailQueryBo bo);

	/**
	 * 根据新增业务对象插入会计核算体系之下级组织
	 * @param bo 会计核算体系之下级组织新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(TOrgAcctSysDetailAddBo bo);

	/**
	 * 根据编辑业务对象修改会计核算体系之下级组织
	 * @param bo 会计核算体系之下级组织编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TOrgAcctSysDetailEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid);
}
