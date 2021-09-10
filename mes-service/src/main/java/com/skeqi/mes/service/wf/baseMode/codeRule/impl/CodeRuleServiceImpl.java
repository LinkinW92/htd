package com.skeqi.mes.service.wf.baseMode.codeRule.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.skeqi.mes.pojo.wf.baseMode.coderRule.CMesCodeRuleT;
import com.skeqi.mes.service.wf.baseMode.codeRule.CodeRuleService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.wf.baseMode.codeRule.CodeRuleConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.wf.baseMode.codeRule.CodeRuleDao;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * @author Lenovo
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CodeRuleServiceImpl implements CodeRuleService {

	@Autowired
	CodeRuleDao dao;

	@Override
	public List<CMesCodeRuleT> findCodeRuleList(CMesCodeRuleT codeRuleT) {
		return dao.findCodeRuleList(codeRuleT);
	}

	@Override
	public Integer addCodeRule(CMesCodeRuleT cMesCodeRuleT) throws Exception {
		//验证规则是否通过
		this.generateCodeRuleValue(cMesCodeRuleT.getCodeRule());
		CMesCodeRuleT ruleByCodeType = dao.findRuleByCodeType(cMesCodeRuleT.getCodeType());
		if (!StringUtils.isEmpty(ruleByCodeType)){
			throw new Exception("已存在记录!!!");
		}
		return dao.addCodeRule(cMesCodeRuleT);
	}

	@Override
	public Rjson delCodeRuleListById(Integer id) throws Exception {
		Integer integer = dao.delCodeRuleListById(id);
		if (integer<1){
			throw new Exception("删除失败!请稍后重试");
		}
		return Rjson.success();
	}

	@Override
	public Rjson editCodeRule(CMesCodeRuleT codeRuleT) throws Exception {
		//验证规则是否通过
		this.generateCodeRuleValue(codeRuleT.getCodeRule());
		Integer rule = dao.editCodeRule(codeRuleT);
		if (rule<1){
			throw new Exception("编辑条码规则失败");
		}
		return Rjson.success();
	}


	/**
	 * 获取最新编号
	 * @param code
	 * @return
	 */
	@Override
	public String getLatestCode(String code) throws Exception {
		CMesCodeRuleT codeRule = dao.findRuleByCodeType(code);
		if (StringUtils.isEmpty(codeRule)){
			throw new Exception("请先添加条码规则！");
		}
		//获取当前日期
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String currentTime = simpleDateFormat.format(new Date());
		//编号周期开始时间
		String createCodeTime = "";
		int i = 1;
		if (!StringUtils.isEmpty(codeRule.getLastCode())){
			//数据不是第一条
			//获取重置周期(0 永不，1 日，2 周，3 月，4 年)
			switch (codeRule.getResetCycle()){
				case 0:
					//数量加1
					i = Integer.parseInt(codeRule.getCodeRuleSuffixValue());
					i++;
					break;
				case 1:
					//编号周期开始时间和当前时间一致则 流水号++
					createCodeTime = simpleDateFormat.format(codeRule.getCreateCodeTime());
					if (createCodeTime.equals(currentTime)){
						//同一天数量加1
						i = Integer.parseInt(codeRule.getCodeRuleSuffixValue());
						i++;
					}
					break;
				case 2:
					//编号周期开始时间和当前时间 是同一周 则 流水号++
					createCodeTime = simpleDateFormat.format(codeRule.getCreateCodeTime());
					if (this.isSameDate(createCodeTime,currentTime)){
						//同一周数量加1
						i = Integer.parseInt(codeRule.getCodeRuleSuffixValue());
						i++;
					}
					break;
				case 3:
					//编号周期开始月份和当前月份 一致则 流水号++
					SimpleDateFormat yyyyMM = new SimpleDateFormat("yyyy-MM");
					//当前年月
					currentTime = yyyyMM.format(new Date());
					//周期开始年月
					createCodeTime = yyyyMM.format(codeRule.getCreateCodeTime());
					if(currentTime.equals(createCodeTime)){
						//同一年月数量加1
						i = Integer.parseInt(codeRule.getCodeRuleSuffixValue());
						i++;
					}
					break;
				case 4:
					//编号周期开始年份和当前年份 一致则 流水号++
					SimpleDateFormat yyyy = new SimpleDateFormat("yyyy");
					//当前年月
					currentTime = yyyy.format(new Date());
					//周期开始年月
					createCodeTime = yyyy.format(codeRule.getCreateCodeTime());
					if(currentTime.equals(createCodeTime)){
						//同一年月数量加1
						i = Integer.parseInt(codeRule.getCodeRuleSuffixValue());
						i++;
					}
					break;
				default:
					break;
			}
		}
		// 1.第一条数据生成时间为周期开始时间
		if (i==1){
			codeRule.setCreateCodeTime(new Date());
		}
		// 2.获取流水号个数
		codeRule.setCodeRuleSuffixValue(String.format("%0" + codeRule.getCodeRuleSuffix() + "d", i));
		// 3.解析条码规则根据条码规则获取规则值
		codeRule.setCodeRuleValue(this.generateCodeRuleValue(codeRule.getCodeRule()));
		// 4.存储最新编号
		codeRule.setLastCode(codeRule.getCodeRulePrefix()+codeRule.getCodeRuleValue()+codeRule.getCodeRuleSuffixValue());

		//更新代码
		Integer up = dao.updateCodeRule(codeRule);
		if (up<1){
			throw new Exception("更新条码规则失败");
		}
		return codeRule.getLastCode();
	}

	/**
	 *解析条码规则根据条码规则生成规则值
	 * @param codeRule
	 * @return
	 */
	private String generateCodeRuleValue(String codeRule) throws Exception {
		StringBuffer stringBuffer = new StringBuffer();
		String[] split = codeRule.split("%");
		SimpleDateFormat sdf = null;
		for (int i = 0; i < split.length; i++) {
			switch (split[i]){
				default:
					throw new Exception("\""+split[i]+"\""+"编号生成规则未定义! 请联系管理员添加规则");
				case CodeRuleConstant.YYYY:
				case CodeRuleConstant.YY:
				case CodeRuleConstant.MM:
				case CodeRuleConstant.DD:
					 sdf = new SimpleDateFormat(split[i]);
					stringBuffer.append(sdf.format(new Date()));
					break;
				case CodeRuleConstant.LL:
					stringBuffer.append("LL");
					break;
			}
		}
		return stringBuffer.toString();
	}


	/**
	 * 更新条码规则最新编号
	 * @param codeRuleT
	 * @return
	 * @throws Exception
	 */
	@Override
	public Integer updateLatestCode(CMesCodeRuleT codeRuleT) throws Exception {
		CMesCodeRuleT codeRule = dao.findRuleByCodeType(codeRuleT.getCodeType());
		if (StringUtils.isEmpty(codeRule)){
			throw new Exception("请先添加条码规则！");
		}
		//最新编号规则value
		Integer getLength = (codeRule.getCodeRulePrefix().length()+codeRule.getCodeRule().length());
		Integer startLength =  codeRule.getCodeRulePrefix().length();
		String ruleValue = codeRuleT.getLastCode().substring(startLength, getLength);
		codeRuleT.setCodeRuleValue(ruleValue);
		//最新编号规则后缀
		String suffixValue = codeRuleT.getLastCode().substring(getLength);
		codeRuleT.setCodeRuleSuffixValue(suffixValue);

		//更新代码
		Integer up = dao.updateCodeRule(codeRuleT);
		if (up<1){
			throw new Exception("更新条码规则失败");
		}
		return up;
	}


	/**
	 * java中如何判断两个日期是否是同一周
	 * java中的Calendar是比较强的。
	 * “2004-12-25”是星期六，也就是说它是2004年中第52周的星期六，
	 * 那么“2004-12-26”到底是2004年的第几周哪，java中经测试取得的它的Week值是1，
	 * 那么也就是说它被看作2005年的第一周了，这个处理是比较好的。
	 * 可以用来判断“2004-12-26”和“2005-1-1”是同一周。
	 */
		public boolean isSameDate(String startTime, String stopTime) throws ParseException {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date d1 = format.parse(startTime);
			Date d2 = format.parse(stopTime);
			Calendar cal1 = Calendar.getInstance();
			Calendar cal2 = Calendar.getInstance();
			//西方周日为一周的第一天，咱得将周一设为一周第一天
			cal1.setFirstDayOfWeek(Calendar.MONDAY);
			cal2.setFirstDayOfWeek(Calendar.MONDAY);
			cal1.setTime(d1);
			cal2.setTime(d2);
			int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
			// subYear==0,说明是同一年
			if (subYear == 0)
			{
				if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR)) {
					return true;
				}
			}
			//subYear==1,说明cal比cal2大一年;java的一月用"0"标识，那么12月用"11"
			else if (subYear == 1 && cal2.get(Calendar.MONTH) == 11)
			{
				if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR)) {
					return true;
				}
			}
			//subYear==-1,说明cal比cal2小一年
			else if (subYear == -1 && cal1.get(Calendar.MONTH) == 11)
			{
				if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR)) {
					return true;
				}
			}
			return false;
		}
}
