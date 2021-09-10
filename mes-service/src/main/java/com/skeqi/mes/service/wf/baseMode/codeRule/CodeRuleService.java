package com.skeqi.mes.service.wf.baseMode.codeRule;

import com.skeqi.mes.pojo.wf.baseMode.coderRule.CMesCodeRuleT;
import com.skeqi.mes.util.Rjson;

import java.util.List;

public interface CodeRuleService {

	List<CMesCodeRuleT> findCodeRuleList(CMesCodeRuleT codeRuleT);

	Integer addCodeRule(CMesCodeRuleT cMesCodeRuleT) throws Exception;

	Rjson delCodeRuleListById(Integer id) throws Exception;

	Rjson editCodeRule(CMesCodeRuleT codeRuleT) throws Exception;

	String getLatestCode(String code) throws Exception;

	Integer updateLatestCode(CMesCodeRuleT codeRuleT) throws Exception;
}
