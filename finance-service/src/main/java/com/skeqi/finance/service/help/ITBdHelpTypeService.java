package com.skeqi.finance.service.help;

import com.skeqi.finance.domain.help.TBdHelpType;
import com.skeqi.finance.pojo.vo.basicinformation.help.TBdHelpTypeVo;
import com.skeqi.finance.pojo.bo.help.TBdHelpTypeQueryBo;
import com.skeqi.finance.pojo.bo.help.TBdHelpTypeAddBo;
import com.skeqi.finance.pojo.bo.help.TBdHelpTypeEditBo;
import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 辅助资料类别Service接口
 *
 * @author toms
 * @date 2021-07-13
 */
public interface ITBdHelpTypeService extends IServicePlus<TBdHelpType> {
	/**
	 * 查询单个
	 * @return
	 */
	TBdHelpTypeVo queryById(Integer fId);

	/**
	 * 查询列表
	 */
    TableDataInfo<TBdHelpTypeVo> queryPageList(TBdHelpTypeQueryBo bo);

	TableDataInfo<TBdHelpTypeVo> queryPageGroup(TBdHelpTypeQueryBo bo);

	/**
	 * 查询下级
	 * @param bo
	 * @return
	 */
	List<TBdHelpTypeVo> getNextGrade(TBdHelpTypeQueryBo bo);

	/**
	 * 查询列表
	 */
	List<TBdHelpTypeVo> queryList(TBdHelpTypeQueryBo bo);

	/**
	 * 根据新增业务对象插入辅助资料类别
	 * @param bo 辅助资料类别新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(TBdHelpTypeAddBo bo);

	/**
	 * 根据编辑业务对象修改辅助资料类别
	 * @param bo 辅助资料类别编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TBdHelpTypeEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid);
}
