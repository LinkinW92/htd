package com.skeqi.mes.service.wms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;

import org.apache.axis.client.Call;
import org.apache.axis.encoding.XMLType;
import org.apache.commons.collections4.map.HashedMap;
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.wms.K3RuKuDuiJieDao;
import com.skeqi.mes.pojo.CMesJlMaterialT;
import com.skeqi.mes.pojo.CMesUserT;
import com.skeqi.mes.pojo.wms.CWmsAllMaterialBarcode;
import com.skeqi.mes.pojo.wms.CWmsApprovalT;
import com.skeqi.mes.pojo.wms.CWmsInTaskqueueT;
import com.skeqi.mes.pojo.wms.CWmsLocationT;
import com.skeqi.mes.pojo.wms.CWmsMaterialBarcodeRuleT;
import com.skeqi.mes.pojo.wms.CWmsMaterialNumberT;
import com.skeqi.mes.pojo.wms.CWmsMaterialRuleAttributeT;
import com.skeqi.mes.pojo.wms.CWmsOutTaskqueueT;
import com.skeqi.mes.pojo.wms.CWmsProject;
import com.skeqi.mes.pojo.wms.CWmsStorageDetailT;
import com.skeqi.mes.pojo.wms.K3ImportNotifydetail;
import com.skeqi.mes.pojo.wms.ProcessApproval;
import com.skeqi.mes.pojo.wms.ProcessApprovalDetail;
import com.skeqi.mes.util.jdbc.SqlServerJDBC;

/**
 * k3入库对接
 *
 * @author yinp
 *
 */
@Service
public class K3RuKuDuiJieServiceImpl implements K3RuKuDuiJieService {

	Connection connection = null;
	String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";// SQL数据库引擎
	String dbURL = "jdbc:sqlserver://192.168.7.8:1433;DatabaseName=DataCenter";// 数据源
																				// //
																				// ！！！注意若出现加载或者连接数据库失败一般是这里出现问题
	String Name = "sa";
	String Pwd = "kingdee";
	Statement stmt = null;
	ResultSet rs = null;

	private String url = "http://127.0.0.1/FISClientService.asmx?wsdl";

	@Autowired
	K3RuKuDuiJieDao dao;

