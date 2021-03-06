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
		 * ???????????????/?????????????????????/?????????????????????????????????????????????????????????????????????????????????
		 * ???????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
		 */
		// ?????? ??????/????????????????????????
		CSrmEnquiryInvitationForBidsTH cSrmEnquiryInvitationForBidsTH = new CSrmEnquiryInvitationForBidsTH();
		cSrmEnquiryInvitationForBidsTH.setRfqOrTenderFormCode(cSrmEnquiryInvitationForBidsTHReq.getRfqOrTenderFormCode());
		CSrmEnquiryInvitationForBidsTH selectByPrimaryKey = cSrmEnquiryInvitationForBidsTHMapper.selectByPrimaryKey(cSrmEnquiryInvitationForBidsTH);
		if (null != selectByPrimaryKey) {
			// ??????????????????
			if (("2").equals(cSrmEnquiryInvitationForBidsTHReq.getOperationSign())) {
				// ????????????/????????????
				Integer id = selectByPrimaryKey.getId();
				selectByPrimaryKey = new CSrmEnquiryInvitationForBidsTH();
				BeanUtils.copyProperties(cSrmEnquiryInvitationForBidsTHReq, selectByPrimaryKey);
				selectByPrimaryKey.setId(id);
				selectByPrimaryKey.setQuotationStopTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(cSrmEnquiryInvitationForBidsTHReq.getQuotationStopTime()));
				selectByPrimaryKey.setBidOpeningTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(cSrmEnquiryInvitationForBidsTHReq.getBidOpeningTime()));
				cSrmEnquiryInvitationForBidsTHMapper.updateByPrimaryKeySelective(selectByPrimaryKey);
				// ????????????/??????????????????
				CSrmEnquiryForBidsTSupplier cSrmEnquiryForBidsTSupplier = new CSrmEnquiryForBidsTSupplier();
				BeanUtils.copyProperties(cSrmEnquiryInvitationForBidsTHReq, cSrmEnquiryForBidsTSupplier);
				cSrmEnquiryForBidsTSupplierMapper.insertOrUpdateSelective(cSrmEnquiryForBidsTSupplier);
				return Rjson.success("????????????", null);
				// ??????????????????
			} else if (("1").equals(cSrmEnquiryInvitationForBidsTHReq.getOperationSign())) {


				return Rjson.success("????????????", null);
			} else {
				return Rjson.error("??????????????????");
			}
		} else {
			return Rjson.error("??????/?????????????????????");
		}
	}

	/**
	 * ??????????????????
	 */
	@Value(value = "${fileName.srmName}")
	private String pathFile;

	@Override
	public Rjson createForQuotationOrCallForBids(CSrmEnquiryInvitationForBidsTHReq cSrmEnquiryInvitationForBidsTHReq) throws Exception {
/**
 * ?????????????????????/???????????????
 * ????????????????????????RFX??????+?????????+3???????????????
 * ????????????????????????BID??????+?????????+3????????????
 * ??????
 * ????????????????????????????????????????????????
 * ??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
 * ???????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
 *
 *
 *	c_srm_enquiry_invitation_for_bids_t_h   ??????/????????????(??????/????????????????????????????????????????????????(1.??????2.?????????3.?????????4.?????????5.?????????6.?????????7.?????????8.?????????)???????????????(1.??????2.??????)???????????????(1.????????????2.????????????)???????????????(1.??????2.??????)??????????????????(1.CNY2.USD)???
 *	?????????????????????????????????????????????????????????????????????????????????????????????)
 * 	c_srm_enquiry_invitation_for_bids_t_r   ??????/????????????(??????/???????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????)
 * 	c_srm_enquiry_for_bids_t_supplier ??????/?????????????????? (??????/?????????????????????????????????????????????????????????(1.?????????2.?????????)????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????)
 */

		// ???????????????3???????????????
		if (!("3").equals(cSrmEnquiryInvitationForBidsTHReq.getOperationSign())) {
			// ??????????????????????????????
			if (null == cSrmCompanyMapper.selectCompanyCode(cSrmEnquiryInvitationForBidsTHReq.getCompanyCode())) {
				return Rjson.error("???????????????");
			} else if (null == cSrmSupplierMapper.selectSupplierCode(cSrmEnquiryInvitationForBidsTHReq.getSupplierCode().stream().findFirst().orElse(null))) {
				return Rjson.error("??????????????????");
			}
		}

		CSrmEnquiryInvitationForBidsTH cSrmEnquiryInvitationForBidsTH = null;
		// ??????
		if (("1").equals(cSrmEnquiryInvitationForBidsTHReq.getOperationSign())) {
//            List<String> supplierCode = cSrmEnquiryInvitationForBidsTHReq.getSupplierCode();
//            for (int i = 0; i < cSrmEnquiryInvitationForBidsTHReq.getSupplierCode().size(); i++) {

			// ?????????????????????
			if (("1").equals(cSrmEnquiryInvitationForBidsTHReq.getSourcingType())) {
				if (!EqualsUtil.StringEqualsNull(cSrmEnquiryInvitationForBidsTHReq.getRfqOrTenderFormCode())) {
					// ??????/?????????????????????????????????/????????????
					cSrmEnquiryInvitationForBidsTH = cSrmEnquiryInvitationForBidsTHMapper.selectFinallyData();
					String yyyyMMdd = new SimpleDateFormat("yyyyMMdd").format(new Date());
					// ????????????/????????????
					if (cSrmEnquiryInvitationForBidsTH == null) {
						// ???????????????????????????????????????
						cSrmEnquiryInvitationForBidsTHReq.setRfqOrTenderFormCode("RFX" + yyyyMMdd + 100);
					} else {
						int rfqOrTenderFormCode = Integer.parseInt(cSrmEnquiryInvitationForBidsTH.getRfqOrTenderFormCode().substring(11)) + 1;
						cSrmEnquiryInvitationForBidsTHReq.setRfqOrTenderFormCode("RFX" + yyyyMMdd + rfqOrTenderFormCode);
					}
				} else {
					// ???????????????????????????????????????
					cSrmEnquiryInvitationForBidsTH = new CSrmEnquiryInvitationForBidsTH();
					cSrmEnquiryInvitationForBidsTH.setRfqOrTenderFormCode(cSrmEnquiryInvitationForBidsTHReq.getRfqOrTenderFormCode());
					CSrmEnquiryInvitationForBidsTH cSrmContractHS = cSrmEnquiryInvitationForBidsTHMapper.selectByPrimaryKey(cSrmEnquiryInvitationForBidsTH);
					if (cSrmContractHS != null) {
						return Rjson.error("??????????????????????????????");
					}
				}
				// ?????????
			} else if (("2").equals(cSrmEnquiryInvitationForBidsTHReq.getSourcingType())) {

				if (!EqualsUtil.StringEqualsNull(cSrmEnquiryInvitationForBidsTHReq.getRfqOrTenderFormCode())) {
					// ??????/??????????????????????????????????????????
					cSrmEnquiryInvitationForBidsTH = cSrmEnquiryInvitationForBidsTHMapper.selectFinallyData();
					String yyyyMMdd = new SimpleDateFormat("yyyyMMdd").format(new Date());
					// ?????????????????????
					if (cSrmEnquiryInvitationForBidsTH == null) {
						// ???????????????????????????????????????
						cSrmEnquiryInvitationForBidsTHReq.setRfqOrTenderFormCode("BID" + yyyyMMdd + 100);
					} else {
						int rfqOrTenderFormCode = Integer.parseInt(cSrmEnquiryInvitationForBidsTH.getRfqOrTenderFormCode().substring(11)) + 1;
						cSrmEnquiryInvitationForBidsTHReq.setRfqOrTenderFormCode("BID" + yyyyMMdd + rfqOrTenderFormCode);
					}
				} else {
					// ???????????????????????????????????????
					cSrmEnquiryInvitationForBidsTH = new CSrmEnquiryInvitationForBidsTH();
					cSrmEnquiryInvitationForBidsTH.setRfqOrTenderFormCode(cSrmEnquiryInvitationForBidsTHReq.getRfqOrTenderFormCode());
					CSrmEnquiryInvitationForBidsTH cSrmContractHS = cSrmEnquiryInvitationForBidsTHMapper.selectByPrimaryKey(cSrmEnquiryInvitationForBidsTH);
					if (cSrmContractHS != null) {
						return Rjson.error("??????????????????????????????");
					}

				}
			}


			// ????????????/????????????
			cSrmEnquiryInvitationForBidsTH = new CSrmEnquiryInvitationForBidsTH();
			BeanUtils.copyProperties(cSrmEnquiryInvitationForBidsTHReq, cSrmEnquiryInvitationForBidsTH);
			if (StringUtil.eqNu(cSrmEnquiryInvitationForBidsTHReq.getQuotationStartTime())) {
				// ??????????????????
				cSrmEnquiryInvitationForBidsTH.setQuotationStartTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(cSrmEnquiryInvitationForBidsTHReq.getQuotationStartTime()));
			}

			if (StringUtil.eqNu(cSrmEnquiryInvitationForBidsTHReq.getQuotationStopTime())) {
				// ??????????????????
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
			// ????????????/????????????
			// ?????????????????????
			List<CSrmFileUploading> addList = new ArrayList<>();
			if (!CollectionUtils.isEmpty(cSrmEnquiryInvitationForBidsTHReq.getRfqList())) {
				for (CSrmEnquiryInvitationForBidsTRReq item : cSrmEnquiryInvitationForBidsTHReq.getRfqList()) {
					EqualsPoJoUtil.string(item.getPurchaseRequest(), "??????????????????");
					// ????????????????????????????????????
					CSrmPurchaseDemandH cSrmPurchaseDemandH = new CSrmPurchaseDemandH();
					cSrmPurchaseDemandH.setRequestCode(item.getPurchaseRequest());
					CSrmPurchaseDemandH cSrmPurchaseDemandH1 = cSrmPurchaseDemandHMapper.selectByPrimaryKey(cSrmPurchaseDemandH);
					if (cSrmPurchaseDemandH1 == null) {
						return Rjson.error("???????????????????????????");
					} else {
						// ?????????
						if (!("4").equals(cSrmPurchaseDemandH1.getStatus())) {
							return Rjson.error("???????????????????????????????????????????????????");
						}
					}

					// ??????????????????????????????/???????????????
					item.setRfqOrTenderFormCode(cSrmEnquiryInvitationForBidsTHReq.getRfqOrTenderFormCode());
					// ??????????????????
					CSrmEnquiryInvitationForBidsTR cSrmEnquiryInvitationForBidsTR = cSrmEnquiryInvitationForBidsTRMapper.selectFinallyData(cSrmEnquiryInvitationForBidsTHReq.getRfqOrTenderFormCode());
					if (cSrmEnquiryInvitationForBidsTR == null) {
						item.setLineNumber("1");
					} else {
						int lineItemNo = Integer.parseInt(cSrmEnquiryInvitationForBidsTR.getLineNumber()) + 1;
						item.setLineNumber(String.valueOf(lineItemNo));
					}
					// ???????????????
					cSrmEnquiryInvitationForBidsTR = new CSrmEnquiryInvitationForBidsTR();
					BeanUtils.copyProperties(item, cSrmEnquiryInvitationForBidsTR);
					cSrmEnquiryInvitationForBidsTR.setCompanyCode(cSrmEnquiryInvitationForBidsTHReq.getSupplierCode().stream().findFirst().orElse(null));
					// ??????????????????
					if (StringUtil.eqNu(item.getRequiredDate())) {
						cSrmEnquiryInvitationForBidsTR.setDemandedDate(new SimpleDateFormat("yyyy-MM-dd").parse(item.getRequiredDate()));
					}
					cSrmEnquiryInvitationForBidsTR.setCreateTime(new Date());
					cSrmEnquiryInvitationForBidsTRMapper.insertSelective(cSrmEnquiryInvitationForBidsTR);

					// ??????????????????????????????   ?????????????????????
					if (StringUtil.eqNu(item.getRowProjectNumber())) {
						// ????????????
						CSrmFileUploadingReq cSrmFileUploadingReq = new CSrmFileUploadingReq();
						cSrmFileUploadingReq.setOrderNumber(item.getPurchaseRequest());
						cSrmFileUploadingReq.setLineNumber(item.getRowProjectNumber());
						List<CSrmFileUploadingRsp> cSrmFileUploadingRsps = cSrmFileUploadingMapper.selectByPrimaryKey(cSrmFileUploadingReq);
						if (!CollectionUtils.isEmpty(cSrmFileUploadingRsps)) {
							cSrmFileUploadingRsps.forEach(oldFilePath -> {
								// ?????????????????????????????????
								oldFilePath.setFilePath(oldFilePath.getFilePath().replaceFirst("??????????????????", "????????????"));
								// ??????????????????????????????
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
				// ??????????????????
				ArrayList<CSrmEnquiryInvitationForBidsTRReq> collect = cSrmEnquiryInvitationForBidsTHReq.getRfqList().stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(
					Comparator.comparing(CSrmEnquiryInvitationForBidsTRReq::getPurchaseRequest)
				)), ArrayList::new));
				collect.forEach(item -> {
					// ???????????????Copy
//                    Properties pps = new Properties();
					try {
//                        pps.load(BusinessInfoFileController.class.getClassLoader().getResourceAsStream("/config.properties"));
//                        String pathFile = pps.getProperty("SRMFile");

						// ??????linux?????????window
						String oldFilePath = "";
						String newFilePath = "";
						// true???Linux,false???Window
						if (isOSLinux()) {
							oldFilePath = pathFile + "/" + "??????????????????/" + item.getPurchaseRequest();
							newFilePath = pathFile + "/" + "????????????/" + item.getRfqOrTenderFormCode();
						} else {
							oldFilePath = pathFile + "\\" + "??????????????????\\" + item.getPurchaseRequest();
							newFilePath = pathFile + "\\" + "????????????\\" + item.getRfqOrTenderFormCode();

						}
//                        String oldFilePath = pathFile+"\\" + "??????????????????\\" + item.getPurchaseRequest();
//                        String newFilePath = pathFile +"\\"+ "????????????\\" + item.getRfqOrTenderFormCode();
						(new File(newFilePath)).mkdirs(); //???????????????????????? ?????????????????????
						FileUploadUtils.copyFolder(oldFilePath, newFilePath);
					} catch (IOException e) {
						e.printStackTrace();
					}
				});

				// ?????????????????????
				cSrmFileUploadingMapper.batchInsert(addList);
			}


			return Rjson.success("????????????", cSrmEnquiryInvitationForBidsTHReq);
			// ??????
		} else if (("2").equals(cSrmEnquiryInvitationForBidsTHReq.getOperationSign())) {
			// ????????????/????????????????????????
			cSrmEnquiryInvitationForBidsTH = new CSrmEnquiryInvitationForBidsTH();
			cSrmEnquiryInvitationForBidsTH.setRfqOrTenderFormCode(cSrmEnquiryInvitationForBidsTHReq.getRfqOrTenderFormCode());
			CSrmEnquiryInvitationForBidsTH selectByPrimaryKey = cSrmEnquiryInvitationForBidsTHMapper.selectByPrimaryKey(cSrmEnquiryInvitationForBidsTH);
			if (null != selectByPrimaryKey) {
				// ????????????/????????????
				cSrmEnquiryInvitationForBidsTH = new CSrmEnquiryInvitationForBidsTH();
				BeanUtils.copyProperties(cSrmEnquiryInvitationForBidsTHReq, cSrmEnquiryInvitationForBidsTH);
				cSrmEnquiryInvitationForBidsTH.setUpdateTime(new Date());
				cSrmEnquiryInvitationForBidsTHMapper.updateByPrimaryKeySelective(cSrmEnquiryInvitationForBidsTH);
				// ????????????/????????????
				if (!CollectionUtils.isEmpty(cSrmEnquiryInvitationForBidsTHReq.getRfqList())) {
					// ??????????????????????????????  --???????????????????????????,????????????--
					cSrmEnquiryInvitationForBidsTRMapper.delData(cSrmEnquiryInvitationForBidsTHReq.getRfqOrTenderFormCode());
					for (CSrmEnquiryInvitationForBidsTRReq item : cSrmEnquiryInvitationForBidsTHReq.getRfqList()) {
						CSrmEnquiryInvitationForBidsTR cSrmEnquiryInvitationForBidsTR = null;
						if (!StringUtil.eqNu(item.getLineNumber())) {
							// ????????????
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
						// ??????????????????
						if (StringUtil.eqNu(item.getRequiredDate())) {
							cSrmEnquiryInvitationForBidsTR.setDemandedDate(new SimpleDateFormat("yyyy-MM-dd").parse(item.getRequiredDate()));
						}
						cSrmEnquiryInvitationForBidsTR.setCreateTime(new Date());
						cSrmEnquiryInvitationForBidsTRMapper.insertSelective(cSrmEnquiryInvitationForBidsTR);
					}
				}
				// ????????????/??????????????????
				if (!CollectionUtils.isEmpty(cSrmEnquiryInvitationForBidsTHReq.getSupList())) {
					// ???????????????
					cSrmEnquiryForBidsTSupplierMapper.delData(cSrmEnquiryInvitationForBidsTHReq.getRfqOrTenderFormCode());
					for (CSrmEnquiryForBidsTSupplierReq item : cSrmEnquiryInvitationForBidsTHReq.getSupList()) {
						CSrmEnquiryForBidsTSupplier cSrmEnquiryForBidsTSupplier = null;
						if (!StringUtil.eqNu(item.getLineNumber())) {
							// ??????????????????
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
							// ????????????
							cSrmEnquiryForBidsTSupplier.setOfferTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(item.getOfferTime()));
						}
						if (StringUtil.eqNu(item.getOfferPeriodOfValidity())) {
							// ???????????????
							cSrmEnquiryForBidsTSupplier.setOfferPeriodOfValidity(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(item.getOfferPeriodOfValidity()));
						}
						if (StringUtil.eqNu(item.getEstimatedTimeOfDelivery())) {
							// ??????????????????
							cSrmEnquiryForBidsTSupplier.setEstimatedTimeOfDelivery(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(item.getEstimatedTimeOfDelivery()));
						}
						if (StringUtil.eqNu(cSrmEnquiryForBidsTSupplier.getUnitPrice())) {
							// ??????????????????
							cSrmEnquiryForBidsTSupplier.setWhetherOffer("1");
						}
						cSrmEnquiryForBidsTSupplierMapper.insertSelective(cSrmEnquiryForBidsTSupplier);
					}

				}
				return Rjson.success("????????????", cSrmEnquiryInvitationForBidsTHReq);
			} else {
				return Rjson.error("??????????????????????????????");
			}

		} else if (("3").equals(cSrmEnquiryInvitationForBidsTHReq.getOperationSign())) {
			// ????????????
			// ????????????/????????????????????????
			cSrmEnquiryInvitationForBidsTH = new CSrmEnquiryInvitationForBidsTH();
			cSrmEnquiryInvitationForBidsTH.setRfqOrTenderFormCode(cSrmEnquiryInvitationForBidsTHReq.getRfqOrTenderFormCode());
			CSrmEnquiryInvitationForBidsTH selectByPrimaryKey = cSrmEnquiryInvitationForBidsTHMapper.selectByPrimaryKey(cSrmEnquiryInvitationForBidsTH);
			if (null != selectByPrimaryKey) {
				// ????????????/????????????
				cSrmEnquiryInvitationForBidsTH = new CSrmEnquiryInvitationForBidsTH();
				BeanUtils.copyProperties(cSrmEnquiryInvitationForBidsTHReq, cSrmEnquiryInvitationForBidsTH);
				cSrmEnquiryInvitationForBidsTH.setUpdateTime(new Date());
				cSrmEnquiryInvitationForBidsTHMapper.updateByPrimaryKeySelective(cSrmEnquiryInvitationForBidsTH);
			} else {
				return Rjson.error("??????????????????????????????");
			}
			return Rjson.success("????????????", null);
		} else {
			return Rjson.error("??????????????????");
		}

	}


	@Override
	public Rjson findForQuotationOrCallForBidsH(CSrmEnquiryInvitationForBidsTHReq req) {

		// ?????????????????????????????????
		if (req.getSupplierCode() != null && StringUtil.eqNu(req.getSupplierCode().get(0))) {
			// ???????????????????????????
			CSrmSupplier cSrmSupplier = cSrmSupplierMapper.selectSupplierCode(req.getSupplierCode().stream().findFirst().orElse(null));
			if (cSrmSupplier == null) {
				return Rjson.error("????????????????????????");
			}
		}
		List<CSrmEnquiryInvitationForBidsTHRsps> forQuotationOrCallForBidsH = null;
		// ???????????? ??????????????????
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
		// ????????????????????????
		List<CSrmEnquiryInvitationForBidsTHRsps> forBidsH = cSrmEnquiryInvitationForBidsTHMapper.findForQuotationOrCallForBidsH(req);
		if (forBidsH.size() == 0) {
			return Rjson.success();
		} else {

			for (CSrmEnquiryInvitationForBidsTHRsps rspItem : forBidsH) {
				BeanUtils.copyProperties(rspItem, rsp);
				// ??????????????????List<String> ????????????
				List<String> supplierCode = new ArrayList<>();
				supplierCode.add(rspItem.getSupplierCode());
				rsp.setSupplierCode(supplierCode);
			}

			// ????????????????????????
			// ???????????????????????????
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
			return Rjson.error("???????????????");
		} else {
			cSrmEnquiryInvitationForBidsTH = new CSrmEnquiryInvitationForBidsTH();
			cSrmEnquiryInvitationForBidsTH.setRfqOrTenderFormCode(cSrmEnquiryInvitationForBidsTHReq.getRfqOrTenderFormCode());
			// ????????????(1.??????2.?????????)
			// ??????(1.????????????2.???????????????3.???????????????4.???????????????5.????????????6.???????????????7.?????????8.?????????9.??????10. ?????????11.?????????)
			// 1.?????????
			if (StringUtil.eqNu(cSrmEnquiryInvitationForBidsTHReq.getName())) {
				System.err.println("name??????" + cSrmEnquiryInvitationForBidsTHReq.getName());
				if (("1").equals(cSrmEnquiryInvitationForBidsTHReq.getSourcingType())) {

					if (("??????").equals(cSrmEnquiryInvitationForBidsTHReq.getName())) {
						cSrmEnquiryInvitationForBidsTH.setStatus("11");

					} else if (("??????").equals(cSrmEnquiryInvitationForBidsTHReq.getName())) {
						cSrmEnquiryInvitationForBidsTH.setStatus("3");

					} else if (("??????").equals(cSrmEnquiryInvitationForBidsTHReq.getName())) {
//                        cSrmEnquiryInvitationForBidsTH.setStatus("");
					}
					// 2.?????????
				} else if (("2").equals(cSrmEnquiryInvitationForBidsTHReq.getSourcingType())) {

					if (("??????").equals(cSrmEnquiryInvitationForBidsTHReq.getName())) {
						cSrmEnquiryInvitationForBidsTH.setStatus("11");

					} else if (("??????").equals(cSrmEnquiryInvitationForBidsTHReq.getName())) {
						cSrmEnquiryInvitationForBidsTH.setStatus("5");

					} else if (("??????").equals(cSrmEnquiryInvitationForBidsTHReq.getName())) {
//                        cSrmEnquiryInvitationForBidsTH.setStatus("");
					}
				}
			} else {
				EqualsPoJoUtil.integer(cSrmEnquiryInvitationForBidsTHReq.getStatus(), "??????", true);
				if (Integer.parseInt(cSrmEnquiryInvitationForBidsTHReq.getStatus()) > 11) {
					return Rjson.error("'status'???????????????");
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



