package com.skeqi.manage.service;

import com.skeqi.manage.domain.SysDeptRank;
import com.skeqi.manage.domain.vo.SysDeptRankVo;
import com.skeqi.manage.domain.bo.SysDeptRankQueryBo;
import com.skeqi.manage.domain.bo.SysDeptRankAddBo;
import com.skeqi.manage.domain.bo.SysDeptRankEditBo;
import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 职级Service接口
 *
 * @author toms
 * @date 2021-08-26
 */
public interface ISysDeptRankService extends IServicePlus<SysDeptRank> {
	/**
	 * 查询单个
	 * @return
	 */
	SysDeptRankVo queryById(Integer id);

	/**
	 * 查询列表
	 */
    TableDataInfo<SysDeptRankVo> queryPageList(SysDeptRankQueryBo bo);

	/**
	 * 查询列表
	 */
	List<SysDeptRankVo> queryList(SysDeptRankQueryBo bo);

	/**
	 * 根据新增业务对象插入职级
	 * @param bo 职级新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(SysDeptRankAddBo bo);

	/**
	 * 根据编辑业务对象修改职级
	 * @param bo 职级编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(SysDeptRankEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid);
}
