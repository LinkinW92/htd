package com.skeqi.mes.service.chenj.srm.impl;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.skeqi.api.feign.ISysUserServiceFeignClient;
import com.skeqi.common.core.domain.AjaxResult;
import com.skeqi.common.core.domain.entity.SysUser;
import com.skeqi.common.exception.CustomException;
import com.skeqi.common.utils.redis.RedisCache;
import com.skeqi.mes.controller.chenj.srm.timer.SrmSupplierTimer;
import com.skeqi.mes.finals.SrmFinal;
import com.skeqi.mes.mapper.chenj.srm.CSrmSupplierHMapper;
import com.skeqi.mes.mapper.chenj.srm.CSrmSupplierMapper;
import com.skeqi.mes.mapper.chenj.srm.CSrmSupplierRMapper;
import com.skeqi.mes.mapper.gmg.UserDao;
import com.skeqi.mes.pojo.chenj.srm.CSrmSupplier;
import com.skeqi.mes.pojo.chenj.srm.CSrmSupplierH;
import com.skeqi.mes.pojo.chenj.srm.CSrmSupplierR;
import com.skeqi.mes.pojo.chenj.srm.kthree.KThreeSupplierReq;
import com.skeqi.mes.pojo.chenj.srm.kthree.KThreeSupplierResult;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmSupplierHRDelReq;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmSupplierHRReq;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmSupplierHReq;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmSupplierRReq;
import com.skeqi.mes.pojo.chenj.srm.rsp.CSrmSupplierHRRsps;
import com.skeqi.mes.service.chenj.srm.CSrmSupplierHService;
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
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmSupplierHServiceImpl
 * @Description ${Description}
 */

