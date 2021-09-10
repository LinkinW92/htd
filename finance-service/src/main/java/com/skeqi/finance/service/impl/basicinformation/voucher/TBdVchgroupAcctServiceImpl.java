package com.skeqi.finance.service.impl.basicinformation.voucher;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.skeqi.common.exception.CustomException;
import com.skeqi.common.utils.PageUtils;
import com.skeqi.common.core.page.PagePlus;
import com.skeqi.common.core.page.TableDataInfo;
import com.skeqi.finance.mapper.account.TBdAccountMapper;
import com.skeqi.finance.pojo.vo.TBdAccount.TBdAccountVo;
import com.skeqi.finance.service.account.ITBdAccountService;
import org.springframework.stereotype.Service;
import com.skeqi.common.core.mybatisplus.core.ServicePlusImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.skeqi.finance.pojo.bo.basicinformation.voucher.TBdVchgroupAcctAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.voucher.TBdVchgroupAcctQueryBo;
import com.skeqi.finance.pojo.bo.basicinformation.voucher.TBdVchgroupAcctEditBo;
import com.skeqi.finance.domain.basicinformation.voucher.TBdVchgroupAcct;
import com.skeqi.finance.mapper.basicinformation.voucher.TBdVchgroupAcctMapper;
import com.skeqi.finance.pojo.vo.basicinformation.voucher.TBdVchgroupAcctVo;
import com.skeqi.finance.service.basicinformation.voucher.ITBdVchgroupAcctService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;


