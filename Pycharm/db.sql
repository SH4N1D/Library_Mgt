/*
SQLyog Community v13.0.1 (64 bit)
MySQL - 8.0.33 : Database - library
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`library` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `library`;

/*Table structure for table `auth_group` */

DROP TABLE IF EXISTS `auth_group`;

CREATE TABLE `auth_group` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(150) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `auth_group` */

/*Table structure for table `auth_group_permissions` */

DROP TABLE IF EXISTS `auth_group_permissions`;

CREATE TABLE `auth_group_permissions` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `group_id` int NOT NULL,
  `permission_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_group_permissions_group_id_permission_id_0cd325b0_uniq` (`group_id`,`permission_id`),
  KEY `auth_group_permissio_permission_id_84c5c92e_fk_auth_perm` (`permission_id`),
  CONSTRAINT `auth_group_permissio_permission_id_84c5c92e_fk_auth_perm` FOREIGN KEY (`permission_id`) REFERENCES `auth_permission` (`id`),
  CONSTRAINT `auth_group_permissions_group_id_b120cbf9_fk_auth_group_id` FOREIGN KEY (`group_id`) REFERENCES `auth_group` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `auth_group_permissions` */

/*Table structure for table `auth_permission` */

DROP TABLE IF EXISTS `auth_permission`;

CREATE TABLE `auth_permission` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `content_type_id` int NOT NULL,
  `codename` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_permission_content_type_id_codename_01ab375a_uniq` (`content_type_id`,`codename`),
  CONSTRAINT `auth_permission_content_type_id_2f476e4b_fk_django_co` FOREIGN KEY (`content_type_id`) REFERENCES `django_content_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=97 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `auth_permission` */

insert  into `auth_permission`(`id`,`name`,`content_type_id`,`codename`) values 
(1,'Can add log entry',1,'add_logentry'),
(2,'Can change log entry',1,'change_logentry'),
(3,'Can delete log entry',1,'delete_logentry'),
(4,'Can view log entry',1,'view_logentry'),
(5,'Can add permission',2,'add_permission'),
(6,'Can change permission',2,'change_permission'),
(7,'Can delete permission',2,'delete_permission'),
(8,'Can view permission',2,'view_permission'),
(9,'Can add group',3,'add_group'),
(10,'Can change group',3,'change_group'),
(11,'Can delete group',3,'delete_group'),
(12,'Can view group',3,'view_group'),
(13,'Can add user',4,'add_user'),
(14,'Can change user',4,'change_user'),
(15,'Can delete user',4,'delete_user'),
(16,'Can view user',4,'view_user'),
(17,'Can add content type',5,'add_contenttype'),
(18,'Can change content type',5,'change_contenttype'),
(19,'Can delete content type',5,'delete_contenttype'),
(20,'Can view content type',5,'view_contenttype'),
(21,'Can add session',6,'add_session'),
(22,'Can change session',6,'change_session'),
(23,'Can delete session',6,'delete_session'),
(24,'Can view session',6,'view_session'),
(25,'Can add books',7,'add_books'),
(26,'Can change books',7,'change_books'),
(27,'Can delete books',7,'delete_books'),
(28,'Can view books',7,'view_books'),
(29,'Can add chat',8,'add_chat'),
(30,'Can change chat',8,'change_chat'),
(31,'Can delete chat',8,'delete_chat'),
(32,'Can view chat',8,'view_chat'),
(33,'Can add library_table',9,'add_library_table'),
(34,'Can change library_table',9,'change_library_table'),
(35,'Can delete library_table',9,'delete_library_table'),
(36,'Can view library_table',9,'view_library_table'),
(37,'Can add login_table',10,'add_login_table'),
(38,'Can change login_table',10,'change_login_table'),
(39,'Can delete login_table',10,'delete_login_table'),
(40,'Can view login_table',10,'view_login_table'),
(41,'Can add notificaton',11,'add_notificaton'),
(42,'Can change notificaton',11,'change_notificaton'),
(43,'Can delete notificaton',11,'delete_notificaton'),
(44,'Can view notificaton',11,'view_notificaton'),
(45,'Can add user_table',12,'add_user_table'),
(46,'Can change user_table',12,'change_user_table'),
(47,'Can delete user_table',12,'delete_user_table'),
(48,'Can view user_table',12,'view_user_table'),
(49,'Can add suggestion',13,'add_suggestion'),
(50,'Can change suggestion',13,'change_suggestion'),
(51,'Can delete suggestion',13,'delete_suggestion'),
(52,'Can view suggestion',13,'view_suggestion'),
(53,'Can add report',14,'add_report'),
(54,'Can change report',14,'change_report'),
(55,'Can delete report',14,'delete_report'),
(56,'Can view report',14,'view_report'),
(57,'Can add rating',15,'add_rating'),
(58,'Can change rating',15,'change_rating'),
(59,'Can delete rating',15,'delete_rating'),
(60,'Can view rating',15,'view_rating'),
(61,'Can add plibrary',16,'add_plibrary'),
(62,'Can change plibrary',16,'change_plibrary'),
(63,'Can delete plibrary',16,'delete_plibrary'),
(64,'Can view plibrary',16,'view_plibrary'),
(65,'Can add order',17,'add_order'),
(66,'Can change order',17,'change_order'),
(67,'Can delete order',17,'delete_order'),
(68,'Can view order',17,'view_order'),
(69,'Can add lnotification',18,'add_lnotification'),
(70,'Can change lnotification',18,'change_lnotification'),
(71,'Can delete lnotification',18,'delete_lnotification'),
(72,'Can view lnotification',18,'view_lnotification'),
(73,'Can add levents',19,'add_levents'),
(74,'Can change levents',19,'change_levents'),
(75,'Can delete levents',19,'delete_levents'),
(76,'Can view levents',19,'view_levents'),
(77,'Can add feedback',20,'add_feedback'),
(78,'Can change feedback',20,'change_feedback'),
(79,'Can delete feedback',20,'delete_feedback'),
(80,'Can view feedback',20,'view_feedback'),
(81,'Can add donation',21,'add_donation'),
(82,'Can change donation',21,'change_donation'),
(83,'Can delete donation',21,'delete_donation'),
(84,'Can view donation',21,'view_donation'),
(85,'Can add complaint',22,'add_complaint'),
(86,'Can change complaint',22,'change_complaint'),
(87,'Can delete complaint',22,'delete_complaint'),
(88,'Can view complaint',22,'view_complaint'),
(89,'Can add authbrd',23,'add_authbrd'),
(90,'Can change authbrd',23,'change_authbrd'),
(91,'Can delete authbrd',23,'delete_authbrd'),
(92,'Can view authbrd',23,'view_authbrd'),
(93,'Can add shelf_table',24,'add_shelf_table'),
(94,'Can change shelf_table',24,'change_shelf_table'),
(95,'Can delete shelf_table',24,'delete_shelf_table'),
(96,'Can view shelf_table',24,'view_shelf_table');