	/**
	 * 直接调用入库接口开始入库
	 *
	 * @param locationId
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean Submission(String tray, Integer locationId, String materialJson, int userId) throws Exception {

		List<JSONObject> list = JSONObject.parseArray(materialJson, JSONObject.class);

		CWmsLocationT location = dao.findLocation(locationId);

		Integer count = dao.findMaterialNumberCount(locationId);

		CWmsMaterialNumberT materialNumber = new CWmsMaterialNumberT();

		// 生成单号
		SimpleDateFormat s = new SimpleDateFormat("yyyyMMddHHmmss");
		// 单号
		String listNo = "";
		// count>0 加料
		if (count > 0) {
			listNo = "K3JL" + s.format(new Date());
			materialNumber = dao.findMaterialNumber(locationId);
			tray = materialNumber.getTray();
		} else {
			listNo = "K3RK" + s.format(new Date());

			CWmsInTaskqueueT inTaskqueue = dao.findInTaskqueueByTray(tray);

			if (inTaskqueue != null) {
				throw new Exception("托盘码：" + tray + "已存在，请更换托盘");
			}

			if (tray.length() != 13) {
				throw new Exception("托盘码规则不匹配");
			}

			if (tray.substring(0, 9).equals("SKQ-ND-L-")) {
				if (location.getTrayType() == 2) {
					throw new Exception("托盘高矮跟库位不匹配");
				}

			} else if (tray.substring(0, 9).equals("SKQ-ND-H-")) {
				if (location.getTrayType() == 1) {
					throw new Exception("托盘高矮跟库位不匹配");
				}
			}

		}

		// 检查出入库队列
		JSONObject check = dao.checkOutAndReceiptQueue(tray);
		if (check.getInteger("out") != 0 || check.getInteger("in") != 0) {
			throw new Exception("'" + tray + "'有未处理的出库或入库操作，请先处理完成");
		}

		Integer res = 0;
		for (JSONObject json : list) {

			K3ImportNotifydetail dx = dao.findK3ImportNotifydetail(json.getInteger("id"));
			res = 0;
			Integer enNumber = json.getInteger("importPackQuantity") - json.getIntValue("receivedNumber");
			// 判断是否全部入库
			if (enNumber.equals(json.getInteger("number"))) {
				res = dao.updateK3ImportNotifydetail(json.getInteger("id"), json.getInteger("number"), 1);
			} else {
				res = dao.updateK3ImportNotifydetail(json.getInteger("id"), json.getInteger("number"), 0);
			}
			if (res != 1) {
				throw new Exception("更新可入库数量出错了");
			}

			// 查询库存是否存在
//			JSONObject materialNumberJson = new JSONObject();
//			materialNumberJson.put("projectId", dx.getProjectId());
//			materialNumberJson.put("materialId", dx.getMaterialId());
//			materialNumberJson.put("locationId", locationId);
//			materialNumberJson = mapper.findStockMaterialNumber(json);
//
//			if (materialNumberJson != null && materialNumberJson.getInteger("ID") != null) {
//				// 存在
//			}else {
//				// 不存在
//			}

			CWmsStorageDetailT sddx = new CWmsStorageDetailT();
			sddx.setProjectId(dx.getProjectId());
			sddx.setMaterialId(dx.getMaterialId());
			sddx.setMaterialNumber(json.getInteger("number"));
			sddx.setMaterialCode(dx.getImportGoodsCode());
			sddx.setWarehouseId(dx.getImportWarehouseId());
			sddx.setAreaId(location.getReservoirArea().getAreaId());
			sddx.setReservoirAreaId(location.getReservoirAreaId());
			sddx.setLocationId(locationId);
			sddx.setListNo(listNo);
			sddx.setTray(tray);
			sddx.setYnShift(0);
			sddx.setIssueOrReceipt(0);
			sddx.setStationId(null);

			res = dao.addStorageDetail(sddx);
			if (res != 1) {
				throw new Exception("新增临时库存详情表出错了");
			}

			// 找到合适的生成条码规则
//			findTheAppropriateBarcodeGenerationRule(dx.getMaterialId(), json.getInteger("number"), listNo);

		}

		CWmsInTaskqueueT cWmsInTaskqueueT = new CWmsInTaskqueueT();
		cWmsInTaskqueueT.setListNo(listNo);
		cWmsInTaskqueueT.setTray(tray);
		cWmsInTaskqueueT.setLocationId(locationId);
		res = dao.addInTaskqueue(cWmsInTaskqueueT);
		if (res != 1) {
			throw new Exception("新增临时入库队列数据出错了");
		}
		CMesUserT user = dao.findUserById(userId);

		// 审批流程类型id
		Integer typeId = 1;
		if (count > 0) {
			typeId = 10;
		}
		ProcessApproval padx = new ProcessApproval();
		padx.setDeptId(Integer.parseInt(user.getDepartment()));
		padx.setRoleId(user.getRoleId());
		padx.setTypeId(typeId);
		// 查询审批流程
		List<ProcessApproval> paList = dao.findProcessApproval(padx);
		if (paList.size() == 0) {
			throw new Exception("无审批流程，无法发起审批流程，请添加审批流程");
		}
		padx = paList.get(0);

		// 查询流程详情
		Map<String, Object> padMap = new HashMap<String, Object>();
		padMap.put("processId", padx.getId());
		List<ProcessApprovalDetail> padList = dao.findProcessApprovalDetailList(padMap);
		if (padList.size() == 0) {
			throw new Exception("查询审批流程详情出错了");
		}

		// 保存审批表
		CWmsApprovalT approval = new CWmsApprovalT();
		approval.setListNo(listNo);// 单号
		approval.setProcessId(padx.getId());// 流程主表id
		approval.setUserId(user.getId());// 申请用户id
		approval.setState(2);// 审批状态
		// 新增审批表记录
		res = dao.addApproval(approval);
		if (res <= 0) {
			throw new Exception("新增审批记录时出错了");
		}

		return true;
	}

	// 抓取中间表数据
	@Override
	public boolean getMiddleDataBase() throws Exception {

		List<K3ImportNotifydetail> list = new ArrayList<K3ImportNotifydetail>();

		try {
			Class.forName(driverName);
			connection = DriverManager.getConnection(dbURL, Name, Pwd);
			System.out.println("连接数据库成功");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("连接K3数据库失败了");
		}

		try {
			stmt = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("创建stmt出错了");
		}

		// 关闭事务自动提交功能
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new Exception("关闭事务自动提交出错了");
		}

		String sql = "select * from K3_IMPORT_NOTIFYDETAIL where IMPORT_DOWNLOAD_FLAG = 0";

		try {

			rs = stmt.executeQuery(sql);
			stmt = connection.createStatement();
			while (rs.next()) {

				sql = "update K3_IMPORT_NOTIFYDETAIL set IMPORT_DOWNLOAD_FLAG = 1 where IMPORT_ACCT_NO = '"
						+ rs.getString("IMPORT_ACCT_NO") + "' and IMPORT_ID = '" + rs.getString("IMPORT_ID")
						+ "' and IMPORT_LIST_NO = '" + rs.getString("IMPORT_LIST_NO") + "'";
				stmt.executeUpdate(sql);
				K3ImportNotifydetail dx = new K3ImportNotifydetail();
				dx.setImportId(rs.getString("IMPORT_ID"));
				dx.setImportGoodsCode(rs.getString("IMPORT_GOODS_CODE"));
				dx.setImportPackQuantity(rs.getInt("IMPORT_PACK_QUANTITY"));
				// dx.setImportWarehouseId(rs.getInt("IMPORT_WAREHOUSE_ID"));
				dx.setImportWarehouseId(1);
				dx.setImportRemark(rs.getString("IMPORT_REMARK"));
				dx.setImportMaterialName(rs.getString("IMPORT_MATERIAL_NAME") + rs.getString("import_goods_model"));
				dx.setImportProjectNo(rs.getString("IMPORT_PROJECT_NO"));
				list.add(dx);
			}

		} finally {
			// 关闭
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new Exception("关闭stmt失败");
			}
		}

		try {
			// 查询K3下发的出库数据
			if (list.size() <= 0) {
				// 没有K3新下发的数据
				throw new Exception("K3没有新下发数据");
			}

			for (K3ImportNotifydetail dx : list) {
				// 查询物料名称
				CMesJlMaterialT material = dao.findMtaerialName(dx.getImportMaterialName());
				if (material == null) {
					material = new CMesJlMaterialT();
					material.setMaterialName(dx.getImportMaterialName());
					material.setBomId(dx.getImportGoodsCode());
					Integer res = dao.addMaterial(material);
					if (res != 1) {
						throw new Exception("新增物料失败");
					}
				}
				material = dao.findMtaerialName(dx.getImportMaterialName());
				dx.setMaterialId(material.getId());

				// 查询项目号
				CWmsProject project = dao.findProject(dx.getImportProjectNo());
				if (project == null) {
					project = new CWmsProject();
					project.setProjectName(dx.getImportProjectNo());
					Integer res = dao.addProject(project);
					if (res != 1) {
						throw new Exception("新增项目失败");
					}
				}
				project = dao.findProject(dx.getImportProjectNo());

				dx.setProjectId(project.getId());

				Integer res = dao.addK3ImportNotifydetail(dx);
				if (res != 1) {
					throw new Exception("新增K3入库数据失败");
				}

			}
		} catch (Exception e) {
			connection.close();
			connection = null;
			throw new Exception(e.getMessage());
		}

		try {
			if(connection!=null) {
				// 提交事务
				connection.commit();
				connection.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new Exception("关闭K3数据库连接失败");
		}

		return true;
	}

	@Override
	public List<K3ImportNotifydetail> findK3ImportNotifydetailList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<K3ImportNotifydetail> list = dao.findK3ImportNotifydetailList(map);
		return list;
	}

	@Override
	public List<JSONObject> findLocationList() {
		// TODO Auto-generated method stub
		return dao.findLocationList();
	}

	@Override
	public String findMaterialNumberCount(Integer locationId) {
		// TODO Auto-generated method stub
		Integer result = dao.findMaterialNumberCount(locationId);
		if (result > 0) {
			return "您选择的是加料操作，请确认选中库位有足够的空间！！";
		} else {
			return "您选择的是入库操作，请确认托盘码的完整性！！";
		}

	}

	/**
	 * 异常处理
	 *
	 * @param json
	 * @return
	 */
	@Override
	public boolean exceptionHandle(String importId) throws Exception {

		connection = SqlServerJDBC.getConnection();
		stmt = connection.createStatement();

		// 关闭事务自动提交功能
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new Exception("关闭事务自动提交出错了");
		}

