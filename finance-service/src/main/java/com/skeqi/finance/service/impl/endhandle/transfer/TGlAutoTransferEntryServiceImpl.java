package com.skeqi.finance.service.impl.endhandle.transfer;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.skeqi.common.utils.PageUtils;
import com.skeqi.common.core.page.PagePlus;
import com.skeqi.common.core.page.TableDataInfo;
import org.springframework.stereotype.Service;
import com.skeqi.common.core.mybatisplus.core.ServicePlusImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.skeqi.finance.pojo.bo.transfer.TGlAutoTransferEntryAddBo;
import com.skeqi.finance.pojo.bo.transfer.TGlAutoTransferEntryQueryBo;
import com.skeqi.finance.pojo.bo.transfer.TGlAutoTransferEntryEditBo;
import com.skeqi.finance.domain.endhandle.transfer.TGlAutoTransferEntry;
import com.skeqi.finance.mapper.endhandle.TGlAutoTransferEntryMapper;
import com.skeqi.finance.pojo.vo.transfer.TGlAutoTransferEntryVo;
import com.skeqi.finance.service.endhandle.transfer.ITGlAutoTransferEntryService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 自动转账分录Service业务层处理
 *
 * @author toms
 * @date 2021-07-26
 */
@Service
public class TGlAutoTransferEntryServiceImpl extends ServicePlusImpl<TGlAutoTransferEntryMapper, TGlAutoTransferEntry> implements ITGlAutoTransferEntryService {

    @Override
    public TGlAutoTransferEntryVo queryById(Integer fTransferEntryId){
        return getVoById(fTransferEntryId, TGlAutoTransferEntryVo.class);
    }

    @Override
    public TableDataInfo<TGlAutoTransferEntryVo> queryPageList(TGlAutoTransferEntryQueryBo bo) {
        PagePlus<TGlAutoTransferEntry, TGlAutoTransferEntryVo> result = pageVo(PageUtils.buildPagePlus(), buildQueryWrapper(bo), TGlAutoTransferEntryVo.class);
        return PageUtils.buildDataInfo(result);
    }

    @Override
    public List<TGlAutoTransferEntryVo> queryList(TGlAutoTransferEntryQueryBo bo) {
        return listVo(buildQueryWrapper(bo), TGlAutoTransferEntryVo.class);
    }
	@Override
	public List<TGlAutoTransferEntryVo> queryListByPid(Integer pId) {
		return baseMapper.queryPageByPId(pId);
	}


    private LambdaQueryWrapper<TGlAutoTransferEntry> buildQueryWrapper(TGlAutoTransferEntryQueryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TGlAutoTransferEntry> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getFTransferId() != null, TGlAutoTransferEntry::getFTransferId, bo.getFTransferId());
        lqw.eq(bo.getFEntrySeq() != null, TGlAutoTransferEntry::getFEntrySeq, bo.getFEntrySeq());
        lqw.eq(StrUtil.isNotBlank(bo.getFExplanation()), TGlAutoTransferEntry::getFExplanation, bo.getFExplanation());
        lqw.eq(bo.getFAccountId() != null, TGlAutoTransferEntry::getFAccountId, bo.getFAccountId());
        lqw.eq(StrUtil.isNotBlank(bo.getFItemIsvalid()), TGlAutoTransferEntry::getFItemIsvalid, bo.getFItemIsvalid());
        lqw.eq(bo.getFCurrencyId() != null, TGlAutoTransferEntry::getFCurrencyId, bo.getFCurrencyId());
        lqw.eq(bo.getFExchangeRateType() != null, TGlAutoTransferEntry::getFExchangeRateType, bo.getFExchangeRateType());
        lqw.eq(bo.getFDc() != null, TGlAutoTransferEntry::getFDc, bo.getFDc());
        lqw.eq(StrUtil.isNotBlank(bo.getFType()), TGlAutoTransferEntry::getFType, bo.getFType());
        lqw.eq(bo.getFPercentage() != null, TGlAutoTransferEntry::getFPercentage, bo.getFPercentage());
        lqw.eq(StrUtil.isNotBlank(bo.getFFormulaType()), TGlAutoTransferEntry::getFFormulaType, bo.getFFormulaType());
        lqw.eq(StrUtil.isNotBlank(bo.getFAmountforFormula()), TGlAutoTransferEntry::getFAmountforFormula, bo.getFAmountforFormula());
        lqw.eq(StrUtil.isNotBlank(bo.getFAmountFormula()), TGlAutoTransferEntry::getFAmountFormula, bo.getFAmountFormula());
        lqw.eq(StrUtil.isNotBlank(bo.getFQtymula()), TGlAutoTransferEntry::getFQtymula, bo.getFQtymula());
        lqw.eq(StrUtil.isNotBlank(bo.getFIsmultiCollect()), TGlAutoTransferEntry::getFIsmultiCollect, bo.getFIsmultiCollect());
        lqw.eq(StrUtil.isNotBlank(bo.getFPosted()), TGlAutoTransferEntry::getFPosted, bo.getFPosted());
        return lqw;
    }

    @Override
    public Boolean insertByAddBo(TGlAutoTransferEntryAddBo bo) {
        TGlAutoTransferEntry add = BeanUtil.toBean(bo, TGlAutoTransferEntry.class);
        validEntityBeforeSave(add);
        return save(add);
    }

    @Override
    public Boolean updateByEditBo(TGlAutoTransferEntryEditBo bo) {
        TGlAutoTransferEntry update = BeanUtil.toBean(bo, TGlAutoTransferEntry.class);
        validEntityBeforeSave(update);
        return updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(TGlAutoTransferEntry entity){
        //TODO 做一些数据校验,如唯一约束
    }

    @Override
    public Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return removeByIds(ids);
    }
}
