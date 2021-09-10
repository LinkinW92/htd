package com.skeqi.finance.service.basicinformation.base;

import com.skeqi.finance.domain.TBdBookParam;
import com.skeqi.finance.pojo.vo.basicinformation.base.TBdBookParamVo;
import com.skeqi.finance.pojo.bo.TBdBookParamQueryBo;
import com.skeqi.finance.pojo.bo.TBdBookParamAddBo;
import com.skeqi.finance.pojo.bo.TBdBookParamEditBo;
import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 总账管理参数-账簿参数Service接口
 *
 * @author toms
 * @date 2021-07-09
 */
public interface ITBdBookParamService extends IServicePlus<TBdBookParam> {
	/**
	 * 查询单个
	 * @return
	 */
	TBdBookParamVo queryById(Integer fId);

	/**
	 * 查询列表
	 */
    TableDataInfo<TBdBookParamVo> queryPageList(TBdBookParamQueryBo bo);

	/**
	 * 查询列表
	 */
	List<TBdBookParamVo> queryList(TBdBookParamQueryBo bo);

	/**
	 * 根据新增业务对象插入总账管理参数-账簿参数
	 * @param bo 总账管理参数-账簿参数新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(TBdBookParamAddBo bo);

	/**
	 * 根据编辑业务对象修改总账管理参数-账簿参数
	 * @param bo 总账管理参数-账簿参数编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TBdBookParamEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid);
}
