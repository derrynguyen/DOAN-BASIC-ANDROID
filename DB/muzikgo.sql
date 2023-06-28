/*
 Navicat Premium Data Transfer

 Source Server         : ef
 Source Server Type    : MySQL
 Source Server Version : 50515
 Source Host           : localhost:3306
 Source Schema         : muzikgo

 Target Server Type    : MySQL
 Target Server Version : 50515
 File Encoding         : 65001

 Date: 19/05/2023 10:18:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phone` varchar(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `password` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `fullname` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `addreas` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES (19, '0903869729', 'efe6398127928f1b2e9ef3207fb82663', 'Thanh Hai', '78 Lê Lai, Phường Bến Thành, Quận 1, Tp. Hồ Chí Minh');
INSERT INTO `account` VALUES (20, '0902627755', 'efe6398127928f1b2e9ef3207fb82663', 'Tran Thi Trang Anh', '330 Trần Hưng Đạo, Phường Nguyễn Cư Trinh, Quận 1, Tp. Hồ Chí Minh');
INSERT INTO `account` VALUES (21, '0338047755', 'efe6398127928f1b2e9ef3207fb82663', 'Do Tan Dat', '35 Trịnh Văn Cấn, Phường Cầu Ông Lãnh, Quận 1, Tp. Hồ Chí Minh');
INSERT INTO `account` VALUES (22, '0338047766', 'efe6398127928f1b2e9ef3207fb82663', 'Let Hoai Dinh', '47/6C Khu pho 2 Phuong Dakao Quan 13');

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart`  (
  `id_cart` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) NULL DEFAULT NULL,
  `name_product` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price_product` int(11) NULL DEFAULT NULL,
  `imgur` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `amount` int(5) NULL DEFAULT NULL,
  PRIMARY KEY (`id_cart`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 107 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of cart
-- ----------------------------
INSERT INTO `cart` VALUES (89, 19, 'Beef Noodle Soup', 15000, 'https://i.imgur.com/sqt85iK_d.webp?maxwidth=760&fidelity=grand', 2);
INSERT INTO `cart` VALUES (91, 19, 'Buger', 25000, 'https://i.imgur.com/4aztSrg.jpeg', 1);
INSERT INTO `cart` VALUES (92, 19, 'Pizza', 15000, 'https://i.imgur.com/VUEGlFp.jpeg', 1);

-- ----------------------------
-- Table structure for order_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail`  (
  `id_payment` int(11) NOT NULL AUTO_INCREMENT,
  `id_order` int(11) NULL DEFAULT NULL,
  `id_user` int(11) NULL DEFAULT NULL,
  `name_product` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `price_product` int(11) NULL DEFAULT NULL,
  `imgur` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `amount` int(11) NULL DEFAULT NULL,
  `total` int(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id_payment`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 103 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of order_detail
-- ----------------------------
INSERT INTO `order_detail` VALUES (97, 990, 21, 'Beef Noodle Soup', 15000, 'https://i.imgur.com/sqt85iK_d.webp?maxwidth=760&fidelity=grand', 1, 15000);
INSERT INTO `order_detail` VALUES (98, 990, 21, 'Spaghetti', 25000, 'https://i.imgur.com/frUrwc8.jpeg', 1, 25000);
INSERT INTO `order_detail` VALUES (99, 990, 21, 'Buger', 25000, 'https://i.imgur.com/4aztSrg.jpeg', 1, 25000);
INSERT INTO `order_detail` VALUES (100, 990, 21, 'Com Chien', 25000, 'https://i.imgur.com/pkyd6tT.jpeg', 1, 25000);
INSERT INTO `order_detail` VALUES (101, 221, 22, 'Pepsi', 10000, 'https://i.imgur.com/ARbGOjd.jpeg', 13, 130000);
INSERT INTO `order_detail` VALUES (102, 221, 22, 'Com Chien', 25000, 'https://i.imgur.com/pkyd6tT.jpeg', 1, 25000);

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id_order` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) NULL DEFAULT NULL,
  `id_detail_order` int(11) NULL DEFAULT NULL,
  `create_at` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id_order`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (1, 21, 990, '2023-05-19 00:00:00');
INSERT INTO `orders` VALUES (2, 22, 221, '2023-05-19 00:00:00');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name_product` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price_product` int(18) NULL DEFAULT NULL,
  `imgur` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (1, 'Pizza', 15000, 'https://i.imgur.com/VUEGlFp.jpeg');
INSERT INTO `product` VALUES (2, 'Buger', 25000, 'https://i.imgur.com/4aztSrg.jpeg');
INSERT INTO `product` VALUES (3, 'Spaghetti', 25000, 'https://i.imgur.com/frUrwc8.jpeg');
INSERT INTO `product` VALUES (4, 'Beef Noodle Soup', 15000, 'https://i.imgur.com/sqt85iK_d.webp?maxwidth=760&fidelity=grand');
INSERT INTO `product` VALUES (5, 'Com Chien', 25000, 'https://i.imgur.com/pkyd6tT.jpeg');
INSERT INTO `product` VALUES (6, 'Thit Kho', 21000, 'https://i.imgur.com/lpUsB8i_d.webp?maxwidth=760&fidelity=grand');
INSERT INTO `product` VALUES (7, 'Pepsi', 10000, 'https://i.imgur.com/ARbGOjd.jpeg');

SET FOREIGN_KEY_CHECKS = 1;
