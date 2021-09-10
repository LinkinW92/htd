package com.skeqi.finance.service.basicinformation.voucher;

import com.skeqi.finance.domain.voucher.TGlVoucherEntry;
import com.skeqi.finance.pojo.vo.voucher.TGlVoucherEntryVo;
import com.skeqi.finance.pojo.bo.voucher.TGlVoucherEntryQueryBo;
import com.skeqi.finance.pojo.bo.voucher.TGlVoucherEntryAddBo;
import com.skeqi.finance.pojo.bo.voucher.TGlVoucherEntryEditBo;
import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;
import com.skeqi.finance.pojo.vo.voucher.VoucherEntryVo;

import java.util.Collection;
import java.util.List;

/**
 * 凭证录入分Service接口
 *
 * @author toms
 * @date 2021-07-09
 */
public interface ITGlVoucherEntryService extends IServicePlus<TGlVoucherEntry> {
	/**
	 * 查询单个
	 * @return
	 */
	TGlVoucherEntryVo queryById(Integer fEntryId);

	/**
	 * 查询列表
	 * @return
	 */
	List<VoucherEntryVo> queryByVchId(Integer vchId);

	/**
	 * 查询列表
	 * @return
	 */
	List<VoucherEntryVo> queryByEntryId(Integer vchId);

	/**
	 * 查询列表
	 */
    TableDataInfo<TGlVoucherEntryVo> queryPageList(TGlVoucherEntryQueryBo bo);

	/**
	 * 查询列表
	 */
	List<TGlVoucherEntryVo> queryList(TGlVoucherEntryQueryBo bo);

	/**
	 * 根据新增业务对象插入凭证录入分
	 * @param bo 凭证录入分新增业务对象
	 * @return
	 */
	Integer insertByAddBo(TGlVoucherEntryAddBo bo);

	/**
	 * 更新维度
	 * @param id
	 * @param detailCode
	 * @return
	 */
	Integer updateDetailCode(Integer id,String detailCode,String dimensionCode);


	/**
	 * 根据编辑业务对象修改凭证录入分
	 * @param bo 凭证录入分编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TGlVoucherEntryEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid);
}
