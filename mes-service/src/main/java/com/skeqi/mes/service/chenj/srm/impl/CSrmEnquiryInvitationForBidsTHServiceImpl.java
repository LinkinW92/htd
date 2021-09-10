package com.skeqi.mes.service.chenj.srm.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.skeqi.mes.controller.crm.BusinessInfoFileController;
import com.skeqi.mes.mapper.chenj.srm.*;
import com.skeqi.mes.pojo.chenj.srm.*;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmEnquiryForBidsTSupplierReq;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmEnquiryInvitationForBidsTHReq;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmEnquiryInvitationForBidsTRReq;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmFileUploadingReq;
import com.skeqi.mes.pojo.chenj.srm.rsp.CSrmEnquiryForBidsTSupplierRsp;
import com.skeqi.mes.pojo.chenj.srm.rsp.CSrmEnquiryInvitationForBidsTHRsp;
import com.skeqi.mes.pojo.chenj.srm.rsp.CSrmEnquiryInvitationForBidsTHRsps;
import com.skeqi.mes.pojo.chenj.srm.rsp.CSrmFileUploadingRsp;
import com.skeqi.mes.service.chenj.srm.CSrmEnquiryInvitationForBidsTHService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.StringUtil;
import com.skeqi.mes.util.chenj.EqualsPoJoUtil;
import com.skeqi.mes.util.chenj.FileUploadUtils;
import com.skeqi.mes.util.yp.EqualsUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static com.skeqi.mes.util.chenj.FileUploadUtils.isOSLinux;

