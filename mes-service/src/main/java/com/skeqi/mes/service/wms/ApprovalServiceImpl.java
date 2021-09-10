package com.skeqi.mes.service.wms;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.mapper.wms.ApprovalDao;
import com.skeqi.mes.pojo.CMesJlMaterialT;
import com.skeqi.mes.pojo.CMesUserT;
import com.skeqi.mes.pojo.wms.CWmsAllMaterialBarcode;
import com.skeqi.mes.pojo.wms.CWmsApprovalDetailsT;
import com.skeqi.mes.pojo.wms.CWmsApprovalT;
import com.skeqi.mes.pojo.wms.CWmsInTaskqueueT;
import com.skeqi.mes.pojo.wms.CWmsMaterialBarcodeRuleT;
import com.skeqi.mes.pojo.wms.CWmsMaterialNumberT;
import com.skeqi.mes.pojo.wms.CWmsMaterialRuleAttributeT;
import com.skeqi.mes.pojo.wms.CWmsOutTaskqueueT;
import com.skeqi.mes.pojo.wms.CWmsStorageDetailT;

/**
 * @package com.skeqi.mes.service.wms
 * @author yp
 * @date 2020年2月15日 审批记录
 */
@Service
public class ApprovalServiceImpl implements ApprovalService {

	@Autowired
	ApprovalDao dao;

	@Autowired
	ApprovalDetailsServiceImpl dService;

	@Autowired
	InTaskqueueServiceImpl iService;

	@Autowired
	OutTaskqueueServiceImpl oService;

	@Autowired
	StorageDetailServiceImpl sService;

	@Autowired
	MaterialNumberServiceImpl mService;

	/**
	 * 通过审批类型查询
	 *
	 * @param approval
	 * @return
	 */
	@Override
	public List<CWmsApprovalT> findApproval(CWmsApprovalT approval) {
		// TODO Auto-generated method stub
		return dao.findApproval(approval);
	}

	/**
	 * 通过单号查询审批记录
	 *
	 * @param listNo
	 * @return
	 */
	@Override
	public CWmsApprovalT findApprovalByListNo(String listNo) {
		// TODO Auto-generated method stub
		return dao.findApprovalByListNo(listNo);
	}

