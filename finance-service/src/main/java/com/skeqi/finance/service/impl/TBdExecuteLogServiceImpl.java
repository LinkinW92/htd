package com.skeqi.finance.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.skeqi.common.core.domain.AjaxResult;
import com.skeqi.common.utils.PageUtils;
import com.skeqi.common.core.page.PagePlus;
import com.skeqi.common.core.page.TableDataInfo;
import com.skeqi.finance.enums.BaseEnum;
import org.springframework.stereotype.Service;
import com.skeqi.common.core.mybatisplus.core.ServicePlusImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.skeqi.finance.pojo.bo.basicinformation.explanation.TBdExecuteLogAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.explanation.TBdExecuteLogQueryBo;
import com.skeqi.finance.pojo.bo.basicinformation.explanation.TBdExecuteLogEditBo;
import com.skeqi.finance.domain.TBdExecuteLog;
import com.skeqi.finance.mapper.TBdExecuteLogMapper;
import com.skeqi.finance.pojo.vo.basicinformation.base.TBdExecuteLogVo;
import com.skeqi.finance.service.basicinformation.base.ITBdExecuteLogService;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 执行业务日志记录Service业务层处理
 *
 * @author toms
 * @date 2021-08-07
 */
@Service
public class TBdExecuteLogServiceImpl extends ServicePlusImpl<TBdExecuteLogMapper, TBdExecuteLog> implements ITBdExecuteLogService {

    @Override
    public TBdExecuteLogVo queryById(Integer id){
        return getVoById(id, TBdExecuteLogVo.class);
    }

    @Override
    public TableDataInfo<TBdExecuteLogVo> queryPageList(TBdExecuteLogQueryBo bo) {
        PagePlus<TBdExecuteLog, TBdExecuteLogVo> result = pageVo(PageUtils.buildPagePlus(), buildQueryWrapper(bo), TBdExecuteLogVo.class);
        return PageUtils.buildDataInfo(result);
    }

    @Override
    public List<TBdExecuteLogVo> queryList(TBdExecuteLogQueryBo bo) {
        return listVo(buildQueryWrapper(bo), TBdExecuteLogVo.class);
    }

    private LambdaQueryWrapper<TBdExecuteLog> buildQueryWrapper(TBdExecuteLogQueryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TBdExecuteLog> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getOutExecuteId() != null, TBdExecuteLog::getOutExecuteId, bo.getOutExecuteId());
        lqw.eq(StrUtil.isNotBlank(bo.getExecuteStatus()), TBdExecuteLog::getExecuteStatus, bo.getExecuteStatus());
        lqw.eq(StrUtil.isNotBlank(bo.getExecuteDetail()), TBdExecuteLog::getExecuteDetail, bo.getExecuteDetail());
        return lqw;
    }

    @Override
    public AjaxResult<TBdExecuteLog> insertByAddBo(TBdExecuteLogAddBo bo) {
        TBdExecuteLog add = BeanUtil.toBean(bo, TBdExecuteLog.class);
        validEntityBeforeSave(add);
        add.setCreateTime(new Date());
        add.setExecuteStatus(BaseEnum.YES.getCode());
        this.save(add);
        return AjaxResult.success(add);
    }

    @Override
    public Boolean updateByEditBo(TBdExecuteLogEditBo bo) {
        TBdExecuteLog update = BeanUtil.toBean(bo, TBdExecuteLog.class);
        validEntityBeforeSave(update);
        return updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(TBdExecuteLog entity){
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
