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
import com.skeqi.finance.pojo.bo.endhandle.TGlWithholdingPeriodAddBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlWithholdingPeriodQueryBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlWithholdingPeriodEditBo;
import com.skeqi.finance.domain.endhandle.withholding.TGlWithholdingPeriod;
import com.skeqi.finance.mapper.endhandle.TGlWithholdingPeriodMapper;
import com.skeqi.finance.pojo.vo.endhandle.TGlWithholdingPeriodVo;
import com.skeqi.finance.service.endhandle.ITGlWithholdingPeriodService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 凭证预提-预提期间Service业务层处理
 *
 * @author toms
 * @date 2021-07-27
 */
@Service
public class TGlWithholdingPeriodServiceImpl extends ServicePlusImpl<TGlWithholdingPeriodMapper, TGlWithholdingPeriod> implements ITGlWithholdingPeriodService {

    @Override
    public TGlWithholdingPeriodVo queryById(Long fYear){
        return getVoById(fYear, TGlWithholdingPeriodVo.class);
    }

    @Override
    public TableDataInfo<TGlWithholdingPeriodVo> queryPageList(TGlWithholdingPeriodQueryBo bo) {
        PagePlus<TGlWithholdingPeriod, TGlWithholdingPeriodVo> result = pageVo(PageUtils.buildPagePlus(), buildQueryWrapper(bo), TGlWithholdingPeriodVo.class);
        return PageUtils.buildDataInfo(result);
    }

    @Override
    public List<TGlWithholdingPeriodVo> queryList(TGlWithholdingPeriodQueryBo bo) {
        return listVo(buildQueryWrapper(bo), TGlWithholdingPeriodVo.class);
    }

    private LambdaQueryWrapper<TGlWithholdingPeriod> buildQueryWrapper(TGlWithholdingPeriodQueryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TGlWithholdingPeriod> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getFYear() != null, TGlWithholdingPeriod::getFYear, bo.getFYear());
        lqw.eq(bo.getFPeriod() != null, TGlWithholdingPeriod::getFPeriod, bo.getFPeriod());
        lqw.eq(StrUtil.isNotBlank(bo.getFYearPeriod()), TGlWithholdingPeriod::getFYearPeriod, bo.getFYearPeriod());
        lqw.eq(bo.getFAmortAmount() != null, TGlWithholdingPeriod::getFAmortAmount, bo.getFAmortAmount());
        lqw.eq(StrUtil.isNotBlank(bo.getFAmorted()), TGlWithholdingPeriod::getFAmorted, bo.getFAmorted());
        lqw.eq(StrUtil.isNotBlank(bo.getFExpression()), TGlWithholdingPeriod::getFExpression, bo.getFExpression());
        return lqw;
    }

    @Override
    public Boolean insertByAddBo(TGlWithholdingPeriodAddBo bo) {
        TGlWithholdingPeriod add = BeanUtil.toBean(bo, TGlWithholdingPeriod.class);
        validEntityBeforeSave(add);
        return save(add);
    }

    @Override
    public Boolean updateByEditBo(TGlWithholdingPeriodEditBo bo) {
        TGlWithholdingPeriod update = BeanUtil.toBean(bo, TGlWithholdingPeriod.class);
        validEntityBeforeSave(update);
        return updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(TGlWithholdingPeriod entity){
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
