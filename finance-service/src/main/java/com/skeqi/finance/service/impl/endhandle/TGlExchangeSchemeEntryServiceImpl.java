package com.skeqi.finance.service.impl.endhandle;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.skeqi.common.utils.PageUtils;
import com.skeqi.common.core.page.PagePlus;
import com.skeqi.common.core.page.TableDataInfo;
import org.springframework.stereotype.Service;
import com.skeqi.common.core.mybatisplus.core.ServicePlusImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.skeqi.finance.pojo.bo.endhandle.TGlExchangeSchemeEntryAddBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlExchangeSchemeEntryQueryBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlExchangeSchemeEntryEditBo;
import com.skeqi.finance.domain.endhandle.TGlExchangeSchemeEntry;
import com.skeqi.finance.mapper.endhandle.TGlExchangeSchemeEntryMapper;
import com.skeqi.finance.pojo.vo.endhandle.TGlExchangeSchemeEntryVo;
import com.skeqi.finance.service.endhandle.ITGlExchangeSchemeEntryService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 期末调汇方案分录Service业务层处理
 *
 * @author toms
 * @date 2021-07-30
 */
@Service
public class TGlExchangeSchemeEntryServiceImpl extends ServicePlusImpl<TGlExchangeSchemeEntryMapper, TGlExchangeSchemeEntry> implements ITGlExchangeSchemeEntryService {

    @Override
    public TGlExchangeSchemeEntryVo queryById(Long fEntryId){
        return getVoById(fEntryId, TGlExchangeSchemeEntryVo.class);
    }

    @Override
    public TableDataInfo<TGlExchangeSchemeEntryVo> queryPageList(TGlExchangeSchemeEntryQueryBo bo) {
        PagePlus<TGlExchangeSchemeEntry, TGlExchangeSchemeEntryVo> result = pageVo(PageUtils.buildPagePlus(), buildQueryWrapper(bo), TGlExchangeSchemeEntryVo.class);
        return PageUtils.buildDataInfo(result);
    }

    @Override
    public List<TGlExchangeSchemeEntryVo> queryList(TGlExchangeSchemeEntryQueryBo bo) {
        return listVo(buildQueryWrapper(bo), TGlExchangeSchemeEntryVo.class);
    }

    private LambdaQueryWrapper<TGlExchangeSchemeEntry> buildQueryWrapper(TGlExchangeSchemeEntryQueryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TGlExchangeSchemeEntry> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getFId() != null, TGlExchangeSchemeEntry::getFId, bo.getFId());
        lqw.eq(bo.getFAcctId() != null, TGlExchangeSchemeEntry::getFAcctId, bo.getFAcctId());
        lqw.eq(StrUtil.isNotBlank(bo.getFIsSelected()), TGlExchangeSchemeEntry::getFIsSelected, bo.getFIsSelected());
        lqw.eq(bo.getFseq() != null, TGlExchangeSchemeEntry::getFseq, bo.getFseq());
        return lqw;
    }

    @Override
    public Boolean insertByAddBo(TGlExchangeSchemeEntryAddBo bo) {
        TGlExchangeSchemeEntry add = BeanUtil.toBean(bo, TGlExchangeSchemeEntry.class);
        validEntityBeforeSave(add);
        return save(add);
    }

    @Override
    public Boolean updateByEditBo(TGlExchangeSchemeEntryEditBo bo) {
        TGlExchangeSchemeEntry update = BeanUtil.toBean(bo, TGlExchangeSchemeEntry.class);
        validEntityBeforeSave(update);
        return updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(TGlExchangeSchemeEntry entity){
        //TODO 做一些数据校验,如唯一约束
    }

    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return removeByIds(ids);
    }
}