/*Table structure for table `auth_user` */

DROP TABLE IF EXISTS `auth_user`;

CREATE TABLE `auth_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `password` varchar(128) NOT NULL,
  `last_login` datetime(6) DEFAULT NULL,
  `is_superuser` tinyint(1) NOT NULL,
  `username` varchar(150) NOT NULL,
  `first_name` varchar(150) NOT NULL,
  `last_name` varchar(150) NOT NULL,
  `email` varchar(254) NOT NULL,
  `is_staff` tinyint(1) NOT NULL,
  `is_active` tinyint(1) NOT NULL,
  `date_joined` datetime(6) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `auth_user` */

insert  into `auth_user`(`id`,`password`,`last_login`,`is_superuser`,`username`,`first_name`,`last_name`,`email`,`is_staff`,`is_active`,`date_joined`) values 
(1,'pbkdf2_sha256$260000$huZwcp3UOjgVikhKHh4aF4$rdkRckSXowfQbza+mq93H6qhfyxppu5gqJiS6aRLaqI=','2024-01-20 09:27:52.467116',1,'admin','','','admin@gmail.com',1,1,'2023-11-26 04:11:48.820679');

/*Table structure for table `auth_user_groups` */

DROP TABLE IF EXISTS `auth_user_groups`;

CREATE TABLE `auth_user_groups` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `group_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_user_groups_user_id_group_id_94350c0c_uniq` (`user_id`,`group_id`),
  KEY `auth_user_groups_group_id_97559544_fk_auth_group_id` (`group_id`),
  CONSTRAINT `auth_user_groups_group_id_97559544_fk_auth_group_id` FOREIGN KEY (`group_id`) REFERENCES `auth_group` (`id`),
  CONSTRAINT `auth_user_groups_user_id_6a12ed8b_fk_auth_user_id` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `auth_user_groups` */

/*Table structure for table `auth_user_user_permissions` */

DROP TABLE IF EXISTS `auth_user_user_permissions`;

CREATE TABLE `auth_user_user_permissions` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `permission_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_user_user_permissions_user_id_permission_id_14a6b632_uniq` (`user_id`,`permission_id`),
  KEY `auth_user_user_permi_permission_id_1fbb5f2c_fk_auth_perm` (`permission_id`),
  CONSTRAINT `auth_user_user_permi_permission_id_1fbb5f2c_fk_auth_perm` FOREIGN KEY (`permission_id`) REFERENCES `auth_permission` (`id`),
  CONSTRAINT `auth_user_user_permissions_user_id_a95ead1b_fk_auth_user_id` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `auth_user_user_permissions` */

/*Table structure for table `django_admin_log` */

DROP TABLE IF EXISTS `django_admin_log`;

CREATE TABLE `django_admin_log` (
  `id` int NOT NULL AUTO_INCREMENT,
  `action_time` datetime(6) NOT NULL,
  `object_id` longtext,
  `object_repr` varchar(200) NOT NULL,
  `action_flag` smallint unsigned NOT NULL,
  `change_message` longtext NOT NULL,
  `content_type_id` int DEFAULT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `django_admin_log_content_type_id_c4bce8eb_fk_django_co` (`content_type_id`),
  KEY `django_admin_log_user_id_c564eba6_fk_auth_user_id` (`user_id`),
  CONSTRAINT `django_admin_log_content_type_id_c4bce8eb_fk_django_co` FOREIGN KEY (`content_type_id`) REFERENCES `django_content_type` (`id`),
  CONSTRAINT `django_admin_log_user_id_c564eba6_fk_auth_user_id` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`id`),
  CONSTRAINT `django_admin_log_chk_1` CHECK ((`action_flag` >= 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `django_admin_log` */

/*Table structure for table `django_content_type` */

DROP TABLE IF EXISTS `django_content_type`;

CREATE TABLE `django_content_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `app_label` varchar(100) NOT NULL,
  `model` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `django_content_type_app_label_model_76bd3d3b_uniq` (`app_label`,`model`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `django_content_type` */

insert  into `django_content_type`(`id`,`app_label`,`model`) values 
(1,'admin','logentry'),
(3,'auth','group'),
(2,'auth','permission'),
(4,'auth','user'),
(5,'contenttypes','contenttype'),
(23,'libraryapp','authbrd'),
(7,'libraryapp','books'),
(8,'libraryapp','chat'),
(22,'libraryapp','complaint'),
(21,'libraryapp','donation'),
(20,'libraryapp','feedback'),
(19,'libraryapp','levents'),
(9,'libraryapp','library_table'),
(18,'libraryapp','lnotification'),
(10,'libraryapp','login_table'),
(11,'libraryapp','notificaton'),
(17,'libraryapp','order'),
(16,'libraryapp','plibrary'),
(15,'libraryapp','rating'),
(14,'libraryapp','report'),
(24,'libraryapp','shelf_table'),
(13,'libraryapp','suggestion'),
(12,'libraryapp','user_table'),
(6,'sessions','session');

/*Table structure for table `django_migrations` */

DROP TABLE IF EXISTS `django_migrations`;

CREATE TABLE `django_migrations` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `app` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `applied` datetime(6) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `django_migrations` */

insert  into `django_migrations`(`id`,`app`,`name`,`applied`) values 
(1,'contenttypes','0001_initial','2023-11-26 03:52:19.240343'),
(2,'auth','0001_initial','2023-11-26 03:52:19.572301'),
(3,'admin','0001_initial','2023-11-26 03:52:19.661308'),
(4,'admin','0002_logentry_remove_auto_add','2023-11-26 03:52:19.672298'),
(5,'admin','0003_logentry_add_action_flag_choices','2023-11-26 03:52:19.682097'),
(6,'contenttypes','0002_remove_content_type_name','2023-11-26 03:52:19.743269'),
(7,'auth','0002_alter_permission_name_max_length','2023-11-26 03:52:19.784924'),
(8,'auth','0003_alter_user_email_max_length','2023-11-26 03:52:19.810456'),
(9,'auth','0004_alter_user_username_opts','2023-11-26 03:52:19.821564'),
(10,'auth','0005_alter_user_last_login_null','2023-11-26 03:52:19.862978'),
(11,'auth','0006_require_contenttypes_0002','2023-11-26 03:52:19.867443'),
(12,'auth','0007_alter_validators_add_error_messages','2023-11-26 03:52:19.878459'),
(13,'auth','0008_alter_user_username_max_length','2023-11-26 03:52:19.926364'),
(14,'auth','0009_alter_user_last_name_max_length','2023-11-26 03:52:19.973151'),
(15,'auth','0010_alter_group_name_max_length','2023-11-26 03:52:19.996814'),
(16,'auth','0011_update_proxy_permissions','2023-11-26 03:52:20.008711'),
(17,'auth','0012_alter_user_first_name_max_length','2023-11-26 03:52:20.054467'),
(18,'libraryapp','0001_initial','2023-11-26 03:52:20.968024'),
(19,'sessions','0001_initial','2023-11-26 03:52:20.994181'),
(20,'libraryapp','0002_auto_20231229_0919','2023-12-29 03:49:55.927899'),
(21,'libraryapp','0003_delete_feedback','2023-12-29 03:58:56.680439'),
(22,'libraryapp','0004_remove_suggestion_suggestion','2023-12-30 05:45:14.766606'),
(23,'libraryapp','0005_donation_status','2024-01-09 10:14:00.858956'),
(24,'libraryapp','0006_auto_20240114_1011','2024-01-14 04:41:19.272979'),
(25,'libraryapp','0007_auto_20240114_1534','2024-01-14 10:04:54.656922');

/*Table structure for table `django_session` */

DROP TABLE IF EXISTS `django_session`;

CREATE TABLE `django_session` (
  `session_key` varchar(40) NOT NULL,
  `session_data` longtext NOT NULL,
  `expire_date` datetime(6) NOT NULL,
  PRIMARY KEY (`session_key`),
  KEY `django_session_expire_date_a5c62663` (`expire_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `django_session` */

insert  into `django_session`(`session_key`,`session_data`,`expire_date`) values 
('kx3nu9y68v0r8iheqaeny6ux3oqh4yis','.eJxVjsEOgyAYg9-FsyH-DAF33N1nMOUHppvBRPS07N03Eg_bqUn7telLjDj2aTxK3MY5iKsg0fx6HvyMuQbhgXxfJa9532YvKyLPtMhhDXG5nezfwIQyfdvRaFj27EHovA6pvWgFw-jQE3WtDcEnp2JkOG1MIrKwrk_UOmVB9dVS_1EjuKp6fwCwoTxq:1rR7do:PFUH4foVdKqWB4Q797aL_q5Wc2yOwGE3DQFR898pCNg','2024-02-03 09:27:52.487875'),
('lcmqxheuha452l6ofzqg2phreok62fsr','.eJxVjsEOgyAYg9-FsyH-DAF33N1nMOUHppvBRPS07N03Eg_bqUn7telLjDj2aTxK3MY5iKsg0fx6HvyMuQbhgXxfJa9532YvKyLPtMhhDXG5nezfwIQyfdvRaFj27EHovA6pvWgFw-jQE3WtDcEnp2JkOG1MIrKwrk_UOmVB9dVS_1EjuKp6fwCwoTxq:1rEhcf:c_cjsQn9BzVR14wHrLDwG7_sVnDIr98w1lZyCIg-efw','2023-12-31 03:15:21.527627'),
('ygl2fzm9vp9aduy9apbqk4wwoar1rh1i','.eJxVjsEOgyAYg9-FsyH-DAF33N1nMOUHppvBRPS07N03Eg_bqUn7telLjDj2aTxK3MY5iKsg0fx6HvyMuQbhgXxfJa9532YvKyLPtMhhDXG5nezfwIQyfdvRaFj27EHovA6pvWgFw-jQE3WtDcEnp2JkOG1MIrKwrk_UOmVB9dVS_1EjuKp6fwCwoTxq:1rOHmF:nET9Xd5xEaxm2QXvZkGuRAnV70JDbRlYoIOxx0-dyfk','2024-01-26 13:40:51.216937');

/*Table structure for table `libraryapp_authbrd` */

DROP TABLE IF EXISTS `libraryapp_authbrd`;

CREATE TABLE `libraryapp_authbrd` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `status` varchar(100) NOT NULL,
  `LIBRARY_id` bigint NOT NULL,
  `USER_id` bigint NOT NULL,
  `post` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `libraryapp_authbrd_LIBRARY_id_215ac175_fk_libraryap` (`LIBRARY_id`),
  KEY `libraryapp_authbrd_USER_id_b1e567d8_fk_libraryapp_user_table_id` (`USER_id`),
  CONSTRAINT `libraryapp_authbrd_LIBRARY_id_215ac175_fk_libraryap` FOREIGN KEY (`LIBRARY_id`) REFERENCES `libraryapp_library_table` (`id`),
  CONSTRAINT `libraryapp_authbrd_USER_id_b1e567d8_fk_libraryapp_user_table_id` FOREIGN KEY (`USER_id`) REFERENCES `libraryapp_user_table` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `libraryapp_authbrd` */

insert  into `libraryapp_authbrd`(`id`,`date`,`status`,`LIBRARY_id`,`USER_id`,`post`) values 
(17,'2024-01-15','accepted',2,1,'pexels-tara-winstead-8850712.jpg'),
(19,'2024-01-15','accepted',2,1,'pexels-ravi-kant-2229237.jpg'),
(23,'2024-01-15','accepted',2,1,'inspirational-quotes-35.jpg'),
(24,'2024-01-15','accepted',2,1,'denise-jans-laoBHO09sU0-unsplash.jpg'),
(26,'2024-01-15','pending',2,1,'james-healy-WZ-YnvCCLug-unsplash.jpg');

/*Table structure for table `libraryapp_books` */

DROP TABLE IF EXISTS `libraryapp_books`;

CREATE TABLE `libraryapp_books` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `image` varchar(100) NOT NULL,
  `bookname` varchar(100) NOT NULL,
  `status` varchar(100) NOT NULL,
  `stock` varchar(100) NOT NULL,
  `details` varchar(100) NOT NULL,
  `author` varchar(100) NOT NULL,
  `review` varchar(100) NOT NULL,
  `category` varchar(100) NOT NULL,
  `LIBRARY_id` bigint NOT NULL,
  `type` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `libraryapp_books_LIBRARY_id_66c020b2_fk_libraryap` (`LIBRARY_id`),
  CONSTRAINT `libraryapp_books_LIBRARY_id_66c020b2_fk_libraryap` FOREIGN KEY (`LIBRARY_id`) REFERENCES `libraryapp_library_table` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `libraryapp_books` */

insert  into `libraryapp_books`(`id`,`image`,`bookname`,`status`,`stock`,`details`,`author`,`review`,`category`,`LIBRARY_id`,`type`) values 
(13,'e4ef24527be4ca7736298bd3b6383b20.jpg','Qwerty ','available','3','point of view of the world cup final ','Poiu',' at the same timeline photos send me the same ','fantasy ',1,'Hard Copy'),
(15,'ccb06a76cc56b397c57a7e001f7d6614.jpg','Asdfg','available','2','sudbeekdk','Qwert','jsjsvev','shebeveb',1,'Hard Copy'),
(16,'6defac1e403e26db56737f89d3fc08b4_C1NmAdQ.jpg','Asdfgh','available','1','sjebeveb','jsbevev','sjjsev','nsjsbdvr',1,'Hard Copy'),
(17,'3378c755ef8384d3eddde0be8d74af0a.jpg','Zxcvb','available','1','ehev3v','widbeb','jshevw','wbeje',1,'Hard Copy'),
(18,'01ba5280ed11b9c00fc9a909374401bc.jpg','Poiuyt','available','4','svwvw','Ehevev','sjdbe','shshehe',2,'Hard Copy'),
(20,'41af8fa75214aa810dc1a73121973301.jpg','Zdty','available','3','ejejeh','Shrfn','ejejeb','dkdkeb',2,'Hard Copy'),
(29,'ccb06a76cc56b397c57a7e001f7d6614_kKJFwyJ.jpg','shshbs','available','2','whegeg','ehehge','wjwgwg','sjejhe',2,'Soft Copy'),
(30,'/media/c78721c48750e2f3802d65da5c55e2cc_4bdIkDh.jpg','jsjhs','available','2','sjshs','whehe','sbsbs','sbsb',1,'hardcopy'),
(31,'pexels-pixabay-256450.jpg','sjsh','available','2','wieheb','tsjdb','sieb','siehe',2,'Hard Copy'),
(34,'pexels-lance-gauer-3990897_axTsqt0.jpg','wiehdh','available','2','enebe ','ejebeb','dndndb','jsnebe',2,'Hard Copy');

/*Table structure for table `libraryapp_chat` */

DROP TABLE IF EXISTS `libraryapp_chat`;

CREATE TABLE `libraryapp_chat` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `fromid_id` bigint NOT NULL,
  `toid_id` bigint NOT NULL,
  `message` varchar(100) NOT NULL,
  `date` date NOT NULL,
  `time` time(6) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `libraryapp_chat_fromid_id_9f767d14` (`fromid_id`),
  KEY `libraryapp_chat_toid_id_5596c62b` (`toid_id`),
  CONSTRAINT `libraryapp_chat_fromid_id_9f767d14_fk_libraryapp_login_table_id` FOREIGN KEY (`fromid_id`) REFERENCES `libraryapp_login_table` (`id`),
  CONSTRAINT `libraryapp_chat_toid_id_5596c62b_fk_libraryapp_login_table_id` FOREIGN KEY (`toid_id`) REFERENCES `libraryapp_login_table` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `libraryapp_chat` */

insert  into `libraryapp_chat`(`id`,`fromid_id`,`toid_id`,`message`,`date`,`time`) values 
(1,5,3,'hshs','2024-01-14','10:11:27.197772'),
(2,5,3,'hshs','2024-01-14','10:11:28.880051'),
(3,5,3,'hshs','2024-01-14','10:11:58.525962'),
(4,5,3,'hai','2024-01-14','10:26:21.229296'),
(5,3,5,'hello','2024-01-14','10:27:21.000000'),
(6,3,5,'sie','2024-01-14','11:05:19.146163'),
(7,3,5,'hello','2024-01-14','13:06:23.016808'),
(8,5,8,'hi','2024-01-14','19:14:25.994879'),
(9,3,5,'no','2024-01-14','19:16:42.970198');

/*Table structure for table `libraryapp_complaint` */

DROP TABLE IF EXISTS `libraryapp_complaint`;

CREATE TABLE `libraryapp_complaint` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `complaint` varchar(100) NOT NULL,
  `date` date NOT NULL,
  `reply` varchar(100) NOT NULL,
  `USER_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `libraryapp_complaint_USER_id_04752baa_fk_libraryap` (`USER_id`),
  CONSTRAINT `libraryapp_complaint_USER_id_04752baa_fk_libraryap` FOREIGN KEY (`USER_id`) REFERENCES `libraryapp_user_table` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `libraryapp_complaint` */

insert  into `libraryapp_complaint`(`id`,`complaint`,`date`,`reply`,`USER_id`) values 
(1,'awkjbchakjw','2023-11-14','PLEASE ENTER THE TEXT HERE\r\n          ',1),
(2,'waekf.nnklal','2023-11-13','pending',2),
(3,'siehhe','2024-01-13','pending',1),
(4,'siheheb','2024-01-13','pending',1);

/*Table structure for table `libraryapp_donation` */

DROP TABLE IF EXISTS `libraryapp_donation`;

CREATE TABLE `libraryapp_donation` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `bookname` varchar(100) NOT NULL,
  `date` date NOT NULL,
  `USER_id` bigint NOT NULL,
  `LIBRARY_id` bigint NOT NULL,
  `author` varchar(100) NOT NULL,
  `category` varchar(100) NOT NULL,
  `details` varchar(100) NOT NULL,
  `image` varchar(100) NOT NULL,
  `review` varchar(100) NOT NULL,
  `stock` varchar(100) NOT NULL,
  `status` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `libraryapp_donation_USER_id_13732628_fk_libraryapp_user_table_id` (`USER_id`),
  KEY `libraryapp_donation_LIBRARY_id_018e9433_fk_libraryap` (`LIBRARY_id`),
  CONSTRAINT `libraryapp_donation_LIBRARY_id_018e9433_fk_libraryap` FOREIGN KEY (`LIBRARY_id`) REFERENCES `libraryapp_library_table` (`id`),
  CONSTRAINT `libraryapp_donation_USER_id_13732628_fk_libraryapp_user_table_id` FOREIGN KEY (`USER_id`) REFERENCES `libraryapp_user_table` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `libraryapp_donation` */

insert  into `libraryapp_donation`(`id`,`bookname`,`date`,`USER_id`,`LIBRARY_id`,`author`,`category`,`details`,`image`,`review`,`stock`,`status`) values 
(1,'utfuf','2024-01-10',2,2,'gyyjy','kjyf','kuggl','kj','ljbk','3','completed'),
(2,'ewjfjw','2024-01-14',1,1,'whevev','ehebe ','ejebe','7f4878a402da392e34a1752b6808ccfc.jpg','dje r ','2','pending'),
(3,'waljlda','2024-01-14',1,1,'sjebe','dbe ','ebev','6defac1e403e26db56737f89d3fc08b4_WGkevcZ.jpg','e e d','2','pending'),
(4,'wafn','2024-01-14',1,1,'wiwv','sbev','wvev','e4ef24527be4ca7736298bd3b6383b20_4a1AWI0.jpg','ebeve','2','pending'),
(5,'awf','2024-01-14',1,1,'sjsje','snsbs','shsbs','c78721c48750e2f3802d65da5c55e2cc_gCkAfyw.jpg','sbsns','2','pending'),
(6,'afwkaf','2024-01-14',1,1,'sjjsbs','sbsbs','wbwvw','1be310f64eae23bb55a0dff76b45b868_nZ9adnF.jpg','sbsbs','3','pending'),
(7,'aegk','2024-01-14',1,1,'sjsj','snsn','wneb','ccb06a76cc56b397c57a7e001f7d6614_yribB9j.jpg','snsb','2','pending'),
(8,'jsjhs','2024-01-14',1,1,'whehe','sbsb','sjshs','c78721c48750e2f3802d65da5c55e2cc_4bdIkDh.jpg','sbsbs','2','completed'),
(9,'hhtdffy','2024-01-14',1,1,'tfervv','ghigvb','trcv','f46f38a27f56fc21ad38e5755c8a362a_6EeP8OL.jpg','grrbbbh','2','pending'),
(10,'hhtdffy','2024-01-14',1,1,'tfervv','ghigvb','trcv','f46f38a27f56fc21ad38e5755c8a362a_KrlYBIF.jpg','grrbbbh','2','pending');

/*Table structure for table `libraryapp_levents` */

DROP TABLE IF EXISTS `libraryapp_levents`;

CREATE TABLE `libraryapp_levents` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `LIBRARY_id` bigint NOT NULL,
  `poster` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `libraryapp_levents_LIBRARY_id_3e0b035e_fk_libraryap` (`LIBRARY_id`),
  CONSTRAINT `libraryapp_levents_LIBRARY_id_3e0b035e_fk_libraryap` FOREIGN KEY (`LIBRARY_id`) REFERENCES `libraryapp_library_table` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `libraryapp_levents` */

insert  into `libraryapp_levents`(`id`,`date`,`LIBRARY_id`,`poster`) values 
(16,'2024-01-15',2,'business-conference-poster-template-03865542958eb60c0ff84323814c99bb_screen.jpg'),
(17,'2024-01-15',2,'e7ccb8ce-dae0-47ae-9204-0561520970a6.webp'),
(22,'2024-01-15',2,'golden-black-event-flyer-template-design-c95f27d2f204a165312afcdc28aba59e_screen.jpg');

/*Table structure for table `libraryapp_library_table` */

DROP TABLE IF EXISTS `libraryapp_library_table`;

CREATE TABLE `libraryapp_library_table` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `place` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `phoneno` bigint NOT NULL,
  `latitude` varchar(100) NOT NULL,
  `longitude` varchar(100) NOT NULL,
  `image` varchar(100) NOT NULL,
  `LOGIN_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `libraryapp_library_t_LOGIN_id_9c105688_fk_libraryap` (`LOGIN_id`),
  CONSTRAINT `libraryapp_library_t_LOGIN_id_9c105688_fk_libraryap` FOREIGN KEY (`LOGIN_id`) REFERENCES `libraryapp_login_table` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `libraryapp_library_table` */

insert  into `libraryapp_library_table`(`id`,`name`,`place`,`email`,`phoneno`,`latitude`,`longitude`,`image`,`LOGIN_id`) values 
(1,'JDT ARTS & SCIENCE Library','Vellimadkunu','aefcae',2143256387,'3245','23423','c184eac45c54dfc395eb3a26fdcd200a.jpg',2),
(2,'PVS HSS Library','Awdhbakwjh','riitnaveen@gmail.com',5465433232,'3842','23245','14cf547c7bcaa057c617747e1c8f2016.jpg',5),
(3,'Zhsndn','Bxnxm','Bnznz',9264649176,'6472992','6473992','a200eb7b3c8fef8397756776192bacea.jpg ',7),
(4,'MSS Library','Malaparamb','jsbsvwb',5432167890,'1515','162526','6b066671dc464a9c0bb999e947774417.jpg',11),
(5,'PVS Library','MUNNAR','jsjsvsv',1234567890,'123','456','864a8df19b14bbbb2cb4bf35386772f6.jpg',12),
(6,'BRT Library','BANGALORE ','nshsbwvv',987654321,'456','789','a200eb7b3c8fef8397756776192bacea.jpg',13);

/*Table structure for table `libraryapp_lnotification` */

DROP TABLE IF EXISTS `libraryapp_lnotification`;

CREATE TABLE `libraryapp_lnotification` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `notifcation` varchar(100) NOT NULL,
  `date` date NOT NULL,
  `ORDER_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `libraryapp_lnotifica_ORDER_id_dccc5e39_fk_libraryap` (`ORDER_id`),
  CONSTRAINT `libraryapp_lnotifica_ORDER_id_dccc5e39_fk_libraryap` FOREIGN KEY (`ORDER_id`) REFERENCES `libraryapp_order` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `libraryapp_lnotification` */

insert  into `libraryapp_lnotification`(`id`,`notifcation`,`date`,`ORDER_id`) values 
(4,'ajwheheh','2024-01-13',30);

/*Table structure for table `libraryapp_login_table` */

DROP TABLE IF EXISTS `libraryapp_login_table`;

CREATE TABLE `libraryapp_login_table` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `type` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `libraryapp_login_table` */

insert  into `libraryapp_login_table`(`id`,`username`,`password`,`type`) values 
(1,'admin','123','admin'),
(2,'jdt','123','library'),
(3,'asd','123','user'),
(5,'awda','123','library'),
(7,'Sudu1','sudu2','pending'),
(8,'Sudu','sudu','user'),
(9,'poi','123','user'),
(10,'lkj','123','user'),
(11,'zxc','123','library'),
(12,'qwe','123','library'),
(13,'cvb','123','library'),
(19,'wnwbwn','wnwnwn','user'),
(20,'wnwbwn','wnwnwn','user');

/*Table structure for table `libraryapp_notificaton` */

DROP TABLE IF EXISTS `libraryapp_notificaton`;

CREATE TABLE `libraryapp_notificaton` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `notification` varchar(100) NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `libraryapp_notificaton` */

insert  into `libraryapp_notificaton`(`id`,`notification`,`date`) values 
(1,'THERE WILL BE MAINTENANCE FOR 2HRS TOMORROW','2023-11-26'),
(7,'PLEASE ENTER THE TEXT HERE','2024-01-07');

/*Table structure for table `libraryapp_order` */

DROP TABLE IF EXISTS `libraryapp_order`;

CREATE TABLE `libraryapp_order` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `status` varchar(100) NOT NULL,
  `orderdate` date NOT NULL,
  `duedate` date NOT NULL,
  `BOOK_id` bigint NOT NULL,
  `USER_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `libraryapp_order_BOOK_id_4d8e2e92_fk_libraryapp_books_id` (`BOOK_id`),
  KEY `libraryapp_order_USER_id_4540a640_fk_libraryapp_user_table_id` (`USER_id`),
  CONSTRAINT `libraryapp_order_BOOK_id_4d8e2e92_fk_libraryapp_books_id` FOREIGN KEY (`BOOK_id`) REFERENCES `libraryapp_books` (`id`),
  CONSTRAINT `libraryapp_order_USER_id_4540a640_fk_libraryapp_user_table_id` FOREIGN KEY (`USER_id`) REFERENCES `libraryapp_user_table` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `libraryapp_order` */

insert  into `libraryapp_order`(`id`,`status`,`orderdate`,`duedate`,`BOOK_id`,`USER_id`) values 
(23,'returned','2024-01-13','2024-01-31',18,1),
(24,'accepted','2024-01-13','2024-01-26',18,5),
(29,'returned','2024-01-13','2024-01-26',18,3),
(30,'returned','2024-01-13','2024-01-23',18,1),
(31,'returned','2024-01-13','2024-01-23',20,1),
(39,'accepted','2024-01-13','2024-01-23',16,1),
(40,'accepted','2024-01-13','2024-01-23',18,1),
(42,'pending','2024-01-14','2024-01-14',13,1),
(43,'pending','2024-01-14','2024-01-14',18,1),
(44,'pending','2024-01-14','2024-01-14',20,1),
(45,'accepted','2024-01-20','2024-01-25',18,3),
(46,'accepted','2024-01-20','2024-01-25',18,3),
(47,'accepted','2024-01-20','2024-01-20',18,3),
(48,'accepted','2024-01-20','2024-01-25',20,3);

/*Table structure for table `libraryapp_plibrary` */

DROP TABLE IF EXISTS `libraryapp_plibrary`;

CREATE TABLE `libraryapp_plibrary` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `BOOK_id` bigint NOT NULL,
  `USER_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `libraryapp_plibrary_BOOK_id_d5164356_fk_libraryapp_books_id` (`BOOK_id`),
  KEY `libraryapp_plibrary_USER_id_25db1791_fk_libraryapp_user_table_id` (`USER_id`),
  CONSTRAINT `libraryapp_plibrary_BOOK_id_d5164356_fk_libraryapp_books_id` FOREIGN KEY (`BOOK_id`) REFERENCES `libraryapp_books` (`id`),
  CONSTRAINT `libraryapp_plibrary_USER_id_25db1791_fk_libraryapp_user_table_id` FOREIGN KEY (`USER_id`) REFERENCES `libraryapp_user_table` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `libraryapp_plibrary` */

insert  into `libraryapp_plibrary`(`id`,`date`,`BOOK_id`,`USER_id`) values 
(8,'2024-01-13',29,1),
(13,'2024-01-14',29,1);

/*Table structure for table `libraryapp_rating` */

DROP TABLE IF EXISTS `libraryapp_rating`;

CREATE TABLE `libraryapp_rating` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `rating` varchar(100) NOT NULL,
  `review` varchar(100) NOT NULL,
  `date` date NOT NULL,
  `USER_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `libraryapp_rating_USER_id_a0002d56_fk_libraryapp_user_table_id` (`USER_id`),
  CONSTRAINT `libraryapp_rating_USER_id_a0002d56_fk_libraryapp_user_table_id` FOREIGN KEY (`USER_id`) REFERENCES `libraryapp_user_table` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `libraryapp_rating` */

insert  into `libraryapp_rating`(`id`,`rating`,`review`,`date`,`USER_id`) values 
(1,'3.4','perfect','2023-11-14',1),
(2,'4.5','good','2023-11-23',2),
(3,'3.5','excellent','2024-01-13',1);

/*Table structure for table `libraryapp_report` */

DROP TABLE IF EXISTS `libraryapp_report`;

CREATE TABLE `libraryapp_report` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `reason` varchar(100) NOT NULL,
  `LIBRARY_id` bigint NOT NULL,
  `USER_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `libraryapp_report_LIBRARY_id_72b1abdd_fk_libraryap` (`LIBRARY_id`),
  KEY `libraryapp_report_USER_id_68caf5fe_fk_libraryapp_user_table_id` (`USER_id`),
  CONSTRAINT `libraryapp_report_LIBRARY_id_72b1abdd_fk_libraryap` FOREIGN KEY (`LIBRARY_id`) REFERENCES `libraryapp_library_table` (`id`),
  CONSTRAINT `libraryapp_report_USER_id_68caf5fe_fk_libraryapp_user_table_id` FOREIGN KEY (`USER_id`) REFERENCES `libraryapp_user_table` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `libraryapp_report` */

insert  into `libraryapp_report`(`id`,`reason`,`LIBRARY_id`,`USER_id`) values 
(1,'kjvhjkv',1,2),
(2,'jgcjkc',2,1);

/*Table structure for table `libraryapp_suggestion` */

DROP TABLE IF EXISTS `libraryapp_suggestion`;

CREATE TABLE `libraryapp_suggestion` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `bookname` varchar(100) NOT NULL,
  `author` varchar(100) NOT NULL,
  `date` date NOT NULL,
  `USER_id` bigint NOT NULL,
  `LIBRARY_id` bigint NOT NULL,
  `category` varchar(100) NOT NULL,
  `details` varchar(100) NOT NULL,
  `image` varchar(100) NOT NULL,
  `review` varchar(100) NOT NULL,
  `stock` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `libraryapp_suggestio_USER_id_60cb5125_fk_libraryap` (`USER_id`),
  KEY `libraryapp_suggestio_LIBRARY_id_f6f9a45c_fk_libraryap` (`LIBRARY_id`),
  CONSTRAINT `libraryapp_suggestio_LIBRARY_id_f6f9a45c_fk_libraryap` FOREIGN KEY (`LIBRARY_id`) REFERENCES `libraryapp_library_table` (`id`),
  CONSTRAINT `libraryapp_suggestio_USER_id_60cb5125_fk_libraryap` FOREIGN KEY (`USER_id`) REFERENCES `libraryapp_user_table` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `libraryapp_suggestion` */

insert  into `libraryapp_suggestion`(`id`,`bookname`,`author`,`date`,`USER_id`,`LIBRARY_id`,`category`,`details`,`image`,`review`,`stock`) values 
(1,'bhjbk','iubyu','2024-01-11',1,2,'uygvb','iubu','iubub','iyvyyu','3'),
(2,'awmfn','2jshe','2024-01-14',1,1,'eheb','wjebe','3378c755ef8384d3eddde0be8d74af0a_u94CtuH.jpg','sbebe','1'),
(3,'esajgn','wusve','2024-01-14',1,1,'sbebe','enebbe','pexels-birgit-held-1046125_9VVZ5ME.jpg','sbe r','1'),
(4,'segsk','isujs','2024-01-14',1,1,'shebs','whehe','ccb06a76cc56b397c57a7e001f7d6614_lDD904p.jpg','sjsbbd','1'),
(5,'gsegm','whsb','2024-01-14',1,1,'sbsbs','svsv','7f712d58d64dadce611882ae9617970d.jpg','sbs s','2'),
(6,'esgsg','eieje','2024-01-14',1,1,'snsbs','sbsbs','01ba5280ed11b9c00fc9a909374401bc_W5y9TUX.jpg','sns s','2'),
(7,'sggbs','sjsnsb','2024-01-14',1,1,'sbsbs','sbsbs','3378c755ef8384d3eddde0be8d74af0a_cDU1Mf0.jpg','sbsbs','2'),
(8,'ajajns','whwh','2024-01-14',1,1,'sjsns','sjsjs','ccb06a76cc56b397c57a7e001f7d6614_QfwqZA6.jpg','snsn','2'),
(9,'yr4vh','hhtdffy ','2024-01-14',1,1,'grrbb','bgtug','c19c8b036119b6ece0e6759d6c44d41d_2ddVWKG.jpg','bitgbj','3');

/*Table structure for table `libraryapp_user_table` */

DROP TABLE IF EXISTS `libraryapp_user_table`;

CREATE TABLE `libraryapp_user_table` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `fname` varchar(100) NOT NULL,
  `lname` varchar(100) NOT NULL,
  `gender` varchar(100) NOT NULL,
  `place` varchar(100) NOT NULL,
  `phoneno` bigint NOT NULL,
  `email` varchar(100) NOT NULL,
  `LOGIN_id` bigint NOT NULL,
  `profile` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `libraryapp_user_tabl_LOGIN_id_07f9635c_fk_libraryap` (`LOGIN_id`),
  CONSTRAINT `libraryapp_user_tabl_LOGIN_id_07f9635c_fk_libraryap` FOREIGN KEY (`LOGIN_id`) REFERENCES `libraryapp_login_table` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `libraryapp_user_table` */

insert  into `libraryapp_user_table`(`id`,`fname`,`lname`,`gender`,`place`,`phoneno`,`email`,`LOGIN_id`,`profile`) values 
(1,'shanu','navas','MALE','wda',234543,'wadad',3,'pexels-leeloo-thefirst-4945449.jpg'),
(2,'afra','pp','FEMALE','dwa',345453,'dvsav',9,'3b7b37f2b4c2ea7294460661a043d60d.jpg'),
(3,'Ghsh','Bxnjxn','FEMALE','Bxmxmxm',1234567890,'naveentjohn@gmail.com',8,'pexels-andrea-piacquadio-733872_KDJaCP6.jpg'),
(4,'Adithyan','pk','MALE','kadalundi',1234567890,'anabbsh',9,'pexels-ashish-sharma-917594.jpg'),
(5,'Athul','jose','MALE','Palakkad',6789012345,'ussjbs',10,'pexels-marlon-schmeiski-2915216.jpg');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
