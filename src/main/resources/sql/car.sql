/*
 Navicat Premium Data Transfer

 Source Server         : Todd-Ding
 Source Server Type    : MySQL
 Source Server Version : 50716
 Source Host           : 127.0.0.1:3306
 Source Schema         : car

 Target Server Type    : MySQL
 Target Server Version : 50716
 File Encoding         : 65001

 Date: 05/12/2020 22:25:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bus_car
-- ----------------------------
DROP TABLE IF EXISTS `bus_car`;
CREATE TABLE `bus_car`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '汽车ID',
  `num` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '车牌号',
  `type` int(1) NOT NULL COMMENT '类型  1 轿车  2 SUV  3 跑车',
  `color` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '颜色',
  `price` int(10) NOT NULL COMMENT '价格',
  `rent_price` int(10) NOT NULL COMMENT '出租金额',
  `deposit` int(10) NOT NULL COMMENT '押金',
  `is_rent` int(1) NOT NULL DEFAULT 1 COMMENT '出租状态 1 未出租  2 已出租',
  `descp` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `img` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '汽车图片',
  `version` int(11) NOT NULL DEFAULT 1 COMMENT '版本号',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '车辆表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bus_car
-- ----------------------------
INSERT INTO `bus_car` VALUES (1, '鄂A666666', 3, '蓝色', 600000, 5999, 200000, 1, '玛莎拉蒂', 'upload/bg_car.jpg', 3, '2020-12-02 20:45:39');
INSERT INTO `bus_car` VALUES (2, '鄂B111111', 1, '白色', 200000, 2000, 100000, 1, 'AE86', 'upload/AE86.jpg', 3, '2020-12-02 20:54:21');
INSERT INTO `bus_car` VALUES (3, '京A111111', 3, '黑色', 1000000, 10000, 350000, 1, '兰博基尼', 'upload/lanbojini.jpg', 1, '2020-12-02 21:01:03');
INSERT INTO `bus_car` VALUES (4, '沪A111111', 3, '黑色', 1100000, 11000, 400000, 1, '超跑', 'upload/chaopao.jpg', 1, '2020-12-02 21:03:01');
INSERT INTO `bus_car` VALUES (5, '广A111111', 3, '蓝色', 1600000, 30000, 1000000, 1, '布加迪威龙', 'upload/bujiadiweilong.jpg', 1, '2020-12-03 10:18:22');
INSERT INTO `bus_car` VALUES (6, '京C666666', 3, '蓝灰色', 2000000, 40000, 1500000, 1, '阿斯顿马丁', 'upload/asidunmading.jpg', 1, '2020-12-03 10:19:33');

-- ----------------------------
-- Table structure for bus_customer
-- ----------------------------
DROP TABLE IF EXISTS `bus_customer`;
CREATE TABLE `bus_customer`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '客户ID',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '客户名称',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '客户电话',
  `address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '客户地址',
  `id_card` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '身份证号',
  `sex` int(1) NOT NULL COMMENT '性别 1 男 2女',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '客户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bus_customer
-- ----------------------------
INSERT INTO `bus_customer` VALUES (1, '李云龙', '18122222222', '平安格勒', '123333445555553333', 1, '2020-12-01 20:46:33', '2020-12-01 21:37:20');
INSERT INTO `bus_customer` VALUES (2, '楚云飞', '18111111111', '晋西北', '321111111112211123', 1, '2020-12-01 21:36:30', '2020-12-01 21:36:59');
INSERT INTO `bus_customer` VALUES (3, '和尚', '18122222233', '平安格勒', '123333445555552992', 1, '2020-12-01 21:50:12', '2020-12-01 21:50:12');
INSERT INTO `bus_customer` VALUES (4, '二营长', '18111111144', '晋西北', '321111111112211008', 1, '2020-12-01 21:50:12', '2020-12-01 21:50:12');
INSERT INTO `bus_customer` VALUES (5, '意大利炮', '18122222245', '平安格勒1', '123333445555552992', 1, '2020-12-02 09:07:09', '2020-12-02 09:07:09');
INSERT INTO `bus_customer` VALUES (6, '开炮', '18111111156', '晋西北1', '321111111112211008', 1, '2020-12-02 09:07:09', '2020-12-02 09:07:09');
INSERT INTO `bus_customer` VALUES (7, '阿杰', '13487036666', '秋名山', '234123456789045674', 1, '2020-12-03 14:59:24', '2020-12-03 14:59:24');

-- ----------------------------
-- Table structure for bus_rent
-- ----------------------------
DROP TABLE IF EXISTS `bus_rent`;
CREATE TABLE `bus_rent`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '出租记录ID',
  `num` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '车牌号',
  `type` int(1) NOT NULL COMMENT '车型  1 轿车 2 SUV  3跑车',
  `rent_price` int(10) NOT NULL COMMENT '租金',
  `deposit` int(10) NOT NULL COMMENT '押金',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '客户名称',
  `id_card` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '客户身份证号',
  `begin_time` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '计划租车开始时间',
  `end_time` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '计划租车的结束时间',
  `flag` int(1) NOT NULL DEFAULT 1 COMMENT '状态  1 未还车  2 已还车',
  `user_id` int(11) NOT NULL COMMENT '业务员ID',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '出租记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bus_rent
-- ----------------------------
INSERT INTO `bus_rent` VALUES (1, '鄂A666666', 3, 5999, 200000, '李云龙', '123333445555553333', '2020-12-03', '2020-12-10', 2, 1, '2020-12-03 14:55:04', '2020-12-03 17:05:30');
INSERT INTO `bus_rent` VALUES (2, '鄂B111111', 1, 2000, 100000, '阿杰', '234123456789045674', '2020-12-01', '2020-12-19', 2, 1, '2020-12-03 15:00:44', '2020-12-03 17:06:59');

-- ----------------------------
-- Table structure for bus_return
-- ----------------------------
DROP TABLE IF EXISTS `bus_return`;
CREATE TABLE `bus_return`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '还车记录ID',
  `num` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '车牌号',
  `rent_id` int(11) NOT NULL COMMENT '出租记录ID',
  `return_time` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '还车时间',
  `rent_price` int(10) NOT NULL COMMENT '租金',
  `pay_money` int(10) NOT NULL DEFAULT 0 COMMENT '赔付金额',
  `problem` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '问题',
  `total_money` int(10) NOT NULL COMMENT '总金额',
  `user_id` int(11) NOT NULL COMMENT '业务员ID',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '还车记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bus_return
-- ----------------------------
INSERT INTO `bus_return` VALUES (1, '鄂A666666', 1, '2020-12-03', 5999, 10000, '擦痕', 15999, 1, '2020-12-03 17:05:30');
INSERT INTO `bus_return` VALUES (2, '鄂B111111', 2, '2020-12-03', 6000, 0, '排水渠过弯', 6000, 1, '2020-12-03 17:06:59');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限ID',
  `title` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限名称',
  `icon` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限图标',
  `href` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限连接 菜单请求的URL地址',
  `spread` bit(1) NULL DEFAULT b'0' COMMENT '是否展开 1.展开 0.不展开',
  `type` int(1) NOT NULL COMMENT '权限类型 1.菜单 2.按钮',
  `tag` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限自定义标识',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序 越大越靠前',
  `parent_id` int(11) NOT NULL DEFAULT 0 COMMENT '父权限ID 默认0 表示一级菜单',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES (1, '租赁管理', '&#xe63c;', '#', b'1', 1, 'bus:admin', 999, 0);
INSERT INTO `sys_permission` VALUES (2, '客户管理', '&#xe613;', 'customer/list.do', b'0', 1, 'customer:list', 99, 1);
INSERT INTO `sys_permission` VALUES (3, '车辆管理', '&#xe657;', 'car/list.do', b'0', 1, 'car:list', 98, 1);
INSERT INTO `sys_permission` VALUES (4, '出租记录', '&#xe60a;', 'rent/list.do', b'0', 1, 'rent:list', 97, 1);
INSERT INTO `sys_permission` VALUES (5, '还车记录', '&#xe62a;', 'return/list.do', b'0', 1, 'return:list', 96, 1);
INSERT INTO `sys_permission` VALUES (6, '系统管理', '&#xe716;', '#', b'1', 1, 'sys:admin', 998, 0);
INSERT INTO `sys_permission` VALUES (7, '用户管理', '&#xe770;', 'user/list.do', b'0', 1, 'user:list', 95, 6);
INSERT INTO `sys_permission` VALUES (8, '角色管理', '&#xe612;', 'role/list.do', b'0', 1, 'role:list', 94, 6);
INSERT INTO `sys_permission` VALUES (9, '权限管理', '&#xe672;', 'perm/list.do', b'0', 1, 'permission:list', 93, 6);
INSERT INTO `sys_permission` VALUES (10, '新增', '&#xe623;', '#', b'0', 2, 'customer:add', 0, 2);
INSERT INTO `sys_permission` VALUES (11, '导出', '&#xe623;', '#', b'0', 2, 'customer:export', 0, 2);
INSERT INTO `sys_permission` VALUES (12, '导入', '&#xe623;', '#', b'0', 2, 'customer:import', 0, 2);
INSERT INTO `sys_permission` VALUES (13, '修改', '&#xe623;', '#', b'0', 2, 'customer:update', 0, 2);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `tag` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色标识',
  `descp` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '管理员', 'admin', '管理员');
INSERT INTO `sys_role` VALUES (2, '测试人员', 'test', '测试人员');
INSERT INTO `sys_role` VALUES (3, '业务员', 'salesman', '业务员');

-- ----------------------------
-- Table structure for sys_role_per_rel
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_per_rel`;
CREATE TABLE `sys_role_per_rel`  (
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `per_id` int(11) NOT NULL COMMENT '权限ID'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色权限关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_per_rel
-- ----------------------------
INSERT INTO `sys_role_per_rel` VALUES (2, 1);
INSERT INTO `sys_role_per_rel` VALUES (2, 2);
INSERT INTO `sys_role_per_rel` VALUES (2, 3);
INSERT INTO `sys_role_per_rel` VALUES (2, 4);
INSERT INTO `sys_role_per_rel` VALUES (2, 5);
INSERT INTO `sys_role_per_rel` VALUES (2, 6);
INSERT INTO `sys_role_per_rel` VALUES (2, 7);
INSERT INTO `sys_role_per_rel` VALUES (2, 8);
INSERT INTO `sys_role_per_rel` VALUES (2, 9);
INSERT INTO `sys_role_per_rel` VALUES (3, 6);
INSERT INTO `sys_role_per_rel` VALUES (3, 7);
INSERT INTO `sys_role_per_rel` VALUES (3, 8);
INSERT INTO `sys_role_per_rel` VALUES (3, 9);
INSERT INTO `sys_role_per_rel` VALUES (1, 1);
INSERT INTO `sys_role_per_rel` VALUES (1, 2);
INSERT INTO `sys_role_per_rel` VALUES (1, 10);
INSERT INTO `sys_role_per_rel` VALUES (1, 11);
INSERT INTO `sys_role_per_rel` VALUES (1, 12);
INSERT INTO `sys_role_per_rel` VALUES (1, 13);
INSERT INTO `sys_role_per_rel` VALUES (1, 3);
INSERT INTO `sys_role_per_rel` VALUES (1, 4);
INSERT INTO `sys_role_per_rel` VALUES (1, 5);
INSERT INTO `sys_role_per_rel` VALUES (1, 6);
INSERT INTO `sys_role_per_rel` VALUES (1, 7);
INSERT INTO `sys_role_per_rel` VALUES (1, 8);
INSERT INTO `sys_role_per_rel` VALUES (1, 9);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `login_name` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '登录名',
  `login_password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '登录密码',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '手机号',
  `realname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '真实姓名',
  `id_card` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '身份证号',
  `sex` int(1) NOT NULL COMMENT '性别  1 男  2 女',
  `address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '地址',
  `img` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图像',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '46eb73c57df9aa4243b445f0bffa6d38', '13888888888', '华仔', '411111111111111111', 1, '香港', 'resources/images/face.jpg', '2020-11-30 15:20:55');
INSERT INTO `sys_user` VALUES (2, 'xiaowu', '46eb73c57df9aa4243b445f0bffa6d38', '18111111111', '吴彦祖', '421181111111111111', 1, '武汉', 'resources/images/face.jpg', '2020-12-01 19:39:49');
INSERT INTO `sys_user` VALUES (3, 'xiaojin', 'cafe5b9a578b701a1110cb22f733d17d', '18111111112', '金志贤', '411111111111111112', 2, '棒子国', 'resources/images/face.jpg', '2020-12-01 19:41:41');
INSERT INTO `sys_user` VALUES (4, 'pengyuyan', 'cafe5b9a578b701a1110cb22f733d17d', '18120316666', '彭于晏', '666666666666666666', 1, '武汉', 'resources/images/pengyuyan.jpg', '2020-12-04 19:53:59');

-- ----------------------------
-- Table structure for sys_user_role_rel
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role_rel`;
CREATE TABLE `sys_user_role_rel`  (
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role_rel
-- ----------------------------
INSERT INTO `sys_user_role_rel` VALUES (1, 1);
INSERT INTO `sys_user_role_rel` VALUES (1, 2);
INSERT INTO `sys_user_role_rel` VALUES (1, 3);
INSERT INTO `sys_user_role_rel` VALUES (2, 2);
INSERT INTO `sys_user_role_rel` VALUES (3, 3);
INSERT INTO `sys_user_role_rel` VALUES (4, 1);
INSERT INTO `sys_user_role_rel` VALUES (4, 2);

SET FOREIGN_KEY_CHECKS = 1;
