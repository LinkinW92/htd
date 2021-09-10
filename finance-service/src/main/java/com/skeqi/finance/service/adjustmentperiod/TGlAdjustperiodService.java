package com.skeqi.finance.service.adjustmentperiod;

import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;
import com.skeqi.finance.domain.adjustmentperiod.TGlAdjustperiod;
import com.baomidou.mybatisplus.extension.service.IService;
import com.skeqi.finance.domain.voucher.TGlVoucher;
import com.skeqi.finance.pojo.bo.adjustmentperiod.TGlAdjustperiodpAddBo;
import com.skeqi.finance.pojo.bo.adjustmentperiod.TGlAdjustperiodpEditBo;
import com.skeqi.finance.pojo.bo.adjustmentperiod.TGlAdjustperiodpQueryBo;
import com.skeqi.finance.pojo.bo.basicinformation.voucher.TBdVoucherGroupQueryBo;
import com.skeqi.finance.pojo.bo.voucher.TGlVoucherQueryBo;
import com.skeqi.finance.pojo.vo.adjustmentperiod.TGlAdjustperiodVo;
import com.skeqi.finance.pojo.vo.basicinformation.voucher.TBdVoucherGroupVo;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

public interface TGlAdjustperiodService extends IServicePlus<TGlAdjustperiod>{


	TableDataInfo<TGlAdjustperiodVo> queryPageList(TGlAdjustperiodpQueryBo bo);

	Boolean insertByAddBo(TGlAdjustperiodpAddBo bo);

	Boolean updateByEditBo(TGlAdjustperiodpEditBo bo);

	Boolean deleteWithValidByIds(List<Integer> integers);

	Boolean updateStatus(List<Integer>  ids);

	Integer queryByBookIdAndYear(TGlAdjustperiodpQueryBo bo);

	List<TGlAdjustperiodVo> queryList(TGlAdjustperiodpQueryBo bo);
}
