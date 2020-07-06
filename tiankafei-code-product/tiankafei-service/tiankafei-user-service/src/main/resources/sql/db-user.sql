/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:3306
 Source Schema         : db-user

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 06/07/2020 20:58:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_department
-- ----------------------------
DROP TABLE IF EXISTS `sys_department`;
CREATE TABLE `sys_department`  (
  `id` int(0) NOT NULL COMMENT '主键id',
  `department_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '部门代码',
  `department_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '部门名称',
  `parent_id` int(0) NULL DEFAULT NULL COMMENT '父部门',
  `serial_number` int(0) NULL DEFAULT NULL COMMENT '顺序',
  `level` int(0) NULL DEFAULT NULL COMMENT '当前部门所处的层级(在第几级)',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '部门职责',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '修改时间',
  `create_user_id` bigint(0) NULL DEFAULT NULL COMMENT '部门创建人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_department_code`(`department_code`) USING BTREE COMMENT '按照部门代码查询部门信息'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统部门表信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_department
-- ----------------------------

-- ----------------------------
-- Table structure for sys_dict_1278356621092749313
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_1278356621092749313`;
CREATE TABLE `sys_dict_1278356621092749313`  (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键id',
  `code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '代码',
  `name` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '名称',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '描述',
  `remarks` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `parent_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '父id',
  `all_parent_id` varchar(2100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '所有父id，用逗号分隔',
  `serial_number` int(0) NULL DEFAULT NULL COMMENT '顺序',
  `level` int(0) NULL DEFAULT NULL COMMENT '所在层级',
  `unit1` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '计量单位1',
  `unit2` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '计量单位2',
  `unit3` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '计量单位3',
  `unit4` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '计量单位4',
  `unit5` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '计量单位5',
  `unit6` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '计量单位6',
  `create_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `create_user_id` bigint(0) NULL DEFAULT NULL COMMENT '创建人',
  UNIQUE INDEX `idx_code`(`code`) USING BTREE COMMENT '按照代码查询详细信息'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '性别字典数据表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_1278356621092749313
-- ----------------------------

-- ----------------------------
-- Table structure for sys_dict_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_info`;
CREATE TABLE `sys_dict_info`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `dict_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '字典代码',
  `dict_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '字典名称',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '状态：1启用，0停用',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '描述',
  `remarks` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `data_table` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '数据表',
  `create_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `create_user_id` bigint(0) NULL DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_dict_code`(`dict_code`) USING BTREE COMMENT '根据字典代码查询字典详细信息'
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统数据字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_info
-- ----------------------------
INSERT INTO `sys_dict_info` VALUES (18, 'GENDER', '性别', 1, '性别代码表', '', 'sys_dict_1278356621092749313', '2020-07-01 23:55:48', '2020-07-01 23:55:48', 0);

-- ----------------------------
-- Table structure for sys_dict_table
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_table`;
CREATE TABLE `sys_dict_table`  (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键id',
  `code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '代码',
  `name` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '名称',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '描述',
  `remarks` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `parent_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '父id',
  `all_parent_id` varchar(2100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '所有父id，用逗号分隔',
  `serial_number` int(0) NULL DEFAULT NULL COMMENT '顺序',
  `level` int(0) NULL DEFAULT NULL COMMENT '所在层级',
  `unit1` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '计量单位1',
  `unit2` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '计量单位2',
  `unit3` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '计量单位3',
  `unit4` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '计量单位4',
  `unit5` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '计量单位5',
  `unit6` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '计量单位6',
  `create_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `create_user_id` bigint(0) NULL DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_code`(`code`) USING BTREE COMMENT '按照代码查询详细信息'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统数据字典的数据表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_table
-- ----------------------------

-- ----------------------------
-- Table structure for sys_feature_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_feature_info`;
CREATE TABLE `sys_feature_info`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `feature_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '功能代码',
  `feature_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '功能名称',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '描述',
  `feature_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '1' COMMENT '节点类型：1目录，2菜单，3按钮',
  `open_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '打开方式：1页签，2新窗口',
  `url` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '功能的url地址',
  `keys` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限关键字',
  `parent_id` int(0) NULL DEFAULT NULL COMMENT '父id',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '状态：1启用，0停用',
  `serial_number` int(0) NULL DEFAULT NULL COMMENT '顺序',
  `icon` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图标的名称',
  `create_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `create_user_id` bigint(0) NULL DEFAULT NULL COMMENT '创建用户id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_feature_code`(`feature_code`) USING BTREE COMMENT '按照功能代码进行查询'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统功能菜单信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_feature_info
-- ----------------------------

-- ----------------------------
-- Table structure for sys_links
-- ----------------------------
DROP TABLE IF EXISTS `sys_links`;
CREATE TABLE `sys_links`  (
  `id` int(0) NOT NULL COMMENT '主键id',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '链接名称',
  `links` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '链接地址',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '状态：是否启用，1启用，2停用',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user_id` bigint(0) NULL DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统的友情链接' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_links
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_feature
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_feature`;
CREATE TABLE `sys_role_feature`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `role_id` int(0) NOT NULL COMMENT '角色id',
  `feature_id` int(0) NOT NULL COMMENT '功能id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_role_id`(`role_id`) USING BTREE COMMENT '根据角色id查询其配置的功能菜单'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统角色对应的功能配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_feature
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_info`;
CREATE TABLE `sys_role_info`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `role_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色代码',
  `role_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色名称',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '描述',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '角色状态:1启用，0停用',
  `create_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `create_user_id` bigint(0) NULL DEFAULT NULL COMMENT '创建用户id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_role_code`(`role_code`) USING BTREE COMMENT '按照索引代码进行查询'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_info
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_info`;
CREATE TABLE `sys_user_info`  (
  `id` bigint(0) NOT NULL COMMENT '主键',
  `username` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `nickname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '昵称，中文名',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `telephone` varchar(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号码',
  `password` char(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `status` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '状态',
  `expiration_date` timestamp(0) NULL DEFAULT NULL COMMENT '有效期截至时间',
  `department_id` int(0) NULL DEFAULT NULL COMMENT '部门id',
  `gender` tinyint(0) NULL DEFAULT NULL COMMENT '性别：1男，2女',
  `born_time` date NULL DEFAULT NULL COMMENT '出生日期',
  `create_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `create_user_id` bigint(0) NULL DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_username`(`username`) USING BTREE COMMENT '按用户名查询或登录',
  UNIQUE INDEX `idx_email`(`email`) USING BTREE COMMENT '按电子邮件查询或登录',
  UNIQUE INDEX `idx_phone`(`telephone`) USING BTREE COMMENT '按手机号码查询或登录'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户基本信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_info
-- ----------------------------
INSERT INTO `sys_user_info` VALUES (1278277824091844610, 'tiankafei', '甜咖啡', '798971170@qq.com', '18500195219', 'tiankafei', '1', NULL, 0, 1, NULL, NULL, NULL, 0);

-- ----------------------------
-- Table structure for sys_user_login
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_login`;
CREATE TABLE `sys_user_login`  (
  `id` bigint(0) NOT NULL COMMENT '主键',
  `username` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `telephone` varchar(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号码',
  `password` char(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `status` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '状态',
  `expiration_date` timestamp(0) NULL DEFAULT NULL COMMENT '有效期截至时间',
  `create_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_username`(`username`) USING BTREE COMMENT '按用户名查询或登录',
  UNIQUE INDEX `idx_email`(`email`) USING BTREE COMMENT '按电子邮件查询或登录',
  UNIQUE INDEX `idx_phone`(`telephone`) USING BTREE COMMENT '按手机号码查询或登录'
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户登录信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_login
-- ----------------------------
INSERT INTO `sys_user_login` VALUES (1278277824091844610, 'tiankafei', '798971170@qq.com', '18500195219', 'tiankafei', '1', NULL, NULL);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` bigint(0) NOT NULL COMMENT '用户id',
  `role_id` int(0) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_user_id`(`user_id`) USING BTREE COMMENT '根据用户id查询所拥有的所有角色',
  INDEX `idx_role_id`(`role_id`) USING BTREE COMMENT '根据角色id查询所有拥有该角色的用户'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户拥有的角色关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
