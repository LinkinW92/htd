package com.skeqi.mes.mapper.zch;

import java.util.List;
import java.util.Map;

public interface HookDao {

	List<Map<String, Object>> findJobList(Map<String, Object> map);

	Integer addJobBinding(Map<String, Object> map);

	Integer editJobBindingStatus(Map<String, Object> map);

	Integer deleteJobBinding(Map<String, Object> map);

	List<Map<String, Object>> findJobBindingList(Map<String, Object> map);

	Map<String, Object> getJobBindingRepetition(Map<String, Object> map);

	List<Map<String, Object>> findJobBindingListByStationId(Map<String, Object> map);

}
