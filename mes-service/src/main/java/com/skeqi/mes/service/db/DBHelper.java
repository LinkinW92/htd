package com.skeqi.mes.service.db;

import java.util.Map;

public interface DBHelper {
    String getSystemCurrentVersion();
    String getDBCurrentVersion();
    Map<String, Object> upgradeToSpecificVersion(String toVersion);
    Map<String, Object> upgradeToNewestVersion();
    Map<String, Object> getDBVersionDetails();
}
