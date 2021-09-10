package com.skeqi.mes.service.db;

// 6.11 和 6.12 之前才可能会有数据库的变化
// 6.11.1 和 6.11.2 小版本之间不会有数据库的变化
public class DBUpgrader {
    public String oldVersion;
    public String newVersion;
    public String[] sqlList;
    public Runnable extraRunnable;

    public DBUpgrader(String oldVersion, String newVersion, String[] sqlList) {
        this.oldVersion = oldVersion;
        this.newVersion = newVersion;
        this.sqlList = sqlList;
    }

    public DBUpgrader(String oldVersion, String newVersion, String[] sqlList, Runnable extraRunnable) {
        this(oldVersion, newVersion, sqlList);
        this.extraRunnable = extraRunnable;
    }
}
