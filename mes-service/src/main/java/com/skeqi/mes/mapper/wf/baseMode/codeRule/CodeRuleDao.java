package com.skeqi.mes.mapper.wf.baseMode.codeRule;

import com.skeqi.mes.pojo.wf.baseMode.coderRule.CMesCodeRuleT;

import java.util.List;

public interface CodeRuleDao {

	List<CMesCodeRuleT> findCodeRuleList(CMesCodeRuleT codeRuleT);

	Integer addCodeRule(CMesCodeRuleT cMesCodeRuleT);

	CMesCodeRuleT findRuleByCodeType(String code);

	Integer delCodeRuleListById(Integer id);

	Integer editCodeRule(CMesCodeRuleT codeRuleT);

	Integer updateCodeRule(CMesCodeRuleT codeRuleT);
}
