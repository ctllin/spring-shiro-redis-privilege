/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50516
Source Host           : localhost:3306
Source Database       : manager

Target Server Type    : MYSQL
Target Server Version : 50516
File Encoding         : 65001

Date: 2018-12-20 16:26:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_data_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `sys_data_dictionary`;
CREATE TABLE `sys_data_dictionary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `keyz` varchar(100) NOT NULL COMMENT '关键字',
  `valuez` varchar(150) DEFAULT NULL COMMENT '多个值之间用英文,隔开',
  `descz` varchar(500) DEFAULT NULL,
  `indexz` int(11) DEFAULT NULL,
  `flag` int(11) DEFAULT NULL,
  `savetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_sys_data_dictionary_keyz_unique` (`keyz`,`indexz`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_data_dictionary
-- ----------------------------

-- ----------------------------
-- Table structure for sys_err_mess
-- ----------------------------
DROP TABLE IF EXISTS `sys_err_mess`;
CREATE TABLE `sys_err_mess` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `project` smallint(4) DEFAULT NULL,
  `method` text,
  `errMess` text,
  `savetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_err_mess
-- ----------------------------

-- ----------------------------
-- Table structure for sys_execute_sh
-- ----------------------------
DROP TABLE IF EXISTS `sys_execute_sh`;
CREATE TABLE `sys_execute_sh` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `path` varchar(200) DEFAULT NULL,
  `detail` varchar(200) DEFAULT NULL,
  `flag` int(4) DEFAULT NULL,
  `firstruntime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  `savetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_execute_sh
-- ----------------------------

-- ----------------------------
-- Table structure for sys_operate_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_operate_log`;
CREATE TABLE `sys_operate_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `loginName` varchar(32) DEFAULT NULL,
  `loginIp` varchar(20) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `url` varchar(65) DEFAULT NULL,
  `savetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_operate_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_organization
-- ----------------------------
DROP TABLE IF EXISTS `sys_organization`;
CREATE TABLE `sys_organization` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `address` varchar(100) DEFAULT NULL,
  `code` varchar(64) NOT NULL,
  `icon` varchar(32) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `seq` int(1) NOT NULL DEFAULT '0',
  `isdefault` int(1) DEFAULT NULL,
  `createdatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='组织机构';

