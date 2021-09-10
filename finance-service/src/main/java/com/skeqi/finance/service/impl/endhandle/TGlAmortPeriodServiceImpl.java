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
import com.skeqi.finance.pojo.bo.endhandle.TGlAmortPeriodAddBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlAmortPeriodQueryBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlAmortPeriodEditBo;
import com.skeqi.finance.domain.endhandle.amortization.TGlAmortPeriod;
import com.skeqi.finance.mapper.endhandle.TGlAmortPeriodMapper;
import com.skeqi.finance.pojo.vo.endhandle.TGlAmortPeriodVo;
import com.skeqi.finance.service.endhandle.ITGlAmortPeriodService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 凭证摊销-摊销期间Service业务层处理
 *
 * @author toms
 * @date 2021-07-27
 */
@Service
public class TGlAmortPeriodServiceImpl extends ServicePlusImpl<TGlAmortPeriodMapper, TGlAmortPeriod> implements ITGlAmortPeriodService {

    @Override
    public TGlAmortPeriodVo queryById(Long fSchemeId){
        return getVoById(fSchemeId, TGlAmortPeriodVo.class);
    }

    @Override
    public TableDataInfo<TGlAmortPeriodVo> queryPageList(TGlAmortPeriodQueryBo bo) {
        PagePlus<TGlAmortPeriod, TGlAmortPeriodVo> result = pageVo(PageUtils.buildPagePlus(), buildQueryWrapper(bo), TGlAmortPeriodVo.class);
        return PageUtils.buildDataInfo(result);
    }

    @Override
    public List<TGlAmortPeriodVo> queryList(TGlAmortPeriodQueryBo bo) {
        return listVo(buildQueryWrapper(bo), TGlAmortPeriodVo.class);
    }

    private LambdaQueryWrapper<TGlAmortPeriod> buildQueryWrapper(TGlAmortPeriodQueryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TGlAmortPeriod> lqw = Wrappers.lambdaQuery();
        lqw.eq(StrUtil.isNotBlank(bo.getFYearPeriod()), TGlAmortPeriod::getFYearPeriod, bo.getFYearPeriod());
        lqw.eq(bo.getFAmortratio() != null, TGlAmortPeriod::getFAmortratio, bo.getFAmortratio());
        lqw.eq(bo.getFAmortamount() != null, TGlAmortPeriod::getFAmortamount, bo.getFAmortamount());
        lqw.eq(StrUtil.isNotBlank(bo.getFAmorted()), TGlAmortPeriod::getFAmorted, bo.getFAmorted());
        lqw.eq(bo.getFYear() != null, TGlAmortPeriod::getFYear, bo.getFYear());
        lqw.eq(bo.getFPeriod() != null, TGlAmortPeriod::getFPeriod, bo.getFPeriod());
        lqw.eq(StrUtil.isNotBlank(bo.getFAmortRatioFixed()), TGlAmortPeriod::getFAmortRatioFixed, bo.getFAmortRatioFixed());
        return lqw;
    }

    @Override
    public Boolean insertByAddBo(TGlAmortPeriodAddBo bo) {
        TGlAmortPeriod add = BeanUtil.toBean(bo, TGlAmortPeriod.class);
        validEntityBeforeSave(add);
        return save(add);
    }

    @Override
    public Boolean updateByEditBo(TGlAmortPeriodEditBo bo) {
        TGlAmortPeriod update = BeanUtil.toBean(bo, TGlAmortPeriod.class);
        validEntityBeforeSave(update);
        return updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(TGlAmortPeriod entity){
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
