package com.skeqi.finance.service.impl.basicinformation.cashflow;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skeqi.common.core.domain.entity.SysUser;
import com.skeqi.common.exception.CustomException;
import com.skeqi.common.utils.PageUtils;
import com.skeqi.common.core.page.TableDataInfo;
import com.skeqi.common.utils.ServletUtils;
import com.skeqi.finance.domain.TBdAccount;
import com.skeqi.finance.domain.voucher.TGlVoucherEntryCash;
import com.skeqi.finance.enums.BaseEnum;
import com.skeqi.finance.enums.DataStatusEnum;
import com.skeqi.finance.mapper.account.TBdAccountMapper;
import com.skeqi.finance.mapper.cashflow.TGlVoucherEntryCashMapper;
import com.skeqi.framecore.web.service.TokenService;
import org.springframework.stereotype.Service;
import com.skeqi.common.core.mybatisplus.core.ServicePlusImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.skeqi.finance.pojo.bo.basicinformation.cashflow.TGlCashFlowAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.cashflow.TGlCashFlowQueryBo;
import com.skeqi.finance.pojo.bo.basicinformation.cashflow.TGlCashFlowEditBo;
import com.skeqi.finance.domain.basicinformation.cashflow.TGlCashFlow;
import com.skeqi.finance.mapper.basicinformation.cashflow.TGlCashFlowMapper;
import com.skeqi.finance.pojo.vo.basicinformation.cashflow.TGlCashFlowVo;
import com.skeqi.finance.service.basicinformation.cashflow.ITGlCashFlowService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * 现金流量项目-3Service业务层处理
 *
 * @author toms
 * @date 2021-07-09
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TGlCashFlowServiceImpl extends ServicePlusImpl<TGlCashFlowMapper, TGlCashFlow> implements ITGlCashFlowService {
	@Resource
	private TokenService tokenService;

	@Resource
	private TGlCashFlowMapper mapper;

	/**
	 * 科目信息
	 */
	@Resource
	private TBdAccountMapper accountMapper;

	/**
	 * 凭证分录现金流量表
	 */
	@Resource
	private TGlVoucherEntryCashMapper entryCashMapper;


    @Override
    public TGlCashFlowVo queryById(Integer fId){
		LambdaQueryWrapper<TGlCashFlow> lqw = Wrappers.lambdaQuery();
		lqw.eq(TGlCashFlow::getFId, fId);
		lqw.ne(TGlCashFlow::getFDocumentStatus,DataStatusEnum.DELETE.getCode());
		TGlCashFlow tGlCashFlow = mapper.selectOne(lqw);
		return BeanUtil.toBean(tGlCashFlow, TGlCashFlowVo.class);
    }

    @Override
    public TableDataInfo<TGlCashFlowVo> queryPageList(TGlCashFlowQueryBo bo) {
		Page<TGlCashFlowQueryBo> userPage = new Page<>(bo.getPageNum(), bo.getPageSize());
		IPage<TGlCashFlowVo> policyVoIPage = mapper.queryPageList(userPage,bo);
		return PageUtils.buildDataInfo(policyVoIPage);
    }

    @Override
    public List<TGlCashFlowVo> queryList(TGlCashFlowQueryBo bo) {
        return listVo(buildQueryWrapper(bo), TGlCashFlowVo.class);
    }

    private LambdaQueryWrapper<TGlCashFlow> buildQueryWrapper(TGlCashFlowQueryBo bo) {
        LambdaQueryWrapper<TGlCashFlow> lqw = Wrappers.lambdaQuery();
        lqw.eq(StrUtil.isNotBlank(bo.getFNumber()), TGlCashFlow::getFNumber, bo.getFNumber());
        lqw.like(StrUtil.isNotBlank(bo.getFName()), TGlCashFlow::getFName, bo.getFName());
        lqw.eq(StrUtil.isNotBlank(bo.getFDescription()), TGlCashFlow::getFDescription, bo.getFDescription());
        lqw.eq(bo.getFParentId() != null, TGlCashFlow::getFParentId, bo.getFParentId());
        lqw.eq(bo.getFLevel() != null, TGlCashFlow::getFLevel, bo.getFLevel());
        lqw.eq(bo.getFDirection() != null, TGlCashFlow::getFDirection, bo.getFDirection());
        lqw.eq(bo.getFItemTypeid() != null, TGlCashFlow::getFItemTypeid, bo.getFItemTypeid());
        lqw.eq(StrUtil.isNotBlank(bo.getFIsdetail()), TGlCashFlow::getFIsdetail, bo.getFIsdetail());
        lqw.eq(bo.getFCashFlowItemTable() != null, TGlCashFlow::getFCashFlowItemTable, bo.getFCashFlowItemTable());
        lqw.eq(bo.getFCreateOrgid() != null, TGlCashFlow::getFCreateOrgid, bo.getFCreateOrgid());
        lqw.eq(bo.getFCreatorid() != null, TGlCashFlow::getFCreatorid, bo.getFCreatorid());
        lqw.eq(bo.getFCreateDate() != null, TGlCashFlow::getFCreateDate, bo.getFCreateDate());
        lqw.eq(bo.getFUseOrgid() != null, TGlCashFlow::getFUseOrgid, bo.getFUseOrgid());
        lqw.eq(bo.getFModifierid() != null, TGlCashFlow::getFModifierid, bo.getFModifierid());
        lqw.eq(bo.getFModifyDate() != null, TGlCashFlow::getFModifyDate, bo.getFModifyDate());
        lqw.eq(StrUtil.isNotBlank(bo.getFDocumentStatus()), TGlCashFlow::getFDocumentStatus, bo.getFDocumentStatus());
        lqw.eq(bo.getFAuditorid() != null, TGlCashFlow::getFAuditorid, bo.getFAuditorid());
        lqw.eq(bo.getFAuditDate() != null, TGlCashFlow::getFAuditDate, bo.getFAuditDate());
        lqw.eq(StrUtil.isNotBlank(bo.getFForbidStatus()), TGlCashFlow::getFForbidStatus, bo.getFForbidStatus());
        lqw.eq(bo.getFForbidderid() != null, TGlCashFlow::getFForbidderid, bo.getFForbidderid());
        lqw.eq(bo.getFForbidDate() != null, TGlCashFlow::getFForbidDate, bo.getFForbidDate());
        lqw.eq(bo.getFIssysPreset() != null, TGlCashFlow::getFIssysPreset, bo.getFIssysPreset());
        lqw.eq(bo.getFMasterId() != null, TGlCashFlow::getFMasterId, bo.getFMasterId());
		lqw.ne(TGlCashFlow::getFDocumentStatus, DataStatusEnum.DELETE.getCode());
        return lqw;
    }

    @Override
    public Boolean insertByAddBo(TGlCashFlowAddBo bo) {
		SysUser user = tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
		// 新增
		TGlCashFlow add = BeanUtil.toBean(bo, TGlCashFlow.class);
		add.setFCreatorid(user.getUserId().intValue());
		add.setFForbidStatus("0");
		add.setFCreateDate(new Date());
		validEntityBeforeSave(add);
        return save(add);
    }

    @Override
    public Boolean updateByEditBo(TGlCashFlowEditBo bo) {
		SysUser user = tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
		TGlCashFlow update = BeanUtil.toBean(bo, TGlCashFlow.class);
		update.setFModifierid(user.getUserId().intValue());
		update.setFModifyDate(new Date());
		validEntityBeforeSave(update);
        return updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(TGlCashFlow entity){
		//TODO 做一些数据校验,如唯一约束
		if (StringUtils.checkValNull(entity)){
			throw new CustomException("保存数据不能为空!",1000);
		}

		LambdaQueryWrapper<TGlCashFlow> lqw = Wrappers.lambdaQuery();
		lqw.eq(entity.getFNumber() != null, TGlCashFlow::getFNumber, entity.getFNumber());
		lqw.eq(entity.getFParentId() != null, TGlCashFlow::getFParentId, entity.getFParentId());
		TGlCashFlow voOne = getVoOne(lqw, TGlCashFlow.class);
		if (StringUtils.checkValNotNull(voOne)){
			// 编辑时执行
			if (voOne.getFId().equals(entity.getFId())){

				if(BaseEnum.YES.getCode().equals(entity.getFIssysPreset())){
					throw new CustomException("系统预设数据不能修改",1000);
				}

				if(DataStatusEnum.AUDIT.getCode().equals(entity.getFDocumentStatus())){
					throw new CustomException("已审核数据不能修改",1000);
				}

				//验证是否
				if (voOne.getFForbidStatus().equals(BaseEnum.YES.getCode())) {
					throw new CustomException("当前数据已被禁用!",1000);
				}
				return;
			}
			throw new CustomException("当前编码已存在!",1000);
		}
    }

    @Override
    public Boolean deleteWithValidByIds(Collection<Integer> ids) {
		//校验
		if (StringUtils.checkValNull(ids)) {
			throw new CustomException("请至少选择一个删除!",1000);
		}
		for (Integer integer : ids) {
			LambdaQueryWrapper<TGlCashFlow> lqw = Wrappers.lambdaQuery();
			lqw.eq(TGlCashFlow::getFId, integer);
			lqw.ne(TGlCashFlow::getFDocumentStatus,DataStatusEnum.DELETE.getCode());
			TGlCashFlow policy = mapper.selectOne(lqw);

			if (StringUtils.checkValNull(policy)) {
				throw new CustomException(String.format("数据不存在!",integer), 1000);
			}
			if (policy.getFIssysPreset().equals(BaseEnum.YES.getCode())) {
				throw new CustomException(String.format("现金流量项目:%s,为系统预设无法删除!",policy.getFName()), 1000);
			}
			if (policy.getFDocumentStatus().equals(DataStatusEnum.AUDIT.getCode())) {
				throw new CustomException(String.format("现金流量项目:%s,已审核数据无法删除!",policy.getFName()), 1000);
			}

			if (policy.getFDocumentStatus().equals(DataStatusEnum.CREATE.getCode())){
				// 未使用的直接删除
				if (!removeById(integer)) {
					throw new CustomException(String.format("现金流量项目:%s,删除失败!",policy.getFName()),1000);
				}
			}else {
				// 验证当前数据是否被引用
				String result = this.verifyThatItIsUsed(integer);
				if (StringUtils.checkValNotNull(result)){
					throw new CustomException(String.format("现金流量项目:%s,已被%s引用,无法删除!",policy.getFName(),result),1000);
				}
				// 已使用过的数据改状态
				TGlCashFlow accountSystem = new TGlCashFlow();
				accountSystem.setFId(policy.getFId());
				accountSystem.setFDocumentStatus(DataStatusEnum.DELETE.getCode());
				if (!updateById(accountSystem)) {
					throw new CustomException(String.format("现金流量项目:%s,删除失败!",policy.getFName()),1000);
				}
			}
		}
		return true;
    }

	/**
	 * 验证是否被引用
	 * @param fCashFlowId
	 */
	private String verifyThatItIsUsed(Integer fCashFlowId){
		// 返回结果
		StringBuilder result = new StringBuilder("");

		List<TBdAccount> tBdAccountList = accountMapper.selectByFCashFlowId(fCashFlowId);
		if (tBdAccountList.size()>0){
			result.append("科目:");
			tBdAccountList.stream().map(account -> account.getFNumber() + ",").forEach(result::append);
			return result.toString();
		}

		// 账簿 查询字段
		Map<String, Object> map = new HashMap<>();
		map.put("main_table_id",fCashFlowId);
		List<TGlVoucherEntryCash> tGlVoucherEntryCashList = entryCashMapper.selectByMap(map);
		if (tGlVoucherEntryCashList.size()>0){
			result.append("凭证管理");
			return result.toString();
		}
		return result.toString();

	}

	@Override
	public Boolean auditByIds(List<Integer> ids) {
		SysUser user = tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
		ids.forEach(id->{
			TGlCashFlow old = getById(id);
			if (StringUtils.checkValNull(old)){
				throw new CustomException(String.format("数据不存在!",id), 1000);
			}
			if (old.getFDocumentStatus().equals(DataStatusEnum.AUDIT.getCode())){
				throw new CustomException(String.format("现金流量项目:%s已经审核,无法重复审核!",old.getFName()), 1000);
			}
			old.setFModifierid(user.getUserId().intValue());
			old.setFModifyDate(new Date());
			old.setFAuditorid(user.getUserId().intValue());
			old.setFAuditDate(new Date());
			old.setFDocumentStatus(DataStatusEnum.AUDIT.getCode());
			boolean b = this.updateById(old);
			if (!b){
				throw new CustomException(String.format("现金流量项目:%s审核失败,请稍后重试!",old.getFName()), 1000);
			}
		});
		return true;
	}

	@Override
	public Boolean antiAuditByIds(List<Integer> ids) {
		SysUser user = tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
		ids.forEach(id->{
			TGlCashFlow old = getById(id);
			if (StringUtils.checkValNull(old)){
				throw new CustomException(String.format("数据不存在!",id), 1000);
			}

			if (!old.getFDocumentStatus().equals(DataStatusEnum.AUDIT.getCode())) {
				throw new CustomException(String.format("现金流量项目:%s未审核,请先提交审核!",old.getFName()), 1000);
			}
			old.setFModifierid(user.getUserId().intValue());
			old.setFModifyDate(new Date());
			old.setFAuditorid(user.getUserId().intValue());
			old.setFAuditDate(new Date());
			old.setFDocumentStatus(DataStatusEnum.REJECT.getCode());
			boolean b = this.updateById(old);
			if (!b){
				throw new CustomException(String.format("现金流量项目:%s反审核失败,请稍后重试!",old.getFName()), 1000);
			}
		});
		return true;
	}

	@Override
	public Boolean disableByEditBo(TGlCashFlowEditBo bo) {
		SysUser user = tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
		TGlCashFlowVo tGlCashFlowVo = queryById(bo.getFId());
		if (tGlCashFlowVo.getFForbidStatus().equals(bo.getFForbidStatus())) {
			switch (bo.getFForbidStatus()) {
				case "0":
					throw new CustomException("现金流量项目:" + tGlCashFlowVo.getFName() + ",已被解禁,无法重复解禁!", 1000);
				case "1":
					throw new CustomException("现金流量项目:" + tGlCashFlowVo.getFName() + ",已禁用,无法重复禁用!", 1000);
			}
		}
		TGlCashFlow update = BeanUtil.toBean(bo, TGlCashFlow.class);
		update.setFModifierid(user.getUserId().intValue());
		update.setFModifyDate(new Date());
		update.setFForbidderid(user.getUserId().intValue());
		update.setFForbidDate(new Date());
		return updateById(update);
	}
}