		String sql = "DELETE FROM K3_IMPORT_NOTIFYDETAIL WHERE IMPORT_ID = '" + importId + "'";

		try {
			Integer res = stmt.executeUpdate(sql);
			if (res <= 0) {
				throw new Exception("没有可执行的单据");
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (!e.getMessage().equals("没有可执行的单据")) {
				throw new Exception("SQL执行有误");
			} else {
				throw new Exception(e.getMessage());
			}
		} finally {
			try {
				// 提交事务
				connection.commit();
			} catch (SQLException e) {
				throw new Exception("关闭K3数据库连接失败");
			}

			SqlServerJDBC.close(connection, stmt);
		}

		return true;
	}

	/**
	 * 删除K3入库信息
	 *
	 * @throws Exception
	 */
	@Override
	public boolean deleteK3Number(Integer id) throws Exception {
		Integer res = dao.deleteK3Number(id);
		if (res != 1) {
			throw new Exception("删除K3入库信息出错了");
		}
		return true;
	}

	/**
	 * 找到合适的生成条码规则
	 *
	 * @return
	 */
	public boolean findTheAppropriateBarcodeGenerationRule(Integer materialId, Integer sum, String listNo)
			throws Exception {

		// 条件+规则
		List<CWmsMaterialBarcodeRuleT> barcodeRuleTs = dao.findMaterialBarcodeRuleList();
		// 属性
		List<CWmsMaterialRuleAttributeT> attributeTs = dao.findMaterialRuleAttributeList();
		// 物料
		CMesJlMaterialT materialT = dao.findMaterial(materialId);
		if (materialT == null) {
			throw new Exception("物料不存在");
		}

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
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}