-- ----------------------------
-- Records of sys_organization
-- ----------------------------
INSERT INTO `sys_organization` VALUES ('1', '系统内置', '系统内置', '1', 'icon-company', null, '1', '0', '2017-05-18 11:27:45');
INSERT INTO `sys_organization` VALUES ('9', '中国', '中国', '001', 'icon-company', null, '0', '1', '2018-12-19 18:05:29');
INSERT INTO `sys_organization` VALUES ('10', '新乡', '', '001001', 'icon-company', '9', '0', '1', '2018-12-19 18:05:50');

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `url` varchar(100) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `icon` varchar(32) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `seq` int(4) NOT NULL DEFAULT '0',
  `state` int(4) NOT NULL DEFAULT '0',
  `resourcetype` int(4) NOT NULL DEFAULT '0',
  `isdefault` int(4) DEFAULT NULL COMMENT '//1非默认资源可以删除，0默认资源不可以删除',
  `hasChild` int(4) DEFAULT NULL COMMENT '默认0没有，1有',
  `createdatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=453 DEFAULT CHARSET=utf8 COMMENT='资源';

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
INSERT INTO `sys_resource` VALUES ('1', '系统管理', '', '系统管理', 'icon-company', null, '100', '0', '0', '0', '1', '2014-02-19 01:00:00');
INSERT INTO `sys_resource` VALUES ('11', '资源管理', '/resource/manager', '资源管理', 'icon-folder', '1', '1', '0', '0', '0', '1', '2014-02-19 01:00:00');
INSERT INTO `sys_resource` VALUES ('111', '列表', '/resource/treeGrid', '资源列表', 'icon-btn', '11', '0', '0', '1', '0', '1', '2014-02-19 01:00:00');
INSERT INTO `sys_resource` VALUES ('112', '添加', '/resource/add', '资源添加', 'icon-btn', '11', '0', '0', '1', '0', '1', '2014-02-19 01:00:00');
INSERT INTO `sys_resource` VALUES ('113', '编辑', '/resource/edit', '资源编辑', 'icon-btn', '11', '0', '0', '1', '0', '1', '2014-02-19 01:00:00');
INSERT INTO `sys_resource` VALUES ('114', '删除', '/resource/delete', '资源删除', 'icon-btn', '11', '0', '0', '1', '0', '1', '2014-02-19 01:00:00');
INSERT INTO `sys_resource` VALUES ('12', '角色管理', '/role/manager', '角色管理', 'icon-folder', '1', '2', '0', '0', '0', '1', '2014-02-19 01:00:00');
INSERT INTO `sys_resource` VALUES ('121', '列表', '/role/dataGrid', '角色列表', 'icon-btn', '12', '0', '0', '1', '0', '1', '2014-02-19 01:00:00');
INSERT INTO `sys_resource` VALUES ('122', '添加', '/role/add', '角色添加', 'icon-btn', '12', '0', '0', '1', '0', '1', '2014-02-19 01:00:00');
INSERT INTO `sys_resource` VALUES ('123', '编辑', '/role/edit', '角色编辑', 'icon-btn', '12', '0', '0', '1', '0', '1', '2014-02-19 01:00:00');
INSERT INTO `sys_resource` VALUES ('124', '删除', '/role/delete', '角色删除', 'icon-btn', '12', '0', '0', '1', '0', '1', '2014-02-19 01:00:00');
INSERT INTO `sys_resource` VALUES ('125', '授权', '/role/grant', '角色授权', 'icon-btn', '12', '0', '0', '1', '0', '1', '2014-02-19 01:00:00');
INSERT INTO `sys_resource` VALUES ('13', '用户管理', '/user/manager', '用户管理', 'icon-folder', '1', '3', '0', '0', '0', '1', '2014-02-19 01:00:00');
INSERT INTO `sys_resource` VALUES ('131', '列表', '/user/dataGrid', '用户列表', 'icon-btn', '13', '0', '0', '1', '0', '1', '2014-02-19 01:00:00');
INSERT INTO `sys_resource` VALUES ('132', '添加', '/user/add', '用户添加', 'icon-btn', '13', '0', '0', '1', '0', '1', '2014-02-19 01:00:00');
INSERT INTO `sys_resource` VALUES ('133', '编辑', '/user/edit', '用户编辑', 'icon-btn', '13', '0', '0', '1', '0', '1', '2014-02-19 01:00:00');
INSERT INTO `sys_resource` VALUES ('134', '删除', '/user/delete', '用户删除', 'icon-btn', '13', '0', '0', '1', '0', '1', '2014-02-19 01:00:00');
INSERT INTO `sys_resource` VALUES ('135', '查看', '/user/view', '用户查看', 'icon-btn', '13', '0', '0', '1', '0', '1', '2014-02-19 01:00:00');
INSERT INTO `sys_resource` VALUES ('14', '部门管理', '/organization/manager', '部门管理', 'icon-folder', '1', '4', '0', '0', '0', '1', '2014-02-19 01:00:00');
INSERT INTO `sys_resource` VALUES ('141', '列表', '/organization/treeGrid', '用户列表', 'icon-btn', '14', '0', '0', '1', '0', '1', '2014-02-19 01:00:00');
INSERT INTO `sys_resource` VALUES ('142', '添加', '/organization/add', '部门添加', 'icon-btn', '14', '0', '0', '1', '0', '1', '2014-02-19 01:00:00');
INSERT INTO `sys_resource` VALUES ('143', '编辑', '/organization/edit', '部门编辑', 'icon-btn', '14', '0', '0', '1', '0', '1', '2014-02-19 01:00:00');
INSERT INTO `sys_resource` VALUES ('144', '删除', '/organization/delete', '部门删除', 'icon-btn', '14', '0', '0', '1', '0', '1', '2014-02-19 01:00:00');
INSERT INTO `sys_resource` VALUES ('145', '查看', '/organization/view', '部门查看', 'icon-btn', '14', '0', '0', '1', '0', '1', '2014-02-19 01:00:00');
INSERT INTO `sys_resource` VALUES ('2', '代码演示', '', '代码演示', 'icon-company', null, '99', '0', '0', '0', '1', '2014-02-19 01:00:00');
INSERT INTO `sys_resource` VALUES ('21', 'DEMO管理', '/demo/manager', 'DEMO管理', 'icon-folder', '2', '1', '0', '0', '0', '1', '2014-02-19 01:00:00');
INSERT INTO `sys_resource` VALUES ('211', '列表', '/demo/dataGrid', '列表', 'icon-btn', '21', '0', '0', '1', '0', '1', '2014-02-19 01:00:00');
INSERT INTO `sys_resource` VALUES ('212', '添加', '/demo/add', '添加', 'icon-btn', '21', '0', '0', '1', '0', '1', '2014-02-19 01:00:00');
INSERT INTO `sys_resource` VALUES ('213', '编辑', '/demo/edit', '编辑', 'icon-btn', '21', '0', '0', '1', '0', '1', '2014-02-19 01:00:00');
INSERT INTO `sys_resource` VALUES ('214', '删除', '/demo/delete', '删除', 'icon-btn', '21', '0', '0', '1', '0', '1', '2014-02-19 01:00:00');
INSERT INTO `sys_resource` VALUES ('215', '查看', '/demo/view', '查看', 'icon-btn', '21', '0', '0', '1', '0', '1', '2014-02-19 01:00:00');
INSERT INTO `sys_resource` VALUES ('22', 'EASYUI', 'http://www.jeasyui.com', 'EASYUI', 'icon-folder', '2', '1', '0', '0', '0', '1', '2014-02-19 01:00:00');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `seq` int(1) NOT NULL DEFAULT '0',
  `description` varchar(64) DEFAULT NULL,
  `isdefault` int(1) NOT NULL DEFAULT '0',
  `updatetime` datetime DEFAULT NULL,
  `savetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '超级管理员', '0', '超级管理员，拥有全部权限', '0', null, null);