/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmEnquiryInvitationForBidsTHServiceImpl
 * @Description ${Description}
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class CSrmEnquiryInvitationForBidsTHServiceImpl implements CSrmEnquiryInvitationForBidsTHService {

	@Resource
	private CSrmEnquiryInvitationForBidsTHMapper cSrmEnquiryInvitationForBidsTHMapper;

	@Resource
	private CSrmEnquiryInvitationForBidsTRMapper cSrmEnquiryInvitationForBidsTRMapper;

	@Resource
	private CSrmSupplierMapper cSrmSupplierMapper;


	@Resource
	private CSrmCompanyMapper cSrmCompanyMapper;


	@Resource
	private CSrmEnquiryForBidsTSupplierMapper cSrmEnquiryForBidsTSupplierMapper;

	@Resource
	private CSrmAssessTemplateRMapper cSrmAssessTemplateRMapper;

	@Resource
	private CSrmPurchaseDemandHMapper cSrmPurchaseDemandHMapper;

	@Resource
	private CSrmFileUploadingMapper cSrmFileUploadingMapper;

	@Override
	public int insertOrUpdateSelective(CSrmEnquiryInvitationForBidsTH record) {
		return cSrmEnquiryInvitationForBidsTHMapper.insertOrUpdateSelective(record);
	}

	@Override
	public int insertSelective(CSrmEnquiryInvitationForBidsTH record) {
		return cSrmEnquiryInvitationForBidsTHMapper.insertSelective(record);
	}

	@Override
	public CSrmEnquiryInvitationForBidsTH selectByPrimaryKey(CSrmEnquiryInvitationForBidsTH record) {
		return cSrmEnquiryInvitationForBidsTHMapper.selectByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKeySelective(CSrmEnquiryInvitationForBidsTH record) {
		return cSrmEnquiryInvitationForBidsTHMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateBatchSelective(List<CSrmEnquiryInvitationForBidsTH> list) {
		return cSrmEnquiryInvitationForBidsTHMapper.updateBatchSelective(list);
	}

	@Override
	public int batchInsert(List<CSrmEnquiryInvitationForBidsTH> list) {
		return cSrmEnquiryInvitationForBidsTHMapper.batchInsert(list);
	}

	@Override
	public Rjson updateSearchForTheSourceInfo(CSrmEnquiryInvitationForBidsTHReq cSrmEnquiryInvitationForBidsTHReq) throws ParseException {
		/**
		 * 输入：询价/报价单号、报价/招标截止时间、供应商代码、开标时间、开标地点、操作标记
		 * 处理：更新询价单头表、行表及供应商表中对应字段，针对供应商代码有值的，增加供应商表数据，赋个供应商值就行。
		 */
		// 校验 询价/报价单号是否存在
		CSrmEnquiryInvitationForBidsTH cSrmEnquiryInvitationForBidsTH = new CSrmEnquiryInvitationForBidsTH();
		cSrmEnquiryInvitationForBidsTH.setRfqOrTenderFormCode(cSrmEnquiryInvitationForBidsTHReq.getRfqOrTenderFormCode());
		CSrmEnquiryInvitationForBidsTH selectByPrimaryKey = cSrmEnquiryInvitationForBidsTHMapper.selectByPrimaryKey(cSrmEnquiryInvitationForBidsTH);
		if (null != selectByPrimaryKey) {
			// 修改操作标识
			if (("2").equals(cSrmEnquiryInvitationForBidsTHReq.getOperationSign())) {
				// 修改询价/招标头表
				Integer id = selectByPrimaryKey.getId();
				selectByPrimaryKey = new CSrmEnquiryInvitationForBidsTH();
				BeanUtils.copyProperties(cSrmEnquiryInvitationForBidsTHReq, selectByPrimaryKey);
				selectByPrimaryKey.setId(id);
				selectByPrimaryKey.setQuotationStopTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(cSrmEnquiryInvitationForBidsTHReq.getQuotationStopTime()));
				selectByPrimaryKey.setBidOpeningTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(cSrmEnquiryInvitationForBidsTHReq.getBidOpeningTime()));
				cSrmEnquiryInvitationForBidsTHMapper.updateByPrimaryKeySelective(selectByPrimaryKey);
				// 新增询价/招标供应商表
				CSrmEnquiryForBidsTSupplier cSrmEnquiryForBidsTSupplier = new CSrmEnquiryForBidsTSupplier();
				BeanUtils.copyProperties(cSrmEnquiryInvitationForBidsTHReq, cSrmEnquiryForBidsTSupplier);
				cSrmEnquiryForBidsTSupplierMapper.insertOrUpdateSelective(cSrmEnquiryForBidsTSupplier);
				return Rjson.success("更新成功", null);
				// 新增操作标识
			} else if (("1").equals(cSrmEnquiryInvitationForBidsTHReq.getOperationSign())) {


				return Rjson.success("创建成功", null);
			} else {
				return Rjson.error("操作标识错误");
			}
		} else {
			return Rjson.error("询价/报价单号不存在");
		}
	}

	/**
	 * 文件上传路径
	 */
	@Value(value = "${fileName.srmName}")
	private String pathFile;

	@Override
	public Rjson createForQuotationOrCallForBids(CSrmEnquiryInvitationForBidsTHReq cSrmEnquiryInvitationForBidsTHReq) throws Exception {
/**
 * 处理：生成询价/报价单号（
 * 寻源类型为询价以RFX开头+年月日+3位流水号，
 * 寻源类型为招标以BID开头+年月日+3位流水号
 * ），
 * 更新询价单头表、行表及供应商表，
 * 寻源类型为询价：保存操作将状态设为新建，发布给供应商后变成报价中，供应商报价后达到报价结束时间变成待核价，核价后变成待审批，提交审批变成审批中，审批通过变成已完成；寻源类型为招标，
 * 保存操作将状态设为新建、提交审批变成审批中，审批通过触发发布变成招标中，招标截止时间到了后变成待开标，开标后变成评分中，评分完成后定标变成已完成，同时生成中标公告
 *
 *
 *	c_srm_enquiry_invitation_for_bids_t_h   询价/招标头表(询价/招标单编码、标题、模板编号、状态(1.新建2.报价中3.待核价4.待审批5.已完成6.招标中7.待开标8.评分中)、寻源类型(1.询价2.招标)、报价方式(1.线上报价2.线下报价)、寻源方式(1.邀请2.公开)、公司、币种(1.CNY2.USD)、
 *	报价开始时间、报价截止时间、开标时间、开标地点、负责人、创建人)
 * 	c_srm_enquiry_invitation_for_bids_t_r   询价/招标行表(询价/招标单编码、行号、公司、库存组织、物料编码（支持无编码情况）、物料名称、需求数量、单位、需求日期、图纸图号、采购申请、附件)
 * 	c_srm_enquiry_for_bids_t_supplier 询价/招标供应商表 (询价/招标单编码、行号、供应商编码、是否报价(1.已报价2.未报价)、报价人、报价时间、报价有效期、单价、是否运输费、最小采购量、最小包装量、预计交货时间、分配数量、选用理由、评分人、评分时间、评分要素及分值、是否推荐、推荐意见)
 */

		// 操作标识为3不进行校验
		if (!("3").equals(cSrmEnquiryInvitationForBidsTHReq.getOperationSign())) {
			// 校验公司编码是否存在
			if (null == cSrmCompanyMapper.selectCompanyCode(cSrmEnquiryInvitationForBidsTHReq.getCompanyCode())) {
				return Rjson.error("公司不存在");
			} else if (null == cSrmSupplierMapper.selectSupplierCode(cSrmEnquiryInvitationForBidsTHReq.getSupplierCode().stream().findFirst().orElse(null))) {
				return Rjson.error("供应商不存在");
			}
		}

		CSrmEnquiryInvitationForBidsTH cSrmEnquiryInvitationForBidsTH = null;
		// 新增
		if (("1").equals(cSrmEnquiryInvitationForBidsTHReq.getOperationSign())) {
//            List<String> supplierCode = cSrmEnquiryInvitationForBidsTHReq.getSupplierCode();
//            for (int i = 0; i < cSrmEnquiryInvitationForBidsTHReq.getSupplierCode().size(); i++) {

			// 寻源类型为询价
			if (("1").equals(cSrmEnquiryInvitationForBidsTHReq.getSourcingType())) {
				if (!EqualsUtil.StringEqualsNull(cSrmEnquiryInvitationForBidsTHReq.getRfqOrTenderFormCode())) {
					// 询价/招投标编号为空生成询价/报价单号
					cSrmEnquiryInvitationForBidsTH = cSrmEnquiryInvitationForBidsTHMapper.selectFinallyData();
					String yyyyMMdd = new SimpleDateFormat("yyyyMMdd").format(new Date());
					// 生成询价/报价单号
					if (cSrmEnquiryInvitationForBidsTH == null) {
						// 未找到数据，从最新一条开始
						cSrmEnquiryInvitationForBidsTHReq.setRfqOrTenderFormCode("RFX" + yyyyMMdd + 100);
					} else {
						int rfqOrTenderFormCode = Integer.parseInt(cSrmEnquiryInvitationForBidsTH.getRfqOrTenderFormCode().substring(11)) + 1;
						cSrmEnquiryInvitationForBidsTHReq.setRfqOrTenderFormCode("RFX" + yyyyMMdd + rfqOrTenderFormCode);
					}
				} else {
					// 校验询报价头表数据是否存在
					cSrmEnquiryInvitationForBidsTH = new CSrmEnquiryInvitationForBidsTH();
					cSrmEnquiryInvitationForBidsTH.setRfqOrTenderFormCode(cSrmEnquiryInvitationForBidsTHReq.getRfqOrTenderFormCode());
					CSrmEnquiryInvitationForBidsTH cSrmContractHS = cSrmEnquiryInvitationForBidsTHMapper.selectByPrimaryKey(cSrmEnquiryInvitationForBidsTH);
					if (cSrmContractHS != null) {
						return Rjson.error("创建失败，单号已存在");
					}
				}
				// 招投标
			} else if (("2").equals(cSrmEnquiryInvitationForBidsTHReq.getSourcingType())) {

				if (!EqualsUtil.StringEqualsNull(cSrmEnquiryInvitationForBidsTHReq.getRfqOrTenderFormCode())) {
					// 询价/招投标编号为空生成招投标单号
					cSrmEnquiryInvitationForBidsTH = cSrmEnquiryInvitationForBidsTHMapper.selectFinallyData();
					String yyyyMMdd = new SimpleDateFormat("yyyyMMdd").format(new Date());
					// 生成招投标单号
					if (cSrmEnquiryInvitationForBidsTH == null) {
						// 未找到数据，从最新一条开始
						cSrmEnquiryInvitationForBidsTHReq.setRfqOrTenderFormCode("BID" + yyyyMMdd + 100);
					} else {
						int rfqOrTenderFormCode = Integer.parseInt(cSrmEnquiryInvitationForBidsTH.getRfqOrTenderFormCode().substring(11)) + 1;
						cSrmEnquiryInvitationForBidsTHReq.setRfqOrTenderFormCode("BID" + yyyyMMdd + rfqOrTenderFormCode);
					}
				} else {
					// 校验招投标头表数据是否存在
					cSrmEnquiryInvitationForBidsTH = new CSrmEnquiryInvitationForBidsTH();
					cSrmEnquiryInvitationForBidsTH.setRfqOrTenderFormCode(cSrmEnquiryInvitationForBidsTHReq.getRfqOrTenderFormCode());
					CSrmEnquiryInvitationForBidsTH cSrmContractHS = cSrmEnquiryInvitationForBidsTHMapper.selectByPrimaryKey(cSrmEnquiryInvitationForBidsTH);
					if (cSrmContractHS != null) {
						return Rjson.error("创建失败，单号已存在");
					}

				}
			}


			// 新增询价/招标头表
			cSrmEnquiryInvitationForBidsTH = new CSrmEnquiryInvitationForBidsTH();
			BeanUtils.copyProperties(cSrmEnquiryInvitationForBidsTHReq, cSrmEnquiryInvitationForBidsTH);
			if (StringUtil.eqNu(cSrmEnquiryInvitationForBidsTHReq.getQuotationStartTime())) {
				// 报价开始时间
				cSrmEnquiryInvitationForBidsTH.setQuotationStartTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(cSrmEnquiryInvitationForBidsTHReq.getQuotationStartTime()));
			}

			if (StringUtil.eqNu(cSrmEnquiryInvitationForBidsTHReq.getQuotationStopTime())) {
				// 报价截至时间
				cSrmEnquiryInvitationForBidsTH.setQuotationStopTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(cSrmEnquiryInvitationForBidsTHReq.getQuotationStopTime()));
			}

			cSrmEnquiryInvitationForBidsTH.setCreateTime(new Date());
//            StringBuilder sb = new StringBuilder();
//            cSrmEnquiryInvitationForBidsTHReq.getSupplierCode().forEach(item -> {
//                sb.append(item).append(",");
//            });
//            String supplierCode = sb.substring(0, sb.lastIndexOf(",") - 1);
//            cSrmEnquiryInvitationForBidsTH.setSupplierCode(supplierCode);
			cSrmEnquiryInvitationForBidsTH.setSupplierCode(cSrmEnquiryInvitationForBidsTHReq.getSupplierCode().stream().findFirst().orElse(null));
			cSrmEnquiryInvitationForBidsTHMapper.insertSelective(cSrmEnquiryInvitationForBidsTH);
			// 新增询价/招标行表
			// 存储新文件数据
			List<CSrmFileUploading> addList = new ArrayList<>();
			if (!CollectionUtils.isEmpty(cSrmEnquiryInvitationForBidsTHReq.getRfqList())) {
				for (CSrmEnquiryInvitationForBidsTRReq item : cSrmEnquiryInvitationForBidsTHReq.getRfqList()) {
					EqualsPoJoUtil.string(item.getPurchaseRequest(), "采购需求单号");
					// 校验采购需求单号是否存在
					CSrmPurchaseDemandH cSrmPurchaseDemandH = new CSrmPurchaseDemandH();
					cSrmPurchaseDemandH.setRequestCode(item.getPurchaseRequest());
					CSrmPurchaseDemandH cSrmPurchaseDemandH1 = cSrmPurchaseDemandHMapper.selectByPrimaryKey(cSrmPurchaseDemandH);
					if (cSrmPurchaseDemandH1 == null) {
						return Rjson.error("采购需求单号不存在");
					} else {
						// 已分配
						if (!("4").equals(cSrmPurchaseDemandH1.getStatus())) {
							return Rjson.error("创建失败，采购需求单号不处于已分配");
						}
					}

					// 给当前数据行赋值询价/招投标单号
					item.setRfqOrTenderFormCode(cSrmEnquiryInvitationForBidsTHReq.getRfqOrTenderFormCode());
					// 生成项目行号
					CSrmEnquiryInvitationForBidsTR cSrmEnquiryInvitationForBidsTR = cSrmEnquiryInvitationForBidsTRMapper.selectFinallyData(cSrmEnquiryInvitationForBidsTHReq.getRfqOrTenderFormCode());
					if (cSrmEnquiryInvitationForBidsTR == null) {
						item.setLineNumber("1");
					} else {
						int lineItemNo = Integer.parseInt(cSrmEnquiryInvitationForBidsTR.getLineNumber()) + 1;
						item.setLineNumber(String.valueOf(lineItemNo));
					}
					// 新增行数据
					cSrmEnquiryInvitationForBidsTR = new CSrmEnquiryInvitationForBidsTR();
					BeanUtils.copyProperties(item, cSrmEnquiryInvitationForBidsTR);
					cSrmEnquiryInvitationForBidsTR.setCompanyCode(cSrmEnquiryInvitationForBidsTHReq.getSupplierCode().stream().findFirst().orElse(null));
					// 需求日期转换
					if (StringUtil.eqNu(item.getRequiredDate())) {
						cSrmEnquiryInvitationForBidsTR.setDemandedDate(new SimpleDateFormat("yyyy-MM-dd").parse(item.getRequiredDate()));
					}
					cSrmEnquiryInvitationForBidsTR.setCreateTime(new Date());
					cSrmEnquiryInvitationForBidsTRMapper.insertSelective(cSrmEnquiryInvitationForBidsTR);

					// 校验原单行号是否有值   新增行值是空的
					if (StringUtil.eqNu(item.getRowProjectNumber())) {
						// 获取文件
						CSrmFileUploadingReq cSrmFileUploadingReq = new CSrmFileUploadingReq();
						cSrmFileUploadingReq.setOrderNumber(item.getPurchaseRequest());
						cSrmFileUploadingReq.setLineNumber(item.getRowProjectNumber());
						List<CSrmFileUploadingRsp> cSrmFileUploadingRsps = cSrmFileUploadingMapper.selectByPrimaryKey(cSrmFileUploadingReq);
						if (!CollectionUtils.isEmpty(cSrmFileUploadingRsps)) {
							cSrmFileUploadingRsps.forEach(oldFilePath -> {
								// 替换文件路径中的模块名
								oldFilePath.setFilePath(oldFilePath.getFilePath().replaceFirst("采购需求管理", "询价信息"));
								// 替换文件路径中的单号
								oldFilePath.setFilePath(oldFilePath.getFilePath().replaceFirst(item.getPurchaseRequest(), cSrmEnquiryInvitationForBidsTHReq.getRfqOrTenderFormCode()));
								oldFilePath.setOrderNumber(cSrmEnquiryInvitationForBidsTHReq.getRfqOrTenderFormCode());
								oldFilePath.setLineNumber(item.getLineNumber());
								CSrmFileUploading cSrmFileUploading = new CSrmFileUploading();
								BeanUtils.copyProperties(oldFilePath, cSrmFileUploading);
								addList.add(cSrmFileUploading);
							});
						}


					}

				}
//                }
			}

			if (!CollectionUtils.isEmpty(addList)) {
				// 根据单号去重
				ArrayList<CSrmEnquiryInvitationForBidsTRReq> collect = cSrmEnquiryInvitationForBidsTHReq.getRfqList().stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(
					Comparator.comparing(CSrmEnquiryInvitationForBidsTRReq::getPurchaseRequest)
				)), ArrayList::new));
				collect.forEach(item -> {
					// 文件夹内容Copy
//                    Properties pps = new Properties();
					try {
//                        pps.load(BusinessInfoFileController.class.getClassLoader().getResourceAsStream("/config.properties"));
//                        String pathFile = pps.getProperty("SRMFile");

						// 存储linux路径或window
						String oldFilePath = "";
						String newFilePath = "";
						// true：Linux,false：Window
						if (isOSLinux()) {
							oldFilePath = pathFile + "/" + "采购需求管理/" + item.getPurchaseRequest();
							newFilePath = pathFile + "/" + "询价信息/" + item.getRfqOrTenderFormCode();
						} else {
							oldFilePath = pathFile + "\\" + "采购需求管理\\" + item.getPurchaseRequest();
							newFilePath = pathFile + "\\" + "询价信息\\" + item.getRfqOrTenderFormCode();

						}
//                        String oldFilePath = pathFile+"\\" + "采购需求管理\\" + item.getPurchaseRequest();
//                        String newFilePath = pathFile +"\\"+ "询价信息\\" + item.getRfqOrTenderFormCode();
						(new File(newFilePath)).mkdirs(); //如果文件夹不存在 则建立新文件夹
						FileUploadUtils.copyFolder(oldFilePath, newFilePath);
					} catch (IOException e) {
						e.printStackTrace();
					}
				});

				// 批量新增文件表
				cSrmFileUploadingMapper.batchInsert(addList);
			}


			return Rjson.success("创建成功", cSrmEnquiryInvitationForBidsTHReq);
			// 修改
		} else if (("2").equals(cSrmEnquiryInvitationForBidsTHReq.getOperationSign())) {
			// 校验询价/报价单号是否存在
			cSrmEnquiryInvitationForBidsTH = new CSrmEnquiryInvitationForBidsTH();
			cSrmEnquiryInvitationForBidsTH.setRfqOrTenderFormCode(cSrmEnquiryInvitationForBidsTHReq.getRfqOrTenderFormCode());
			CSrmEnquiryInvitationForBidsTH selectByPrimaryKey = cSrmEnquiryInvitationForBidsTHMapper.selectByPrimaryKey(cSrmEnquiryInvitationForBidsTH);
			if (null != selectByPrimaryKey) {
				// 修改询价/招标头表
				cSrmEnquiryInvitationForBidsTH = new CSrmEnquiryInvitationForBidsTH();
				BeanUtils.copyProperties(cSrmEnquiryInvitationForBidsTHReq, cSrmEnquiryInvitationForBidsTH);
				cSrmEnquiryInvitationForBidsTH.setUpdateTime(new Date());
				cSrmEnquiryInvitationForBidsTHMapper.updateByPrimaryKeySelective(cSrmEnquiryInvitationForBidsTH);
				// 修改询价/招标行表
				if (!CollectionUtils.isEmpty(cSrmEnquiryInvitationForBidsTHReq.getRfqList())) {
					// 删除之前存储的行数据  --之前的数据不需要了,物理删除--
					cSrmEnquiryInvitationForBidsTRMapper.delData(cSrmEnquiryInvitationForBidsTHReq.getRfqOrTenderFormCode());
					for (CSrmEnquiryInvitationForBidsTRReq item : cSrmEnquiryInvitationForBidsTHReq.getRfqList()) {
						CSrmEnquiryInvitationForBidsTR cSrmEnquiryInvitationForBidsTR = null;
						if (!StringUtil.eqNu(item.getLineNumber())) {
							// 生成行号
							cSrmEnquiryInvitationForBidsTR = cSrmEnquiryInvitationForBidsTRMapper.selectFinallyData(cSrmEnquiryInvitationForBidsTHReq.getRfqOrTenderFormCode());
							if (cSrmEnquiryInvitationForBidsTR == null) {
								item.setLineNumber("1");
							} else {
								int lineItemNo = Integer.parseInt(cSrmEnquiryInvitationForBidsTR.getLineNumber()) + 1;
								item.setLineNumber(String.valueOf(lineItemNo));
							}
						}
						cSrmEnquiryInvitationForBidsTR = new CSrmEnquiryInvitationForBidsTR();
						item.setRfqOrTenderFormCode(cSrmEnquiryInvitationForBidsTHReq.getRfqOrTenderFormCode());
						BeanUtils.copyProperties(item, cSrmEnquiryInvitationForBidsTR);
						// 需求日期转换
						if (StringUtil.eqNu(item.getRequiredDate())) {
							cSrmEnquiryInvitationForBidsTR.setDemandedDate(new SimpleDateFormat("yyyy-MM-dd").parse(item.getRequiredDate()));
						}
						cSrmEnquiryInvitationForBidsTR.setCreateTime(new Date());
						cSrmEnquiryInvitationForBidsTRMapper.insertSelective(cSrmEnquiryInvitationForBidsTR);
					}
				}
				// 新增询价/招标供应商表
				if (!CollectionUtils.isEmpty(cSrmEnquiryInvitationForBidsTHReq.getSupList())) {
					// 删除旧数据
					cSrmEnquiryForBidsTSupplierMapper.delData(cSrmEnquiryInvitationForBidsTHReq.getRfqOrTenderFormCode());
					for (CSrmEnquiryForBidsTSupplierReq item : cSrmEnquiryInvitationForBidsTHReq.getSupList()) {
						CSrmEnquiryForBidsTSupplier cSrmEnquiryForBidsTSupplier = null;
						if (!StringUtil.eqNu(item.getLineNumber())) {
							// 生成项目行号
							cSrmEnquiryForBidsTSupplier = cSrmEnquiryForBidsTSupplierMapper.selectFinallyData(cSrmEnquiryInvitationForBidsTHReq.getRfqOrTenderFormCode());
							if (null == cSrmEnquiryForBidsTSupplier) {
								item.setLineNumber("01");
							} else {
								int lineItemNo = Integer.parseInt(cSrmEnquiryForBidsTSupplier.getLineNumber().substring(1)) + 1;
								item.setLineNumber("0" + lineItemNo);

							}
						}
						cSrmEnquiryForBidsTSupplier = new CSrmEnquiryForBidsTSupplier();
						item.setRfqOrTenderFormCode(cSrmEnquiryInvitationForBidsTHReq.getRfqOrTenderFormCode());
						BeanUtils.copyProperties(item, cSrmEnquiryForBidsTSupplier);
						cSrmEnquiryForBidsTSupplier.setSupplierCode(cSrmEnquiryInvitationForBidsTHReq.getSupplierCode().stream().findFirst().orElse(null));
						cSrmEnquiryForBidsTSupplier.setCreateTime(new Date());
						if (StringUtil.eqNu(item.getOfferTime())) {
							// 报价时间
							cSrmEnquiryForBidsTSupplier.setOfferTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(item.getOfferTime()));
						}
						if (StringUtil.eqNu(item.getOfferPeriodOfValidity())) {
							// 报价有效期
							cSrmEnquiryForBidsTSupplier.setOfferPeriodOfValidity(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(item.getOfferPeriodOfValidity()));
						}
						if (StringUtil.eqNu(item.getEstimatedTimeOfDelivery())) {
							// 预计交货时间
							cSrmEnquiryForBidsTSupplier.setEstimatedTimeOfDelivery(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(item.getEstimatedTimeOfDelivery()));
						}
						if (StringUtil.eqNu(cSrmEnquiryForBidsTSupplier.getUnitPrice())) {
							// 设置为已报价
							cSrmEnquiryForBidsTSupplier.setWhetherOffer("1");
						}
						cSrmEnquiryForBidsTSupplierMapper.insertSelective(cSrmEnquiryForBidsTSupplier);
					}

				}
				return Rjson.success("更新成功", cSrmEnquiryInvitationForBidsTHReq);
			} else {
				return Rjson.error("操作失败，单号不存在");
			}

		} else if (("3").equals(cSrmEnquiryInvitationForBidsTHReq.getOperationSign())) {
			// 变更状态
			// 校验询价/报价单号是否存在
			cSrmEnquiryInvitationForBidsTH = new CSrmEnquiryInvitationForBidsTH();
			cSrmEnquiryInvitationForBidsTH.setRfqOrTenderFormCode(cSrmEnquiryInvitationForBidsTHReq.getRfqOrTenderFormCode());
			CSrmEnquiryInvitationForBidsTH selectByPrimaryKey = cSrmEnquiryInvitationForBidsTHMapper.selectByPrimaryKey(cSrmEnquiryInvitationForBidsTH);
			if (null != selectByPrimaryKey) {
				// 修改询价/招标头表
				cSrmEnquiryInvitationForBidsTH = new CSrmEnquiryInvitationForBidsTH();
				BeanUtils.copyProperties(cSrmEnquiryInvitationForBidsTHReq, cSrmEnquiryInvitationForBidsTH);
				cSrmEnquiryInvitationForBidsTH.setUpdateTime(new Date());
				cSrmEnquiryInvitationForBidsTHMapper.updateByPrimaryKeySelective(cSrmEnquiryInvitationForBidsTH);
			} else {
				return Rjson.error("操作失败，单号不存在");
			}
			return Rjson.success("更新成功", null);
		} else {
			return Rjson.error("操作标识错误");
		}

	}


	@Override
	public Rjson findForQuotationOrCallForBidsH(CSrmEnquiryInvitationForBidsTHReq req) {

		// 校验供应商代码是否存在
		if (req.getSupplierCode() != null && StringUtil.eqNu(req.getSupplierCode().get(0))) {
			// 供应商报价查询入口
			CSrmSupplier cSrmSupplier = cSrmSupplierMapper.selectSupplierCode(req.getSupplierCode().stream().findFirst().orElse(null));
			if (cSrmSupplier == null) {
				return Rjson.error("供应商代码不存在");
			}
		}
		List<CSrmEnquiryInvitationForBidsTHRsps> forQuotationOrCallForBidsH = null;
		// 条件查询 物料编码有值
		if (StringUtil.eqNu(req.getMaterialCode())) {
			PageHelper.startPage(req.getPageNum(), req.getPageSize());
			forQuotationOrCallForBidsH = cSrmEnquiryInvitationForBidsTHMapper.findForQuotationOrCallForBidsHS(req);
		} else {
			PageHelper.startPage(req.getPageNum(), req.getPageSize());
			forQuotationOrCallForBidsH = cSrmEnquiryInvitationForBidsTHMapper.findForQuotationOrCallForBidsH(req);
		}
		return Rjson.success(new PageInfo<>(forQuotationOrCallForBidsH));
	}

	@Override
	public Rjson findForQuotationOrCallForBidsHS(CSrmEnquiryInvitationForBidsTHReq req) {
		PageHelper.startPage(req.getPageNum(), req.getPageSize());
		return Rjson.success(new PageInfo<>(cSrmEnquiryInvitationForBidsTHMapper.findForQuotationOrCallForBidsHS(req)));
	}

	@Override
	public Rjson findForQuotationOrCallForBidsHR(CSrmEnquiryInvitationForBidsTHReq req) {

		CSrmEnquiryInvitationForBidsTHRsp rsp = null;
		List<CSrmEnquiryInvitationForBidsTHRsp> rspList = null;
		rsp = new CSrmEnquiryInvitationForBidsTHRsp();
		rspList = new ArrayList<>();
		// 查询寻报价头数据
		List<CSrmEnquiryInvitationForBidsTHRsps> forBidsH = cSrmEnquiryInvitationForBidsTHMapper.findForQuotationOrCallForBidsH(req);
		if (forBidsH.size() == 0) {
			return Rjson.success();
		} else {

			for (CSrmEnquiryInvitationForBidsTHRsps rspItem : forBidsH) {
				BeanUtils.copyProperties(rspItem, rsp);
				// 供应商代码是List<String> 类型参数
				List<String> supplierCode = new ArrayList<>();
				supplierCode.add(rspItem.getSupplierCode());
				rsp.setSupplierCode(supplierCode);
			}

			// 查询寻报价行数据
			// 校验单号是否已报价
			List<CSrmEnquiryForBidsTSupplierRsp> cSrmEnquiryForBidsTSupplierRsps = cSrmEnquiryForBidsTSupplierMapper.selectByPrimaryList(req);
			PageHelper.startPage(req.getPageNum(), req.getPageSize());
			if (cSrmEnquiryForBidsTSupplierRsps.size() > 0) {
				rsp.setRfqList(new PageInfo<>(cSrmEnquiryInvitationForBidsTRMapper.selectByPrimaryList(req)));
			} else {
				rsp.setRfqList(new PageInfo<>(cSrmEnquiryInvitationForBidsTRMapper.selectByPrimaryListNoOffer(req)));
			}
			rspList.add(rsp);
		}
		return Rjson.success(rspList);
	}


	@Override
	public Rjson findUpdateCall(CSrmEnquiryInvitationForBidsTHReq req) {
		PageHelper.startPage(req.getPageNum(), req.getPageSize());
		return Rjson.success(new PageInfo<>(cSrmEnquiryInvitationForBidsTHMapper.findUpdateCall(req)));
	}

	@Override
	public Rjson updateCall(CSrmEnquiryInvitationForBidsTHReq cSrmEnquiryInvitationForBidsTHReq) throws Exception {
		CSrmEnquiryInvitationForBidsTH cSrmEnquiryInvitationForBidsTH = new CSrmEnquiryInvitationForBidsTH();
		cSrmEnquiryInvitationForBidsTH.setRfqOrTenderFormCode(cSrmEnquiryInvitationForBidsTHReq.getRfqOrTenderFormCode());
		if (cSrmEnquiryInvitationForBidsTHMapper.selectByPrimaryKey(cSrmEnquiryInvitationForBidsTH) == null) {
			return Rjson.error("单号不存在");
		} else {
			cSrmEnquiryInvitationForBidsTH = new CSrmEnquiryInvitationForBidsTH();
			cSrmEnquiryInvitationForBidsTH.setRfqOrTenderFormCode(cSrmEnquiryInvitationForBidsTHReq.getRfqOrTenderFormCode());
			// 寻源类型(1.询价2.招投标)
			// 状态(1.询价新建2.询价报价中3.询价待核价4.询价待审批5.投标新建6.投标审批中7.投标中8.待开标9.评分10. 待定标11.已完成)
			// 1.询报价
			if (StringUtil.eqNu(cSrmEnquiryInvitationForBidsTHReq.getName())) {
				System.err.println("name的值" + cSrmEnquiryInvitationForBidsTHReq.getName());
				if (("1").equals(cSrmEnquiryInvitationForBidsTHReq.getSourcingType())) {

					if (("通过").equals(cSrmEnquiryInvitationForBidsTHReq.getName())) {
						cSrmEnquiryInvitationForBidsTH.setStatus("11");

					} else if (("驳回").equals(cSrmEnquiryInvitationForBidsTHReq.getName())) {
						cSrmEnquiryInvitationForBidsTH.setStatus("3");

					} else if (("撤销").equals(cSrmEnquiryInvitationForBidsTHReq.getName())) {
//                        cSrmEnquiryInvitationForBidsTH.setStatus("");
					}
					// 2.招投标
				} else if (("2").equals(cSrmEnquiryInvitationForBidsTHReq.getSourcingType())) {

					if (("通过").equals(cSrmEnquiryInvitationForBidsTHReq.getName())) {
						cSrmEnquiryInvitationForBidsTH.setStatus("11");

					} else if (("驳回").equals(cSrmEnquiryInvitationForBidsTHReq.getName())) {
						cSrmEnquiryInvitationForBidsTH.setStatus("5");

					} else if (("撤销").equals(cSrmEnquiryInvitationForBidsTHReq.getName())) {
//                        cSrmEnquiryInvitationForBidsTH.setStatus("");
					}
				}
			} else {
				EqualsPoJoUtil.integer(cSrmEnquiryInvitationForBidsTHReq.getStatus(), "状态", true);
				if (Integer.parseInt(cSrmEnquiryInvitationForBidsTHReq.getStatus()) > 11) {
					return Rjson.error("'status'超出范围值");
				}
				cSrmEnquiryInvitationForBidsTH.setStatus(cSrmEnquiryInvitationForBidsTHReq.getStatus());
			}
			cSrmEnquiryInvitationForBidsTHMapper.updateByPrimaryKeySelective(cSrmEnquiryInvitationForBidsTH);
			return Rjson.success();


		}

	}

	@Override
	public Rjson findBidOpening(CSrmEnquiryInvitationForBidsTHReq req) {
		PageHelper.startPage(req.getPageNum(), req.getPageSize());
		return Rjson.success(new PageInfo<>(cSrmEnquiryInvitationForBidsTHMapper.findBidOpening(req)));
	}

	@Override
	public Rjson expertRating(CSrmEnquiryInvitationForBidsTHReq req) {
		PageHelper.startPage(req.getPageNum(), req.getPageSize());
		return Rjson.success(new PageInfo<>(cSrmEnquiryInvitationForBidsTHMapper.expertRating(req)));
	}

	@Override
	public Rjson scalingQuery(CSrmEnquiryInvitationForBidsTHReq req) {
		PageHelper.startPage(req.getPageNum(), req.getPageSize());
		return Rjson.success(new PageInfo<>(cSrmEnquiryInvitationForBidsTHMapper.scalingQuery(req)));
	}

	@Override
	public Rjson findExamineNumber(CSrmEnquiryInvitationForBidsTHReq req) {

		JSONObject json = new JSONObject();
		json.put("examineNumber", cSrmEnquiryInvitationForBidsTHMapper.findExamineNumber(req));
		return Rjson.success(json);
	}
}



