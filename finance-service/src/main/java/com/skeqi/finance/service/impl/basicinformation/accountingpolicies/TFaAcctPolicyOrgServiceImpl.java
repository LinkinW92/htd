package com.skeqi.finance.service.impl.basicinformation.accountingpolicies;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.skeqi.common.utils.PageUtils;
import com.skeqi.common.core.page.PagePlus;
import com.skeqi.common.core.page.TableDataInfo;
import org.springframework.stereotype.Service;
import com.skeqi.common.core.mybatisplus.core.ServicePlusImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.skeqi.finance.pojo.bo.basicinformation.accountingpolicies.TFaAcctPolicyOrgAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.accountingpolicies.TFaAcctPolicyOrgQueryBo;
import com.skeqi.finance.pojo.bo.basicinformation.accountingpolicies.TFaAcctPolicyOrgEditBo;
import com.skeqi.finance.domain.basicinformation.accountingpolicies.TFaAcctPolicyOrg;
import com.skeqi.finance.mapper.basicinformation.accountingpolicies.TFaAcctPolicyOrgMapper;
import com.skeqi.finance.pojo.vo.basicinformation.accountingpolicies.TFaAcctPolicyOrgVo;
import com.skeqi.finance.service.basicinformation.accountingpolicies.ITFaAcctPolicyOrgService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 会计政策适用核算组织Service业务层处理
 *
 * @author toms
 * @date 2021-07-09
 */
@Service
public class TFaAcctPolicyOrgServiceImpl extends ServicePlusImpl<TFaAcctPolicyOrgMapper, TFaAcctPolicyOrg> implements ITFaAcctPolicyOrgService {

    @Override
    public TFaAcctPolicyOrgVo queryById(Integer fEntryId){
        return getVoById(fEntryId, TFaAcctPolicyOrgVo.class);
    }

    @Override
    public TableDataInfo<TFaAcctPolicyOrgVo> queryPageList(TFaAcctPolicyOrgQueryBo bo) {
        PagePlus<TFaAcctPolicyOrg, TFaAcctPolicyOrgVo> result = pageVo(PageUtils.buildPagePlus(), buildQueryWrapper(bo), TFaAcctPolicyOrgVo.class);
        return PageUtils.buildDataInfo(result);
    }

    @Override
    public List<TFaAcctPolicyOrgVo> queryList(TFaAcctPolicyOrgQueryBo bo) {
        return listVo(buildQueryWrapper(bo), TFaAcctPolicyOrgVo.class);
    }

    private LambdaQueryWrapper<TFaAcctPolicyOrg> buildQueryWrapper(TFaAcctPolicyOrgQueryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TFaAcctPolicyOrg> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getFAcctpolicyId() != null, TFaAcctPolicyOrg::getFAcctpolicyId, bo.getFAcctpolicyId());
        lqw.eq(bo.getFSeq() != null, TFaAcctPolicyOrg::getFSeq, bo.getFSeq());
        lqw.eq(StrUtil.isNotBlank(bo.getFIsdefault()), TFaAcctPolicyOrg::getFIsdefault, bo.getFIsdefault());
        lqw.eq(bo.getFAcctsystemId() != null, TFaAcctPolicyOrg::getFAcctsystemId, bo.getFAcctsystemId());
        lqw.eq(bo.getFAcctOrgid() != null, TFaAcctPolicyOrg::getFAcctOrgid, bo.getFAcctOrgid());
        lqw.eq(StrUtil.isNotBlank(bo.getFAcctBook()), TFaAcctPolicyOrg::getFAcctBook, bo.getFAcctBook());
        lqw.eq(StrUtil.isNotBlank(bo.getFIsaudit()), TFaAcctPolicyOrg::getFIsaudit, bo.getFIsaudit());
        return lqw;
    }

    @Override
    public Boolean insertByAddBo(TFaAcctPolicyOrgAddBo bo) {
        TFaAcctPolicyOrg add = BeanUtil.toBean(bo, TFaAcctPolicyOrg.class);
        validEntityBeforeSave(add);
        return save(add);
    }

    @Override
    public Boolean updateByEditBo(TFaAcctPolicyOrgEditBo bo) {
        TFaAcctPolicyOrg update = BeanUtil.toBean(bo, TFaAcctPolicyOrg.class);
        validEntityBeforeSave(update);
        return updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(TFaAcctPolicyOrg entity){
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
