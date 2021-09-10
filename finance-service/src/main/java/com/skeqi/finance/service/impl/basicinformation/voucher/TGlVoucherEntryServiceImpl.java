package com.skeqi.finance.service.impl.basicinformation.voucher;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.skeqi.common.exception.CustomException;
import com.skeqi.common.utils.PageUtils;
import com.skeqi.common.core.page.PagePlus;
import com.skeqi.common.core.page.TableDataInfo;
import com.skeqi.finance.pojo.vo.voucher.VoucherEntryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.skeqi.common.core.mybatisplus.core.ServicePlusImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.skeqi.finance.pojo.bo.voucher.TGlVoucherEntryAddBo;
import com.skeqi.finance.pojo.bo.voucher.TGlVoucherEntryQueryBo;
import com.skeqi.finance.pojo.bo.voucher.TGlVoucherEntryEditBo;
import com.skeqi.finance.domain.voucher.TGlVoucherEntry;
import com.skeqi.finance.mapper.basicinformation.voucher.TGlVoucherEntryMapper;
import com.skeqi.finance.pojo.vo.voucher.TGlVoucherEntryVo;
import com.skeqi.finance.service.basicinformation.voucher.ITGlVoucherEntryService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 凭证录入分Service业务层处理
 *
 * @author toms
 * @date 2021-07-09
 */
@Service
public class TGlVoucherEntryServiceImpl extends ServicePlusImpl<TGlVoucherEntryMapper, TGlVoucherEntry> implements ITGlVoucherEntryService {


	@Autowired
	TGlVoucherEntryMapper tGlVoucherEntryMapper;
    @Override
    public TGlVoucherEntryVo queryById(Integer fEntryId){
        return getVoById(fEntryId, TGlVoucherEntryVo.class);
    }

	/**
	 * 查询列表
	 * @return
	 */
	@Override
	public List<VoucherEntryVo> queryByVchId(Integer vchId){
       return tGlVoucherEntryMapper.getPageByVid(vchId);
	}

	/**
	 * 查询列表
	 * @return
	 */
	@Override
	public List<VoucherEntryVo> queryByEntryId(Integer vchId){
		return tGlVoucherEntryMapper.queryByEntryId(vchId);
	}

    @Override
    public TableDataInfo<TGlVoucherEntryVo> queryPageList(TGlVoucherEntryQueryBo bo) {
        PagePlus<TGlVoucherEntry, TGlVoucherEntryVo> result = pageVo(PageUtils.buildPagePlus(), buildQueryWrapper(bo), TGlVoucherEntryVo.class);
        return PageUtils.buildDataInfo(result);
    }

    @Override
    public List<TGlVoucherEntryVo> queryList(TGlVoucherEntryQueryBo bo) {
        return listVo(buildQueryWrapper(bo), TGlVoucherEntryVo.class);
    }

