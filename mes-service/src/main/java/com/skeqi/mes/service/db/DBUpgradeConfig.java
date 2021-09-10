package com.skeqi.mes.service.db;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public interface DBUpgradeConfig {
    List<DBUpgrader> getConfig(JdbcTemplate jdbcTemplate);
}
