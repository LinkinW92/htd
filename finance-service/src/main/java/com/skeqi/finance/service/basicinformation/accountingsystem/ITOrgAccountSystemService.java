package com.skeqi.finance.service.basicinformation.accountingsystem;

import com.skeqi.finance.domain.basicinformation.accountingsystem.TOrgAccountSystem;
import com.skeqi.finance.pojo.bo.basicinformation.voucher.TBdVoucherGroupEditBo;
import com.skeqi.finance.pojo.vo.basicinformation.accountingsystem.TOrgAccountSystemVo;
import com.skeqi.finance.pojo.bo.basicinformation.accountingsystem.TOrgAccountSystemQueryBo;
import com.skeqi.finance.pojo.bo.basicinformation.accountingsystem.TOrgAccountSystemAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.accountingsystem.TOrgAccountSystemEditBo;
import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 会计核算体系Service接口
 *
 * @author toms
 * @date 2021-07-09
 */
public interface ITOrgAccountSystemService extends IServicePlus<TOrgAccountSystem> {
	/**
	 * 查询单个
	 * @return
	 */
	TOrgAccountSystemVo queryById(Integer fId);

	/**
	 * 查询列表
	 */
    TableDataInfo<TOrgAccountSystemVo> queryPageList(TOrgAccountSystemQueryBo bo);

	/**
	 * 查询列表
	 */
	List<TOrgAccountSystemVo> queryList(TOrgAccountSystemQueryBo bo);

	/**
	 * 根据新增业务对象插入会计核算体系
	 * @param bo 会计核算体系新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(TOrgAccountSystemAddBo bo);

	/**
	 * 根据编辑业务对象修改会计核算体系
	 * @param bo 会计核算体系编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TOrgAccountSystemEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @return
	 */
	Boolean deleteWithValidByIds(List<Integer> ids);

	/**
	 * 审核
	 * @param ids
	 * @return
	 */
	Boolean auditByIds(List<Integer> ids);

	/**
	 * 反审核
	 * @param ids
	 * @return
	 */
	Boolean antiAuditByIds(List<Integer> ids);

	/**
	 * 禁用
	 * @param bo
	 * @return
	 */
	Boolean disableByEditBo(TOrgAccountSystemEditBo bo);


	/**
	 * 查询会计核算体系列表
	 * @param bo
	 * @return
	 */
	TableDataInfo<TOrgAccountSystemVo> queryPageListAccountSystem(TOrgAccountSystemQueryBo bo);


}