    private LambdaQueryWrapper<TGlVoucherEntry> buildQueryWrapper(TGlVoucherEntryQueryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TGlVoucherEntry> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getFVoucherId() != null, TGlVoucherEntry::getFVoucherId, bo.getFVoucherId());
        lqw.eq(StrUtil.isNotBlank(bo.getFExplanation()), TGlVoucherEntry::getFExplanation, bo.getFExplanation());
        lqw.eq(bo.getFAccountId() != null, TGlVoucherEntry::getFAccountId, bo.getFAccountId());
        lqw.eq(bo.getFDetailCode()!=null, TGlVoucherEntry::getFDetailCode, bo.getFDetailCode());
        lqw.eq(bo.getFAmountfor() != null, TGlVoucherEntry::getFAmountfor, bo.getFAmountfor());
        lqw.eq(bo.getFAmount() != null, TGlVoucherEntry::getFAmount, bo.getFAmount());
        lqw.eq(bo.getFCurrencyId() != null, TGlVoucherEntry::getFCurrencyId, bo.getFCurrencyId());
        lqw.eq(bo.getFExchangeRateType() != null, TGlVoucherEntry::getFExchangeRateType, bo.getFExchangeRateType());
        lqw.eq(bo.getFExchangeRate() != null, TGlVoucherEntry::getFExchangeRate, bo.getFExchangeRate());
        lqw.eq(StrUtil.isNotBlank(bo.getFDc()), TGlVoucherEntry::getFDc, bo.getFDc());
        lqw.eq(bo.getFMeasureUnitId() != null, TGlVoucherEntry::getFMeasureUnitId, bo.getFMeasureUnitId());
        lqw.eq(bo.getFUnitPrice() != null, TGlVoucherEntry::getFUnitPrice, bo.getFUnitPrice());
        lqw.eq(bo.getFQuantity() != null, TGlVoucherEntry::getFQuantity, bo.getFQuantity());
        lqw.eq(bo.getFAcctUnitQty() != null, TGlVoucherEntry::getFAcctUnitQty, bo.getFAcctUnitQty());
        lqw.eq(bo.getFBaseUnitQty() != null, TGlVoucherEntry::getFBaseUnitQty, bo.getFBaseUnitQty());
        lqw.eq(bo.getFSettleTypeId() != null, TGlVoucherEntry::getFSettleTypeId, bo.getFSettleTypeId());
        lqw.eq(StrUtil.isNotBlank(bo.getFSettleNo()), TGlVoucherEntry::getFSettleNo, bo.getFSettleNo());
        lqw.eq(bo.getFDebit() != null, TGlVoucherEntry::getFDebit, bo.getFDebit());
        lqw.eq(bo.getFCredit() != null, TGlVoucherEntry::getFCredit, bo.getFCredit());
        lqw.eq(bo.getFEntrySeq() != null, TGlVoucherEntry::getFEntrySeq, bo.getFEntrySeq());
        lqw.eq(bo.getFSideEntrySeq() != null, TGlVoucherEntry::getFSideEntrySeq, bo.getFSideEntrySeq());
        lqw.eq(StrUtil.isNotBlank(bo.getFCashFlowItem()), TGlVoucherEntry::getFCashFlowItem, bo.getFCashFlowItem());
        lqw.eq(bo.getFOriginalDetailId() != null, TGlVoucherEntry::getFOriginalDetailId, bo.getFOriginalDetailId());
        lqw.eq(StrUtil.isNotBlank(bo.getFIsmultiCollect()), TGlVoucherEntry::getFIsmultiCollect, bo.getFIsmultiCollect());
        return lqw;
    }

    @Override
    public Integer insertByAddBo(TGlVoucherEntryAddBo bo) {
        TGlVoucherEntry add = BeanUtil.toBean(bo, TGlVoucherEntry.class);
        validEntityBeforeSave(add);
        baseMapper.insert(add);
        return add.getFEntryId();
    }
	/**
	 * 更新维度
	 * @param id
	 * @param detailCode
	 * @return
	 */
	@Override
	public Integer updateDetailCode(Integer id,String detailCode,String dimensionCode){
        baseMapper.updateDetailCode(detailCode,dimensionCode,id);
		return 1;
	}


    @Override
    public Boolean updateByEditBo(TGlVoucherEntryEditBo bo) {
        TGlVoucherEntry update = BeanUtil.toBean(bo, TGlVoucherEntry.class);
        validEntityBeforeSave(update);
        return updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(TGlVoucherEntry entity){
		if(null==entity.getFVoucherId()){
			throw new CustomException("凭证ID不存在",1000);
		}
		if(StrUtil.isBlank(entity.getFExplanation())){
			throw new CustomException("摘要不能为空",1000);
		}
		if(null==entity.getFAccountId()){
			throw new CustomException("科目不能为空",1000);
		}
		if(null==entity.getFCurrencyId()){
			throw new CustomException("币别不能为空",1000);
		}
		if(null==entity.getFExchangeRateType()){
			throw new CustomException("汇率类型不能为空",1000);
		}
		if(null==entity.getFExchangeRate()){
			throw new CustomException("汇率不能为空",1000);
		}
		if(null==entity.getFDc()){
			throw new CustomException("借贷方向不能为空",1000);
		}
		if(null==entity.getFAmount()){
			throw new CustomException("本位币金额不能为空",1000);
		}
		if(null==entity.getFAmountfor()){
			throw new CustomException("原币金额不能为空",1000);
		}
		if(null==entity.getFAmountfor()){
			throw new CustomException("原币金额不能为空",1000);
		}


    }

    @Override
    public Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return removeByIds(ids);
    }
}
