package com.skeqi.mes.util.yp.oa;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.yp.oa.InitiateApplicationDao;

/**
 * 流程
 *
 * @author yinp
 *
 */
public class ProcessUtil {

	// 保存流程步骤
	static List<JSONObject> stepList = new ArrayList<JSONObject>();

	/**
	 * 获取步骤
	 *
	 * @param process
	 * @param formDetails
	 * @return
	 * @throws Exception
	 */
	public static List<JSONObject> accessSteps(JSONObject user, JSONObject process, List<JSONObject> formDetails,
			InitiateApplicationDao dao) throws Exception {
		stepList = new ArrayList<JSONObject>();
		getAccessSteps(dao, user, process, formDetails);

		return stepList;

	}

	// 获取步骤
	public static void getAccessSteps(InitiateApplicationDao dao, JSONObject user, JSONObject process,
			List<JSONObject> formDetails) throws Exception {

		if (!process.getString("type").equals("condition")) {
			JSONObject step = new JSONObject();
			step.put("step", stepList.size() + 1);
			step.put("type", process.getString("type"));
			step.put("title", JSONObject.parseObject(process.getString("properties")).getString("title"));
			step.put("content", process.getString("content"));
			step.put("properties", JSONObject.parseObject(process.getString("properties")));
			stepList.add(step);
		}

		if (process.getString("conditionNodes") != null) {
			judgingConditions(dao, user, JSONObject.parseArray(process.getString("conditionNodes"), JSONObject.class),
					formDetails);
		}

		if (process.getString("childNode") != null) {
			getAccessSteps(dao, user, JSONObject.parseObject(process.getString("childNode")), formDetails);
		}
	}

	/**
	 * 判断条件
	 *
	 * @param process     当前节点
	 * @param formDetails 表单详情
	 * @return
	 * @throws Exception
	 */
	public static void judgingConditions(InitiateApplicationDao dao, JSONObject user, List<JSONObject> process,
			List<JSONObject> formDetails) throws Exception {
		// process 条件分支
		// 遍历条件分支
		for (JSONObject jsonObject : process) {

			// 条件优先级
			int priority = jsonObject.getJSONObject("properties").getInteger("priority");

			// 条件名称
			String title = jsonObject.getJSONObject("properties").getString("title");

			// 条件组
			List<String> conditionGropList = JSONObject
					.parseArray(jsonObject.getJSONObject("properties").getString("conditionGrop"), String.class);

			// 没有条件走这条
			if (conditionGropList.size() == 0) {
				// 进入此条路线
				// 进入此条路线
				getAccessSteps(dao, user, jsonObject, formDetails);
				return;
			}

			for (int j = 0; j < conditionGropList.size(); j++) {
				//// 条件的集合
				List<JSONObject> conditions = JSONObject.parseArray(conditionGropList.get(j), JSONObject.class);

				// 保存条件符合的数量
				int number = 0;
				// 遍历条件去判断是否符合
				for (JSONObject json1 : conditions) {
					// 发起着
					if (json1.getJSONObject("conditionValue").getString("type").equals("Sponsor")) {
						String value = json1.getJSONObject("conditionValue").getString("value");
						List<JSONObject> valueList = JSONObject.parseArray(value, JSONObject.class);
						if (valueList.size() == 0) {
							number = number + 1;
							continue;
						}
						i: for (int i = 0; i < valueList.size(); i++) {
							if (valueList.get(i).getString("type").equals("部门")) {
								if (valueList.get(i).getString("id").equals(user.getString("department"))) {
									number++;
									break;
								} else {
									Integer superiorId = user.getInteger("superiorId");
									while (true) {
										if (superiorId.toString().equals("0")) {
											continue i;
										}
										JSONObject department = dao.findDepartmentById(superiorId);
										superiorId = department.getInteger("superiorId");
										if (valueList.get(i).getString("id").equals(department.getString("ID"))) {
											number++;
											break i;
										}

									}
								}
							}
							if (valueList.get(i).getString("type").equals("用户")) {
								if (valueList.get(i).getString("id").equals(user.getString("id"))) {
									number++;
									break;
								}
							}
							if (valueList.get(i).getString("type").equals("角色")) {
								if (valueList.get(i).getString("id").equals(user.getString("roleId"))) {
									number++;
									break;
								}
							}
						}
					} else if (json1.getString("type").equals("职级")) {
						Integer rankGrade = user.getInteger("rankGrade");
						if (rankGrade == null) {
							rankGrade = 99999;
						}
						String type = json1.getJSONObject("conditionValue").getString("type");
						Integer value = json1.getJSONObject("conditionValue").getInteger("value");
						//通过职级id查询职级
						JSONObject rank = dao.findRankById(value);
						if(rank==null) {
							throw new Exception("表单内职级不存在");
						}
						value = rank.getInteger("grade");
						switch (type) {
						case "lt":
							if (rankGrade < value) {
								number++;
							}
							break;
						case "eq":
							if (rankGrade.equals(value)) {
								number++;
							}
							break;
						case "gt":
							if (rankGrade > value) {
								number++;
							}
							break;
						case "lte":
							if (rankGrade <= value) {
								number++;
							}
							break;
						case "gte":
							if (rankGrade >= value) {
								number++;
							}
							break;
						}
					} else {
						// 找到表单内容
						for (JSONObject formData : formDetails) {
							if(formData.getString("typeY").equals("分栏") || formData.getString("typeY").equals("表格")) {
								List<JSONObject> childrenList = JSONObject.parseArray(formData.getString("children"), JSONObject.class);;
								for (JSONObject formData2 : childrenList) {
									if (json1.getString("formId").equals(formData2.getString("formId"))) {
										if (formData2.getJSONObject("attribute").get("value") != null) {
											// 条件对象
											JSONObject conditionValue = json1.getJSONObject("conditionValue");

											// 计算结果
											if (conditionalJudgment(formData2, conditionValue)) {
												number++;
											}
										}
									}
								}
							}else {
								if (json1.getString("formId").equals(formData.getString("formId"))) {
									if (formData.getJSONObject("attribute").get("value") != null) {
										// 条件对象
										JSONObject conditionValue = json1.getJSONObject("conditionValue");

										// 计算结果
										if (conditionalJudgment(formData, conditionValue)) {
											number++;
										}
									}
								}
							}
						}
					}

				}
				// 满足条件的数量等于条件的数量 说明条件全部完成、
				// 确认走此条路线
				if (number == conditions.size()) {
					// 进入此条路线
					getAccessSteps(dao, user, jsonObject, formDetails);
					return;
				}
			}
		}
		throw new Exception("流程错误：当前条件没有设置审批人");

	}

