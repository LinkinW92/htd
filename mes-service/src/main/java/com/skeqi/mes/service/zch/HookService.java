package com.skeqi.mes.service.zch;

import java.util.List;
import java.util.Map;

public interface HookService {

	List<Map<String, Object>> findJobList(Map<String, Object> map);

	Integer addJobBinding(Map<String, Object> map);

	Integer editJobBindingStatus(Map<String, Object> map);

	Integer deleteJobBinding(Map<String, Object> map);

	List<Map<String, Object>> findJobBindingList(Map<String, Object> map);

	Boolean checkJob(Map<String, Object> map) throws Exception;

}
