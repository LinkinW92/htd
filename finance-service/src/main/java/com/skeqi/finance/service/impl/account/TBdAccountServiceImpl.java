package com.skeqi.finance.service.impl.account;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skeqi.common.core.mybatisplus.core.ServicePlusImpl;
import com.skeqi.common.core.page.TableDataInfo;
import com.skeqi.common.exception.CustomException;
import com.skeqi.common.utils.PageUtils;
import com.skeqi.finance.domain.TBdAccount;
import com.skeqi.finance.domain.TBdAccountGroup;
import com.skeqi.finance.domain.TBdAccountTable;
import com.skeqi.finance.domain.TBdAccountType;
import com.skeqi.finance.enums.BaseEnum;
import com.skeqi.finance.enums.BorrowEnum;
import com.skeqi.finance.enums.DataStatusEnum;
import com.skeqi.finance.mapper.TBdAccountGroupMapper;
import com.skeqi.finance.mapper.account.TBdAccountMapper;
import com.skeqi.finance.mapper.account.TBdAccountTableMapper;
import com.skeqi.finance.mapper.account.TBdAccountTypeMapper;
import com.skeqi.finance.pojo.bo.TBdAccount.TBdAccountAddBo;
import com.skeqi.finance.pojo.bo.TBdAccount.TBdAccountEditBo;
import com.skeqi.finance.pojo.bo.TBdAccount.TBdAccountQueryBo;
import com.skeqi.finance.pojo.bo.TBdAccountFlexentry.TBdAccountFlexentryAddBo;
import com.skeqi.finance.pojo.bo.TBdAccountType.TBdAccountTypeQueryBo;
import com.skeqi.finance.pojo.bo.TBdDimensionSource.DisableBo;
import com.skeqi.finance.pojo.vo.TBdAccount.*;
import com.skeqi.finance.pojo.vo.basicinformation.base.TBdCurrencyVo;
import com.skeqi.finance.service.basicinformation.base.BaseTableService;
import com.skeqi.finance.service.basicinformation.base.ITBdCurrencyService;
import com.skeqi.finance.service.account.ITBdAccountFlexentryService;
import com.skeqi.finance.service.account.ITBdAccountService;
import com.skeqi.finance.service.account.ITBdAccountTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 科目信息Service业务层处理
 *
 * @author toms
 * @date 2021-07-19
 */
@Service
public class TBdAccountServiceImpl extends ServicePlusImpl<TBdAccountMapper, TBdAccount> implements ITBdAccountService, BaseTableService {


	//科目表
	@SuppressWarnings("all")
	@Autowired
	private TBdAccountTableMapper tBdAccountTableMapper;
	//会计要素信息
	@SuppressWarnings("all")
	@Autowired
	private TBdAccountGroupMapper tBdAccountGroupMapper;
	//核算维度
	@SuppressWarnings("all")
	@Autowired
	private TBdAccountTypeMapper tBdAccountTypeMapper;

	//科目类别
	@Autowired
	ITBdAccountTypeService itBdAccountTypeService;

	//币别
	@Autowired
	private ITBdCurrencyService itBdCurrencyService;
	//科目核算维度
	@Autowired
	private ITBdAccountFlexentryService iTBdAccountFlexentryService;

	@Override
	public TBdAccountVo queryById(Integer fAcctId) {
		TBdAccountVo vo = baseMapper.queryOneById(fAcctId);
		if(vo != null) {
			if (BaseEnum.YES.getCode().equals(vo.getFAllcurrency())) {
				vo.setFCurrencyName("所有外币");
			} else {
				String currencys = vo.getFCurrencys();
				if (StringUtils.isNotBlank(currencys)) {
					List<Map> currencyList = baseMapper.queryCurrencyList(currencys);
					vo.setFCurrencyList(currencyList);
				}
			}
			//查询核算维度名称
			List<Map> flexentryList = baseMapper.queryFlexentryList(vo.getFAcctId());
			vo.setFItemDetailList(flexentryList);
		}
		return vo;
	}


