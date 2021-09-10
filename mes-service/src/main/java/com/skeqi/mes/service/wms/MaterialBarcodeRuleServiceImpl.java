package com.skeqi.mes.service.wms;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.wms.MaterialBarcodeRuleDao;
import com.skeqi.mes.pojo.wms.CWmsMaterialBarcodeRuleT;
import com.skeqi.mes.pojo.wms.CWmsMaterialRuleAttributeT;


/**规则描述
 * 物料条码规则
 *
 * @author Administrator
 *
 */
@Service
public class MaterialBarcodeRuleServiceImpl implements MaterialBarcodeRuleService {

	@Autowired
	MaterialBarcodeRuleDao dao;

	@Override
	public List<CWmsMaterialBarcodeRuleT> findMaterialBarcodeRuleList(CWmsMaterialBarcodeRuleT dx) {
		return dao.findMaterialBarcodeRuleList(dx);
	}

	@Override
	public int addMaterialBarcodeRule(CWmsMaterialBarcodeRuleT dx) throws Exception {

		String condition = dx.getCondition();
		String rule = dx.getRule();
		List<CWmsMaterialRuleAttributeT> list = dao.findMaterialRuleAttributeList();

		try {
			if (checkCondition(condition, list)) {
				System.out.println("条件格式通过");
			}
			if (checkRule(rule, list)) {
				System.out.println("规则格式通过");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}

		return dao.addMaterialBarcodeRule(dx);
	}

	@Override
	public int updateMaterialBarcodeRule(CWmsMaterialBarcodeRuleT dx) throws Exception {

		String condition = dx.getCondition();
		String rule = dx.getRule();
		List<CWmsMaterialRuleAttributeT> list = dao.findMaterialRuleAttributeList();

		try {
			if (checkCondition(condition, list)) {
				System.out.println("条件格式通过");
			}
			if (checkRule(rule, list)) {
				System.out.println("规则格式通过");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}

		return dao.updateMaterialBarcodeRule(dx);
	}

	@Override
	public int deleteMaterialBarcodeRule(Integer id) {
		return dao.deleteMaterialBarcodeRule(id);
	}

	/**
	 * 校验条件
	 *
	 * @param str
	 * @return
	 */
	public static boolean checkCondition(String str, List<CWmsMaterialRuleAttributeT> materialRuleAttributeList)
			throws Exception {

		List<String> strList = new ArrayList<String>();
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		try {
			while (true) {

				String string = str.substring(0, str.indexOf(";"));
				String material = string.substring(0, string.indexOf("."));
				if (!material.equals("物料")) {
					throw new Exception("验证物料——格式错误");
				} else {
					// System.out.println("验证物料通过");
				}

				String type = string.substring(string.indexOf(".") + 1, string.indexOf("="));
				for (int i = 0; i < materialRuleAttributeList.size(); i++) {
					if (materialRuleAttributeList.get(i).getAttributeCn().equals(type)) {
						// System.out.println("验证类型通过");
						break;
					} else {
						if (i == (materialRuleAttributeList.size() - 1)) {
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
				System.out.println();
			}
		} catch (StringIndexOutOfBoundsException e) {
			e.printStackTrace();
			throw new Exception("规则条件格式有误，正确的格式如：'物料.物料编码=aaa;'");
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		for (Map<String, Object> map : mapList) {
			System.out.println(map.get("material") + "==" + map.get("type") + "==" + map.get("value"));
		}

		return true;
	}

	/**
	 * 校验规则
	 *
	 * @param str
	 * @return
	 */
	public static boolean checkRule(String str, List<CWmsMaterialRuleAttributeT> materialRuleAttributeList)
			throws Exception {

		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		try {

			while (true) {
				Map<String, Object> map = new HashedMap<String, Object>();
				if (str.length() > 0) {
					Integer num = str.indexOf("{");
					if (num > 0) {
						if (str.indexOf("}") < str.indexOf("{")) {
							throw new Exception("格式中包含'{xx:xx.xx}'可能缺失'{'或者'}'");
						}
						String text = str.substring(0, str.indexOf("{"));
						map.put("type", "text");
						map.put("content", text);
						str = str.substring(str.indexOf("{"), str.length());
					} else {
						String strNet = str.substring(str.indexOf("{") + 1, str.length());
						if (strNet.indexOf("{") != -1) {
							if (strNet.indexOf("{") < strNet.indexOf("}")
									||strNet.indexOf("}")==-1) {
								System.out.println("格式中包含'{xx:xx.xx}'可能缺失'{'或者'}'");
								break;
							}
						}else {
							if (str.indexOf("}") == -1) {
								throw new Exception("格式中包含'{xx:xx.xx}'可能缺失'{'或者'}'");
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
			for (Map<String, Object> map : mapList) {
				System.out.println(map.get("content").toString());
			}
			for (int i = 0; i < mapList.size(); i++) {
				if(mapList.get(i).get("type").equals("attribute")){
					String content = mapList.get(i).get("content").toString();
					System.out.println();
					String type = content.substring(1,content.indexOf(":"));
					System.out.println("==========================="+type);
					if(type.equals("流水")){
						break;
					}
				}
				if(i==(mapList.size()-1)){
					throw new Exception("没有检测到'流水信息'");
				}

			}
			for (Map<String, Object> map : mapList) {
				if (map.get("type").equals("attribute")) {
					String type = map.get("content").toString().substring(1,
							map.get("content").toString().indexOf(":"));
					if (type.equals("日期")) {
						String riQiGeSi = map.get("content").toString().substring(
								map.get("content").toString().indexOf(":") + 1,
								map.get("content").toString().length() - 1);
						if (riQiGeSi.equals("YYYY-MM-DD") || riQiGeSi.equals("YY-MM-DD") || riQiGeSi.equals("MM-DD")
								|| riQiGeSi.equals("YYYYMMDD") || riQiGeSi.equals("YYMMDD")
								|| riQiGeSi.equals("MMDD")) {
							System.out.println("日期验证通过");
						} else {
							throw new Exception("日期格式只能为：'YYYY-MM-DD','YY-MM-DD','YYYYMMDD','YYMMDD','MM-DD','MMDD'");
						}
						System.out.println("=================");
					} else if (type.equals("属性")) {
						String materialAndType = map.get("content").toString().substring(
								map.get("content").toString().indexOf(":") + 1,
								map.get("content").toString().length() - 1);
						String material = materialAndType.substring(0, materialAndType.indexOf("."));
						if (material.equals("物料")) {
							String materialType = materialAndType.substring(materialAndType.indexOf(".") + 1,
									materialAndType.length());
							for (int i = 0; i < materialRuleAttributeList.size(); i++) {
								if (materialRuleAttributeList.get(i).getAttributeCn().equals(materialType)) {
									// System.out.println("验证类型通过");
									break;
								} else {
									if (i == (materialRuleAttributeList.size() - 1)) {
										throw new Exception("验证类型——参数错误:" + materialType);
									}
								}
							}
						} else {
							throw new Exception("物料属性格式只能是：'{属性:物料.类型}'");
						}
					}else if(type.equals("流水")){
						System.out.println("流水==========");
						String liuShuiType = map.get("content").toString();
						String liuShui = liuShuiType.substring(liuShuiType.indexOf(".")+1,liuShuiType.length()-1);
						System.out.println(Integer.parseInt(liuShui));
						//日清、月清、总计
						String qing = liuShuiType.substring(liuShuiType.indexOf(":")+1,liuShuiType.indexOf("."));
						if(qing.equals("日清")
								||qing.equals("月清")
								||qing.equals("总计")){
							System.out.println("流水正确");
						}else{
							throw new Exception("规则描述格式有误：流水格式");
						}

//						liuShui = "1"+liuShui;
//						Integer liuShuiNumber = Integer.parseInt(liuShui)+1;
//						liuShui = liuShuiNumber.toString().substring(1,liuShuiNumber.toString().length());
//						System.out.println(liuShui);
					}
				}
			}
		} catch (StringIndexOutOfBoundsException e) {
			e.printStackTrace();
			throw new Exception("规则描述格式有误,正确的格式日:'SKQ{属性:物料.类型1}AA{日期:YYMMDD}{流水号:0000001}'");
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return true;
	}

	public static void main(String[] args) {
		// String str = "物料.物料编码=aaa;物料.规格=ccc;物料.型号=ccc;";
		List<CWmsMaterialRuleAttributeT> materialRuleAttributeList = new ArrayList<CWmsMaterialRuleAttributeT>();
		CWmsMaterialRuleAttributeT dx = new CWmsMaterialRuleAttributeT();
		dx.setAttributeCn("物料编码");
		dx.setColumn("BOM_ID");
		materialRuleAttributeList.add(dx);
		dx = new CWmsMaterialRuleAttributeT();
		dx.setAttributeCn("规格");
		dx.setColumn("SPECIFICATIONS");
		materialRuleAttributeList.add(dx);
		dx = new CWmsMaterialRuleAttributeT();
		dx.setAttributeCn("型号");
		dx.setColumn("TYPENUM");
		materialRuleAttributeList.add(dx);
		//
		// try {
		// if (checkCondition(str, materialRuleAttributeList)) {
		// System.out.println("条件格式通过");
		// }
		// } catch (Exception e) {
		// System.out.println(e.getMessage());
		// }

		String str = "SKQ{属性:物料.物料编码}AA{日期:YYMMDD}{流水:日清.0000001}";
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();

		while (true) {
			Map<String, Object> map = new HashedMap<String, Object>();
			if (str.length() > 0) {
				Integer num = str.indexOf("{");
				if (num > 0) {
					if (str.indexOf("}") < str.indexOf("{")) {
						System.out.println("格式中包含'{xx:xx.xx}'可能缺失'{'或者'}'");
						break;
					}
					String text = str.substring(0, str.indexOf("{"));
					map.put("type", "text");
					map.put("content", text);
					str = str.substring(str.indexOf("{"), str.length());
				} else {
					String strNet = str.substring(str.indexOf("{") + 1, str.length());
					if (strNet.indexOf("{") != -1) {
						if (strNet.indexOf("{") < strNet.indexOf("}")
								||strNet.indexOf("}")==-1) {
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
				System.out.println("=================");
				break;
			}
		}

		boolean result = true;

		for (Map<String, Object> map : mapList) {
			if (map.get("type").equals("attribute")) {
				String type = map.get("content").toString().substring(1, map.get("content").toString().indexOf(":"));
				if (type.equals("日期")) {
					String riQiGeSi = map.get("content").toString().substring(
							map.get("content").toString().indexOf(":") + 1, map.get("content").toString().length() - 1);
					if (riQiGeSi.equals("YYYY-MM-DD") || riQiGeSi.equals("YY-MM-DD") || riQiGeSi.equals("MM-DD")
							|| riQiGeSi.equals("YYYYMMDD") || riQiGeSi.equals("YYMMDD") || riQiGeSi.equals("MMDD")) {
						System.out.println("日期验证通过");
					} else {
						System.out.println("日期格式只能为：'YYYY-MM-DD','YY-MM-DD','YYYYMMDD','YYMMDD','MM-DD','MMDD'");
						result = false;
					}
					System.out.println("=================");
				} else if (type.equals("属性")) {
					String materialAndType = map.get("content").toString().substring(
							map.get("content").toString().indexOf(":") + 1, map.get("content").toString().length() - 1);
					String material = materialAndType.substring(0, materialAndType.indexOf("."));
					if (material.equals("物料")) {
						String materialType = materialAndType.substring(materialAndType.indexOf(".") + 1,
								materialAndType.length());
						for (int i = 0; i < materialRuleAttributeList.size(); i++) {
							if (materialRuleAttributeList.get(i).getAttributeCn().equals(materialType)) {
								// System.out.println("验证类型通过");
								break;
							} else {
								if (i == (materialRuleAttributeList.size() - 1)) {
									System.out.println("验证类型——参数错误:" + materialType);
									result = false;
								}
							}
						}
					} else {
						System.out.println("物料属性格式只能是：'{属性:物料.类型}'");
						result = false;
					}
					System.out.println("=================");
				}else if(type.equals("流水")){
					System.out.println("流水==========");
					String liuShuiType = map.get("content").toString();
					String liuShui = liuShuiType.substring(liuShuiType.indexOf(".")+1,liuShuiType.length()-1);
					System.out.println(Integer.parseInt(liuShui));
					//日清、月清、总计
					String qing = liuShuiType.substring(liuShuiType.indexOf(":")+1,liuShuiType.indexOf("."));
					if(qing.equals("日清")
							||qing.equals("月清")
							||qing.equals("总计")){
						System.out.println("流水正确");
					}else{
						System.out.println("格式不对");
					}

//					liuShui = "1"+liuShui;
//					Integer liuShuiNumber = Integer.parseInt(liuShui)+1;
//					liuShui = liuShuiNumber.toString().substring(1,liuShuiNumber.toString().length());
//					System.out.println(liuShui);
				}
			}
			if (!result) {
				return;
			}
		}

	}

}
