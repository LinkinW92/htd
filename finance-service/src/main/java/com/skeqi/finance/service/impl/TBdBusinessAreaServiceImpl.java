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
import com.skeqi.finance.pojo.bo.basicinformation.area.TBdBusinessAreaAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.area.TBdBusinessAreaQueryBo;
import com.skeqi.finance.pojo.bo.basicinformation.area.TBdBusinessAreaEditBo;
import com.skeqi.finance.domain.TBdBusinessArea;
import com.skeqi.finance.mapper.TBdBusinessAreaMapper;
import com.skeqi.finance.pojo.vo.basicinformation.base.TBdBusinessAreaVo;
import com.skeqi.finance.service.basicinformation.base.ITBdBusinessAreaService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 业务领域Service业务层处理
 *
 * @author toms
 * @date 2021-07-13
 */
@Service
public class TBdBusinessAreaServiceImpl extends ServicePlusImpl<TBdBusinessAreaMapper, TBdBusinessArea> implements ITBdBusinessAreaService {

    @Override
    public TBdBusinessAreaVo queryById(Integer id){
        return getVoById(id, TBdBusinessAreaVo.class);
    }

    @Override
    public TableDataInfo<TBdBusinessAreaVo> queryPageList(TBdBusinessAreaQueryBo bo) {
        PagePlus<TBdBusinessArea, TBdBusinessAreaVo> result = pageVo(PageUtils.buildPagePlus(), buildQueryWrapper(bo), TBdBusinessAreaVo.class);
        return PageUtils.buildDataInfo(result);
    }

    @Override
    public List<TBdBusinessAreaVo> queryList(TBdBusinessAreaQueryBo bo) {
        return listVo(buildQueryWrapper(bo), TBdBusinessAreaVo.class);
    }

    private LambdaQueryWrapper<TBdBusinessArea> buildQueryWrapper(TBdBusinessAreaQueryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TBdBusinessArea> lqw = Wrappers.lambdaQuery();
        lqw.like(StrUtil.isNotBlank(bo.getName()), TBdBusinessArea::getName, bo.getName());
        lqw.eq(StrUtil.isNotBlank(bo.getFIssysPreset()), TBdBusinessArea::getFIssysPreset, bo.getFIssysPreset());
        return lqw;
    }

    @Override
    public Boolean insertByAddBo(TBdBusinessAreaAddBo bo) {
        TBdBusinessArea add = BeanUtil.toBean(bo, TBdBusinessArea.class);
        validEntityBeforeSave(add);
        return save(add);
    }

    @Override
    public Boolean updateByEditBo(TBdBusinessAreaEditBo bo) {
        TBdBusinessArea update = BeanUtil.toBean(bo, TBdBusinessArea.class);
        validEntityBeforeSave(update);
        return updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(TBdBusinessArea entity){
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