	@Override
	public TableDataInfo<TBdAccountVo> queryPageList(TBdAccountQueryBo bo) {
		if (bo.getPageNum() == null) bo.setPageNum(1);
		if (bo.getPageSize() == null) bo.setPageSize(10);
		IPage<TBdAccountVo> page = new Page<>(bo.getPageNum(), bo.getPageSize());
		Page<TBdAccountVo> tBdAccountVoPage = baseMapper.queryList2(page, bo);
		List<TBdAccountVo> tBdAccountVoPageRecords = tBdAccountVoPage.getRecords();
		for (TBdAccountVo vo : tBdAccountVoPageRecords) {
			if (BaseEnum.YES.getCode().equals(vo.getFAllcurrency())) {
				vo.setFCurrencyName("所有外币");
			} else {
				String currencys = vo.getFCurrencys();
				if (StringUtils.isNotBlank(currencys)) {
					List<String> currencyNameList = baseMapper.queryCurrencyNameList(currencys);
					vo.setFCurrencyName(String.join("/", currencyNameList));
				}
			}
			//查询核算维度名称
			List<String> flexentryNameList = baseMapper.queryFlexentryNameList(vo.getFAcctId());
			vo.setFItemDetailName(String.join("/", flexentryNameList));
		}
		return PageUtils.buildDataInfo(tBdAccountVoPage);

	}

	@Override
	public List<TBdAccountVo> queryList(TBdAccountQueryBo bo) {
		return listVo(buildQueryWrapper(bo), TBdAccountVo.class);
	}

	private LambdaQueryWrapper<TBdAccount> buildQueryWrapper(TBdAccountQueryBo bo) {
		Map<String, Object> params = bo.getParams();
		LambdaQueryWrapper<TBdAccount> lqw = Wrappers.lambdaQuery();
		lqw.eq(StrUtil.isNotBlank(bo.getFNumber()), TBdAccount::getFNumber, bo.getFNumber());
		lqw.like(StrUtil.isNotBlank(bo.getFName()), TBdAccount::getFName, bo.getFName());
		lqw.eq(StrUtil.isNotBlank(bo.getFDescription()), TBdAccount::getFDescription, bo.getFDescription());
		lqw.eq(bo.getFParentId() != null, TBdAccount::getFParentId, bo.getFParentId());
		lqw.eq(StrUtil.isNotBlank(bo.getFHelpErcode()), TBdAccount::getFHelpErcode, bo.getFHelpErcode());
		lqw.eq(bo.getFGroupId() != null, TBdAccount::getFGroupId, bo.getFGroupId());
		lqw.eq(bo.getFDc() != null, TBdAccount::getFDc, bo.getFDc());
		lqw.eq(bo.getFAccttblid() != null, TBdAccount::getFAccttblid, bo.getFAccttblid());
		lqw.eq(StrUtil.isNotBlank(bo.getFIscash()), TBdAccount::getFIscash, bo.getFIscash());
		lqw.eq(StrUtil.isNotBlank(bo.getFIsbank()), TBdAccount::getFIsbank, bo.getFIsbank());
		lqw.eq(StrUtil.isNotBlank(bo.getFIsallocate()), TBdAccount::getFIsallocate, bo.getFIsallocate());
		lqw.eq(StrUtil.isNotBlank(bo.getFIscashFlow()), TBdAccount::getFIscashFlow, bo.getFIscashFlow());
		lqw.eq(bo.getFItemDetailid() != null, TBdAccount::getFItemDetailid, bo.getFItemDetailid());
		lqw.eq(StrUtil.isNotBlank(bo.getFIsquantities()), TBdAccount::getFIsquantities, bo.getFIsquantities());
		lqw.eq(bo.getFUnitGroupid() != null, TBdAccount::getFUnitGroupid, bo.getFUnitGroupid());
		lqw.eq(bo.getFUnitId() != null, TBdAccount::getFUnitId, bo.getFUnitId());
		lqw.eq(StrUtil.isNotBlank(bo.getFIsdetail()), TBdAccount::getFIsdetail, bo.getFIsdetail());
		lqw.eq(bo.getFLevel() != null, TBdAccount::getFLevel, bo.getFLevel());
		lqw.eq(bo.getFCreateOrgid() != null, TBdAccount::getFCreateOrgid, bo.getFCreateOrgid());
		lqw.eq(bo.getFCreatorid() != null, TBdAccount::getFCreatorid, bo.getFCreatorid());
		lqw.eq(bo.getFCreateDate() != null, TBdAccount::getFCreateDate, bo.getFCreateDate());
		lqw.eq(bo.getFUseOrgid() != null, TBdAccount::getFUseOrgid, bo.getFUseOrgid());
		lqw.eq(bo.getFModifierid() != null, TBdAccount::getFModifierid, bo.getFModifierid());
		lqw.eq(bo.getFModifyDate() != null, TBdAccount::getFModifyDate, bo.getFModifyDate());
		lqw.eq(StrUtil.isNotBlank(bo.getFDocumentStatus()), TBdAccount::getFDocumentStatus, bo.getFDocumentStatus());
		lqw.eq(bo.getFAuditorid() != null, TBdAccount::getFAuditorid, bo.getFAuditorid());
		lqw.eq(bo.getFAuditDate() != null, TBdAccount::getFAuditDate, bo.getFAuditDate());
		lqw.eq(StrUtil.isNotBlank(bo.getFForbidStatus()), TBdAccount::getFForbidStatus, bo.getFForbidStatus());
		lqw.eq(bo.getFForbidderid() != null, TBdAccount::getFForbidderid, bo.getFForbidderid());
		lqw.eq(bo.getFForbidDate() != null, TBdAccount::getFForbidDate, bo.getFForbidDate());
		lqw.eq(bo.getFIssysPreset() != null, TBdAccount::getFIssysPreset, bo.getFIssysPreset());
		lqw.eq(bo.getFCfitemId() != null, TBdAccount::getFCfitemId, bo.getFCfitemId());
		lqw.eq(bo.getFOcfitemId() != null, TBdAccount::getFOcfitemId, bo.getFOcfitemId());
		lqw.eq(bo.getFCfindirectitemId() != null, TBdAccount::getFCfindirectitemId, bo.getFCfindirectitemId());
		lqw.eq(bo.getFOcfindirectitemId() != null, TBdAccount::getFOcfindirectitemId, bo.getFOcfindirectitemId());
		lqw.eq(StrUtil.isNotBlank(bo.getFAllcurrency()), TBdAccount::getFAllcurrency, bo.getFAllcurrency());
		lqw.eq(StrUtil.isNotBlank(bo.getFCurrencys()), TBdAccount::getFCurrencys, bo.getFCurrencys());
		lqw.eq(StrUtil.isNotBlank(bo.getFIsshowJournal()), TBdAccount::getFIsshowJournal, bo.getFIsshowJournal());
		return lqw;
	}

