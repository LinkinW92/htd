package com.skeqi.mes.service.chenj.srm.impl;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.common.utils.redis.RedisCache;
import com.skeqi.mes.controller.chenj.srm.timer.SrmSupplierTimer;
import com.skeqi.mes.finals.SrmFinal;
import com.skeqi.mes.mapper.chenj.srm.*;
import com.skeqi.mes.mapper.wf.CMesFactoryTMapper;
import com.skeqi.mes.pojo.chenj.srm.*;
import com.skeqi.mes.pojo.chenj.srm.kthree.KThreePOReceive;
import com.skeqi.mes.pojo.chenj.srm.kthree.KThreePOReceiveHReq;
import com.skeqi.mes.pojo.chenj.srm.kthree.KThreePOReceiveRReq;
import com.skeqi.mes.pojo.chenj.srm.kthree.KThreePOReceiveResult;
import com.skeqi.mes.pojo.chenj.srm.req.*;
import com.skeqi.mes.service.chenj.srm.CSrmSendCommodityHService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.StringUtil;
import com.skeqi.mes.util.chenj.CommonUtils;
import com.skeqi.mes.util.chenj.EqualsPoJoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmSendCommodityHServiceImpl
 * @Description ${Description}
 */

@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class CSrmSendCommodityHServiceImpl implements CSrmSendCommodityHService {

	@Resource
	private CSrmSendCommodityHMapper cSrmSendCommodityHMapper;

	@Resource
	private CSrmSupplierMapper cSrmSupplierMapper;

	@Resource
	private CSrmSendCommodityRMapper cSrmSendCommodityRMapper;

	@Resource
	private CSrmPurchaseOrderHMapper cSrmPurchaseOrderHMapper;

	@Resource
	private CSrmPurchaseOrderRMapper cSrmPurchaseOrderRMapper;

	@Resource
	private CSrmTheNumberAuditHMapper cSrmTheNumberAuditHMapper;

	@Resource
	private CSrmTheNumberAuditRMapper cSrmTheNumberAuditRMapper;


	@Resource
	private CSrmSendCommodityLogisticsMapper cSrmSendCommodityLogisticsMapper;

	@Resource
	private CMesFactoryTMapper cMesFactoryTMapper;

	@Autowired
	private RedisCache redisCache;

	@Override
	public int insertOrUpdateSelective(CSrmSendCommodityH record) {
		return cSrmSendCommodityHMapper.insertOrUpdateSelective(record);
	}

	@Override
	public int insertSelective(CSrmSendCommodityH record) {
		return cSrmSendCommodityHMapper.insertSelective(record);
	}

	@Override
	public CSrmSendCommodityH selectByPrimaryKey(CSrmSendCommodityH record) {
		return cSrmSendCommodityHMapper.selectByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKeySelective(CSrmSendCommodityH record) {
		return cSrmSendCommodityHMapper.updateByPrimaryKeySelective(record);
	}


	@Override
	public int updateBatchSelective(List<CSrmSendCommodityH> list) {
		return cSrmSendCommodityHMapper.updateBatchSelective(list);
	}


	@Override
	public Rjson updateDeliverySlip(CSrmSendCommodityHReq cSrmSendCommodityHReq, SrmSupplierTimer srmSupplierTimer) throws Exception {
		/**
		 * 输入：送货单号、送货单类型、发货日期、预计到货日期、收货地点、发货地点、收货组织、客户、供应商、创建日期、创建人、状态、行项目号、物料编码、数量、单位、采购订单号
		 * 、操作标识
		 * 处理：送货单号为空生成送货单编号（以ASN开头+年月日+3位流水号）,更新送货单头行表，保存后状态变成新建、提交后变成待确认，确认后变成待发货，供应商发货后变成待收货（如果需要在ERP进行该单据收货，需要调用ERP接口推送单据），仓库接收后变成已完成（如果是在SRM做库存接收，需要调用仓库或者ERP接口进行库存处理）
		 *
		 * 	c_srm_send_commodity_h  送货单头表 (送货单号、送货单类型(1.标准送货单)、发货日期、预计到货日期、收货地点、发货地点、收货组织、客户、供应商、创建日期、创建人、状态(1.新建2.待确认3.待发货4.待收货5.已完成))
		 *     c_srm_send_commodity_r  送货单行表 (送货单号、行项目号、物料编码、数量、单位、采购订单号)
		 * 	c_srm_send_commodity_logistics   送货单物流信息表 (送货单号、物流单号、物流公司信息、联系方式)
		 *
		 */

		// 创建
		CSrmSendCommodityH cSrmSendCommodityH = null;
		if (("1").equals(cSrmSendCommodityHReq.getOperationSign()) || ("2").equals(cSrmSendCommodityHReq.getOperationSign())) {
			// 校验收货组织是否存在
			CMesFactoryTReq req = new CMesFactoryTReq();
			req.setFactoryCode(cSrmSendCommodityHReq.getReceivingOrganization());
			if (cMesFactoryTMapper.findFactoryList(req).size() == 0) {
				return Rjson.error("收货组织不存在");
			}
		}


		if (("1").equals(cSrmSendCommodityHReq.getOperationSign())) {
			// 校验供应商编码是否存在
			if (null == cSrmSupplierMapper.selectSupplierCode(cSrmSendCommodityHReq.getSupplierCode())) {
				return Rjson.error("供应商代码不存在");
			}
			// 校验采购订单是否处于已确认状态
			CSrmPurchaseOrderH cSrmPurchaseOrderH = new CSrmPurchaseOrderH();
			CSrmSendCommodityRReq cSrmSendCommodityRReq = cSrmSendCommodityHReq.getDeList().stream().findFirst().orElse(null);
			if (null == cSrmSendCommodityRReq || !StringUtil.eqNu(cSrmSendCommodityRReq.getPurchaseOrderNo())) {
				return Rjson.error("'采购申请号'不能为空");
			} else if (StringUtil.eqNu(cSrmSendCommodityRReq.getPurchaseOrderNo())) {
				// 查询采购订单号是否存在
				cSrmPurchaseOrderH.setOrderNumber(cSrmSendCommodityRReq.getPurchaseOrderNo());
				List<CSrmPurchaseOrderH> cSrmPurchaseOrderHS = cSrmPurchaseOrderHMapper.selectByPrimaryKey(cSrmPurchaseOrderH);
				if (cSrmPurchaseOrderHS.size() == 1) {
					CSrmPurchaseOrderH cSrmPurchaseOrderH1 = cSrmPurchaseOrderHS.stream().findFirst().orElse(null);
					// 校验订单状态是否处于已确认状态
					if (!("4").equals(cSrmPurchaseOrderH1.getStatus())) {
						return Rjson.error("创建失败，采购订单不处于已确认状态");
					}
				} else if (cSrmPurchaseOrderHS.size() == 0) {
					return Rjson.error("创建失败，采购订单不存在");
				}

			}
			// 生成送货单号（以ASN开头+年月日+3位流水号）
			cSrmSendCommodityH = cSrmSendCommodityHMapper.selectFinallyData();
			String yyyyMMdd = new SimpleDateFormat("yyyyMMdd").format(new Date());
			if (cSrmSendCommodityH == null) {
				// 未找到数据，从最新一条开始
				cSrmSendCommodityHReq.setDeliveryNumber("ASN" + yyyyMMdd + 100);
			} else {
				int requestCode = Integer.parseInt(cSrmSendCommodityH.getDeliveryNumber().substring(11)) + 1;
				cSrmSendCommodityHReq.setDeliveryNumber("ASN" + yyyyMMdd + requestCode);
			}

			// 新增送货单头表
			cSrmSendCommodityH = new CSrmSendCommodityH();
			BeanUtils.copyProperties(cSrmSendCommodityHReq, cSrmSendCommodityH);
			// 发货日期
			cSrmSendCommodityH.setDeliveryDate(new SimpleDateFormat("yyyy-MM-dd").parse(cSrmSendCommodityHReq.getDeliveryDate()));
			// 预计到货日期
			cSrmSendCommodityH.setExpectedDateOfArrival(new SimpleDateFormat("yyyy-MM-dd").parse(cSrmSendCommodityHReq.getExpectedDateOfArrival()));
			// 创建时间
			cSrmSendCommodityH.setCreateTime(new Date());
			// 返回给前端创建时间
			cSrmSendCommodityHReq.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cSrmSendCommodityH.getCreateTime()));
			cSrmSendCommodityHMapper.insertSelective(cSrmSendCommodityH);
			// 新增送货单行表
			for (CSrmSendCommodityRReq item : cSrmSendCommodityHReq.getDeList()) {
				// 校验参数
				EqualsPoJoUtil.string(item.getMaterialCode(), "物料编码");
				// 生成项目行号
				CSrmSendCommodityR commodityR = cSrmSendCommodityRMapper.selectFinallyData(cSrmSendCommodityHReq.getDeliveryNumber());
				if (commodityR == null) {
					item.setLineItemNo("1");
				} else {
					int lineItemNo = Integer.parseInt(commodityR.getLineItemNo()) + 1;
					item.setLineItemNo(String.valueOf(lineItemNo));
				}
				commodityR = new CSrmSendCommodityR();
				item.setDeliveryNumber(cSrmSendCommodityHReq.getDeliveryNumber());
				BeanUtils.copyProperties(item, commodityR);
				commodityR.setCreateTime(new Date());
				cSrmSendCommodityRMapper.insertSelective(commodityR);
				// 更新采购订单头表中 是否已创建开票申请单为 已创建
				CSrmPurchaseOrderH srmPurchaseOrderH = new CSrmPurchaseOrderH();
				srmPurchaseOrderH.setOrderNumber(item.getPurchaseOrderNo());
				srmPurchaseOrderH.setIsOpenTicket(1);
				cSrmPurchaseOrderHMapper.updateByPrimaryKeySelective(srmPurchaseOrderH);
			}


			return Rjson.success("创建成功", cSrmSendCommodityHReq);
		} else if (("2").equals(cSrmSendCommodityHReq.getOperationSign())) {
			// 校验送货单号是否存在
			cSrmSendCommodityH = new CSrmSendCommodityH();
			cSrmSendCommodityH.setDeliveryNumber(cSrmSendCommodityHReq.getDeliveryNumber());
			cSrmSendCommodityH = cSrmSendCommodityHMapper.selectByPrimaryKey(cSrmSendCommodityH);
			if (cSrmSendCommodityH == null) {
				return Rjson.error("更新失败，送货单号不存在");
			} else {
				// 修改送货单头表
				cSrmSendCommodityH = new CSrmSendCommodityH();
				BeanUtils.copyProperties(cSrmSendCommodityHReq, cSrmSendCommodityH);
				// 发货日期
				if (StringUtil.eqNu(cSrmSendCommodityHReq.getDeliveryDate())) {
					cSrmSendCommodityH.setDeliveryDate(new SimpleDateFormat("yyyy-MM-dd").parse(cSrmSendCommodityHReq.getDeliveryDate()));
				}

				// 预计到货日期
				if (StringUtil.eqNu(cSrmSendCommodityHReq.getExpectedDateOfArrival())) {
					cSrmSendCommodityH.setExpectedDateOfArrival(new SimpleDateFormat("yyyy-MM-dd").parse(cSrmSendCommodityHReq.getExpectedDateOfArrival()));
				}
				cSrmSendCommodityH.setUpdateTime(new Date());


				cSrmSendCommodityHMapper.updateByPrimaryKeySelective(cSrmSendCommodityH);
				// 修改送货单行表
				if (!CollectionUtils.isEmpty(cSrmSendCommodityHReq.getDeList())) {
					for (CSrmSendCommodityRReq item : cSrmSendCommodityHReq.getDeList()) {
						CSrmSendCommodityR commodityR = null;
						if (!StringUtil.eqNu(item.getLineItemNo())) {
							// 生成项目行号
							commodityR = cSrmSendCommodityRMapper.selectFinallyData(cSrmSendCommodityHReq.getDeliveryNumber());
							if (commodityR == null) {
								item.setLineItemNo("1");
							} else {
								int lineItemNo = Integer.parseInt(commodityR.getLineItemNo()) + 1;
								item.setLineItemNo(String.valueOf(lineItemNo));
							}
							// 新增
							commodityR = new CSrmSendCommodityR();
							item.setDeliveryNumber(cSrmSendCommodityHReq.getDeliveryNumber());
							BeanUtils.copyProperties(item, commodityR);
							commodityR.setCreateTime(new Date());
							cSrmSendCommodityRMapper.insertSelective(commodityR);
						} else {
							commodityR = new CSrmSendCommodityR();
							item.setDeliveryNumber(cSrmSendCommodityHReq.getDeliveryNumber());
							BeanUtils.copyProperties(item, commodityR);
							commodityR.setUpdateTime(new Date());
							cSrmSendCommodityRMapper.updateByPrimaryKeySelective(commodityR);
						}
					}
				}
				// 送货单状态为 3.待发货
				if (("3").equals(cSrmSendCommodityHReq.getStatus())) {
					// 校验是否需要将送货单推送至K3
					if (StringUtil.eqNu(cSrmSendCommodityHReq.getServiceType()) && cSrmSendCommodityHReq.getPush()) {

						if (CommonUtils.getRedisValue(redisCache,SrmFinal.K_THREE_SERVICE_TYPE,"推送K3服务凭证").equals(String.valueOf(cSrmSendCommodityHReq.getServiceType()))) {
							// 推送至K3
							log.info("----开始推送'送货单'至K3----");
							// 新增
							// 封装头请求参数
							List<KThreePOReceiveHReq> addList = new ArrayList<>();
							// 封装行请求参数
							List<KThreePOReceiveRReq> addItemsList = new ArrayList<>();

							KThreePOReceive kThreePOReceive = new KThreePOReceive();
							// 赋值送货单头数据  触发与K3字段映射值
							BeanUtils.copyProperties(cSrmSendCommodityHReq, kThreePOReceive);
							KThreePOReceiveHReq threePOReceiveReq = new KThreePOReceiveHReq();
							BeanUtils.copyProperties(kThreePOReceive, threePOReceiveReq);
							// 创建时间
							threePOReceiveReq.setcDate(new SimpleDateFormat("yyyy-MM-dd").parse(cSrmSendCommodityHReq.getCreateTime()));
							// 修改时间
							threePOReceiveReq.setmDate(new SimpleDateFormat("yyyy-MM-dd").format(cSrmSendCommodityH.getUpdateTime()));
							addList.add(threePOReceiveReq);

							// 获取送货单行数据
							for (CSrmSendCommodityRReq item : cSrmSendCommodityHReq.getDeList()) {
								KThreePOReceiveRReq threePOReceiveRReqItem = new KThreePOReceiveRReq();
								// 赋值送货单行数据  触发与K3字段映射值
								BeanUtils.copyProperties(item, kThreePOReceive);
								// 行号
								kThreePOReceive.setLineItemNo(Integer.parseInt(item.getLineItemNo()));
								// 数量
								kThreePOReceive.setCount(Integer.parseInt(item.getCount()));
								// 订单行号
								kThreePOReceive.setPurLineItemNo(Integer.parseInt(item.getPurLineItemNo()));
								// 赋值
								BeanUtils.copyProperties(kThreePOReceive, threePOReceiveRReqItem);
								addItemsList.add(threePOReceiveRReqItem);
								// 赋值行数据
								threePOReceiveReq.setItems(addItemsList);
							}
							Map<String, Object> map = new HashMap<>();
							map.put("jktype", "POReceive");
							map.put("method", "add");
							map.put("data", addList);
							log.info("【推送送货单入参】[{}]", JSONUtil.toJsonStr(map.toString()));
							KThreePOReceiveResult poPOReceiveResult = JSONObject.parseObject(srmSupplierTimer.sendPost(map), KThreePOReceiveResult.class);
							if (SrmFinal.SUCESS.equals(poPOReceiveResult.getStatus())) {
								log.info("【送货单入参】[{}]", JSONUtil.toJsonStr(poPOReceiveResult.toString()));
							} else {
								log.error("【送货单出参】[{}]", JSONUtil.toJsonStr(poPOReceiveResult.toString()));
								throw new Exception("K3服务异常：" + poPOReceiveResult.getMessage());
							}

						}

					}

				}


				return Rjson.success("更新成功", null);
			}


		} else if (("3").equals(cSrmSendCommodityHReq.getOperationSign())) {

			// 校验送货单号是否存在
			cSrmSendCommodityH = new CSrmSendCommodityH();
			cSrmSendCommodityH.setDeliveryNumber(cSrmSendCommodityHReq.getDeliveryNumber());
			cSrmSendCommodityH = cSrmSendCommodityHMapper.selectByPrimaryKey(cSrmSendCommodityH);
			if (cSrmSendCommodityH == null) {
				return Rjson.error("更新失败，送货单号不存在");
			}
			// 修改送货单头表
			cSrmSendCommodityH = new CSrmSendCommodityH();
			BeanUtils.copyProperties(cSrmSendCommodityHReq, cSrmSendCommodityH);
			cSrmSendCommodityH.setUpdateTime(new Date());
			// 校验当前操作是否为收货时间  (1.新建2.待确认3.待发货4.待收货5.已完成)
			if (("5").equals(cSrmSendCommodityHReq.getStatus())) {
				cSrmSendCommodityH.setReceivingGoodsDt(new Date());
			}
			cSrmSendCommodityHMapper.updateByPrimaryKeySelective(cSrmSendCommodityH);
			return Rjson.success("更新成功", null);
		} else {
			return Rjson.error("操作标识错误");
		}
	}


	@Override
	public Rjson findDeliverySlipHR(CSrmSendCommodityHReq cSrmSendCommodityHReq) {
		// 查询头数据
		CSrmSendCommodityHRsp rsp = new CSrmSendCommodityHRsp();
		CSrmSendCommodityHRsp cSrmSendCommodityHRsp = cSrmSendCommodityHMapper.selectByPrimaryList(cSrmSendCommodityHReq);
		if (null == cSrmSendCommodityHRsp) {
			return Rjson.success();
		}
		BeanUtils.copyProperties(cSrmSendCommodityHRsp, rsp);
		// 查询行数据
		CSrmSendCommodityR commodityR = new CSrmSendCommodityR();
		commodityR.setDeliveryNumber(cSrmSendCommodityHReq.getDeliveryNumber());
		PageHelper.startPage(cSrmSendCommodityHReq.getPageNum(), cSrmSendCommodityHReq.getPageSize());
		rsp.setDeList(new PageInfo<>(cSrmSendCommodityRMapper.selectByPrimaryList(commodityR)));
		PageHelper.startPage(cSrmSendCommodityHReq.getPageNum(), cSrmSendCommodityHReq.getPageSize());
		// 查询物流数据
//        rsp.setWlList(new PageInfo<>(cSrmSendCommodityLogisticsMapper.selectByPrimaryList(cSrmSendCommodityHReq)));
		List<CSrmSendCommodityHRsp> rspList = new ArrayList<>();
		rspList.add(rsp);
		return Rjson.success(rspList);
	}


	@Override
	public Rjson findDeliverySlipH(CSrmSendCommodityHReq cSrmSendCommodityHReq) {
		// 查询头数据
		PageHelper.startPage(cSrmSendCommodityHReq.getPageNum(), cSrmSendCommodityHReq.getPageSize());
		return Rjson.success(new PageInfo<>(cSrmSendCommodityHMapper.selectByPrimaryListDataS(cSrmSendCommodityHReq)));
	}

	@Override
	public int insertOrUpdate(CSrmSendCommodityH record) {
		return cSrmSendCommodityHMapper.insertOrUpdate(record);
	}

	/**
	 * 与K3交互接口
	 *
	 * @param receiptReq
	 * @return
	 */
	@Override
	public Rjson ReceiveTheActualReceipt(ReceiveTheActualReceiptReq receiptReq) {
		// 校验处理标记状态   处理标记(1、已完成2、拒绝)
		if (("1").equals(receiptReq.getDisposeSign())) {
			// 批量更新物料行实收数  至 c_srm_send_commodity_h、c_srm_send_commodity_r(实收数)  头行对应数据
			// 更新送货单头状态为已完成  根据送货单号更新状态
			CSrmSendCommodityH cSrmSendCommodityH = new CSrmSendCommodityH();
			cSrmSendCommodityH.setDeliveryNumber(receiptReq.getDeliveryNumber());
			cSrmSendCommodityH.setConsigneeName(receiptReq.getConsigneeName());
			// 状态(1.新建2.待确认3.待发货4.待收货5.已完成)
			cSrmSendCommodityH.setStatus("5");
			cSrmSendCommodityH.setUpdateTime(new Date());
			cSrmSendCommodityHMapper.updateByPrimaryKeySelective(cSrmSendCommodityH);
			// 批量更新送货单行实收数  根据送货单号、物料编码、行号更新每行实收数
			// 赋值单号
			receiptReq.getMaterialLine().forEach(item -> {
				item.setUpdateTime(new Date());
				cSrmSendCommodityRMapper.updateByPrimaryKeySelective(item);
			});
		} else {
			// 暂时先不处理
			System.err.println("----K3推送送货单信息----\n处理标记状态为2：拒绝");
		}
		return Rjson.success();
	}

	@Override
	public Rjson delDeliverySlip(CSrmSendCommodityHReq req) {
		if (StringUtil.eqNu(req.getDeliveryNumber())) {
			// 删除头表记录
			CSrmSendCommodityH cSrmSendCommodityH = new CSrmSendCommodityH();
			cSrmSendCommodityH.setDeliveryNumber(req.getDeliveryNumber());
			cSrmSendCommodityH.setDelete(true);
			cSrmSendCommodityHMapper.updateByPrimaryKeySelective(cSrmSendCommodityH);
			// 删除行表记录
			CSrmSendCommodityR cSrmSendCommodityR = new CSrmSendCommodityR();
			cSrmSendCommodityR.setDeliveryNumber(req.getDeliveryNumber());
			cSrmSendCommodityR.setDelete(true);
			cSrmSendCommodityRMapper.updateByPrimaryKeySelective(cSrmSendCommodityR);

		}

		// 删除对账开票数据
		if (StringUtil.eqNu(req.getTheNumberOfHeInvoiceApplication())) {
			// 删除头表记录
			CSrmTheNumberAuditH cSrmTheNumberAuditH = new CSrmTheNumberAuditH();
			cSrmTheNumberAuditH.setTheNumberOfHeInvoiceApplication(req.getTheNumberOfHeInvoiceApplication());
			cSrmTheNumberAuditH.setIsDelete(true);
			cSrmTheNumberAuditHMapper.updateByPrimaryKeySelective(cSrmTheNumberAuditH);
			// 删除行表记录
			CSrmTheNumberAuditR cSrmTheNumberAuditR = new CSrmTheNumberAuditR();
			cSrmTheNumberAuditR.setTheNumberOfHeInvoiceApplication(req.getTheNumberOfHeInvoiceApplication());
			cSrmTheNumberAuditR.setDelete(true);
			cSrmTheNumberAuditRMapper.updateByPrimaryKeySelective(cSrmTheNumberAuditR);

		}


		if (!CollectionUtils.isEmpty(req.getDeliveryNumberList())) {
			for (CSrmSendCommodityRReq item : req.getDeliveryNumberList()) {
				// 更新送货单头表中 是否已创建对账申请单为 未创建
				CSrmSendCommodityH srmSendCommodityH = new CSrmSendCommodityH();
				srmSendCommodityH.setDeliveryNumber(item.getDeliveryNumber());
				srmSendCommodityH.setIsOpenTicket(0);
				cSrmSendCommodityHMapper.updateByPrimaryKeySelective(srmSendCommodityH);
			}
		}


		return Rjson.success();
	}
}






