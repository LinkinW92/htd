package com.skeqi.finance.service.impl.basicinformation.voucher;

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
import com.skeqi.finance.domain.TGlVoucherGroupNo;
import com.skeqi.finance.domain.basicinformation.accountbook.TBdAccountBook;
import com.skeqi.finance.domain.basicinformation.voucher.TBdVchgroupAcct;
import com.skeqi.finance.domain.endhandle.TGlExchangeScheme;
import com.skeqi.finance.domain.endhandle.amortization.TGlAmortizationScheme;
import com.skeqi.finance.domain.endhandle.transfer.TGlAutoTransfer;
import com.skeqi.finance.domain.endhandle.withholding.TGlWithholdingScheme;
import com.skeqi.finance.domain.voucher.TGlVoucher;
import com.skeqi.finance.enums.BaseEnum;
import com.skeqi.finance.enums.DataStatusEnum;
import com.skeqi.finance.mapper.endhandle.TGlExchangeSchemeMapper;
import com.skeqi.finance.mapper.TGlVoucherGroupNoMapper;
import com.skeqi.finance.mapper.basicinformation.accountbook.TBdAccountBookMapper;
import com.skeqi.finance.mapper.basicinformation.voucher.TGlVoucherMapper;
import com.skeqi.finance.mapper.endhandle.TGlAmortizationSchemeMapper;
import com.skeqi.finance.mapper.endhandle.TGlAutoTransferMapper;
import com.skeqi.finance.mapper.endhandle.TGlWithholdingSchemeMapper;
import com.skeqi.finance.pojo.bo.basicinformation.voucher.*;
import com.skeqi.finance.service.account.ITBdAccountTableService;
import com.skeqi.finance.service.basicinformation.voucher.ITBdVchgroupAcctService;
import com.skeqi.framecore.web.service.TokenService;
import org.springframework.stereotype.Service;
import com.skeqi.common.core.mybatisplus.core.ServicePlusImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.skeqi.finance.domain.basicinformation.voucher.TBdVoucherGroup;
import com.skeqi.finance.mapper.basicinformation.voucher.TBdVoucherGroupMapper;
import com.skeqi.finance.pojo.vo.basicinformation.voucher.TBdVoucherGroupVo;
import com.skeqi.finance.service.basicinformation.voucher.ITBdVoucherGroupService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * ?????????Service???????????????
 *
 * @author toms
 * @date 2021-07-09
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TBdVoucherGroupServiceImpl extends ServicePlusImpl<TBdVoucherGroupMapper, TBdVoucherGroup> implements ITBdVoucherGroupService {


	/**
	 *?????????service
	 */
	@Resource
	ITBdAccountTableService accountTableService;

	@Resource
	private TokenService tokenService;

	@Resource
	private TBdVoucherGroupMapper groupMapper;

	@Resource
	private ITBdVchgroupAcctService vchgroupAcctService;

	/**
	 * ????????????
	 */
	@Resource
	private TGlAmortizationSchemeMapper tGlAmortizationSchemeMapper;

	/**
	 * ????????????
	 */
	@Resource
	private TGlAutoTransferMapper tGlAutoTransferMapper;

	/**
	 * ??????????????????
	 */
	@Resource
	private TGlExchangeSchemeMapper exchangeSchemeMapper;

	/**
	 * ??????????????????
	 */
	@Resource
	private TGlVoucherMapper voucherMapper;

	/**
	 * ???????????????
	 */
	@Resource
	private TGlVoucherGroupNoMapper voucherGroupNoMapper;

	/**
	 * ????????????
	 */
	@Resource
	private TGlWithholdingSchemeMapper withholdingSchemeMapper;

	/**
	 * ??????
	 */
	@Resource
	private TBdAccountBookMapper bookMapper;



    @Override
    public TBdVoucherGroupVo queryById(Integer fVchgroupId){
		LambdaQueryWrapper<TBdVoucherGroup> lqw = Wrappers.lambdaQuery();
		lqw.eq(TBdVoucherGroup::getFVchgroupId, fVchgroupId);
		lqw.ne(TBdVoucherGroup::getFDocumentStatus,DataStatusEnum.DELETE.getCode());
		TBdVoucherGroup tBdVoucherGroup = groupMapper.selectOne(lqw);
		TBdVoucherGroupVo tBdVoucherGroupVo = BeanUtil.toBean(tBdVoucherGroup, TBdVoucherGroupVo.class);
		return tBdVoucherGroupVo;
    }

    @Override
    public TableDataInfo<TBdVoucherGroupVo> queryPageList(TBdVoucherGroupQueryBo bo) {
		Page<TBdVoucherGroupQueryBo> userPage = new Page<>(bo.getPageNum(), bo.getPageSize());
		IPage<TBdVoucherGroupVo> tBdVoucherGroupVoIPage = groupMapper.queryPageList(userPage,bo);
        return PageUtils.buildDataInfo(tBdVoucherGroupVoIPage);
    }

    @Override
    public List<TBdVoucherGroupVo> queryList(TBdVoucherGroupQueryBo bo) {
        return listVo(buildQueryWrapper(bo), TBdVoucherGroupVo.class);
    }

    private LambdaQueryWrapper<TBdVoucherGroup> buildQueryWrapper(TBdVoucherGroupQueryBo bo) {
        LambdaQueryWrapper<TBdVoucherGroup> lqw = Wrappers.lambdaQuery();
        lqw.eq(StrUtil.isNotBlank(bo.getFLimitMulti()), TBdVoucherGroup::getFLimitMulti, bo.getFLimitMulti());
        lqw.eq(bo.getFAccttableId() != null, TBdVoucherGroup::getFAccttableId, bo.getFAccttableId());
        lqw.eq(StrUtil.isNotBlank(bo.getFIsdefault()), TBdVoucherGroup::getFIsdefault, bo.getFIsdefault());
        lqw.eq(bo.getFCreateOrgid() != null, TBdVoucherGroup::getFCreateOrgid, bo.getFCreateOrgid());
        lqw.eq(bo.getFCreatorid() != null, TBdVoucherGroup::getFCreatorid, bo.getFCreatorid());
        lqw.eq(bo.getFCreateDate() != null, TBdVoucherGroup::getFCreateDate, bo.getFCreateDate());
        lqw.eq(bo.getFUseOrgid() != null, TBdVoucherGroup::getFUseOrgid, bo.getFUseOrgid());
        lqw.eq(bo.getFModifierid() != null, TBdVoucherGroup::getFModifierid, bo.getFModifierid());
        lqw.eq(bo.getFModifyDate() != null, TBdVoucherGroup::getFModifyDate, bo.getFModifyDate());
        lqw.eq(StrUtil.isNotBlank(bo.getFDocumentStatus()), TBdVoucherGroup::getFDocumentStatus, bo.getFDocumentStatus());
        lqw.eq(bo.getFAuditorid() != null, TBdVoucherGroup::getFAuditorid, bo.getFAuditorid());
        lqw.eq(bo.getFAuditDate() != null, TBdVoucherGroup::getFAuditDate, bo.getFAuditDate());
        lqw.eq(StrUtil.isNotBlank(bo.getFForbidStatus()), TBdVoucherGroup::getFForbidStatus, bo.getFForbidStatus());
        lqw.eq(bo.getFForbidderid() != null, TBdVoucherGroup::getFForbidderid, bo.getFForbidderid());
        lqw.eq(bo.getFForbidDate() != null, TBdVoucherGroup::getFForbidDate, bo.getFForbidDate());
        lqw.eq(bo.getFIssysPreset() != null, TBdVoucherGroup::getFIssysPreset, bo.getFIssysPreset());
        lqw.eq(bo.getFMasterId() != null, TBdVoucherGroup::getFMasterId, bo.getFMasterId());
        lqw.eq(bo.getFVoucherWords() !=null,TBdVoucherGroup::getFVoucherWords,bo.getFVoucherWords());
        lqw.ne(TBdVoucherGroup::getFDocumentStatus,DataStatusEnum.DELETE.getCode());
        return lqw;
    }

    @Override
    public Boolean insertByAddBo(TBdVoucherGroupAddBo bo) {
		SysUser user = tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
		// ???????????????
		TBdVoucherGroup add = BeanUtil.toBean(bo, TBdVoucherGroup.class);
		add.setFCreatorid(user.getUserId().intValue());
		add.setFCreateDate(new Date());
		validEntityBeforeSave(add);
		Boolean save = save(add);
		if (save){
			//?????????????????????????????? ?????????:0  ?????????????????????1  ??????0
			if (bo.getFLimitMulti().equals(BaseEnum.YES.getCode())){
				//???????????????-????????????
				bo.getAcctAddBoList().forEach(tBdVchgroupAcctAddBo -> {
					tBdVchgroupAcctAddBo.setFAccttableId(add.getFAccttableId());
					tBdVchgroupAcctAddBo.setFVchgroupId(add.getFVchgroupId());
				});
				vchgroupAcctService.insertByAddBoList(bo.getAcctAddBoList());
			}
		}
		return save;
    }

    @Override
    public Boolean updateByEditBo(TBdVoucherGroupEditBo bo) {
		SysUser user = tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
        TBdVoucherGroup update = BeanUtil.toBean(bo, TBdVoucherGroup.class);
        update.setFModifierid(user.getUserId().intValue());
        update.setFModifyDate(new Date());
        validEntityBeforeSave(update);
		Boolean up = updateById(update);
		if (up){
			//???????????????-????????????
			bo.getAcctEditBoList().forEach(editBo -> editBo.setFVchgroupId(bo.getFVchgroupId()));
			vchgroupAcctService.updateByEditBoList(bo.getAcctEditBoList());
		}
        return up;
    }

    /**
     * ????????????????????????
     *
     * @param entity ???????????????
     */
    private void validEntityBeforeSave(TBdVoucherGroup entity){
        //TODO ?????????????????????,???????????????
		if (StringUtils.checkValNull(entity)){
			throw new CustomException("????????????????????????!",1000);
		}
		// ?????? ?????????????????????
		if (StringUtils.checkValNull(accountTableService.queryById(entity.getFAccttableId()))){
			throw new CustomException("????????????????????????",1000);
		}

		LambdaQueryWrapper<TBdVoucherGroup> lqw = Wrappers.lambdaQuery();
		lqw.eq(entity.getFAccttableId() != null, TBdVoucherGroup::getFAccttableId, entity.getFAccttableId());
		lqw.eq(entity.getFVoucherWords() !=null,TBdVoucherGroup::getFVoucherWords,entity.getFVoucherWords());
		TBdVoucherGroup voOne = getVoOne(lqw, TBdVoucherGroup.class);
		if (StringUtils.checkValNotNull(voOne)){
			// ???????????????
			if (voOne.getFVchgroupId().equals(entity.getFVchgroupId())){

				if(BaseEnum.YES.getCode().equals(entity.getFIssysPreset())){
					throw new CustomException("??????????????????????????????",1000);
				}

				if(DataStatusEnum.AUDIT.getCode().equals(entity.getFDocumentStatus())){
					throw new CustomException("???????????????????????????",1000);
				}

				//????????????
				if (voOne.getFForbidStatus().equals(BaseEnum.YES.getCode())) {
					throw new CustomException(String.format("?????????:%s,????????????",voOne.getFVoucherWords()),1000);
				}
				return;
			}
			throw new CustomException("?????????+???????????????,?????????????????????!",1000);
		}
    }

    @Override
    public Boolean
	deleteWithValidByIds(List<Integer> ids) {
			//??????
			if (StringUtils.checkValNull(ids)) {
				throw new CustomException("???????????????????????????!",1000);
			}
			for (Integer integer : ids) {
				LambdaQueryWrapper<TBdVoucherGroup> lqw = Wrappers.lambdaQuery();
				lqw.eq(TBdVoucherGroup::getFVchgroupId, integer);
				lqw.ne(TBdVoucherGroup::getFDocumentStatus,DataStatusEnum.DELETE.getCode());
				TBdVoucherGroup tBdVoucherGroupVo = groupMapper.selectOne(lqw);

				if (StringUtils.checkValNull(tBdVoucherGroupVo)) {
					throw new CustomException(String.format("??????:%s,???????????????????????????",integer), 1000);
				}
				if (tBdVoucherGroupVo.getFIssysPreset().equals(BaseEnum.YES.getCode())) {
					throw new CustomException(String.format("?????????:%s,???????????????????????????",tBdVoucherGroupVo.getFVoucherWords()), 1000);
				}
				if (tBdVoucherGroupVo.getFDocumentStatus().equals(DataStatusEnum.AUDIT.getCode())) {
					throw new CustomException(String.format("?????????:%s,???????????????????????????",tBdVoucherGroupVo.getFVoucherWords()), 1000);
				}

				if (tBdVoucherGroupVo.getFDocumentStatus().equals(DataStatusEnum.CREATE.getCode())){
					// ????????????????????????
					if (!removeById(integer)) {
						throw new CustomException(String.format("?????????:%s,????????????",tBdVoucherGroupVo.getFVoucherWords()),1000);
					}
					//?????????????????????
					LambdaQueryWrapper<TBdVchgroupAcct> lambdaQuery = Wrappers.lambdaQuery();
					lambdaQuery.eq(TBdVchgroupAcct::getFVchgroupId, tBdVoucherGroupVo.getFVchgroupId());
					vchgroupAcctService.remove(lambdaQuery);
				}else {
					// ?????????????????????????????????
				String result = this.verifyThatItIsUsed(integer);
				if (StringUtils.checkValNotNull(result)){
						throw new CustomException(String.format("?????????:%s,??????%s??????,????????????!",tBdVoucherGroupVo.getFVoucherWords(),result),1000);
					}
					// ??????????????????????????????
					TBdVoucherGroup group = new TBdVoucherGroup();
					group.setFVchgroupId(tBdVoucherGroupVo.getFVchgroupId());
					group.setFDocumentStatus(DataStatusEnum.DELETE.getCode());
					if (!updateById(group)) {
						throw new CustomException(String.format("?????????:%s,????????????!",tBdVoucherGroupVo.getFVoucherWords()),1000);
					}
				}
			}
		return true;
    }
	/**
	 * ?????????????????????
	 * @param fVoucherGroupId
	 */
	private String verifyThatItIsUsed(Integer fVoucherGroupId){
		// ????????????
		StringBuilder result = new StringBuilder("");
		// ?????? ????????????
		Map<String, Object> map = new HashMap<>();
		map.put("f_voucher_group_id",fVoucherGroupId);

		// ???????????? ????????????
		Map<String, Object> exchangeSchemeMap = new HashMap<>();
		exchangeSchemeMap.put("f_vchgroup_id",fVoucherGroupId);

		// ?????? ????????????
		Map<String, Object> bookMap = new HashMap<>();
		bookMap.put("f_default_voucher_type",fVoucherGroupId);

		List<TGlAmortizationScheme> tGlAmortizationSchemeList = tGlAmortizationSchemeMapper.selectByMap(map);
		if (tGlAmortizationSchemeList.size()>0){
			result.append("????????????:");
			for (TGlAmortizationScheme tGlAmortizationScheme : tGlAmortizationSchemeList) {
				result.append(tGlAmortizationScheme.getFName()+",");
			}
			return result.toString();
		}

		List<TGlAutoTransfer> autoTransferList = tGlAutoTransferMapper.selectByMap(map);
		if (autoTransferList.size()>0){
			result.append("????????????:");
			for (TGlAutoTransfer autoTransfer : autoTransferList) {
				result.append(autoTransfer.getFName()+",");
			}
			return result.toString();
		}

		List<TGlExchangeScheme> exchangeSchemeList = exchangeSchemeMapper.selectByMap(exchangeSchemeMap);
		if (exchangeSchemeList.size()>0){
			result.append("??????????????????:");
			for (TGlExchangeScheme exchangeScheme : exchangeSchemeList) {
				result.append(exchangeScheme.getFName()+",");
			}
			return result.toString();
		}

		List<TGlVoucher> tGlVouchers = voucherMapper.selectByMap(map);
		if (tGlVouchers.size()>0){
			result.append("?????????:");
			for (TGlVoucher voucher : tGlVouchers) {
				result.append(voucher.getFVoucherId()+",");
			}
			return result.toString();
		}

		List<TGlVoucherGroupNo> voucherGroupNoVoList = voucherGroupNoMapper.selectByMap(map);
		if (voucherGroupNoVoList.size()>0){
			result.append("???????????????:");
			for (TGlVoucherGroupNo no : voucherGroupNoVoList) {
				result.append(no.getFId()+",");
			}
			return result.toString();
		}

		List<TGlWithholdingScheme> tGlWithholdingSchemeList = withholdingSchemeMapper.selectByMap(map);
		if (tGlWithholdingSchemeList.size()>0){
			result.append("????????????:");
			for (TGlWithholdingScheme withholdingScheme : tGlWithholdingSchemeList) {
				result.append(withholdingScheme.getFName()+",");
			}
			return result.toString();
		}

		List<TBdAccountBook> tBdAccountBooks = bookMapper.selectByMap(bookMap);
		if (tBdAccountBooks.size()>0){
			result.append("??????:");
			for (TBdAccountBook bdAccountBook : tBdAccountBooks) {
				result.append(bdAccountBook.getFBookName()+",");
			}
			return result.toString();
		}

		return result.toString();
	}

	@Override
	public Boolean auditByIds(List<Integer> ids) {
		SysUser user = tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
		for (Integer id : ids) {
			TBdVoucherGroup old = getById(id);
			if (StringUtils.checkValNull(old)){
				throw new CustomException(String.format("???????????????!",id), 1000);
			}

			if (old.getFDocumentStatus().equals(DataStatusEnum.AUDIT.getCode())){
				throw new CustomException(String.format("?????????:%s????????????,??????????????????!",old.getFVoucherWords()), 1000);
			}
			old.setFModifierid(user.getUserId().intValue());
			old.setFModifyDate(new Date());
			old.setFAuditorid(user.getUserId().intValue());
			old.setFAuditDate(new Date());
			old.setFDocumentStatus(DataStatusEnum.AUDIT.getCode());
			boolean b = this.updateById(old);
			if (!b){
				throw new CustomException(String.format("?????????:%s????????????,???????????????!",old.getFVoucherWords()), 1000);
			}
		}
		return true;
	}

	/**
	 * ?????????
	 *
	 * @param ids
	 * @return
	 */
	@Override
	public Boolean antiAuditByIds(List<Integer> ids) {
		SysUser user = tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
		for (Integer id : ids) {
			TBdVoucherGroup old = getById(id);
			if (StringUtils.checkValNull(old)){
				throw new CustomException(String.format("???????????????!",id), 1000);
			}

			if (!old.getFDocumentStatus().equals(DataStatusEnum.AUDIT.getCode())) {
				throw new CustomException(String.format("?????????:%s?????????,??????????????????!",old.getFVoucherWords()), 1000);
			}
			old.setFModifierid(user.getUserId().intValue());
			old.setFModifyDate(new Date());
			old.setFAuditorid(user.getUserId().intValue());
			old.setFAuditDate(new Date());
			old.setFDocumentStatus(DataStatusEnum.REJECT.getCode());
			boolean b = this.updateById(old);
			if (!b){
				throw new CustomException(String.format("?????????:%s???????????????,???????????????!",old.getFVoucherWords()), 1000);
			}
		}
		return true;
	}

	@Override
	public Boolean disableByEditBo(TBdVoucherGroupEditBo bo) {
		SysUser user = tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
		TBdVoucherGroupVo tBdVoucherGroupVo = queryById(bo.getFVchgroupId());
		if (tBdVoucherGroupVo.getFForbidStatus().equals(bo.getFForbidStatus())) {
			switch (bo.getFForbidStatus()) {
				case "0":
					throw new CustomException("?????????:" + tBdVoucherGroupVo.getFVoucherWords() + ",????????????,??????????????????!", 1000);
				case "1":
						throw new CustomException("?????????:" + tBdVoucherGroupVo.getFVoucherWords() + ",?????????,??????????????????!", 1000);
			}
		}
		TBdVoucherGroup update = BeanUtil.toBean(bo, TBdVoucherGroup.class);
		update.setFModifierid(user.getUserId().intValue());
		update.setFModifyDate(new Date());
		update.setFForbidderid(user.getUserId().intValue());
		update.setFForbidDate(new Date());
		return updateById(update);
	}
}