	/**
	 * 审批驳回
	 *
	 * @param userId
	 * @param listNo
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean approvalReject(int userId, String listNo) throws Exception {
		CWmsApprovalT approval = new CWmsApprovalT();
		approval.setUserId(userId);
		approval.setListNo(listNo);

		// 查询出当前审批的是哪一条记录
		List<CWmsApprovalT> list = dao.findApprovalList(approval);
		if (list.size() != 1) {
			throw new Exception("查询当前审批记录出错了");
		}

		// 查询出来的数据赋值给dx
		CWmsApprovalT dx = list.get(0);
		// 设置审批主表状态为驳回
		dx.setState(3);
		// 修改审批主表状态
		boolean res = updateApproval(dx);
		if (!res) {
			throw new Exception("修改审批主表状态失败");
		}

		// 此处如果是盘点驳回
		if (dx.getProcessType().getId() == 9) {
			return true;
		}

		CWmsApprovalDetailsT detailsT = new CWmsApprovalDetailsT();
		detailsT.setApprovalId(dx.getId());

		List<CWmsApprovalDetailsT> detailsList = dService.findApprovalDetails(detailsT);

		// 遍历
		for (CWmsApprovalDetailsT dx1 : detailsList) {
			// 找到当前的审批详情
			if (dx1.getApprovalResult() == null || dx1.getApprovalResult().equals("")) {

				// 修改状态为驳回 不通过
				dx1.setApprovalResult(2);
				// 修改审批详情 是否审批列为0 不可审批
				dx1.setYnApproved(0);
				res = dService.updateApprovalDetails(dx1);
				if (!res) {
					throw new Exception("修改审批详情出错了");
				}

				break;
			}
		}

		// 一下是处理库存数据
		CWmsStorageDetailT storageDetail = new CWmsStorageDetailT();
		storageDetail.setListNo(listNo);
		// 查询当前单号所有库存详情
		List<CWmsStorageDetailT> storageDetailList = sService.findStorageDetail(storageDetail);
		if (storageDetailList.size() == 0) {
			throw new Exception("查询库存详情出错了");
		}

		// 遍历库存详情
		for (CWmsStorageDetailT cWmsStorageDetailT : storageDetailList) {

			// 判断一下该单据执行的是出库操作还是入库操作
			if (storageDetailList.get(0).getIssueOrReceipt() == 0) {
				// 出库需要减去待出库数量
				CWmsMaterialNumberT materialNumber = new CWmsMaterialNumberT();
				materialNumber.setMaterialId(cWmsStorageDetailT.getMaterialId());
				materialNumber.setProjectId(cWmsStorageDetailT.getProjectId());
				materialNumber.setLocationId(cWmsStorageDetailT.getLocationId());
				materialNumber.setLmminentRelease(cWmsStorageDetailT.getMaterialNumber());
				res = mService.updateLmminentRelease(materialNumber, 1);
				if (!res) {
					throw new Exception("修改物料库存即将出库数量出错了");
				}

			}

			// 新增P表
			res = sService.addPStorageDetail(cWmsStorageDetailT);
			if (!res) {
				throw new Exception("新增库存详情永久表出错了");
			}
			// 删除R表
			res = sService.deleteStorageDetail(cWmsStorageDetailT.getId());
			if (!res) {
				throw new Exception("删除库存详情临时表出错了");
			}
		}

		return true;
	}

	/**
	 * 审批同意
	 *
	 * @param userId
	 * @param listNo
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean approvalAgree(int userId, String listNo) throws Exception {

		// 查询出入库是否存在记录
		JSONObject outIt = dao.findOutInCount(listNo);
		if (outIt.getInteger("in") != 0) {
			throw new Exception("该托盘还有入库或加料操作未执行，无法通过审批");
		}
		if (outIt.getInteger("out") != 0) {
			throw new Exception("该托盘还有出库操作未执行，无法通过审批");
		}

		CWmsApprovalT approval = new CWmsApprovalT();
		approval.setUserId(userId);
		approval.setListNo(listNo);

		// 查询出当前审批的是哪一条记录
		List<CWmsApprovalT> list = dao.findApprovalList(approval);
		if (list.size() != 1) {
			throw new Exception("查询审批记录出错了");
		}

		// 查询出来的数据赋值给dx
		CWmsApprovalT dx = list.get(0);

		// 查询后面是否还有审批
		// 是否最后个人审批了
		List<CWmsApprovalDetailsT> detailList = dService.findShiFouZuiHouYiGeShenPiRen(listNo,
				dx.getApprovalDetails().getPriorityLevel());

		// 更新审批详表审批结果
		boolean res = dService.updateApprovalResult(dx.getApprovalDetails().getId());
		if (!res) {
			throw new Exception("更新审批详表审批结出错了");
		}

		if (detailList.size() > 0) {
			// 不是最后一个审批的人
			// 只需要修改当前审批详情的审批状态以及下一条审批记录的‘是否可审批字段’

			// 如果是第一次审批这个单据 也就是开始审批 则修改单据状态为‘审批中’
			if (dx.getApprovalDetails().getPriorityLevel() == 1) {
				// 修改审批单据状态为‘1’表示审批中
				dx.setState(1);
				res = updateApproval(dx);
				if (!res) {
					throw new Exception("修改单据状态出错了");
				}
			}

			CWmsApprovalDetailsT detail = detailList.get(0);

			// 更新下一条需要审批的 审批详表ynApproved
			res = dService.updateYnApproved(detail.getId());
			if (!res) {
				throw new Exception("更新审批详表ynApproved出错了");
			}

		} else {

			Map<String, Object> in_out = dao.findWhetherItCanBeApproved(listNo);
			if (Integer.parseInt(in_out.get("in").toString()) != 0
					|| Integer.parseInt(in_out.get("out").toString()) != 0) {
				throw new Exception("暂时无法同意审批，该单据需要操作的托盘已经在准备放入或者取出");
			}

			// 当前已经是最后一个人审批了
			// 需要修改审批主表审批状态

			// 修改dx审批状态
			dx.setState(2);
			res = updateApproval(dx);
			if (!res) {
				throw new Exception("修改审批状态出错了");
			}

			// 如果是盘点直接完成
			if (dx.getProcessType().getId() == 9) {
				return true;
			}

			// 一下是生成临时队列步骤
			// 通过单号查询库存详情
			CWmsStorageDetailT storageDetail = new CWmsStorageDetailT();
			storageDetail.setListNo(dx.getListNo());
			List<CWmsStorageDetailT> storageDetailList = sService.findStorageDetail(storageDetail);

			if (storageDetailList.size() == 0) {
				throw new Exception("查询库存详情出错了");
			}

			/**
			 * 生成条码
			 */
//			if (dx.getProcessType().getId() == 1 || dx.getProcessType().getId() == 10) {
//				// 生成条码
//				List<JSONObject> jsonList = mapper.findRStorageDetailSum(listNo);
//				for (JSONObject j1 : jsonList) {
//					findTheAppropriateBarcodeGenerationRule(j1);
//				}
//			}

