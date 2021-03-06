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
		// ?????????????????????????????????
		CSrmSupplier cSrmSupplier = cSrmSupplierMapper.selectSupplierCode(cSrmSupplierHReq.getSupplierCode());
		if (cSrmSupplier == null) {
			return Rjson.error("????????????????????????");
		} else {
			// ???????????????????????????????????????????????????????????????
//            CSrmSupplierH cSrmSupplierH = cSrmSupplierHMapper.selectSupplierCode(cSrmSupplierHReq.getSupplierCode(), "2");
//            if (null != cSrmSupplierH) {
//                return Rjson.error("????????????????????????????????????????????????????????????");
//            } else {
			// ???????????????????????????????????????K3??????
			CSrmSupplier cSrmSupplierKThree = new CSrmSupplier();
			BeanUtils.copyProperties(cSrmSupplier, cSrmSupplierKThree);
			CSrmSupplierH cSrmSupplierH = new CSrmSupplierH();
			if (("1").equals(cSrmSupplierHReq.getOperationSign())) {
				// ????????????????????????
				CSrmSupplierH selectFinallyData = cSrmSupplierHMapper.selectFinallyData();
				String yyyyMMdd = new SimpleDateFormat("yyyyMMdd").format(new Date());
				if (null == selectFinallyData) {
					// ???????????????????????????????????????
					cSrmSupplierHReq.setRequestCode("SUP" + yyyyMMdd + 100);
				} else {
					int requestCode = Integer.parseInt(selectFinallyData.getRequestCode().substring(11)) + 1;
					cSrmSupplierHReq.setRequestCode("SUP" + yyyyMMdd + requestCode);
				}
				// ????????????????????????????????????
				BeanUtils.copyProperties(cSrmSupplierHReq, cSrmSupplierH);
				cSrmSupplierH.setCreateTime(new Date());
				cSrmSupplierHMapper.insertSelective(cSrmSupplierH);
				// ????????????????????????????????????
				if (!CollectionUtils.isEmpty(cSrmSupplierHReq.getReqList())) {
					for (CSrmSupplierRReq req : cSrmSupplierHReq.getReqList()) {
						// ????????????
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

				return Rjson.success("????????????", cSrmSupplierHReq);

			} else if (("2").equals(cSrmSupplierHReq.getOperationSign())) {

				// ????????????????????????????????????
				BeanUtils.copyProperties(cSrmSupplierHReq, cSrmSupplierH);

				cSrmSupplierH.setUpdateTime(new Date());
				cSrmSupplierHMapper.updateByPrimaryKeySelective(cSrmSupplierH);
				// ????????????????????????????????????
				if (!CollectionUtils.isEmpty(cSrmSupplierHReq.getReqList())) {
					for (CSrmSupplierRReq req : cSrmSupplierHReq.getReqList()) {
						CSrmSupplierR cSrmSupplierR = null;
						// ??????????????????
						req.setRequestCode(cSrmSupplierHReq.getRequestCode());
						if (!StringUtil.eqNu(req.getLineNumber())) {
							// ????????????
							cSrmSupplierR = cSrmSupplierRMapper.selectFinallyData(cSrmSupplierHReq.getRequestCode());
							if (cSrmSupplierR == null) {
								req.setLineNumber("1");
							} else {
								int lineItemNo = Integer.parseInt(cSrmSupplierR.getLineNumber()) + 1;
								req.setLineNumber(String.valueOf(lineItemNo));
							}
							// ??????
							cSrmSupplierR = new CSrmSupplierR();
							BeanUtils.copyProperties(req, cSrmSupplierR);
							cSrmSupplierR.setCreateTime(new Date());
							cSrmSupplierRMapper.insertSelective(cSrmSupplierR);
						} else {
							// ??????
							cSrmSupplierR = new CSrmSupplierR();
							BeanUtils.copyProperties(req, cSrmSupplierR);
							cSrmSupplierR.setUpdateTime(new Date());
							cSrmSupplierRMapper.updateByPrimaryKeySelective(cSrmSupplierR);
						}
					}
				}

				if (("3").equals(cSrmSupplierHReq.getRequestStatus())) {
					// ???????????????????????????????????????????????????
					if (("4").equals(cSrmSupplierHReq.getTargetPhase())) {
						CSrmSupplier cSrmSupplier1 = new CSrmSupplier();
						cSrmSupplier1.setSupplierCode(cSrmSupplierHReq.getSupplierCode());
						// ??????
						cSrmSupplier1.setStatus(0);
						// ??????
						cSrmSupplier1.setInPhase("4");
						cSrmSupplierMapper.updateByPrimaryKeySelective(cSrmSupplier1);

						// ???????????????????????????
//						CMesUserTReq req = new CMesUserTReq();
//						req.setSupplierCode(cSrmSupplierHReq.getSupplierCode());
//						// 0.?????????1.??????
//						req.setStatus("0");
//						userDao.updateByPrimaryKeySelective(req);

						// ?????? sys_user status ???????????????0?????? 1?????????
						SysUser sysUser = new SysUser();
						sysUser.setSupplierCode(cSrmSupplierHReq.getSupplierCode());
						sysUser.setStatus("1");
						AjaxResult result = iSysUserServiceFeignClient.updateStatus(sysUser);
						if (HttpStatus.OK.value() != result.getCode()) {
							throw new CustomException(result.getMsg());
						}
						log.info("??????????????????????????????[{}]", JSONUtil.toJsonStr(result));
						// ????????????????????????K3??????????????????????????????
						if (StringUtil.eqNu(cSrmSupplierHReq.getServiceType()) && cSrmSupplierHReq.getPush()) {
							// ?????????K3
							log.info("----????????????'???????????????????????????'???K3----");
							if (CommonUtils.getRedisValue(redisCache,SrmFinal.K_THREE_SERVICE_TYPE,"??????K3????????????").equals(String.valueOf(cSrmSupplierHReq.getServiceType()))) {
								// ??????????????????
								Map<String, Object> map = new HashMap<>();
								map.put("jktype", "Supplier");
								map.put("method", "modify");
								List<KThreeSupplierReq> updateList = new ArrayList<>();
								// ?????????????????????
								KThreeSupplierReq threeSupplierReq = new KThreeSupplierReq();
								threeSupplierReq.setID(cSrmSupplierKThree.getSupplierCode());
								threeSupplierReq.setFName(cSrmSupplierKThree.getName());
								threeSupplierReq.setFDeleted(1);
								updateList.add(threeSupplierReq);
								map.put("data", updateList);
								log.info("???????????????????????????????????????[{}]", JSONUtil.toJsonStr(map.toString()));
								KThreeSupplierResult supplierResult = JSONObject.parseObject(srmSupplierTimer.sendPost(map), KThreeSupplierResult.class);
								if (SrmFinal.SUCESS.equals(supplierResult.getStatus())) {
									System.err.println("???????????????K3????????????----" + supplierResult.toString() + "----");
									log.info("???????????????????????????????????????[{}]", JSONUtil.toJsonStr(supplierResult.toString()));
								} else {
									log.error("???????????????????????????????????????[{}]", JSONUtil.toJsonStr(supplierResult.toString()));
									throw new Exception("K3???????????????" + supplierResult.toString());
								}
							}
						}

					}
				}


				return Rjson.success("????????????", cSrmSupplierHReq);
			} else if (("3").equals(cSrmSupplierHReq.getOperationSign())) {
				// ????????????????????????????????????
				BeanUtils.copyProperties(cSrmSupplierHReq, cSrmSupplierH);
				cSrmSupplierH.setUpdateTime(new Date());
				cSrmSupplierHMapper.updateByPrimaryKeySelective(cSrmSupplierH);
				return Rjson.success("????????????", cSrmSupplierHReq);
			} else {
				return Rjson.error("??????????????????");
			}
		}
	}
//    }

	@Override
	public Rjson findApplicationFormHR(CSrmSupplierHRReq req) {
		CSrmSupplierHRRsps rsp = new CSrmSupplierHRRsps();
		// ???????????????
		BeanUtils.copyProperties(cSrmSupplierHMapper.selectByPrimaryHList(req).stream().findFirst(), rsp);
		// ???????????????
		rsp.setRspList(cSrmSupplierRMapper.selectByPrimaryKeyList(req));
		return Rjson.success(rsp);
	}

	@Override
	public Rjson findApplicationFormH(CSrmSupplierHRReq req) {
		// ???????????????
		return Rjson.success(cSrmSupplierHMapper.selectByPrimaryHList(req));
	}

	@Override
	public Rjson findApplicationFormR(CSrmSupplierHRReq req) {
		// ???????????????
		return Rjson.success(cSrmSupplierRMapper.selectByPrimaryKeyList(req));
	}

	@Override
	public Rjson delApplicationForm(CSrmSupplierHRDelReq delReq) {
		// ???????????????
		cSrmSupplierHMapper.deleteByPrimaryData(delReq);
		// ???????????????
		if (!CollectionUtils.isEmpty(delReq.getLineNumber())) {
			cSrmSupplierRMapper.deleteByPrimaryData(delReq);
		}
		return Rjson.success("????????????", null);
	}
}