INSERT INTO `sys_role` VALUES ('15', '部门管理员', '2', '部门管理员', '1', null, null);
INSERT INTO `sys_role` VALUES ('16', '用户管理员', '3', '用户管理员', '1', null, null);
INSERT INTO `sys_role` VALUES ('17', '资源管理', '3', '资源管理', '1', null, null);

-- ----------------------------
-- Table structure for sys_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_resource`;
CREATE TABLE `sys_role_resource` (
  `role_id` int(5) NOT NULL,
  `resource_id` int(5) NOT NULL,
  PRIMARY KEY (`role_id`,`resource_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='角色资源';

-- ----------------------------
-- Records of sys_role_resource
-- ----------------------------
INSERT INTO `sys_role_resource` VALUES ('1', '1');
INSERT INTO `sys_role_resource` VALUES ('1', '2');
INSERT INTO `sys_role_resource` VALUES ('1', '11');
INSERT INTO `sys_role_resource` VALUES ('1', '12');
INSERT INTO `sys_role_resource` VALUES ('1', '13');
INSERT INTO `sys_role_resource` VALUES ('1', '14');
INSERT INTO `sys_role_resource` VALUES ('1', '21');
INSERT INTO `sys_role_resource` VALUES ('1', '22');
INSERT INTO `sys_role_resource` VALUES ('1', '111');
INSERT INTO `sys_role_resource` VALUES ('1', '112');
INSERT INTO `sys_role_resource` VALUES ('1', '113');
INSERT INTO `sys_role_resource` VALUES ('1', '114');
INSERT INTO `sys_role_resource` VALUES ('1', '121');
INSERT INTO `sys_role_resource` VALUES ('1', '122');
INSERT INTO `sys_role_resource` VALUES ('1', '123');
INSERT INTO `sys_role_resource` VALUES ('1', '124');
INSERT INTO `sys_role_resource` VALUES ('1', '125');
INSERT INTO `sys_role_resource` VALUES ('1', '131');
INSERT INTO `sys_role_resource` VALUES ('1', '132');
INSERT INTO `sys_role_resource` VALUES ('1', '133');
INSERT INTO `sys_role_resource` VALUES ('1', '134');
INSERT INTO `sys_role_resource` VALUES ('1', '135');
INSERT INTO `sys_role_resource` VALUES ('1', '141');
INSERT INTO `sys_role_resource` VALUES ('1', '142');
INSERT INTO `sys_role_resource` VALUES ('1', '143');
INSERT INTO `sys_role_resource` VALUES ('1', '144');
INSERT INTO `sys_role_resource` VALUES ('1', '145');
INSERT INTO `sys_role_resource` VALUES ('1', '211');
INSERT INTO `sys_role_resource` VALUES ('1', '212');
INSERT INTO `sys_role_resource` VALUES ('1', '213');
INSERT INTO `sys_role_resource` VALUES ('1', '214');
INSERT INTO `sys_role_resource` VALUES ('1', '215');
INSERT INTO `sys_role_resource` VALUES ('15', '14');
INSERT INTO `sys_role_resource` VALUES ('15', '141');
INSERT INTO `sys_role_resource` VALUES ('15', '142');
INSERT INTO `sys_role_resource` VALUES ('15', '143');
INSERT INTO `sys_role_resource` VALUES ('15', '144');
INSERT INTO `sys_role_resource` VALUES ('15', '145');
INSERT INTO `sys_role_resource` VALUES ('16', '13');
INSERT INTO `sys_role_resource` VALUES ('16', '131');
INSERT INTO `sys_role_resource` VALUES ('16', '132');
INSERT INTO `sys_role_resource` VALUES ('16', '133');
INSERT INTO `sys_role_resource` VALUES ('16', '134');
INSERT INTO `sys_role_resource` VALUES ('16', '135');
INSERT INTO `sys_role_resource` VALUES ('17', '11');
INSERT INTO `sys_role_resource` VALUES ('17', '111');
INSERT INTO `sys_role_resource` VALUES ('17', '112');
INSERT INTO `sys_role_resource` VALUES ('17', '113');
INSERT INTO `sys_role_resource` VALUES ('17', '114');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `loginname` varchar(64) NOT NULL,
  `name` varchar(64) NOT NULL,
  `password` varchar(64) NOT NULL,
  `password_salt` varchar(64) DEFAULT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `sex` int(1) DEFAULT '0',
  `age` int(1) DEFAULT '0',
  `usertype` int(1) DEFAULT '0',
  `isdefault` int(1) DEFAULT '0',
  `state` int(1) DEFAULT '0',
  `organization_id` int(11) DEFAULT '0',
  `createdatetime` datetime DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `organizationName` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `loginname` (`loginname`)
) ENGINE=MyISAM AUTO_INCREMENT=37 DEFAULT CHARSET=utf8 COMMENT='用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '超级管理员', 'b7e2e951d6e1003c108d5726db81b66f', 'ctllin', null, '0', '18', '0', '0', '0', '1', '2012-06-04 01:00:00', null, null);
INSERT INTO `sys_user` VALUES ('36', 'ctl', 'ctl', 'b7e2e951d6e1003c108d5726db81b66f', 'ctllin', null, '0', null, '1', '1', '0', '1', '2018-12-19 18:01:11', '', 'eb755ccf2e8fa968a9db849abef02038');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` int(5) NOT NULL,
  `role_id` int(5) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='用户角色';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1');
INSERT INTO `sys_user_role` VALUES ('36', '15');
INSERT INTO `sys_user_role` VALUES ('36', '16');
INSERT INTO `sys_user_role` VALUES ('36', '17');

-- ----------------------------
-- View structure for view_sys_user
-- ----------------------------
DROP VIEW IF EXISTS `view_sys_user`;
CREATE VIEW `view_sys_user` AS select `su`.`id` AS `id`,`su`.`loginname` AS `loginname`,`su`.`name` AS `name`,`su`.`password` AS `password`,`su`.`password_salt` AS `password_salt`,`su`.`customer_id` AS `customer_id`,`su`.`sex` AS `sex`,`su`.`age` AS `age`,`su`.`usertype` AS `usertype`,`su`.`isdefault` AS `isdefault`,`su`.`state` AS `state`,`su`.`organization_id` AS `organization_id`,`su`.`createdatetime` AS `createdatetime`,`su`.`phone` AS `phone`,`so`.`name` AS `organizationName` from (`sys_user` `su` left join `sys_organization` `so` on((`su`.`organization_id` = `so`.`id`))) ;
