/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : qihang1

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 03/09/2021 14:30:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`  (
  `config_id` int NOT NULL AUTO_INCREMENT COMMENT '参数主键',
  `config_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '参数名称',
  `config_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '参数键名',
  `config_value` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '参数键值',
  `config_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`config_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '参数配置表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES (1, '默认皮肤样式名称', 'sys.index.skinName', 'skin-blue', 'Y', 'admin', '2021-07-06 16:53:37', 'admin', '2021-07-10 12:28:55', '蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red');
INSERT INTO `sys_config` VALUES (2, '用户管理-账号初始密码', 'sys.user.initPassword', '123456', 'Y', 'admin', '2021-07-06 16:53:37', '', NULL, '初始化密码 123456');
INSERT INTO `sys_config` VALUES (3, '侧边栏主题', 'sys.index.sideTheme', 'theme-light', 'N', 'admin', '2021-07-06 16:53:37', 'admin', '2021-07-09 15:40:06', '深色主题theme-dark，浅色主题theme-light');
INSERT INTO `sys_config` VALUES (4, ' SRM用户未认证权限', 'sys.user.srmUnverified', '3', 'Y', 'admin', '2021-09-03 14:33:57', 'admin', '2021-09-03 14:38:54', 'SRM用户未认证权限');
INSERT INTO `sys_config` VALUES (5, 'SRM用户已认证权限', 'sys.user.srmAuthenticated', '4', 'Y', 'admin', '2021-09-03 14:44:35', 'admin', '2021-09-03 14:54:52', 'SRM用户已认证权限');
INSERT INTO `sys_config` VALUES (6, 'SRM推送K3服务凭证', 'sys.k3Push.serviceType', '10000', 'Y', 'admin', '2021-09-03 14:49:43', 'admin', '2021-09-03 14:54:55', 'SRM推送K3服务凭证');
INSERT INTO `sys_config` VALUES (7, '供应商部门id', 'sys.supplier.departmentId', '113', 'Y', 'admin', '2021-09-03 14:50:28', 'admin', '2021-09-03 14:55:00', '供应商部门id');
INSERT INTO `sys_config` VALUES (8, 'OA审批回调类型通过', 'sys.oa.pass', '通过', 'Y', 'admin', '2021-09-03 14:53:30', 'admin', '2021-09-03 14:53:37', 'OA审批回调类型通过');
INSERT INTO `sys_config` VALUES (9, 'OA审批回调类型驳回', 'sys.oa.reject', '驳回', 'Y', 'admin', '2021-09-03 14:54:01', 'admin', '2021-09-03 14:55:06', 'OA审批回调类型驳回');
INSERT INTO `sys_config` VALUES (10, 'OA审批回调类型撤销', 'sys.oa.reversal', '撤销', 'Y', 'admin', '2021-09-03 14:54:22', 'admin', '2021-09-03 14:55:09', 'OA审批回调类型撤销');
INSERT INTO `sys_config` VALUES (11, '提供服务调用者-K3', 'sys.k3.kThreeUrl', 'http://192.168.7.8:10002/', 'Y', 'admin', '2021-09-03 14:54:43', 'admin', '2021-09-03 15:34:15', '提供服务调用者-K3');

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `dept_id` bigint NOT NULL AUTO_INCREMENT COMMENT '机构id',
  `parent_id` bigint NULL DEFAULT 0 COMMENT '父机构id',
  `ancestors` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '祖级列表',
  `dept_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '2' COMMENT '机构类型 1公司 2部门',
  `dept_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '机构名称',
  `dept_code` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '机构编码',
  `order_num` int NULL DEFAULT 0 COMMENT '显示顺序',
  `leader` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '负责人',
  `leader_id` bigint NULL DEFAULT NULL COMMENT '主管ID',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dept_id`) USING BTREE,
  UNIQUE INDEX `dept_code`(`dept_code`) USING BTREE
) ENGINE = InnoDB  CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '机构表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (100, 0, '0', '0', '集团', '10000', 0, '集团负责人', 200, '18888888888', 'admin@skeqi.com', '0', '0', 'admin', '2021-07-06 16:53:36', 'admin', '2021-07-09 14:08:59');


-- ----------------------------
-- Table structure for sys_dept_rank
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept_rank`;
CREATE TABLE `sys_dept_rank`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增',
  `code` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编码',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '职级名称',
  `level` int NULL DEFAULT NULL COMMENT '等级',
  `create_time` datetime NULL DEFAULT NULL COMMENT '时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `code`(`code`) USING BTREE
) ENGINE = InnoDB  CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '职级表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dept_rank
-- ----------------------------

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data`  (
  `dict_code` bigint NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_sort` int NULL DEFAULT 0 COMMENT '字典排序',
  `dict_label` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '表格回显样式',
  `is_default` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典数据表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
INSERT INTO `sys_dict_data` VALUES (1, 1, '男', '0', 'sys_user_sex', '', '', 'Y', '0', 'admin', '2021-07-06 16:53:37', '', NULL, '性别男');
INSERT INTO `sys_dict_data` VALUES (2, 2, '女', '1', 'sys_user_sex', '', '', 'N', '0', 'admin', '2021-07-06 16:53:37', '', NULL, '性别女');
INSERT INTO `sys_dict_data` VALUES (3, 3, '未知', '2', 'sys_user_sex', '', '', 'N', '0', 'admin', '2021-07-06 16:53:37', '', NULL, '性别未知');
INSERT INTO `sys_dict_data` VALUES (4, 1, '显示', '0', 'sys_show_hide', '', 'primary', 'Y', '0', 'admin', '2021-07-06 16:53:37', '', NULL, '显示菜单');
INSERT INTO `sys_dict_data` VALUES (5, 2, '隐藏', '1', 'sys_show_hide', '', 'danger', 'N', '0', 'admin', '2021-07-06 16:53:37', '', NULL, '隐藏菜单');
INSERT INTO `sys_dict_data` VALUES (6, 1, '正常', '0', 'sys_normal_disable', '', 'primary', 'Y', '0', 'admin', '2021-07-06 16:53:37', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (7, 2, '停用', '1', 'sys_normal_disable', '', 'danger', 'N', '0', 'admin', '2021-07-06 16:53:37', '', NULL, '停用状态');
INSERT INTO `sys_dict_data` VALUES (8, 1, '正常', '0', 'sys_job_status', '', 'primary', 'Y', '0', 'admin', '2021-07-06 16:53:37', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (9, 2, '暂停', '1', 'sys_job_status', '', 'danger', 'N', '0', 'admin', '2021-07-06 16:53:37', '', NULL, '停用状态');
INSERT INTO `sys_dict_data` VALUES (10, 1, '默认', 'DEFAULT', 'sys_job_group', '', '', 'Y', '0', 'admin', '2021-07-06 16:53:37', '', NULL, '默认分组');
INSERT INTO `sys_dict_data` VALUES (11, 2, '系统', 'SYSTEM', 'sys_job_group', '', '', 'N', '0', 'admin', '2021-07-06 16:53:37', '', NULL, '系统分组');
INSERT INTO `sys_dict_data` VALUES (12, 1, '是', 'Y', 'sys_yes_no', '', 'primary', 'Y', '0', 'admin', '2021-07-06 16:53:37', '', NULL, '系统默认是');
INSERT INTO `sys_dict_data` VALUES (13, 2, '否', 'N', 'sys_yes_no', '', 'danger', 'N', '0', 'admin', '2021-07-06 16:53:37', '', NULL, '系统默认否');
INSERT INTO `sys_dict_data` VALUES (14, 1, '通知', '1', 'sys_notice_type', '', 'warning', 'Y', '0', 'admin', '2021-07-06 16:53:37', '', NULL, '通知');
INSERT INTO `sys_dict_data` VALUES (15, 2, '公告', '2', 'sys_notice_type', '', 'success', 'N', '0', 'admin', '2021-07-06 16:53:37', '', NULL, '公告');
INSERT INTO `sys_dict_data` VALUES (16, 1, '正常', '0', 'sys_notice_status', '', 'primary', 'Y', '0', 'admin', '2021-07-06 16:53:37', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (17, 2, '关闭', '1', 'sys_notice_status', '', 'danger', 'N', '0', 'admin', '2021-07-06 16:53:37', '', NULL, '关闭状态');
INSERT INTO `sys_dict_data` VALUES (18, 1, '新增', '1', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2021-07-06 16:53:37', '', NULL, '新增操作');
INSERT INTO `sys_dict_data` VALUES (19, 2, '修改', '2', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2021-07-06 16:53:37', '', NULL, '修改操作');
INSERT INTO `sys_dict_data` VALUES (20, 3, '删除', '3', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2021-07-06 16:53:37', '', NULL, '删除操作');
INSERT INTO `sys_dict_data` VALUES (21, 4, '授权', '4', 'sys_oper_type', '', 'primary', 'N', '0', 'admin', '2021-07-06 16:53:37', '', NULL, '授权操作');
INSERT INTO `sys_dict_data` VALUES (22, 5, '导出', '5', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2021-07-06 16:53:37', '', NULL, '导出操作');
INSERT INTO `sys_dict_data` VALUES (23, 6, '导入', '6', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2021-07-06 16:53:37', '', NULL, '导入操作');
INSERT INTO `sys_dict_data` VALUES (24, 7, '强退', '7', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2021-07-06 16:53:37', '', NULL, '强退操作');
INSERT INTO `sys_dict_data` VALUES (25, 8, '生成代码', '8', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2021-07-06 16:53:37', '', NULL, '生成操作');
INSERT INTO `sys_dict_data` VALUES (26, 9, '清空数据', '9', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2021-07-06 16:53:37', '', NULL, '清空操作');
INSERT INTO `sys_dict_data` VALUES (27, 1, '成功', '0', 'sys_common_status', '', 'primary', 'N', '0', 'admin', '2021-07-06 16:53:37', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (28, 2, '失败', '1', 'sys_common_status', '', 'danger', 'N', '0', 'admin', '2021-07-06 16:53:37', '', NULL, '停用状态');

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type`  (
  `dict_id` bigint NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `dict_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_id`) USING BTREE,
  UNIQUE INDEX `dict_type`(`dict_type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典类型表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type` VALUES (1, '用户性别', 'sys_user_sex', '0', 'admin', '2021-07-06 16:53:37', '', NULL, '用户性别列表');
INSERT INTO `sys_dict_type` VALUES (2, '菜单状态', 'sys_show_hide', '0', 'admin', '2021-07-06 16:53:37', '', NULL, '菜单状态列表');
INSERT INTO `sys_dict_type` VALUES (3, '系统开关', 'sys_normal_disable', '0', 'admin', '2021-07-06 16:53:37', '', NULL, '系统开关列表');
INSERT INTO `sys_dict_type` VALUES (4, '任务状态', 'sys_job_status', '0', 'admin', '2021-07-06 16:53:37', '', NULL, '任务状态列表');
INSERT INTO `sys_dict_type` VALUES (5, '任务分组', 'sys_job_group', '0', 'admin', '2021-07-06 16:53:37', '', NULL, '任务分组列表');
INSERT INTO `sys_dict_type` VALUES (6, '系统是否', 'sys_yes_no', '0', 'admin', '2021-07-06 16:53:37', '', NULL, '系统是否列表');
INSERT INTO `sys_dict_type` VALUES (7, '通知类型', 'sys_notice_type', '0', 'admin', '2021-07-06 16:53:37', '', NULL, '通知类型列表');
INSERT INTO `sys_dict_type` VALUES (8, '通知状态', 'sys_notice_status', '0', 'admin', '2021-07-06 16:53:37', '', NULL, '通知状态列表');
INSERT INTO `sys_dict_type` VALUES (9, '操作类型', 'sys_oper_type', '0', 'admin', '2021-07-06 16:53:37', '', NULL, '操作类型列表');
INSERT INTO `sys_dict_type` VALUES (10, '系统状态', 'sys_common_status', '0', 'admin', '2021-07-06 16:53:37', '', NULL, '登录状态列表');

-- ----------------------------
-- Table structure for sys_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_job`;
CREATE TABLE `sys_job`  (
  `job_id` bigint NOT NULL AUTO_INCREMENT COMMENT '任务ID',
  `job_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '任务名称',
  `job_group` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'DEFAULT' COMMENT '任务组名',
  `invoke_target` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调用目标字符串',
  `cron_expression` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT 'cron执行表达式',
  `misfire_policy` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '3' COMMENT '计划执行错误策略（1立即执行 2执行一次 3放弃执行）',
  `concurrent` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '是否并发执行（0允许 1禁止）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1暂停）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '备注信息',
  PRIMARY KEY (`job_id`, `job_name`, `job_group`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '定时任务调度表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_job
-- ----------------------------

-- ----------------------------
-- Table structure for sys_job_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_job_log`;
CREATE TABLE `sys_job_log`  (
  `job_log_id` bigint NOT NULL AUTO_INCREMENT COMMENT '任务日志ID',
  `job_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务名称',
  `job_group` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务组名',
  `invoke_target` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调用目标字符串',
  `job_message` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '日志信息',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '执行状态（0正常 1失败）',
  `exception_info` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '异常信息',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_log_id`) USING BTREE
) ENGINE = InnoDB  CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '定时任务调度日志表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for sys_logininfor
-- ----------------------------
DROP TABLE IF EXISTS `sys_logininfor`;
CREATE TABLE `sys_logininfor`  (
  `info_id` bigint NOT NULL AUTO_INCREMENT COMMENT '访问ID',
  `user_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '用户账号',
  `ipaddr` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '操作系统',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
  `msg` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '提示消息',
  `login_time` datetime NULL DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`info_id`) USING BTREE
) ENGINE = InnoDB  CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统访问记录' ROW_FORMAT = COMPACT;


-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` bigint NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名称',
  `parent_id` bigint NULL DEFAULT 0 COMMENT '父菜单ID',
  `order_num` int NULL DEFAULT 0 COMMENT '显示顺序',
  `path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组件路径',
  `is_frame` int NULL DEFAULT 1 COMMENT '是否为外链（0是 1否）',
  `is_cache` int NULL DEFAULT 0 COMMENT '是否缓存（0缓存 1不缓存）',
  `menu_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  `perms` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2344 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '系统管理', 0, 1, 'system', NULL, 1, 0, 'M', '0', '0', '', 'xfc_home', 'admin', '2021-07-06 16:53:36', 'admin1', '2021-09-02 15:40:51', '系统管理目录');
INSERT INTO `sys_menu` VALUES (2, '系统监控', 0, 2, 'monitor', NULL, 1, 0, 'M', '0', '0', '', 'monitor', 'admin', '2021-07-06 16:53:36', '', NULL, '系统监控目录');
INSERT INTO `sys_menu` VALUES (3, '财务管理', 0, 3, '/finance', NULL, 1, 0, 'M', '0', '0', NULL, 'dindan', 'admin', '2021-07-19 16:35:27', 'admin1', '2021-09-02 15:40:20', '');
INSERT INTO `sys_menu` VALUES (100, '用户管理', 1, 1, '/org/user', 'system/user/index', 1, 0, 'C', '0', '0', 'system:user:list', 'user', 'admin', '2021-07-06 16:53:36', '', NULL, '用户管理菜单');
INSERT INTO `sys_menu` VALUES (101, '角色管理', 1, 2, '/org/role', 'system/role/index', 1, 0, 'C', '0', '0', 'system:role:list', 'peoples', 'admin', '2021-07-06 16:53:36', '', NULL, '角色管理菜单');
INSERT INTO `sys_menu` VALUES (102, '菜单管理', 1, 5, 'menu', 'system/menu/index', 1, 0, 'C', '0', '0', 'system:menu:list', 'tree-table', 'admin', '2021-07-06 16:53:36', 'admin', '2021-07-10 12:25:13', '菜单管理菜单');
INSERT INTO `sys_menu` VALUES (103, '机构管理', 1, 3, '/org/dept', 'system/dept/index', 1, 0, 'C', '0', '0', 'system:dept:list', 'tree', 'admin', '2021-07-06 16:53:36', 'admin', '2021-07-10 12:24:46', '部门管理菜单');
INSERT INTO `sys_menu` VALUES (104, '岗位管理', 1, 4, '/org/post', 'system/post/index', 1, 0, 'C', '0', '0', 'system:post:list', 'post', 'admin', '2021-07-06 16:53:36', 'admin', '2021-07-10 12:25:45', '岗位管理菜单');
INSERT INTO `sys_menu` VALUES (105, '字典管理', 1, 6, 'dict', 'system/dict/index', 1, 0, 'C', '0', '0', 'system:dict:list', 'dict', 'admin', '2021-07-06 16:53:36', '', NULL, '字典管理菜单');
INSERT INTO `sys_menu` VALUES (106, '参数设置', 1, 7, 'config', 'system/config/index', 1, 0, 'C', '0', '0', 'system:config:list', 'edit', 'admin', '2021-07-06 16:53:36', '', NULL, '参数设置菜单');
INSERT INTO `sys_menu` VALUES (107, '通知公告', 1, 8, 'notice', 'system/notice/index', 1, 0, 'C', '0', '0', 'system:notice:list', 'message', 'admin', '2021-07-06 16:53:36', '', NULL, '通知公告菜单');
INSERT INTO `sys_menu` VALUES (108, '日志管理', 1, 9, 'log', '', 1, 0, 'M', '0', '0', '', 'log', 'admin', '2021-07-06 16:53:36', '', NULL, '日志管理菜单');
INSERT INTO `sys_menu` VALUES (109, '在线用户', 2, 1, 'online', 'monitor/online/index', 1, 0, 'C', '0', '0', 'monitor:online:list', 'online', 'admin', '2021-07-06 16:53:36', '', NULL, '在线用户菜单');
INSERT INTO `sys_menu` VALUES (110, '定时任务', 2, 2, 'job', 'monitor/job/index', 1, 0, 'C', '0', '0', 'monitor:job:list', 'job', 'admin', '2021-07-06 16:53:36', '', NULL, '定时任务菜单');
INSERT INTO `sys_menu` VALUES (111, '数据监控', 2, 3, 'druid', 'monitor/druid/index', 1, 0, 'C', '0', '0', 'monitor:druid:list', 'druid', 'admin', '2021-07-06 16:53:36', '', NULL, '数据监控菜单');
INSERT INTO `sys_menu` VALUES (113, '缓存监控', 2, 5, 'cache', 'monitor/cache/index', 1, 0, 'C', '0', '0', 'monitor:cache:list', 'redis', 'admin', '2021-07-06 16:53:36', '', NULL, '缓存监控菜单');
INSERT INTO `sys_menu` VALUES (117, 'Admin监控', 2, 5, 'Admin', 'monitor/admin/index', 1, 0, 'C', '0', '0', 'monitor:admin:list', 'dashboard', 'admin', '2021-07-06 16:53:36', '', NULL, 'Admin监控菜单');
INSERT INTO `sys_menu` VALUES (500, '操作日志', 108, 1, 'operlog', 'monitor/operlog/index', 1, 0, 'C', '0', '0', 'monitor:operlog:list', 'form', 'admin', '2021-07-06 16:53:36', '', NULL, '操作日志菜单');
INSERT INTO `sys_menu` VALUES (501, '登录日志', 108, 2, 'logininfor', 'monitor/logininfor/index', 1, 0, 'C', '0', '0', 'monitor:logininfor:list', 'logininfor', 'admin', '2021-07-06 16:53:36', '', NULL, '登录日志菜单');
INSERT INTO `sys_menu` VALUES (1001, '用户查询', 100, 1, '', '', 1, 0, 'F', '0', '0', 'system:user:query', '#', 'admin', '2021-07-06 16:53:36', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1002, '用户新增', 100, 2, '', '', 1, 0, 'F', '0', '0', 'system:user:add', '#', 'admin', '2021-07-06 16:53:36', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1003, '用户修改', 100, 3, '', '', 1, 0, 'F', '0', '0', 'system:user:edit', '#', 'admin', '2021-07-06 16:53:36', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1004, '用户删除', 100, 4, '', '', 1, 0, 'F', '0', '0', 'system:user:remove', '#', 'admin', '2021-07-06 16:53:36', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1005, '用户导出', 100, 5, '', '', 1, 0, 'F', '0', '0', 'system:user:export', '#', 'admin', '2021-07-06 16:53:36', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1006, '用户导入', 100, 6, '', '', 1, 0, 'F', '0', '0', 'system:user:import', '#', 'admin', '2021-07-06 16:53:36', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1007, '重置密码', 100, 7, '', '', 1, 0, 'F', '0', '0', 'system:user:resetPwd', '#', 'admin', '2021-07-06 16:53:36', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1008, '角色查询', 101, 1, '', '', 1, 0, 'F', '0', '0', 'system:role:query', '#', 'admin', '2021-07-06 16:53:36', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1009, '角色新增', 101, 2, '', '', 1, 0, 'F', '0', '0', 'system:role:add', '#', 'admin', '2021-07-06 16:53:36', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1010, '角色修改', 101, 3, '', '', 1, 0, 'F', '0', '0', 'system:role:edit', '#', 'admin', '2021-07-06 16:53:36', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1011, '角色删除', 101, 4, '', '', 1, 0, 'F', '0', '0', 'system:role:remove', '#', 'admin', '2021-07-06 16:53:36', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1012, '角色导出', 101, 5, '', '', 1, 0, 'F', '0', '0', 'system:role:export', '#', 'admin', '2021-07-06 16:53:36', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1013, '菜单查询', 102, 1, '', '', 1, 0, 'F', '0', '0', 'system:menu:query', '#', 'admin', '2021-07-06 16:53:36', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1014, '菜单新增', 102, 2, '', '', 1, 0, 'F', '0', '0', 'system:menu:add', '#', 'admin', '2021-07-06 16:53:36', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1015, '菜单修改', 102, 3, '', '', 1, 0, 'F', '0', '0', 'system:menu:edit', '#', 'admin', '2021-07-06 16:53:36', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1016, '菜单删除', 102, 4, '', '', 1, 0, 'F', '0', '0', 'system:menu:remove', '#', 'admin', '2021-07-06 16:53:36', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1017, '机构查询', 103, 1, '', '', 1, 0, 'F', '0', '0', 'system:dept:query', '#', 'admin', '2021-07-06 16:53:36', 'admin', '2021-07-16 14:02:30', '');
INSERT INTO `sys_menu` VALUES (1018, '机构新增', 103, 2, '', '', 1, 0, 'F', '0', '0', 'system:dept:add', '#', 'admin', '2021-07-06 16:53:36', 'admin', '2021-07-16 14:02:37', '');
INSERT INTO `sys_menu` VALUES (1019, '机构修改', 103, 3, '', '', 1, 0, 'F', '0', '0', 'system:dept:edit', '#', 'admin', '2021-07-06 16:53:36', 'admin', '2021-07-16 14:02:44', '');
INSERT INTO `sys_menu` VALUES (1020, '机构删除', 103, 4, '', '', 1, 0, 'F', '0', '0', 'system:dept:remove', '#', 'admin', '2021-07-06 16:53:36', 'admin', '2021-07-16 14:02:51', '');
INSERT INTO `sys_menu` VALUES (1021, '岗位查询', 104, 1, '', '', 1, 0, 'F', '0', '0', 'system:post:query', '#', 'admin', '2021-07-06 16:53:36', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1022, '岗位新增', 104, 2, '', '', 1, 0, 'F', '0', '0', 'system:post:add', '#', 'admin', '2021-07-06 16:53:36', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1023, '岗位修改', 104, 3, '', '', 1, 0, 'F', '0', '0', 'system:post:edit', '#', 'admin', '2021-07-06 16:53:36', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1024, '岗位删除', 104, 4, '', '', 1, 0, 'F', '0', '0', 'system:post:remove', '#', 'admin', '2021-07-06 16:53:36', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1025, '岗位导出', 104, 5, '', '', 1, 0, 'F', '0', '0', 'system:post:export', '#', 'admin', '2021-07-06 16:53:36', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1026, '字典查询', 105, 1, '#', '', 1, 0, 'F', '0', '0', 'system:dict:query', '#', 'admin', '2021-07-06 16:53:36', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1027, '字典新增', 105, 2, '#', '', 1, 0, 'F', '0', '0', 'system:dict:add', '#', 'admin', '2021-07-06 16:53:36', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1028, '字典修改', 105, 3, '#', '', 1, 0, 'F', '0', '0', 'system:dict:edit', '#', 'admin', '2021-07-06 16:53:36', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1029, '字典删除', 105, 4, '#', '', 1, 0, 'F', '0', '0', 'system:dict:remove', '#', 'admin', '2021-07-06 16:53:36', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1030, '字典导出', 105, 5, '#', '', 1, 0, 'F', '0', '0', 'system:dict:export', '#', 'admin', '2021-07-06 16:53:36', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1031, '参数查询', 106, 1, '#', '', 1, 0, 'F', '0', '0', 'system:config:query', '#', 'admin', '2021-07-06 16:53:36', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1032, '参数新增', 106, 2, '#', '', 1, 0, 'F', '0', '0', 'system:config:add', '#', 'admin', '2021-07-06 16:53:36', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1033, '参数修改', 106, 3, '#', '', 1, 0, 'F', '0', '0', 'system:config:edit', '#', 'admin', '2021-07-06 16:53:36', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1034, '参数删除', 106, 4, '#', '', 1, 0, 'F', '0', '0', 'system:config:remove', '#', 'admin', '2021-07-06 16:53:36', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1035, '参数导出', 106, 5, '#', '', 1, 0, 'F', '0', '0', 'system:config:export', '#', 'admin', '2021-07-06 16:53:36', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1036, '公告查询', 107, 1, '#', '', 1, 0, 'F', '0', '0', 'system:notice:query', '#', 'admin', '2021-07-06 16:53:36', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1037, '公告新增', 107, 2, '#', '', 1, 0, 'F', '0', '0', 'system:notice:add', '#', 'admin', '2021-07-06 16:53:36', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1038, '公告修改', 107, 3, '#', '', 1, 0, 'F', '0', '0', 'system:notice:edit', '#', 'admin', '2021-07-06 16:53:36', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1039, '公告删除', 107, 4, '#', '', 1, 0, 'F', '0', '0', 'system:notice:remove', '#', 'admin', '2021-07-06 16:53:36', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1040, '操作查询', 500, 1, '#', '', 1, 0, 'F', '0', '0', 'monitor:operlog:query', '#', 'admin', '2021-07-06 16:53:36', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1041, '操作删除', 500, 2, '#', '', 1, 0, 'F', '0', '0', 'monitor:operlog:remove', '#', 'admin', '2021-07-06 16:53:36', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1042, '日志导出', 500, 4, '#', '', 1, 0, 'F', '0', '0', 'monitor:operlog:export', '#', 'admin', '2021-07-06 16:53:36', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1043, '登录查询', 501, 1, '#', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:query', '#', 'admin', '2021-07-06 16:53:36', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1044, '登录删除', 501, 2, '#', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:remove', '#', 'admin', '2021-07-06 16:53:36', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1045, '日志导出', 501, 3, '#', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:export', '#', 'admin', '2021-07-06 16:53:36', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1046, '在线查询', 109, 1, '#', '', 1, 0, 'F', '0', '0', 'monitor:online:query', '#', 'admin', '2021-07-06 16:53:36', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1047, '批量强退', 109, 2, '#', '', 1, 0, 'F', '0', '0', 'monitor:online:batchLogout', '#', 'admin', '2021-07-06 16:53:36', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1048, '单条强退', 109, 3, '#', '', 1, 0, 'F', '0', '0', 'monitor:online:forceLogout', '#', 'admin', '2021-07-06 16:53:36', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1049, '任务查询', 110, 1, '#', '', 1, 0, 'F', '0', '0', 'monitor:job:query', '#', 'admin', '2021-07-06 16:53:36', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1050, '任务新增', 110, 2, '#', '', 1, 0, 'F', '0', '0', 'monitor:job:add', '#', 'admin', '2021-07-06 16:53:36', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1051, '任务修改', 110, 3, '#', '', 1, 0, 'F', '0', '0', 'monitor:job:edit', '#', 'admin', '2021-07-06 16:53:36', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1052, '任务删除', 110, 4, '#', '', 1, 0, 'F', '0', '0', 'monitor:job:remove', '#', 'admin', '2021-07-06 16:53:36', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1053, '状态修改', 110, 5, '#', '', 1, 0, 'F', '0', '0', 'monitor:job:changeStatus', '#', 'admin', '2021-07-06 16:53:36', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1054, '任务导出', 110, 7, '#', '', 1, 0, 'F', '0', '0', 'monitor:job:export', '#', 'admin', '2021-07-06 16:53:36', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1061, '账簿', 1360, 11, 'accountBook', 'finance/accountBook/index', 1, 0, 'C', '0', '0', 'finance:accountBook:list', '#', 'admin', '2021-07-19 16:26:53', 'admin', '2021-07-29 11:15:42', '账簿菜单');
INSERT INTO `sys_menu` VALUES (1062, '账簿查询', 1061, 1, '#', '', 1, 0, 'F', '0', '0', 'finance:accountBook:query', '#', 'admin', '2021-07-19 16:26:53', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1063, '账簿新增', 1061, 2, '#', '', 1, 0, 'F', '0', '0', 'finance:accountBook:add', '#', 'admin', '2021-07-19 16:26:53', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1064, '账簿修改', 1061, 3, '#', '', 1, 0, 'F', '0', '0', 'finance:accountBook:edit', '#', 'admin', '2021-07-19 16:26:53', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1065, '账簿删除', 1061, 4, '#', '', 1, 0, 'F', '0', '0', 'finance:accountBook:remove', '#', 'admin', '2021-07-19 16:26:53', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1066, '账簿导出', 1061, 5, '#', '', 1, 0, 'F', '0', '0', 'finance:accountBook:export', '#', 'admin', '2021-07-19 16:26:53', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1067, '科目核算维度初始数据', 1241, 1, 'initDimension', 'finance/initDimension/index', 1, 0, 'C', '0', '0', 'finance:initDimension:list', '#', 'admin', '2021-07-19 16:32:54', 'admin', '2021-07-29 11:24:42', '科目核算维度初始数据菜单');
INSERT INTO `sys_menu` VALUES (1068, '科目核算维度初始数据查询', 1067, 1, '#', '', 1, 0, 'F', '0', '0', 'finance:initDimension:query', '#', 'admin', '2021-07-19 16:32:54', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1069, '科目核算维度初始数据新增', 1067, 2, '#', '', 1, 0, 'F', '0', '0', 'finance:initDimension:add', '#', 'admin', '2021-07-19 16:32:54', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1070, '科目核算维度初始数据修改', 1067, 3, '#', '', 1, 0, 'F', '0', '0', 'finance:initDimension:edit', '#', 'admin', '2021-07-19 16:32:54', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1071, '科目核算维度初始数据删除', 1067, 4, '#', '', 1, 0, 'F', '0', '0', 'finance:initDimension:remove', '#', 'admin', '2021-07-19 16:32:54', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1072, '科目核算维度初始数据导出', 1067, 5, '#', '', 1, 0, 'F', '0', '0', 'finance:initDimension:export', '#', 'admin', '2021-07-19 16:32:54', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1073, '基础汇率', 1360, 2, 'rate', 'finance/rate/index', 1, 0, 'C', '0', '0', 'finance:rate:list', '#', 'admin', '2021-07-19 16:32:54', 'admin', '2021-07-29 11:05:11', '基础汇率菜单');
INSERT INTO `sys_menu` VALUES (1074, '基础汇率查询', 1073, 1, '#', '', 1, 0, 'F', '0', '0', 'finance:rate:query', '#', 'admin', '2021-07-19 16:32:54', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1075, '基础汇率新增', 1073, 2, '#', '', 1, 0, 'F', '0', '0', 'finance:rate:add', '#', 'admin', '2021-07-19 16:32:54', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1076, '基础汇率修改', 1073, 3, '#', '', 1, 0, 'F', '0', '0', 'finance:rate:edit', '#', 'admin', '2021-07-19 16:32:54', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1077, '基础汇率删除', 1073, 4, '#', '', 1, 0, 'F', '0', '0', 'finance:rate:remove', '#', 'admin', '2021-07-19 16:32:54', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1078, '基础汇率导出', 1073, 5, '#', '', 1, 0, 'F', '0', '0', 'finance:rate:export', '#', 'admin', '2021-07-19 16:32:54', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1079, '汇率类型', 1360, 3, 'rateType', 'finance/rateType/index', 1, 0, 'C', '0', '0', 'finance:rateType:list', '#', 'admin', '2021-07-19 16:32:54', 'admin', '2021-07-29 11:05:41', '汇率类型菜单');
INSERT INTO `sys_menu` VALUES (1080, '汇率类型查询', 1079, 1, '#', '', 1, 0, 'F', '0', '0', 'finance:rateType:query', '#', 'admin', '2021-07-19 16:32:54', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1081, '汇率类型新增', 1079, 2, '#', '', 1, 0, 'F', '0', '0', 'finance:rateType:add', '#', 'admin', '2021-07-19 16:32:54', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1082, '汇率类型修改', 1079, 3, '#', '', 1, 0, 'F', '0', '0', 'finance:rateType:edit', '#', 'admin', '2021-07-19 16:32:54', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1083, '汇率类型删除', 1079, 4, '#', '', 1, 0, 'F', '0', '0', 'finance:rateType:remove', '#', 'admin', '2021-07-19 16:32:54', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1084, '汇率类型导出', 1079, 5, '#', '', 1, 0, 'F', '0', '0', 'finance:rateType:export', '#', 'admin', '2021-07-19 16:32:54', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1085, '单位换算', 3, 1, 'unitConvertRate', 'finance/unitConvertRate/index', 1, 0, 'C', '0', '0', 'finance:unitConvertRate:list', '#', 'admin', '2021-07-19 16:32:54', '', NULL, '单位换算菜单');
INSERT INTO `sys_menu` VALUES (1086, '单位换算查询', 1085, 1, '#', '', 1, 0, 'F', '0', '0', 'finance:unitConvertRate:query', '#', 'admin', '2021-07-19 16:32:54', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1087, '单位换算新增', 1085, 2, '#', '', 1, 0, 'F', '0', '0', 'finance:unitConvertRate:add', '#', 'admin', '2021-07-19 16:32:54', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1088, '单位换算修改', 1085, 3, '#', '', 1, 0, 'F', '0', '0', 'finance:unitConvertRate:edit', '#', 'admin', '2021-07-19 16:32:54', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1089, '单位换算删除', 1085, 4, '#', '', 1, 0, 'F', '0', '0', 'finance:unitConvertRate:remove', '#', 'admin', '2021-07-19 16:32:54', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1090, '单位换算导出', 1085, 5, '#', '', 1, 0, 'F', '0', '0', 'finance:unitConvertRate:export', '#', 'admin', '2021-07-19 16:32:54', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1091, '计量单位组', 3, 1, 'unitGroup', 'finance/unitGroup/index', 1, 0, 'C', '0', '0', 'finance:unitGroup:list', '#', 'admin', '2021-07-19 16:32:54', '', NULL, '计量单位组菜单');
INSERT INTO `sys_menu` VALUES (1092, '计量单位组查询', 1091, 1, '#', '', 1, 0, 'F', '0', '0', 'finance:unitGroup:query', '#', 'admin', '2021-07-19 16:32:54', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1093, '计量单位组新增', 1091, 2, '#', '', 1, 0, 'F', '0', '0', 'finance:unitGroup:add', '#', 'admin', '2021-07-19 16:32:54', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1094, '计量单位组修改', 1091, 3, '#', '', 1, 0, 'F', '0', '0', 'finance:unitGroup:edit', '#', 'admin', '2021-07-19 16:32:54', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1095, '计量单位组删除', 1091, 4, '#', '', 1, 0, 'F', '0', '0', 'finance:unitGroup:remove', '#', 'admin', '2021-07-19 16:32:55', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1096, '计量单位组导出', 1091, 5, '#', '', 1, 0, 'F', '0', '0', 'finance:unitGroup:export', '#', 'admin', '2021-07-19 16:32:55', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1097, '计量单位分组', 3, 1, 'unitGroupTable', 'finance/unitGroupTable/index', 1, 0, 'C', '0', '0', 'finance:unitGroupTable:list', '#', 'admin', '2021-07-19 16:32:55', '', NULL, '计量单位分组菜单');
INSERT INTO `sys_menu` VALUES (1098, '计量单位分组查询', 1097, 1, '#', '', 1, 0, 'F', '0', '0', 'finance:unitGroupTable:query', '#', 'admin', '2021-07-19 16:32:55', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1099, '计量单位分组新增', 1097, 2, '#', '', 1, 0, 'F', '0', '0', 'finance:unitGroupTable:add', '#', 'admin', '2021-07-19 16:32:55', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1100, '计量单位分组修改', 1097, 3, '#', '', 1, 0, 'F', '0', '0', 'finance:unitGroupTable:edit', '#', 'admin', '2021-07-19 16:32:55', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1101, '计量单位分组删除', 1097, 4, '#', '', 1, 0, 'F', '0', '0', 'finance:unitGroupTable:remove', '#', 'admin', '2021-07-19 16:32:55', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1102, '计量单位分组导出', 1097, 5, '#', '', 1, 0, 'F', '0', '0', 'finance:unitGroupTable:export', '#', 'admin', '2021-07-19 16:32:55', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1103, '计量单位', 3, 1, 'unit', 'finance/unit/index', 1, 0, 'C', '0', '0', 'finance:unit:list', '#', 'admin', '2021-07-19 16:32:55', '', NULL, '计量单位菜单');
INSERT INTO `sys_menu` VALUES (1104, '计量单位查询', 1103, 1, '#', '', 1, 0, 'F', '0', '0', 'finance:unit:query', '#', 'admin', '2021-07-19 16:32:55', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1105, '计量单位新增', 1103, 2, '#', '', 1, 0, 'F', '0', '0', 'finance:unit:add', '#', 'admin', '2021-07-19 16:32:55', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1106, '计量单位修改', 1103, 3, '#', '', 1, 0, 'F', '0', '0', 'finance:unit:edit', '#', 'admin', '2021-07-19 16:32:55', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1107, '计量单位删除', 1103, 4, '#', '', 1, 0, 'F', '0', '0', 'finance:unit:remove', '#', 'admin', '2021-07-19 16:32:55', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1108, '计量单位导出', 1103, 5, '#', '', 1, 0, 'F', '0', '0', 'finance:unit:export', '#', 'admin', '2021-07-19 16:32:55', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1109, '凭证字-科目控制', 1121, 1, 'vchgroupAcct', 'finance/vchgroupAcct/index', 1, 0, 'C', '0', '0', 'finance:vchgroupAcct:list', '#', 'admin', '2021-07-19 16:32:55', 'admin', '2021-07-29 11:14:38', '凭证字-科目控制菜单');
INSERT INTO `sys_menu` VALUES (1110, '凭证字-科目控制查询', 1109, 1, '#', '', 1, 0, 'F', '0', '0', 'finance:vchgroupAcct:query', '#', 'admin', '2021-07-19 16:32:55', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1111, '凭证字-科目控制新增', 1109, 2, '#', '', 1, 0, 'F', '0', '0', 'finance:vchgroupAcct:add', '#', 'admin', '2021-07-19 16:32:55', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1112, '凭证字-科目控制修改', 1109, 3, '#', '', 1, 0, 'F', '0', '0', 'finance:vchgroupAcct:edit', '#', 'admin', '2021-07-19 16:32:55', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1113, '凭证字-科目控制删除', 1109, 4, '#', '', 1, 0, 'F', '0', '0', 'finance:vchgroupAcct:remove', '#', 'admin', '2021-07-19 16:32:55', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1114, '凭证字-科目控制导出', 1109, 5, '#', '', 1, 0, 'F', '0', '0', 'finance:vchgroupAcct:export', '#', 'admin', '2021-07-19 16:32:55', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1115, '凭证录入分', 3, 1, 'voucherEntry', 'finance/voucherEntry/index', 1, 0, 'C', '0', '0', 'finance:voucherEntry:list', '#', 'admin', '2021-07-19 16:32:55', '', NULL, '凭证录入分菜单');
INSERT INTO `sys_menu` VALUES (1116, '凭证录入分查询', 1115, 1, '#', '', 1, 0, 'F', '0', '0', 'finance:voucherEntry:query', '#', 'admin', '2021-07-19 16:32:55', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1117, '凭证录入分新增', 1115, 2, '#', '', 1, 0, 'F', '0', '0', 'finance:voucherEntry:add', '#', 'admin', '2021-07-19 16:32:55', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1118, '凭证录入分修改', 1115, 3, '#', '', 1, 0, 'F', '0', '0', 'finance:voucherEntry:edit', '#', 'admin', '2021-07-19 16:32:55', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1119, '凭证录入分删除', 1115, 4, '#', '', 1, 0, 'F', '0', '0', 'finance:voucherEntry:remove', '#', 'admin', '2021-07-19 16:32:55', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1120, '凭证录入分导出', 1115, 5, '#', '', 1, 0, 'F', '0', '0', 'finance:voucherEntry:export', '#', 'admin', '2021-07-19 16:32:55', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1121, '凭证字', 1360, 8, 'voucherGroup', 'finance/voucherGroup/index', 1, 0, 'C', '0', '0', 'finance:voucherGroup:list', '#', 'admin', '2021-07-19 16:32:55', 'admin', '2021-07-29 11:14:26', '凭证字菜单');
INSERT INTO `sys_menu` VALUES (1122, '凭证字查询', 1121, 1, '#', '', 1, 0, 'F', '0', '0', 'finance:voucherGroup:query', '#', 'admin', '2021-07-19 16:32:55', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1123, '凭证字新增', 1121, 2, '#', '', 1, 0, 'F', '0', '0', 'finance:voucherGroup:add', '#', 'admin', '2021-07-19 16:32:55', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1124, '凭证字修改', 1121, 3, '#', '', 1, 0, 'F', '0', '0', 'finance:voucherGroup:edit', '#', 'admin', '2021-07-19 16:32:55', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1125, '凭证字删除', 1121, 4, '#', '', 1, 0, 'F', '0', '0', 'finance:voucherGroup:remove', '#', 'admin', '2021-07-19 16:32:55', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1126, '凭证字导出', 1121, 5, '#', '', 1, 0, 'F', '0', '0', 'finance:voucherGroup:export', '#', 'admin', '2021-07-19 16:32:55', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1127, '凭证录入主', 3, 1, 'voucher', 'finance/voucher/index', 1, 0, 'C', '0', '0', 'finance:voucher:list', '#', 'admin', '2021-07-19 16:32:55', '', NULL, '凭证录入主菜单');
INSERT INTO `sys_menu` VALUES (1128, '凭证录入主查询', 1127, 1, '#', '', 1, 0, 'F', '0', '0', 'finance:voucher:query', '#', 'admin', '2021-07-19 16:32:55', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1129, '凭证录入主新增', 1127, 2, '#', '', 1, 0, 'F', '0', '0', 'finance:voucher:add', '#', 'admin', '2021-07-19 16:32:55', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1130, '凭证录入主修改', 1127, 3, '#', '', 1, 0, 'F', '0', '0', 'finance:voucher:edit', '#', 'admin', '2021-07-19 16:32:55', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1131, '凭证录入主删除', 1127, 4, '#', '', 1, 0, 'F', '0', '0', 'finance:voucher:remove', '#', 'admin', '2021-07-19 16:32:55', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1132, '凭证录入主导出', 1127, 5, '#', '', 1, 0, 'F', '0', '0', 'finance:voucher:export', '#', 'admin', '2021-07-19 16:32:55', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1133, '会计日历', 1360, 3, 'accountCalendar', 'finance/accountCalendar/index', 1, 0, 'C', '0', '0', 'finance:accountCalendar:list', '#', 'admin', '2021-07-19 16:32:55', 'admin', '2021-07-29 11:06:40', '会计日历菜单');
INSERT INTO `sys_menu` VALUES (1134, '会计日历查询', 1133, 1, '#', '', 1, 0, 'F', '0', '0', 'finance:accountCalendar:query', '#', 'admin', '2021-07-19 16:32:55', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1135, '会计日历新增', 1133, 2, '#', '', 1, 0, 'F', '0', '0', 'finance:accountCalendar:add', '#', 'admin', '2021-07-19 16:32:55', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1136, '会计日历修改', 1133, 3, '#', '', 1, 0, 'F', '0', '0', 'finance:accountCalendar:edit', '#', 'admin', '2021-07-19 16:32:55', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1137, '会计日历删除', 1133, 4, '#', '', 1, 0, 'F', '0', '0', 'finance:accountCalendar:remove', '#', 'admin', '2021-07-19 16:32:55', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1138, '会计日历导出', 1133, 5, '#', '', 1, 0, 'F', '0', '0', 'finance:accountCalendar:export', '#', 'admin', '2021-07-19 16:32:55', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1139, '科目核算币别', 1187, 1, 'accountcy', 'finance/accountcy/index', 1, 0, 'C', '0', '0', 'finance:accountcy:list', '#', 'admin', '2021-07-19 16:32:55', 'admin', '2021-07-29 11:12:46', '科目核算币别菜单');
INSERT INTO `sys_menu` VALUES (1140, '科目核算币别查询', 1139, 1, '#', '', 1, 0, 'F', '0', '0', 'finance:accountcy:query', '#', 'admin', '2021-07-19 16:32:55', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1141, '科目核算币别新增', 1139, 2, '#', '', 1, 0, 'F', '0', '0', 'finance:accountcy:add', '#', 'admin', '2021-07-19 16:32:56', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1142, '科目核算币别修改', 1139, 3, '#', '', 1, 0, 'F', '0', '0', 'finance:accountcy:edit', '#', 'admin', '2021-07-19 16:32:56', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1143, '科目核算币别删除', 1139, 4, '#', '', 1, 0, 'F', '0', '0', 'finance:accountcy:remove', '#', 'admin', '2021-07-19 16:32:56', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1144, '科目核算币别导出', 1139, 5, '#', '', 1, 0, 'F', '0', '0', 'finance:accountcy:export', '#', 'admin', '2021-07-19 16:32:56', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1145, '科目分发', 1187, 2, 'accountDistribute', 'finance/accountDistribute/index', 1, 0, 'C', '0', '0', 'finance:accountDistribute:list', '#', 'admin', '2021-07-19 16:32:56', 'admin', '2021-07-29 11:12:58', '科目分发菜单');
INSERT INTO `sys_menu` VALUES (1146, '科目分发查询', 1145, 1, '#', '', 1, 0, 'F', '0', '0', 'finance:accountDistribute:query', '#', 'admin', '2021-07-19 16:32:56', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1147, '科目分发新增', 1145, 2, '#', '', 1, 0, 'F', '0', '0', 'finance:accountDistribute:add', '#', 'admin', '2021-07-19 16:32:56', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1148, '科目分发修改', 1145, 3, '#', '', 1, 0, 'F', '0', '0', 'finance:accountDistribute:edit', '#', 'admin', '2021-07-19 16:32:56', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1149, '科目分发删除', 1145, 4, '#', '', 1, 0, 'F', '0', '0', 'finance:accountDistribute:remove', '#', 'admin', '2021-07-19 16:32:56', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1150, '科目分发导出', 1145, 5, '#', '', 1, 0, 'F', '0', '0', 'finance:accountDistribute:export', '#', 'admin', '2021-07-19 16:32:56', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1151, '科目核算维度组分录', 1187, 3, 'accountFlexentry', 'finance/accountFlexentry/index', 1, 0, 'C', '0', '0', 'finance:accountFlexentry:list', '#', 'admin', '2021-07-19 16:32:56', 'admin', '2021-07-29 11:13:14', '科目核算维度组分录菜单');
INSERT INTO `sys_menu` VALUES (1152, '科目核算维度组分录查询', 1151, 1, '#', '', 1, 0, 'F', '0', '0', 'finance:accountFlexentry:query', '#', 'admin', '2021-07-19 16:32:56', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1153, '科目核算维度组分录新增', 1151, 2, '#', '', 1, 0, 'F', '0', '0', 'finance:accountFlexentry:add', '#', 'admin', '2021-07-19 16:32:56', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1154, '科目核算维度组分录修改', 1151, 3, '#', '', 1, 0, 'F', '0', '0', 'finance:accountFlexentry:edit', '#', 'admin', '2021-07-19 16:32:56', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1155, '科目核算维度组分录删除', 1151, 4, '#', '', 1, 0, 'F', '0', '0', 'finance:accountFlexentry:remove', '#', 'admin', '2021-07-19 16:32:56', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1156, '科目核算维度组分录导出', 1151, 5, '#', '', 1, 0, 'F', '0', '0', 'finance:accountFlexentry:export', '#', 'admin', '2021-07-19 16:32:56', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1157, '会计要素', 1360, 4, 'accountGroup', 'finance/accountGroup/index', 1, 0, 'C', '0', '0', 'finance:accountGroup:list', '#', 'admin', '2021-07-19 16:32:56', 'admin', '2021-07-29 11:06:48', '会计要素菜单');
INSERT INTO `sys_menu` VALUES (1158, '会计要素查询', 1157, 1, '#', '', 1, 0, 'F', '0', '0', 'finance:accountGroup:query', '#', 'admin', '2021-07-19 16:32:56', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1159, '会计要素新增', 1157, 2, '#', '', 1, 0, 'F', '0', '0', 'finance:accountGroup:add', '#', 'admin', '2021-07-19 16:32:56', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1160, '会计要素修改', 1157, 3, '#', '', 1, 0, 'F', '0', '0', 'finance:accountGroup:edit', '#', 'admin', '2021-07-19 16:32:56', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1161, '会计要素删除', 1157, 4, '#', '', 1, 0, 'F', '0', '0', 'finance:accountGroup:remove', '#', 'admin', '2021-07-19 16:32:56', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1162, '会计要素导出', 1157, 5, '#', '', 1, 0, 'F', '0', '0', 'finance:accountGroup:export', '#', 'admin', '2021-07-19 16:32:56', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1163, '会计要素', 3, 1, 'accountGroupTable', 'finance/accountGroupTable/index', 1, 0, 'C', '0', '0', 'finance:accountGroupTable:list', '#', 'admin', '2021-07-19 16:32:56', '', NULL, '会计要素菜单');
INSERT INTO `sys_menu` VALUES (1164, '会计要素查询', 1163, 1, '#', '', 1, 0, 'F', '0', '0', 'finance:accountGroupTable:query', '#', 'admin', '2021-07-19 16:32:56', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1165, '会计要素新增', 1163, 2, '#', '', 1, 0, 'F', '0', '0', 'finance:accountGroupTable:add', '#', 'admin', '2021-07-19 16:32:56', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1166, '会计要素修改', 1163, 3, '#', '', 1, 0, 'F', '0', '0', 'finance:accountGroupTable:edit', '#', 'admin', '2021-07-19 16:32:56', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1167, '会计要素删除', 1163, 4, '#', '', 1, 0, 'F', '0', '0', 'finance:accountGroupTable:remove', '#', 'admin', '2021-07-19 16:32:56', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1168, '会计要素导出', 1163, 5, '#', '', 1, 0, 'F', '0', '0', 'finance:accountGroupTable:export', '#', 'admin', '2021-07-19 16:32:56', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1169, '科目信息', 1187, 4, 'account', 'finance/account/index', 1, 0, 'C', '0', '0', 'finance:account:list', '#', 'admin', '2021-07-19 16:32:56', 'admin', '2021-07-29 11:13:20', '科目信息菜单');
INSERT INTO `sys_menu` VALUES (1170, '科目信息查询', 1169, 1, '#', '', 1, 0, 'F', '0', '0', 'finance:account:query', '#', 'admin', '2021-07-19 16:32:56', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1171, '科目信息新增', 1169, 2, '#', '', 1, 0, 'F', '0', '0', 'finance:account:add', '#', 'admin', '2021-07-19 16:32:56', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1172, '科目信息修改', 1169, 3, '#', '', 1, 0, 'F', '0', '0', 'finance:account:edit', '#', 'admin', '2021-07-19 16:32:56', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1173, '科目信息删除', 1169, 4, '#', '', 1, 0, 'F', '0', '0', 'finance:account:remove', '#', 'admin', '2021-07-19 16:32:56', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1174, '科目信息导出', 1169, 5, '#', '', 1, 0, 'F', '0', '0', 'finance:account:export', '#', 'admin', '2021-07-19 16:32:56', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1175, '会计期间', 3, 1, 'accountPeriod', 'finance/accountPeriod/index', 1, 0, 'C', '0', '0', 'finance:accountPeriod:list', '#', 'admin', '2021-07-19 16:32:56', '', NULL, '会计期间菜单');
INSERT INTO `sys_menu` VALUES (1176, '会计期间查询', 1175, 1, '#', '', 1, 0, 'F', '0', '0', 'finance:accountPeriod:query', '#', 'admin', '2021-07-19 16:32:56', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1177, '会计期间新增', 1175, 2, '#', '', 1, 0, 'F', '0', '0', 'finance:accountPeriod:add', '#', 'admin', '2021-07-19 16:32:56', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1178, '会计期间修改', 1175, 3, '#', '', 1, 0, 'F', '0', '0', 'finance:accountPeriod:edit', '#', 'admin', '2021-07-19 16:32:56', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1179, '会计期间删除', 1175, 4, '#', '', 1, 0, 'F', '0', '0', 'finance:accountPeriod:remove', '#', 'admin', '2021-07-19 16:32:56', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1180, '会计期间导出', 1175, 5, '#', '', 1, 0, 'F', '0', '0', 'finance:accountPeriod:export', '#', 'admin', '2021-07-19 16:32:56', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1181, '会计核算体系', 1360, 10, 'accountSystem', 'finance/accountSystem/index', 1, 0, 'C', '0', '0', 'finance:accountSystem:list', '#', 'admin', '2021-07-19 16:32:56', 'admin', '2021-07-29 11:07:26', '会计核算体系菜单');
INSERT INTO `sys_menu` VALUES (1182, '会计核算体系查询', 1181, 1, '#', '', 1, 0, 'F', '0', '0', 'finance:accountSystem:query', '#', 'admin', '2021-07-19 16:32:56', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1183, '会计核算体系新增', 1181, 2, '#', '', 1, 0, 'F', '0', '0', 'finance:accountSystem:add', '#', 'admin', '2021-07-19 16:32:56', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1184, '会计核算体系修改', 1181, 3, '#', '', 1, 0, 'F', '0', '0', 'finance:accountSystem:edit', '#', 'admin', '2021-07-19 16:32:56', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1185, '会计核算体系删除', 1181, 4, '#', '', 1, 0, 'F', '0', '0', 'finance:accountSystem:remove', '#', 'admin', '2021-07-19 16:32:56', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1186, '会计核算体系导出', 1181, 5, '#', '', 1, 0, 'F', '0', '0', 'finance:accountSystem:export', '#', 'admin', '2021-07-19 16:32:56', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1187, '科目', 1360, 7, 'accountTable', 'finance/accountTable/index', 1, 0, 'C', '0', '0', 'finance:accountTable:list', '#', 'admin', '2021-07-19 16:32:56', 'admin', '2021-07-29 11:11:32', '科目菜单');
INSERT INTO `sys_menu` VALUES (1188, '科目查询', 1187, 1, '#', '', 1, 0, 'F', '0', '0', 'finance:accountTable:query', '#', 'admin', '2021-07-19 16:32:56', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1189, '科目新增', 1187, 2, '#', '', 1, 0, 'F', '0', '0', 'finance:accountTable:add', '#', 'admin', '2021-07-19 16:32:56', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1190, '科目修改', 1187, 3, '#', '', 1, 0, 'F', '0', '0', 'finance:accountTable:edit', '#', 'admin', '2021-07-19 16:32:57', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1191, '科目删除', 1187, 4, '#', '', 1, 0, 'F', '0', '0', 'finance:accountTable:remove', '#', 'admin', '2021-07-19 16:32:57', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1192, '科目导出', 1187, 5, '#', '', 1, 0, 'F', '0', '0', 'finance:accountTable:export', '#', 'admin', '2021-07-19 16:32:57', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1193, '科目类别', 1187, 5, 'accountType', 'finance/accountType/index', 1, 0, 'C', '0', '0', 'finance:accountType:list', '#', 'admin', '2021-07-19 16:32:57', 'admin', '2021-07-29 11:13:25', '科目类别菜单');
INSERT INTO `sys_menu` VALUES (1194, '科目类别查询', 1193, 1, '#', '', 1, 0, 'F', '0', '0', 'finance:accountType:query', '#', 'admin', '2021-07-19 16:32:57', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1195, '科目类别新增', 1193, 2, '#', '', 1, 0, 'F', '0', '0', 'finance:accountType:add', '#', 'admin', '2021-07-19 16:32:57', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1196, '科目类别修改', 1193, 3, '#', '', 1, 0, 'F', '0', '0', 'finance:accountType:edit', '#', 'admin', '2021-07-19 16:32:57', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1197, '科目类别删除', 1193, 4, '#', '', 1, 0, 'F', '0', '0', 'finance:accountType:remove', '#', 'admin', '2021-07-19 16:32:57', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1198, '科目类别导出', 1193, 5, '#', '', 1, 0, 'F', '0', '0', 'finance:accountType:export', '#', 'admin', '2021-07-19 16:32:57', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1199, '会计政策资产政策', 1360, 9, 'acctPolicyAsset', 'finance/acctPolicyAsset/index', 1, 0, 'C', '0', '0', 'finance:acctPolicyAsset:list', '#', 'admin', '2021-07-19 16:32:57', 'admin', '2021-07-29 11:07:56', '会计政策资产政策菜单');
INSERT INTO `sys_menu` VALUES (1200, '会计政策资产政策查询', 1199, 1, '#', '', 1, 0, 'F', '0', '0', 'finance:acctPolicyAsset:query', '#', 'admin', '2021-07-19 16:32:57', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1201, '会计政策资产政策新增', 1199, 2, '#', '', 1, 0, 'F', '0', '0', 'finance:acctPolicyAsset:add', '#', 'admin', '2021-07-19 16:32:57', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1202, '会计政策资产政策修改', 1199, 3, '#', '', 1, 0, 'F', '0', '0', 'finance:acctPolicyAsset:edit', '#', 'admin', '2021-07-19 16:32:57', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1203, '会计政策资产政策删除', 1199, 4, '#', '', 1, 0, 'F', '0', '0', 'finance:acctPolicyAsset:remove', '#', 'admin', '2021-07-19 16:32:57', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1204, '会计政策资产政策导出', 1199, 5, '#', '', 1, 0, 'F', '0', '0', 'finance:acctPolicyAsset:export', '#', 'admin', '2021-07-19 16:32:57', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1205, '会计政策', 1360, 9, 'acctPolicy', 'finance/acctPolicy/index', 1, 0, 'C', '0', '0', 'finance:acctPolicy:list', '#', 'admin', '2021-07-19 16:32:57', 'admin', '2021-07-29 11:07:44', '会计政策菜单');
INSERT INTO `sys_menu` VALUES (1206, '会计政策查询', 1205, 1, '#', '', 1, 0, 'F', '0', '0', 'finance:acctPolicy:query', '#', 'admin', '2021-07-19 16:32:57', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1207, '会计政策新增', 1205, 2, '#', '', 1, 0, 'F', '0', '0', 'finance:acctPolicy:add', '#', 'admin', '2021-07-19 16:32:57', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1208, '会计政策修改', 1205, 3, '#', '', 1, 0, 'F', '0', '0', 'finance:acctPolicy:edit', '#', 'admin', '2021-07-19 16:32:57', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1209, '会计政策删除', 1205, 4, '#', '', 1, 0, 'F', '0', '0', 'finance:acctPolicy:remove', '#', 'admin', '2021-07-19 16:32:57', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1210, '会计政策导出', 1205, 5, '#', '', 1, 0, 'F', '0', '0', 'finance:acctPolicy:export', '#', 'admin', '2021-07-19 16:32:57', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1211, '会计政策适用核算组织', 1360, 9, 'acctPolicyOrg', 'finance/acctPolicyOrg/index', 1, 0, 'C', '0', '0', 'finance:acctPolicyOrg:list', '#', 'admin', '2021-07-19 16:32:57', 'admin', '2021-07-29 11:07:50', '会计政策适用核算组织菜单');
INSERT INTO `sys_menu` VALUES (1212, '会计政策适用核算组织查询', 1211, 1, '#', '', 1, 0, 'F', '0', '0', 'finance:acctPolicyOrg:query', '#', 'admin', '2021-07-19 16:32:57', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1213, '会计政策适用核算组织新增', 1211, 2, '#', '', 1, 0, 'F', '0', '0', 'finance:acctPolicyOrg:add', '#', 'admin', '2021-07-19 16:32:57', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1214, '会计政策适用核算组织修改', 1211, 3, '#', '', 1, 0, 'F', '0', '0', 'finance:acctPolicyOrg:edit', '#', 'admin', '2021-07-19 16:32:57', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1215, '会计政策适用核算组织删除', 1211, 4, '#', '', 1, 0, 'F', '0', '0', 'finance:acctPolicyOrg:remove', '#', 'admin', '2021-07-19 16:32:57', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1216, '会计政策适用核算组织导出', 1211, 5, '#', '', 1, 0, 'F', '0', '0', 'finance:acctPolicyOrg:export', '#', 'admin', '2021-07-19 16:32:57', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1217, '会计核算体系之下级组织', 1360, 10, 'acctSysDetail', 'finance/acctSysDetail/index', 1, 0, 'C', '0', '0', 'finance:acctSysDetail:list', '#', 'admin', '2021-07-19 16:32:57', 'admin', '2021-07-29 11:08:10', '会计核算体系之下级组织菜单');
INSERT INTO `sys_menu` VALUES (1218, '会计核算体系之下级组织查询', 1217, 1, '#', '', 1, 0, 'F', '0', '0', 'finance:acctSysDetail:query', '#', 'admin', '2021-07-19 16:32:57', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1219, '会计核算体系之下级组织新增', 1217, 2, '#', '', 1, 0, 'F', '0', '0', 'finance:acctSysDetail:add', '#', 'admin', '2021-07-19 16:32:57', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1220, '会计核算体系之下级组织修改', 1217, 3, '#', '', 1, 0, 'F', '0', '0', 'finance:acctSysDetail:edit', '#', 'admin', '2021-07-19 16:32:57', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1221, '会计核算体系之下级组织删除', 1217, 4, '#', '', 1, 0, 'F', '0', '0', 'finance:acctSysDetail:remove', '#', 'admin', '2021-07-19 16:32:57', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1222, '会计核算体系之下级组织导出', 1217, 5, '#', '', 1, 0, 'F', '0', '0', 'finance:acctSysDetail:export', '#', 'admin', '2021-07-19 16:32:57', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1223, '会计核算体系之会计主体', 1360, 10, 'acctSysEntry', 'finance/acctSysEntry/index', 1, 0, 'C', '0', '0', 'finance:acctSysEntry:list', '#', 'admin', '2021-07-19 16:32:57', 'admin', '2021-07-29 11:08:18', '会计核算体系之会计主体菜单');
INSERT INTO `sys_menu` VALUES (1224, '会计核算体系之会计主体查询', 1223, 1, '#', '', 1, 0, 'F', '0', '0', 'finance:acctSysEntry:query', '#', 'admin', '2021-07-19 16:32:57', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1225, '会计核算体系之会计主体新增', 1223, 2, '#', '', 1, 0, 'F', '0', '0', 'finance:acctSysEntry:add', '#', 'admin', '2021-07-19 16:32:57', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1226, '会计核算体系之会计主体修改', 1223, 3, '#', '', 1, 0, 'F', '0', '0', 'finance:acctSysEntry:edit', '#', 'admin', '2021-07-19 16:32:57', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1227, '会计核算体系之会计主体删除', 1223, 4, '#', '', 1, 0, 'F', '0', '0', 'finance:acctSysEntry:remove', '#', 'admin', '2021-07-19 16:32:57', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1228, '会计核算体系之会计主体导出', 1223, 5, '#', '', 1, 0, 'F', '0', '0', 'finance:acctSysEntry:export', '#', 'admin', '2021-07-19 16:32:57', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1229, '资产类别组', 3, 1, 'assetTypeGroup', 'finance/assetTypeGroup/index', 1, 0, 'C', '0', '0', 'finance:assetTypeGroup:list', '#', 'admin', '2021-07-19 16:32:57', '', NULL, '资产类别组菜单');
INSERT INTO `sys_menu` VALUES (1230, '资产类别组查询', 1229, 1, '#', '', 1, 0, 'F', '0', '0', 'finance:assetTypeGroup:query', '#', 'admin', '2021-07-19 16:32:57', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1231, '资产类别组新增', 1229, 2, '#', '', 1, 0, 'F', '0', '0', 'finance:assetTypeGroup:add', '#', 'admin', '2021-07-19 16:32:57', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1232, '资产类别组修改', 1229, 3, '#', '', 1, 0, 'F', '0', '0', 'finance:assetTypeGroup:edit', '#', 'admin', '2021-07-19 16:32:57', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1233, '资产类别组删除', 1229, 4, '#', '', 1, 0, 'F', '0', '0', 'finance:assetTypeGroup:remove', '#', 'admin', '2021-07-19 16:32:57', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1234, '资产类别组导出', 1229, 5, '#', '', 1, 0, 'F', '0', '0', 'finance:assetTypeGroup:export', '#', 'admin', '2021-07-19 16:32:57', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1235, '资产类别', 3, 1, 'assetType', 'finance/assetType/index', 1, 0, 'C', '0', '0', 'finance:assetType:list', '#', 'admin', '2021-07-19 16:32:58', '', NULL, '资产类别菜单');
INSERT INTO `sys_menu` VALUES (1236, '资产类别查询', 1235, 1, '#', '', 1, 0, 'F', '0', '0', 'finance:assetType:query', '#', 'admin', '2021-07-19 16:32:58', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1237, '资产类别新增', 1235, 2, '#', '', 1, 0, 'F', '0', '0', 'finance:assetType:add', '#', 'admin', '2021-07-19 16:32:58', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1238, '资产类别修改', 1235, 3, '#', '', 1, 0, 'F', '0', '0', 'finance:assetType:edit', '#', 'admin', '2021-07-19 16:32:58', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1239, '资产类别删除', 1235, 4, '#', '', 1, 0, 'F', '0', '0', 'finance:assetType:remove', '#', 'admin', '2021-07-19 16:32:58', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1240, '资产类别导出', 1235, 5, '#', '', 1, 0, 'F', '0', '0', 'finance:assetType:export', '#', 'admin', '2021-07-19 16:32:58', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1241, '科目初始录入数据', 1362, 2, 'balanceInit', 'finance/balanceInit/index', 1, 0, 'C', '0', '0', 'finance:balanceInit:list', '#', 'admin', '2021-07-19 16:32:58', 'admin', '2021-07-29 11:25:13', '科目初始录入数据菜单');
INSERT INTO `sys_menu` VALUES (1242, '科目初始录入数据查询', 1241, 1, '#', '', 1, 0, 'F', '0', '0', 'finance:balanceInit:query', '#', 'admin', '2021-07-19 16:32:58', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1243, '科目初始录入数据新增', 1241, 2, '#', '', 1, 0, 'F', '0', '0', 'finance:balanceInit:add', '#', 'admin', '2021-07-19 16:32:58', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1244, '科目初始录入数据修改', 1241, 3, '#', '', 1, 0, 'F', '0', '0', 'finance:balanceInit:edit', '#', 'admin', '2021-07-19 16:32:58', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1245, '科目初始录入数据删除', 1241, 4, '#', '', 1, 0, 'F', '0', '0', 'finance:balanceInit:remove', '#', 'admin', '2021-07-19 16:32:58', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1246, '科目初始录入数据导出', 1241, 5, '#', '', 1, 0, 'F', '0', '0', 'finance:balanceInit:export', '#', 'admin', '2021-07-19 16:32:58', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1247, '科目余额', 3, 1, 'balance', 'finance/balance/index', 1, 0, 'C', '0', '0', 'finance:balance:list', '#', 'admin', '2021-07-19 16:32:58', '', NULL, '科目余额菜单');
INSERT INTO `sys_menu` VALUES (1248, '科目余额查询', 1247, 1, '#', '', 1, 0, 'F', '0', '0', 'finance:balance:query', '#', 'admin', '2021-07-19 16:32:58', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1249, '科目余额新增', 1247, 2, '#', '', 1, 0, 'F', '0', '0', 'finance:balance:add', '#', 'admin', '2021-07-19 16:32:58', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1250, '科目余额修改', 1247, 3, '#', '', 1, 0, 'F', '0', '0', 'finance:balance:edit', '#', 'admin', '2021-07-19 16:32:58', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1251, '科目余额删除', 1247, 4, '#', '', 1, 0, 'F', '0', '0', 'finance:balance:remove', '#', 'admin', '2021-07-19 16:32:58', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1252, '科目余额导出', 1247, 5, '#', '', 1, 0, 'F', '0', '0', 'finance:balance:export', '#', 'admin', '2021-07-19 16:32:58', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1253, '总账管理参数-账簿参数', 1361, 1, 'bookParam', 'finance/bookParam/index', 1, 0, 'C', '0', '0', 'finance:bookParam:list', '#', 'admin', '2021-07-19 16:32:58', 'admin', '2021-07-29 11:18:03', '总账管理参数-账簿参数菜单');
INSERT INTO `sys_menu` VALUES (1254, '总账管理参数-账簿参数查询', 1253, 1, '#', '', 1, 0, 'F', '0', '0', 'finance:bookParam:query', '#', 'admin', '2021-07-19 16:32:58', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1255, '总账管理参数-账簿参数新增', 1253, 2, '#', '', 1, 0, 'F', '0', '0', 'finance:bookParam:add', '#', 'admin', '2021-07-19 16:32:58', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1256, '总账管理参数-账簿参数修改', 1253, 3, '#', '', 1, 0, 'F', '0', '0', 'finance:bookParam:edit', '#', 'admin', '2021-07-19 16:32:58', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1257, '总账管理参数-账簿参数删除', 1253, 4, '#', '', 1, 0, 'F', '0', '0', 'finance:bookParam:remove', '#', 'admin', '2021-07-19 16:32:58', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1258, '总账管理参数-账簿参数导出', 1253, 5, '#', '', 1, 0, 'F', '0', '0', 'finance:bookParam:export', '#', 'admin', '2021-07-19 16:32:58', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1259, '业务领域', 3, 1, 'businessArea', 'finance/businessArea/index', 1, 0, 'C', '0', '0', 'finance:businessArea:list', '#', 'admin', '2021-07-19 16:32:58', '', NULL, '业务领域菜单');
INSERT INTO `sys_menu` VALUES (1260, '业务领域查询', 1259, 1, '#', '', 1, 0, 'F', '0', '0', 'finance:businessArea:query', '#', 'admin', '2021-07-19 16:32:58', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1261, '业务领域新增', 1259, 2, '#', '', 1, 0, 'F', '0', '0', 'finance:businessArea:add', '#', 'admin', '2021-07-19 16:32:58', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1262, '业务领域修改', 1259, 3, '#', '', 1, 0, 'F', '0', '0', 'finance:businessArea:edit', '#', 'admin', '2021-07-19 16:32:58', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1263, '业务领域删除', 1259, 4, '#', '', 1, 0, 'F', '0', '0', 'finance:businessArea:remove', '#', 'admin', '2021-07-19 16:32:58', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1264, '业务领域导出', 1259, 5, '#', '', 1, 0, 'F', '0', '0', 'finance:businessArea:export', '#', 'admin', '2021-07-19 16:32:58', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1265, '现金流量项目-1', 1360, 13, 'cashFlowItemTable', 'finance/cashFlowItemTable/index', 1, 0, 'C', '0', '0', 'finance:cashFlowItemTable:list', '#', 'admin', '2021-07-19 16:32:58', 'admin', '2021-07-29 11:17:04', '现金流量项目-1菜单');
INSERT INTO `sys_menu` VALUES (1266, '现金流量项目-1查询', 1265, 1, '#', '', 1, 0, 'F', '0', '0', 'finance:cashFlowItemTable:query', '#', 'admin', '2021-07-19 16:32:58', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1267, '现金流量项目-1新增', 1265, 2, '#', '', 1, 0, 'F', '0', '0', 'finance:cashFlowItemTable:add', '#', 'admin', '2021-07-19 16:32:58', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1268, '现金流量项目-1修改', 1265, 3, '#', '', 1, 0, 'F', '0', '0', 'finance:cashFlowItemTable:edit', '#', 'admin', '2021-07-19 16:32:58', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1269, '现金流量项目-1删除', 1265, 4, '#', '', 1, 0, 'F', '0', '0', 'finance:cashFlowItemTable:remove', '#', 'admin', '2021-07-19 16:32:58', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1270, '现金流量项目-1导出', 1265, 5, '#', '', 1, 0, 'F', '0', '0', 'finance:cashFlowItemTable:export', '#', 'admin', '2021-07-19 16:32:58', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1271, '现金流量项目-3', 1265, 1, 'cashFlow', 'finance/cashFlow/index', 1, 0, 'C', '0', '0', 'finance:cashFlow:list', '#', 'admin', '2021-07-19 16:32:58', 'admin', '2021-07-29 11:17:14', '现金流量项目-3菜单');
INSERT INTO `sys_menu` VALUES (1272, '现金流量项目-3查询', 1271, 1, '#', '', 1, 0, 'F', '0', '0', 'finance:cashFlow:query', '#', 'admin', '2021-07-19 16:32:58', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1273, '现金流量项目-3新增', 1271, 2, '#', '', 1, 0, 'F', '0', '0', 'finance:cashFlow:add', '#', 'admin', '2021-07-19 16:32:58', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1274, '现金流量项目-3修改', 1271, 3, '#', '', 1, 0, 'F', '0', '0', 'finance:cashFlow:edit', '#', 'admin', '2021-07-19 16:32:58', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1275, '现金流量项目-3删除', 1271, 4, '#', '', 1, 0, 'F', '0', '0', 'finance:cashFlow:remove', '#', 'admin', '2021-07-19 16:32:58', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1276, '现金流量项目-3导出', 1271, 5, '#', '', 1, 0, 'F', '0', '0', 'finance:cashFlow:export', '#', 'admin', '2021-07-19 16:32:58', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1277, '现金流量项目类别-2', 1265, 1, 'cashFlowType', 'finance/cashFlowType/index', 1, 0, 'C', '0', '0', 'finance:cashFlowType:list', '#', 'admin', '2021-07-19 16:32:58', 'admin', '2021-07-29 11:17:20', '现金流量项目类别-2菜单');
INSERT INTO `sys_menu` VALUES (1278, '现金流量项目类别-2查询', 1277, 1, '#', '', 1, 0, 'F', '0', '0', 'finance:cashFlowType:query', '#', 'admin', '2021-07-19 16:32:59', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1279, '现金流量项目类别-2新增', 1277, 2, '#', '', 1, 0, 'F', '0', '0', 'finance:cashFlowType:add', '#', 'admin', '2021-07-19 16:32:59', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1280, '现金流量项目类别-2修改', 1277, 3, '#', '', 1, 0, 'F', '0', '0', 'finance:cashFlowType:edit', '#', 'admin', '2021-07-19 16:32:59', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1281, '现金流量项目类别-2删除', 1277, 4, '#', '', 1, 0, 'F', '0', '0', 'finance:cashFlowType:remove', '#', 'admin', '2021-07-19 16:32:59', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1282, '现金流量项目类别-2导出', 1277, 5, '#', '', 1, 0, 'F', '0', '0', 'finance:cashFlowType:export', '#', 'admin', '2021-07-19 16:32:59', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1283, '凭证参数', 1361, 2, 'credentialParam', 'finance/credentialParam/index', 1, 0, 'C', '0', '0', 'finance:credentialParam:list', '#', 'admin', '2021-07-19 16:32:59', 'admin', '2021-07-29 11:18:17', '凭证参数菜单');
INSERT INTO `sys_menu` VALUES (1284, '凭证参数查询', 1283, 1, '#', '', 1, 0, 'F', '0', '0', 'finance:credentialParam:query', '#', 'admin', '2021-07-19 16:32:59', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1285, '凭证参数新增', 1283, 2, '#', '', 1, 0, 'F', '0', '0', 'finance:credentialParam:add', '#', 'admin', '2021-07-19 16:32:59', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1286, '凭证参数修改', 1283, 3, '#', '', 1, 0, 'F', '0', '0', 'finance:credentialParam:edit', '#', 'admin', '2021-07-19 16:32:59', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1287, '凭证参数删除', 1283, 4, '#', '', 1, 0, 'F', '0', '0', 'finance:credentialParam:remove', '#', 'admin', '2021-07-19 16:32:59', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1288, '凭证参数导出', 1283, 5, '#', '', 1, 0, 'F', '0', '0', 'finance:credentialParam:export', '#', 'admin', '2021-07-19 16:32:59', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1289, '基础-币别', 1360, 1, 'finance/currency', 'finance/currency/index', 1, 0, 'C', '0', '0', 'finance:currency:list', '#', 'admin', '2021-07-19 16:32:59', 'admin', '2021-07-28 16:30:32', '基础-币别菜单');
INSERT INTO `sys_menu` VALUES (1290, '基础-币别查询', 1289, 1, '#', '', 1, 0, 'F', '0', '0', 'finance:currency:query', '#', 'admin', '2021-07-19 16:32:59', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1291, '基础-币别新增', 1289, 2, '#', '', 1, 0, 'F', '0', '0', 'finance:currency:add', '#', 'admin', '2021-07-19 16:32:59', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1292, '基础-币别修改', 1289, 3, '#', '', 1, 0, 'F', '0', '0', 'finance:currency:edit', '#', 'admin', '2021-07-19 16:32:59', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1293, '基础-币别删除', 1289, 4, '#', '', 1, 0, 'F', '0', '0', 'finance:currency:remove', '#', 'admin', '2021-07-19 16:32:59', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1294, '基础-币别导出', 1289, 5, '#', '', 1, 0, 'F', '0', '0', 'finance:currency:export', '#', 'admin', '2021-07-19 16:32:59', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1295, '折旧方法明细', 3, 1, 'deprMethodEntry', 'finance/deprMethodEntry/index', 1, 0, 'C', '0', '0', 'finance:deprMethodEntry:list', '#', 'admin', '2021-07-19 16:32:59', '', NULL, '折旧方法明细菜单');
INSERT INTO `sys_menu` VALUES (1296, '折旧方法明细查询', 1295, 1, '#', '', 1, 0, 'F', '0', '0', 'finance:deprMethodEntry:query', '#', 'admin', '2021-07-19 16:32:59', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1297, '折旧方法明细新增', 1295, 2, '#', '', 1, 0, 'F', '0', '0', 'finance:deprMethodEntry:add', '#', 'admin', '2021-07-19 16:32:59', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1298, '折旧方法明细修改', 1295, 3, '#', '', 1, 0, 'F', '0', '0', 'finance:deprMethodEntry:edit', '#', 'admin', '2021-07-19 16:32:59', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1299, '折旧方法明细删除', 1295, 4, '#', '', 1, 0, 'F', '0', '0', 'finance:deprMethodEntry:remove', '#', 'admin', '2021-07-19 16:32:59', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1300, '折旧方法明细导出', 1295, 5, '#', '', 1, 0, 'F', '0', '0', 'finance:deprMethodEntry:export', '#', 'admin', '2021-07-19 16:32:59', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1301, '折旧方法元素', 3, 1, 'deprMethodItem', 'finance/deprMethodItem/index', 1, 0, 'C', '0', '0', 'finance:deprMethodItem:list', '#', 'admin', '2021-07-19 16:32:59', '', NULL, '折旧方法元素菜单');
INSERT INTO `sys_menu` VALUES (1302, '折旧方法元素查询', 1301, 1, '#', '', 1, 0, 'F', '0', '0', 'finance:deprMethodItem:query', '#', 'admin', '2021-07-19 16:32:59', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1303, '折旧方法元素新增', 1301, 2, '#', '', 1, 0, 'F', '0', '0', 'finance:deprMethodItem:add', '#', 'admin', '2021-07-19 16:32:59', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1304, '折旧方法元素修改', 1301, 3, '#', '', 1, 0, 'F', '0', '0', 'finance:deprMethodItem:edit', '#', 'admin', '2021-07-19 16:32:59', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1305, '折旧方法元素删除', 1301, 4, '#', '', 1, 0, 'F', '0', '0', 'finance:deprMethodItem:remove', '#', 'admin', '2021-07-19 16:32:59', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1306, '折旧方法元素导出', 1301, 5, '#', '', 1, 0, 'F', '0', '0', 'finance:deprMethodItem:export', '#', 'admin', '2021-07-19 16:32:59', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1307, '折旧方法', 3, 1, 'deprMethod', 'finance/deprMethod/index', 1, 0, 'C', '0', '0', 'finance:deprMethod:list', '#', 'admin', '2021-07-19 16:32:59', '', NULL, '折旧方法菜单');
INSERT INTO `sys_menu` VALUES (1308, '折旧方法查询', 1307, 1, '#', '', 1, 0, 'F', '0', '0', 'finance:deprMethod:query', '#', 'admin', '2021-07-19 16:32:59', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1309, '折旧方法新增', 1307, 2, '#', '', 1, 0, 'F', '0', '0', 'finance:deprMethod:add', '#', 'admin', '2021-07-19 16:32:59', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1310, '折旧方法修改', 1307, 3, '#', '', 1, 0, 'F', '0', '0', 'finance:deprMethod:edit', '#', 'admin', '2021-07-19 16:32:59', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1311, '折旧方法删除', 1307, 4, '#', '', 1, 0, 'F', '0', '0', 'finance:deprMethod:remove', '#', 'admin', '2021-07-19 16:32:59', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1312, '折旧方法导出', 1307, 5, '#', '', 1, 0, 'F', '0', '0', 'finance:deprMethod:export', '#', 'admin', '2021-07-19 16:32:59', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1313, '折旧政策', 3, 1, 'deprPolicy', 'finance/deprPolicy/index', 1, 0, 'C', '0', '0', 'finance:deprPolicy:list', '#', 'admin', '2021-07-19 16:32:59', '', NULL, '折旧政策菜单');
INSERT INTO `sys_menu` VALUES (1314, '折旧政策查询', 1313, 1, '#', '', 1, 0, 'F', '0', '0', 'finance:deprPolicy:query', '#', 'admin', '2021-07-19 16:32:59', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1315, '折旧政策新增', 1313, 2, '#', '', 1, 0, 'F', '0', '0', 'finance:deprPolicy:add', '#', 'admin', '2021-07-19 16:32:59', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1316, '折旧政策修改', 1313, 3, '#', '', 1, 0, 'F', '0', '0', 'finance:deprPolicy:edit', '#', 'admin', '2021-07-19 16:32:59', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1317, '折旧政策删除', 1313, 4, '#', '', 1, 0, 'F', '0', '0', 'finance:deprPolicy:remove', '#', 'admin', '2021-07-19 16:32:59', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1318, '折旧政策导出', 1313, 5, '#', '', 1, 0, 'F', '0', '0', 'finance:deprPolicy:export', '#', 'admin', '2021-07-19 16:32:59', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1319, '维度来源', 3, 1, 'dimensionSource', 'finance/dimensionSource/index', 1, 0, 'C', '0', '0', 'finance:dimensionSource:list', '#', 'admin', '2021-07-19 16:32:59', '', NULL, '维度来源菜单');
INSERT INTO `sys_menu` VALUES (1320, '维度来源查询', 1319, 1, '#', '', 1, 0, 'F', '0', '0', 'finance:dimensionSource:query', '#', 'admin', '2021-07-19 16:32:59', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1321, '维度来源新增', 1319, 2, '#', '', 1, 0, 'F', '0', '0', 'finance:dimensionSource:add', '#', 'admin', '2021-07-19 16:32:59', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1322, '维度来源修改', 1319, 3, '#', '', 1, 0, 'F', '0', '0', 'finance:dimensionSource:edit', '#', 'admin', '2021-07-19 16:32:59', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1323, '维度来源删除', 1319, 4, '#', '', 1, 0, 'F', '0', '0', 'finance:dimensionSource:remove', '#', 'admin', '2021-07-19 16:33:00', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1324, '维度来源导出', 1319, 5, '#', '', 1, 0, 'F', '0', '0', 'finance:dimensionSource:export', '#', 'admin', '2021-07-19 16:33:00', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1325, '摘要库', 1360, 12, 'explanation', 'finance/explanation/index', 1, 0, 'C', '0', '0', 'finance:explanation:list', '#', 'admin', '2021-07-19 16:33:00', 'admin', '2021-07-29 11:16:07', '摘要库菜单');
INSERT INTO `sys_menu` VALUES (1326, '摘要库查询', 1325, 1, '#', '', 1, 0, 'F', '0', '0', 'finance:explanation:query', '#', 'admin', '2021-07-19 16:33:00', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1327, '摘要库新增', 1325, 2, '#', '', 1, 0, 'F', '0', '0', 'finance:explanation:add', '#', 'admin', '2021-07-19 16:33:00', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1328, '摘要库修改', 1325, 3, '#', '', 1, 0, 'F', '0', '0', 'finance:explanation:edit', '#', 'admin', '2021-07-19 16:33:00', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1329, '摘要库删除', 1325, 4, '#', '', 1, 0, 'F', '0', '0', 'finance:explanation:remove', '#', 'admin', '2021-07-19 16:33:00', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1330, '摘要库导出', 1325, 5, '#', '', 1, 0, 'F', '0', '0', 'finance:explanation:export', '#', 'admin', '2021-07-19 16:33:00', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1331, '摘要类别', 1325, 1, 'explanationType', 'finance/explanationType/index', 1, 0, 'C', '0', '0', 'finance:explanationType:list', '#', 'admin', '2021-07-19 16:33:00', 'admin', '2021-07-29 11:16:19', '摘要类别菜单');
INSERT INTO `sys_menu` VALUES (1332, '摘要类别查询', 1331, 1, '#', '', 1, 0, 'F', '0', '0', 'finance:explanationType:query', '#', 'admin', '2021-07-19 16:33:00', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1333, '摘要类别新增', 1331, 2, '#', '', 1, 0, 'F', '0', '0', 'finance:explanationType:add', '#', 'admin', '2021-07-19 16:33:00', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1334, '摘要类别修改', 1331, 3, '#', '', 1, 0, 'F', '0', '0', 'finance:explanationType:edit', '#', 'admin', '2021-07-19 16:33:00', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1335, '摘要类别删除', 1331, 4, '#', '', 1, 0, 'F', '0', '0', 'finance:explanationType:remove', '#', 'admin', '2021-07-19 16:33:00', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1336, '摘要类别导出', 1331, 5, '#', '', 1, 0, 'F', '0', '0', 'finance:explanationType:export', '#', 'admin', '2021-07-19 16:33:00', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1337, '核算维度', 1360, 5, 'flexItemProperty', 'finance/flexItemProperty/index', 1, 0, 'C', '0', '0', 'finance:flexItemProperty:list', '#', 'admin', '2021-07-19 16:33:00', 'admin', '2021-07-29 11:10:54', '核算维度菜单');
INSERT INTO `sys_menu` VALUES (1338, '核算维度查询', 1337, 1, '#', '', 1, 0, 'F', '0', '0', 'finance:flexItemProperty:query', '#', 'admin', '2021-07-19 16:33:00', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1339, '核算维度新增', 1337, 2, '#', '', 1, 0, 'F', '0', '0', 'finance:flexItemProperty:add', '#', 'admin', '2021-07-19 16:33:00', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1340, '核算维度修改', 1337, 3, '#', '', 1, 0, 'F', '0', '0', 'finance:flexItemProperty:edit', '#', 'admin', '2021-07-19 16:33:00', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1341, '核算维度删除', 1337, 4, '#', '', 1, 0, 'F', '0', '0', 'finance:flexItemProperty:remove', '#', 'admin', '2021-07-19 16:33:00', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1342, '核算维度导出', 1337, 5, '#', '', 1, 0, 'F', '0', '0', 'finance:flexItemProperty:export', '#', 'admin', '2021-07-19 16:33:00', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1343, '辅助资料', 3, 1, 'helpData', 'finance/helpData/index', 1, 0, 'C', '0', '0', 'finance:helpData:list', '#', 'admin', '2021-07-19 16:33:00', '', NULL, '辅助资料菜单');
INSERT INTO `sys_menu` VALUES (1344, '辅助资料查询', 1343, 1, '#', '', 1, 0, 'F', '0', '0', 'finance:helpData:query', '#', 'admin', '2021-07-19 16:33:00', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1345, '辅助资料新增', 1343, 2, '#', '', 1, 0, 'F', '0', '0', 'finance:helpData:add', '#', 'admin', '2021-07-19 16:33:00', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1346, '辅助资料修改', 1343, 3, '#', '', 1, 0, 'F', '0', '0', 'finance:helpData:edit', '#', 'admin', '2021-07-19 16:33:00', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1347, '辅助资料删除', 1343, 4, '#', '', 1, 0, 'F', '0', '0', 'finance:helpData:remove', '#', 'admin', '2021-07-19 16:33:00', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1348, '辅助资料导出', 1343, 5, '#', '', 1, 0, 'F', '0', '0', 'finance:helpData:export', '#', 'admin', '2021-07-19 16:33:00', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1349, '辅助资料类别', 3, 1, 'helpType', 'finance/helpType/index', 1, 0, 'C', '0', '0', 'finance:helpType:list', '#', 'admin', '2021-07-19 16:33:00', '', NULL, '辅助资料类别菜单');
INSERT INTO `sys_menu` VALUES (1350, '辅助资料类别查询', 1349, 1, '#', '', 1, 0, 'F', '0', '0', 'finance:helpType:query', '#', 'admin', '2021-07-19 16:33:00', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1351, '辅助资料类别新增', 1349, 2, '#', '', 1, 0, 'F', '0', '0', 'finance:helpType:add', '#', 'admin', '2021-07-19 16:33:00', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1352, '辅助资料类别修改', 1349, 3, '#', '', 1, 0, 'F', '0', '0', 'finance:helpType:edit', '#', 'admin', '2021-07-19 16:33:00', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1353, '辅助资料类别删除', 1349, 4, '#', '', 1, 0, 'F', '0', '0', 'finance:helpType:remove', '#', 'admin', '2021-07-19 16:33:00', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1354, '辅助资料类别导出', 1349, 5, '#', '', 1, 0, 'F', '0', '0', 'finance:helpType:export', '#', 'admin', '2021-07-19 16:33:00', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1356, '总账', 3, 0, 'generalLedger', 'finance/generalLedger/index', 1, 1, 'C', '0', '0', NULL, 'clipboard', 'admin', '2021-07-21 14:00:29', 'admin', '2021-07-21 14:01:17', '');
INSERT INTO `sys_menu` VALUES (1358, '总账概述', 3, 1, '/finance', NULL, 1, 0, 'M', '0', '0', NULL, '#', 'admin', '2021-07-29 10:47:28', 'admin', '2021-07-29 14:11:15', '');
INSERT INTO `sys_menu` VALUES (1359, '总账系统总体流程图', 3, 2, '/finance', NULL, 1, 0, 'M', '0', '0', NULL, 'download', 'admin', '2021-07-29 10:52:20', 'admin', '2021-07-29 10:53:59', '');
INSERT INTO `sys_menu` VALUES (1360, '基础资料', 3, 3, '/finance', NULL, 1, 0, 'M', '0', '0', NULL, 'dict', 'admin', '2021-07-29 10:53:52', 'admin', '2021-07-29 11:39:42', '');
INSERT INTO `sys_menu` VALUES (1361, '总账参数管理', 3, 4, '/finance', NULL, 1, 0, 'M', '0', '0', NULL, 'documentation', 'admin', '2021-07-29 10:58:14', 'admin', '2021-07-29 14:11:43', '');
INSERT INTO `sys_menu` VALUES (1362, '总账初始化', 3, 5, '/finance', NULL, 1, 0, 'M', '0', '0', NULL, 'drag', 'admin', '2021-07-29 10:59:07', 'admin', '2021-07-29 14:11:49', '');
INSERT INTO `sys_menu` VALUES (1363, '凭证管理', 1356, 6, '/finance', NULL, 1, 0, 'M', '0', '0', NULL, '#', 'admin', '2021-07-29 10:59:27', 'admin', '2021-07-29 14:11:57', '');
INSERT INTO `sys_menu` VALUES (1364, '现金流量', 1356, 7, '1', NULL, 1, 0, 'C', '0', '0', NULL, '#', 'admin', '2021-07-29 10:59:54', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1365, '财务账表查询', 1356, 8, '1', NULL, 1, 0, 'C', '0', '0', NULL, '#', 'admin', '2021-07-29 11:00:21', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1366, '期末处理', 1356, 9, '1', NULL, 1, 0, 'C', '0', '0', NULL, '#', 'admin', '2021-07-29 11:00:45', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1367, '调整期间业务处理', 1356, 10, '1', NULL, 1, 0, 'C', '0', '0', NULL, '#', 'admin', '2021-07-29 11:01:24', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1368, '凭证查询', 3, 20, 'voucherList/vList', 'finance/voucherList/vList', 1, 0, 'C', '0', '0', NULL, '#', 'admin', '2021-08-13 09:04:23', 'admin', '2021-08-13 09:05:58', '');
INSERT INTO `sys_menu` VALUES (1369, '凭证审核', 3, 21, 'voucherList/vExamine', 'finance/voucherList/vExamine', 1, 0, 'C', '0', '0', NULL, '#', 'admin', '2021-08-13 09:09:08', 'admin', '2021-08-13 18:13:09', '');
INSERT INTO `sys_menu` VALUES (1370, '凭证过账', 3, 22, 'voucherList/Vposting', 'finance/voucherList/Vposting', 1, 0, 'C', '0', '0', NULL, '#', 'admin', '2021-08-13 19:46:09', 'admin', '2021-08-13 19:47:05', '');
INSERT INTO `sys_menu` VALUES (1371, '科目初始数据录入', 3, 23, 'subjectInit', 'finance/InitializationEntry/subjectInit', 1, 0, 'C', '0', '0', NULL, '#', 'admin', '2021-08-18 15:39:46', 'admin', '2021-08-18 15:44:34', '');
INSERT INTO `sys_menu` VALUES (1372, '现金流量初始数据录入', 3, 24, 'cashFlowInit', 'finance/InitializationEntry/cashFlowInit', 1, 0, 'C', '0', '0', NULL, '#', 'admin', '2021-08-18 15:41:11', 'admin', '2021-08-18 15:44:28', '');
INSERT INTO `sys_menu` VALUES (1373, '总账初始化1', 3, 25, 'generalInit', 'finance/InitializationEntry/generalInit', 1, 0, 'C', '0', '0', NULL, '#', 'admin', '2021-08-18 15:42:44', 'admin', '2021-08-18 15:44:19', '');
INSERT INTO `sys_menu` VALUES (1374, '首页', 0, 1, '/', NULL, 1, 0, 'M', '1', '0', NULL, 'shouye', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1375, '基础建模', 0, 2, '/skq', NULL, 1, 0, 'M', '0', '0', NULL, 'moban', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1376, '生产', 0, 7, '/productions', NULL, 1, 0, 'M', '0', '0', NULL, 'shengchan', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1377, '物料', 0, 6, '/material', NULL, 1, 0, 'M', '0', '0', NULL, 'wuliao', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1378, '设备', 0, 9950, '/equipment', NULL, 1, 0, 'M', '1', '0', NULL, 'shebei', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1379, '质量', 0, 9930, '/quality', NULL, 1, 0, 'M', '0', '0', NULL, 'zhiliang', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1380, '立库仓管', 0, 9951, '/warehouse', NULL, 1, 0, 'M', '1', '0', NULL, 'warehouse', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1381, '告警通知', 0, 9970, '/alarm', NULL, 1, 0, 'M', '1', '0', NULL, 'gaojing', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1382, '报表', 0, 9980, '/report', NULL, 1, 0, 'M', '1', '0', NULL, 'baobiao', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1383, '看板v1', 0, 9960, '/kanban', NULL, 1, 0, 'M', '1', '0', NULL, 'monitor', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1384, '人员', 0, 9940, '/hr', NULL, 1, 0, 'M', '1', '0', NULL, 'personnel', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1385, '计划', 0, 9880, '/plan', NULL, 1, 0, 'M', '1', '0', NULL, 'shebei', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1387, '看板', 0, 9961, '/kanban2', NULL, 1, 0, 'M', '1', '0', NULL, 'monitor', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1388, '日志', 0, 9975, '/log', NULL, 1, 0, 'M', '1', '0', NULL, 'moban', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1389, 'API脚本', 0, 9998, 'apiScript', NULL, 1, 0, 'M', '1', '0', NULL, 'apijiaoben', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1390, '销售', 0, 3, '/crm', NULL, 1, 0, 'M', '0', '0', NULL, 'wuliao', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1391, '对象列表', 0, 9999, 'objectList', NULL, 1, 0, 'M', '1', '0', NULL, 'duixiangliebiao', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1392, 'OA', 0, 7, '/OA', NULL, 1, 0, 'M', '0', '0', NULL, 'wuliao', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1393, '工序流转', 0, 10000, '/processFlow', NULL, 1, 0, 'M', '1', '0', NULL, 'wuliao', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1394, '营销', 0, 5, '/mk', NULL, 1, 0, 'M', '1', '0', NULL, 'wuliao', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1396, '作业管理', 0, 10001, '/jobManagement', NULL, 1, 0, 'M', '1', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1397, '仓管', 0, 5, '/newWarehouse', NULL, 1, 0, 'M', '0', '0', NULL, 'warehouse', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1398, '采购', 0, 4, '/', NULL, 1, 0, 'M', '0', '0', NULL, 'moban', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1411, '用户管理', 1375, 1, 'userList', NULL, 1, 0, 'C', '1', '0', NULL, 'more', '', NULL, 'admin', '2021-09-03 09:59:43', '');
INSERT INTO `sys_menu` VALUES (1412, '角色管理', 1375, 2, 'roleManager', NULL, 1, 0, 'C', '1', '0', NULL, 'more', '', NULL, 'admin', '2021-09-03 09:59:48', '');
INSERT INTO `sys_menu` VALUES (1413, '部门管理', 1375, 5, 'Department', NULL, 1, 0, 'C', '1', '0', NULL, 'more', '', NULL, 'admin', '2021-09-03 09:59:54', '');
INSERT INTO `sys_menu` VALUES (1414, '程序注册', 1375, 8, 'programerRegiste', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1415, '集团建模', 1375, 9, 'groupModeling', NULL, 1, 0, 'C', '1', '0', NULL, 'more', '', NULL, 'admin', '2021-09-03 09:59:58', '');
INSERT INTO `sys_menu` VALUES (1416, '自定义属性', 1375, 10, 'customAttributes', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1417, '条码规则', 1375, 11, 'barCodeRules', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1418, '公司定义1', 1375, 12, 'groupModeling/company', NULL, 1, 0, 'C', '1', '0', NULL, 'more', '', NULL, 'admin', '2021-09-03 10:00:03', '');
INSERT INTO `sys_menu` VALUES (1419, '外部联系人', 1375, 4, '/skq/externalContacts', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1420, '外部联系人标签', 1375, 10, '/skq/externalContactTag', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1421, '外部联系人标签类型', 1375, 10, '/skq/externalContactTagType', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1422, '告警配置', 1375, 15, 'alarmConfiguration', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1423, '全局配置', 1375, 16, 'globalConfiguration', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1424, '组织通讯录', 1375, 100, 'mailList', NULL, 1, 0, 'C', '1', '0', NULL, 'more', '', NULL, 'admin', '2021-09-03 10:00:07', '');
INSERT INTO `sys_menu` VALUES (1425, '职级管理', 1375, 33, 'rankManagement', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1427, '班组班次', 1376, 2, '/group', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1428, '工艺', 1376, 1, '/process', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1429, '工单', 1376, 3, '/production', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1430, '维修', 1376, 6, 'productMaintenance', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1431, '数据拆解', 1376, 7, 'dataDismantl', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1432, '安灯', 1376, 4, '/andeng', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1433, '模拟', 1376, 9, 'simulation', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1434, '生产信息', 1376, 10, 'information', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1435, '生产日志', 1376, 11, 'productionLog', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1436, '数据收束配置', 1376, 20, 'dataQuery', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1437, '生产模拟', 1376, 12, 'productionSimulation', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1438, '线边库', 1376, 30, '/lineSideLibrary', NULL, 1, 0, 'C', '0', '0', NULL, 'guanli', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1439, '事件管理', 1377, 40, '/eventManage', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1440, '映射管理', 1377, 5, 'mappingManage', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1441, '设备资料', 1378, 1, 'EquipmentInformation', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1442, '设备点检', 1378, 2, 'equipmentCheck', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1443, '设备保养', 1378, 3, 'equipmentMaintain', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1444, '设备维修', 1378, 4, 'equipmentRepair', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1445, '设备点检记录', 1378, 5, 'equipmentCheckData', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1446, '设备保养记录', 1378, 6, 'equipmentMaintainData', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1447, '不良原因', 1379, 5, 'badReason', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1448, '清单管理', 1379, 6, 'inspectionChecklist', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1449, '质检记录', 1379, 7, 'qualityInspectionRecord', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1450, '处理记录', 1379, 8, 'processingRecords', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1451, '不合格编码管理', 1379, 9, 'ncCodeManager', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1452, '数据收集管理', 1379, 10, 'dataCollectionManager', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1453, '仓库管理', 1380, 1, 'Warehouses', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1454, '区域管理', 1380, 2, 'Area', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1455, '库区管理', 1380, 3, 'ReseviorArea', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1456, '库位管理', 1380, 4, 'Location', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1457, '审批流程', 1380, 5, 'ProcessApproval', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1458, '物料条码规则', 1380, 6, 'MaterialBarCodeRule', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1459, '物料库存', 1380, 7, 'MaterialNumber', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1460, '库存详情', 1380, 8, 'StorageDetail', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1461, '项目管理', 1380, 9, 'Project', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1462, '项目类型管理', 1380, 10, 'ProjectType', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1463, '待审批管理', 1380, 11, 'Approval', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1464, '领用出库', 1380, 12, 'LeadOut', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1465, '出库队列', 1380, 13, 'OutTaskqueue', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1466, '入库管理', 1380, 14, 'Warehousing', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1467, '入库队列', 1380, 15, 'InTaskqueue', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1468, '加料队列', 1380, 16, 'ChargingTaskQueue', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1469, 'K3入库信息', 1380, 18, 'K3ImportNotifydetail', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1470, 'K3出库信息', 1380, 19, 'K3ExportNotifydetail', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1471, '库存盘点', 1380, 20, 'StockInventory', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1472, '托盘移库', 1380, 21, 'TrayMoveLocation', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1473, '托盘管理', 1380, 22, 'TrayManagement', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1474, '审批结果', 1380, 23, 'ApprovalResult', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1475, '队列维护', 1380, 17, 'Taskqueue', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1476, '3d仓库', 1380, 2, '3dWarehouse', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1477, '通知渠道', 1381, 1, 'notificationChannels', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1478, '通知方式', 1381, 2, 'notificationMethod', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1479, '邮箱服务', 1381, 4, 'email', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1480, '短信服务', 1381, 5, 'shortMessage', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1481, '授权Token', 1381, 6, 'authorizationToken', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1482, '策略', 1381, 7, 'strategy', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1483, '服务配置', 1381, 3, 'serviceConfig', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1484, '电子报表', 1382, 1, 'eReport', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1485, '数据报表', 1382, 2, 'dataReport', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1486, '模组报表（无）', 1382, 3, 'modeReport', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1487, '月产量统计（旧）', 1382, 4, 'monthOutput', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1488, '日产量统计（旧）', 1382, 5, 'dayOutput', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1489, '设备使用率', 1382, 6, 'equipmentUse', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1490, '设备OEE', 1382, 7, 'equipmentOEE', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1491, '拧紧合格率', 1382, 8, 'screwDown', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1492, '工位完成数量统计', 1382, 9, 'completeStatistic', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1493, '班次数量统计（无)', 1382, 10, 'shiftStatistic', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1494, '整体合格率统计', 1382, 11, 'passRateStatistic', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1495, '工位时间统计(无）', 1382, 12, 'timeStatistic', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1496, '节拍统计（无）', 1382, 13, 'beatStatistic', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1497, '一次通过率统计', 1382, 14, 'onePassRate', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1498, 'SPC统计', 1382, 15, 'SPCStatistic', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1499, '月产量统计', 1382, 4, 'monthOutput2', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1500, '日产量统计', 1382, 5, 'dayOutput2', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1501, 'MTTR', 1382, 16, 'MTTR', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1502, '批量产品追溯报告', 1382, 16, 'batchProductTraceability', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1503, '质量报告', 1382, 17, 'qualityReport', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1504, 'MTBF', 1382, 18, 'MTBF', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1505, '数据收束报表', 1382, 10, 'dataCollectReport', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1506, '生产看板1', 1383, 9986, '/kanban/kanban/index?id=201', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1507, '生产看板2', 1383, 9987, '/kanban/kanban/index?id=202', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1508, '订单', 1385, 1, '/order', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1509, '排产', 1385, 2, '/orderScheduling', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1510, '采购', 1385, 3, '/purchase', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1511, '库存计划', 1385, 4, '/entiretyPlanManage', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1512, '计划管理', 1385, 10, 'planManagement', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1513, '容器定义', 1385, 20, 'container', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1514, '包装管理', 1385, 30, 'packManagement', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1515, 'BMS产线看板', 1387, 1, '/kanban/kanbanv2/index?id=337', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1516, '焊接处理段监视平台', 1387, 1, '/kanban/kanbanv2/index?id=339', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1517, '安灯看板', 1387, 1, '/kanban/kanbanv2/index?id=342', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1518, '573看板', 1387, 1, '/kanban/kanbanv2/index?id=348', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1519, '操作日志', 1388, 10, 'operationLog', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1520, '访问日志', 1388, 20, 'visitLog', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1521, 'API历史脚本', 1389, 11, 'historyScript', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1522, 'API执行脚本', 1389, 12, 'runScript', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1523, '客户基础资料', 1390, 10, '/customerBaseInfo', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1524, '客户组织架构', 1390, 11, '/customerDepartment', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1525, '客户成员信息', 1390, 12, '/customerMember', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1526, '客户专项信息', 1390, 13, '/customerAllInfo', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1527, '项目基础信息', 1390, 18, '/projectManager', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1528, '商务信息存档管理', 1390, 19, '/businessFileManager', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1529, '公司资质文件管理', 1390, 20, '/companyFileManage', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1530, '文件类型管理', 1390, 16, '/fileTypeManager', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1532, '报价管理', 1390, 30, '/quotationManagement', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1533, '报价变更管理', 1390, 31, '/quotationChangeManagement', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1534, '核心列表', 1391, 1, 'kernelList', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1535, '审批流转', 1392, 1, 'ApprovalCirculation', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1536, '文件管理', 1392, 2, 'fileManagement', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1537, '文档管理', 1392, 11, '/OA/file', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1538, '草稿箱', 1392, 7, '/OA/drafts', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1539, '组织架构', 1392, 9, '/skq/orgStructure2', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1542, '工序管理', 1393, 3, 'processManager', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1543, '生产任务', 1393, 5, 'productionTask', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1544, '生产任务详情', 1393, 6, 'processDetails', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1545, '下料工序', 1393, 50, '/cuttingProcess', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1546, '质量检验', 1393, 7, '/qualityRestriction', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1547, '焊接工序', 1393, 51, '/weldingProcess', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1548, '喷塑工序', 1393, 52, '/sprayProcess', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1549, '委外接收申请', 1393, 55, '/outSourcing', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1550, '成品入库申请', 1393, 57, '/finishedProduct', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1551, '委外发出申请', 1393, 53, '/inSourcing', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1552, '下料日志', 1393, 100, '/cuttingLogInfo', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1553, '焊接日志', 1393, 101, '/weldingLogInfo', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1554, '喷塑日志', 1393, 102, '/sprayLogInfo', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1555, '委外接收日志', 1393, 104, '/outLogInfo', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1556, '委外发出日志', 1393, 103, '/inLogInfo', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1557, '成品入库日志', 1393, 105, '/finishLogInfo', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1558, '质量检验日志', 1393, 99, '/qualityLogInfo', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1559, '系统问题', 1393, 1000, '/problem', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1560, '质量检验审批', 1393, 8, '/qualityApproval', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1561, '成品入库审批', 1393, 58, '/finishApproval', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1562, '委外发出审批', 1393, 54, '/outSourceApproval', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1563, '委外接收审批', 1393, 56, '/inSourceApproval', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1564, '返修成功申请', 1393, 10, '/processRework', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1565, '返修成功审批', 1393, 11, '/processReworkApproval', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1566, '导入测试模块', 1393, 1001, '/processImport', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1567, '工艺路线清单', 1393, 999, '/routeListInfo', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1568, '工艺路线', 1393, 4, '/newRouteLine', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1569, '返修成功日志', 1393, 106, '/reworkLog', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1570, '营销报表', 1394, 1, '/index', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1571, '属性管理', 1394, 2, '/tableCol/index', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1572, '权限控制', 1394, 3, '/tableRole/index', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1573, '作业配置', 1396, 1, 'jobConfig', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1574, '规则配置', 1396, 2, 'jobRule', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1575, '工厂物料', 1397, 1, 'factoryMaterials', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1576, '库存信息', 1397, 2, 'newInventory', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1577, '条码管理', 1397, 3, 'barCodeManage', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, 'admin1', '2021-09-02 15:28:13', '');
INSERT INTO `sys_menu` VALUES (1578, '来料入库', 1397, 3, 'InMaterialWarehousing', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, 'admin1', '2021-09-02 15:28:19', '');
INSERT INTO `sys_menu` VALUES (1579, '销售退货', 1397, 5, 'salesWarehousing', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, 'admin1', '2021-09-02 15:28:24', '');
INSERT INTO `sys_menu` VALUES (1580, '来料检验', 1397, 6, 'qualityTesting', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, 'admin1', '2021-09-02 15:28:28', '');
INSERT INTO `sys_menu` VALUES (1581, '生产入库', 1397, 7, '/newWareHouse/productionWarehousing', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, 'admin', '2021-09-02 15:28:33', '');
INSERT INTO `sys_menu` VALUES (1582, '供应商管理', 1398, 1, '/supplierManagement', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1583, '采购订单协同', 1398, 6, '/purchaseOrderSynergy', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1584, '询报价', 1398, 3, '/forQuotation', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1585, '招投标', 1398, 4, '/forCastSign', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1586, '采购需求管理', 1398, 2, '/purchaseDemandManagement', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1587, '采购合同管理', 1398, 5, '/purchaseContractManagement)', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1662, '完成工单', 1386, 4, 'completeWork', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1663, '工单管理', 1386, 1, 'workOrderManagement', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1682, '公司定义', 1415, 1, 'company', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1683, '工厂定义', 1415, 2, 'factory', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1684, '区域定义', 1415, 3, 'area', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1685, '车间定义', 1415, 4, 'plant', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1686, '邮箱发件人配置', 1422, 2, 'emailConfig', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1687, '通知内容配置', 1422, 1, 'informConfiguration', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1688, '系统-配置', 1423, 3, 'globalSystemConfig', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1689, '定时任务-配置', 1423, 1, 'globalTime', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1690, '定时任务-执行日志', 1423, 2, 'globalTimeLog', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1691, '班次管理', 1427, 1, 'shiftManager', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1692, '班组管理', 1427, 2, 'groupManager', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1693, '员工管理', 1427, 3, 'staffManager', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1694, '员工类型管理', 1427, 4, 'staffTypeManager', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1695, '制造参数清单', 1428, 20, 'makeBill', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1696, '标签管理', 1428, 16, 'labelManager', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1697, '规则管理', 1428, 17, 'ruleTypeManager', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1698, '工艺路线', 1428, 9, 'processRoute', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1699, '配方管理', 1428, 10, 'formulaManager', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1700, '配方明细管理', 1428, 11, 'formulaDatailManager', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1701, '导入配方', 1428, 12, 'importFormula', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1702, '产品管理', 1428, 14, 'productManager', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1703, 'BOM管理', 1428, 15, 'materialSheetManage', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1704, '标题信息', 1428, 11, 'titleInformation', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1705, '返修路线', 1428, 10, 'reworkRoute', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1708, '完成工单', 1429, 4, 'completeWork', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1709, '工单管理', 1429, 1, 'workOrderManagement', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1710, '当前故障列表', 1432, 1, 'fault', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1711, '故障类型', 1432, 2, 'faulttype', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1712, '历史故障列表', 1432, 3, 'hisfault', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1713, '损失类型', 1432, 4, 'losstype', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1714, 'OEE', 1432, 5, 'oee', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1715, '产量统计', 1432, 6, 'outputstatic', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1716, '损失统计', 1432, 7, 'Pareto', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1717, '损失原因', 1432, 8, 'reason', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1718, '货架-管理', 1438, 1, 'MaterialRack', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1719, '配置-管理', 1438, 2, 'lineSideLibraryConfig', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1720, '库存-管理', 1438, 3, 'lineSideLibraryStock', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1721, '任务-管理', 1438, 4, 'lineSideLibraryTaskList', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1722, '模组库', 1438, 10, 'moduleLib', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1723, '模组发布', 1438, 11, 'moduleDelivery', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1724, '电芯库', 1438, 12, 'cellLib', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1725, '电芯发布', 1438, 13, 'cellDelivery', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1726, '盘点-管理', 1438, 5, 'checkInventory', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1727, '退料-管理', 1438, 6, 'materialReturnList', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1728, '下线-管理', 1438, 7, 'productOffline', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1729, '编号映射', 1440, 2, 'codeMapping', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1730, '批次映射', 1440, 1, 'batchMapping', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1731, '批次生成规则', 1440, 3, 'batchGenerationRule', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1732, '自检清单', 1448, 1, 'selfCheckList', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1733, '首检清单', 1448, 2, 'firstCheckList', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1734, '巡检清单', 1448, 3, 'inspectionChecklist', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1735, '尾检清单', 1448, 4, 'tailCheckList', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1736, '自检', 1449, 1, 'selfIndex', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1737, '首检', 1449, 2, 'firstIndex', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1738, '巡检', 1449, 3, 'inspectionIndex', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1739, '尾检', 1449, 4, 'tailIndex', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1740, '待处理', 1450, 1, 'pending', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1741, '已处理', 1450, 2, 'processed', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1742, '不合格记录', 1451, 2, 'ncCodeRecord', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1743, '不合格编码配置', 1451, 1, 'ncCodeConfig', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1744, '数据收集组配置', 1452, 1, 'dataCollectionGroup', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1745, '数据收集操作', 1452, 2, 'dataCollectionOperation', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1746, '订单管理', 1508, 10, 'orderManagement', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1747, '订单审核', 1508, 15, '/orderExamine', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1748, '采购管理', 1510, 10, '/purchaseManagement', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1749, '采购审核', 1510, 20, '/purchaseExamine', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1750, '流程定义', 1535, 1, 'ApprovalProcessManagement', NULL, 1, 0, 'C', '1', '0', NULL, 'more', '', NULL, 'admin', '2021-09-02 19:40:44', '');
INSERT INTO `sys_menu` VALUES (1751, '表单模板', 1535, 2, 'FormTemplateManagement', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1752, '发起申请', 1535, 3, 'InitiateApplication', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1753, '待我审批', 1535, 4, 'WaitingForMyApproval', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1754, '我的申请查询', 1535, 6, 'MyApplicationInquiry', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1755, '我的审批记录', 1535, 5, 'MyApprovalRecord', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1756, '表单分组', 1535, 2, 'formTemplateType', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1757, '相关单据', 1535, 80, 'documentQuery', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1758, '个人文件夹', 1537, 1, 'personalFolder', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1759, '公告', 1537, 2, 'Notice', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1760, '新闻', 1537, 3, 'news', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1761, '知识库', 1537, 4, 'knowledgeBase', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1762, '企业认证', 1582, 1, '/enterpriseAuthentication', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1763, '供应商生命周期管理', 1582, 3, '/supplierAllVitaManagement', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1764, '企业信息变更', 1582, 1, '/enterpriseInfoEdit', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1765, '供应商绩效评估', 1582, 3, '/supplierPerformanceAssess', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1766, '已收评估结果查询', 1582, 4, '/receivedAssessResultFind', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1767, '注册企业审核', 1582, 2, '/registerEnterpriseAudit', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1768, '寻源大厅', 1584, 1, '/toFindTheSourceHall', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1769, '供应商报价', 1584, 2, '/supplierOffer', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1770, '寻源过程控制', 1584, 3, '/searchForTheSourceProcessControl', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1771, '核价', 1584, 4, '/pricing', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1772, '招标大厅', 1585, 1, '/biddingHallZ', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1773, '投标大厅', 1585, 2, '/biddingHallT', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1774, '招标变更', 1585, 3, '/biddingEdit', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1775, '开标', 1585, 4, '/bidOpening', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1776, '专家评分', 1585, 5, '/expertScore', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1777, '定标', 1585, 6, '/target', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1778, '合同模板', 1587, 4, '/ContractModel', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1779, '合同查询', 1587, 5, '/ContractFiction', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (1787, '退料入库', 1397, 8, '/newWarehouse/returnStockIn', NULL, 1, 0, 'C', '0', '0', NULL, 'more', 'admin1', '2021-09-02 15:26:12', 'admin1', '2021-09-02 15:27:47', '');
INSERT INTO `sys_menu` VALUES (1788, '销售退货入库', 1397, 9, '/newWareHouse/salesReturnReceipt', NULL, 1, 0, 'C', '0', '0', NULL, 'more', 'admin1', '2021-09-02 15:30:14', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1789, '费用化入库', 1397, 9, '/newWareHouse/expenseWarehousing', NULL, 1, 0, 'C', '0', '0', NULL, 'more', 'admin1', '2021-09-02 15:36:47', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1790, '非生产报废入库', 1397, 10, '/newWareHouse/nonProductionScrapReceipt', NULL, 1, 0, 'C', '0', '0', NULL, 'more', 'admin1', '2021-09-02 15:40:16', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (2031, '库内转移', 1397, 11, '/newWareHouse/intraLibraryTransfer', NULL, 1, 0, 'C', '0', '0', NULL, 'more', 'admin1', '2021-09-02 15:45:24', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (2032, '采购订单编辑', 1583, 1, '/purchaseOrderEdit', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2033, '客户订单确认', 1583, 2, '/clientOrderAffirm', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2034, '送货单编辑', 1583, 3, '/deliverySlipEdit', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2035, '对账申请', 1583, 4, '/invoicingApplication', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2036, '对账申请审核', 1583, 5, '/invoicingApplicationAudit', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2037, '审核应收发票', 1583, 6, '/auditInvoiceReceivable', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2038, '审核应付发票', 1583, 7, '/auditPayableInvoice', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2039, '对账信息展示', 1583, 4, '/showInvoicingApplication', NULL, 1, 0, 'C', '0', '0', NULL, 'more', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2040, '盘点', 1397, 12, '/newWareHouse/inventory', NULL, 1, 0, 'C', '0', '0', NULL, 'more', 'admin1', '2021-09-02 15:46:09', 'admin', '2021-09-02 15:49:12', '');
INSERT INTO `sys_menu` VALUES (2041, '生产领用', 1397, 12, '/newWareHouse/productionRequisition', NULL, 1, 0, 'C', '0', '0', NULL, 'more', 'admin1', '2021-09-02 15:47:17', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (2042, '费用化领用', 1397, 13, '/newWareHouse/expenseCollection', NULL, 1, 0, 'C', '0', '0', NULL, 'more', 'admin', '2021-09-02 15:51:16', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (2043, '销售出库', 1397, 14, '/newWareHouse/salesDelivery', NULL, 1, 0, 'C', '0', '0', NULL, 'more', 'admin', '2021-09-02 15:55:33', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (2044, '采购退货', 1397, 15, '/newWareHouse/purchaseReturn', NULL, 1, 0, 'C', '0', '0', NULL, 'more', 'admin', '2021-09-02 15:56:42', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (2045, '报废出库', 1397, 16, '/newWareHouse/scrapIssue', NULL, 1, 0, 'C', '0', '0', NULL, 'more', 'admin', '2021-09-02 15:57:26', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (2046, '添加', 1419, 0, '/', NULL, 1, 0, 'F', '0', '0', 'externalContacts:add', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2047, '删除', 1419, 0, '/', NULL, 1, 0, 'F', '0', '0', 'externalContacts:delete', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2048, '修改', 1419, 0, '/', NULL, 1, 0, 'F', '0', '0', 'externalContacts:update', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2049, '查询', 1419, 0, '/', NULL, 1, 0, 'F', '0', '0', 'externalContacts:list', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2050, '添加', 1420, 0, '/', NULL, 1, 0, 'F', '0', '0', 'externalContactTag:add', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2051, '删除', 1420, 0, '/', NULL, 1, 0, 'F', '0', '0', 'externalContactTag:delete', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2052, '编辑', 1420, 0, '/', NULL, 1, 0, 'F', '0', '0', 'externalContactTag:update', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2053, '查询', 1420, 0, '/', NULL, 1, 0, 'F', '0', '0', 'externalContactTag:list', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2054, '添加', 1421, 0, '/', NULL, 1, 0, 'F', '0', '0', 'externalContactTagType:add', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2055, '删除', 1421, 0, '/', NULL, 1, 0, 'F', '0', '0', 'externalContactTagType:delete', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2056, '查询', 1421, 0, '/', NULL, 1, 0, 'F', '0', '0', 'externalContactTagType:list', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2057, '编辑', 1421, 0, '/', NULL, 1, 0, 'F', '0', '0', 'externalContactTagType:update', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2058, '添加', 1756, 0, '/', NULL, 1, 0, 'F', '0', '0', 'formTemplateType:add', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2059, '删除', 1756, 0, '/', NULL, 1, 0, 'F', '0', '0', 'formTemplateType:delete', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2060, '编辑', 1756, 0, '/', NULL, 1, 0, 'F', '0', '0', 'formTemplateType:update', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2061, '查询', 1756, 0, '/', NULL, 1, 0, 'F', '0', '0', 'formTemplateType:list', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2062, '添加', 1751, 2, '/', NULL, 1, 0, 'F', '0', '0', 'FormTemplateManagement:add', '#', '', NULL, 'admin1', '2021-09-02 19:42:04', '');
INSERT INTO `sys_menu` VALUES (2063, '删除', 1751, 3, '/', NULL, 1, 0, 'F', '0', '0', 'FormTemplateManagement:delete', '#', '', NULL, 'admin1', '2021-09-02 19:42:08', '');
INSERT INTO `sys_menu` VALUES (2064, '编辑', 1751, 4, '/', NULL, 1, 0, 'F', '0', '0', 'FormTemplateManagement:update', '#', '', NULL, 'admin1', '2021-09-02 19:42:12', '');
INSERT INTO `sys_menu` VALUES (2065, '查询', 1751, 1, '/', NULL, 1, 0, 'F', '0', '0', 'FormTemplateManagement:list', '#', '', NULL, 'admin1', '2021-09-02 19:42:00', '');
INSERT INTO `sys_menu` VALUES (2066, '查询', 1752, 0, '/', NULL, 1, 0, 'F', '0', '0', 'InitiateApplication:list', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2067, '查询', 1753, 0, '/', NULL, 1, 0, 'F', '0', '0', 'WaitingForMyApproval:list', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2068, '查询', 1755, 0, '/', NULL, 1, 0, 'F', '0', '0', 'MyApprovalRecord:list', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2069, '查询', 1754, 0, '/', NULL, 1, 0, 'F', '0', '0', 'MyApplicationInquiry:list', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2070, '编辑', 1754, 0, '/', NULL, 1, 0, 'F', '0', '0', 'MyApplicationInquiry:update', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2071, '查询', 1757, 0, '/', NULL, 1, 0, 'F', '0', '0', 'documentQuery:list', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2072, '添加', 1758, 0, '/', NULL, 1, 0, 'F', '0', '0', 'personalFolder:add', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2073, '删除', 1758, 0, '/', NULL, 1, 0, 'F', '0', '0', 'personalFolder:delete', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2074, '编辑', 1758, 0, '/', NULL, 1, 0, 'F', '0', '0', 'personalFolder:update', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2075, '查询', 1758, 0, '/', NULL, 1, 0, 'F', '0', '0', 'personalFolder:list', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2076, '查询', 1759, 0, '/', NULL, 1, 0, 'F', '0', '0', 'Notice:list', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2077, '权限设置', 1759, 0, '/', NULL, 1, 0, 'F', '0', '0', 'Notice:permissionConfiguration', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2078, '查询', 1760, 0, '/', NULL, 1, 0, 'F', '0', '0', 'news:list', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2079, '权限设置', 1760, 0, '/', NULL, 1, 0, 'F', '0', '0', 'news:permissionConfiguration', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2080, '查询', 1761, 0, '/', NULL, 1, 0, 'F', '0', '0', 'knowledgeBase:list', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2081, '权限设置', 1761, 0, '/', NULL, 1, 0, 'F', '0', '0', 'knowledgeBase:permissionConfiguration', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2082, '查看我的企业信息', 1762, 0, '/enterpriseAuthentication', NULL, 1, 0, 'F', '0', '0', 'srm-sup:enterprise', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2083, '查询企业审核信息', 1762, 0, '/registerEnterpriseAudit', NULL, 1, 0, 'F', '0', '0', 'srm-sup:findEnterprise', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2084, '发布调查表', 296, 0, '/issueSurveyForm', NULL, 1, 0, 'F', '0', '0', 'srm-sup:addSurvey', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2085, '调查表查询及答复', 297, 0, '/receivedQuestionFindAndAnswer', NULL, 1, 0, 'F', '0', '0', 'srm-sup:findSurveyOrAnswer', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2086, '供应商生命周期管理', 1763, 0, '/supplierAllVitaManagement', NULL, 1, 0, 'F', '0', '0', 'srm-sup:supManagement', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2087, '企业信息变更', 1764, 0, '/enterpriseInfoEdit', NULL, 1, 0, 'F', '0', '0', 'srm-sup:enterpriseInfoEdit', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2088, '评估指标定义', 302, 0, '/assessIndexDefinition', NULL, 1, 0, 'F', '0', '0', 'srm-sup:indeDefinition', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2089, '评估模板定义', 303, 0, '/assessTemplateDefinition', NULL, 1, 0, 'F', '0', '0', 'srm-sup:templateDefinition', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2090, '供应商绩效评估', 1765, 0, '/supplierPerformanceAssess', NULL, 1, 0, 'F', '0', '0', 'srm-sup:supPerAssess', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2091, '已收评估结果查询', 1766, 0, '/receivedAssessResultFind', NULL, 1, 0, 'F', '0', '0', 'srm-sup:findAssessResult', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2092, '采购需求管理查询', 1586, 0, '/purchaseDemandManagement', NULL, 1, 0, 'F', '0', '0', 'srm-sup:findRequestOrder', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2093, '寻源模板定义', 307, 0, '/searchForTheSourceTemplateDefinition', NULL, 1, 0, 'F', '0', '0', 'srm-fso-createTemplate', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2094, '评分模板定义', 308, 0, '/scoreTemplateDefinition', NULL, 1, 0, 'F', '0', '0', 'srm-fso-createScoreTemplate', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2095, '查询寻源大厅', 1768, 0, '/toFindTheSourceHall', NULL, 1, 0, 'F', '0', '0', 'srm-fso-findSourceHall', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2096, '供应商报价操作', 1769, 0, '/supplierOffer', NULL, 1, 0, 'F', '0', '0', 'srm-fso-UpdatesupplierOffer', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2097, '寻源过程控制', 1770, 0, '/searchForTheSourceProcessControl', NULL, 1, 0, 'F', '0', '0', 'srm-fso-searchControl', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2098, '核价操作', 1771, 0, '/pricing', NULL, 1, 0, 'F', '0', '0', 'srm-fso-updatePricing', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2099, '查询招标大厅', 1772, 0, '/biddingHallZ', NULL, 1, 0, 'F', '0', '0', 'srm-call-findBidHallZ', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2100, '查询投标大厅', 1773, 0, '/biddingHallT', NULL, 1, 0, 'F', '0', '0', 'srm-call-findBidHallT', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2101, '招标变更操作', 1774, 0, '/biddingEdit', NULL, 1, 0, 'F', '0', '0', 'srm-call-updateBidHallZ', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2102, '开标操作', 1775, 0, '/bidOpening', NULL, 1, 0, 'F', '0', '0', 'srm-call-openBid', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2103, '专家评分操作', 1776, 0, '/expertScore', NULL, 1, 0, 'F', '0', '0', 'srm-call-aduitScore', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2104, '定标操作', 1777, 0, '/target', NULL, 1, 0, 'F', '0', '0', 'srm-call-confirmBid', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2105, '合同编辑操作', 1778, 0, '/ContractEdit', NULL, 1, 0, 'F', '0', '0', 'srm-call-updateContractEdit', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2106, '合同签署', 1779, 0, '/ContractSign', NULL, 1, 0, 'F', '0', '0', 'srm-call-updateContractSign', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2107, '采购订单编辑操作', 2032, 0, '/purchaseOrderEdit', NULL, 1, 0, 'F', '0', '0', 'srm-pur-orderEdit', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2108, '客户订单确认', 2033, 0, '/clientOrderAffirm', NULL, 1, 0, 'F', '0', '0', 'srm--pur-clientOrderAffirm', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2109, '送货单编辑操作', 2034, 0, '/deliverySlipEdit', NULL, 1, 0, 'F', '0', '0', 'srm-pur-deliverySlipEdit', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2110, '创建开票申请操作', 2035, 0, '/invoicingApplication', NULL, 1, 0, 'F', '0', '0', 'srm-pur-createInvoicingApplication', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2111, '开票申请审核操作', 2036, 0, '/invoicingApplicationAudit', NULL, 1, 0, 'F', '0', '0', 'srm-pur-auditInvoicingApplicationAudit', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2112, '审核应收发票操作', 2037, 0, '/auditInvoiceReceivable', NULL, 1, 0, 'F', '0', '0', 'srm-pur-auditInvoiceReceivable', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2113, '审核应付发票操作', 2038, 0, '/auditPayableInvoice', NULL, 1, 0, 'F', '0', '0', 'srm-pur-auditPayableInvoice', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2114, '收款确认操作', 2039, 0, 'receiptConfirmation', NULL, 1, 0, 'F', '0', '0', 'srm-pur-confirmReceiptConfirmation', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2115, '合同模板查询', 1778, 0, '/ContracTemplate', NULL, 1, 0, 'F', '0', '0', 'srm-call-findContracFind', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2116, '合同查询', 1779, 0, '/ContracFind', NULL, 1, 0, 'F', '0', '0', 'srm-call-findTemplateContracFind', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2117, '生产任务', 1543, 0, '/', NULL, 1, 0, 'F', '0', '0', 'processTask:show', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2118, '工序管理', 1542, 0, '/', NULL, 1, 0, 'F', '0', '0', 'processTask:show', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2119, '工艺路线', 1568, 0, '/', NULL, 1, 0, 'F', '0', '0', 'processTask:show', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2120, '品质检验', 1546, 0, '/', NULL, 1, 0, 'F', '0', '0', 'processTask:show', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2121, '下料工序', 1545, 0, '/', NULL, 1, 0, 'F', '0', '0', 'processTask:show', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2122, '焊接工序', 1547, 0, '/', NULL, 1, 0, 'F', '0', '0', 'processTask:show', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2123, '喷塑工序', 1548, 0, '/', NULL, 1, 0, 'F', '0', '0', 'processTask:show', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2124, '委外发出', 1551, 0, '/', NULL, 1, 0, 'F', '0', '0', 'processTask:show', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2125, '委外接收', 1549, 0, '/', NULL, 1, 0, 'F', '0', '0', 'processTask:show', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2126, '成品入库', 1550, 0, '/', NULL, 1, 0, 'F', '0', '0', 'processTask:show', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2127, '下料日志', 1552, 0, '/', NULL, 1, 0, 'F', '0', '0', 'process:show', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2128, '焊接日志', 1553, 0, '/', NULL, 1, 0, 'F', '0', '0', 'process:show', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2129, '喷塑日志', 1554, 0, '/', NULL, 1, 0, 'F', '0', '0', 'process:show', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2130, '委外接收日志', 1555, 0, '/', NULL, 1, 0, 'F', '0', '0', 'process:show', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2131, '委外发出日志', 1556, 0, '/', NULL, 1, 0, 'F', '0', '0', 'process:show', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2132, '成品入库日志', 1557, 0, '/', NULL, 1, 0, 'F', '0', '0', 'process:show', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2133, '品检日志', 1558, 0, '/', NULL, 1, 0, 'F', '0', '0', 'process:log', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2134, '多条件查询客户基础信息', 1523, 0, 'crm/showCustomerBaseInfoList', NULL, 1, 0, 'F', '0', '0', 'crm:searchBaseCustomer', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2135, '新增客户基础信息', 1523, 0, '/crm/addCustomerBaseInfo', NULL, 1, 0, 'F', '0', '0', 'crm:addBaseCustomer', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2136, '删除客户基础信息', 1523, 0, 'crm/delCustomerBaseInfo', NULL, 1, 0, 'F', '0', '0', 'crm:delBaseCustomer', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2137, '编辑客户基础信息', 1523, 0, 'crm/editCustomerBaseInfo', NULL, 1, 0, 'F', '0', '0', 'crm:editBaseCustomer', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2138, '展示客户开票信息', 1523, 0, 'crm/showBillingInformationList', NULL, 1, 0, 'F', '0', '0', 'crm:showBillInfo', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2139, '新增客户开票信息', 1523, 0, 'crm/addBillingInformation', NULL, 1, 0, 'F', '0', '0', 'crm:addBillInfo', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2140, '编辑客户开票信息', 1523, 0, 'crm/editBillingInformation', NULL, 1, 0, 'F', '0', '0', 'crm:editBillInfo', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2141, '删除客户开票信息', 1523, 0, 'crm/delBillingInformation', NULL, 1, 0, 'F', '0', '0', 'crm:delBillInfo', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2142, '展示客户通讯地址', 1523, 0, 'crm/showPostalAddressList', NULL, 1, 0, 'F', '0', '0', 'crm:showPastolAddressInfo', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2143, '新增客户通讯地址', 1523, 0, 'crm/addPostalAddress', NULL, 1, 0, 'F', '0', '0', 'crm:addPastolAddressInfo', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2144, '删除客户通讯地址', 1523, 0, 'crm/delPostalAddress', NULL, 1, 0, 'F', '0', '0', 'crm:delPastolAddressInfo', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2145, '编辑客户通讯地址', 1523, 0, 'crm/editPostalAddress', NULL, 1, 0, 'F', '0', '0', 'crm:editPastolAddressInfo', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2146, '展示客户组织架构及其关键人', 1523, 0, 'crm/showDepartmentMemberByCustomerId', NULL, 1, 0, 'F', '0', '0', 'crm:showCustomerMember', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2147, '新增客户成员信息', 1525, 0, 'crm/addDepartmentMemberInfo', NULL, 1, 0, 'F', '0', '0', 'crm:addCustomerMember', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2148, '编辑客户成员信息', 1525, 0, 'crm/editDepartmentMemberInfo', NULL, 1, 0, 'F', '0', '0', 'crm:editCustomerMember', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2149, '删除客户成员信息', 1525, 0, 'crm/delDepartmentMemberInfo', NULL, 1, 0, 'F', '0', '0', 'crm:delCustomerMember', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2150, '多条件查询客户成员信息', 1525, 0, 'crm/showDepartmentMemberList', NULL, 1, 0, 'F', '0', '0', 'crm:searchCustomerMember', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2151, '多条件查询客户组织架构', 1524, 0, 'crm/showDepartmentInfoList', NULL, 1, 0, 'F', '0', '0', 'crm:searchCustomerDepartment', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2152, '新增客户组织架构', 1524, 0, 'crm/addDepartmentInfos', NULL, 1, 0, 'F', '0', '0', 'crm:addCustomerDepartment', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2153, '编辑客户组织架构', 1524, 0, 'crm/editDepartmentInfos', NULL, 1, 0, 'F', '0', '0', 'crm:editCustomerDepartment', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2154, '删除客户组织架构', 1524, 0, 'crm/delDepartmentInfos', NULL, 1, 0, 'F', '0', '0', 'crm:delCustomerDepartment', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2155, '展示客户基础信息(资料卡)', 1523, 0, 'crm/showCustomerBaseInfoList', NULL, 1, 0, 'F', '0', '0', 'crm:showBaseCustomer', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2156, '展示客户组织架构', 1524, 0, 'crm/showDepartmentInfoList', NULL, 1, 0, 'F', '0', '0', 'crm:showCustomerDepartment', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2157, '展示客户组织架构(资料卡)', 1523, 0, 'crm/showDepartmentInfoList', NULL, 1, 0, 'F', '0', '0', 'crm:showCustomerDepartment', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2158, '展示文件类型', 1530, 0, 'crm/showAllFileTypeInfo', NULL, 1, 0, 'F', '0', '0', 'crm:showFileType', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2159, '新增文件类型', 1530, 0, 'crm/addFileTypeInfo', NULL, 1, 0, 'F', '0', '0', 'crm:addFileType', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2160, '编辑文件类型', 1530, 0, 'crm/editFileTypeInfo', NULL, 1, 0, 'F', '0', '0', 'crm:editFileType', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2161, '删除文件类型', 1530, 0, 'crm/delFileTypeInfo', NULL, 1, 0, 'F', '0', '0', 'crm:delFileType', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2162, '展示项目基础信息', 1527, 0, 'crm/showProjectBasicInfo', NULL, 1, 0, 'F', '0', '0', 'crm:showProjectBaseInfo', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2163, '删除项目基础信息', 1527, 0, 'crm/delProjectBasicInfo', NULL, 1, 0, 'F', '0', '0', 'crm:delProjectBaseInfo', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2164, '多条件查询项目基础信息', 1527, 0, 'crm/showProjectBasicInfo', NULL, 1, 0, 'F', '0', '0', 'crm:searchProjectBaseInfo', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2165, '添加项目基础信息', 1527, 0, 'crm/addProjectBasicInfo', NULL, 1, 0, 'F', '0', '0', 'crm:addProjectBaseInfo', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2166, '编辑项目基础信息', 1527, 0, 'crm/editProjectBasicInfo', NULL, 1, 0, 'F', '0', '0', 'crm:editProjectBaseInfo', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2167, '展示商务信息存档', 1528, 0, 'crm/showProjectBasicInfo', NULL, 1, 0, 'F', '0', '0', 'crm:showProjectBaseInfo', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2168, '查看文件', 1528, 0, 'crm/showFileDetailsList', NULL, 1, 0, 'F', '0', '0', 'crm:showProjectBusinessFile', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2169, '上传文件', 1528, 0, 'crm/testFile', NULL, 1, 0, 'F', '0', '0', 'crm:uploadProjectBusinessFile', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2170, '下载文件', 1528, 0, '/', NULL, 1, 0, 'F', '0', '0', 'crm:downloadFile', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2171, '删除文件', 1528, 0, '/', NULL, 1, 0, 'F', '0', '0', 'crm:delFile', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2172, '新增文件项', 1528, 0, 'crm/addFileType', NULL, 1, 0, 'F', '0', '0', 'crm:addProjectBusinessFile', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2173, '删除文件项', 1528, 0, 'crm/delFileType', NULL, 1, 0, 'F', '0', '0', 'crm:delProjectBusinessFile', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2174, '查询', 1570, 0, '/', NULL, 1, 0, 'F', '0', '0', 'mk:data', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2175, '导入', 1570, 0, '/', NULL, 1, 0, 'F', '0', '0', 'mk:import', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2176, '查询', 1571, 0, '/', NULL, 1, 0, 'F', '0', '0', 'mk:tableCol:data', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2177, '新增', 1571, 0, '/', NULL, 1, 0, 'F', '0', '0', 'mk:tableCol:add', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2178, '修改', 1571, 0, '/', NULL, 1, 0, 'F', '0', '0', 'mk:tableCol:update', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2179, '删除', 1571, 0, '/', NULL, 1, 0, 'F', '0', '0', 'mk:tableCol:delete', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2180, '查询', 1572, 0, '/', NULL, 1, 0, 'F', '0', '0', 'mk:tableRole:data', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2181, '修改', 1572, 0, '/', NULL, 1, 0, 'F', '0', '0', 'mk:tableRole:update', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2182, '删除', 1570, 0, '/', NULL, 1, 0, 'F', '0', '0', 'mk:delete', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2183, '展示公司资质文件', 1529, 0, 'crm/showAllFileType', NULL, 1, 0, 'F', '0', '0', 'crm:showCompanyFile', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2184, '新增公司文件项', 1529, 0, 'crm/addFileType', NULL, 1, 0, 'F', '0', '0', 'crm:addCompanyFile', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2185, '删除公司文件项', 1529, 0, 'crm/delFileType', NULL, 1, 0, 'F', '0', '0', 'crm:delCompanyFile', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2186, '查看文件', 1529, 0, 'crm/showFileDetailsList', NULL, 1, 0, 'F', '0', '0', 'crm:showCompanyFileList', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2187, '上传文件', 1529, 0, 'crm/testFile', NULL, 1, 0, 'F', '0', '0', 'crm:uploadCompanyFile', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2188, '下载文件', 1529, 0, '/', NULL, 1, 0, 'F', '0', '0', 'crm:downloadCompanyFile', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2189, '删除文件', 1529, 0, 'crm/delMeetingMinutes', NULL, 1, 0, 'F', '0', '0', 'crm:delCompanyFileList', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2190, '工艺路线', 1568, 0, '/', NULL, 1, 0, 'F', '0', '0', 'processTask:show', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2191, '新增生产任务', 1543, 0, '/', NULL, 1, 0, 'F', '0', '0', 'processTask:power', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2192, '返修成功申请', 1564, 0, '/', NULL, 1, 0, 'F', '0', '0', 'processTask:show', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2193, '返修成功审批', 1565, 0, '/', NULL, 1, 0, 'F', '0', '0', 'processTask:show', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2194, '返修成功日志', 1569, 0, '/', NULL, 1, 0, 'F', '0', '0', 'processTask:show', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2195, '工艺路线日志', 262, 0, '/', NULL, 1, 0, 'F', '0', '0', 'processTask:show', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2196, '工序管理日志', 263, 0, '/', NULL, 1, 0, 'F', '0', '0', 'processTask:show', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2197, '供应商管理', 264, 0, '/', NULL, 1, 0, 'F', '0', '0', 'processTask:show', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2198, '下料工序批量', 265, 0, '/', NULL, 1, 0, 'F', '0', '0', 'processTask:show', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2199, '委外发出审核', 1562, 0, '/', NULL, 1, 0, 'F', '0', '0', 'process', '#', '', NULL, '', NULL, '');
INSERT INTO `sys_menu` VALUES (2301, '下料工序批量', 1393, 3, '/newCuttingInfo', NULL, 1, 0, 'C', '0', '0', NULL, 'more', 'admin', '2021-09-02 20:48:16', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (2302, '产线管理', 1428, 1, '/skq/lineManager', NULL, 1, 0, 'C', '0', '0', NULL, 'more', 'admin', '2021-09-03 08:13:08', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (2303, '工位管理', 1428, 2, '/skq/stationManager', NULL, 1, 0, 'C', '0', '0', NULL, 'more', 'admin', '2021-09-03 08:14:58', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (2304, '编辑', 1709, 1, '', NULL, 1, 0, 'F', '0', '0', 'workOrderManagement:edit', '#', 'admin', '2021-09-03 10:14:19', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (2305, '删除', 1709, 4, '', NULL, 1, 0, 'F', '0', '0', 'workOrderManagement:delete', '#', 'admin', '2021-09-03 10:14:38', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (2306, '新增', 1709, 2, '', NULL, 1, 0, 'F', '0', '0', 'workOrderManagement:add', '#', 'admin', '2021-09-03 10:15:19', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (2307, '查询', 1709, 3, '', NULL, 1, 0, 'F', '0', '0', 'workOrderManagement:list', '#', 'admin', '2021-09-03 10:15:57', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (2308, '自动转账', 3, 33, 'autoTransfer', 'finance/autoTransfer', 1, 0, 'C', '0', '0', NULL, '#', 'admin', '2021-08-24 11:29:01', 'admin', '2021-08-25 09:30:24', '');
INSERT INTO `sys_menu` VALUES (2309, '新增自动转账', 3, 22, '/addAutoTransfer', 'finance/autoTransfer/add', 1, 0, 'C', '1', '0', NULL, '#', 'admin', '2021-08-24 14:23:36', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (2310, '凭证摊销', 3, 22, 'amortizationScheme', 'finance/amortizationScheme', 1, 0, 'C', '0', '0', NULL, '#', 'admin', '2021-08-25 09:29:26', 'admin', '2021-08-25 09:30:59', '');
INSERT INTO `sys_menu` VALUES (2311, '新增凭证摊销', 3, 33, '/addAmortizationScheme', 'finance/amortizationScheme/add', 1, 0, 'C', '1', '0', NULL, '#', 'admin', '2021-08-25 10:08:15', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (2312, '新增期末调汇', 3, 22, '/addExchangeScheme', 'finance/exchangeScheme/add', 1, 0, 'C', '1', '0', NULL, '#', 'admin', '2021-08-26 09:57:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (2313, '职级管理', 2319, 8, 'deptRank', 'system/deptRank/index', 1, 0, 'C', '0', '0', 'manage:deptRank:list', 'table', 'admin', '2021-08-26 14:59:21', 'admin', '2021-08-26 15:00:25', '职级菜单');
INSERT INTO `sys_menu` VALUES (2314, '职级查询', 2313, 1, '#', '', 1, 0, 'F', '0', '0', 'manage:deptRank:query', '#', 'admin', '2021-08-26 14:59:21', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2315, '职级新增', 2313, 2, '#', '', 1, 0, 'F', '0', '0', 'manage:deptRank:add', '#', 'admin', '2021-08-26 14:59:21', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2316, '职级修改', 2313, 3, '#', '', 1, 0, 'F', '0', '0', 'manage:deptRank:edit', '#', 'admin', '2021-08-26 14:59:21', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2317, '职级删除', 2313, 4, '#', '', 1, 0, 'F', '0', '0', 'manage:deptRank:remove', '#', 'admin', '2021-08-26 14:59:21', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2318, '职级导出', 2313, 5, '#', '', 1, 0, 'F', '0', '0', 'manage:deptRank:export', '#', 'admin', '2021-08-26 14:59:21', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2319, '机构管理', 0, 2, 'org', NULL, 1, 0, 'M', '0', '0', NULL, 'chart', 'admin', '2021-08-26 15:29:43', 'admin', '2021-08-26 15:30:02', '');
INSERT INTO `sys_menu` VALUES (2320, '新增结转损益', 3, 22, '/addPlscheme', 'finance/plscheme/add', 1, 0, 'C', '1', '0', NULL, '#', 'admin', '2021-08-26 15:41:53', 'admin', '2021-08-27 07:58:44', '');
INSERT INTO `sys_menu` VALUES (2321, '核算组织', 2319, 1, 'organization', 'finance/organization/index', 1, 0, 'C', '0', '0', 'finance:organization:list', 'build', 'admin', '2021-08-26 15:42:54', 'admin', '2021-08-26 15:45:08', '组织管理菜单');
INSERT INTO `sys_menu` VALUES (2322, '组织管理查询', 2321, 1, '#', '', 1, 0, 'F', '0', '0', 'finance:organization:query', '#', 'admin', '2021-08-26 15:42:54', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2323, '组织管理新增', 2321, 2, '#', '', 1, 0, 'F', '0', '0', 'finance:organization:add', '#', 'admin', '2021-08-26 15:42:54', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2324, '组织管理修改', 2321, 3, '#', '', 1, 0, 'F', '0', '0', 'finance:organization:edit', '#', 'admin', '2021-08-26 15:42:54', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2325, '组织管理删除', 2321, 4, '#', '', 1, 0, 'F', '0', '0', 'finance:organization:remove', '#', 'admin', '2021-08-26 15:42:54', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2326, '组织管理导出', 2321, 5, '#', '', 1, 0, 'F', '0', '0', 'finance:organization:export', '#', 'admin', '2021-08-26 15:42:54', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2327, '期末调汇', 3, 22, '/finance/exchangeScheme', 'finance/exchangeScheme', 1, 0, 'C', '0', '0', NULL, '#', 'admin', '2021-08-30 14:17:16', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (2328, '结转损益', 3, 22, '/finance/plscheme', 'finance/plscheme', 1, 0, 'C', '0', '0', NULL, '#', 'admin', '2021-08-30 14:29:02', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (2329, '修改结转损益', 3, 33, '/editPlscheme', 'finance/plscheme/edit', 1, 0, 'C', '1', '0', NULL, '#', 'admin', '2021-08-30 15:52:35', 'admin', '2021-08-30 15:54:42', '');
INSERT INTO `sys_menu` VALUES (2330, '修改自动转账', 3, 22, '/editAutoTransfer', 'finance/autoTransfer/edit', 1, 0, 'C', '0', '0', NULL, '#', 'admin', '2021-08-30 18:53:43', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (2331, '修改期末调汇', 3, 22, '/editExchangeScheme', 'finance/exchangeScheme/edit', 1, 0, 'C', '1', '0', NULL, '#', 'admin', '2021-08-30 18:55:00', 'admin', '2021-08-31 10:48:10', '');
INSERT INTO `sys_menu` VALUES (2332, '总账期末结账', 3, 22, '/finance/closingAccount', 'finance/closingAccount', 1, 0, 'C', '0', '0', NULL, '#', 'admin', '2021-08-31 11:34:15', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (2333, '总账分类', 3, 22, '/finance/totalbalance', 'finance/totalbalance', 1, 0, 'C', '0', '0', NULL, '#', 'admin', '2021-08-31 13:54:43', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (2334, '核算维度明细分类', 3, 22, '/finance/propertybalance', 'finance/propertybalance', 1, 0, 'C', '0', '0', NULL, '#', 'admin', '2021-08-31 13:55:17', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (2335, '明细分类账', 3, 22, '/finance/voucherListDetail', 'finance/voucherListDetail', 1, 0, 'C', '0', '0', NULL, '#', 'admin', '2021-09-01 15:21:46', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (2336, '调整期间管理', 3, 22, '/finance/adjustPeriod', 'finance/adjustPeriod', 1, 0, 'C', '0', '0', NULL, '#', 'admin', '2021-09-03 11:29:50', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (2337, '调整期间凭证录入-新增', 3, 33, '/addAdjustPeriodVoucher', 'finance/adjustPeriodVoucher/add', 1, 0, 'C', '1', '0', NULL, '#', 'admin', '2021-09-03 17:19:27', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (2338, '销售合同管理', 1390, 32, '/showSalesContractManagement', NULL, 1, 0, 'C', '0', '0', NULL, 'more', 'test', '2021-09-04 08:15:26', 'test', '2021-09-04 08:19:30', '');
INSERT INTO `sys_menu` VALUES (2339, '凭证字', 3, 55, '/finance/voucherGroup', 'finance/voucherGroup', 1, 0, 'C', '0', '0', NULL, '#', 'admin', '2021-09-04 09:14:24', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (2340, '科目', 3, 33, '/finance/accountTable', 'finance/accountTable', 1, 0, 'C', '0', '0', NULL, '#', 'admin', '2021-09-04 09:19:37', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (2341, '调整期间凭证查询', 3, 33, '/finance/adjustPeriodVlist', 'finance/adjustPeriodVoucher/query', 1, 0, 'C', '0', '0', NULL, '#', 'admin', '2021-09-04 10:11:17', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (2342, '调整期间凭证修改', 3, 20, '/editAdjustPeriodVoucher', 'finance/adjustPeriodVoucher/edit', 1, 0, 'C', '1', '0', NULL, '#', 'admin', '2021-09-04 14:25:40', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (2343, '调整期间凭证过账', 3, 22, '/finance/adjustPeriodVposting', 'finance/adjustPeriodVoucher/posting', 1, 0, 'C', '0', '0', NULL, '#', 'admin', '2021-09-04 14:37:27', NULL, NULL, '');


-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`  (
  `notice_id` int NOT NULL AUTO_INCREMENT COMMENT '公告ID',
  `notice_title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '公告标题',
  `notice_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '公告类型（1通知 2公告）',
  `notice_content` longblob NULL COMMENT '公告内容',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`notice_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '通知公告表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_notice
-- ----------------------------

-- ----------------------------
-- Table structure for sys_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_log`;
CREATE TABLE `sys_oper_log`  (
  `oper_id` bigint NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '模块标题',
  `business_type` int NULL DEFAULT 0 COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '方法名称',
  `request_method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '请求方式',
  `operator_type` int NULL DEFAULT 0 COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '操作人员',
  `dept_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '部门名称',
  `oper_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '请求URL',
  `oper_ip` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '主机地址',
  `oper_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '操作地点',
  `oper_param` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '请求参数',
  `json_result` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '返回参数',
  `status` int NULL DEFAULT 0 COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '错误消息',
  `oper_time` datetime NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`oper_id`) USING BTREE
) ENGINE = InnoDB  CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '操作日志记录' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for sys_organization
-- ----------------------------
DROP TABLE IF EXISTS `sys_organization`;
CREATE TABLE `sys_organization`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '组织ID',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组织名称',
  `org_code` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组织编码',
  `cmp_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组织形态 1总公司 2公司 3工厂 4事业部 5分公司 6办事处 7部分 8其他',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地址',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `leader` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '负责人',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系人电话',
  `post_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮编',
  `org_type` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '核算组织 1法人 2利润中心',
  `legal_parent_id` int NULL DEFAULT NULL COMMENT '所属法人ID',
  `legal_parent_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '所属法人名称',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `status` int NULL DEFAULT NULL COMMENT '数据状态',
  `create_user` int NULL DEFAULT NULL COMMENT '创建人',
  `update_user` int NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `org_code`(`org_code`, `name`) USING BTREE
) ENGINE = InnoDB  CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '组织管理' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_organization
-- ----------------------------

-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post`  (
  `post_id` bigint NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
  `post_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '岗位编码',
  `post_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '岗位名称',
  `post_sort` int NOT NULL COMMENT '显示顺序',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`post_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '岗位信息表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_post
-- ----------------------------
INSERT INTO `sys_post` VALUES (1, 'ceo', '董事长', 1, '0', 'admin', '2021-07-06 16:53:36', '', NULL, '');
INSERT INTO `sys_post` VALUES (2, 'se', '项目经理', 2, '0', 'admin', '2021-07-06 16:53:36', '', NULL, '');
INSERT INTO `sys_post` VALUES (3, 'hr', '人力资源', 3, '0', 'admin', '2021-07-06 16:53:36', '', NULL, '');
INSERT INTO `sys_post` VALUES (4, 'user', '普通员工', 4, '0', 'admin', '2021-07-06 16:53:36', '', NULL, '');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色权限字符串',
  `role_sort` int NOT NULL COMMENT '显示顺序',
  `data_scope` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  `menu_check_strictly` tinyint(1) NULL DEFAULT 1 COMMENT '菜单树选择项是否关联显示',
  `dept_check_strictly` tinyint(1) NULL DEFAULT 1 COMMENT '部门树选择项是否关联显示',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色信息表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', 'admin', 1, '1', 1, 1, '0', '0', 'admin', '2021-07-06 16:53:36', '', NULL, '超级管理员');
INSERT INTO `sys_role` VALUES (2, '普通角色', 'common', 2, '2', 1, 1, '0', '0', 'admin', '2021-07-06 16:53:36', 'admin', '2021-07-09 14:10:23', '普通角色');
INSERT INTO `sys_role` VALUES (3, 'SRM未认证权限', 'srmUnverified', 3, '1', 1, 1, '0', '0', 'admin', '2021-09-01 15:47:35', 'admin', '2021-09-01 15:50:22', 'SRM未认证权限');
INSERT INTO `sys_role` VALUES (4, 'SRM已认证权限', 'srmAuthenticated', 4, '1', 1, 1, '0', '0', 'admin', '2021-09-01 16:22:19', 'admin', '2021-09-03 10:07:16', 'SRM已认证权限');
INSERT INTO `sys_role` VALUES (5, 'SRM采购员', 'srmPurchase', 5, '1', 1, 1, '0', '0', 'admin', '2021-09-01 16:23:04', 'admin', '2021-09-01 16:23:14', 'SRM采购员');
INSERT INTO `sys_role` VALUES (6, 'SRM采购管理员', 'srmPurMmanagement', 6, '1', 1, 1, '0', '0', 'admin', '2021-09-01 16:25:13', 'admin', '2021-09-03 10:10:59', 'SRM采购管理员');
INSERT INTO `sys_role` VALUES (7, '阿萨德', '阿萨德', 3, '1', 0, 0, '0', '2', 'admin', '2021-09-01 17:20:53', 'admin', '2021-09-01 17:33:31', NULL);
INSERT INTO `sys_role` VALUES (8, '财务', '7', 7, '1', 1, 1, '0', '0', 'admin', '2021-09-03 14:07:00', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept`  (
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `dept_id` bigint NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`role_id`, `dept_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色和部门关联表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------
INSERT INTO `sys_role_dept` VALUES (2, 100);
INSERT INTO `sys_role_dept` VALUES (2, 101);
INSERT INTO `sys_role_dept` VALUES (2, 105);
INSERT INTO `sys_role_dept` VALUES (3, 113);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `menu_id` bigint NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色和菜单关联表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (2, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2);
INSERT INTO `sys_role_menu` VALUES (2, 3);
INSERT INTO `sys_role_menu` VALUES (2, 100);
INSERT INTO `sys_role_menu` VALUES (2, 101);
INSERT INTO `sys_role_menu` VALUES (2, 102);
INSERT INTO `sys_role_menu` VALUES (2, 103);
INSERT INTO `sys_role_menu` VALUES (2, 104);
INSERT INTO `sys_role_menu` VALUES (2, 105);
INSERT INTO `sys_role_menu` VALUES (2, 106);
INSERT INTO `sys_role_menu` VALUES (2, 107);
INSERT INTO `sys_role_menu` VALUES (2, 108);
INSERT INTO `sys_role_menu` VALUES (2, 109);
INSERT INTO `sys_role_menu` VALUES (2, 110);
INSERT INTO `sys_role_menu` VALUES (2, 111);
INSERT INTO `sys_role_menu` VALUES (2, 113);
INSERT INTO `sys_role_menu` VALUES (2, 117);
INSERT INTO `sys_role_menu` VALUES (2, 500);
INSERT INTO `sys_role_menu` VALUES (2, 501);
INSERT INTO `sys_role_menu` VALUES (2, 1001);
INSERT INTO `sys_role_menu` VALUES (2, 1002);
INSERT INTO `sys_role_menu` VALUES (2, 1003);
INSERT INTO `sys_role_menu` VALUES (2, 1004);
INSERT INTO `sys_role_menu` VALUES (2, 1005);
INSERT INTO `sys_role_menu` VALUES (2, 1006);
INSERT INTO `sys_role_menu` VALUES (2, 1007);
INSERT INTO `sys_role_menu` VALUES (2, 1008);
INSERT INTO `sys_role_menu` VALUES (2, 1009);
INSERT INTO `sys_role_menu` VALUES (2, 1010);
INSERT INTO `sys_role_menu` VALUES (2, 1011);
INSERT INTO `sys_role_menu` VALUES (2, 1012);
INSERT INTO `sys_role_menu` VALUES (2, 1013);
INSERT INTO `sys_role_menu` VALUES (2, 1014);
INSERT INTO `sys_role_menu` VALUES (2, 1015);
INSERT INTO `sys_role_menu` VALUES (2, 1016);
INSERT INTO `sys_role_menu` VALUES (2, 1017);
INSERT INTO `sys_role_menu` VALUES (2, 1018);
INSERT INTO `sys_role_menu` VALUES (2, 1019);
INSERT INTO `sys_role_menu` VALUES (2, 1020);
INSERT INTO `sys_role_menu` VALUES (2, 1021);
INSERT INTO `sys_role_menu` VALUES (2, 1022);
INSERT INTO `sys_role_menu` VALUES (2, 1023);
INSERT INTO `sys_role_menu` VALUES (2, 1024);
INSERT INTO `sys_role_menu` VALUES (2, 1025);
INSERT INTO `sys_role_menu` VALUES (2, 1026);
INSERT INTO `sys_role_menu` VALUES (2, 1027);
INSERT INTO `sys_role_menu` VALUES (2, 1028);
INSERT INTO `sys_role_menu` VALUES (2, 1029);
INSERT INTO `sys_role_menu` VALUES (2, 1030);
INSERT INTO `sys_role_menu` VALUES (2, 1031);
INSERT INTO `sys_role_menu` VALUES (2, 1032);
INSERT INTO `sys_role_menu` VALUES (2, 1033);
INSERT INTO `sys_role_menu` VALUES (2, 1034);
INSERT INTO `sys_role_menu` VALUES (2, 1035);
INSERT INTO `sys_role_menu` VALUES (2, 1036);
INSERT INTO `sys_role_menu` VALUES (2, 1037);
INSERT INTO `sys_role_menu` VALUES (2, 1038);
INSERT INTO `sys_role_menu` VALUES (2, 1039);
INSERT INTO `sys_role_menu` VALUES (2, 1040);
INSERT INTO `sys_role_menu` VALUES (2, 1041);
INSERT INTO `sys_role_menu` VALUES (2, 1042);
INSERT INTO `sys_role_menu` VALUES (2, 1043);
INSERT INTO `sys_role_menu` VALUES (2, 1044);
INSERT INTO `sys_role_menu` VALUES (2, 1045);
INSERT INTO `sys_role_menu` VALUES (2, 1046);
INSERT INTO `sys_role_menu` VALUES (2, 1047);
INSERT INTO `sys_role_menu` VALUES (2, 1048);
INSERT INTO `sys_role_menu` VALUES (2, 1049);
INSERT INTO `sys_role_menu` VALUES (2, 1050);
INSERT INTO `sys_role_menu` VALUES (2, 1051);
INSERT INTO `sys_role_menu` VALUES (2, 1052);
INSERT INTO `sys_role_menu` VALUES (2, 1053);
INSERT INTO `sys_role_menu` VALUES (2, 1054);
INSERT INTO `sys_role_menu` VALUES (2, 1061);
INSERT INTO `sys_role_menu` VALUES (2, 1062);
INSERT INTO `sys_role_menu` VALUES (2, 1063);
INSERT INTO `sys_role_menu` VALUES (2, 1064);
INSERT INTO `sys_role_menu` VALUES (2, 1065);
INSERT INTO `sys_role_menu` VALUES (2, 1066);
INSERT INTO `sys_role_menu` VALUES (2, 1067);
INSERT INTO `sys_role_menu` VALUES (2, 1068);
INSERT INTO `sys_role_menu` VALUES (2, 1069);
INSERT INTO `sys_role_menu` VALUES (2, 1070);
INSERT INTO `sys_role_menu` VALUES (2, 1071);
INSERT INTO `sys_role_menu` VALUES (2, 1072);
INSERT INTO `sys_role_menu` VALUES (2, 1073);
INSERT INTO `sys_role_menu` VALUES (2, 1074);
INSERT INTO `sys_role_menu` VALUES (2, 1075);
INSERT INTO `sys_role_menu` VALUES (2, 1076);
INSERT INTO `sys_role_menu` VALUES (2, 1077);
INSERT INTO `sys_role_menu` VALUES (2, 1078);
INSERT INTO `sys_role_menu` VALUES (2, 1079);
INSERT INTO `sys_role_menu` VALUES (2, 1080);
INSERT INTO `sys_role_menu` VALUES (2, 1081);
INSERT INTO `sys_role_menu` VALUES (2, 1082);
INSERT INTO `sys_role_menu` VALUES (2, 1083);
INSERT INTO `sys_role_menu` VALUES (2, 1084);
INSERT INTO `sys_role_menu` VALUES (2, 1085);
INSERT INTO `sys_role_menu` VALUES (2, 1086);
INSERT INTO `sys_role_menu` VALUES (2, 1087);
INSERT INTO `sys_role_menu` VALUES (2, 1088);
INSERT INTO `sys_role_menu` VALUES (2, 1089);
INSERT INTO `sys_role_menu` VALUES (2, 1090);
INSERT INTO `sys_role_menu` VALUES (2, 1091);
INSERT INTO `sys_role_menu` VALUES (2, 1092);
INSERT INTO `sys_role_menu` VALUES (2, 1093);
INSERT INTO `sys_role_menu` VALUES (2, 1094);
INSERT INTO `sys_role_menu` VALUES (2, 1095);
INSERT INTO `sys_role_menu` VALUES (2, 1096);
INSERT INTO `sys_role_menu` VALUES (2, 1097);
INSERT INTO `sys_role_menu` VALUES (2, 1098);
INSERT INTO `sys_role_menu` VALUES (2, 1099);
INSERT INTO `sys_role_menu` VALUES (2, 1100);
INSERT INTO `sys_role_menu` VALUES (2, 1101);
INSERT INTO `sys_role_menu` VALUES (2, 1102);
INSERT INTO `sys_role_menu` VALUES (2, 1103);
INSERT INTO `sys_role_menu` VALUES (2, 1104);
INSERT INTO `sys_role_menu` VALUES (2, 1105);
INSERT INTO `sys_role_menu` VALUES (2, 1106);
INSERT INTO `sys_role_menu` VALUES (2, 1107);
INSERT INTO `sys_role_menu` VALUES (2, 1108);
INSERT INTO `sys_role_menu` VALUES (2, 1109);
INSERT INTO `sys_role_menu` VALUES (2, 1110);
INSERT INTO `sys_role_menu` VALUES (2, 1111);
INSERT INTO `sys_role_menu` VALUES (2, 1112);
INSERT INTO `sys_role_menu` VALUES (2, 1113);
INSERT INTO `sys_role_menu` VALUES (2, 1114);
INSERT INTO `sys_role_menu` VALUES (2, 1115);
INSERT INTO `sys_role_menu` VALUES (2, 1116);
INSERT INTO `sys_role_menu` VALUES (2, 1117);
INSERT INTO `sys_role_menu` VALUES (2, 1118);
INSERT INTO `sys_role_menu` VALUES (2, 1119);
INSERT INTO `sys_role_menu` VALUES (2, 1120);
INSERT INTO `sys_role_menu` VALUES (2, 1121);
INSERT INTO `sys_role_menu` VALUES (2, 1122);
INSERT INTO `sys_role_menu` VALUES (2, 1123);
INSERT INTO `sys_role_menu` VALUES (2, 1124);
INSERT INTO `sys_role_menu` VALUES (2, 1125);
INSERT INTO `sys_role_menu` VALUES (2, 1126);
INSERT INTO `sys_role_menu` VALUES (2, 1127);
INSERT INTO `sys_role_menu` VALUES (2, 1128);
INSERT INTO `sys_role_menu` VALUES (2, 1129);
INSERT INTO `sys_role_menu` VALUES (2, 1130);
INSERT INTO `sys_role_menu` VALUES (2, 1131);
INSERT INTO `sys_role_menu` VALUES (2, 1132);
INSERT INTO `sys_role_menu` VALUES (2, 1133);
INSERT INTO `sys_role_menu` VALUES (2, 1134);
INSERT INTO `sys_role_menu` VALUES (2, 1135);
INSERT INTO `sys_role_menu` VALUES (2, 1136);
INSERT INTO `sys_role_menu` VALUES (2, 1137);
INSERT INTO `sys_role_menu` VALUES (2, 1138);
INSERT INTO `sys_role_menu` VALUES (2, 1139);
INSERT INTO `sys_role_menu` VALUES (2, 1140);
INSERT INTO `sys_role_menu` VALUES (2, 1141);
INSERT INTO `sys_role_menu` VALUES (2, 1142);
INSERT INTO `sys_role_menu` VALUES (2, 1143);
INSERT INTO `sys_role_menu` VALUES (2, 1144);
INSERT INTO `sys_role_menu` VALUES (2, 1145);
INSERT INTO `sys_role_menu` VALUES (2, 1146);
INSERT INTO `sys_role_menu` VALUES (2, 1147);
INSERT INTO `sys_role_menu` VALUES (2, 1148);
INSERT INTO `sys_role_menu` VALUES (2, 1149);
INSERT INTO `sys_role_menu` VALUES (2, 1150);
INSERT INTO `sys_role_menu` VALUES (2, 1151);
INSERT INTO `sys_role_menu` VALUES (2, 1152);
INSERT INTO `sys_role_menu` VALUES (2, 1153);
INSERT INTO `sys_role_menu` VALUES (2, 1154);
INSERT INTO `sys_role_menu` VALUES (2, 1155);
INSERT INTO `sys_role_menu` VALUES (2, 1156);
INSERT INTO `sys_role_menu` VALUES (2, 1157);
INSERT INTO `sys_role_menu` VALUES (2, 1158);
INSERT INTO `sys_role_menu` VALUES (2, 1159);
INSERT INTO `sys_role_menu` VALUES (2, 1160);
INSERT INTO `sys_role_menu` VALUES (2, 1161);
INSERT INTO `sys_role_menu` VALUES (2, 1162);
INSERT INTO `sys_role_menu` VALUES (2, 1163);
INSERT INTO `sys_role_menu` VALUES (2, 1164);
INSERT INTO `sys_role_menu` VALUES (2, 1165);
INSERT INTO `sys_role_menu` VALUES (2, 1166);
INSERT INTO `sys_role_menu` VALUES (2, 1167);
INSERT INTO `sys_role_menu` VALUES (2, 1168);
INSERT INTO `sys_role_menu` VALUES (2, 1169);
INSERT INTO `sys_role_menu` VALUES (2, 1170);
INSERT INTO `sys_role_menu` VALUES (2, 1171);
INSERT INTO `sys_role_menu` VALUES (2, 1172);
INSERT INTO `sys_role_menu` VALUES (2, 1173);
INSERT INTO `sys_role_menu` VALUES (2, 1174);
INSERT INTO `sys_role_menu` VALUES (2, 1175);
INSERT INTO `sys_role_menu` VALUES (2, 1176);
INSERT INTO `sys_role_menu` VALUES (2, 1177);
INSERT INTO `sys_role_menu` VALUES (2, 1178);
INSERT INTO `sys_role_menu` VALUES (2, 1179);
INSERT INTO `sys_role_menu` VALUES (2, 1180);
INSERT INTO `sys_role_menu` VALUES (2, 1181);
INSERT INTO `sys_role_menu` VALUES (2, 1182);
INSERT INTO `sys_role_menu` VALUES (2, 1183);
INSERT INTO `sys_role_menu` VALUES (2, 1184);
INSERT INTO `sys_role_menu` VALUES (2, 1185);
INSERT INTO `sys_role_menu` VALUES (2, 1186);
INSERT INTO `sys_role_menu` VALUES (2, 1187);
INSERT INTO `sys_role_menu` VALUES (2, 1188);
INSERT INTO `sys_role_menu` VALUES (2, 1189);
INSERT INTO `sys_role_menu` VALUES (2, 1190);
INSERT INTO `sys_role_menu` VALUES (2, 1191);
INSERT INTO `sys_role_menu` VALUES (2, 1192);
INSERT INTO `sys_role_menu` VALUES (2, 1193);
INSERT INTO `sys_role_menu` VALUES (2, 1194);
INSERT INTO `sys_role_menu` VALUES (2, 1195);
INSERT INTO `sys_role_menu` VALUES (2, 1196);
INSERT INTO `sys_role_menu` VALUES (2, 1197);
INSERT INTO `sys_role_menu` VALUES (2, 1198);
INSERT INTO `sys_role_menu` VALUES (2, 1199);
INSERT INTO `sys_role_menu` VALUES (2, 1200);
INSERT INTO `sys_role_menu` VALUES (2, 1201);
INSERT INTO `sys_role_menu` VALUES (2, 1202);
INSERT INTO `sys_role_menu` VALUES (2, 1203);
INSERT INTO `sys_role_menu` VALUES (2, 1204);
INSERT INTO `sys_role_menu` VALUES (2, 1205);
INSERT INTO `sys_role_menu` VALUES (2, 1206);
INSERT INTO `sys_role_menu` VALUES (2, 1207);
INSERT INTO `sys_role_menu` VALUES (2, 1208);
INSERT INTO `sys_role_menu` VALUES (2, 1209);
INSERT INTO `sys_role_menu` VALUES (2, 1210);
INSERT INTO `sys_role_menu` VALUES (2, 1211);
INSERT INTO `sys_role_menu` VALUES (2, 1212);
INSERT INTO `sys_role_menu` VALUES (2, 1213);
INSERT INTO `sys_role_menu` VALUES (2, 1214);
INSERT INTO `sys_role_menu` VALUES (2, 1215);
INSERT INTO `sys_role_menu` VALUES (2, 1216);
INSERT INTO `sys_role_menu` VALUES (2, 1217);
INSERT INTO `sys_role_menu` VALUES (2, 1218);
INSERT INTO `sys_role_menu` VALUES (2, 1219);
INSERT INTO `sys_role_menu` VALUES (2, 1220);
INSERT INTO `sys_role_menu` VALUES (2, 1221);
INSERT INTO `sys_role_menu` VALUES (2, 1222);
INSERT INTO `sys_role_menu` VALUES (2, 1223);
INSERT INTO `sys_role_menu` VALUES (2, 1224);
INSERT INTO `sys_role_menu` VALUES (2, 1225);
INSERT INTO `sys_role_menu` VALUES (2, 1226);
INSERT INTO `sys_role_menu` VALUES (2, 1227);
INSERT INTO `sys_role_menu` VALUES (2, 1228);
INSERT INTO `sys_role_menu` VALUES (2, 1229);
INSERT INTO `sys_role_menu` VALUES (2, 1230);
INSERT INTO `sys_role_menu` VALUES (2, 1231);
INSERT INTO `sys_role_menu` VALUES (2, 1232);
INSERT INTO `sys_role_menu` VALUES (2, 1233);
INSERT INTO `sys_role_menu` VALUES (2, 1234);
INSERT INTO `sys_role_menu` VALUES (2, 1235);
INSERT INTO `sys_role_menu` VALUES (2, 1236);
INSERT INTO `sys_role_menu` VALUES (2, 1237);
INSERT INTO `sys_role_menu` VALUES (2, 1238);
INSERT INTO `sys_role_menu` VALUES (2, 1239);
INSERT INTO `sys_role_menu` VALUES (2, 1240);
INSERT INTO `sys_role_menu` VALUES (2, 1241);
INSERT INTO `sys_role_menu` VALUES (2, 1242);
INSERT INTO `sys_role_menu` VALUES (2, 1243);
INSERT INTO `sys_role_menu` VALUES (2, 1244);
INSERT INTO `sys_role_menu` VALUES (2, 1245);
INSERT INTO `sys_role_menu` VALUES (2, 1246);
INSERT INTO `sys_role_menu` VALUES (2, 1247);
INSERT INTO `sys_role_menu` VALUES (2, 1248);
INSERT INTO `sys_role_menu` VALUES (2, 1249);
INSERT INTO `sys_role_menu` VALUES (2, 1250);
INSERT INTO `sys_role_menu` VALUES (2, 1251);
INSERT INTO `sys_role_menu` VALUES (2, 1252);
INSERT INTO `sys_role_menu` VALUES (2, 1253);
INSERT INTO `sys_role_menu` VALUES (2, 1254);
INSERT INTO `sys_role_menu` VALUES (2, 1255);
INSERT INTO `sys_role_menu` VALUES (2, 1256);
INSERT INTO `sys_role_menu` VALUES (2, 1257);
INSERT INTO `sys_role_menu` VALUES (2, 1258);
INSERT INTO `sys_role_menu` VALUES (2, 1259);
INSERT INTO `sys_role_menu` VALUES (2, 1260);
INSERT INTO `sys_role_menu` VALUES (2, 1261);
INSERT INTO `sys_role_menu` VALUES (2, 1262);
INSERT INTO `sys_role_menu` VALUES (2, 1263);
INSERT INTO `sys_role_menu` VALUES (2, 1264);
INSERT INTO `sys_role_menu` VALUES (2, 1265);
INSERT INTO `sys_role_menu` VALUES (2, 1266);
INSERT INTO `sys_role_menu` VALUES (2, 1267);
INSERT INTO `sys_role_menu` VALUES (2, 1268);
INSERT INTO `sys_role_menu` VALUES (2, 1269);
INSERT INTO `sys_role_menu` VALUES (2, 1270);
INSERT INTO `sys_role_menu` VALUES (2, 1271);
INSERT INTO `sys_role_menu` VALUES (2, 1272);
INSERT INTO `sys_role_menu` VALUES (2, 1273);
INSERT INTO `sys_role_menu` VALUES (2, 1274);
INSERT INTO `sys_role_menu` VALUES (2, 1275);
INSERT INTO `sys_role_menu` VALUES (2, 1276);
INSERT INTO `sys_role_menu` VALUES (2, 1277);
INSERT INTO `sys_role_menu` VALUES (2, 1278);
INSERT INTO `sys_role_menu` VALUES (2, 1279);
INSERT INTO `sys_role_menu` VALUES (2, 1280);
INSERT INTO `sys_role_menu` VALUES (2, 1281);
INSERT INTO `sys_role_menu` VALUES (2, 1282);
INSERT INTO `sys_role_menu` VALUES (2, 1283);
INSERT INTO `sys_role_menu` VALUES (2, 1284);
INSERT INTO `sys_role_menu` VALUES (2, 1285);
INSERT INTO `sys_role_menu` VALUES (2, 1286);
INSERT INTO `sys_role_menu` VALUES (2, 1287);
INSERT INTO `sys_role_menu` VALUES (2, 1288);
INSERT INTO `sys_role_menu` VALUES (2, 1289);
INSERT INTO `sys_role_menu` VALUES (2, 1290);
INSERT INTO `sys_role_menu` VALUES (2, 1291);
INSERT INTO `sys_role_menu` VALUES (2, 1292);
INSERT INTO `sys_role_menu` VALUES (2, 1293);
INSERT INTO `sys_role_menu` VALUES (2, 1294);
INSERT INTO `sys_role_menu` VALUES (2, 1295);
INSERT INTO `sys_role_menu` VALUES (2, 1296);
INSERT INTO `sys_role_menu` VALUES (2, 1297);
INSERT INTO `sys_role_menu` VALUES (2, 1298);
INSERT INTO `sys_role_menu` VALUES (2, 1299);
INSERT INTO `sys_role_menu` VALUES (2, 1300);
INSERT INTO `sys_role_menu` VALUES (2, 1301);
INSERT INTO `sys_role_menu` VALUES (2, 1302);
INSERT INTO `sys_role_menu` VALUES (2, 1303);
INSERT INTO `sys_role_menu` VALUES (2, 1304);
INSERT INTO `sys_role_menu` VALUES (2, 1305);
INSERT INTO `sys_role_menu` VALUES (2, 1306);
INSERT INTO `sys_role_menu` VALUES (2, 1307);
INSERT INTO `sys_role_menu` VALUES (2, 1308);
INSERT INTO `sys_role_menu` VALUES (2, 1309);
INSERT INTO `sys_role_menu` VALUES (2, 1310);
INSERT INTO `sys_role_menu` VALUES (2, 1311);
INSERT INTO `sys_role_menu` VALUES (2, 1312);
INSERT INTO `sys_role_menu` VALUES (2, 1313);
INSERT INTO `sys_role_menu` VALUES (2, 1314);
INSERT INTO `sys_role_menu` VALUES (2, 1315);
INSERT INTO `sys_role_menu` VALUES (2, 1316);
INSERT INTO `sys_role_menu` VALUES (2, 1317);
INSERT INTO `sys_role_menu` VALUES (2, 1318);
INSERT INTO `sys_role_menu` VALUES (2, 1319);
INSERT INTO `sys_role_menu` VALUES (2, 1320);
INSERT INTO `sys_role_menu` VALUES (2, 1321);
INSERT INTO `sys_role_menu` VALUES (2, 1322);
INSERT INTO `sys_role_menu` VALUES (2, 1323);
INSERT INTO `sys_role_menu` VALUES (2, 1324);
INSERT INTO `sys_role_menu` VALUES (2, 1325);
INSERT INTO `sys_role_menu` VALUES (2, 1326);
INSERT INTO `sys_role_menu` VALUES (2, 1327);
INSERT INTO `sys_role_menu` VALUES (2, 1328);
INSERT INTO `sys_role_menu` VALUES (2, 1329);
INSERT INTO `sys_role_menu` VALUES (2, 1330);
INSERT INTO `sys_role_menu` VALUES (2, 1331);
INSERT INTO `sys_role_menu` VALUES (2, 1332);
INSERT INTO `sys_role_menu` VALUES (2, 1333);
INSERT INTO `sys_role_menu` VALUES (2, 1334);
INSERT INTO `sys_role_menu` VALUES (2, 1335);
INSERT INTO `sys_role_menu` VALUES (2, 1336);
INSERT INTO `sys_role_menu` VALUES (2, 1337);
INSERT INTO `sys_role_menu` VALUES (2, 1338);
INSERT INTO `sys_role_menu` VALUES (2, 1339);
INSERT INTO `sys_role_menu` VALUES (2, 1340);
INSERT INTO `sys_role_menu` VALUES (2, 1341);
INSERT INTO `sys_role_menu` VALUES (2, 1342);
INSERT INTO `sys_role_menu` VALUES (2, 1343);
INSERT INTO `sys_role_menu` VALUES (2, 1344);
INSERT INTO `sys_role_menu` VALUES (2, 1345);
INSERT INTO `sys_role_menu` VALUES (2, 1346);
INSERT INTO `sys_role_menu` VALUES (2, 1347);
INSERT INTO `sys_role_menu` VALUES (2, 1348);
INSERT INTO `sys_role_menu` VALUES (2, 1349);
INSERT INTO `sys_role_menu` VALUES (2, 1350);
INSERT INTO `sys_role_menu` VALUES (2, 1351);
INSERT INTO `sys_role_menu` VALUES (2, 1352);
INSERT INTO `sys_role_menu` VALUES (2, 1353);
INSERT INTO `sys_role_menu` VALUES (2, 1354);
INSERT INTO `sys_role_menu` VALUES (2, 1356);
INSERT INTO `sys_role_menu` VALUES (2, 1358);
INSERT INTO `sys_role_menu` VALUES (2, 1359);
INSERT INTO `sys_role_menu` VALUES (2, 1360);
INSERT INTO `sys_role_menu` VALUES (2, 1361);
INSERT INTO `sys_role_menu` VALUES (2, 1362);
INSERT INTO `sys_role_menu` VALUES (2, 1363);
INSERT INTO `sys_role_menu` VALUES (2, 1364);
INSERT INTO `sys_role_menu` VALUES (2, 1365);
INSERT INTO `sys_role_menu` VALUES (2, 1366);
INSERT INTO `sys_role_menu` VALUES (2, 1367);
INSERT INTO `sys_role_menu` VALUES (2, 1368);
INSERT INTO `sys_role_menu` VALUES (2, 1369);
INSERT INTO `sys_role_menu` VALUES (2, 1370);
INSERT INTO `sys_role_menu` VALUES (2, 1371);
INSERT INTO `sys_role_menu` VALUES (2, 1372);
INSERT INTO `sys_role_menu` VALUES (2, 1373);
INSERT INTO `sys_role_menu` VALUES (2, 1374);
INSERT INTO `sys_role_menu` VALUES (2, 1375);
INSERT INTO `sys_role_menu` VALUES (2, 1376);
INSERT INTO `sys_role_menu` VALUES (2, 1377);
INSERT INTO `sys_role_menu` VALUES (2, 1378);
INSERT INTO `sys_role_menu` VALUES (2, 1379);
INSERT INTO `sys_role_menu` VALUES (2, 1380);
INSERT INTO `sys_role_menu` VALUES (2, 1381);
INSERT INTO `sys_role_menu` VALUES (2, 1382);
INSERT INTO `sys_role_menu` VALUES (2, 1383);
INSERT INTO `sys_role_menu` VALUES (2, 1384);
INSERT INTO `sys_role_menu` VALUES (2, 1385);
INSERT INTO `sys_role_menu` VALUES (2, 1387);
INSERT INTO `sys_role_menu` VALUES (2, 1388);
INSERT INTO `sys_role_menu` VALUES (2, 1389);
INSERT INTO `sys_role_menu` VALUES (2, 1390);
INSERT INTO `sys_role_menu` VALUES (2, 1391);
INSERT INTO `sys_role_menu` VALUES (2, 1392);
INSERT INTO `sys_role_menu` VALUES (2, 1393);
INSERT INTO `sys_role_menu` VALUES (2, 1394);
INSERT INTO `sys_role_menu` VALUES (2, 1396);
INSERT INTO `sys_role_menu` VALUES (2, 1397);
INSERT INTO `sys_role_menu` VALUES (2, 1398);
INSERT INTO `sys_role_menu` VALUES (2, 1411);
INSERT INTO `sys_role_menu` VALUES (2, 1412);
INSERT INTO `sys_role_menu` VALUES (2, 1413);
INSERT INTO `sys_role_menu` VALUES (2, 1414);
INSERT INTO `sys_role_menu` VALUES (2, 1415);
INSERT INTO `sys_role_menu` VALUES (2, 1416);
INSERT INTO `sys_role_menu` VALUES (2, 1417);
INSERT INTO `sys_role_menu` VALUES (2, 1418);
INSERT INTO `sys_role_menu` VALUES (2, 1419);
INSERT INTO `sys_role_menu` VALUES (2, 1420);
INSERT INTO `sys_role_menu` VALUES (2, 1421);
INSERT INTO `sys_role_menu` VALUES (2, 1422);
INSERT INTO `sys_role_menu` VALUES (2, 1423);
INSERT INTO `sys_role_menu` VALUES (2, 1424);
INSERT INTO `sys_role_menu` VALUES (2, 1425);
INSERT INTO `sys_role_menu` VALUES (2, 1427);
INSERT INTO `sys_role_menu` VALUES (2, 1428);
INSERT INTO `sys_role_menu` VALUES (2, 1429);
INSERT INTO `sys_role_menu` VALUES (2, 1430);
INSERT INTO `sys_role_menu` VALUES (2, 1431);
INSERT INTO `sys_role_menu` VALUES (2, 1432);
INSERT INTO `sys_role_menu` VALUES (2, 1433);
INSERT INTO `sys_role_menu` VALUES (2, 1434);
INSERT INTO `sys_role_menu` VALUES (2, 1435);
INSERT INTO `sys_role_menu` VALUES (2, 1436);
INSERT INTO `sys_role_menu` VALUES (2, 1437);
INSERT INTO `sys_role_menu` VALUES (2, 1438);
INSERT INTO `sys_role_menu` VALUES (2, 1439);
INSERT INTO `sys_role_menu` VALUES (2, 1440);
INSERT INTO `sys_role_menu` VALUES (2, 1441);
INSERT INTO `sys_role_menu` VALUES (2, 1442);
INSERT INTO `sys_role_menu` VALUES (2, 1443);
INSERT INTO `sys_role_menu` VALUES (2, 1444);
INSERT INTO `sys_role_menu` VALUES (2, 1445);
INSERT INTO `sys_role_menu` VALUES (2, 1446);
INSERT INTO `sys_role_menu` VALUES (2, 1447);
INSERT INTO `sys_role_menu` VALUES (2, 1448);
INSERT INTO `sys_role_menu` VALUES (2, 1449);
INSERT INTO `sys_role_menu` VALUES (2, 1450);
INSERT INTO `sys_role_menu` VALUES (2, 1451);
INSERT INTO `sys_role_menu` VALUES (2, 1452);
INSERT INTO `sys_role_menu` VALUES (2, 1453);
INSERT INTO `sys_role_menu` VALUES (2, 1454);
INSERT INTO `sys_role_menu` VALUES (2, 1455);
INSERT INTO `sys_role_menu` VALUES (2, 1456);
INSERT INTO `sys_role_menu` VALUES (2, 1457);
INSERT INTO `sys_role_menu` VALUES (2, 1458);
INSERT INTO `sys_role_menu` VALUES (2, 1459);
INSERT INTO `sys_role_menu` VALUES (2, 1460);
INSERT INTO `sys_role_menu` VALUES (2, 1461);
INSERT INTO `sys_role_menu` VALUES (2, 1462);
INSERT INTO `sys_role_menu` VALUES (2, 1463);
INSERT INTO `sys_role_menu` VALUES (2, 1464);
INSERT INTO `sys_role_menu` VALUES (2, 1465);
INSERT INTO `sys_role_menu` VALUES (2, 1466);
INSERT INTO `sys_role_menu` VALUES (2, 1467);
INSERT INTO `sys_role_menu` VALUES (2, 1468);
INSERT INTO `sys_role_menu` VALUES (2, 1469);
INSERT INTO `sys_role_menu` VALUES (2, 1470);
INSERT INTO `sys_role_menu` VALUES (2, 1471);
INSERT INTO `sys_role_menu` VALUES (2, 1472);
INSERT INTO `sys_role_menu` VALUES (2, 1473);
INSERT INTO `sys_role_menu` VALUES (2, 1474);
INSERT INTO `sys_role_menu` VALUES (2, 1475);
INSERT INTO `sys_role_menu` VALUES (2, 1476);
INSERT INTO `sys_role_menu` VALUES (2, 1477);
INSERT INTO `sys_role_menu` VALUES (2, 1478);
INSERT INTO `sys_role_menu` VALUES (2, 1479);
INSERT INTO `sys_role_menu` VALUES (2, 1480);
INSERT INTO `sys_role_menu` VALUES (2, 1481);
INSERT INTO `sys_role_menu` VALUES (2, 1482);
INSERT INTO `sys_role_menu` VALUES (2, 1483);
INSERT INTO `sys_role_menu` VALUES (2, 1484);
INSERT INTO `sys_role_menu` VALUES (2, 1485);
INSERT INTO `sys_role_menu` VALUES (2, 1486);
INSERT INTO `sys_role_menu` VALUES (2, 1487);
INSERT INTO `sys_role_menu` VALUES (2, 1488);
INSERT INTO `sys_role_menu` VALUES (2, 1489);
INSERT INTO `sys_role_menu` VALUES (2, 1490);
INSERT INTO `sys_role_menu` VALUES (2, 1491);
INSERT INTO `sys_role_menu` VALUES (2, 1492);
INSERT INTO `sys_role_menu` VALUES (2, 1493);
INSERT INTO `sys_role_menu` VALUES (2, 1494);
INSERT INTO `sys_role_menu` VALUES (2, 1495);
INSERT INTO `sys_role_menu` VALUES (2, 1496);
INSERT INTO `sys_role_menu` VALUES (2, 1497);
INSERT INTO `sys_role_menu` VALUES (2, 1498);
INSERT INTO `sys_role_menu` VALUES (2, 1499);
INSERT INTO `sys_role_menu` VALUES (2, 1500);
INSERT INTO `sys_role_menu` VALUES (2, 1501);
INSERT INTO `sys_role_menu` VALUES (2, 1502);
INSERT INTO `sys_role_menu` VALUES (2, 1503);
INSERT INTO `sys_role_menu` VALUES (2, 1504);
INSERT INTO `sys_role_menu` VALUES (2, 1505);
INSERT INTO `sys_role_menu` VALUES (2, 1506);
INSERT INTO `sys_role_menu` VALUES (2, 1507);
INSERT INTO `sys_role_menu` VALUES (2, 1508);
INSERT INTO `sys_role_menu` VALUES (2, 1509);
INSERT INTO `sys_role_menu` VALUES (2, 1510);
INSERT INTO `sys_role_menu` VALUES (2, 1511);
INSERT INTO `sys_role_menu` VALUES (2, 1512);
INSERT INTO `sys_role_menu` VALUES (2, 1513);
INSERT INTO `sys_role_menu` VALUES (2, 1514);
INSERT INTO `sys_role_menu` VALUES (2, 1515);
INSERT INTO `sys_role_menu` VALUES (2, 1516);
INSERT INTO `sys_role_menu` VALUES (2, 1517);
INSERT INTO `sys_role_menu` VALUES (2, 1518);
INSERT INTO `sys_role_menu` VALUES (2, 1519);
INSERT INTO `sys_role_menu` VALUES (2, 1520);
INSERT INTO `sys_role_menu` VALUES (2, 1521);
INSERT INTO `sys_role_menu` VALUES (2, 1522);
INSERT INTO `sys_role_menu` VALUES (2, 1523);
INSERT INTO `sys_role_menu` VALUES (2, 1524);
INSERT INTO `sys_role_menu` VALUES (2, 1525);
INSERT INTO `sys_role_menu` VALUES (2, 1526);
INSERT INTO `sys_role_menu` VALUES (2, 1527);
INSERT INTO `sys_role_menu` VALUES (2, 1528);
INSERT INTO `sys_role_menu` VALUES (2, 1529);
INSERT INTO `sys_role_menu` VALUES (2, 1530);
INSERT INTO `sys_role_menu` VALUES (2, 1532);
INSERT INTO `sys_role_menu` VALUES (2, 1533);
INSERT INTO `sys_role_menu` VALUES (2, 1534);
INSERT INTO `sys_role_menu` VALUES (2, 1535);
INSERT INTO `sys_role_menu` VALUES (2, 1536);
INSERT INTO `sys_role_menu` VALUES (2, 1537);
INSERT INTO `sys_role_menu` VALUES (2, 1538);
INSERT INTO `sys_role_menu` VALUES (2, 1539);
INSERT INTO `sys_role_menu` VALUES (2, 1542);
INSERT INTO `sys_role_menu` VALUES (2, 1543);
INSERT INTO `sys_role_menu` VALUES (2, 1544);
INSERT INTO `sys_role_menu` VALUES (2, 1545);
INSERT INTO `sys_role_menu` VALUES (2, 1546);
INSERT INTO `sys_role_menu` VALUES (2, 1547);
INSERT INTO `sys_role_menu` VALUES (2, 1548);
INSERT INTO `sys_role_menu` VALUES (2, 1549);
INSERT INTO `sys_role_menu` VALUES (2, 1550);
INSERT INTO `sys_role_menu` VALUES (2, 1551);
INSERT INTO `sys_role_menu` VALUES (2, 1552);
INSERT INTO `sys_role_menu` VALUES (2, 1553);
INSERT INTO `sys_role_menu` VALUES (2, 1554);
INSERT INTO `sys_role_menu` VALUES (2, 1555);
INSERT INTO `sys_role_menu` VALUES (2, 1556);
INSERT INTO `sys_role_menu` VALUES (2, 1557);
INSERT INTO `sys_role_menu` VALUES (2, 1558);
INSERT INTO `sys_role_menu` VALUES (2, 1559);
INSERT INTO `sys_role_menu` VALUES (2, 1560);
INSERT INTO `sys_role_menu` VALUES (2, 1561);
INSERT INTO `sys_role_menu` VALUES (2, 1562);
INSERT INTO `sys_role_menu` VALUES (2, 1563);
INSERT INTO `sys_role_menu` VALUES (2, 1564);
INSERT INTO `sys_role_menu` VALUES (2, 1565);
INSERT INTO `sys_role_menu` VALUES (2, 1566);
INSERT INTO `sys_role_menu` VALUES (2, 1567);
INSERT INTO `sys_role_menu` VALUES (2, 1568);
INSERT INTO `sys_role_menu` VALUES (2, 1569);
INSERT INTO `sys_role_menu` VALUES (2, 1570);
INSERT INTO `sys_role_menu` VALUES (2, 1571);
INSERT INTO `sys_role_menu` VALUES (2, 1572);
INSERT INTO `sys_role_menu` VALUES (2, 1573);
INSERT INTO `sys_role_menu` VALUES (2, 1574);
INSERT INTO `sys_role_menu` VALUES (2, 1575);
INSERT INTO `sys_role_menu` VALUES (2, 1576);
INSERT INTO `sys_role_menu` VALUES (2, 1577);
INSERT INTO `sys_role_menu` VALUES (2, 1578);
INSERT INTO `sys_role_menu` VALUES (2, 1579);
INSERT INTO `sys_role_menu` VALUES (2, 1580);
INSERT INTO `sys_role_menu` VALUES (2, 1581);
INSERT INTO `sys_role_menu` VALUES (2, 1582);
INSERT INTO `sys_role_menu` VALUES (2, 1583);
INSERT INTO `sys_role_menu` VALUES (2, 1584);
INSERT INTO `sys_role_menu` VALUES (2, 1585);
INSERT INTO `sys_role_menu` VALUES (2, 1586);
INSERT INTO `sys_role_menu` VALUES (2, 1587);
INSERT INTO `sys_role_menu` VALUES (2, 1662);
INSERT INTO `sys_role_menu` VALUES (2, 1663);
INSERT INTO `sys_role_menu` VALUES (2, 1682);
INSERT INTO `sys_role_menu` VALUES (2, 1683);
INSERT INTO `sys_role_menu` VALUES (2, 1684);
INSERT INTO `sys_role_menu` VALUES (2, 1685);
INSERT INTO `sys_role_menu` VALUES (2, 1686);
INSERT INTO `sys_role_menu` VALUES (2, 1687);
INSERT INTO `sys_role_menu` VALUES (2, 1688);
INSERT INTO `sys_role_menu` VALUES (2, 1689);
INSERT INTO `sys_role_menu` VALUES (2, 1690);
INSERT INTO `sys_role_menu` VALUES (2, 1691);
INSERT INTO `sys_role_menu` VALUES (2, 1692);
INSERT INTO `sys_role_menu` VALUES (2, 1693);
INSERT INTO `sys_role_menu` VALUES (2, 1694);
INSERT INTO `sys_role_menu` VALUES (2, 1695);
INSERT INTO `sys_role_menu` VALUES (2, 1696);
INSERT INTO `sys_role_menu` VALUES (2, 1697);
INSERT INTO `sys_role_menu` VALUES (2, 1698);
INSERT INTO `sys_role_menu` VALUES (2, 1699);
INSERT INTO `sys_role_menu` VALUES (2, 1700);
INSERT INTO `sys_role_menu` VALUES (2, 1701);
INSERT INTO `sys_role_menu` VALUES (2, 1702);
INSERT INTO `sys_role_menu` VALUES (2, 1703);
INSERT INTO `sys_role_menu` VALUES (2, 1704);
INSERT INTO `sys_role_menu` VALUES (2, 1705);
INSERT INTO `sys_role_menu` VALUES (2, 1708);
INSERT INTO `sys_role_menu` VALUES (2, 1709);
INSERT INTO `sys_role_menu` VALUES (2, 1710);
INSERT INTO `sys_role_menu` VALUES (2, 1711);
INSERT INTO `sys_role_menu` VALUES (2, 1712);
INSERT INTO `sys_role_menu` VALUES (2, 1713);
INSERT INTO `sys_role_menu` VALUES (2, 1714);
INSERT INTO `sys_role_menu` VALUES (2, 1715);
INSERT INTO `sys_role_menu` VALUES (2, 1716);
INSERT INTO `sys_role_menu` VALUES (2, 1717);
INSERT INTO `sys_role_menu` VALUES (2, 1718);
INSERT INTO `sys_role_menu` VALUES (2, 1719);
INSERT INTO `sys_role_menu` VALUES (2, 1720);
INSERT INTO `sys_role_menu` VALUES (2, 1721);
INSERT INTO `sys_role_menu` VALUES (2, 1722);
INSERT INTO `sys_role_menu` VALUES (2, 1723);
INSERT INTO `sys_role_menu` VALUES (2, 1724);
INSERT INTO `sys_role_menu` VALUES (2, 1725);
INSERT INTO `sys_role_menu` VALUES (2, 1726);
INSERT INTO `sys_role_menu` VALUES (2, 1727);
INSERT INTO `sys_role_menu` VALUES (2, 1728);
INSERT INTO `sys_role_menu` VALUES (2, 1729);
INSERT INTO `sys_role_menu` VALUES (2, 1730);
INSERT INTO `sys_role_menu` VALUES (2, 1731);
INSERT INTO `sys_role_menu` VALUES (2, 1732);
INSERT INTO `sys_role_menu` VALUES (2, 1733);
INSERT INTO `sys_role_menu` VALUES (2, 1734);
INSERT INTO `sys_role_menu` VALUES (2, 1735);
INSERT INTO `sys_role_menu` VALUES (2, 1736);
INSERT INTO `sys_role_menu` VALUES (2, 1737);
INSERT INTO `sys_role_menu` VALUES (2, 1738);
INSERT INTO `sys_role_menu` VALUES (2, 1739);
INSERT INTO `sys_role_menu` VALUES (2, 1740);
INSERT INTO `sys_role_menu` VALUES (2, 1741);
INSERT INTO `sys_role_menu` VALUES (2, 1742);
INSERT INTO `sys_role_menu` VALUES (2, 1743);
INSERT INTO `sys_role_menu` VALUES (2, 1744);
INSERT INTO `sys_role_menu` VALUES (2, 1745);
INSERT INTO `sys_role_menu` VALUES (2, 1746);
INSERT INTO `sys_role_menu` VALUES (2, 1747);
INSERT INTO `sys_role_menu` VALUES (2, 1748);
INSERT INTO `sys_role_menu` VALUES (2, 1749);
INSERT INTO `sys_role_menu` VALUES (2, 1750);
INSERT INTO `sys_role_menu` VALUES (2, 1751);
INSERT INTO `sys_role_menu` VALUES (2, 1752);
INSERT INTO `sys_role_menu` VALUES (2, 1753);
INSERT INTO `sys_role_menu` VALUES (2, 1754);
INSERT INTO `sys_role_menu` VALUES (2, 1755);
INSERT INTO `sys_role_menu` VALUES (2, 1756);
INSERT INTO `sys_role_menu` VALUES (2, 1757);
INSERT INTO `sys_role_menu` VALUES (2, 1758);
INSERT INTO `sys_role_menu` VALUES (2, 1759);
INSERT INTO `sys_role_menu` VALUES (2, 1760);
INSERT INTO `sys_role_menu` VALUES (2, 1761);
INSERT INTO `sys_role_menu` VALUES (2, 1762);
INSERT INTO `sys_role_menu` VALUES (2, 1763);
INSERT INTO `sys_role_menu` VALUES (2, 1764);
INSERT INTO `sys_role_menu` VALUES (2, 1765);
INSERT INTO `sys_role_menu` VALUES (2, 1766);
INSERT INTO `sys_role_menu` VALUES (2, 1767);
INSERT INTO `sys_role_menu` VALUES (2, 1768);
INSERT INTO `sys_role_menu` VALUES (2, 1769);
INSERT INTO `sys_role_menu` VALUES (2, 1770);
INSERT INTO `sys_role_menu` VALUES (2, 1771);
INSERT INTO `sys_role_menu` VALUES (2, 1772);
INSERT INTO `sys_role_menu` VALUES (2, 1773);
INSERT INTO `sys_role_menu` VALUES (2, 1774);
INSERT INTO `sys_role_menu` VALUES (2, 1775);
INSERT INTO `sys_role_menu` VALUES (2, 1776);
INSERT INTO `sys_role_menu` VALUES (2, 1777);
INSERT INTO `sys_role_menu` VALUES (2, 1778);
INSERT INTO `sys_role_menu` VALUES (2, 1779);
INSERT INTO `sys_role_menu` VALUES (2, 1787);
INSERT INTO `sys_role_menu` VALUES (2, 1788);
INSERT INTO `sys_role_menu` VALUES (2, 1789);
INSERT INTO `sys_role_menu` VALUES (2, 1790);
INSERT INTO `sys_role_menu` VALUES (2, 2031);
INSERT INTO `sys_role_menu` VALUES (2, 2032);
INSERT INTO `sys_role_menu` VALUES (2, 2033);
INSERT INTO `sys_role_menu` VALUES (2, 2034);
INSERT INTO `sys_role_menu` VALUES (2, 2035);
INSERT INTO `sys_role_menu` VALUES (2, 2036);
INSERT INTO `sys_role_menu` VALUES (2, 2037);
INSERT INTO `sys_role_menu` VALUES (2, 2038);
INSERT INTO `sys_role_menu` VALUES (2, 2039);
INSERT INTO `sys_role_menu` VALUES (2, 2040);
INSERT INTO `sys_role_menu` VALUES (2, 2041);
INSERT INTO `sys_role_menu` VALUES (2, 2042);
INSERT INTO `sys_role_menu` VALUES (2, 2043);
INSERT INTO `sys_role_menu` VALUES (2, 2044);
INSERT INTO `sys_role_menu` VALUES (2, 2045);
INSERT INTO `sys_role_menu` VALUES (2, 2046);
INSERT INTO `sys_role_menu` VALUES (2, 2047);
INSERT INTO `sys_role_menu` VALUES (2, 2048);
INSERT INTO `sys_role_menu` VALUES (2, 2049);
INSERT INTO `sys_role_menu` VALUES (2, 2050);
INSERT INTO `sys_role_menu` VALUES (2, 2051);
INSERT INTO `sys_role_menu` VALUES (2, 2052);
INSERT INTO `sys_role_menu` VALUES (2, 2053);
INSERT INTO `sys_role_menu` VALUES (2, 2054);
INSERT INTO `sys_role_menu` VALUES (2, 2055);
INSERT INTO `sys_role_menu` VALUES (2, 2056);
INSERT INTO `sys_role_menu` VALUES (2, 2057);
INSERT INTO `sys_role_menu` VALUES (2, 2058);
INSERT INTO `sys_role_menu` VALUES (2, 2059);
INSERT INTO `sys_role_menu` VALUES (2, 2060);
INSERT INTO `sys_role_menu` VALUES (2, 2061);
INSERT INTO `sys_role_menu` VALUES (2, 2062);
INSERT INTO `sys_role_menu` VALUES (2, 2063);
INSERT INTO `sys_role_menu` VALUES (2, 2064);
INSERT INTO `sys_role_menu` VALUES (2, 2065);
INSERT INTO `sys_role_menu` VALUES (2, 2066);
INSERT INTO `sys_role_menu` VALUES (2, 2067);
INSERT INTO `sys_role_menu` VALUES (2, 2068);
INSERT INTO `sys_role_menu` VALUES (2, 2069);
INSERT INTO `sys_role_menu` VALUES (2, 2070);
INSERT INTO `sys_role_menu` VALUES (2, 2071);
INSERT INTO `sys_role_menu` VALUES (2, 2072);
INSERT INTO `sys_role_menu` VALUES (2, 2073);
INSERT INTO `sys_role_menu` VALUES (2, 2074);
INSERT INTO `sys_role_menu` VALUES (2, 2075);
INSERT INTO `sys_role_menu` VALUES (2, 2076);
INSERT INTO `sys_role_menu` VALUES (2, 2077);
INSERT INTO `sys_role_menu` VALUES (2, 2078);
INSERT INTO `sys_role_menu` VALUES (2, 2079);
INSERT INTO `sys_role_menu` VALUES (2, 2080);
INSERT INTO `sys_role_menu` VALUES (2, 2081);
INSERT INTO `sys_role_menu` VALUES (2, 2082);
INSERT INTO `sys_role_menu` VALUES (2, 2083);
INSERT INTO `sys_role_menu` VALUES (2, 2084);
INSERT INTO `sys_role_menu` VALUES (2, 2085);
INSERT INTO `sys_role_menu` VALUES (2, 2086);
INSERT INTO `sys_role_menu` VALUES (2, 2087);
INSERT INTO `sys_role_menu` VALUES (2, 2088);
INSERT INTO `sys_role_menu` VALUES (2, 2089);
INSERT INTO `sys_role_menu` VALUES (2, 2090);
INSERT INTO `sys_role_menu` VALUES (2, 2091);
INSERT INTO `sys_role_menu` VALUES (2, 2092);
INSERT INTO `sys_role_menu` VALUES (2, 2093);
INSERT INTO `sys_role_menu` VALUES (2, 2094);
INSERT INTO `sys_role_menu` VALUES (2, 2095);
INSERT INTO `sys_role_menu` VALUES (2, 2096);
INSERT INTO `sys_role_menu` VALUES (2, 2097);
INSERT INTO `sys_role_menu` VALUES (2, 2098);
INSERT INTO `sys_role_menu` VALUES (2, 2099);
INSERT INTO `sys_role_menu` VALUES (2, 2100);
INSERT INTO `sys_role_menu` VALUES (2, 2101);
INSERT INTO `sys_role_menu` VALUES (2, 2102);
INSERT INTO `sys_role_menu` VALUES (2, 2103);
INSERT INTO `sys_role_menu` VALUES (2, 2104);
INSERT INTO `sys_role_menu` VALUES (2, 2105);
INSERT INTO `sys_role_menu` VALUES (2, 2106);
INSERT INTO `sys_role_menu` VALUES (2, 2107);
INSERT INTO `sys_role_menu` VALUES (2, 2108);
INSERT INTO `sys_role_menu` VALUES (2, 2109);
INSERT INTO `sys_role_menu` VALUES (2, 2110);
INSERT INTO `sys_role_menu` VALUES (2, 2111);
INSERT INTO `sys_role_menu` VALUES (2, 2112);
INSERT INTO `sys_role_menu` VALUES (2, 2113);
INSERT INTO `sys_role_menu` VALUES (2, 2114);
INSERT INTO `sys_role_menu` VALUES (2, 2115);
INSERT INTO `sys_role_menu` VALUES (2, 2116);
INSERT INTO `sys_role_menu` VALUES (2, 2117);
INSERT INTO `sys_role_menu` VALUES (2, 2118);
INSERT INTO `sys_role_menu` VALUES (2, 2119);
INSERT INTO `sys_role_menu` VALUES (2, 2120);
INSERT INTO `sys_role_menu` VALUES (2, 2121);
INSERT INTO `sys_role_menu` VALUES (2, 2122);
INSERT INTO `sys_role_menu` VALUES (2, 2123);
INSERT INTO `sys_role_menu` VALUES (2, 2124);
INSERT INTO `sys_role_menu` VALUES (2, 2125);
INSERT INTO `sys_role_menu` VALUES (2, 2126);
INSERT INTO `sys_role_menu` VALUES (2, 2127);
INSERT INTO `sys_role_menu` VALUES (2, 2128);
INSERT INTO `sys_role_menu` VALUES (2, 2129);
INSERT INTO `sys_role_menu` VALUES (2, 2130);
INSERT INTO `sys_role_menu` VALUES (2, 2131);
INSERT INTO `sys_role_menu` VALUES (2, 2132);
INSERT INTO `sys_role_menu` VALUES (2, 2133);
INSERT INTO `sys_role_menu` VALUES (2, 2134);
INSERT INTO `sys_role_menu` VALUES (2, 2135);
INSERT INTO `sys_role_menu` VALUES (2, 2136);
INSERT INTO `sys_role_menu` VALUES (2, 2137);
INSERT INTO `sys_role_menu` VALUES (2, 2138);
INSERT INTO `sys_role_menu` VALUES (2, 2139);
INSERT INTO `sys_role_menu` VALUES (2, 2140);
INSERT INTO `sys_role_menu` VALUES (2, 2141);
INSERT INTO `sys_role_menu` VALUES (2, 2142);
INSERT INTO `sys_role_menu` VALUES (2, 2143);
INSERT INTO `sys_role_menu` VALUES (2, 2144);
INSERT INTO `sys_role_menu` VALUES (2, 2145);
INSERT INTO `sys_role_menu` VALUES (2, 2146);
INSERT INTO `sys_role_menu` VALUES (2, 2147);
INSERT INTO `sys_role_menu` VALUES (2, 2148);
INSERT INTO `sys_role_menu` VALUES (2, 2149);
INSERT INTO `sys_role_menu` VALUES (2, 2150);
INSERT INTO `sys_role_menu` VALUES (2, 2151);
INSERT INTO `sys_role_menu` VALUES (2, 2152);
INSERT INTO `sys_role_menu` VALUES (2, 2153);
INSERT INTO `sys_role_menu` VALUES (2, 2154);
INSERT INTO `sys_role_menu` VALUES (2, 2155);
INSERT INTO `sys_role_menu` VALUES (2, 2156);
INSERT INTO `sys_role_menu` VALUES (2, 2157);
INSERT INTO `sys_role_menu` VALUES (2, 2158);
INSERT INTO `sys_role_menu` VALUES (2, 2159);
INSERT INTO `sys_role_menu` VALUES (2, 2160);
INSERT INTO `sys_role_menu` VALUES (2, 2161);
INSERT INTO `sys_role_menu` VALUES (2, 2162);
INSERT INTO `sys_role_menu` VALUES (2, 2163);
INSERT INTO `sys_role_menu` VALUES (2, 2164);
INSERT INTO `sys_role_menu` VALUES (2, 2165);
INSERT INTO `sys_role_menu` VALUES (2, 2166);
INSERT INTO `sys_role_menu` VALUES (2, 2167);
INSERT INTO `sys_role_menu` VALUES (2, 2168);
INSERT INTO `sys_role_menu` VALUES (2, 2169);
INSERT INTO `sys_role_menu` VALUES (2, 2170);
INSERT INTO `sys_role_menu` VALUES (2, 2171);
INSERT INTO `sys_role_menu` VALUES (2, 2172);
INSERT INTO `sys_role_menu` VALUES (2, 2173);
INSERT INTO `sys_role_menu` VALUES (2, 2174);
INSERT INTO `sys_role_menu` VALUES (2, 2175);
INSERT INTO `sys_role_menu` VALUES (2, 2176);
INSERT INTO `sys_role_menu` VALUES (2, 2177);
INSERT INTO `sys_role_menu` VALUES (2, 2178);
INSERT INTO `sys_role_menu` VALUES (2, 2179);
INSERT INTO `sys_role_menu` VALUES (2, 2180);
INSERT INTO `sys_role_menu` VALUES (2, 2181);
INSERT INTO `sys_role_menu` VALUES (2, 2182);
INSERT INTO `sys_role_menu` VALUES (2, 2183);
INSERT INTO `sys_role_menu` VALUES (2, 2184);
INSERT INTO `sys_role_menu` VALUES (2, 2185);
INSERT INTO `sys_role_menu` VALUES (2, 2186);
INSERT INTO `sys_role_menu` VALUES (2, 2187);
INSERT INTO `sys_role_menu` VALUES (2, 2188);
INSERT INTO `sys_role_menu` VALUES (2, 2189);
INSERT INTO `sys_role_menu` VALUES (2, 2190);
INSERT INTO `sys_role_menu` VALUES (2, 2191);
INSERT INTO `sys_role_menu` VALUES (2, 2192);
INSERT INTO `sys_role_menu` VALUES (2, 2193);
INSERT INTO `sys_role_menu` VALUES (2, 2194);
INSERT INTO `sys_role_menu` VALUES (2, 2195);
INSERT INTO `sys_role_menu` VALUES (2, 2196);
INSERT INTO `sys_role_menu` VALUES (2, 2197);
INSERT INTO `sys_role_menu` VALUES (2, 2198);
INSERT INTO `sys_role_menu` VALUES (2, 2199);
INSERT INTO `sys_role_menu` VALUES (2, 2301);
INSERT INTO `sys_role_menu` VALUES (2, 2302);
INSERT INTO `sys_role_menu` VALUES (2, 2303);
INSERT INTO `sys_role_menu` VALUES (2, 2304);
INSERT INTO `sys_role_menu` VALUES (2, 2305);
INSERT INTO `sys_role_menu` VALUES (2, 2306);
INSERT INTO `sys_role_menu` VALUES (2, 2307);
INSERT INTO `sys_role_menu` VALUES (2, 2308);
INSERT INTO `sys_role_menu` VALUES (2, 2309);
INSERT INTO `sys_role_menu` VALUES (2, 2310);
INSERT INTO `sys_role_menu` VALUES (2, 2311);
INSERT INTO `sys_role_menu` VALUES (2, 2312);
INSERT INTO `sys_role_menu` VALUES (2, 2313);
INSERT INTO `sys_role_menu` VALUES (2, 2314);
INSERT INTO `sys_role_menu` VALUES (2, 2315);
INSERT INTO `sys_role_menu` VALUES (2, 2316);
INSERT INTO `sys_role_menu` VALUES (2, 2317);
INSERT INTO `sys_role_menu` VALUES (2, 2318);
INSERT INTO `sys_role_menu` VALUES (2, 2319);
INSERT INTO `sys_role_menu` VALUES (2, 2320);
INSERT INTO `sys_role_menu` VALUES (2, 2321);
INSERT INTO `sys_role_menu` VALUES (2, 2322);
INSERT INTO `sys_role_menu` VALUES (2, 2323);
INSERT INTO `sys_role_menu` VALUES (2, 2324);
INSERT INTO `sys_role_menu` VALUES (2, 2325);
INSERT INTO `sys_role_menu` VALUES (2, 2326);
INSERT INTO `sys_role_menu` VALUES (2, 2327);
INSERT INTO `sys_role_menu` VALUES (2, 2328);
INSERT INTO `sys_role_menu` VALUES (2, 2329);
INSERT INTO `sys_role_menu` VALUES (2, 2330);
INSERT INTO `sys_role_menu` VALUES (2, 2331);
INSERT INTO `sys_role_menu` VALUES (2, 2332);
INSERT INTO `sys_role_menu` VALUES (2, 2333);
INSERT INTO `sys_role_menu` VALUES (2, 2334);
INSERT INTO `sys_role_menu` VALUES (2, 2335);
INSERT INTO `sys_role_menu` VALUES (2, 2336);
INSERT INTO `sys_role_menu` VALUES (2, 2337);
INSERT INTO `sys_role_menu` VALUES (2, 2338);
INSERT INTO `sys_role_menu` VALUES (3, 1398);
INSERT INTO `sys_role_menu` VALUES (3, 1582);
INSERT INTO `sys_role_menu` VALUES (3, 1762);
INSERT INTO `sys_role_menu` VALUES (3, 2082);
INSERT INTO `sys_role_menu` VALUES (4, 1398);
INSERT INTO `sys_role_menu` VALUES (4, 1582);
INSERT INTO `sys_role_menu` VALUES (4, 1583);
INSERT INTO `sys_role_menu` VALUES (4, 1584);
INSERT INTO `sys_role_menu` VALUES (4, 1762);
INSERT INTO `sys_role_menu` VALUES (4, 1764);
INSERT INTO `sys_role_menu` VALUES (4, 1766);
INSERT INTO `sys_role_menu` VALUES (4, 1768);
INSERT INTO `sys_role_menu` VALUES (4, 1769);
INSERT INTO `sys_role_menu` VALUES (4, 2033);
INSERT INTO `sys_role_menu` VALUES (4, 2034);
INSERT INTO `sys_role_menu` VALUES (4, 2035);
INSERT INTO `sys_role_menu` VALUES (4, 2037);
INSERT INTO `sys_role_menu` VALUES (4, 2039);
INSERT INTO `sys_role_menu` VALUES (4, 2082);
INSERT INTO `sys_role_menu` VALUES (4, 2083);
INSERT INTO `sys_role_menu` VALUES (4, 2087);
INSERT INTO `sys_role_menu` VALUES (4, 2091);
INSERT INTO `sys_role_menu` VALUES (4, 2095);
INSERT INTO `sys_role_menu` VALUES (4, 2096);
INSERT INTO `sys_role_menu` VALUES (4, 2114);
INSERT INTO `sys_role_menu` VALUES (5, 1398);
INSERT INTO `sys_role_menu` VALUES (5, 1583);
INSERT INTO `sys_role_menu` VALUES (5, 1584);
INSERT INTO `sys_role_menu` VALUES (5, 1586);
INSERT INTO `sys_role_menu` VALUES (5, 1768);
INSERT INTO `sys_role_menu` VALUES (5, 1771);
INSERT INTO `sys_role_menu` VALUES (5, 2032);
INSERT INTO `sys_role_menu` VALUES (5, 2033);
INSERT INTO `sys_role_menu` VALUES (5, 2034);
INSERT INTO `sys_role_menu` VALUES (5, 2035);
INSERT INTO `sys_role_menu` VALUES (5, 2036);
INSERT INTO `sys_role_menu` VALUES (5, 2038);
INSERT INTO `sys_role_menu` VALUES (5, 2092);
INSERT INTO `sys_role_menu` VALUES (5, 2095);
INSERT INTO `sys_role_menu` VALUES (5, 2098);
INSERT INTO `sys_role_menu` VALUES (5, 2108);
INSERT INTO `sys_role_menu` VALUES (5, 2113);
INSERT INTO `sys_role_menu` VALUES (6, 1398);
INSERT INTO `sys_role_menu` VALUES (6, 1582);
INSERT INTO `sys_role_menu` VALUES (6, 1583);
INSERT INTO `sys_role_menu` VALUES (6, 1584);
INSERT INTO `sys_role_menu` VALUES (6, 1586);
INSERT INTO `sys_role_menu` VALUES (6, 1763);
INSERT INTO `sys_role_menu` VALUES (6, 1765);
INSERT INTO `sys_role_menu` VALUES (6, 1766);
INSERT INTO `sys_role_menu` VALUES (6, 1767);
INSERT INTO `sys_role_menu` VALUES (6, 1768);
INSERT INTO `sys_role_menu` VALUES (6, 1769);
INSERT INTO `sys_role_menu` VALUES (6, 1770);
INSERT INTO `sys_role_menu` VALUES (6, 1771);
INSERT INTO `sys_role_menu` VALUES (6, 2032);
INSERT INTO `sys_role_menu` VALUES (6, 2033);
INSERT INTO `sys_role_menu` VALUES (6, 2034);
INSERT INTO `sys_role_menu` VALUES (6, 2035);
INSERT INTO `sys_role_menu` VALUES (6, 2036);
INSERT INTO `sys_role_menu` VALUES (6, 2037);
INSERT INTO `sys_role_menu` VALUES (6, 2086);
INSERT INTO `sys_role_menu` VALUES (6, 2090);
INSERT INTO `sys_role_menu` VALUES (6, 2091);
INSERT INTO `sys_role_menu` VALUES (6, 2092);
INSERT INTO `sys_role_menu` VALUES (6, 2095);
INSERT INTO `sys_role_menu` VALUES (6, 2096);
INSERT INTO `sys_role_menu` VALUES (6, 2097);
INSERT INTO `sys_role_menu` VALUES (6, 2098);
INSERT INTO `sys_role_menu` VALUES (8, 3);
INSERT INTO `sys_role_menu` VALUES (8, 1061);
INSERT INTO `sys_role_menu` VALUES (8, 1062);
INSERT INTO `sys_role_menu` VALUES (8, 1063);
INSERT INTO `sys_role_menu` VALUES (8, 1064);
INSERT INTO `sys_role_menu` VALUES (8, 1065);
INSERT INTO `sys_role_menu` VALUES (8, 1066);
INSERT INTO `sys_role_menu` VALUES (8, 1067);
INSERT INTO `sys_role_menu` VALUES (8, 1068);
INSERT INTO `sys_role_menu` VALUES (8, 1069);
INSERT INTO `sys_role_menu` VALUES (8, 1070);
INSERT INTO `sys_role_menu` VALUES (8, 1071);
INSERT INTO `sys_role_menu` VALUES (8, 1072);
INSERT INTO `sys_role_menu` VALUES (8, 1073);
INSERT INTO `sys_role_menu` VALUES (8, 1074);
INSERT INTO `sys_role_menu` VALUES (8, 1075);
INSERT INTO `sys_role_menu` VALUES (8, 1076);
INSERT INTO `sys_role_menu` VALUES (8, 1077);
INSERT INTO `sys_role_menu` VALUES (8, 1078);
INSERT INTO `sys_role_menu` VALUES (8, 1079);
INSERT INTO `sys_role_menu` VALUES (8, 1080);
INSERT INTO `sys_role_menu` VALUES (8, 1081);
INSERT INTO `sys_role_menu` VALUES (8, 1082);
INSERT INTO `sys_role_menu` VALUES (8, 1083);
INSERT INTO `sys_role_menu` VALUES (8, 1084);
INSERT INTO `sys_role_menu` VALUES (8, 1085);
INSERT INTO `sys_role_menu` VALUES (8, 1086);
INSERT INTO `sys_role_menu` VALUES (8, 1087);
INSERT INTO `sys_role_menu` VALUES (8, 1088);
INSERT INTO `sys_role_menu` VALUES (8, 1089);
INSERT INTO `sys_role_menu` VALUES (8, 1090);
INSERT INTO `sys_role_menu` VALUES (8, 1091);
INSERT INTO `sys_role_menu` VALUES (8, 1092);
INSERT INTO `sys_role_menu` VALUES (8, 1093);
INSERT INTO `sys_role_menu` VALUES (8, 1094);
INSERT INTO `sys_role_menu` VALUES (8, 1095);
INSERT INTO `sys_role_menu` VALUES (8, 1096);
INSERT INTO `sys_role_menu` VALUES (8, 1097);
INSERT INTO `sys_role_menu` VALUES (8, 1098);
INSERT INTO `sys_role_menu` VALUES (8, 1099);
INSERT INTO `sys_role_menu` VALUES (8, 1100);
INSERT INTO `sys_role_menu` VALUES (8, 1101);
INSERT INTO `sys_role_menu` VALUES (8, 1102);
INSERT INTO `sys_role_menu` VALUES (8, 1103);
INSERT INTO `sys_role_menu` VALUES (8, 1104);
INSERT INTO `sys_role_menu` VALUES (8, 1105);
INSERT INTO `sys_role_menu` VALUES (8, 1106);
INSERT INTO `sys_role_menu` VALUES (8, 1107);
INSERT INTO `sys_role_menu` VALUES (8, 1108);
INSERT INTO `sys_role_menu` VALUES (8, 1109);
INSERT INTO `sys_role_menu` VALUES (8, 1110);
INSERT INTO `sys_role_menu` VALUES (8, 1111);
INSERT INTO `sys_role_menu` VALUES (8, 1112);
INSERT INTO `sys_role_menu` VALUES (8, 1113);
INSERT INTO `sys_role_menu` VALUES (8, 1114);
INSERT INTO `sys_role_menu` VALUES (8, 1115);
INSERT INTO `sys_role_menu` VALUES (8, 1116);
INSERT INTO `sys_role_menu` VALUES (8, 1117);
INSERT INTO `sys_role_menu` VALUES (8, 1118);
INSERT INTO `sys_role_menu` VALUES (8, 1119);
INSERT INTO `sys_role_menu` VALUES (8, 1120);
INSERT INTO `sys_role_menu` VALUES (8, 1121);
INSERT INTO `sys_role_menu` VALUES (8, 1122);
INSERT INTO `sys_role_menu` VALUES (8, 1123);
INSERT INTO `sys_role_menu` VALUES (8, 1124);
INSERT INTO `sys_role_menu` VALUES (8, 1125);
INSERT INTO `sys_role_menu` VALUES (8, 1126);
INSERT INTO `sys_role_menu` VALUES (8, 1127);
INSERT INTO `sys_role_menu` VALUES (8, 1128);
INSERT INTO `sys_role_menu` VALUES (8, 1129);
INSERT INTO `sys_role_menu` VALUES (8, 1130);
INSERT INTO `sys_role_menu` VALUES (8, 1131);
INSERT INTO `sys_role_menu` VALUES (8, 1132);
INSERT INTO `sys_role_menu` VALUES (8, 1133);
INSERT INTO `sys_role_menu` VALUES (8, 1134);
INSERT INTO `sys_role_menu` VALUES (8, 1135);
INSERT INTO `sys_role_menu` VALUES (8, 1136);
INSERT INTO `sys_role_menu` VALUES (8, 1137);
INSERT INTO `sys_role_menu` VALUES (8, 1138);
INSERT INTO `sys_role_menu` VALUES (8, 1139);
INSERT INTO `sys_role_menu` VALUES (8, 1140);
INSERT INTO `sys_role_menu` VALUES (8, 1141);
INSERT INTO `sys_role_menu` VALUES (8, 1142);
INSERT INTO `sys_role_menu` VALUES (8, 1143);
INSERT INTO `sys_role_menu` VALUES (8, 1144);
INSERT INTO `sys_role_menu` VALUES (8, 1145);
INSERT INTO `sys_role_menu` VALUES (8, 1146);
INSERT INTO `sys_role_menu` VALUES (8, 1147);
INSERT INTO `sys_role_menu` VALUES (8, 1148);
INSERT INTO `sys_role_menu` VALUES (8, 1149);
INSERT INTO `sys_role_menu` VALUES (8, 1150);
INSERT INTO `sys_role_menu` VALUES (8, 1151);
INSERT INTO `sys_role_menu` VALUES (8, 1152);
INSERT INTO `sys_role_menu` VALUES (8, 1153);
INSERT INTO `sys_role_menu` VALUES (8, 1154);
INSERT INTO `sys_role_menu` VALUES (8, 1155);
INSERT INTO `sys_role_menu` VALUES (8, 1156);
INSERT INTO `sys_role_menu` VALUES (8, 1157);
INSERT INTO `sys_role_menu` VALUES (8, 1158);
INSERT INTO `sys_role_menu` VALUES (8, 1159);
INSERT INTO `sys_role_menu` VALUES (8, 1160);
INSERT INTO `sys_role_menu` VALUES (8, 1161);
INSERT INTO `sys_role_menu` VALUES (8, 1162);
INSERT INTO `sys_role_menu` VALUES (8, 1163);
INSERT INTO `sys_role_menu` VALUES (8, 1164);
INSERT INTO `sys_role_menu` VALUES (8, 1165);
INSERT INTO `sys_role_menu` VALUES (8, 1166);
INSERT INTO `sys_role_menu` VALUES (8, 1167);
INSERT INTO `sys_role_menu` VALUES (8, 1168);
INSERT INTO `sys_role_menu` VALUES (8, 1169);
INSERT INTO `sys_role_menu` VALUES (8, 1170);
INSERT INTO `sys_role_menu` VALUES (8, 1171);
INSERT INTO `sys_role_menu` VALUES (8, 1172);
INSERT INTO `sys_role_menu` VALUES (8, 1173);
INSERT INTO `sys_role_menu` VALUES (8, 1174);
INSERT INTO `sys_role_menu` VALUES (8, 1175);
INSERT INTO `sys_role_menu` VALUES (8, 1176);
INSERT INTO `sys_role_menu` VALUES (8, 1177);
INSERT INTO `sys_role_menu` VALUES (8, 1178);
INSERT INTO `sys_role_menu` VALUES (8, 1179);
INSERT INTO `sys_role_menu` VALUES (8, 1180);
INSERT INTO `sys_role_menu` VALUES (8, 1181);
INSERT INTO `sys_role_menu` VALUES (8, 1182);
INSERT INTO `sys_role_menu` VALUES (8, 1183);
INSERT INTO `sys_role_menu` VALUES (8, 1184);
INSERT INTO `sys_role_menu` VALUES (8, 1185);
INSERT INTO `sys_role_menu` VALUES (8, 1186);
INSERT INTO `sys_role_menu` VALUES (8, 1187);
INSERT INTO `sys_role_menu` VALUES (8, 1188);
INSERT INTO `sys_role_menu` VALUES (8, 1189);
INSERT INTO `sys_role_menu` VALUES (8, 1190);
INSERT INTO `sys_role_menu` VALUES (8, 1191);
INSERT INTO `sys_role_menu` VALUES (8, 1192);
INSERT INTO `sys_role_menu` VALUES (8, 1193);
INSERT INTO `sys_role_menu` VALUES (8, 1194);
INSERT INTO `sys_role_menu` VALUES (8, 1195);
INSERT INTO `sys_role_menu` VALUES (8, 1196);
INSERT INTO `sys_role_menu` VALUES (8, 1197);
INSERT INTO `sys_role_menu` VALUES (8, 1198);
INSERT INTO `sys_role_menu` VALUES (8, 1199);
INSERT INTO `sys_role_menu` VALUES (8, 1200);
INSERT INTO `sys_role_menu` VALUES (8, 1201);
INSERT INTO `sys_role_menu` VALUES (8, 1202);
INSERT INTO `sys_role_menu` VALUES (8, 1203);
INSERT INTO `sys_role_menu` VALUES (8, 1204);
INSERT INTO `sys_role_menu` VALUES (8, 1205);
INSERT INTO `sys_role_menu` VALUES (8, 1206);
INSERT INTO `sys_role_menu` VALUES (8, 1207);
INSERT INTO `sys_role_menu` VALUES (8, 1208);
INSERT INTO `sys_role_menu` VALUES (8, 1209);
INSERT INTO `sys_role_menu` VALUES (8, 1210);
INSERT INTO `sys_role_menu` VALUES (8, 1211);
INSERT INTO `sys_role_menu` VALUES (8, 1212);
INSERT INTO `sys_role_menu` VALUES (8, 1213);
INSERT INTO `sys_role_menu` VALUES (8, 1214);
INSERT INTO `sys_role_menu` VALUES (8, 1215);
INSERT INTO `sys_role_menu` VALUES (8, 1216);
INSERT INTO `sys_role_menu` VALUES (8, 1217);
INSERT INTO `sys_role_menu` VALUES (8, 1218);
INSERT INTO `sys_role_menu` VALUES (8, 1219);
INSERT INTO `sys_role_menu` VALUES (8, 1220);
INSERT INTO `sys_role_menu` VALUES (8, 1221);
INSERT INTO `sys_role_menu` VALUES (8, 1222);
INSERT INTO `sys_role_menu` VALUES (8, 1223);
INSERT INTO `sys_role_menu` VALUES (8, 1224);
INSERT INTO `sys_role_menu` VALUES (8, 1225);
INSERT INTO `sys_role_menu` VALUES (8, 1226);
INSERT INTO `sys_role_menu` VALUES (8, 1227);
INSERT INTO `sys_role_menu` VALUES (8, 1228);
INSERT INTO `sys_role_menu` VALUES (8, 1229);
INSERT INTO `sys_role_menu` VALUES (8, 1230);
INSERT INTO `sys_role_menu` VALUES (8, 1231);
INSERT INTO `sys_role_menu` VALUES (8, 1232);
INSERT INTO `sys_role_menu` VALUES (8, 1233);
INSERT INTO `sys_role_menu` VALUES (8, 1234);
INSERT INTO `sys_role_menu` VALUES (8, 1235);
INSERT INTO `sys_role_menu` VALUES (8, 1236);
INSERT INTO `sys_role_menu` VALUES (8, 1237);
INSERT INTO `sys_role_menu` VALUES (8, 1238);
INSERT INTO `sys_role_menu` VALUES (8, 1239);
INSERT INTO `sys_role_menu` VALUES (8, 1240);
INSERT INTO `sys_role_menu` VALUES (8, 1241);
INSERT INTO `sys_role_menu` VALUES (8, 1242);
INSERT INTO `sys_role_menu` VALUES (8, 1243);
INSERT INTO `sys_role_menu` VALUES (8, 1244);
INSERT INTO `sys_role_menu` VALUES (8, 1245);
INSERT INTO `sys_role_menu` VALUES (8, 1246);
INSERT INTO `sys_role_menu` VALUES (8, 1247);
INSERT INTO `sys_role_menu` VALUES (8, 1248);
INSERT INTO `sys_role_menu` VALUES (8, 1249);
INSERT INTO `sys_role_menu` VALUES (8, 1250);
INSERT INTO `sys_role_menu` VALUES (8, 1251);
INSERT INTO `sys_role_menu` VALUES (8, 1252);
INSERT INTO `sys_role_menu` VALUES (8, 1253);
INSERT INTO `sys_role_menu` VALUES (8, 1254);
INSERT INTO `sys_role_menu` VALUES (8, 1255);
INSERT INTO `sys_role_menu` VALUES (8, 1256);
INSERT INTO `sys_role_menu` VALUES (8, 1257);
INSERT INTO `sys_role_menu` VALUES (8, 1258);
INSERT INTO `sys_role_menu` VALUES (8, 1259);
INSERT INTO `sys_role_menu` VALUES (8, 1260);
INSERT INTO `sys_role_menu` VALUES (8, 1261);
INSERT INTO `sys_role_menu` VALUES (8, 1262);
INSERT INTO `sys_role_menu` VALUES (8, 1263);
INSERT INTO `sys_role_menu` VALUES (8, 1264);
INSERT INTO `sys_role_menu` VALUES (8, 1265);
INSERT INTO `sys_role_menu` VALUES (8, 1266);
INSERT INTO `sys_role_menu` VALUES (8, 1267);
INSERT INTO `sys_role_menu` VALUES (8, 1268);
INSERT INTO `sys_role_menu` VALUES (8, 1269);
INSERT INTO `sys_role_menu` VALUES (8, 1270);
INSERT INTO `sys_role_menu` VALUES (8, 1271);
INSERT INTO `sys_role_menu` VALUES (8, 1272);
INSERT INTO `sys_role_menu` VALUES (8, 1273);
INSERT INTO `sys_role_menu` VALUES (8, 1274);
INSERT INTO `sys_role_menu` VALUES (8, 1275);
INSERT INTO `sys_role_menu` VALUES (8, 1276);
INSERT INTO `sys_role_menu` VALUES (8, 1277);
INSERT INTO `sys_role_menu` VALUES (8, 1278);
INSERT INTO `sys_role_menu` VALUES (8, 1279);
INSERT INTO `sys_role_menu` VALUES (8, 1280);
INSERT INTO `sys_role_menu` VALUES (8, 1281);
INSERT INTO `sys_role_menu` VALUES (8, 1282);
INSERT INTO `sys_role_menu` VALUES (8, 1283);
INSERT INTO `sys_role_menu` VALUES (8, 1284);
INSERT INTO `sys_role_menu` VALUES (8, 1285);
INSERT INTO `sys_role_menu` VALUES (8, 1286);
INSERT INTO `sys_role_menu` VALUES (8, 1287);
INSERT INTO `sys_role_menu` VALUES (8, 1288);
INSERT INTO `sys_role_menu` VALUES (8, 1289);
INSERT INTO `sys_role_menu` VALUES (8, 1290);
INSERT INTO `sys_role_menu` VALUES (8, 1291);
INSERT INTO `sys_role_menu` VALUES (8, 1292);
INSERT INTO `sys_role_menu` VALUES (8, 1293);
INSERT INTO `sys_role_menu` VALUES (8, 1294);
INSERT INTO `sys_role_menu` VALUES (8, 1295);
INSERT INTO `sys_role_menu` VALUES (8, 1296);
INSERT INTO `sys_role_menu` VALUES (8, 1297);
INSERT INTO `sys_role_menu` VALUES (8, 1298);
INSERT INTO `sys_role_menu` VALUES (8, 1299);
INSERT INTO `sys_role_menu` VALUES (8, 1300);
INSERT INTO `sys_role_menu` VALUES (8, 1301);
INSERT INTO `sys_role_menu` VALUES (8, 1302);
INSERT INTO `sys_role_menu` VALUES (8, 1303);
INSERT INTO `sys_role_menu` VALUES (8, 1304);
INSERT INTO `sys_role_menu` VALUES (8, 1305);
INSERT INTO `sys_role_menu` VALUES (8, 1306);
INSERT INTO `sys_role_menu` VALUES (8, 1307);
INSERT INTO `sys_role_menu` VALUES (8, 1308);
INSERT INTO `sys_role_menu` VALUES (8, 1309);
INSERT INTO `sys_role_menu` VALUES (8, 1310);
INSERT INTO `sys_role_menu` VALUES (8, 1311);
INSERT INTO `sys_role_menu` VALUES (8, 1312);
INSERT INTO `sys_role_menu` VALUES (8, 1313);
INSERT INTO `sys_role_menu` VALUES (8, 1314);
INSERT INTO `sys_role_menu` VALUES (8, 1315);
INSERT INTO `sys_role_menu` VALUES (8, 1316);
INSERT INTO `sys_role_menu` VALUES (8, 1317);
INSERT INTO `sys_role_menu` VALUES (8, 1318);
INSERT INTO `sys_role_menu` VALUES (8, 1319);
INSERT INTO `sys_role_menu` VALUES (8, 1320);
INSERT INTO `sys_role_menu` VALUES (8, 1321);
INSERT INTO `sys_role_menu` VALUES (8, 1322);
INSERT INTO `sys_role_menu` VALUES (8, 1323);
INSERT INTO `sys_role_menu` VALUES (8, 1324);
INSERT INTO `sys_role_menu` VALUES (8, 1325);
INSERT INTO `sys_role_menu` VALUES (8, 1326);
INSERT INTO `sys_role_menu` VALUES (8, 1327);
INSERT INTO `sys_role_menu` VALUES (8, 1328);
INSERT INTO `sys_role_menu` VALUES (8, 1329);
INSERT INTO `sys_role_menu` VALUES (8, 1330);
INSERT INTO `sys_role_menu` VALUES (8, 1331);
INSERT INTO `sys_role_menu` VALUES (8, 1332);
INSERT INTO `sys_role_menu` VALUES (8, 1333);
INSERT INTO `sys_role_menu` VALUES (8, 1334);
INSERT INTO `sys_role_menu` VALUES (8, 1335);
INSERT INTO `sys_role_menu` VALUES (8, 1336);
INSERT INTO `sys_role_menu` VALUES (8, 1337);
INSERT INTO `sys_role_menu` VALUES (8, 1338);
INSERT INTO `sys_role_menu` VALUES (8, 1339);
INSERT INTO `sys_role_menu` VALUES (8, 1340);
INSERT INTO `sys_role_menu` VALUES (8, 1341);
INSERT INTO `sys_role_menu` VALUES (8, 1342);
INSERT INTO `sys_role_menu` VALUES (8, 1343);
INSERT INTO `sys_role_menu` VALUES (8, 1344);
INSERT INTO `sys_role_menu` VALUES (8, 1345);
INSERT INTO `sys_role_menu` VALUES (8, 1346);
INSERT INTO `sys_role_menu` VALUES (8, 1347);
INSERT INTO `sys_role_menu` VALUES (8, 1348);
INSERT INTO `sys_role_menu` VALUES (8, 1349);
INSERT INTO `sys_role_menu` VALUES (8, 1350);
INSERT INTO `sys_role_menu` VALUES (8, 1351);
INSERT INTO `sys_role_menu` VALUES (8, 1352);
INSERT INTO `sys_role_menu` VALUES (8, 1353);
INSERT INTO `sys_role_menu` VALUES (8, 1354);
INSERT INTO `sys_role_menu` VALUES (8, 1356);
INSERT INTO `sys_role_menu` VALUES (8, 1358);
INSERT INTO `sys_role_menu` VALUES (8, 1359);
INSERT INTO `sys_role_menu` VALUES (8, 1360);
INSERT INTO `sys_role_menu` VALUES (8, 1361);
INSERT INTO `sys_role_menu` VALUES (8, 1362);
INSERT INTO `sys_role_menu` VALUES (8, 1363);
INSERT INTO `sys_role_menu` VALUES (8, 1364);
INSERT INTO `sys_role_menu` VALUES (8, 1365);
INSERT INTO `sys_role_menu` VALUES (8, 1366);
INSERT INTO `sys_role_menu` VALUES (8, 1367);
INSERT INTO `sys_role_menu` VALUES (8, 1368);
INSERT INTO `sys_role_menu` VALUES (8, 1369);
INSERT INTO `sys_role_menu` VALUES (8, 1370);
INSERT INTO `sys_role_menu` VALUES (8, 1371);
INSERT INTO `sys_role_menu` VALUES (8, 1372);
INSERT INTO `sys_role_menu` VALUES (8, 1373);
INSERT INTO `sys_role_menu` VALUES (8, 2308);
INSERT INTO `sys_role_menu` VALUES (8, 2309);
INSERT INTO `sys_role_menu` VALUES (8, 2310);
INSERT INTO `sys_role_menu` VALUES (8, 2311);
INSERT INTO `sys_role_menu` VALUES (8, 2312);
INSERT INTO `sys_role_menu` VALUES (8, 2320);
INSERT INTO `sys_role_menu` VALUES (8, 2327);
INSERT INTO `sys_role_menu` VALUES (8, 2328);
INSERT INTO `sys_role_menu` VALUES (8, 2329);
INSERT INTO `sys_role_menu` VALUES (8, 2330);
INSERT INTO `sys_role_menu` VALUES (8, 2331);
INSERT INTO `sys_role_menu` VALUES (8, 2332);
INSERT INTO `sys_role_menu` VALUES (8, 2333);
INSERT INTO `sys_role_menu` VALUES (8, 2334);
INSERT INTO `sys_role_menu` VALUES (8, 2335);
INSERT INTO `sys_role_menu` VALUES (8, 2336);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `dept_id` bigint NULL DEFAULT NULL COMMENT '部门ID',
  `dept_rank` int NULL DEFAULT NULL COMMENT '职级ID',
  `user_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户账号',
  `user_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户编码',
  `nick_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `user_type` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '00' COMMENT '用户类型（00系统用户）',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '用户邮箱',
  `phonenumber` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '手机号码',
  `sex` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '头像地址',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '密码',
  `leader_id` bigint NULL DEFAULT NULL COMMENT '主管ID',
  `supplier_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '供应商编码',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `login_ip` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '最后登录IP',
  `login_date` datetime NULL DEFAULT NULL COMMENT '最后登录时间',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB  CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户信息表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 103, 1, 'admin', '10001', '管理员', '00', 'admin@skeqi.com', '19999999999', '0', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', NULL, NULL, '0', '0', '127.0.0.1', '2021-09-04 14:37:35', 'admin', '2021-07-06 16:53:36', 'admin', '2021-09-04 14:37:35', '管理员');

-- ----------------------------
-- Table structure for sys_user_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_post`;
CREATE TABLE `sys_user_post`  (
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `post_id` bigint NOT NULL COMMENT '岗位ID',
  PRIMARY KEY (`user_id`, `post_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户与岗位关联表' ROW_FORMAT = COMPACT;



-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户和角色关联表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1);

-- ----------------------------
-- Triggers structure for table sys_dept
-- ----------------------------
DROP TRIGGER IF EXISTS `base_data_add_by_sys_dept`;
delimiter ;;
CREATE TRIGGER `base_data_add_by_sys_dept` AFTER INSERT ON `sys_dept` FOR EACH ROW BEGIN
DECLARE lsort int;
#查询当前最大的排序id
select  (max(f_sort)+1) into lsort from t_bd_base_data where f_type_id = 3;
#不存在就为1
if lsort is null then
	set lsort = 1;
end if;

INSERT INTO
`t_bd_base_data`( `f_name`, `f_number`, `f_remark`, `f_sort`, `f_type_id`, `f_type_name`) VALUES
(NEW.`dept_name`, NEW.dept_id, '', lsort, 3, '部门');

END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table sys_dept
-- ----------------------------
DROP TRIGGER IF EXISTS `sys_dept_to_department_trigger`;
delimiter ;;
CREATE TRIGGER `sys_dept_to_department_trigger` AFTER INSERT ON `sys_dept` FOR EACH ROW BEGIN
 INSERT INTO c_wms_department_t(ID,DM_NUMBER,DM_NAME,MODIFY_TIME,superiorId,executive) VALUES(new.dept_id,new.dept_code,new.dept_name,now(),new.parent_id,new.leader_id);

END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table sys_dept
-- ----------------------------
DROP TRIGGER IF EXISTS `base_data_update_by_sys_dept`;
delimiter ;;
CREATE TRIGGER `base_data_update_by_sys_dept` AFTER UPDATE ON `sys_dept` FOR EACH ROW BEGIN
update t_bd_base_data set f_name = NEW.`dept_name`,f_number=NEW.dept_id,f_remark = ''
where f_type_id = 3 and f_number = old.dept_id;

END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table sys_dept
-- ----------------------------
DROP TRIGGER IF EXISTS `sys_dept_update_department_trigger`;
delimiter ;;
CREATE TRIGGER `sys_dept_update_department_trigger` AFTER UPDATE ON `sys_dept` FOR EACH ROW BEGIN
set @del_flag=(SELECT case del_flag when '0' THEN '1'
                        END '0'
               from sys_dept WHERE dept_id=old.dept_id);
 UPDATE c_wms_department_t SET DM_NAME=new.dept_name,DM_NUMBER=new.dept_code,superiorId=new.parent_id,executive=new.leader_id,VIEW_MODE=@del_flag WHERE id=new.dept_id;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table sys_dept
-- ----------------------------
DROP TRIGGER IF EXISTS `base_data_delete_by_sys_dept`;
delimiter ;;
CREATE TRIGGER `base_data_delete_by_sys_dept` AFTER DELETE ON `sys_dept` FOR EACH ROW BEGIN
delete from  t_bd_base_data
where f_type_id = 3 and f_number = old.dept_id;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table sys_dept_rank
-- ----------------------------
DROP TRIGGER IF EXISTS `sys_dept_rank_to_qh_rank_trigger`;
delimiter ;;
CREATE TRIGGER `sys_dept_rank_to_qh_rank_trigger` AFTER INSERT ON `sys_dept_rank` FOR EACH ROW BEGIN
 INSERT INTO c_qh_rank_t(id,dt,rankName,grade) VALUES(new.id,now(),new.name,new.level);
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table sys_dept_rank
-- ----------------------------
DROP TRIGGER IF EXISTS `sys_dept_rank_update_qh_rank_trigger`;
delimiter ;;
CREATE TRIGGER `sys_dept_rank_update_qh_rank_trigger` AFTER UPDATE ON `sys_dept_rank` FOR EACH ROW BEGIN
 UPDATE c_qh_rank_t SET rankName=new.name,grade=new.level WHERE id=old.id;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table sys_dept_rank
-- ----------------------------
DROP TRIGGER IF EXISTS `sys_dept_rank_del_qh_rank`;
delimiter ;;
CREATE TRIGGER `sys_dept_rank_del_qh_rank` AFTER DELETE ON `sys_dept_rank` FOR EACH ROW begin
delete from c_qh_rank_t where id=old.id;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table sys_user
-- ----------------------------
DROP TRIGGER IF EXISTS `sys_user_to_mes_user_trigger`;
delimiter ;;
CREATE TRIGGER `sys_user_to_mes_user_trigger` AFTER INSERT ON `sys_user` FOR EACH ROW BEGIN
INSERT INTO c_mes_user_t(ID,USER_NAME,DEPARTMENT,mobile,email,reportsTo,name,rankId,supplier_code) VALUES(new.user_id,new.user_name,new.dept_id,new.phonenumber,new.email,new.leader_id,new.nick_name,new.dept_rank,new.supplier_code);
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table sys_user
-- ----------------------------
DROP TRIGGER IF EXISTS `sys_user_update_mes_user_trigger`;
delimiter ;;
CREATE TRIGGER `sys_user_update_mes_user_trigger` AFTER UPDATE ON `sys_user` FOR EACH ROW begin
set @del_flag=(SELECT case del_flag when '0' THEN '1'
                        END '0'
               from sys_user WHERE user_id=old.user_id);
update c_mes_user_t set DEPARTMENT=new.dept_id,mobile=new.phonenumber,email=new.email,reportsTo=new.leader_id,supplier_code=new.supplier_code,rankId=new.dept_rank,name=new.nick_name,viewMode=@del_flag where ID=old.user_id;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table sys_user_post
-- ----------------------------
DROP TRIGGER IF EXISTS `sys_user_post_to_mes_user_trigger`;
delimiter ;;
CREATE TRIGGER `sys_user_post_to_mes_user_trigger` AFTER INSERT ON `sys_user_post` FOR EACH ROW BEGIN
set @postname=(SELECT GROUP_CONCAT(p.post_name) as post_name FROM (SELECT * from sys_user_post WHERE user_id=new.user_id) up LEFT JOIN sys_post p on p.post_id=up.post_id);
UPDATE c_mes_user_t set position=@postname WHERE ID=new.user_id;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table sys_user_post
-- ----------------------------
DROP TRIGGER IF EXISTS `sys_user_post_update_mes_user_trigger`;
delimiter ;;
CREATE TRIGGER `sys_user_post_update_mes_user_trigger` AFTER UPDATE ON `sys_user_post` FOR EACH ROW begin
set @postname=(SELECT GROUP_CONCAT(p.post_name) as post_name FROM (SELECT * from sys_user_post WHERE user_id=old.user_id) up LEFT JOIN sys_post p on p.post_id=up.post_id);
UPDATE c_mes_user_t set position=@postname WHERE ID=old.user_id;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;


-- ----------------------------
-- MES SYSTEM -Table structure for c_alarm_email_config
-- ----------------------------
DROP TABLE IF EXISTS `c_alarm_email_config`;
CREATE TABLE `c_alarm_email_config`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `sender_email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发件人',
  `the_server` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '服务器',
  `authorization_code` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '授权码',
  `if_enable` int(0) NULL DEFAULT NULL COMMENT '是否启用(0 关闭，1 开启)',
  `cdt` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `udt` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '邮箱服务配置' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_alarm_email_config
-- ----------------------------

-- ----------------------------
-- Table structure for c_alarm_event
-- ----------------------------
DROP TABLE IF EXISTS `c_alarm_event`;
CREATE TABLE `c_alarm_event`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `event` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '事件',
  `user_id` int(0) NULL DEFAULT NULL,
  `dt` datetime(0) NULL DEFAULT NULL,
  `note` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `view_mode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '0:不可查；1：可查',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '事件' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_alarm_event
-- ----------------------------

-- ----------------------------
-- Table structure for c_alarm_ip_white_list
-- ----------------------------
DROP TABLE IF EXISTS `c_alarm_ip_white_list`;
CREATE TABLE `c_alarm_ip_white_list`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `user_id` int(0) NULL DEFAULT NULL,
  `ip` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'ip白名单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_alarm_ip_white_list
-- ----------------------------

-- ----------------------------
-- Table structure for c_alarm_notice_logs
-- ----------------------------
DROP TABLE IF EXISTS `c_alarm_notice_logs`;
CREATE TABLE `c_alarm_notice_logs`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `fault_id` int(0) NULL DEFAULT NULL COMMENT '故障id',
  `lossType` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '损失类型',
  `dt` datetime(0) NULL DEFAULT NULL COMMENT '时间',
  `sendOut` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发送',
  `receive` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接收',
  `channels` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '渠道',
  `channelsType` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '渠道类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 94 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '通知记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_alarm_notice_logs
-- ----------------------------

-- ----------------------------
-- Table structure for c_alarm_notification_channels
-- ----------------------------
DROP TABLE IF EXISTS `c_alarm_notification_channels`;
CREATE TABLE `c_alarm_notification_channels`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `user_id` int(0) NULL DEFAULT NULL COMMENT '用户id',
  `notification_channels_type_id` int(0) NULL DEFAULT NULL COMMENT '通知渠道类型id',
  `notification_channels` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '通知渠道',
  `notification_channels_content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容',
  `dt` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `view_mode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '0:不可查；1：可查',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '通知渠道' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_alarm_notification_channels
-- ----------------------------

-- ----------------------------
-- Table structure for c_alarm_notification_channels_type
-- ----------------------------
DROP TABLE IF EXISTS `c_alarm_notification_channels_type`;
CREATE TABLE `c_alarm_notification_channels_type`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `notification_channels_type_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '渠道类型名称',
  `note` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '说明',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_alarm_notification_channels_type
-- ----------------------------

-- ----------------------------
-- Table structure for c_alarm_notification_method
-- ----------------------------
DROP TABLE IF EXISTS `c_alarm_notification_method`;
CREATE TABLE `c_alarm_notification_method`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `Loss_Type_Id` int(0) NULL DEFAULT NULL COMMENT '损失类型id',
  `notification_channels_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '通知渠道',
  `user_id` int(0) NULL DEFAULT NULL COMMENT '用户ID',
  `service_Id` int(0) NULL DEFAULT NULL COMMENT '服务id',
  `problem_Level_Id` int(0) NULL DEFAULT NULL COMMENT '问题等级',
  `problem_State` int(0) NULL DEFAULT NULL COMMENT '问题状态(1:触发、2:响应、3:处理)',
  `dt` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 55 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '通知方式' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_alarm_notification_method
-- ----------------------------

-- ----------------------------
-- Table structure for c_alarm_problem_level
-- ----------------------------
DROP TABLE IF EXISTS `c_alarm_problem_level`;
CREATE TABLE `c_alarm_problem_level`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `level` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '级别',
  `explain` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '说明',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '问题等级' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_alarm_problem_level
-- ----------------------------

-- ----------------------------
-- Table structure for c_alarm_problem_upgrade
-- ----------------------------
DROP TABLE IF EXISTS `c_alarm_problem_upgrade`;
CREATE TABLE `c_alarm_problem_upgrade`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `problem_level_id` int(0) NULL DEFAULT NULL COMMENT '问题等级',
  `upgrade_problem_level_id` int(0) NULL DEFAULT NULL COMMENT '升级后的问题等级',
  `trigger_time` int(0) NULL DEFAULT NULL COMMENT '触发时间/分钟',
  `Loss_Type_Id` int(0) NULL DEFAULT NULL COMMENT '损失类型id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '问题升级' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_alarm_problem_upgrade
-- ----------------------------

-- ----------------------------
-- Table structure for c_alarm_problem_upgrade_logs
-- ----------------------------
DROP TABLE IF EXISTS `c_alarm_problem_upgrade_logs`;
CREATE TABLE `c_alarm_problem_upgrade_logs`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `fault_id` int(0) NULL DEFAULT NULL COMMENT '故障id',
  `before_Upgrade_level_Id` int(0) NULL DEFAULT NULL COMMENT '升级前等级编号',
  `after_Upgrade_level_Id` int(0) NULL DEFAULT NULL COMMENT '升级后等级编号 ',
  `dt` datetime(0) NULL DEFAULT NULL COMMENT '升级时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 229 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '问题升级日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_alarm_problem_upgrade_logs
-- ----------------------------

-- ----------------------------
-- Table structure for c_alarm_shortmessage_config
-- ----------------------------
DROP TABLE IF EXISTS `c_alarm_shortmessage_config`;
CREATE TABLE `c_alarm_shortmessage_config`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `user_id` int(0) NULL DEFAULT NULL COMMENT '用户id',
  `header` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '请求头',
  `parameter` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '参数',
  `charset` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字符集',
  `api_url` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '接口地址',
  `request_method` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求方式',
  `dt` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '短信服务配置' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_alarm_shortmessage_config
-- ----------------------------

-- ----------------------------
-- Table structure for c_andon_message
-- ----------------------------
DROP TABLE IF EXISTS `c_andon_message`;
CREATE TABLE `c_andon_message`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `dt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '安灯-信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_andon_message
-- ----------------------------

-- ----------------------------
-- Table structure for c_andon_message_logs
-- ----------------------------
DROP TABLE IF EXISTS `c_andon_message_logs`;
CREATE TABLE `c_andon_message_logs`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `lineName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `stationName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `lossType` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `fault_id` int(0) NULL DEFAULT NULL,
  `dt` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_andon_message_logs
-- ----------------------------

-- ----------------------------
-- Table structure for c_crm_billing_information
-- ----------------------------
DROP TABLE IF EXISTS `c_crm_billing_information`;
CREATE TABLE `c_crm_billing_information`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '开票信息ID（主键）',
  `CUSTOMER_ID` int(0) NULL DEFAULT NULL COMMENT '客户基础信息ID',
  `DUTY_PARAGRAPH` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '税号',
  `UNIT_ADDRESS` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '单位地址',
  `TEL` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `BANK_OF_DEPOSIT` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开户银行',
  `BANK_ACCOUNT` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '银行账户',
  `QR_CODE` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '二维码(图片)',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '开票信息表(关联：客户基础信息)' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_crm_billing_information
-- ----------------------------

-- ----------------------------
-- Table structure for c_crm_customer_basic_information
-- ----------------------------
DROP TABLE IF EXISTS `c_crm_customer_basic_information`;
CREATE TABLE `c_crm_customer_basic_information`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '客户ID(主键)',
  `CUSTOMER_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户名称',
  `CUSTOMER_TYPE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户类型(睡眠客户、潜在客户、我的客户)',
  `CUSTOMER_WEBSITE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户官网',
  `CUSTOMER_PROFILE` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '客户简介',
  `BUSINESS_PERSON` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '营业当担人',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 109 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '客户基础信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_crm_customer_basic_information
-- ----------------------------

-- ----------------------------
-- Table structure for c_crm_customer_department_information
-- ----------------------------
DROP TABLE IF EXISTS `c_crm_customer_department_information`;
CREATE TABLE `c_crm_customer_department_information`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '客户组织结构ID（主键）',
  `CUSTOMER_ID` int(0) NULL DEFAULT NULL COMMENT '客户基础信息表ID',
  `CUSTOMER_DEPARTMENT` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户部门',
  `DEPARTMENT_DESCRIPTION` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户部门描述',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 77 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '客户组织架构表（部门表）' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_crm_customer_department_information
-- ----------------------------

-- ----------------------------
-- Table structure for c_crm_customer_department_member
-- ----------------------------
DROP TABLE IF EXISTS `c_crm_customer_department_member`;
CREATE TABLE `c_crm_customer_department_member`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '客户部门成员ID(主键)',
  `CUSTOMER_ID` int(0) NULL DEFAULT NULL COMMENT '客户基础信息ID',
  `CUSTOMER_DEPARTMENT_ID` int(0) NULL DEFAULT NULL COMMENT '客户部门ID',
  `NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `TEL` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `ADDRESS` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '住址',
  `HOBBY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '爱好',
  `MAIL` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮件',
  `POSITION` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职位',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 75 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '客户部门成员表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_crm_customer_department_member
-- ----------------------------

-- ----------------------------
-- Table structure for c_crm_file_type_info
-- ----------------------------
DROP TABLE IF EXISTS `c_crm_file_type_info`;
CREATE TABLE `c_crm_file_type_info`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '文件类型ID',
  `FILE_TYPE_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件类型名称',
  `FILE_SHOW` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否展示 ：2公司文件 1项目文件  0项目项',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_crm_file_type_info
-- ----------------------------

-- ----------------------------
-- Table structure for c_crm_file_upload_info
-- ----------------------------
DROP TABLE IF EXISTS `c_crm_file_upload_info`;
CREATE TABLE `c_crm_file_upload_info`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT 'ID(主键)',
  `PROJECT_ID` int(0) NULL DEFAULT NULL COMMENT '项目ID',
  `TYPE_ID` int(0) NULL DEFAULT NULL COMMENT '文件类型ID',
  `FILE_PATH` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件路径',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 76 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文件上传表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_crm_file_upload_info
-- ----------------------------

-- ----------------------------
-- Table structure for c_crm_log_t
-- ----------------------------
DROP TABLE IF EXISTS `c_crm_log_t`;
CREATE TABLE `c_crm_log_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT 'CRM日志主键',
  `DT` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作时间',
  `USER` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作用户',
  `MENU_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3668 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_crm_log_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_crm_log_t_copy1
-- ----------------------------
DROP TABLE IF EXISTS `c_crm_log_t_copy1`;
CREATE TABLE `c_crm_log_t_copy1`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT 'CRM日志主键',
  `DT` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作时间',
  `USER` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作用户',
  `MENU_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_crm_log_t_copy1
-- ----------------------------

-- ----------------------------
-- Table structure for c_crm_postal_address
-- ----------------------------
DROP TABLE IF EXISTS `c_crm_postal_address`;
CREATE TABLE `c_crm_postal_address`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '通讯地址ID',
  `CUSTOMER_ID` int(0) NULL DEFAULT NULL COMMENT '客户基础信息ID',
  `RECEIVING_ADDRESS` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '收货地址',
  `RECEIVING_ADDRESS_CONTACTS` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收发货联系人',
  `INVOICE_RECEIVING_ADDRESS` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '收发票地址',
  `INVOICE_RECEIVING_ADDRESS_CONTACTS` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发票联系人',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 42 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '通讯地址表（关联：客户基础信息表）' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_crm_postal_address
-- ----------------------------

-- ----------------------------
-- Table structure for c_crm_project_basic_info
-- ----------------------------
DROP TABLE IF EXISTS `c_crm_project_basic_info`;
CREATE TABLE `c_crm_project_basic_info`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT 'ID(主键)',
  `PROJECT_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目名称',
  `PROJECT_NO` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目号',
  `SCHEME_NO` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '方案号',
  `PROJECT_ADDRESS` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '项目地址',
  `PROJECT_MANAGER` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目经理',
  `CUSTOMER_ID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户ID',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '项目基础信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_crm_project_basic_info
-- ----------------------------

-- ----------------------------
-- Table structure for c_lsl_bom_detail_t
-- ----------------------------
DROP TABLE IF EXISTS `c_lsl_bom_detail_t`;
CREATE TABLE `c_lsl_bom_detail_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cdt` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `udt` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `bomId` int(0) NULL DEFAULT NULL COMMENT 'bom主表id',
  `stationId` int(0) NULL DEFAULT NULL COMMENT '工位id',
  `materialId` int(0) NULL DEFAULT NULL COMMENT '物料id',
  `quantity` int(0) NULL DEFAULT NULL COMMENT '数量',
  `status` int(0) NULL DEFAULT NULL COMMENT '状态 1 启用 2 关闭',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'BOM明细表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_lsl_bom_detail_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_lsl_bom_t
-- ----------------------------
DROP TABLE IF EXISTS `c_lsl_bom_t`;
CREATE TABLE `c_lsl_bom_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cdt` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `udt` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `productId` int(0) NULL DEFAULT NULL COMMENT '产品id',
  `lineId` int(0) NULL DEFAULT NULL COMMENT '产线id',
  `status` int(0) NULL DEFAULT NULL COMMENT '状态 1 启用 2 关闭',
  `bomCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '编号',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'BOM总表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_lsl_bom_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_lsl_check_inventory_detailed_detail_t
-- ----------------------------
DROP TABLE IF EXISTS `c_lsl_check_inventory_detailed_detail_t`;
CREATE TABLE `c_lsl_check_inventory_detailed_detail_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `number` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '明细编号',
  `detailed_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '详情编号',
  `real_quantity` int(0) NULL DEFAULT NULL COMMENT '实际数量',
  `warehouse_quantity` int(0) NULL DEFAULT NULL COMMENT '系统数量',
  `discrepancy_quantity` int(0) NULL DEFAULT NULL COMMENT '差异数量',
  `material_sn` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '总成号',
  `cdt` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `udt` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '线边库盘点库存详情明细表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_lsl_check_inventory_detailed_detail_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_lsl_check_inventory_detailed_t
-- ----------------------------
DROP TABLE IF EXISTS `c_lsl_check_inventory_detailed_t`;
CREATE TABLE `c_lsl_check_inventory_detailed_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `number` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '行号',
  `check_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '盘点单号',
  `material_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '物料编码',
  `real_quantity` int(0) NULL DEFAULT NULL COMMENT '实际数量',
  `warehouse_quantity` int(0) NULL DEFAULT NULL COMMENT '系统数量',
  `discrepancy_quantity` int(0) NULL DEFAULT NULL COMMENT '差异数量',
  `work_order` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '工单',
  `cdt` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `udt` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '线边库盘点库存详情表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_lsl_check_inventory_detailed_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_lsl_check_inventory_t
-- ----------------------------
DROP TABLE IF EXISTS `c_lsl_check_inventory_t`;
CREATE TABLE `c_lsl_check_inventory_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `cdt` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `udt` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `number` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '盘点单号',
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建者',
  `mender` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '修改者',
  `status` int(0) NULL DEFAULT NULL COMMENT '状态（0新建，1完成）',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '线边库盘点库存表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_lsl_check_inventory_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_lsl_dictionary_t
-- ----------------------------
DROP TABLE IF EXISTS `c_lsl_dictionary_t`;
CREATE TABLE `c_lsl_dictionary_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `key` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '属性名称',
  `value` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '属性值',
  `describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '字段描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '线边库字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_lsl_dictionary_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_lsl_material_inventory_t
-- ----------------------------
DROP TABLE IF EXISTS `c_lsl_material_inventory_t`;
CREATE TABLE `c_lsl_material_inventory_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cdt` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `udt` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `productId` int(0) NULL DEFAULT NULL COMMENT '产品id',
  `lineId` int(0) NULL DEFAULT NULL COMMENT '产线id',
  `stationId` int(0) NULL DEFAULT NULL COMMENT '工位id',
  `batchCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '批次条码',
  `materialCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '物料条码',
  `materialNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '物料编号',
  `materialName` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '物料名称',
  `type` int(0) NULL DEFAULT NULL COMMENT '物料类型 0 混合 1 批次 2 单个',
  `quantity` int(0) NULL DEFAULT NULL COMMENT '精确数量',
  `status` int(0) NULL DEFAULT NULL COMMENT '状态 1 正常, 2 冻结',
  `rockId` int(0) NULL DEFAULT NULL COMMENT '料架id',
  `projectNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '项目号',
  `frozenQuantity` int(0) NULL DEFAULT NULL COMMENT '冻结数量',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 171 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '线边库实时库存表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_lsl_material_inventory_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_lsl_material_request_detail_t
-- ----------------------------
DROP TABLE IF EXISTS `c_lsl_material_request_detail_t`;
CREATE TABLE `c_lsl_material_request_detail_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cdt` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `udt` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `rockId` int(0) NULL DEFAULT NULL COMMENT '料架id',
  `materialNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '物料编码',
  `materialName` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '物料名称',
  `requiredQuantity` int(0) NULL DEFAULT NULL COMMENT '需求数量',
  `billNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '要料请求单据号',
  `tracesType` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '追溯方式(0.混合追溯，1.批次追溯，2.精确追溯)',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 228 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '线边库物料请求记录明细详情表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_lsl_material_request_detail_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_lsl_material_return_detailed_detail_t
-- ----------------------------
DROP TABLE IF EXISTS `c_lsl_material_return_detailed_detail_t`;
CREATE TABLE `c_lsl_material_return_detailed_detail_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `number` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '明细单号',
  `detailed_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '详情单号',
  `cdt` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `udt` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `material_sn` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '物料条码',
  `quantity` int(0) NULL DEFAULT NULL COMMENT '退料数量',
  `stock_quantity` int(0) NULL DEFAULT NULL COMMENT '库存数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '线边库退料详情明细表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_lsl_material_return_detailed_detail_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_lsl_material_return_detailed_t
-- ----------------------------
DROP TABLE IF EXISTS `c_lsl_material_return_detailed_t`;
CREATE TABLE `c_lsl_material_return_detailed_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `number` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '详情单号',
  `return_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '退料单号',
  `cdt` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `udt` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `material_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '物料编号',
  `quantity` int(0) NULL DEFAULT NULL COMMENT '退料数量',
  `stock_quantity` int(0) NULL DEFAULT NULL COMMENT '库存数量',
  `work_order` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '工单',
  `materialId` int(0) NULL DEFAULT NULL COMMENT '物料id',
  `unit` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '单位',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '线边库退料详情表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_lsl_material_return_detailed_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_lsl_material_return_t
-- ----------------------------
DROP TABLE IF EXISTS `c_lsl_material_return_t`;
CREATE TABLE `c_lsl_material_return_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `number` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '退料单号',
  `cdt` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `udt` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `type` int(0) NULL DEFAULT NULL COMMENT '退料类型（0 余料退料，1 不良退料）',
  `status` int(0) NULL DEFAULT NULL COMMENT '0、新建 1、过账 2、拒账',
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建者',
  `mender` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '修改者',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备注',
  `receivingGoodsUserId` int(0) NULL DEFAULT NULL COMMENT '收货人',
  `receivingGoodsDt` datetime(0) NULL DEFAULT NULL COMMENT '收货时间',
  `receivingGoodsFactoryId` int(0) NULL DEFAULT NULL COMMENT '收货工厂',
  `receivingGoodsLocationId` int(0) NULL DEFAULT NULL COMMENT '收货库位id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '线边库退料表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_lsl_material_return_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_lsl_pack_detail_t
-- ----------------------------
DROP TABLE IF EXISTS `c_lsl_pack_detail_t`;
CREATE TABLE `c_lsl_pack_detail_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `containerNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '容器号',
  `lineNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '行号',
  `sn` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '总成',
  `quantity` int(0) NULL DEFAULT NULL COMMENT '数量',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '包装明细表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_lsl_pack_detail_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_lsl_pack_t
-- ----------------------------
DROP TABLE IF EXISTS `c_lsl_pack_t`;
CREATE TABLE `c_lsl_pack_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cdt` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `udt` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `containerId` int(0) NULL DEFAULT NULL COMMENT '容器类型id',
  `containerNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '容器号',
  `packager` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '打包者',
  `lineId` int(0) NULL DEFAULT NULL COMMENT '打包产线',
  `status` int(0) NULL DEFAULT NULL COMMENT '状态 0 新建 1 已完成',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '包装表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_lsl_pack_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_lsl_product_offline_detailed_detail_t
-- ----------------------------
DROP TABLE IF EXISTS `c_lsl_product_offline_detailed_detail_t`;
CREATE TABLE `c_lsl_product_offline_detailed_detail_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `number` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '明细单号',
  `detailed_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '详情编号',
  `quantity` int(0) NULL DEFAULT NULL COMMENT '下线数量',
  `pack_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '包装号',
  `material_sn` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '总成号',
  `cdt` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `udt` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '线边库产品下线详情明细表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_lsl_product_offline_detailed_detail_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_lsl_product_offline_detailed_t
-- ----------------------------
DROP TABLE IF EXISTS `c_lsl_product_offline_detailed_t`;
CREATE TABLE `c_lsl_product_offline_detailed_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `number` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '明细单号',
  `offline_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '下线单号',
  `quantity` int(0) NULL DEFAULT NULL COMMENT '下线数量',
  `material_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '物料编号',
  `cdt` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `udt` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `unit` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '单位',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '线边库产品下线详情表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_lsl_product_offline_detailed_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_lsl_product_offline_t
-- ----------------------------
DROP TABLE IF EXISTS `c_lsl_product_offline_t`;
CREATE TABLE `c_lsl_product_offline_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `number` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '下线单号',
  `type` int(0) NULL DEFAULT NULL COMMENT '下线类型（0 批次，1 单件）',
  `status` int(0) NULL DEFAULT NULL COMMENT '状态 1新建, 2 已提交, 3已过账, 4拒绝',
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建者',
  `mender` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '修改者',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备注',
  `batch` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '批次号',
  `offline_line` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '下线产线',
  `cdt` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `udt` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `creatorUserId` int(0) NULL DEFAULT NULL COMMENT '创建者ID',
  `menderUserId` int(0) NULL DEFAULT NULL COMMENT '修改者ID',
  `receivingGoodsUserId` int(0) NULL DEFAULT NULL COMMENT '收货人',
  `receivingGoodsDt` datetime(0) NULL DEFAULT NULL COMMENT '收货时间',
  `factoryId` int(0) NULL DEFAULT NULL COMMENT '工厂id',
  `locationId` int(0) NULL DEFAULT NULL COMMENT '库位id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '线边库产品下线表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_lsl_product_offline_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_lsl_rock_config_t
-- ----------------------------
DROP TABLE IF EXISTS `c_lsl_rock_config_t`;
CREATE TABLE `c_lsl_rock_config_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cdt` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `udt` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `stationId` int(0) NULL DEFAULT NULL COMMENT '工位id',
  `materialNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '物料编号',
  `materialName` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '物料名称',
  `capacity` int(0) NULL DEFAULT NULL COMMENT '容量',
  `safetyLevel` int(0) NULL DEFAULT NULL COMMENT '安全水位',
  `rockId` int(0) NULL DEFAULT NULL COMMENT '料架id',
  `versionId` int(0) NULL DEFAULT NULL COMMENT '版本id',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '线边库料架配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_lsl_rock_config_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_lsl_rock_t
-- ----------------------------
DROP TABLE IF EXISTS `c_lsl_rock_t`;
CREATE TABLE `c_lsl_rock_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cdt` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `udt` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `rockNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '料格号',
  `describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '料架描述',
  `ptlNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'PTL编号',
  `rackNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '料架号',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '线边库料架表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_lsl_rock_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_lsl_rock_version_t
-- ----------------------------
DROP TABLE IF EXISTS `c_lsl_rock_version_t`;
CREATE TABLE `c_lsl_rock_version_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cdt` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `udt` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `version` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '版本',
  `lineId` int(0) NULL DEFAULT NULL COMMENT '产线id',
  `productId` int(0) NULL DEFAULT NULL COMMENT '产品id',
  `status` int(0) NULL DEFAULT NULL COMMENT '状态 1 已启用 0 未启用',
  `describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '描述',
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建人',
  `reviser` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '线边库料架配置版本表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_lsl_rock_version_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_alarm_code_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_alarm_code_t`;
CREATE TABLE `c_mes_alarm_code_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT,
  `DT` datetime(0) NOT NULL COMMENT '日期',
  `ALARM_CODE` int(0) NOT NULL COMMENT '报警代码',
  `ALARM_TEXT` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '报警中文',
  `ALARM_ENGLISH` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '报警英文',
  `ALARM_GRADE` int(0) NULL DEFAULT NULL COMMENT '0:不严重 1：严重 ',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 52 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '报警代码' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_alarm_code_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_alarm_config_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_alarm_config_t`;
CREATE TABLE `c_mes_alarm_config_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '配置编号',
  `sql` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '执行sql',
  `way` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '告警方式（0 邮箱，1，短信，2 电话）',
  `reception_staff` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '接收人员',
  `data_format` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '数据格式（邮件：表格式/短信：json/电话：json）',
  `cdt` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `udt` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '标题',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '正文',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '告警配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_alarm_config_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_andon_fault_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_andon_fault_t`;
CREATE TABLE `c_mes_andon_fault_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `LINE_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '产线名称',
  `STATION_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '工位名称',
  `ESTABLISH_DT` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `RESPONSE_DT` datetime(0) NULL DEFAULT NULL COMMENT '响应时间',
  `SOLVE_DT` datetime(0) NULL DEFAULT NULL COMMENT '解决时间',
  `LOSS_TYPE` int(0) NULL DEFAULT NULL COMMENT '损失类型（组织损失、技术损失、质量损失、物料损失）',
  `STATUS` int(0) NULL DEFAULT NULL COMMENT '状态（0：已创建，1：已响应，2：已解决）',
  `FAULT_TYPE` int(0) NULL DEFAULT NULL COMMENT '故障类型（设备故障、物料故障）',
  `EMP` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '处理人员',
  `NOTE` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '问题详情',
  `TOOL_ID` int(0) NULL DEFAULT NULL COMMENT '设备id',
  `TOOL_IDS` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '设备id（中间表使用）',
  `DT` datetime(0) NULL DEFAULT NULL,
  `REASON` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `fault_level_id` int(0) NULL DEFAULT 1 COMMENT '故障等级',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 673 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '安灯故障表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_andon_fault_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_andon_info_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_andon_info_t`;
CREATE TABLE `c_mes_andon_info_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `LINE_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '产线名称',
  `STATION_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '工位名称',
  `DT` datetime(0) NULL DEFAULT NULL COMMENT '时间',
  `SN` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'SN',
  `PRODUCT_MARK` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '产品标记',
  `COUNT_TYPE` int(0) NULL DEFAULT NULL COMMENT '计数方式',
  `WORK_ID` int(0) NULL DEFAULT NULL COMMENT '工单id',
  `pattern` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7347 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_andon_info_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_andon_plan_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_andon_plan_t`;
CREATE TABLE `c_mes_andon_plan_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `DT` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '时间',
  `PLAN_START_TIME` datetime(0) NULL DEFAULT NULL COMMENT '计划开始时间',
  `PLAN_END_TIME` datetime(0) NULL DEFAULT NULL COMMENT '计划完工时间',
  `ACTUAL_START_TIME` datetime(0) NULL DEFAULT NULL COMMENT '实际开始时间',
  `ACTUAL_END_TIME` datetime(0) NULL DEFAULT NULL COMMENT '实际完工时间',
  `PLAN_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '计划名称',
  `PLAN_NUMBER` int(0) NULL DEFAULT NULL COMMENT '计划数量',
  `COMPLETE_NUMBER` int(0) NULL DEFAULT NULL COMMENT '完成数量',
  `REMAIND_NUMBER` int(0) NULL DEFAULT NULL COMMENT '剩余数量',
  `OK_NUMBER` int(0) NULL DEFAULT NULL COMMENT '合格数量',
  `NG_NUMBER` int(0) NULL DEFAULT NULL COMMENT '不合格数量',
  `LINE_ID` int(0) NULL DEFAULT NULL COMMENT '产线ID',
  `COMPLETE_FLAG` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '完成标记 0:表示初始化 1：表示开始 2：表示暂停 3：表示强制关闭 4：表示完成',
  `PRODUCTION_ID` int(0) NULL DEFAULT NULL COMMENT '产品id',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_andon_plan_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_api_dictionaries_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_api_dictionaries_t`;
CREATE TABLE `c_mes_api_dictionaries_t`  (
  `actionurl` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '请求路径',
  `commitType` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'http请求类型(GET|POST)',
  `paramsType` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数类型(FORM|JSON)',
  PRIMARY KEY (`actionurl`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'api操作字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_api_dictionaries_t
-- ----------------------------
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/addEmp', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/addEmpType', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/addLabelManager', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/addLossReason', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/addLossType', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/addRecipe', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/addRuleTypeManager', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/addShfitTeam', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/addTeam', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/addtotalRecipe', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/AlarmEmailConfig/addEmailConfig', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/AlarmNnotificationMethod/addNnotificationMethod', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/AlarmNotificationChannels/addNotificationChannels', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/AlarmNotificationChannels/deleteNotificationChannels', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/AlarmNotificationChannels/updateNotificationChannels', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/AlarmProblemUpgrade/addProblemUpgrade', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/AlarmProblemUpgrade/deleteProblemUpgrade', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/AlarmUserToken/updateUserToken', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/badReason/addBadReason', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/badReason/updateBadReason', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/CQhRole/addRole', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/deleteLossType', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/deleteProcessRoute', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/deleteShiftTeam', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/deleteTeam', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/delRecipe', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/delRecipeDetail', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/delTotalRecipe', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/editLabelManager', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/editRuleTypeManager', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/EquipmentInformation/add', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/EquipmentInformation/customColumns/add', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/EquipmentInformation/delete', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/EquipmentInformation/update', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/insertrecipeDetail', 'POST', 'JSON');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/iqc/addIQC', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/iqc/alterFreezeState', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/iqc/alterIQC', 'POST', 'JSON');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/iqc/alterStatus', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/line/addLine', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/line/delLine', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/line/editLine', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/line/toeditstatus', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/makeParameterList/addMakeParameter', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/makeParameterList/addMakeParameterDetail', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/material/addMaterial', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/material/deleteMaterial', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/material/updateMaterial', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/materialInstance/addMaterialInstance', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/materialInstance/freezeInventory', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/materialInstance/updateMaterialInstance', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/materialList/addMaterialDetail', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/materialList/addMeterial', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/materialList/deleteMaterial', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/materialList/delMaterialDetail', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/materialList/editMaterialDetail', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/materialList/updateMeterial', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/oa/ApprovalCirculation/FormTemplateManagement/add', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/oa/ApprovalCirculation/FormTemplateManagement/addTemplateDetailed', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/oa/ApprovalCirculation/FormTemplateManagement/delete', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/oa/ApprovalCirculation/FormTemplateManagement/update', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/oa/file/configurePermissions', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/oa/file/createFolder', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/oa/file/deleteFile', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/oa/file/downloadFile', 'GET', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/oa/file/updateFileName', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/oa/file/uploadFile', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/oa/formTemplateType/add', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/oa/formTemplateType/delete', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/oa/initiateApplication/saveDraft', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/oa/myApplicationInquiry/revoke', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/oa/processDefinition/add', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/oa/processDefinition/delete', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/oa/processDefinition/updateProcess', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/oa/SinglePageFile/downloadAssociatedFiles', 'GET', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/oa/SinglePageFile/downloadFile/2021/06/21/1624237562898IiuaqeSbsu/1.png', 'GET', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/oa/SinglePageFile/downloadFile/2021/07/01/1625125945941YbrakISqKk/1.png', 'GET', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/oa/waitingForMyApproval/Approved', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/patrol/addPatrol', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/patrol/alterPatrol', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/Production/addAll', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/Production/deletePro', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/Production/updateProduct', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/Productions/Information/shutDown', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/regist/addLine', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/relieveData', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/removeLabelManager', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/removeRuleTypeManager', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/role/delRole', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/saveProcessRoute', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/station/addStation', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/station/delStation', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/station/editStation', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/titleName/updateTitleName', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/updataViewBoltData', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/updateLossReason', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/updateLossType', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/updaterecipedetails', 'POST', 'JSON');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/updateShiftTeam', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/updatetotalRecipe', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/user/addUser', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/user/delUser', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/user/resetPwd', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('api/user/updateUser', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('checkInventory/addCheckInventory', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('checkInventory/delCheckInventory', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('checkInventory/findCheckInventoryDetailedDetailList', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('checkInventory/findCheckInventoryDetailedList', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('checkInventory/findCheckInventoryList', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('checkInventory/importExcel', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('checkList/addCheckList', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('checkList/addCheckListDetail', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('checkList/deleteCheckList', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('checkList/deleteCheckListDetail', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('checkList/editCheckList', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('checkList/editCheckListDetail', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('checkList/editCheckListDetailByStart', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('checkList/findCheckList', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('checkList/findCheckListByProduceType', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('checkList/findCheckListDetail', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('checkList/findCheckListDetailAll', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('checkList/getNextCheckListCode', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('checkList/importChecklistExcel', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('checkList/selectCheckListDetailByVersions', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('cod/addCodeRule', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('cod/delCodeRuleListById', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('cod/editCodeRule', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('cod/getNextCode', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('cod/updateLatestCode', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('crm/showBillingInformationList', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('custom/addCustomProperty', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('custom/delCustomPropertyById', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('custom/editCustomProperty', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('dataCollection/group/addGroup', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('dataCollection/group/delGroupByNumber', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('dataCollection/group/editGroup', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('dataCollection/group/findGroupList', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('dataCollection/group/findGroupListAll', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('dataCollection/params/addParams', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('dataCollection/params/delParamsByNumber', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('dataCollection/params/editParams', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('dataCollection/params/findParamsList', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('dataCollection/params/findParamsListAll', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('dataCollection/range/addRange', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('dataCollection/range/delRangeByNumber', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('dataCollection/range/findRangeList', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('dataCollection/range/findRangeListAll', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('dataCollection/record/addRecord', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('dataCollection/record/findList', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('dataCollection/record/findRecordList', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('groupModeling/addArea', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('groupModeling/addCompany', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('groupModeling/addFactory', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('groupModeling/addPlant', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('groupModeling/delAreaByIdAndCode', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('groupModeling/delCompanyByIdAndCode', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('groupModeling/delFactoryByIdAndCode', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('groupModeling/editArea', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('groupModeling/editCompany', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('groupModeling/editFactory', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('groupModeling/editPlant', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('job/addJobBinding', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('job/deleteJobBinding', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('job/editJobBindingStatus', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('jobManagement/jobConfig/delete', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('jobManagement/jobConfig/findList', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('jobManagement/jobConfig/insert', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('jobManagement/jobConfig/update', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('jobManagement/jobRule/delete', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('jobManagement/jobRule/findList', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('jobManagement/jobRule/insert', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('jobManagement/jobRule/update', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('lsl/addRock', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('lsl/addRockConfig', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('lsl/addRockConfigVersion', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('lsl/deleteRock', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('lsl/deleteRockConfig', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('lsl/editRock', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('lsl/editRockConfig', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('lsl/editRockConfigVersion', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('lsl/enableRockConfigVersion', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('materialInventory/findMaterialInventoryBySn', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('materialMapping/importBatchExcel', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('mk/deleteData', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('mk/importData', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('mk/tableCol/deleteCol', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('mk/tableCol/saveCol', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('mk/tableCol/updateCol', 'POST', 'JSON');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('mk/updateMkData', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('mod/addDataConf', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('mod/deleteDataConf', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('mon/api/copyKanban', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('mon/api/removeKanban', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('mon/api/saveKanban', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('mon/api/updateKanban', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('ncCode/addNcCode', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('ncCode/config/addNcCodeConfig', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('ncCode/config/delNcCodeConfigById', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('ncCode/config/editNcCodeConfig', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('ncCode/config/findAllStation', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('ncCode/config/findNcCodeConfigAll', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('ncCode/config/findNcCodeConfigList', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('ncCode/config/verifySn', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('ncCode/delNcCodeById', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('ncCode/editNcCode', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('ncCode/findAllStation', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('ncCode/record/addNcCodeRecord', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('ncCode/record/delNcCodeRecordById', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('ncCode/record/findNcCodeRecordList', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('ncCode/record/updateByStatus', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('ord/addOrder', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('ord/addOrderrecord', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('ord/deleteOrder', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('ord/updateOrder', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('pro/addContainer', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('pro/addPack', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('pro/addPackDetail', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('pro/addPlan', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('pro/deletePack', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('pro/deletePackDetail', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('pro/deletePlan', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('pro/editContainer', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('pro/editPack', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('pro/editPackDetail', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('pro/editPlan', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('pro/splitSN', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('pro/transcodingSN', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('productOffline/addProductOffline', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('productOffline/delProductOffline', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('productOffline/findDetailedDetailList', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('productOffline/findDetailedList', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('productOffline/findList', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('productOffline/ifPack', 'POST', 'JSON');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('pur/addPurchase', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('pur/addPurchaseDetails', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('pur/deletePurchase', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('pur/updatePurchase', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('qhapi/assembleBolt', 'POST', 'JSON');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('qhapi/assembleKeypart', 'POST', 'JSON');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('qhapi/checkMaterial', 'POST', 'JSON');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('qhapi/checkSN', 'POST', 'JSON');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('qhapi/nextBarcode', 'POST', 'JSON');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('qhapi/updateSn', 'POST', 'JSON');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('quality/findCheckListDetailBySN', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('quality/findDisposeQuality', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('quality/findQuality', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('quality/findQualityAll', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('quality/findSNByLineId', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('report/monthOutput/listLine', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('report/monthOutput/monthOutput', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('report/monthOutput/okRate', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('report/monthOutput/yearOutput', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('returnMaterial/addCheckReturnMaterial', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('returnMaterial/addMaterialReturnByList', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('returnMaterial/addReturnMaterial', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('returnMaterial/delReturnMaterial', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('returnMaterial/findReturnMaterialDetailedDetailList', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('returnMaterial/findReturnMaterialDetailedList', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('returnMaterial/findReturnMaterialList', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('returnMaterial/importTxt', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('wms/area/add', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('wms/area/updateAreaViewMode', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('wms/department/add', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('wms/department/delete', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('wms/department/update', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('wms/inTaskqueue/directWarehousing', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('wms/inTaskqueue/updateTray', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('wms/location/add', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('wms/location/update', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('wms/location/viewMode', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('wms/outTaskqueue/MaterialForwardOrBackWard', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('wms/processApproval/add', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('wms/processApproval/delete', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('wms/processApproval/update', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('wms/project/add', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('wms/projectType/add', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('wms/reservoirArea/add', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('wms/reservoirArea/viewMode', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('wms/resevoirArea/add', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('wms/warehouse/add', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('wms/warehouse/viewMode', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('wms/warehousing/add', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('wor/addWorkorder', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('wor/deleteWorkorder', 'POST', 'FORM');
INSERT INTO `c_mes_api_dictionaries_t` VALUES ('wor/updateWorkorder', 'POST', 'FORM');

-- ----------------------------
-- Table structure for c_mes_area_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_area_t`;
CREATE TABLE `c_mes_area_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '区域id',
  `areaCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区域编号',
  `areaName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区域名称',
  `describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区域描述',
  `companyCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属公司',
  `factoryCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属工厂',
  `dateTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `alterDt` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '区域表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_area_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_bad_reason_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_bad_reason_t`;
CREATE TABLE `c_mes_bad_reason_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `bad_reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '不良原因',
  `bad_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '不良代码',
  `bad_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '不良描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_bad_reason_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_board_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_board_t`;
CREATE TABLE `c_mes_board_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `boardName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名字',
  `boardType` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '看板类型',
  `lineName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产线名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_board_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_call_logs
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_call_logs`;
CREATE TABLE `c_mes_call_logs`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `CALL_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接口名称',
  `CALL_TIME` datetime(0) NULL DEFAULT NULL COMMENT '调用时间',
  `CALLER` int(0) NULL DEFAULT NULL COMMENT '调用人id',
  `PARAMETER` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数',
  `RETURN_RESULT` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '返回结果',
  `RETURN_TIME` datetime(0) NULL DEFAULT NULL COMMENT '返回时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '调用接口记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_call_logs
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_check_content
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_check_content`;
CREATE TABLE `c_mes_check_content`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '检查内容ID',
  `STANDARD_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '内容标准名称',
  `STANDARD_VALUE` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '内容标准值',
  `RESULT` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '检查内容结果',
  `CHECK_LIST_CODE` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '质检记录编号',
  `ELIGIBILITY` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '是否合格（OK/NG）',
  `RESULT_LEAD` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '组长检查结果',
  `ELIGIBILITY_LEAD` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '组长检查是否合格（OK/NG)',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 107 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '质检记录检查内容表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_check_content
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_check_data
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_check_data`;
CREATE TABLE `c_mes_check_data`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `dt` datetime(0) NULL DEFAULT NULL,
  `code` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 372 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_check_data
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_client_purview_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_client_purview_t`;
CREATE TABLE `c_mes_client_purview_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `DT` datetime(0) NULL DEFAULT NULL COMMENT '日期',
  `FUN_NO` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模块编号',
  `OWN_PASSWORD` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模块开启密码',
  `FUN_NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模块名称',
  `DIS` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '客户端功能模块密码表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_client_purview_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_code_rule_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_code_rule_t`;
CREATE TABLE `c_mes_code_rule_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `DT` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `ALTER_DT` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `CODE_RULE_PREFIX` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编号规则前缀',
  `CODE_RULE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编号规则',
  `CODE_RULE_SUFFIX` int(0) NULL DEFAULT NULL COMMENT '编号规则后缀(几位流水号)',
  `CODE_TYPE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型编码',
  `CODE_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编号名称',
  `LAST_CODE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '当前最后编号',
  `CODE_RULE_VALUE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最新编号规则value',
  `CODE_RULE_SUFFIX_VALUE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最新编号规则后缀value',
  `EXPLAIN` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '规则说明',
  `ENABLE_RESET` int(0) NULL DEFAULT NULL COMMENT '是否重置(0 关闭, 1 开启)',
  `RESET_CYCLE` int(0) NULL DEFAULT NULL COMMENT '重置周期(0 永不，1 日，2 周，3 月，4 年)',
  `CREATE_CODE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '编号周期开始时间',
  `DELETE` int(0) NULL DEFAULT 0 COMMENT '逻辑删除（0未删除，1已删除)',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '编号规则表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_code_rule_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_company_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_company_t`;
CREATE TABLE `c_mes_company_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '公司id',
  `companyCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司编号',
  `companyName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司名称',
  `describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司描述',
  `companyAddress` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司地址',
  `dateTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `alterDt` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '公司表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_company_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_container_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_container_t`;
CREATE TABLE `c_mes_container_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `containerName` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '容器名称',
  `capacity` int(0) NULL DEFAULT NULL COMMENT '容量大小',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '容器定义表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_container_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_custom_property
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_custom_property`;
CREATE TABLE `c_mes_custom_property`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '属性id',
  `PROPERTY_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '属性名称',
  `PROPERTY_ENGLISH_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '属性英文名称',
  `PROPERTY_TYPE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '属性类型',
  `OBJECT_TYPE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '对象类型（物料，产品，订单，工单等）',
  `BIND_SCOPE_KEY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '属性范围 （全部，某个字段）',
  `BIND_CONDITION` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '属性范围判断条件',
  `BIND_SCOPE_VALUE` varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '属性范围值',
  `PROPERTY_GROUP` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '属性分组',
  `PROPERTY_EXPLAIN` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '属性说明',
  `DEFAULT` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'default value',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 160 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '自定义属性表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_custom_property
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_custom_property_value
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_custom_property_value`;
CREATE TABLE `c_mes_custom_property_value`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '自定义属性内容ID',
  `VALUE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内容',
  `PROPERTY_ID` int(0) NULL DEFAULT NULL COMMENT '自定义属性外键',
  `OBJECT_TYPE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属对象（物料，订单）',
  `OBJECT_CODE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属对象明细（某个物料）对象编号',
  `BIND_SCOPE_KEY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属范围',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2454 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '自定义属性内容表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_custom_property_value
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_customer_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_customer_t`;
CREATE TABLE `c_mes_customer_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `DT` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `COMPANY_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司名称',
  `CUSTOMER_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人',
  `PHONE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '客户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_customer_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_data_collection_group_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_data_collection_group_t`;
CREATE TABLE `c_mes_data_collection_group_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `number` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '组号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '组名称',
  `range` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '适用范围编号',
  `cdt` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `udt` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '数据收集组定义表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_data_collection_group_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_data_collection_params_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_data_collection_params_t`;
CREATE TABLE `c_mes_data_collection_params_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `number` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '编号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '名称',
  `group_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '所属组号',
  `upper_limit` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '上限',
  `lower_limit` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '下限',
  `overrun` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '超限',
  `necessary` int(0) NULL DEFAULT NULL COMMENT '必收标记（0 true, 1 false）',
  `cdt` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `udt` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '数据收集组参数清单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_data_collection_params_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_data_collection_range_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_data_collection_range_t`;
CREATE TABLE `c_mes_data_collection_range_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '范围名称',
  `number` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '编号',
  `process` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '工序（* 所有）',
  `equipment` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '设备（* 所有）',
  `material` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '物料（* 所有）',
  `cdt` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `udt` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '数据收集组适用范围表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_data_collection_range_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_data_collection_record_detail_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_data_collection_record_detail_t`;
CREATE TABLE `c_mes_data_collection_record_detail_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `record_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '数据收集记录编号',
  `params_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '参数编号',
  `params_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '参数名称',
  `value` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '实际值',
  `cdt` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `udt` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `upper_limit` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '上限',
  `lower_limit` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '下限',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '数据收集记录明细表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_data_collection_record_detail_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_data_collection_record_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_data_collection_record_t`;
CREATE TABLE `c_mes_data_collection_record_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `number` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '编号',
  `grou_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '所属组号',
  `staff` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '记录人员',
  `process` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '工序',
  `equipment` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '设备',
  `sn` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '总成号',
  `cdt` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `udt` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '数据收集记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_data_collection_record_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_day
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_day`;
CREATE TABLE `c_mes_day`  (
  `day_num` int(0) NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_day
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_defect_entry_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_defect_entry_t`;
CREATE TABLE `c_mes_defect_entry_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT,
  `DT` datetime(0) NULL DEFAULT NULL COMMENT '录入时间',
  `SN` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'pack',
  `PRODUCTION_ID` int(0) NULL DEFAULT NULL COMMENT '产品id',
  `REASON` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '原因',
  `EMP` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '员工',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '缺陷录入表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_defect_entry_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_device_repair
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_device_repair`;
CREATE TABLE `c_mes_device_repair`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `DT` datetime(0) NULL DEFAULT NULL COMMENT '维修时间',
  `DEVICE_NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备id',
  `REPAIR_PERSON` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '维修人',
  `EMP` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '负责员工',
  `REASON` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '维修原因',
  `NOTE` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `LINE_ID` int(0) NULL DEFAULT NULL COMMENT '所属产线',
  `parentId` int(0) NULL DEFAULT NULL COMMENT '设备id',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '设备维修表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_device_repair
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_device_spot_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_device_spot_t`;
CREATE TABLE `c_mes_device_spot_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT,
  `DT` datetime(0) NULL DEFAULT NULL COMMENT '时间',
  `DEVICE_NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备name',
  `LINE_ID` int(0) NULL DEFAULT NULL COMMENT '产线id',
  `STATUS` int(0) NULL DEFAULT NULL COMMENT '点检状态，1：成功，2：失败',
  `EMP` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '负责员工',
  `parentId` int(0) NULL DEFAULT NULL COMMENT '设备id',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '设备点检表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_device_spot_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_device_upkeep
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_device_upkeep`;
CREATE TABLE `c_mes_device_upkeep`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT,
  `DT` datetime(0) NULL DEFAULT NULL COMMENT '上次保养时间',
  `DEVICE_NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备name',
  `LINE_ID` int(0) NULL DEFAULT NULL COMMENT '产线ID',
  `EMP` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '负责员工',
  `UPKEEP_PERSON` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '保养人',
  `NOTE` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `ENDTIME` date NULL DEFAULT NULL COMMENT '下次保养时间',
  `ESTIMATE_LIFE` int(0) NULL DEFAULT NULL COMMENT '预计寿命',
  `USE_LIFE` int(0) NULL DEFAULT NULL COMMENT '已用寿命',
  `SURPLUS_LIFE` int(0) NULL DEFAULT NULL COMMENT '剩余寿命',
  `MAINTAIN_CYCLE` int(0) NULL DEFAULT NULL COMMENT '维护周期',
  `SURPLUS_MAINTAIN` int(0) NULL DEFAULT NULL COMMENT '剩余维护天数',
  `EVERYLIMES` int(0) NULL DEFAULT NULL COMMENT '每次使用消耗寿命',
  `parentId` int(0) NULL DEFAULT NULL COMMENT '设备id',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '设备保养表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_device_upkeep
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_duty_type_manager_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_duty_type_manager_t`;
CREATE TABLE `c_mes_duty_type_manager_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `DT` datetime(0) NOT NULL COMMENT '修改时间',
  `DUTY_TYPE_ID` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '责任类型编号',
  `DUTY_TYPE_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '责任类型名称',
  `DUTY_TYPE_DIS` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '责任类型描述',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '责任类型管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_duty_type_manager_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_emp_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_emp_t`;
CREATE TABLE `c_mes_emp_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `DT` datetime(0) NULL DEFAULT NULL COMMENT '日期',
  `EMP_NO` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '员工编号',
  `EMP_NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '员工名称',
  `EMP_SEX` int(0) NULL DEFAULT NULL COMMENT '员工性别 0：男  1：女',
  `EMP_TYPE` int(0) NULL DEFAULT NULL COMMENT '员工类别  0：一般员工 1：班组长  2：主管  3：资深主管 4：工程师  5：资深工程师 6：经理',
  `EMP_TP` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '员工电话',
  `EMP_DEPARTMENT` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '员工部门',
  `EMP_TEAM_ID` int(0) NULL DEFAULT NULL COMMENT '员工所属班组',
  `LINE_ID` int(0) NULL DEFAULT NULL COMMENT '所属产线',
  `EMP_VR` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '员工编号校验规则',
  `EMP_MAIL` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '员工邮箱',
  `STATION_ID` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '所属工位',
  `ROLE_ID` int(0) NULL DEFAULT NULL COMMENT '关联角色ID',
  `CODE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '员工编码',
  `IS_WHOLE` int(0) NULL DEFAULT NULL COMMENT '是否全班',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 123 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '产线员工表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_emp_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_emp_team_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_emp_team_t`;
CREATE TABLE `c_mes_emp_team_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `DIS` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `LINE_ID` int(0) NULL DEFAULT NULL COMMENT '产线ID',
  `EMPS` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '包含员工',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '员工班组表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_emp_team_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_emp_type_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_emp_type_t`;
CREATE TABLE `c_mes_emp_type_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT,
  `EMP_TYPE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '员工类型',
  `DIS` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注释',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_emp_type_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_error_log_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_error_log_t`;
CREATE TABLE `c_mes_error_log_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dt` datetime(0) NULL DEFAULT NULL COMMENT '报错时间',
  `errorMsg` varchar(1000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '报错信息',
  `username` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '操作用户',
  `ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'IP地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4329 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '错误日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_error_log_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_event_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_event_t`;
CREATE TABLE `c_mes_event_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT,
  `DT` datetime(0) NULL DEFAULT NULL COMMENT '事件时间',
  `OBJECT_TYPE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '对象类型',
  `OBJECT_ID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '对象id',
  `EVENT` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '事件类型',
  `PARAMETER1` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数1',
  `PARAMETER2` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数2',
  `PARAMETER3` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数3',
  `OPERATOR` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人',
  `EVENT_DIS` varchar(3635) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '事件详情',
  `OBJECT_TYPE2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '对象类型2',
  `PARAMETER4` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数4',
  `PARAMETER5` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数5',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8580 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '事件表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_event_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_factory_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_factory_t`;
CREATE TABLE `c_mes_factory_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '工厂id',
  `factoryCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工厂编号',
  `factoryName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工厂名称',
  `describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工厂描述',
  `dateTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `alterDt` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `companyCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '工厂表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_factory_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_fault_type_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_fault_type_t`;
CREATE TABLE `c_mes_fault_type_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `TYPE_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '故障名称',
  `DT` datetime(0) NULL DEFAULT NULL COMMENT '故障类型',
  `NOTE` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_fault_type_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_hour
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_hour`;
CREATE TABLE `c_mes_hour`  (
  `hour_number` int(0) NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_hour
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_incoming_stock_h
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_incoming_stock_h`;
CREATE TABLE `c_mes_incoming_stock_h`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cdt` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `udt` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `factoryId` int(0) NULL DEFAULT NULL COMMENT '收货工厂',
  `billNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '单据号',
  `purchaseOrderNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '采购订单号',
  `supplierNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '供应商编号',
  `receivingAddress` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '收货地点',
  `status` int(0) NULL DEFAULT NULL COMMENT '状态',
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建人',
  `consignee` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '收货人',
  `receivingTime` datetime(0) NULL DEFAULT NULL COMMENT '收货时间',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '来料入库单头表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_incoming_stock_h
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_incoming_stock_r
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_incoming_stock_r`;
CREATE TABLE `c_mes_incoming_stock_r`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cdt` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `udt` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `billNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '单据号',
  `projectNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '项目号',
  `materialNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '物料编码',
  `planNum` int(0) NULL DEFAULT NULL COMMENT '计划数量',
  `actualNum` int(0) NULL DEFAULT NULL COMMENT '实收数量',
  `unit` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '单位',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '来料入库单行表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_incoming_stock_r
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_inspection_checklist_detail_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_inspection_checklist_detail_t`;
CREATE TABLE `c_mes_inspection_checklist_detail_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '质检清单版本迭代ID',
  `DT` datetime(0) NULL DEFAULT NULL COMMENT '发布时间',
  `VERSIONS` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '版本',
  `START` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '当前状态（关闭，启用  0，1）',
  `CONTENT` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '检查内容',
  `CHECK_LIST_CODE` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '质检清单编号',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 107 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '质检清单版本迭代表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_inspection_checklist_detail_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_inspection_checklist_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_inspection_checklist_t`;
CREATE TABLE `c_mes_inspection_checklist_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '质检清单ID',
  `NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '清单名称',
  `CODE` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '清单编号',
  `PRODUCE_TYPE` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '产品型号',
  `DT` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `TYPE` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '清单类型（自检，首检，巡检，尾检）1，2，3，4',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 92 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '质检清单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_inspection_checklist_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_iqc_check_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_iqc_check_t`;
CREATE TABLE `c_mes_iqc_check_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT,
  `FACTORY_NO` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工厂编号',
  `MATERIAL_VOUCHER` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料凭证号',
  `CHECK_BATCH` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '校验批次',
  `OTIGIN` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '校验批来源',
  `MATERIAL_NO` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料编号',
  `MATERIAL_DESCRIBE` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '物料描述',
  `CHECK_NUM` int(0) NULL DEFAULT NULL COMMENT '校验批数量',
  `NG_NUM` int(0) NULL DEFAULT NULL COMMENT '不合格数量',
  `SEQ_NG_NUM` int(0) NULL DEFAULT NULL COMMENT 'SQE不合格数量',
  `CALCULATE_UNIT` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '计量单位',
  `SUPPLIER_NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '供应商名称',
  `EMP` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `DT` datetime(0) NULL DEFAULT NULL COMMENT '送检时间',
  `CHECK_PERSON` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '检验人',
  `PRODUCTION_HANDIE` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品处置 1:接受，2：退货,3：入库',
  `STATUS` int(0) NULL DEFAULT NULL COMMENT '状态，1：未处理，2：已处理，3：已复核',
  `CHECK_DT` datetime(0) NULL DEFAULT NULL COMMENT '复核时间',
  `MATERIAL_NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料名称',
  `FREEZE` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '冻结状态',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 44 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'IQC校验' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_iqc_check_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_jl_material_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_jl_material_t`;
CREATE TABLE `c_mes_jl_material_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `BOM_ID` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料编码\n',
  `MATERIAL_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料名称\n',
  `DESCRIPTION` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述\n',
  `SPECIFICATIONS` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '规格\n',
  `MATERIAL_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料组\r\n',
  `MATERIAL_TYPE` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料类型(BOM,物料)',
  `STOCK_UNIT` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '库存单位\n',
  `INVENTORY_MODEL_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '库存模型组\r\n',
  `INVENTORY_DIMENSION_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '库存维组\n',
  `RELEASE` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发放（y:自动发送，n:工单发送）',
  `INSPECTION` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '检验等级\r\n',
  `FICTITIOUS` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '虚拟\n',
  `SALES_UNIT` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '销售单位\r\n',
  `SECRECY` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '保密否\r\n',
  `PURCHASING_UNIT` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '采购单位\n',
  `PRODUCTION_TEAM` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生产组\r\n',
  `MININUMBER_OF_PACKAGES` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最小包装数量\r\n',
  `TERM_OF_VALIDITY` datetime(0) NULL DEFAULT NULL COMMENT '有效期\r\n',
  `TYPENUM` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '型号\n',
  `VOLTAGE` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电压容量\n',
  `PART_COUNTS` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '子件数\r\n',
  `CELL_CAPACITY` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电芯容量\n',
  `SCAN` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否扫描(Y:是,N:否)',
  `CELL_SPECIFICATION` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电芯规格\n',
  `VIEW_MODE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '逻辑删除',
  `TRACES_TYPE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '追溯方式(0.混合追溯，1.批次追溯，2.精确追溯)',
  `DELIVERY_STRATEGY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '出库策略 1 空间利用率（默认）',
  `PURCHASING_STRATEGY` int(0) NULL DEFAULT 1 COMMENT '采购策略 1 标准 2 追溯项目号',
  `PROCUREMENT_CYCLE` int(0) NULL DEFAULT NULL COMMENT '采购周期（天数）',
  `CUSTOMER_PART_CODE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户零件编码(SD新增字段)',
  `wQIS` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '入库质检策略（先入后检、先检后入）',
  `InspectionMethod` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '检验方式',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '金龙物料表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_jl_material_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_job_binding_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_job_binding_t`;
CREATE TABLE `c_mes_job_binding_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `stationId` int(0) NULL DEFAULT NULL COMMENT '工位id',
  `jobId` int(0) NULL DEFAULT NULL COMMENT '作业Id',
  `inOut` int(0) NULL DEFAULT NULL COMMENT '进出站 1 进站 2 出站',
  `status` int(0) NULL DEFAULT NULL COMMENT '状态 0 禁用 1 启用',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '工位作业绑定表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_job_binding_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_job_configuration_rule_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_job_configuration_rule_t`;
CREATE TABLE `c_mes_job_configuration_rule_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '规则主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '名称',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '编码',
  `value` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '值',
  `job_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '作业配置编码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '作业配置规则表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_job_configuration_rule_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_job_configuration_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_job_configuration_t`;
CREATE TABLE `c_mes_job_configuration_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '作业主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '名称',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '编码',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '程序url',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '作业配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_job_configuration_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_jounral_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_jounral_t`;
CREATE TABLE `c_mes_jounral_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `dt` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '时间',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '操作人',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '操作内容',
  `str` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '预留字段',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_jounral_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_label_manager_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_label_manager_t`;
CREATE TABLE `c_mes_label_manager_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT,
  `LABEL_NUMBER` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签编号',
  `LABEL_NAME` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签名称',
  `LABEL_RULES` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签规则',
  `LINE_ID` int(0) NULL DEFAULT NULL COMMENT '所属产线',
  `DIS` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签描述',
  `LABEL_DATE` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `LABEL_TYPE_ID` int(0) NULL DEFAULT NULL COMMENT '标签类型ID',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '打印标签管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_label_manager_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_label_type
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_label_type`;
CREATE TABLE `c_mes_label_type`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `DT` datetime(0) NULL DEFAULT NULL COMMENT '日期',
  `NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `LABEL_VR` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型规则',
  `LABEL_DIS` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型描述',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '标签类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_label_type
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_line_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_line_t`;
CREATE TABLE `c_mes_line_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `DT` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `NAME` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `DSC` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '描述',
  `CODE_TYPE` int(0) NULL DEFAULT NULL COMMENT '产线条码生成方式 0：同一日期相同产品计划条码连续 1：同一日期不同产品条码连续 2：不同日期相同产品计划条码连续 3：不同日期不同产品计划条码连续 4：其他',
  `WS_ID` int(0) NULL DEFAULT NULL COMMENT '车间ID',
  `CODE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产线编码',
  `STATUS` int(0) NULL DEFAULT NULL COMMENT '状态 1 生产中 0 非生产',
  `YIELD_NUMBER` int(0) NULL DEFAULT NULL COMMENT '产量',
  `REGION` int(0) NULL DEFAULT NULL COMMENT '区域id',
  `PRODUCT_MARK` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品标记',
  `COUNT_TYPE` int(0) NULL DEFAULT NULL COMMENT '计数方式（1：计件，2：计件带类型条码，3：计件带产品条码）',
  `PAIBAN_STATUS` int(0) NULL DEFAULT NULL,
  `ANDENG_STATUS` int(0) NULL DEFAULT NULL,
  `companyCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属公司',
  `factoryCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属工厂',
  `areaCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属区域',
  `plantCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属车间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 201 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '产线表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_line_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_loading_material_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_loading_material_t`;
CREATE TABLE `c_mes_loading_material_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `lineId` int(0) NULL DEFAULT NULL COMMENT '产线id',
  `stationId` int(0) NULL DEFAULT NULL COMMENT '工位id',
  `workorderId` int(0) NULL DEFAULT NULL COMMENT '工单id',
  `materialNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '物料编码',
  `batchCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '批次号',
  `quantity` int(0) NULL DEFAULT NULL COMMENT '数量',
  `remainQuantity` int(0) NULL DEFAULT NULL COMMENT '剩余数量',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '产线上料批次缓存表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_loading_material_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_loss_reason_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_loss_reason_t`;
CREATE TABLE `c_mes_loss_reason_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `LOSS_ID` int(0) NULL DEFAULT NULL,
  `NOTE` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `REASON_NO` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_loss_reason_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_loss_type_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_loss_type_t`;
CREATE TABLE `c_mes_loss_type_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT,
  `LOSS_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '损失名称',
  `DT` datetime(0) NULL DEFAULT NULL COMMENT '时间',
  `NOTE` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_loss_type_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_manufacture_parameters_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_manufacture_parameters_t`;
CREATE TABLE `c_mes_manufacture_parameters_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `DT` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `LIST_NO` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '制造参数清单编号',
  `LIST_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '制造参数清单名称',
  `EFFECTIVE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '生效时间',
  `INVALID_TIME` datetime(0) NULL DEFAULT NULL COMMENT '失效时间',
  `LIST_VERSION` int(0) NULL DEFAULT NULL COMMENT '版本',
  `LINE_ID` int(0) NULL DEFAULT NULL COMMENT '所属产线',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '制造参数清单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_manufacture_parameters_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_material_batch_mapping
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_material_batch_mapping`;
CREATE TABLE `c_mes_material_batch_mapping`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `supplier_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '供应商名称',
  `supplier_material_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '供应商物料编码',
  `material_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料编码',
  `supplier_batch` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '供应商批次号',
  `batch` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '批次号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 583 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '物料批次映射表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_material_batch_mapping
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_material_code_mapping_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_material_code_mapping_t`;
CREATE TABLE `c_mes_material_code_mapping_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `supplier_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '供应商名称',
  `supplier_material_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '供应商物料编码',
  `material_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料编码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '物料编号映射表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_material_code_mapping_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_material_event_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_material_event_t`;
CREATE TABLE `c_mes_material_event_t`  (
  `ID` int(0) NOT NULL COMMENT '事件id',
  `MATERIAL_CODE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料编码',
  `MATERIAL_BATCH` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料批次',
  `MATERIAL_SN` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料SN',
  `NUMBER` int(0) NOT NULL COMMENT '物料数量',
  `EVENT_TYPE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '事件类型（入库、质检、出库、上线、下线\n等）',
  `EVENT_INSTRUCTIONS` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '事件说明',
  `DT` datetime(0) NOT NULL COMMENT '创建时间',
  `ALTER_DT` datetime(0) NOT NULL COMMENT '修改时间',
  `PARAMETER` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数（出入库来源或去处）',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '物料事件表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_material_event_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_material_instance_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_material_instance_t`;
CREATE TABLE `c_mes_material_instance_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '实例id',
  `MATERIAL_CODE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料编码',
  `MATERIAL_BATCH` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料批次',
  `MATERIAL_SN` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料SN',
  `MATERIAL_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '实例名称',
  `MATERIAL_TYPE` int(0) NULL DEFAULT NULL COMMENT '物料类型',
  `INSTANCE_VALIDITY` datetime(0) NULL DEFAULT NULL COMMENT '物料有效期',
  `INSTANCE_DESCRIPTION` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '实例描述',
  `EVENT_ID` int(0) NULL DEFAULT NULL COMMENT '事件id',
  `WEAR_STATE` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '消耗状态（0未消耗，1已消耗）',
  `MATERIAL_NUMBER` int(0) NULL DEFAULT NULL COMMENT '批次数量',
  `DT` datetime(0) NOT NULL COMMENT '创建时间',
  `ALTER_DT` datetime(0) NOT NULL COMMENT '修改时间',
  `DELETED` int(0) NULL DEFAULT 0 COMMENT '逻辑删除 0 正常 1 删除',
  `WORKORDER_ID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工单编号',
  `NUMBER_REMAINING` int(0) NULL DEFAULT NULL COMMENT '剩余数量',
  `PICKING_LOCK_NUMBER` int(0) NULL DEFAULT NULL COMMENT '拣货锁定数',
  `FROZEN_NUMBER` int(0) NULL DEFAULT NULL COMMENT '质量冻结数',
  `LOCATION_ID` int(0) NULL DEFAULT NULL COMMENT '库位id',
  `PROJECT_NO` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目号',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 430 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '物料实例表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_material_instance_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_material_instance_t_copy1
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_material_instance_t_copy1`;
CREATE TABLE `c_mes_material_instance_t_copy1`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '实例id',
  `MATERIAL_CODE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料编码',
  `MATERIAL_BATCH` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料批次',
  `MATERIAL_SN` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料SN',
  `MATERIAL_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '实例名称',
  `MATERIAL_TYPE` int(0) NULL DEFAULT NULL COMMENT '物料类型',
  `INSTANCE_VALIDITY` datetime(0) NULL DEFAULT NULL COMMENT '物料有效期',
  `INSTANCE_DESCRIPTION` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '实例描述',
  `EVENT_ID` int(0) NULL DEFAULT NULL COMMENT '事件id',
  `WEAR_STATE` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '消耗状态（0未消耗，1已消耗）',
  `MATERIAL_NUMBER` int(0) NULL DEFAULT NULL COMMENT '批次数量',
  `DT` datetime(0) NOT NULL COMMENT '创建时间',
  `ALTER_DT` datetime(0) NOT NULL COMMENT '修改时间',
  `DELETED` int(0) NULL DEFAULT 0 COMMENT '逻辑删除 0 正常 1 删除',
  `WORKORDER_ID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工单编号',
  `NUMBER_REMAINING` int(0) NULL DEFAULT NULL COMMENT '剩余数量',
  `PICKING_LOCK_NUMBER` int(0) NULL DEFAULT NULL COMMENT '拣货锁定数',
  `FROZEN_NUMBER` int(0) NULL DEFAULT NULL COMMENT '质量冻结数',
  `LOCATION_ID` int(0) NULL DEFAULT NULL COMMENT '库位id',
  `PROJECT_NO` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目号',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 430 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '物料实例表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_material_instance_t_copy1
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_material_list_detail_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_material_list_detail_t`;
CREATE TABLE `c_mes_material_list_detail_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `DT` datetime(0) NULL DEFAULT NULL COMMENT '日期',
  `FIGURE_NO` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图号',
  `MATERIAL_NO` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料编号',
  `MATERIAL_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料名称',
  `MATERIAL_SHEFT` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料槽号',
  `MATERIAL_REPLACE` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料替代组',
  `MATERIAL_TRACE` int(0) NULL DEFAULT NULL COMMENT '追溯方式 0：批次追溯 1：精确追溯 2：不追溯',
  `MATERIAL_NUMBER` int(0) NULL DEFAULT NULL COMMENT '物料数量',
  `MATERIAL_IMP_FLAG` int(0) NULL DEFAULT NULL COMMENT '主关键物料标记 0：否 1：是',
  `MATERIAL_CHECK` int(0) NULL DEFAULT NULL COMMENT '物料完整性检查标记  0：不检查  1：检查',
  `MATERIAL_GET_NUMBER` int(0) NULL DEFAULT NULL COMMENT '取料数量',
  `MATERIAL_STORE` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料存储仓库位置',
  `MATERIAL_GET_CHECK_FLAG` int(0) NULL DEFAULT NULL COMMENT '取料检查标记 0：不检查 1：检查',
  `STATION_ID` int(0) NULL DEFAULT NULL COMMENT '工位ID',
  `MATERIAL_PULL_FALG` int(0) NULL DEFAULT NULL COMMENT '物料拉动标记',
  `MATERILA_LIST_ID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'BOM编号',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 45 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '料单明细表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_material_list_detail_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_material_list_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_material_list_t`;
CREATE TABLE `c_mes_material_list_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `DT` datetime(0) NULL DEFAULT NULL COMMENT '修改日期',
  `LIST_NO` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'BOM料单编号',
  `LIST_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '料单名称',
  `EFFECTIVE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '生效时间',
  `INVALID_TIME` datetime(0) NULL DEFAULT NULL COMMENT '失效时间',
  `LIST_VERSION` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '版本',
  `LINE_ID` int(0) NULL DEFAULT NULL COMMENT '所属产线',
  `PRODUCT_TYPE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品型号',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '物料料单管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_material_list_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_material_repair_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_material_repair_t`;
CREATE TABLE `c_mes_material_repair_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT,
  `MATERIAL_ID` int(0) NULL DEFAULT NULL COMMENT '物料id',
  `STATION_ID` int(0) NULL DEFAULT NULL COMMENT '工位id',
  `DT` datetime(0) NULL DEFAULT NULL COMMENT '发起检修时间',
  `EMP` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发起人',
  `NOTE` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `ENDDT` datetime(0) NULL DEFAULT NULL COMMENT '维修完成时间',
  `STATUS` int(0) NULL DEFAULT NULL COMMENT '1:未完成，2：维修完成',
  `REPAIR_EMP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '维修人',
  `REASON` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '原因',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '物料维修表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_material_repair_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_material_type_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_material_type_t`;
CREATE TABLE `c_mes_material_type_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT,
  `MATERIAL_TYPE` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `DIS` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `DT` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_material_type_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_mf_parameters_detail_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_mf_parameters_detail_t`;
CREATE TABLE `c_mes_mf_parameters_detail_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `DT` datetime(0) NULL DEFAULT NULL COMMENT '日期',
  `PARAMETER_NO` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '制造参数编号',
  `PARAMETER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '制造参数名称',
  `PARAMETER_MAIN_FLAG` int(0) NULL DEFAULT NULL COMMENT '制造参数主参数标记 0：非主参数 1：主参数',
  `PARAMETER_CHECK` int(0) NULL DEFAULT NULL COMMENT '制造参数完整性检查标记 0：不检查 1：检查',
  `PARAMETER_REPLACE` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '制造参数可代替组',
  `SCREW_NUMBER` int(0) NULL DEFAULT NULL COMMENT '拧紧数量',
  `NORMAL_T` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '拧紧标准扭矩值',
  `T_UPPER_LIMIT` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '拧紧扭矩上限',
  `T_LOWER_LIMIT` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '拧紧扭矩下限',
  `NORMAL_A` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '拧紧标准角度值',
  `A_UPPER_LIMIT` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '拧紧角度上限',
  `A_LOWER_LIMIT` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '拧紧角度下限',
  `OTHER_MORMAL_T` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '其他种类参数标准值',
  `OTHER_UPPER_LIMIT` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '其他种类参数上限值',
  `OTHER_LOWER_LIMIT` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '其他种类参数下限值',
  `MF_PARAMETERS_ID` int(0) NULL DEFAULT NULL COMMENT '制造参数清单ID',
  `STATION_ID` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '制造参数明细清单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_mf_parameters_detail_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_month
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_month`;
CREATE TABLE `c_mes_month`  (
  `month_num` int(0) NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_month
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_nc_code_config_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_nc_code_config_t`;
CREATE TABLE `c_mes_nc_code_config_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '不合格编码',
  `describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '编码描述',
  `category` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '不合格编码类别',
  `process` int(0) NULL DEFAULT NULL COMMENT '适用工序',
  `special_control` int(0) NULL DEFAULT NULL COMMENT '特殊管控(是否拦截 0 否，1 是)',
  `cdt` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `udt` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '不合格编码配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_nc_code_config_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_nc_code_record_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_nc_code_record_t`;
CREATE TABLE `c_mes_nc_code_record_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `cdt` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `udt` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `sn` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '总成号',
  `nc_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '不合格编码',
  `staff` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '记录人员',
  `status` int(0) NULL DEFAULT NULL COMMENT '状态（0 开启，1关闭）',
  `number` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 75 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'SN不合格记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_nc_code_record_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_operation_log_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_operation_log_t`;
CREATE TABLE `c_mes_operation_log_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '操作日志id',
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人',
  `module` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '一级模块',
  `module2` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '二级模块',
  `methods` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '执行方法',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '操作内容',
  `actionurl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'IP地址',
  `dt` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  `commite` tinyint(0) NULL DEFAULT NULL COMMENT '执行描述（1:执行成功、2:执行失败）',
  `params` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '请求参数',
  `apiAnnotation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'API注释',
  `is_delete` bit(1) NULL DEFAULT b'0' COMMENT '逻辑删除(0:未删除、1:已删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20290 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '操作日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_operation_log_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_orderrecord_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_orderrecord_t`;
CREATE TABLE `c_mes_orderrecord_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `DT` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `ALTER_DT` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `ORDER_ID` int(0) NULL DEFAULT NULL COMMENT '订单id',
  `PRODUCT_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品名称',
  `DEMAND_NUM` int(0) NULL DEFAULT NULL COMMENT '需求数量',
  `MFG_PARAMS` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '制造参数',
  `ROUTING_ID` int(0) NULL DEFAULT NULL COMMENT '工艺id',
  `TOTAL_RECIPE_ID` int(0) NULL DEFAULT NULL COMMENT '配方id',
  `PRODUCT_MODEL` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品型号',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_orderrecord_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_patrol_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_patrol_t`;
CREATE TABLE `c_mes_patrol_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT,
  `PRODUCTION_ID` int(0) NULL DEFAULT NULL,
  `SN` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `STATION_ID` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `EMP` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `REASON` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `DT` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100029 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '巡检记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_patrol_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_plan_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_plan_t`;
CREATE TABLE `c_mes_plan_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cdt` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `udt` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `planNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '计划号',
  `orderNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '订单号',
  `projectNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '项目号',
  `projectName` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '项目名称',
  `productId` int(0) NULL DEFAULT NULL COMMENT '产品id',
  `materialNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '物料编码',
  `lineId` int(0) NULL DEFAULT NULL COMMENT '产线id',
  `quantity` int(0) NULL DEFAULT NULL COMMENT '数量',
  `customer` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '客户',
  `planStartDate` datetime(0) NULL DEFAULT NULL COMMENT '计划开始时间',
  `planEndDate` datetime(0) NULL DEFAULT NULL COMMENT '计划结束时间',
  `status` int(0) NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '计划表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_plan_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_plan_type_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_plan_type_t`;
CREATE TABLE `c_mes_plan_type_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `typeName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '计划类型名称',
  `dis` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_plan_type_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_plant_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_plant_t`;
CREATE TABLE `c_mes_plant_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '车间id',
  `plantCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车间编号',
  `plantName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车间名称',
  `describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车间描述',
  `companyCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属公司',
  `factoryCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属工厂',
  `areaCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属区域',
  `dateTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `alterDt` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '车间表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_plant_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_process_production_way_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_process_production_way_t`;
CREATE TABLE `c_mes_process_production_way_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `DT` datetime(0) NOT NULL COMMENT '日期',
  `ST_ID` int(0) NOT NULL COMMENT '工位ID',
  `ROUTING_ID` int(0) NULL DEFAULT NULL COMMENT '工艺表id',
  `SERIAL_NO` int(0) NOT NULL COMMENT '序号',
  `PROCESS_REMARKS` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工序备注',
  `RUN_TIMES` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单件运行时间（分钟）4.0',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3688 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_process_production_way_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_process_routing_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_process_routing_t`;
CREATE TABLE `c_mes_process_routing_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT,
  `PRODUCTION_ID` int(0) NULL DEFAULT NULL,
  `LINE_ID` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ROUTE` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '工艺路线名称（工件名称4.0）',
  `VIEW_STATE` int(0) NULL DEFAULT NULL,
  `default_route` int(0) NULL DEFAULT NULL COMMENT '是否默认工艺路线 1 默认 0 非默认',
  `json` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `lineList` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '线列表',
  `nodeList` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '节点列表',
  `DT` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `ALTER_DT` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `PROJECT_NUMX` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目名4.0',
  `STATION_NUMX` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工位名称4.0',
  `SPECIFICATION_AND_MODELX` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '规格型号4.0',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 320 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '工艺路线' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_process_routing_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_process_station_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_process_station_t`;
CREATE TABLE `c_mes_process_station_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `STATION_INDEX` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工位下标',
  `STATION_NAME` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工位名称',
  `STATION_PROCESSOK` int(0) NULL DEFAULT NULL COMMENT '是否流程检查 0：否  1：是',
  `STATION_DATAOK` int(0) NULL DEFAULT NULL COMMENT '是否数据检查 0：否  1：是',
  `STATION_TYPE` int(0) NULL DEFAULT NULL COMMENT '工位类型（线内站、线外站）0：线内站  1：线外站',
  `STATION_RECIPEORNOT` int(0) NULL DEFAULT NULL COMMENT '是否需要配方  0：否  1：是',
  `STATION_AGVORNOT` int(0) NULL DEFAULT NULL COMMENT '是否需要AGV 0：否  1：是',
  `STATION_REQUSTOUTLINE` int(0) NULL DEFAULT NULL COMMENT '是否出站校验 0：否  1：是',
  `STATION_LIGHTORNOT` int(0) NULL DEFAULT NULL COMMENT '是否点亮放行灯 0：否  1：是',
  `STATION_REQUSTIN` int(0) NULL DEFAULT NULL COMMENT '是否请求进站 0：否  1：是',
  `STATION_REVIEWORNOT` int(0) NULL DEFAULT NULL COMMENT '是否追溯 0：否  1：是',
  `STATION_PRINTORNOT` int(0) NULL DEFAULT NULL COMMENT '是否打印 0：否  1：是',
  `STATION_UPLOADMES` int(0) NULL DEFAULT NULL COMMENT '是否上传MES 0：否  1：是',
  `STATION_ENDORNOT` int(0) NULL DEFAULT NULL COMMENT '是否末站 0：否  1：是',
  `STATION_GUNORNOT` int(0) NULL DEFAULT NULL COMMENT '是否有拧紧枪 0：否  1：是',
  `STATION_AUTOORNOT` int(0) NULL DEFAULT NULL COMMENT '站业务属性 0:手动站 1：自动站 2：测试站  3：返修站',
  `STATION_TIME` int(0) NULL DEFAULT NULL COMMENT '工位节拍',
  `LINE_ID` int(0) NULL DEFAULT NULL COMMENT '所属产线',
  `STATION_SCANDER_FLAG` int(0) NULL DEFAULT NULL COMMENT '是否有扫描枪：0：否 1：是',
  `STATION_CCD_FLAG` int(0) NULL DEFAULT NULL COMMENT '是否有视觉系统： 0：否  1：是',
  `STATION_RUBBER_FLAG` int(0) NULL DEFAULT NULL COMMENT '是否有涂胶系统:0：否 1：是',
  `STATION_LEAKAGE_FLAG` int(0) NULL DEFAULT NULL COMMENT '是否有气密测试 0：否 1：是',
  `STATION_EOL_FLAG` int(0) NULL DEFAULT NULL COMMENT '是否有EOL设备 0:否  1：是',
  `STATION_ADAM_FLAG` int(0) NULL DEFAULT NULL COMMENT '是否有板卡',
  `STATION_PLC_FLAG` int(0) NULL DEFAULT NULL COMMENT '是否有PLC',
  `CODE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工位编码',
  `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工控机ip',
  `userName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工控机系统登录名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工控机系统登录密码',
  `STAION_FLOWORNOT` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `STATION_TYPES` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工序类型:0表示普通类型，1表示委外类型，3返修类型',
  `UNIT_PRICE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '小时单价4.0',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 561 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '工位表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_process_station_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_process_supplier_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_process_supplier_t`;
CREATE TABLE `c_mes_process_supplier_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '供应商ID',
  `SUPPLIER` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '供应商',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_process_supplier_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_production_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_production_t`;
CREATE TABLE `c_mes_production_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '产品ID',
  `PRODUCTION_NAME` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '产品名称',
  `PRODUCTION_TYPE` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '产品型号',
  `PRODUCTION_SN` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品条码',
  `MATERIAL_CODE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料编码',
  `PRODUCTION_TRADEMARK` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品商标',
  `PRODUCTION_SERIES` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品系列',
  `PRODUCTION_VR` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品校验规则',
  `PRODUCTION_DISCRIPTION` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品描述',
  `STATION_NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工位名称',
  `PRODUCTION_ET` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `PRODUCTION_GT` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `PRODUCTION_STE` int(0) NULL DEFAULT NULL COMMENT '产品状态 0：在线  1：离线',
  `PRODUCTION_SYSTEM_ID` int(0) NULL DEFAULT NULL COMMENT '产品标签ID(系统码)',
  `PRODUCTION_GROUP_ID` int(0) NULL DEFAULT NULL COMMENT '产品标签ID(套数码)',
  `GROUP_NUMBER` int(0) NULL DEFAULT NULL COMMENT '系统码对应套数数量',
  `PRODUCTION_CODE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品编码',
  `PATH` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '图片路径',
  `code_digit` int(0) NULL DEFAULT NULL COMMENT '条码位数',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 460 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '产品表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_production_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_production_way_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_production_way_t`;
CREATE TABLE `c_mes_production_way_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `DT` datetime(0) NOT NULL COMMENT '日期',
  `ST_ID` int(0) NOT NULL COMMENT '工位ID',
  `ROUTING_ID` int(0) NULL DEFAULT NULL COMMENT '工艺表id',
  `SERIAL_NO` int(0) NOT NULL COMMENT '序号',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 371 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_production_way_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_purchase_order_details_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_purchase_order_details_t`;
CREATE TABLE `c_mes_purchase_order_details_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `DT` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `PURCHASE_ORDER_ID` int(0) NULL DEFAULT NULL COMMENT '采购单id',
  `MATERIAL_NO` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料编号',
  `MATERIAL_MODEL` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '型号',
  `MATERIAL_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `MATERIAL_NUM` int(0) NULL DEFAULT NULL COMMENT '数量',
  `REMARKS` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '采购详情表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_purchase_order_details_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_purchase_order_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_purchase_order_t`;
CREATE TABLE `c_mes_purchase_order_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `DT` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `PURCHASE_NO` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '采购单号',
  `PURCHASER` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '采购人',
  `SUPPLIER` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '供应商',
  `ORDER_TIME` datetime(0) NULL DEFAULT NULL COMMENT '下单时间',
  `ARRIVAL_TIME` datetime(0) NULL DEFAULT NULL COMMENT '到货时间',
  `STATUS` int(0) NOT NULL DEFAULT 1 COMMENT '状态：1 已创建、2 已审批、3 审批拒绝、4 已采购、5 已收货',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '采购单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_purchase_order_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_quality_inspection_record
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_quality_inspection_record`;
CREATE TABLE `c_mes_quality_inspection_record`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '质检记录ID',
  `CODE` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '质检记录编号',
  `SN` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '序列号',
  `TYPE` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '类型',
  `START` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '状态(\'OK\',\'NG\')',
  `QC_PERSONNEL` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '质检人',
  `DT` datetime(0) NULL DEFAULT NULL COMMENT '质检时间',
  `QC_LEAD` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '质检组长',
  `RESULT_LEAD` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '组长检查结果',
  `DT_LEAD` datetime(0) NULL DEFAULT NULL COMMENT '组长检查时间',
  `PROCESSING_TYPE` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '是否处理  （0待处理，1已处理）',
  `PROCESS_TYPE` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '已处理类型（0合格，1返厂，2报废）',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '质检记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_quality_inspection_record
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_recipe_datil_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_recipe_datil_t`;
CREATE TABLE `c_mes_recipe_datil_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `STEP_CATEGORY` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类别',
  `MATERIAL_NAME` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `GUN_NO` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '枪号',
  `PROGRAM_NO` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '程序号',
  `PHOTO_NO` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '相机号',
  `SLEEVE_NO` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '套筒号',
  `REWORKTIMES` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '返工次数',
  `FEACODE` varchar(140) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '校验规则',
  `CHEKORNO` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否校验 0：不校验 1：校验',
  `REVIEWORNO` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否追溯 0：不追溯 1：追溯',
  `EXACTORNO` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料类别 0：批次追溯 1：精确追溯',
  `MATERIALPN` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料PN',
  `BOLTEQS` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工位节拍',
  `A_LIMIT` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角度上限',
  `T_LIMIT` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '扭矩上限',
  `RECIPE_ID` int(0) NULL DEFAULT NULL COMMENT '配方ID',
  `UPLOAD_CODE` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上传代码',
  `STEPNO` int(0) NULL DEFAULT NULL COMMENT '步序',
  `NUMBERS` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数量',
  `PICTURN_PATH` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片路径',
  `BOLT_JSON` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '螺栓json数据',
  `PATH_BINARY` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '图片二进制',
  `MATERIAL_ID` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料ID',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2018 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_recipe_datil_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_recipe_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_recipe_t`;
CREATE TABLE `c_mes_recipe_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `TOTAL_ID` int(0) NOT NULL COMMENT '总配方id',
  `RECIPE_NAME` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '配方名称',
  `RECIPE_DISCRIPTION` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '配方描述',
  `STATION_ID` int(0) NULL DEFAULT NULL COMMENT '工位id',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 307 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '配方表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_recipe_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_recipe_type_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_recipe_type_t`;
CREATE TABLE `c_mes_recipe_type_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `DT` datetime(0) NULL DEFAULT NULL COMMENT '日期',
  `TYPE_NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类别名称',
  `TYPE_NO` int(0) NULL DEFAULT NULL COMMENT '类别编号 1：扫描总成 2：扫描员工号 3：扫描物料 4：扫描物料（唯一编码）5：扫描模组 6：扫描电芯 7：拧紧 8：气密性测试 9：通断测试 10：拍照 11：打印 12：用户录入 13：电性能测试 14：结束 15：称重（属于测试）',
  `DIS` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `DISTINGUISH` int(0) NULL DEFAULT NULL COMMENT '大类  1：扫描 2：拧紧 3 测试 4：拍照 5：打印 6：用户录入 7：结束',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '配方类别表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_recipe_type_t
-- ----------------------------
INSERT INTO `c_mes_recipe_type_t` VALUES (1, '2018-08-11 00:00:00', '扫描总成', 1, '扫描总成号', 1);
INSERT INTO `c_mes_recipe_type_t` VALUES (2, '2018-08-11 00:00:00', '扫描员工号', 2, '扫描员工号', 2);
INSERT INTO `c_mes_recipe_type_t` VALUES (3, '2018-08-11 00:00:00', '扫描物料', 3, '扫描物料', 1);
INSERT INTO `c_mes_recipe_type_t` VALUES (4, '2018-08-11 00:00:00', '扫描物料(唯一编码)', 4, '扫描物料的唯一编码', 1);
INSERT INTO `c_mes_recipe_type_t` VALUES (5, '2018-08-11 00:00:00', '扫描模组', 5, '扫描模组号', 1);
INSERT INTO `c_mes_recipe_type_t` VALUES (6, '2018-08-11 00:00:00', '扫描电芯', 6, '扫描电芯', 1);
INSERT INTO `c_mes_recipe_type_t` VALUES (7, '2018-08-11 00:00:00', '拧紧', 7, '拧紧类别', 2);
INSERT INTO `c_mes_recipe_type_t` VALUES (8, '2018-08-11 00:00:00', '气密性测试', 8, '气密性测试', 3);
INSERT INTO `c_mes_recipe_type_t` VALUES (9, '2018-08-11 00:00:00', '通断测试', 9, '通断测试', 3);
INSERT INTO `c_mes_recipe_type_t` VALUES (10, '2018-08-11 00:00:00', '拍照', 10, '拍照', 4);
INSERT INTO `c_mes_recipe_type_t` VALUES (11, '2018-08-11 00:00:00', '打印', 11, '打印', 5);
INSERT INTO `c_mes_recipe_type_t` VALUES (12, '2018-08-11 00:00:00', '用户录入', 12, '用户录入', 6);
INSERT INTO `c_mes_recipe_type_t` VALUES (13, '2018-08-11 00:00:00', '电性能测试', 13, '电性能测试', 3);
INSERT INTO `c_mes_recipe_type_t` VALUES (14, '2018-08-11 00:00:00', '结束', 14, '结束', 7);
INSERT INTO `c_mes_recipe_type_t` VALUES (15, '2018-08-11 00:00:00', '称重', 15, '称重', 8);
INSERT INTO `c_mes_recipe_type_t` VALUES (16, '2019-05-06 00:00:00', '扫描二级总成', 16, '扫描线外站的二级总成', 1);
INSERT INTO `c_mes_recipe_type_t` VALUES (17, '2019-06-06 00:00:00', '扫描总成码确认', 17, '扫描总成码', 1);

-- ----------------------------
-- Table structure for c_mes_retrospect_properties_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_retrospect_properties_t`;
CREATE TABLE `c_mes_retrospect_properties_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cdt` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `udt` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `retrospectId` int(0) NULL DEFAULT NULL COMMENT '追溯id',
  `stockKey` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '库存属性',
  `stockValue` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '库存属性值',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '消耗追溯属性表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_retrospect_properties_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_retrospect_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_retrospect_t`;
CREATE TABLE `c_mes_retrospect_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cdt` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `udt` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `sn` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '总成',
  `materialNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '物料号',
  `consumeNum` int(0) NULL DEFAULT NULL COMMENT '消耗数量',
  `locationId` int(0) NULL DEFAULT NULL COMMENT '库存ID',
  `operator` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '操作者',
  `operationTime` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  `equipmentNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '设备号',
  `stationId` int(0) NULL DEFAULT NULL COMMENT '工序号',
  `uninstall` int(0) NULL DEFAULT NULL COMMENT '是否卸载 0 否 1 是',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '消耗追溯表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_retrospect_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_routing_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_routing_t`;
CREATE TABLE `c_mes_routing_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT,
  `PRODUCTION_ID` int(0) NULL DEFAULT NULL,
  `LINE_ID` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ROUTE` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '工艺路线名称',
  `VIEW_STATE` int(0) NULL DEFAULT NULL,
  `default_route` int(0) NULL DEFAULT NULL COMMENT '是否默认工艺路线 1 默认 0 非默认',
  `json` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `lineList` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '线列表',
  `nodeList` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '节点列表',
  `DT` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `ALTER_DT` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 110 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '工艺路线' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_routing_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_salesorder_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_salesorder_t`;
CREATE TABLE `c_mes_salesorder_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `DT` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `ALTER_DT` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `CUSTOMER_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户名称',
  `ORDER_TIME` datetime(0) NULL DEFAULT NULL COMMENT '下单时间',
  `DELIVERY_PLAN_TIME` datetime(0) NULL DEFAULT NULL COMMENT '计划交货时间',
  `DELIVERY_ACTUAL_TIME` datetime(0) NULL DEFAULT NULL COMMENT '实际交货时间',
  `DELIVERY_INFORMATION` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交货信息',
  `STATUS` int(0) NULL DEFAULT NULL COMMENT '当前状态（1：已创建 2：已审批 3：审批不过 4：已排产 5：已生产 6：已发货 7：已关闭）',
  `CODE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编码',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 54 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '客户订单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_salesorder_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_scheduling_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_scheduling_t`;
CREATE TABLE `c_mes_scheduling_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT,
  `SHIFT_ID` int(0) NULL DEFAULT NULL,
  `TEAM_ID` int(0) NULL DEFAULT NULL,
  `DT` date NULL DEFAULT NULL COMMENT '日期',
  `LINE_ID` int(0) NULL DEFAULT NULL,
  `PLAN_NUMBER` int(0) NULL DEFAULT NULL COMMENT '计划产量',
  `ACTUAl_NUMBER` int(0) NULL DEFAULT NULL COMMENT '实际产量',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 336 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_scheduling_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_shift_emps_team_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_shift_emps_team_t`;
CREATE TABLE `c_mes_shift_emps_team_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT,
  `SHIFTS_TEAM_ID` int(0) NULL DEFAULT NULL,
  `EMP_TEAM_ID` int(0) NULL DEFAULT NULL,
  `LINE_ID` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '计划班次表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_shift_emps_team_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_shifts_team_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_shifts_team_t`;
CREATE TABLE `c_mes_shifts_team_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `START_TIME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开始时间',
  `END_TIME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '结束时间',
  `DIS` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `LINE_ID` int(0) NULL DEFAULT NULL COMMENT '产线ID',
  `PLAN_TIME` int(0) NULL DEFAULT NULL COMMENT '计划生产时间',
  `JUMP_TIME` int(0) NULL DEFAULT NULL,
  `EMP_TEAM_ID` int(0) NULL DEFAULT NULL COMMENT '班组id',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 62 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '员工班次表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_shifts_team_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_station_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_station_t`;
CREATE TABLE `c_mes_station_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `STATION_INDEX` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工位下标',
  `STATION_NAME` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工位名称',
  `STATION_PROCESSOK` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否流程检查 0：否  1：是',
  `STATION_DATAOK` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否数据检查',
  `STATION_TYPE` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工位类型（线内站、线外站）0：线内站  1：线外站',
  `STATION_RECIPEORNOT` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否需要配方  0：否  1：是',
  `STATION_AGVORNOT` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否需要AGV 0：否  1：是',
  `STATION_REQUSTOUTLINE` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否出站校验 0：否  1：是',
  `STATION_LIGHTORNOT` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否点亮放行灯 0：否  1：是',
  `STATION_REQUSTIN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否请求进站 0：否  1：是',
  `STATION_REVIEWORNOT` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '是否追溯 0：否  1：是',
  `STATION_PRINTORNOT` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否打印 0：否  1：是',
  `STATION_UPLOADMES` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否上传MES 0：否  1：是',
  `STATION_ENDORNOT` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否末站 0：否  1：是',
  `STATION_GUNORNOT` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否有拧紧枪 0：否  1：是',
  `STATION_AUTOORNOT` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '站业务属性 0:手动站 1：自动站 2：测试站  3：返修站',
  `STATION_TIME` int(0) NULL DEFAULT NULL COMMENT '工位节拍',
  `STAION_FLOWORNOT` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `LINE_ID` int(0) NULL DEFAULT NULL COMMENT '所属产线',
  `STATION_SCANDER_FLAG` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否有扫描枪：0：否 1：是',
  `STATION_CCD_FLAG` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否有视觉系统： 0：否  1：是',
  `STATION_RUBBER_FLAG` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否有涂胶系统:0：否 1：是',
  `STATION_LEAKAGE_FLAG` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否有气密测试 0：否 1：是',
  `STATION_EOL_FLAG` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否有EOL设备 0:否  1：是',
  `STATION_ADAM_FLAG` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否有办卡',
  `STATION_PLC_FLAG` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否有PLC',
  `CODE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工位编码',
  `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工控机ip',
  `userName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工控机系统登录名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工控机系统登录密码',
  `trumpet` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工位喇叭唯一标识',
  `loopSum` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工位喇叭播放次数',
  `automaticPolling` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '要料自动轮询（0 开启，1 关闭）',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 350 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '工位表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_station_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_system
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_system`;
CREATE TABLE `c_mes_system`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数名',
  `parameter` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dis` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '说明',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_system
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_table_columns_report_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_table_columns_report_t`;
CREATE TABLE `c_mes_table_columns_report_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `TABLE_COLUMNS_NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据库表列名',
  `SHOW_COLUMNS_NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '报表列显示名称',
  `TABLE_REPORT_ID` int(0) NULL DEFAULT NULL COMMENT '数据库显示表名主键',
  `SHOW_FLAG` int(0) NULL DEFAULT NULL COMMENT '是否显示标记',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 112 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '数据报表显示列' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_table_columns_report_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_table_report_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_table_report_t`;
CREATE TABLE `c_mes_table_report_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `TABLE_NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据库表名',
  `SHOW_NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '报表显示名称',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '数据库报表显示表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_table_report_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_team_detail_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_team_detail_t`;
CREATE TABLE `c_mes_team_detail_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT,
  `STATION_ID` int(0) NULL DEFAULT NULL,
  `EMP_ICD` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `EMP_TEAM_ID` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '班组成员表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_team_detail_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_team_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_team_t`;
CREATE TABLE `c_mes_team_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `DIS` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `SHIFT_ID` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_team_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_timer_config_cron_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_timer_config_cron_t`;
CREATE TABLE `c_mes_timer_config_cron_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `cron` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'cron表达式',
  `cron_explain` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'cron表达式说明',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '定时器执行间隔规则表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_timer_config_cron_t
-- ----------------------------
INSERT INTO `c_mes_timer_config_cron_t` VALUES (1, '*/5 * * * * ?', '5秒执行一次');
INSERT INTO `c_mes_timer_config_cron_t` VALUES (2, '0 */1 * * * ?', '1分钟执行一次');
INSERT INTO `c_mes_timer_config_cron_t` VALUES (3, '0 */5 * * * ?', '5分钟执行一次');
INSERT INTO `c_mes_timer_config_cron_t` VALUES (4, '0 */10 * * * ?', '10分钟执行一次');
INSERT INTO `c_mes_timer_config_cron_t` VALUES (5, '0 */15 * * * ?', '15分钟执行一次');
INSERT INTO `c_mes_timer_config_cron_t` VALUES (6, '0 */30 * * * ?', '30分钟执行一次');
INSERT INTO `c_mes_timer_config_cron_t` VALUES (7, '0 */45 * * * ?', '45分钟执行一次');
INSERT INTO `c_mes_timer_config_cron_t` VALUES (8, '0 0 */1 * * ?', '1小时执行一次');

-- ----------------------------
-- Table structure for c_mes_timer_config_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_timer_config_t`;
CREATE TABLE `c_mes_timer_config_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '定时器主键',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '类型编码',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '名称',
  `status` int(0) NULL DEFAULT NULL COMMENT '是否启用（0停止，1启用，2暂停）',
  `params` longtext CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '参数',
  `start_time` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `stop_time` datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
  `cron` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '定时规则',
  `cron_explain` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'cron表达式说明',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '定时器配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_timer_config_t
-- ----------------------------
INSERT INTO `c_mes_timer_config_t` VALUES (2, 'alarConfigInform', '告警配置定时器', 0, '5', '2021-08-09 11:31:53', '2021-08-09 11:32:04', '*/5 * * * * ?', '5秒执行一次');
INSERT INTO `c_mes_timer_config_t` VALUES (15, 'produceLine', '产线要料定时器', 0, NULL, '2021-07-28 14:48:05', '2021-07-28 14:48:05', '*/5 * * * * ?', '5秒执行一次');
INSERT INTO `c_mes_timer_config_t` VALUES (16, 'srmPoRequest', 'SRM采购申请定时器', 0, '', '2021-08-31 16:07:26', '2021-08-31 16:08:33', '*/1 * * * * ?', '1秒执行一次');
INSERT INTO `c_mes_timer_config_t` VALUES (17, 'srmPoOrder', 'SRM采购订单定时器', 0, NULL, '2021-08-31 16:07:24', '2021-08-31 16:08:34', '*/1 * * * * ?', '1秒执行一次');
INSERT INTO `c_mes_timer_config_t` VALUES (18, 'srmPoReceive', 'SRM送货单定时器', 0, NULL, '2021-08-31 16:07:25', '2021-08-31 16:08:34', '*/1 * * * * ?', '1秒执行一次');
INSERT INTO `c_mes_timer_config_t` VALUES (19, 'srmTransferOfOrder', 'SRM采购申请转订单定时器', 0, NULL, '2021-09-01 09:06:47', '2021-09-01 09:09:17', '*/60 * * * * ?', '60秒执行一次');

-- ----------------------------
-- Table structure for c_mes_timer_perform_log_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_timer_perform_log_t`;
CREATE TABLE `c_mes_timer_perform_log_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `task_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '定时器任务编号',
  `result` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '执行结果(S/E)',
  `log_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '日志内容',
  `start_time` datetime(0) NULL DEFAULT NULL COMMENT '执行时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3446 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '定时器执行日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_timer_perform_log_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_title_name_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_title_name_t`;
CREATE TABLE `c_mes_title_name_t`  (
  `id` int(0) NOT NULL,
  `dt` datetime(0) NULL DEFAULT NULL COMMENT '时间',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '琦航登录页标题' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_title_name_t
-- ----------------------------
INSERT INTO `c_mes_title_name_t` VALUES (1, '2021-06-22 14:39:18', '琦航数字工厂信息系统');

-- ----------------------------
-- Table structure for c_mes_tool_manage_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_tool_manage_t`;
CREATE TABLE `c_mes_tool_manage_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '自增',
  `DT` datetime(0) NULL DEFAULT NULL COMMENT '时间',
  `TOOL_NO` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编号',
  `TOOL_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `TOOL_DIS` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `ESTIMATE_LIFE` int(0) NULL DEFAULT NULL COMMENT '预计寿命',
  `USEFUL_LIFE` int(0) NULL DEFAULT NULL COMMENT '已用寿命',
  `SURPLUS_LIFE` int(0) NULL DEFAULT NULL COMMENT '剩余寿命',
  `MAINTAIN_CYCLE` int(0) NULL DEFAULT NULL COMMENT '维护周期',
  `LAST_MAINTAIN` date NULL DEFAULT NULL COMMENT '上次维护时间',
  `NEXT_MAINTAIN` date NULL DEFAULT NULL COMMENT '下次维护时间',
  `SURPLUS_MAINTAIN` int(0) NULL DEFAULT NULL COMMENT '剩余维护天数',
  `FIRST_USE` date NULL DEFAULT NULL COMMENT '初次使用时间',
  `EVERYTIMES` int(0) NULL DEFAULT NULL COMMENT '每一次消耗寿命',
  `VIEW_STATE` int(0) NULL DEFAULT NULL COMMENT '状态：0正常 1删除',
  `LINE_ID` int(0) NULL DEFAULT NULL,
  `STATION_ID` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 62 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '工具管理表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_tool_manage_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_total_recipe_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_total_recipe_t`;
CREATE TABLE `c_mes_total_recipe_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT,
  `TOTAL_RECIPE_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '总配方名称',
  `TOTAL_RECIPE_DESCRIBE` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '配方描述',
  `LINE_ID` int(0) NULL DEFAULT NULL COMMENT '产线ID',
  `PRODUCTION_ID` int(0) NULL DEFAULT NULL COMMENT '产品ID',
  `STATUS` int(0) NULL DEFAULT NULL COMMENT '是否默认(0：否；1：是)',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 112 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_total_recipe_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_transcoding_record_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_transcoding_record_t`;
CREATE TABLE `c_mes_transcoding_record_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `DT` datetime(0) NULL DEFAULT NULL COMMENT '转码时间',
  `originalSn` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '原码',
  `newSn` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '新码',
  `lineId` int(0) NULL DEFAULT NULL COMMENT '产线id',
  `type` int(0) NULL DEFAULT NULL COMMENT '类型 1 转码 2 拆分',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '转码记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_transcoding_record_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_user_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_user_t`;
CREATE TABLE `c_mes_user_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `USER_PWD` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `STATUS` int(0) NULL DEFAULT NULL COMMENT '身份',
  `DEPARTMENT` int(0) NULL DEFAULT NULL COMMENT '部门',
  `SECURITY` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '保密',
  `USER_POWER` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ROLE_ID` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `USER_NUMBER` int(0) NULL DEFAULT NULL,
  `LOGINSTATUS` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录标识符',
  `token` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `mobile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `appId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '钉钉appID',
  `position` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职务',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户邮箱',
  `reportsTo` int(0) NULL DEFAULT NULL COMMENT '直属主管',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `viewMode` int(0) NULL DEFAULT NULL,
  `rankId` int(0) NULL DEFAULT NULL COMMENT '职级id',
  `supplier_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '供应商代码--供应商账号标识',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB  CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_user_t
-- ----------------------------
INSERT INTO `c_mes_user_t` VALUES (1, 'admin', '5b6f250d9a79f738e3028e0cf873a652', 1, 1, '1', '1', '1', 10001, '2', 'lEVaXnKZXBphpyqUynRsbLtnIAqSAjvAPfSS', '18068711360', '28590759431053374', '软件工程师', NULL, 81, NULL, 1, NULL, NULL);

-- ----------------------------
-- Table structure for c_mes_user_t-bak
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_user_t-bak`;
CREATE TABLE `c_mes_user_t-bak`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `USER_PWD` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `STATUS` int(0) NULL DEFAULT NULL COMMENT '身份',
  `DEPARTMENT` int(0) NULL DEFAULT NULL COMMENT '部门',
  `SECURITY` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '保密',
  `USER_POWER` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ROLE_ID` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `USER_NUMBER` int(0) NULL DEFAULT NULL,
  `LOGINSTATUS` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录标识符',
  `token` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `mobile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `appId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '钉钉appID',
  `position` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职务',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户邮箱',
  `reportsTo` int(0) NULL DEFAULT NULL COMMENT '直属主管',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `viewMode` int(0) NULL DEFAULT NULL,
  `rankId` int(0) NULL DEFAULT NULL COMMENT '职级id',
  `supplier_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '供应商代码--供应商账号标识',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1000 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_user_t-bak
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_visit_log_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_visit_log_t`;
CREATE TABLE `c_mes_visit_log_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户名',
  `dt` datetime(0) NULL DEFAULT NULL COMMENT '访问时间',
  `pageName` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '访问页面',
  `ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'IP地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 41023 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '访问日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_visit_log_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_web_api_logs
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_web_api_logs`;
CREATE TABLE `c_mes_web_api_logs`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT,
  `API_NAME` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '接口名称',
  `CALL_TIME` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '调用时间',
  `PARAMETER` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '参数',
  `RETURN_RESULT` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '返回结果',
  `RETURN_TIME` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '返回时间',
  `sn` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 506774 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci COMMENT = 'web接口调用日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_web_api_logs
-- ----------------------------

-- ----------------------------
-- Table structure for c_mes_workorder_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mes_workorder_t`;
CREATE TABLE `c_mes_workorder_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT,
  `PLAN_ID` int(0) NULL DEFAULT NULL COMMENT '计划id',
  `WORK_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '工单名称',
  `NUMBER` int(0) NULL DEFAULT NULL COMMENT '工单数量',
  `PRODUCT_MARK` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '产品标记',
  `SURPLUS_NUMBER` int(0) NULL DEFAULT NULL COMMENT '之前的计划数量',
  `COMPLETE_NUMBER` int(0) NULL DEFAULT NULL COMMENT '完成数量',
  `SCHE_ID` int(0) NULL DEFAULT NULL COMMENT '排班id',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mes_workorder_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mod_call_mes_interface_log_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mod_call_mes_interface_log_t`;
CREATE TABLE `c_mod_call_mes_interface_log_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `DT` datetime(0) NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '模组调用MES日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mod_call_mes_interface_log_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mod_client_log_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mod_client_log_t`;
CREATE TABLE `c_mod_client_log_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type` int(0) NULL DEFAULT NULL COMMENT '类型',
  `lineName` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '产线名称',
  `DT` datetime(0) NULL DEFAULT NULL COMMENT '时间',
  `content` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '内容',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '客户端日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mod_client_log_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mod_data_collect_conf_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mod_data_collect_conf_t`;
CREATE TABLE `c_mod_data_collect_conf_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `DT` datetime(0) NULL DEFAULT NULL COMMENT '时间',
  `lineName` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '产线名称',
  `stationName` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '工位名称',
  `productionName` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '产品名称',
  `keyName` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '参数名',
  `PLCAddress` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'PLC地址',
  `unit` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '单位',
  `keyCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '参数编码',
  `device` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '设备',
  `upperLimit` varchar(11) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '上限',
  `lowerLimit` varchar(11) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '下限',
  `describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '参数描述',
  `defaultValue` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '默认值',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '数据配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mod_data_collect_conf_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mod_device_state_log_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mod_device_state_log_t`;
CREATE TABLE `c_mod_device_state_log_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `DT` datetime(0) NULL DEFAULT NULL COMMENT '时间',
  `lineName` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '产线名称',
  `stationName` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '工位名称',
  `originalState` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '原状态',
  `presentState` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '现状态',
  `emp` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '员工号',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '设备状态记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mod_device_state_log_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mod_interface_conf_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mod_interface_conf_t`;
CREATE TABLE `c_mod_interface_conf_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `DT` datetime(0) NULL DEFAULT NULL COMMENT '时间',
  `key` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '参数名',
  `value` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '参数值',
  `typeId` int(0) NULL DEFAULT NULL COMMENT '类型id',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'MES接口配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mod_interface_conf_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mod_interface_type_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mod_interface_type_t`;
CREATE TABLE `c_mod_interface_type_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `typeName` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '类型名称',
  `describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '描述',
  `lineName` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '产线名称',
  `stationName` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '工位名称',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'MES接口配置类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mod_interface_type_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_mod_ng_log_t
-- ----------------------------
DROP TABLE IF EXISTS `c_mod_ng_log_t`;
CREATE TABLE `c_mod_ng_log_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT,
  `DT` datetime(0) NULL DEFAULT NULL,
  `SN` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `lineName` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `stationName` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `workorderId` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '工位NG记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_mod_ng_log_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_oa_process_t
-- ----------------------------
DROP TABLE IF EXISTS `c_oa_process_t`;
CREATE TABLE `c_oa_process_t`  (
  `id` int(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'oa审批流程' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_oa_process_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_oa_approval_batch_records_t
-- ----------------------------
DROP TABLE IF EXISTS `c_oa_approval_batch_records_t`;
CREATE TABLE `c_oa_approval_batch_records_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `dt` datetime(0) NULL DEFAULT NULL,
  `listNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `userId` int(0) NULL DEFAULT NULL,
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '我的已批记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_oa_approval_batch_records_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_oa_approval_record_detailed_t
-- ----------------------------
DROP TABLE IF EXISTS `c_oa_approval_record_detailed_t`;
CREATE TABLE `c_oa_approval_record_detailed_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `step` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '步骤',
  `formData` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '表单',
  `listNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '单号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '审批记录详情表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_oa_approval_record_detailed_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_oa_approval_record_draft_t
-- ----------------------------
DROP TABLE IF EXISTS `c_oa_approval_record_draft_t`;
CREATE TABLE `c_oa_approval_record_draft_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `dt` datetime(0) NULL DEFAULT NULL,
  `userId` int(0) NULL DEFAULT NULL,
  `formTemplateId` int(0) NULL DEFAULT NULL,
  `details` text CHARACTER SET utf8 COLLATE utf8_bin NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '审批草稿表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_oa_approval_record_draft_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_oa_approval_record_note_t
-- ----------------------------
DROP TABLE IF EXISTS `c_oa_approval_record_note_t`;
CREATE TABLE `c_oa_approval_record_note_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `dt` datetime(0) NULL DEFAULT NULL,
  `dis` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '说明',
  `listNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '单号',
  `userId` int(0) NULL DEFAULT NULL COMMENT '审批人',
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '状态',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '审批或者抄送',
  `step` int(0) NULL DEFAULT NULL COMMENT '步序',
  `list` text CHARACTER SET utf8 COLLATE utf8_bin NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 48 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '审批记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_oa_approval_record_note_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_oa_approval_record_step_t
-- ----------------------------
DROP TABLE IF EXISTS `c_oa_approval_record_step_t`;
CREATE TABLE `c_oa_approval_record_step_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `step` int(0) NULL DEFAULT NULL COMMENT '步骤',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '内容',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '标题',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '类型',
  `properties` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '其他参数',
  `listNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '单号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 61 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '审批流程步序表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_oa_approval_record_step_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_oa_approval_record_t
-- ----------------------------
DROP TABLE IF EXISTS `c_oa_approval_record_t`;
CREATE TABLE `c_oa_approval_record_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `dt` datetime(0) NULL DEFAULT NULL,
  `userId` int(0) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `listNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '详情地址',
  `parameter` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '参数',
  `callbackUrl` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '回调地址',
  `urgent` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '加急',
  `overtime` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '超时',
  `timedOut` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '已超时',
  `overtimeType` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '超时类型（天、时、分）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '审批表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_oa_approval_record_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_oa_approval_record_with_t
-- ----------------------------
DROP TABLE IF EXISTS `c_oa_approval_record_with_t`;
CREATE TABLE `c_oa_approval_record_with_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `dt` datetime(0) NULL DEFAULT NULL,
  `userId` int(0) NULL DEFAULT NULL COMMENT '用户',
  `listNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '单号',
  `overtime` int(0) NULL DEFAULT NULL COMMENT '超时',
  `timedOut` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '已超时',
  `overtimeType` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '超时类型（天、时、分）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 75 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '待审批流程记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_oa_approval_record_with_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_oa_approval_related_t
-- ----------------------------
DROP TABLE IF EXISTS `c_oa_approval_related_t`;
CREATE TABLE `c_oa_approval_related_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `listNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `userId` int(0) NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '类型 审批或抄送',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 97 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '相关审批' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_oa_approval_related_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_oa_file_authority_t
-- ----------------------------
DROP TABLE IF EXISTS `c_oa_file_authority_t`;
CREATE TABLE `c_oa_file_authority_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '路径',
  `keyId` int(0) NULL DEFAULT NULL COMMENT '外键id',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '权限类型 只读，读写',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '文件夹名',
  `userOrRole` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户或者角色',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 403 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_oa_file_authority_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_oa_file_t
-- ----------------------------
DROP TABLE IF EXISTS `c_oa_file_t`;
CREATE TABLE `c_oa_file_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '名称',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '类型；文件夹、文件',
  `path` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '路径',
  `establishTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `userId` int(0) NULL DEFAULT NULL COMMENT '上传用户',
  `size` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '文件大小',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 120 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '文件' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_oa_file_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_oa_form_template_detailed_t
-- ----------------------------
DROP TABLE IF EXISTS `c_oa_form_template_detailed_t`;
CREATE TABLE `c_oa_form_template_detailed_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `key` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '类型',
  `check` bit(1) NULL DEFAULT NULL COMMENT '是否必填',
  `formTemplateId` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '表单模板id',
  `order` int(0) NULL DEFAULT NULL COMMENT '顺序',
  `parameter` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '全部参数',
  `width` int(0) NULL DEFAULT NULL COMMENT '宽度',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 503 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '表单模板明细' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_oa_form_template_detailed_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_oa_form_template_t
-- ----------------------------
DROP TABLE IF EXISTS `c_oa_form_template_t`;
CREATE TABLE `c_oa_form_template_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `dt` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '编号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '模板名称',
  `dis` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '模板说明',
  `typeId` int(0) NULL DEFAULT NULL COMMENT '模板类型id',
  `jurisdiction` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '权限',
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '状态  启用、停用',
  `process` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '审批流程',
  `detailed` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '明细',
  `tokenUrl` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '回调地址',
  `formType` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '业务类型（普通、业务）',
  `jumpUrl` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '跳转地址',
  `overtime` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '超时分钟',
  `overtimeType` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '超时类型（天、时、分）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 520 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '表单模板' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_oa_form_template_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_oa_form_template_table_t
-- ----------------------------
DROP TABLE IF EXISTS `c_oa_form_template_table_t`;
CREATE TABLE `c_oa_form_template_table_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `key` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '类型',
  `formTemplateId` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '表单模板id',
  `order` int(0) NULL DEFAULT NULL COMMENT '顺序',
  `group` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '组',
  `groupName` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '组名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 293 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '表单模板表格' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_oa_form_template_table_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_oa_form_template_type_t
-- ----------------------------
DROP TABLE IF EXISTS `c_oa_form_template_type_t`;
CREATE TABLE `c_oa_form_template_type_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dis` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '表单模板类型' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_oa_form_template_type_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_process_details_t
-- ----------------------------
DROP TABLE IF EXISTS `c_process_details_t`;
CREATE TABLE `c_process_details_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `TASK_ID` int(0) NULL DEFAULT NULL COMMENT '生产任务ID',
  `PROCESS_ID` int(0) NULL DEFAULT NULL COMMENT '工序ID',
  `PROCESS_ORDER` int(0) NULL DEFAULT NULL COMMENT '工序顺序',
  `PLAN_NUM` int(0) NULL DEFAULT NULL COMMENT '计划数量',
  `COMPLETE_NUM` int(0) NULL DEFAULT NULL COMMENT '完成数量',
  `USEABLE_NUM` int(0) NULL DEFAULT NULL COMMENT '合格数量',
  `OK_OUTSOURCE_TEM` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '(临时合格数量)',
  `NG_NUM` int(0) NULL DEFAULT NULL COMMENT 'NG数量',
  `NG_NUM_TEM` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'NG数量暂存区',
  `OUTSOURCE_OUT_NUM` int(0) NULL DEFAULT NULL COMMENT '委外发出',
  `OK_INSOURCE_TEM` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '临时存储委外发出数量',
  `TESTING_NUM` int(0) NULL DEFAULT NULL COMMENT '待检测数量',
  `TESTING_NUM_TEM` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '临时待检测数量',
  `OUTSOURCE_IN_NUM` int(0) NULL DEFAULT NULL COMMENT '委外接收',
  `STATUS_FLAGS` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工序状态：0代表未审核，1代表审核，2代表审核通过67成品89委外发出1011代表委外接收',
  `QUALITY_PERSON` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '品检员',
  `ALL_QUALITY_PERSON` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '品检员集合',
  `OUT_PERSON` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '委外发出负责人',
  `IN_PERSON` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '委外接收负责人',
  `PERSON` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审批人',
  `ALL_PERSON` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '审批人集合',
  `OUT_PERSON_APPROVAL` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '委外发出审核负责人',
  `IN_PERSON_APPROVAL` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '委外接收审核人',
  `OUT_SUPPLIER` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发出供应商',
  `IN_SUPPLIER` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接收供应商',
  `ALL_IN_SUPPLIER` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接收供应商集合',
  `ALL_OUT_SUPPLIER` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发出供应商集合',
  `FINISH_PRODUCT_TEM` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '临时入库成功数量',
  `REMARKS` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '一条备注',
  `ALL_REMARKS` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注集合',
  `NG_NUM_TEM_APPROVAL` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '临时审批NG数量',
  `OK_NUM_TEM_APPROVAL` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '临时审批OK数量',
  `TESTING_NUM_TEM_APPROVAL` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '临时审批待检测数量',
  `OK_INSOURCE_TEM_APPROVLA` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '临时审批委外发出数量',
  `OK_OUTSOURCE_TEMS` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '临时委外合格数量',
  `NG_OUTSOURCE_TEMS` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '临时委外NG数量',
  `OK_INSOURCE_TEMS` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '临时委外待检测数量',
  `INSOURCE_IN_TEMS` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '临时下推数量',
  `INSOURCE_IN_TEMS_APPROVAL` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '临时下推审核数量',
  `OK_FINISH_PRODUCTION_TEMS` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '临时入库合格数量',
  `OK_FINISH_PRODUCTION_TEMS_APPROVAL` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '临时入库合格审批数量',
  `REWORK_NUM` int(0) NULL DEFAULT NULL COMMENT '返修数量',
  `USE_REWORK_NUM` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '可用于返修的数量',
  `USE_REWORK_NUM_TEM` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '临时用于返修审批的数量',
  `USE_REWORK_NUM_TEM_APPROVAL` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '临时用于审批的返修数量',
  `USE_REWORK_NUM_PERSON` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '返修申请审批人',
  `USE_REWORK_NUM_PERSON_APPROVAL` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'f返修审核审批人',
  `EDL_FLAGS` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '拆分标记（可删除标记）0代表初始分支（可删除），1代表使用过的分支（不可删除）',
  `SPLIT_FLAGS` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '拆分状态：1表示主分支未被拆解，2表示主分支被拆解，3表示未审批的子分支，4表示要被审批的子分支',
  `PUSH_DOWN_NUM` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '质检后到达下推的数量',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1023 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '工序详情表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_process_details_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_process_operation_log
-- ----------------------------
DROP TABLE IF EXISTS `c_process_operation_log`;
CREATE TABLE `c_process_operation_log`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `DT` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日志创建时间',
  `TASK_ID` int(0) NULL DEFAULT NULL COMMENT '生产任务ID',
  `PROCESS_ID` int(0) NULL DEFAULT NULL COMMENT '工序ID',
  `LOG_INFO` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '日志信息',
  `LOG_TYPE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日志类型（生产任务、品检、下推、委外发出、委外接收、成品入库）',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4312 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_process_operation_log
-- ----------------------------

-- ----------------------------
-- Table structure for c_process_outsource_list
-- ----------------------------
DROP TABLE IF EXISTS `c_process_outsource_list`;
CREATE TABLE `c_process_outsource_list`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '委外清单ID',
  `TASK_ID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务单ID',
  `PROCESS_ID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工序ID',
  `SPLIT_ID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工序拆分ID',
  `OUT_SIDE_NUM` int(0) NULL DEFAULT NULL COMMENT '委外发出数量',
  `OUT_SIDE_NUM_APPLY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '委外发出申请',
  `OUT_SIDE_NUM_EXAMINE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '委外发出审核',
  `IN_SIDE_NUM` int(0) NULL DEFAULT NULL COMMENT '委外接收数量',
  `IN_SIDE_NUM_APPLY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '委外接收申请',
  `IN_SIDE_NUM_EXAMINE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '委外接收审核',
  `OK_NUM` int(0) NULL DEFAULT NULL COMMENT '合格数量',
  `OK_NUM_APPLY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '合格数量申请',
  `OK_NUM_EXAMINE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '合格数量审批',
  `NG_NUM` int(0) NULL DEFAULT NULL COMMENT 'NG数量',
  `NG_NUM_APPLY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'NG数量申请',
  `NG_NUM_EXAMINE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'NG数量审批',
  `SUPPLIER` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '供应商',
  `REWORK_NUM` int(0) NULL DEFAULT NULL COMMENT '委外返修数量',
  `REWORK_APPLY_NUM` int(0) NULL DEFAULT NULL COMMENT '委外返修申请数量',
  `REWORK_EXAMINE_NUM` int(0) NULL DEFAULT NULL COMMENT '委外返修审核数量',
  `STATUS` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '清单状态：0待申请  1待审批  2已完成',
  `OUT_SIDE_PERSON_APPLY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '委外发出申请负责人',
  `OUT_SIDE_PERSON_EXAMINE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '委外发出审核负责人',
  `IN_SIDE_PERSON_APPLY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '委外接收申请负责人',
  `IN_SIDE_PERSON_EXAMINE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '委外接收审批责任人',
  `QUALITY_PERSON_APPLY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '委外质检申请负责人',
  `QUALITY_PERSON_EXAMINE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '委外质检审核负责人',
  `PLAN_NUM` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '计划数量',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 57 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '委外清单列表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_process_outsource_list
-- ----------------------------

-- ----------------------------
-- Table structure for c_process_production_task
-- ----------------------------
DROP TABLE IF EXISTS `c_process_production_task`;
CREATE TABLE `c_process_production_task`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `PROJECT_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目名称',
  `PROJECT_NUM` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目号',
  `SPECIFICATION_AND_MODEL` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '规格型号',
  `MATERIAL_CODE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料代码',
  `ROUTE_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工艺路线',
  `PLAN_NUM` int(0) NULL DEFAULT NULL COMMENT '计划数量',
  `COMPLETE_NUM` int(0) NULL DEFAULT NULL COMMENT '完成数量',
  `NG_NUM` int(0) NULL DEFAULT NULL COMMENT '不合格数量',
  `REWORK_NUM` int(0) NULL DEFAULT NULL COMMENT '返修数量',
  `OPERATION_DEPARTMENT` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作部门(操作人)',
  `STATUS_FLAGS` int(0) NULL DEFAULT NULL COMMENT '状态：0开始，1进行中，2暂停，3完成',
  `DT` datetime(0) NULL DEFAULT NULL COMMENT '生产任务创建时间',
  `IMAGES` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '生产任务产品图片',
  `MATERIAL_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料名称',
  `MATERIAL_QUALITY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料材质',
  `NUM_REMARKS` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数量备注',
  `STATION_NUM` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工位号',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1396 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '生产任务表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_process_production_task
-- ----------------------------

-- ----------------------------
-- Table structure for c_process_routing_remarks
-- ----------------------------
DROP TABLE IF EXISTS `c_process_routing_remarks`;
CREATE TABLE `c_process_routing_remarks`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ROUTE_ID` int(0) NULL DEFAULT NULL COMMENT '工艺ID',
  `PROCESS_ID` int(0) NULL DEFAULT NULL COMMENT '工序ID',
  `REMARKS` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '工序备注',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 298 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_process_routing_remarks
-- ----------------------------

-- ----------------------------
-- Table structure for c_qh_authority_interface
-- ----------------------------
DROP TABLE IF EXISTS `c_qh_authority_interface`;
CREATE TABLE `c_qh_authority_interface`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路径',
  `operation_type_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作类型代码',
  `menu_id` int(0) NULL DEFAULT NULL COMMENT '菜单id',
  `operation_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作类型',
  `describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 198 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限接口			\r\n' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_qh_authority_interface
-- ----------------------------

-- ----------------------------
-- Table structure for c_qh_equipment_check_config_t
-- ----------------------------
DROP TABLE IF EXISTS `c_qh_equipment_check_config_t`;
CREATE TABLE `c_qh_equipment_check_config_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `dt` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '点检名 ',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '点检编号',
  `equipmentId` int(0) NULL DEFAULT NULL COMMENT '设备 id',
  `explain` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '说明 ',
  `editionId` int(0) NULL DEFAULT NULL COMMENT '版本表id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '设备点检配置 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_qh_equipment_check_config_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_qh_equipment_check_data_detailed_t
-- ----------------------------
DROP TABLE IF EXISTS `c_qh_equipment_check_data_detailed_t`;
CREATE TABLE `c_qh_equipment_check_data_detailed_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `dt` datetime(0) NULL DEFAULT NULL COMMENT '点检时间',
  `item` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '点检项',
  `explain` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '点检说明',
  `result` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '点检结果',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备注',
  `checkDataId` int(0) NULL DEFAULT NULL COMMENT '点检记录id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '设备点检记录明细' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_qh_equipment_check_data_detailed_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_qh_equipment_check_data_t
-- ----------------------------
DROP TABLE IF EXISTS `c_qh_equipment_check_data_t`;
CREATE TABLE `c_qh_equipment_check_data_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `dt` datetime(0) NULL DEFAULT NULL COMMENT '点检时间',
  `equipmentName` varchar(11) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '设备名称',
  `lineName` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '产线名称',
  `checkPerson` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '点检人',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备注',
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '设备点检记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_qh_equipment_check_data_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_qh_equipment_check_edition_t
-- ----------------------------
DROP TABLE IF EXISTS `c_qh_equipment_check_edition_t`;
CREATE TABLE `c_qh_equipment_check_edition_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `edition` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '版本号',
  `dt` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `state` int(0) NULL DEFAULT NULL COMMENT '状态1启用2停用',
  `equipmentId` int(0) NULL DEFAULT NULL COMMENT '设备id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '设备点检等级' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_qh_equipment_check_edition_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_qh_equipment_check_items_t
-- ----------------------------
DROP TABLE IF EXISTS `c_qh_equipment_check_items_t`;
CREATE TABLE `c_qh_equipment_check_items_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `checkItems` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '点检项',
  `explain` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '说明 ',
  `checkConfigId` int(0) NULL DEFAULT NULL COMMENT '设备点检配置id',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '点检内容',
  `editionId` int(0) NULL DEFAULT NULL COMMENT '版本id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '设备点检项' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_qh_equipment_check_items_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_qh_equipment_information
-- ----------------------------
DROP TABLE IF EXISTS `c_qh_equipment_information`;
CREATE TABLE `c_qh_equipment_information`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备编号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备名称',
  `model` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备型号',
  `brand` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备品牌',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备类型',
  `maintenancePeriod` int(0) NULL DEFAULT NULL COMMENT '保养周期',
  `workState` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工作状态 1:正常 2:异常',
  `functionState` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '运行状态 1:运行中 2:休息',
  `plcAdd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'PLC地址',
  `ipAdd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'IP地址',
  `port` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '端口号',
  `lineId` int(0) NULL DEFAULT NULL COMMENT '产线id',
  `stationIds` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所有工位id',
  `describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备描述',
  `lastMaintenanceDate` datetime(0) NULL DEFAULT NULL COMMENT '上次保养日期',
  `nextMaintenanceDate` date NULL DEFAULT NULL COMMENT '下次保养日期',
  `lastRepairDate` datetime(0) NULL DEFAULT NULL COMMENT '上次维修日期',
  `spotCheckCycle` int(0) NULL DEFAULT NULL COMMENT '点检周期',
  `lastSpotCheckDate` datetime(0) NULL DEFAULT NULL COMMENT '上次点检日期',
  `nextSpotCheckDate` datetime(0) NULL DEFAULT NULL COMMENT '下次点检日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_qh_equipment_information
-- ----------------------------

-- ----------------------------
-- Table structure for c_qh_equipment_information_custom_columns
-- ----------------------------
DROP TABLE IF EXISTS `c_qh_equipment_information_custom_columns`;
CREATE TABLE `c_qh_equipment_information_custom_columns`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `parentId` int(0) NULL DEFAULT NULL COMMENT '父级 id',
  `columnName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '列名',
  `value` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '值',
  `explain` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '说明',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_qh_equipment_information_custom_columns
-- ----------------------------

-- ----------------------------
-- Table structure for c_qh_equipment_information_event
-- ----------------------------
DROP TABLE IF EXISTS `c_qh_equipment_information_event`;
CREATE TABLE `c_qh_equipment_information_event`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `dt` datetime(0) NULL DEFAULT NULL,
  `parentId` int(0) NULL DEFAULT NULL,
  `event` int(0) NULL DEFAULT NULL COMMENT '事件 ： 1.创建、2.编辑、3.正常、4.异常、5.运行中、6.休息、7.点检、8.保养、9.维修',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 45 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_qh_equipment_information_event
-- ----------------------------

-- ----------------------------
-- Table structure for c_qh_equipment_information_station
-- ----------------------------
DROP TABLE IF EXISTS `c_qh_equipment_information_station`;
CREATE TABLE `c_qh_equipment_information_station`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `stationId` int(0) NULL DEFAULT NULL,
  `parentId` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 209 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_qh_equipment_information_station
-- ----------------------------

-- ----------------------------
-- Table structure for c_qh_equipment_maintain_config_t
-- ----------------------------
DROP TABLE IF EXISTS `c_qh_equipment_maintain_config_t`;
CREATE TABLE `c_qh_equipment_maintain_config_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `dt` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '名称',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '编号',
  `equipmentId` int(0) NULL DEFAULT NULL COMMENT '设备 id',
  `explain` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '说明 ',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '设备点检配置 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_qh_equipment_maintain_config_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_qh_equipment_maintain_data_detailed_t
-- ----------------------------
DROP TABLE IF EXISTS `c_qh_equipment_maintain_data_detailed_t`;
CREATE TABLE `c_qh_equipment_maintain_data_detailed_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `dt` datetime(0) NULL DEFAULT NULL COMMENT '保养时间',
  `item` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '保养项',
  `explain` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '保养说明',
  `result` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '保养结果',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备注',
  `checkDataId` int(0) NULL DEFAULT NULL COMMENT '保养记录id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '设备保养记录明细' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_qh_equipment_maintain_data_detailed_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_qh_equipment_maintain_data_t
-- ----------------------------
DROP TABLE IF EXISTS `c_qh_equipment_maintain_data_t`;
CREATE TABLE `c_qh_equipment_maintain_data_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `dt` datetime(0) NULL DEFAULT NULL COMMENT '保养时间',
  `equipmentName` varchar(11) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '设备名称',
  `lineName` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '产线名称',
  `checkPerson` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '保养人',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备注',
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '设备保养记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_qh_equipment_maintain_data_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_qh_equipment_maintain_edition_t
-- ----------------------------
DROP TABLE IF EXISTS `c_qh_equipment_maintain_edition_t`;
CREATE TABLE `c_qh_equipment_maintain_edition_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `edition` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '版本号',
  `dt` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `state` int(0) NULL DEFAULT NULL COMMENT '状态1启用2停用',
  `equipmentId` int(0) NULL DEFAULT NULL COMMENT '设备id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 63 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_qh_equipment_maintain_edition_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_qh_equipment_maintain_items_t
-- ----------------------------
DROP TABLE IF EXISTS `c_qh_equipment_maintain_items_t`;
CREATE TABLE `c_qh_equipment_maintain_items_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `item` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '项目',
  `explain` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '说明 ',
  `editionId` int(0) NULL DEFAULT NULL COMMENT '版本id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 113 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '设备点检项' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_qh_equipment_maintain_items_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_qh_equipment_maintain_t
-- ----------------------------
DROP TABLE IF EXISTS `c_qh_equipment_maintain_t`;
CREATE TABLE `c_qh_equipment_maintain_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `equipmentId` int(0) NULL DEFAULT NULL COMMENT '设备id',
  `dt` datetime(0) NULL DEFAULT NULL COMMENT '保养时间',
  `personLiable` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '负责人 ',
  `maintainer` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '保养人',
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '设备保养' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_qh_equipment_maintain_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_qh_equipment_repair_t
-- ----------------------------
DROP TABLE IF EXISTS `c_qh_equipment_repair_t`;
CREATE TABLE `c_qh_equipment_repair_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `dt` datetime(0) NULL DEFAULT NULL COMMENT '维修时间',
  `equipmentId` int(0) NULL DEFAULT NULL COMMENT '设备id',
  `repairman` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '维修人',
  `personInCharge` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '负责人 ',
  `reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '维修原因',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '设备维修记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_qh_equipment_repair_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_qh_external_contact_tag_t
-- ----------------------------
DROP TABLE IF EXISTS `c_qh_external_contact_tag_t`;
CREATE TABLE `c_qh_external_contact_tag_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `dt` datetime(0) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '标签名称',
  `typeId` int(0) NULL DEFAULT NULL COMMENT '类型Id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '外部联系人标签' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_qh_external_contact_tag_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_qh_external_contact_tag_type_t
-- ----------------------------
DROP TABLE IF EXISTS `c_qh_external_contact_tag_type_t`;
CREATE TABLE `c_qh_external_contact_tag_type_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `dt` datetime(0) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '类型名称',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '标签使用类型：单选，多选',
  `dis` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '外部联系人标签类型' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_qh_external_contact_tag_type_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_qh_external_contacts_t
-- ----------------------------
DROP TABLE IF EXISTS `c_qh_external_contacts_t`;
CREATE TABLE `c_qh_external_contacts_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `dt` datetime(0) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '姓名',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '电话',
  `company` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '公司',
  `position` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '职位',
  `mailbox` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '邮箱',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '地址',
  `dis` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备注',
  `personInCharge` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '负责人',
  `sharer` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '共享人',
  `tag` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '标签',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '外部联系人' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_qh_external_contacts_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_qh_factory_material_t
-- ----------------------------
DROP TABLE IF EXISTS `c_qh_factory_material_t`;
CREATE TABLE `c_qh_factory_material_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `dt` datetime(0) NULL DEFAULT NULL,
  `materialId` int(0) NULL DEFAULT NULL COMMENT '物料ID',
  `factoryId` int(0) NULL DEFAULT NULL COMMENT '工厂ID',
  `STOCK_UNIT` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '库存单位',
  `INVENTORY_MODEL_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '库存模型组',
  `INVENTORY_DIMENSION_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '库存维组',
  `RELEASE` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发放（y:自动发送，n:工单发送）',
  `INSPECTION` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '检验等级',
  `FICTITIOUS` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '虚拟',
  `SALES_UNIT` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '销售单位',
  `SECRECY` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '保密否',
  `PURCHASING_UNIT` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '采购单位',
  `PRODUCTION_TEAM` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生产组',
  `MININUMBER_OF_PACKAGES` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最小包装数量',
  `TERM_OF_VALIDITY` datetime(0) NULL DEFAULT NULL COMMENT '有效期',
  `TYPENUM` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '型号',
  `VOLTAGE` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电压容量',
  `PART_COUNTS` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '子件数',
  `CELL_CAPACITY` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电芯容量\n',
  `SCAN` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否扫描(Y:是,N:否)',
  `CELL_SPECIFICATION` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电芯规格\n',
  `TRACES_TYPE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '追溯方式(0.混合追溯，1.批次追溯，2.精确追溯)',
  `DELIVERY_STRATEGY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '出库策略 1 空间利用率（默认）',
  `PURCHASING_STRATEGY` int(0) NULL DEFAULT 1 COMMENT '采购策略 1 标准 2 追溯项目号',
  `PROCUREMENT_CYCLE` int(0) NULL DEFAULT NULL COMMENT '采购周期（天数）',
  `wQIS` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '入库质检策略（先入后检、先检后入）',
  `InspectionMethod` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '检验方式',
  `VIEW_MODE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_qh_factory_material_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_qh_head_of_department_t
-- ----------------------------
DROP TABLE IF EXISTS `c_qh_head_of_department_t`;
CREATE TABLE `c_qh_head_of_department_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `departmentId` int(0) NULL DEFAULT NULL COMMENT '部门id',
  `userId` int(0) NULL DEFAULT NULL COMMENT '用户id',
  `order` int(0) NULL DEFAULT NULL COMMENT '顺序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 83 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '部门主管' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_qh_head_of_department_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_qh_interface
-- ----------------------------
DROP TABLE IF EXISTS `c_qh_interface`;
CREATE TABLE `c_qh_interface`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `authority_interface_id` int(0) NULL DEFAULT NULL COMMENT '权限id',
  `InterfaceAddress` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接口地址',
  `describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_qh_interface
-- ----------------------------

-- ----------------------------
-- Table structure for c_qh_menu
-- ----------------------------
DROP TABLE IF EXISTS `c_qh_menu`;
CREATE TABLE `c_qh_menu`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `menu_grade` int(0) NULL DEFAULT NULL COMMENT '菜单等级',
  `superior_menu_id` int(0) NULL DEFAULT NULL COMMENT '上级菜单id',
  `menu_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `if_enable` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否启用(布尔类型)',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路径',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `order` int(0) NULL DEFAULT NULL COMMENT '顺序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3003 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_qh_menu
-- ----------------------------

-- ----------------------------
-- Table structure for c_qh_news_t
-- ----------------------------
DROP TABLE IF EXISTS `c_qh_news_t`;
CREATE TABLE `c_qh_news_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `dt` datetime(0) NULL DEFAULT NULL COMMENT '通知日期',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '标题',
  `msg` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '消息',
  `userId` int(0) NULL DEFAULT NULL COMMENT '接收人',
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '状态，已读、未读',
  `expandData` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '拓展数据JSON格式',
  `url` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '跳转地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11140 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_qh_news_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_qh_rank_t
-- ----------------------------
DROP TABLE IF EXISTS `c_qh_rank_t`;
CREATE TABLE `c_qh_rank_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `dt` datetime(0) NULL DEFAULT NULL,
  `rankName` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '职级名称',
  `grade` int(0) NULL DEFAULT NULL COMMENT '等级，数值越小等级越大',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB  CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '职级表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for c_qh_role
-- ----------------------------
DROP TABLE IF EXISTS `c_qh_role`;
CREATE TABLE `c_qh_role`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 102 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_qh_role
-- ----------------------------
INSERT INTO `c_qh_role` VALUES (1, 'system', '');

-- ----------------------------
-- Table structure for c_qh_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `c_qh_role_menu`;
CREATE TABLE `c_qh_role_menu`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `role_id` int(0) NULL DEFAULT NULL COMMENT '角色id',
  `menu_id` int(0) NULL DEFAULT NULL COMMENT '菜单id',
  `operation_type_id` int(0) NULL DEFAULT NULL COMMENT '操作类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1185 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色_菜单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_qh_role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for c_qh_wms_barcode_template_details_t
-- ----------------------------
DROP TABLE IF EXISTS `c_qh_wms_barcode_template_details_t`;
CREATE TABLE `c_qh_wms_barcode_template_details_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `key` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '变量',
  `value` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '值',
  `templateId` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '模板id',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '获取方式',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '条码模板详情' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_qh_wms_barcode_template_details_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_qh_wms_barcode_template_t
-- ----------------------------
DROP TABLE IF EXISTS `c_qh_wms_barcode_template_t`;
CREATE TABLE `c_qh_wms_barcode_template_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `dt` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '标题',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '模板名称',
  `userId` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建人',
  `viewMode` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '条码模板' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_qh_wms_barcode_template_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_qh_wms_expense_collection_d_t
-- ----------------------------
DROP TABLE IF EXISTS `c_qh_wms_expense_collection_d_t`;
CREATE TABLE `c_qh_wms_expense_collection_d_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `rId` int(0) NULL DEFAULT NULL COMMENT '行id',
  `packingId` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '包装id',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '单件码',
  `packingNumber` int(0) NULL DEFAULT NULL COMMENT '包装数量',
  `number` int(0) NULL DEFAULT NULL COMMENT '单件数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '费用化领用 详情表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_qh_wms_expense_collection_d_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_qh_wms_expense_collection_h_t
-- ----------------------------
DROP TABLE IF EXISTS `c_qh_wms_expense_collection_h_t`;
CREATE TABLE `c_qh_wms_expense_collection_h_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `listNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '单据号',
  `factoryId` int(0) NULL DEFAULT NULL COMMENT '工厂id',
  `locationId` int(0) NULL DEFAULT NULL COMMENT '库位id',
  `departmentId` int(0) NULL DEFAULT NULL COMMENT '部门id',
  `projectId` int(0) NULL DEFAULT NULL COMMENT '项目id',
  `establishUserId` int(0) NULL DEFAULT NULL COMMENT '创建用户id',
  `establishDt` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建时间',
  `handleUserId` int(0) NULL DEFAULT NULL COMMENT '处理用户id',
  `handleDt` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '处理时间',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '新建（保存）、已提交审批（提交审批）、审批驳回（审批结果驳回）、已提交仓库（审批通过或者提交仓库）、已完成（过账、生成条码库存）、仓库驳回（仓库过账驳回）',
  `dis` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '备注',
  `viewMode` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '费用化领用 头表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_qh_wms_expense_collection_h_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_qh_wms_expense_collection_r_t
-- ----------------------------
DROP TABLE IF EXISTS `c_qh_wms_expense_collection_r_t`;
CREATE TABLE `c_qh_wms_expense_collection_r_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `listNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '单据号',
  `materialNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '物料编号',
  `materialName` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '物料名称',
  `number` int(0) NULL DEFAULT NULL COMMENT '数量',
  `unit` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '单位',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '费用化领用 行表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_qh_wms_expense_collection_r_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_qh_wms_expense_warehousing_d_t
-- ----------------------------
DROP TABLE IF EXISTS `c_qh_wms_expense_warehousing_d_t`;
CREATE TABLE `c_qh_wms_expense_warehousing_d_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `packingId` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '包装id',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '单件码',
  `packingNumber` int(0) NULL DEFAULT NULL COMMENT '包装数量',
  `number` int(0) NULL DEFAULT NULL COMMENT '单件数量',
  `rId` int(0) NULL DEFAULT NULL COMMENT '行id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '费用化入库 详情表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_qh_wms_expense_warehousing_d_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_qh_wms_expense_warehousing_h_t
-- ----------------------------
DROP TABLE IF EXISTS `c_qh_wms_expense_warehousing_h_t`;
CREATE TABLE `c_qh_wms_expense_warehousing_h_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `listNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `receivingFactoryId` int(0) NULL DEFAULT NULL COMMENT '收货工厂id',
  `receivingLocationId` int(0) NULL DEFAULT NULL COMMENT '收货库位id',
  `departmentId` int(0) NULL DEFAULT NULL COMMENT '部门id',
  `projectId` int(0) NULL DEFAULT NULL COMMENT '项目id',
  `establishUserId` int(0) NULL DEFAULT NULL COMMENT '创建用户id',
  `establishDt` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `receivingUserId` int(0) NULL DEFAULT NULL COMMENT '收货用户id',
  `receivingDt` datetime(0) NULL DEFAULT NULL COMMENT '收货时间',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '状态',
  `dis` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备注',
  `viewMode` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '费用化入库 头表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_qh_wms_expense_warehousing_h_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_qh_wms_expense_warehousing_r_t
-- ----------------------------
DROP TABLE IF EXISTS `c_qh_wms_expense_warehousing_r_t`;
CREATE TABLE `c_qh_wms_expense_warehousing_r_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `listNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '单据号',
  `materialNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '物料id',
  `number` int(0) NULL DEFAULT NULL COMMENT '数量',
  `unit` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '单位',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '费用化入库 行表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_qh_wms_expense_warehousing_r_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_qh_wms_intra_library_transfer_d_t
-- ----------------------------
DROP TABLE IF EXISTS `c_qh_wms_intra_library_transfer_d_t`;
CREATE TABLE `c_qh_wms_intra_library_transfer_d_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `packingId` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '包装id',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '单件码',
  `packingNumber` int(0) NULL DEFAULT NULL COMMENT '包装数量',
  `number` int(0) NULL DEFAULT NULL COMMENT '单件数量',
  `rId` int(0) NULL DEFAULT NULL COMMENT '行id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '库内转移 详情表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_qh_wms_intra_library_transfer_d_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_qh_wms_intra_library_transfer_h_t
-- ----------------------------
DROP TABLE IF EXISTS `c_qh_wms_intra_library_transfer_h_t`;
CREATE TABLE `c_qh_wms_intra_library_transfer_h_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `listNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `sourceFactoryId` int(0) NULL DEFAULT NULL COMMENT '来源工厂ID',
  `sourceLocationId` int(0) NULL DEFAULT NULL COMMENT '来源库位ID',
  `receivingFactoryId` int(0) NULL DEFAULT NULL COMMENT '收货工厂ID',
  `receivingLocationId` int(0) NULL DEFAULT NULL COMMENT '收货库位ID',
  `establishUserId` int(0) NULL DEFAULT NULL COMMENT '创建用户ID',
  `establishDt` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `receivingUserId` int(0) NULL DEFAULT NULL COMMENT '收货用户ID',
  `receivingDt` datetime(0) NULL DEFAULT NULL COMMENT '收货时间',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '状态：新建、过账',
  `dis` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '备注',
  `viewMode` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '库内转移 头表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_qh_wms_intra_library_transfer_h_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_qh_wms_intra_library_transfer_r_t
-- ----------------------------
DROP TABLE IF EXISTS `c_qh_wms_intra_library_transfer_r_t`;
CREATE TABLE `c_qh_wms_intra_library_transfer_r_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `listNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '单据号',
  `materialNo` int(0) NULL DEFAULT NULL COMMENT '物料id',
  `number` int(0) NULL DEFAULT NULL COMMENT '数量',
  `unit` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '单位',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '库内转移 行表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_qh_wms_intra_library_transfer_r_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_qh_wms_inventory_d_t
-- ----------------------------
DROP TABLE IF EXISTS `c_qh_wms_inventory_d_t`;
CREATE TABLE `c_qh_wms_inventory_d_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `rId` int(0) NULL DEFAULT NULL COMMENT '行id',
  `packingId` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '包装id',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '单件码',
  `packingStockNumber` int(0) NULL DEFAULT NULL COMMENT '包装库存数量',
  `packingActualNumber` int(0) NULL DEFAULT NULL COMMENT '包装实际数量',
  `stockNumber` int(0) NULL DEFAULT NULL COMMENT '单件库存数量',
  `actualNumber` int(0) NULL DEFAULT NULL COMMENT '单件实际数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '盘点 详情表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_qh_wms_inventory_d_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_qh_wms_inventory_h_t
-- ----------------------------
DROP TABLE IF EXISTS `c_qh_wms_inventory_h_t`;
CREATE TABLE `c_qh_wms_inventory_h_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `listNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '类型',
  `factoryId` int(0) NULL DEFAULT NULL COMMENT '工厂ID',
  `locationId` int(0) NULL DEFAULT NULL COMMENT '库位ID',
  `establishUserId` int(0) NULL DEFAULT NULL COMMENT '创建用户ID',
  `establishDt` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `handleUserId` int(0) NULL DEFAULT NULL COMMENT '处理用户ID',
  `handleDt` datetime(0) NULL DEFAULT NULL COMMENT '处理时间',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '状态：新建（保存）、已提交审批（提交审批）、审批驳回（审批结果驳回）、已完成（审批通过直接触发、生成条码库存）',
  `dis` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '备注',
  `viewMode` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '盘点 头表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_qh_wms_inventory_h_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_qh_wms_inventory_r_t
-- ----------------------------
DROP TABLE IF EXISTS `c_qh_wms_inventory_r_t`;
CREATE TABLE `c_qh_wms_inventory_r_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `listNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '单据号',
  `materialNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '物料编号',
  `stockNumber` int(0) NULL DEFAULT NULL COMMENT '库存数量',
  `actualNumber` int(0) NULL DEFAULT NULL COMMENT '实际数量',
  `unit` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '单位',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '盘点 行表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_qh_wms_inventory_r_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_qh_wms_inventory_transaction_t
-- ----------------------------
DROP TABLE IF EXISTS `c_qh_wms_inventory_transaction_t`;
CREATE TABLE `c_qh_wms_inventory_transaction_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `dt` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '交易时间',
  `factoryId` int(0) NULL DEFAULT NULL COMMENT '工厂ID',
  `listNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '单据',
  `projectId` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '项目ID',
  `packageId` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '包装ID',
  `unitCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '单件码',
  `itemNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '物料号',
  `batchNumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '批次号',
  `number` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '数量',
  `sign` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '出入库标记',
  `transactionType` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '交易类型',
  `userId` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '操作员',
  `transactionCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '交易代码',
  `outLocationId` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '发出库位',
  `intLocationId` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '接收库位',
  `materialId` int(0) NULL DEFAULT NULL COMMENT '物料id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 205 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '库存交易表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_qh_wms_inventory_transaction_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_qh_wms_non_production_scrap_receipt_d_t
-- ----------------------------
DROP TABLE IF EXISTS `c_qh_wms_non_production_scrap_receipt_d_t`;
CREATE TABLE `c_qh_wms_non_production_scrap_receipt_d_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `packingId` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '包装id',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '单件码',
  `packingNumber` int(0) NULL DEFAULT NULL COMMENT '包装数量',
  `number` int(0) NULL DEFAULT NULL COMMENT '单件数量',
  `rId` int(0) NULL DEFAULT NULL COMMENT '行id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '非生产报废入库单 详情表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_qh_wms_non_production_scrap_receipt_d_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_qh_wms_non_production_scrap_receipt_h_t
-- ----------------------------
DROP TABLE IF EXISTS `c_qh_wms_non_production_scrap_receipt_h_t`;
CREATE TABLE `c_qh_wms_non_production_scrap_receipt_h_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `listNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '单号',
  `scrapType` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '报废类型：费用化报废、库存报废',
  `sourceFactoryId` int(0) NULL DEFAULT NULL COMMENT '来源工厂ID',
  `sourceLocationId` int(0) NULL DEFAULT NULL COMMENT '来源库位ID',
  `sourceDepartmentId` int(0) NULL DEFAULT NULL COMMENT '来源部门ID',
  `sourceProjectId` int(0) NULL DEFAULT NULL COMMENT '来源项目ID',
  `receivingFactoryId` int(0) NULL DEFAULT NULL COMMENT '收货工厂ID',
  `receivingLocationId` int(0) NULL DEFAULT NULL COMMENT '收货库位ID',
  `establishUserId` int(0) NULL DEFAULT NULL COMMENT '创建用户ID',
  `establishDt` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `receivingUserId` int(0) NULL DEFAULT NULL COMMENT '收货用户ID',
  `receivingDt` datetime(0) NULL DEFAULT NULL COMMENT '收货时间',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '状态：新建、过账',
  `dis` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '备注',
  `viewMode` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '非生产报废入库单 头表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_qh_wms_non_production_scrap_receipt_h_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_qh_wms_non_production_scrap_receipt_r_t
-- ----------------------------
DROP TABLE IF EXISTS `c_qh_wms_non_production_scrap_receipt_r_t`;
CREATE TABLE `c_qh_wms_non_production_scrap_receipt_r_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `listNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '单据号',
  `materialId` int(0) NULL DEFAULT NULL COMMENT '物料id',
  `number` int(0) NULL DEFAULT NULL COMMENT '数量',
  `unit` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '单位',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '非生产报废入库单 行表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_qh_wms_non_production_scrap_receipt_r_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_qh_wms_production_requisition_d_t
-- ----------------------------
DROP TABLE IF EXISTS `c_qh_wms_production_requisition_d_t`;
CREATE TABLE `c_qh_wms_production_requisition_d_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `rId` int(0) NULL DEFAULT NULL,
  `packingId` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '包装id',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '单件码',
  `packingNumber` int(0) NULL DEFAULT NULL COMMENT '包装数量',
  `number` int(0) NULL DEFAULT NULL COMMENT '单件数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '生产领料单详情表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_qh_wms_production_requisition_d_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_qh_wms_production_requisition_h_t
-- ----------------------------
DROP TABLE IF EXISTS `c_qh_wms_production_requisition_h_t`;
CREATE TABLE `c_qh_wms_production_requisition_h_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `dt` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建时间',
  `factoryId` int(0) NULL DEFAULT NULL COMMENT '工厂ID',
  `listNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '单据号',
  `workOrderNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '工单编号',
  `lineId` int(0) NULL DEFAULT NULL COMMENT '产线',
  `rockNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '料格',
  `stationId` int(0) NULL DEFAULT NULL COMMENT '工位',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '类型',
  `userId` int(0) NULL DEFAULT NULL COMMENT '创建人',
  `handleUserId` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '处理用户',
  `handleDt` datetime(0) NULL DEFAULT NULL COMMENT '处理时间',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '状态',
  `dis` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '备注',
  `viewMode` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '生产领用单抬头表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_qh_wms_production_requisition_h_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_qh_wms_production_requisition_r_t
-- ----------------------------
DROP TABLE IF EXISTS `c_qh_wms_production_requisition_r_t`;
CREATE TABLE `c_qh_wms_production_requisition_r_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `listNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '单据号',
  `materialNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '物料编码',
  `materialName` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '物料名称',
  `demandQuantity` int(0) NULL DEFAULT NULL COMMENT '需求数量',
  `issuedQuantity` int(0) NULL DEFAULT NULL COMMENT '实发数量',
  `unit` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '单位',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '生产领用单行表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_qh_wms_production_requisition_r_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_qh_wms_purchase_return_d_t
-- ----------------------------
DROP TABLE IF EXISTS `c_qh_wms_purchase_return_d_t`;
CREATE TABLE `c_qh_wms_purchase_return_d_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `rId` int(0) NULL DEFAULT NULL,
  `packingId` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `packingNumber` int(0) NULL DEFAULT NULL,
  `number` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '采购退货 详情表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_qh_wms_purchase_return_d_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_qh_wms_purchase_return_h_t
-- ----------------------------
DROP TABLE IF EXISTS `c_qh_wms_purchase_return_h_t`;
CREATE TABLE `c_qh_wms_purchase_return_h_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `listNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '单据号',
  `factoryId` int(0) NULL DEFAULT NULL COMMENT '工厂ID',
  `orderNumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '订单号',
  `locationId` int(0) NULL DEFAULT NULL COMMENT '库位ID',
  `establishUserId` int(0) NULL DEFAULT NULL COMMENT '创建人ID',
  `establishDt` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `handleUserId` int(0) NULL DEFAULT NULL COMMENT '处理人',
  `handleDt` datetime(0) NULL DEFAULT NULL COMMENT '处理时间',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态',
  `dis` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `viewMode` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '采购退货 头表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_qh_wms_purchase_return_h_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_qh_wms_purchase_return_r_t
-- ----------------------------
DROP TABLE IF EXISTS `c_qh_wms_purchase_return_r_t`;
CREATE TABLE `c_qh_wms_purchase_return_r_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `listNo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '单据号',
  `materialNo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '物料编号',
  `materialName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '物料名称',
  `demandQuantity` int(0) NULL DEFAULT NULL COMMENT '需求数量',
  `issuedQuantity` int(0) NULL DEFAULT NULL COMMENT '实发数量',
  `unit` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '单位',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '采购退货 行表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_qh_wms_purchase_return_r_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_qh_wms_sales_delivery_d_t
-- ----------------------------
DROP TABLE IF EXISTS `c_qh_wms_sales_delivery_d_t`;
CREATE TABLE `c_qh_wms_sales_delivery_d_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `rId` int(0) NULL DEFAULT NULL COMMENT '行ID',
  `packingId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '包装ID',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '单件码',
  `packingNumber` int(0) NULL DEFAULT NULL COMMENT '包装数量',
  `number` int(0) NULL DEFAULT NULL COMMENT '单件数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '销售出库 详情表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_qh_wms_sales_delivery_d_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_qh_wms_sales_delivery_h_t
-- ----------------------------
DROP TABLE IF EXISTS `c_qh_wms_sales_delivery_h_t`;
CREATE TABLE `c_qh_wms_sales_delivery_h_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `listNo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '单据号',
  `factoryId` int(0) NULL DEFAULT NULL COMMENT '工厂ID',
  `orderNumber` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '订单号',
  `locationId` int(0) NULL DEFAULT NULL COMMENT '库位ID',
  `establishUserId` int(0) NULL DEFAULT NULL COMMENT '创建人ID',
  `establishDt` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `handleUserId` int(0) NULL DEFAULT NULL COMMENT '处理人',
  `handleDt` datetime(0) NULL DEFAULT NULL COMMENT '处理时间',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态',
  `dis` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `viewMode` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '销售出库 头表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_qh_wms_sales_delivery_h_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_qh_wms_sales_delivery_r_t
-- ----------------------------
DROP TABLE IF EXISTS `c_qh_wms_sales_delivery_r_t`;
CREATE TABLE `c_qh_wms_sales_delivery_r_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `listNo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '单据号',
  `materialNo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '物料编号',
  `materialName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '物料名称',
  `demandQuantity` int(0) NULL DEFAULT NULL COMMENT '需求数量',
  `issuedQuantity` int(0) NULL DEFAULT NULL COMMENT '实发数量',
  `unit` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '单位',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '销售出库 行表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_qh_wms_sales_delivery_r_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_qh_wms_sales_return_receipt_d_t
-- ----------------------------
DROP TABLE IF EXISTS `c_qh_wms_sales_return_receipt_d_t`;
CREATE TABLE `c_qh_wms_sales_return_receipt_d_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `packingId` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '包装id',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '单件码',
  `packingNumber` int(0) NULL DEFAULT NULL COMMENT '包装数量',
  `number` int(0) NULL DEFAULT NULL COMMENT '单件数量',
  `rId` int(0) NULL DEFAULT NULL COMMENT '对应行表id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '销售退货入库单 详情表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_qh_wms_sales_return_receipt_d_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_qh_wms_sales_return_receipt_h_t
-- ----------------------------
DROP TABLE IF EXISTS `c_qh_wms_sales_return_receipt_h_t`;
CREATE TABLE `c_qh_wms_sales_return_receipt_h_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `listNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '单据号',
  `receivingFactoryId` int(0) NULL DEFAULT NULL COMMENT '收货工厂id',
  `receivingLocationId` int(0) NULL DEFAULT NULL COMMENT '收货库位id',
  `salesReturnListNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '销售退货单号',
  `customerId` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '客户id',
  `establishUserId` int(0) NULL DEFAULT NULL COMMENT '创建用户id',
  `establishDt` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `receivingUserId` int(0) NULL DEFAULT NULL COMMENT '收货用户id',
  `receivingDt` datetime(0) NULL DEFAULT NULL COMMENT '收货时间',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '状态',
  `dis` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备注',
  `viewMode` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '销售退货入库单 头表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_qh_wms_sales_return_receipt_h_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_qh_wms_sales_return_receipt_r_t
-- ----------------------------
DROP TABLE IF EXISTS `c_qh_wms_sales_return_receipt_r_t`;
CREATE TABLE `c_qh_wms_sales_return_receipt_r_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `materialNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '物料编号',
  `number` int(0) NULL DEFAULT NULL COMMENT '数量',
  `unit` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '单位',
  `listNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '销售退货入库单号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '销售退货入库单 行表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_qh_wms_sales_return_receipt_r_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_qh_wms_scrap_issue_d_t
-- ----------------------------
DROP TABLE IF EXISTS `c_qh_wms_scrap_issue_d_t`;
CREATE TABLE `c_qh_wms_scrap_issue_d_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `rId` int(0) NULL DEFAULT NULL,
  `packingId` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `packingNumber` int(0) NULL DEFAULT NULL,
  `number` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '报废出库 详情表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_qh_wms_scrap_issue_d_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_qh_wms_scrap_issue_h_t
-- ----------------------------
DROP TABLE IF EXISTS `c_qh_wms_scrap_issue_h_t`;
CREATE TABLE `c_qh_wms_scrap_issue_h_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `listNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '单据号',
  `factoryId` int(0) NULL DEFAULT NULL COMMENT '工厂ID',
  `locationId` int(0) NULL DEFAULT NULL COMMENT '库位ID',
  `establishUserId` int(0) NULL DEFAULT NULL COMMENT '创建人ID',
  `establishDt` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `handleUserId` int(0) NULL DEFAULT NULL COMMENT '处理人',
  `handleDt` datetime(0) NULL DEFAULT NULL COMMENT '处理时间',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态',
  `dis` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `viewMode` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '报废出库 头表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_qh_wms_scrap_issue_h_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_qh_wms_scrap_issue_r_t
-- ----------------------------
DROP TABLE IF EXISTS `c_qh_wms_scrap_issue_r_t`;
CREATE TABLE `c_qh_wms_scrap_issue_r_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `listNo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '单据号',
  `materialNo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '物料编号',
  `materialName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '物料名称',
  `quantity` int(0) NULL DEFAULT NULL COMMENT '数量',
  `unit` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '单位',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '报废出库 行表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_qh_wms_scrap_issue_r_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_qh_wms_stock_t
-- ----------------------------
DROP TABLE IF EXISTS `c_qh_wms_stock_t`;
CREATE TABLE `c_qh_wms_stock_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `dt` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `factoryId` int(0) NULL DEFAULT NULL COMMENT '工厂',
  `packageId` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '包装ID',
  `unitCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '单件码',
  `ItemNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '物料号',
  `batchNumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '批次号',
  `number` int(0) NULL DEFAULT NULL COMMENT '数量',
  `numberOfPackages` int(0) NULL DEFAULT NULL COMMENT '包装数',
  `locationId` int(0) NULL DEFAULT NULL COMMENT '库位id',
  `shelfNumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '货架号',
  `toBeInspectedNumber` int(0) NULL DEFAULT NULL COMMENT '待检数',
  `frozenNumber` int(0) NULL DEFAULT NULL COMMENT '质量冻结数',
  `pickingLockNumber` int(0) NULL DEFAULT NULL COMMENT '拣货锁定数',
  `shipmentLockingNumber` int(0) NULL DEFAULT NULL COMMENT '出货锁定数',
  `unit` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '单位',
  `supplierBatchNumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '供应商批次号',
  `dateOfManufacture` date NULL DEFAULT NULL COMMENT '生产日期',
  `expiryDate` date NULL DEFAULT NULL COMMENT '有效截止日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 120 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '库存表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_qh_wms_stock_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_sd_customer_basic info
-- ----------------------------
DROP TABLE IF EXISTS `c_sd_customer_basic info`;
CREATE TABLE `c_sd_customer_basic info`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `changeRecordNo` varchar(0) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户ID',
  `customerID` int(0) NULL DEFAULT NULL COMMENT '客户名称',
  `customerType` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户类型',
  `contactAddress` datetime(0) NULL DEFAULT NULL COMMENT '联系地址',
  `contactNumber` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `contacts` datetime(0) NULL DEFAULT NULL COMMENT '联系人',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '客户基础信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_sd_customer_basic info
-- ----------------------------

-- ----------------------------
-- Table structure for c_sd_customer_partner_info
-- ----------------------------
DROP TABLE IF EXISTS `c_sd_customer_partner_info`;
CREATE TABLE `c_sd_customer_partner_info`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `customerID` varchar(0) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户ID',
  `serialNumber` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '序号',
  `partnerType` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '伙伴类型',
  `partnerID` varchar(0) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '伙伴ID',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '客户伙伴信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_sd_customer_partner_info
-- ----------------------------

-- ----------------------------
-- Table structure for c_sd_quotation_change_record_h
-- ----------------------------
DROP TABLE IF EXISTS `c_sd_quotation_change_record_h`;
CREATE TABLE `c_sd_quotation_change_record_h`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `changeRecordNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '变更记录号',
  `customerID` int(0) NULL DEFAULT NULL COMMENT '客户ID',
  `applicant` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请人',
  `applicationTime` datetime(0) NULL DEFAULT NULL COMMENT '申请时间',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  `reasonForChange` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '变更原因',
  `companyCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司代码',
  `isDelete` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '(0:未删除，1已删除)',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 61 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '报价变更记录头表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_sd_quotation_change_record_h
-- ----------------------------

-- ----------------------------
-- Table structure for c_sd_quotation_change_record_r
-- ----------------------------
DROP TABLE IF EXISTS `c_sd_quotation_change_record_r`;
CREATE TABLE `c_sd_quotation_change_record_r`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `changeRecordNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '变更记录号',
  `lineNumber` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '行号',
  `materialCode` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料编码',
  `materialName` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料名称',
  `originalQuotation` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '原报价',
  `newOffer` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '新报价',
  `unit` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位',
  `effectiveDate` datetime(0) NULL DEFAULT NULL COMMENT '生效日期',
  `isDelete` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '(0:未删除，1已删除)',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '报价变更记录行表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_sd_quotation_change_record_r
-- ----------------------------

-- ----------------------------
-- Table structure for c_sd_quotation_record_h
-- ----------------------------
DROP TABLE IF EXISTS `c_sd_quotation_record_h`;
CREATE TABLE `c_sd_quotation_record_h`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `quotationRecordNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '报价记录号',
  `customerID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户ID',
  `creationTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `founder` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  `reviser` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `revisionTime` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `companyCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司代码',
  `isDelete` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '(0:未删除，1已删除)',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 131 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '报价记录头表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_sd_quotation_record_h
-- ----------------------------

-- ----------------------------
-- Table structure for c_sd_quotation_record_r
-- ----------------------------
DROP TABLE IF EXISTS `c_sd_quotation_record_r`;
CREATE TABLE `c_sd_quotation_record_r`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `quotationRecordNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '报价记录号',
  `lineNumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '行号',
  `materialCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料编码',
  `materialName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料名称',
  `costPrice` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '成本价',
  `offer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '报价',
  `priceUnit` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '价格单位',
  `effectiveDate` datetime(0) NULL DEFAULT NULL COMMENT '生效日期',
  `isDelete` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '(0:未删除，1已删除)',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 140 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '报价记录行表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_sd_quotation_record_r
-- ----------------------------

-- ----------------------------
-- Table structure for c_sd_sales_contract_h
-- ----------------------------
DROP TABLE IF EXISTS `c_sd_sales_contract_h`;
CREATE TABLE `c_sd_sales_contract_h`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `contractNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '合同号',
  `customerID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户ID',
  `founder` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `creationTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `status` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  `effectiveDate` datetime(0) NULL DEFAULT NULL COMMENT '生效日期',
  `reviser` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `revisionTime` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `companyCode` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司代码',
  `enclosure` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '附件',
  `isDelete` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '(0:未删除，1已删除)',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '销售合同头表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_sd_sales_contract_h
-- ----------------------------

-- ----------------------------
-- Table structure for c_sd_sales_contract_r
-- ----------------------------
DROP TABLE IF EXISTS `c_sd_sales_contract_r`;
CREATE TABLE `c_sd_sales_contract_r`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `contractNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '合同号',
  `lineNumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '行号',
  `materialCode` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料编码',
  `materialName` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料名称',
  `moldAmortization` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模具摊销费用',
  `amortizationAmount` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '摊销数量',
  `amortizationUnitPrice` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '摊销单价',
  `productPrice` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品价格',
  `demandQuantity` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '需求数量',
  `quantityDelivered` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '已交货数量',
  `enclosure` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '附件',
  `isDelete` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '(0:未删除，1已删除)',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '销售合同行表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_sd_sales_contract_r
-- ----------------------------

-- ----------------------------
-- Table structure for c_sd_sales_order_h
-- ----------------------------
DROP TABLE IF EXISTS `c_sd_sales_order_h`;
CREATE TABLE `c_sd_sales_order_h`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `orderNumber` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单号',
  `OrderType` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单类型',
  `contractNo` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '合同号',
  `customerNumber` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户号',
  `deliveryParty` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '送达方',
  `payer` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '付款方',
  `founder` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `creationTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `status` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  `customerPONumber` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户PO号',
  `reviser` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `revisionTime` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `salesOrganization` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '销售组织',
  `companyCode` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司代码',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '销售订单头表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_sd_sales_order_h
-- ----------------------------

-- ----------------------------
-- Table structure for c_sd_sales_order_r
-- ----------------------------
DROP TABLE IF EXISTS `c_sd_sales_order_r`;
CREATE TABLE `c_sd_sales_order_r`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `orderNumber` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单号',
  `lineNumber` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '行号',
  `materialCode` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料编码',
  `versionNumber` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '版本号',
  `quantity` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数量',
  `priceExcludingTax` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '不含税价',
  `taxRate` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '税率',
  `plannedDeliveryDate` datetime(0) NULL DEFAULT NULL COMMENT '计划交货日期',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '销售订单行表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_sd_sales_order_r
-- ----------------------------

-- ----------------------------
-- Table structure for c_sd_sales_organization_t
-- ----------------------------
DROP TABLE IF EXISTS `c_sd_sales_organization_t`;
CREATE TABLE `c_sd_sales_organization_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `company` varchar(0) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司',
  `salesOrganization` int(0) NULL DEFAULT NULL COMMENT '销售组织',
  `organizationName` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组织名称',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '销售组织表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_sd_sales_organization_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_sd_shipping_plan_h
-- ----------------------------
DROP TABLE IF EXISTS `c_sd_shipping_plan_h`;
CREATE TABLE `c_sd_shipping_plan_h`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `planNumber` varchar(0) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '计划单号',
  `customerID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户ID',
  `founder` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `creationTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `revisionTime` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `reviser` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `deliveryFactory` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出货工厂',
  `shippingDate` datetime(0) NULL DEFAULT NULL COMMENT '出货日期',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '出货计划头表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_sd_shipping_plan_h
-- ----------------------------

-- ----------------------------
-- Table structure for c_sd_shipping_plan_r
-- ----------------------------
DROP TABLE IF EXISTS `c_sd_shipping_plan_r`;
CREATE TABLE `c_sd_shipping_plan_r`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `planNumber` varchar(0) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '计划单号',
  `lineNumber` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '行号',
  `orderNumber` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单号',
  `materialCode` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料编码',
  `quantity` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数量',
  `shippingLocation` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出货库位',
  `customerReceivingPoint` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户收货点',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '出货计划行表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_sd_shipping_plan_r
-- ----------------------------

-- ----------------------------
-- Table structure for c_srm_accessory
-- ----------------------------
DROP TABLE IF EXISTS `c_srm_accessory`;
CREATE TABLE `c_srm_accessory`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `the_attachment_describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '附件描述',
  `attachment_uploading` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '附件上传',
  `is_delete` bit(1) NULL DEFAULT b'0' COMMENT '逻辑删除(0.未删除1.已删除)',
  `supplier_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '供应商代码',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `company_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司编码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '附件信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_srm_accessory
-- ----------------------------

-- ----------------------------
-- Table structure for c_srm_account
-- ----------------------------
DROP TABLE IF EXISTS `c_srm_account`;
CREATE TABLE `c_srm_account`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '账号信息id',
  `account_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `belong_to_company` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属公司',
  `department` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门',
  `post` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '岗位',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系邮箱',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `company_id` int(0) NULL DEFAULT NULL COMMENT '公司id',
  `supplier_id` int(0) NULL DEFAULT NULL COMMENT '供应商id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '账号信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_srm_account
-- ----------------------------

-- ----------------------------
-- Table structure for c_srm_alteration
-- ----------------------------
DROP TABLE IF EXISTS `c_srm_alteration`;
CREATE TABLE `c_srm_alteration`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '变更记录id',
  `changing_object_types` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '变更对象类型',
  `changing_object_values` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '变更对象值',
  `property_changes` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '变更属性',
  `the_old_attribute_values` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '属性旧值',
  `the_new_attribute_values` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '属性新值',
  `change_a_person` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '变更人',
  `alteration_time` datetime(0) NULL DEFAULT NULL COMMENT '变更时间',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `supplier_id` int(0) NULL DEFAULT NULL COMMENT '供应商id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '变更记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_srm_alteration
-- ----------------------------

-- ----------------------------
-- Table structure for c_srm_assess_record_h
-- ----------------------------
DROP TABLE IF EXISTS `c_srm_assess_record_h`;
CREATE TABLE `c_srm_assess_record_h`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '评估档案头id',
  `file_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '档案编号',
  `file_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '档案名称',
  `evaluation_start_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评估起始时间/考评时间从',
  `evaluation_cutoff_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评估截止时间/考评时间至',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态(1.新建2.评分中3.待发布4.已完成)',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间/建档时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `is_delete` bit(1) NULL DEFAULT b'0' COMMENT '逻辑删除(0.未删除1.已删除)',
  `file_description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '档案描述',
  `evaluation_template` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '考评模板',
  `company_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '考评公司',
  `evaluation_period` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '考评周期(1.月度2.季度3.半年度4.年度)',
  `appraisal_way` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '考评方式',
  `appraisal_leader` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '考评负责人',
  `evaluation_rule_explain` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '考评规则说明(不使用)',
  `evaluation_explain` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '考评说明',
  `supplier_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '考评对象',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `file_number`(`file_number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '评估档案头表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_srm_assess_record_h
-- ----------------------------

-- ----------------------------
-- Table structure for c_srm_assess_record_r
-- ----------------------------
DROP TABLE IF EXISTS `c_srm_assess_record_r`;
CREATE TABLE `c_srm_assess_record_r`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '评估档案行id',
  `file_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '档案编号',
  `index` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '指标',
  `index_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '指标值',
  `supplier_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '供应商代码',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `assess_id` int(0) NULL DEFAULT NULL COMMENT '评估档案头id',
  `is_delete` bit(1) NULL DEFAULT b'0' COMMENT '逻辑删除(0.未删除1.已删除)',
  `scoring_items` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评分项',
  `score` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分值',
  `grading_staff` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评分人员',
  `line_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '行号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '评估档案行表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_srm_assess_record_r
-- ----------------------------

-- ----------------------------
-- Table structure for c_srm_assess_template_h
-- ----------------------------
DROP TABLE IF EXISTS `c_srm_assess_template_h`;
CREATE TABLE `c_srm_assess_template_h`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '评估模板头id',
  `template_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模板编号',
  `template_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模板名称',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型(1.供应商考核2.供应商升级评审)',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态(1.新建2.已发布3.发布失败)',
  `version` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '版本',
  `adapt_the_vendor_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '适配供应商代码',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `is_delete` bit(1) NULL DEFAULT b'0' COMMENT '逻辑删除(0.未删除1.已删除)',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `template_code`(`template_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '评估模板头表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_srm_assess_template_h
-- ----------------------------

-- ----------------------------
-- Table structure for c_srm_assess_template_r
-- ----------------------------
DROP TABLE IF EXISTS `c_srm_assess_template_r`;
CREATE TABLE `c_srm_assess_template_r`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '评估模板行id',
  `template_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模板编号',
  `version` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '版本',
  `index_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '指标编码',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `template_id` int(0) NULL DEFAULT NULL COMMENT '评估模板头id',
  `weight_calculation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权重式计算(1.未勾选，2.已勾选)',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态(1.解锁，2.禁用)',
  `priority` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '优先级',
  `is_delete` bit(1) NULL DEFAULT b'0' COMMENT '逻辑删除(0.未删除1.已删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '评估模板行表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_srm_assess_template_r
-- ----------------------------

-- ----------------------------
-- Table structure for c_srm_bank
-- ----------------------------
DROP TABLE IF EXISTS `c_srm_bank`;
CREATE TABLE `c_srm_bank`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '供应商银行信息id',
  `bank_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开户银行名称',
  `bank_account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '银行账号',
  `supplier_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '供应商代码',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `supplier_id` int(0) NULL DEFAULT NULL COMMENT '供应商id',
  `is_delete` bit(1) NULL DEFAULT b'0' COMMENT '逻辑删除(0.未删除1.已删除)',
  `account_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账户名称',
  `receiver_mailbox` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收票人邮箱',
  `collector_telephone_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收票人电话',
  `company_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司编码',
  `tax_file_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '税号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 827 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '供应商银行信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_srm_bank
-- ----------------------------

-- ----------------------------
-- Table structure for c_srm_bank_change_record
-- ----------------------------
DROP TABLE IF EXISTS `c_srm_bank_change_record`;
CREATE TABLE `c_srm_bank_change_record`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '供应商银行信息id',
  `bank_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开户银行名称',
  `bank_account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '银行账号',
  `supplier_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '供应商代码',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态(0.已保存1.变更中2.已变更3.变更失败)',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `supplier_id` int(0) NULL DEFAULT NULL COMMENT '供应商id',
  `is_delete` bit(1) NULL DEFAULT b'0' COMMENT '逻辑删除(0.未删除1.已删除)',
  `account_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账户名称',
  `receiver_mailbox` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收票人邮箱',
  `collector_telephone_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收票人电话',
  `company_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司编码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '供应商银行信息变更记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_srm_bank_change_record
-- ----------------------------

-- ----------------------------
-- Table structure for c_srm_company
-- ----------------------------
DROP TABLE IF EXISTS `c_srm_company`;
CREATE TABLE `c_srm_company`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '公司信息id',
  `company_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司代码--不使用',
  `supplier_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '供应商代码',
  `company_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司名称--不使用',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `supplier_id` int(0) NULL DEFAULT NULL COMMENT '供应商id',
  `is_delete` bit(1) NULL DEFAULT b'0' COMMENT '逻辑删除(0.未删除1.已删除)',
  `unify_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '统一社会信用代码',
  `enterprise_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业类型(1.国有企业 2.集体所有制企业 3.私营企 4.股份制企业 5.有限合伙企业 6.联营企业 7.外商投资企业 8.个人独资企业 9.港澳台企业 10.股份合作企业)',
  `registered_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注册地址',
  `particular_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详细地址',
  `legal_representative` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '法定代表人',
  `registered_capital` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注册资本',
  `register_date` date NULL DEFAULT NULL COMMENT '成立日期',
  `upload_of_business_license` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '营业执照上传',
  `is_auth` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '是否已认证(1未认证.2.已认证3.认证中)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 827 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '公司信息表--供应商信息附属表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_srm_company
-- ----------------------------

-- ----------------------------
-- Table structure for c_srm_contract_affiliate_r
-- ----------------------------
DROP TABLE IF EXISTS `c_srm_contract_affiliate_r`;
CREATE TABLE `c_srm_contract_affiliate_r`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '合同附属表id',
  `contract_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '合同编号',
  `first_party` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '甲方',
  `second_party` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '乙方',
  `object_of_contract_or_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '合同条款对象及对象值',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `contract_id` int(0) NULL DEFAULT NULL COMMENT '合同头表id',
  `code_of_business_terms` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务条款编码',
  `business_box_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务条框名称',
  `content_of_business_terms` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务条款内容',
  `statement_of_business_terms` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务条款说明',
  `line_item_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '行项目号',
  `is_delete` bit(1) NULL DEFAULT b'0' COMMENT '逻辑删除(0.未删除1.已删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '合同附属表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_srm_contract_affiliate_r
-- ----------------------------

-- ----------------------------
-- Table structure for c_srm_contract_h
-- ----------------------------
DROP TABLE IF EXISTS `c_srm_contract_h`;
CREATE TABLE `c_srm_contract_h`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '合同头表id',
  `contract_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '合同编号',
  `contract_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '合同名称',
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `contract_character` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '合同性质(1.普通合同2.附件合同)',
  `company_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司代码',
  `supplier_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '供应商代码',
  `effective_date_of_contract` datetime(0) NULL DEFAULT NULL COMMENT '合同生效日期',
  `date_of_termination` datetime(0) NULL DEFAULT NULL COMMENT '合同终止日期',
  `using_template_numbers` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '使用模板编号',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态(1.新建2.待审批3.待签署4.待存档5.已存档)',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `is_delete` bit(1) NULL DEFAULT b'0' COMMENT '逻辑删除(0:未删除、1:已删除)',
  `company_id` int(0) NULL DEFAULT NULL COMMENT '公司id',
  `supplier_id` int(0) NULL DEFAULT NULL COMMENT '供应商id',
  `buyer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '采购员',
  `master_contract` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主合同',
  `source_of_the_contract` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '合同来源(1.手动创建2.采购申请转换3.寻源结果引用)',
  `contract_rental` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '合同总额',
  `purchasing_organization` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '采购组织',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `contract_no`(`contract_no`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '合同头表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_srm_contract_h
-- ----------------------------

-- ----------------------------
-- Table structure for c_srm_contract_r
-- ----------------------------
DROP TABLE IF EXISTS `c_srm_contract_r`;
CREATE TABLE `c_srm_contract_r`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '合同行表id',
  `contract_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '合同编号',
  `line_item_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '行项目号',
  `material_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料编码',
  `count` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数量',
  `unit` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位',
  `currency` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '币种',
  `tax_rate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '税率',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `contract_id` int(0) NULL DEFAULT NULL COMMENT '合同头表id',
  `material_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料名称',
  `material_classification` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料分类',
  `specifications` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '规格',
  `model` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '型号',
  `tax_category` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '税种',
  `tax_amount` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '税额',
  `unit_price_of_taxIn_original_currency` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '原币种税单价',
  `unit_price_excluding_tax` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '不含税单价',
  `amountIncluding_tax` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '含税价金额',
  `amount_excluding_tax` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '不含税价金额',
  `payment_date` datetime(0) NULL DEFAULT NULL COMMENT '支付日期',
  `attribute` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '属性',
  `buyer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '采购员',
  `custodian` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '保管人',
  `acceptance_person` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '验收人',
  `cost_bearing_department` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '费用承担部门',
  `place` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地点',
  `project_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目编号',
  `entr_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目名称',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `source_document_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '来源单据编号',
  `source_document_line_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '来源单据行号',
  `is_delete` bit(1) NULL DEFAULT b'0' COMMENT '逻辑删除(0.未删除1.已删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '合同行表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_srm_contract_r
-- ----------------------------

-- ----------------------------
-- Table structure for c_srm_contract_template
-- ----------------------------
DROP TABLE IF EXISTS `c_srm_contract_template`;
CREATE TABLE `c_srm_contract_template`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '合同模板记录id',
  `template_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模板编号',
  `template_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '合同模板名称',
  `adapter_company` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分配适用公司',
  `form_work_accessory` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模板文件',
  `is_start` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否启用(1.禁用2.启用)',
  `template_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '合同类型(1.普通合同)',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `is_delete` bit(1) NULL DEFAULT b'0' COMMENT '逻辑删除(0.未删除1.已删除)',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `template_number`(`template_number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '合同模板记录头表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_srm_contract_template
-- ----------------------------

-- ----------------------------
-- Table structure for c_srm_enquiry_for_bids_t_supplier
-- ----------------------------
DROP TABLE IF EXISTS `c_srm_enquiry_for_bids_t_supplier`;
CREATE TABLE `c_srm_enquiry_for_bids_t_supplier`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '询价/招标供应商id',
  `rfq_or_tender_form_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '询价/招标单编码',
  `line_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '行号',
  `supplier_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '供应商编码',
  `material_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料编码',
  `material_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料名称',
  `whether_offer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '2' COMMENT '是否报价(1.已报价2.未报价)',
  `offer_er` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '报价人(供应商报价)',
  `offer_time` datetime(0) NULL DEFAULT NULL COMMENT '报价时间(供应商报价)',
  `offer_period_of_validity` datetime(0) NULL DEFAULT NULL COMMENT '报价有效期(供应商报价)',
  `unit_price` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单价(供应商报价)',
  `freight_or_not` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否运输费(1.是2.否)',
  `minimum_order` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最小采购量(供应商报价)',
  `minimum_packing_quantity` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最小包装量(供应商报价)',
  `estimated_time_of_delivery` datetime(0) NULL DEFAULT NULL COMMENT '预计交货时间(供应商报价)',
  `quantity_allotted` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分配数量(定标\\核价时填写)',
  `choose_reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '选用理由(定标\\核价时填写)',
  `scorer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评分人/评分专家(专家评分)',
  `marking_time` datetime(0) NULL DEFAULT NULL COMMENT '评分时间(专家评分)',
  `score_elements_and_scores` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评分要素(专家评分)',
  `score` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分值(专家评分)',
  `whether_to_recommend` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否推荐(1.是2.否)(定标\\核价时填写)',
  `recommendations` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '推荐意见',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `supplier_id` int(0) NULL DEFAULT NULL COMMENT '供应商id',
  `company_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司代码',
  `is_delete` bit(1) NULL DEFAULT b'0' COMMENT '逻辑删除(0.未删除1.已删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '询价/招标供应商表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_srm_enquiry_for_bids_t_supplier
-- ----------------------------

-- ----------------------------
-- Table structure for c_srm_enquiry_invitation_for_bids_t_h
-- ----------------------------
DROP TABLE IF EXISTS `c_srm_enquiry_invitation_for_bids_t_h`;
CREATE TABLE `c_srm_enquiry_invitation_for_bids_t_h`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '询价/招标头id',
  `rfq_or_tender_form_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '询价/招标单编码',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题/招标事项',
  `template_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模板编号',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态(1.询价新建2.询价报价中3.询价待核价4.询价待审批5.投标新建6.投标审批中7.投标中8.待开标9.评分10. 待定标11.已完成)',
  `sourcing_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '寻源类型(1.询价2.招标)',
  `way_of_quoting` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '报价方式(1.线上报价2.线下报价)',
  `way_to_find_the_source` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '寻源方式(1.邀请2.公开)',
  `currency` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '币种(1.CNY2.USD)',
  `quotation_start_time` datetime(0) NULL DEFAULT NULL COMMENT '报价/投标开始时间',
  `quotation_stop_time` datetime(0) NULL DEFAULT NULL COMMENT '报价/投标截止时间',
  `bid_opening_place` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开标地点',
  `bid_opening_time` datetime(0) NULL DEFAULT NULL COMMENT '开标时间',
  `principal` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '负责人',
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `template_id` int(0) NULL DEFAULT NULL COMMENT '招标评分模板行id',
  `company_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司代码',
  `supplier_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '供应商代码',
  `update_explain` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '变更说明',
  `is_delete` bit(1) NULL DEFAULT b'0' COMMENT '逻辑删除(0.未删除1.已删除)',
  `purchasing_organization` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '采购组织名称',
  `clarification_deadline` datetime(0) NULL DEFAULT NULL COMMENT '澄清截止时间',
  `buyer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '采购员',
  `the_bid_opening_people` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开标人',
  `selection_of_people` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '定标人',
  `bidding_technical_documents` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '招标技术文件',
  `bidding_business_documents` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '招标商务文件',
  `budget` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预算金额',
  `item_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目编号',
  `item_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目名称',
  `item_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目地址',
  `exchange_rate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '汇率',
  `payment_method` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '付款方式(1.线上支付2.线下支付)',
  `terms_of_payment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '付款条款',
  `purchasing_contact` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '采购联系人',
  `telephone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人电话',
  `contact_email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人邮箱',
  `examine_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'OA审批单号(关联OA审批)',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `rfq_or_tender_form_code`(`rfq_or_tender_form_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '询价/招标头表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_srm_enquiry_invitation_for_bids_t_h
-- ----------------------------

-- ----------------------------
-- Table structure for c_srm_enquiry_invitation_for_bids_t_r
-- ----------------------------
DROP TABLE IF EXISTS `c_srm_enquiry_invitation_for_bids_t_r`;
CREATE TABLE `c_srm_enquiry_invitation_for_bids_t_r`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '询价/招标行id',
  `rfq_or_tender_form_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '询价/招标单编码',
  `line_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '行号',
  `company_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司代码',
  `inventory_organization` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '库存组织(工厂)',
  `material_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料编码(支持无编码情况)',
  `material_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料名称',
  `quantity_required` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '需求数量',
  `unit` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位',
  `unit_price` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单价',
  `demanded_date` date NULL DEFAULT NULL COMMENT '需求日期',
  `drawing_figure_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图纸图号',
  `purchase_request` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '采购申请单号',
  `accessory` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '附件',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `enquiry_id` int(0) NULL DEFAULT NULL COMMENT '询价/招标头id',
  `is_delete` bit(1) NULL DEFAULT b'0' COMMENT '逻辑删除(0.未删除1.已删除)',
  `business_entity` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务实体',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 477 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '询价/招标行表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_srm_enquiry_invitation_for_bids_t_r
-- ----------------------------

-- ----------------------------
-- Table structure for c_srm_file_uploading
-- ----------------------------
DROP TABLE IF EXISTS `c_srm_file_uploading`;
CREATE TABLE `c_srm_file_uploading`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `file_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '文件路径',
  `file_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '文件名',
  `flag` int(0) NULL DEFAULT NULL COMMENT '标记(1.采购文件,2供应商文件)',
  `order_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '订单号',
  `line_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '行号',
  `is_delete` bit(1) NULL DEFAULT b'0' COMMENT '逻辑删除(0.未删除1.已删除)',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `supplier_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '供应商代码',
  `only_pur_show` int(0) NULL DEFAULT 0 COMMENT '仅采购可看权限( 0.不可见、1.可见)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 43 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_srm_file_uploading
-- ----------------------------

-- ----------------------------
-- Table structure for c_srm_finance
-- ----------------------------
DROP TABLE IF EXISTS `c_srm_finance`;
CREATE TABLE `c_srm_finance`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `year` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '年度',
  `total_assets` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '总资产(万元)',
  `gross_liability` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '总负债(万元)',
  `current_assets` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流动资产(万元)',
  `current_liabilities` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流动负债(万元)',
  `operating_receipt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '营业收入(万元)',
  `retained_profits` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '净利润(万元)',
  `is_delete` bit(1) NULL DEFAULT b'0' COMMENT '逻辑删除(0.未删除1.已删除)',
  `supplier_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '供应商代码',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `company_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司编码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '财务信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_srm_finance
-- ----------------------------

-- ----------------------------
-- Table structure for c_srm_finance_change_record
-- ----------------------------
DROP TABLE IF EXISTS `c_srm_finance_change_record`;
CREATE TABLE `c_srm_finance_change_record`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `year` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '年度',
  `total_assets` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '总资产(万元)',
  `gross_liability` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '总负债(万元)',
  `current_assets` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流动资产(万元)',
  `current_liabilities` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流动负债(万元)',
  `operating_receipt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '营业收入(万元)',
  `retained_profits` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '净利润(万元)',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态(0.已保存1.变更中2.已变更3.变更失败)',
  `is_delete` bit(1) NULL DEFAULT b'0' COMMENT '逻辑删除(0.未删除1.已删除)',
  `supplier_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '供应商代码',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `company_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司编码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '财务信息变更记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_srm_finance_change_record
-- ----------------------------

-- ----------------------------
-- Table structure for c_srm_indicators_dimension
-- ----------------------------
DROP TABLE IF EXISTS `c_srm_indicators_dimension`;
CREATE TABLE `c_srm_indicators_dimension`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '指标维度id',
  `index_coding` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '指标编码',
  `index_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '指标名称',
  `score_is` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评分方式(1.专家评分2.系统评分)',
  `pointer_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '指标类型(1.专家评分2.系统评分)',
  `score_start` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分值起始值',
  `score_stop` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分值截止值',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `template_id` int(0) NULL DEFAULT NULL COMMENT '评估模板头表id',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态(1.启用2.禁用)',
  `data_source` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据来源',
  `is_delete` bit(1) NULL DEFAULT b'0' COMMENT '逻辑删除(0.未删除1.已删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '指标维度表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_srm_indicators_dimension
-- ----------------------------

-- ----------------------------
-- Table structure for c_srm_invitation_for_bids_score_t_h
-- ----------------------------
DROP TABLE IF EXISTS `c_srm_invitation_for_bids_score_t_h`;
CREATE TABLE `c_srm_invitation_for_bids_score_t_h`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '招标评分模板头id',
  `template_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模板编号',
  `template_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模板名称',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型(1.专家评分2.系统评分)',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态(1.已禁用2.已启用)',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `is_delete` bit(1) NULL DEFAULT b'0' COMMENT '逻辑删除(0.未删除1.已删除)',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `template_number`(`template_number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '招标评分模板头表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_srm_invitation_for_bids_score_t_h
-- ----------------------------

-- ----------------------------
-- Table structure for c_srm_invitation_for_bids_score_t_r
-- ----------------------------
DROP TABLE IF EXISTS `c_srm_invitation_for_bids_score_t_r`;
CREATE TABLE `c_srm_invitation_for_bids_score_t_r`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '招标评分模板行id',
  `template_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模板编号',
  `scoring_elements` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评分要素',
  `weight` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权重',
  `index` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '指标',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `template_id` int(0) NULL DEFAULT NULL COMMENT '评估模板头表id',
  `is_delete` bit(1) NULL DEFAULT b'0' COMMENT '逻辑删除(0.未删除1.已删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '招标评分模板行表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_srm_invitation_for_bids_score_t_r
-- ----------------------------

-- ----------------------------
-- Table structure for c_srm_invoice_receivable
-- ----------------------------
DROP TABLE IF EXISTS `c_srm_invoice_receivable`;
CREATE TABLE `c_srm_invoice_receivable`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '应收发票表id',
  `invoice_receivable_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '应收发票编号',
  `date_of_issue` datetime(0) NULL DEFAULT NULL COMMENT '开票日期',
  `tax_invoice_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '税务发票代码',
  `invoice_gross_amount` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发票总额',
  `the_invoice_amount` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发票税额',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态(1.新建2.待审核3.待付款4.待收款5.已完成)',
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `auditor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审核人',
  `audit_time` datetime(0) NULL DEFAULT NULL COMMENT '审核时间',
  `payer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '付款人',
  `pay_time` datetime(0) NULL DEFAULT NULL COMMENT '付款时间',
  `confirmation_of_receipt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收款确认人',
  `acknowledging_time` datetime(0) NULL DEFAULT NULL COMMENT '确认时间',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `the_number_id` int(0) NULL DEFAULT NULL COMMENT '开票申请表id',
  `is_delete` bit(1) NULL DEFAULT b'0' COMMENT '逻辑删除(0.未删除1.已删除)',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `supplier_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '供应商编码',
  `supplier_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '供应商名称',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '供应商地址',
  `tax_money_count_money` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '含税总额(系统)',
  `sum_tax` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '总税额(系统)',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `invoice_receivable_no`(`invoice_receivable_no`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '应收发票表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_srm_invoice_receivable
-- ----------------------------

-- ----------------------------
-- Table structure for c_srm_invoice_receivable_r
-- ----------------------------
DROP TABLE IF EXISTS `c_srm_invoice_receivable_r`;
CREATE TABLE `c_srm_invoice_receivable_r`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '应收发票行表id',
  `invoice_receivable_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '应收发票编号',
  `line_item_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '行号',
  `the_number_of_he_invoice_application` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开票申请单号',
  `material_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料编码',
  `material_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料名称',
  `common_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '通用名',
  `supplier_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '供应商料号',
  `supplier_number_describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '供应商料号描述',
  `rules_of_the_model` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '规则型号',
  `unit` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位',
  `count` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '可开票数量',
  `no_unit_price` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '不含税单价',
  `no_tax_money_money` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '不含税金额',
  `tax_rate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '税率(%)',
  `tax` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '税额',
  `unit_price` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '含税单价',
  `unit_money` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '含税金额',
  `row_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '行备注',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `order_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '采购订单号',
  `is_delete` bit(1) NULL DEFAULT b'0' COMMENT '逻辑删除(0.未删除1.已删除)',
  `delivery_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '送货单号',
  `paid_in_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '实收数(可开票数量)',
  `no_tax_money` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '不含税金额',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '应收/应付发票行表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_srm_invoice_receivable_r
-- ----------------------------

-- ----------------------------
-- Table structure for c_srm_k_three_purchase_abutting
-- ----------------------------
DROP TABLE IF EXISTS `c_srm_k_three_purchase_abutting`;
CREATE TABLE `c_srm_k_three_purchase_abutting`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `do_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单据号',
  `do_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单据类型((1.采购申请、2.采购订单、3.送货单))',
  `alter_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '变更类型(1.创建、2.修改、3.删除、4.入库(送货单))',
  `alter_status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '变更状态(1.待处理、2.已变更)',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `is_delete` bit(1) NULL DEFAULT b'0' COMMENT '逻辑删除(0.未删除1.已删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 305 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'K3采购对接日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_srm_k_three_purchase_abutting
-- ----------------------------

-- ----------------------------
-- Table structure for c_srm_linkman
-- ----------------------------
DROP TABLE IF EXISTS `c_srm_linkman`;
CREATE TABLE `c_srm_linkman`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别(1.男2.女)',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `department` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门',
  `position` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职位',
  `supplier_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '供应商代码',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `is_delete` bit(1) NULL DEFAULT b'0' COMMENT '逻辑删除(0.未删除1.已删除)',
  `company_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司编码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 448 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '联系人信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_srm_linkman
-- ----------------------------

-- ----------------------------
-- Table structure for c_srm_linkman_change_record
-- ----------------------------
DROP TABLE IF EXISTS `c_srm_linkman_change_record`;
CREATE TABLE `c_srm_linkman_change_record`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别(1.男2.女)',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `department` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门',
  `position` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职位',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态(0.已保存1.变更中2.已变更3.变更失败)',
  `supplier_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '供应商代码',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `is_delete` bit(1) NULL DEFAULT b'0' COMMENT '逻辑删除(0.未删除1.已删除)',
  `company_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司编码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '联系人变更记录信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_srm_linkman_change_record
-- ----------------------------

-- ----------------------------
-- Table structure for c_srm_log_t
-- ----------------------------
DROP TABLE IF EXISTS `c_srm_log_t`;
CREATE TABLE `c_srm_log_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'CRM日志主键',
  `dt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作时间',
  `user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作用户',
  `menu_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 93 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_srm_log_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_srm_product
-- ----------------------------
DROP TABLE IF EXISTS `c_srm_product`;
CREATE TABLE `c_srm_product`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '供应商产品信息id',
  `supplier_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '供应商代码',
  `product_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品名称',
  `product_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品类别(1.中草药材、2.棉花)',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `supplier_id` int(0) NULL DEFAULT NULL COMMENT '供应商id',
  `is_delete` bit(1) NULL DEFAULT b'0' COMMENT '逻辑删除(0.未删除1.已删除)',
  `business_nature` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '经营性质(1.制造商 2.贸易商 3.服务商)',
  `products_or_services` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品/服务',
  `client` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户',
  `company_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司编码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '供应商产品信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_srm_product
-- ----------------------------

-- ----------------------------
-- Table structure for c_srm_pur_partner_info_r
-- ----------------------------
DROP TABLE IF EXISTS `c_srm_pur_partner_info_r`;
CREATE TABLE `c_srm_pur_partner_info_r`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `partner_type_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '伙伴类型名称',
  `partner_type_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '伙伴类型编码',
  `company_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司编码',
  `corporate_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司名称',
  `representative` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '代表人',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `contact_information` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系方式',
  `mailbox` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `name_of_bank_of_deposit` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开户行名称',
  `bank_account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '银行账号',
  `explain` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '说明',
  `contract_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '合同编号',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `line_item_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '行项目号',
  `is_delete` bit(1) NULL DEFAULT b'0' COMMENT '逻辑删除(0.未删除1.已删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '添加采购合作伙伴信息行表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_srm_pur_partner_info_r
-- ----------------------------

-- ----------------------------
-- Table structure for c_srm_purchase_demand_h
-- ----------------------------
DROP TABLE IF EXISTS `c_srm_purchase_demand_h`;
CREATE TABLE `c_srm_purchase_demand_h` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '采购需求头id',
  `request_code` varchar(255) DEFAULT NULL COMMENT '申请单号',
  `status` varchar(255) DEFAULT '4' COMMENT '状态(1.新建2.审批中3.待分配4.已分配5.已删除6.已询价7.部分转单8.全部转单9.无需转单)',
  `proposer` varchar(255) DEFAULT NULL COMMENT '申请人',
  `application_date` date DEFAULT NULL COMMENT '申请日期',
  `project_code` varchar(255) DEFAULT NULL COMMENT '项目号',
  `project_name` varchar(255) DEFAULT NULL COMMENT '项目名称',
  `apply_for_department` varchar(255) DEFAULT NULL COMMENT '申请部门',
  `buyer` varchar(255) DEFAULT NULL COMMENT '采购员',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `supplier_id` int(11) DEFAULT NULL COMMENT '供应商id',
  `is_delete` bit(1) DEFAULT b'0' COMMENT '逻辑删除(0.未删除1.已删除)',
  `buyer_type` varchar(255) DEFAULT '1' COMMENT '采购员类型(1.SRM系统采购员2.K3采购员)',
  `business_type` varchar(255) DEFAULT NULL COMMENT '业务类型',
  `delivery_date` date DEFAULT NULL COMMENT '到货日期--不使用',
  `audit_data` varchar(255) DEFAULT NULL COMMENT '审核日期',
  `prepared_by` varchar(255) DEFAULT NULL COMMENT '制单人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `request_code` (`request_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=190580 DEFAULT CHARSET=utf8 COMMENT='采购需求头表';

-- ----------------------------
-- Records of c_srm_purchase_demand_h
-- ----------------------------

-- ----------------------------
-- Table structure for c_srm_purchase_demand_r
-- ----------------------------
DROP TABLE IF EXISTS `c_srm_purchase_demand_r`;
CREATE TABLE `c_srm_purchase_demand_r`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '采购需求行id',
  `request_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请单号',
  `row_project_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '行项目号',
  `material_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料编码',
  `material_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料名称',
  `count` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数量',
  `unit` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位',
  `required_date` date NULL DEFAULT NULL COMMENT '需求日期',
  `place_of_receipt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货地点',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `purchase_demand_id` int(0) NULL DEFAULT NULL COMMENT '采购需求头id',
  `is_delete` bit(1) NULL DEFAULT b'0' COMMENT '逻辑删除(0.未删除1.已删除)',
  `specification` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '规格',
  `project` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `station` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工位',
  `delivery_date` date NULL DEFAULT NULL COMMENT '到货日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 52475 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '采购需求行表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_srm_purchase_demand_r
-- ----------------------------

-- ----------------------------
-- Table structure for c_srm_purchase_order_h
-- ----------------------------
DROP TABLE IF EXISTS `c_srm_purchase_order_h`;
CREATE TABLE `c_srm_purchase_order_h`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '采购订单头表id',
  `order_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单号',
  `order_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单类型(1.普通采购)',
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `buyer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '采购员',
  `company` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司',
  `supplier_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '供应商代码',
  `currency` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '币种(1.CNY2.USD)',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '3' COMMENT '状态(1.新建2.待审批3.待确认4.已确认)',
  `payment_method` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '付款方式',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `is_delete` bit(1) NULL DEFAULT b'0' COMMENT '逻辑删除(0:未删除、1:已删除)',
  `payment_clause` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '付款条件',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `contract_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '合同编码',
  `supplier_id` int(0) NULL DEFAULT NULL COMMENT '供应商id',
  `delivery_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交货地址',
  `buyer_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '采购员类型(1.SRM系统采购员2.K3采购员)',
  `supplier_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '供应商名称',
  `is_open_ticket` bit(1) NULL DEFAULT b'0' COMMENT '是否已创建送货单(0.未创建1.已创建)',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `order_number`(`order_number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8763 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '采购订单头表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_srm_purchase_order_h
-- ----------------------------

-- ----------------------------
-- Table structure for c_srm_purchase_order_r
-- ----------------------------
DROP TABLE IF EXISTS `c_srm_purchase_order_r`;
CREATE TABLE `c_srm_purchase_order_r`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '采购订单行表id',
  `order_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单号',
  `line_item_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '行项目号',
  `material_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料编码',
  `material_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料名称',
  `count` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数量',
  `unit` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位',
  `unit_price` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '含税单价',
  `tax_rate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '税率',
  `shipping_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '库存组织(工厂)',
  `expected_date_of_arrival` datetime(0) NULL DEFAULT NULL COMMENT '预计到货日期',
  `purchase_request_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '采购申请号(关联采购需求申请单号)',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `purchase_order_id` int(0) NULL DEFAULT NULL COMMENT '采购订单头表id',
  `is_delete` bit(1) NULL DEFAULT b'0' COMMENT '逻辑删除(0.未删除1.已删除)',
  `department` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `accessory_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '附件地址',
  `materialId` int(0) NULL DEFAULT NULL COMMENT '物料id',
  `specification` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '规格',
  `project` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目',
  `station` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工位',
  `row_project_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '采购申请单行号(关联采购需求申请单行号)',
  `price_tax_sum` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '价税合计',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 52691 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '采购订单行表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_srm_purchase_order_r
-- ----------------------------

-- ----------------------------
-- Table structure for c_srm_send_commodity_d
-- ----------------------------
DROP TABLE IF EXISTS `c_srm_send_commodity_d`;
CREATE TABLE `c_srm_send_commodity_d`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `dt` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `packingId` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '包装id',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '单件码',
  `unitQuantity` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '单件数量',
  `numberOfPackages` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '包装数量',
  `supplierBatchCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '供应商批次码',
  `productionDt` datetime(0) NULL DEFAULT NULL COMMENT '生产日期',
  `termOfValidityDt` datetime(0) NULL DEFAULT NULL COMMENT '有效截止日期',
  `listNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '单据号',
  `rowId` int(0) NULL DEFAULT NULL COMMENT '行ID',
  `InspectionMethod` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '检验方式',
  `checkNumber` int(0) NULL DEFAULT NULL COMMENT '校验数量',
  `qualifiedNumber` int(0) NULL DEFAULT NULL COMMENT '合格数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '送货单详情表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_srm_send_commodity_d
-- ----------------------------

-- ----------------------------
-- Table structure for c_srm_send_commodity_h
-- ----------------------------
DROP TABLE IF EXISTS `c_srm_send_commodity_h`;
CREATE TABLE `c_srm_send_commodity_h`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '送货单头表id',
  `delivery_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '送货单号',
  `type_of_delivery_note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '送货单类型(1.标准送货单)',
  `delivery_date` datetime(0) NULL DEFAULT NULL COMMENT '发货日期',
  `expected_date_of_arrival` datetime(0) NULL DEFAULT NULL COMMENT '预计到货日期',
  `place_of_receipt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货地点',
  `point_of_departure` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发货地点',
  `receiving_organization` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货组织',
  `client` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户',
  `consignee` int(0) NULL DEFAULT NULL COMMENT '收货人ID',
  `supplier_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '供应商代码',
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态(1.新建2.待确认3.待发货4.待收货5.已完成)',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `supplier_id` int(0) NULL DEFAULT NULL COMMENT '供应商id',
  `is_delete` bit(1) NULL DEFAULT b'0' COMMENT '逻辑删除(0.未删除1.已删除)',
  `factoryId` int(0) NULL DEFAULT NULL COMMENT '工厂id',
  `locationId` int(0) NULL DEFAULT NULL COMMENT '库位id',
  `receivingGoodsDt` datetime(0) NULL DEFAULT NULL COMMENT '收货时间',
  `dis` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `checkUserId` int(0) NULL DEFAULT NULL COMMENT '校验人ID',
  `checkDt` datetime(0) NULL DEFAULT NULL COMMENT '校验时间',
  `is_open_ticket` bit(1) NULL DEFAULT b'0' COMMENT '是否已创建开票申请单(0.未创建1.已创建)',
  `consignee_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货人名称',
  `tracking_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物流单号',
  `logistics_company_information` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物流公司信息',
  `contact_way` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系方式',
  `shipping_method` int(0) NULL DEFAULT NULL COMMENT '送货方式 ( 1.人工,2.物流)',
  `wQIS` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '检验方式',
  `qualityTestingStatus` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '质检状态',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `delivery_number`(`delivery_number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '送货单头表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_srm_send_commodity_h
-- ----------------------------

-- ----------------------------
-- Table structure for c_srm_send_commodity_logistics
-- ----------------------------
DROP TABLE IF EXISTS `c_srm_send_commodity_logistics`;
CREATE TABLE `c_srm_send_commodity_logistics`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '送货单物流信息表id',
  `delivery_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '送货单号',
  `tracking_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物流单号',
  `logistics_company_information` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物流公司信息',
  `contact_way` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系方式',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `is_delete` bit(1) NULL DEFAULT b'0' COMMENT '逻辑删除(0.未删除1.已删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '送货单物流信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_srm_send_commodity_logistics
-- ----------------------------

-- ----------------------------
-- Table structure for c_srm_send_commodity_r
-- ----------------------------
DROP TABLE IF EXISTS `c_srm_send_commodity_r`;
CREATE TABLE `c_srm_send_commodity_r`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '送货单行表id',
  `delivery_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '送货单号',
  `line_item_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '行项目号',
  `material_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料编码',
  `count` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数量',
  `paid_in_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '实收数(可开票数量)',
  `unit` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位',
  `purchase_order_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '采购订单号',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `commodity_id` int(0) NULL DEFAULT NULL COMMENT '送货单头表id',
  `is_delete` bit(1) NULL DEFAULT b'0' COMMENT '逻辑删除(0.未删除1.已删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '送货单行表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_srm_send_commodity_r
-- ----------------------------

-- ----------------------------
-- Table structure for c_srm_sourcing_template
-- ----------------------------
DROP TABLE IF EXISTS `c_srm_sourcing_template`;
CREATE TABLE `c_srm_sourcing_template`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '寻源模板id',
  `template_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模板编号',
  `template_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模板名称',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型(1.询报价2.招投标)',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态(1.已禁用2.已启用)',
  `version` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '版本',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `is_delete` bit(1) NULL DEFAULT b'0' COMMENT '逻辑删除(0.未删除1.已删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '寻源模板表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_srm_sourcing_template
-- ----------------------------

-- ----------------------------
-- Table structure for c_srm_sourcing_template_affiliate
-- ----------------------------
DROP TABLE IF EXISTS `c_srm_sourcing_template_affiliate`;
CREATE TABLE `c_srm_sourcing_template_affiliate`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '寻源模板附属信息id',
  `template_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模板编号',
  `accessory_categories` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '附属类别(1.银行信息2.产品信息)',
  `property_field` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '属性字段',
  `field_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字段名称',
  `field_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字段值',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `template_id` int(0) NULL DEFAULT NULL COMMENT '寻源模板id',
  `is_delete` bit(1) NULL DEFAULT b'0' COMMENT '逻辑删除(0.未删除1.已删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '寻源模板附属信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_srm_sourcing_template_affiliate
-- ----------------------------

-- ----------------------------
-- Table structure for c_srm_supplier
-- ----------------------------
DROP TABLE IF EXISTS `c_srm_supplier`;
CREATE TABLE `c_srm_supplier`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '供应商名称',
  `contact_person` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人',
  `contact_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话/方式(移动电话)',
  `contact_email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系邮箱',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `company_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司编码',
  `supplier_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '供应商代码',
  `in_phase` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '所处阶段(1.注册、2.潜在、3.合格、4.淘汰)',
  `status` int(0) NULL DEFAULT 1 COMMENT '认证状态(0.冻结1.未认证2.认证中3.已认证4.认证失败5.企业信息变更失败)',
  `role_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限id',
  `token` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'token',
  `token_create_time` datetime(0) NULL DEFAULT NULL COMMENT 'token创建时间',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `is_delete` bit(1) NULL DEFAULT b'0' COMMENT '逻辑删除(0:未删除、1:已删除)',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话(座机)',
  `fax` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '传真',
  `abbreviation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '简称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 841 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '供应商信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_srm_supplier
-- ----------------------------

-- ----------------------------
-- Table structure for c_srm_supplier_change_record
-- ----------------------------
DROP TABLE IF EXISTS `c_srm_supplier_change_record`;
CREATE TABLE `c_srm_supplier_change_record`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '供应商变更记录表id',
  `supplier_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '供应商代码',
  `bank_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开户银行名称',
  `bank_account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '银行账号',
  `product_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品名称',
  `product_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品类别(1.中草药材、2.棉花)(不使用)',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态(0.已保存1.变更中2.已变更3.变更失败)',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `is_delete` bit(1) NULL DEFAULT b'0' COMMENT '逻辑删除(0.未删除1.已删除)',
  `unify_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '统一社会信用代码',
  `enterprise_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业类型(1.国有企业 2.集体所有制企业 3.私营企 4.股份制企业 5.有限合伙企业 6.联营企业 7.外商投资企业 8.个人独资企业 9.港澳台企业 10.股份合作企业)',
  `registered_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注册地址',
  `particular_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详细地址',
  `legal_representative` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '法定代表人',
  `registered_capital` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注册资本',
  `register_date` date NULL DEFAULT NULL COMMENT '成立日期',
  `upload_of_business_license` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '营业执照上传',
  `business_nature` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '经营性质(1.制造商 2.贸易商 3.服务商)',
  `products_or_services` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品/服务',
  `client` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户',
  `company_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司编码',
  `company_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '供应商变更记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_srm_supplier_change_record
-- ----------------------------

-- ----------------------------
-- Table structure for c_srm_supplier_h
-- ----------------------------
DROP TABLE IF EXISTS `c_srm_supplier_h`;
CREATE TABLE `c_srm_supplier_h`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '供应商升降级申请头表id',
  `request_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请单号',
  `supplier_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '供应商代码',
  `current_generation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '当前阶段(1.注册、2.潜在、3.合格、4.淘汰)',
  `target_phase` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '目标阶段(1.注册、2.潜在、3.合格、4.淘汰)',
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态(0.冻结1.未认证2.认证中3.已认证4.认证失败)',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注/说明',
  `blacklist_mark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '黑名单标记(0.黑名单1.正常)',
  `attachment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '附件',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `supplier_id` int(0) NULL DEFAULT NULL COMMENT '供应商id',
  `is_delete` bit(1) NULL DEFAULT b'0' COMMENT '逻辑删除(0.未删除1.已删除)',
  `sum_score` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '总得分',
  `grade` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '等级',
  `request_status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请状态(1.已创建2.申请中3.申请成功4.申请失败)',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `request_code`(`request_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '供应商升降级申请头表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_srm_supplier_h
-- ----------------------------

-- ----------------------------
-- Table structure for c_srm_supplier_r
-- ----------------------------
DROP TABLE IF EXISTS `c_srm_supplier_r`;
CREATE TABLE `c_srm_supplier_r`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '供应商升降级申请行表id',
  `request_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请单号',
  `grading_index` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评分指标',
  `index_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '指标值',
  `grading_staff` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评分人员',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `request_code_id` int(0) NULL DEFAULT NULL COMMENT '供应商升降级申请头表id',
  `is_delete` bit(1) NULL DEFAULT b'0' COMMENT '逻辑删除(0.未删除1.已删除)',
  `evaluation_item_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评价项目编号',
  `evaluation_item` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评价项目',
  `evaluation_criterion` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评价标准',
  `score_is` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评分方式(1.手工评分2.系统评分)',
  `score_start` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分值从',
  `score_stop` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分值至',
  `score` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '得分',
  `weight` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权重(%)',
  `line_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '行号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '供应商升降级申请行表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_srm_supplier_r
-- ----------------------------

-- ----------------------------
-- Table structure for c_srm_survey
-- ----------------------------
DROP TABLE IF EXISTS `c_srm_survey`;
CREATE TABLE `c_srm_survey`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '调查表记录id',
  `survey_form_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '调查表单号',
  `company_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司名称',
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `template_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模板编号',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态(1.待发布2.已拒绝3.已完成)',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `company_id` int(0) NULL DEFAULT NULL COMMENT '公司id',
  `is_delete` bit(1) NULL DEFAULT b'0' COMMENT '逻辑删除(0.未删除1.已删除)',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `survey_form_number`(`survey_form_number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '调查表记录主表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_srm_survey
-- ----------------------------

-- ----------------------------
-- Table structure for c_srm_survey_details
-- ----------------------------
DROP TABLE IF EXISTS `c_srm_survey_details`;
CREATE TABLE `c_srm_survey_details`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '调查表记录附属id',
  `survey_form_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '调查表单号',
  `auxiliary_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '附属类别',
  `property_field` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '属性字段',
  ` field_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字段值',
  `supplier_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '供应商代码',
  `whether_a_reply` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否答复',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `supplier_id` int(0) NULL DEFAULT NULL COMMENT '供应商id',
  `is_delete` bit(1) NULL DEFAULT b'0' COMMENT '逻辑删除(0.未删除1.已删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '调查表记录附属表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_srm_survey_details
-- ----------------------------

-- ----------------------------
-- Table structure for c_srm_template
-- ----------------------------
DROP TABLE IF EXISTS `c_srm_template`;
CREATE TABLE `c_srm_template`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '调查表模板id',
  `template_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模板编号',
  `template_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模板名称',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型(1.生产制造类)',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态(1.新建、2.审批中、3.待发布、4.待回复、5.已完成)',
  `version` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '版本',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `supplier_id` int(0) NULL DEFAULT NULL COMMENT '供应商id',
  `is_delete` bit(1) NULL DEFAULT b'0' COMMENT '逻辑删除(0.未删除1.已删除)',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `template_code`(`template_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '调查表模板主表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_srm_template
-- ----------------------------

-- ----------------------------
-- Table structure for c_srm_template_details
-- ----------------------------
DROP TABLE IF EXISTS `c_srm_template_details`;
CREATE TABLE `c_srm_template_details`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '调查表模板附属信息id',
  `template_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模板编号',
  `auxiliary_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '附属类别(1.银行信息2.产品信息)',
  `property_field` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '属性字段',
  `version` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '版本',
  `field_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字段名称',
  `field_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字段类型(1.int、2.string)',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `template_id` int(0) NULL DEFAULT NULL COMMENT '调查表模板id',
  `is_delete` bit(1) NULL DEFAULT b'0' COMMENT '逻辑删除(0.未删除1.已删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '调查表模板附属信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_srm_template_details
-- ----------------------------

-- ----------------------------
-- Table structure for c_srm_the_number_audit_h
-- ----------------------------
DROP TABLE IF EXISTS `c_srm_the_number_audit_h`;
CREATE TABLE `c_srm_the_number_audit_h`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `the_number_of_he_invoice_application` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开票单号',
  `no_tax_money_count_money` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '不含税总金额',
  `currency` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '币种',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  `supplier_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '供应商备注',
  `company_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司',
  `business_entity` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务实体',
  `pur_organization` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '采购组织',
  `inventory_organization` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '库存组织',
  `buyer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '采购员',
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '提交日期',
  `the_drawer_side` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出票方',
  `make_theme` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开票主题',
  `the_balance` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '期初余额',
  `current_increase` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '本期增加额',
  `current_reduction` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '本期减少额',
  `ending_balance` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '期末余额',
  `is_delete` bit(1) NULL DEFAULT b'0' COMMENT '逻辑删除(0.未删除1.已删除)',
  `delivery_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '送货单号',
  `is_created` int(0) NULL DEFAULT 0 COMMENT '是否已创建应收/付发票(0.未创建1.已创建)',
  `shipping_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '库存组织',
  `tax_money_count_money` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '含税总金额',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '供应商地址',
  `sum_tax` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '总税额',
  `supplier_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '供应商编码',
  `supplier_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '供应商名称',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `the_number_of_he_invoice_application`(`the_number_of_he_invoice_application`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '对账申请头表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_srm_the_number_audit_h
-- ----------------------------

-- ----------------------------
-- Table structure for c_srm_the_number_audit_r
-- ----------------------------
DROP TABLE IF EXISTS `c_srm_the_number_audit_r`;
CREATE TABLE `c_srm_the_number_audit_r`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `line_item_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '行号',
  `the_number_of_he_invoice_application` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开票单行号',
  `material_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料编码',
  `material_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料名称',
  `common_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '通用名',
  `supplier_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '供应商料号',
  `supplier_number_describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '供应商料号描述',
  `rules_of_the_model` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '规则型号',
  `unit` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位',
  `count` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '可开票数量',
  `no_unit_price` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '不含税单价',
  `no_tax_money_money` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '不含税金额',
  `tax_rate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '税率(%)',
  `tax` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '税额',
  `unit_price` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '含税单价',
  `unit_money` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '含税金额',
  `row_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '行备注',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `order_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '采购订单号',
  `is_delete` bit(1) NULL DEFAULT b'0' COMMENT '逻辑删除(0.未删除1.已删除)',
  `delivery_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '送货单号',
  `paid_in_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '实收数(可开票数量)',
  `no_tax_money` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '不含税金额',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '对账申请行表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_srm_the_number_audit_r
-- ----------------------------

-- ----------------------------
-- Table structure for c_wms_all_material_barcode
-- ----------------------------
DROP TABLE IF EXISTS `c_wms_all_material_barcode`;
CREATE TABLE `c_wms_all_material_barcode`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `materialBarcodeRule_Id` int(0) NULL DEFAULT NULL COMMENT '物料条码规则Id',
  `bar_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '条码',
  `generation_time` datetime(0) NULL DEFAULT NULL COMMENT '生成时间',
  `printing_time` datetime(0) NULL DEFAULT NULL COMMENT '打印时间',
  `state` int(0) NULL DEFAULT NULL COMMENT '状态：0:使用，1：丢弃',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2097620 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '所有物料条码' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_wms_all_material_barcode
-- ----------------------------

-- ----------------------------
-- Table structure for c_wms_approval_details_t
-- ----------------------------
DROP TABLE IF EXISTS `c_wms_approval_details_t`;
CREATE TABLE `c_wms_approval_details_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键：编号',
  `LIST_NO` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单号',
  `APPROVAL_RESULT` int(0) NULL DEFAULT NULL COMMENT '0.未审批 1.通过 2.不通过',
  `USER_ID` int(0) NULL DEFAULT NULL COMMENT '审批人id',
  `REASON` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '原因',
  `APPROVAL_ID` int(0) NULL DEFAULT NULL COMMENT '审批主表ID',
  `DT` datetime(0) NULL DEFAULT NULL COMMENT '审批时间',
  `PRIORITY_LEVEL` int(0) NULL DEFAULT NULL COMMENT '优先级',
  `YN_APPROVED` int(0) NULL DEFAULT NULL COMMENT '是否可审批1：可以、0：不可以',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3122 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '审批详情' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_wms_approval_details_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_wms_approval_t
-- ----------------------------
DROP TABLE IF EXISTS `c_wms_approval_t`;
CREATE TABLE `c_wms_approval_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键：编号',
  `LIST_NO` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '外键：单号',
  `DT` datetime(0) NULL DEFAULT NULL COMMENT '生成记录的时间',
  `PROCESS_ID` int(0) NULL DEFAULT NULL COMMENT '外键：流程表id',
  `USER_ID` int(0) NULL DEFAULT NULL COMMENT '申请人id',
  `STATE` int(0) NULL DEFAULT NULL COMMENT '状态：0.未审批、1.审批中、2.审批成功、3.驳回、4撤销',
  `NOTE` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `index_approval`(`LIST_NO`, `STATE`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7297 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '审批表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_wms_approval_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_wms_area_t
-- ----------------------------
DROP TABLE IF EXISTS `c_wms_area_t`;
CREATE TABLE `c_wms_area_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT,
  `AREA_NO` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区域编号',
  `AREA_NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区域名称',
  `AREA_TYPE` int(0) NULL DEFAULT NULL COMMENT '区域类型，0：立库 1：平库 2：OTHER',
  `AREA_LENGTH` int(0) NULL DEFAULT NULL COMMENT '区域长度，单位M',
  `AREA_WIDTH` int(0) NULL DEFAULT NULL COMMENT '区域宽度，单位M',
  `AREA_HIGHT` int(0) NULL DEFAULT NULL COMMENT '区域高度，单位M',
  `AREA_LIMIT` int(0) NULL DEFAULT NULL COMMENT '区域上限，单位M',
  `AREA_LOADBEARING` int(0) NULL DEFAULT NULL COMMENT '区域承重,单位KG',
  `MODIFY_TIME` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `WAREHOUSE_ID` int(0) NULL DEFAULT NULL COMMENT '所属仓库,仓库ID',
  `VIEW_MODE` int(0) NULL DEFAULT 1 COMMENT '0：不可查询 1：可查询',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '仓库区域表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_wms_area_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_wms_department_t
-- ----------------------------
DROP TABLE IF EXISTS `c_wms_department_t`;
CREATE TABLE `c_wms_department_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT,
  `DM_NUMBER` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门编号',
  `DM_NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门名称',
  `NOTE` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `MODIFY_TIME` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `companyId` int(0) NULL DEFAULT NULL COMMENT '公司id',
  `superiorId` int(0) NULL DEFAULT NULL COMMENT '上级id',
  `hierarchy` int(0) NULL DEFAULT NULL COMMENT '层级',
  `VIEW_MODE` int(0) NULL DEFAULT 1 COMMENT '0：不可查询 1：可查询',
  `organizationalPath` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组织路径',
  `superiorName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上级部门',
  `executive` int(0) NULL DEFAULT NULL COMMENT '主管',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门基础表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_wms_department_t
-- ----------------------------
INSERT INTO `c_wms_department_t` VALUES (1, '10000', '集团', NULL, NULL, NULL, 0, NULL, 1, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for c_wms_inventory_detail_t
-- ----------------------------
DROP TABLE IF EXISTS `c_wms_inventory_detail_t`;
CREATE TABLE `c_wms_inventory_detail_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `INVENTORY_ID` int(0) NULL DEFAULT NULL COMMENT '盘点id',
  `MATERIAL_ID` int(0) NULL DEFAULT NULL COMMENT '物料ID',
  `INVENTORY_NO` int(0) NULL DEFAULT NULL COMMENT '物料盘点数量',
  `STOCK_NO` int(0) NULL DEFAULT NULL COMMENT '物料库存数量',
  `DIFFERENCE_NO` int(0) NULL DEFAULT NULL COMMENT '物料差异数量',
  `LOCATION_ID` int(0) NULL DEFAULT NULL COMMENT '库位',
  `PROJECT_ID` int(0) NULL DEFAULT NULL COMMENT '项目',
  `TRAY_CODE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '托盘码',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '仓库盘点明细表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_wms_inventory_detail_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_wms_inventory_t
-- ----------------------------
DROP TABLE IF EXISTS `c_wms_inventory_t`;
CREATE TABLE `c_wms_inventory_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键 ',
  `LIST_NO` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '盘点单据号',
  `DT` datetime(0) NULL DEFAULT NULL COMMENT '日期',
  `USER_ID` int(0) NULL DEFAULT NULL COMMENT '操作人员',
  `STATE` int(0) NULL DEFAULT NULL COMMENT '盘点状态1.生效、0.未生效',
  `DIS` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '库存盘点历史记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_wms_inventory_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_wms_location_t
-- ----------------------------
DROP TABLE IF EXISTS `c_wms_location_t`;
CREATE TABLE `c_wms_location_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT,
  `LOCATION_NO` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '库位编号',
  `LOCATION_NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '库位名称',
  `LOCATION_LENGTH` int(0) NULL DEFAULT NULL COMMENT '库位长度，单位M',
  `LOCATION_WIDTH` int(0) NULL DEFAULT NULL COMMENT '库位宽度，单位M',
  `LOCATION_HIGHT` int(0) NULL DEFAULT NULL COMMENT '库位高度，单位M',
  `LOCATION_VOLUME` int(0) NULL DEFAULT NULL COMMENT '库位体积，单位M',
  `LOCATION_TYPE` int(0) NULL DEFAULT NULL COMMENT '库位类型，0：立库 1：平库 2：OTHER',
  `LOCATION_MARK` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '库位标识',
  `LOCATION_TYPE1` int(0) NULL DEFAULT NULL COMMENT '库位种类，备用',
  `LOCATION_CAPACITY` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '库位容量',
  `LOCATION_WEIGHT` int(0) NULL DEFAULT NULL COMMENT '库位载重量，单位KG',
  `LOCATION_STATUS` int(0) NULL DEFAULT NULL COMMENT '库位状态，0：正常 1：满库 2：维修 3：禁用 4：报废 5:未满可使用 6.空托盘放置',
  `LOCATION_X` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '库位横坐标，考虑小数点',
  `LOCATION_Y` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '库位纵坐标，考虑小数点',
  `LOCATION_Z` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '库位Z坐标，考虑小数点',
  `LOCATION_VR` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '库位校验规则',
  `RESERVOIR_AREA_ID` int(0) NULL DEFAULT NULL COMMENT '库区ID，关联库区表',
  `MODIFY_TIME` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `TRAY_TYPE` int(0) NULL DEFAULT NULL COMMENT '可容纳托盘类型 1:矮、2:高',
  `TRAY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '托盘码',
  `VIEW_MODE` int(0) NULL DEFAULT NULL,
  `PTL_NO` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'PTL编号',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 573 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '仓库库位表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_wms_location_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_wms_material_barcode_rule_t
-- ----------------------------
DROP TABLE IF EXISTS `c_wms_material_barcode_rule_t`;
CREATE TABLE `c_wms_material_barcode_rule_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `rule` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '规则',
  `condition` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '条件',
  `modify_dt` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '物料条码规则' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_wms_material_barcode_rule_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_wms_material_inventory_t
-- ----------------------------
DROP TABLE IF EXISTS `c_wms_material_inventory_t`;
CREATE TABLE `c_wms_material_inventory_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `MATERIAL_NO` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料编号',
  `MATERIAL_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料名称',
  `MATERIAL_NUMBER` int(0) NULL DEFAULT NULL COMMENT '物料数量',
  `INVENTORY_TIME` datetime(0) NULL DEFAULT NULL COMMENT '记录日期',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1107 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '物料库存数量记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_wms_material_inventory_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_wms_material_number_t
-- ----------------------------
DROP TABLE IF EXISTS `c_wms_material_number_t`;
CREATE TABLE `c_wms_material_number_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `MATERIAL_NO` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料编号',
  `MATERIAL_NAME` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料名称',
  `MATERIAL_NUMBER` int(0) NULL DEFAULT NULL COMMENT '数量',
  `PROJECT_ID` int(0) NULL DEFAULT NULL COMMENT '项目ID',
  `MATERIAL_ID` int(0) NULL DEFAULT NULL COMMENT '物料ID',
  `WAREHOUSE_ID` int(0) NULL DEFAULT NULL COMMENT '仓库ID',
  `AREA_ID` int(0) NULL DEFAULT NULL COMMENT '区域ID',
  `LOCATION_ID` int(0) NULL DEFAULT NULL COMMENT '库位表ID',
  `RESERVOIR_AREA_ID` int(0) NULL DEFAULT NULL COMMENT '仓库库区ID',
  `LMMINENT_RELEASE` int(0) NULL DEFAULT NULL COMMENT '即将出库的数量',
  `DT` datetime(0) NULL DEFAULT NULL COMMENT '入库时间',
  `FREEZING_NUMBER` int(0) NULL DEFAULT NULL COMMENT '冻结数',
  `RESERVED_NUMBER` int(0) NULL DEFAULT NULL COMMENT '预留数',
  `TRAY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '托盘号',
  `MATERIAL_CODE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料条码',
  `bar_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13687 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '物料库存总表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_wms_material_number_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_wms_material_rule_attribute_t
-- ----------------------------
DROP TABLE IF EXISTS `c_wms_material_rule_attribute_t`;
CREATE TABLE `c_wms_material_rule_attribute_t`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `attribute_cn` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '中文属性',
  `column` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '属性列',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '物料条码规则属性表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_wms_material_rule_attribute_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_wms_material_t
-- ----------------------------
DROP TABLE IF EXISTS `c_wms_material_t`;
CREATE TABLE `c_wms_material_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT,
  `MODIFY_TIME` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `MATERIAL_NO` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料编号',
  `MATERIAL_PARTNO` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料图号',
  `MATERIAL_NAME` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料名称',
  `MATERIAL_TYPE` int(0) NULL DEFAULT NULL COMMENT '物料类别，物料类型表id',
  `MATERIAL_UNIT` int(0) NULL DEFAULT NULL COMMENT '物料单位，物料单位表id',
  `MATERIAL_LENGTH` decimal(65, 2) NULL DEFAULT NULL COMMENT '物料长度，单位m',
  `MATERIAL_WIDTH` decimal(65, 2) NULL DEFAULT NULL COMMENT '物料宽度，单位m',
  `MATERIAL_HIGHT` decimal(65, 2) NULL DEFAULT NULL COMMENT '物料高度，单位m',
  `MATERIAL_VOLUME` decimal(65, 2) NULL DEFAULT NULL COMMENT '物料体积。单位m3',
  `MATERIAL_WEIGHT` decimal(65, 2) NULL DEFAULT NULL COMMENT '物料重量，单位KG',
  `MATERIAL_LT` int(0) NULL DEFAULT NULL COMMENT '存放库位类型，0：立库；1：平库；2：other',
  `MATERIAL_INCOM_TYPE` int(0) NULL DEFAULT NULL COMMENT '来料方式，0：单个；1：批次',
  `MATERIAL_MODEL` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料型号',
  `MATERIAL_CODE` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料条码',
  `MATERIAL_LOW_LIMITMATERIAL` int(0) NOT NULL COMMENT '物料库存下限',
  `MATERIAL_SC` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料防伪码',
  `MATERIAL_BATCH` decimal(65, 30) NOT NULL COMMENT '物料批次数量',
  `MATERIAL_VR` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料校验规则',
  `DAYS_OF_FAILURE` decimal(65, 30) NULL DEFAULT NULL COMMENT '失效天数',
  `NOTE` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `MATERIAL_COST` decimal(65, 2) NULL DEFAULT NULL COMMENT '成本',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '物料表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_wms_material_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_wms_material_type_t
-- ----------------------------
DROP TABLE IF EXISTS `c_wms_material_type_t`;
CREATE TABLE `c_wms_material_type_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `MODIFY_TIME` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `MT_NO` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类别编号',
  `MT_NAME` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类别名称',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '物料类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_wms_material_type_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_wms_material_unit_t
-- ----------------------------
DROP TABLE IF EXISTS `c_wms_material_unit_t`;
CREATE TABLE `c_wms_material_unit_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `MODIFY_TIME` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `UNIT_NO` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位编号',
  `UNIT_NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位名称',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '物料单位表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_wms_material_unit_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_wms_process_approval_detail
-- ----------------------------
DROP TABLE IF EXISTS `c_wms_process_approval_detail`;
CREATE TABLE `c_wms_process_approval_detail`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(0) NULL DEFAULT NULL COMMENT '用户id',
  `PRIORITY_LEVEL` int(0) NULL DEFAULT NULL COMMENT '优先级',
  `DT` datetime(0) NULL DEFAULT NULL,
  `PROCESS_ID` int(0) NULL DEFAULT NULL COMMENT '流程审批外键',
  `DIS` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '流程审批明细' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_wms_process_approval_detail
-- ----------------------------

-- ----------------------------
-- Table structure for c_wms_process_approval_t
-- ----------------------------
DROP TABLE IF EXISTS `c_wms_process_approval_t`;
CREATE TABLE `c_wms_process_approval_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT,
  `DEPT_ID` int(0) NULL DEFAULT NULL COMMENT '所属部门',
  `ROLE_ID` int(0) NULL DEFAULT NULL COMMENT '所属角色',
  `TYPE_ID` int(0) NULL DEFAULT NULL COMMENT '流程类型',
  `DT` datetime(0) NULL DEFAULT NULL,
  `DIS` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 53 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '流程审批表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_wms_process_approval_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_wms_process_type_t
-- ----------------------------
DROP TABLE IF EXISTS `c_wms_process_type_t`;
CREATE TABLE `c_wms_process_type_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT,
  `PROCESS_TYPE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程类型',
  `DIS` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `VIEW_MODE` int(0) NULL DEFAULT 1 COMMENT '0：不可查询 1：可查询',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '流程类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_wms_process_type_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_wms_project_t
-- ----------------------------
DROP TABLE IF EXISTS `c_wms_project_t`;
CREATE TABLE `c_wms_project_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT,
  `MANAGEMENT_NO` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理编号',
  `CONTRACT_NO` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '合同编号',
  `PROJECT_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目名称',
  `PRODUCT_NUMBER` int(0) NULL DEFAULT NULL COMMENT '产品数量',
  `START_DATE` date NULL DEFAULT NULL COMMENT '指派时间',
  `END_DATE` date NULL DEFAULT NULL COMMENT '交货日期',
  `PROJECT_NATURE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目性质(1.销售 2.免费 3.制样)',
  `PROJECT_LEADER` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目负责人',
  `PROJECT_TYPE` int(0) NULL DEFAULT NULL COMMENT '项目类别',
  `INTERNAL_PROJECT_NO` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内部项目号',
  `UPDATE_DATE` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `DIS` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `VIEW_MODE` int(0) NULL DEFAULT 1 COMMENT '0：不可查询 1：可查询',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 241 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '项目表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_wms_project_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_wms_project_type_t
-- ----------------------------
DROP TABLE IF EXISTS `c_wms_project_type_t`;
CREATE TABLE `c_wms_project_type_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT,
  `PROJECT_TYPE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目类型名称',
  `DIS` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注释',
  `VIEW_MODE` int(0) NULL DEFAULT 1 COMMENT '0：不可查询 1：可查询',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_wms_project_type_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_wms_reservoir_area_t
-- ----------------------------
DROP TABLE IF EXISTS `c_wms_reservoir_area_t`;
CREATE TABLE `c_wms_reservoir_area_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT,
  `RA_NO` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '库区编号',
  `RA_NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '库区名称',
  `RA_TYPE` int(0) NULL DEFAULT NULL COMMENT '库区类型，0：立库 1：平库 2：OTHER',
  `LOCATION_ID` int(0) NULL DEFAULT NULL COMMENT '默认拣货库位，库位ID',
  `RA_LOCATION_LIMIT` int(0) NULL DEFAULT NULL COMMENT '库位上限',
  `RA_ALARM_LIMIT` int(0) NULL DEFAULT NULL COMMENT '库区报警下限',
  `RA_PRINT_ADD` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '库区打印机地址',
  `AREA_ID` int(0) NULL DEFAULT NULL COMMENT '所属区域，区域ID',
  `MODIFY_TIME` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `VIEW_MODE` int(0) NULL DEFAULT 1 COMMENT '0：不可查询 1：可查询',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '仓库库区表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_wms_reservoir_area_t
-- ----------------------------

-- ----------------------------
-- Table structure for c_wms_warehouse_t
-- ----------------------------
DROP TABLE IF EXISTS `c_wms_warehouse_t`;
CREATE TABLE `c_wms_warehouse_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '仓库名称',
  `MANAGER` int(0) NULL DEFAULT NULL COMMENT '负责人，员工id',
  `OPERATIONS` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作员，存储员工ID,可选多个，以“;”隔开',
  `MANAGER_TELE` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '负责人电话',
  `ADDRESS` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '仓库地址',
  `NOTE` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `DEFAULT_M` int(0) NULL DEFAULT NULL COMMENT '默认仓库，0：否 1：是',
  `MODIFY_TIME` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `VIEW_MODE` int(0) NULL DEFAULT 1 COMMENT '0：不可查询 1：可查询',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '仓库表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_wms_warehouse_t
-- ----------------------------

-- ----------------------------
-- Table structure for db_upgrade_sample
-- ----------------------------
DROP TABLE IF EXISTS `db_upgrade_sample`;
CREATE TABLE `db_upgrade_sample`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `content` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of db_upgrade_sample
-- ----------------------------

-- ----------------------------
-- Table structure for db_version
-- ----------------------------
DROP TABLE IF EXISTS `db_version`;
CREATE TABLE `db_version`  (
  `version` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of db_version
-- ----------------------------

-- ----------------------------
-- Table structure for dgtx_places
-- ----------------------------
DROP TABLE IF EXISTS `dgtx_places`;
CREATE TABLE `dgtx_places`  (
  `id` smallint(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `parent_id` smallint(0) UNSIGNED NOT NULL DEFAULT 0,
  `cname` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `ctype` tinyint(1) NOT NULL DEFAULT 2,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `parent_id`(`parent_id`) USING BTREE,
  INDEX `ctype`(`ctype`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 3409 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dgtx_places
-- ----------------------------

-- ----------------------------
-- Table structure for k3_export_notifydetail
-- ----------------------------
DROP TABLE IF EXISTS `k3_export_notifydetail`;
CREATE TABLE `k3_export_notifydetail`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT,
  `EXPORT_ID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '单据编号 出库单ID',
  `BOM_ID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料长代码',
  `EXPORT_LOT_NO` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单号',
  `EXPORT_PACK_QUANTITY` int(0) NULL DEFAULT NULL COMMENT '出库数量',
  `EXPORT_WAREHOUSE_ID` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '仓库ID',
  `Material_Id` int(0) NULL DEFAULT NULL COMMENT '物料id',
  `materialGoodsModel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料规格型号',
  `project_Id` int(0) NULL DEFAULT NULL COMMENT '项目id',
  `dt` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '生成时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 163 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of k3_export_notifydetail
-- ----------------------------

-- ----------------------------
-- Table structure for k3_import_notifydetail
-- ----------------------------
DROP TABLE IF EXISTS `k3_import_notifydetail`;
CREATE TABLE `k3_import_notifydetail`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT,
  `IMPORT_ID` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '单据编号 入库单ID',
  `IMPORT_GOODS_CODE` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料长代码',
  `IMPORT_PACK_QUANTITY` int(0) NULL DEFAULT NULL COMMENT '入库数量 内箱',
  `IMPORT_WAREHOUSE_ID` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '仓库ID',
  `IMPORT_REMARK` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
  `IMPORT_MATERIAL_NAME` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '物料名称',
  `IMPORT_PROJECT_NO` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目号 物料所属项目',
  `RESULT` int(0) NULL DEFAULT 0 COMMENT '是否全部入库 0否 1是',
  `RECEIVED_NUMBER` int(0) NULL DEFAULT NULL COMMENT '已入库数量',
  `project_id` int(0) NULL DEFAULT NULL COMMENT '项目id',
  `material_id` int(0) NULL DEFAULT NULL COMMENT '物料id',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22862 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of k3_import_notifydetail
-- ----------------------------

-- ----------------------------
-- Table structure for kanban_attr
-- ----------------------------
DROP TABLE IF EXISTS `kanban_attr`;
CREATE TABLE `kanban_attr`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '属性名称',
  `objId` int(0) NOT NULL COMMENT '对象id',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 73 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '数据属性和对象绑定' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kanban_attr
-- ----------------------------

-- ----------------------------
-- Table structure for kanban_bgimg
-- ----------------------------
DROP TABLE IF EXISTS `kanban_bgimg`;
CREATE TABLE `kanban_bgimg`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT,
  `user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户',
  `imagePath` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '背景图地址',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 42 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '背景图地址' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kanban_bgimg
-- ----------------------------

-- ----------------------------
-- Table structure for kanban_editauth
-- ----------------------------
DROP TABLE IF EXISTS `kanban_editauth`;
CREATE TABLE `kanban_editauth`  (
  `ID` int(0) NOT NULL COMMENT '0:可编辑，其他：不可编辑',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kanban_editauth
-- ----------------------------

-- ----------------------------
-- Table structure for kanban_eleattr
-- ----------------------------
DROP TABLE IF EXISTS `kanban_eleattr`;
CREATE TABLE `kanban_eleattr`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT,
  `eleId` int(0) NOT NULL COMMENT '元素id',
  `attrId` int(0) NOT NULL COMMENT '属性id',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 289 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '元素和数据属性绑定' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kanban_eleattr
-- ----------------------------

-- ----------------------------
-- Table structure for kanban_eleobj
-- ----------------------------
DROP TABLE IF EXISTS `kanban_eleobj`;
CREATE TABLE `kanban_eleobj`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT,
  `eleName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '元素名称',
  `objId` int(0) NULL DEFAULT NULL COMMENT '对象id',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 49 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '元素与对象匹配表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kanban_eleobj
-- ----------------------------

-- ----------------------------
-- Table structure for kanban_elewhere
-- ----------------------------
DROP TABLE IF EXISTS `kanban_elewhere`;
CREATE TABLE `kanban_elewhere`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT,
  `eleId` int(0) NOT NULL COMMENT '元素id',
  `whereId` int(0) NOT NULL COMMENT '条件id',
  `whereValue` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'where条件值',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '元素得数据限制条件' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kanban_elewhere
-- ----------------------------

-- ----------------------------
-- Table structure for kanban_flag
-- ----------------------------
DROP TABLE IF EXISTS `kanban_flag`;
CREATE TABLE `kanban_flag`  (
  `ID` int(0) NOT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kanban_flag
-- ----------------------------

-- ----------------------------
-- Table structure for kanban_img
-- ----------------------------
DROP TABLE IF EXISTS `kanban_img`;
CREATE TABLE `kanban_img`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT,
  `user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户',
  `imagePath` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片地址',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '图片地址' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kanban_img
-- ----------------------------

-- ----------------------------
-- Table structure for kanban_obj
-- ----------------------------
DROP TABLE IF EXISTS `kanban_obj`;
CREATE TABLE `kanban_obj`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '对象名称',
  `api` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接口地址',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '数据对象' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kanban_obj
-- ----------------------------

-- ----------------------------
-- Table structure for kanban_objwhere
-- ----------------------------
DROP TABLE IF EXISTS `kanban_objwhere`;
CREATE TABLE `kanban_objwhere`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT,
  `whereName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'where条件名',
  `showName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'where条件名',
  `objId` int(0) NOT NULL COMMENT '对象id',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 41 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '对象支持的where条件' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kanban_objwhere
-- ----------------------------

-- ----------------------------
-- Table structure for kanban_test1
-- ----------------------------
DROP TABLE IF EXISTS `kanban_test1`;
CREATE TABLE `kanban_test1`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '看板名称',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '看板介绍',
  `json` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '看板json',
  `imagesByte` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '缩略图',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '看板内容' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kanban_test1
-- ----------------------------

-- ----------------------------
-- Table structure for kanban_test2
-- ----------------------------
DROP TABLE IF EXISTS `kanban_test2`;
CREATE TABLE `kanban_test2`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '看板名称',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '看板介绍',
  `json` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '看板json',
  `imagesByte` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '缩略图',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 349 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '看板内容' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kanban_test2
-- ----------------------------

-- ----------------------------
-- Table structure for kanban_v1
-- ----------------------------
DROP TABLE IF EXISTS `kanban_v1`;
CREATE TABLE `kanban_v1`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '看板名称',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '看板介绍',
  `json` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '看板json',
  `imagesByte` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '缩略图',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 310 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '看板内容' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kanban_v1
-- ----------------------------

-- ----------------------------
-- Table structure for mk_pj_fin
-- ----------------------------
DROP TABLE IF EXISTS `mk_pj_fin`;
CREATE TABLE `mk_pj_fin`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `pj_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目编号',
  `status` int(0) NULL DEFAULT 0 COMMENT '状态',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `A` double NULL DEFAULT NULL COMMENT '单价（万）',
  `B` double NULL DEFAULT NULL COMMENT '总价（万）',
  `C` int(0) NULL DEFAULT NULL COMMENT '已开票（万）',
  `D` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收款比例',
  `E` int(0) NULL DEFAULT NULL COMMENT '已收款 （万）',
  `F` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '付款条件',
  `G` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开票条件',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 86 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mk_pj_fin
-- ----------------------------

-- ----------------------------
-- Table structure for mk_pj_fin_1
-- ----------------------------
DROP TABLE IF EXISTS `mk_pj_fin_1`;
CREATE TABLE `mk_pj_fin_1`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `pj_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目编号',
  `status` int(0) NULL DEFAULT 0 COMMENT '状态',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '项目财务信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mk_pj_fin_1
-- ----------------------------

-- ----------------------------
-- Table structure for mk_pj_info
-- ----------------------------
DROP TABLE IF EXISTS `mk_pj_info`;
CREATE TABLE `mk_pj_info`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `pj_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目编号',
  `status` int(0) NULL DEFAULT 0 COMMENT '状态',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `A` datetime(0) NULL DEFAULT NULL COMMENT '合同签订日期',
  `B` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '归属地',
  `C` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '合同号',
  `D` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目号',
  `E` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型',
  `F` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `G` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目名称',
  `H` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '对应方案号',
  `I` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '方案设计人',
  `J` int(0) NULL DEFAULT NULL COMMENT '数量',
  `K` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位',
  `L` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户名称',
  `M` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务经理',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 235 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mk_pj_info
-- ----------------------------

-- ----------------------------
-- Table structure for mk_pj_info_1
-- ----------------------------
DROP TABLE IF EXISTS `mk_pj_info_1`;
CREATE TABLE `mk_pj_info_1`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `pj_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目编号',
  `status` int(0) NULL DEFAULT 0 COMMENT '状态',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '项目信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mk_pj_info_1
-- ----------------------------

-- ----------------------------
-- Table structure for mk_pj_node
-- ----------------------------
DROP TABLE IF EXISTS `mk_pj_node`;
CREATE TABLE `mk_pj_node`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `pj_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目编号',
  `status` int(0) NULL DEFAULT 0 COMMENT '状态',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `A` datetime(0) NULL DEFAULT NULL COMMENT '生产任务下单日',
  `B` datetime(0) NULL DEFAULT NULL COMMENT '合同发货时间',
  `C` datetime(0) NULL DEFAULT NULL COMMENT '实际发货日期',
  `D` datetime(0) NULL DEFAULT NULL COMMENT '交付日期',
  `E` datetime(0) NULL DEFAULT NULL COMMENT 'FAT日期',
  `F` datetime(0) NULL DEFAULT NULL COMMENT 'QUALIFY日期',
  `G` datetime(0) NULL DEFAULT NULL COMMENT 'SAT日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 68 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mk_pj_node
-- ----------------------------

-- ----------------------------
-- Table structure for mk_pj_node_1
-- ----------------------------
DROP TABLE IF EXISTS `mk_pj_node_1`;
CREATE TABLE `mk_pj_node_1`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `pj_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目编号',
  `status` int(0) NULL DEFAULT 0 COMMENT '状态',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '项目时间节点' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mk_pj_node_1
-- ----------------------------

-- ----------------------------
-- Table structure for mk_pj_team
-- ----------------------------
DROP TABLE IF EXISTS `mk_pj_team`;
CREATE TABLE `mk_pj_team`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `pj_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目编号',
  `status` int(0) NULL DEFAULT 0 COMMENT '状态',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `A` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '技术负责人',
  `B` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目经理',
  `C` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户联系人',
  `D` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 65 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mk_pj_team
-- ----------------------------

-- ----------------------------
-- Table structure for mk_pj_team_1
-- ----------------------------
DROP TABLE IF EXISTS `mk_pj_team_1`;
CREATE TABLE `mk_pj_team_1`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `pj_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目编号',
  `status` int(0) NULL DEFAULT 0 COMMENT '状态',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '项目团队信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mk_pj_team_1
-- ----------------------------

-- ----------------------------
-- Table structure for mk_table
-- ----------------------------
DROP TABLE IF EXISTS `mk_table`;
CREATE TABLE `mk_table`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `table_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表名',
  `show_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表显示名称',
  `maxId` int(0) NULL DEFAULT NULL COMMENT '最大列id',
  `order_no` int(0) NULL DEFAULT 0 COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '营销信息表管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mk_table
-- ----------------------------

-- ----------------------------
-- Table structure for mk_table_1
-- ----------------------------
DROP TABLE IF EXISTS `mk_table_1`;
CREATE TABLE `mk_table_1`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `table_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表名',
  `show_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表显示名称',
  `maxId` int(0) NULL DEFAULT NULL COMMENT '最大列id',
  `order_no` int(0) NULL DEFAULT 0 COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '营销信息表管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mk_table_1
-- ----------------------------

-- ----------------------------
-- Table structure for mk_table_col
-- ----------------------------
DROP TABLE IF EXISTS `mk_table_col`;
CREATE TABLE `mk_table_col`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `table_id` int(0) NULL DEFAULT NULL COMMENT '表id',
  `col_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '列名',
  `col_type` int(0) NULL DEFAULT NULL COMMENT '列名类型：1:数字，2:字符串，3:时间。',
  `show_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '列显示名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 58 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mk_table_col
-- ----------------------------

-- ----------------------------
-- Table structure for mk_table_col_1
-- ----------------------------
DROP TABLE IF EXISTS `mk_table_col_1`;
CREATE TABLE `mk_table_col_1`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `table_id` int(0) NULL DEFAULT NULL COMMENT '表id',
  `col_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '列名',
  `col_type` int(0) NULL DEFAULT NULL COMMENT '列名类型：1:数字，2:字符串，3:时间。',
  `show_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '列显示名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '营销信息表的列' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mk_table_col_1
-- ----------------------------

-- ----------------------------
-- Table structure for mk_table_role
-- ----------------------------
DROP TABLE IF EXISTS `mk_table_role`;
CREATE TABLE `mk_table_role`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `role_id` int(0) NOT NULL COMMENT '角色id',
  `col_id` int(0) NULL DEFAULT NULL COMMENT '列名id',
  `auth_id` int(0) NULL DEFAULT NULL COMMENT '操作类型:0显示，1添加，2修改，3删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1162 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mk_table_role
-- ----------------------------

-- ----------------------------
-- Table structure for mk_table_role_1
-- ----------------------------
DROP TABLE IF EXISTS `mk_table_role_1`;
CREATE TABLE `mk_table_role_1`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `role_id` int(0) NOT NULL COMMENT '角色id',
  `col_id` int(0) NULL DEFAULT NULL COMMENT '列名id',
  `auth_id` int(0) NULL DEFAULT NULL COMMENT '操作类型:0显示，1添加，2修改，3删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '营销信息表的权限管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mk_table_role_1
-- ----------------------------

-- ----------------------------
-- Table structure for p_alarm_problems
-- ----------------------------
DROP TABLE IF EXISTS `p_alarm_problems`;
CREATE TABLE `p_alarm_problems`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `problem` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '问题',
  `establish_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `response_time` datetime(0) NULL DEFAULT NULL COMMENT '响应时间',
  `solve_time` datetime(0) NULL DEFAULT NULL COMMENT '解决时间',
  `establish_user_id` int(0) NULL DEFAULT NULL COMMENT '创建用户id',
  `problem_level_id` int(0) NULL DEFAULT NULL COMMENT '问题级别',
  `event_id` int(0) NULL DEFAULT NULL COMMENT '事件',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '历史问题' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of p_alarm_problems
-- ----------------------------

-- ----------------------------
-- Table structure for p_andon_issued_message
-- ----------------------------
DROP TABLE IF EXISTS `p_andon_issued_message`;
CREATE TABLE `p_andon_issued_message`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `fault_id` int(0) NULL DEFAULT NULL,
  `message_id` int(0) NULL DEFAULT NULL,
  `dt` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of p_andon_issued_message
-- ----------------------------

-- ----------------------------
-- Table structure for p_mes_bolt_t
-- ----------------------------
DROP TABLE IF EXISTS `p_mes_bolt_t`;
CREATE TABLE `p_mes_bolt_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `DT` datetime(0) NULL DEFAULT NULL COMMENT '日期',
  `TRANSFER` decimal(11, 0) NULL DEFAULT NULL COMMENT '上传标记',
  `BOLT_MODE` int(0) NULL DEFAULT NULL COMMENT '模式',
  `SN` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '总成',
  `ST` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工位',
  `T` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '扭矩值',
  `A` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角度值',
  `P` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '程序号',
  `C` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '循环号',
  `R` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '结果',
  `EQS` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `T_LIMIT` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '扭矩上限',
  `A_LIMIT` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角度上线',
  `WID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '员工号',
  `TID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '托盘号',
  `BOLT_NUM` int(0) NULL DEFAULT NULL COMMENT '螺栓编号',
  `BOLT_NAME` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '螺栓名称',
  `REWORK_FLAG` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '返工标记',
  `REWORK_ST` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '返工工位',
  `GUN_NO` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '枪号',
  `REASONS` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '返修原因',
  `Y` int(0) NOT NULL DEFAULT 0,
  `MATERIAL_INSTANCE_ID` int(0) NULL DEFAULT NULL COMMENT '物料实例id',
  `step` int(0) NOT NULL COMMENT '步序',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3670459 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of p_mes_bolt_t
-- ----------------------------

-- ----------------------------
-- Table structure for p_mes_discharge_t
-- ----------------------------
DROP TABLE IF EXISTS `p_mes_discharge_t`;
CREATE TABLE `p_mes_discharge_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT,
  `SN` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'SN',
  `MCNUM` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `STCODE` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '工位',
  `OPERATOR` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '调试员工',
  `STEPNO` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '步序',
  `INSTRUCTIONS` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '测试项',
  `TESTVALUE` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '测试值',
  `JUDGMENT` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `UNIT` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '单位',
  `ISPASS` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `RESULT` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '结果',
  `OPTIME` datetime(0) NULL DEFAULT NULL COMMENT '时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 226 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '充放电' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of p_mes_discharge_t
-- ----------------------------

-- ----------------------------
-- Table structure for p_mes_eol_t
-- ----------------------------
DROP TABLE IF EXISTS `p_mes_eol_t`;
CREATE TABLE `p_mes_eol_t`  (
  `ID` int(0) NOT NULL,
  `DT` datetime(0) NULL DEFAULT NULL,
  `ST` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `TT` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `SN` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `T1` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '开路电压测试\n',
  `T2` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '正极对壳体绝缘阻抗测试\n',
  `T3` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '负极对壳体绝缘阻抗测试\n',
  `T4` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '正极对壳体直流耐压测试\n',
  `T5` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '负极对壳体直流耐压测试\n',
  `T6` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `R` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of p_mes_eol_t
-- ----------------------------

-- ----------------------------
-- Table structure for p_mes_interface_t
-- ----------------------------
DROP TABLE IF EXISTS `p_mes_interface_t`;
CREATE TABLE `p_mes_interface_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT,
  `DT` datetime(0) NOT NULL,
  `MARK` int(0) NOT NULL,
  `ST` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `SN` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CONTENT` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of p_mes_interface_t
-- ----------------------------

-- ----------------------------
-- Table structure for p_mes_keypart_t
-- ----------------------------
DROP TABLE IF EXISTS `p_mes_keypart_t`;
CREATE TABLE `p_mes_keypart_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `DT` datetime(0) NULL DEFAULT NULL COMMENT '日期',
  `TRANSFER` int(0) NULL DEFAULT NULL COMMENT '上传标记',
  `KEYPART_MODE` int(0) NULL DEFAULT NULL COMMENT '模式',
  `ST` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '工位',
  `SN` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '总成',
  `WID` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '员工号',
  `TID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '托盘号',
  `SECOND_NUM` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '二级总成',
  `KEYPART_ID` int(0) NULL DEFAULT NULL,
  `KEYPART_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '物料名称',
  `KEYPART_PN` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '物料图号',
  `KEYPART_NUM` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '物料编码',
  `KEYPART_MODULE` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '模组',
  `KEYPART_REWORK_FG` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '返修标记',
  `KEYPART_REWORK_ST` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '返修工位',
  `MATERIAL_ID` int(0) NULL DEFAULT NULL COMMENT '物料ID',
  `MATERIAL_NUMBER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '物料数量',
  `REASONS` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `MATERIAL_INSTANCE_ID` int(0) NULL DEFAULT NULL COMMENT '物料实例id',
  `step` int(0) NULL DEFAULT NULL COMMENT '步序',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1938682 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of p_mes_keypart_t
-- ----------------------------

-- ----------------------------
-- Table structure for p_mes_leakage_t
-- ----------------------------
DROP TABLE IF EXISTS `p_mes_leakage_t`;
CREATE TABLE `p_mes_leakage_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `DT` datetime(0) NULL DEFAULT NULL COMMENT '日期',
  `ST` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '工位',
  `SN` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '总成号',
  `LEAKAGE_NAME` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '气密名称',
  `LEAKAGE_PV` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '压力',
  `LEAKAGE_LV` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '泄露值',
  `LEAKAGE_R` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '结果',
  `WID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '员工号',
  `TRANSFER` int(0) NULL DEFAULT NULL COMMENT '上传标记',
  `LEAKAGE_MODE` int(0) NULL DEFAULT NULL,
  `REWORK_FLAG` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '返修标记',
  `REASON` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '气密性信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of p_mes_leakage_t
-- ----------------------------

-- ----------------------------
-- Table structure for p_mes_log_t
-- ----------------------------
DROP TABLE IF EXISTS `p_mes_log_t`;
CREATE TABLE `p_mes_log_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `DT` datetime(0) NULL DEFAULT NULL COMMENT '时间',
  `LOGLEVEL` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日志类型',
  `LOGGER` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '对应产线工位',
  `MACHINENAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电脑名称',
  `MESSAGE` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '错误信息',
  `APPDOMAIN` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '客户端名称',
  `ASSEMBLY_VERSION` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '版本',
  `BASEDIR` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `CALLSITE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `CALLSITE_LINENUMBER` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `PROCESSID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `PROCESSNAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `STACKTRACE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `COUNTER` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `EXCEPTION` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `THREADID` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `THREADNAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of p_mes_log_t
-- ----------------------------

-- ----------------------------
-- Table structure for p_mes_material_pull_t
-- ----------------------------
DROP TABLE IF EXISTS `p_mes_material_pull_t`;
CREATE TABLE `p_mes_material_pull_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `DT` datetime(0) NULL DEFAULT NULL COMMENT '叫料日期',
  `MATERIAL_ID` int(0) NULL DEFAULT NULL COMMENT '物料ID',
  `MATERIAL_NUMBER` int(0) NULL DEFAULT NULL COMMENT '物料数量',
  `OPERATOR_USER` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '叫料人员',
  `SEND_USER` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '送料人员',
  `SEND_NUMBER` int(0) NULL DEFAULT NULL COMMENT '送料数量',
  `SEND_DT` datetime(0) NULL DEFAULT NULL COMMENT '送料时间',
  `STATUS` int(0) NULL DEFAULT NULL COMMENT '状态  0：正常  1：缺料  2：强制关闭 3:来料质量缺陷 4：退料 5：批次冻结',
  `NOTE` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `LINE_ID` int(0) NULL DEFAULT NULL COMMENT '产线ID',
  `STATION_ID` int(0) NULL DEFAULT NULL COMMENT '工位ID',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '物料拉动永久性表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of p_mes_material_pull_t
-- ----------------------------

-- ----------------------------
-- Table structure for p_mes_plan_print_t
-- ----------------------------
DROP TABLE IF EXISTS `p_mes_plan_print_t`;
CREATE TABLE `p_mes_plan_print_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `DT` datetime(0) NOT NULL COMMENT '日期',
  `SN` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '总成号',
  `PLAN_ID` int(0) NULL DEFAULT NULL COMMENT '计划ID',
  `LINE_ID` int(0) NOT NULL COMMENT '产线ID',
  `PRODUCTION_ID` int(0) NOT NULL COMMENT '产品ID',
  `SERIAL_NO` int(0) NOT NULL COMMENT '序列号',
  `PRINT_FLAG` int(0) NOT NULL COMMENT '是否打印 0：否  1：是',
  `WORK_ORDER_ID` int(0) NULL DEFAULT NULL COMMENT '工单ID',
  `R_MES_PLAN_PRINT_ID` int(0) NULL DEFAULT NULL COMMENT '工单ID',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1672 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '计划打印表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of p_mes_plan_print_t
-- ----------------------------

-- ----------------------------
-- Table structure for p_mes_station_pass_t
-- ----------------------------
DROP TABLE IF EXISTS `p_mes_station_pass_t`;
CREATE TABLE `p_mes_station_pass_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `DT` datetime(0) NOT NULL COMMENT '时间',
  `SN` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '总成',
  `ST` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '工位',
  `EMP` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '员工号',
  `TID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '托盘号',
  `STATUS` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '总成状态',
  `PASS_FLAG` int(0) NULL DEFAULT NULL COMMENT '1：进站  2：出站',
  `LINE_ID` int(0) NULL DEFAULT NULL COMMENT '所属产线',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14171 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '过站信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of p_mes_station_pass_t
-- ----------------------------

-- ----------------------------
-- Table structure for p_mes_station_serial_flag_t
-- ----------------------------
DROP TABLE IF EXISTS `p_mes_station_serial_flag_t`;
CREATE TABLE `p_mes_station_serial_flag_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `SN` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '总成号',
  `DT` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `ST` int(0) NULL DEFAULT NULL COMMENT '工位id',
  `SERIAL` int(0) NULL DEFAULT NULL COMMENT '序号',
  `FLAG` int(0) NULL DEFAULT 0 COMMENT '完成标记 0未完成 1完成',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 732 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '工位完成情况永久表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of p_mes_station_serial_flag_t
-- ----------------------------

-- ----------------------------
-- Table structure for p_mes_tracking_t
-- ----------------------------
DROP TABLE IF EXISTS `p_mes_tracking_t`;
CREATE TABLE `p_mes_tracking_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `DT` datetime(0) NULL DEFAULT NULL COMMENT '日期',
  `ST` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '当前工位',
  `BST` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '前工位',
  `SN` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '总成',
  `ENGINESN` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '下线类别：0：正常下线 1：维修下线 2：报废',
  `GEARBOXSN` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '当前工位下标（前工位）',
  `TYPENAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '产品类别',
  `TRAYNUM` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '托盘号',
  `PRODUCTNUM` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '产品编号（与PLC交互用）',
  `STATUS` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '产品状态',
  `DIS` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '描述',
  `PLAN_ID` int(0) NULL DEFAULT NULL COMMENT '计划ID',
  `OFFLINE_DT` datetime(0) NULL DEFAULT NULL COMMENT '下线时间',
  `REWORK_FLAG` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '返工标记',
  `PRODUCTION_ID` int(0) NULL DEFAULT NULL COMMENT '产品ID',
  `LINE_ID` int(0) NULL DEFAULT NULL COMMENT '产线ID',
  `WORK_ORDER_ID` int(0) NULL DEFAULT NULL COMMENT '工单ID',
  `UDT` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `STATE` int(0) NULL DEFAULT NULL COMMENT '状态 1 排队 2 活动 3 完成 4 暂停 5 报废 6 无效 7 删除',
  `PLAN_NUM` int(0) NULL DEFAULT NULL COMMENT '计划数量',
  `IN_PROCESS_NUM` int(0) NULL DEFAULT NULL COMMENT '在制数',
  `COMPLETE_NUM` int(0) NULL DEFAULT NULL COMMENT '完工数',
  `SCRAP_NUM` int(0) NULL DEFAULT NULL COMMENT '报废数',
  `CREATOR` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `REVISER` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `TOTAL_RECIPE_ID` int(0) NULL DEFAULT NULL COMMENT '总配方id',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `word_order_id_index`(`WORK_ORDER_ID`) USING BTREE,
  INDEX `SN_INDEX`(`SN`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 419270 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '产品下线表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of p_mes_tracking_t
-- ----------------------------

-- ----------------------------
-- Table structure for p_mes_weigh_t
-- ----------------------------
DROP TABLE IF EXISTS `p_mes_weigh_t`;
CREATE TABLE `p_mes_weigh_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `DT` datetime(0) NULL DEFAULT NULL COMMENT '日期',
  `ST` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '工位',
  `SN` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '总成',
  `WID` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '员工',
  `WEIGH` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '重量',
  `WEIGH1` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '重量1（预留）',
  `DIS` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `TRANSFER` int(0) NULL DEFAULT NULL COMMENT '上传标记',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '称重记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of p_mes_weigh_t
-- ----------------------------

-- ----------------------------
-- Table structure for p_mes_workorder_detail_t
-- ----------------------------
DROP TABLE IF EXISTS `p_mes_workorder_detail_t`;
CREATE TABLE `p_mes_workorder_detail_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `DT` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `WORKORDER_ID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '工单名称',
  `ORDER_NUMBER` int(0) NOT NULL COMMENT '订单数量',
  `ONLINE_NUMBER` int(0) NULL DEFAULT NULL COMMENT '上线数量',
  `OFFLINE_NUMBER` int(0) NULL DEFAULT NULL COMMENT '下线数量',
  `DEFFECT_NUMBER` int(0) NULL DEFAULT NULL COMMENT '缺陷数量',
  `LINE_ID` int(0) NULL DEFAULT NULL COMMENT '产线ID',
  `TEAM_ID` int(0) NULL DEFAULT NULL COMMENT '班次ID',
  `LEVEL_NO` int(0) NULL DEFAULT NULL COMMENT '优先级',
  `STATUS` int(0) NULL DEFAULT NULL COMMENT '状态  0-创建 1-提交 2-下达 3-投产 4-关闭  6-暂停',
  `ALTER_DT` datetime(0) NULL DEFAULT NULL COMMENT '修改日期',
  `PLAN_ID` int(0) NULL DEFAULT NULL COMMENT '计划ID',
  `CREATE_BARCODE_FLAG` int(0) NULL DEFAULT NULL,
  `PRINTE_NUMBER` int(0) NULL DEFAULT NULL COMMENT '生成条码数量',
  `SFC_CODE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生产控制序列编码',
  `LINE_CODE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产线编码',
  `SFC_TYPE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '\"生产控制序列类型：0：正常生产；1：拆解返修；\n2：报废返修\"',
  `OLD_SFC_CODE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '原生产控制序列编码',
  `OLD_SN_CODE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '原pack码',
  `R_MES_WORKORDER_DETAIL_ID` int(0) NULL DEFAULT NULL COMMENT '原有工单',
  `COMPLETE_NUMBER` int(0) NULL DEFAULT NULL COMMENT '完成数量',
  `PLAN_START_TIME` datetime(0) NULL DEFAULT NULL COMMENT '计划开始时间',
  `PLAN_END_TIME` datetime(0) NULL DEFAULT NULL COMMENT '计划结束时间',
  `ACTUAL_START_TIME` datetime(0) NULL DEFAULT NULL COMMENT '实际开始时间',
  `ACTUAL_END_TIME` datetime(0) NULL DEFAULT NULL COMMENT '实际结束时间',
  `ROUTING_ID` int(0) NULL DEFAULT NULL COMMENT '工艺路线id',
  `BARCODE_RULE_ID` int(0) NULL DEFAULT NULL COMMENT '条码规则id',
  `BOM_ID` int(0) NULL DEFAULT NULL COMMENT 'BOMid',
  `PRODUCT_ID` int(0) NULL DEFAULT NULL COMMENT '产品id',
  `TOTAL_RECIPE_ID` int(0) NULL DEFAULT NULL COMMENT '配方id',
  `OK_NUMBER` int(0) NULL DEFAULT NULL COMMENT '合格数量',
  `ORDER_ID` int(0) NULL DEFAULT NULL COMMENT '订单id',
  `ORDERRECORD_ID` int(0) NULL DEFAULT NULL COMMENT '订单记录id',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 179 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '计划明细  订单永久性表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of p_mes_workorder_detail_t
-- ----------------------------

-- ----------------------------
-- Table structure for p_wms_in_taskqueue_t
-- ----------------------------
DROP TABLE IF EXISTS `p_wms_in_taskqueue_t`;
CREATE TABLE `p_wms_in_taskqueue_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `DT` datetime(0) NULL DEFAULT NULL COMMENT '日期',
  `LIST_NO` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单号',
  `TRAY` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '托盘号',
  `FLAG` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '动作标记',
  `OVER_DT` datetime(0) NULL DEFAULT NULL COMMENT '完成时间',
  `LOCATION_ID` int(0) NULL DEFAULT NULL COMMENT '库位id',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 63 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '入库队列永久记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of p_wms_in_taskqueue_t
-- ----------------------------

-- ----------------------------
-- Table structure for p_wms_out_taskqueue_t
-- ----------------------------
DROP TABLE IF EXISTS `p_wms_out_taskqueue_t`;
CREATE TABLE `p_wms_out_taskqueue_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `DT` datetime(0) NULL DEFAULT NULL COMMENT '日期',
  `LIST_NO` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单号',
  `TRAY` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '托盘号',
  `STATION_ID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '目标工位ID',
  `FLAG` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '动作标记',
  `OVER_DT` datetime(0) NULL DEFAULT NULL COMMENT '完成时间',
  `LOCATION_ID` int(0) NULL DEFAULT NULL COMMENT '库位表',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2947 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '出库队列永久记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of p_wms_out_taskqueue_t
-- ----------------------------

-- ----------------------------
-- Table structure for p_wms_storage_detail_t
-- ----------------------------
DROP TABLE IF EXISTS `p_wms_storage_detail_t`;
CREATE TABLE `p_wms_storage_detail_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT,
  `DT` datetime(0) NULL DEFAULT NULL COMMENT '入库时间',
  `MATERIAL_ID` int(0) NULL DEFAULT NULL COMMENT '物料id',
  `MATERIAL_NUMBER` int(0) NULL DEFAULT NULL COMMENT '数量，用字符表示，如果是出库“-”号，直接是数字',
  `MATERIAL_CODE` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料条码',
  `AREA_ID` int(0) NULL DEFAULT NULL COMMENT '区域id',
  `RESERVOIR_AREA_ID` int(0) NULL DEFAULT NULL COMMENT '库区id',
  `LOCATION_ID` int(0) NULL DEFAULT NULL COMMENT '库位id',
  `NOTE` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `LIST_NO` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单号，根据单号查找明细',
  `PROJECT_ID` int(0) NULL DEFAULT NULL COMMENT '所属项目',
  `SUPPLIER_ID` int(0) NULL DEFAULT NULL COMMENT '供应商id',
  `TRAY` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '托盘号',
  `WAREHOUSE_ID` int(0) NULL DEFAULT NULL COMMENT '仓库ID',
  `YN_SHIFT` int(0) NULL DEFAULT NULL COMMENT '是否移库  0：否、1：是',
  `ISSUE_OR_RECEIPT` int(0) NULL DEFAULT NULL COMMENT '出库还是入库 0：出库  1：入库',
  `STATION_ID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '目标工位ID',
  `bar_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `MATERIAL_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料名称',
  `WAREHOUSE_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '仓库名称',
  `AREA_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区域名称',
  `RESERVOIR_AREA_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '库区名称',
  `LOCATION_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '库位名称',
  `PROJECT_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目名称',
  `STATION_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工位名称',
  `materialNumberId` int(0) NULL DEFAULT NULL COMMENT '库存表id',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `p_wms_storage_detail_t`(`MATERIAL_ID`, `AREA_ID`, `RESERVOIR_AREA_ID`, `LOCATION_ID`, `LIST_NO`, `PROJECT_ID`, `WAREHOUSE_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24349 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '库存明细表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of p_wms_storage_detail_t
-- ----------------------------

-- ----------------------------
-- Table structure for pm_field
-- ----------------------------
DROP TABLE IF EXISTS `pm_field`;
CREATE TABLE `pm_field`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '自增',
  `pm_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '项目编码或任务编码',
  `pf_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '标题',
  `pf_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '值',
  `pf_type` int(0) NOT NULL COMMENT '类型(1：构建，2：初始化，3：结束)',
  `cdt` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `cu` int(0) NULL DEFAULT NULL COMMENT '创建人',
  `udt` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `uu` int(0) NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '项目字段表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pm_field
-- ----------------------------

-- ----------------------------
-- Table structure for pm_field_role
-- ----------------------------
DROP TABLE IF EXISTS `pm_field_role`;
CREATE TABLE `pm_field_role`  (
  `pm_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '项目编码',
  `pf_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字段名',
  `user_list` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务成员、任务负责人、具体人、项目负责人、项目成员',
  `operate` int(0) NOT NULL COMMENT '操作（1：查看，2：编辑）'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '项目字段权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pm_field_role
-- ----------------------------

-- ----------------------------
-- Table structure for pm_field_task
-- ----------------------------
DROP TABLE IF EXISTS `pm_field_task`;
CREATE TABLE `pm_field_task`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '自增',
  `pt_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '项目编码或任务编码',
  `pf_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '标题',
  `pf_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '值',
  `pf_type` int(0) NOT NULL COMMENT '类型(1：构建，2：初始化，3：结束)',
  `cdt` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `cu` int(0) NULL DEFAULT NULL COMMENT '创建人',
  `udt` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `uu` int(0) NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '任务字段表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pm_field_task
-- ----------------------------

-- ----------------------------
-- Table structure for pm_field_task_role
-- ----------------------------
DROP TABLE IF EXISTS `pm_field_task_role`;
CREATE TABLE `pm_field_task_role`  (
  `pt_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务编码',
  `pf_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字段名',
  `user_list` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务成员、任务负责人、具体人、项目负责人、项目成员',
  `operate` int(0) NOT NULL COMMENT '操作（1：查看，2：编辑）'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '任务字段权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pm_field_task_role
-- ----------------------------

-- ----------------------------
-- Table structure for pm_info
-- ----------------------------
DROP TABLE IF EXISTS `pm_info`;
CREATE TABLE `pm_info`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '自增',
  `code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '项目编码',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '项目名',
  `status` int(0) NULL DEFAULT NULL COMMENT '状态(1：构建，2：初始化，3：结束)',
  `principal_list` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '负责人',
  `member_list` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '成员',
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '项目类型',
  `cdt` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `cu` int(0) NULL DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '项目表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pm_info
-- ----------------------------

-- ----------------------------
-- Table structure for pm_stage
-- ----------------------------
DROP TABLE IF EXISTS `pm_stage`;
CREATE TABLE `pm_stage`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '自增',
  `code` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '阶段code。自动生成4位数字',
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '项目名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '项目阶段表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pm_stage
-- ----------------------------

-- ----------------------------
-- Table structure for pm_task
-- ----------------------------
DROP TABLE IF EXISTS `pm_task`;
CREATE TABLE `pm_task`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '自增',
  `pm_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '项目code',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务名称',
  `status` int(0) NULL DEFAULT NULL COMMENT '状态(1：构建，2：初始化，3：结束)',
  `principal_list` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '负责人',
  `member_list` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '成员',
  `sdt` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `edt` datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
  `construction_period` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '工期',
  `pm_stage_code` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '阶段code',
  `act_sdt` datetime(0) NULL DEFAULT NULL COMMENT '实际开始时间',
  `act_edt` datetime(0) NULL DEFAULT NULL COMMENT '实际结束时间',
  `schedule` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '进度',
  `front_task_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '前置任务（任务名称1，任务名称2）',
  `cdt` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `cu` int(0) NULL DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '任务表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pm_task
-- ----------------------------

-- ----------------------------
-- Table structure for process_problem_submit
-- ----------------------------
DROP TABLE IF EXISTS `process_problem_submit`;
CREATE TABLE `process_problem_submit`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `DEPARTMENT` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门',
  `DESCRIBES` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '描述',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of process_problem_submit
-- ----------------------------

-- ----------------------------
-- Table structure for r_alarm_problems
-- ----------------------------
DROP TABLE IF EXISTS `r_alarm_problems`;
CREATE TABLE `r_alarm_problems`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `problem` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '问题',
  `establish_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `response_time` datetime(0) NULL DEFAULT NULL COMMENT '响应时间',
  `solve_time` datetime(0) NULL DEFAULT NULL COMMENT '解决时间',
  `establish_user_id` int(0) NULL DEFAULT NULL COMMENT '创建用户id',
  `problem_level_id` int(0) NULL DEFAULT NULL COMMENT '问题级别',
  `event_id` int(0) NULL DEFAULT NULL COMMENT '事件',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '当前问题' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of r_alarm_problems
-- ----------------------------

-- ----------------------------
-- Table structure for r_andon_issued_message
-- ----------------------------
DROP TABLE IF EXISTS `r_andon_issued_message`;
CREATE TABLE `r_andon_issued_message`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `fault_id` int(0) NULL DEFAULT NULL,
  `message_id` int(0) NULL DEFAULT NULL,
  `dt` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of r_andon_issued_message
-- ----------------------------

-- ----------------------------
-- Table structure for r_andon_steel_platform
-- ----------------------------
DROP TABLE IF EXISTS `r_andon_steel_platform`;
CREATE TABLE `r_andon_steel_platform`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `dt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '扫码时间',
  `sn` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '总成号',
  `productId` int(0) NULL DEFAULT NULL COMMENT '产品id',
  `productName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品名称',
  `productNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品编码',
  `productModel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品型号',
  `productAbbreviation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品简称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of r_andon_steel_platform
-- ----------------------------

-- ----------------------------
-- Table structure for r_asm_electricdetection_t
-- ----------------------------
DROP TABLE IF EXISTS `r_asm_electricdetection_t`;
CREATE TABLE `r_asm_electricdetection_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT,
  `SN` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `DT` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `CHECK_BURMER` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `CHECK_COMMUNICATION` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `CHECK_ALLVOLTAGE` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `VALUE_ALLVOLTAGE` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `CHECK_SINGLEVOLTAGE` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `VALUE_CELLVMAX` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `VALUE_CELLVMIN` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `VALUE_CELLVDIF` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `CHECK_TEMPERATURE` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `VALUE_CELLTMAX` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `VALUE_CELLTMIN` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `VALUE_CELLTDIF` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `CHECK_PLUS_MINUS` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `CHECK_PRIMING` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `CHECK_TRICKLECHARGE` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `CHECK_HIGHHANDED` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `CHECK_FAUSE` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `CHECK_HARDBORD` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of r_asm_electricdetection_t
-- ----------------------------

-- ----------------------------
-- Table structure for r_lsl_inventory_records_t
-- ----------------------------
DROP TABLE IF EXISTS `r_lsl_inventory_records_t`;
CREATE TABLE `r_lsl_inventory_records_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cdt` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `udt` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `productId` int(0) NULL DEFAULT NULL COMMENT '产品id',
  `lineId` int(0) NULL DEFAULT NULL COMMENT '产线id',
  `stationId` int(0) NULL DEFAULT NULL COMMENT '工位id',
  `rockNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '货架号',
  `batchCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '批次条码',
  `materialCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '物料条码',
  `materialNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '物料编号',
  `materialName` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '物料名称',
  `type` int(0) NULL DEFAULT NULL COMMENT '物料类型 1 批次 2 单个',
  `quantity` int(0) NULL DEFAULT NULL COMMENT '精确数量',
  `status` int(0) NULL DEFAULT NULL COMMENT '状态 0 未用，1 已用，2 报废，3 损坏，4 挂失',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '线边仓库存记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of r_lsl_inventory_records_t
-- ----------------------------

-- ----------------------------
-- Table structure for r_lsl_material_request_t
-- ----------------------------
DROP TABLE IF EXISTS `r_lsl_material_request_t`;
CREATE TABLE `r_lsl_material_request_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cdt` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `udt` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `billNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '单据号',
  `productId` int(0) NULL DEFAULT NULL COMMENT '产品id',
  `lineId` int(0) NULL DEFAULT NULL COMMENT '产线id',
  `stationId` int(0) NULL DEFAULT NULL COMMENT '工位id',
  `status` int(0) NULL DEFAULT 0 COMMENT '状态 0 待处理、1 拣货中、2 已出库、3 已确认、4 已取消、5已拒收',
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建者',
  `picker` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '捡料员',
  `pickTime` datetime(0) NULL DEFAULT NULL COMMENT '捡料时间',
  `collector` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '收料员',
  `collectTime` datetime(0) NULL DEFAULT NULL COMMENT '收料时间',
  `serialNumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '流水号',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '取消描述',
  `rejecter` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '拒收人员',
  `refusedTime` datetime(0) NULL DEFAULT NULL COMMENT '拒收时间',
  `refuseDescribe` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '拒收描述',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 192 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '线边仓物料请求表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of r_lsl_material_request_t
-- ----------------------------

-- ----------------------------
-- Table structure for r_lsl_material_response_t
-- ----------------------------
DROP TABLE IF EXISTS `r_lsl_material_response_t`;
CREATE TABLE `r_lsl_material_response_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cdt` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `udt` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `requestDetailId` int(0) NULL DEFAULT NULL COMMENT '要料请求详情Id',
  `materialBatch` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '物料批次',
  `materialCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '物料单个条码',
  `quantity` int(0) NULL DEFAULT NULL COMMENT '实际数量',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 614 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '线边仓物料返回表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of r_lsl_material_response_t
-- ----------------------------

-- ----------------------------
-- Table structure for r_mes_bolt_t
-- ----------------------------
DROP TABLE IF EXISTS `r_mes_bolt_t`;
CREATE TABLE `r_mes_bolt_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT,
  `DT` datetime(0) NULL DEFAULT NULL,
  `TRANSFER` int(0) NULL DEFAULT NULL,
  `BOLT_MODE` int(0) NULL DEFAULT NULL,
  `SN` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `ST` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `T` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `A` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `P` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `C` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `R` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `EQS` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `T_LIMIT` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `A_LIMIT` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `WID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `TID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `BOLT_NUM` int(0) NULL DEFAULT NULL,
  `BOLT_NAME` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `REWORK_FLAG` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `REWORK_ST` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `GUN_NO` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '枪号',
  `REASONS` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `Y` int(0) NOT NULL DEFAULT 0,
  `MATERIAL_INSTANCE_ID` int(0) NULL DEFAULT NULL COMMENT '物料实例id',
  `step` int(0) NULL DEFAULT NULL COMMENT '步序',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2505868 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of r_mes_bolt_t
-- ----------------------------

-- ----------------------------
-- Table structure for r_mes_keypart_t
-- ----------------------------
DROP TABLE IF EXISTS `r_mes_keypart_t`;
CREATE TABLE `r_mes_keypart_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT,
  `DT` datetime(0) NULL DEFAULT NULL,
  `TRANSFER` int(0) NULL DEFAULT NULL,
  `KEYPART_MODE` int(0) NULL DEFAULT NULL,
  `ST` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `SN` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `WID` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `TID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `SECOND_NUM` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `KEYPART_ID` int(0) NULL DEFAULT NULL,
  `KEYPART_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `KEYPART_PN` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `KEYPART_NUM` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `KEYPART_MODULE` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `KEYPART_REWORK_FG` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `KEYPART_REWORK_ST` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `MATERIAL_ID` int(0) NULL DEFAULT NULL COMMENT '物料ID',
  `MATERIAL_NUMBER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '物料数量',
  `REASONS` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '拆解原因',
  `MATERIAL_INSTANCE_ID` int(0) NULL DEFAULT NULL COMMENT '物料实例id',
  `step` int(0) NULL DEFAULT NULL COMMENT '步序',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1938700 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of r_mes_keypart_t
-- ----------------------------

-- ----------------------------
-- Table structure for r_mes_leakage_t
-- ----------------------------
DROP TABLE IF EXISTS `r_mes_leakage_t`;
CREATE TABLE `r_mes_leakage_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT,
  `DT` datetime(0) NULL DEFAULT NULL,
  `ST` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `SN` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `LEAKAGE_NAME` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `LEAKAGE_PV` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `LEAKAGE_LV` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `LEAKAGE_R` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `WID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `TRANSFER` int(0) NULL DEFAULT NULL,
  `LEAKAGE_MODE` int(0) NULL DEFAULT NULL,
  `REWORK_FLAG` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `REASON` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1803572 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '气密性测试' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of r_mes_leakage_t
-- ----------------------------

-- ----------------------------
-- Table structure for r_mes_material_pull_t
-- ----------------------------
DROP TABLE IF EXISTS `r_mes_material_pull_t`;
CREATE TABLE `r_mes_material_pull_t`  (
  `ID` int(0) NOT NULL COMMENT '主键',
  `DT` datetime(0) NULL DEFAULT NULL COMMENT '叫料日期',
  `MATERIAL_ID` decimal(65, 30) NULL DEFAULT NULL COMMENT '物料ID',
  `MATERIAL_NUMBER` decimal(65, 30) NULL DEFAULT NULL COMMENT '物料数量',
  `OPERATOR_USER` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '叫料人员',
  `SEND_USER` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '送料人员',
  `SEND_NUMBER` decimal(65, 30) NULL DEFAULT NULL COMMENT '送料数量',
  `SEND_DT` datetime(0) NULL DEFAULT NULL COMMENT '送料时间',
  `STATUS` decimal(65, 30) NULL DEFAULT NULL COMMENT '状态  0：正常  1：缺料  2：强制关闭 3:来料质量缺陷 4：退料 5：批次冻结',
  `NOTE` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `STATION_ID` decimal(65, 30) NULL DEFAULT NULL COMMENT '工位ID',
  `LINE_ID` decimal(65, 30) NULL DEFAULT NULL COMMENT '产线ID',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '物料拉动实时表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of r_mes_material_pull_t
-- ----------------------------

-- ----------------------------
-- Table structure for r_mes_plan_print_t
-- ----------------------------
DROP TABLE IF EXISTS `r_mes_plan_print_t`;
CREATE TABLE `r_mes_plan_print_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `DT` datetime(0) NOT NULL COMMENT '日期',
  `SN` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '总成号',
  `PLAN_ID` int(0) NULL DEFAULT NULL COMMENT '计划ID',
  `LINE_ID` int(0) NOT NULL COMMENT '产线ID',
  `PRODUCTION_ID` int(0) NOT NULL COMMENT '产品ID',
  `SERIAL_NO` int(0) NOT NULL COMMENT '序列号',
  `PRINT_FLAG` int(0) NOT NULL COMMENT '是否打印标记  0：否  1：是',
  `WORK_ORDER_ID` int(0) NULL DEFAULT NULL COMMENT '工单ID',
  `quantity` int(0) NULL DEFAULT NULL COMMENT '数量',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1749 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '临时计划打印表(SN表)' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of r_mes_plan_print_t
-- ----------------------------

-- ----------------------------
-- Table structure for r_mes_rework_way_t
-- ----------------------------
DROP TABLE IF EXISTS `r_mes_rework_way_t`;
CREATE TABLE `r_mes_rework_way_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `DT` datetime(0) NOT NULL COMMENT '日期',
  `SN` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '总成号',
  `ST_NAME` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '工位名称',
  `ST_ID` int(0) NOT NULL COMMENT '工位ID',
  `SERIAL_NO` int(0) NOT NULL COMMENT '序列号',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '返修路线表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of r_mes_rework_way_t
-- ----------------------------

-- ----------------------------
-- Table structure for r_mes_sn_t
-- ----------------------------
DROP TABLE IF EXISTS `r_mes_sn_t`;
CREATE TABLE `r_mes_sn_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cdt` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `udt` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `sn` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '总成',
  `status` int(0) NULL DEFAULT NULL COMMENT '状态 1 排队 2 活动 3 完成 4 暂停 5 报废 6 无效 7 删除',
  `workorderId` int(0) NULL DEFAULT NULL COMMENT '工单id',
  `planNum` int(0) NULL DEFAULT NULL COMMENT '计划数量',
  `inProcessNum` int(0) NULL DEFAULT NULL COMMENT '在制数',
  `completeNum` int(0) NULL DEFAULT NULL COMMENT '完工数',
  `scrapNum` int(0) NULL DEFAULT NULL COMMENT '报废数',
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建人',
  `reviser` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '修改人',
  `bomId` int(0) NULL DEFAULT NULL COMMENT 'BOMid',
  `routingId` int(0) NULL DEFAULT NULL COMMENT '工艺路线id',
  `recipeId` int(0) NULL DEFAULT NULL COMMENT '总配方id',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'SN表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of r_mes_sn_t
-- ----------------------------

-- ----------------------------
-- Table structure for r_mes_station_serial_flag_t
-- ----------------------------
DROP TABLE IF EXISTS `r_mes_station_serial_flag_t`;
CREATE TABLE `r_mes_station_serial_flag_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `SN` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '总成号',
  `DT` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `ST` int(0) NULL DEFAULT NULL COMMENT '工位id',
  `SERIAL` int(0) NULL DEFAULT NULL COMMENT '序号',
  `FLAG` int(0) NULL DEFAULT 0 COMMENT '完成标记 0未完成 1完成',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 732 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '工位完成情况表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of r_mes_station_serial_flag_t
-- ----------------------------

-- ----------------------------
-- Table structure for r_mes_step_t
-- ----------------------------
DROP TABLE IF EXISTS `r_mes_step_t`;
CREATE TABLE `r_mes_step_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `DT` datetime(0) NULL DEFAULT NULL COMMENT '日期',
  `ST` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工位',
  `SN` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '总成',
  `STEP_NO` int(0) NULL DEFAULT NULL COMMENT '步序',
  `EMP` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '员工',
  `LINE_NAME` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产线名称',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 222 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '步序缓存表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of r_mes_step_t
-- ----------------------------

-- ----------------------------
-- Table structure for r_mes_tracking_t
-- ----------------------------
DROP TABLE IF EXISTS `r_mes_tracking_t`;
CREATE TABLE `r_mes_tracking_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `DT` datetime(0) NULL DEFAULT NULL COMMENT '日期',
  `ST` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '当前工位',
  `BST` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '前工位',
  `SN` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '总成',
  `ENGINESN` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '下线类型：0：正常下线 1：维修下线 2：报废 3：返工',
  `GEARBOXSN` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '当前工位下标',
  `TYPENAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '产品类别',
  `TRAYNUM` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '托盘号',
  `PRODUCTNUM` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '产品编号（PLC用）',
  `STATUS` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '产品状态',
  `DIS` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '产品描述',
  `PLAN_ID` int(0) NULL DEFAULT NULL COMMENT '计划ID',
  `REWORK_FLAG` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '返修标记',
  `PRODUCTION_ID` int(0) NULL DEFAULT NULL COMMENT '产品ID',
  `LINE_ID` int(0) NULL DEFAULT NULL COMMENT '产线ID',
  `WORK_ORDER_ID` int(0) NULL DEFAULT NULL COMMENT '工单ID',
  `UDT` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `STATE` int(0) NULL DEFAULT 1 COMMENT '状态 1 排队 2 活动 3 完成 4 暂停 5 报废 6 无效 7 删除',
  `PLAN_NUM` int(0) NULL DEFAULT 1 COMMENT '计划数量',
  `IN_PROCESS_NUM` int(0) NULL DEFAULT 0 COMMENT '在制数',
  `COMPLETE_NUM` int(0) NULL DEFAULT 0 COMMENT '完工数',
  `SCRAP_NUM` int(0) NULL DEFAULT 0 COMMENT '报废数',
  `CREATOR` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `REVISER` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `TOTAL_RECIPE_ID` int(0) NULL DEFAULT NULL COMMENT '总配方id',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1658 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '总成运行表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of r_mes_tracking_t
-- ----------------------------

-- ----------------------------
-- Table structure for r_mes_workorder_detail_t
-- ----------------------------
DROP TABLE IF EXISTS `r_mes_workorder_detail_t`;
CREATE TABLE `r_mes_workorder_detail_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `DT` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `WORKORDER_ID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '工单编号',
  `ORDER_NUMBER` int(0) NOT NULL COMMENT '订单数量',
  `ONLINE_NUMBER` int(0) NULL DEFAULT 0 COMMENT '上线数量',
  `OFFLINE_NUMBER` int(0) NULL DEFAULT 0 COMMENT '下线数量',
  `DEFFECT_NUMBER` int(0) NULL DEFAULT 0 COMMENT '缺陷数量',
  `LINE_ID` int(0) NULL DEFAULT NULL COMMENT '产线ID',
  `TEAM_ID` int(0) NULL DEFAULT NULL COMMENT '班次ID',
  `LEVEL_NO` int(0) NOT NULL COMMENT '优先级',
  `STATUS` int(0) NULL DEFAULT NULL COMMENT '状态  0-新建；1：开始；2：暂停；3：完成；4：关闭',
  `ALTER_DT` datetime(0) NULL DEFAULT NULL COMMENT '修改日期',
  `PLAN_ID` int(0) NULL DEFAULT NULL COMMENT '计划ID',
  `CREATE_BARCODE_FLAG` int(0) NULL DEFAULT NULL COMMENT '生成条码标记  0：没有生成  1：已生成',
  `PRINTE_NUMBER` int(0) NOT NULL COMMENT '生成条码数量',
  `SFC_CODE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生产控制序列编码',
  `LINE_CODE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产线编码',
  `SFC_TYPE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生产控制序列类型：0：正常生产；1：拆解返修；\n2：报废返修',
  `OLD_SFC_CODE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '原生产控制序列编码',
  `OLD_SN_CODE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '原pack码',
  `COMPLETE_NUMBER` int(0) NULL DEFAULT 0 COMMENT '完成数量',
  `REMAIND_NUMBER` int(0) NULL DEFAULT NULL COMMENT '剩余数量',
  `PLAN_START_TIME` datetime(0) NULL DEFAULT NULL COMMENT '计划开始时间',
  `PLAN_END_TIME` datetime(0) NULL DEFAULT NULL COMMENT '计划结束时间',
  `ACTUAL_START_TIME` datetime(0) NULL DEFAULT NULL COMMENT '实际开始时间',
  `ACTUAL_END_TIME` datetime(0) NULL DEFAULT NULL COMMENT '实际结束时间',
  `ROUTING_ID` int(0) NULL DEFAULT NULL COMMENT '工艺路线id',
  `BARCODE_RULE_ID` int(0) NULL DEFAULT NULL COMMENT '条码规则id',
  `BOM_ID` int(0) NULL DEFAULT NULL COMMENT 'BOMid',
  `PRODUCT_ID` int(0) NULL DEFAULT NULL COMMENT '产品id',
  `TOTAL_RECIPE_ID` int(0) NULL DEFAULT NULL COMMENT '配方id',
  `OK_NUMBER` int(0) NULL DEFAULT 0 COMMENT '合格数量',
  `ORDER_ID` int(0) NULL DEFAULT NULL COMMENT '订单id',
  `ORDERRECORD_ID` int(0) NULL DEFAULT NULL COMMENT '订单记录id',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 266 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '工单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of r_mes_workorder_detail_t
-- ----------------------------

-- ----------------------------
-- Table structure for r_mod_data_collect_detail_t
-- ----------------------------
DROP TABLE IF EXISTS `r_mod_data_collect_detail_t`;
CREATE TABLE `r_mod_data_collect_detail_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `collectId` int(0) NOT NULL COMMENT '主表id',
  `keyName` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '参数名',
  `value` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '参数值',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `INDEX_COLLECTID`(`collectId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 89 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '数据收束副表-运行表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of r_mod_data_collect_detail_t
-- ----------------------------

-- ----------------------------
-- Table structure for r_mod_data_collect_t
-- ----------------------------
DROP TABLE IF EXISTS `r_mod_data_collect_t`;
CREATE TABLE `r_mod_data_collect_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `DT` datetime(0) NULL DEFAULT NULL COMMENT '时间',
  `lineName` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '产线名称',
  `stationName` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '工位名称',
  `productionName` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '产品名称',
  `SN` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '总成',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '状态 OK/NG',
  `valid` int(0) NULL DEFAULT 1 COMMENT '1 有效 0 无效（废弃）',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '数据收束主表-运行表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of r_mod_data_collect_t
-- ----------------------------

-- ----------------------------
-- Table structure for r_upload_data_t
-- ----------------------------
DROP TABLE IF EXISTS `r_upload_data_t`;
CREATE TABLE `r_upload_data_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT,
  `ST` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工位名称',
  `SN` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '总成',
  `NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据名称',
  `DATAVALUE` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据值',
  `SPDATAVALUE` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '唯一码',
  `TYPE` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型',
  `NUMBERS` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数量',
  `MATERIAL_NAME` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料名称',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '上传数据缓存表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of r_upload_data_t
-- ----------------------------

-- ----------------------------
-- Table structure for r_wms_in_taskqueue_t
-- ----------------------------
DROP TABLE IF EXISTS `r_wms_in_taskqueue_t`;
CREATE TABLE `r_wms_in_taskqueue_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `DT` datetime(0) NULL DEFAULT NULL COMMENT '日期',
  `LIST_NO` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单号',
  `TRAY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '托盘号',
  `FLAG` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '动作标记、0待操作、1正在进行',
  `OVER_DT` datetime(0) NULL DEFAULT NULL COMMENT '完成时间',
  `LOCATION_ID` int(0) NULL DEFAULT NULL COMMENT '库位id',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '入库队列永久记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of r_wms_in_taskqueue_t
-- ----------------------------

-- ----------------------------
-- Table structure for r_wms_out_taskqueue_t
-- ----------------------------
DROP TABLE IF EXISTS `r_wms_out_taskqueue_t`;
CREATE TABLE `r_wms_out_taskqueue_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `DT` datetime(0) NULL DEFAULT NULL COMMENT '日期',
  `LIST_NO` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单号',
  `TRAY` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '托盘号',
  `STATION_ID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '目标工位ID',
  `FLAG` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '动作标记、0待操作、1正在进行',
  `OVER_DT` datetime(0) NULL DEFAULT NULL COMMENT '完成时间',
  `LOCATION_ID` int(0) NULL DEFAULT NULL COMMENT '库位表',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '出库队列永久记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of r_wms_out_taskqueue_t
-- ----------------------------

-- ----------------------------
-- Table structure for r_wms_storage_detail_t
-- ----------------------------
DROP TABLE IF EXISTS `r_wms_storage_detail_t`;
CREATE TABLE `r_wms_storage_detail_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT,
  `DT` datetime(0) NULL DEFAULT NULL COMMENT '入库时间',
  `MATERIAL_ID` int(0) NULL DEFAULT NULL COMMENT '物料id',
  `MATERIAL_NUMBER` int(0) NULL DEFAULT NULL COMMENT '数量，用字符表示，如果是出库“-”号，直接是数字',
  `MATERIAL_CODE` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物料条码',
  `AREA_ID` int(0) NULL DEFAULT NULL COMMENT '区域id',
  `RESERVOIR_AREA_ID` int(0) NULL DEFAULT NULL COMMENT '库区id',
  `LOCATION_ID` int(0) NULL DEFAULT NULL COMMENT '库位id',
  `NOTE` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `LIST_NO` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单号，根据单号查找明细',
  `PROJECT_ID` int(0) NULL DEFAULT NULL COMMENT '所属项目',
  `SUPPLIER_ID` int(0) NULL DEFAULT NULL COMMENT '供应商id',
  `TRAY` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '托盘号',
  `WAREHOUSE_ID` int(0) NULL DEFAULT NULL COMMENT '仓库ID',
  `YN_SHIFT` int(0) NULL DEFAULT NULL COMMENT '是否移库  0：否、1：是',
  `ISSUE_OR_RECEIPT` int(0) NULL DEFAULT NULL COMMENT '出库还是入库 0：出库  1：入库',
  `STATION_ID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '目标工位ID',
  `user_id` int(0) NULL DEFAULT NULL COMMENT '用户id',
  `bar_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '条码',
  `materialNumberId` int(0) NULL DEFAULT NULL COMMENT '库存表id',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `storage_datail_index`(`MATERIAL_ID`, `AREA_ID`, `RESERVOIR_AREA_ID`, `LOCATION_ID`, `LIST_NO`, `PROJECT_ID`, `SUPPLIER_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17402 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '库存明细表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of r_wms_storage_detail_t
-- ----------------------------

-- ----------------------------
-- Table structure for s_mes_register_t
-- ----------------------------
DROP TABLE IF EXISTS `s_mes_register_t`;
CREATE TABLE `s_mes_register_t`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `DT` datetime(0) NULL DEFAULT NULL COMMENT '日期',
  `IP` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'IP地址',
  `MAC` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '客户端MAC地址',
  `HOSTNAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主机名称',
  `LINE_ID` int(0) NULL DEFAULT NULL COMMENT '所属产线',
  `STATION_ID` int(0) NULL DEFAULT NULL COMMENT '所属工位',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '注册表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of s_mes_register_t
-- ----------------------------

-- ----------------------------
-- Triggers structure for table c_crm_customer_basic_information
-- ----------------------------
DROP TRIGGER IF EXISTS `base_data_add_by_crm_customer`;
delimiter ;;
CREATE TRIGGER `base_data_add_by_crm_customer` AFTER INSERT ON `c_crm_customer_basic_information` FOR EACH ROW BEGIN
DECLARE lsort int;
#查询当前最大的排序id
select  (max(f_sort)+1) into lsort from t_bd_base_data where f_type_id = 3;
#不存在就为1
if lsort is null then
	set lsort = 1;
end if;

INSERT INTO
`t_bd_base_data`( `f_name`, `f_number`, `f_remark`, `f_sort`, `f_type_id`, `f_type_name`) VALUES
(NEW.`CUSTOMER_NAME`, NEW.ID, '', lsort, 4, '客户');

END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table c_crm_customer_basic_information
-- ----------------------------
DROP TRIGGER IF EXISTS `base_data_update_by_crm_customer`;
delimiter ;;
CREATE TRIGGER `base_data_update_by_crm_customer` AFTER UPDATE ON `c_crm_customer_basic_information` FOR EACH ROW BEGIN
update t_bd_base_data set f_name = NEW.`CUSTOMER_NAME`,f_number=NEW.ID,f_remark = ''
where f_type_id = 4 and f_number = old.ID;

END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table c_crm_customer_basic_information
-- ----------------------------
DROP TRIGGER IF EXISTS `base_data_delete_by_crm_customer`;
delimiter ;;
CREATE TRIGGER `base_data_delete_by_crm_customer` AFTER DELETE ON `c_crm_customer_basic_information` FOR EACH ROW BEGIN
delete from  t_bd_base_data
where f_type_id = 4 and f_number = old.ID;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table c_mes_company_t
-- ----------------------------
DROP TRIGGER IF EXISTS `base_data_add_by_mes_company`;
delimiter ;;
CREATE TRIGGER `base_data_add_by_mes_company` AFTER INSERT ON `c_mes_company_t` FOR EACH ROW BEGIN
DECLARE lsort int;
#查询当前最大的排序id
select  (max(f_sort)+1) into lsort from t_bd_base_data where f_type_id = 2;
#不存在就为1
if lsort is null then
	set lsort = 1;
end if;

INSERT INTO
`t_bd_base_data`( `f_name`, `f_number`, `f_remark`, `f_sort`, `f_type_id`, `f_type_name`) VALUES
(NEW.`companyName`, NEW.companyCode, '', lsort, 2, '公司');

END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table c_mes_company_t
-- ----------------------------
DROP TRIGGER IF EXISTS `base_data_update_by_mes_company`;
delimiter ;;
CREATE TRIGGER `base_data_update_by_mes_company` AFTER UPDATE ON `c_mes_company_t` FOR EACH ROW BEGIN
update t_bd_base_data set f_name = NEW.`companyName`,f_number=NEW.companyCode,f_remark = ''
where f_type_id = 2 and f_number = old.companyCode;

END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table c_mes_company_t
-- ----------------------------
DROP TRIGGER IF EXISTS `base_data_delete_by_mes_company`;
delimiter ;;
CREATE TRIGGER `base_data_delete_by_mes_company` AFTER DELETE ON `c_mes_company_t` FOR EACH ROW BEGIN
delete from  t_bd_base_data
where f_type_id = 2 and f_number = old.companyCode;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table c_srm_supplier
-- ----------------------------
DROP TRIGGER IF EXISTS `base_data_add_by_srm_supplier`;
delimiter ;;
CREATE TRIGGER `base_data_add_by_srm_supplier` AFTER INSERT ON `c_srm_supplier` FOR EACH ROW BEGIN
DECLARE lsort int;
#查询当前最大的排序id
select  (max(f_sort)+1) into lsort from t_bd_base_data where f_type_id = 1;
#不存在就为1
if lsort is null then
	set lsort = 1;
end if;

INSERT INTO
`t_bd_base_data`( `f_name`, `f_number`, `f_remark`, `f_sort`, `f_type_id`, `f_type_name`) VALUES
(NEW.`name`, NEW.supplier_code, '', lsort, 1, '供应商');

END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table c_srm_supplier
-- ----------------------------
DROP TRIGGER IF EXISTS `base_data_update_by_srm_supplier`;
delimiter ;;
CREATE TRIGGER `base_data_update_by_srm_supplier` AFTER UPDATE ON `c_srm_supplier` FOR EACH ROW BEGIN
update t_bd_base_data set f_name = NEW.`name`,f_number=NEW.supplier_code,f_remark = ''
where f_type_id = 1 and f_number = old.supplier_code;

END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table c_srm_supplier
-- ----------------------------
DROP TRIGGER IF EXISTS `base_data_delete_by_srm_supplier`;
delimiter ;;
CREATE TRIGGER `base_data_delete_by_srm_supplier` AFTER DELETE ON `c_srm_supplier` FOR EACH ROW BEGIN
delete from  t_bd_base_data
where f_type_id = 1 and f_number = old.supplier_code;
END
;;
delimiter ;


-- ----------------------------
-- Table structure for t_bd_account
-- ----------------------------
DROP TABLE IF EXISTS `t_bd_account`;
CREATE TABLE `t_bd_account`  (
  `f_acct_id` int NOT NULL AUTO_INCREMENT COMMENT '内码',
  `f_number` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '科目代码',
  `f_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `f_description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `f_parent_id` int NULL DEFAULT NULL COMMENT '上级科目内码',
  `f_help_ercode` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '助记码',
  `f_group_id` int NULL DEFAULT NULL COMMENT '科目类别内码 ，引用会计要素表 ',
  `f_dc` int NULL DEFAULT NULL COMMENT '余额方向 1 ：借方 ； -1 ：贷方 ',
  `f_accttblid` int NULL DEFAULT NULL COMMENT '科目表内码',
  `f_iscash` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '是否现金科目0：非现金科目 ，1：现金科目 ，默认0',
  `f_isbank` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '是否银行科目 0：非银行科目 ，1：银行科目 ，默认0',
  `f_isallocate` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否期末调汇',
  `f_iscash_flow` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '是否现金等价物 0：非现金等价物 ，1：现金等价物 ，默认0',
  `f_item_detailid` int NULL DEFAULT NULL COMMENT '核算项目组',
  `f_isquantities` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '	是否数量金额辅助核算 0：非数量金额核算 ，1：数量金额核算 ，默认0',
  `f_unit_groupid` int NULL DEFAULT NULL COMMENT '单位组 引用计量单位组',
  `f_unit_id` int NULL DEFAULT NULL COMMENT '默认单位 引用计量单位',
  `f_isdetail` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否明细科目0=非明细科目 1=明细科目 默认为明细科目',
  `f_level` int NULL DEFAULT NULL COMMENT '科目级次从一级开始',
  `f_create_orgid` int NULL DEFAULT NULL COMMENT '创建组织',
  `f_creatorid` int NULL DEFAULT NULL COMMENT '创建人',
  `f_create_date` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `f_use_orgid` int NULL DEFAULT NULL COMMENT '使用组织',
  `f_modifierid` int NULL DEFAULT NULL COMMENT '修改人',
  `f_modify_date` datetime NULL DEFAULT NULL COMMENT '修改日期',
  `f_document_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '数据状态',
  `f_auditorid` int NULL DEFAULT NULL COMMENT '审核人',
  `f_audit_date` datetime NULL DEFAULT NULL COMMENT '审核日期',
  `f_forbid_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '禁用状态',
  `f_forbidderid` int NULL DEFAULT NULL COMMENT '禁用人',
  `f_forbid_date` datetime NULL DEFAULT NULL COMMENT '禁用日期',
  `f_issys_preset` int NULL DEFAULT 0 COMMENT '是否系统预设1 系统预设0 非系统预设默认0',
  `f_master_id` int NULL DEFAULT NULL COMMENT '组织隔离字段',
  `f_cfitem_id` int NULL DEFAULT NULL COMMENT '主表项目（流入)',
  `f_ocfitem_id` int NULL DEFAULT NULL COMMENT '主表项目（流出)',
  `f_cfindirectitem_id` int NULL DEFAULT NULL COMMENT '附表项目（流入）',
  `f_ocfindirectitem_id` int NULL DEFAULT NULL COMMENT '附表项目（流出）',
  `f_allcurrency` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '核算所有币别',
  `f_currencys` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '外币核算 逗号分割id列表',
  `f_isshow_journal` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '是否出日记账 0：不出日记账 ，1：出日记账，默认0',
  PRIMARY KEY (`f_acct_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '科目信息' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for t_bd_account_book
-- ----------------------------
DROP TABLE IF EXISTS `t_bd_account_book`;
CREATE TABLE `t_bd_account_book`  (
  `f_book_id` int NOT NULL AUTO_INCREMENT COMMENT '内码',
  `f_book_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '账簿名称',
  `f_number` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编码',
  `f_accttable_id` int NULL DEFAULT NULL COMMENT '科目表内码',
  `f_period_id` int NULL DEFAULT NULL COMMENT '会计日历内码',
  `f_currency_id` int NULL DEFAULT NULL COMMENT '币别表内码',
  `f_rate_type_id` int NULL DEFAULT NULL COMMENT '汇率类型内码',
  `f_account_orgid` int NULL DEFAULT NULL COMMENT '核算组织',
  `f_acctsystem_id` int NULL DEFAULT NULL COMMENT '核算体系',
  `f_book_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '引用枚举类型【账簿类型】0 非主账簿1主账簿默认0',
  `f_default_voucher_type` int NULL DEFAULT NULL COMMENT '默认凭证字',
  `f_use_adjust_period` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '启用调整期',
  `f_initial_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '结束初始化状态：0:否，1是',
  `f_current_year` int NULL DEFAULT NULL COMMENT '当前年度',
  `f_current_period` int NULL DEFAULT NULL COMMENT '当前期间',
  `f_crt_year_period` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '当前年度期间',
  `f_start_year` int NULL DEFAULT NULL COMMENT '启用年度',
  `f_start_period` int NULL DEFAULT NULL COMMENT '启用期间',
  `f_yearand_period` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '启用年度期间',
  `f_acct_policyid` int NULL DEFAULT NULL COMMENT '会计政策内码',
  `f_apfin_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '财务应付确认方式',
  `f_arfin_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '财务应收确认方式',
  `f_create_orgid` int NULL DEFAULT NULL COMMENT '创建组织',
  `f_creatorid` int NULL DEFAULT NULL COMMENT '创建人',
  `f_create_date` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `f_use_orgid` int NULL DEFAULT NULL COMMENT '使用组织',
  `f_modifierid` int NULL DEFAULT NULL COMMENT '修改人',
  `f_modify_date` datetime NULL DEFAULT NULL COMMENT '修改日期',
  `f_document_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数据状态',
  `f_auditorid` int NULL DEFAULT NULL COMMENT '审核人',
  `f_audit_date` datetime NULL DEFAULT NULL COMMENT '审核日期',
  `f_forbid_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '禁用状态',
  `f_forbidderid` int NULL DEFAULT NULL COMMENT '禁用人',
  `f_forbid_date` datetime NULL DEFAULT NULL COMMENT '禁用日期',
  `f_issys_preset` int NULL DEFAULT 0 COMMENT '是否系统预设1 系统预设0 非系统预设默认0',
  `f_master_id` int NULL DEFAULT NULL COMMENT '组织隔离字段',
  PRIMARY KEY (`f_book_id`) USING BTREE,
  UNIQUE INDEX `f_number`(`f_number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '账簿' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for t_bd_account_calendar
-- ----------------------------
DROP TABLE IF EXISTS `t_bd_account_calendar`;
CREATE TABLE `t_bd_account_calendar`  (
  `f_id` int NOT NULL AUTO_INCREMENT COMMENT '内码',
  `f_number` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编码 ',
  `f_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `f_start_date` date NULL DEFAULT NULL COMMENT '会计日历开始日期',
  `f_end_date` date NULL DEFAULT NULL COMMENT '会计日历截止日期',
  `f_period_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '期间类型:1 年、2 季度、3 月、4 四周、5 周、6 日 ',
  `f_start_year` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '起始会计年度：前后控制各50年',
  `f_create_orgid` int NULL DEFAULT NULL COMMENT '创建组织',
  `f_creatorid` int NULL DEFAULT NULL COMMENT '创建人',
  `f_create_date` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `f_use_orgid` int NULL DEFAULT NULL COMMENT '使用组织',
  `f_modifierid` int NULL DEFAULT NULL COMMENT '修改人',
  `f_modify_date` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改日期',
  `f_document_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '数据状态',
  `f_auditorid` int NULL DEFAULT NULL COMMENT '审核人',
  `f_audit_date` datetime NULL DEFAULT NULL COMMENT '审核日期',
  `f_forbid_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '禁用状态',
  `f_forbidderid` int NULL DEFAULT NULL COMMENT '禁用人',
  `f_forbid_date` datetime NULL DEFAULT NULL COMMENT '禁用日期',
  `f_issys_preset` int NULL DEFAULT 0 COMMENT '是否系统预设1 系统预设0 非系统预设默认0',
  `f_master_id` int NULL DEFAULT NULL COMMENT '主ID 继承与模板,用于组织隔离',
  `f_period_count` int NULL DEFAULT NULL COMMENT '期间数 ',
  PRIMARY KEY (`f_id`) USING BTREE,
  UNIQUE INDEX `f_number`(`f_number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '会计日历' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for t_bd_account_distribute
-- ----------------------------
DROP TABLE IF EXISTS `t_bd_account_distribute`;
CREATE TABLE `t_bd_account_distribute`  (
  `f_entry_id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `f_acct_id` int NULL DEFAULT NULL COMMENT '科目ID',
  `f_distribute_orgid` int NULL DEFAULT NULL COMMENT '分发组织ID',
  `f_use_orgid` int NULL DEFAULT NULL COMMENT '适用组织ID',
  `f_distributeor_id` int NULL DEFAULT NULL COMMENT '分发人ID',
  `f_distribute_date` datetime NULL DEFAULT NULL COMMENT '分发时间',
  `f_isadd_child` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '是否允许新增下级 1：是（默认）0: 否',
  `f_forbid_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '禁用状态 A：未禁用 B：禁用',
  `f_forbidder_id` int NULL DEFAULT NULL COMMENT '第一禁用人',
  `f_forbid_orgid` int NULL DEFAULT NULL COMMENT '禁用组织',
  `f_forbid_date` datetime NULL DEFAULT NULL COMMENT '禁用时间',
  `f_isredistribute` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '是否允许再分配 1：是0： 否（默认',
  PRIMARY KEY (`f_entry_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '科目分发表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for t_bd_account_flexentry
-- ----------------------------
DROP TABLE IF EXISTS `t_bd_account_flexentry`;
CREATE TABLE `t_bd_account_flexentry`  (
  `f_entry_id` int NOT NULL AUTO_INCREMENT COMMENT '分录内码 ',
  `f_acct_id` int NULL DEFAULT NULL COMMENT '科目主表内码',
  `f_flexitemproperty_id` int NULL DEFAULT NULL COMMENT '维度内码',
  `f_acctitemisvalid` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否启用 \'0\'未启用 \'1\'启用',
  `f_input_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否必录类型需要有分类：1 必录 0 可选',
  `f_data_fieldname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '维度数据表中的字段名称',
  `f_seq` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '显示顺序',
  `f_master_id` int NULL DEFAULT NULL COMMENT '组织隔离字段',
  PRIMARY KEY (`f_entry_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '科目核算维度组分录' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for t_bd_account_group
-- ----------------------------
DROP TABLE IF EXISTS `t_bd_account_group`;
CREATE TABLE `t_bd_account_group`  (
  `f_acctgroup_id` int NOT NULL AUTO_INCREMENT COMMENT '内码',
  `f_number` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编码',
  `f_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `f_description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `f_dc` int NULL DEFAULT NULL COMMENT '借贷方向  1:借方 ；-1：贷方\r\n ',
  `f_account_type` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类别',
  `f_acctgroup_tblid` int NULL DEFAULT NULL COMMENT '会计要素表内码 ',
  `f_isprofit` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否结转损益',
  `f_create_orgid` int NULL DEFAULT NULL COMMENT '创建组织',
  `f_creatorid` int NULL DEFAULT NULL COMMENT '创建人',
  `f_create_date` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `f_use_orgid` int NULL DEFAULT NULL COMMENT '使用组织',
  `f_modifierid` int NULL DEFAULT NULL COMMENT '修改人',
  `f_modify_date` datetime NULL DEFAULT NULL COMMENT '修改日期',
  `f_document_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数据状态',
  `f_auditorid` int NULL DEFAULT NULL COMMENT '审核人',
  `f_audit_date` datetime NULL DEFAULT NULL COMMENT '审核日期',
  `f_forbid_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '禁用状态',
  `f_forbidderid` int NULL DEFAULT NULL COMMENT '禁用人',
  `f_forbid_date` datetime NULL DEFAULT NULL COMMENT '禁用日期',
  `f_issys_preset` int NULL DEFAULT 0 COMMENT '是否系统预设1 系统预设0 非系统预设默认0',
  `f_master_id` int NULL DEFAULT NULL COMMENT '组织隔离字段',
  PRIMARY KEY (`f_acctgroup_id`) USING BTREE,
  UNIQUE INDEX `f_number`(`f_number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '会计要素' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for t_bd_account_group_table
-- ----------------------------
DROP TABLE IF EXISTS `t_bd_account_group_table`;
CREATE TABLE `t_bd_account_group_table`  (
  `f_acctgroup_tblid` int NOT NULL AUTO_INCREMENT COMMENT '内码',
  `f_number` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编码',
  `f_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `f_description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `f_create_orgid` int NULL DEFAULT NULL COMMENT '创建组织',
  `f_creatorid` int NULL DEFAULT NULL COMMENT '创建人',
  `f_create_date` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `f_use_orgid` int NULL DEFAULT NULL COMMENT '使用组织',
  `f_modifierid` int NULL DEFAULT NULL COMMENT '修改人',
  `f_modify_date` datetime NULL DEFAULT NULL COMMENT '修改日期',
  `f_document_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数据状态',
  `f_auditorid` int NULL DEFAULT NULL COMMENT '审核人',
  `f_audit_date` datetime NULL DEFAULT NULL COMMENT '审核日期',
  `f_forbid_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '禁用状态',
  `f_forbidderid` int NULL DEFAULT NULL COMMENT '禁用人',
  `f_forbid_date` datetime NULL DEFAULT NULL COMMENT '禁用日期',
  `f_issys_preset` int NULL DEFAULT 0 COMMENT '是否系统预设1 系统预设0 非系统预设默认0',
  `f_master_id` int NULL DEFAULT NULL COMMENT '组织隔离字段',
  PRIMARY KEY (`f_acctgroup_tblid`) USING BTREE,
  UNIQUE INDEX `f_number`(`f_number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '会计要素表' ROW_FORMAT = COMPACT;


-- ----------------------------
-- Table structure for t_bd_account_period
-- ----------------------------
DROP TABLE IF EXISTS `t_bd_account_period`;
CREATE TABLE `t_bd_account_period`  (
  `f_id` int NOT NULL AUTO_INCREMENT COMMENT '内码',
  `f_entry_id` int NULL DEFAULT NULL COMMENT '分录内码',
  `f_year` int NULL DEFAULT NULL COMMENT '期间所在年',
  `f_period` int NULL DEFAULT NULL COMMENT '期间流水',
  `f_period_startdate` date NULL DEFAULT NULL COMMENT '期间开始日期',
  `f_period_enddate` date NULL DEFAULT NULL COMMENT '期间截止日期',
  `f_quarter` int NULL DEFAULT NULL COMMENT '本期间所在季度：动态算出',
  `f_month` int NULL DEFAULT NULL COMMENT '月份',
  `f_week` int NULL DEFAULT NULL COMMENT '周',
  `f_entryseq` int NULL DEFAULT NULL COMMENT '行号',
  PRIMARY KEY (`f_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 169 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '会计期间' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for t_bd_account_table
-- ----------------------------
DROP TABLE IF EXISTS `t_bd_account_table`;
CREATE TABLE `t_bd_account_table`  (
  `f_id` int NOT NULL AUTO_INCREMENT COMMENT '内码',
  `f_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `f_description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `f_acct_group_tblid` int NULL DEFAULT NULL COMMENT '会计要素表内码 ',
  `f_number` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编码',
  `f_max_grade` int NULL DEFAULT NULL COMMENT '最大科目等级 最大6 ，0表示不限制',
  `f_isuse_control` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否启用管控 1是 0否 ，启用管控则数据为分配型，否则为共享型',
  `f_parent_id` int NULL DEFAULT NULL COMMENT '上级科目ID',
  `f_create_orgid` int NULL DEFAULT NULL COMMENT '创建组织',
  `f_creatorid` int NULL DEFAULT NULL COMMENT '创建人',
  `f_create_date` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `f_use_orgid` int NULL DEFAULT NULL COMMENT '使用组织,管控组织',
  `f_modifierid` int NULL DEFAULT NULL COMMENT '修改人',
  `f_modify_date` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改日期',
  `f_document_status` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '数据状态',
  `f_auditorid` int NULL DEFAULT NULL COMMENT '审核人',
  `f_audit_date` datetime NULL DEFAULT NULL COMMENT '审核日期',
  `f_forbid_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '禁用状态',
  `f_forbidderid` int NULL DEFAULT NULL COMMENT '禁用人',
  `f_forbid_date` datetime NULL DEFAULT NULL COMMENT '禁用日期',
  `f_issys_preset` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '是否系统预设1 系统预设0 非系统预设默认0',
  `f_master_id` int NULL DEFAULT NULL COMMENT '组织隔离字段',
  PRIMARY KEY (`f_id`) USING BTREE,
  UNIQUE INDEX `f_numberIndex`(`f_number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '科目表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for t_bd_account_type
-- ----------------------------
DROP TABLE IF EXISTS `t_bd_account_type`;
CREATE TABLE `t_bd_account_type`  (
  `f_acct_type_id` int NOT NULL AUTO_INCREMENT COMMENT '内码',
  `f_number` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编码',
  `f_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `f_acct_group_id` int NULL DEFAULT NULL COMMENT '会计要素内码',
  `f_acct_table_id` int NULL DEFAULT NULL COMMENT '科目表内码 ',
  `f_dc` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '借贷方向  1:借方 ；-1：贷方 \r\n ',
  `f_level` int NULL DEFAULT NULL COMMENT '级别',
  `f_parent_id` int NULL DEFAULT NULL COMMENT '上级类别内码',
  `f_priorpl_adjust` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '以前年度损益调整 ',
  `f_create_orgid` int NULL DEFAULT NULL COMMENT '创建组织',
  `f_creatorid` int NULL DEFAULT NULL COMMENT '创建人',
  `f_create_date` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `f_use_orgid` int NULL DEFAULT NULL COMMENT '使用组织',
  `f_modifierid` int NULL DEFAULT NULL COMMENT '修改人',
  `f_modify_date` datetime NULL DEFAULT NULL COMMENT '修改日期',
  `f_document_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数据状态',
  `f_auditorid` int NULL DEFAULT NULL COMMENT '审核人',
  `f_audit_date` datetime NULL DEFAULT NULL COMMENT '审核日期',
  `f_forbid_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '禁用状态',
  `f_forbidderid` int NULL DEFAULT NULL COMMENT '禁用人',
  `f_forbid_date` datetime NULL DEFAULT NULL COMMENT '禁用日期',
  `f_issys_preset` int NULL DEFAULT 0 COMMENT '是否系统预设1 系统预设0 非系统预设默认0',
  `f_master_id` int NULL DEFAULT NULL COMMENT '组织隔离字段',
  PRIMARY KEY (`f_acct_type_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '科目类别' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for t_bd_accountcy
-- ----------------------------
DROP TABLE IF EXISTS `t_bd_accountcy`;
CREATE TABLE `t_bd_accountcy`  (
  `f_currency_id` int NOT NULL AUTO_INCREMENT COMMENT '内码',
  `f_entry_id` int NULL DEFAULT NULL COMMENT '分录内码',
  `f_acct_id` int NULL DEFAULT NULL COMMENT '科目内码',
  `f_seq` int NULL DEFAULT NULL COMMENT '显示顺序 ',
  PRIMARY KEY (`f_currency_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '科目核算币别' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for t_bd_base_data
-- ----------------------------
DROP TABLE IF EXISTS `t_bd_base_data`;
CREATE TABLE `t_bd_base_data`  (
  `f_id` int NOT NULL AUTO_INCREMENT COMMENT '内码',
  `f_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `f_number` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编码',
  `f_remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `f_sort` int NULL DEFAULT 0 COMMENT '排序',
  `f_type_id` int NULL DEFAULT NULL COMMENT '类别ID',
  `f_type_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类别名字',
  `f_parent_id` int NULL DEFAULT NULL COMMENT '上级资料',
  `f_create_orgid` int NULL DEFAULT NULL COMMENT '创建组织',
  `f_use_orgid` int NULL DEFAULT NULL COMMENT '使用组织',
  `f_creatorid` int NULL DEFAULT NULL COMMENT '创建人',
  `f_create_date` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `f_modifierid` int NULL DEFAULT NULL COMMENT '修改人',
  `f_modify_date` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改日期',
  `f_document_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '数据状态',
  `f_auditorid` int NULL DEFAULT NULL COMMENT '审核人',
  `f_audit_date` datetime NULL DEFAULT NULL COMMENT '审核日期',
  `f_forbid_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '禁用状态',
  `f_forbidderid` int NULL DEFAULT NULL COMMENT '禁用人',
  `f_forbid_date` datetime NULL DEFAULT NULL COMMENT '禁用日期',
  `f_issys_preset` int NULL DEFAULT 0 COMMENT '是否系统预设1 系统预设0 非系统预设默认0',
  `f_master_id` int NULL DEFAULT NULL COMMENT '组织隔离字段',
  PRIMARY KEY (`f_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1190 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '基础资料' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for t_bd_base_type
-- ----------------------------
DROP TABLE IF EXISTS `t_bd_base_type`;
CREATE TABLE `t_bd_base_type`  (
  `f_id` int NOT NULL AUTO_INCREMENT COMMENT '内码',
  `f_number` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编码',
  `f_type_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '基础资料名称',
  `f_table_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '表名',
  `f_listsql` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '查询结果集sql',
  `f_onesql` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '查询单条sql',
  `f_create_orgid` int NULL DEFAULT NULL COMMENT '创建组织',
  `f_use_orgid` int NULL DEFAULT NULL COMMENT '使用组织',
  `f_creatorid` int NULL DEFAULT NULL COMMENT '创建人',
  `f_create_date` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `f_modifierid` int NULL DEFAULT NULL COMMENT '修改人',
  `f_modify_date` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改日期',
  `f_document_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '数据状态',
  `f_auditorid` int NULL DEFAULT NULL COMMENT '审核人',
  `f_audit_date` datetime NULL DEFAULT NULL COMMENT '审核日期',
  `f_forbid_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '禁用状态',
  `f_forbidderid` int NULL DEFAULT NULL COMMENT '禁用人',
  `f_forbid_date` datetime NULL DEFAULT NULL COMMENT '禁用日期',
  `f_issys_preset` int NULL DEFAULT 0 COMMENT '是否系统预设1 系统预设0 非系统预设默认0',
  `f_master_id` int NULL DEFAULT NULL COMMENT '组织隔离字段',
  PRIMARY KEY (`f_id`) USING BTREE,
  UNIQUE INDEX `f_number`(`f_number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 44 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '基础资料类别' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for t_bd_book_param
-- ----------------------------
DROP TABLE IF EXISTS `t_bd_book_param`;
CREATE TABLE `t_bd_book_param`  (
  `f_id` int NOT NULL AUTO_INCREMENT COMMENT '内码',
  `f_profit_distribution_account` int NULL DEFAULT NULL COMMENT '利润分配科目',
  `f_current_year_profit_account` int NULL DEFAULT NULL COMMENT '\r\n基本选项\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n本年利润科目',
  `f_base_one` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '账表余额方向与科目设置的余额方向相同',
  `f_base_two` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '明细账(表)摘要自动继承上条分录摘要',
  `f_base_three` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '明细账科目显示所有科目名称',
  `f_base_four` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '多栏账成本类科目期初余额从余额表取数',
  `f_base_five` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '多栏账损益类科目期初余额从余额表取数',
  `f_base_six` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '核算维度余额表非明细级的核算维度的余额合并在一个方向',
  `f_base_seven` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '往来科目必须输入业务编号（影响凭证及科目初始数据录入）',
  `f_checkout_options_one` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '结账时要求损益类科目余额为零',
  `f_checkout_options_two` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '不允许跨财务年度的反结账',
  `f_checkout_options_three` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '业务系统结账可与总账结账期间不一致',
  `f_checkout_options_four` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '结账时不检查业务单据的凭证生成情况（总账结账慢时建议勾选）',
  `f_checkout_options_five` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '总账结账检查对账结果包含调整期凭证',
  `f_book_id` int NULL DEFAULT NULL COMMENT '账簿ID',
  `f_create_orgid` int NULL DEFAULT NULL COMMENT '创建组织',
  `f_creatorid` int NULL DEFAULT NULL COMMENT '创建人',
  `f_create_date` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `f_use_orgid` int NULL DEFAULT NULL COMMENT '使用组织',
  `f_modifierid` int NULL DEFAULT NULL COMMENT '修改人',
  `f_modify_date` datetime NULL DEFAULT NULL COMMENT '修改日期',
  `f_document_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数据状态',
  `f_auditorid` int NULL DEFAULT NULL COMMENT '审核人',
  `f_audit_date` datetime NULL DEFAULT NULL COMMENT '审核日期',
  `f_forbid_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '禁用状态',
  `f_forbidderid` int NULL DEFAULT NULL COMMENT '禁用人',
  `f_forbid_date` datetime NULL DEFAULT NULL COMMENT '禁用日期',
  `f_issys_preset` int NULL DEFAULT 0 COMMENT '是否系统预设1 系统预设0 非系统预设默认0',
  `f_master_id` int NULL DEFAULT NULL COMMENT '组织隔离字段',
  PRIMARY KEY (`f_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '总账管理参数-账簿参数' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for t_bd_business_area
-- ----------------------------
DROP TABLE IF EXISTS `t_bd_business_area`;
CREATE TABLE `t_bd_business_area`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '内码',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `f_issys_preset` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '是否系统预设1 系统预设0 非系统预设默认0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '业务领域' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of t_bd_business_area
-- ----------------------------
INSERT INTO `t_bd_business_area` VALUES (1, '财务会计', '1');
INSERT INTO `t_bd_business_area` VALUES (2, '电商和分销', '1');
INSERT INTO `t_bd_business_area` VALUES (3, '供应链', '1');
INSERT INTO `t_bd_business_area` VALUES (4, 'BOS', '1');
INSERT INTO `t_bd_business_area` VALUES (5, '生产制造', '1');
INSERT INTO `t_bd_business_area` VALUES (6, '系统管理', '1');
INSERT INTO `t_bd_business_area` VALUES (7, '管理会计', '1');
INSERT INTO `t_bd_business_area` VALUES (8, 'PLM', '1');
INSERT INTO `t_bd_business_area` VALUES (9, '共享服务中心', '1');
INSERT INTO `t_bd_business_area` VALUES (10, '移动应用', '1');
INSERT INTO `t_bd_business_area` VALUES (11, '基础管理', '1');
INSERT INTO `t_bd_business_area` VALUES (12, '税务管理', '1');
INSERT INTO `t_bd_business_area` VALUES (13, '客户关系管理', '1');
INSERT INTO `t_bd_business_area` VALUES (14, '流程中心', '1');
INSERT INTO `t_bd_business_area` VALUES (15, '资产管理', '1');
INSERT INTO `t_bd_business_area` VALUES (16, '质量管理', '1');
INSERT INTO `t_bd_business_area` VALUES (17, '成本管理', '1');
INSERT INTO `t_bd_business_area` VALUES (18, '经营分析', '1');
INSERT INTO `t_bd_business_area` VALUES (19, '零售管理', '1');

-- ----------------------------
-- Table structure for t_bd_credential_param
-- ----------------------------
DROP TABLE IF EXISTS `t_bd_credential_param`;
CREATE TABLE `t_bd_credential_param`  (
  `f_id` int NOT NULL AUTO_INCREMENT COMMENT '内码',
  `f_base_one` int NULL DEFAULT 0 COMMENT '允许录入凭证的未来期间数',
  `f_base_tow` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '凭证过账前必须审核',
  `f_base_three` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '凭证过账前必须出纳复核',
  `f_base_four` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '允许取消复核人与出纳复核人不一致',
  `f_base_five` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '凭证中的汇率允许手工修改',
  `f_base_six` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '凭证录入显示科目全名',
  `f_base_seven` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '凭证中显示科目的最新余额',
  `f_cash_flow_one` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '现金流量科目必须输入现金流量项目',
  `f_cash_flow_two` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '录入凭证时指定现金流量附表项目',
  `f_cash_flow_three` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '已审核、已过账凭证不允许修改现金流量',
  `f_cash_flow_four` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '凭证保存后自动指定现金流量',
  `f_cash_flow_five` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '2' COMMENT '1主表 2主表及附表 默认2',
  `f_data_validation_one` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '每条凭证分录必须有摘要',
  `f_data_validation_two` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '银行存款科目必须输入结算方式和结算号',
  `f_data_validation_three` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '凭证中的单价、数量字段允许为零',
  `f_voucher_number_one` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '凭证号排列规则 1按年度排列 2按期间排列 默认1',
  `f_voucher_number_two` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '不允许手工修改凭证号',
  `f_voucher_number_three` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '新增凭证自动填补断号',
  `f_voucher_number_four` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '保存凭证时支持检查凭证号（适用于手工修改凭证号及凭证引入）',
  `f_other_one` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '业务系统生成的总账凭证允许修改',
  `f_other_two` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '业务系统生成的总账凭证允许作废',
  `f_other_three` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数量金额核算中，凭证支持跨计量单位组的单位',
  `f_other_four` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '维度录入时部门与员工不关联',
  `f_book_id` int NULL DEFAULT NULL COMMENT '账簿ID',
  `f_create_orgid` int NULL DEFAULT NULL COMMENT '创建组织',
  `f_creatorid` int NULL DEFAULT NULL COMMENT '创建人',
  `f_create_date` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `f_use_orgid` int NULL DEFAULT NULL COMMENT '使用组织',
  `f_modifierid` int NULL DEFAULT NULL COMMENT '修改人',
  `f_modify_date` datetime NULL DEFAULT NULL COMMENT '修改日期',
  `f_document_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数据状态',
  `f_auditorid` int NULL DEFAULT NULL COMMENT '审核人',
  `f_audit_date` datetime NULL DEFAULT NULL COMMENT '审核日期',
  `f_forbid_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '禁用状态',
  `f_forbidderid` int NULL DEFAULT NULL COMMENT '禁用人',
  `f_forbid_date` datetime NULL DEFAULT NULL COMMENT '禁用日期',
  `f_issys_preset` int NULL DEFAULT 0 COMMENT '是否系统预设1 系统预设0 非系统预设默认0',
  `f_master_id` int NULL DEFAULT NULL COMMENT '组织隔离字段',
  PRIMARY KEY (`f_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for t_bd_currency
-- ----------------------------
DROP TABLE IF EXISTS `t_bd_currency`;
CREATE TABLE `t_bd_currency`  (
  `f_currency_id` int NOT NULL AUTO_INCREMENT COMMENT '内码 ',
  `f_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `f_number` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '币别编码 ',
  `f_code` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '货币代码 ',
  `f_sysmbol` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '货币符号',
  `f_pricedigits` int NOT NULL DEFAULT 4 COMMENT '单价精度 ',
  `f_amountdigits` int NOT NULL DEFAULT 2 COMMENT '金额精度 ',
  `f_priority` int NOT NULL DEFAULT 0 COMMENT '优先级',
  `f_istrans` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '是否中间币  0不是 1是',
  `f_settle_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '舍入类型 1四舍五入  2四舍六入五双',
  `f_master_id` int NULL DEFAULT NULL COMMENT '基类主标识 ',
  `f_isshow_csymbol` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '显示货币符号 ',
  `f_currency_symbolid` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '货币符号内码',
  `f_format_order` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '显示顺序',
  `f_create_orgid` int NULL DEFAULT NULL COMMENT '创建组织',
  `f_creatorid` int NULL DEFAULT NULL COMMENT '创建人',
  `f_create_date` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `f_use_orgid` int NULL DEFAULT NULL COMMENT '使用组织',
  `f_modifierid` int NULL DEFAULT NULL COMMENT '修改人',
  `f_modify_date` datetime NULL DEFAULT NULL COMMENT '修改日期',
  `f_document_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '数据状态',
  `f_auditorid` int NULL DEFAULT NULL COMMENT '审核人',
  `f_audit_date` datetime NULL DEFAULT NULL COMMENT '审核日期',
  `f_forbid_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '禁用状态',
  `f_forbidderid` int NULL DEFAULT NULL COMMENT '禁用人',
  `f_forbid_date` datetime NULL DEFAULT NULL COMMENT '禁用日期',
  `f_issys_preset` int NULL DEFAULT 0 COMMENT '是否系统预设1 系统预设0 非系统预设默认0',
  PRIMARY KEY (`f_currency_id`) USING BTREE,
  UNIQUE INDEX `f_number`(`f_number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '基础-币别表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for t_bd_dimension_source
-- ----------------------------
DROP TABLE IF EXISTS `t_bd_dimension_source`;
CREATE TABLE `t_bd_dimension_source`  (
  `f_id` int NOT NULL AUTO_INCREMENT COMMENT '内码',
  `f_number` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编码',
  `f_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `f_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类型 1基础资料 2辅助资料',
  `f_strategy_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '策略类型',
  `f_create_orgid` int NULL DEFAULT NULL COMMENT '创建组织',
  `f_creatorid` int NULL DEFAULT NULL COMMENT '创建人',
  `f_create_date` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `f_use_orgid` int NULL DEFAULT NULL COMMENT '使用组织',
  `f_modifierid` int NULL DEFAULT NULL COMMENT '修改人',
  `f_modify_date` datetime NULL DEFAULT NULL COMMENT '修改日期',
  `f_document_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数据状态',
  `f_auditorid` int NULL DEFAULT NULL COMMENT '审核人',
  `f_audit_date` datetime NULL DEFAULT NULL COMMENT '审核日期',
  `f_forbid_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '禁用状态',
  `f_forbidderid` int NULL DEFAULT NULL COMMENT '禁用人',
  `f_forbid_date` datetime NULL DEFAULT NULL COMMENT '禁用日期',
  `f_issys_preset` int NULL DEFAULT 0 COMMENT '是否系统预设1 系统预设0 非系统预设默认0',
  `f_master_id` int NULL DEFAULT NULL COMMENT '组织隔离字段',
  PRIMARY KEY (`f_id`) USING BTREE,
  UNIQUE INDEX `f_numberIndex`(`f_number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '维度来源' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for t_bd_execute_log
-- ----------------------------
DROP TABLE IF EXISTS `t_bd_execute_log`;
CREATE TABLE `t_bd_execute_log`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `execute_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '执行类型',
  `execute_id` int NULL DEFAULT NULL COMMENT '执行ID',
  `out_execute_id` int NULL DEFAULT NULL COMMENT '外部关联执行ID',
  `execute_status` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '执行结果 1成功 0失败',
  `execute_detail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '详细信息',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '执行业务日志记录' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for t_bd_file_manage
-- ----------------------------
DROP TABLE IF EXISTS `t_bd_file_manage`;
CREATE TABLE `t_bd_file_manage`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名字',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地址',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编号',
  `out_id` int NULL DEFAULT NULL COMMENT '外部关联ID',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user` int NULL DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文件管理' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for t_bd_flex_item_property
-- ----------------------------
DROP TABLE IF EXISTS `t_bd_flex_item_property`;
CREATE TABLE `t_bd_flex_item_property`  (
  `f_id` int NOT NULL AUTO_INCREMENT COMMENT '内码',
  `f_number` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编码',
  `f_dimension_source_id` int NULL DEFAULT NULL COMMENT '维度来源',
  `f_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类型 1基础资料 2辅助资料',
  `f_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `f_description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `f_create_orgid` int NULL DEFAULT NULL COMMENT '创建组织',
  `f_creatorid` int NULL DEFAULT NULL COMMENT '创建人',
  `f_create_date` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `f_use_orgid` int NULL DEFAULT NULL COMMENT '使用组织',
  `f_modifierid` int NULL DEFAULT NULL COMMENT '修改人',
  `f_modify_date` datetime NULL DEFAULT NULL COMMENT '修改日期',
  `f_document_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数据状态',
  `f_auditorid` int NULL DEFAULT NULL COMMENT '审核人',
  `f_audit_date` datetime NULL DEFAULT NULL COMMENT '审核日期',
  `f_forbid_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '禁用状态',
  `f_forbidderid` int NULL DEFAULT NULL COMMENT '禁用人',
  `f_forbid_date` datetime NULL DEFAULT NULL COMMENT '禁用日期',
  `f_issys_preset` int NULL DEFAULT 0 COMMENT '是否系统预设1 系统预设0 非系统预设默认0',
  `f_master_id` int NULL DEFAULT NULL COMMENT '组织隔离字段',
  PRIMARY KEY (`f_id`) USING BTREE,
  UNIQUE INDEX `f_numberIndex`(`f_number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '核算维度表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for t_bd_help_data
-- ----------------------------
DROP TABLE IF EXISTS `t_bd_help_data`;
CREATE TABLE `t_bd_help_data`  (
  `f_id` int NOT NULL AUTO_INCREMENT COMMENT '内码',
  `f_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `f_number` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编码',
  `f_remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `f_sort` int NULL DEFAULT 0 COMMENT '排序',
  `f_type_id` int NULL DEFAULT NULL COMMENT '类别ID',
  `f_type_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类别名字',
  `f_parent_id` int NULL DEFAULT NULL COMMENT '上级资料',
  `f_create_orgid` int NULL DEFAULT NULL COMMENT '创建组织',
  `f_use_orgid` int NULL DEFAULT NULL COMMENT '使用组织',
  `f_creatorid` int NULL DEFAULT NULL COMMENT '创建人',
  `f_create_date` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `f_modifierid` int NULL DEFAULT NULL COMMENT '修改人',
  `f_modify_date` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改日期',
  `f_document_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '数据状态',
  `f_auditorid` int NULL DEFAULT NULL COMMENT '审核人',
  `f_audit_date` datetime NULL DEFAULT NULL COMMENT '审核日期',
  `f_forbid_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '禁用状态',
  `f_forbidderid` int NULL DEFAULT NULL COMMENT '禁用人',
  `f_forbid_date` datetime NULL DEFAULT NULL COMMENT '禁用日期',
  `f_issys_preset` int NULL DEFAULT 0 COMMENT '是否系统预设1 系统预设0 非系统预设默认0',
  `f_master_id` int NULL DEFAULT NULL COMMENT '组织隔离字段',
  PRIMARY KEY (`f_id`) USING BTREE,
  UNIQUE INDEX `f_number`(`f_number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '辅助资料' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for t_bd_help_type
-- ----------------------------
DROP TABLE IF EXISTS `t_bd_help_type`;
CREATE TABLE `t_bd_help_type`  (
  `f_id` int NOT NULL AUTO_INCREMENT COMMENT '内码',
  `f_number` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编码',
  `f_business_area_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '业务领域名称',
  `f_business_area` int NULL DEFAULT NULL COMMENT '业务领域ID',
  `f_type_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类别名称',
  `f_description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `f_parent_id` int NULL DEFAULT 0 COMMENT '上级ID',
  `f_create_orgid` int NULL DEFAULT NULL COMMENT '创建组织',
  `f_use_orgid` int NULL DEFAULT NULL COMMENT '使用组织',
  `f_creatorid` int NULL DEFAULT NULL COMMENT '创建人',
  `f_create_date` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `f_modifierid` int NULL DEFAULT NULL COMMENT '修改人',
  `f_modify_date` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改日期',
  `f_document_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '数据状态',
  `f_auditorid` int NULL DEFAULT NULL COMMENT '审核人',
  `f_audit_date` datetime NULL DEFAULT NULL COMMENT '审核日期',
  `f_forbid_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '禁用状态',
  `f_forbidderid` int NULL DEFAULT NULL COMMENT '禁用人',
  `f_forbid_date` datetime NULL DEFAULT NULL COMMENT '禁用日期',
  `f_issys_preset` int NULL DEFAULT 0 COMMENT '是否系统预设1 系统预设0 非系统预设默认0',
  `f_master_id` int NULL DEFAULT NULL COMMENT '组织隔离字段',
  PRIMARY KEY (`f_id`) USING BTREE,
  UNIQUE INDEX `f_number`(`f_number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '辅助资料类别' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of t_bd_help_type
-- ----------------------------
INSERT INTO `t_bd_help_type` VALUES (2, 'Currency Symbol', '基础管理', 11, '货币符号', '货币符号', 9, NULL, NULL, NULL, '2021-07-19 09:51:27', NULL, '2021-07-23 15:47:39', '1', NULL, NULL, '0', NULL, NULL, 1, NULL);
INSERT INTO `t_bd_help_type` VALUES (3, 'finance acct', '财务会计', 1, '财务会计', '财务会计', 0, NULL, NULL, NULL, '2021-07-19 09:57:29', NULL, '2021-07-23 08:57:51', '1', NULL, NULL, NULL, NULL, NULL, 1, NULL);
INSERT INTO `t_bd_help_type` VALUES (4, 'supply chain', '供应链', 3, '供应链', '供应链', 0, NULL, NULL, NULL, '2021-07-19 09:59:18', NULL, '2021-07-23 10:11:09', '1', NULL, NULL, NULL, NULL, NULL, 1, NULL);
INSERT INTO `t_bd_help_type` VALUES (5, 'E-commerce distribution', '电商和分销', 2, '电商与分销', '电商与分销', 0, NULL, NULL, NULL, '2021-07-19 10:00:30', NULL, '2021-07-23 10:11:11', '1', NULL, NULL, NULL, NULL, NULL, 1, NULL);
INSERT INTO `t_bd_help_type` VALUES (6, 'manufacturing', '生产制造', 5, '生产制造', '生产制造', 0, NULL, NULL, NULL, '2021-07-19 10:01:15', NULL, '2021-07-23 10:11:12', '1', NULL, NULL, NULL, NULL, NULL, 1, NULL);
INSERT INTO `t_bd_help_type` VALUES (7, 'Shared Service Center', '共享服务中心', 9, '共享服务中心', '共享服务中心', 0, NULL, NULL, NULL, '2021-07-19 10:02:22', NULL, '2021-07-23 10:11:12', '1', NULL, NULL, NULL, NULL, NULL, 1, NULL);
INSERT INTO `t_bd_help_type` VALUES (8, 'Customer relationship', '客户关系管理', 13, '客户关系管理', '客户关系管理', 0, NULL, NULL, NULL, '2021-07-19 10:06:28', NULL, '2021-07-23 10:11:15', '1', NULL, NULL, NULL, NULL, NULL, 1, NULL);
INSERT INTO `t_bd_help_type` VALUES (9, 'Basic management', '基础管理', 11, '基础管理', '基础管理', 0, NULL, NULL, NULL, '2021-07-19 13:37:47', NULL, '2021-07-23 10:11:14', '1', NULL, NULL, NULL, NULL, NULL, 1, NULL);
INSERT INTO `t_bd_help_type` VALUES (10, 'mobile application', '移动应用', 10, '移动应用', '移动应用', 0, NULL, NULL, NULL, '2021-07-19 13:38:50', NULL, '2021-07-23 10:11:17', '1', NULL, NULL, NULL, NULL, NULL, 1, NULL);
INSERT INTO `t_bd_help_type` VALUES (16, 'mobile5', '移动应用', 10, '移动应用子类5', '移动应用子类5', 15, NULL, NULL, NULL, '2021-07-23 09:19:10', NULL, '2021-07-23 17:36:56', '1', NULL, NULL, NULL, NULL, NULL, 1, NULL);


-- ----------------------------
-- Table structure for t_bd_rate
-- ----------------------------
DROP TABLE IF EXISTS `t_bd_rate`;
CREATE TABLE `t_bd_rate`  (
  `f_rate_id` int NOT NULL AUTO_INCREMENT COMMENT '汇率内码',
  `f_rate_type_id` int NULL DEFAULT NULL COMMENT '汇率类型内码',
  `f_cyforid` int NULL DEFAULT NULL COMMENT '原币内码 ',
  `f_cytoid` int NULL DEFAULT NULL COMMENT '目标币内码',
  `f_exchange_rate` decimal(23, 10) NULL DEFAULT NULL COMMENT '直接汇率',
  `f_reverseex_rate` decimal(23, 10) NULL DEFAULT NULL COMMENT '间接汇率',
  `f_beg_date` date NULL DEFAULT NULL COMMENT '生效日期',
  `f_end_date` date NULL DEFAULT NULL COMMENT '失效日期',
  `f_number` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '特别说明：编码字段为冗余字段，主要是要基础资料模型需要这个字段，自动生成，界面不可见',
  `f_master_id` int NULL DEFAULT NULL COMMENT '组织隔离字段',
  `f_create_orgid` int NULL DEFAULT NULL COMMENT '创建组织',
  `f_creatorid` int NULL DEFAULT NULL COMMENT '创建人',
  `f_create_date` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `f_use_orgid` int NULL DEFAULT NULL COMMENT '使用组织',
  `f_modifierid` int NULL DEFAULT NULL COMMENT '修改人',
  `f_modify_date` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改日期',
  `f_document_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '数据状态',
  `f_auditorid` int NULL DEFAULT NULL COMMENT '审核人',
  `f_audit_date` datetime NULL DEFAULT NULL COMMENT '审核日期',
  `f_forbid_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '禁用状态',
  `f_forbidderid` int NULL DEFAULT NULL COMMENT '禁用人',
  `f_forbid_date` datetime NULL DEFAULT NULL COMMENT '禁用日期',
  `f_issys_preset` int NULL DEFAULT 0 COMMENT '是否系统预设1 系统预设0 非系统预设默认0',
  PRIMARY KEY (`f_rate_id`) USING BTREE
) ENGINE = InnoDB  CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '基础汇率' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of t_bd_rate_type
-- ----------------------------
INSERT INTO `t_bd_rate_type` VALUES (1, '期初汇率', '001', NULL, 4, 10, NULL, NULL, NULL, NULL, NULL, '2021-08-09 09:28:35', '1', NULL, NULL, '0', NULL, NULL, 0, NULL);
INSERT INTO `t_bd_rate_type` VALUES (2, '期末汇率', '002', NULL, 4, 10, NULL, NULL, NULL, NULL, NULL, '2021-08-09 09:28:38', '1', NULL, NULL, '0', NULL, NULL, 0, NULL);
INSERT INTO `t_bd_rate_type` VALUES (3, '预算汇率', '003', NULL, 4, 10, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, '0', NULL, NULL, 0, NULL);

-- ----------------------------
-- Table structure for t_bd_rate_type
-- ----------------------------
DROP TABLE IF EXISTS `t_bd_rate_type`;
CREATE TABLE `t_bd_rate_type`  (
  `f_rate_type_id` int NOT NULL AUTO_INCREMENT COMMENT '内码',
  `f_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `f_number` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '汇率类型编码',
  `f_description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `f_digits` smallint NULL DEFAULT 4 COMMENT '直接汇率精度',
  `f_reverse_digits` smallint NULL DEFAULT 10 COMMENT '间接汇率精度',
  `f_create_orgid` int NULL DEFAULT NULL COMMENT '创建组织',
  `f_creatorid` int NULL DEFAULT NULL COMMENT '创建人',
  `f_create_date` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `f_use_orgid` int NULL DEFAULT NULL COMMENT '使用组织',
  `f_modifierid` int NULL DEFAULT NULL COMMENT '修改人',
  `f_modify_date` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改日期',
  `f_document_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '数据状态',
  `f_auditorid` int NULL DEFAULT NULL COMMENT '审核人',
  `f_audit_date` datetime NULL DEFAULT NULL COMMENT '审核日期',
  `f_forbid_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '禁用状态',
  `f_forbidderid` int NULL DEFAULT NULL COMMENT '禁用人',
  `f_forbid_date` datetime NULL DEFAULT NULL COMMENT '禁用日期',
  `f_issys_preset` int NULL DEFAULT 0 COMMENT '是否系统预设1 系统预设0 非系统预设默认0',
  `f_master_id` int NULL DEFAULT NULL COMMENT '组织隔离字段',
  PRIMARY KEY (`f_rate_type_id`) USING BTREE,
  UNIQUE INDEX `f_number`(`f_number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '汇率类型' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for t_bd_unit
-- ----------------------------
DROP TABLE IF EXISTS `t_bd_unit`;
CREATE TABLE `t_bd_unit`  (
  `f_unit_id` int NOT NULL AUTO_INCREMENT COMMENT '内码',
  `f_number` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编码',
  `f_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `f_description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `f_unit_group_id` int NULL DEFAULT NULL COMMENT '单位组内码 ',
  `f_isbase_unit` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '基准计量单位',
  `f_precision` int NULL DEFAULT 0 COMMENT '精度',
  `f_round_type` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '舍入类型',
  `f_create_orgid` int NULL DEFAULT NULL COMMENT '创建组织',
  `f_creatorid` int NULL DEFAULT NULL COMMENT '创建人',
  `f_create_date` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `f_use_orgid` int NULL DEFAULT NULL COMMENT '使用组织',
  `f_modifierid` int NULL DEFAULT NULL COMMENT '修改人',
  `f_modify_date` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改日期',
  `f_document_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '数据状态',
  `f_auditorid` int NULL DEFAULT NULL COMMENT '审核人',
  `f_audit_date` datetime NULL DEFAULT NULL COMMENT '审核日期',
  `f_forbid_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '禁用状态',
  `f_forbidderid` int NULL DEFAULT NULL COMMENT '禁用人',
  `f_forbid_date` datetime NULL DEFAULT NULL COMMENT '禁用日期',
  `f_issys_preset` int NULL DEFAULT 0 COMMENT '是否系统预设1 系统预设0 非系统预设默认0',
  `f_master_id` int NULL DEFAULT NULL COMMENT '组织隔离字段',
  PRIMARY KEY (`f_unit_id`) USING BTREE,
  UNIQUE INDEX `f_number`(`f_number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '计量单位' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for t_bd_unit_convert_rate
-- ----------------------------
DROP TABLE IF EXISTS `t_bd_unit_convert_rate`;
CREATE TABLE `t_bd_unit_convert_rate`  (
  `f_unit_convert_rateid` int NOT NULL AUTO_INCREMENT COMMENT '换算率内码',
  `f_bill_no` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '单据编号',
  `f_unit_id` int NOT NULL COMMENT '关联计量单位内码 ',
  `f_formid` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '单据类型',
  `f_material_id` int NULL DEFAULT NULL COMMENT '物料代码',
  `f_current_unit_id` int NULL DEFAULT NULL COMMENT '当前单位内码',
  `f_dest_unit_id` int NULL DEFAULT 1 COMMENT '目标单位内码',
  `f_convert_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '换算类型',
  `f_convert_numerator` decimal(23, 10) NULL DEFAULT NULL COMMENT '换算分子',
  `f_convert_denominator` decimal(23, 10) NULL DEFAULT NULL COMMENT '换算分母',
  `f_create_orgid` int NULL DEFAULT NULL COMMENT '创建组织',
  `f_creatorid` int NULL DEFAULT NULL COMMENT '创建人',
  `f_create_date` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `f_use_orgid` int NULL DEFAULT NULL COMMENT '使用组织',
  `f_modifierid` int NULL DEFAULT NULL COMMENT '修改人',
  `f_modify_date` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改日期',
  `f_document_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数据状态',
  `f_auditorid` int NULL DEFAULT NULL COMMENT '审核人',
  `f_audit_date` datetime NULL DEFAULT NULL COMMENT '审核日期',
  `f_forbid_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '禁用状态',
  `f_forbidderid` int NULL DEFAULT NULL COMMENT '禁用人',
  `f_forbid_date` datetime NULL DEFAULT NULL COMMENT '禁用日期',
  `f_issys_preset` int NULL DEFAULT 0 COMMENT '是否系统预设1 系统预设0 非系统预设默认0',
  `f_master_id` int NULL DEFAULT NULL COMMENT '组织隔离字段',
  PRIMARY KEY (`f_unit_convert_rateid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '单位换算' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for t_bd_unit_group
-- ----------------------------
DROP TABLE IF EXISTS `t_bd_unit_group`;
CREATE TABLE `t_bd_unit_group`  (
  `f_unit_group_id` int NOT NULL AUTO_INCREMENT COMMENT '内码',
  `f_number` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编码',
  `f_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `f_base_unit_number` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '基准单位编码',
  `f_base_unit_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '基准单位名称',
  `f_description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `f_isconvertenable` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '存在换算关系',
  `f_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '计量单位分组ID',
  `f_create_orgid` int NULL DEFAULT NULL COMMENT '创建组织',
  `f_creatorid` int NULL DEFAULT NULL COMMENT '创建人',
  `f_create_date` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `f_use_orgid` int NULL DEFAULT NULL COMMENT '使用组织',
  `f_modifierid` int NULL DEFAULT NULL COMMENT '修改人',
  `f_modify_date` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改日期',
  `f_document_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '数据状态',
  `f_auditorid` int NULL DEFAULT NULL COMMENT '审核人',
  `f_audit_date` datetime NULL DEFAULT NULL COMMENT '审核日期',
  `f_forbid_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '禁用状态',
  `f_forbidderid` int NULL DEFAULT NULL COMMENT '禁用人',
  `f_forbid_date` datetime NULL DEFAULT NULL COMMENT '禁用日期',
  `f_issys_preset` int NULL DEFAULT 0 COMMENT '是否系统预设1 系统预设0 非系统预设默认0',
  `f_master_id` int NULL DEFAULT NULL COMMENT '组织隔离字段',
  PRIMARY KEY (`f_unit_group_id`) USING BTREE,
  UNIQUE INDEX `f_number`(`f_number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '计量单位组' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for t_bd_unit_group_table
-- ----------------------------
DROP TABLE IF EXISTS `t_bd_unit_group_table`;
CREATE TABLE `t_bd_unit_group_table`  (
  `f_id` int NOT NULL AUTO_INCREMENT COMMENT '内码',
  `f_number` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编码',
  `f_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `f_parent_id` int NULL DEFAULT NULL COMMENT '上级ID',
  `f_description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`f_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '计量单位分组' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for t_bd_vchgroup_acct
-- ----------------------------
DROP TABLE IF EXISTS `t_bd_vchgroup_acct`;
CREATE TABLE `t_bd_vchgroup_acct`  (
  `f_entry_id` int NOT NULL AUTO_INCREMENT COMMENT '分录内码',
  `f_vchgroup_number` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '凭证字编码',
  `f_acct_number` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '科目编码',
  `f_debit` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '借方必有 1：必有 0非必有',
  `f_credit` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '贷方必有 1：必有 0非必有',
  `f_debit_credit` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '借或贷必有 1：必有 0非必有',
  `f_debit_no` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '借方必无  1：必有 0非必有',
  `f_credit_no` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '贷方必无 1：必有 0非必有',
  `f_debit_credit_no` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '借和贷必无 1：必有 0非必有',
  PRIMARY KEY (`f_entry_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 56 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '凭证字-科目控制' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for t_bd_voucher_group
-- ----------------------------
DROP TABLE IF EXISTS `t_bd_voucher_group`;
CREATE TABLE `t_bd_voucher_group`  (
  `f_vchgroup_id` int NOT NULL AUTO_INCREMENT COMMENT '内码',
  `f_limit_multi` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '限制多借多贷凭证 不限制:0  限制多借多贷：1  默认0\r\n ',
  `f_accttable_id` int NULL DEFAULT NULL COMMENT '科目表内码，用于单据体中科目的过滤 ',
  `f_isdefault` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否默认  0 非默认 1 默认',
  `f_number` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编码',
  `f_create_orgid` int NULL DEFAULT NULL COMMENT '创建组织',
  `f_creatorid` int NULL DEFAULT NULL COMMENT '创建人',
  `f_create_date` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `f_use_orgid` int NULL DEFAULT NULL COMMENT '使用组织',
  `f_modifierid` int NULL DEFAULT NULL COMMENT '修改人',
  `f_modify_date` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改日期',
  `f_document_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '数据状态(-1删除,0创建,1暂存,2重新审核,3已审核)',
  `f_auditorid` int NULL DEFAULT NULL COMMENT '审核人',
  `f_audit_date` datetime NULL DEFAULT NULL COMMENT '审核日期',
  `f_forbid_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '禁用状态(1是,0否)默认0',
  `f_forbidderid` int NULL DEFAULT NULL COMMENT '禁用人',
  `f_forbid_date` datetime NULL DEFAULT NULL COMMENT '禁用日期',
  `f_issys_preset` int NULL DEFAULT 0 COMMENT '是否系统预设1 系统预设0 非系统预设默认0',
  `f_master_id` int NULL DEFAULT NULL COMMENT '组织隔离字段',
  `f_voucher_words` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '凭证字',
  PRIMARY KEY (`f_vchgroup_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '凭证字' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for t_fa_acct_policy
-- ----------------------------
DROP TABLE IF EXISTS `t_fa_acct_policy`;
CREATE TABLE `t_fa_acct_policy`  (
  `f_acctpolicy_id` int NOT NULL AUTO_INCREMENT COMMENT '内码',
  `f_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `f_number` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编码',
  `f_description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `f_currency_id` int NULL DEFAULT NULL COMMENT '币别',
  `f_acctcalendar_id` int NULL DEFAULT NULL COMMENT '会计日历',
  `f_acctgroup_id` int NULL DEFAULT NULL COMMENT '会计要素',
  `f_ratetype_id` int NULL DEFAULT NULL COMMENT '汇率类型',
  `f_iscalbyexp_item` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '存货核算按费用项目明细核算默认0',
  `f_istax_amount` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '成本以含税金额进行核算',
  `f_iscalbyactual_cost` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '启用实际成本核算 默认0',
  `f_isdefault` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否默认 0：非默认，默认 1：',
  `f_create_orgid` int NULL DEFAULT NULL COMMENT '创建组织',
  `f_creatorid` int NULL DEFAULT NULL COMMENT '创建人',
  `f_create_date` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `f_use_orgid` int NULL DEFAULT NULL COMMENT '使用组织',
  `f_modifierid` int NULL DEFAULT NULL COMMENT '修改人',
  `f_modify_date` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改日期',
  `f_document_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '数据状态',
  `f_auditorid` int NULL DEFAULT NULL COMMENT '审核人',
  `f_audit_date` datetime NULL DEFAULT NULL COMMENT '审核日期',
  `f_forbid_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '禁用状态',
  `f_forbidderid` int NULL DEFAULT NULL COMMENT '禁用人',
  `f_forbid_date` datetime NULL DEFAULT NULL COMMENT '禁用日期',
  `f_issys_preset` int NULL DEFAULT 0 COMMENT '是否系统预设1 系统预设0 非系统预设默认0',
  `f_master_id` int NULL DEFAULT NULL COMMENT '组织隔离字段',
  PRIMARY KEY (`f_acctpolicy_id`) USING BTREE,
  UNIQUE INDEX `unique_f_number`(`f_number`) USING BTREE COMMENT '唯一编码索引'
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '会计政策' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for t_fa_acct_policy_asset
-- ----------------------------
DROP TABLE IF EXISTS `t_fa_acct_policy_asset`;
CREATE TABLE `t_fa_acct_policy_asset`  (
  `f_entry_id` int NOT NULL AUTO_INCREMENT COMMENT '内码',
  `f_seq` int NULL DEFAULT NULL COMMENT '序号',
  `f_acctpolicy_id` int NULL DEFAULT NULL COMMENT '会计政策内码',
  `f_asset_typeid` int NULL DEFAULT NULL COMMENT '资产类别',
  `f_legal_depryears` decimal(23, 10) NULL DEFAULT NULL COMMENT '法定折旧年限',
  `f_ent_depryears` decimal(23, 10) NULL DEFAULT NULL COMMENT '企业折旧年限',
  `f_residual_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '残值类型 1：百分比 2：金额',
  `f_legal_residual_rate` decimal(23, 10) NULL DEFAULT NULL COMMENT '法定残值率',
  `f_ent_residual_rate` decimal(23, 10) NULL DEFAULT NULL COMMENT '企业残值率',
  `f_residual_amount` decimal(23, 10) NULL DEFAULT NULL COMMENT '残值额',
  `f_depr_method` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '折旧方法 1：平均年限法 2：双倍余额递减法 3：年数总和法',
  `f_depr_policy_id` int NULL DEFAULT NULL COMMENT '折旧政策',
  `f_work_loadunit_id` int NULL DEFAULT NULL COMMENT '工作量单位',
  `f_entdepr_workload` decimal(23, 10) NULL DEFAULT NULL COMMENT '企业折旧工作量',
  `f_legal_depr_workload` decimal(23, 10) NULL DEFAULT NULL COMMENT '法定折旧工作量',
  PRIMARY KEY (`f_entry_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '会计政策资产政策' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for t_fa_acct_policy_org
-- ----------------------------
DROP TABLE IF EXISTS `t_fa_acct_policy_org`;
CREATE TABLE `t_fa_acct_policy_org`  (
  `f_entry_id` int NOT NULL AUTO_INCREMENT COMMENT '内码',
  `f_acctpolicy_id` int NULL DEFAULT NULL COMMENT '会计政策内码 ',
  `f_seq` int NULL DEFAULT NULL COMMENT '序号',
  `f_isdefault` char(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否默认 1是 0否',
  `f_acctsystem_id` int NULL DEFAULT NULL COMMENT '会计核算体系',
  `f_acct_orgid` int NULL DEFAULT NULL COMMENT '适用核算组织',
  `f_acct_book` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '适用账簿',
  `f_isaudit` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否审核',
  PRIMARY KEY (`f_entry_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '会计政策适用核算组织' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for t_fa_asset_type
-- ----------------------------
DROP TABLE IF EXISTS `t_fa_asset_type`;
CREATE TABLE `t_fa_asset_type`  (
  `f_id` int NOT NULL AUTO_INCREMENT COMMENT '内码',
  `f_number` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编码',
  `f_group` int NULL DEFAULT NULL COMMENT '资产类别分组',
  `f_code_rule` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '卡片编码规则',
  `f_asset_code_rule` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资产编码规则',
  `f_unit_id` int NULL DEFAULT NULL COMMENT '默认计量单位',
  `f_include_tax` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '原值包含进税项 1：包含 0不包含',
  `f_card_swift_number` int NULL DEFAULT NULL COMMENT '卡片编码流水号长度',
  `f_asset_swift_number` int NULL DEFAULT NULL COMMENT '资产编码流水号长度',
  `f_code_rule_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '卡片编码规则ID',
  `f_asset_code_ruleid` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资产编码规则ID',
  `f_create_orgid` int NULL DEFAULT NULL COMMENT '创建组织',
  `f_creatorid` int NULL DEFAULT NULL COMMENT '创建人',
  `f_create_date` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `f_use_orgid` int NULL DEFAULT NULL COMMENT '使用组织',
  `f_modifierid` int NULL DEFAULT NULL COMMENT '修改人',
  `f_modify_date` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改日期',
  `f_document_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '数据状态',
  `f_auditorid` int NULL DEFAULT NULL COMMENT '审核人',
  `f_audit_date` datetime NULL DEFAULT NULL COMMENT '审核日期',
  `f_forbid_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '禁用状态',
  `f_forbidderid` int NULL DEFAULT NULL COMMENT '禁用人',
  `f_forbid_date` datetime NULL DEFAULT NULL COMMENT '禁用日期',
  `f_issys_preset` int NULL DEFAULT 0 COMMENT '是否系统预设1 系统预设0 非系统预设默认0',
  `f_master_id` int NULL DEFAULT NULL COMMENT '组织隔离字段',
  PRIMARY KEY (`f_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '资产类别' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for t_fa_asset_type_group
-- ----------------------------
DROP TABLE IF EXISTS `t_fa_asset_type_group`;
CREATE TABLE `t_fa_asset_type_group`  (
  `f_id` int NOT NULL AUTO_INCREMENT COMMENT '内码',
  `f_number` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资产类别分组编码',
  PRIMARY KEY (`f_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '资产类别组' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for t_fa_depr_method
-- ----------------------------
DROP TABLE IF EXISTS `t_fa_depr_method`;
CREATE TABLE `t_fa_depr_method`  (
  `f_id` int NOT NULL AUTO_INCREMENT COMMENT '内码',
  `f_number` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资产类别分组编码',
  `f_depr_option` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '折旧依据\r\n1.最后一期提完折旧\r\n2.最后一期剩余折旧额不处理\r\n3.最后一期剩余折旧额大于2倍当期折旧额则继续提取，否则当期提完',
  `f_calc_way` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '剩余年限计算方法\r\n1、向下取整\r\n2、向上取整\r\n3、四舍五入\r\n4、精确计算',
  `f_create_orgid` int NULL DEFAULT NULL COMMENT '创建组织',
  `f_creatorid` int NULL DEFAULT NULL COMMENT '创建人',
  `f_create_date` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `f_use_orgid` int NULL DEFAULT NULL COMMENT '使用组织',
  `f_modifierid` int NULL DEFAULT NULL COMMENT '修改人',
  `f_modify_date` datetime NULL DEFAULT NULL COMMENT '修改日期',
  `f_document_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数据状态',
  `f_auditorid` int NULL DEFAULT NULL COMMENT '审核人',
  `f_audit_date` datetime NULL DEFAULT NULL COMMENT '审核日期',
  `f_forbid_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '禁用状态',
  `f_forbidderid` int NULL DEFAULT NULL COMMENT '禁用人',
  `f_forbid_date` datetime NULL DEFAULT NULL COMMENT '禁用日期',
  `f_issys_preset` int NULL DEFAULT 0 COMMENT '是否系统预设1 系统预设0 非系统预设默认0',
  `f_master_id` int NULL DEFAULT NULL COMMENT '组织隔离字段',
  PRIMARY KEY (`f_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '折旧方法' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for t_fa_depr_method_entry
-- ----------------------------
DROP TABLE IF EXISTS `t_fa_depr_method_entry`;
CREATE TABLE `t_fa_depr_method_entry`  (
  `f_entry_id` int NOT NULL AUTO_INCREMENT COMMENT '内码',
  `f_id` int NULL DEFAULT NULL COMMENT '折旧方法内码',
  `f_formula_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '公式类型 1、期折旧额 2、期折旧率',
  `f_formula_content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '公式内容',
  `f_isactive` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否静态 0静态 1动态',
  `f_islast_twoyear` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否最后两年 1：是最后两年 0否',
  PRIMARY KEY (`f_entry_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '折旧方法明细表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for t_fa_depr_method_item
-- ----------------------------
DROP TABLE IF EXISTS `t_fa_depr_method_item`;
CREATE TABLE `t_fa_depr_method_item`  (
  `f_id` int NOT NULL AUTO_INCREMENT COMMENT '内码',
  `f_field_type` int NULL DEFAULT NULL COMMENT '字段类型 1：文本 2：金额 ',
  `f_formula_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '对应的取数字段 ',
  `f_number` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编码',
  PRIMARY KEY (`f_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '折旧方法元素表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for t_fa_depr_policy
-- ----------------------------
DROP TABLE IF EXISTS `t_fa_depr_policy`;
CREATE TABLE `t_fa_depr_policy`  (
  `f_policy_id` int NOT NULL AUTO_INCREMENT COMMENT '内码',
  `f_number` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编码',
  `f_clear_depr_policy` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '新增、清理折旧政策\r\n1：新增当期计提折旧，清理当期不提折旧\r\n2：新增当期计提折旧，清理当期计提折旧\r\n3：新增当期不提折旧，清理当期计提折旧\r\n4：新增当期不提折旧，清理当期不提折旧',
  `f_isaffect_depr` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '变动当期即影响折旧\r\n0：不选中\r\n1：选中',
  `f_affectpr_policy` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '折旧要素变动时按静态折旧方法计算',
  `f_create_orgid` int NULL DEFAULT NULL COMMENT '创建组织',
  `f_creatorid` int NULL DEFAULT NULL COMMENT '创建人',
  `f_create_date` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `f_use_orgid` int NULL DEFAULT NULL COMMENT '使用组织',
  `f_modifierid` int NULL DEFAULT NULL COMMENT '修改人',
  `f_modify_date` datetime NULL DEFAULT NULL COMMENT '修改日期',
  `f_document_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数据状态',
  `f_auditorid` int NULL DEFAULT NULL COMMENT '审核人',
  `f_audit_date` datetime NULL DEFAULT NULL COMMENT '审核日期',
  `f_forbid_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '禁用状态',
  `f_forbidderid` int NULL DEFAULT NULL COMMENT '禁用人',
  `f_forbid_date` datetime NULL DEFAULT NULL COMMENT '禁用日期',
  `f_issys_preset` int NULL DEFAULT 0 COMMENT '是否系统预设1 系统预设0 非系统预设默认0',
  `f_master_id` int NULL DEFAULT NULL COMMENT '组织隔离字段',
  PRIMARY KEY (`f_policy_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '折旧政策' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for t_gl_adjustperiod
-- ----------------------------
DROP TABLE IF EXISTS `t_gl_adjustperiod`;
CREATE TABLE `t_gl_adjustperiod`  (
  `f_adjust_period_id` int NOT NULL AUTO_INCREMENT COMMENT '内码',
  `f_book_id` int NULL DEFAULT NULL COMMENT '账簿内码',
  `f_year` int NULL DEFAULT NULL COMMENT '会计年度',
  `f_adjust_period` int NULL DEFAULT NULL COMMENT '调整期间',
  `f_adjust_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '状态',
  `f_creator_id` int NULL DEFAULT NULL COMMENT '创建人',
  `f_create_date` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `f_modifier_id` int NULL DEFAULT NULL COMMENT '修改人',
  `f_modifier_date` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `f_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `f_describe` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`f_adjust_period_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for t_gl_amort_acct
-- ----------------------------
DROP TABLE IF EXISTS `t_gl_amort_acct`;
CREATE TABLE `t_gl_amort_acct`  (
  `f_scheme_id` int NULL DEFAULT NULL COMMENT '方案ID',
  `f_id` int NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `f_amortize_account` int NULL DEFAULT NULL COMMENT '待摊销科目',
  `f_amortizing_amount` decimal(23, 10) NULL DEFAULT NULL COMMENT '待摊销金额',
  `f_detail_id` int NULL DEFAULT NULL COMMENT '核算维度',
  `f_expression` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '公式定义',
  PRIMARY KEY (`f_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '凭证摊销-待摊销科目' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for t_gl_amort_acct_dimension
-- ----------------------------
DROP TABLE IF EXISTS `t_gl_amort_acct_dimension`;
CREATE TABLE `t_gl_amort_acct_dimension`  (
  `dimension_id` int NULL DEFAULT NULL COMMENT '维度ID',
  `ds_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '维度编码',
  `ds_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `amort_entry_id` int NULL DEFAULT NULL COMMENT '摊销关联ID',
  `acct_id` int NULL DEFAULT NULL COMMENT '科目ID'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '凭证摊销预提维度控制' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for t_gl_amort_inacct
-- ----------------------------
DROP TABLE IF EXISTS `t_gl_amort_inacct`;
CREATE TABLE `t_gl_amort_inacct`  (
  `f_id` int NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `f_scheme_id` int NOT NULL COMMENT '方案内码',
  `f_enter_account_id` int NOT NULL COMMENT '转入科目ID',
  `f_enter_ratio` decimal(23, 10) NULL DEFAULT NULL COMMENT '比例',
  `f_detail_id` int NULL DEFAULT NULL COMMENT '核算维度',
  `f_enter_ratio_fixed` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '固定',
  PRIMARY KEY (`f_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '凭证摊销-转入科目' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for t_gl_amort_inacct_dimension
-- ----------------------------
DROP TABLE IF EXISTS `t_gl_amort_inacct_dimension`;
CREATE TABLE `t_gl_amort_inacct_dimension`  (
  `dimension_id` int NULL DEFAULT NULL COMMENT '维度ID',
  `ds_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '维度编码',
  `ds_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `amort_entry_id` int NULL DEFAULT NULL COMMENT '摊销关联ID',
  `acct_id` int NULL DEFAULT NULL COMMENT '科目ID'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '凭证摊销转入科目维度控制' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for t_gl_amort_period
-- ----------------------------
DROP TABLE IF EXISTS `t_gl_amort_period`;
CREATE TABLE `t_gl_amort_period`  (
  `f_scheme_id` int NOT NULL COMMENT '方案内码',
  `f_year_period` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '会计期间',
  `f_amortratio` decimal(23, 10) NULL DEFAULT NULL COMMENT '摊销比例',
  `f_amortamount` decimal(23, 10) NULL DEFAULT NULL COMMENT '摊销金额',
  `f_amorted` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否摊销',
  `f_year` int NULL DEFAULT NULL COMMENT '会计年度',
  `f_period` int NULL DEFAULT NULL COMMENT '会计期间',
  `f_amort_ratio_fixed` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '固定'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '凭证摊销-摊销期间' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for t_gl_amortization_scheme
-- ----------------------------
DROP TABLE IF EXISTS `t_gl_amortization_scheme`;
CREATE TABLE `t_gl_amortization_scheme`  (
  `f_scheme_id` int NOT NULL AUTO_INCREMENT COMMENT '方案内码',
  `f_number` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '编码',
  `f_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `f_description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `f_isamort` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '是否摊销方案',
  `f_account_book_id` int NOT NULL COMMENT '账簿',
  `f_voucher_group_id` int NOT NULL COMMENT '凭证字',
  `f_currency_id` int NOT NULL COMMENT '币别',
  `f_exchange_rate_type` int NOT NULL COMMENT '汇率类型',
  `f_explanation` varchar(510) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '摘要',
  `f_pedding_amort_amount` decimal(23, 10) NULL DEFAULT NULL COMMENT '待摊销总额',
  `f_amorted_amount` decimal(23, 10) NULL DEFAULT NULL COMMENT '已摊销总额',
  `f_end_balance` decimal(23, 10) NULL DEFAULT NULL COMMENT '余额',
  `f_last_execute_time` datetime NULL DEFAULT NULL COMMENT '执行时间',
  `f_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '状态',
  `f_forbid_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '禁用状态',
  `f_forbidder_id` int NULL DEFAULT NULL COMMENT '禁用人',
  `f_forbid_date` datetime NULL DEFAULT NULL COMMENT '禁用时间',
  PRIMARY KEY (`f_scheme_id`) USING BTREE,
  UNIQUE INDEX `f_number`(`f_number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '凭证摊销' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for t_gl_auto_transfer
-- ----------------------------
DROP TABLE IF EXISTS `t_gl_auto_transfer`;
CREATE TABLE `t_gl_auto_transfer`  (
  `f_transfer_id` int NOT NULL AUTO_INCREMENT COMMENT '内码',
  `f_number` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编码',
  `f_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `f_book_id` int NULL DEFAULT NULL COMMENT '账簿内码',
  `f_transfer_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '转账类型',
  `f_voucher_group_id` int NOT NULL COMMENT '凭证字',
  `f_period_range` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '转账期间',
  `f_last_date` datetime NULL DEFAULT NULL COMMENT '最近一次转账时间',
  `f_opt_way` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '执行方式',
  `f_opt_frequency` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '执行频率',
  `f_forbid_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '禁用状态',
  `f_forbidder_id` int NULL DEFAULT NULL COMMENT '禁用人',
  `f_forbid_date` datetime NULL DEFAULT NULL COMMENT '禁用日期',
  PRIMARY KEY (`f_transfer_id`) USING BTREE,
  UNIQUE INDEX `f_number`(`f_number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '自动转账主表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for t_gl_auto_transfer_entry
-- ----------------------------
DROP TABLE IF EXISTS `t_gl_auto_transfer_entry`;
CREATE TABLE `t_gl_auto_transfer_entry`  (
  `f_transfer_entry_id` int NOT NULL AUTO_INCREMENT COMMENT '分录表内码',
  `f_transfer_id` int NOT NULL COMMENT '自动转账ID',
  `f_entry_seq` int NULL DEFAULT NULL COMMENT '序号',
  `f_explanation` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '摘要',
  `f_account_id` int NOT NULL COMMENT '会计科目',
  `f_item_isvalid` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '核算维度是否启用',
  `f_currency_id` int NOT NULL COMMENT '币别',
  `f_exchange_rate_type` int NOT NULL COMMENT '汇率类型',
  `f_dc` smallint NOT NULL COMMENT '方向',
  `f_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '转账方式',
  `f_percentage` decimal(15, 12) NULL DEFAULT NULL COMMENT '转账比例',
  `f_formula_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '公式方法',
  `f_amountfor_formula` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '原币公式',
  `f_amount_formula` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '本币公式',
  `f_qtymula` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数量公式',
  `f_ismulti_collect` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '不参与多栏账汇总',
  `f_posted` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '包含未过账凭证',
  PRIMARY KEY (`f_transfer_entry_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '自动转账分录表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for t_gl_auto_transfer_entry_item
-- ----------------------------
DROP TABLE IF EXISTS `t_gl_auto_transfer_entry_item`;
CREATE TABLE `t_gl_auto_transfer_entry_item`  (
  `f_entry_id` int NOT NULL AUTO_INCREMENT COMMENT '内码',
  `f_transfer_entry_id` int NOT NULL COMMENT '自动转账分录内码',
  `f_flexitem_property_id` int NULL DEFAULT NULL COMMENT '核算维度',
  `f_begin_item_number` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '起始核算维度编码',
  `f_end_item_number` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '截止核算维度编码',
  `f_issequent_select` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '连续范围过滤',
  `f_item_number` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '项目',
  PRIMARY KEY (`f_entry_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '自动转账核算维度' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for t_gl_balance
-- ----------------------------
DROP TABLE IF EXISTS `t_gl_balance`;
CREATE TABLE `t_gl_balance`  (
  `f_account_book_id` int NULL DEFAULT NULL COMMENT '账簿内码',
  `f_currency_id` int NULL DEFAULT NULL COMMENT '币别内码 ',
  `dimension_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '维度类型ID组合信息',
  `f_detail_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '核算维度值组合内码',
  `f_account_id` int NULL DEFAULT NULL COMMENT '科目内码',
  `f_period` int NULL DEFAULT NULL COMMENT '期间',
  `f_year` int NULL DEFAULT NULL COMMENT '年',
  `f_begin_balancefor` decimal(23, 10) NULL DEFAULT NULL COMMENT '期初原币金额',
  `f_begin_balance` decimal(23, 10) NULL DEFAULT NULL COMMENT '期初本位币金额',
  `f_debit_for` decimal(23, 10) NULL DEFAULT NULL COMMENT '借方原币金额',
  `f_debit` decimal(23, 10) NULL DEFAULT NULL COMMENT '借方本位币金额',
  `f_credit_for` decimal(23, 10) NULL DEFAULT NULL COMMENT '贷方原币金额',
  `f_credit` decimal(23, 10) NULL DEFAULT NULL COMMENT '贷方本位币金额',
  `f_ytd_debitfor` decimal(23, 10) NULL DEFAULT NULL COMMENT '本年累计借方原币金额',
  `f_ytd_debit` decimal(23, 10) NULL DEFAULT NULL COMMENT '本年累计借方本位币金额',
  `f_ytd_creditfor` decimal(23, 10) NULL DEFAULT NULL COMMENT '本年累计贷方原币金额',
  `f_ytd_credit` decimal(23, 10) NULL DEFAULT NULL COMMENT '本年累计贷方本位币金额',
  `f_end_balancefor` decimal(23, 10) NULL DEFAULT NULL COMMENT '期末原币金额',
  `f_end_balance` decimal(23, 10) NULL DEFAULT NULL COMMENT '期末本位币金额',
  `f_adjust_period` int NULL DEFAULT 0 COMMENT '调整期 =0表示正常期间凭证，非调整期 >0则表示对应的调整期',
  `f_year_period` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '年期',
  UNIQUE INDEX `badcpy`(`f_account_book_id`, `f_account_id`, `f_detail_code`, `f_currency_id`, `f_period`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '科目余额表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for t_gl_balance_init
-- ----------------------------
DROP TABLE IF EXISTS `t_gl_balance_init`;
CREATE TABLE `t_gl_balance_init`  (
  `f_account_book_id` int NULL DEFAULT NULL COMMENT '账簿内码',
  `dimension_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '维度ID组合',
  `f_detail_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '核算维度值组合内码',
  `f_account_id` int NULL DEFAULT NULL COMMENT '科目内码',
  `f_currency_id` int NULL DEFAULT NULL COMMENT '币别内码 ',
  `f_period` int NULL DEFAULT NULL COMMENT '期间',
  `f_year` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '年',
  `f_dc` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '余额方向',
  `f_begin_balancefor` decimal(23, 10) NULL DEFAULT NULL COMMENT '期初原币金额',
  `f_begin_balance` decimal(23, 10) NULL DEFAULT NULL COMMENT '期初本位币金额',
  `f_debit_for` decimal(23, 10) NULL DEFAULT NULL COMMENT '借方原币金额',
  `f_debit` decimal(23, 10) NULL DEFAULT NULL COMMENT '借方本位币金额',
  `f_credit_for` decimal(23, 10) NULL DEFAULT NULL COMMENT '贷方原币金额',
  `f_credit` decimal(23, 10) NULL DEFAULT NULL COMMENT '贷方本位币金额',
  `f_ytd_debitfor` decimal(23, 10) NULL DEFAULT NULL COMMENT '本年累计借方原币金额',
  `f_ytd_debit` decimal(23, 10) NULL DEFAULT NULL COMMENT '本年累计借方本位币金额',
  `f_ytd_creditfor` decimal(23, 10) NULL DEFAULT NULL COMMENT '本年累计贷方原币金额',
  `f_ytd_credit` decimal(23, 10) NULL DEFAULT NULL COMMENT '本年累计贷方本位币金额',
  `f_end_balancefor` decimal(23, 10) NULL DEFAULT NULL COMMENT '期末原币金额',
  `f_end_balance` decimal(23, 10) NULL DEFAULT NULL COMMENT '期末本位币金额',
  `f_adjust_period` int NULL DEFAULT NULL COMMENT '调整期 =0表示正常期间凭证，非调整期 >0则表示对应的调整期',
  `f_year_period` int NULL DEFAULT NULL COMMENT '年期'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '科目初始录入数据表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for t_gl_cash_flow
-- ----------------------------
DROP TABLE IF EXISTS `t_gl_cash_flow`;
CREATE TABLE `t_gl_cash_flow`  (
  `f_id` int NOT NULL AUTO_INCREMENT COMMENT '内码',
  `f_number` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编码',
  `f_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `f_description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `f_parent_id` int NULL DEFAULT NULL COMMENT '上级流量项目',
  `f_level` int NULL DEFAULT NULL COMMENT '级次',
  `f_direction` int NULL DEFAULT NULL COMMENT '方向',
  `f_item_typeid` int NULL DEFAULT NULL COMMENT '项目类别内码 1:现金流入;-1:现金流出 ',
  `f_isdetail` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否明细 1明细 0非明细',
  `f_cash_flow_item_table` int NULL DEFAULT NULL COMMENT '现金流量项目表',
  `f_create_orgid` int NULL DEFAULT NULL COMMENT '创建组织',
  `f_creatorid` int NULL DEFAULT NULL COMMENT '创建人',
  `f_create_date` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `f_use_orgid` int NULL DEFAULT NULL COMMENT '使用组织',
  `f_modifierid` int NULL DEFAULT NULL COMMENT '修改人',
  `f_modify_date` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改日期',
  `f_document_status` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '数据状态',
  `f_auditorid` int NULL DEFAULT NULL COMMENT '审核人',
  `f_audit_date` datetime NULL DEFAULT NULL COMMENT '审核日期',
  `f_forbid_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '禁用状态',
  `f_forbidderid` int NULL DEFAULT NULL COMMENT '禁用人',
  `f_forbid_date` datetime NULL DEFAULT NULL COMMENT '禁用日期',
  `f_issys_preset` int NULL DEFAULT 0 COMMENT '是否系统预设1 系统预设0 非系统预设默认0',
  `f_master_id` int NULL DEFAULT NULL COMMENT '组织隔离字段',
  PRIMARY KEY (`f_id`) USING BTREE,
  UNIQUE INDEX `unique_f_number`(`f_number`) USING BTREE COMMENT '唯一编码索引'
) ENGINE = InnoDB AUTO_INCREMENT = 65 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '现金流量项目-3' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for t_gl_cash_flow_item_table
-- ----------------------------
DROP TABLE IF EXISTS `t_gl_cash_flow_item_table`;
CREATE TABLE `t_gl_cash_flow_item_table`  (
  `f_id` int NOT NULL AUTO_INCREMENT COMMENT '内码',
  `f_number` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编码',
  `f_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `f_acct_group_tblid` int NULL DEFAULT NULL COMMENT '会计要素表ID',
  `f_creatorid` int NULL DEFAULT NULL COMMENT '创建人',
  `f_create_date` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `f_modifierid` int NULL DEFAULT NULL COMMENT '修改人',
  `f_modify_date` datetime NULL DEFAULT NULL COMMENT '修改日期',
  PRIMARY KEY (`f_id`) USING BTREE,
  UNIQUE INDEX `unique_f_number`(`f_number`) USING BTREE COMMENT '唯一编码索引'
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '现金流量项目表-1' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for t_gl_cash_flow_type
-- ----------------------------
DROP TABLE IF EXISTS `t_gl_cash_flow_type`;
CREATE TABLE `t_gl_cash_flow_type`  (
  `f_item_typeid` int NOT NULL AUTO_INCREMENT COMMENT '现金流量项目类别内码',
  `f_number` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编码',
  `f_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `f_item_groupid` int NULL DEFAULT NULL COMMENT '类别属性 主表项目 = 1，附表项目 = 2',
  `f_group_typeid` int NULL DEFAULT NULL COMMENT '项目分类 经营活动 = 1投资活动 = 2筹资活动 = 3汇率变动和其他 = 4  ',
  `f_description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `f_cash_flow_item_table` int NULL DEFAULT NULL COMMENT '现金流量项目表 ',
  `f_create_orgid` int NULL DEFAULT NULL COMMENT '创建组织',
  `f_creatorid` int NULL DEFAULT NULL COMMENT '创建人',
  `f_create_date` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `f_use_orgid` int NULL DEFAULT NULL COMMENT '使用组织',
  `f_modifierid` int NULL DEFAULT NULL COMMENT '修改人',
  `f_modify_date` datetime NULL DEFAULT NULL COMMENT '修改日期',
  `f_master_id` int NULL DEFAULT NULL COMMENT '组织隔离字段',
  PRIMARY KEY (`f_item_typeid`) USING BTREE,
  UNIQUE INDEX `unique_f_number`(`f_number`) USING BTREE COMMENT '唯一编码索引'
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '现金流量项目类别-2' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for t_gl_exchange_flex_entry
-- ----------------------------
DROP TABLE IF EXISTS `t_gl_exchange_flex_entry`;
CREATE TABLE `t_gl_exchange_flex_entry`  (
  `f_entry_id` int NOT NULL AUTO_INCREMENT COMMENT '分录主键',
  `f_id` int NULL DEFAULT NULL COMMENT '期末调汇方案主键',
  `f_flex_id` int NULL DEFAULT NULL COMMENT '核算维度ID',
  `f_flex_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '核算维度值名字',
  `f_flex_value` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '核算维度值',
  PRIMARY KEY (`f_entry_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '期末调汇核算维度分录表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for t_gl_exchange_scheme
-- ----------------------------
DROP TABLE IF EXISTS `t_gl_exchange_scheme`;
CREATE TABLE `t_gl_exchange_scheme`  (
  `f_id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `f_number` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '编码',
  `f_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `f_description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `f_explanation` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '摘要',
  `f_account_book_id` int NULL DEFAULT NULL COMMENT '账簿',
  `f_generate_type` int NULL DEFAULT NULL COMMENT '生成方式',
  `f_operator_id` int NULL DEFAULT NULL COMMENT '操作人',
  `f_frequency` int NULL DEFAULT NULL COMMENT '自动生成频率',
  `f_generate_day` int NULL DEFAULT 0 COMMENT '自动生成天',
  `f_generate_hour` int NULL DEFAULT 0 COMMENT '自动生成时',
  `f_generate_minute` int NULL DEFAULT 0 COMMENT '自动生成分',
  `f_last_execute_time` datetime NULL DEFAULT NULL COMMENT '最后执行时间',
  `f_document_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '数据状态',
  `f_forbid_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '禁用状态',
  `f_forbider_id` int NULL DEFAULT NULL COMMENT '禁用人',
  `f_forbid_date` datetime NULL DEFAULT NULL COMMENT '禁用时间',
  `f_creator_id` int NULL DEFAULT NULL COMMENT '创建人',
  `f_creator_date` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `f_modifier_id` int NULL DEFAULT NULL COMMENT '修改人',
  `f_modify_date` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `f_vchgroup_id` int NULL DEFAULT NULL COMMENT '凭证字',
  `f_voucher_date_type` int NULL DEFAULT NULL COMMENT '凭证日期方式',
  `f_exchange_type` int NULL DEFAULT NULL COMMENT '汇率类型',
  `f_vch_process_type` int NULL DEFAULT NULL COMMENT '凭证处理方式',
  `f_result_vch_process_type` int NULL DEFAULT NULL COMMENT '结果凭证处理方式',
  `f_acct_chose_type` int NULL DEFAULT NULL COMMENT '科目选择方式',
  `f_transfer_type` int NULL DEFAULT NULL COMMENT '凭证类型',
  `f_exac_count` int NULL DEFAULT NULL COMMENT '汇兑损益科目',
  `f_dc` int NULL DEFAULT NULL COMMENT '汇兑损益科目方向',
  `f_exchange_acct_dc` int NULL DEFAULT NULL COMMENT '调汇科目方向',
  `f_allocate_date_type` int NULL DEFAULT NULL COMMENT '调汇日期方式',
  `f_pl` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '按收益和损益分开生成凭证',
  `f_isset_flex_item` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否已指定核算维度',
  `f_isdraft_voucher` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '是否暂存失败的凭证',
  `f_year` int NULL DEFAULT NULL COMMENT '年：凭证所在年度',
  `f_period` int NULL DEFAULT NULL COMMENT '期 :凭证所在期间',
  `f_isadjust_voucher` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '是否调整期',
  PRIMARY KEY (`f_id`) USING BTREE,
  UNIQUE INDEX `f_number`(`f_number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '期末调汇方案' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for t_gl_exchange_scheme_entry
-- ----------------------------
DROP TABLE IF EXISTS `t_gl_exchange_scheme_entry`;
CREATE TABLE `t_gl_exchange_scheme_entry`  (
  `f_entry_id` int NOT NULL AUTO_INCREMENT COMMENT '分录主键',
  `f_id` int NULL DEFAULT NULL COMMENT '期末调汇方案主键',
  `f_acct_id` int NULL DEFAULT NULL COMMENT '待调汇科目',
  `f_is_selected` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '是否选中',
  `fseq` int NULL DEFAULT 0 COMMENT '序号',
  PRIMARY KEY (`f_entry_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '期末调汇方案分录' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for t_gl_explanation
-- ----------------------------
DROP TABLE IF EXISTS `t_gl_explanation`;
CREATE TABLE `t_gl_explanation`  (
  `f_id` int NOT NULL AUTO_INCREMENT COMMENT '内码',
  `f_number` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编码',
  `f_explanation_groupid` int NULL DEFAULT NULL COMMENT '摘要类别内码',
  `f_ismulti_collect` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '不参与多栏账汇总  1是 0否',
  `f_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `f_create_orgid` int NULL DEFAULT NULL COMMENT '创建组织',
  `f_creatorid` int NULL DEFAULT NULL COMMENT '创建人',
  `f_create_date` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `f_use_orgid` int NULL DEFAULT NULL COMMENT '使用组织',
  `f_modifierid` int NULL DEFAULT NULL COMMENT '修改人',
  `f_modify_date` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改日期',
  `f_document_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '数据状态',
  `f_auditorid` int NULL DEFAULT NULL COMMENT '审核人',
  `f_audit_date` datetime NULL DEFAULT NULL COMMENT '审核日期',
  `f_forbid_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '禁用状态',
  `f_forbidderid` int NULL DEFAULT NULL COMMENT '禁用人',
  `f_forbid_date` datetime NULL DEFAULT NULL COMMENT '禁用日期',
  `f_issys_preset` int NULL DEFAULT 0 COMMENT '是否系统预设1 系统预设0 非系统预设默认0',
  `f_master_id` int NULL DEFAULT NULL COMMENT '组织隔离字段',
  PRIMARY KEY (`f_id`) USING BTREE,
  UNIQUE INDEX `f_number`(`f_number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '摘要库' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for t_gl_explanation_type
-- ----------------------------
DROP TABLE IF EXISTS `t_gl_explanation_type`;
CREATE TABLE `t_gl_explanation_type`  (
  `f_id` int NOT NULL AUTO_INCREMENT COMMENT '内码',
  `f_number` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编码',
  `f_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `f_description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `f_create_orgid` int NULL DEFAULT NULL COMMENT '创建组织',
  `f_creatorid` int NULL DEFAULT NULL COMMENT '创建人',
  `f_create_date` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `f_use_orgid` int NULL DEFAULT NULL COMMENT '使用组织',
  `f_modifierid` int NULL DEFAULT NULL COMMENT '修改人',
  `f_modify_date` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改日期',
  `f_document_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '数据状态',
  `f_auditorid` int NULL DEFAULT NULL COMMENT '审核人',
  `f_audit_date` datetime NULL DEFAULT NULL COMMENT '审核日期',
  `f_forbid_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '禁用状态',
  `f_forbidderid` int NULL DEFAULT NULL COMMENT '禁用人',
  `f_forbid_date` datetime NULL DEFAULT NULL COMMENT '禁用日期',
  `f_issys_preset` int NULL DEFAULT 0 COMMENT '是否系统预设1 系统预设0 非系统预设默认0',
  `f_master_id` int NULL DEFAULT NULL COMMENT '组织隔离字段',
  PRIMARY KEY (`f_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '摘要类别' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for t_gl_init_cashflow
-- ----------------------------
DROP TABLE IF EXISTS `t_gl_init_cashflow`;
CREATE TABLE `t_gl_init_cashflow`  (
  `f_id` int NOT NULL AUTO_INCREMENT COMMENT '内码',
  `f_account_book_id` int NULL DEFAULT NULL COMMENT '账簿内码',
  `f_item_id` int NULL DEFAULT NULL COMMENT '现金流量项目内码',
  `f_currency_id` int NULL DEFAULT NULL COMMENT '币别',
  `f_is_detail` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否明细',
  `f_amount_for` decimal(23, 0) NULL DEFAULT NULL COMMENT '原币金额',
  `f_amount` decimal(23, 0) NULL DEFAULT NULL COMMENT '本位币金额',
  `f_type_id` int NULL DEFAULT NULL COMMENT '现金流量项目类别内码',
  PRIMARY KEY (`f_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '现金流量项目初始余额' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for t_gl_init_dimension
-- ----------------------------
DROP TABLE IF EXISTS `t_gl_init_dimension`;
CREATE TABLE `t_gl_init_dimension`  (
  `f_id` int NOT NULL AUTO_INCREMENT COMMENT '科目初始核算维度内码',
  `f_acct_id` int NULL DEFAULT NULL COMMENT '科目ID',
  `f_book_id` int NULL DEFAULT NULL COMMENT '账簿ID',
  `f_dimension_id` int NULL DEFAULT NULL COMMENT '维度内码',
  `f_dimension_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '维度名称',
  `f_begin_balancefor` decimal(23, 10) NULL DEFAULT NULL COMMENT '期初原币金额',
  `f_begin_balance` decimal(23, 10) NULL DEFAULT NULL COMMENT '期初本位币金额',
  `f_debit_for` decimal(23, 10) NULL DEFAULT NULL COMMENT '借方原币金额',
  `f_debit` decimal(23, 10) NULL DEFAULT NULL COMMENT '借方本位币金额',
  `f_credit_for` decimal(23, 10) NULL DEFAULT NULL COMMENT '贷方原币金额',
  `f_credit` decimal(23, 10) NULL DEFAULT NULL COMMENT '贷方本位币金额',
  `f_ytd_debitfor` decimal(23, 10) NULL DEFAULT NULL COMMENT '本年累计借方原币金额',
  `f_ytd_debit` decimal(23, 10) NULL DEFAULT NULL COMMENT '本年累计借方本位币金额',
  `f_ytd_creditfor` decimal(23, 10) NULL DEFAULT NULL COMMENT '本年累计贷方原币金额',
  `f_ytd_credit` decimal(23, 10) NULL DEFAULT NULL COMMENT '本年累计贷方本位币金额',
  `f_end_balancefor` decimal(23, 10) NULL DEFAULT NULL COMMENT '期末原币金额',
  `f_end_balance` decimal(23, 10) NULL DEFAULT NULL COMMENT '期末本位币金额',
  `f_create_orgid` int NULL DEFAULT NULL COMMENT '创建组织',
  `f_creatorid` int NULL DEFAULT NULL COMMENT '创建人',
  `f_create_date` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `f_use_orgid` int NULL DEFAULT NULL COMMENT '使用组织',
  `f_modifierid` int NULL DEFAULT NULL COMMENT '修改人',
  `f_modify_date` datetime NULL DEFAULT NULL COMMENT '修改日期',
  `f_document_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数据状态',
  `f_auditorid` int NULL DEFAULT NULL COMMENT '审核人',
  `f_audit_date` datetime NULL DEFAULT NULL COMMENT '审核日期',
  `f_forbid_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '禁用状态',
  `f_forbidderid` int NULL DEFAULT NULL COMMENT '禁用人',
  `f_forbid_date` datetime NULL DEFAULT NULL COMMENT '禁用日期',
  `f_issys_preset` int NULL DEFAULT 0 COMMENT '是否系统预设1 系统预设0 非系统预设默认0',
  `f_master_id` int NULL DEFAULT NULL COMMENT '组织隔离字段',
  PRIMARY KEY (`f_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '科目核算维度初始数据表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for t_gl_plscheme
-- ----------------------------
DROP TABLE IF EXISTS `t_gl_plscheme`;
CREATE TABLE `t_gl_plscheme`  (
  `f_id` int NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `f_number` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '编码',
  `f_explanation` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '摘要',
  `f_account_id` int NULL DEFAULT NULL COMMENT '科目ID',
  `f_dc` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '方向',
  `f_description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `f_account_book_id` int NOT NULL COMMENT '账簿ID',
  `f_generate_type` int NULL DEFAULT 1 COMMENT '生成方式 1手动 0自动',
  `f_operator_id` int NULL DEFAULT NULL COMMENT '操作人',
  `f_frequency` int NULL DEFAULT NULL COMMENT '频率：0,年1,月 2,周',
  `f_generate_day` int NULL DEFAULT NULL COMMENT '生成天',
  `f_generate_hour` int NULL DEFAULT NULL COMMENT '生成小时',
  `f_generate_minute` int NULL DEFAULT NULL COMMENT '生成分钟',
  `f_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `f_last_execute_time` datetime NULL DEFAULT NULL COMMENT '最后执行时间',
  `f_document_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '数据状态',
  `f_forbid_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '禁用状态',
  `f_forbider_id` int NULL DEFAULT NULL COMMENT '禁用人',
  `f_forbid_date` datetime NULL DEFAULT NULL COMMENT '禁用时间',
  `f_creator_id` int NULL DEFAULT NULL COMMENT '创建人',
  `f_creator_date` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `f_modifier_id` int NULL DEFAULT NULL COMMENT '修改人',
  `f_modify_date` datetime NULL DEFAULT NULL COMMENT '修改日期',
  `f_vchgroup_id` int NULL DEFAULT NULL COMMENT '凭证字',
  `f_voucher_date_type` int NULL DEFAULT NULL COMMENT '凭证日期类型',
  `f_transfer_type` int NULL DEFAULT NULL COMMENT '结转方式',
  `f_voucher_type` int NULL DEFAULT NULL COMMENT '凭证生成方式',
  `f_plvch_process_type` int NULL DEFAULT NULL COMMENT '损益凭证处理方式',
  `f_result_vch_process_type` int NULL DEFAULT NULL COMMENT '结果凭证处理方式',
  `f_is_acct_dc` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否按科目的反方向生成凭证',
  `f_is_connect_unit` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否关联单位',
  `f_is_mergepl_acct` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否合并损益科目',
  `f_flex_item` int NULL DEFAULT NULL COMMENT '核算维度',
  `f_acct_chose_type` int NULL DEFAULT NULL COMMENT '科目选择方式',
  `f_is_draft_voucher` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否暂存失败的凭证',
  `f_year` int NULL DEFAULT NULL COMMENT '年：凭证所在年度',
  `f_period` int NULL DEFAULT NULL COMMENT '期 :凭证所在期间',
  `f_isadjust_voucher` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '是否调整期',
  PRIMARY KEY (`f_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '结转损益方案' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for t_gl_plscheme_entry
-- ----------------------------
DROP TABLE IF EXISTS `t_gl_plscheme_entry`;
CREATE TABLE `t_gl_plscheme_entry`  (
  `f_entry_id` int NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `f_id` int NOT NULL COMMENT '结转损益方案主键',
  `f_acct_id` int NOT NULL COMMENT '待结转科目',
  `f_placct_id` int NULL DEFAULT NULL COMMENT '结转科目',
  `f_is_selected` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '是否选中',
  `f_seq` int NOT NULL COMMENT '序号',
  PRIMARY KEY (`f_entry_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '结转损益方案分录表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for t_gl_plscheme_flex
-- ----------------------------
DROP TABLE IF EXISTS `t_gl_plscheme_flex`;
CREATE TABLE `t_gl_plscheme_flex`  (
  `f_entry_id` int NOT NULL AUTO_INCREMENT COMMENT '分录主键',
  `f_id` int NULL DEFAULT NULL COMMENT '结账损益方案主键',
  `f_flex_id` int NULL DEFAULT NULL COMMENT '核算维度ID',
  `f_flex_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '核算维度值名字',
  `f_flex_value` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '核算维度值',
  PRIMARY KEY (`f_entry_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '结账损益核算维度表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for t_gl_voucher
-- ----------------------------
DROP TABLE IF EXISTS `t_gl_voucher`;
CREATE TABLE `t_gl_voucher`  (
  `f_voucher_id` int NOT NULL AUTO_INCREMENT COMMENT '内码',
  `f_account_book_id` int NULL DEFAULT NULL COMMENT '账簿内码',
  `f_acct_orgid` int NULL DEFAULT NULL COMMENT '核算组织内码',
  `f_date` date NULL DEFAULT NULL COMMENT '日期',
  `f_year` int NULL DEFAULT NULL COMMENT '年：凭证所在年度，根据凭证日期FDATE字段，结合会计日历进行计算所得 ',
  `f_period` int NULL DEFAULT NULL COMMENT '期 :凭证所在期间，根据凭证日期FDATE及会计日历计算所得',
  `f_bill_no` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '期号',
  `f_voucher_group_id` int NULL DEFAULT NULL COMMENT '凭证字：取自于基础资料凭证字，保存时还需要根据凭证字上面的相关属性进行控制',
  `f_voucher_group_no` int NULL DEFAULT NULL COMMENT '凭证号',
  `f_attachments` int NULL DEFAULT NULL COMMENT '附件数：凭证附件数',
  `f_internalind` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '凭证来源：\r\n1、一般凭证：手工录入\r\n2、业务系统凭证：由业务系统单据生成\r\n3、机制凭证：总账系统通过凭证模板系统自动生成',
  `f_reference` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '参考信息',
  `f_settle_type_id` int NULL DEFAULT NULL COMMENT '结算方式：来源于基础资料结算方式，当科目为银行科目时必录',
  `f_settle_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '结算号',
  `f_base_currency_id` int NULL DEFAULT NULL COMMENT '本位币',
  `f_debit_total` decimal(23, 10) NULL DEFAULT NULL COMMENT '借方总金额：凭证分录上借方金额的总和',
  `f_credit_total` decimal(23, 10) NULL DEFAULT NULL COMMENT '贷方总金额：凭证分录上贷方金额的汇总',
  `f_cashier_id` int NULL DEFAULT NULL COMMENT '出纳：来源于用户',
  `f_creatorid` int NULL DEFAULT NULL COMMENT '创建人',
  `f_create_date` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `f_modifierid` int NULL DEFAULT NULL COMMENT '修改人',
  `f_modify_date` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改日期',
  `f_document_status` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '数据状态',
  `f_checked` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '是否审核',
  `f_checker_id` int NULL DEFAULT NULL COMMENT '审核人ID',
  `f_audit_date` datetime NULL DEFAULT NULL COMMENT '审核日期',
  `f_posted` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '是否过账：0 未过账 1 已过账',
  `f_poster_id` int NULL DEFAULT NULL COMMENT '过账：来源于用户',
  `f_post_date` datetime NULL DEFAULT NULL COMMENT '过账日期',
  `f_adjust_period` int NULL DEFAULT 0 COMMENT '调整期=0表示正常期间凭证，非调整期 >0则表示对应的调整期',
  `f_invalid` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '是否作废凭证：0正常凭证，1作废凭证',
  `f_invalider_id` int NULL DEFAULT NULL COMMENT '作废人',
  `f_mapvch_id` int NULL DEFAULT NULL COMMENT '关联凭证ID',
  `f_source_bill_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '来源单据标识，记录该凭证的数据来源于哪种类型的单据（记录对应单据的FormID）',
  `f_isadjust_voucher` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '是否调整期凭证',
  `f_system_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '对应业务对象：BOS_SubSystem中fid',
  `f_iscash_flow` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '已指定流量 1是 0否',
  `f_issplit` int NULL DEFAULT 0 COMMENT '是否拆分 0:未拆分1:按金额接近拆分2:按金额比例拆分',
  `f_isvir_post` int NULL DEFAULT 0 COMMENT '是否虚拟过账 0否 1是',
  `f_print_times` int NULL DEFAULT 0 COMMENT '打印次数',
  `f_bus_date` date NULL DEFAULT NULL COMMENT '业务日期',
  `f_settle` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '是否结账 0否 1是',
  PRIMARY KEY (`f_voucher_id`) USING BTREE,
  UNIQUE INDEX `f_account_book_id`(`f_account_book_id`, `f_year`, `f_period`, `f_voucher_group_id`, `f_voucher_group_no`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '凭证录入主表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for t_gl_voucher_entry
-- ----------------------------
DROP TABLE IF EXISTS `t_gl_voucher_entry`;
CREATE TABLE `t_gl_voucher_entry`  (
  `f_entry_id` int NOT NULL AUTO_INCREMENT COMMENT '内码',
  `f_voucher_id` int NOT NULL COMMENT '凭证主表内码',
  `f_explanation` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '摘要：文本信息，可以来源于基础资料摘要',
  `f_account_id` int NULL DEFAULT NULL COMMENT '科目信息ID',
  `f_detail_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '核算维度值编码信息',
  `dimension_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '核算维度类ID组合',
  `f_amountfor` decimal(23, 10) NULL DEFAULT NULL COMMENT '原币金额',
  `f_amount` decimal(23, 10) NULL DEFAULT NULL COMMENT '本位币金额',
  `f_currency_id` int NULL DEFAULT NULL COMMENT '币别',
  `f_exchange_rate_type` int NULL DEFAULT NULL COMMENT '汇率类型',
  `f_exchange_rate` decimal(23, 10) NULL DEFAULT NULL COMMENT '汇率',
  `f_dc` char(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '借贷方向',
  `f_measure_unit_id` int NULL DEFAULT NULL COMMENT '计量单位\r\n',
  `f_unit_price` decimal(23, 10) NULL DEFAULT NULL COMMENT '单价',
  `f_quantity` decimal(23, 10) NULL DEFAULT NULL COMMENT '数量',
  `f_acct_unit_qty` decimal(23, 10) NULL DEFAULT NULL COMMENT '科目单位数量',
  `f_base_unit_qty` decimal(23, 10) NULL DEFAULT NULL COMMENT '计量单位数量',
  `f_settle_type_id` int NULL DEFAULT NULL COMMENT '结算方式',
  `f_settle_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '结算号',
  `f_debit` decimal(23, 10) NULL DEFAULT NULL COMMENT '借方金额',
  `f_credit` decimal(23, 10) NULL DEFAULT NULL COMMENT '贷方金额',
  `f_entry_seq` int NULL DEFAULT NULL COMMENT '行号',
  `f_side_entry_seq` int NULL DEFAULT NULL COMMENT '对方科目所在的分录id',
  `f_cash_flow_item` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '是否已指定现金流量',
  `f_original_detail_id` int NULL DEFAULT NULL COMMENT '原始核算维度组合ID 核算维度：记录核算数据的内码',
  `f_ismulti_collect` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否参与多栏账汇总',
  PRIMARY KEY (`f_entry_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 76 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '凭证录入分表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for t_gl_voucher_entry_cash
-- ----------------------------
DROP TABLE IF EXISTS `t_gl_voucher_entry_cash`;
CREATE TABLE `t_gl_voucher_entry_cash`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '凭证现金流量ID',
  `book_id` int NULL DEFAULT NULL COMMENT '账簿ID',
  `voucher_entry_id` int NULL DEFAULT NULL COMMENT '现金分录ID',
  `for_voucher_entry_id` int NULL DEFAULT NULL COMMENT '对方分录ID',
  `cash_account_id` int NULL DEFAULT NULL COMMENT '现金科目',
  `for_acct_id` int NULL DEFAULT NULL COMMENT '对方科目ID',
  `main_table_id` int NULL DEFAULT NULL COMMENT '主表ID',
  `attach_table_id` int NULL DEFAULT NULL COMMENT '附表ID',
  `currency_id` int NULL DEFAULT NULL COMMENT '币别',
  `amount_for` decimal(23, 10) NULL DEFAULT NULL COMMENT '原币金额',
  `f_explanation` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '摘要',
  `amount` decimal(23, 10) NULL DEFAULT NULL COMMENT '本位币金额',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '凭证分录现金流量表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for t_gl_voucher_entry_dimension
-- ----------------------------
DROP TABLE IF EXISTS `t_gl_voucher_entry_dimension`;
CREATE TABLE `t_gl_voucher_entry_dimension`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '凭证维度内码',
  `dimension_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '维度ID组合',
  `dimension_all` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '维度组合信息',
  `ds_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '维度值编码',
  `ds_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '维度值名称',
  `voucher_entry_id` int NULL DEFAULT NULL COMMENT '凭证分录ID',
  `book_id` int NULL DEFAULT NULL COMMENT '账簿ID',
  `acct_id` int NULL DEFAULT NULL COMMENT '科目ID',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user` int NULL DEFAULT NULL COMMENT '创建用户',
  `update_user` int NULL DEFAULT NULL COMMENT '更新用户',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '凭证分录维度控制' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for t_gl_voucher_group_no
-- ----------------------------
DROP TABLE IF EXISTS `t_gl_voucher_group_no`;
CREATE TABLE `t_gl_voucher_group_no`  (
  `f_id` int NOT NULL AUTO_INCREMENT COMMENT '内码',
  `f_year` int NULL DEFAULT NULL COMMENT '年',
  `f_voucher_group_no` int NOT NULL COMMENT '凭证号',
  `f_period` int NULL DEFAULT NULL COMMENT '期间',
  `f_voucher_group_id` int NOT NULL COMMENT '凭证字ID',
  `f_book_id` int NULL DEFAULT NULL COMMENT '账簿ID',
  PRIMARY KEY (`f_id`) USING BTREE,
  UNIQUE INDEX `f_year`(`f_year`, `f_voucher_group_no`, `f_period`, `f_voucher_group_id`, `f_book_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 48 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '凭证号排序表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for t_gl_withholding_acct
-- ----------------------------
DROP TABLE IF EXISTS `t_gl_withholding_acct`;
CREATE TABLE `t_gl_withholding_acct`  (
  `f_id` int NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `f_scheme_id` int NULL DEFAULT NULL COMMENT '方案内码',
  `f_provision_account` int NULL DEFAULT NULL COMMENT '预提科目',
  `f_detail_id` int NULL DEFAULT NULL COMMENT '核算维度',
  `f_provision_ratio` decimal(23, 10) NULL DEFAULT NULL COMMENT '比例',
  `f_provision_fixed` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '固定',
  PRIMARY KEY (`f_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '凭证预提-预提科目' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for t_gl_withholding_acct_dimension
-- ----------------------------
DROP TABLE IF EXISTS `t_gl_withholding_acct_dimension`;
CREATE TABLE `t_gl_withholding_acct_dimension`  (
  `dimension_id` int NULL DEFAULT NULL COMMENT '维度ID',
  `ds_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '维度编码',
  `ds_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `amort_entry_id` int NULL DEFAULT NULL COMMENT '预提关联ID',
  `acct_id` int NULL DEFAULT NULL COMMENT '科目ID'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '凭证预提科目维度控制' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for t_gl_withholding_inacct
-- ----------------------------
DROP TABLE IF EXISTS `t_gl_withholding_inacct`;
CREATE TABLE `t_gl_withholding_inacct`  (
  `f_id` int NOT NULL AUTO_INCREMENT COMMENT '自增',
  `f_scheme_id` int NULL DEFAULT NULL COMMENT '方案内码',
  `f_enter_account_id` int NOT NULL COMMENT '转入科目ID',
  `f_enter_ratio` decimal(23, 10) NULL DEFAULT NULL COMMENT '比例',
  `f_enter_detail` int NULL DEFAULT NULL COMMENT '核算维度',
  `f_enter_ratio_fixed` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '固定',
  PRIMARY KEY (`f_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '凭证预提-转入科目' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for t_gl_withholding_inacct_dimension
-- ----------------------------
DROP TABLE IF EXISTS `t_gl_withholding_inacct_dimension`;
CREATE TABLE `t_gl_withholding_inacct_dimension`  (
  `dimension_id` int NULL DEFAULT NULL COMMENT '维度ID',
  `f_scheme_id` int NULL DEFAULT NULL,
  `ds_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '维度编码',
  `ds_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `amort_entry_id` int NULL DEFAULT NULL COMMENT '预提关联ID',
  `acct_id` int NULL DEFAULT NULL COMMENT '科目ID'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '凭证预提转入科目维度控制' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for t_gl_withholding_period
-- ----------------------------
DROP TABLE IF EXISTS `t_gl_withholding_period`;
CREATE TABLE `t_gl_withholding_period`  (
  `f_scheme_id` int NOT NULL COMMENT '方案内码',
  `f_year` int NULL DEFAULT NULL COMMENT '会计年度',
  `f_period` int NULL DEFAULT NULL COMMENT '会计期间',
  `f_year_period` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '会计年期间',
  `f_amort_amount` decimal(23, 10) NULL DEFAULT NULL COMMENT '预提金额',
  `f_amorted` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '已预提',
  `f_expression` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '公式定义'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '凭证预提-预提期间' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for t_gl_withholding_scheme
-- ----------------------------
DROP TABLE IF EXISTS `t_gl_withholding_scheme`;
CREATE TABLE `t_gl_withholding_scheme`  (
  `f_scheme_id` int NOT NULL AUTO_INCREMENT COMMENT '方案内码',
  `f_number` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '编码',
  `f_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `f_description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `f_isamort` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '是否预提方案',
  `f_account_book_id` int NOT NULL COMMENT '账簿',
  `f_voucher_group_id` int NOT NULL COMMENT '凭证字',
  `f_currency_id` int NOT NULL COMMENT '币别',
  `f_exchange_rate_type` int NOT NULL COMMENT '汇率类型',
  `f_explanation` varchar(510) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '摘要',
  `f_pedding_draw_amount` decimal(23, 10) NOT NULL COMMENT '待预提总额',
  `f_amortized_amount` decimal(23, 10) NOT NULL COMMENT '已预提总额',
  `f_end_balance` decimal(23, 10) NOT NULL COMMENT '余额',
  `f_last_execute_time` datetime NULL DEFAULT NULL COMMENT '执行时间',
  `f_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '状态',
  `f_forbid_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '禁用状态',
  `f_forbidder_id` int NULL DEFAULT NULL COMMENT '禁用人',
  `f_forbid_date` datetime NULL DEFAULT NULL COMMENT '禁用时间',
  PRIMARY KEY (`f_scheme_id`) USING BTREE,
  UNIQUE INDEX `f_number`(`f_number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '凭证预提' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for t_org_account_system
-- ----------------------------
DROP TABLE IF EXISTS `t_org_account_system`;
CREATE TABLE `t_org_account_system`  (
  `f_id` int NOT NULL AUTO_INCREMENT COMMENT '内码',
  `f_number` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编码',
  `f_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `f_isdefault_acct` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '默认核算体系 0否 1是',
  `f_acct_orgid` int NULL DEFAULT NULL COMMENT '核算组织ID',
  `f_default_acctpolicy_id` int NULL DEFAULT 0 COMMENT '默认会计政策',
  `f_create_orgid` int NULL DEFAULT NULL COMMENT '创建组织',
  `f_creatorid` int NULL DEFAULT NULL COMMENT '创建人',
  `f_create_date` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `f_use_orgid` int NULL DEFAULT NULL COMMENT '使用组织',
  `f_modifierid` int NULL DEFAULT NULL COMMENT '修改人',
  `f_modify_date` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改日期',
  `f_document_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '数据状态(-1删除,0创建,1暂存,2重新审核,3已审核)',
  `f_auditorid` int NULL DEFAULT NULL COMMENT '审核人',
  `f_audit_date` datetime NULL DEFAULT NULL COMMENT '审核日期',
  `f_forbid_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '禁用状态',
  `f_forbidderid` int NULL DEFAULT NULL COMMENT '禁用人',
  `f_forbid_date` datetime NULL DEFAULT NULL COMMENT '禁用日期',
  `f_issys_preset` int NULL DEFAULT 0 COMMENT '是否系统预设1 系统预设0 非系统预设默认0',
  `f_master_id` int NULL DEFAULT NULL COMMENT '组织隔离字段',
  `f_iscorpo_rate` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '法人核算体系',
  `f_description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`f_id`) USING BTREE,
  UNIQUE INDEX `f_number`(`f_number`) USING BTREE COMMENT '编码唯一索引'
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '会计核算体系' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for t_org_acct_sys_detail
-- ----------------------------
DROP TABLE IF EXISTS `t_org_acct_sys_detail`;
CREATE TABLE `t_org_acct_sys_detail`  (
  `f_id` int NOT NULL AUTO_INCREMENT COMMENT '内码',
  `f_acctsysentry_id` int NULL DEFAULT NULL COMMENT '会计核算体系之会计主体ID',
  `f_acct_orgid` int NULL DEFAULT NULL COMMENT '下级核算组织ID',
  `f_description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`f_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '会计核算体系之下级组织' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for t_org_acct_sys_entry
-- ----------------------------
DROP TABLE IF EXISTS `t_org_acct_sys_entry`;
CREATE TABLE `t_org_acct_sys_entry`  (
  `f_id` int NOT NULL AUTO_INCREMENT COMMENT '内码',
  `f_acctsystem_id` int NULL DEFAULT NULL COMMENT '会计核算体系ID',
  `f_acct_orgid` int NULL DEFAULT NULL COMMENT '核算组织ID',
  `f_default_acctpolicy_id` int NULL DEFAULT NULL COMMENT '默认会计政策',
  `f_acctpolicy_id` int NULL DEFAULT NULL COMMENT '适用会计政策',
  `f_description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`f_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '会计核算体系之会计主体' ROW_FORMAT = COMPACT;


SET FOREIGN_KEY_CHECKS = 1;
