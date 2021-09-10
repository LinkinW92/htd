package com.skeqi.mes.service.db;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DBUpgradeConfigImpl implements DBUpgradeConfig {

    @Override
    public List<DBUpgrader> getConfig(JdbcTemplate jdbcTemplate) {

        List<DBUpgrader> list = new ArrayList<>();

        list.add(new DBUpgrader("5.4", "6.11", new String[]{
                //创建表
                "DROP TABLE IF EXISTS `c_alarm_email_config`;",

                        "CREATE TABLE `c_alarm_email_config`  (" +
                        "  `id` int(0) NOT NULL AUTO_INCREMENT," +
                        "  `user_id` int(0) NULL DEFAULT NULL COMMENT '用户id'," +
                        "  `sender_email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发件人'," +
                        "  `the_server` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '服务器'," +
                        "  `authorization_code` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '授权码'," +
                        "  `dt` datetime(0) NULL DEFAULT NULL COMMENT '更新时间'," +
                        "  PRIMARY KEY (`id`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '邮箱服务配置' ROW_FORMAT = Dynamic;",

                //创建表
                "DROP TABLE IF EXISTS `c_alarm_event`;",

                        "CREATE TABLE `c_alarm_event`  (" +
                        "  `id` int(0) NOT NULL AUTO_INCREMENT," +
                        "  `event` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '事件'," +
                        "  `user_id` int(0) NULL DEFAULT NULL," +
                        "  `dt` datetime(0) NULL DEFAULT NULL," +
                        "  `note` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL," +
                        "  `view_mode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '0:不可查；1：可查'," +
                        "  PRIMARY KEY (`id`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '事件' ROW_FORMAT = Dynamic;",

                //创建表
                "DROP TABLE IF EXISTS `c_alarm_ip_white_list`;",
                        "CREATE TABLE `c_alarm_ip_white_list`  (" +
                        "  `id` int(0) NOT NULL AUTO_INCREMENT," +
                        "  `user_id` int(0) NULL DEFAULT NULL," +
                        "  `ip` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL," +
                        "  PRIMARY KEY (`id`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'ip白名单' ROW_FORMAT = Dynamic;",
                //创建表
                "DROP TABLE IF EXISTS `c_alarm_notice_logs`;",
                        "CREATE TABLE `c_alarm_notice_logs`  (" +
                        "  `id` int(0) NOT NULL AUTO_INCREMENT," +
                        "  `fault_id` int(0) NULL DEFAULT NULL COMMENT '故障id'," +
                        "  `lossType` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '损失类型'," +
                        "  `dt` datetime(0) NULL DEFAULT NULL COMMENT '时间'," +
                        "  `sendOut` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发送'," +
                        "  `receive` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接收'," +
                        "  `channels` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '渠道'," +
                        "  `channelsType` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '渠道类型'," +
                        "  PRIMARY KEY (`id`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 95 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '通知记录' ROW_FORMAT = Dynamic;",
                //创建表
                "DROP TABLE IF EXISTS `c_alarm_notification_channels`;",
                        "CREATE TABLE `c_alarm_notification_channels`  (" +
                        "  `id` int(0) NOT NULL AUTO_INCREMENT," +
                        "  `user_id` int(0) NULL DEFAULT NULL COMMENT '用户id'," +
                        "  `notification_channels_type_id` int(0) NULL DEFAULT NULL COMMENT '通知渠道类型id'," +
                        "  `notification_channels` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '通知渠道'," +
                        "  `notification_channels_content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容'," +
                        "  `dt` datetime(0) NULL DEFAULT NULL COMMENT '更新时间'," +
                        "  `view_mode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '0:不可查；1：可查'," +
                        "  PRIMARY KEY (`id`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '通知渠道' ROW_FORMAT = Dynamic;",
                //创建表
                "DROP TABLE IF EXISTS `c_alarm_notification_channels_type`;",
                        "CREATE TABLE `c_alarm_notification_channels_type`  (" +
                        "  `id` int(0) NOT NULL AUTO_INCREMENT," +
                        "  `notification_channels_type_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '渠道类型名称'," +
                        "  `note` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '说明'," +
                        "  PRIMARY KEY (`id`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;",
                //创建表
                "DROP TABLE IF EXISTS `c_alarm_notification_method`;",
                        "CREATE TABLE `c_alarm_notification_method`  (" +
                        "  `id` int(0) NOT NULL AUTO_INCREMENT," +
                        "  `Loss_Type_Id` int(0) NULL DEFAULT NULL COMMENT '损失类型id'," +
                        "  `notification_channels_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '通知渠道'," +
                        "  `user_id` int(0) NULL DEFAULT NULL COMMENT '用户ID'," +
                        "  `service_Id` int(0) NULL DEFAULT NULL COMMENT '服务id'," +
                        "  `problem_Level_Id` int(0) NULL DEFAULT NULL COMMENT '问题等级'," +
                        "  `problem_State` int(0) NULL DEFAULT NULL COMMENT '问题状态(1:触发、2:响应、3:处理)'," +
                        "  `dt` datetime(0) NULL DEFAULT NULL COMMENT '更新时间'," +
                        "  PRIMARY KEY (`id`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 55 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '通知方式' ROW_FORMAT = Dynamic;",
                //创建表
                "DROP TABLE IF EXISTS `c_alarm_problem_level`;",
                        "CREATE TABLE `c_alarm_problem_level`  (" +
                        "  `id` int(0) NOT NULL AUTO_INCREMENT," +
                        "  `level` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '级别'," +
                        "  `explain` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '说明'," +
                        "  PRIMARY KEY (`id`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '问题等级' ROW_FORMAT = Dynamic;",
                //创建表
                "DROP TABLE IF EXISTS `c_alarm_problem_upgrade`;",
                        "CREATE TABLE `c_alarm_problem_upgrade`  (" +
                        "  `id` int(0) NOT NULL AUTO_INCREMENT," +
                        "  `problem_level_id` int(0) NULL DEFAULT NULL COMMENT '问题等级'," +
                        "  `upgrade_problem_level_id` int(0) NULL DEFAULT NULL COMMENT '升级后的问题等级'," +
                        "  `trigger_time` int(0) NULL DEFAULT NULL COMMENT '触发时间/分钟'," +
                        "  `Loss_Type_Id` int(0) NULL DEFAULT NULL COMMENT '损失类型id'," +
                        "  PRIMARY KEY (`id`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '问题升级' ROW_FORMAT = Dynamic;",
                //创建表
                "DROP TABLE IF EXISTS `c_alarm_problem_upgrade_logs`;",
                        "CREATE TABLE `c_alarm_problem_upgrade_logs`  (" +
                        "  `id` int(0) NOT NULL AUTO_INCREMENT," +
                        "  `fault_id` int(0) NULL DEFAULT NULL COMMENT '故障id'," +
                        "  `before_Upgrade_level_Id` int(0) NULL DEFAULT NULL COMMENT '升级前等级编号'," +
                        "  `after_Upgrade_level_Id` int(0) NULL DEFAULT NULL COMMENT '升级后等级编号 '," +
                        "  `dt` datetime(0) NULL DEFAULT NULL COMMENT '升级时间'," +
                        "  PRIMARY KEY (`id`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 229 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '问题升级日志' ROW_FORMAT = Dynamic;",
                //创建表
                "DROP TABLE IF EXISTS `c_alarm_shortmessage_config`;",
                        "CREATE TABLE `c_alarm_shortmessage_config`  (" +
                        "  `id` int(0) NOT NULL AUTO_INCREMENT," +
                        "  `user_id` int(0) NULL DEFAULT NULL COMMENT '用户id'," +
                        "  `header` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '请求头'," +
                        "  `parameter` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '参数'," +
                        "  `charset` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字符集'," +
                        "  `api_url` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '接口地址'," +
                        "  `request_method` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求方式'," +
                        "  `dt` datetime(0) NULL DEFAULT NULL COMMENT '更新时间'," +
                        "  PRIMARY KEY (`id`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '短信服务配置' ROW_FORMAT = Dynamic;",
                //创建表
                "DROP TABLE IF EXISTS `c_andon_message`;",
                        "CREATE TABLE `c_andon_message`  (" +
                        "  `id` int(0) NOT NULL AUTO_INCREMENT," +
                        "  `dt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL," +
                        "  `phone` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL," +
                        "  PRIMARY KEY (`id`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;",
                //创建表
                "DROP TABLE IF EXISTS `c_andon_message_logs`;",
                        "CREATE TABLE `c_andon_message_logs`  (" +
                        "  `id` int(0) NOT NULL AUTO_INCREMENT," +
                        "  `lineName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL," +
                        "  `stationName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL," +
                        "  `lossType` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL," +
                        "  `phone` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL," +
                        "  `fault_id` int(0) NULL DEFAULT NULL," +
                        "  `dt` datetime(0) NULL DEFAULT NULL," +
                        "  PRIMARY KEY (`id`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;",
                //创建表
                "DROP TABLE IF EXISTS `c_mes_activation_record`;",
                        "CREATE TABLE `c_mes_activation_record`  (" +
                        "  `id` int(0) NOT NULL AUTO_INCREMENT," +
                        "  `dt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '使用时间'," +
                        "  `activation_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '激活码'," +
                        "  `days` int(0) NULL DEFAULT NULL COMMENT '天数'," +
                        "  PRIMARY KEY (`id`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;",
                //创建表
                "DROP TABLE IF EXISTS `c_mes_andon_fault_t`;",
                        "CREATE TABLE `c_mes_andon_fault_t`  (" +
                        "  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键自增'," +
                        "  `LINE_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '产线名称'," +
                        "  `STATION_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '工位名称'," +
                        "  `ESTABLISH_DT` datetime(0) NULL DEFAULT NULL COMMENT '创建时间'," +
                        "  `RESPONSE_DT` datetime(0) NULL DEFAULT NULL COMMENT '响应时间'," +
                        "  `SOLVE_DT` datetime(0) NULL DEFAULT NULL COMMENT '解决时间'," +
                        "  `LOSS_TYPE` int(0) NULL DEFAULT NULL COMMENT '损失类型（组织损失、技术损失、质量损失、物料损失）'," +
                        "  `STATUS` int(0) NULL DEFAULT NULL COMMENT '状态（0：已创建，1：已响应，2：已解决）'," +
                        "  `FAULT_TYPE` int(0) NULL DEFAULT NULL COMMENT '故障类型（设备故障、物料故障）'," +
                        "  `EMP` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '处理人员'," +
                        "  `NOTE` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '问题详情'," +
                        "  `TOOL_ID` int(0) NULL DEFAULT NULL COMMENT '设备id'," +
                        "  `TOOL_IDS` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '设备id（中间表使用）'," +
                        "  `DT` datetime(0) NULL DEFAULT NULL," +
                        "  `REASON` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL," +
                        "  `fault_level_id` int(0) NULL DEFAULT 1 COMMENT '故障等级'," +
                        "  PRIMARY KEY (`ID`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 653 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '安灯故障表' ROW_FORMAT = Dynamic;",
                //创建表
                "DROP TABLE IF EXISTS `c_mes_andon_info_t`;",
                        "CREATE TABLE `c_mes_andon_info_t`  (" +
                        "  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT 'ID'," +
                        "  `LINE_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '产线名称'," +
                        "  `STATION_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '工位名称'," +
                        "  `DT` datetime(0) NULL DEFAULT NULL COMMENT '时间'," +
                        "  `SN` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'SN'," +
                        "  `PRODUCT_MARK` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '产品标记'," +
                        "  `COUNT_TYPE` int(0) NULL DEFAULT NULL COMMENT '计数方式'," +
                        "  `WORK_ID` int(0) NULL DEFAULT NULL COMMENT '工单id'," +
                        "  `pattern` int(0) NULL DEFAULT NULL," +
                        "  PRIMARY KEY (`ID`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 7347 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;",
                //创建表
                "DROP TABLE IF EXISTS `c_mes_andon_plan_t`;",
                        "CREATE TABLE `c_mes_andon_plan_t`  (" +
                        "  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id'," +
                        "  `DT` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '时间'," +
                        "  `PLAN_START_TIME` datetime(0) NULL DEFAULT NULL COMMENT '计划开始时间'," +
                        "  `PLAN_END_TIME` datetime(0) NULL DEFAULT NULL COMMENT '计划完工时间'," +
                        "  `ACTUAL_START_TIME` datetime(0) NULL DEFAULT NULL COMMENT '实际开始时间'," +
                        "  `ACTUAL_END_TIME` datetime(0) NULL DEFAULT NULL COMMENT '实际完工时间'," +
                        "  `PLAN_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '计划名称'," +
                        "  `PLAN_NUMBER` int(0) NULL DEFAULT NULL COMMENT '计划数量'," +
                        "  `COMPLETE_NUMBER` int(0) NULL DEFAULT NULL COMMENT '完成数量'," +
                        "  `REMAIND_NUMBER` int(0) NULL DEFAULT NULL COMMENT '剩余数量'," +
                        "  `OK_NUMBER` int(0) NULL DEFAULT NULL COMMENT '合格数量'," +
                        "  `NG_NUMBER` int(0) NULL DEFAULT NULL COMMENT '不合格数量'," +
                        "  `LINE_ID` int(0) NULL DEFAULT NULL COMMENT '产线ID'," +
                        "  `COMPLETE_FLAG` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '完成标记 0:表示初始化 1：表示开始 2：表示暂停 3：表示强制关闭 4：表示完成'," +
                        "  `PRODUCTION_ID` int(0) NULL DEFAULT NULL COMMENT '产品id'," +
                        "  PRIMARY KEY (`ID`) USING BTREE" +
                        ") ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;",
                //创建表
                "DROP TABLE IF EXISTS `c_mes_area_t`;",
                        "CREATE TABLE `c_mes_area_t`  (" +
                        "  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '区域id'," +
                        "  `areaCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区域编号'," +
                        "  `areaName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区域名称'," +
                        "  `describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区域描述'," +
                        "  `companyCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属公司'," +
                        "  `factoryCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属工厂'," +
                        "  `dateTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间'," +
                        "  `alterDt` datetime(0) NULL DEFAULT NULL COMMENT '修改时间'," +
                        "  PRIMARY KEY (`id`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '区域表' ROW_FORMAT = Dynamic;",
                //创建表
                "DROP TABLE IF EXISTS `c_mes_board_t`;",
                        "CREATE TABLE `c_mes_board_t`  (" +
                        "  `id` int(0) NOT NULL AUTO_INCREMENT," +
                        "  `boardName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名字'," +
                        "  `boardType` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '看板类型'," +
                        "  `lineName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产线名称'," +
                        "  PRIMARY KEY (`id`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;",
                //增加列
                "ALTER TABLE c_mes_busbar_t ADD WELDING_SPEED varchar(255) DEFAULT NULL COMMENT '焊接速度';",

                //创建表
                "DROP TABLE IF EXISTS `c_mes_call_logs`;",
                        "CREATE TABLE `c_mes_call_logs`  (" +
                        "  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键'," +
                        "  `CALL_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接口名称'," +
                        "  `CALL_TIME` datetime(0) NULL DEFAULT NULL COMMENT '调用时间'," +
                        "  `CALLER` int(0) NULL DEFAULT NULL COMMENT '调用人id'," +
                        "  `PARAMETER` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数'," +
                        "  `RETURN_RESULT` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '返回结果'," +
                        "  `RETURN_TIME` datetime(0) NULL DEFAULT NULL COMMENT '返回时间'," +
                        "  PRIMARY KEY (`ID`) USING BTREE" +
                        ") ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '调用接口记录表' ROW_FORMAT = Dynamic;",
                //增加列
                "ALTER TABLE c_mes_ccd_photograph_t ADD CCD_PHOTOGRAPH_LOCATION varchar(255) DEFAULT NULL COMMENT '位置';",
                //增加列
                "ALTER TABLE c_mes_ccd_photograph_t ADD CCD_PHOTOGRAPH_POWER_NUM varchar(255) DEFAULT NULL COMMENT '极柱数量编号';",
                //创建表
                "DROP TABLE IF EXISTS `c_mes_check_data`;",
                        "CREATE TABLE `c_mes_check_data`  (" +
                        "  `id` int(0) NOT NULL AUTO_INCREMENT," +
                        "  `dt` datetime(0) NULL DEFAULT NULL," +
                        "  `code` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL," +
                        "  PRIMARY KEY (`id`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 221 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;",
                //创建表
                "-- Table structure for c_mes_code_rule_t" +
                        "-- ----------------------------" ,
                        "DROP TABLE IF EXISTS `c_mes_code_rule_t`;",
                        "CREATE TABLE `c_mes_code_rule_t`  (" +
                        "  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键'," +
                        "  `DT` datetime(0) NULL DEFAULT NULL COMMENT '创建时间'," +
                        "  `ALTER_DT` datetime(0) NULL DEFAULT NULL COMMENT '修改时间'," +
                        "  `CODE_RULE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编号规则'," +
                        "  `CODE_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编号名称'," +
                        "  `LAST_CODE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '当前最后编号'," +
                        "  PRIMARY KEY (`ID`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '编号规则表' ROW_FORMAT = Dynamic;",
                        "" +
                        "-- ----------------------------" ,
                        "-- Table structure for c_mes_company_t" +
                        "-- ----------------------------" ,
                        "DROP TABLE IF EXISTS `c_mes_company_t`;",
                        "CREATE TABLE `c_mes_company_t`  (" +
                        "  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '公司id'," +
                        "  `companyCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司编号'," +
                        "  `companyName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司名称'," +
                        "  `describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司描述'," +
                        "  `companyAddress` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司地址'," +
                        "  `dateTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间'," +
                        "  `alterDt` datetime(0) NULL DEFAULT NULL COMMENT '修改时间'," +
                        "  PRIMARY KEY (`id`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '公司表' ROW_FORMAT = Dynamic;",
                //增加列
                "ALTER TABLE c_mes_crud_t ADD MENU_ID int(0) DEFAULT NULL COMMENT '模板id';",
                //设置主键
                "ALTER TABLE c_mes_crud_t DROP PRIMARY KEY ,ADD PRIMARY KEY ( `ID` );",
                //修改表注释
                "ALTER TABLE c_mes_crud_t COMMENT '用户增删改权限表';",
                //创建表
                "DROP TABLE IF EXISTS `c_mes_custom_property`;",
                        "CREATE TABLE `c_mes_custom_property`  (" +
                        "  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '属性id'," +
                        "  `PROPERTY_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '属性名称'," +
                        "  `PROPERTY_ENGLISH_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '属性英文名称'," +
                        "  `PROPERTY_TYPE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '属性类型'," +
                        "  `OBJECT_TYPE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '对象类型（物料，产品，订单，工单等）'," +
                        "  `BIND_SCOPE_KEY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '属性范围 （全部，某个字段）'," +
                        "  `BIND_CONDITION` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '属性范围判断条件'," +
                        "  `BIND_SCOPE_VALUE` varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '属性范围值'," +
                        "  `PROPERTY_GROUP` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '属性分组'," +
                        "  `PROPERTY_EXPLAIN` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '属性说明'," +
                        "  `DEFAULT` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'default value'," +
                        "  PRIMARY KEY (`ID`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '自定义属性表' ROW_FORMAT = Dynamic;",

                //创建表
                "DROP TABLE IF EXISTS `c_mes_custom_property_value`;",
                        "CREATE TABLE `c_mes_custom_property_value`  (" +
                        "  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '自定义属性内容ID'," +
                        "  `VALUE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内容'," +
                        "  `PROPERTY_ID` int(0) NULL DEFAULT NULL COMMENT '自定义属性外键'," +
                        "  `OBJECT_TYPE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属对象（物料，订单）'," +
                        "  `OBJECT_CODE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属对象明细（某个物料）对象编号'," +
                        "  `BIND_SCOPE_KEY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属范围'," +
                        "  PRIMARY KEY (`ID`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 78 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '自定义属性内容表' ROW_FORMAT = Dynamic;",
                //创建表
                "-- ----------------------------" ,
                        "-- Table structure for c_mes_customer_t" +
                        "-- ----------------------------" ,
                        "DROP TABLE IF EXISTS `c_mes_customer_t`;",
                        "CREATE TABLE `c_mes_customer_t`  (" +
                        "  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键'," +
                        "  `DT` datetime(0) NULL DEFAULT NULL COMMENT '创建时间'," +
                        "  `COMPANY_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司名称'," +
                        "  `CUSTOMER_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人'," +
                        "  `PHONE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话'," +
                        "  PRIMARY KEY (`ID`) USING BTREE" +
                        ") ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '客户表' ROW_FORMAT = Dynamic;",
                        "" +
                        "-- ----------------------------" ,
                        "-- Table structure for c_mes_day" +
                        "-- ----------------------------" ,
                        "DROP TABLE IF EXISTS `c_mes_day`;",
                        "CREATE TABLE `c_mes_day`  (" +
                        "  `day_num` int(0) NOT NULL" +
                        ") ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;",
                //增加列
                "ALTER TABLE c_mes_device_repair ADD parentId int(0) DEFAULT NULL COMMENT '设备id';",
                //增加列
                //"ALTER TABLE c_mes_device_spot_t ADD `DT` datetime(0) DEFAULT NULL COMMENT '时间';",

                        "ALTER TABLE c_mes_device_spot_t ADD parentId int(0) DEFAULT NULL COMMENT '设备id';",
                //增加列
                "ALTER TABLE c_mes_device_t " +
                        "ADD DEVICE_TYPE varchar(150) DEFAULT NULL COMMENT '设备类型'," +
                        "ADD DEVICE_IP varchar(150) DEFAULT NULL COMMENT '设备IP'," +
                        "ADD DEVICE_CID varchar(150) DEFAULT NULL COMMENT '设备卡槽号'," +
                        "ADD DEVICE_PORT varchar(150) DEFAULT NULL COMMENT '设备端口号'," +
                        "ADD DEVICE_COM text DEFAULT NULL COMMENT '设备COM'," +
                        "ADD DEVICE_LINE varchar(150) DEFAULT NULL COMMENT '所属产线'," +
                        "ADD DEVICE_NUMBER int(0) DEFAULT NULL ," +
                        "ADD DEVICE_LIFE int(0) DEFAULT NULL;",
                //增加列
                "ALTER TABLE c_mes_device_upkeep ADD parentId int(0) DEFAULT NULL COMMENT '设备id';",
                //创建表
                "DROP TABLE IF EXISTS `c_mes_event_t`;",
                        "CREATE TABLE `c_mes_event_t`  (" +
                        "  `ID` int(0) NOT NULL AUTO_INCREMENT," +
                        "  `DT` datetime(0) NULL DEFAULT NULL COMMENT '事件时间'," +
                        "  `OBJECT_TYPE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '对象类型'," +
                        "  `OBJECT_ID` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '对象id'," +
                        "  `EVENT` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '事件类型'," +
                        "  `PARAMETER1` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数1'," +
                        "  `PARAMETER2` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数2'," +
                        "  `PARAMETER3` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数3'," +
                        "  `OPERATOR` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人'," +
                        "  `EVENT_DIS` varchar(3635) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '事件详情'," +
                        "  `OBJECT_TYPE2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '对象类型2'," +
                        "  `PARAMETER4` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数4'," +
                        "  `PARAMETER5` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数5'," +
                        "  PRIMARY KEY (`ID`) USING BTREE," +
                        "  INDEX `object_type_index`(`OBJECT_TYPE`) USING BTREE," +
                        "  INDEX `EVENT_index`(`EVENT`) USING BTREE," +
                        "  INDEX `DT_index`(`DT`) USING BTREE," +
                        "  INDEX `OBJECT_ID_index`(`OBJECT_ID`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 25193132 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '事件表' ROW_FORMAT = Dynamic;",
                //创建表
                "DROP TABLE IF EXISTS `c_mes_factory_t`;",
                        "CREATE TABLE `c_mes_factory_t`  (" +
                        "  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '工厂id'," +
                        "  `factoryCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工厂编号'," +
                        "  `factoryName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工厂名称'," +
                        "  `describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工厂描述'," +
                        "  `dateTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间'," +
                        "  `alterDt` datetime(0) NULL DEFAULT NULL COMMENT '修改时间'," +
                        "  `companyCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司编号'," +
                        "  PRIMARY KEY (`id`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '工厂表' ROW_FORMAT = Dynamic;",
                        "" +
                        "-- ----------------------------" ,
                        "-- Table structure for c_mes_fault_type_t" +
                        "-- ----------------------------" ,
                        "DROP TABLE IF EXISTS `c_mes_fault_type_t`;",
                        "CREATE TABLE `c_mes_fault_type_t`  (" +
                        "  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id'," +
                        "  `TYPE_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '故障名称'," +
                        "  `DT` datetime(0) NULL DEFAULT NULL COMMENT '故障类型'," +
                        "  `NOTE` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '备注'," +
                        "  PRIMARY KEY (`ID`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;",
                //增加列
                "ALTER TABLE c_mes_fpc_laser_t ADD WELDING_SPEED varchar(255) DEFAULT NULL COMMENT '焊接速度';",
                //创建表
                "DROP TABLE IF EXISTS `c_mes_hour`;",
                        "CREATE TABLE `c_mes_hour`  (" +
                        "  `hour_number` int(0) NOT NULL" +
                        ") ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;",
                //增加列
                "ALTER TABLE c_mes_jl_material_t ADD VIEW_MODE varchar(255) DEFAULT NULL;",
                //增加列
                "ALTER TABLE c_mes_line_t " +
                        "ADD COLUMN YIELD_NUMBER int(0) DEFAULT NULL COMMENT '产量'," +
                        " ADD COLUMN REGION int(0) DEFAULT NULL COMMENT '区域'," +
                        " ADD COLUMN PRODUCT_MARK varchar(255) DEFAULT NULL COMMENT '产品标记'," +
                        " ADD COLUMN COUNT_TYPE int(0) DEFAULT NULL COMMENT '计数方式（1：计件，2：计件带类型条码，3：计件带产品条码）'," +
                        " ADD COLUMN PAIBAN_STATUS int(0) DEFAULT NULL," +
                        " ADD COLUMN ANDENG_STATUS int(0) DEFAULT NULL;",
                //创建表
                "-- Table structure for c_mes_loss_reason_t" +
                        "-- ----------------------------" ,
                        "DROP TABLE IF EXISTS `c_mes_loss_reason_t`;",
                        "CREATE TABLE `c_mes_loss_reason_t`  (" +
                        "  `ID` int(0) NOT NULL AUTO_INCREMENT," +
                        "  `NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL," +
                        "  `LOSS_ID` int(0) NULL DEFAULT NULL," +
                        "  `NOTE` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL," +
                        "  `REASON_NO` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL," +
                        "  PRIMARY KEY (`ID`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;",
                        "" +
                        "-- ----------------------------" ,
                        "-- Table structure for c_mes_loss_type_t" +
                        "-- ----------------------------" ,
                        "DROP TABLE IF EXISTS `c_mes_loss_type_t`;",
                        "CREATE TABLE `c_mes_loss_type_t`  (" +
                        "  `ID` int(0) NOT NULL AUTO_INCREMENT," +
                        "  `LOSS_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '损失名称'," +
                        "  `DT` datetime(0) NULL DEFAULT NULL COMMENT '时间'," +
                        "  `NOTE` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '备注'," +
                        "  PRIMARY KEY (`ID`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;",

                //创建表
                "-- Table structure for c_mes_material_event_t" +
                        "-- ----------------------------" +
                        "DROP TABLE IF EXISTS `c_mes_material_instance_t`;",
                        "CREATE TABLE `c_mes_material_instance_t`  (" +
                        "  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '实例id'," +
                        "  `MATERIAL_CODE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料编码'," +
                        "  `MATERIAL_BATCH` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料批次'," +
                        "  `MATERIAL_SN` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料SN'," +
                        "  `MATERIAL_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '实例名称'," +
                        "  `MATERIAL_TYPE` int(0) NULL DEFAULT NULL COMMENT '物料类型'," +
                        "  `INSTANCE_VALIDITY` datetime(0) NULL DEFAULT NULL COMMENT '物料有效期'," +
                        "  `INSTANCE_DESCRIPTION` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '实例描述'," +
                        "  `EVENT_ID` int(0) NULL DEFAULT NULL COMMENT '事件id'," +
                        "  `WEAR_STATE` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '消耗状态（0未消耗，1已消耗）'," +
                        "  `MATERIAL_NUMBER` int(0) NULL DEFAULT NULL COMMENT '批次数量'," +
                        "  `DT` datetime(0) NOT NULL COMMENT '创建时间'," +
                        "  `ALTER_DT` datetime(0) NOT NULL COMMENT '修改时间'," +
                        "  `DELETED` int(0) NULL DEFAULT 0 COMMENT '逻辑删除'," +
                        "  `WORKORDER_ID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工单编号'," +
                        "  `NUMBER_REMAINING` int(0) NULL DEFAULT NULL COMMENT '剩余数量'," +
                        "  PRIMARY KEY (`ID`) USING BTREE," +
                        "  INDEX `MATERIAL_NAME_INDEX`(`MATERIAL_NAME`) USING BTREE," +
                        "  INDEX `WEAR_STATE_INDEX`(`WEAR_STATE`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 1115966 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '物料实例表' ROW_FORMAT = Dynamic;",
                //修改列
                "ALTER TABLE c_mes_material_list_detail_t MODIFY MATERILA_LIST_ID varchar(255) COMMENT 'BOM编号';",
                //增加列
                "ALTER TABLE c_mes_material_list_t ADD PRODUCT_TYPE varchar(255) DEFAULT NULL COMMENT '产品型号';",

                //创建表
                "-- Table structure for c_mes_orderrecord_t" +
                        "-- ----------------------------" ,
                        "DROP TABLE IF EXISTS `c_mes_orderrecord_t`;",
                        "CREATE TABLE `c_mes_orderrecord_t`  (" +
                        "  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键'," +
                        "  `DT` datetime(0) NULL DEFAULT NULL COMMENT '创建时间'," +
                        "  `ALTER_DT` datetime(0) NULL DEFAULT NULL COMMENT '修改时间'," +
                        "  `ORDER_ID` int(0) NULL DEFAULT NULL COMMENT '订单id'," +
                        "  `PRODUCT_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品名称'," +
                        "  `DEMAND_NUM` int(0) NULL DEFAULT NULL COMMENT '需求数量'," +
                        "  `MFG_PARAMS` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '制造参数'," +
                        "  `ROUTING_ID` int(0) NULL DEFAULT NULL COMMENT '工艺id'," +
                        "  `TOTAL_RECIPE_ID` int(0) NULL DEFAULT NULL COMMENT '配方id'," +
                        "  `PRODUCT_MODEL` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品型号'," +
                        "  PRIMARY KEY (`ID`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单记录' ROW_FORMAT = Dynamic;",
                //创建表
                "DROP TABLE IF EXISTS `c_mes_plan_type_t`;",
                        "CREATE TABLE `c_mes_plan_type_t`  (" +
                        "  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id'," +
                        "  `typeName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '计划类型名称'," +
                        "  `dis` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述'," +
                        "  PRIMARY KEY (`id`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;",
                        "" +
                        "-- ----------------------------" ,
                        "-- Table structure for c_mes_plant_t" +
                        "-- ----------------------------" ,
                        "DROP TABLE IF EXISTS `c_mes_plant_t`;",
                        "CREATE TABLE `c_mes_plant_t`  (" +
                        "  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '车间id'," +
                        "  `plantCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车间编号'," +
                        "  `plantName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车间名称'," +
                        "  `describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车间描述'," +
                        "  `companyCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属公司'," +
                        "  `factoryCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属工厂'," +
                        "  `areaCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属区域'," +
                        "  `dateTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间'," +
                        "  `alterDt` datetime(0) NULL DEFAULT NULL COMMENT '修改时间'," +
                        "  PRIMARY KEY (`id`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '车间表' ROW_FORMAT = Dynamic;",

                //修改列
                "ALTER TABLE c_mes_production_t MODIFY PRODUCTION_VR varchar(255) DEFAULT NULL;",
                        "ALTER TABLE c_mes_production_t MODIFY PRODUCTION_DISCRIPTION varchar(255) DEFAULT NULL;",
                //增加列
                "ALTER TABLE c_mes_production_t ADD PRODUCTION_SN varchar(255) DEFAULT NULL COMMENT '产品条码';",
                        "ALTER TABLE c_mes_production_t ADD MATERIAL_CODE varchar(255) DEFAULT NULL COMMENT '物料编码';",
                        "ALTER TABLE c_mes_production_t ADD code_digit varchar(255) DEFAULT NULL COMMENT '条码位数';",
                //增加列
                "ALTER TABLE c_mes_production_way_t ADD ROUTING_ID int(0) DEFAULT NULL COMMENT '工艺表id';",
                //创建表
                "DROP TABLE IF EXISTS `c_mes_purchase_order_details_t`;",
                        "CREATE TABLE `c_mes_purchase_order_details_t`  (" +
                        "  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键'," +
                        "  `DT` datetime(0) NULL DEFAULT NULL COMMENT '创建时间'," +
                        "  `PURCHASE_ORDER_ID` int(0) NULL DEFAULT NULL COMMENT '采购单id'," +
                        "  `MATERIAL_NO` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料编号'," +
                        "  `MATERIAL_MODEL` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '型号'," +
                        "  `MATERIAL_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称'," +
                        "  `MATERIAL_NUM` int(0) NULL DEFAULT NULL COMMENT '数量'," +
                        "  `REMARKS` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注'," +
                        "  PRIMARY KEY (`ID`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;",
                        "" +
                        "-- ----------------------------" ,
                        "-- Table structure for c_mes_purchase_order_t" +
                        "-- ----------------------------" ,
                        "DROP TABLE IF EXISTS `c_mes_purchase_order_t`;",
                        "CREATE TABLE `c_mes_purchase_order_t`  (" +
                        "  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键'," +
                        "  `DT` datetime(0) NULL DEFAULT NULL COMMENT '创建时间'," +
                        "  `PURCHASE_NO` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '采购单号'," +
                        "  `PURCHASER` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '采购人'," +
                        "  `SUPPLIER` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '供应商'," +
                        "  `ORDER_TIME` datetime(0) NULL DEFAULT NULL COMMENT '下单时间'," +
                        "  `ARRIVAL_TIME` datetime(0) NULL DEFAULT NULL COMMENT '到货时间'," +
                        "  `STATUS` int(0) NOT NULL DEFAULT 1 COMMENT '状态：1 已创建、2 已审批、3 审批拒绝、4 已采购、5 已收货'," +
                        "  PRIMARY KEY (`ID`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '采购单表' ROW_FORMAT = Dynamic;",
                //修改列
                "ALTER TABLE c_mes_recipe_datil_t MODIFY NUMBERS varchar(40) DEFAULT NULL;",
                        "ALTER TABLE c_mes_recipe_datil_t MODIFY MATERIAL_ID varchar(11) DEFAULT NULL;",
                //增加列
                "ALTER TABLE c_mes_recipe_t ADD TOTAL_ID int(0) DEFAULT NULL COMMENT '总配方id';",
                        "ALTER TABLE c_mes_recipe_t ADD STATION_ID int(0) DEFAULT NULL COMMENT '工位id';",
                //修改列
                "ALTER TABLE c_mes_rework_recipe_t MODIFY RECIPEJSON text;",

                //创建表
                "DROP TABLE IF EXISTS `c_mes_salesorder_t`;",
                        "CREATE TABLE `c_mes_salesorder_t`  (" +
                        "  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键'," +
                        "  `DT` datetime(0) NULL DEFAULT NULL COMMENT '创建时间'," +
                        "  `ALTER_DT` datetime(0) NULL DEFAULT NULL COMMENT '修改时间'," +
                        "  `CUSTOMER_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户名称'," +
                        "  `ORDER_TIME` datetime(0) NULL DEFAULT NULL COMMENT '下单时间'," +
                        "  `DELIVERY_PLAN_TIME` datetime(0) NULL DEFAULT NULL COMMENT '计划交货时间'," +
                        "  `DELIVERY_ACTUAL_TIME` datetime(0) NULL DEFAULT NULL COMMENT '实际交货时间'," +
                        "  `DELIVERY_INFORMATION` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交货信息'," +
                        "  `STATUS` int(0) NULL DEFAULT NULL COMMENT '当前状态（1：已创建 2：已审批 3：审批不过 4：已排产 5：已生产 6：已发货 7：已关闭）'," +
                        "  `CODE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单编号'," +
                        "  PRIMARY KEY (`ID`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 44 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '客户订单表' ROW_FORMAT = Dynamic;",
                        "" +
                        "-- ----------------------------" ,
                        "-- Table structure for c_mes_scheduling_t" +
                        "-- ----------------------------" ,
                        "DROP TABLE IF EXISTS `c_mes_scheduling_t`;",
                        "CREATE TABLE `c_mes_scheduling_t`  (" +
                        "  `ID` int(0) NOT NULL AUTO_INCREMENT," +
                        "  `SHIFT_ID` int(0) NULL DEFAULT NULL," +
                        "  `TEAM_ID` int(0) NULL DEFAULT NULL," +
                        "  `DT` date NULL DEFAULT NULL COMMENT '日期'," +
                        "  `LINE_ID` int(0) NULL DEFAULT NULL," +
                        "  `PLAN_NUMBER` int(0) NULL DEFAULT NULL COMMENT '计划产量'," +
                        "  `ACTUAl_NUMBER` int(0) NULL DEFAULT NULL COMMENT '实际产量'," +
                        "  PRIMARY KEY (`ID`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 336 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;",
                //创建表
                "DROP TABLE IF EXISTS `c_mes_routing_t`;",
                        "CREATE TABLE `c_mes_routing_t`  (" +
                        "  `ID` int(0) NOT NULL AUTO_INCREMENT," +
                        "  `PRODUCTION_ID` int(0) NULL DEFAULT NULL," +
                        "  `LINE_ID` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL," +
                        "  `ROUTE` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL," +
                        "  `NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '工艺路线名称'," +
                        "  `VIEW_STATE` int(0) NULL DEFAULT NULL," +
                        "  `default_route` int(0) NULL DEFAULT NULL," +
                        "  `json` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL," +
                        "  `lineList` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '线列表'," +
                        "  `nodeList` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '节点列表'," +
                        "  `DT` datetime(0) NULL DEFAULT NULL COMMENT '创建时间'," +
                        "  `ALTER_DT` datetime(0) NULL DEFAULT NULL COMMENT '修改时间'," +
                        "  PRIMARY KEY (`ID`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 52 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;",
                //增加列
                "ALTER TABLE c_mes_shifts_team_t ADD PLAN_TIME int(0) DEFAULT NULL COMMENT '计划生产时间';",
                        "ALTER TABLE c_mes_shifts_team_t ADD JUMP_TIME int(0) DEFAULT NULL;",
                //创建表
                "DROP TABLE IF EXISTS `c_mes_system`;",
                        "CREATE TABLE `c_mes_system`  (" +
                        "  `id` int(0) NOT NULL AUTO_INCREMENT," +
                        "  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数名'," +
                        "  `parameter` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL," +
                        "  `dis` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '说明'," +
                        "  PRIMARY KEY (`id`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;",
                //创建表
                "DROP TABLE IF EXISTS `c_mes_team_t`;",
                        "CREATE TABLE `c_mes_team_t`  (" +
                        "  `ID` int(0) NOT NULL AUTO_INCREMENT," +
                        "  `NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL," +
                        "  `DIS` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL," +
                        "  `SHIFT_ID` int(0) NULL DEFAULT NULL," +
                        "  PRIMARY KEY (`ID`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;",
                        "" +
                        "-- ----------------------------" ,
                        "-- Table structure for c_mes_title_name_t" +
                        "-- ----------------------------" ,
                        "DROP TABLE IF EXISTS `c_mes_title_name_t`;",
                        "CREATE TABLE `c_mes_title_name_t`  (" +
                        "  `id` int(0) NOT NULL," +
                        "  `dt` datetime(0) NULL DEFAULT NULL COMMENT '时间'," +
                        "  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称'," +
                        "  PRIMARY KEY (`id`) USING BTREE" +
                        ") ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '琦航登录页标题' ROW_FORMAT = Dynamic;",
                        "" +
                        "-- ----------------------------" ,
                        "-- Table structure for c_mes_tool_manage_t" +
                        "-- ----------------------------" ,
                        "DROP TABLE IF EXISTS `c_mes_tool_manage_t`;",
                        "CREATE TABLE `c_mes_tool_manage_t`  (" +
                        "  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '自增'," +
                        "  `DT` datetime(0) NULL DEFAULT NULL COMMENT '时间'," +
                        "  `TOOL_NO` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编号'," +
                        "  `TOOL_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称'," +
                        "  `TOOL_DIS` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述'," +
                        "  `ESTIMATE_LIFE` int(0) NULL DEFAULT NULL COMMENT '预计寿命'," +
                        "  `USEFUL_LIFE` int(0) NULL DEFAULT NULL COMMENT '已用寿命'," +
                        "  `SURPLUS_LIFE` int(0) NULL DEFAULT NULL COMMENT '剩余寿命'," +
                        "  `MAINTAIN_CYCLE` int(0) NULL DEFAULT NULL COMMENT '维护周期'," +
                        "  `LAST_MAINTAIN` date NULL DEFAULT NULL COMMENT '上次维护时间'," +
                        "  `NEXT_MAINTAIN` date NULL DEFAULT NULL COMMENT '下次维护时间'," +
                        "  `SURPLUS_MAINTAIN` int(0) NULL DEFAULT NULL COMMENT '剩余维护天数'," +
                        "  `FIRST_USE` date NULL DEFAULT NULL COMMENT '初次使用时间'," +
                        "  `EVERYTIMES` int(0) NULL DEFAULT NULL COMMENT '每一次消耗寿命'," +
                        "  `VIEW_STATE` int(0) NULL DEFAULT NULL COMMENT '状态：0正常 1删除'," +
                        "  `LINE_ID` int(0) NULL DEFAULT NULL," +
                        "  `STATION_ID` int(0) NULL DEFAULT NULL," +
                        "  PRIMARY KEY (`ID`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 62 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '工具管理表' ROW_FORMAT = Dynamic;",
                        "" +
                        "-- ----------------------------" ,
                        "-- Table structure for c_mes_total_recipe_t" +
                        "-- ----------------------------" ,
                        "DROP TABLE IF EXISTS `c_mes_total_recipe_t`;",
                        "CREATE TABLE `c_mes_total_recipe_t`  (" +
                        "  `ID` int(0) NOT NULL AUTO_INCREMENT," +
                        "  `TOTAL_RECIPE_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '总配方名称'," +
                        "  `TOTAL_RECIPE_DESCRIBE` varchar(11) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '配方描述'," +
                        "  `LINE_ID` int(0) NULL DEFAULT NULL COMMENT '产线ID'," +
                        "  `PRODUCTION_ID` int(0) NULL DEFAULT NULL COMMENT '产品ID'," +
                        "  `STATUS` int(0) NULL DEFAULT NULL COMMENT '是否默认'," +
                        "  PRIMARY KEY (`ID`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 62 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;",
                //增加列
                "ALTER TABLE c_mes_user_t ADD LOGINSTATUS varchar(100) DEFAULT NULL COMMENT '登录标识符';",
                        "ALTER TABLE c_mes_user_t ADD token varchar(255) DEFAULT NULL;",

                //创建表
                "DROP TABLE IF EXISTS `c_mes_workorder_t`;",
                        "CREATE TABLE `c_mes_workorder_t`  (" +
                        "  `ID` int(0) NOT NULL AUTO_INCREMENT," +
                        "  `PLAN_ID` int(0) NULL DEFAULT NULL COMMENT '计划id'," +
                        "  `WORK_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '工单名称'," +
                        "  `NUMBER` int(0) NULL DEFAULT NULL COMMENT '工单数量'," +
                        "  `PRODUCT_MARK` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '产品标记'," +
                        "  `SURPLUS_NUMBER` int(0) NULL DEFAULT NULL COMMENT '之前的计划数量'," +
                        "  `COMPLETE_NUMBER` int(0) NULL DEFAULT NULL COMMENT '完成数量'," +
                        "  `SCHE_ID` int(0) NULL DEFAULT NULL COMMENT '排班id'," +
                        "  PRIMARY KEY (`ID`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 4217 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;",
                //创建表
                "DROP TABLE IF EXISTS `c_qh_authority_interface`;",
                        "CREATE TABLE `c_qh_authority_interface`  (" +
                        "  `id` int(0) NOT NULL AUTO_INCREMENT," +
                        "  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路径'," +
                        "  `operation_type_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作类型代码'," +
                        "  `menu_id` int(0) NULL DEFAULT NULL COMMENT '菜单id'," +
                        "  `operation_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作类型'," +
                        "  `describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述'," +
                        "  PRIMARY KEY (`id`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 58 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限接口\t\t\t\\r\' ROW_FORMAT = Dynamic;",
                        "" +
                        "-- ----------------------------" ,
                        "-- Table structure for c_qh_equipment_information" +
                        "-- ----------------------------" ,
                        "DROP TABLE IF EXISTS `c_qh_equipment_information`;",
                        "CREATE TABLE `c_qh_equipment_information`  (" +
                        "  `id` int(0) NOT NULL AUTO_INCREMENT," +
                        "  `number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备编号'," +
                        "  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备名称'," +
                        "  `model` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备型号'," +
                        "  `brand` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备品牌'," +
                        "  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备类型'," +
                        "  `maintenancePeriod` int(0) NULL DEFAULT NULL COMMENT '保养周期'," +
                        "  `workState` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工作状态 1:正常 2:异常'," +
                        "  `functionState` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '运行状态 1:运行中 2:休息'," +
                        "  `plcAdd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'PLC地址'," +
                        "  `ipAdd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'IP地址'," +
                        "  `port` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '端口号'," +
                        "  `lineId` int(0) NULL DEFAULT NULL COMMENT '产线id'," +
                        "  `stationIds` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所有工位id'," +
                        "  `describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备描述'," +
                        "  `lastMaintenanceDate` datetime(0) NULL DEFAULT NULL COMMENT '上次保养日期'," +
                        "  `nextMaintenanceDate` date NULL DEFAULT NULL COMMENT '下次保养日期'," +
                        "  `lastRepairDate` datetime(0) NULL DEFAULT NULL COMMENT '上次维修日期'," +
                        "  PRIMARY KEY (`id`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;",
                        "" +
                        "-- ----------------------------" ,
                        "-- Table structure for c_qh_equipment_information_custom_columns" +
                        "-- ----------------------------" ,
                        "DROP TABLE IF EXISTS `c_qh_equipment_information_custom_columns`;",
                        "CREATE TABLE `c_qh_equipment_information_custom_columns`  (" +
                        "  `id` int(0) NOT NULL AUTO_INCREMENT," +
                        "  `parentId` int(0) NULL DEFAULT NULL COMMENT '父级 id'," +
                        "  `columnName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '列名'," +
                        "  `value` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '值'," +
                        "  `explain` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '说明'," +
                        "  PRIMARY KEY (`id`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;",
                        "" +
                        "-- ----------------------------" ,
                        "-- Table structure for c_qh_equipment_information_event" +
                        "-- ----------------------------" ,
                        "DROP TABLE IF EXISTS `c_qh_equipment_information_event`;",
                        "CREATE TABLE `c_qh_equipment_information_event`  (" +
                        "  `id` int(0) NOT NULL AUTO_INCREMENT," +
                        "  `dt` datetime(0) NULL DEFAULT NULL," +
                        "  `parentId` int(0) NULL DEFAULT NULL," +
                        "  `event` int(0) NULL DEFAULT NULL COMMENT '事件 ： 1.创建、2.编辑、3.正常、4.异常、5.运行中、6.休息、7.点检、8.保养、9.维修'," +
                        "  PRIMARY KEY (`id`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;",
                        "" +
                        "-- ----------------------------" ,
                        "-- Table structure for c_qh_equipment_information_station" +
                        "-- ----------------------------" ,
                        "DROP TABLE IF EXISTS `c_qh_equipment_information_station`;",
                        "CREATE TABLE `c_qh_equipment_information_station`  (" +
                        "  `id` int(0) NOT NULL AUTO_INCREMENT," +
                        "  `stationId` int(0) NULL DEFAULT NULL," +
                        "  `parentId` int(0) NULL DEFAULT NULL," +
                        "  PRIMARY KEY (`id`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 193 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;",
                        "" +
                        "-- ----------------------------" ,
                        "-- Table structure for c_qh_interface" +
                        "-- ----------------------------" ,
                        "DROP TABLE IF EXISTS `c_qh_interface`;",
                        "CREATE TABLE `c_qh_interface`  (" +
                        "  `id` int(0) NOT NULL AUTO_INCREMENT," +
                        "  `authority_interface_id` int(0) NULL DEFAULT NULL COMMENT '权限id'," +
                        "  `InterfaceAddress` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接口地址'," +
                        "  `describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述'," +
                        "  PRIMARY KEY (`id`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;",
                        "" +
                        "-- ----------------------------" ,
                        "-- Table structure for c_qh_menu" +
                        "-- ----------------------------" ,
                        "DROP TABLE IF EXISTS `c_qh_menu`;",
                        "CREATE TABLE `c_qh_menu`  (" +
                        "  `id` int(0) NOT NULL AUTO_INCREMENT," +
                        "  `menu_grade` int(0) NULL DEFAULT NULL COMMENT '菜单等级'," +
                        "  `superior_menu_id` int(0) NULL DEFAULT NULL COMMENT '上级菜单id'," +
                        "  `menu_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称'," +
                        "  `if_enable` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否启用(布尔类型)'," +
                        "  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路径'," +
                        "  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL," +
                        "  `order` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '顺序'," +
                        "  PRIMARY KEY (`id`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 190 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;",
                        "" +
                        "-- ----------------------------" ,
                        "-- Table structure for c_qh_role" +
                        "-- ----------------------------" ,
                        "DROP TABLE IF EXISTS `c_qh_role`;",
                        "CREATE TABLE `c_qh_role`  (" +
                        "  `id` int(0) NOT NULL AUTO_INCREMENT," +
                        "  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称'," +
                        "  `describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述'," +
                        "  PRIMARY KEY (`id`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色' ROW_FORMAT = Dynamic;",
                        "" +
                        "-- ----------------------------" ,
                        "-- Table structure for c_qh_role_menu" +
                        "-- ----------------------------" ,
                        "DROP TABLE IF EXISTS `c_qh_role_menu`;",
                        "CREATE TABLE `c_qh_role_menu`  (" +
                        "  `id` int(0) NOT NULL AUTO_INCREMENT," +
                        "  `role_id` int(0) NULL DEFAULT NULL COMMENT '角色id'," +
                        "  `menu_id` int(0) NULL DEFAULT NULL COMMENT '菜单id'," +
                        "  `operation_type_id` int(0) NULL DEFAULT NULL COMMENT '操作类型'," +
                        "  PRIMARY KEY (`id`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 1108 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色_菜单' ROW_FORMAT = Dynamic;",
                        "" +
                        "-- ----------------------------" ,
                        "-- Table structure for c_wms_all_material_barcode" +
                        "-- ----------------------------" ,
                        "DROP TABLE IF EXISTS `c_wms_all_material_barcode`;",
                        "CREATE TABLE `c_wms_all_material_barcode`  (" +
                        "  `id` int(0) NOT NULL AUTO_INCREMENT," +
                        "  `materialBarcodeRule_Id` int(0) NULL DEFAULT NULL COMMENT '物料条码规则Id'," +
                        "  `bar_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '条码'," +
                        "  `generation_time` datetime(0) NULL DEFAULT NULL COMMENT '生成时间'," +
                        "  `printing_time` datetime(0) NULL DEFAULT NULL COMMENT '打印时间'," +
                        "  `state` int(0) NULL DEFAULT NULL COMMENT '状态：0:使用，1：丢弃'," +
                        "  PRIMARY KEY (`id`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 2097620 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '所有物料条码' ROW_FORMAT = Dynamic;",
                        "" +
                        "-- ----------------------------" ,
                        "-- Table structure for c_wms_approval_details_t" +
                        "-- ----------------------------" ,
                        "DROP TABLE IF EXISTS `c_wms_approval_details_t`;",
                        "CREATE TABLE `c_wms_approval_details_t`  (" +
                        "  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键：编号'," +
                        "  `LIST_NO` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单号'," +
                        "  `APPROVAL_RESULT` int(0) NULL DEFAULT NULL COMMENT '0.未审批 1.通过 2.不通过'," +
                        "  `USER_ID` int(0) NULL DEFAULT NULL COMMENT '审批人id'," +
                        "  `REASON` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '原因'," +
                        "  `APPROVAL_ID` int(0) NULL DEFAULT NULL COMMENT '审批主表ID'," +
                        "  `DT` datetime(0) NULL DEFAULT NULL COMMENT '审批时间'," +
                        "  `PRIORITY_LEVEL` int(0) NULL DEFAULT NULL COMMENT '优先级'," +
                        "  `YN_APPROVED` int(0) NULL DEFAULT NULL COMMENT '是否可审批1：可以、0：不可以'," +
                        "  PRIMARY KEY (`ID`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 3120 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '审批详情' ROW_FORMAT = Dynamic;",
                        "" +
                        "-- ----------------------------" ,
                        "-- Table structure for c_wms_approval_t" +
                        "-- ----------------------------" ,
                        "DROP TABLE IF EXISTS `c_wms_approval_t`;",
                        "CREATE TABLE `c_wms_approval_t`  (" +
                        "  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键：编号'," +
                        "  `LIST_NO` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '外键：单号'," +
                        "  `DT` datetime(0) NULL DEFAULT NULL COMMENT '生成记录的时间'," +
                        "  `PROCESS_ID` int(0) NULL DEFAULT NULL COMMENT '外键：流程表id'," +
                        "  `USER_ID` int(0) NULL DEFAULT NULL COMMENT '申请人id'," +
                        "  `STATE` int(0) NULL DEFAULT NULL COMMENT '状态：0.未审批、1.审批中、2.审批成功、3.驳回、4撤销'," +
                        "  `NOTE` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注'," +
                        "  `process_type_id` int(0) NULL DEFAULT NULL COMMENT '审批类型id'," +
                        "  PRIMARY KEY (`ID`) USING BTREE," +
                        "  INDEX `index_approval`(`LIST_NO`, `STATE`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 7293 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '审批表' ROW_FORMAT = Dynamic;",
                //创建表
                "DROP TABLE IF EXISTS `c_wms_area_t`;",
                        "CREATE TABLE `c_wms_area_t`  (" +
                        "  `ID` int(0) NOT NULL AUTO_INCREMENT," +
                        "  `AREA_NO` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区域编号'," +
                        "  `AREA_NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区域名称'," +
                        "  `AREA_TYPE` int(0) NULL DEFAULT NULL COMMENT '区域类型，0：立库 1：平库 2：OTHER'," +
                        "  `AREA_LENGTH` int(0) NULL DEFAULT NULL COMMENT '区域长度，单位M'," +
                        "  `AREA_WIDTH` int(0) NULL DEFAULT NULL COMMENT '区域宽度，单位M'," +
                        "  `AREA_HIGHT` int(0) NULL DEFAULT NULL COMMENT '区域高度，单位M'," +
                        "  `AREA_LIMIT` int(0) NULL DEFAULT NULL COMMENT '区域上限，单位M'," +
                        "  `AREA_LOADBEARING` int(0) NULL DEFAULT NULL COMMENT '区域承重,单位KG'," +
                        "  `MODIFY_TIME` datetime(0) NULL DEFAULT NULL COMMENT '修改时间'," +
                        "  `WAREHOUSE_ID` int(0) NULL DEFAULT NULL COMMENT '所属仓库,仓库ID'," +
                        "  `VIEW_MODE` int(0) NULL DEFAULT 1 COMMENT '0：不可查询 1：可查询'," +
                        "  PRIMARY KEY (`ID`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '仓库区域表' ROW_FORMAT = Dynamic;",
                        "" +
                        "-- ----------------------------" ,
                        "-- Table structure for c_wms_department_t" +
                        "-- ----------------------------" ,
                        "DROP TABLE IF EXISTS `c_wms_department_t`;",
                        "CREATE TABLE `c_wms_department_t`  (" +
                        "  `ID` int(0) NOT NULL AUTO_INCREMENT," +
                        "  `DM_NUMBER` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门编号'," +
                        "  `DM_NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门名称'," +
                        "  `NOTE` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注'," +
                        "  `MODIFY_TIME` datetime(0) NULL DEFAULT NULL COMMENT '修改时间'," +
                        "  `VIEW_MODE` int(0) NULL DEFAULT 1 COMMENT '0：不可查询 1：可查询'," +
                        "  PRIMARY KEY (`ID`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 87 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门基础表' ROW_FORMAT = Dynamic;",
                        "" +
                        "-- ----------------------------" ,
                        "-- Table structure for c_wms_inventory_detail_t" +
                        "-- ----------------------------" ,
                        "DROP TABLE IF EXISTS `c_wms_inventory_detail_t`;",
                        "CREATE TABLE `c_wms_inventory_detail_t`  (" +
                        "  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键'," +
                        "  `INVENTORY_ID` int(0) NULL DEFAULT NULL COMMENT '盘点id'," +
                        "  `MATERIAL_ID` int(0) NULL DEFAULT NULL COMMENT '物料ID'," +
                        "  `INVENTORY_NO` int(0) NULL DEFAULT NULL COMMENT '物料盘点数量'," +
                        "  `STOCK_NO` int(0) NULL DEFAULT NULL COMMENT '物料库存数量'," +
                        "  `DIFFERENCE_NO` int(0) NULL DEFAULT NULL COMMENT '物料差异数量'," +
                        "  `LOCATION_ID` int(0) NULL DEFAULT NULL COMMENT '库位'," +
                        "  `PROJECT_ID` int(0) NULL DEFAULT NULL COMMENT '项目'," +
                        "  `TRAY_CODE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '托盘码'," +
                        "  PRIMARY KEY (`ID`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '仓库盘点明细表' ROW_FORMAT = Dynamic;",
                //创建表
                "DROP TABLE IF EXISTS `c_wms_inventory_t`;",
                        "CREATE TABLE `c_wms_inventory_t`  (" +
                        "  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键 '," +
                        "  `LIST_NO` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '盘点单据号'," +
                        "  `DT` datetime(0) NULL DEFAULT NULL COMMENT '日期'," +
                        "  `USER_ID` int(0) NULL DEFAULT NULL COMMENT '操作人员'," +
                        "  `STATE` int(0) NULL DEFAULT NULL COMMENT '盘点状态1.生效、0.未生效'," +
                        "  `DIS` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述'," +
                        "  PRIMARY KEY (`ID`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '库存盘点历史记录表' ROW_FORMAT = Dynamic;",
                        "" +
                        "-- ----------------------------" ,
                        "-- Table structure for c_wms_location_t" +
                        "-- ----------------------------" ,
                        "DROP TABLE IF EXISTS `c_wms_location_t`;",
                        "CREATE TABLE `c_wms_location_t`  (" +
                        "  `ID` int(0) NOT NULL AUTO_INCREMENT," +
                        "  `LOCATION_NO` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '库位编号'," +
                        "  `LOCATION_NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '库位名称'," +
                        "  `LOCATION_LENGTH` int(0) NULL DEFAULT NULL COMMENT '库位长度，单位M'," +
                        "  `LOCATION_WIDTH` int(0) NULL DEFAULT NULL COMMENT '库位宽度，单位M'," +
                        "  `LOCATION_HIGHT` int(0) NULL DEFAULT NULL COMMENT '库位高度，单位M'," +
                        "  `LOCATION_VOLUME` int(0) NULL DEFAULT NULL COMMENT '库位体积，单位M'," +
                        "  `LOCATION_TYPE` int(0) NULL DEFAULT NULL COMMENT '库位类型，0：立库 1：平库 2：OTHER'," +
                        "  `LOCATION_MARK` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '库位标识'," +
                        "  `LOCATION_TYPE1` int(0) NULL DEFAULT NULL COMMENT '库位种类，备用'," +
                        "  `LOCATION_CAPACITY` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '库位容量'," +
                        "  `LOCATION_WEIGHT` int(0) NULL DEFAULT NULL COMMENT '库位载重量，单位KG'," +
                        "  `LOCATION_STATUS` int(0) NULL DEFAULT NULL COMMENT '库位状态，0：正常 1：满库 2：维修 3：禁用 4：报废 5:未满可使用 6.空托盘放置'," +
                        "  `LOCATION_X` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '库位横坐标，考虑小数点'," +
                        "  `LOCATION_Y` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '库位纵坐标，考虑小数点'," +
                        "  `LOCATION_Z` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '库位Z坐标，考虑小数点'," +
                        "  `LOCATION_VR` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '库位校验规则'," +
                        "  `RESERVOIR_AREA_ID` int(0) NULL DEFAULT NULL COMMENT '库区ID，关联库区表'," +
                        "  `MODIFY_TIME` datetime(0) NULL DEFAULT NULL COMMENT '修改时间'," +
                        "  `TRAY_TYPE` int(0) NULL DEFAULT NULL COMMENT '可容纳托盘类型 1:矮、2:高'," +
                        "  `TRAY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '托盘码'," +
                        "  `VIEW_MODE` int(0) NULL DEFAULT NULL," +
                        "  PRIMARY KEY (`ID`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 569 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '仓库库位表' ROW_FORMAT = Dynamic;",
                        "" +
                        "-- ----------------------------" ,
                        "-- Table structure for c_wms_material_barcode_rule_t" +
                        "-- ----------------------------" ,
                        "DROP TABLE IF EXISTS `c_wms_material_barcode_rule_t`;",
                        "CREATE TABLE `c_wms_material_barcode_rule_t`  (" +
                        "  `id` int(0) NOT NULL AUTO_INCREMENT," +
                        "  `rule` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '规则'," +
                        "  `condition` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '条件'," +
                        "  `modify_dt` datetime(0) NULL DEFAULT NULL COMMENT '修改时间'," +
                        "  PRIMARY KEY (`id`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '物料条码规则' ROW_FORMAT = Dynamic;",
                        "" +
                        "-- ----------------------------" ,
                        "-- Table structure for c_wms_material_inventory_t" +
                        "-- ----------------------------" ,
                        "DROP TABLE IF EXISTS `c_wms_material_inventory_t`;",
                        "CREATE TABLE `c_wms_material_inventory_t`  (" +
                        "  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键'," +
                        "  `MATERIAL_NO` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料编号'," +
                        "  `MATERIAL_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料名称'," +
                        "  `MATERIAL_NUMBER` int(0) NULL DEFAULT NULL COMMENT '物料数量'," +
                        "  `INVENTORY_TIME` datetime(0) NULL DEFAULT NULL COMMENT '记录日期'," +
                        "  PRIMARY KEY (`ID`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 243 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '物料库存数量记录表' ROW_FORMAT = Dynamic;",
                        "" +
                        "-- ----------------------------" ,
                        "-- Table structure for c_wms_material_number_t" +
                        "-- ----------------------------" ,
                        "DROP TABLE IF EXISTS `c_wms_material_number_t`;",
                        "CREATE TABLE `c_wms_material_number_t`  (" +
                        "  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键'," +
                        "  `MATERIAL_NO` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料编号'," +
                        "  `MATERIAL_NAME` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料名称'," +
                        "  `MATERIAL_NUMBER` int(0) NULL DEFAULT NULL COMMENT '数量'," +
                        "  `PROJECT_ID` int(0) NULL DEFAULT NULL COMMENT '项目ID'," +
                        "  `MATERIAL_ID` int(0) NULL DEFAULT NULL COMMENT '物料ID'," +
                        "  `WAREHOUSE_ID` int(0) NULL DEFAULT NULL COMMENT '仓库ID'," +
                        "  `AREA_ID` int(0) NULL DEFAULT NULL COMMENT '区域ID'," +
                        "  `LOCATION_ID` int(0) NULL DEFAULT NULL COMMENT '库位表ID'," +
                        "  `RESERVOIR_AREA_ID` int(0) NULL DEFAULT NULL COMMENT '仓库库区ID'," +
                        "  `LMMINENT_RELEASE` int(0) NULL DEFAULT NULL COMMENT '即将出库的数量'," +
                        "  `DT` datetime(0) NULL DEFAULT NULL COMMENT '入库时间'," +
                        "  `FREEZING_NUMBER` int(0) NULL DEFAULT NULL COMMENT '冻结数'," +
                        "  `RESERVED_NUMBER` int(0) NULL DEFAULT NULL COMMENT '预留数'," +
                        "  `TRAY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '托盘号'," +
                        "  `MATERIAL_CODE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料条码'," +
                        "  `bar_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL," +
                        "  PRIMARY KEY (`ID`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 13686 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '物料库存总表' ROW_FORMAT = Dynamic;",
                        "" +
                        "-- ----------------------------" ,
                        "-- Table structure for c_wms_material_rule_attribute_t" +
                        "-- ----------------------------" ,
                        "DROP TABLE IF EXISTS `c_wms_material_rule_attribute_t`;",
                        "CREATE TABLE `c_wms_material_rule_attribute_t`  (" +
                        "  `id` int(0) NOT NULL AUTO_INCREMENT," +
                        "  `attribute_cn` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '中文属性'," +
                        "  `column` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '属性列'," +
                        "  PRIMARY KEY (`id`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '物料条码规则属性表' ROW_FORMAT = Dynamic;",
                        "" +
                        "-- ----------------------------" ,
                        "-- Table structure for c_wms_material_t" +
                        "-- ----------------------------" ,
                        "DROP TABLE IF EXISTS `c_wms_material_t`;",
                        "CREATE TABLE `c_wms_material_t`  (" +
                        "  `ID` int(0) NOT NULL AUTO_INCREMENT," +
                        "  `MODIFY_TIME` datetime(0) NULL DEFAULT NULL COMMENT '修改时间'," +
                        "  `MATERIAL_NO` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料编号'," +
                        "  `MATERIAL_PARTNO` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料图号'," +
                        "  `MATERIAL_NAME` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料名称'," +
                        "  `MATERIAL_TYPE` int(0) NULL DEFAULT NULL COMMENT '物料类别，物料类型表id'," +
                        "  `MATERIAL_UNIT` int(0) NULL DEFAULT NULL COMMENT '物料单位，物料单位表id'," +
                        "  `MATERIAL_LENGTH` decimal(65, 2) NULL DEFAULT NULL COMMENT '物料长度，单位m'," +
                        "  `MATERIAL_WIDTH` decimal(65, 2) NULL DEFAULT NULL COMMENT '物料宽度，单位m'," +
                        "  `MATERIAL_HIGHT` decimal(65, 2) NULL DEFAULT NULL COMMENT '物料高度，单位m'," +
                        "  `MATERIAL_VOLUME` decimal(65, 2) NULL DEFAULT NULL COMMENT '物料体积。单位m3'," +
                        "  `MATERIAL_WEIGHT` decimal(65, 2) NULL DEFAULT NULL COMMENT '物料重量，单位KG'," +
                        "  `MATERIAL_LT` int(0) NULL DEFAULT NULL COMMENT '存放库位类型，0：立库；1：平库；2：other'," +
                        "  `MATERIAL_INCOM_TYPE` int(0) NULL DEFAULT NULL COMMENT '来料方式，0：单个；1：批次'," +
                        "  `MATERIAL_MODEL` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料型号'," +
                        "  `MATERIAL_CODE` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料条码'," +
                        "  `MATERIAL_LOW_LIMITMATERIAL` int(0) NOT NULL COMMENT '物料库存下限'," +
                        "  `MATERIAL_SC` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料防伪码'," +
                        "  `MATERIAL_BATCH` decimal(65, 30) NOT NULL COMMENT '物料批次数量'," +
                        "  `MATERIAL_VR` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料校验规则'," +
                        "  `DAYS_OF_FAILURE` decimal(65, 30) NULL DEFAULT NULL COMMENT '失效天数'," +
                        "  `NOTE` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注'," +
                        "  `MATERIAL_COST` decimal(65, 2) NULL DEFAULT NULL COMMENT '成本'," +
                        "  PRIMARY KEY (`ID`) USING BTREE" +
                        ") ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '物料表' ROW_FORMAT = Dynamic;",
                        "" +
                        "-- ----------------------------" ,
                        "-- Table structure for c_wms_material_type_t" +
                        "-- ----------------------------" ,
                        "DROP TABLE IF EXISTS `c_wms_material_type_t`;",
                        "CREATE TABLE `c_wms_material_type_t`  (" +
                        "  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键'," +
                        "  `MODIFY_TIME` datetime(0) NULL DEFAULT NULL COMMENT '修改时间'," +
                        "  `MT_NO` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类别编号'," +
                        "  `MT_NAME` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类别名称'," +
                        "  PRIMARY KEY (`ID`) USING BTREE" +
                        ") ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '物料类型表' ROW_FORMAT = Dynamic;",
                        "" +
                        "-- ----------------------------" ,
                        "-- Table structure for c_wms_material_unit_t" +
                        "-- ----------------------------" ,
                        "DROP TABLE IF EXISTS `c_wms_material_unit_t`;",
                        "CREATE TABLE `c_wms_material_unit_t`  (" +
                        "  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键'," +
                        "  `MODIFY_TIME` datetime(0) NULL DEFAULT NULL COMMENT '修改时间'," +
                        "  `UNIT_NO` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位编号'," +
                        "  `UNIT_NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位名称'," +
                        "  PRIMARY KEY (`ID`) USING BTREE" +
                        ") ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '物料单位表' ROW_FORMAT = Dynamic;",
                //创建表
                "DROP TABLE IF EXISTS `c_wms_process_approval_detail`;",
                        "CREATE TABLE `c_wms_process_approval_detail`  (" +
                        "  `ID` int(0) NOT NULL AUTO_INCREMENT," +
                        "  `USER_ID` int(0) NULL DEFAULT NULL COMMENT '用户id'," +
                        "  `PRIORITY_LEVEL` int(0) NULL DEFAULT NULL COMMENT '优先级'," +
                        "  `DT` datetime(0) NULL DEFAULT NULL," +
                        "  `PROCESS_ID` int(0) NULL DEFAULT NULL COMMENT '流程审批外键'," +
                        "  `DIS` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL," +
                        "  PRIMARY KEY (`ID`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 95 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '流程审批明细' ROW_FORMAT = Dynamic;",
                        "" +
                        "-- ----------------------------" ,
                        "-- Table structure for c_wms_process_approval_t" +
                        "-- ----------------------------" ,
                        "DROP TABLE IF EXISTS `c_wms_process_approval_t`;",
                        "CREATE TABLE `c_wms_process_approval_t`  (" +
                        "  `ID` int(0) NOT NULL AUTO_INCREMENT," +
                        "  `DEPT_ID` int(0) NULL DEFAULT NULL COMMENT '所属部门'," +
                        "  `ROLE_ID` int(0) NULL DEFAULT NULL COMMENT '所属角色'," +
                        "  `TYPE_ID` int(0) NULL DEFAULT NULL COMMENT '流程类型'," +
                        "  `DT` datetime(0) NULL DEFAULT NULL," +
                        "  `DIS` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL," +
                        "  PRIMARY KEY (`ID`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 48 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '流程审批表' ROW_FORMAT = Dynamic;",
                        "" +
                        "-- ----------------------------" ,
                        "-- Table structure for c_wms_process_type_t" +
                        "-- ----------------------------" ,
                        "DROP TABLE IF EXISTS `c_wms_process_type_t`;",
                        "CREATE TABLE `c_wms_process_type_t`  (" +
                        "  `ID` int(0) NOT NULL AUTO_INCREMENT," +
                        "  `PROCESS_TYPE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程类型'," +
                        "  `DIS` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL," +
                        "  `VIEW_MODE` int(0) NULL DEFAULT 1 COMMENT '0：不可查询 1：可查询'," +
                        "  PRIMARY KEY (`ID`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '流程类型表' ROW_FORMAT = Dynamic;",
                        "" +
                        "-- ----------------------------" ,
                        "-- Table structure for c_wms_project_t" +
                        "-- ----------------------------" ,
                        "DROP TABLE IF EXISTS `c_wms_project_t`;",
                        "CREATE TABLE `c_wms_project_t`  (" +
                        "  `ID` int(0) NOT NULL AUTO_INCREMENT," +
                        "  `MANAGEMENT_NO` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理编号'," +
                        "  `CONTRACT_NO` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '合同编号'," +
                        "  `PROJECT_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目名称'," +
                        "  `PRODUCT_NUMBER` int(0) NULL DEFAULT NULL COMMENT '产品数量'," +
                        "  `START_DATE` date NULL DEFAULT NULL COMMENT '指派时间'," +
                        "  `END_DATE` date NULL DEFAULT NULL COMMENT '交货日期'," +
                        "  `PROJECT_NATURE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目性质(1.销售 2.免费 3.制样)'," +
                        "  `PROJECT_LEADER` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目负责人'," +
                        "  `PROJECT_TYPE` int(0) NULL DEFAULT NULL COMMENT '项目类别'," +
                        "  `INTERNAL_PROJECT_NO` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内部项目号'," +
                        "  `UPDATE_DATE` datetime(0) NULL DEFAULT NULL COMMENT '修改时间'," +
                        "  `DIS` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述'," +
                        "  `VIEW_MODE` int(0) NULL DEFAULT 1 COMMENT '0：不可查询 1：可查询'," +
                        "  PRIMARY KEY (`ID`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 239 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '项目表' ROW_FORMAT = Dynamic;",
                        "" +
                        "-- ----------------------------" ,
                        "-- Table structure for c_wms_project_type_t" +
                        "-- ----------------------------" ,
                        "DROP TABLE IF EXISTS `c_wms_project_type_t`;",
                        "CREATE TABLE `c_wms_project_type_t`  (" +
                        "  `ID` int(0) NOT NULL AUTO_INCREMENT," +
                        "  `PROJECT_TYPE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目类型名称'," +
                        "  `DIS` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注释'," +
                        "  `VIEW_MODE` int(0) NULL DEFAULT 1 COMMENT '0：不可查询 1：可查询'," +
                        "  PRIMARY KEY (`ID`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;",
                        "" +
                        "-- ----------------------------" ,
                        "-- Table structure for c_wms_reservoir_area_t" +
                        "-- ----------------------------" ,
                        "DROP TABLE IF EXISTS `c_wms_reservoir_area_t`;",
                        "CREATE TABLE `c_wms_reservoir_area_t`  (" +
                        "  `ID` int(0) NOT NULL AUTO_INCREMENT," +
                        "  `RA_NO` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '库区编号'," +
                        "  `RA_NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '库区名称'," +
                        "  `RA_TYPE` int(0) NULL DEFAULT NULL COMMENT '库区类型，0：立库 1：平库 2：OTHER'," +
                        "  `LOCATION_ID` int(0) NULL DEFAULT NULL COMMENT '默认拣货库位，库位ID'," +
                        "  `RA_LOCATION_LIMIT` int(0) NULL DEFAULT NULL COMMENT '库位上限'," +
                        "  `RA_ALARM_LIMIT` int(0) NULL DEFAULT NULL COMMENT '库区报警下限'," +
                        "  `RA_PRINT_ADD` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '库区打印机地址'," +
                        "  `AREA_ID` int(0) NULL DEFAULT NULL COMMENT '所属区域，区域ID'," +
                        "  `MODIFY_TIME` datetime(0) NULL DEFAULT NULL COMMENT '修改时间'," +
                        "  `VIEW_MODE` int(0) NULL DEFAULT 1 COMMENT '0：不可查询 1：可查询'," +
                        "  PRIMARY KEY (`ID`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '仓库库区表' ROW_FORMAT = Dynamic;",
                        "" +
                        "-- ----------------------------" ,
                        "-- Table structure for c_wms_warehouse_t" +
                        "-- ----------------------------" ,
                        "DROP TABLE IF EXISTS `c_wms_warehouse_t`;",
                        "CREATE TABLE `c_wms_warehouse_t`  (" +
                        "  `ID` int(0) NOT NULL AUTO_INCREMENT," +
                        "  `NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '仓库名称'," +
                        "  `MANAGER` int(0) NULL DEFAULT NULL COMMENT '负责人，员工id'," +
                        "  `OPERATIONS` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作员，存储员工ID,可选多个，以“;”隔开'," +
                        "  `MANAGER_TELE` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '负责人电话'," +
                        "  `ADDRESS` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '仓库地址'," +
                        "  `NOTE` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注'," +
                        "  `DEFAULT_M` int(0) NULL DEFAULT NULL COMMENT '默认仓库，0：否 1：是'," +
                        "  `MODIFY_TIME` datetime(0) NULL DEFAULT NULL COMMENT '修改时间'," +
                        "  `VIEW_MODE` int(0) NULL DEFAULT 1 COMMENT '0：不可查询 1：可查询'," +
                        "  PRIMARY KEY (`ID`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '仓库表' ROW_FORMAT = Dynamic;",
                        "" +
                        "-- ----------------------------" ,
                        "-- Table structure for db_version" +
                        "-- ----------------------------" ,
                        "DROP TABLE IF EXISTS `db_version`;",
                        "CREATE TABLE `db_version`  (" +
                        "  `version` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL" +
                        ") ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;",
                        "" +
                        "-- ----------------------------" ,
                        "-- Table structure for k3_export_notifydetail" +
                        "-- ----------------------------" ,
                        "DROP TABLE IF EXISTS `k3_export_notifydetail`;",
                        "CREATE TABLE `k3_export_notifydetail`  (" +
                        "  `ID` int(0) NOT NULL AUTO_INCREMENT," +
                        "  `EXPORT_ID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '单据编号 出库单ID'," +
                        "  `BOM_ID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料长代码'," +
                        "  `EXPORT_LOT_NO` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单号'," +
                        "  `EXPORT_PACK_QUANTITY` int(0) NULL DEFAULT NULL COMMENT '出库数量'," +
                        "  `EXPORT_WAREHOUSE_ID` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '仓库ID'," +
                        "  `Material_Id` int(0) NULL DEFAULT NULL COMMENT '物料id'," +
                        "  `materialGoodsModel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料规格型号'," +
                        "  `project_Id` int(0) NULL DEFAULT NULL COMMENT '项目id'," +
                        "  `dt` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '生成时间'," +
                        "  PRIMARY KEY (`ID`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 163 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;",
                        "" +
                        "-- ----------------------------" ,
                        "-- Table structure for k3_import_notifydetail" +
                        "-- ----------------------------" ,
                        "DROP TABLE IF EXISTS `k3_import_notifydetail`;",
                        "CREATE TABLE `k3_import_notifydetail`  (" +
                        "  `ID` int(0) NOT NULL AUTO_INCREMENT," +
                        "  `IMPORT_ID` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '单据编号 入库单ID'," +
                        "  `IMPORT_GOODS_CODE` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料长代码'," +
                        "  `IMPORT_PACK_QUANTITY` int(0) NULL DEFAULT NULL COMMENT '入库数量 内箱'," +
                        "  `IMPORT_WAREHOUSE_ID` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '仓库ID'," +
                        "  `IMPORT_REMARK` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注'," +
                        "  `IMPORT_MATERIAL_NAME` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '物料名称'," +
                        "  `IMPORT_PROJECT_NO` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目号 物料所属项目'," +
                        "  `RESULT` int(0) NULL DEFAULT 0 COMMENT '是否全部入库 0否 1是'," +
                        "  `RECEIVED_NUMBER` int(0) NULL DEFAULT NULL COMMENT '已入库数量'," +
                        "  `project_id` int(0) NULL DEFAULT NULL COMMENT '项目id'," +
                        "  `material_id` int(0) NULL DEFAULT NULL COMMENT '物料id'," +
                        "  PRIMARY KEY (`ID`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 22862 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;",
                        "" +
                        "-- ----------------------------" ,
                        "-- Table structure for kanban_attr" +
                        "-- ----------------------------" ,
                        "DROP TABLE IF EXISTS `kanban_attr`;",
                        "CREATE TABLE `kanban_attr`  (" +
                        "  `ID` int(0) NOT NULL AUTO_INCREMENT," +
                        "  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '属性名称'," +
                        "  `objId` int(0) NOT NULL COMMENT '对象id'," +
                        "  PRIMARY KEY (`ID`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 73 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '数据属性和对象绑定' ROW_FORMAT = Dynamic;",
                        "" +
                        "-- ----------------------------" ,
                        "-- Table structure for kanban_eleattr" +
                        "-- ----------------------------" ,
                        "DROP TABLE IF EXISTS `kanban_eleattr`;",
                        "CREATE TABLE `kanban_eleattr`  (" +
                        "  `ID` int(0) NOT NULL AUTO_INCREMENT," +
                        "  `eleId` int(0) NOT NULL COMMENT '元素id'," +
                        "  `attrId` int(0) NOT NULL COMMENT '属性id'," +
                        "  PRIMARY KEY (`ID`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 88 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '元素和数据属性绑定' ROW_FORMAT = Dynamic;",
                        "" +
                        "-- ----------------------------" ,
                        "-- Table structure for kanban_eleobj" +
                        "-- ----------------------------" ,
                        "DROP TABLE IF EXISTS `kanban_eleobj`;",
                        "CREATE TABLE `kanban_eleobj`  (" +
                        "  `ID` int(0) NOT NULL AUTO_INCREMENT," +
                        "  `eleName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '元素名称'," +
                        "  `objId` int(0) NULL DEFAULT NULL COMMENT '对象id'," +
                        "  PRIMARY KEY (`ID`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 43 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '元素与对象匹配表' ROW_FORMAT = Dynamic;",
                        "" +
                        "-- ----------------------------" ,
                        "-- Table structure for kanban_elewhere" +
                        "-- ----------------------------" ,
                        "DROP TABLE IF EXISTS `kanban_elewhere`;",
                        "CREATE TABLE `kanban_elewhere`  (" +
                        "  `ID` int(0) NOT NULL AUTO_INCREMENT," +
                        "  `eleId` int(0) NOT NULL COMMENT '元素id'," +
                        "  `whereId` int(0) NOT NULL COMMENT '条件id'," +
                        "  `whereValue` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'where条件值'," +
                        "  PRIMARY KEY (`ID`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '元素得数据限制条件' ROW_FORMAT = Dynamic;",
                        "" +
                        "-- ----------------------------" ,
                        "-- Table structure for kanban_flag" +
                        "-- ----------------------------" ,
                        "DROP TABLE IF EXISTS `kanban_flag`;",
                        "CREATE TABLE `kanban_flag`  (" +
                        "  `ID` int(0) NOT NULL," +
                        "  PRIMARY KEY (`ID`) USING BTREE" +
                        ") ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;",
                        "" +
                        "-- ----------------------------" ,
                        "-- Table structure for kanban_obj" +
                        "-- ----------------------------" ,
                        "DROP TABLE IF EXISTS `kanban_obj`;",
                        "CREATE TABLE `kanban_obj`  (" +
                        "  `ID` int(0) NOT NULL AUTO_INCREMENT," +
                        "  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '对象名称'," +
                        "  PRIMARY KEY (`ID`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 43 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '数据对象' ROW_FORMAT = Dynamic;",
                        "" +
                        "-- ----------------------------" ,
                        "-- Table structure for kanban_objwhere" +
                        "-- ----------------------------" ,
                        "DROP TABLE IF EXISTS `kanban_objwhere`;",
                        "CREATE TABLE `kanban_objwhere`  (" +
                        "  `ID` int(0) NOT NULL AUTO_INCREMENT," +
                        "  `whereName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'where条件名'," +
                        "  `showName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'where条件名'," +
                        "  `objId` int(0) NOT NULL COMMENT '对象id'," +
                        "  PRIMARY KEY (`ID`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 41 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '对象支持的where条件' ROW_FORMAT = Dynamic;",
                        "" +
                        "-- ----------------------------" ,
                        "-- Table structure for kanban_test1" +
                        "-- ----------------------------" ,
                        "DROP TABLE IF EXISTS `kanban_test1`;",
                        "CREATE TABLE `kanban_test1`  (" +
                        "  `ID` int(0) NOT NULL AUTO_INCREMENT," +
                        "  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '看板名称'," +
                        "  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '看板介绍'," +
                        "  `json` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '看板json'," +
                        "  `imagesByte` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '缩略图'," +
                        "  PRIMARY KEY (`ID`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '看板内容' ROW_FORMAT = Dynamic;",
                        "" +
                        "-- ----------------------------" ,
                        "-- Table structure for p_alarm_problems" +
                        "-- ----------------------------" ,
                        "DROP TABLE IF EXISTS `p_alarm_problems`;",
                        "CREATE TABLE `p_alarm_problems`  (" +
                        "  `id` int(0) NOT NULL AUTO_INCREMENT," +
                        "  `problem` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '问题'," +
                        "  `establish_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间'," +
                        "  `response_time` datetime(0) NULL DEFAULT NULL COMMENT '响应时间'," +
                        "  `solve_time` datetime(0) NULL DEFAULT NULL COMMENT '解决时间'," +
                        "  `establish_user_id` int(0) NULL DEFAULT NULL COMMENT '创建用户id'," +
                        "  `problem_level_id` int(0) NULL DEFAULT NULL COMMENT '问题级别'," +
                        "  `event_id` int(0) NULL DEFAULT NULL COMMENT '事件'," +
                        "  PRIMARY KEY (`id`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '历史问题' ROW_FORMAT = Dynamic;",
                        "" +
                        "-- ----------------------------" ,
                        "-- Table structure for p_andon_issued_message" +
                        "-- ----------------------------" ,
                        "DROP TABLE IF EXISTS `p_andon_issued_message`;",
                        "CREATE TABLE `p_andon_issued_message`  (" +
                        "  `id` int(0) NOT NULL AUTO_INCREMENT," +
                        "  `fault_id` int(0) NULL DEFAULT NULL," +
                        "  `message_id` int(0) NULL DEFAULT NULL," +
                        "  `dt` datetime(0) NULL DEFAULT NULL," +
                        "  PRIMARY KEY (`id`) USING BTREE" +
                        ") ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;",
                //增加列
                "ALTER TABLE p_mes_bolt_t ADD Y int(0) DEFAULT NULL;",
                        "ALTER TABLE p_mes_bolt_t ADD MATERIAL_INSTANCE_ID int(0) DEFAULT NULL COMMENT '物料实例id';",
                //修改列
                "ALTER TABLE p_mes_keypart_t MODIFY TRANSFER int(0) COMMENT '上传标记';",
                        "ALTER TABLE p_mes_keypart_t MODIFY KEYPART_MODE int(0) COMMENT '模式';",
                //增加列
                "ALTER TABLE p_mes_keypart_t ADD MATERIAL_INSTANCE_ID int(0) DEFAULT NULL COMMENT '物料实例id';",
                //修改表注释
                "ALTER TABLE p_mes_leakage_t COMMENT '气密性信息';",

                //修改列
                "ALTER TABLE p_mes_plan_t MODIFY  DT datetime(0) not null," +
                        " MODIFY  PLAN_NAME text not null," +
                        " MODIFY  PRODUCTION_ID int(0) not null," +
                        " MODIFY  PLAN_NUMBER int(0) not null;",
                //增加列
                "ALTER TABLE" +
                        "  p_mes_plan_t" +
                        " ADD" +
                        "  OVER_DT datetime(0) NULL DEFAULT NULL COMMENT '结束日期'," +
                        " ADD" +
                        "  R_MES_PLAN_ID int(0) NULL DEFAULT NULL COMMENT '原有计划'," +
                        " ADD" +
                        " OUTING_ID int(0) NULL DEFAULT NULL COMMENT '工艺id',"+
                        " ADD" +
                        " TOTAL_RECIPE_ID int(0) NULL DEFAULT NULL COMMENT '配方id'," +
                        " ADD" +
                        " PLAN_START_TIME datetime(0) NULL DEFAULT NULL COMMENT '计划开始时间'," +
                        " ADD" +
                        " ACTUAL_START_TIME datetime(0) NULL DEFAULT NULL COMMENT '实际开始时间'," +
                        " ADD" +
                        " PLAN_END_TIME datetime(0) NULL DEFAULT NULL COMMENT '计划完工时间'," +
                        " ADD" +
                        " ACTUAL_END_TIME datetime(0) NULL DEFAULT NULL COMMENT '实际完工时间';",
                //创建表
                "DROP TABLE IF EXISTS `p_mes_station_serial_flag_t`;",
                        "CREATE TABLE `p_mes_station_serial_flag_t`  (" +
                        "  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键'," +
                        "  `SN` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '总成号'," +
                        "  `DT` datetime(0) NULL DEFAULT NULL COMMENT '创建时间'," +
                        "  `ST` int(0) NULL DEFAULT NULL COMMENT '工位id'," +
                        "  `SERIAL` int(0) NULL DEFAULT NULL COMMENT '序号'," +
                        "  `FLAG` int(0) NULL DEFAULT 0 COMMENT '完成标记 0未完成 1完成'," +
                        "  PRIMARY KEY (`ID`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 403 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '工位完成情况永久表' ROW_FORMAT = Dynamic;",
                //修改表注释
                "ALTER TABLE p_mes_tracking_t COMMENT '产品下线表';",
                //修改列
                "ALTER TABLE p_mes_tracking_t MODIFY  SN varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '总成';",
                //添加索引
                "ALTER TABLE `p_mes_tracking_t` ADD INDEX `word_order_id_index`(`WORK_ORDER_ID`) USING BTREE;",
                //添加索引
                "ALTER TABLE `p_mes_tracking_t` ADD INDEX `SN_INDEX`(`SN`) USING BTREE;",

                //修改列
                "ALTER TABLE p_mes_workorder_detail_t" +
                        " MODIFY " +
                        "  PLAN_ID int(0) NULL DEFAULT NULL COMMENT '计划ID'," +
                        " MODIFY " +
                        "  PRINTE_NUMBER int(0) NULL DEFAULT NULL COMMENT '生成条码数量';",
                //增加列
                "ALTER TABLE" +
                        "  p_mes_workorder_detail_t" +
                        "  ADD" +
                        "  `COMPLETE_NUMBER` int(0) NULL DEFAULT NULL COMMENT '完成数量'," +
                        "  ADD" +
                        "    `PLAN_START_TIME` datetime(0) NULL DEFAULT NULL COMMENT '计划开始时间'," +
                        "  ADD" +
                        "    `PLAN_END_TIME` datetime(0) NULL DEFAULT NULL COMMENT '计划结束时间'," +
                        "  ADD" +
                        "    `ACTUAL_START_TIME` datetime(0) NULL DEFAULT NULL COMMENT '实际开始时间'," +
                        "  ADD" +
                        "    `ACTUAL_END_TIME` datetime(0) NULL DEFAULT NULL COMMENT '实际结束时间'," +
                        "  ADD" +
                        "    `ROUTING_ID` int(0) NULL DEFAULT NULL COMMENT '工艺路线id'," +
                        "  ADD" +
                        "    `BARCODE_RULE_ID` int(0) NULL DEFAULT NULL COMMENT '条码规则id'," +
                        "  ADD" +
                        "    `BOM_ID` int(0) NULL DEFAULT NULL COMMENT 'BOMid'," +
                        "  ADD" +
                        "    `PRODUCT_ID` int(0) NULL DEFAULT NULL COMMENT '产品id'," +
                        "  ADD" +
                        "    `TOTAL_RECIPE_ID` int(0) NULL DEFAULT NULL COMMENT '配方id'," +
                        "  ADD" +
                        "    `OK_NUMBER` int(0) NULL DEFAULT NULL COMMENT '合格数量'," +
                        "  ADD" +
                        "    `ORDER_ID` int(0) NULL DEFAULT NULL COMMENT '订单id'," +
                        "  ADD" +
                        "    `ORDERRECORD_ID` int(0) NULL DEFAULT NULL COMMENT '订单记录id';",

                //创建表
                "DROP TABLE IF EXISTS `p_wms_in_taskqueue_t`;",
                        "CREATE TABLE `p_wms_in_taskqueue_t`  (" +
                        "  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键'," +
                        "  `DT` datetime(0) NULL DEFAULT NULL COMMENT '日期'," +
                        "  `LIST_NO` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单号'," +
                        "  `TRAY` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '托盘号'," +
                        "  `FLAG` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '动作标记'," +
                        "  `OVER_DT` datetime(0) NULL DEFAULT NULL COMMENT '完成时间'," +
                        "  `LOCATION_ID` int(0) NULL DEFAULT NULL COMMENT '库位id'," +
                        "  PRIMARY KEY (`ID`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 62 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '入库队列永久记录表' ROW_FORMAT = Dynamic;",
                        "" +
                        "-- ----------------------------" ,
                        "-- Table structure for p_wms_out_taskqueue_t" +
                        "-- ----------------------------" ,
                        "DROP TABLE IF EXISTS `p_wms_out_taskqueue_t`;",
                        "CREATE TABLE `p_wms_out_taskqueue_t`  (" +
                        "  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键'," +
                        "  `DT` datetime(0) NULL DEFAULT NULL COMMENT '日期'," +
                        "  `LIST_NO` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单号'," +
                        "  `TRAY` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '托盘号'," +
                        "  `STATION_ID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '目标工位ID'," +
                        "  `FLAG` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '动作标记'," +
                        "  `OVER_DT` datetime(0) NULL DEFAULT NULL COMMENT '完成时间'," +
                        "  `LOCATION_ID` int(0) NULL DEFAULT NULL COMMENT '库位表'," +
                        "  PRIMARY KEY (`ID`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 2947 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '出库队列永久记录表' ROW_FORMAT = Dynamic;",
                //创建表
                "DROP TABLE IF EXISTS `p_wms_storage_detail_t`;",
                        "CREATE TABLE `p_wms_storage_detail_t`  (" +
                        "  `ID` int(0) NOT NULL AUTO_INCREMENT," +
                        "  `DT` datetime(0) NULL DEFAULT NULL COMMENT '入库时间'," +
                        "  `MATERIAL_ID` int(0) NULL DEFAULT NULL COMMENT '物料id'," +
                        "  `MATERIAL_NUMBER` int(0) NULL DEFAULT NULL COMMENT '数量，用字符表示，如果是出库“-”号，直接是数字'," +
                        "  `MATERIAL_CODE` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料条码'," +
                        "  `AREA_ID` int(0) NULL DEFAULT NULL COMMENT '区域id'," +
                        "  `RESERVOIR_AREA_ID` int(0) NULL DEFAULT NULL COMMENT '库区id'," +
                        "  `LOCATION_ID` int(0) NULL DEFAULT NULL COMMENT '库位id'," +
                        "  `NOTE` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注'," +
                        "  `LIST_NO` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单号，根据单号查找明细'," +
                        "  `PROJECT_ID` int(0) NULL DEFAULT NULL COMMENT '所属项目'," +
                        "  `SUPPLIER_ID` int(0) NULL DEFAULT NULL COMMENT '供应商id'," +
                        "  `TRAY` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '托盘号'," +
                        "  `WAREHOUSE_ID` int(0) NULL DEFAULT NULL COMMENT '仓库ID'," +
                        "  `YN_SHIFT` int(0) NULL DEFAULT NULL COMMENT '是否移库  0：否、1：是'," +
                        "  `ISSUE_OR_RECEIPT` int(0) NULL DEFAULT NULL COMMENT '出库还是入库 0：出库  1：入库'," +
                        "  `STATION_ID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '目标工位ID'," +
                        "  `bar_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL," +
                        "  `MATERIAL_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料名称'," +
                        "  `WAREHOUSE_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '仓库名称'," +
                        "  `AREA_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区域名称'," +
                        "  `RESERVOIR_AREA_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '库区名称'," +
                        "  `LOCATION_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '库位名称'," +
                        "  `PROJECT_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目名称'," +
                        "  `STATION_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工位名称'," +
                        "  PRIMARY KEY (`ID`) USING BTREE," +
                        "  INDEX `p_wms_storage_detail_t`(`MATERIAL_ID`, `AREA_ID`, `RESERVOIR_AREA_ID`, `LOCATION_ID`, `LIST_NO`, `PROJECT_ID`, `WAREHOUSE_ID`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 24348 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '库存明细表' ROW_FORMAT = Dynamic;",
                //创建表
                "DROP TABLE IF EXISTS `r_alarm_problems`;",
                        " CREATE TABLE `r_alarm_problems`  (" +
                        "  `id` int(0) NOT NULL AUTO_INCREMENT," +
                        "  `problem` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '问题'," +
                        "  `establish_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间'," +
                        "  `response_time` datetime(0) NULL DEFAULT NULL COMMENT '响应时间'," +
                        "  `solve_time` datetime(0) NULL DEFAULT NULL COMMENT '解决时间'," +
                        "  `establish_user_id` int(0) NULL DEFAULT NULL COMMENT '创建用户id'," +
                        "  `problem_level_id` int(0) NULL DEFAULT NULL COMMENT '问题级别'," +
                        "  `event_id` int(0) NULL DEFAULT NULL COMMENT '事件'," +
                        "  PRIMARY KEY (`id`) USING BTREE" +
                        ") ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '当前问题' ROW_FORMAT = Dynamic;",
                        "" +
                        "-- ----------------------------" ,
                        "-- Table structure for r_andon_issued_message" +
                        "-- ----------------------------" ,
                        "DROP TABLE IF EXISTS `r_andon_issued_message`;",
                        "CREATE TABLE `r_andon_issued_message`  (" +
                        "  `id` int(0) NOT NULL AUTO_INCREMENT," +
                        "  `fault_id` int(0) NULL DEFAULT NULL," +
                        "  `message_id` int(0) NULL DEFAULT NULL," +
                        "  `dt` datetime(0) NULL DEFAULT NULL," +
                        "  PRIMARY KEY (`id`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;",
                        "" +
                        "-- ----------------------------" ,
                        "-- Table structure for r_andon_steel_platform" +
                        "-- ----------------------------" ,
                        "DROP TABLE IF EXISTS `r_andon_steel_platform`;",
                        "CREATE TABLE `r_andon_steel_platform`  (" +
                        "  `id` int(0) NOT NULL AUTO_INCREMENT," +
                        "  `dt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '扫码时间'," +
                        "  `sn` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '总成号'," +
                        "  `productId` int(0) NULL DEFAULT NULL COMMENT '产品id'," +
                        "  `productName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品名称'," +
                        "  `productNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品编码'," +
                        "  `productModel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品型号'," +
                        "  `productAbbreviation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品简称'," +
                        "  PRIMARY KEY (`id`) USING BTREE" +
                        ") ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;",
                //修改列
                "ALTER TABLE r_mes_bolt_t" +
                        "  MODIFY" +
                        "  `Y` int(0) NOT NULL DEFAULT 0," +
                        " MODIFY" +
                        "  TRANSFER int(0) NULL DEFAULT NULL;",
                //增加列
                "ALTER TABLE" +
                        "  r_mes_bolt_t" +
                        "  ADD" +
                        "    `MATERIAL_INSTANCE_ID` int(0) NULL DEFAULT NULL COMMENT '物料实例id';",
                //添加索引
                "ALTER TABLE `r_mes_bolt_t` ADD INDEX `ST_INDEX`(`ST`) USING BTREE;",
                //添加索引
                "ALTER TABLE `r_mes_bolt_t` ADD INDEX `SN_INDEX`(`SN`) USING BTREE;",
                //添加索引
                "ALTER TABLE `r_mes_bolt_t` ADD INDEX `BOLT_NAME_INDEX`(`BOLT_NAME`) USING BTREE;",
                //修改列
                "ALTER TABLE r_mes_keypart_t" +
                        "  MODIFY" +
                        " `TRANSFER` int(0) NULL DEFAULT NULL," +
                        "  MODIFY" +
                        "   `KEYPART_MODE` int(0) NULL DEFAULT NULL," +
                        "  MODIFY" +
                        " `KEYPART_ID` int(0) NULL DEFAULT NULL," +
                        "  MODIFY" +
                        "   `MATERIAL_ID` int(0) NULL DEFAULT NULL COMMENT '物料ID';",
                //增加列
                "ALTER TABLE" +
                        "  r_mes_leakage_t" +
                        "  ADD" +
                        "  `MATERIAL_INSTANCE_ID` int(0) NULL DEFAULT NULL COMMENT '物料实例id';",
                //修改表注释
                "ALTER TABLE p_mes_tracking_t COMMENT '气密性测试';",
                //修改列
                "ALTER TABLE r_mes_motor_leakage_t" +
                        "   MODIFY" +
                        "  `ID` int(0) NOT NULL," +
                        "   MODIFY" +
                        "  `LEAKAGE_NAME` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL," +
                        "   MODIFY" +
                        "    `LEAKAGE_PV` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL," +
                        "   MODIFY" +
                        "    `LEAKAGE_LV` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL," +
                        "   MODIFY" +
                        "    `LEAKAGE_R` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL," +
                        "   MODIFY" +
                        "  `REASONS` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,"+
                        "   MODIFY" +
                        "    `WID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL;",
                //修改列
                "ALTER TABLE r_mes_moudle_leakage_t" +
                        "  MODIFY" +
                        " `TRANSFER` decimal(65, 30) NULL DEFAULT NULL," +
                        "  MODIFY" +
                        "   `LEAKAGE_MODE` decimal(65, 30) NULL DEFAULT NULL;",
                //修改列
                "ALTER TABLE r_mes_moudle_tracking_t" +
                        "  MODIFY" +
                        " `ID` int(0) NOT NULL COMMENT '主键'," +
                        "  MODIFY" +
                        " `PLAN_ID` decimal(65, 30) NULL DEFAULT NULL COMMENT '计划ID',"+
                        "  MODIFY" +
                        " `PRODUCTION_ID` decimal(65, 30) NULL DEFAULT NULL COMMENT '产品ID'," +
                        "  MODIFY" +
                        "   `LINE_ID` decimal(65, 30) NULL DEFAULT NULL COMMENT '产线ID'," +
                        "  MODIFY" +
                        "   `WORK_ORDER_ID` decimal(65, 30) NULL DEFAULT NULL COMMENT '工单ID';",
                //修改列
                "ALTER TABLE r_mes_plan_print_t" +
                        "  MODIFY" +
                        " `SN` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '总成号'," +
                        "  MODIFY" +
                        "   `PLAN_ID` int(0) NULL DEFAULT NULL COMMENT '计划ID'," +
                        "  MODIFY" +
                        "   `PRINT_FLAG` int(0) NOT NULL COMMENT '是否打印标记  0：否  1：是';",
                //增加列
                "ALTER TABLE" +
                        "  r_mes_plan_print_t" +
                        "  ADD" +
                        "  `DT_DAYS` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '格式化后的日期只显示到天'," +
                        "  ADD" +
                        "    `DT_DAYS_INTEGER` int(0) NULL DEFAULT NULL COMMENT '0000年（公元1年）至当前日期的总天数。',"+
                        "  ADD" +
                        "  INDEX `dt_index`(`DT`) USING BTREE," +
                        "  ADD" +
                        "    INDEX `dt_days_index`(`DT_DAYS`) USING BTREE," +
                        "  ADD" +
                        "    INDEX `dt_days_integer_index`(`DT_DAYS_INTEGER`) USING BTREE," +
                        "  ADD" +
                        "    INDEX `lid_index`(`LINE_ID`) USING BTREE," +
                        "  ADD" +
                        "    INDEX `SN_INDEX`(`SN`) USING BTREE;",

                //修改列
                "ALTER TABLE r_mes_plan_t" +
                        "   MODIFY" +
                        "  `PLAN_NAME` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '计划名称'," +
                        "   MODIFY"+
                        "    `PRODUCTION_ID` int(0) DEFAULT NULL COMMENT '产品ID'," +
                        "   MODIFY"+
                        "    `PLAN_NUMBER` int(0) DEFAULT NULL COMMENT '计划数量'," +
                        "   MODIFY"+
                        "    `COMPLETE_NUMBER` int(0) NULL DEFAULT NULL COMMENT '完成数量'," +
                        "   MODIFY" +
                        "  `ONLINE_NUMBER` int(0) NULL DEFAULT NULL COMMENT '上线数量';",
                //增加列
                "ALTER TABLE" +
                        "  r_mes_plan_t" +
                        "  ADD" +
                        "  `ROUTING_ID` int(0) NULL DEFAULT NULL COMMENT '工艺id'," +
                        "  ADD" +
                        "    `TOTAL_RECIPE_ID` int(0) NULL DEFAULT NULL COMMENT '配方id'," +
                        "  ADD" +
                        "    `PLAN_START_TIME` datetime(0) NULL DEFAULT NULL COMMENT '计划开始时间'," +
                        "  ADD" +
                        "    `PLAN_END_TIME` datetime(0) NULL DEFAULT NULL COMMENT '计划完工时间'," +
                        "  ADD" +
                        "    `ACTUAL_START_TIME` datetime(0) NULL DEFAULT NULL COMMENT '实际开始时间'," +
                        "  ADD" +
                        "    `ACTUAL_END_TIME` datetime(0) NULL DEFAULT NULL COMMENT '实际完工时间'," +
                        "  ADD" +
                        "    `PRODUCT_MARK` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品标记'," +
                        "  ADD" +
                        "    `PLAN_TYPE_ID` int(0) NULL DEFAULT NULL COMMENT '计划类型ID';",
                //创建表
                "-- Table structure for r_mes_station_serial_flag_t" +
                        "-- ----------------------------" ,
                        "DROP TABLE IF EXISTS `r_mes_station_serial_flag_t`;",
                        "CREATE TABLE `r_mes_station_serial_flag_t`  (" +
                        "  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键'," +
                        "  `SN` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '总成号'," +
                        "  `DT` datetime(0) NULL DEFAULT NULL COMMENT '创建时间'," +
                        "  `ST` int(0) NULL DEFAULT NULL COMMENT '工位id'," +
                        "  `SERIAL` int(0) NULL DEFAULT NULL COMMENT '序号'," +
                        "  `FLAG` int(0) NULL DEFAULT 0 COMMENT '完成标记 0未完成 1完成'," +
                        "  PRIMARY KEY (`ID`) USING BTREE," +
                        "  INDEX `SN_INDEX`(`SN`) USING BTREE," +
                        "  INDEX `ST_INDEX`(`ST`) USING BTREE" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 1969100 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '工位完成情况表' ROW_FORMAT = Dynamic;",
                //修改列
                "ALTER TABLE r_mes_tracking_t" +
                        "  MODIFY" +
                        " `SN` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '总成';",
                //添加索引
                "ALTER TABLE `r_mes_tracking_t` ADD INDEX `SN_INDEX`(`SN`) USING BTREE",
                //修改列名
                "alter table r_mes_workorder_detail_t change PRODUCTION_ID WORKORDER_ID varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '工单编号';",
                //修改列
                "ALTER TABLE r_mes_workorder_detail_t" +
                        "   MODIFY" +
                        "  `LEVEL_NO` int(0) NOT NULL COMMENT '优先级',"+
                        "   MODIFY" +
                        "  `STATUS` int(0) NULL DEFAULT NULL COMMENT '状态  0-创建 1-提交 2-下达 3-投产 4-关闭  6-暂停 10-完成',"+
                        "   MODIFY" +
                        "  `PLAN_ID` int(0) NULL DEFAULT NULL COMMENT '计划ID'," +
                        "   MODIFY" +
                        "    `CREATE_BARCODE_FLAG` int(0) NULL DEFAULT NULL COMMENT '生成条码标记  0：没有生成  1：已生成';",
                //增加列
                "ALTER TABLE" +
                        "  r_mes_workorder_detail_t" +
                        "  ADD" +
                        "  `COMPLETE_NUMBER` int(0) NULL DEFAULT NULL COMMENT '完成数量'," +
                        "  ADD" +
                        "    `REMAIND_NUMBER` int(0) NULL DEFAULT NULL COMMENT '剩余数量'," +
                        "  ADD" +
                        "    `PLAN_START_TIME` datetime(0) NULL DEFAULT NULL COMMENT '计划开始时间'," +
                        "  ADD" +
                        "    `PLAN_END_TIME` datetime(0) NULL DEFAULT NULL COMMENT '计划结束时间'," +
                        "  ADD" +
                        "    `ACTUAL_START_TIME` datetime(0) NULL DEFAULT NULL COMMENT '实际开始时间'," +
                        "  ADD" +
                        "    `ACTUAL_END_TIME` datetime(0) NULL DEFAULT NULL COMMENT '实际结束时间'," +
                        "  ADD" +
                        "    `ROUTING_ID` int(0) NULL DEFAULT NULL COMMENT '工艺路线id'," +
                        "  ADD" +
                        "    `BARCODE_RULE_ID` int(0) NULL DEFAULT NULL COMMENT '条码规则id'," +
                        "  ADD" +
                        "    `BOM_ID` int(0) NULL DEFAULT NULL COMMENT 'BOMid'," +
                        "  ADD" +
                        "    `PRODUCT_ID` int(0) NULL DEFAULT NULL COMMENT '产品id'," +
                        "  ADD" +
                        "    `TOTAL_RECIPE_ID` int(0) NULL DEFAULT NULL COMMENT '配方id'," +
                        "  ADD" +
                        "    `OK_NUMBER` int(0) NULL DEFAULT NULL COMMENT '合格数量'," +
                        "  ADD" +
                        "    `ORDER_ID` int(0) NULL DEFAULT NULL COMMENT '订单id'," +
                        "  ADD" +
                        "    `ORDERRECORD_ID` int(0) NULL DEFAULT NULL COMMENT '订单记录id';",
                //添加索引
                "ALTER TABLE `r_mes_workorder_detail_t` ADD INDEX `STATUS_index`(`STATUS`) USING BTREE;",
                //添加索引
                "ALTER TABLE `r_mes_workorder_detail_t` ADD INDEX `LINE_ID_index`(`LINE_ID`) USING BTREE;",

                //创建表
                "-- Table structure for r_wms_in_taskqueue_t" +
                        "-- ----------------------------" ,
                        "DROP TABLE IF EXISTS `r_wms_in_taskqueue_t`;",
                        "CREATE TABLE `r_wms_in_taskqueue_t`  (" +
                        "  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键'," +
                        "  `DT` datetime(0) NULL DEFAULT NULL COMMENT '日期'," +
                        "  `LIST_NO` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单号'," +
                        "  `TRAY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '托盘号'," +
                        "  `FLAG` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '动作标记、0待操作、1正在进行'," +
                        "  `OVER_DT` datetime(0) NULL DEFAULT NULL COMMENT '完成时间'," +
                        "  `LOCATION_ID` int(0) NULL DEFAULT NULL COMMENT '库位id'," +
                        "  PRIMARY KEY (`ID`) USING BTREE" +
                        ") ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '入库队列永久记录表' ROW_FORMAT = Dynamic;",
                        "" +
                        "-- ----------------------------" ,
                        "-- Table structure for r_wms_out_taskqueue_t" +
                        "-- ----------------------------" ,
                        "DROP TABLE IF EXISTS `r_wms_out_taskqueue_t`;",
                        "CREATE TABLE `r_wms_out_taskqueue_t`  (" +
                        "  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键'," +
                        "  `DT` datetime(0) NULL DEFAULT NULL COMMENT '日期'," +
                        "  `LIST_NO` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单号'," +
                        "  `TRAY` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '托盘号'," +
                        "  `STATION_ID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '目标工位ID'," +
                        "  `FLAG` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '动作标记、0待操作、1正在进行'," +
                        "  `OVER_DT` datetime(0) NULL DEFAULT NULL COMMENT '完成时间'," +
                        "  `LOCATION_ID` int(0) NULL DEFAULT NULL COMMENT '库位表'," +
                        "  PRIMARY KEY (`ID`) USING BTREE" +
                        ") ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '出库队列永久记录表' ROW_FORMAT = Dynamic;",
                        "" +
                        "-- ----------------------------" ,
                        "-- Table structure for r_wms_storage_detail_t" +
                        "-- ----------------------------" ,
                        "DROP TABLE IF EXISTS `r_wms_storage_detail_t`;",
                        "CREATE TABLE `r_wms_storage_detail_t`  (" +
                        "  `ID` int(0) NOT NULL AUTO_INCREMENT," +
                        "  `DT` datetime(0) NULL DEFAULT NULL COMMENT '入库时间'," +
                        "  `MATERIAL_ID` int(0) NULL DEFAULT NULL COMMENT '物料id'," +
                        "  `MATERIAL_NUMBER` int(0) NULL DEFAULT NULL COMMENT '数量，用字符表示，如果是出库“-”号，直接是数字'," +
                        "  `MATERIAL_CODE` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料条码'," +
                        "  `AREA_ID` int(0) NULL DEFAULT NULL COMMENT '区域id'," +
                        "  `RESERVOIR_AREA_ID` int(0) NULL DEFAULT NULL COMMENT '库区id'," +
                        "  `LOCATION_ID` int(0) NULL DEFAULT NULL COMMENT '库位id'," +
                        "  `NOTE` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注'," +
                        "  `LIST_NO` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单号，根据单号查找明细'," +
                        "  `PROJECT_ID` int(0) NULL DEFAULT NULL COMMENT '所属项目'," +
                        "  `SUPPLIER_ID` int(0) NULL DEFAULT NULL COMMENT '供应商id'," +
                        "  `TRAY` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '托盘号'," +
                        "  `WAREHOUSE_ID` int(0) NULL DEFAULT NULL COMMENT '仓库ID'," +
                        "  `YN_SHIFT` int(0) NULL DEFAULT NULL COMMENT '是否移库  0：否、1：是'," +
                        "  `ISSUE_OR_RECEIPT` int(0) NULL DEFAULT NULL COMMENT '出库还是入库 0：出库  1：入库'," +
                        "  `STATION_ID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '目标工位ID'," +
                        "  `user_id` int(0) NULL DEFAULT NULL COMMENT '用户id'," +
                        "  `bar_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '条码'," +
                        "  PRIMARY KEY (`ID`) USING BTREE," +
                        "  INDEX `storage_datail_index`(`MATERIAL_ID`, `AREA_ID`, `RESERVOIR_AREA_ID`, `LOCATION_ID`, `LIST_NO`, `PROJECT_ID`, `SUPPLIER_ID`) USING BTREE" +
                        ") ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '库存明细表' ROW_FORMAT = Dynamic;",
                //添加视图
                "-- View structure for view_event" +
                        "-- ----------------------------" ,
                        "DROP VIEW IF EXISTS `view_event`;",
                        "CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `view_event` AS select `c`.`ID` AS `ID`,`c`.`DT` AS `DT`,`c`.`OBJECT_TYPE` AS `OBJECT_TYPE`,`c`.`OBJECT_ID` AS `OBJECT_ID`,`c`.`EVENT` AS `EVENT`,`c`.`PARAMETER1` AS `PARAMETER1`,`c`.`PARAMETER2` AS `PARAMETER2`,`c`.`PARAMETER3` AS `PARAMETER3`,`c`.`OPERATOR` AS `OPERATOR`,`c`.`EVENT_DIS` AS `EVENT_DIS`,`c`.`OBJECT_TYPE2` AS `OBJECT_TYPE2`,`c`.`PARAMETER4` AS `PARAMETER4`,`c`.`PARAMETER5` AS `PARAMETER5`,`p`.`WORK_ORDER_ID` AS `WORK_ORDER_ID` from (`c_mes_event_t` `c` join `p_mes_tracking_t` `p` on((`c`.`OBJECT_ID` = `p`.`SN`)));",

                //添加表
                "DROP TABLE IF EXISTS `c_qh_menu`;\n",
                        "CREATE TABLE `c_qh_menu`  (\n" +
                "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                "  `menu_grade` int(11) NULL DEFAULT NULL COMMENT '菜单等级',\n" +
                "  `superior_menu_id` int(11) NULL DEFAULT NULL COMMENT '上级菜单id',\n" +
                "  `menu_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',\n" +
                        "  `if_enable` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否启用(布尔类型)',\n" +
                        "  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路径',\n" +
                "  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,\n" +
                "  `order` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '顺序',\n" +
                "  PRIMARY KEY (`id`) USING BTREE\n" +
                ") ENGINE = InnoDB AUTO_INCREMENT = 193 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;\n",

                //添加数据
                "INSERT INTO `c_qh_menu` VALUES (1, 1, 0, '首页', 'true', '/', 'shouye', '1'),"+
                " (2, 1, 0, '基础建模', 'true', '/skq', 'moban', '3'),"+
                " (3, 2, 2, '用户管理', 'true', 'userList', 'more', '1'),"+
                " (4, 2, 2, '角色管理', 'true', 'roleManager', 'more', '2'),"+
                " (5, 2, 2, '部门管理', 'true', 'Department', 'more', '3'),"+
                " (6, 2, 2, '产线管理', 'true', 'lineManager', 'more', '4'),"+
                " (7, 2, 2, '工位管理', 'true', 'stationManager', 'more', '5'),"+
                " (8, 2, 2, '程序注册', 'true', 'programerRegiste', 'more', '6'),"+
                " (9, 1, 0, '生产', 'true', '/productions', 'shengchan', '40'),"+
                " (10, 1, 0, '物料', 'true', '/material', 'wuliao', '9920'),"+
                " (11, 3, 10, '物料管理', 'true', 'materialManager', 'more', '1'),"+
                " (45, 3, 10, '物料实例管理', 'true', 'instanceManager', 'more', '2'),"+
                " (46, 3, 10, '物料事件', 'true', 'eventManager', 'more', '3'),"+
                " (47, 2, 9, 'BOM', 'false', '/bom', 'more', '4'),"+
                " (49, 3, 64, '制造参数清单', 'true', 'makeBill', 'more', '20'),"+
                " (54, 2, 9, '班组班次', 'true', '/group', 'more', '5'),"+
                " (55, 3, 54, '班次管理', 'true', 'shiftManager', 'more', '1'),"+
                " (56, 3, 54, '班组管理', 'true', 'groupManager', 'more', '2'),"+
                " (57, 3, 54, '员工管理', 'true', 'staffManager', 'more', '3'),"+
                " (58, 3, 54, '员工类型管理', 'true', 'staffTypeManager', 'more', '4'),"+
                " (59, 1, 0, '设备', 'true', '/equipment', 'shebei', '9950'),"+
                " (64, 2, 9, '工艺', 'true', '/process', 'more', '3'),"+
                " (66, 3, 64, '标签管理', 'true', 'labelManager', 'more', '16'),"+
                " (67, 3, 64, '规则管理', 'true', 'ruleTypeManager', 'more', '17'),"+
                " (68, 3, 64, '工艺路线', 'true', 'processRoute', 'more', '9'),"+
                " (69, 3, 64, '配方管理', 'true', 'formulaManager', 'more', '10'),"+
                " (70, 3, 64, '配方明细管理', 'true', 'formulaDatailManager', 'more', '11'),"+
                " (71, 3, 64, '导入配方', 'true', 'importFormula', 'more', '12'),"+
                " (72, 2, 9, '工单', 'true', '/production', 'more', '1'),"+
                " (73, 3, 72, '计划类型管理', 'false', 'planType', 'more', '1'),"+
                " (74, 3, 72, '计划配置', 'false', 'planConfiguration', 'more', '2'),"+
                " (76, 3, 72, '完成工单', 'true', 'completeWork', 'more', '4'),"+
                " (78, 3, 9, '维修', 'true', 'productMaintenance', 'more', '6'),"+
                " (79, 3, 9, '数据拆解', 'true', 'dataDismantl', 'more', '7'),"+
                " (80, 1, 0, '质量', 'true', '/quality', 'zhiliang', '9930'),"+
                " (81, 3, 80, 'IQC检验', 'true', 'IQC', 'more', '1'),"+
                " (82, 3, 80, 'SQE复核', 'true', 'SQE', 'more', '2'),"+
                " (83, 3, 80, '缺陷录入', 'true', 'defectEntry', 'more', '3'),"+
                " (84, 3, 80, '巡检记录', 'true', 'InspectionRecords', 'more', '4'),"+
                " (90, 2, 9, '安灯', 'true', '/andeng', 'more', '2'),"+
                " (91, 2, 90, '当前故障列表', 'true', 'fault', 'more', '1'),"+
                " (92, 2, 90, '故障类型', 'true', 'faulttype', 'more', '2'),"+
                " (93, 2, 90, '历史故障列表', 'true', 'hisfault', 'more', '3'),"+
                " (94, 2, 90, '损失类型', 'true', 'losstype', 'more', '4'),"+
                " (95, 2, 90, 'OEE', 'true', 'oee', 'more', '5'),"+
                " (96, 2, 90, '产量统计', 'true', 'outputstatic', 'more', '6'),"+
                " (97, 2, 90, '损失统计', 'true', 'Pareto', 'more', '7'),"+
                " (98, 2, 90, '损失原因', 'true', 'reason', 'more', '8'),"+
                " (99, 1, 0, '仓管', 'true', '/warehouse', 'guanli', '9951'),"+
                " (100, 2, 99, '仓库管理', 'true', 'Warehouses', 'more', '1'),"+
                " (101, 2, 99, '区域管理', 'true', 'Area', 'more', '2'),"+
                " (102, 2, 99, '库区管理', 'true', 'ReseviorArea', 'more', '3'),"+
                " (103, 2, 99, '库位管理', 'true', 'Location', 'more', '4'),"+
                " (104, 2, 99, '审批流程', 'true', 'ProcessApproval', 'more', '5'),"+
                " (105, 2, 99, '物料条码规则', 'true', 'MaterialBarCodeRule', 'more', '6'),"+
                " (106, 2, 99, '物料库存', 'true', 'MaterialNumber', 'more', '7'),"+
                " (107, 2, 99, '库存详情', 'true', 'StorageDetail', 'more', '8'),"+
                " (108, 2, 99, '项目管理', 'true', 'Project', 'more', '9'),"+
                " (109, 2, 99, '项目类型管理', 'true', 'ProjectType', 'more', '10'),"+
                " (110, 2, 99, '待审批管理', 'true', 'Approval', 'more', '11'),"+
                " (111, 2, 99, '领用出库', 'true', 'LeadOut', 'more', '12'),"+
                " (112, 2, 99, '出库队列', 'true', 'OutTaskqueue', 'more', '13'),"+
                " (113, 2, 99, '入库管理', 'true', 'Warehousing', 'more', '14'),"+
                " (114, 2, 99, '入库队列', 'true', 'InTaskqueue', 'more', '15'),"+
                " (115, 2, 99, '加料队列', 'true', 'ChargingTaskQueue', 'more', '16'),"+
                " (116, 2, 99, 'K3入库信息', 'true', 'K3ImportNotifydetail', 'more', '18'),"+
                " (117, 2, 99, 'K3出库信息', 'true', 'K3ExportNotifydetail', 'more', '19'),"+
                " (119, 2, 99, '库存盘点', 'true', 'StockInventory', 'more', '20'),"+
                " (120, 2, 99, '托盘移库', 'true', 'TrayMoveLocation', 'more', '21'),"+
                " (121, 2, 99, '托盘管理', 'true', 'TrayManagement', 'more', '22'),"+
                " (122, 2, 99, '审批结果', 'true', 'ApprovalResult', 'more', '23'),"+
                " (123, 1, 0, '告警通知', 'true', '/alarm', 'guanli', '9970'),"+
                " (124, 2, 123, '通知渠道', 'true', 'notificationChannels', 'more', '1'),"+
                " (125, 2, 123, '通知方式', 'true', 'notificationMethod', 'more', '2'),"+
                " (126, 2, 123, '邮箱服务', 'true', 'email', 'more', '3'),"+
                " (127, 2, 123, '短信服务', 'true', 'shortMessage', 'more', '4'),"+
                " (128, 2, 123, '授权Token', 'true', 'authorizationToken', 'more', '5'),"+
                " (129, 2, 123, '策略', 'true', 'strategy', 'more', '6'),"+
                " (130, 1, 0, '报表', 'true', '/report', 'baobiao', '9980'),"+
                " (131, 2, 130, '电子报表', 'true', 'eReport', 'more', '1'),"+
                " (132, 2, 130, '数据报表', 'true', 'dataReport', 'more', '2'),"+
                " (133, 2, 130, '模组报表', 'true', 'modeReport', 'more', '3'),"+
                " (134, 2, 130, '月产量统计', 'true', 'monthOutput', 'more', '4'),"+
                " (135, 2, 130, '日产量统计', 'true', 'dayOutput', 'more', '5'),"+
                " (136, 2, 130, '设备使用率', 'true', 'equipmentUse', 'more', '6'),"+
                " (137, 2, 130, '设备OEE', 'true', 'equipmentOEE', 'more', '7'),"+
                " (138, 2, 130, '拧紧合格率', 'true', 'screwDown', 'more', '8'),"+
                " (139, 2, 130, '工位完成数量统计', 'true', 'completeStatistic', 'more', '9'),"+
                " (140, 2, 130, '班次数量统计', 'true', 'shiftStatistic', 'more', '10'),"+
                " (141, 2, 130, '整体合格率统计', 'true', 'passRateStatistic', 'more', '11'),"+
                " (142, 2, 130, '工位时间统计', 'true', 'timeStatistic', 'more', '12'),"+
                " (143, 2, 130, '节拍统计', 'true', 'beatStatistic', 'more', '13'),"+
                " (144, 2, 130, '一次通过率统计', 'true', 'onePassRate', 'more', '14'),"+
                " (145, 2, 130, 'SPC统计', 'true', 'SPCStatistic', 'more', '15'),"+
                " (146, 3, 72, '工单管理', 'true', 'workOrderManagement', 'more', '1'),"+
                " (150, 2, 167, '订单', 'true', '/order', 'more', '1'),"+
                " (151, 3, 150, '订单管理', 'true', '/orderManagement', 'more', '10'),"+
                " (152, 2, 167, '排产', 'true', '/orderScheduling', 'more', '2'),"+
                " (153, 3, 150, '订单审核', 'true', '/orderExamine', 'more', '15'),"+
                " (154, 2, 10, '事件管理', 'true', '/eventManage', 'more', '40'),"+
                " (155, 3, 64, '产品管理', 'true', 'productManager', 'more', '14'),"+
                " (156, 3, 64, 'BOM管理', 'true', 'materialSheetManage', 'more', '15'),"+
                " (157, 2, 99, '队列维护', 'true', 'Taskqueue', 'more', '17'),"+
                " (158, 3, 59, '设备资料', 'true', 'EquipmentInformation', 'more', '1'),"+
                " (159, 2, 9, '模拟', 'true', 'simulation', 'more', '9'),"+
                " (160, 1, 0, '看板', 'true', '/kanban', 'monitor', '9960'),"+
                " (162, 2, 167, '采购', 'true', '/purchase', 'more', '3'),"+
                " (163, 3, 162, '采购管理', 'true', '/purchaseManagement', 'more', '10'),"+
                " (164, 3, 162, '采购审核', 'true', '/purchaseExamine', 'more', '20'),"+
                " (166, 1, 0, '人员', 'true', '/hr', 'shebei', '9940'),"+
                " (167, 1, 0, '计划', 'true', '/plan', 'shebei', '9880'),"+
                " (169, 1, 0, '生产', 'false', '/production', 'shebei', '9890'),"+
                " (170, 2, 2, '集团建模', 'true', 'groupModeling', 'more', '4'),"+
                " (171, 2, 167, '库存计划', 'true', '/entiretyPlanManage', 'more', '4'),"+
                " (177, 2, 160, '生产看板1', 'true', '/kanban/kanban/index?id=6', 'more', '9986'),"+
                " (178, 2, 160, '生产看板2', 'true', '/kanban/kanban/index?id=7', 'more', '9987'),"+
                " (184, 3, 64, '标题信息', 'true', 'titleInformation', 'more', '11'),"+
                " (186, 3, 170, '公司定义', 'true', 'company', 'more', '1'),"+
                " (187, 3, 170, '工厂定义', 'true', 'factory', 'more', '2'),"+
                " (188, 3, 170, '区域定义', 'true', 'area', 'more', '3'),"+
                " (189, 3, 170, '车间定义', 'true', 'plant', 'more', '4'),"+
                " (190, 2, 9, '生产信息', 'true', 'information', 'more', '10'),"+
                " (191, 2, 80, '不良原因', 'true', 'badReason', 'more', '5'),"+
                " (192, 2, 99, '3d仓库', 'true', '3dWarehouse', 'more', '2');\n"
        }));

        list.add(new DBUpgrader("6.11", "6.13", new String[]{
                //添加字段
                "ALTER TABLE c_mes_shifts_team_t ADD EMP_TEAM_ID int(11) DEFAULT NULL COMMENT '班组id';\n",
                //更新菜单
                " INSERT INTO `c_qh_menu` VALUES (194, 2, 2, '自定义属性', 'true', 'customAttributes', 'more', '8'),\n"+
                        " (195, 2, 9, '生产日志', 'true', 'productionLog', 'more', '11'),\n" +
                        " (196, 2, 80, '生产质量管理', 'true', 'productionQuality', 'more', '6'),\n" +
                        " (197, 3, 196, '自检记录', 'true', 'selfIndex', 'more', '5'),\n" +
                        " (198, 1, 0, '看板V2', 'true', '/kanban2', 'monitor', '9961'),\n" +
                        " (199, 3, 196, '自检清单', 'true', 'selfCheckList', 'more', '1'),\n" +
                        " (200, 3, 196, '首检清单', 'true', 'firstCheckList', 'more', '2'),\n" +
                        " (201, 3, 196, '巡检清单', 'true', 'inspectionChecklist', 'more', '3'),\n" +
                        " (202, 3, 196, '尾检清单', 'true', 'tailCheckList', 'more', '4'),\n" +
                        " (203, 1, 0, '日志', 'true', '/log', 'moban', '9975'),\n" +
                        " (204, 2, 203, '操作日志', 'true', 'operationLog', 'more', '10'),\n" +
                        " (205, 2, 59, '设备点检', 'true', 'equipmentCheck', 'more', '2'),\n" +
                        " (206, 2, 59, '设备保养', 'true', 'equipmentMaintain', 'more', '3'),\n" +
                        " (207, 2, 59, '设备维修', 'true', 'equipmentRepair', 'more', '4'),\n" +
                        " (208, 2, 130, '月产量统计v2', 'true', 'monthOutput2', 'more', '0'),\n" +
                        " (209, 2, 130, '日产量统计v2', 'true', 'dayOutput2', 'more', '0'),\n" +
                        " (210, 3, 196, '首检记录', 'true', 'firstIndex', 'more', '6'),\n" +
                        " (211, 3, 196, '巡检记录', 'true', 'inspectionIndex', 'more', '7'),\n" +
                        " (212, 3, 196, '尾检记录', 'true', 'tailIndex', 'more', '8'),\n" +
                        " (213, 2, 59, '设备点检记录', 'true', 'equipmentCheckData', 'more', '5'),\n" +
                        " (214, 2, 59, '设备保养记录', 'true', 'equipmentMaintainData', 'more', '6'),\n" +
                        " (215, 2, 9, '生产模拟', 'true', 'productionSimulation', 'more', '12'),\n" +
                        " (216, 3, 196, '待处理记录', 'true', 'pending', 'more', '9'),\n" +
                        " (217, 3, 196, '已处理记录', 'true', 'processed', 'more', '10');\n",
                //增加列
                "ALTER TABLE" +
                        "  c_mes_line_t" +
                        "  ADD" +
                        "  `companyCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '所属公司',\n" +
                        "  ADD" +
                        "  `factoryCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '所属工厂',\n" +
                        "  ADD" +
                        "  `areaCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '所属区域',\n" +
                        "  ADD" +
                        "  `plantCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '所属车间';",
                //增加列
                "ALTER TABLE" +
                        "  c_mes_station_t" +
                        "  ADD" +
                        "  `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '工控机ip',\n" +
                        "  ADD" +
                        "  `userName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '工控机系统登录名',\n" +
                        "  ADD" +
                        "  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '工控机系统登录密码';",
        }));

        list.add(new DBUpgrader("6.13", "6.14", new String[]{
                //创建表
                "CREATE TABLE c_mes_operation_log_t (\n" +
                        "  id int(11) NOT NULL AUTO_INCREMENT COMMENT '操作日志id',\n" +
                        "  username varchar(20) DEFAULT NULL COMMENT '操作人',\n" +
                        "  module varchar(30) DEFAULT NULL COMMENT '一级模块',\n" +
                        "  module2 varchar(30) DEFAULT NULL COMMENT '二级模块',\n" +
                        "  methods varchar(50) DEFAULT NULL COMMENT '执行方法',\n" +
                        "  content varchar(255) DEFAULT NULL COMMENT '操作内容',\n" +
                        "  actionurl varchar(50) DEFAULT NULL COMMENT '请求路径',\n" +
                        "  ip varchar(50) DEFAULT NULL COMMENT 'IP地址',\n" +
                        "  dt datetime DEFAULT NULL COMMENT '操作时间',\n" +
                        "  commite tinyint(2) DEFAULT NULL COMMENT '执行描述（1:执行成功、2:执行失败）',\n" +
                        "  PRIMARY KEY (id)\n" +
                        ") ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='操作日志';\n",

                        //创建表
                        "DROP TABLE IF EXISTS c_mes_inspection_checklist_t;\n" ,
                        "CREATE TABLE c_mes_inspection_checklist_t  (\n" +
                        "ID int(11) NOT NULL AUTO_INCREMENT COMMENT '质检清单ID',\n" +
                        "NAME varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '清单名称',\n" +
                        "CODE varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '清单编号',\n" +
                        "PRODUCE_TYPE varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '产品型号',\n" +
                        "DT datetime(0) NULL DEFAULT NULL COMMENT '创建时间',\n" +
                        "TYPE varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '清单类型（自检，首检，巡检，尾检）1，2，3，4',\n" +
                        "  PRIMARY KEY (ID) USING BTREE\n" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 62 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '质检清单表' ROW_FORMAT = Dynamic;\n" ,
                        "SET FOREIGN_KEY_CHECKS = 1;\n",
                        //创建表
                        "DROP TABLE IF EXISTS c_mes_inspection_checklist_detail_t;\n" ,
                        "CREATE TABLE c_mes_inspection_checklist_detail_t  (\n" +
                        "ID int(11) NOT NULL AUTO_INCREMENT COMMENT '质检清单版本迭代ID',\n" +
                        "DT datetime(0) NULL DEFAULT NULL COMMENT '发布时间',\n" +
                        "VERSIONS varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '版本',\n" +
                        "START varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '当前状态（关闭，启用  0，1）',\n" +
                        "CONTENT text CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '检查内容',\n" +
                        "CHECK_LIST_CODE varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '质检清单编号',\n" +
                        "  PRIMARY KEY (ID) USING BTREE\n" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 71 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '质检清单版本迭代表' ROW_FORMAT = Dynamic;\n" ,
                        "SET FOREIGN_KEY_CHECKS = 1;\n"
        }));

        list.add(new DBUpgrader("6.14","6.15",new String[]{
                //创建表
                "DROP TABLE IF EXISTS c_mes_quality_inspection_record;\n",

                        "CREATE TABLE c_mes_quality_inspection_record (\n" +
                        "ID int(11) NOT NULL AUTO_INCREMENT COMMENT '质检记录ID',\n" +
                        "CODE varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '质检记录编号',\n" +
                        "SN varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '序列号',\n" +
                        "TYPE varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '类型',\n" +
                        "START varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '状态(\\'OK\\',\\'NG\\')',\n" +
                        "QC_PERSONNEL varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '质检人',\n" +
                        "DT datetime(0) NULL DEFAULT NULL COMMENT '质检时间',\n" +
                        "QC_LEAD varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '质检组长',\n" +
                        "RESULT_LEAD varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '组长检查结果',\n" +
                        "DT_LEAD datetime(0) NULL DEFAULT NULL COMMENT '组长检查时间',\n" +
                        "PROCESSING_TYPE varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '是否处理  （0待处理，1已处理）',\n" +
                        "PROCESS_TYPE varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '已处理类型（0合格，1返厂，2报废）',\n" +
                        "  PRIMARY KEY (ID) USING BTREE\n" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '质检记录表' ROW_FORMAT = Dynamic;\n" ,
                        "SET FOREIGN_KEY_CHECKS = 1;",
                        //创建表
                        "DROP TABLE IF EXISTS c_mes_check_content;\n",
                        "CREATE TABLE c_mes_check_content  (\n" +
                        "ID int(11) NOT NULL AUTO_INCREMENT COMMENT '检查内容ID',\n" +
                        "STANDARD_NAME varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '内容标准名称',\n" +
                        "STANDARD_VALUE varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '内容标准值',\n" +
                        "RESULT varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '检查内容结果',\n" +
                        "CHECK_LIST_CODE varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '质检记录编号',\n" +
                        "ELIGIBILITY varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '是否合格（OK/NG）',\n" +
                        "RESULT_LEAD varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '组长检查结果',\n" +
                        "ELIGIBILITY_LEAD varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '组长检查是否合格（OK/NG)',\n" +
                        "  PRIMARY KEY (ID) USING BTREE\n" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 88 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '质检记录检查内容表' ROW_FORMAT = Dynamic;\n" ,
                        "SET FOREIGN_KEY_CHECKS = 1;",
                        //修改列
                        "alter table c_mes_material_list_t modify column LIST_VERSION varchar(255);",
                        //修改列
                        "ALTER TABLE  c_mes_operation_log_t MODIFY COLUMN actionurl VARCHAR(255);"
        }));

        list.add(new DBUpgrader("6.15","6.16",new String[]{
                //创建表
                "CREATE TABLE c_qh_equipment_check_config_t (\n" +
                        " id int(11) NOT NULL AUTO_INCREMENT,\n" +
                        " dt datetime DEFAULT NULL COMMENT '更新时间',\n" +
                        " name varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '点检名 ',\n" +
                        " code varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '点检编号',\n" +
                        " equipmentId int(11) DEFAULT NULL COMMENT '设备 id',\n" +
                        " `explain` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '说明 ',\n" +
                        " editionId int(11) DEFAULT NULL COMMENT '版本表id',\n" +
                        " PRIMARY KEY (id) USING BTREE\n" +
                        ") ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='设备点检配置 ';\n",
                //创建表
                "CREATE TABLE c_qh_equipment_check_edition_t (\n" +
                        " id int(11) NOT NULL AUTO_INCREMENT,\n" +
                        " edition varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '版本号',\n" +
                        " dt datetime DEFAULT NULL COMMENT '创建时间',\n" +
                        " state int(11) DEFAULT NULL COMMENT '状态1启用2停用',\n" +
                        " equipmentId int(11) DEFAULT NULL COMMENT '设备id',\n" +
                        " PRIMARY KEY (id) USING BTREE\n" +
                        ") ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='设备点检等级';\n",
                //创建表
                "CREATE TABLE c_qh_equipment_check_items_t (\n" +
                        " id int(11) NOT NULL AUTO_INCREMENT,\n" +
                        " checkItems varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '点检项',\n" +
                        " `explain` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '说明 ',\n" +
                        " checkConfigId int(11) DEFAULT NULL COMMENT '设备点检配置id',\n" +
                        " content varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '点检内容',\n" +
                        " editionId int(11) DEFAULT NULL COMMENT '版本id',\n" +
                        " PRIMARY KEY (id) USING BTREE\n" +
                        ") ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='设备点检项';\n",
                //创建表
                "CREATE TABLE c_qh_equipment_check_data_t (\n" +
                        " id int(11) NOT NULL AUTO_INCREMENT,\n" +
                        " dt datetime DEFAULT NULL COMMENT '点检时间',\n" +
                        " equipmentName varchar(11) COLLATE utf8_bin DEFAULT NULL COMMENT '设备名称',\n" +
                        " lineName varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '产线名称',\n" +
                        " checkPerson varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '点检人',\n" +
                        " remarks varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',\n" +
                        " PRIMARY KEY (id)\n" +
                        ") ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='设备点检记录';\n",
                //创建表
                "CREATE TABLE c_qh_equipment_check_data_detailed_t (\n" +
                        " id int(11) NOT NULL AUTO_INCREMENT,\n" +
                        " dt datetime DEFAULT NULL COMMENT '点检时间',\n" +
                        " item varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '点检项',\n" +
                        " `explain` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '点检说明',\n" +
                        " result varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '点检结果',\n" +
                        " remarks varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',\n" +
                        " checkDataId int(11) DEFAULT NULL COMMENT '点检记录id',\n" +
                        " PRIMARY KEY (id)\n" +
                        ") ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='设备点检记录明细';\n",
                //创建表
                "CREATE TABLE c_qh_equipment_maintain_config_t (\n" +
                        " id int(11) NOT NULL AUTO_INCREMENT,\n" +
                        " dt datetime DEFAULT NULL COMMENT '更新时间',\n" +
                        " name varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '名称',\n" +
                        " code varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '编号',\n" +
                        " equipmentId int(11) DEFAULT NULL COMMENT '设备 id',\n" +
                        " `explain` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '说明 ',\n" +
                        " PRIMARY KEY (id)\n" +
                        ") ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='设备保养配置 ';\n",
                //创建表
                "CREATE TABLE c_qh_equipment_maintain_edition_t (\n" +
                        " id int(11) NOT NULL AUTO_INCREMENT,\n" +
                        " edition varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '版本号',\n" +
                        " dt datetime DEFAULT NULL COMMENT '创建时间',\n" +
                        " state int(11) DEFAULT NULL COMMENT '状态1启用2停用',\n" +
                        " equipmentId int(11) DEFAULT NULL COMMENT '设备id',\n" +
                        " PRIMARY KEY (id)\n" +
                        ") ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='设备保养等级';\n",
                //创建表
                "CREATE TABLE c_qh_equipment_maintain_items_t (\n" +
                        " id int(11) NOT NULL AUTO_INCREMENT,\n" +
                        " item varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '项目',\n" +
                        " `explain` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '说明 ',\n" +
                        " editionId int(11) DEFAULT NULL COMMENT '版本id',\n" +
                        " PRIMARY KEY (id)\n" +
                        ") ENGINE=InnoDB AUTO_INCREMENT=115 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='设备保养项';\n",
                //创建表
                "CREATE TABLE c_qh_equipment_maintain_data_t (\n" +
                        " id int(11) NOT NULL AUTO_INCREMENT,\n" +
                        " dt datetime DEFAULT NULL COMMENT '保养时间',\n" +
                        " equipmentName varchar(11) COLLATE utf8_bin DEFAULT NULL COMMENT '设备名称',\n" +
                        " lineName varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '产线名称',\n" +
                        " checkPerson varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '保养人',\n" +
                        " remarks varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',\n" +
                        " PRIMARY KEY (id)\n" +
                        ") ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='设备保养记录';\n",
                //创建表
                "CREATE TABLE c_qh_equipment_maintain_data_detailed_t (\n" +
                        " id int(11) NOT NULL AUTO_INCREMENT,\n" +
                        " dt datetime DEFAULT NULL COMMENT '保养时间',\n" +
                        " item varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '保养项',\n" +
                        " `explain` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '保养说明',\n" +
                        " result varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '保养结果',\n" +
                        " remarks varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',\n" +
                        " checkDataId int(11) DEFAULT NULL COMMENT '保养记录id',\n" +
                        " PRIMARY KEY (id)\n" +
                        ") ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='设备保养记录明细';\n",
                //创建表
                "CREATE TABLE c_mes_operation_log_t (\n" +
                        " id int(11) NOT NULL AUTO_INCREMENT COMMENT '操作日志id',\n" +
                        " username varchar(20) DEFAULT NULL COMMENT '操作人',\n" +
                        " module varchar(30) DEFAULT NULL COMMENT '一级模块',\n" +
                        " module2 varchar(30) DEFAULT NULL COMMENT '二级模块',\n" +
                        " methods varchar(50) DEFAULT NULL COMMENT '执行方法',\n" +
                        " content longtext DEFAULT NULL COMMENT '操作内容',\n" +
                        " actionurl varchar(255) DEFAULT NULL,\n" +
                        " ip varchar(50) DEFAULT NULL COMMENT 'IP地址',\n" +
                        " dt datetime DEFAULT NULL COMMENT '操作时间',\n" +
                        " commite tinyint(4) DEFAULT NULL COMMENT '执行描述（1:执行成功、2:执行失败）',\n" +
                        " PRIMARY KEY (id) USING BTREE\n" +
                        ") ENGINE=InnoDB AUTO_INCREMENT=538 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='操作日志';",
                //创建表
                "CREATE TABLE c_mes_error_log_t (\n" +
                        " id int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',\n" +
                        " dt datetime DEFAULT NULL COMMENT '报错时间',\n" +
                        " errorMsg varchar(1000) COLLATE utf8_bin DEFAULT NULL COMMENT '报错信息',\n" +
                        " username varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '操作用户',\n" +
                        " ip varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT 'IP地址',\n" +
                        " PRIMARY KEY (id) USING BTREE\n" +
                        ") ENGINE=InnoDB AUTO_INCREMENT=341 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='错误日志';",
                //创建表
                "CREATE TABLE c_mes_visit_log_t (\n" +
                        " id int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',\n" +
                        " username varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '用户名',\n" +
                        " dt datetime DEFAULT NULL COMMENT '访问时间',\n" +
                        " pageName varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '访问页面',\n" +
                        " ip varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT 'IP地址',\n" +
                        " PRIMARY KEY (id) USING BTREE\n" +
                        ") ENGINE=InnoDB AUTO_INCREMENT=4109 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='访问日志';",

                //添加默认值
                "INSERT INTO mes417.c_mes_system(id, name, parameter, dis) VALUES (1, '系统.超级管理员密码', '123456', NULL);",
                "INSERT INTO mes417.c_mes_system(id, name, parameter, dis) VALUES (2, '系统.版本', '3.1', NULL);",
                "INSERT INTO mes417.c_mes_system(id, name, parameter, dis) VALUES (3, '仓管.是否立库', '2', '1：是、2：否');",
                "INSERT INTO mes417.c_mes_system(id, name, parameter, dis) VALUES (4, '仓管.是否需要审批', '1', '1：是、2：否');",
                "INSERT INTO mes417.c_mes_system(id, name, parameter, dis) VALUES (5, '通用.项目', '琦航', '1:立库、XTxxx：项目');",
                //添加默认值
                "INSERT INTO qihang.c_mes_material_type_t(ID, MATERIAL_TYPE, DIS, DT) VALUES (4, '原料', '生产所需原料', '2020-11-24 10:44:46');\n",
                "INSERT INTO qihang.c_mes_material_type_t(ID, MATERIAL_TYPE, DIS, DT) VALUES (5, '半成品', '生产所需材料', '2020-11-24 10:45:42');\n",
                "INSERT INTO qihang.c_mes_material_type_t(ID, MATERIAL_TYPE, DIS, DT) VALUES (6, '成品', '生产产品', '2020-11-24 10:46:19');\n",
                "INSERT INTO qihang.c_mes_material_type_t(ID, MATERIAL_TYPE, DIS, DT) VALUES (7, '设备', '生产所需设备', '2020-11-24 10:46:37');\n",
                "INSERT INTO qihang.c_mes_material_type_t(ID, MATERIAL_TYPE, DIS, DT) VALUES (8, '设备配件', '生产所需设备配件', '2020-11-24 10:46:54');",

                //添加列
                "ALTER TABLE" +
                        "  c_mes_emp_t" +
                        "  ADD" +
                        "  `IS_WHOLE` int(11) DEFAULT NULL COMMENT '是否全班',\n" +
                        "  ADD" +
                        "  `password` varchar(255) DEFAULT NULL COMMENT '密码';",
                //添加默认标题
                "INSERT INTO `qihang`.`c_mes_title_name_t`(`id`, `dt`, `name`) VALUES (1, '2021-01-26 16:36:19', '琦航数字工厂信息系统');\n",
                //添加列
                "ALTER TABLE\n" +
                        "         c_qh_equipment_information\n" +
                        "         ADD\n" +
                        "spotCheckCycle int(11) DEFAULT NULL COMMENT '点检周期',\n" +
                        "ADD\n" +
                        " lastSpotCheckDate datetime DEFAULT NULL COMMENT '上次点检日期',\n" +
                        "ADD\n" +
                        " nextSpotCheckDate datetime DEFAULT NULL COMMENT '下次点检日期';",
                //添加表
                "CREATE TABLE `c_qh_equipment_repair_t`  (\n" +
                        "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                        "  `dt` datetime(0) NULL DEFAULT NULL COMMENT '维修时间',\n" +
                        "  `equipmentId` int(11) NULL DEFAULT NULL COMMENT '设备id',\n" +
                        "  `repairman` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '维修人',\n" +
                        "  `personInCharge` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '负责人 ',\n" +
                        "  `reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '维修原因',\n" +
                        "  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备注',\n" +
                        "  PRIMARY KEY (`id`) USING BTREE\n" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '设备维修记录' ROW_FORMAT = Dynamic;\n",
                //添加列
                "ALTER TABLE\n" +
                        "  c_qh_equipment_check_data_t\n" +
                        "  ADD\n" +
                        "`state` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '状态';",
                //添加列
                "ALTER TABLE\n" +
                        "  c_qh_equipment_maintain_data_t\n" +
                        "  ADD\n" +
                        "`state` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '状态';",
        }));

        list.add(new DBUpgrader("6.16","6.17",new String[]{
                //添加列
                "ALTER TABLE" +
                        "  c_mes_code_rule_t" +
                        "  ADD" +
                        "  `CODE_RULE_PREFIX` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编号规则前缀',\n" +
                        "  ADD" +
                        "  `CODE_RULE_SUFFIX` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编号规则后缀',\n" +
                        "  ADD" +
                        "  `CODE_RULE_VALUE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最新编号规则value',\n" +
                        "  ADD" +
                        "  `CODE_RULE_SUFFIX_VALUE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最新编号规则后缀value',\n" +
                        "  ADD" +
                        "  `EXPLAIN` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '规则说明',\n" +
                        "  ADD" +
                        "  `DELETE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '逻辑删除（0未删除，1已删除)';",
                //默认值
                    "INSERT INTO `c_mes_code_rule_t` VALUES (1, '2021-01-15 14:30:14', '2021-01-15 14:30:17', 'WO', 'yyyyMMdd', '######', '工单编号', '', '', '', '', '0');",
                "INSERT INTO `c_mes_code_rule_t` VALUES (2, '2021-03-23 16:32:00', '2021-03-23 16:32:02', 'MAT', 'yyyyMMdd', '###', '物料编号', '', '', '', '', '0');",

                "INSERT INTO `c_mes_code_rule_t`(`ID`, `DT`, `ALTER_DT`, `CODE_RULE_PREFIX`, `CODE_RULE`, `CODE_RULE_SUFFIX`, `CODE_NAME`, `LAST_CODE`, `CODE_RULE_VALUE`, `CODE_RULE_SUFFIX_VALUE`, `EXPLAIN`, `DELETE`) " +
                        "VALUES (3, '2021-03-23 16:32:00', '2021-03-23 16:32:02', 'BATCH', 'yyyyMMdd', '###', '物料批次映射', '', '', '', '', '0');",

                "INSERT INTO `c_mes_code_rule_t`(`ID`, `DT`, `ALTER_DT`, `CODE_RULE_PREFIX`, `CODE_RULE`, `CODE_RULE_SUFFIX`, `CODE_NAME`, `LAST_CODE`, `CODE_RULE_VALUE`, `CODE_RULE_SUFFIX_VALUE`, `EXPLAIN`, `DELETE`) " +
                        "VALUES (4, '2021-03-23 16:32:00', '2021-03-23 16:32:02', 'CODE', 'yyyyMMdd', '###', '物料编号映射', '', '', '', '', '0');",
                //添加列
                "ALTER TABLE\n" +
                        "p_mes_keypart_t\n" +
                        "ADD\n" +
                        "'STEP' varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '步序';\n",
                //添加列
                "ALTER TABLE\n" +
                        "r_mes_keypart_t\n" +
                        "ADD\n" +
                        "'STEP' varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '步序';\n",

                //添加列
                "ALTER TABLE\n" +
                        "p_mes_bolt_t\n" +
                        "ADD\n" +
                        "'STEP' varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '步序';\n",

                //添加列
                "ALTER TABLE\n" +
                        "r_mes_bolt_t\n" +
                        "ADD\n" +
                        "'STEP' varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '步序';\n",

                //添加列
                "ALTER TABLE\n" +
                        "p_mes_leakage_t\n" +
                        "ADD\n" +
                        "'STEP' varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '步序';\n",

                //添加列
                "ALTER TABLE\n" +
                        "r_mes_leakage_t\n" +
                        "ADD\n" +
                        "'STEP' varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '步序';\n",

                //添加列
                "ALTER TABLE\n" +
                        "p_mes_tracking_t\n" +
                        "ADD\n" +
                        "'reason 'varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'NG原因';\n",

                //添加列
                "ALTER TABLE\n" +
                        "r_mes_tracking_t\n" +
                        "ADD\n" +
                        "'reason 'varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'NG原因';",

                //添加列
                "alter table c_mes_operation_log_t add(\n" +
                        " params varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '请求参数',\n" +
                        " apiAnnotation varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'API注释'\n" +
                        ");",
                //新增表
                "CREATE TABLE c_mes_api_dictionaries_t (\n" +
                        " actionurl varchar(100) NOT NULL COMMENT '请求路径',\n" +
                        " commitType varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'http请求类型(GET|POST)',\n" +
                        " paramsType varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '参数类型(FORM|JSON)',\n" +
                        " PRIMARY KEY (actionurl) USING BTREE\n" +
                        ") ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='api操作字典表';",
        }));

        list.add(new DBUpgrader("6.17","6.18",new String[]{
                //添加列
                "ALTER TABLE\n" +
                        "c_mes_material_batch_mapping\n" +
                        "ADD (\n" +
                        "`supplier_material_code `varchar(255) DEFAULT NULL COMMENT '供应商物料编码',\n" +
                        " `material_code `varchar(255) DEFAULT NULL COMMENT '物料编码');",
                //添加菜单列
                "INSERT INTO c_qh_menu(id, menu_grade, superior_menu_id, menu_name, if_enable, path, icon, order) VALUES (241, 3, 219, '批次生成规则', 'true', 'batchGenerationRule', 'more', '3');"
        }));

        return list;
    }
}
