package com.skeqi.finance.service;

import com.skeqi.finance.domain.SysOrganization;
import com.skeqi.finance.pojo.vo.SysOrganizationVo;
import com.skeqi.finance.pojo.bo.basicinformation.org.SysOrganizationQueryBo;
import com.skeqi.finance.pojo.bo.basicinformation.org.SysOrganizationAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.org.SysOrganizationEditBo;
import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 组织管理Service接口
 *
 * @author toms
 * @date 2021-07-16
 */
public interface ISysOrganizationService extends IServicePlus<SysOrganization> {
	/**
	 * 查询单个
	 * @return
	 */
	SysOrganizationVo queryById(Integer id);

	/**
	 * 查询列表
	 */
    TableDataInfo<SysOrganizationVo> queryPageList(SysOrganizationQueryBo bo);

	/**
	 * 查询列表
	 */
	List<SysOrganizationVo> queryList(SysOrganizationQueryBo bo);

	/**
	 * 根据新增业务对象插入组织管理
	 * @param bo 组织管理新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(SysOrganizationAddBo bo);

	/**
	 * 根据编辑业务对象修改组织管理
	 * @param bo 组织管理编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(SysOrganizationEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid);
}
