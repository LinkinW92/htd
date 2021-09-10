package com.skeqi.mes.controller.db;

import com.skeqi.mes.service.db.DBHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("db")
public class DBController {

    @Autowired
    private DBHelper dbHelper;

    @RequestMapping("/version")
    public String getDBCurrentVersion() {
        return dbHelper.getDBCurrentVersion();
    }

    @RequestMapping("/version/system")
    public String getSystemCurrentVersion() {
        return dbHelper.getSystemCurrentVersion();
    }

    public static class TargetVersionDto {
        public String targetVersion;
    }

    @RequestMapping(value = "/version/upgradeToSpecific", method = RequestMethod.POST)
    public Map<String, Object> upgradeToSpecificVersion(@RequestBody TargetVersionDto targetVersion) {
        return dbHelper.upgradeToSpecificVersion(targetVersion.targetVersion);
    }

    @RequestMapping(value = "/version/upgradeToNewest", method = RequestMethod.POST)
    public Map<String, Object> upgradeToNewestVersion() {
        return dbHelper.upgradeToNewestVersion();
    }

    @RequestMapping("/version/detail")
    public Map<String, Object> getDBVersionDetails() {
        return dbHelper.getDBVersionDetails();
    }


}