			if (storageDetailList.get(0).getIssueOrReceipt() == 0) {
				// 生成临时出库队列
				List<CWmsOutTaskqueueT> out = new ArrayList<CWmsOutTaskqueueT>();
				// 遍历库存详情
				for (int i = 0; i < storageDetailList.size(); i++) {
					CWmsOutTaskqueueT cWmsOutTaskqueueT = new CWmsOutTaskqueueT();
					if (i == 0) {
						cWmsOutTaskqueueT.setListNo(storageDetailList.get(i).getListNo());
						cWmsOutTaskqueueT.setTray(storageDetailList.get(i).getTray());
						cWmsOutTaskqueueT.setStationId(storageDetailList.get(i).getStationId());
						cWmsOutTaskqueueT.setLocationId(storageDetailList.get(i).getLocationId());
						out.add(cWmsOutTaskqueueT);
					} else {
						// 遍历已经生成的出库队列
						for (int j = 0; j < out.size(); j++) {
							// 判断有不有同库位的出库
							if (out.get(j).getLocationId().equals(storageDetailList.get(i).getLocationId())) {
								// 如果发现有库位的出库队列就直接进入下一次遍历
								continue;
							} else {
								if (j == (out.size() - 1)) {
									// 匹配到了最后还是没有匹配到有出库队列就加入一条出库队列
									cWmsOutTaskqueueT.setListNo(storageDetailList.get(i).getListNo());
									cWmsOutTaskqueueT.setTray(storageDetailList.get(i).getTray());
									cWmsOutTaskqueueT.setStationId(storageDetailList.get(i).getStationId());
									cWmsOutTaskqueueT.setLocationId(storageDetailList.get(i).getLocationId());
									out.add(cWmsOutTaskqueueT);
								}
							}
						}
					}
				}
				// 新增出库队列到数据库
				for (CWmsOutTaskqueueT outdx : out) {
					res = oService.addOutTaskqueue(outdx);
					if (!res) {
						throw new Exception("新增出库队列出错了");
					}
				}

			} else {
				// 生成临时入库队列
				List<CWmsInTaskqueueT> in = new ArrayList<CWmsInTaskqueueT>();
				// 遍历库存详情
				for (int i = 0; i < storageDetailList.size(); i++) {
					CWmsInTaskqueueT cWmsInTaskqueueT = new CWmsInTaskqueueT();
					if (i == 0) {
						cWmsInTaskqueueT.setListNo(storageDetailList.get(i).getListNo());
						cWmsInTaskqueueT.setTray(storageDetailList.get(i).getTray());
						cWmsInTaskqueueT.setLocationId(storageDetailList.get(i).getLocationId());
						in.add(cWmsInTaskqueueT);
					} else {
						// 遍历已经生成的入库队列
						for (int j = 0; j < in.size(); j++) {
							// 判断有不有同库位的入库
							if (in.get(j).getLocationId().equals(storageDetailList.get(i).getLocationId())) {
								// 如果发现有库位的入库队列就直接进入下一次遍历
								continue;
							} else {
								if (j == (in.size() - 1)) {
									// 匹配到了最后还是没有匹配到有入库队列就加入一条入库队列
									cWmsInTaskqueueT.setListNo(storageDetailList.get(i).getListNo());
									cWmsInTaskqueueT.setTray(storageDetailList.get(i).getTray());
									cWmsInTaskqueueT.setLocationId(storageDetailList.get(i).getLocationId());
									in.add(cWmsInTaskqueueT);
								}
							}
						}
					}
				}
				// 新增入库队列到数据库
				for (CWmsInTaskqueueT indx : in) {
					res = iService.addInTaskqueue(indx);
					if (!res) {
						throw new Exception("新增入库队列出错");
					}
				}
			}
		}
		return true;
	}

	/**
	 * 查询
	 *
	 * @param approval
	 * @return
	 * @throws Exception
	 */
	@Override
	public PageInfo<CWmsApprovalT> findApprovalList(JSONObject json) throws Exception {

		CWmsApprovalT dx = new CWmsApprovalT();
		dx.setUserId(json.getInteger("userId"));

		PageHelper.startPage(json.getInteger("pageNumber"), 8);
		List<CWmsApprovalT> list = dao.findApprovalList(dx);
		PageInfo<CWmsApprovalT> pageInfo = new PageInfo<CWmsApprovalT>(list, 5);

		return pageInfo;
	}

	/**
	 * 通过id查询
	 *
	 * @param approvalId
	 * @return
	 */
	@Override
	public CWmsApprovalT findApprovalById(Integer approvalId) {
		CWmsApprovalT approval = new CWmsApprovalT();
		approval.setId(approvalId);

		List<CWmsApprovalT> approvalList = dao.findApprovalList(approval);
		if (approvalList.size() == 1) {
			approval = approvalList.get(0);
			return approval;
		} else {
			return null;
		}
	}

	/**
	 * 新增
	 *
	 * @param approval
	 * @return
	 */
	@Override
	public boolean addApproval(CWmsApprovalT approval) {
		Integer result = dao.addApproval(approval);
		if (result == 1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 更新
	 *
	 * @param approval
	 * @return
	 */
	@Override
	public boolean updateApproval(CWmsApprovalT approval) {
		Integer result = dao.updateApproval(approval);
		if (result == 1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 删除
	 *
	 * @param approvalId
	 * @return
	 */
	@Override
	public boolean deleteApproval(Integer approvalId) {
		Integer result = dao.deleteApproval(approvalId);
		if (result == 1) {
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) {

		String str = "SKQ{属性:物料.物料编码}{日期:YYMMDD}{流水:日清.1001}";
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		while (true) {
			Map<String, Object> map = new HashedMap<String, Object>();
			if (str.length() > 0) {
				Integer num = str.indexOf("{");
				if (num > 0) {
					if (str.indexOf("}") < str.indexOf("{")) {
						// throw new Exception("格式中包含'{xx:xx.xx}'可能缺失'{'或者'}'");
					}
					String text = str.substring(0, str.indexOf("{"));
					map.put("type", "text");
					map.put("content", text);
					str = str.substring(str.indexOf("{"), str.length());
				} else {
					String strNet = str.substring(str.indexOf("{") + 1, str.length());
					if (strNet.indexOf("{") != -1) {
						if (strNet.indexOf("{") < strNet.indexOf("}") || strNet.indexOf("}") == -1) {
							System.out.println("格式中包含'{xx:xx.xx}'可能缺失'{'或者'}'");
							break;
						}
					}

					String attribute = str.substring(str.indexOf("{"), str.indexOf("}") + 1);
					str = str.substring(str.indexOf("}") + 1, str.length());

					map.put("type", "attribute");
					map.put("content", attribute);
				}
				mapList.add(map);
			} else {
				System.out.println("规则基础校验通过");
				break;
			}
		}

		String barCode = "";

		for (Map<String, Object> map : mapList) {
			String content = map.get("content").toString();
			String type = map.get("type").toString();
			// 获取到流水
			if (type.equals("attribute")) {
				if (content.indexOf("流水") != -1) {
					String tiaomaType = content.substring(content.indexOf(":") + 1, content.indexOf("."));
					String liushui = content.substring(content.indexOf(".") + 1, content.indexOf("}"));
					Integer liushuiLeng = liushui.length();
					Integer liushuiNumber = Integer.parseInt(liushui) + 1;
					liushui = "";
					for (int i = liushuiNumber.toString().length(); i < liushuiLeng; i++) {
						liushui += "0";
					}
					liushui += liushuiNumber;
					barCode += liushui;
				} else if (content.indexOf("日期") != -1) {
					SimpleDateFormat df = new SimpleDateFormat("YYMMdd");// 设置日期格式
					barCode += df.format(new Date());
				} else if (content.indexOf("属性:物料") != -1) {
					String materialType = content.substring(content.indexOf(".") + 1, content.length() - 1);
					barCode += materialType;
				}
			} else if (type.equals("text")) {
				barCode += content;
			}
		}

		System.out.println(barCode);

	}

	@Override
	public List<CWmsStorageDetailT> findStorageDetail(CWmsStorageDetailT storageDetail) {
		// TODO Auto-generated method stub
		return dao.findStorageDetail(storageDetail);
	}

	@Override
	public List<JSONObject> findRStorageDetailSum(String listNo) {
		// TODO Auto-generated method stub
		return dao.findRStorageDetailSum(listNo);
	}

	/**
	 * 找到合适的生成条码规则
	 *
	 * @return
	 */
	public boolean findTheAppropriateBarcodeGenerationRule(JSONObject json) throws Exception {

		// 条件+规则
		List<CWmsMaterialBarcodeRuleT> barcodeRuleTs = dao.findMaterialBarcodeRuleList();
		// 属性
		List<CWmsMaterialRuleAttributeT> attributeTs = dao.findMaterialRuleAttributeList();
		// 物料
		CMesJlMaterialT materialT = dao.findMaterial(json.getInteger("materialId"));

		List<String> strList = new ArrayList<String>();
		String barcodeRules = "";
		Integer materialBarcodeRuleId = null;
		try {
			// 循环出满足物料的条件
			for (CWmsMaterialBarcodeRuleT dx1 : barcodeRuleTs) {

				boolean True = true;

				// 条件
				String str = dx1.getCondition();
				// 保存所有的小条件
				List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
				// 遍历出所有 小条件
				while (true) {

					String string = str.substring(0, str.indexOf(";"));
					String material = string.substring(0, string.indexOf("."));
					if (!material.equals("物料")) {
						throw new Exception("验证物料——格式错误");
					}

					String type = string.substring(string.indexOf(".") + 1, string.indexOf("="));
					for (int i = 0; i < attributeTs.size(); i++) {
						if (attributeTs.get(i).getAttributeCn().equals(type)) {
							break;
						} else {
							if (i == (attributeTs.size() - 1)) {
								throw new Exception("验证类型——参数错误:" + type);
							}
						}
					}

					String value = string.substring(string.indexOf("=") + 1, string.length());

					Map<String, Object> map = new HashedMap<String, Object>();
					map.put("material", material);
					map.put("type", type);
					map.put("value", value);
					mapList.add(map);

					strList.add(str.substring(0, str.indexOf(";")));
					str = str.substring(str.indexOf(";") + 1, str.length());

					if (str.length() == 0) {
						break;
					}
				}
				for (Map<String, Object> map : mapList) {

					switch (map.get("type").toString()) {
					case "物料编码":
						if (!map.get("value").toString().equals(materialT.getBomId())) {
							True = false;
						}
						break;
					case "规格":
						if (!map.get("value").toString().equals(materialT.getSpecifications())) {
							True = false;
						}
						break;
					case "型号":
						if (!map.get("value").toString().equals(materialT.getTypenum())) {
							True = false;
						}
						break;
					}
				}
				if (True) {
					barcodeRules = dx1.getRule();
					materialBarcodeRuleId = dx1.getId();
				}
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		System.out.println("满足的条码规则：" + barcodeRules);
		generateBarcode(barcodeRules, materialBarcodeRuleId, json);
		return true;
	}

	/**
	 * 生成条码
	 *
	 * @param barcodeRules          生成条码规则
	 * @param materialBarcodeRuleId 物料条码规则Id
	 * @return
	 * @throws Exception
	 */
	public boolean generateBarcode(String barcodeRules, Integer materialBarcodeRuleId, JSONObject json)
			throws Exception {

		String str = barcodeRules;
		// 没有制定条码规则
		if (barcodeRules == null || barcodeRules.equals("")) {
			throw new Exception("没有配置生成条码规则");
		}
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		while (true) {
			Map<String, Object> map = new HashedMap<String, Object>();
			if (str.length() > 0) {
				Integer num = str.indexOf("{");
				if (num > 0) {
					if (str.indexOf("}") < str.indexOf("{")) {
						// throw new Exception("格式中包含'{xx:xx.xx}'可能缺失'{'或者'}'");
					}
					String text = str.substring(0, str.indexOf("{"));
					map.put("type", "text");
					map.put("content", text);
					str = str.substring(str.indexOf("{"), str.length());
				} else {
					String strNet = str.substring(str.indexOf("{") + 1, str.length());
					if (strNet.indexOf("{") != -1) {
						if (strNet.indexOf("{") < strNet.indexOf("}") || strNet.indexOf("}") == -1) {
							System.out.println("格式中包含'{xx:xx.xx}'可能缺失'{'或者'}'");
							break;
						}
					}

					String attribute = str.substring(str.indexOf("{"), str.indexOf("}") + 1);
					str = str.substring(str.indexOf("}") + 1, str.length());

					map.put("type", "attribute");
					map.put("content", attribute);
				}
				mapList.add(map);
			} else {
				System.out.println("规则基础校验通过");
				break;
			}
		}

		List<JSONObject> barJsonList = new ArrayList<JSONObject>();

		for (int q = 0; q < json.getInteger("materialNumber"); q++) {
			String barCode = "";
			for (Map<String, Object> map : mapList) {
				String content = map.get("content").toString();
				String type = map.get("type").toString();
				// 获取到流水
				if (type.equals("attribute")) {
					if (content.indexOf("流水") != -1) {
						// 流水类型 日清、月清、总计
						String tiaomaType = content.substring(content.indexOf(":") + 1, content.indexOf("."));

						if (q == 0) {
							// 查询使用该规则打印过得最大条码值
							CWmsAllMaterialBarcode allMaterialBarcode = dao
									.findAllMaterialBarcodeMax(materialBarcodeRuleId, tiaomaType);
							if (allMaterialBarcode == null) {
								String liushui = content.substring(content.indexOf(".") + 1, content.indexOf("}"));
								barCode += liushui;
							} else {
								// 流水的位数
								Integer liushuiLeng = content.substring(content.indexOf(".") + 1, content.indexOf("}"))
										.length();
								String getBarCode = allMaterialBarcode.getBarCode();
								Integer liushui = Integer.parseInt(
										getBarCode.substring(getBarCode.length() - liushuiLeng, getBarCode.length()))
										+ 1;
								for (int i = liushui.toString().length(); i < liushuiLeng; i++) {
									barCode += "0";
								}
								barCode += liushui;
							}
						}else {
							// 流水的位数
							Integer liushuiLeng = content.substring(content.indexOf(".") + 1, content.indexOf("}"))
									.length();
							String getBarCode = barJsonList.get(barJsonList.size()-1).getString("barCode");
							Integer liushui = Integer.parseInt(
									getBarCode.substring(getBarCode.length() - liushuiLeng, getBarCode.length()))
									+ 1;
							for (int i = liushui.toString().length(); i < liushuiLeng; i++) {
								barCode += "0";
							}
							barCode += liushui;
						}

					} else if (content.indexOf("日期") != -1) {
						SimpleDateFormat df = new SimpleDateFormat("YYMMdd");// 设置日期格式
						barCode += df.format(new Date());
					} else if (content.indexOf("属性:物料") != -1) {
						String materialType = content.substring(content.indexOf(".") + 1, content.length() - 1);
						switch (materialType) {
						case "物料编码":
							barCode += json.getString("bomId");
							break;
						case "规格":
							barCode += json.getString("specifications");
							break;
						case "型号":
							barCode += json.getString("typenum");
							break;
						}

					}
				} else if (type.equals("text")) {
					barCode += content;
				}
			}
			CWmsAllMaterialBarcode dx = new CWmsAllMaterialBarcode();
			dx.setBarCode(barCode);
			dx.setMaterialBarcodeRuleId(materialBarcodeRuleId);

			JSONObject barJson = new JSONObject();
			barJson.put("barCode", barCode);
			barJson.put("materialBarcodeRuleId", materialBarcodeRuleId);

			barJson.put("materialId", json.getString("materialId"));
			barJson.put("projectId", json.getString("projectId"));
			barJson.put("locationId", json.getString("locationId"));

			barJsonList.add(barJson);

		}

		StringBuffer barcodeBuffer = new StringBuffer();
		StringBuffer stockBarcodeBuffer = new StringBuffer();
		for (int i = 0; i < barJsonList.size(); i++) {
			barcodeBuffer.append("('" + barJsonList.get(i).getString("barCode") + "',");
			barcodeBuffer.append(barJsonList.get(i).getInteger("materialBarcodeRuleId") + "),");

			stockBarcodeBuffer.append("('" + barJsonList.get(i).getString("barCode") + "','");
			stockBarcodeBuffer.append(barJsonList.get(i).getString("materialId") + "','");
			stockBarcodeBuffer.append(barJsonList.get(i).getString("projectId") + "','");
			stockBarcodeBuffer.append(barJsonList.get(i).getString("locationId") + "'),");
		}

		// 新增所有物料条码表数据
		String barcodeSql = barcodeBuffer.toString();
		dao.addAllMaterialBarcode(barcodeSql.substring(0, barcodeSql.length() - 1));

		// 批量新增库存条码
		String stockBarcodeSql = stockBarcodeBuffer.toString();
		dao.addStockBarcode(stockBarcodeSql.substring(0, stockBarcodeSql.length() - 1));

		return true;
	}

	/**
	 * 通过单号查询盘点详情
	 *
	 * @param listNo
	 * @return
	 */
	@Override
	public List<JSONObject> findInventoryDetailByListNo(String listNo) {
		// TODO Auto-generated method stub
		return dao.findInventoryDetailByListNo(listNo);
	}

}
