package com.skeqi.finance.service.impl.basicinformation.accountingpolicies;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.skeqi.common.exception.CustomException;
import com.skeqi.common.utils.PageUtils;
import com.skeqi.common.core.page.PagePlus;
import com.skeqi.common.core.page.TableDataInfo;
import com.skeqi.finance.domain.basicinformation.accountingsystem.TOrgAcctSysEntry;
import com.skeqi.finance.pojo.bo.basicinformation.accountingpolicies.TFaAcctPolicyOrgAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.accountingsystem.TOrgAcctSysEntryAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.accountingsystem.TOrgAcctSysEntryEditBo;
import org.springframework.stereotype.Service;
import com.skeqi.common.core.mybatisplus.core.ServicePlusImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.skeqi.finance.pojo.bo.basicinformation.accountingpolicies.TFaAcctPolicyAssetAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.accountingpolicies.TFaAcctPolicyAssetQueryBo;
import com.skeqi.finance.pojo.bo.basicinformation.accountingpolicies.TFaAcctPolicyAssetEditBo;
import com.skeqi.finance.domain.basicinformation.accountingpolicies.TFaAcctPolicyAsset;
import com.skeqi.finance.mapper.basicinformation.accountingpolicies.TFaAcctPolicyAssetMapper;
import com.skeqi.finance.pojo.vo.basicinformation.accountingpolicies.TFaAcctPolicyAssetVo;
import com.skeqi.finance.service.basicinformation.accountingpolicies.ITFaAcctPolicyAssetService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 会计政策资产政策Service业务层处理
 *
 * @author toms
 * @date 2021-07-09
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TFaAcctPolicyAssetServiceImpl extends ServicePlusImpl<TFaAcctPolicyAssetMapper, TFaAcctPolicyAsset> implements ITFaAcctPolicyAssetService {

	@Resource
	private TFaAcctPolicyAssetMapper mapper;

    @Override
    public TFaAcctPolicyAssetVo queryById(Integer fEntryId){
        return getVoById(fEntryId, TFaAcctPolicyAssetVo.class);
    }

    @Override
    public TableDataInfo<TFaAcctPolicyAssetVo> queryPageList(TFaAcctPolicyAssetQueryBo bo) {
        PagePlus<TFaAcctPolicyAsset, TFaAcctPolicyAssetVo> result = pageVo(PageUtils.buildPagePlus(), buildQueryWrapper(bo), TFaAcctPolicyAssetVo.class);
        return PageUtils.buildDataInfo(result);
    }

    @Override
    public List<TFaAcctPolicyAssetVo> queryList(TFaAcctPolicyAssetQueryBo bo) {
        return listVo(buildQueryWrapper(bo), TFaAcctPolicyAssetVo.class);
    }

    private LambdaQueryWrapper<TFaAcctPolicyAsset> buildQueryWrapper(TFaAcctPolicyAssetQueryBo bo) {
        LambdaQueryWrapper<TFaAcctPolicyAsset> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getFSeq() != null, TFaAcctPolicyAsset::getFSeq, bo.getFSeq());
        lqw.eq(bo.getFAcctpolicyId() != null, TFaAcctPolicyAsset::getFAcctpolicyId, bo.getFAcctpolicyId());
        lqw.eq(bo.getFAssetTypeid() != null, TFaAcctPolicyAsset::getFAssetTypeid, bo.getFAssetTypeid());
        lqw.eq(bo.getFLegalDepryears() != null, TFaAcctPolicyAsset::getFLegalDepryears, bo.getFLegalDepryears());
        lqw.eq(bo.getFEntDepryears() != null, TFaAcctPolicyAsset::getFEntDepryears, bo.getFEntDepryears());
        lqw.eq(StrUtil.isNotBlank(bo.getFResidualType()), TFaAcctPolicyAsset::getFResidualType, bo.getFResidualType());
        lqw.eq(bo.getFLegalResidualRate() != null, TFaAcctPolicyAsset::getFLegalResidualRate, bo.getFLegalResidualRate());
        lqw.eq(bo.getFEntResidualRate() != null, TFaAcctPolicyAsset::getFEntResidualRate, bo.getFEntResidualRate());
        lqw.eq(bo.getFResidualAmount() != null, TFaAcctPolicyAsset::getFResidualAmount, bo.getFResidualAmount());
        lqw.eq(StrUtil.isNotBlank(bo.getFDeprMethod()), TFaAcctPolicyAsset::getFDeprMethod, bo.getFDeprMethod());
        lqw.eq(bo.getFDeprPolicyId() != null, TFaAcctPolicyAsset::getFDeprPolicyId, bo.getFDeprPolicyId());
        lqw.eq(bo.getFWorkLoadunitId() != null, TFaAcctPolicyAsset::getFWorkLoadunitId, bo.getFWorkLoadunitId());
        lqw.eq(bo.getFEntdeprWorkload() != null, TFaAcctPolicyAsset::getFEntdeprWorkload, bo.getFEntdeprWorkload());
        lqw.eq(bo.getFLegalDeprWorkload() != null, TFaAcctPolicyAsset::getFLegalDeprWorkload, bo.getFLegalDeprWorkload());
        return lqw;
    }

    @Override
    public Boolean insertByAddBo(TFaAcctPolicyAssetAddBo bo) {
        TFaAcctPolicyAsset add = BeanUtil.toBean(bo, TFaAcctPolicyAsset.class);
        validEntityBeforeSave(add);
        return save(add);
    }

    @Override
    public Boolean updateByEditBo(TFaAcctPolicyAssetEditBo bo) {
        TFaAcctPolicyAsset update = BeanUtil.toBean(bo, TFaAcctPolicyAsset.class);
        validEntityBeforeSave(update);
        return updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(TFaAcctPolicyAsset entity){
        //TODO 做一些数据校验,如唯一约束
    }

    @Override
    public Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return removeByIds(ids);
    }

    @Override
    public Boolean insertByAddByList(List<TFaAcctPolicyAssetAddBo> policyAssetList) {
		Integer result = 0;
		if (StringUtils.checkValNotNull(policyAssetList)&&policyAssetList.size()>0){
			result = mapper.insertByAddBoList(policyAssetList);
			if (result<1){
				throw new CustomException("保存失败!",1000);
			}
		}
		return result > 0;
    }

	@Override
	public Boolean updateByEditBoList(List<TFaAcctPolicyAssetEditBo> policyAssetList) {
		List<TFaAcctPolicyAssetAddBo> addList = new ArrayList<>();
		List<Integer> delList = new ArrayList<>();
		List<TFaAcctPolicyAssetEditBo> editList = new ArrayList<>();
		Integer result = 0;
		if (StringUtils.checkValNotNull(policyAssetList)&&policyAssetList.size()>0){
			Map<String,Object> map = new HashMap<>();
			map.put("f_acctpolicy_id",policyAssetList.get(0).getFAcctpolicyId());
			List<TFaAcctPolicyAsset> list = listByMap(map);

			// 数据库记录与传入记录不同数据则删除
			list.forEach(acct -> {
				List<TFaAcctPolicyAssetEditBo> collect = policyAssetList.parallelStream().filter(s -> s.getFEntryId().equals(acct.getFEntryId())).collect(Collectors.toList());
				if (collect.size()==0){
					delList.add(acct.getFEntryId());
				}
				editList.addAll(collect);
			});

			// 传入记录数据库不存在则是新增数据
			policyAssetList.forEach(editBo -> {
				List<TFaAcctPolicyAsset> collect = list.parallelStream().filter(s -> s.getFEntryId().equals(editBo.getFEntryId())).collect(Collectors.toList());
				if (collect.size()==0){
					addList.add(BeanUtil.toBean(editBo,TFaAcctPolicyAssetAddBo.class));
				}
			});
			// 新增会计核算体系之会计主体
			if (addList.size()>0){
				result = mapper.insertByAddBoList(addList);
				if (result<1){
					throw new CustomException("保存失败!",1000);
				}
			}
			// 删除会计核算体系之会计主体
			if (delList.size()>0){
				if (!removeByIds(delList)){
					throw new CustomException("保存失败!",1000);
				}
			}
			// 更新会计核算体系之会计主体
			if (editList.size()>0){
				result = mapper.updateByEditList(editList);
				if (result<1){
					throw new CustomException("保存失败!",1000);
				}
			}
		}
		return result > 0;
	}

	@Override
	public List<TFaAcctPolicyAssetVo> queryListByFAcctpolicyId(Integer fAcctpolicyId) {
		return mapper.queryListByFAcctpolicyId(fAcctpolicyId);
	}
}
