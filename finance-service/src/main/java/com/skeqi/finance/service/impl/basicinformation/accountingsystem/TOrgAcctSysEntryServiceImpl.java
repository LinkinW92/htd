package com.skeqi.finance.service.impl.basicinformation.accountingsystem;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.skeqi.common.exception.CustomException;
import com.skeqi.common.utils.PageUtils;
import com.skeqi.common.core.page.PagePlus;
import com.skeqi.common.core.page.TableDataInfo;
import com.skeqi.finance.domain.basicinformation.voucher.TBdVchgroupAcct;
import com.skeqi.finance.mapper.basicinformation.accountingpolicies.TFaAcctPolicyOrgMapper;
import com.skeqi.finance.pojo.bo.basicinformation.accountingpolicies.TFaAcctPolicyOrgAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.voucher.TBdVchgroupAcctAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.voucher.TBdVchgroupAcctEditBo;
import com.skeqi.finance.service.basicinformation.accountingpolicies.ITFaAcctPolicyOrgService;
import org.springframework.stereotype.Service;
import com.skeqi.common.core.mybatisplus.core.ServicePlusImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.skeqi.finance.pojo.bo.basicinformation.accountingsystem.TOrgAcctSysEntryAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.accountingsystem.TOrgAcctSysEntryQueryBo;
import com.skeqi.finance.pojo.bo.basicinformation.accountingsystem.TOrgAcctSysEntryEditBo;
import com.skeqi.finance.domain.basicinformation.accountingsystem.TOrgAcctSysEntry;
import com.skeqi.finance.mapper.basicinformation.accountingsystem.TOrgAcctSysEntryMapper;
import com.skeqi.finance.pojo.vo.basicinformation.accountingsystem.TOrgAcctSysEntryVo;
import com.skeqi.finance.service.basicinformation.accountingsystem.ITOrgAcctSysEntryService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 会计核算体系之会计主体Service业务层处理
 *
 * @author toms
 * @date 2021-07-09
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TOrgAcctSysEntryServiceImpl extends ServicePlusImpl<TOrgAcctSysEntryMapper, TOrgAcctSysEntry> implements ITOrgAcctSysEntryService {

	@Resource
	private TFaAcctPolicyOrgMapper orgMapper;
	@Resource
	private TOrgAcctSysEntryMapper mapper;

    @Override
    public TOrgAcctSysEntryVo queryById(Integer fId){
        return getVoById(fId, TOrgAcctSysEntryVo.class);
    }

    @Override
    public TableDataInfo<TOrgAcctSysEntryVo> queryPageList(TOrgAcctSysEntryQueryBo bo) {
        PagePlus<TOrgAcctSysEntry, TOrgAcctSysEntryVo> result = pageVo(PageUtils.buildPagePlus(), buildQueryWrapper(bo), TOrgAcctSysEntryVo.class);
        return PageUtils.buildDataInfo(result);
    }

    @Override
    public List<TOrgAcctSysEntryVo> queryList(TOrgAcctSysEntryQueryBo bo) {
        return listVo(buildQueryWrapper(bo), TOrgAcctSysEntryVo.class);
    }

    private LambdaQueryWrapper<TOrgAcctSysEntry> buildQueryWrapper(TOrgAcctSysEntryQueryBo bo) {
        LambdaQueryWrapper<TOrgAcctSysEntry> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getFAcctsystemId() != null, TOrgAcctSysEntry::getFAcctsystemId, bo.getFAcctsystemId());
        lqw.eq(bo.getFAcctOrgid() != null, TOrgAcctSysEntry::getFAcctOrgid, bo.getFAcctOrgid());
        lqw.eq(bo.getFDefaultAcctpolicyId() != null, TOrgAcctSysEntry::getFDefaultAcctpolicyId, bo.getFDefaultAcctpolicyId());
        lqw.eq(bo.getFAcctpolicyId() != null, TOrgAcctSysEntry::getFAcctpolicyId, bo.getFAcctpolicyId());
        lqw.eq(StrUtil.isNotBlank(bo.getFDescription()), TOrgAcctSysEntry::getFDescription, bo.getFDescription());
        return lqw;
    }

    @Override
    public Boolean insertByAddBo(TOrgAcctSysEntryAddBo bo) {
        TOrgAcctSysEntry add = BeanUtil.toBean(bo, TOrgAcctSysEntry.class);
        validEntityBeforeSave(add);
        return save(add);
    }

    @Override
    public Boolean updateByEditBo(TOrgAcctSysEntryEditBo bo) {
        TOrgAcctSysEntry update = BeanUtil.toBean(bo, TOrgAcctSysEntry.class);
        validEntityBeforeSave(update);
        return updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(TOrgAcctSysEntry entity){
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
    public Boolean insertByAddByList(List<TOrgAcctSysEntryAddBo> entryAddBoList) {
		Integer result = 0;
		if (StringUtils.checkValNotNull(entryAddBoList)&&entryAddBoList.size()>0){
			result = mapper.insertByAddBoList(entryAddBoList);
			if (result<1){
				throw new CustomException("保存失败!",1000);
			}
			List<TFaAcctPolicyOrgAddBo> orgAddBoList = new ArrayList<>();
			for (TOrgAcctSysEntryAddBo entryAddBo : entryAddBoList) {
				TFaAcctPolicyOrgAddBo orgAddBo = new TFaAcctPolicyOrgAddBo();
				orgAddBo.setFAcctpolicyId(entryAddBo.getFAcctpolicyId());
				orgAddBo.setFAcctsystemId(entryAddBo.getFAcctsystemId());
				orgAddBo.setFAcctOrgid(entryAddBo.getFAcctOrgid());
				orgAddBo.setFIsdefault("0");
				if (entryAddBo.getFAcctpolicyId()!=null&&entryAddBo.getFDefaultAcctpolicyId().equals(entryAddBo.getFAcctpolicyId())){
					orgAddBo.setFIsdefault("1");
				}
				orgAddBoList.add(orgAddBo);
			}
			result = orgMapper.insertByAddBoList(orgAddBoList);
			if (result<1){
				throw new CustomException("保存失败!",1000);
			}
		}
		return result > 0;
    }

	@Override
	public Boolean updateByEditBoList(List<TOrgAcctSysEntryEditBo> entryAddBoList) {
		List<TOrgAcctSysEntryAddBo> addList = new ArrayList<>();
		List<Integer> delList = new ArrayList<>();
		List<TOrgAcctSysEntryEditBo> editList = new ArrayList<>();
		Integer result = 0;
		if (StringUtils.checkValNotNull(entryAddBoList)&&entryAddBoList.size()>0){
			Map<String,Object> map = new HashMap<>();
			map.put("f_acctsystem_id",entryAddBoList.get(0).getFAcctsystemId());
			List<TOrgAcctSysEntry> list = listByMap(map);

			// 数据库记录与传入记录不同数据则删除
			list.forEach(acct -> {
				List<TOrgAcctSysEntryEditBo> collect = entryAddBoList.parallelStream().filter(s -> s.getFId().equals(acct.getFId())).collect(Collectors.toList());
				if (collect.size()==0){
					delList.add(acct.getFId());
				}
				editList.addAll(collect);
			});

			// 传入记录数据库不存在则是新增数据
			entryAddBoList.forEach(editBo -> {
				List<TOrgAcctSysEntry> collect = list.parallelStream().filter(s -> s.getFId().equals(editBo.getFId())).collect(Collectors.toList());
				if (collect.size()==0){
					addList.add(BeanUtil.toBean(editBo,TOrgAcctSysEntryAddBo.class));
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
	public List<TOrgAcctSysEntryVo> queryListByFAcctsystemId(Integer fAcctsystemId) {
		return mapper.queryListByFAcctsystemId(fAcctsystemId);
	}
}
