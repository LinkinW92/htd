package com.skeqi.finance.service.impl.basicinformation.accountingsystem;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skeqi.common.core.domain.entity.SysUser;
import com.skeqi.common.exception.CustomException;
import com.skeqi.common.utils.PageUtils;
import com.skeqi.common.core.page.PagePlus;
import com.skeqi.common.core.page.TableDataInfo;
import com.skeqi.common.utils.ServletUtils;
import com.skeqi.finance.domain.basicinformation.accountbook.TBdAccountBook;
import com.skeqi.finance.domain.basicinformation.accountingpolicies.TFaAcctPolicy;
import com.skeqi.finance.domain.basicinformation.accountingsystem.TOrgAcctSysEntry;
import com.skeqi.finance.domain.basicinformation.voucher.TBdVchgroupAcct;
import com.skeqi.finance.domain.basicinformation.voucher.TBdVoucherGroup;
import com.skeqi.finance.enums.BaseEnum;
import com.skeqi.finance.enums.DataStatusEnum;
import com.skeqi.finance.mapper.basicinformation.accountbook.TBdAccountBookMapper;
import com.skeqi.finance.mapper.basicinformation.voucher.TBdVoucherGroupMapper;
import com.skeqi.finance.pojo.bo.basicinformation.accountingsystem.TOrgAcctSysEntryAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.voucher.TBdVoucherGroupQueryBo;
import com.skeqi.finance.pojo.vo.basicinformation.voucher.TBdVoucherGroupVo;
import com.skeqi.finance.service.basicinformation.accountingsystem.ITOrgAcctSysEntryService;
import com.skeqi.framecore.web.service.TokenService;
import org.springframework.stereotype.Service;
import com.skeqi.common.core.mybatisplus.core.ServicePlusImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.skeqi.finance.pojo.bo.basicinformation.accountingsystem.TOrgAccountSystemAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.accountingsystem.TOrgAccountSystemQueryBo;
import com.skeqi.finance.pojo.bo.basicinformation.accountingsystem.TOrgAccountSystemEditBo;
import com.skeqi.finance.domain.basicinformation.accountingsystem.TOrgAccountSystem;
import com.skeqi.finance.mapper.basicinformation.accountingsystem.TOrgAccountSystemMapper;
import com.skeqi.finance.pojo.vo.basicinformation.accountingsystem.TOrgAccountSystemVo;
import com.skeqi.finance.service.basicinformation.accountingsystem.ITOrgAccountSystemService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 会计核算体系Service业务层处理
 *
 * @author toms
 * @date 2021-07-09
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TOrgAccountSystemServiceImpl extends ServicePlusImpl<TOrgAccountSystemMapper, TOrgAccountSystem> implements ITOrgAccountSystemService {

	@Resource
	private TokenService tokenService;

	@Resource
	private TOrgAccountSystemMapper systemMapper;

	@Resource
	private ITOrgAcctSysEntryService entryService;

	/**
	 * 账簿
	 */
	@Resource
	private TBdAccountBookMapper bookMapper;

	@Override
    public TOrgAccountSystemVo queryById(Integer fId){
		LambdaQueryWrapper<TOrgAccountSystem> lqw = Wrappers.lambdaQuery();
		lqw.eq(TOrgAccountSystem::getFId, fId);
		lqw.ne(TOrgAccountSystem::getFDocumentStatus,DataStatusEnum.DELETE.getCode());
		TOrgAccountSystem accountSystem = systemMapper.selectOne(lqw);
        return BeanUtil.toBean(accountSystem, TOrgAccountSystemVo.class);
    }

    @Override
    public TableDataInfo<TOrgAccountSystemVo> queryPageList(TOrgAccountSystemQueryBo bo) {
		Page<TOrgAccountSystemQueryBo> userPage = new Page<>(bo.getPageNum(), bo.getPageSize());
		IPage<TOrgAccountSystemVo> systemVoIPage = systemMapper.queryPageList(userPage,buildQueryWrapper(bo));
		return PageUtils.buildDataInfo(systemVoIPage);
    }

    @Override
    public List<TOrgAccountSystemVo> queryList(TOrgAccountSystemQueryBo bo) {
        return listVo(buildQueryWrapper(bo), TOrgAccountSystemVo.class);
    }

    private LambdaQueryWrapper<TOrgAccountSystem> buildQueryWrapper(TOrgAccountSystemQueryBo bo) {
        LambdaQueryWrapper<TOrgAccountSystem> lqw = Wrappers.lambdaQuery();
        lqw.eq(StrUtil.isNotBlank(bo.getFNumber()), TOrgAccountSystem::getFNumber, bo.getFNumber());
        lqw.like(StrUtil.isNotBlank(bo.getFName()), TOrgAccountSystem::getFName, bo.getFName());
        lqw.eq(StrUtil.isNotBlank(bo.getFIsdefaultAcct()), TOrgAccountSystem::getFIsdefaultAcct, bo.getFIsdefaultAcct());
        lqw.eq(bo.getFAcctOrgid() != null, TOrgAccountSystem::getFAcctOrgid, bo.getFAcctOrgid());
        lqw.eq(bo.getFDefaultAcctpolicyId() != null, TOrgAccountSystem::getFDefaultAcctpolicyId, bo.getFDefaultAcctpolicyId());
        lqw.eq(bo.getFCreateOrgid() != null, TOrgAccountSystem::getFCreateOrgid, bo.getFCreateOrgid());
        lqw.eq(bo.getFCreatorid() != null, TOrgAccountSystem::getFCreatorid, bo.getFCreatorid());
        lqw.eq(bo.getFCreateDate() != null, TOrgAccountSystem::getFCreateDate, bo.getFCreateDate());
        lqw.eq(bo.getFUseOrgid() != null, TOrgAccountSystem::getFUseOrgid, bo.getFUseOrgid());
        lqw.eq(bo.getFModifierid() != null, TOrgAccountSystem::getFModifierid, bo.getFModifierid());
        lqw.eq(bo.getFModifyDate() != null, TOrgAccountSystem::getFModifyDate, bo.getFModifyDate());
        lqw.eq(StrUtil.isNotBlank(bo.getFDocumentStatus()), TOrgAccountSystem::getFDocumentStatus, bo.getFDocumentStatus());
        lqw.eq(bo.getFAuditorid() != null, TOrgAccountSystem::getFAuditorid, bo.getFAuditorid());
        lqw.eq(bo.getFAuditDate() != null, TOrgAccountSystem::getFAuditDate, bo.getFAuditDate());
        lqw.eq(StrUtil.isNotBlank(bo.getFForbidStatus()), TOrgAccountSystem::getFForbidStatus, bo.getFForbidStatus());
        lqw.eq(bo.getFForbidderid() != null, TOrgAccountSystem::getFForbidderid, bo.getFForbidderid());
        lqw.eq(bo.getFForbidDate() != null, TOrgAccountSystem::getFForbidDate, bo.getFForbidDate());
        lqw.eq(bo.getFIssysPreset() != null, TOrgAccountSystem::getFIssysPreset, bo.getFIssysPreset());
        lqw.eq(bo.getFMasterId() != null, TOrgAccountSystem::getFMasterId, bo.getFMasterId());
        lqw.eq(StrUtil.isNotBlank(bo.getFIscorpoRate()), TOrgAccountSystem::getFIscorpoRate, bo.getFIscorpoRate());
        lqw.eq(StrUtil.isNotBlank(bo.getFDescription()), TOrgAccountSystem::getFDescription, bo.getFDescription());
		lqw.ne(TOrgAccountSystem::getFDocumentStatus,DataStatusEnum.DELETE.getCode());
        return lqw;
    }

    @Override
    public Boolean insertByAddBo(TOrgAccountSystemAddBo bo) {
		SysUser user = tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
		// 新增核算体系
		TOrgAccountSystem add = BeanUtil.toBean(bo, TOrgAccountSystem.class);
		add.setFCreatorid(user.getUserId().intValue());
		add.setFCreateDate(new Date());
		validEntityBeforeSave(add);
		Boolean save = save(add);
		if (!save){
			throw new CustomException("保存失败,请稍后重试!",1000);
		}
		List<Integer> collect = bo.getEntryAddBoList().stream().map(TOrgAcctSysEntryAddBo::getFAcctOrgid).collect(Collectors.toList());
		long count = collect.stream().distinct().count();
		if (collect.size() != count) {
			throw new CustomException("保存失败、核算组织重复,请验证后重试!",1000);
		}

		for (Integer integer : collect) {
			if (systemMapper.selectByFAcctOrgId(integer).size()>0){
				throw new CustomException(String.format("保存失败、核算组织[%s]已有默认核算体系或法人核算体系,请验证后重试!",integer),1000);
			}
		}

		//新增会计核算体系之会计主体
		bo.getEntryAddBoList().forEach(tOrgAcctSysEntryAddBo -> tOrgAcctSysEntryAddBo.setFAcctsystemId(add.getFId()));
		save = entryService.insertByAddByList(bo.getEntryAddBoList());
        return save;
    }

    @Override
    public Boolean updateByEditBo(TOrgAccountSystemEditBo bo) {
		SysUser user = tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
		TOrgAccountSystem update = BeanUtil.toBean(bo, TOrgAccountSystem.class);
		update.setFModifierid(user.getUserId().intValue());
		update.setFModifyDate(new Date());
		validEntityBeforeSave(update);
		Boolean up = updateById(update);
		if (up){
			//编辑会计核算体系之会计主体
			bo.getEntryAddBoList().forEach(editBo -> editBo.setFAcctsystemId(bo.getFId()));
			entryService.updateByEditBoList(bo.getEntryAddBoList());
		}
		return up;
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(TOrgAccountSystem entity){
        //TODO 做一些数据校验,如唯一约束
		if (StringUtils.checkValNull(entity)){
			throw new CustomException("保存数据不能为空!",1000);
		}
		LambdaQueryWrapper<TOrgAccountSystem> lqw = Wrappers.lambdaQuery();
		lqw.eq(entity.getFNumber() != null, TOrgAccountSystem::getFNumber, entity.getFNumber());
		TOrgAccountSystem voOne = getVoOne(lqw, TOrgAccountSystem.class);
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
    public Boolean deleteWithValidByIds(List<Integer> ids) {
		//校验
		if (StringUtils.checkValNull(ids)) {
			throw new CustomException("请至少选择一个删除!",1000);
		}
		for (Integer integer : ids) {
			LambdaQueryWrapper<TOrgAccountSystem> lqw = Wrappers.lambdaQuery();
			lqw.eq(TOrgAccountSystem::getFId, integer);
			lqw.ne(TOrgAccountSystem::getFDocumentStatus,DataStatusEnum.DELETE.getCode());
			TOrgAccountSystem orgAccountSystem = systemMapper.selectOne(lqw);

			if (StringUtils.checkValNull(orgAccountSystem)) {
				throw new CustomException(String.format("数据不存在!",integer), 1000);
			}
			if (BaseEnum.YES.getCode().equals(orgAccountSystem.getFIssysPreset())) {
				throw new CustomException(String.format("会计核算体系:%s,为系统预设无法删除!",orgAccountSystem.getFName()), 1000);
			}
			if (orgAccountSystem.getFDocumentStatus().equals(DataStatusEnum.AUDIT.getCode())) {
				throw new CustomException(String.format("会计核算体系:%s,已审核数据不能删除!",orgAccountSystem.getFName()), 1000);
			}

			if (orgAccountSystem.getFDocumentStatus().equals(DataStatusEnum.CREATE.getCode())){
				// 未使用的直接删除
				if (!removeById(integer)) {
					throw new CustomException(String.format("会计核算体系:%s,删除失败!",orgAccountSystem.getFName()),1000);
				}
				LambdaQueryWrapper<TOrgAcctSysEntry> lambdaQuery = Wrappers.lambdaQuery();
				lambdaQuery.eq(TOrgAcctSysEntry::getFAcctsystemId, orgAccountSystem.getFId());
				if (!entryService.remove(lambdaQuery)) {
					throw new CustomException(String.format("会计核算体系:%s,删除失败!",orgAccountSystem.getFName()),1000);
				}
			}else {
				// 验证当前数据是否被引用
				String result = this.verifyThatItIsUsed(integer);
				if (StringUtils.checkValNotNull(result)){
					throw new CustomException(String.format("会计核算体系:%s,已被%s引用,无法删除!",orgAccountSystem.getFName(),result),1000);
				}
				// 已使用过的数据改状态
				TOrgAccountSystem accountSystem = new TOrgAccountSystem();
				accountSystem.setFId(orgAccountSystem.getFId());
				accountSystem.setFDocumentStatus(DataStatusEnum.DELETE.getCode());
				if (!updateById(accountSystem)) {
					throw new CustomException(String.format("会计核算体系:%s,删除失败!",orgAccountSystem.getFName()),1000);
				}
			}
		}
		return true;
    }

	/**
	 * 验证是否被引用
	 * @param fOrgAccountSystemId
	 */
	private String verifyThatItIsUsed(Integer fOrgAccountSystemId){
		// 返回结果
		StringBuilder result = new StringBuilder("");

		// 账簿 查询字段
		Map<String, Object> bookMap = new HashMap<>();
		bookMap.put("f_acctsystem_id",fOrgAccountSystemId);

		List<TBdAccountBook> tBdAccountBooks = bookMapper.selectByMap(bookMap);
		if (tBdAccountBooks.size()>0){
			result.append("账簿:");
			tBdAccountBooks.stream().map(bdAccountBook -> bdAccountBook.getFBookName() + ",").forEach(result::append);
			return result.toString();
		}
		return result.toString();

	}

	@Override
	public Boolean auditByIds(List<Integer> ids) {
		SysUser user = tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
		ids.forEach(id->{
			TOrgAccountSystem old = getById(id);
			if (StringUtils.checkValNull(old)){
				throw new CustomException(String.format("数据不存在!",id), 1000);
			}
			if (old.getFDocumentStatus().equals(DataStatusEnum.AUDIT.getCode())){
				throw new CustomException(String.format("会计核算体系:%s已经审核,无法重复审核!",old.getFName()), 1000);
			}
			old.setFModifierid(user.getUserId().intValue());
			old.setFModifyDate(new Date());
			old.setFAuditorid(user.getUserId().intValue());
			old.setFAuditDate(new Date());
			old.setFDocumentStatus(DataStatusEnum.AUDIT.getCode());
			boolean b = this.updateById(old);
			if (!b){
				throw new CustomException(String.format("会计核算体系:%s审核失败,请稍后重试!",old.getFName()), 1000);
			}
		});
		return true;
	}

	@Override
	public Boolean antiAuditByIds(List<Integer> ids) {
		SysUser user = tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
		ids.forEach(id->{
			TOrgAccountSystem old = getById(id);
			if (StringUtils.checkValNull(old)){
				throw new CustomException(String.format("数据不存在!",id), 1000);
			}

			if (!old.getFDocumentStatus().equals(DataStatusEnum.AUDIT.getCode())) {
				throw new CustomException(String.format("会计核算体系:%s未审核,请先提交审核!",old.getFName()), 1000);
			}
			old.setFModifierid(user.getUserId().intValue());
			old.setFModifyDate(new Date());
			old.setFAuditorid(user.getUserId().intValue());
			old.setFAuditDate(new Date());
			old.setFDocumentStatus(DataStatusEnum.REJECT.getCode());
			boolean b = this.updateById(old);
			if (!b){
				throw new CustomException(String.format("会计核算体系:%s反审核失败,请稍后重试!",old.getFName()), 1000);
			}
		});
		return true;
	}

	@Override
	public Boolean disableByEditBo(TOrgAccountSystemEditBo bo) {
		SysUser user = tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
		TOrgAccountSystemVo tOrgAccountSystemVo = queryById(bo.getFId());
		if (tOrgAccountSystemVo.getFForbidStatus().equals(bo.getFForbidStatus())){
			switch (bo.getFForbidStatus()) {
				case "0":
					throw new CustomException("会计核算体系:" + tOrgAccountSystemVo.getFName() + ",已被解禁,无法重复解禁!", 1000);
				case "1":
					throw new CustomException("会计核算体系:" + tOrgAccountSystemVo.getFName() + ",已禁用,无法重复禁用!", 1000);
			}
		}
		TOrgAccountSystem update = BeanUtil.toBean(bo, TOrgAccountSystem.class);
		update.setFModifierid(user.getUserId().intValue());
		update.setFModifyDate(new Date());
		update.setFForbidderid(user.getUserId().intValue());
		update.setFForbidDate(new Date());
		return updateById(update);
	}

	@Override
	public TableDataInfo<TOrgAccountSystemVo> queryPageListAccountSystem(TOrgAccountSystemQueryBo bo) {
		Page<TOrgAccountSystemQueryBo> userPage = new Page<>(bo.getPageNum(), bo.getPageSize());
		IPage<TOrgAccountSystemVo> systemVoIPage = systemMapper.queryPageListAccountSystem(userPage,buildQueryWrapper(bo));
		return PageUtils.buildDataInfo(systemVoIPage);
	}
}
