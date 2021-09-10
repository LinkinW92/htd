package com.skeqi.mes.service.chenj.srm.impl;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.api.feign.ISysUserServiceFeignClient;
import com.skeqi.common.core.domain.AjaxResult;
import com.skeqi.common.core.domain.entity.SysUser;
import com.skeqi.common.exception.CustomException;
import com.skeqi.common.utils.redis.RedisCache;
import com.skeqi.common.utils.time.CollectionUtil;
//import com.skeqi.manage.domain.SysUserRole;
import com.skeqi.mes.controller.chenj.srm.timer.SrmSupplierTimer;
import com.skeqi.mes.finals.SrmFinal;
import com.skeqi.mes.mapper.chenj.srm.*;
import com.skeqi.mes.mapper.gmg.UserDao;
import com.skeqi.mes.pojo.CMesUserT;
import com.skeqi.mes.pojo.chenj.srm.*;
import com.skeqi.mes.pojo.chenj.srm.kthree.KThreeSupplier;
import com.skeqi.mes.pojo.chenj.srm.kthree.KThreeSupplierReq;
import com.skeqi.mes.pojo.chenj.srm.kthree.KThreeSupplierResult;
import com.skeqi.mes.pojo.chenj.srm.req.*;
import com.skeqi.mes.pojo.chenj.srm.rsp.*;
import com.skeqi.mes.service.chenj.srm.CSrmSupplierService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.StringUtil;
import com.skeqi.mes.util.chenj.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmSupplierServiceImpl
 * @Description ${Description}
 */

