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
import com.skeqi.finance.pojo.bo.TBdAccount.TBdAccountDistributeAddBo;
import com.skeqi.finance.pojo.bo.TBdAccount.TBdAccountDistributeQueryBo;
import com.skeqi.finance.pojo.bo.TBdAccount.TBdAccountDistributeEditBo;
import com.skeqi.finance.domain.TBdAccountDistribute;
import com.skeqi.finance.mapper.TBdAccountDistributeMapper;
import com.skeqi.finance.pojo.vo.TBdAccount.TBdAccountDistributeVo;
import com.skeqi.finance.service.account.ITBdAccountDistributeService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 科目分发Service业务层处理
 *
 * @author toms
 * @date 2021-07-09
 */
@Service
public class TBdAccountDistributeServiceImpl extends ServicePlusImpl<TBdAccountDistributeMapper, TBdAccountDistribute> implements ITBdAccountDistributeService {

    @Override
    public TBdAccountDistributeVo queryById(Integer fEntryId){
        return getVoById(fEntryId, TBdAccountDistributeVo.class);
    }

    @Override
    public TableDataInfo<TBdAccountDistributeVo> queryPageList(TBdAccountDistributeQueryBo bo) {
        PagePlus<TBdAccountDistribute, TBdAccountDistributeVo> result = pageVo(PageUtils.buildPagePlus(), buildQueryWrapper(bo), TBdAccountDistributeVo.class);
        return PageUtils.buildDataInfo(result);
    }

    @Override
    public List<TBdAccountDistributeVo> queryList(TBdAccountDistributeQueryBo bo) {
        return listVo(buildQueryWrapper(bo), TBdAccountDistributeVo.class);
    }

    private LambdaQueryWrapper<TBdAccountDistribute> buildQueryWrapper(TBdAccountDistributeQueryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TBdAccountDistribute> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getFAcctId() != null, TBdAccountDistribute::getFAcctId, bo.getFAcctId());
        lqw.eq(bo.getFDistributeOrgid() != null, TBdAccountDistribute::getFDistributeOrgid, bo.getFDistributeOrgid());
        lqw.eq(bo.getFUseOrgid() != null, TBdAccountDistribute::getFUseOrgid, bo.getFUseOrgid());
        lqw.eq(bo.getFDistributeorId() != null, TBdAccountDistribute::getFDistributeorId, bo.getFDistributeorId());
        lqw.eq(bo.getFDistributeDate() != null, TBdAccountDistribute::getFDistributeDate, bo.getFDistributeDate());
        lqw.eq(StrUtil.isNotBlank(bo.getFIsaddChild()), TBdAccountDistribute::getFIsaddChild, bo.getFIsaddChild());
        lqw.eq(StrUtil.isNotBlank(bo.getFForbidStatus()), TBdAccountDistribute::getFForbidStatus, bo.getFForbidStatus());
        lqw.eq(bo.getFForbidderId() != null, TBdAccountDistribute::getFForbidderId, bo.getFForbidderId());
        lqw.eq(bo.getFForbidOrgid() != null, TBdAccountDistribute::getFForbidOrgid, bo.getFForbidOrgid());
        lqw.eq(bo.getFForbidDate() != null, TBdAccountDistribute::getFForbidDate, bo.getFForbidDate());
        lqw.eq(StrUtil.isNotBlank(bo.getFIsredistribute()), TBdAccountDistribute::getFIsredistribute, bo.getFIsredistribute());
        return lqw;
    }

    @Override
    public Boolean insertByAddBo(TBdAccountDistributeAddBo bo) {
        TBdAccountDistribute add = BeanUtil.toBean(bo, TBdAccountDistribute.class);
        validEntityBeforeSave(add);
        return save(add);
    }

    @Override
    public Boolean updateByEditBo(TBdAccountDistributeEditBo bo) {
        TBdAccountDistribute update = BeanUtil.toBean(bo, TBdAccountDistribute.class);
        validEntityBeforeSave(update);
        return updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(TBdAccountDistribute entity){
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