	@Override
	@Transactional
	public Boolean insertByAddBo(TBdAccountAddBo bo) {
		TBdAccount add = BeanUtil.toBean(bo, TBdAccount.class);
		add.setAddDefault();
		validEntityBeforeSave(add);
		add.setFCreateDate(new Date());
		add.setFDocumentStatus(DataStatusEnum.CREATE.getCode());
		add.setFForbidStatus(BaseEnum.NO.getCode());
		add.setFIssysPreset(BaseEnum.NO.getCode());
		save(add);
		//添加科目核算维度组
		List<TBdAccountFlexentryAddBo> accountFlexentryAddBoList = bo.getTBdAccountFlexentryAddBoList();
		int i = 1;
		for (TBdAccountFlexentryAddBo accountFlexentryAddBo : accountFlexentryAddBoList) {
			accountFlexentryAddBo.setFAcctId(add.getFAcctId());
			accountFlexentryAddBo.setFAcctitemisvalid(BaseEnum.YES.getCode());
			accountFlexentryAddBo.setFSeq("" + i);
			iTBdAccountFlexentryService.insertByAddBo(accountFlexentryAddBo);
			i++;
		}
		return true;
	}

	@Override
	public Boolean updateByEditBo(TBdAccountEditBo bo) {
		TBdAccount update = BeanUtil.toBean(bo, TBdAccount.class);
		update.setUpdateDefault();
		validEntityBeforeUpdate(update);
		update.setFModifyDate(new Date());
		return updateById(update);
	}


