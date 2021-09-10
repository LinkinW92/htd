package com.skeqi.finance.service;

import com.skeqi.finance.domain.TBdCredentialParam;
import com.skeqi.finance.pojo.vo.TBdCredentialParamVo;
import com.skeqi.finance.pojo.bo.TBdCredentialParamQueryBo;
import com.skeqi.finance.pojo.bo.TBdCredentialParamAddBo;
import com.skeqi.finance.pojo.bo.TBdCredentialParamEditBo;
import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 凭证Service接口
 *
 * @author toms
 * @date 2021-07-09
 */
public interface ITBdCredentialParamService extends IServicePlus<TBdCredentialParam> {
	/**
	 * 查询单个
	 * @return
	 */
	TBdCredentialParamVo queryById(Integer fId);

	/**
	 * 查询列表
	 */
    TableDataInfo<TBdCredentialParamVo> queryPageList(TBdCredentialParamQueryBo bo);

	/**
	 * 查询列表
	 */
	List<TBdCredentialParamVo> queryList(TBdCredentialParamQueryBo bo);

	/**
	 * 根据新增业务对象插入凭证
	 * @param bo 凭证新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(TBdCredentialParamAddBo bo);

	/**
	 * 根据编辑业务对象修改凭证
	 * @param bo 凭证编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TBdCredentialParamEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid);
}