@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class CSrmSupplierServiceImpl implements CSrmSupplierService {

	@Resource
	private CSrmSupplierMapper cSrmSupplierMapper;

	@Resource
	private CSrmLinkmanMapper cSrmLinkmanMapper;
	@Resource
	private CSrmFinanceMapper cSrmFinanceMapper;
	@Resource
	private CSrmBankMapper cSrmBankMapper;

	@Resource
	private CSrmSupplierChangeRecordMapper cSrmSupplierChangeRecordMapper;
	@Resource
	private CSrmLinkmanChangeRecordMapper cSrmLinkmanChangeRecordMapper;
	@Resource
	private CSrmFinanceChangeRecordMapper cSrmFinanceChangeRecordMapper;
	@Resource
	private CSrmBankChangeRecordMapper cSrmBankChangeRecordMapper;


	@Resource
	private UserDao userDao;

	@Resource
	private CSrmCompanyMapper cSrmCompanyMapper;

	@Resource
	private CSrmProductMapper cSrmProductMapper;

	@Resource
	private CSrmAccessoryMapper cSrmAccessoryMapper;


	@Autowired
	ISysUserServiceFeignClient iSysUserServiceFeignClient;
	//    @Resource
//    private SrmSupplierTimer srmSupplierTimer;
	@Autowired
	private RedisCache redisCache;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return cSrmSupplierMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(CSrmSupplier record) {
		return cSrmSupplierMapper.insert(record);
	}

	@Override
	public int insertOrUpdate(CSrmSupplier record) {
		return cSrmSupplierMapper.insertOrUpdate(record);
	}

	@Override
	public int insertOrUpdateSelective(CSrmSupplier record) {
		return cSrmSupplierMapper.insertOrUpdateSelective(record);
	}

	@Override
	public int insertSelective(CSrmSupplier record) {
		return cSrmSupplierMapper.insertSelective(record);
	}

	@Override
	public List<CSrmSupplier> selectByPrimaryKey(CSrmSupplier cSrmSupplier) {
		return cSrmSupplierMapper.selectByPrimaryKey(cSrmSupplier);
	}

	@Override
	public int updateByPrimaryKeySelective(CSrmSupplier record) {
		return cSrmSupplierMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(CSrmSupplier record) {
		return cSrmSupplierMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateBatch(List<CSrmSupplier> list) {
		return cSrmSupplierMapper.updateBatch(list);
	}

	@Override
	public int updateBatchSelective(List<CSrmSupplier> list) {
		return cSrmSupplierMapper.updateBatchSelective(list);
	}

	@Override
	public int batchInsert(List<CSrmSupplier> list) {
		return cSrmSupplierMapper.batchInsert(list);
	}

	@Override
	public CSrmSupplier checkSupplierCode(String supplierCode, String account) {
		return cSrmSupplierMapper.checkSupplierCode(supplierCode, null);
	}

	@Override
	public Rjson addSupplier(CSrmSupplierReq cSrmSupplierReq) {
		// 查询供应商账号是否存在
		CMesUserT user = userDao.findByName(cSrmSupplierReq.getAccount());
		if (null != user) {
			return Rjson.error("注册账号已存在");
		} else {
			// 校验供应商名称是否存在
			String supplierCode = "";
			Long roleId = 0L;
			CSrmSupplier cSrmSupplier = new CSrmSupplier();
			cSrmSupplier.setName(cSrmSupplierReq.getName());
			List<CSrmSupplier> selectByPrimaryKey = cSrmSupplierMapper.selectByPrimaryKey(cSrmSupplier);
			if (!CollectionUtils.isEmpty(selectByPrimaryKey)) {
				supplierCode = selectByPrimaryKey.get(0).getSupplierCode();
				cSrmSupplier.setSupplierCode(supplierCode);
				// 校验该公司是否已认证过
				CSrmCompany cSrmCompany = cSrmCompanyMapper.selectSupplierCode(selectByPrimaryKey.get(0).getSupplierCode());
				if (null != cSrmCompany) {
					if (("2").equals(cSrmCompany.getIsAuth())) {
						// 该供应商所属公司已完成认证
						// 设置已认证状态及权限
						cSrmSupplier.setStatus(3);
						roleId = Long.parseLong(CommonUtils.getRedisValue(redisCache, SrmFinal.SRM_AUTHENTICATED, "SRM已认证权限"));
					}
				} else {
					// 设置未认证权限
					roleId = Long.parseLong(CommonUtils.getRedisValue(redisCache, SrmFinal.SRM_UNVERIFIED, "SRM未认证权限"));


				}

			} else {
				// 获取最后一条数据
				CSrmSupplier finallyOneData = cSrmSupplierMapper.selectFinallyData();
				if (null == finallyOneData) {
					// 未找到数据，从最新一条开始
					cSrmSupplier.setSupplierCode("S" + 100000);
				} else {
					int supplierCodeNew = Integer.parseInt(finallyOneData.getSupplierCode().substring(1)) + 1;
					cSrmSupplier.setSupplierCode("S" + supplierCodeNew);
				}
				// 设置未认证权限
				roleId = Long.parseLong(CommonUtils.getRedisValue(redisCache, SrmFinal.SRM_UNVERIFIED, "SRM未认证权限"));
				BeanUtils.copyProperties(cSrmSupplierReq, cSrmSupplier);
				cSrmSupplier.setCreateTime(new Date());
				// 不存储账号、密码
				cSrmSupplier.setAccount("");
				cSrmSupplier.setPassword("");
				cSrmSupplierMapper.insertSelective(cSrmSupplier);
			}
			// 新增用户表
//            CMesUserTReq req = new CMesUserTReq();
//            req.setUserName(cSrmSupplierReq.getAccount());
//            req.setUserPwd(cSrmSupplierReq.getPassword());
//            req.setViewmode(1);
//            req.setRoleId(roleId);
//            // 0.冻结 1.正常
//            req.setStatus("0");
//            req.setSupplierCode(cSrmSupplier.getSupplierCode());
//            req.setName(cSrmSupplier.getContactPerson());
			SysUser sysUser = new SysUser();
			sysUser.setUserName(cSrmSupplierReq.getAccount());
			sysUser.setStatus("1");
			sysUser.setRoleIds(new Long[]{roleId});
			sysUser.setDeptId(Long.parseLong(CommonUtils.getRedisValue(redisCache, SrmFinal.SUPPLIER_DEPARTMENT, "SRM供应商部门id")));
			sysUser.setUserCode(cSrmSupplier.getSupplierCode());
			sysUser.setRankName(cSrmSupplier.getContactPerson());
			sysUser.setPwd(cSrmSupplierReq.getPassword());
			sysUser.setSupplierCode(cSrmSupplier.getSupplierCode());
			AjaxResult result = iSysUserServiceFeignClient.addUserInfo(sysUser);
			if (HttpStatus.OK.value() != result.getCode()) {
				throw new CustomException(result.getMsg());
			}
			log.info("【供应商注册出参】[{}]", JSONUtil.toJsonStr(result));
			// 联系人表
			CSrmLinkman linkman = new CSrmLinkman();
			linkman.setName(cSrmSupplierReq.getContactPerson());
			// 校验联系人名称是否已经存在
			if (null == cSrmLinkmanMapper.selectByPrimaryKeyList(linkman)) {
				linkman.setPhone(cSrmSupplierReq.getContactNumber());
				linkman.setEmail(cSrmSupplierReq.getContactEmail());
				linkman.setSupplierCode(cSrmSupplier.getSupplierCode());
				linkman.setCreateTime(new Date());
				cSrmLinkmanMapper.insertSelective(linkman);
			}

			CSrmSupplierRsp cSrmSupplierRsp = new CSrmSupplierRsp();
			cSrmSupplierRsp.setSupplierCode(cSrmSupplier.getSupplierCode());
			return Rjson.success("注册成功", cSrmSupplierRsp);
		}
	}

	@Override
	public Rjson addEnterprise(CSrmEnterpriseReq cSrmEnterpriseReq) throws ParseException {
		// 查询供应商代码是否存在
		CSrmSupplier cSrmSupplier = cSrmSupplierMapper.selectSupplierCode(cSrmEnterpriseReq.getSupplierCode());
		if (cSrmSupplier == null) {
			return Rjson.error("供应商代码不存在");
		} else {
			// 校验公司编码是否存在
			CSrmCompany company1 = cSrmCompanyMapper.selectSupplierCode(cSrmEnterpriseReq.getSupplierCode());
			if (null != company1) {
				// 校验是否已认证过
				if (("2").equals(company1.getIsAuth())) {
					return Rjson.error("已认证过了,请勿重复认证");
				}
				if (cSrmSupplier.getStatus() == 2) {
					return Rjson.error("已处于认证中，请耐心等待管理员审批");
				} else if (cSrmSupplier.getStatus() == 3) {
					return Rjson.error("已认证过了,请勿重复认证");
				}
			}

			CSrmCompany cSrmCompany = null;
			// 校验供应商代码是否存在
			if (null == cSrmCompanyMapper.selectSupplierCode(cSrmEnterpriseReq.getSupplierCode())) {
				// 新增公司基础信息
				cSrmCompany = new CSrmCompany();
				if (StringUtil.eqNu(cSrmEnterpriseReq.getRegisterDate())) {
					cSrmCompany.setRegisterDate(new SimpleDateFormat("yyyy-MM-dd").parse(cSrmEnterpriseReq.getRegisterDate()));
				}
				BeanUtils.copyProperties(cSrmEnterpriseReq, cSrmCompany);
				cSrmCompany.setCreateTime(new Date());
				cSrmCompanyMapper.insertSelective(cSrmCompany);
			} else {
				// 更新公司基础信息
				cSrmCompany = new CSrmCompany();
				if (StringUtil.eqNu(cSrmEnterpriseReq.getRegisterDate())) {
					cSrmCompany.setRegisterDate(new SimpleDateFormat("yyyy-MM-dd").parse(cSrmEnterpriseReq.getRegisterDate()));
				}
				BeanUtils.copyProperties(cSrmEnterpriseReq, cSrmCompany);
				cSrmCompany.setUpdateTime(new Date());
				cSrmCompanyMapper.updateByPrimaryKeySelective(cSrmCompany);
			}

			// 添加主要产品\服务信息
			CSrmProduct cSrmProduct = new CSrmProduct();
			BeanUtils.copyProperties(cSrmEnterpriseReq, cSrmProduct);
			cSrmProduct.setCreateTime(new Date());
			cSrmProductMapper.insertSelective(cSrmProduct);


			// 添加联系人信息
			if (!CollectionUtils.isEmpty(cSrmEnterpriseReq.getLinkManList())) {
				CSrmLinkman cSrmLinkman = null;
				for (CSrmLinkmanChangeRecordReq item : cSrmEnterpriseReq.getLinkManList()) {
					cSrmLinkman = new CSrmLinkman();
					BeanUtils.copyProperties(item, cSrmLinkman);
					cSrmLinkman.setCreateTime(new Date());
					cSrmLinkman.setSupplierCode(cSrmEnterpriseReq.getSupplierCode());
					cSrmLinkmanMapper.insertSelective(cSrmLinkman);
				}
			}
			// 添加财务信息
			if (!CollectionUtils.isEmpty(cSrmEnterpriseReq.getFinanceList())) {
				CSrmFinance cSrmFinance = null;
				for (CSrmFinanceChangeRecordReq item : cSrmEnterpriseReq.getFinanceList()) {
					cSrmFinance = new CSrmFinance();
					BeanUtils.copyProperties(item, cSrmFinance);
					cSrmFinance.setCreateTime(new Date());
					cSrmFinance.setSupplierCode(cSrmEnterpriseReq.getSupplierCode());
					cSrmFinanceMapper.insertSelective(cSrmFinance);
				}
			}

			// 添加银行\交易信息
			if (!CollectionUtils.isEmpty(cSrmEnterpriseReq.getBankList())) {
				CSrmBank cSrmBank = null;
				for (CSrmBankChangeRecordReq item : cSrmEnterpriseReq.getBankList()) {
					cSrmBank = new CSrmBank();
					BeanUtils.copyProperties(item, cSrmBank);
					cSrmBank.setCreateTime(new Date());
					cSrmBank.setSupplierCode(cSrmEnterpriseReq.getSupplierCode());
					cSrmBankMapper.insertSelective(cSrmBank);
				}
			}

			// 添加附件信息  // 等前端需求时再松开
//            CSrmAccessory cSrmAccessory = new CSrmAccessory();
//            BeanUtils.copyProperties(cSrmEnterpriseReq, cSrmAccessory);
//            cSrmAccessory.setCreateTime(new Date());
//            cSrmAccessory.setCompanyCode(cSrmCompany.getCompanyCode());
//            cSrmAccessoryMapper.insertSelective(cSrmAccessory);

			// 修改状态为认证中
			int id = cSrmSupplier.getId();
			// 存储供应商名称
			String name = cSrmSupplier.getName();
			cSrmSupplier = new CSrmSupplier();
			cSrmSupplier.setSupplierCode(cSrmEnterpriseReq.getSupplierCode());
			cSrmSupplier.setId(id);
			cSrmSupplier.setStatus(2);
			// 设置提交时间
			cSrmSupplier.setUpdateTime(new Date());
			cSrmSupplierMapper.updateByPrimaryKeySelective(cSrmSupplier);

			CSrmEnterpriseRsp cSrmEnterpriseRsp = new CSrmEnterpriseRsp();
//            CSrmCompany srmCompany = new CSrmCompany();
//            srmCompany.setSupplierCode(cSrmEnterpriseReq.getSupplierCode());
//            CSrmCompany company = cSrmCompanyMapper.selectByPrimaryKey(srmCompany);
			cSrmEnterpriseRsp.setSupplierCode(cSrmEnterpriseReq.getSupplierCode());
			cSrmEnterpriseRsp.setEnterprise(name);
			cSrmEnterpriseRsp.setSubmitTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			cSrmEnterpriseRsp.setStatus(cSrmSupplier.getStatus());
			return Rjson.success("您已提交认证申请，请耐心等待审批", cSrmEnterpriseRsp);
		}
	}

	@Override
	public Rjson findSupplierEnterpriseInfo(CSrmEnterpriseReq cSrmEnterpriseReq) {
		PageHelper.startPage(cSrmEnterpriseReq.getPageNum(), cSrmEnterpriseReq.getPageSize());
		return Rjson.success(new PageInfo<>(cSrmSupplierMapper.selectSupplierEnterpriseInfo(cSrmEnterpriseReq)));
	}


	@Override
	public Rjson findSupplierInfo(CSrmEnterpriseReq cSrmEnterpriseReq) {
		CSrmSupplier cSrmSupplier = new CSrmSupplier();
		BeanUtils.copyProperties(cSrmEnterpriseReq, cSrmSupplier);
		PageHelper.startPage(cSrmEnterpriseReq.getPageNum(), cSrmEnterpriseReq.getPageSize());
		return Rjson.success(new PageInfo<>(cSrmSupplierMapper.selectSupplierInfo(cSrmSupplier)));
	}

	@Override
	public Rjson findEnterpriseInfo(CSrmEnterpriseReq cSrmEnterpriseReq) {
		CSrmSupplierChangeRecordRsp rsp = new CSrmSupplierChangeRecordRsp();
		// 查询公司信息
		CSrmSupplierChangeRecordRsp changeRecordRsp = cSrmCompanyMapper.selectByPrimaryKeyList(cSrmEnterpriseReq);
		if (changeRecordRsp != null) {
			BeanUtils.copyProperties(changeRecordRsp, rsp);
			// 获取企业名称
			CSrmSupplier cSrmSupplier = new CSrmSupplier();
			cSrmSupplier.setSupplierCode(cSrmEnterpriseReq.getSupplierCode());
			List<CSrmSupplierInfoRsp> cSrmSuppliers = cSrmSupplierMapper.selectSupplierInfo(cSrmSupplier);
			rsp.setCompanyName(cSrmSuppliers.get(0).getName());
		} else {
			return Rjson.error("获取认证信息失败");
		}
		// 查询主要产品\服务信息
		CSrmSupplierChangeRecordRsp cSrmSupplierChangeRecordRsp = cSrmProductMapper.selectByPrimaryKeyList(cSrmEnterpriseReq);
		if (null != cSrmSupplierChangeRecordRsp) {
			// 相同POJO结构使用BeanUtils 会进行覆盖操作  所以产品/服务  手动进行赋值
			rsp.setBusinessNature(cSrmSupplierChangeRecordRsp.getBusinessNature());
			rsp.setProductsOrServices(cSrmSupplierChangeRecordRsp.getProductsOrServices());
			rsp.setClient(cSrmSupplierChangeRecordRsp.getClient());
		}
		// 查询联系人信息
		rsp.setLinkManList(cSrmLinkmanMapper.selectByPrimaryKeyListD(cSrmEnterpriseReq));
		// 查询财务信息
		rsp.setFinanceList(cSrmFinanceMapper.selectByPrimaryKeyList(cSrmEnterpriseReq));
		// 查询银行\交易信息
		rsp.setBankList(cSrmBankMapper.selectByPrimaryKeyList(cSrmEnterpriseReq));
		return Rjson.success(rsp);
	}

	@Override
	public Rjson findSupplierStatus(CSrmEnterpriseReq cSrmEnterpriseReq) {
		return Rjson.success(cSrmSupplierMapper.findSupplierStatus(cSrmEnterpriseReq));
	}

	@Override
	public Rjson findSupplierAuthInfo(CSrmEnterpriseReq cSrmEnterpriseReq) {
		CSrmEnterpriseRsp cSrmEnterpriseRsp = new CSrmEnterpriseRsp();
		CSrmSupplier cSrmSupplier = new CSrmSupplier();
		cSrmSupplier.setSupplierCode(cSrmEnterpriseReq.getSupplierCode());
		// 获取供应商创建时间
		List<CSrmSupplierInfoRsp> cSrmSuppliers = cSrmSupplierMapper.selectSupplierInfo(cSrmSupplier);
//        CSrmCompany cSrmCompany = new CSrmCompany();
//        cSrmCompany.setCompanyCode(cSrmEnterpriseReq.getCompanyCode());
		// 获取公司名称
//        CSrmCompany company = cSrmCompanyMapper.selectByPrimaryKey(cSrmCompany);
//        cSrmEnterpriseRsp.setEnterprise(company.getCompanyName());
		cSrmEnterpriseRsp.setSupplierCode(cSrmEnterpriseReq.getSupplierCode());
		cSrmEnterpriseRsp.setEnterprise(cSrmSuppliers.get(0).getName());
		if (!StringUtil.eqNu(cSrmSuppliers.get(0).getUpdateTime())) {
			cSrmEnterpriseRsp.setSubmitTime(cSrmSuppliers.get(0).getCreateTime());
		} else {
			cSrmEnterpriseRsp.setSubmitTime(cSrmSuppliers.get(0).getUpdateTime());
		}
		cSrmEnterpriseRsp.setStatus(Integer.parseInt(cSrmEnterpriseReq.getStatus()));
		return Rjson.success("您已提交认证申请，请耐心等待审批", cSrmEnterpriseRsp);
	}

	@Override
	public Rjson supplierAudit(CSrmEnterpriseReq cSrmEnterpriseReq, SrmSupplierTimer srmSupplierTimer) throws Exception {
		/**
		 * 执行逻辑：
		 * 校验供应商代码是否存在
		 * 传入状态3 代表认证操作  4 代表认证失败操作  对应修改企业信息变更表数据  2 代表变更成功 3 代表变更失败
		 * 校验企业信息变更表中数据 存在则取出并更新对应表，不存在则更新供应商表数据为已认证并开发认证权限
		 * 最后更新企业信息变更表中数据为变更成功或变更失败
		 */
		// 查询供应商代码是否存在
		CSrmSupplier cSrmSupplier = cSrmSupplierMapper.selectSupplierCode(cSrmEnterpriseReq.getSupplierCode());
		if (cSrmSupplier == null) {
			return Rjson.error("供应商代码不存在");
		} else {
			// 存储供应商信息，下方推送至K3需要
			CSrmSupplier cSrmSupplierKThree = new CSrmSupplier();
			BeanUtils.copyProperties(cSrmSupplier, cSrmSupplierKThree);
			// 控制供应商变更记录表中状态
			String status = "";
			// 存储公司名称
			String companyName = "";
			// 审批通过
			if (("3").equals(cSrmEnterpriseReq.getStatus())) {
				// 变更中的状态
				cSrmEnterpriseReq.setStatus("1");
				status = "2";
				// 获取企业信息变更表中数据
				CSrmSupplierChangeRecordReq recordReq = new CSrmSupplierChangeRecordReq();
				BeanUtils.copyProperties(cSrmEnterpriseReq, recordReq);
				// 传入 status 1  查询变更中的
				// 获取公司信息、产品/服务信息数据
				CSrmSupplierChangeRecordRsp changeRecords = cSrmSupplierChangeRecordMapper.selectChangeRecordD(recordReq);
				// 手动变更过数据,就有数据
				if (changeRecords != null) {
					// 更新公司基础信息
					CSrmCompany cSrmCompany = new CSrmCompany();
					BeanUtils.copyProperties(changeRecords, cSrmCompany);
					cSrmCompanyMapper.updateByPrimaryKeySelective(cSrmCompany);
					companyName = changeRecords.getCompanyName();
					// 更新产品基础信息
					CSrmProduct cSrmProduct = new CSrmProduct();
					BeanUtils.copyProperties(changeRecords, cSrmProduct);
					cSrmProductMapper.updateByPrimaryKeySelective(cSrmProduct);
					// 删除联系人信息旧数据
					CSrmLinkman cSrmLinkman = new CSrmLinkman();
					cSrmLinkman.setSupplierCode(changeRecords.getSupplierCode());
					cSrmLinkmanMapper.delData(cSrmLinkman);
					// 删除财务信息旧数据
					CSrmFinance finance = new CSrmFinance();
					finance.setSupplierCode(changeRecords.getSupplierCode());
					cSrmFinanceMapper.delData(finance);
					// 删除银行信息旧数据
					CSrmBank cSrmBank = new CSrmBank();
					cSrmBank.setSupplierCode(changeRecords.getSupplierCode());
					cSrmBankMapper.delData(cSrmBank);
					/**
					 * c_srm_supplier_change_record  // 公司信息、产品/服务信息变更
					 * c_srm_linkman_change_record  // 联系人信息变更
					 * c_srm_finance_change_record  // 财务信息变更
					 * c_srm_bank_change_record // 银行信息变更
					 */
					// 获取联系人信息变更中的数据
					CSrmSupplierChangeRecordReq srmSupplierChangeRecordReq = new CSrmSupplierChangeRecordReq();
					BeanUtils.copyProperties(cSrmEnterpriseReq, srmSupplierChangeRecordReq);
					List<CSrmLinkmanChangeRecordRsp> cSrmLinkmanChangeRecordRsps = cSrmLinkmanChangeRecordMapper.selectByPrimaryKeyList(srmSupplierChangeRecordReq);
					// 获取财务信息变更中的数据
					CSrmSupplierChangeRecordReq recordReq1 = new CSrmSupplierChangeRecordReq();
					BeanUtils.copyProperties(cSrmEnterpriseReq, recordReq1);
					List<CSrmFinanceChangeRecordRsp> cSrmFinanceChangeRecordRsps = cSrmFinanceChangeRecordMapper.selectByPrimaryKeyList(recordReq1);
					// 获取银行信息变更中的数据
					CSrmSupplierChangeRecordReq cSrmSupplierChangeRecordReq = new CSrmSupplierChangeRecordReq();
					BeanUtils.copyProperties(cSrmEnterpriseReq, cSrmSupplierChangeRecordReq);
					List<CSrmBankChangeRecordRsp> cSrmBankChangeRecordRsps = cSrmBankChangeRecordMapper.selectByPrimaryKeyList(cSrmSupplierChangeRecordReq);
					// 新增联系人信息
					if (!CollectionUtils.isEmpty(cSrmLinkmanChangeRecordRsps)) {
						cSrmLinkmanMapper.batchInsertData(cSrmLinkmanChangeRecordRsps);
					}
					if (!CollectionUtils.isEmpty(cSrmFinanceChangeRecordRsps)) {
						// 新增财务信息
						cSrmFinanceMapper.batchInsertData(cSrmFinanceChangeRecordRsps);
					}
					if (!CollectionUtils.isEmpty(cSrmBankChangeRecordRsps)) {
						// 新增银行信息
						cSrmBankMapper.batchInsertData(cSrmBankChangeRecordRsps);
					}

				}
				// 更新认证状态为已认证
				cSrmSupplier = new CSrmSupplier();
				cSrmSupplier.setSupplierCode(cSrmEnterpriseReq.getSupplierCode());
				// 第一次注册 值为空
				if (StringUtil.eqNu(companyName)) {
					cSrmSupplier.setName(companyName);
				}
				cSrmSupplier.setStatus(3);
				cSrmSupplier.setUpdateTime(new Date());

				// 无论成功失败 回退权限
				cSrmSupplierMapper.updateByPrimaryKeySelective(cSrmSupplier);

				// 校验目前状态是否处于未认证
				List<SysUser> sysUserList = iSysUserServiceFeignClient.findSysUserList(cSrmEnterpriseReq.getSupplierCode());
				if (CollectionUtil.isNotEmpty(sysUserList)) {
					// Redis中缓存SRM未认证权限 权限id
					Long roleId;
					// 数据库中权限id
					Long userRoleId;
					log.info("【查询用户列表出参】[{}]", JSONUtil.toJsonStr(sysUserList));
					roleId = Long.parseLong(CommonUtils.getRedisValue(redisCache, SrmFinal.SRM_UNVERIFIED, "SRM未认证权限"));
					// 根据用户id获取用户权限id
					userRoleId = iSysUserServiceFeignClient.findSysUserRole(Objects.requireNonNull(sysUserList.stream().findFirst().orElse(null)).getUserId());
					log.info("【查询用户权限id出参】[{}]", JSONUtil.toJsonStr(userRoleId));
					if (roleId.equals(userRoleId)) {
						// 授权SRM菜单权限
						// 根据供应商代码获取用户表id // sys_user
						List<Map<String, Object>> list = new ArrayList<>();
						sysUserList.forEach(item -> {
							Map<String, Object> map = new HashMap<>();
							map.put("userId", item.getUserId());
							map.put("roleId", Long.parseLong(CommonUtils.getRedisValue(redisCache, SrmFinal.SRM_AUTHENTICATED, "SRM已认证权限")));
							list.add(map);
						});

						// 根据用户id批量更新用户权限  // sys_user_role
						AjaxResult result = iSysUserServiceFeignClient.batchUpdateRole(JSONObject.toJSONString(list));
						if (HttpStatus.OK.value() != result.getCode()) {
							throw new CustomException(result.getMsg());
						}
						log.info("【批量修改用户权限id出参】[{}]", JSONUtil.toJsonStr(result));
					}

					// 更改用户表权限
//					userDao.updateByPrimaryKeySelective(req);
				}

				// 删除联系人信息变更中的数据
				CSrmLinkmanChangeRecord cSrmLinkmanChangeRecord = new CSrmLinkmanChangeRecord();
				BeanUtils.copyProperties(cSrmEnterpriseReq, cSrmLinkmanChangeRecord);
				cSrmLinkmanChangeRecord.setStatus("1");
				cSrmLinkmanChangeRecordMapper.delData(cSrmLinkmanChangeRecord);
				// 删除财务信息变更中的数据
				CSrmFinanceChangeRecord changeRecord = new CSrmFinanceChangeRecord();
				BeanUtils.copyProperties(cSrmEnterpriseReq, changeRecord);
				changeRecord.setStatus("1");
				cSrmFinanceChangeRecordMapper.delData(changeRecord);
				// 删除银行信息变更中的数据
				CSrmBankChangeRecord cSrmBankChangeRecord = new CSrmBankChangeRecord();
				BeanUtils.copyProperties(cSrmEnterpriseReq, cSrmBankChangeRecord);
				cSrmBankChangeRecord.setStatus("1");
				cSrmBankChangeRecordMapper.delData(cSrmBankChangeRecord);

				// 审批拒绝  4:认证失败 5:企业信息变更失败
			} else if (("4").equals(cSrmEnterpriseReq.getStatus()) || ("5").equals(cSrmEnterpriseReq.getStatus())) {
				status = "3";
				// 未做更改操作
				// 更新供应商表数据为已认证
				cSrmSupplier = new CSrmSupplier();
				// 更新认证状态
				cSrmSupplier.setSupplierCode(cSrmEnterpriseReq.getSupplierCode());
				// 处理供应商二次认证逻辑控制
				if (("5").equals(cSrmEnterpriseReq.getStatus())) {

					// Redis中缓存SRM未认证权限 权限id
					Long roleId;
					// 数据库中权限id
					Long userRoleId;
					// 校验目前状态是否处于未认证
					List<SysUser> sysUserList = iSysUserServiceFeignClient.findSysUserList(cSrmEnterpriseReq.getSupplierCode());
					if (CollectionUtil.isNotEmpty(sysUserList)) {
						log.info("【查询用户列表出参】[{}]", JSONUtil.toJsonStr(sysUserList));
						roleId = Long.parseLong(CommonUtils.getRedisValue(redisCache, SrmFinal.SRM_UNVERIFIED, "SRM未认证权限"));
						// 根据用户id获取用户权限id
						userRoleId = iSysUserServiceFeignClient.findSysUserRole(Objects.requireNonNull(sysUserList.stream().findFirst().orElse(null)).getUserId());
						log.info("【查询用户权限id出参】[{}]", JSONUtil.toJsonStr(userRoleId));
						if ((roleId).equals(userRoleId)) {
							cSrmEnterpriseReq.setStatus("4");
						}
					}


				}
				// 4.认证失败 5.企业信息变更失败
				cSrmSupplier.setStatus(Integer.parseInt(cSrmEnterpriseReq.getStatus()));
				cSrmSupplier.setUpdateTime(new Date());
				cSrmSupplierMapper.updateByPrimaryKeySelective(cSrmSupplier);
			}

			CSrmCompany cSrmCompany = new CSrmCompany();
			cSrmCompany.setIsAuth("2");
			cSrmCompany.setSupplierCode(cSrmEnterpriseReq.getSupplierCode());
			cSrmCompany.setUpdateTime(new Date());
			cSrmCompanyMapper.updateByPrimaryKeySelective(cSrmCompany);


			CSrmSupplierChangeRecord cSrmSupplierChangeRecord = new CSrmSupplierChangeRecord();
			if (status.equals("2")) {
				// 更新公司基础信息\产品服务信息变更表
				cSrmSupplierChangeRecord.setSupplierCode(cSrmEnterpriseReq.getSupplierCode());
				cSrmSupplierChangeRecord.setCompanyCode(cSrmEnterpriseReq.getCompanyCode());
				cSrmSupplierChangeRecord.setStatus(status);
				// true 设置已删除状态
				cSrmSupplierChangeRecord.setIsDelete(true);
				cSrmSupplierChangeRecord.setUpdateTime(new Date());
				cSrmSupplierChangeRecordMapper.updateByPrimaryKeySelective(cSrmSupplierChangeRecord);

				// 推送已完成认证的供应商信息
				// 校验是否需要将供应商推送至K3
				if (StringUtil.eqNu(cSrmEnterpriseReq.getServiceType()) && cSrmEnterpriseReq.getPush()) {
					if (CommonUtils.getRedisValue(redisCache, SrmFinal.K_THREE_SERVICE_TYPE, "推送K3服务凭证").equals(String.valueOf(cSrmEnterpriseReq.getServiceType()))) {
						// 推送至K3
						log.info("----开始推送供应商至K3注册----");
						// 校验供应商是否已经存在
						// 封装请求参数
						Map<String, Object> map = new HashMap<>();
						map.put("jktype", "Supplier");
						map.put("method", "view");
						map.put("filter", "ID='" + cSrmEnterpriseReq.getSupplierCode() + "' and FName='" + cSrmSupplierKThree.getName() + "'");
						KThreeSupplierResult supplierResult = JSONObject.parseObject(srmSupplierTimer.sendPost(map), KThreeSupplierResult.class);
						log.info("【校验供应商是否已存在入参】[{}]", JSONUtil.toJsonStr(map.toString()));
						if (!CollectionUtils.isEmpty(supplierResult.getData())) {
							log.info("K3响应结果----供应商已存在，执行更新----");
							// 更新
							// 封装请求参数
							List<KThreeSupplierReq> updateList = new ArrayList<>();
							KThreeSupplierReq threeSupplierReq = new KThreeSupplierReq();
							// 赋值供应商数据
							KThreeSupplier kThreeSupplier = new KThreeSupplier();
							BeanUtils.copyProperties(cSrmSupplierKThree, kThreeSupplier);
							BeanUtils.copyProperties(kThreeSupplier, threeSupplierReq);
							// 0.正常 1.禁用
							threeSupplierReq.setFDeleted(0);
							updateList.add(threeSupplierReq);
							map = new HashMap<>();
							map.put("jktype", "Supplier");
							map.put("method", "modify");
							map.put("data", updateList);
							supplierResult = JSONObject.parseObject(srmSupplierTimer.sendPost(map), KThreeSupplierResult.class);
							log.info("【推送供应商入参】[{}]", JSONUtil.toJsonStr(map.toString()));
							if (SrmFinal.SUCESS.equals(supplierResult.getStatus())) {
								log.info("【推送供应商出参】[{}]", JSONUtil.toJsonStr(supplierResult.toString()));
							} else {
								log.error("【推送供应商出参】[{}]", JSONUtil.toJsonStr(supplierResult.toString()));
								throw new Exception("K3服务异常：" + supplierResult.getMessage());
							}

						} else {
							log.info("K3响应结果----供应商不存在，执行新增----");
							// 新增
							// 封装请求参数
							List<KThreeSupplierReq> addList = new ArrayList<>();
							KThreeSupplierReq threeSupplierReq = new KThreeSupplierReq();
							// 赋值供应商数据
							KThreeSupplier kThreeSupplier = new KThreeSupplier();
							BeanUtils.copyProperties(cSrmSupplierKThree, kThreeSupplier);
							BeanUtils.copyProperties(kThreeSupplier, threeSupplierReq);
							// 0.正常 1.禁用
							threeSupplierReq.setFDeleted(0);
							addList.add(threeSupplierReq);
							map = new HashMap<>();
							map.put("jktype", "Supplier");
							map.put("method", "add");
							map.put("data", addList);
							log.info("【推送供应商入参】[{}]", JSONUtil.toJsonStr(map.toString()));
							supplierResult = JSONObject.parseObject(srmSupplierTimer.sendPost(map), KThreeSupplierResult.class);
							if (SrmFinal.SUCESS.equals(supplierResult.getStatus())) {
								log.info("【推送供应商出参】[{}]", JSONUtil.toJsonStr(supplierResult.toString()));
							} else {
								log.error("【推送供应商出参】[{}]", JSONUtil.toJsonStr(supplierResult.toString()));
								throw new Exception("K3服务异常：" + supplierResult.getMessage());
							}
						}
					}
				}
			}
		}
		return Rjson.success("审核成功", null);
	}


	@Override
	public Integer updateTokenCreateTimeOrToken(String tokenCreateTime, String userName, String token) {
		return cSrmSupplierMapper.updateTokenCreateTimeOrToken(tokenCreateTime, userName, token);
	}

	@Override
	public CSrmSupplier findByToken(String token) {
		return cSrmSupplierMapper.findByToken(token);
	}

	@Override
	public Rjson findSupplierManagement(CSrmEnterpriseReq req) {
		PageHelper.startPage(req.getPageNum(), req.getPageSize());
		return Rjson.success(new PageInfo<>(cSrmSupplierMapper.findSupplierManagement(req)));

	}

	@Override
	public Rjson selectSupplierList(CSrmSupplierHReqs req) {
		PageHelper.startPage(req.getPageNum(), req.getPageSize());
		return Rjson.success(new PageInfo<>(cSrmSupplierMapper.selectSupplierList(req)));
	}
}





