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
import com.skeqi.finance.pojo.bo.endhandle.TGlAmortInacctDimensionAddBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlAmortInacctDimensionQueryBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlAmortInacctDimensionEditBo;
import com.skeqi.finance.domain.endhandle.amortization.TGlAmortInacctDimension;
import com.skeqi.finance.mapper.endhandle.TGlAmortInacctDimensionMapper;
import com.skeqi.finance.pojo.vo.endhandle.TGlAmortInacctDimensionVo;
import com.skeqi.finance.service.endhandle.ITGlAmortInacctDimensionService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 凭证摊销转入科目维度控制Service业务层处理
 *
 * @author toms
 * @date 2021-07-27
 */
@Service
public class TGlAmortInacctDimensionServiceImpl extends ServicePlusImpl<TGlAmortInacctDimensionMapper, TGlAmortInacctDimension> implements ITGlAmortInacctDimensionService {

    @Override
    public TGlAmortInacctDimensionVo queryById(Long dimensionId){
        return getVoById(dimensionId, TGlAmortInacctDimensionVo.class);
    }

    @Override
    public TableDataInfo<TGlAmortInacctDimensionVo> queryPageList(TGlAmortInacctDimensionQueryBo bo) {
        PagePlus<TGlAmortInacctDimension, TGlAmortInacctDimensionVo> result = pageVo(PageUtils.buildPagePlus(), buildQueryWrapper(bo), TGlAmortInacctDimensionVo.class);
        return PageUtils.buildDataInfo(result);
    }

    @Override
    public List<TGlAmortInacctDimensionVo> queryList(TGlAmortInacctDimensionQueryBo bo) {
        return listVo(buildQueryWrapper(bo), TGlAmortInacctDimensionVo.class);
    }

    private LambdaQueryWrapper<TGlAmortInacctDimension> buildQueryWrapper(TGlAmortInacctDimensionQueryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TGlAmortInacctDimension> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getDimensionId() != null, TGlAmortInacctDimension::getDimensionId, bo.getDimensionId());
        lqw.eq(StrUtil.isNotBlank(bo.getDsCode()), TGlAmortInacctDimension::getDsCode, bo.getDsCode());
        lqw.like(StrUtil.isNotBlank(bo.getDsName()), TGlAmortInacctDimension::getDsName, bo.getDsName());
        lqw.eq(bo.getAmortEntryId() != null, TGlAmortInacctDimension::getAmortEntryId, bo.getAmortEntryId());
        lqw.eq(bo.getAcctId() != null, TGlAmortInacctDimension::getAcctId, bo.getAcctId());
        return lqw;
    }

    @Override
    public Boolean insertByAddBo(TGlAmortInacctDimensionAddBo bo) {
        TGlAmortInacctDimension add = BeanUtil.toBean(bo, TGlAmortInacctDimension.class);
        validEntityBeforeSave(add);
        return save(add);
    }

    @Override
    public Boolean updateByEditBo(TGlAmortInacctDimensionEditBo bo) {
        TGlAmortInacctDimension update = BeanUtil.toBean(bo, TGlAmortInacctDimension.class);
        validEntityBeforeSave(update);
        return updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(TGlAmortInacctDimension entity){
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