/**
 * 凭证字-科目控制Service业务层处理
 *
 * @author toms
 * @date 2021-07-09
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TBdVchgroupAcctServiceImpl extends ServicePlusImpl<TBdVchgroupAcctMapper, TBdVchgroupAcct> implements ITBdVchgroupAcctService {

	/**
	 * 科目信息
	 */
	@Resource
	TBdAccountMapper accountMapper;

	@Resource
	private TBdVchgroupAcctMapper mapper;

    @Override
    public TBdVchgroupAcctVo queryById(Integer fEntryId){
        return getVoById(fEntryId, TBdVchgroupAcctVo.class);
    }

    @Override
    public TableDataInfo<TBdVchgroupAcctVo> queryPageList(TBdVchgroupAcctQueryBo bo) {
        PagePlus<TBdVchgroupAcct, TBdVchgroupAcctVo> result = pageVo(PageUtils.buildPagePlus(), buildQueryWrapper(bo), TBdVchgroupAcctVo.class);
        return PageUtils.buildDataInfo(result);
    }

    @Override
    public List<TBdVchgroupAcctVo> queryList(TBdVchgroupAcctQueryBo bo) {
        return listVo(buildQueryWrapper(bo), TBdVchgroupAcctVo.class);
    }

    private LambdaQueryWrapper<TBdVchgroupAcct> buildQueryWrapper(TBdVchgroupAcctQueryBo bo) {
        LambdaQueryWrapper<TBdVchgroupAcct> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getFAcctNumber() != null, TBdVchgroupAcct::getFAcctNumber, bo.getFAcctNumber());
        lqw.eq(StrUtil.isNotBlank(bo.getFAcctNumber()), TBdVchgroupAcct::getFAcctNumber, bo.getFAcctNumber());
        lqw.eq(StrUtil.isNotBlank(bo.getFDebit()), TBdVchgroupAcct::getFDebit, bo.getFDebit());
        lqw.eq(StrUtil.isNotBlank(bo.getFCredit()), TBdVchgroupAcct::getFCredit, bo.getFCredit());
        lqw.eq(StrUtil.isNotBlank(bo.getFDebitCredit()), TBdVchgroupAcct::getFDebitCredit, bo.getFDebitCredit());
        lqw.eq(StrUtil.isNotBlank(bo.getFDebitNo()), TBdVchgroupAcct::getFDebitNo, bo.getFDebitNo());
        lqw.eq(StrUtil.isNotBlank(bo.getFCreditNo()), TBdVchgroupAcct::getFCreditNo, bo.getFCreditNo());
        lqw.eq(StrUtil.isNotBlank(bo.getFDebitCreditNo()), TBdVchgroupAcct::getFDebitCreditNo, bo.getFDebitCreditNo());
        return lqw;
    }

    @Override
    public Boolean insertByAddBo(TBdVchgroupAcctAddBo bo) {
        TBdVchgroupAcct add = BeanUtil.toBean(bo, TBdVchgroupAcct.class);
        validEntityBeforeSave(add);
        return save(add);
    }

    @Override
    public Boolean updateByEditBo(TBdVchgroupAcctEditBo bo) {
        TBdVchgroupAcct update = BeanUtil.toBean(bo, TBdVchgroupAcct.class);
        validEntityBeforeSave(update);
        return updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(TBdVchgroupAcct entity){
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
	public Boolean insertByAddBoList(List<TBdVchgroupAcctAddBo> acctAddBoList) {
    	acctAddBoList.forEach(tBdVchgroupAcctAddBo -> {
			// 验证 科目编码是否存在
			TBdAccountVo tBdAccount = accountMapper.selectByFAcctNumber(tBdVchgroupAcctAddBo.getFAcctNumber(),tBdVchgroupAcctAddBo.getFAccttableId());
			if (StringUtils.checkValNull(tBdAccount)){
				throw new CustomException(String.format("科目编码[%s]不存在",tBdVchgroupAcctAddBo.getFAcctNumber()),1000);
			}
		});
		Integer result = 0;
		if (StringUtils.checkValNotNull(acctAddBoList)&&acctAddBoList.size()>0){
			result = mapper.insertByAddBoList(acctAddBoList);
			if (result<1){
				throw new CustomException("凭证字-科目控制保存失败!",1000);
			}
		}
    	return result > 1;
	}

	@Override
	public Boolean updateByEditBoList(List<TBdVchgroupAcctEditBo> acctEditBoList) {
    	List<TBdVchgroupAcctAddBo> addList = new ArrayList<>();
		List<Integer> delList = new ArrayList<>();
		List<TBdVchgroupAcctEditBo> editList = new ArrayList<>();
		Integer result = 0;
		if (StringUtils.checkValNotNull(acctEditBoList)&&acctEditBoList.size()>0){
			Map<String,Object> map = new HashMap<>();
			map.put("f_vchgroup_id",acctEditBoList.get(0).getFVchgroupId());
			List<TBdVchgroupAcct> list = listByMap(map);

			// 数据库记录与传入记录不同数据则删除
			list.forEach(acct -> {
				List<TBdVchgroupAcctEditBo> collect = acctEditBoList.parallelStream().filter(s -> s.getFEntryId() == acct.getFEntryId()).collect(Collectors.toList());
				if (collect.size()==0){
					delList.add(acct.getFEntryId());
				}
				editList.addAll(collect);
			});

			// 传入记录数据库不存在则是新增数据
			acctEditBoList.forEach(editBo -> {
				List<TBdVchgroupAcct> collect = list.parallelStream().filter(s -> s.getFEntryId() == editBo.getFEntryId()).collect(Collectors.toList());
				if (collect.size()==0){
					addList.add(BeanUtil.toBean(editBo,TBdVchgroupAcctAddBo.class));
				}
			});
			// 新增凭证字-科目控制
			if (addList.size()>0){
				result = mapper.insertByAddBoList(addList);
				if (result<1){
					throw new CustomException("凭证字-科目控制保存失败!",1000);
				}
			}
			// 删除凭证字-科目控制
			if (delList.size()>0){
				if (!removeByIds(delList)){
					throw new CustomException("凭证字-科目控制保存失败!",1000);
				}
			}
			// 更新凭证字-科目控制
			if (editList.size()>0){
				result = mapper.updateByEditList(editList);
				if (result<1){
					throw new CustomException("凭证字-科目控制保存失败!",1000);
				}
			}
		}
		return result > 1;
	}

	/**
	 * 按凭证子内码查询科目控制列表
	 *
	 * @param fVchgroupId
	 * @return
	 */
	@Override
	public List<TBdVchgroupAcctVo> selectByFVchgroupId(Integer fVchgroupId) {
		return mapper.selectByFVchgroupId(fVchgroupId);
	}
}
