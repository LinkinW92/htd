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
import com.skeqi.finance.pojo.bo.endhandle.TGlExchangeFlexEntryAddBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlExchangeFlexEntryQueryBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlExchangeFlexEntryEditBo;
import com.skeqi.finance.domain.endhandle.TGlExchangeFlexEntry;
import com.skeqi.finance.mapper.endhandle.TGlExchangeFlexEntryMapper;
import com.skeqi.finance.pojo.vo.endhandle.TGlExchangeFlexEntryVo;
import com.skeqi.finance.service.endhandle.ITGlExchangeFlexEntryService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 期末调汇核算维度分录Service业务层处理
 *
 * @author toms
 * @date 2021-07-30
 */
@Service
public class TGlExchangeFlexEntryServiceImpl extends ServicePlusImpl<TGlExchangeFlexEntryMapper, TGlExchangeFlexEntry> implements ITGlExchangeFlexEntryService {

    @Override
    public TGlExchangeFlexEntryVo queryById(Integer fEntryId){
        return getVoById(fEntryId, TGlExchangeFlexEntryVo.class);
    }

    @Override
    public TableDataInfo<TGlExchangeFlexEntryVo> queryPageList(TGlExchangeFlexEntryQueryBo bo) {
        PagePlus<TGlExchangeFlexEntry, TGlExchangeFlexEntryVo> result = pageVo(PageUtils.buildPagePlus(), buildQueryWrapper(bo), TGlExchangeFlexEntryVo.class);
        return PageUtils.buildDataInfo(result);
    }

    @Override
    public List<TGlExchangeFlexEntryVo> queryList(TGlExchangeFlexEntryQueryBo bo) {
        return listVo(buildQueryWrapper(bo), TGlExchangeFlexEntryVo.class);
    }

    private LambdaQueryWrapper<TGlExchangeFlexEntry> buildQueryWrapper(TGlExchangeFlexEntryQueryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TGlExchangeFlexEntry> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getFId() != null, TGlExchangeFlexEntry::getFId, bo.getFId());
        lqw.eq(bo.getFFlexId() != null, TGlExchangeFlexEntry::getFFlexId, bo.getFFlexId());
        lqw.eq(StrUtil.isNotBlank(bo.getFFlexValue()), TGlExchangeFlexEntry::getFFlexValue, bo.getFFlexValue());
        return lqw;
    }

    @Override
    public Boolean insertByAddBo(TGlExchangeFlexEntryAddBo bo) {
        TGlExchangeFlexEntry add = BeanUtil.toBean(bo, TGlExchangeFlexEntry.class);
        validEntityBeforeSave(add);
        return save(add);
    }

    @Override
    public Boolean updateByEditBo(TGlExchangeFlexEntryEditBo bo) {
        TGlExchangeFlexEntry update = BeanUtil.toBean(bo, TGlExchangeFlexEntry.class);
        validEntityBeforeSave(update);
        return updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(TGlExchangeFlexEntry entity){
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