@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class CSrmSupplierHServiceImpl implements CSrmSupplierHService {

	@Resource
	private CSrmSupplierHMapper cSrmSupplierHMapper;

	@Resource
	private CSrmSupplierRMapper cSrmSupplierRMapper;

	@Resource
	private CSrmSupplierMapper cSrmSupplierMapper;

	@Resource
	UserDao userDao;

	@Autowired
	ISysUserServiceFeignClient iSysUserServiceFeignClient;

	@Autowired
	private RedisCache redisCache;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return cSrmSupplierHMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(CSrmSupplierH record) {
		return cSrmSupplierHMapper.insert(record);
	}

	@Override
	public int insertOrUpdate(CSrmSupplierH record) {
		return cSrmSupplierHMapper.insertOrUpdate(record);
	}

	@Override
	public int insertOrUpdateSelective(CSrmSupplierH record) {
		return cSrmSupplierHMapper.insertOrUpdateSelective(record);
	}

	@Override
	public int insertSelective(CSrmSupplierH record) {
		return cSrmSupplierHMapper.insertSelective(record);
	}

	@Override
	public CSrmSupplierH selectByPrimaryKey(CSrmSupplierH record) {
		return cSrmSupplierHMapper.selectByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKeySelective(CSrmSupplierH record) {
		return cSrmSupplierHMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(CSrmSupplierH record) {
		return cSrmSupplierHMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateBatch(List<CSrmSupplierH> list) {
		return cSrmSupplierHMapper.updateBatch(list);
	}

	@Override
	public int updateBatchSelective(List<CSrmSupplierH> list) {
		return cSrmSupplierHMapper.updateBatchSelective(list);
	}

	@Override
	public int batchInsert(List<CSrmSupplierH> list) {
		return cSrmSupplierHMapper.batchInsert(list);
	}

	@Override
	public Rjson createAnApplicationForm(CSrmSupplierHReq cSrmSupplierHReq, SrmSupplierTimer srmSupplierTimer) throws Exception {
		// 查询供应商代码是否存在
		CSrmSupplier cSrmSupplier = cSrmSupplierMapper.selectSupplierCode(cSrmSupplierHReq.getSupplierCode());
		if (cSrmSupplier == null) {
			return Rjson.error("供应商代码不存在");
		} else {
			// 查询供应商升降级申请头表中是否已处于申请中
//            CSrmSupplierH cSrmSupplierH = cSrmSupplierHMapper.selectSupplierCode(cSrmSupplierHReq.getSupplierCode(), "2");
//            if (null != cSrmSupplierH) {
//                return Rjson.error("申请单已处于申请中，请耐心等待管理员审核");
//            } else {
			// 存储供应商信息，下方推送至K3需要
			CSrmSupplier cSrmSupplierKThree = new CSrmSupplier();
			BeanUtils.copyProperties(cSrmSupplier, cSrmSupplierKThree);
			CSrmSupplierH cSrmSupplierH = new CSrmSupplierH();
			if (("1").equals(cSrmSupplierHReq.getOperationSign())) {
				// 获取最后一条数据
				CSrmSupplierH selectFinallyData = cSrmSupplierHMapper.selectFinallyData();
				String yyyyMMdd = new SimpleDateFormat("yyyyMMdd").format(new Date());
				if (null == selectFinallyData) {
					// 未找到数据，从最新一条开始
					cSrmSupplierHReq.setRequestCode("SUP" + yyyyMMdd + 100);
				} else {
					int requestCode = Integer.parseInt(selectFinallyData.getRequestCode().substring(11)) + 1;
					cSrmSupplierHReq.setRequestCode("SUP" + yyyyMMdd + requestCode);
				}
				// 新增供应商升降级申请头表
				BeanUtils.copyProperties(cSrmSupplierHReq, cSrmSupplierH);
				cSrmSupplierH.setCreateTime(new Date());
				cSrmSupplierHMapper.insertSelective(cSrmSupplierH);
				// 新增供应商升降级申请行表
				if (!CollectionUtils.isEmpty(cSrmSupplierHReq.getReqList())) {
					for (CSrmSupplierRReq req : cSrmSupplierHReq.getReqList()) {
						// 生成行号
						CSrmSupplierR cSrmSupplierR = cSrmSupplierRMapper.selectFinallyData(cSrmSupplierHReq.getRequestCode());
						if (cSrmSupplierR == null) {
							req.setLineNumber("1");
						} else {
							int lineItemNo = Integer.parseInt(cSrmSupplierR.getLineNumber()) + 1;
							req.setLineNumber(String.valueOf(lineItemNo));
						}
						req.setRequestCode(cSrmSupplierHReq.getRequestCode());
						cSrmSupplierR = new CSrmSupplierR();
						BeanUtils.copyProperties(req, cSrmSupplierR);
						cSrmSupplierR.setCreateTime(new Date());
						cSrmSupplierRMapper.insertSelective(cSrmSupplierR);
					}
				}

				return Rjson.success("创建成功", cSrmSupplierHReq);

			} else if (("2").equals(cSrmSupplierHReq.getOperationSign())) {

				// 更新供应商升降级申请头表
				BeanUtils.copyProperties(cSrmSupplierHReq, cSrmSupplierH);

				cSrmSupplierH.setUpdateTime(new Date());
				cSrmSupplierHMapper.updateByPrimaryKeySelective(cSrmSupplierH);
				// 更新供应商升降级申请行表
				if (!CollectionUtils.isEmpty(cSrmSupplierHReq.getReqList())) {
					for (CSrmSupplierRReq req : cSrmSupplierHReq.getReqList()) {
						CSrmSupplierR cSrmSupplierR = null;
						// 存储申请单号
						req.setRequestCode(cSrmSupplierHReq.getRequestCode());
						if (!StringUtil.eqNu(req.getLineNumber())) {
							// 生成行号
							cSrmSupplierR = cSrmSupplierRMapper.selectFinallyData(cSrmSupplierHReq.getRequestCode());
							if (cSrmSupplierR == null) {
								req.setLineNumber("1");
							} else {
								int lineItemNo = Integer.parseInt(cSrmSupplierR.getLineNumber()) + 1;
								req.setLineNumber(String.valueOf(lineItemNo));
							}
							// 新增
							cSrmSupplierR = new CSrmSupplierR();
							BeanUtils.copyProperties(req, cSrmSupplierR);
							cSrmSupplierR.setCreateTime(new Date());
							cSrmSupplierRMapper.insertSelective(cSrmSupplierR);
						} else {
							// 更新
							cSrmSupplierR = new CSrmSupplierR();
							BeanUtils.copyProperties(req, cSrmSupplierR);
							cSrmSupplierR.setUpdateTime(new Date());
							cSrmSupplierRMapper.updateByPrimaryKeySelective(cSrmSupplierR);
						}
					}
				}

				if (("3").equals(cSrmSupplierHReq.getRequestStatus())) {
					// 如果目标阶段为淘汰则冻结供应商账号
					if (("4").equals(cSrmSupplierHReq.getTargetPhase())) {
						CSrmSupplier cSrmSupplier1 = new CSrmSupplier();
						cSrmSupplier1.setSupplierCode(cSrmSupplierHReq.getSupplierCode());
						// 冻结
						cSrmSupplier1.setStatus(0);
						// 淘汰
						cSrmSupplier1.setInPhase("4");
						cSrmSupplierMapper.updateByPrimaryKeySelective(cSrmSupplier1);

						// 冻结用户表登录状态
//						CMesUserTReq req = new CMesUserTReq();
//						req.setSupplierCode(cSrmSupplierHReq.getSupplierCode());
//						// 0.冻结，1.正常
//						req.setStatus("0");
//						userDao.updateByPrimaryKeySelective(req);

						// 冻结 sys_user status 帐号状态（0正常 1停用）
						SysUser sysUser = new SysUser();
						sysUser.setSupplierCode(cSrmSupplierHReq.getSupplierCode());
						sysUser.setStatus("1");
						AjaxResult result = iSysUserServiceFeignClient.updateStatus(sysUser);
						if (HttpStatus.OK.value() != result.getCode()) {
							throw new CustomException(result.getMsg());
						}
						log.info("【修改用户状态出参】[{}]", JSONUtil.toJsonStr(result));
						// 校验是否需要推送K3同步冻结此供应商账号
						if (StringUtil.eqNu(cSrmSupplierHReq.getServiceType()) && cSrmSupplierHReq.getPush()) {
							// 推送至K3
							log.info("----开始推送'供应商账号状态变更'至K3----");
							if (CommonUtils.getRedisValue(redisCache,SrmFinal.K_THREE_SERVICE_TYPE,"推送K3服务凭证").equals(String.valueOf(cSrmSupplierHReq.getServiceType()))) {
								// 封装请求参数
								Map<String, Object> map = new HashMap<>();
								map.put("jktype", "Supplier");
								map.put("method", "modify");
								List<KThreeSupplierReq> updateList = new ArrayList<>();
								// 赋值供应商数据
								KThreeSupplierReq threeSupplierReq = new KThreeSupplierReq();
								threeSupplierReq.setID(cSrmSupplierKThree.getSupplierCode());
								threeSupplierReq.setFName(cSrmSupplierKThree.getName());
								threeSupplierReq.setFDeleted(1);
								updateList.add(threeSupplierReq);
								map.put("data", updateList);
								log.info("【供应商账号状态变更入参】[{}]", JSONUtil.toJsonStr(map.toString()));
								KThreeSupplierResult supplierResult = JSONObject.parseObject(srmSupplierTimer.sendPost(map), KThreeSupplierResult.class);
								if (SrmFinal.SUCESS.equals(supplierResult.getStatus())) {
									System.err.println("更新成功，K3响应结果----" + supplierResult.toString() + "----");
									log.info("【供应商账号状态变更出参】[{}]", JSONUtil.toJsonStr(supplierResult.toString()));
								} else {
									log.error("【供应商账号状态变更出参】[{}]", JSONUtil.toJsonStr(supplierResult.toString()));
									throw new Exception("K3服务异常：" + supplierResult.toString());
								}
							}
						}

					}
				}


				return Rjson.success("更新成功", cSrmSupplierHReq);
			} else if (("3").equals(cSrmSupplierHReq.getOperationSign())) {
				// 更新供应商升降级申请头表
				BeanUtils.copyProperties(cSrmSupplierHReq, cSrmSupplierH);
				cSrmSupplierH.setUpdateTime(new Date());
				cSrmSupplierHMapper.updateByPrimaryKeySelective(cSrmSupplierH);
				return Rjson.success("更新成功", cSrmSupplierHReq);
			} else {
				return Rjson.error("操作标识错误");
			}
		}
	}
//    }

	@Override
	public Rjson findApplicationFormHR(CSrmSupplierHRReq req) {
		CSrmSupplierHRRsps rsp = new CSrmSupplierHRRsps();
		// 获取头数据
		BeanUtils.copyProperties(cSrmSupplierHMapper.selectByPrimaryHList(req).stream().findFirst(), rsp);
		// 获取行数据
		rsp.setRspList(cSrmSupplierRMapper.selectByPrimaryKeyList(req));
		return Rjson.success(rsp);
	}

	@Override
	public Rjson findApplicationFormH(CSrmSupplierHRReq req) {
		// 获取头数据
		return Rjson.success(cSrmSupplierHMapper.selectByPrimaryHList(req));
	}

	@Override
	public Rjson findApplicationFormR(CSrmSupplierHRReq req) {
		// 获取行数据
		return Rjson.success(cSrmSupplierRMapper.selectByPrimaryKeyList(req));
	}

	@Override
	public Rjson delApplicationForm(CSrmSupplierHRDelReq delReq) {
		// 删除头数据
		cSrmSupplierHMapper.deleteByPrimaryData(delReq);
		// 删除行数据
		if (!CollectionUtils.isEmpty(delReq.getLineNumber())) {
			cSrmSupplierRMapper.deleteByPrimaryData(delReq);
		}
		return Rjson.success("删除成功", null);
	}
}


