package com.skeqi.mes.service.wf;

import java.util.List;
import java.util.Map;

public interface GroupModelingPageService {
     List<Map<String, Object>> findProductionLine();

    List<Map<String, Object>> findStation();

}