		System.out.println("满足的条码规则：" + barcodeRules);
		generateBarcode(barcodeRules, materialBarcodeRuleId, materialT, sum, listNo);
		return true;
	}

	/**
	 * 生成条码
	 *
	 * @param barcodeRules          生成条码规则
	 * @param materialBarcodeRuleId 物料条码规则Id
	 * @return
	 */
	public boolean generateBarcode(String barcodeRules, Integer materialBarcodeRuleId, CMesJlMaterialT material,
			Integer sum, String listNo) {

		String str = barcodeRules;
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

		for (int q = 0; q < sum; q++) {
			String barCode = "";
			for (Map<String, Object> map : mapList) {
				String content = map.get("content").toString();
				String type = map.get("type").toString();
				// 获取到流水
				if (type.equals("attribute")) {
					if (content.indexOf("流水") != -1) {
						// 流水类型 日清、月清、总计
						String tiaomaType = content.substring(content.indexOf(":") + 1, content.indexOf("."));
						// 查询使用该规则打印过得最大条码值
						CWmsAllMaterialBarcode allMaterialBarcode = dao.findAllMaterialBarcodeMax(materialBarcodeRuleId,
								tiaomaType);
						if (allMaterialBarcode == null) {
							String liushui = content.substring(content.indexOf(".") + 1, content.indexOf("}"));
							// Integer liushuiLeng = liushui.length();
							// Integer liushuiNumber =
							// Integer.parseInt(liushui)+1;
							// liushui = "";
							// for (int i = liushuiNumber.toString().length(); i
							// < liushuiLeng; i++) {
							// liushui+="0";
							// }
							// liushui+=liushuiNumber;
							barCode += liushui;
						} else {
							// 流水的位数
							Integer liushuiLeng = content.substring(content.indexOf(".") + 1, content.indexOf("}"))
									.length();
							String getBarCode = allMaterialBarcode.getBarCode();
							Integer liushui = Integer.parseInt(
									getBarCode.substring(getBarCode.length() - liushuiLeng, getBarCode.length())) + 1;
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
							barCode += material.getBomId();
							break;
						case "规格":
							barCode += material.getSpecifications();
							break;
						case "型号":
							barCode += material.getTypenum();
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

			dao.updateStorageDetailT(barCode, material.getId(), listNo);

			dao.addAllMaterialBarcode(dx);
		}

		return true;
	}

	public static void main(String[] args) {
		String s = "[{\"id\":10892,\"importId\":\"WIN017725\",\"importGoodsCode\":\"1.02.04.0721\",\"importPackQuantity\":4,\"importWarehouseId\":1,\"importRemark\":\"\",\"importMaterialName\":\"两孔按钮盒XALB02C\",\"importProjectNo\":\"XT392\",\"receivedNumber\":0,\"result\":0,\"projectId\":129,\"materialId\":6438,\"number\":4}]";
		List<JSONObject> jsonList = JSONObject.parseArray(s, JSONObject.class);
		for (JSONObject jsonObject : jsonList) {
			System.out.println(jsonObject);
		}
	}

	@Override
	public List<JSONObject> projectList() {
		// TODO Auto-generated method stub
		return dao.projectList();
	}

}
