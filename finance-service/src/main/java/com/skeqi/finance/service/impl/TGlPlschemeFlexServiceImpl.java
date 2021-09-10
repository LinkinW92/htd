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
import com.skeqi.finance.pojo.bo.endhandle.TGlPlschemeFlexAddBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlPlschemeFlexQueryBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlPlschemeFlexEditBo;
import com.skeqi.finance.domain.endhandle.TGlPlschemeFlex;
import com.skeqi.finance.mapper.endhandle.TGlPlschemeFlexMapper;
import com.skeqi.finance.pojo.vo.endhandle.TGlPlschemeFlexVo;
import com.skeqi.finance.service.endhandle.ITGlPlschemeFlexService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 结账损益核算维度Service业务层处理
 *
 * @author toms
 * @date 2021-08-17
 */
@Service
public class TGlPlschemeFlexServiceImpl extends ServicePlusImpl<TGlPlschemeFlexMapper, TGlPlschemeFlex> implements ITGlPlschemeFlexService {

    @Override
    public TGlPlschemeFlexVo queryById(Integer fEntryId){
        return getVoById(fEntryId, TGlPlschemeFlexVo.class);
    }

    @Override
    public TableDataInfo<TGlPlschemeFlexVo> queryPageList(TGlPlschemeFlexQueryBo bo) {
        PagePlus<TGlPlschemeFlex, TGlPlschemeFlexVo> result = pageVo(PageUtils.buildPagePlus(), buildQueryWrapper(bo), TGlPlschemeFlexVo.class);
        return PageUtils.buildDataInfo(result);
    }

    @Override
    public List<TGlPlschemeFlexVo> queryList(TGlPlschemeFlexQueryBo bo) {
        return listVo(buildQueryWrapper(bo), TGlPlschemeFlexVo.class);
    }

    private LambdaQueryWrapper<TGlPlschemeFlex> buildQueryWrapper(TGlPlschemeFlexQueryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TGlPlschemeFlex> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getFId() != null, TGlPlschemeFlex::getFId, bo.getFId());
        lqw.eq(bo.getFFlexId() != null, TGlPlschemeFlex::getFFlexId, bo.getFFlexId());
        lqw.eq(StrUtil.isNotBlank(bo.getFFlexValue()), TGlPlschemeFlex::getFFlexValue, bo.getFFlexValue());
        return lqw;
    }

    @Override
    public Boolean insertByAddBo(TGlPlschemeFlexAddBo bo) {
        TGlPlschemeFlex add = BeanUtil.toBean(bo, TGlPlschemeFlex.class);
        validEntityBeforeSave(add);
        return save(add);
    }

    @Override
    public Boolean updateByEditBo(TGlPlschemeFlexEditBo bo) {
        TGlPlschemeFlex update = BeanUtil.toBean(bo, TGlPlschemeFlex.class);
        validEntityBeforeSave(update);
        return updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(TGlPlschemeFlex entity){
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