	/**
	 * 保存前的数据校验
	 *
	 * @param entity 实体类数据
	 */
	private void validEntityBeforeSave(TBdAccount entity) {
		//TODO 做一些数据校验,如唯一约束
		//科目信息内码
		String number = entity.getFNumber();
		int lastIndexOf = number.lastIndexOf(".");
		int parentId;
		if(lastIndexOf != -1){
			String parentNumber = number.substring(0,lastIndexOf);
			TBdAccount tBdAccount = baseMapper.selectOne(new LambdaQueryWrapper<>());
			if(tBdAccount == null){
				throw new CustomException(String.format("上级科目不存在"));
			}else if (!DataStatusEnum.AUDIT.getCode().equals(tBdAccount.getFDocumentStatus())) {
				throw new CustomException(String.format("父科目没有审核"));
			} else if (BaseEnum.YES.getCode().equals(tBdAccount.getFForbidStatus())) {
				throw new CustomException(String.format("父科目被禁用"));
			}
		}

		//科目类别码
		Integer groupId = entity.getFGroupId();
		TBdAccountTypeVo tBdAccountTypeVo = itBdAccountTypeService.queryById(groupId);
		if (tBdAccountTypeVo == null) {
			throw new CustomException(String.format("%s科目类别不存在", "" + groupId));
		}
		//科目表
		Integer accttblid = tBdAccountTypeVo.getFAcctTableId();
		entity.setFAccttblid(accttblid);
//		TBdAccountTableVo tBdAccountTableVo = itBdAccountTableService.queryById(accttblid);
//		if (tBdAccountTableVo == null) {
//			throw new CustomException(String.format("%s科目表不存在", "" + accttblid));
//		}
		//同一个科目表中科目编码唯一
		LambdaQueryWrapper<TBdAccount> lqwAccount = new LambdaQueryWrapper<TBdAccount>();
		lqwAccount.eq(TBdAccount::getFGroupId, groupId);
		lqwAccount.eq(TBdAccount::getFAccttblid, accttblid);
		lqwAccount.eq(TBdAccount::getFNumber, number);
		TBdAccount tBdAccount = getVoOne(lqwAccount, TBdAccount.class);
		if (tBdAccount != null) {
			throw new CustomException(String.format("%s同一个科目表中科目编码唯一", "" + number));
		}
		//余额方向
		Integer dc = entity.getFDc();
		if (null == BorrowEnum.getObj(dc + "")) {
			throw new CustomException(String.format("%s余额方向1 ：借方 ； -1 ：贷方", "" + dc));
		}
		//是否现金科目0：非现金科目 ，1：现金科目 ，默认0
		String iscash = entity.getFIscash();
		if (null == BaseEnum.getObj(iscash)) {
			throw new CustomException(String.format("%s现金科目0：非现金科目 ，1：现金科目 ", "" + iscash));
		}
		//是否银行科目 0：非银行科目 ，1：银行科目 ，默认0
		String isbank = entity.getFIsbank();
		if (null == BaseEnum.getObj(isbank)) {
			throw new CustomException(String.format("%s银行科目 0：非银行科目 ，1：银行科目", "" + isbank));
		}
		//是否期末调汇
		String isallocate = entity.getFIsallocate();
		if (null == BaseEnum.getObj(isallocate)) {
			throw new CustomException(String.format("%s期末调汇", "" + isallocate));
		}
		//是否现金等价物 0：非现金等价物 ，1：现金等价物 ，默认0
		String iscashFlow = entity.getFIscashFlow();
		if (null == BaseEnum.getObj(iscashFlow)) {
			throw new CustomException(String.format("%s现金等价物 0：非现金等价物 ，1：现金等价物", "" + iscashFlow));
		}
		//是否出日记账 0：不出日记账 ，1：出日记账，默认0
		String isshowJournal = entity.getFIsshowJournal();
		if (null == BaseEnum.getObj(isshowJournal)) {
			throw new CustomException(String.format("出日记账 0：不出日记账 ，1：出日记账,收到的是%s", isshowJournal));
		}
		//核算所有币别
		String allcurrency = entity.getFAllcurrency();
		if (null == BaseEnum.getObj(allcurrency)) {
			throw new CustomException(String.format("核算所有币别,收到的是%s", allcurrency));
		} else {
			entity.setFCurrencys("");
		}
		//币别列表
		if (!allcurrency.equals(BaseEnum.YES.getCode())) {
			String currencys = entity.getFCurrencys();
			if (currencys != null && !("".equals(currencys))) {
				String[] currencyAry = currencys.split(",");
				for (String currency : currencyAry) {
					TBdCurrencyVo tBdCurrencyVo = itBdCurrencyService.queryById(Integer.parseInt(currency));
					if (tBdAccount == null) {
						throw new CustomException(String.format("%s所有币别中%s币别不存在", currencys, currency));
					} else if (!DataStatusEnum.AUDIT.getCode().equals(tBdCurrencyVo.getFDocumentStatus())) {
						throw new CustomException(String.format("%s所有币别中%s币别没有审核通过", currencys, currency));
					} else if (BaseEnum.YES.getCode().equals(tBdCurrencyVo.getFForbidStatus())) {
						throw new CustomException(String.format("%s所有币别中%s币别被禁用", currencys, currency));
					}
				}
			}
		}

		//会计要素
		Integer acctGroupId = tBdAccountTypeVo.getFAcctGroupId();
		//主表项目（流入)
		Integer cfitemId = entity.getFCfitemId();
		if (cfitemId != null) {
			Map map = baseMapper.getGlCashFlow(cfitemId);
			if (map == null) {
				throw new CustomException(String.format("主表项目（流入)不存在,收到的是%s", cfitemId));
			}
			Integer fItemTypeid = Integer.parseInt(map.get("f_item_typeid").toString());
			Integer fAcctGroupTblid = Integer.parseInt(map.get("f_acct_group_tblid").toString());
			Integer fItemGroupid = Integer.parseInt(map.get("f_item_groupid").toString());
			String fDocumentStatus = map.get("f_document_status").toString();
			String fForbidStatus = map.get("f_forbid_status").toString();
			if (fItemTypeid != 1) {
				throw new CustomException(String.format("现金流项目不是流入类型,收到的是%s", cfitemId));
			} else if (fItemGroupid != 2) {
				throw new CustomException(String.format("现金流项目不是主表项目,收到的是%s", cfitemId));
			} else if (fAcctGroupTblid != acctGroupId) {
				throw new CustomException(String.format("现金流项目会计要素不匹配,收到的是%s", cfitemId));
			} else if (!DataStatusEnum.AUDIT.getCode().equals(fDocumentStatus)) {
				throw new CustomException(String.format("现金流项目没有审核通过,收到的是%s", cfitemId));
			} else if (BaseEnum.YES.getCode().equals(fForbidStatus)) {
				throw new CustomException(String.format("现金流项目被禁用", cfitemId));
			}
		}
		//查询数据状态，禁用状态，项目类别。  表：会计要素
		//主表项目（流出)
		Integer ocfitemId = entity.getFOcfitemId();
		if (ocfitemId != null) {
			Map map = baseMapper.getGlCashFlow(ocfitemId);
			if (map == null) {
				throw new CustomException(String.format("主表项目（流入)不存在,收到的是%s", cfitemId));
			}
			Integer fItemTypeid = Integer.parseInt(map.get("f_item_typeid").toString());
			Integer fAcctGroupTblid = Integer.parseInt(map.get("f_acct_group_tblid").toString());
			Integer fItemGroupid = Integer.parseInt(map.get("f_item_groupid").toString());
			String fDocumentStatus = map.get("f_document_status").toString();
			String fForbidStatus = map.get("f_forbid_status").toString();
			if (fItemTypeid != -1) {
				throw new CustomException(String.format("现金流项目不是流出类型,收到的是%s", cfitemId));
			} else if (fItemGroupid != 2) {
				throw new CustomException(String.format("现金流项目不是主表项目,收到的是%s", cfitemId));
			} else if (fAcctGroupTblid != acctGroupId) {
				throw new CustomException(String.format("现金流项目会计要素不匹配,收到的是%s", cfitemId));
			} else if (!DataStatusEnum.AUDIT.getCode().equals(fDocumentStatus)) {
				throw new CustomException(String.format("现金流项目没有审核通过,收到的是%s", cfitemId));
			} else if (BaseEnum.YES.getCode().equals(fForbidStatus)) {
				throw new CustomException(String.format("现金流项目被禁用", cfitemId));
			}
		}
		//附表项目（流入）
		Integer cfindirectitemId = entity.getFCfindirectitemId();
		if (cfindirectitemId != null) {
			Map map = baseMapper.getGlCashFlow(cfindirectitemId);
			if (map == null) {
				throw new CustomException(String.format("主表项目（流入)不存在,收到的是%s", cfitemId));
			}
			Integer fItemTypeid = Integer.parseInt(map.get("f_item_typeid").toString());
			Integer fAcctGroupTblid = Integer.parseInt(map.get("f_acct_group_tblid").toString());
			Integer fItemGroupid = Integer.parseInt(map.get("f_item_groupid").toString());
			String fDocumentStatus = map.get("f_document_status").toString();
			String fForbidStatus = map.get("f_forbid_status").toString();
			if (fItemTypeid != 1) {
				throw new CustomException(String.format("现金流项目不是流入类型,收到的是%s", cfitemId));
			} else if (fItemGroupid != -1) {
				throw new CustomException(String.format("现金流项目不是附表项目,收到的是%s", cfitemId));
			} else if (fAcctGroupTblid != acctGroupId) {
				throw new CustomException(String.format("现金流项目会计要素不匹配,收到的是%s", cfitemId));
			} else if (!DataStatusEnum.AUDIT.getCode().equals(fDocumentStatus)) {
				throw new CustomException(String.format("现金流项目没有审核通过,收到的是%s", cfitemId));
			} else if (BaseEnum.YES.getCode().equals(fForbidStatus)) {
				throw new CustomException(String.format("现金流项目被禁用", cfitemId));
			}
		}
		//附表项目（流出）
		Integer ocfindirectitemId = entity.getFOcfindirectitemId();
		if (ocfindirectitemId != null) {
			Map map = baseMapper.getGlCashFlow(ocfindirectitemId);
			if (map == null) {
				throw new CustomException(String.format("主表项目（流入)不存在,收到的是%s", cfitemId));
			}
			Integer fItemTypeid = Integer.parseInt(map.get("f_item_typeid").toString());
			Integer fAcctGroupTblid = Integer.parseInt(map.get("f_acct_group_tblid").toString());
			Integer fItemGroupid = Integer.parseInt(map.get("f_item_groupid").toString());
			String fDocumentStatus = map.get("f_document_status").toString();
			String fForbidStatus = map.get("f_forbid_status").toString();
			if (fItemTypeid != -1) {
				throw new CustomException(String.format("现金流项目不是流出类型,收到的是%s", cfitemId));
			} else if (fItemGroupid != 2) {
				throw new CustomException(String.format("现金流项目不是附表项目,收到的是%s", cfitemId));
			} else if (fAcctGroupTblid != acctGroupId) {
				throw new CustomException(String.format("现金流项目会计要素不匹配,收到的是%s", cfitemId));
			} else if (!DataStatusEnum.AUDIT.getCode().equals(fDocumentStatus)) {
				throw new CustomException(String.format("现金流项目没有审核通过,收到的是%s", cfitemId));
			} else if (BaseEnum.YES.getCode().equals(fForbidStatus)) {
				throw new CustomException(String.format("现金流项目被禁用", cfitemId));
			}
		}
	}


