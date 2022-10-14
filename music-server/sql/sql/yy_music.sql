/*
 Navicat Premium Data Transfer

 Source Server         : ethereal
 Source Server Type    : MySQL
 Source Server Version : 80030
 Source Host           : localhost:3306
 Source Schema         : yy_music

 Target Server Type    : MySQL
 Target Server Version : 80030
 File Encoding         : 65001

 Date: 13/10/2022 17:56:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `password` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name_UNIQUE`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', '123');
INSERT INTO `admin` VALUES (2, 'admin1', '565');

-- ----------------------------
-- Table structure for banner
-- ----------------------------
DROP TABLE IF EXISTS `banner`;
CREATE TABLE `banner`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `pic` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of banner
-- ----------------------------
INSERT INTO `banner` VALUES (1, '/img/bannerPic/1.jpg');
INSERT INTO `banner` VALUES (2, 'img/bannerPic/2.jpg');
INSERT INTO `banner` VALUES (3, '/img/bannerPic/3.jpg');
INSERT INTO `banner` VALUES (4, '/img/bannerPic/4.jpg');
INSERT INTO `banner` VALUES (5, '/img/bannerPic/5.jpg');
INSERT INTO `banner` VALUES (6, '/img/bannerPic/6.jpg');
INSERT INTO `banner` VALUES (7, '/img/bannerPic/7.jpg');
INSERT INTO `banner` VALUES (8, '/img/bannerPic/8.jpg');
INSERT INTO `banner` VALUES (9, '/img/bannerPic/9.jpg');
INSERT INTO `banner` VALUES (10, '/img/bannerPic/10.jpg');

-- ----------------------------
-- Table structure for collect
-- ----------------------------
DROP TABLE IF EXISTS `collect`;
CREATE TABLE `collect`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` int(0) UNSIGNED NOT NULL,
  `type` tinyint(0) NOT NULL,
  `song_id` int(0) UNSIGNED NULL DEFAULT NULL,
  `song_list_id` int(0) UNSIGNED NULL DEFAULT NULL,
  `create_time` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 59 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of collect
-- ----------------------------
INSERT INTO `collect` VALUES (58, 37, 0, 114, NULL, '2022-09-22 11:58:03');
INSERT INTO `collect` VALUES (59, 37, 0, 115, NULL, '2022-09-22 11:58:38');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` int(0) UNSIGNED NOT NULL,
  `song_id` int(0) UNSIGNED NULL DEFAULT NULL,
  `song_list_id` int(0) UNSIGNED NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `type` tinyint(0) NOT NULL,
  `up` int(0) UNSIGNED NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 64 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (64, 37, 114, NULL, '但偏偏雨渐渐大到我看你不见', '2022-09-21 16:56:52', 0, 0);

-- ----------------------------
-- Table structure for consumer
-- ----------------------------
DROP TABLE IF EXISTS `consumer`;
CREATE TABLE `consumer`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `password` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `sex` tinyint(0) NULL DEFAULT NULL,
  `phone_num` char(15) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `email` char(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `birth` datetime(0) NULL DEFAULT NULL,
  `introduction` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `location` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NOT NULL,
  `update_time` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username_UNIQUE`(`username`) USING BTREE,
  UNIQUE INDEX `email_UNIQUE`(`email`) USING BTREE,
  UNIQUE INDEX `phone_num_UNIQUE`(`phone_num`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 45 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of consumer
-- ----------------------------
INSERT INTO `consumer` VALUES (37, 'xihao', '3056dd67e7d880f5bfd4022267799a48', 1, '111111', 'xh.ethereal@gmail.com', '2022-09-14 17:00:00', '111', '重庆', '/img/avatarImages/16637397446011.jpg', '2022-09-14 17:11:27', '2022-09-21 13:55:45');
INSERT INTO `consumer` VALUES (45, 'yiyun', '3056dd67e7d880f5bfd4022267799a48', 0, '111111111', 'xh.ethe@gmail.com', '2022-09-23 16:18:58', 'dsfsd', '北京', '/img/avatarImages/1663921231189李荣浩.jpg', '2022-09-23 16:19:24', '2022-09-23 16:20:31');

-- ----------------------------
-- Table structure for feedback
-- ----------------------------
DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `message` varchar(1024) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for list_song
-- ----------------------------
DROP TABLE IF EXISTS `list_song`;
CREATE TABLE `list_song`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `song_id` int(0) UNSIGNED NOT NULL,
  `song_list_id` int(0) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 212 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for message_board
-- ----------------------------
DROP TABLE IF EXISTS `message_board`;
CREATE TABLE `message_board`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `message` varchar(1024) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `up` int(0) UNSIGNED NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `replied` tinyint(3) UNSIGNED ZEROFILL NULL DEFAULT NULL,
  `reply_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `reply_content` varchar(1024) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `enabled` tinyint(3) UNSIGNED ZEROFILL NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of message_board
-- ----------------------------
INSERT INTO `message_board` VALUES (1, '37', '作者真帅', NULL, '2022-10-11 15:07:38', 001, 'admin', '那是真的帅', '2022-10-12 14:30:43', 001);
INSERT INTO `message_board` VALUES (2, 'xihao', 'cpdd', NULL, '2022-10-12 15:43:43', NULL, NULL, NULL, NULL, 001);
INSERT INTO `message_board` VALUES (4, 'xihao', '啊倒萨', NULL, NULL, NULL, NULL, NULL, NULL, 000);

-- ----------------------------
-- Table structure for rank_list
-- ----------------------------
DROP TABLE IF EXISTS `rank_list`;
CREATE TABLE `rank_list`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `song_list_id` bigint(0) UNSIGNED NOT NULL,
  `consumer_id` bigint(0) UNSIGNED NOT NULL,
  `score` int(0) UNSIGNED NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `consumerId`(`consumer_id`, `song_list_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for singer
-- ----------------------------
DROP TABLE IF EXISTS `singer`;
CREATE TABLE `singer`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `sex` tinyint(0) NULL DEFAULT NULL,
  `pic` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `birth` datetime(0) NULL DEFAULT NULL,
  `location` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `introduction` varchar(1024) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 61 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of singer
-- ----------------------------
INSERT INTO `singer` VALUES (47, '周杰伦', 1, '/img/singerPic/1663748903484周杰伦.jpg', '1979-01-18 08:00:00', '中国', '周杰伦（Jay Chou），1979年1月18日出生于台湾省新北市，祖籍福建省泉州市永春县，中国台湾流行乐男歌手、音乐人、演员、导演、编剧，毕业于淡江中学。\n2000年发行首张个人专辑《Jay》。2001年发行的专辑《范特西》奠定其融合中西方音乐的风格。2002年举行“The One”世界巡回演唱会 。2003年成为美国《时代周刊》封面人物 。2004年获得世界音乐大奖中国区最畅销艺人奖 。2005年凭借动作片《头文字D》获得金马奖、金像奖最佳新人奖 。2006年起连续三年获得世界音乐大奖中国区最畅销艺人奖 。2007年自编自导的文艺片《不能说的秘密》获得金马奖年度台湾杰出电影奖  。\n2008年凭借歌曲《青花瓷》获得第19届金曲奖最佳作曲人奖。2009年入选美国CNN评出的“25位亚洲最具影响力人物”，同年凭借专辑《魔杰座》获得第20届金曲奖最佳国语男歌手奖 。2010年入选美国《Fast Company》评出的“全球百大创意人物”。2011年凭借专辑《跨时代》再度获得金曲奖最佳国语男歌手奖，并且第四次获得金曲奖最佳国语专辑奖；同年主演好莱坞电影《青蜂侠》。2012年登福布斯中国名人榜榜首 。2014年发行华语乐坛首张数字音乐专辑《哎呦，不错哦》。2021年在央视春晚演唱歌曲《Mojito》');
INSERT INTO `singer` VALUES (50, '薛之谦', 1, '/img/singerPic/1663817246827薛之谦.jpg', '1983-07-17 08:00:00', '中国', '薛之谦（Joker Xue），1983年7月17日出生于上海，中国内地流行乐男歌手、影视演员、音乐制作人，毕业于格里昂酒店管理学院。 2005年，因参加选秀节目《我型我秀》而正式出道。2006年，发行首张同名专辑《薛之谦》，随后凭借歌曲《认真的雪》获得广泛关注。2007年，凭借专辑《你过得好吗》获得MusicRadio中国TOP排行榜内地年度最受欢迎男歌手奖。2008年，发行专辑《深深爱过你》，同年在上海举行个人首场演唱会“谦年传说”。2013年，专辑《几个薛之谦》获得MusicRadio中国TOP排行榜内地推荐唱片。2014年，凭借专辑《意外》获得第21届东方风云榜颁奖最佳唱作人；同年他还获得音悦V榜年度盛典年度创作歌手奖。 2015年6月，薛之谦首度担当制作人并发行原创EP《绅士》；同年，他还主演都市励志剧《妈妈像花儿一样》。2016年，凭借EP《绅士》、《一半》获得第16届全球华语歌曲排行榜最受欢迎男歌手、五大最受欢迎男歌手奖、上海地区杰出歌手奖及最受欢迎创作歌手奖，而歌曲《初学者》则获得年度二十大金曲奖。2017年4月，开启“我好像在哪见过你”全国巡回演唱会；同年，薛之谦获得第7届全球流行音乐年度盛典年度最佳男歌手及MusicRadio榜中国TOP排行榜内地最佳男歌手。 2018年7月，薛之谦“摩天大楼”世界巡回演唱会于北京站启程，整个巡演横跨4大洲，8个国家，21个城市，累计上演场次23场。2020年12月31日，发行第11张个人专辑《天外来物》。');
INSERT INTO `singer` VALUES (51, '许嵩', 1, '/img/singerPic/1663817634016许嵩.jpg', '1986-05-14 09:00:00', '中国', '许嵩（Vae），1986年5月14日出生于安徽省合肥市，中国内地流行乐男歌手、原创音乐人，现任海蝶音乐公司（现为太合音乐集团）音乐总监，毕业于安徽医科大学。2006年起许嵩开始发表音乐作品，至2021年已成功发行八张个人原创专辑以及五十余首原创单曲，所有歌曲由许嵩创作词曲及音乐制作。截至2020年，许嵩已获得中歌榜、中国华鼎奖、东方风云榜、音乐风云榜等权威音乐榜单颁发的“最佳创作歌手奖”、“最佳唱作人奖”等个人奖项共20次；应邀为游戏、影视剧等创作主题曲包括《雨幕》（新天龙八部端游主题曲）、《千古》（电视剧《花千骨》片头曲）等歌曲共15首；六登央视舞台，十上卫视晚会献唱。');
INSERT INTO `singer` VALUES (52, '陈奕迅', 1, '/img/singerPic/1663817705361陈奕迅.jpg', '1974-07-27 08:00:00', '中国', '陈奕迅（Eason Chan），1974年7月27日出生于中国香港，祖籍广东省东莞市，中国香港流行乐男歌手、演员、作曲人，毕业于英国金斯顿大学。 1995年参加第14届新秀歌唱大赛并获得冠军，同年正式出道。1996年发行个人首张专辑《陈奕迅》。1997年主演个人首部电影《旺角大家姐》。1998年凭借歌曲《天下无双》在乐坛获得关注。2000年发行的歌曲《K歌之王》奠定其在歌坛的地位。2001年发行流行摇滚风格的专辑《反正是我》。2003年发行个人首张概念专辑《黑·白·灰》，专辑中的歌曲《十年》获得第4届音乐风云榜十大金曲奖；同年凭借专辑《Special Thanks to》获得第14届台湾金曲奖最佳国语男歌手以及最佳专辑奖。 2005年发行粤语专辑《U87》，专辑中的歌曲《浮夸》成为其歌唱生涯的代表作品之一。2006年起连续九年获得叱咤乐坛流行榜颁奖典礼我最喜爱的男歌手奖。2007年获得十大劲歌金曲最受欢迎男歌星奖。2010年凭借剧情片《金钱帝国》获得星光大典港台年度电影男演员奖。2012年发行舞曲风格的粤语专辑《…3mm》。2015年凭借专辑《米·闪》获得第26届台湾金曲奖最佳国语男歌手奖。2016年举行“Another Eason\'s LIFE世界巡回演唱会”。2018年凭借专辑《C\'mon in~》获得第29届台湾金曲奖最佳国语男歌手奖 ；同年发行专辑《L.O.V.E.》；同年，其主演的电影《卧底巨星》上映。 2020年7月11日在香港举办《Live is so much better with Music Eason Chan Charity Concert》慈善音乐会。');
INSERT INTO `singer` VALUES (53, '张杰', 1, '/img/singerPic/1663818228056张杰.jpg', '1982-12-20 08:00:00', '中国', '张杰，华语LIVE王。 出道16年来，发行了14张专辑，200多首高品质音乐作品，40多首OST金曲，拥有众多被广为传唱的歌曲。近年来，他创新运用MIX-POP曲风，开辟音乐发展新征程。 170多个音乐奖项，让他实现内地音乐奖项大满贯。2010年，获得韩国Mnet亚洲音乐盛典“亚洲之星”大奖；2014年，获得第42届全美音乐奖“年度国际艺人大奖”，成为内地首位获得该奖项的歌手。 至今为止，举办了 61场大型演唱会，巡演足迹踏过海内外30余座城市，累计130万人观看。2018、2019连续两年打破鸟巢单场演唱会票房纪录。');
INSERT INTO `singer` VALUES (61, '现在掺杂些', 1, '/img/singerPic/1663918273364李荣浩.jpg', '2022-09-23 08:00:00', '阿富汗', '啊书法大赛');

-- ----------------------------
-- Table structure for song
-- ----------------------------
DROP TABLE IF EXISTS `song`;
CREATE TABLE `song`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `singer_id` int(0) UNSIGNED NOT NULL,
  `name` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `introduction` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NOT NULL COMMENT '发行时间',
  `update_time` datetime(0) NOT NULL,
  `pic` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `lyric` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL,
  `url` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 115 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of song
-- ----------------------------
INSERT INTO `song` VALUES (114, 47, '周杰伦-晴天', '《叶惠美》', '2022-09-21 16:47:25', '2022-09-23 16:14:13', '/img/songPic/1663920780863周杰伦.jpg', '[ar:晴天 周杰伦]\r\n[ti: 晴天 周杰伦 ]\r\n[00:00.71]晴天 - 周杰伦\r\n[00:28.84]故事的小黄花\r\n[00:32.34]从出生那年就飘着\r\n[00:35.85]童年的荡秋千\r\n[00:39.41]随记忆一直晃到现在\r\n[00:42.77]rui sou sou xi dou xi la\r\n[00:45.50]sou la xi xi xi xi la xi la sou\r\n[00:49.41]吹着前奏望着天空\r\n[00:52.60]我想起花瓣试着掉落\r\n[00:56.10]为你翘课的那一天\r\n[00:58.29]花落的那一天\r\n[01:00.14]教室的那一间\r\n[01:01.79]我怎么看不见\r\n[01:03.61]消失的下雨天\r\n[01:05.38]我好想再淋一遍\r\n[01:09.51]没想到失去的勇气我还留着\r\n[01:15.72]好想再问一遍\r\n[01:17.47]你会等待还是离开\r\n[01:24.46]刮风这天我试过握着你手\r\n[01:30.18]但偏偏雨渐渐大到我看你不见\r\n[01:38.50]还要多久我才能在你身边\r\n[01:44.93]等到放晴的那天也许我会比较好一点\r\n[01:52.41]从前从前有个人爱你很久\r\n[01:58.07]但偏偏风渐渐把距离吹得好远\r\n[02:06.44]好不容易又能再多爱一天\r\n[02:12.81]但故事的最后你好像还是说了拜拜\r\n[02:33.93]为你翘课的那一天\r\n[02:36.39]花落的那一天\r\n[02:38.13]教室的那一间\r\n[02:39.84]我怎么看不见\r\n[02:41.55]消失的下雨天\r\n[02:43.31]我好想再淋一遍\r\n[02:47.39]没想到失去的勇气我还留着\r\n[02:53.39]好想再问一遍\r\n[02:55.49]你会等待还是离开\r\n[03:02.46]刮风这天我试过握着你手\r\n[03:08.12]但偏偏雨渐渐大到我看你不见\r\n[03:16.54]还要多久我才能在你身边\r\n[03:23.10]等到放晴的那天也许我会比较好一点\r\n[03:30.49]从前从前有个人爱你很久\r\n[03:36.26]但偏偏风渐渐把距离吹得好远\r\n[03:44.48]好不容易又能再多爱一天\r\n[03:51.05]但故事的最后你好像还是说了拜拜\r\n[03:57.65]刮风这天我试过握着你手\r\n[04:01.45]但偏偏雨渐渐大到我看你不见\r\n[04:04.98]还要多久我才能够在你身边\r\n[04:08.64]等到放晴那天也许我会比较好一点\r\n[04:12.34]从前从前有个人爱你很久\r\n[04:15.40]但偏偏雨渐渐把距离吹得好远\r\n[04:19.05]好不容易又能再多爱一天\r\n[04:22.42]但故事的最后你好像还是说了吧', '/song/晴天-周杰伦.mp3');
INSERT INTO `song` VALUES (115, 50, '薛之谦-暧昧', '《渡》', '2022-09-22 11:29:21', '2022-09-22 11:29:50', '/img/songPic/1663817389842暧昧.jpg', '[ver:v1.0]\r\n[ti:暧昧 - 薛之谦]\r\n[00:00.88]暧昧 - 薛之谦\r\n[00:02.44]作词：薛之谦\r\n[00:04.19]作曲：薛之谦\r\n[00:05.57]编曲：薛之谦&郑伟\r\n[00:07.67]制作人：郑伟\r\n[00:15.59]反正现在的感情 都暧昧\r\n[00:20.04]你大可不必为难 找般配\r\n[00:25.09]付出过的人排队 谈体会\r\n[00:29.30]趁年轻别害怕一个人睡\r\n[00:34.41]可能是现在感情 太昂贵\r\n[00:39.27]让付出真心的人 好狼狈\r\n[00:44.01]还不如听首情歌 的机会 忘了谁\r\n[00:55.55]感情像牛奶一杯 越甜越让人生畏\r\n[01:04.69]都早有些防备 润色前的原味\r\n[01:13.95]所以人们都拿起咖啡 把试探放在两人位\r\n[01:24.17]距离感一对 就不必再赤裸相对\r\n[01:32.54]反正现在的感情 都暧昧\r\n[01:36.92]你大可不必为难 找般配\r\n[01:41.62]付出过的人排队 谈体会\r\n[01:46.22]弃之可惜 食而无味\r\n[01:51.22]可能是现在感情 太珍贵\r\n[01:55.98]让付出真心的人 好疲惫\r\n[02:00.83]谁不曾用过卑微的词汇 想留住谁\r\n[02:50.33]还贪恋着衣衫昂贵 却输给了廉价香水\r\n[03:00.00]他先诱你入位 还刻意放低了分贝\r\n[03:09.62]可感情越爱越妩媚 像烂掉的苹果一堆\r\n[03:19.20]连基因都不对 还在意什么鱼腥味\r\n[03:27.64]反正现在的感情 都暧昧\r\n[03:32.03]你大可不必为难 找般配\r\n[03:37.11]何必给自己沉迷 的机会\r\n[03:41.54]不如用误会来结尾\r\n[03:46.38]反正现在的我们 算暧昧\r\n[03:51.18]我愿意给的感情 请浪费\r\n[03:55.99]反正流过的眼泪 难收回\r\n[04:00.79]就别再安慰\r\n[04:05.95]看你入眠的侧脸 有多美\r\n[04:10.83]和你丢下的一切 好匹配\r\n[04:17.45]我还以为我能 多狼狈\r\n[04:23.72]我自以为', '/song/暧昧-薛之谦.mp3');

-- ----------------------------
-- Table structure for song_list
-- ----------------------------
DROP TABLE IF EXISTS `song_list`;
CREATE TABLE `song_list`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `pic` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `introduction` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL,
  `style` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '无',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 42340357 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of song_list
-- ----------------------------
INSERT INTO `song_list` VALUES (42340356, '111', '/img/songListPic/1663920925330Ariana Grande.jpg', '111', '111');
INSERT INTO `song_list` VALUES (42340357, 'GDFG', '/img/songListPic/songListDefault.jpg', 'DFH', '欧美');

-- ----------------------------
-- Table structure for user_support
-- ----------------------------
DROP TABLE IF EXISTS `user_support`;
CREATE TABLE `user_support`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `comment_id` int(0) NOT NULL,
  `user_id` int(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
