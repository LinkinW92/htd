package com.skeqi.finance.service.basicinformation.base;

import com.skeqi.finance.domain.TBdFileManage;
import com.skeqi.finance.pojo.vo.basicinformation.base.TBdFileManageVo;
import com.skeqi.finance.pojo.bo.basicinformation.file.TBdFileManageQueryBo;
import com.skeqi.finance.pojo.bo.basicinformation.file.TBdFileManageAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.file.TBdFileManageEditBo;
import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 文件管理Service接口
 *
 * @author toms
 * @date 2021-08-18
 */
public interface ITBdFileManageService extends IServicePlus<TBdFileManage> {
	/**
	 * 查询单个
	 * @return
	 */
	TBdFileManageVo queryById(Integer id);

	/**
	 * 查询列表
	 */
    TableDataInfo<TBdFileManageVo> queryPageList(TBdFileManageQueryBo bo);

	/**
	 * 查询列表
	 */
	List<TBdFileManageVo> queryList(TBdFileManageQueryBo bo);

	/**
	 * 根据新增业务对象插入文件管理
	 * @param bo 文件管理新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(TBdFileManageAddBo bo);

	/**
	 * 根据编辑业务对象修改文件管理
	 * @param bo 文件管理编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TBdFileManageEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid);
}