	/**
	 * 修改前的数据校验
	 *
	 * @param entity 实体类数据
	 */
	private void validEntityBeforeUpdate(TBdAccount entity) {
		entity.setUpdateDefault();
		//TODO 做一些数据校验,如唯一约束
		//内码
		Integer acctId = entity.getFAcctId();
		TBdAccountVo tBdAccountVo = queryById(acctId);
		if (tBdAccountVo == null) {
			throw new CustomException(String.format("%s科目id不存在", "" + acctId));
		}
		//审核下不能修改
		if (!DataStatusEnum.AUDIT.getCode().equals(tBdAccountVo.getFDocumentStatus())) {
			throw new CustomException(String.format("%s以审核不能在修改", "" + acctId));
		}
		validEntityBeforeSave(entity);
	}

	@Override
	public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
		if (isValid) {
			//TODO 做一些业务上的校验,判断是否需要校验
			ids.stream().forEach(id -> {
				//账簿
				TBdAccountTypeQueryBo tBdAccountTypeQueryBo = new TBdAccountTypeQueryBo();
				tBdAccountTypeQueryBo.setFAcctTableId(id.intValue());
				List<TBdAccountTypeVo> tBdAccountTypeVos = itBdAccountTypeService.queryList(tBdAccountTypeQueryBo);
				if (tBdAccountTypeVos.size() > 0) {
					throw new CustomException(String.format("%s存在绑定的科目类别，不能删除", "" + id));
				}
			});
		}
		return removeByIds(ids);
	}


	/**
	 * 科目表查询。查询关联的会计要素，关联科目类别
	 *
	 * @return
	 */
	@Override
	public List<AccountTableVo> listAcountTable() {
		//查询到科目表
		List<TBdAccountTable> tBdAccountTableList = tBdAccountTableMapper.selectList(new LambdaQueryWrapper<TBdAccountTable>());
		List<AccountTableVo> tBdAccountVoList = tBdAccountTableList.stream()
			.map(any -> BeanUtil.toBean(any, AccountTableVo.class))
			.collect(Collectors.toList());
		for (AccountTableVo accountTableVo : tBdAccountVoList) {
			LambdaQueryWrapper<TBdAccountGroup> lqw = new LambdaQueryWrapper<>();
			Integer acctGroupTblid = accountTableVo.getFAcctGroupTblid();
			Integer acctTableId = accountTableVo.getFId();
			lqw.eq(TBdAccountGroup::getFAcctgroupTblid, acctGroupTblid);
			//查询到会计要素
			List<TBdAccountGroup> tBdAccountGroupList = tBdAccountGroupMapper.selectList(lqw);
			List<AccountGroupVo> accountGroupVoList = tBdAccountGroupList.stream()
				.map(any -> BeanUtil.toBean(any, AccountGroupVo.class))
				.collect(Collectors.toList());
			accountTableVo.setList(accountGroupVoList);
			for (AccountGroupVo accountGroupVo : accountGroupVoList) {
				LambdaQueryWrapper<TBdAccountType> lqwAccountType = new LambdaQueryWrapper<>();
				Integer acctgroupId = accountGroupVo.getFAcctgroupId();
				lqwAccountType.eq(TBdAccountType::getFAcctTableId, acctTableId);
				lqwAccountType.eq(TBdAccountType::getFAcctGroupId, acctgroupId);
				List<TBdAccountType> tBdAccountTypeList = tBdAccountTypeMapper.selectList(lqwAccountType);
				List<AccountTypeVo> accountTypeVoList = tBdAccountTypeList.stream()
					.map(any -> BeanUtil.toBean(any, AccountTypeVo.class))
					.collect(Collectors.toList());
				accountGroupVo.setList(accountTypeVoList);
			}
		}
		return tBdAccountVoList;
	}


	@Override
	public Boolean updateByList(List<TBdAccountEditBo> list) {
		if (StringUtils.checkValNull(list) || list.size() < 1) {
			throw new CustomException("保存数据不能为空!", 1000);
		}
		Boolean result = tBdAccountTypeMapper.updateByList(list) > 0;
		if (!result) {
			throw new CustomException("保存数据失败!", 1000);
		}
		return result;
	}

	@Override
	public Boolean audit(Collection ids) {
		Date now = new Date();
		ids.stream().forEach(id -> {
			TBdAccount update = new TBdAccount();
			update.setFAuditDate(now);
			update.setFAcctId(Integer.parseInt(id.toString()));
			update.setFDocumentStatus(DataStatusEnum.AUDIT.getCode());
			updateById(update);
		});
		return true;
	}

	@Override
	public Boolean disable(DisableBo bo) {
		return null;
	}
}
