package com.skeqi.finance.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.skeqi.common.utils.PageUtils;
import com.skeqi.common.core.page.PagePlus;
import com.skeqi.common.core.page.TableDataInfo;
import org.springframework.stereotype.Service;
import com.skeqi.common.core.mybatisplus.core.ServicePlusImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.skeqi.finance.pojo.bo.basicinformation.depr.TFaDeprMethodEntryAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.depr.TFaDeprMethodEntryQueryBo;
import com.skeqi.finance.pojo.bo.basicinformation.depr.TFaDeprMethodEntryEditBo;
import com.skeqi.finance.domain.TFaDeprMethodEntry;
import com.skeqi.finance.mapper.TFaDeprMethodEntryMapper;
import com.skeqi.finance.pojo.vo.basicinformation.depr.TFaDeprMethodEntryVo;
import com.skeqi.finance.service.depr.ITFaDeprMethodEntryService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 折旧方法明细Service业务层处理
 *
 * @author toms
 * @date 2021-07-09
 */
@Service
public class TFaDeprMethodEntryServiceImpl extends ServicePlusImpl<TFaDeprMethodEntryMapper, TFaDeprMethodEntry> implements ITFaDeprMethodEntryService {

    @Override
    public TFaDeprMethodEntryVo queryById(Integer fEntryId){
        return getVoById(fEntryId, TFaDeprMethodEntryVo.class);
    }

    @Override
    public TableDataInfo<TFaDeprMethodEntryVo> queryPageList(TFaDeprMethodEntryQueryBo bo) {
        PagePlus<TFaDeprMethodEntry, TFaDeprMethodEntryVo> result = pageVo(PageUtils.buildPagePlus(), buildQueryWrapper(bo), TFaDeprMethodEntryVo.class);
        return PageUtils.buildDataInfo(result);
    }

    @Override
    public List<TFaDeprMethodEntryVo> queryList(TFaDeprMethodEntryQueryBo bo) {
        return listVo(buildQueryWrapper(bo), TFaDeprMethodEntryVo.class);
    }

    private LambdaQueryWrapper<TFaDeprMethodEntry> buildQueryWrapper(TFaDeprMethodEntryQueryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TFaDeprMethodEntry> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getFId() != null, TFaDeprMethodEntry::getFId, bo.getFId());
        lqw.eq(StrUtil.isNotBlank(bo.getFFormulaType()), TFaDeprMethodEntry::getFFormulaType, bo.getFFormulaType());
        lqw.eq(StrUtil.isNotBlank(bo.getFFormulaContent()), TFaDeprMethodEntry::getFFormulaContent, bo.getFFormulaContent());
        lqw.eq(StrUtil.isNotBlank(bo.getFIsactive()), TFaDeprMethodEntry::getFIsactive, bo.getFIsactive());
        lqw.eq(StrUtil.isNotBlank(bo.getFIslastTwoyear()), TFaDeprMethodEntry::getFIslastTwoyear, bo.getFIslastTwoyear());
        return lqw;
    }

    @Override
    public Boolean insertByAddBo(TFaDeprMethodEntryAddBo bo) {
        TFaDeprMethodEntry add = BeanUtil.toBean(bo, TFaDeprMethodEntry.class);
        validEntityBeforeSave(add);
        return save(add);
    }

    @Override
    public Boolean updateByEditBo(TFaDeprMethodEntryEditBo bo) {
        TFaDeprMethodEntry update = BeanUtil.toBean(bo, TFaDeprMethodEntry.class);
        validEntityBeforeSave(update);
        return updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(TFaDeprMethodEntry entity){
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
