package com.skeqi.mes.service.zch;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.pojo.wf.linesidelibrary.RLslMaterialRequestT;

public interface LineSideLibraryApiService {

	JSONObject materialReceiving(Map<String, Object> map);

	JSONObject manualRequest(Map<String, Object> map) throws ParseException, IOException;

	JSONObject reclaimAlarm(Map<String, Object> map) throws IOException;

	JSONObject materialScrap(Map<String, Object> map);

	JSONObject materialReceivingQuery(Map<String, Object> map);

	List<RLslMaterialRequestT> findLastRequest();

}
