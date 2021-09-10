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
import com.skeqi.finance.pojo.bo.endhandle.TGlWithholdingInacctAddBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlWithholdingInacctQueryBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlWithholdingInacctEditBo;
import com.skeqi.finance.domain.endhandle.withholding.TGlWithholdingInacct;
import com.skeqi.finance.mapper.endhandle.TGlWithholdingInacctMapper;
import com.skeqi.finance.pojo.vo.endhandle.TGlWithholdingInacctVo;
import com.skeqi.finance.service.endhandle.ITGlWithholdingInacctService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 凭证预提-转入科目Service业务层处理
 *
 * @author toms
 * @date 2021-07-27
 */
@Service
public class TGlWithholdingInacctServiceImpl extends ServicePlusImpl<TGlWithholdingInacctMapper, TGlWithholdingInacct> implements ITGlWithholdingInacctService {

    @Override
    public TGlWithholdingInacctVo queryById(Long fEnterAccountId){
        return getVoById(fEnterAccountId, TGlWithholdingInacctVo.class);
    }

    @Override
    public TableDataInfo<TGlWithholdingInacctVo> queryPageList(TGlWithholdingInacctQueryBo bo) {
        PagePlus<TGlWithholdingInacct, TGlWithholdingInacctVo> result = pageVo(PageUtils.buildPagePlus(), buildQueryWrapper(bo), TGlWithholdingInacctVo.class);
        return PageUtils.buildDataInfo(result);
    }

    @Override
    public List<TGlWithholdingInacctVo> queryList(TGlWithholdingInacctQueryBo bo) {
        return listVo(buildQueryWrapper(bo), TGlWithholdingInacctVo.class);
    }

    private LambdaQueryWrapper<TGlWithholdingInacct> buildQueryWrapper(TGlWithholdingInacctQueryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TGlWithholdingInacct> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getFEnterRatio() != null, TGlWithholdingInacct::getFEnterRatio, bo.getFEnterRatio());
        lqw.eq(bo.getFEnterDetail() != null, TGlWithholdingInacct::getFEnterDetail, bo.getFEnterDetail());
        lqw.eq(StrUtil.isNotBlank(bo.getFEnterRatioFixed()), TGlWithholdingInacct::getFEnterRatioFixed, bo.getFEnterRatioFixed());
        return lqw;
    }

    @Override
    public Boolean insertByAddBo(TGlWithholdingInacctAddBo bo) {
        TGlWithholdingInacct add = BeanUtil.toBean(bo, TGlWithholdingInacct.class);
        validEntityBeforeSave(add);
        return save(add);
    }

    @Override
    public Boolean updateByEditBo(TGlWithholdingInacctEditBo bo) {
        TGlWithholdingInacct update = BeanUtil.toBean(bo, TGlWithholdingInacct.class);
        validEntityBeforeSave(update);
        return updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(TGlWithholdingInacct entity){
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
