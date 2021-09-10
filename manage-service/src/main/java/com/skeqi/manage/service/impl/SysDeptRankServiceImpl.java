package com.skeqi.manage.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.skeqi.common.utils.PageUtils;
import com.skeqi.common.core.page.PagePlus;
import com.skeqi.common.core.page.TableDataInfo;
import org.springframework.stereotype.Service;
import com.skeqi.common.core.mybatisplus.core.ServicePlusImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.skeqi.manage.domain.bo.SysDeptRankAddBo;
import com.skeqi.manage.domain.bo.SysDeptRankQueryBo;
import com.skeqi.manage.domain.bo.SysDeptRankEditBo;
import com.skeqi.manage.domain.SysDeptRank;
import com.skeqi.manage.mapper.SysDeptRankMapper;
import com.skeqi.manage.domain.vo.SysDeptRankVo;
import com.skeqi.manage.service.ISysDeptRankService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 职级Service业务层处理
 *
 * @author toms
 * @date 2021-08-26
 */
@Service
public class SysDeptRankServiceImpl extends ServicePlusImpl<SysDeptRankMapper, SysDeptRank> implements ISysDeptRankService {

    @Override
    public SysDeptRankVo queryById(Integer id){
        return getVoById(id, SysDeptRankVo.class);
    }

    @Override
    public TableDataInfo<SysDeptRankVo> queryPageList(SysDeptRankQueryBo bo) {
        PagePlus<SysDeptRank, SysDeptRankVo> result = pageVo(PageUtils.buildPagePlus(), buildQueryWrapper(bo), SysDeptRankVo.class);
        return PageUtils.buildDataInfo(result);
    }

    @Override
    public List<SysDeptRankVo> queryList(SysDeptRankQueryBo bo) {
        return listVo(buildQueryWrapper(bo), SysDeptRankVo.class);
    }

    private LambdaQueryWrapper<SysDeptRank> buildQueryWrapper(SysDeptRankQueryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<SysDeptRank> lqw = Wrappers.lambdaQuery();
        lqw.eq(StrUtil.isNotBlank(bo.getCode()), SysDeptRank::getCode, bo.getCode());
        lqw.like(StrUtil.isNotBlank(bo.getName()), SysDeptRank::getName, bo.getName());
        lqw.eq(bo.getLevel() != null, SysDeptRank::getLevel, bo.getLevel());
        return lqw;
    }

    @Override
    public Boolean insertByAddBo(SysDeptRankAddBo bo) {
        SysDeptRank add = BeanUtil.toBean(bo, SysDeptRank.class);
        validEntityBeforeSave(add);
        return save(add);
    }

    @Override
    public Boolean updateByEditBo(SysDeptRankEditBo bo) {
        SysDeptRank update = BeanUtil.toBean(bo, SysDeptRank.class);
        validEntityBeforeSave(update);
        return updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(SysDeptRank entity){
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
