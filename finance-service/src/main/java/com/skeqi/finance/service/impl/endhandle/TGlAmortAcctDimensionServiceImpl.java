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
import com.skeqi.finance.pojo.bo.endhandle.TGlAmortAcctDimensionAddBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlAmortAcctDimensionQueryBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlAmortAcctDimensionEditBo;
import com.skeqi.finance.domain.endhandle.amortization.TGlAmortAcctDimension;
import com.skeqi.finance.mapper.endhandle.TGlAmortAcctDimensionMapper;
import com.skeqi.finance.pojo.vo.endhandle.TGlAmortAcctDimensionVo;
import com.skeqi.finance.service.endhandle.ITGlAmortAcctDimensionService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 凭证摊销预提维度控制Service业务层处理
 *
 * @author toms
 * @date 2021-07-27
 */
@Service
public class TGlAmortAcctDimensionServiceImpl extends ServicePlusImpl<TGlAmortAcctDimensionMapper, TGlAmortAcctDimension> implements ITGlAmortAcctDimensionService {

    @Override
    public TGlAmortAcctDimensionVo queryById(Long dimensionId){
        return getVoById(dimensionId, TGlAmortAcctDimensionVo.class);
    }

    @Override
    public TableDataInfo<TGlAmortAcctDimensionVo> queryPageList(TGlAmortAcctDimensionQueryBo bo) {
        PagePlus<TGlAmortAcctDimension, TGlAmortAcctDimensionVo> result = pageVo(PageUtils.buildPagePlus(), buildQueryWrapper(bo), TGlAmortAcctDimensionVo.class);
        return PageUtils.buildDataInfo(result);
    }

    @Override
    public List<TGlAmortAcctDimensionVo> queryList(TGlAmortAcctDimensionQueryBo bo) {
        return listVo(buildQueryWrapper(bo), TGlAmortAcctDimensionVo.class);
    }

    private LambdaQueryWrapper<TGlAmortAcctDimension> buildQueryWrapper(TGlAmortAcctDimensionQueryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TGlAmortAcctDimension> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getDimensionId() != null, TGlAmortAcctDimension::getDimensionId, bo.getDimensionId());
        lqw.eq(StrUtil.isNotBlank(bo.getDsCode()), TGlAmortAcctDimension::getDsCode, bo.getDsCode());
        lqw.like(StrUtil.isNotBlank(bo.getDsName()), TGlAmortAcctDimension::getDsName, bo.getDsName());
        lqw.eq(bo.getAmortEntryId() != null, TGlAmortAcctDimension::getAmortEntryId, bo.getAmortEntryId());
        lqw.eq(bo.getAcctId() != null, TGlAmortAcctDimension::getAcctId, bo.getAcctId());
        return lqw;
    }

    @Override
    public Boolean insertByAddBo(TGlAmortAcctDimensionAddBo bo) {
        TGlAmortAcctDimension add = BeanUtil.toBean(bo, TGlAmortAcctDimension.class);
        validEntityBeforeSave(add);
        return save(add);
    }

    @Override
    public Boolean updateByEditBo(TGlAmortAcctDimensionEditBo bo) {
        TGlAmortAcctDimension update = BeanUtil.toBean(bo, TGlAmortAcctDimension.class);
        validEntityBeforeSave(update);
        return updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(TGlAmortAcctDimension entity){
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
