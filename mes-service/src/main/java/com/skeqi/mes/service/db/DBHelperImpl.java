package com.skeqi.mes.service.db;

import com.skeqi.mes.common.lcy.OracleEncrytion;
import com.skeqi.mes.mapper.db.CMesDBVersionDao;
import com.skeqi.mes.util.StringUtil;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service
public class DBHelperImpl implements DBHelper {

    private static final int ERROR_CODE_TARGET_VERSION_NOT_EXIST = -1000;

    private static final int ERROR_CODE_UPGRADE_DONE = 0;

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CMesDBVersionDao cMesDBVersionDao;

    @Autowired
    public DBUpgradeConfig dbUpgradeConfig;

    @Autowired
    public DBVersionComparator dbVersionComparator;

    @Override
    public String getDBCurrentVersion() {
        return cMesDBVersionDao.getCurrentDatabaseVersion();
    }

//    public DBHelperImpl() {
//        Properties prop = new Properties();
//        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("jdbc.properties"); //properties
//
//        if (inputStream == null) {
//            System.out.println("inputStream null");
//        }
//
//        try {
//            prop.load(inputStream);
//            String jdbc_url = prop.getProperty("jdbc_url");
//            String username = prop.getProperty("username");
//            String password = prop.getProperty("password");
//
//            BasicDataSource dataSource = new BasicDataSource();
//            dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//            dataSource.setUrl(jdbc_url);
//            dataSource.setUsername(OracleEncrytion.getOraclePassword(username));
//            dataSource.setPassword(OracleEncrytion.getOraclePassword(password));
//            jdbcTemplate = new JdbcTemplate(dataSource);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    private Map<String, Object> buildResponse(int errorCode, String errorMsg) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", errorCode);
        map.put("msg", errorMsg);
        return map;
    }

    @Override
    public Map<String, Object> upgradeToSpecificVersion(String targetVersion) {
        List<DBUpgrader> dbUpgraderList = dbUpgradeConfig.getConfig(jdbcTemplate);
        DBUpgrader target = null;
        for (DBUpgrader dbUpgrader : dbUpgraderList) {
            if (dbUpgrader.newVersion.equals(targetVersion)) {
                target = dbUpgrader;
                break;
            }
        }

        if (target == null) {
            String errorMsg = "目标版本不存在, 升级中止";
            System.out.println(errorMsg);
            return buildResponse(ERROR_CODE_TARGET_VERSION_NOT_EXIST, errorMsg);
        }

        String currentVersion = getDBCurrentVersion();

        if (currentVersion.equals(getSystemCurrentVersion())) {
            String errorMsg = "已经是最新版本, 升级中止";
            System.out.println(errorMsg);
            return buildResponse(ERROR_CODE_TARGET_VERSION_NOT_EXIST, errorMsg);
        }

        if (currentVersion.equals(targetVersion)) {
            String errorMsg = "目标版本和当前版本相同, 升级中止";
            System.out.println(errorMsg);
            return buildResponse(ERROR_CODE_TARGET_VERSION_NOT_EXIST, errorMsg);
        }

        while (!currentVersion.equals(targetVersion)) {
            boolean exist = false;
            for (DBUpgrader dbUpgrader : dbUpgraderList) {
                if (dbUpgrader.oldVersion.equals(currentVersion)) {
                    executeUpgrade(dbUpgrader);
                    currentVersion = dbUpgrader.newVersion;
                    exist = true;
                    break;
                }
            }

            if (!exist) {
                String errorMsg = "版本升级信息" + currentVersion + " 缺失，升级终止";
                System.out.println(errorMsg);
                return buildResponse(ERROR_CODE_TARGET_VERSION_NOT_EXIST, errorMsg);
            }
        }

        return buildResponse(ERROR_CODE_UPGRADE_DONE, "升级成功，已升级至 " + targetVersion);
    }

    private void executeUpgrade(DBUpgrader dbUpgrader) {
        for (String sql : dbUpgrader.sqlList) {
            System.out.println("Apply sql:" + sql);
            try {
                jdbcTemplate.update(sql);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Apply sql failed:" + sql);
            }
        }

        if (dbUpgrader.extraRunnable != null) {
            dbUpgrader.extraRunnable.run();
        }

        String sql;
        String currVersion = getDBCurrentVersion();
        if (TextUtils.isEmpty(currVersion)) {
            sql = "insert into db_version (version) values ('" + dbUpgrader.newVersion + "')";
        } else {
            sql = "update db_version set version = '" + dbUpgrader.newVersion + "'";
        }
        System.out.println("Apply update db version sql:" + sql);
        jdbcTemplate.update(sql);
    }

    @Override
    public Map<String, Object> upgradeToNewestVersion() {
        return upgradeToSpecificVersion(getSystemCurrentVersion());
    }

    @Override
    public Map<String, Object> getDBVersionDetails() {
        List<DBUpgrader> dbUpgraderList = dbUpgradeConfig.getConfig(jdbcTemplate);
        Map<String, Object> map = new HashMap<String, Object>();

        String currentVersion = getDBCurrentVersion();
        List<DBUpgrader> list = new ArrayList<>();
        for (DBUpgrader upgrader : dbUpgraderList) {
            if (dbVersionComparator.compare(upgrader.newVersion, currentVersion) > 0) {
                list.add(upgrader);
            }
        }

        map.put("versionDetails", list);
        return map;
    }

    @Override
    public String getSystemCurrentVersion() {
        List<DBUpgrader> dbUpgraderList = dbUpgradeConfig.getConfig(jdbcTemplate);
        String maxVersion = dbUpgraderList.get(0).newVersion;
        for (DBUpgrader upgrader : dbUpgraderList) {
            if (dbVersionComparator.compare(upgrader.newVersion, maxVersion) > 0) {
                maxVersion = upgrader.newVersion;
            }
        }
        return maxVersion;
    }
}

