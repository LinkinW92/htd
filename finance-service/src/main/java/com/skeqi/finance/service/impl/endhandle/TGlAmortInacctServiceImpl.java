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
import com.skeqi.finance.pojo.bo.endhandle.TGlAmortInacctAddBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlAmortInacctQueryBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlAmortInacctEditBo;
import com.skeqi.finance.domain.endhandle.amortization.TGlAmortInacct;
import com.skeqi.finance.mapper.endhandle.TGlAmortInacctMapper;
import com.skeqi.finance.pojo.vo.endhandle.TGlAmortInacctVo;
import com.skeqi.finance.service.endhandle.ITGlAmortInacctService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 凭证摊销-转入科目Service业务层处理
 *
 * @author toms
 * @date 2021-07-27
 */
@Service
public class TGlAmortInacctServiceImpl extends ServicePlusImpl<TGlAmortInacctMapper, TGlAmortInacct> implements ITGlAmortInacctService {

    @Override
    public TGlAmortInacctVo queryById(Long fId){
        return getVoById(fId, TGlAmortInacctVo.class);
    }

    @Override
    public TableDataInfo<TGlAmortInacctVo> queryPageList(TGlAmortInacctQueryBo bo) {
        PagePlus<TGlAmortInacct, TGlAmortInacctVo> result = pageVo(PageUtils.buildPagePlus(), buildQueryWrapper(bo), TGlAmortInacctVo.class);
        return PageUtils.buildDataInfo(result);
    }

    @Override
    public List<TGlAmortInacctVo> queryList(TGlAmortInacctQueryBo bo) {
        return listVo(buildQueryWrapper(bo), TGlAmortInacctVo.class);
    }

    private LambdaQueryWrapper<TGlAmortInacct> buildQueryWrapper(TGlAmortInacctQueryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TGlAmortInacct> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getFEnterAccountId() != null, TGlAmortInacct::getFEnterAccountId, bo.getFEnterAccountId());
        lqw.eq(bo.getFEnterRatio() != null, TGlAmortInacct::getFEnterRatio, bo.getFEnterRatio());
        lqw.eq(bo.getFDetailId() != null, TGlAmortInacct::getFDetailId, bo.getFDetailId());
        lqw.eq(StrUtil.isNotBlank(bo.getFEnterRatioFixed()), TGlAmortInacct::getFEnterRatioFixed, bo.getFEnterRatioFixed());
        return lqw;
    }

    @Override
    public Boolean insertByAddBo(TGlAmortInacctAddBo bo) {
        TGlAmortInacct add = BeanUtil.toBean(bo, TGlAmortInacct.class);
        validEntityBeforeSave(add);
        return save(add);
    }

    @Override
    public Boolean updateByEditBo(TGlAmortInacctEditBo bo) {
        TGlAmortInacct update = BeanUtil.toBean(bo, TGlAmortInacct.class);
        validEntityBeforeSave(update);
        return updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(TGlAmortInacct entity){
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