	/**
	 * 判断条件
	 *
	 * @param formData       表单类型
	 * @param conditionValue 条件类型
	 * @return
	 */
	public static boolean conditionalJudgment(JSONObject formData, JSONObject conditionValue) {

		// 表单的属性
		// value:值
		JSONObject attribute = formData.getJSONObject("attribute");
		// 表单value
		Object formValue = attribute.get("value");

		if (formData.getString("typeY").equals("日期范围")) {
			formValue = attribute.get("number");
			if (formValue.toString().equals("")) {
				return false;
			}
		} else if (formData.getString("typeY").equals("金额") || formData.getString("typeY").equals("数字输入框")) {
			if (Integer.parseInt(formValue.toString()) == 0) {
				return false;
			}
		}

		// 条件类型
		String type = conditionValue.getString("type");
		Object value = conditionValue.get("value");

		switch (type) {
		case "lt":
			return Integer.parseInt(formValue.toString()) < Integer.parseInt(value.toString());
		case "eq":
			return formValue.toString().equals(value.toString());
		case "gt":
			return Integer.parseInt(formValue.toString()) > Integer.parseInt(value.toString());
		case "lte":
			return Integer.parseInt(formValue.toString()) <= Integer.parseInt(value.toString());
		case "gte":
			return Integer.parseInt(formValue.toString()) >= Integer.parseInt(value.toString());
		case "bet":
			Integer value1 = Integer.parseInt(formValue.toString());
			String[] value2 = (String[]) formValue;
			boolean[] a = new boolean[2];

			// 小于
			if (value2[1].equals("lt")) {
				if (Integer.parseInt(value2[0]) < value1) {
					a[0] = true;
				}
			} else if (value2[1].equals("lte")) {
				// 小于等于
				if (Integer.parseInt(value2[0]) <= value1) {
					a[0] = true;
				}
			}

			// 小于
			if (value2[1].equals("lt")) {
				if (value1 < Integer.parseInt(value2[3])) {
					a[1] = true;
				}
			} else if (value2[1].equals("lte")) {
				// 小于等于
				if (value1 <= Integer.parseInt(value2[3])) {
					a[1] = true;
				}
			}

			if (a[0] && a[1]) {
				return true;
			}

			return false;
		case "eeq":
			// 完成等于
			// 条件
			List<String> string = JSONObject.parseArray(value.toString(), String.class);

			// 值
			List<String> valueList = JSONObject.parseArray(formValue.toString(), String.class);

			// 值的数量还没有条件要求的数量，就不需要比较了，直接判定为不符合
			if (string.size() > valueList.size()) {
				return false;
			}

			for (String str1 : string) {
				i: for (int i = 0; i < valueList.size(); i++) {
					// 如果满足条件里的此条选项就循环判断下一条
					if (str1.equals(valueList.get(i))) {
						break i;
					}
					// 直到最后一次还没匹配到此条数据就不符合完全等于
					if (i == valueList.size() - 1) {
						return false;
					}
				}
			}

			return true;
		case "contain":
			// 包含
			// 条件
			List<String> string1 = JSONObject.parseArray(value.toString(), String.class);

			try {
				// 表单内容
				List<String> valuestring = JSONObject.parseArray(formValue.toString(), String.class);
				for (String s1 : string1) {
					for (String s2 : valuestring) {
						// 只要条件有等于value的直接通过
						if (s1.equals(s2)) {
							return true;
						}
					}
				}
			} catch (Exception e) {
				// 报错说明是value不是数组
				for (String s1 : string1) {
					// 直接拿value比较
					if (s1.equals(formValue.toString())) {
						return true;
					}
				}
			}

			return false;
		default:
			return false;
		}
	}

}
