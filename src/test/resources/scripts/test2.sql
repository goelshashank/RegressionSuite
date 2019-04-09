use pushcoretest;

DROP TABLE IF EXISTS `brand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `brand` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `code` varchar(255) NOT NULL,
  `is_shadowed` int(11) DEFAULT 0,
  `topic` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brand`
--

LOCK TABLES `brand` WRITE;
/*!40000 ALTER TABLE `brand` DISABLE KEYS */;
INSERT INTO `brand` VALUES
 (1,'2019-01-15 00:00:00','2019-01-15 00:00:00','VS',0,'VS_Brand_2'),(2,'2019-01-15
00:00:00','2019-01-15 00:00:00','M4',0,'M4_Brand_topic_test'),
(5,'2019-04-02 10:44:22','2019-04-02 10:44:22','VS_DR',0,'VS_Brand_2_test'),
(6,'2019-04-02 10:44:22','2019-04-02 10:44:22','VS_LBR',0,'VS_Brand_2_test'),(7,'2019-04-02 10:44:22',
'2019-04-02 10:44:22','ZZ_DR',0,'ZZ_Brand_2'),
(8,'2019-04-02 10:44:22','2019-04-02 10:44:22','ZZ_LBR',0,'ZZ_Brand_2'),
(3,'2019-04-03 00:00:00','2019-04-03 00:00:00','ZZ',0,'ZZ_Brand_2');
/*!40000 ALTER TABLE `brand` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `brand_subscription`
--

DROP TABLE IF EXISTS `brand_subscription`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `brand_subscription` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `is_shadowed` bit(1) NOT NULL,
  `updated_at` datetime NOT NULL,
  `brand_id` bigint(20) DEFAULT NULL,
  `channel_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKau8rb8m9938uo55v4fbad6y7b` (`brand_id`),
  KEY `FKe5kc93cr4mlcgj13igqx1k9nb` (`channel_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brand_subscription`
--

LOCK TABLES `brand_subscription` WRITE;
/*!40000 ALTER TABLE `brand_subscription` DISABLE KEYS */;
INSERT INTO `brand_subscription` VALUES (1,'2019-02-20 13:07:12','\0','2019-02-20 13:07:12',2,1),(2,'2019-02-20 13:07:12','\0','2019-02-20 13:07:12',1,2),(3,'2019-02-20 13:07:12','\0','2019-02-20 13:07:12',2,2);
/*!40000 ALTER TABLE `brand_subscription` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cassandra_config`
--

DROP TABLE IF EXISTS `cassandra_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cassandra_config` (
  `config_key` varchar(255) NOT NULL,
  `config_value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`config_key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cassandra_config`
--

LOCK TABLES `cassandra_config` WRITE;
/*!40000 ALTER TABLE `cassandra_config` DISABLE KEYS */;
/*!40000 ALTER TABLE `cassandra_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `channel`
--

DROP TABLE IF EXISTS `channel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `channel` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `channel_concurrency` int(11) NOT NULL DEFAULT 0,
  `channel_hotel_concurrency` int(11) NOT NULL DEFAULT 0,
  `consumer_count` int(11) NOT NULL DEFAULT 1,
  `name` varchar(255) NOT NULL,
  `is_shadowed` int(11) DEFAULT 0,
  `topic` varchar(255) DEFAULT NULL,
  `sga_code` varchar(255) DEFAULT NULL,
  `app_instances` int(11) NOT NULL DEFAULT 0,
  `is_public_rates_allowed` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `channel`
--

LOCK TABLES `channel` WRITE;
/*!40000 ALTER TABLE `channel` DISABLE KEYS */;
INSERT INTO `channel` VALUES
(1,'2019-01-15 00:00:00','2019-01-15 00:00:00',100,15,4,'RoyalArabians',1,'RoyalArabians_test','9z',1,0),
(2,'2019-01-15 00:00:00','2019-01-15 00:00:00',100,20,1,'Booking.com',0,'BookingDotCom2_test','01',1,1);
/*!40000 ALTER TABLE `channel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `channel_credential`
--

DROP TABLE IF EXISTS `channel_credential`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `channel_credential` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `password` varchar(255) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  `is_shadowed` int(11) DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `channel_credential`
--

LOCK TABLES `channel_credential` WRITE;
/*!40000 ALTER TABLE `channel_credential` DISABLE KEYS */;
INSERT INTO `channel_credential` VALUES (1,'2019-01-15 00:00:00','2019-01-15 00:00:00','rg@cityspeedtours','CitySpeedTours',0),(2,'2019-01-15 00:00:00','2019-01-15 00:00:00',')6^i?32(wSjw/lLs-.ghY.!)::euB)4itzX4:s^Y','RateGain-Test',0);
/*!40000 ALTER TABLE `channel_credential` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `channel_rezgain_supported_attributes`
--

DROP TABLE IF EXISTS `channel_rezgain_supported_attributes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `channel_rezgain_supported_attributes` (
  `channel_id` bigint(20) NOT NULL,
  `attribute_id` bigint(20) NOT NULL,
  PRIMARY KEY (`channel_id`,`attribute_id`),
  KEY `FK7aqc4kgn3opdj7vj87keimci8` (`attribute_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `channel_rezgain_supported_attributes`
--

LOCK TABLES `channel_rezgain_supported_attributes` WRITE;
/*!40000 ALTER TABLE `channel_rezgain_supported_attributes` DISABLE KEYS */;
INSERT INTO `channel_rezgain_supported_attributes` VALUES (2,1),(2,2),(2,3),(2,4),(2,5),(2,6),(2,7),(2,8),(2,9),(2,10);
/*!40000 ALTER TABLE `channel_rezgain_supported_attributes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `generic_config`
--

DROP TABLE IF EXISTS `generic_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `generic_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `max_threads_per_jvm` int(11) DEFAULT NULL,
  `rezgain_url` varchar(255) NOT NULL,
  `total_instances` int(11) DEFAULT NULL,
  `is_shadowed` int(11) DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `generic_config`
--

LOCK TABLES `generic_config` WRITE;
/*!40000 ALTER TABLE `generic_config` DISABLE KEYS */;
INSERT INTO `generic_config` VALUES
 (1,'2019-01-15 00:00:00','2019-01-15 00:00:00',200,'http://valptddevsre01a.asp.dhisco.com:8082/pctest/capture-rez-gain',1,0);
/*!40000 ALTER TABLE `generic_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `global`
--

DROP TABLE IF EXISTS `global`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `global` (
  `config_key` varchar(255) NOT NULL,
  `config_value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`config_key`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `global`
--

LOCK TABLES `global` WRITE;
/*!40000 ALTER TABLE `global` DISABLE KEYS */;
INSERT INTO `global` VALUES ('cassandra.consistency.level','ALL'),('cassandra.max.requests.per.connection','32768'),('cassandra.nodes','dhscaldevapp03a.asp.dhisco.com'),('cassandra.speculative.delay.ms','500'),('cassandra.speculative.execs','3'),('kafka.bootstrap.servers','10.215.34.220:9092,10.215.34.221:9092,10.215.34.222:9092'),('kafka.num.kafka.stream.threads','10'),('kafka.p2d.changes.topic.name','p2d_updates_final'),('kafka.producer.acks','all'),('kafka.producer.compression.type','snappy'),('kafka.producer.linger.ms','0'),('kafka.producer.retries','0'),('kafka.state.dir','./kafka-streams'),('zookeeper.address','10.215.34.223:2181,10.215.34.224:2181,10.215.34.225:2181');
/*!40000 ALTER TABLE `global` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hotel_property`
--

DROP TABLE IF EXISTS `hotel_property`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hotel_property` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `hotel_code` varchar(255) NOT NULL,
  `brand_id` bigint(20) DEFAULT NULL,
  `is_shadowed` int(11) DEFAULT 0,
  PRIMARY KEY (`id`),
  KEY `FK8kl956y0l0wrm4ceekif1ob2b` (`brand_id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotel_property`
--

LOCK TABLES `hotel_property` WRITE;
/*!40000 ALTER TABLE `hotel_property` DISABLE KEYS */;
INSERT INTO `hotel_property` VALUES (1,'2019-01-15 00:00:00','2019-01-15 00:00:00','3646',1,0),(2,'2019-01-15 00:00:00','2019-01-15 00:00:00','1300',2,0),(3,'2019-04-02 10:47:13','2019-04-02 10:47:13','3646',5,0),(4,'2019-04-02 10:47:13','2019-04-02 10:47:13','3646',6,0),(5,'2019-04-02 10:47:13','2019-04-02 10:47:13','9435',7,0),(6,'2019-04-02 10:47:13','2019-04-02 10:47:13','9435',8,0),(7,'2019-04-02 10:47:13','2019-04-02 10:47:13','6438',7,0),(8,'2019-04-02 10:47:13','2019-04-02 10:47:13','6438',8,0);
/*!40000 ALTER TABLE `hotel_property` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hotel_property_channel_mapping`
--

DROP TABLE IF EXISTS `hotel_property_channel_mapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hotel_property_channel_mapping` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `channel_hotel_code` varchar(255) NOT NULL,
  `channel_id` bigint(20) DEFAULT NULL,
  `hotel_property_id` bigint(20) DEFAULT NULL,
  `is_shadowed` int(11) DEFAULT 0,
  `supported_price_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKrl6ydt9q93jhkv1p46cxer84f` (`hotel_property_id`,`channel_id`),
  KEY `FKek6vn4iq4faps8d04xeq59ddv` (`channel_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotel_property_channel_mapping`
--

LOCK TABLES `hotel_property_channel_mapping` WRITE;
/*!40000 ALTER TABLE `hotel_property_channel_mapping` DISABLE KEYS */;
INSERT INTO `hotel_property_channel_mapping` VALUES (1,'2019-01-15 00:00:00','2019-01-15 00:00:00','3326096',2,1,0,NULL),(2,'2019-01-15 00:00:00','2019-01-15 00:00:00','3326095',2,2,0,NULL),(3,'2019-04-03 17:25:24','2019-04-03 17:25:24','3326095',2,3,0,NULL),(4,'2019-04-03 17:25:24','2019-04-03 17:25:24','3326096',2,4,0,NULL);
/*!40000 ALTER TABLE `hotel_property_channel_mapping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hotel_property_subscription`
--

DROP TABLE IF EXISTS `hotel_property_subscription`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hotel_property_subscription` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `is_shadowed` bit(1) NOT NULL,
  `updated_at` datetime NOT NULL,
  `channel_id` bigint(20) DEFAULT NULL,
  `hotel_property_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsuhxy2ibrwegstyyiga3g4t2j` (`channel_id`),
  KEY `FKm2ftn5md6ml400mftbxa7kblp` (`hotel_property_id`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotel_property_subscription`
--

LOCK TABLES `hotel_property_subscription` WRITE;
/*!40000 ALTER TABLE `hotel_property_subscription` DISABLE KEYS */;
INSERT INTO `hotel_property_subscription` VALUES (1,'2019-02-20 13:07:12','\0','2019-02-20 13:07:12',1,1),(4,'2019-03-12 05:41:55','\0','2019-03-12 05:41:55',2,1),(5,'2019-03-12 05:41:55','\0','2019-03-12 05:41:55',2,2),(6,'2019-04-02 10:50:12','\0','2019-04-02 10:50:12',2,3),(7,'2019-04-02 10:50:12','\0','2019-04-02 10:50:12',2,4),(8,'2019-04-02 10:50:12','\0','2019-04-02 10:50:12',2,5),(9,'2019-04-02 10:50:12','\0','2019-04-02 10:50:12',2,6),(10,'2019-04-02 10:50:12','\0','2019-04-02 10:50:12',2,7),(11,'2019-04-02 10:50:12','\0','2019-04-02 10:50:12',2,8);
/*!40000 ALTER TABLE `hotel_property_subscription` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inventory_code`
--

DROP TABLE IF EXISTS `inventory_code`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inventory_code` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `name` varchar(255) NOT NULL,
  `hotel_property_id` bigint(20) DEFAULT NULL,
  `is_shadowed` int(11) DEFAULT 0,
  PRIMARY KEY (`id`),
  KEY `FKq5h3pcviuyh4y9nfe6u4curnc` (`hotel_property_id`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventory_code`
--

LOCK TABLES `inventory_code` WRITE;
/*!40000 ALTER TABLE `inventory_code` DISABLE KEYS */;
INSERT INTO `inventory_code` VALUES (1,'2019-01-15 00:00:00','2019-01-15 00:00:00','N2D',1,0),(2,'2019-01-15 00:00:00','2019-01-15 00:00:00','B2T',2,0),(3,'2019-01-15 00:00:00','2019-01-15 00:00:00','B1D',2,0),(4,'2019-01-15 00:00:00','2019-01-15 00:00:00','D2T',2,0),(5,'2019-01-15 00:00:00','2019-01-15 00:00:00','N1K',1,0),(6,'2019-01-15 00:00:00','2019-01-15 00:00:00','A1K',1,0),(7,'2019-01-15 00:00:00','2019-01-15 00:00:00','A1K',2,0),(8,'2019-01-15 00:00:00','2019-01-15 00:00:00','C1K',3,0),(9,'2019-01-15 00:00:00','2019-01-15 00:00:00','S1K',1,0),(10,'2019-01-15 00:00:00','2019-01-15 00:00:00','S1K',2,0),(11,'2019-04-03 16:52:21','2019-04-03 16:52:21','A1K',3,0),(12,'2019-04-03 16:52:21','2019-04-03 16:52:21','A1K',4,0);
/*!40000 ALTER TABLE `inventory_code` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inventory_code_channel_mapping`
--

DROP TABLE IF EXISTS `inventory_code_channel_mapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inventory_code_channel_mapping` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `room_code` varchar(255) NOT NULL,
  `room_name` varchar(255) NOT NULL,
  `channel_id` bigint(20) DEFAULT NULL,
  `inventory_code_id` bigint(20) DEFAULT NULL,
  `is_shadowed` int(11) DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK4bcmw46bgi3a43aqcar1cj4ua` (`inventory_code_id`,`channel_id`),
  KEY `FK8n0q548kd77uafiv2g2j9y9u2` (`channel_id`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventory_code_channel_mapping`
--

LOCK TABLES `inventory_code_channel_mapping` WRITE;
/*!40000 ALTER TABLE `inventory_code_channel_mapping` DISABLE KEYS */;
INSERT INTO `inventory_code_channel_mapping` VALUES (7,'2019-01-15 00:00:00','2019-01-15 00:00:00','332609603','Double Room(332609003)',2,8,0),(10,'2019-01-15 00:00:00','2019-01-15 00:00:00','332609503','Standard Room(332609502)',2,3,0),(11,'2019-04-03 17:01:02','2019-04-03 17:01:02','332609503','Double Room(332609003)',2,11,0),(12,'2019-04-03 17:01:02','2019-04-03 17:01:02','332609603','Double Room(332609003)',2,12,0);
/*!40000 ALTER TABLE `inventory_code_channel_mapping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kafka_config`
--

DROP TABLE IF EXISTS `kafka_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kafka_config` (
  `config_key` varchar(255) NOT NULL,
  `config_value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`config_key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kafka_config`
--

LOCK TABLES `kafka_config` WRITE;
/*!40000 ALTER TABLE `kafka_config` DISABLE KEYS */;
/*!40000 ALTER TABLE `kafka_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `key_value_properties_entity`
--

DROP TABLE IF EXISTS `key_value_properties_entity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `key_value_properties_entity` (
  `config_key` varchar(255) NOT NULL,
  `config_value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`config_key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `key_value_properties_entity`
--

LOCK TABLES `key_value_properties_entity` WRITE;
/*!40000 ALTER TABLE `key_value_properties_entity` DISABLE KEYS */;
/*!40000 ALTER TABLE `key_value_properties_entity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `max_occ_for_rtc`
--

DROP TABLE IF EXISTS `max_occ_for_rtc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `max_occ_for_rtc` (
  `brand` varchar(255) NOT NULL,
  `rtc` varchar(255) NOT NULL,
  `maxocc` int(11) DEFAULT NULL,
  PRIMARY KEY (`brand`,`rtc`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `max_occ_for_rtc`
--

LOCK TABLES `max_occ_for_rtc` WRITE;
/*!40000 ALTER TABLE `max_occ_for_rtc` DISABLE KEYS */;
INSERT INTO `max_occ_for_rtc` VALUES ('ZZ','BLC',1),('ZZ','DXK',1),('ZZ','VST',1),('ZZ','2BD',1),('ZZ','VWS',1),('ZZ','AZK',1),('ZZ','AKS',1),('ZZ','1VK',1),('ZZ','TRV',1),('ZZ','SPS',1),('ZZ','TRR',1),('ZZ','TVW',1),('ZZ','A0A',1),('ZZ','LFK',1),('ZZ','A09',1),('ZZ','LLK',1),('ZZ','A08',1),('ZZ','A07',1),('ZZ','A05',1),('ZZ','SPA',1),('ZZ','KNG',1),('ZZ','A03',1),('ZZ','2BT',1),('ZZ','SAS',1),('ZZ','A02',1),('ZZ','2DT',1),('ZZ','A01',1),('ZZ','2DS',1),('ZZ','1DT',1),('ZZ','TER',1),('VS','C1K',1),('VS','S1K',1),('VS','A1K',1),('VS','N1K',1),('VS','N2D',1),('M4','C1K',1),('M4','S1K',1),('M4','A1K',1),('M4','N1K',1),('M4','N2D',1);
/*!40000 ALTER TABLE `max_occ_for_rtc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nrc`
--

DROP TABLE IF EXISTS `nrc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nrc` (
  `demand_partner` varchar(255) NOT NULL,
  `rpc` varchar(255) NOT NULL,
  `sga_name` varchar(255) NOT NULL,
  `ccn` varchar(255) DEFAULT NULL,
  `matching_qualifier` varchar(255) DEFAULT NULL,
  `rpi` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`demand_partner`,`rpc`,`sga_name`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nrc`
--

LOCK TABLES `nrc` WRITE;
/*!40000 ALTER TABLE `nrc` DISABLE KEYS */;
INSERT INTO `nrc` VALUES ('R2ARI','PEG','01',NULL,'REQUESTED_OR_PUBLIC','N'),('R2ARI','DA5','01',NULL,'REQUESTED_OR_PUBLIC','N'),('R2ARI','DLU','01',NULL,'REQUESTED_OR_PUBLIC','N'),('R2ARI','DER','01',NULL,'REQUESTED_OR_PUBLIC','N');
/*!40000 ALTER TABLE `nrc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `per_brand`
--

DROP TABLE IF EXISTS `per_brand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `per_brand` (
  `brand` varchar(255) NOT NULL,
  `demand_partner` varchar(255) NOT NULL,
  `sga_name` varchar(255) NOT NULL,
  `maxlos` int(11) DEFAULT NULL,
  `maxnrcs` int(11) DEFAULT NULL,
  `maxocc` int(11) DEFAULT NULL,
  `maxrooms` int(11) DEFAULT NULL,
  `rolling_update_interval` int(11) DEFAULT NULL,
  PRIMARY KEY (`brand`,`demand_partner`,`sga_name`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `per_brand`
--

LOCK TABLES `per_brand` WRITE;
/*!40000 ALTER TABLE `per_brand` DISABLE KEYS */;
INSERT INTO `per_brand` VALUES ('XN','R2ARI','01',7,15,2,2,30),('XM','R2ARI','01',7,15,2,2,0),('ZZ','R2ARI','01',7,15,1,1,0),('VS','R2ARI','01',7,15,2,2,0),('PU','R2ARI','01',7,15,2,2,0),('WY','R2ARI','01',7,15,1,1,0),('M4','R2ARI','01',7,15,2,2,0);
/*!40000 ALTER TABLE `per_brand` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `hotel_property_id` bigint(20) DEFAULT NULL,
  `inventory_code_id` bigint(20) DEFAULT NULL,
  `rate_plan_id` bigint(20) DEFAULT NULL,
  `is_shadowed` int(11) DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKtm5dc9oa4ii37akv4p6yf9y2w` (`hotel_property_id`,`rate_plan_id`,`inventory_code_id`),
  KEY `FK2k15cx0e767qayhm88bdx0uvd` (`inventory_code_id`),
  KEY `FKmhgb5hbpxn8naoewgdy5npv7p` (`rate_plan_id`)
) ENGINE=MyISAM AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'2019-01-15 00:00:00','2019-01-15 00:00:00',1,5,2,0),(2,'2019-01-15 00:00:00','2019-01-15 00:00:00',1,5,4,0),(3,'2019-01-15 00:00:00','2019-01-15 00:00:00',1,9,11,0),(4,'2019-01-15 00:00:00','2019-01-15 00:00:00',2,3,3,0),(5,'2019-01-15 00:00:00','2019-01-15 00:00:00',1,9,9,0),(6,'2019-01-15 00:00:00','2019-01-15 00:00:00',2,7,10,0),(7,'2019-01-15 00:00:00','2019-01-15 00:00:00',1,5,11,0),(8,'2019-01-15 00:00:00','2019-01-15 00:00:00',1,5,9,0),(9,'2019-01-15 00:00:00','2019-01-15 00:00:00',1,9,16,0),(10,'2019-01-15 00:00:00','2019-01-15 00:00:00',1,9,17,0),(11,'2019-01-15 00:00:00','2019-01-15 00:00:00',2,7,19,0),(12,'2019-01-15 00:00:00','2019-01-15 00:00:00',2,3,10,0),(13,'2019-01-15 00:00:00','2019-01-15 00:00:00',1,6,2,0),(14,'2019-01-15 00:00:00','2019-01-15 00:00:00',1,6,3,0),(15,'2019-01-15 00:00:00','2019-01-15 00:00:00',2,7,8,0),(16,'2019-01-15 00:00:00','2019-01-15 00:00:00',1,8,14,0),(17,'2019-01-15 00:00:00','2019-01-15 00:00:00',2,7,3,0),(18,'2019-01-15 00:00:00','2019-01-15 00:00:00',2,3,8,0),(19,'2019-01-15 00:00:00','2019-01-15 00:00:00',2,7,1,0),(20,'2019-01-15 00:00:00','2019-01-15 00:00:00',1,8,12,0),(21,'2019-01-15 00:00:00','2019-01-15 00:00:00',1,9,2,0),(22,'2019-01-15 00:00:00','2019-01-15 00:00:00',2,2,15,0),(23,'2019-01-15 00:00:00','2019-01-15 00:00:00',2,3,1,0),(24,'2019-01-15 00:00:00','2019-01-15 00:00:00',1,1,18,0),(25,'2019-01-15 00:00:00','2019-01-15 00:00:00',1,9,4,0),(26,'2019-01-15 00:00:00','2019-01-15 00:00:00',2,10,19,0),(27,'2019-01-15 00:00:00','2019-01-15 00:00:00',1,8,18,0),(28,'2019-01-15 00:00:00','2019-01-15 00:00:00',1,6,18,0),(29,'2019-01-15 00:00:00','2019-01-15 00:00:00',1,9,14,0),(30,'2019-01-15 00:00:00','2019-01-15 00:00:00',1,5,12,0),(31,'2019-01-15 00:00:00','2019-01-15 00:00:00',1,5,7,0),(32,'2019-01-15 00:00:00','2019-01-15 00:00:00',2,4,19,0),(33,'2019-01-15 00:00:00','2019-01-15 00:00:00',1,5,6,0),(34,'2019-01-15 00:00:00','2019-01-15 00:00:00',2,2,13,0),(35,'2019-01-15 00:00:00','2019-01-15 00:00:00',1,9,12,0),(36,'2019-01-15 00:00:00','2019-01-15 00:00:00',1,9,7,0),(37,'2019-01-15 00:00:00','2019-01-15 00:00:00',1,5,14,0),(38,'2019-01-15 00:00:00','2019-01-15 00:00:00',1,5,16,0),(39,'2019-01-15 00:00:00','2019-01-15 00:00:00',1,9,18,0),(40,'2019-01-15 00:00:00','2019-01-15 00:00:00',2,2,5,0),(41,'2019-01-15 00:00:00','2019-01-15 00:00:00',1,5,17,0),(42,'2019-01-15 00:00:00','2019-01-15 00:00:00',1,5,18,0),(43,'2019-01-15 00:00:00','2019-01-15 00:00:00',2,3,19,0),(44,'2019-01-15 00:00:00','2019-01-15 00:00:00',1,5,20,0),(45,'2019-01-15 00:00:00','2019-01-15 00:00:00',1,9,20,0),(46,'2019-03-19 12:59:13','2019-03-19 12:59:13',1,8,23,0),(47,'2019-04-03 17:42:37','2019-04-03 17:42:37',4,12,24,0),(48,'2019-04-04 05:55:53','2019-04-04 05:55:53',3,11,23,0);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_subscription`
--

DROP TABLE IF EXISTS `product_subscription`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_subscription` (
  `channel_id` bigint(20) NOT NULL,
  `product_id` bigint(20) NOT NULL,
  PRIMARY KEY (`channel_id`,`product_id`),
  KEY `FKb8vxhi4gh0hed2mm02maxg6yn` (`product_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_subscription`
--

LOCK TABLES `product_subscription` WRITE;
/*!40000 ALTER TABLE `product_subscription` DISABLE KEYS */;
INSERT INTO `product_subscription` VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(1,9),(1,10),(1,11),(1,12),(1,13),(1,14),(1,15),(1,16),(1,17),(1,18),(1,19),(1,20),(1,21),(1,22),(1,23),(1,24),(1,25),(1,26),(1,27),(1,28),(1,29),(1,30),(1,31),(1,32),(1,33),(1,34),(1,35),(1,36),(1,37),(1,38),(1,39),(1,40),(1,41),(1,42),(1,43),(1,44),(1,45),(2,1),(2,2),(2,3),(2,4),(2,5),(2,6),(2,7),(2,8),(2,9),(2,10),(2,11),(2,12),(2,13),(2,14),(2,15),(2,16),(2,17),(2,18),(2,19),(2,20),(2,21),(2,22),(2,23),(2,24),(2,25),(2,26),(2,27),(2,28),(2,29),(2,30),(2,31),(2,32),(2,33),(2,34),(2,35),(2,36),(2,37),(2,38),(2,39),(2,40),(2,41),(2,42),(2,43),(2,44),(2,45);
/*!40000 ALTER TABLE `product_subscription` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `products` (
  `brand` varchar(255) NOT NULL,
  `pid` varchar(255) NOT NULL,
  `rpc` varchar(255) NOT NULL,
  `rtc` varchar(255) NOT NULL,
  `rrp` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`brand`,`pid`,`rpc`,`rtc`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES ('M4','1300','1BO','N1K','DA5'),('M4','1300','1BO','S1K','DA5'),('M4','1300','3DM','A1K','PEG'),('M4','1300','3DM','N1K','PEG'),('M4','1300','3DM','S1K','PEG'),('M4','1300','BVW','A1K','DLU'),('M4','1300','BVW','C1K','DLU'),('M4','1300','BVW','N1K','DLU'),('M4','1300','BVW','N2D','DLU'),('M4','1300','BVW','S1K','DLU'),('M4','1300','D8R','N1K','DER'),('M4','1300','D8R','S1K','DER'),('VS','3646','1BO','N1K','DA5'),('VS','3646','1BO','S1K','DA5'),('VS','3646','3DM','A1K','PEG'),('VS','3646','3DM','N1K','PEG'),('VS','3646','3DM','S1K','PEG'),('VS','3646','BVW','A1K','DLU'),('VS','3646','BVW','C1K','DLU'),('VS','3646','BVW','N1K','DLU'),('VS','3646','BVW','N2D','DLU'),('VS','3646','BVW','S1K','DLU'),('VS','3646','D8R','N1K','DER'),('VS','3646','D8R','S1K','DER'),('ZZ','6438','A07','A01',NULL),('ZZ','6438','A07','A02',NULL),('ZZ','6438','A07','A03',NULL),('ZZ','6438','A07','A05',NULL),('ZZ','6438','A07','A07',NULL),('ZZ','6438','A07','A08',NULL),('ZZ','6438','A07','A09',NULL),('ZZ','6438','A07','A0A',NULL),('ZZ','6438','A5J','A01',NULL),('ZZ','6438','A5J','A02',NULL),('ZZ','6438','A5J','A03',NULL),('ZZ','6438','A5J','A05',NULL),('ZZ','6438','A5J','A07',NULL),('ZZ','6438','A5J','A08',NULL),('ZZ','6438','A5J','A09',NULL),('ZZ','6438','A5J','A0A',NULL),('ZZ','9435','ADPR','1DT',NULL),('ZZ','9435','ADPR','1VK',NULL),('ZZ','9435','ADPR','2DS',NULL),('ZZ','9435','ADPR','2DT',NULL),('ZZ','9435','ADPR','AKS',NULL),('ZZ','9435','ADPR','AZK',NULL),('ZZ','9435','ADPR','BLC',NULL),('ZZ','9435','ADPR','DXK',NULL),('ZZ','9435','ADPR','KNG',NULL),('ZZ','9435','ADPR','LFK',NULL),('ZZ','9435','ADPR','SPS',NULL),('ZZ','9435','ADPR','TRR',NULL),('ZZ','9435','ADPR','TVW',NULL),('ZZ','9435','ADPR','VST',NULL),('ZZ','9435','FRX4','2DS',NULL),('ZZ','9435','FRX4','2DT',NULL),('ZZ','9435','FRX4','TRR',NULL),('ZZ','9435','FRX4','TVW',NULL),('ZZ','9435','FRX4','VWS',NULL),('ZZ','9435','N10V','DXK',NULL),('ZZ','9435','PAWA','1DT',NULL),('ZZ','9435','PAWA','1VK',NULL),('ZZ','9435','PAWA','AKS',NULL),('ZZ','9435','PAWA','AZK',NULL),('ZZ','9435','PAWA','BLC',NULL),('ZZ','9435','PAWA','DXK',NULL),('ZZ','9435','PAWA','KNG',NULL),('ZZ','9435','PAWA','LLK',NULL),('ZZ','9435','PAWA','SAS',NULL),('ZZ','9435','PAWA','TRR',NULL),('ZZ','9435','PAWA','TVW',NULL),('ZZ','9435','PAWA','VST',NULL),('ZZ','9435','RACK','1DT',NULL),('ZZ','9435','RACK','1VK',NULL),('ZZ','9435','RACK','2DS',NULL),('ZZ','9435','RACK','2DT',NULL),('ZZ','9435','RACK','AKS',NULL),('ZZ','9435','RACK','AZK',NULL),('ZZ','9435','RACK','BLC',NULL),('ZZ','9435','RACK','KNG',NULL),('ZZ','9435','RACK','LLK',NULL),('ZZ','9435','RACK','SAS',NULL),('ZZ','9435','RACK','TRR',NULL),('ZZ','9435','RACK','TVW',NULL),('ZZ','9435','RACK','VST',NULL),('ZZ','9435','STXP','2BD',NULL),('ZZ','9435','STXP','2BT',NULL),('ZZ','9435','STXP','AZK',NULL),('ZZ','9435','STXP','BLC',NULL),('ZZ','9435','STXP','LFK',NULL),('ZZ','9435','STXP','SPA',NULL),('ZZ','9435','STXP','TER',NULL),('ZZ','9435','STXP','TRV',NULL),('ZZ','9435','STXP','VST',NULL);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `property_channel_credential_mapping`
--

DROP TABLE IF EXISTS `property_channel_credential_mapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `property_channel_credential_mapping` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `channel_id` bigint(20) DEFAULT NULL,
  `channel_credential_id` bigint(20) DEFAULT NULL,
  `hotel_property_id` bigint(20) DEFAULT NULL,
  `is_shadowed` int(11) DEFAULT 0,
  PRIMARY KEY (`id`),
  KEY `FKdlxs74smee79bocv0uux54b9y` (`channel_id`),
  KEY `FK6k05occ713w3v6pdf0knt8ayp` (`channel_credential_id`),
  KEY `FKk72io5essa8io7y8oncr5p7y3` (`hotel_property_id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `property_channel_credential_mapping`
--

LOCK TABLES `property_channel_credential_mapping` WRITE;
/*!40000 ALTER TABLE `property_channel_credential_mapping` DISABLE KEYS */;
INSERT INTO `property_channel_credential_mapping` VALUES (1,'2019-01-15 00:00:00','2019-01-15 00:00:00',1,1,1,0),(2,'2019-01-15 00:00:00','2019-01-15 00:00:00',1,1,2,0),(3,'2019-01-15 00:00:00','2019-01-15 00:00:00',1,2,1,0),(4,'2019-01-15 00:00:00','2019-01-15 00:00:00',1,2,2,0),(5,'2019-01-15 00:00:00','2019-01-15 00:00:00',2,2,1,0),(6,'2019-01-15 00:00:00','2019-01-15 00:00:00',2,2,2,0),(7,'2019-04-03 17:30:36','2019-04-03 17:30:36',2,2,3,0),(8,'2019-04-03 17:30:36','2019-04-03 17:30:36',2,2,4,0);
/*!40000 ALTER TABLE `property_channel_credential_mapping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quartz_properties`
--

DROP TABLE IF EXISTS `quartz_properties`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quartz_properties` (
  `config_key` varchar(255) NOT NULL,
  `config_value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`config_key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quartz_properties`
--

LOCK TABLES `quartz_properties` WRITE;
/*!40000 ALTER TABLE `quartz_properties` DISABLE KEYS */;
/*!40000 ALTER TABLE `quartz_properties` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rate_plan`
--

DROP TABLE IF EXISTS `rate_plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rate_plan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `name` varchar(255) NOT NULL,
  `hotel_property_id` bigint(20) DEFAULT NULL,
  `is_shadowed` int(11) DEFAULT 0,
  PRIMARY KEY (`id`),
  KEY `FKiddw1pq54j6942s53naqg31uo` (`hotel_property_id`)
) ENGINE=MyISAM AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rate_plan`
--

LOCK TABLES `rate_plan` WRITE;
/*!40000 ALTER TABLE `rate_plan` DISABLE KEYS */;
INSERT INTO `rate_plan` VALUES (1,'2019-01-15 00:00:00','2019-01-15 00:00:00','PVG',2,0),(2,'2019-01-15 00:00:00','2019-01-15 00:00:00','RA1',1,0),(3,'2019-01-15 00:00:00','2019-01-15 00:00:00','RA1',2,0),(4,'2019-01-15 00:00:00','2019-01-15 00:00:00','RA3',1,0),(5,'2019-01-15 00:00:00','2019-01-15 00:00:00','RA3',2,0),(6,'2019-01-15 00:00:00','2019-01-15 00:00:00','0VO',1,0),(7,'2019-01-15 00:00:00','2019-01-15 00:00:00','1KD',1,0),(8,'2019-01-15 00:00:00','2019-01-15 00:00:00','1KD',2,0),(9,'2019-01-15 00:00:00','2019-01-15 00:00:00','MNL',1,0),(10,'2019-01-15 00:00:00','2019-01-15 00:00:00','MNL',2,0),(11,'2019-01-15 00:00:00','2019-01-15 00:00:00','PKE',1,0),(12,'2019-01-15 00:00:00','2019-01-15 00:00:00','3KD',1,0),(13,'2019-01-15 00:00:00','2019-01-15 00:00:00','3KD',2,0),(14,'2019-01-15 00:00:00','2019-01-15 00:00:00','K03',1,0),(15,'2019-01-15 00:00:00','2019-01-15 00:00:00','K03',2,0),(16,'2019-01-15 00:00:00','2019-01-15 00:00:00','R4T',1,0),(17,'2019-01-15 00:00:00','2019-01-15 00:00:00','O16',1,0),(18,'2019-01-15 00:00:00','2019-01-15 00:00:00','RDI',1,0),(19,'2019-01-15 00:00:00','2019-01-15 00:00:00','RDI',2,0),(20,'2019-01-15 00:00:00','2019-01-15 00:00:00','1BK',1,0),(21,'2019-03-19 11:25:18','2019-03-19 11:25:18','D8R',1,0),(23,'2019-03-19 12:39:46','2019-03-19 12:39:46','BVW',3,0),(24,'2019-04-03 16:49:29','2019-04-03 16:49:29','BVW',4,0);
/*!40000 ALTER TABLE `rate_plan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rate_plan_channel_mapping`
--

DROP TABLE IF EXISTS `rate_plan_channel_mapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rate_plan_channel_mapping` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `rate_code` varchar(255) NOT NULL,
  `rate_name` varchar(255) NOT NULL,
  `channel_id` bigint(20) DEFAULT NULL,
  `rate_plan_id` bigint(20) DEFAULT NULL,
  `is_shadowed` int(11) DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKqbfdp792jr5aiqts58kx3x2oe` (`rate_plan_id`,`channel_id`),
  KEY `FK5mshgjthageffse2q6tr4xkr` (`channel_id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rate_plan_channel_mapping`
--

LOCK TABLES `rate_plan_channel_mapping` WRITE;
/*!40000 ALTER TABLE `rate_plan_channel_mapping` DISABLE KEYS */;
INSERT INTO `rate_plan_channel_mapping` VALUES (4,'2019-01-15 00:00:00','2019-01-15 00:00:00','11300248','Standard ratepan',2,18,0),(5,'2019-01-15 00:00:00','2019-01-15 00:00:00','11300247','Standard rateplan',2,19,0),(6,'2019-03-19 11:43:55','2019-03-19 11:43:55','11300248','Basic rateplan',2,21,0),(7,'2019-03-19 12:40:34','2019-03-19 12:40:34','11300247','Test rateplan',2,23,0),(8,'2019-04-03 16:58:20','2019-04-03 16:58:20','11300248','Test rateplan2',2,24,0);
/*!40000 ALTER TABLE `rate_plan_channel_mapping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `renderedtoari`
--

DROP TABLE IF EXISTS `renderedtoari`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `renderedtoari` (
  `config_key` varchar(255) NOT NULL,
  `config_value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`config_key`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `renderedtoari`
--

LOCK TABLES `renderedtoari` WRITE;
/*!40000 ALTER TABLE `renderedtoari` DISABLE KEYS */;
INSERT INTO `renderedtoari` VALUES ('ari.interpreter.book.endpoint','http://localhost:8080/interpret/OTA_HotelBookingRuleNotifRQ'),('ari.interpreter.inventory.endpoint','http://localhost:8080/interpret/OTA_HotelInvCountNotifRQ'),('ari.interpreter.rate.endpoint','http://localhost:8080/interpret/OTA_HotelRateAmountNotifRQ'),('kafka.application.id','rendered-to-ari'),('kafka.client.id','rendered-to-ari-client'),('kafka.number.of.stream.threads','100'),('log4j.appender.file','org.apache.log4j.DailyRollingFileAppender'),('log4j.appender.file.Append','true'),('log4j.appender.file.File','./rendered2ari.log'),('log4j.appender.file.layout','org.apache.log4j.PatternLayout'),('log4j.appender.file.layout.conversionPattern','%d{yyyy-MM-dd HH:mm:ss.SSS} [%c] [%p] [%t] %m%n'),('log4j.appender.stdout','org.apache.log4j.ConsoleAppender'),('log4j.appender.stdout.layout','org.apache.log4j.PatternLayout'),('log4j.appender.stdout.Target','System.out'),('log4j.rootLogger','INFO, stdout, file'),('push.interval','30'),('rendered.admin.topic','rendered-admin-topic'),('rendered.persistence.cassandra.keyspace','rendered2ari'),('rendered.shop.topic','rendered-shop-topic'),('test.adapter.name','test-adapter');
/*!40000 ALTER TABLE `renderedtoari` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rezgain_channel_mapping`
--

DROP TABLE IF EXISTS `rezgain_channel_mapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rezgain_channel_mapping` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `rezgain_channel_id` bigint(20) DEFAULT NULL,
  `rezgain_channel_name` varchar(255) DEFAULT NULL,
  `channel_id` bigint(20) DEFAULT NULL,
  `is_shadowed` int(11) DEFAULT 0,
  PRIMARY KEY (`id`),
  KEY `FKk9a4iw689ebl7j2q8stinbpnf` (`channel_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rezgain_channel_mapping`
--

LOCK TABLES `rezgain_channel_mapping` WRITE;
/*!40000 ALTER TABLE `rezgain_channel_mapping` DISABLE KEYS */;
INSERT INTO `rezgain_channel_mapping` VALUES (1,'2019-01-15 00:00:00','2019-01-15 00:00:00',221,'Booking.com',2,0),(2,'2019-01-15 00:00:00','2019-01-15 00:00:00',1145,'CitySpeed Tours',1,0);
/*!40000 ALTER TABLE `rezgain_channel_mapping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rezgain_update_attribute`
--

DROP TABLE IF EXISTS `rezgain_update_attribute`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rezgain_update_attribute` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `is_shadowed` int(11) DEFAULT 0,
  `updated_at` datetime NOT NULL,
  `attribute_id` int(11) NOT NULL,
  `attribute_name` varchar(255) NOT NULL,
  `attribute_type_id` int(11) NOT NULL,
  `attribute_type_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rezgain_update_attribute`
--

LOCK TABLES `rezgain_update_attribute` WRITE;
/*!40000 ALTER TABLE `rezgain_update_attribute` DISABLE KEYS */;
INSERT INTO `rezgain_update_attribute` VALUES (1,'2019-01-15 00:00:00',0,'2019-01-15 00:00:00',1,'Allocation',1,'Setting'),(2,'2019-01-15 00:00:00',0,'2019-01-15 00:00:00',49,'Available',1,'Setting'),(3,'2019-01-15 00:00:00',0,'2019-01-15 00:00:00',7,'CTA',2,'Restriction'),(4,'2019-01-15 00:00:00',0,'2019-01-15 00:00:00',8,'CTD',2,'Restriction'),(5,'2019-01-15 00:00:00',0,'2019-01-15 00:00:00',11,'MaxLOS',2,'Restriction'),(6,'2019-01-15 00:00:00',0,'2019-01-15 00:00:00',12,'MinLOS',2,'Restriction'),(7,'2019-01-15 00:00:00',0,'2019-01-15 00:00:00',13,'MinLOSThrough',2,'Restriction'),(8,'2019-01-15 00:00:00',0,'2019-01-15 00:00:00',61,'MaxLOSThrough',2,'Restriction'),(9,'2019-01-15 00:00:00',0,'2019-01-15 00:00:00',101,'FullOccupancy',3,'OcuupancyPrice'),(10,'2019-01-15 00:00:00',0,'2019-01-15 00:00:00',102,'1Adult',3,'OcuupancyPrice');
/*!40000 ALTER TABLE `rezgain_update_attribute` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scheduler`
--

DROP TABLE IF EXISTS `scheduler`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `scheduler` (
  `config_key` varchar(255) NOT NULL,
  `config_value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`config_key`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scheduler`
--

LOCK TABLES `scheduler` WRITE;
/*!40000 ALTER TABLE `scheduler` DISABLE KEYS */;
INSERT INTO `scheduler` VALUES ('cassandra.keyspace','stp'),('chunk.max.num.adults','2'),('chunk.max.num.checkin.dates','1'),('chunk.max.num.los','7'),('chunk.max.num.pids','1'),('chunk.max.num.rooms','2'),('chunking.config.name','standard'),('instance_id','AUTO'),('instance_name','ShoppingScheduler'),('jobStore_class','org.quartz.simpl.RAMJobStore'),('jobStore_misfireThreshold','60000'),('kafka.admin.topic.name','stp-admin-scheduler'),('kafka.application.id','scheduler'),('kafka.cds.topic.name','stp-cds'),('kafka.debezium.database.server.name','configdb'),('kafka.shopping.topic.name','stp-shopping'),('log4j.appender.app_appender','org.apache.log4j.DailyRollingFileAppender'),('log4j.appender.app_appender.Append','TRUE'),('log4j.appender.app_appender.BufferedIO','FALSE'),('log4j.appender.app_appender.DatePattern','.yyyy-MM-dd'),('log4j.appender.app_appender.file','./scheduler.log'),('log4j.appender.app_appender.ImmediateFlush','TRUE'),('log4j.appender.app_appender.layout','org.apache.log4j.PatternLayout'),('log4j.appender.app_appender.layout.conversionPattern','%d{yyyy-MM-dd HH:mm:ss.SSS} [%c] [%p] [%t] %m%n'),('log4j.rootLogger','INFO, app_appender'),('max.shopping.interval','365'),('plugins_triggerHistroryClass','org.quartz.plugins.history.LoggingJobHistoryPlugin'),('skip_update_check','TRUE'),('thread_pool_class','org.quartz.simpl.SimpleThreadPool'),('thread_pool_threadCount','3'),('thread_pool_threadPriority','5');
/*!40000 ALTER TABLE `scheduler` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scheduler_common_config`
--

DROP TABLE IF EXISTS `scheduler_common_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `scheduler_common_config` (
  `config_key` varchar(255) NOT NULL,
  `config_value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`config_key`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scheduler_common_config`
--

LOCK TABLES `scheduler_common_config` WRITE;
/*!40000 ALTER TABLE `scheduler_common_config` DISABLE KEYS */;
/*!40000 ALTER TABLE `scheduler_common_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shoppingEngine`
--

DROP TABLE IF EXISTS `shoppingEngine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shoppingEngine` (
  `config_key` varchar(255) NOT NULL,
  `config_value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`config_key`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shoppingEngine`
--

LOCK TABLES `shoppingEngine` WRITE;
/*!40000 ALTER TABLE `shoppingEngine` DISABLE KEYS */;
INSERT INTO `shoppingEngine` VALUES ('cassandra.keyspace','stp'),('hcd.hotel.info.rest.service.url','http://dhscaluatglf02e.asp.dhisco.com:28080/hdc4stp/rest/content/hotelBasicInfo?brand={brand}&pid={pid}'),('hcd.rest.service.max.retries','3'),('hcd.room.descriptions.rest.service.url','http://dhscaluatglf02e.asp.dhisco.com:28080/hdc4stp/rest/content/roomDesc?brand={brand}&pid={pid}&rtcs={rtcs}'),('kafka.admin.topic.name','stp-admin-engine'),('kafka.appender.ConversionPattern','%d{yyyy-MM-dd HH:mm:ss.SSS} [%c] [%5p] [%t] [%x]  (%F:%M:%L)  %m%n'),('kafka.appender.kafkaAppender','org.apache.kafka.log4jappender.KafkaLog4jAppender'),('kafka.appender.kafkaAppender.BrokerList','10.215.34.220:9092,10.215.34.221:9092,10.215.34.222:9092'),('kafka.appender.kafkaAppender.syncSend','FALSE'),('kafka.appender.kafkaAppender.Topic','s2p-error-log'),('kafka.application.id','stp'),('kafka.client.id','stp-client'),('kafka.error.logger','INFO, kafkaAppender'),('kafka.num.stream.threads','10'),('kafka.resend.topic.name','stp-resend'),('kafka.shopping.topic.name','stp-shopping'),('log4j.appender.app_appender','org.apache.log4j.DailyRollingFileAppender'),('log4j.appender.app_appender.Append','TRUE'),('log4j.appender.app_appender.BufferedIO','FALSE'),('log4j.appender.app_appender.DatePattern','.yyyy-MM-dd'),('log4j.appender.app_appender.file','./shopping-engine.log'),('log4j.appender.app_appender.ImmediateFlush','TRUE'),('log4j.appender.app_appender.layout','org.apache.log4j.PatternLayout'),('log4j.appender.app_appender.layout.conversionPattern','%d{yyyy-MM-dd HH:mm:ss.SSS} [%c] [%p] [%t] %m%n'),('log4j.appender.stats_appender','org.apache.log4j.DailyRollingFileAppender'),('log4j.appender.stats_appender.Append','TRUE'),('log4j.appender.stats_appender.BufferedIO','FALSE'),('log4j.appender.stats_appender.DatePattern','.yyyy-MM-dd'),('log4j.appender.stats_appender.file','./shopping-engine_stats.log'),('log4j.appender.stats_appender.ImmediateFlush','TRUE'),('log4j.appender.stats_appender.layout','org.apache.log4j.PatternLayout'),('log4j.appender.stats_appender.layout.conversionPattern','%d{yyyy-MM-dd HH:mm:ss.SSS} [%c] [%5p] [%t] [%x]  %m%n'),('log4j.appender.trans_appender','org.apache.log4j.DailyRollingFileAppender'),('log4j.appender.trans_appender.Append','TRUE'),('log4j.appender.trans_appender.BufferedIO','TRUE'),('log4j.appender.trans_appender.DatePattern','.yyyy-MM-dd'),('log4j.appender.trans_appender.file','./shopping-engine_trans.log'),('log4j.appender.trans_appender.ImmediateFlush','FALSE'),('log4j.appender.trans_appender.layout','org.apache.log4j.PatternLayout'),('log4j.appender.trans_appender.layout.conversionPattern','%d{yyyy-MM-dd HH:mm:ss.SSS} [%c{1}] [%p] [%t] [%m]%n'),('log4j.rootLogger','INFO, app_appender, trans_appender, stats_appender'),('pid.service.url','http://dhscaluatglf02e.asp.dhisco.com:28080/hdc4stp/rest/content/allPids?brand={brand}'),('pw.board.type.matching.AI','ALLINC'),('pw.board.type.matching.BB','BRKFST|BRKBUF|CNTBRK|COMBRK|FEBBRK|BB|BP|CB'),('pw.board.type.matching.DO','DINNER|COMPDN'),('pw.board.type.matching.FB','FAP'),('pw.board.type.matching.HB','MAP'),('pw.board.type.matching.LO','LUNCH|COMPLN'),('pw.board.type.matching.RO','EP'),('pw.default.biggest.max.occ','7'),('pw.default.channel.group.code','PW default SGA code'),('pw.default.max.occupancy','2'),('pw.default.separator',';'),('pw.dp.cache.ttl.seconds','4320000'),('pw.error.message.ttl','172800'),('pw.json.rest.url','http://213.131.248.205:1253'),('pw.max.paying.person.age','255'),('pw.min.child.age','0'),('pw.min.paying.person.age','13'),('pw.rest.template.read.timeout','30000'),('pw.room.type.matching.regex.AP','(?i)Apartment'),('pw.room.type.matching.regex.DL','(?i)(Deluxe|Dlx|Premium|Luxury|VIP|Royal|Presidential|Majestic)'),('pw.room.type.matching.regex.DP','(?i)Duplex'),('pw.room.type.matching.regex.DR','(?i)(Double|Dbl|King|Kng|Queen|Qn)'),('pw.room.type.matching.regex.ER','(?i)(Economy|Small|Basic)'),('pw.room.type.matching.regex.FR','(?i)Family'),('pw.room.type.matching.regex.HA','(?i)(Accessible|Physical|Physically|Handicap|Handicapped|Mobility)'),('pw.room.type.matching.regex.JS','(?i)(Junior Suite|JR Suite|JrSuite)'),('pw.room.type.matching.regex.SP','(?i)(Superior|Sup|Executive|Exec|Concierge|Club|Lounge Access)'),('pw.room.type.matching.regex.SR','(?i)(1 Single|One Single|Sgl|1 Sgl|1 person)'),('pw.room.type.matching.regex.ST','(?i)Studio'),('pw.room.type.matching.regex.SU','(?i)(Suite|Bedroom)'),('pw.room.type.matching.regex.TR','(?i)Triple'),('pw.room.type.matching.regex.VI','(?i)Villa'),('pw.store.jsons','false'),('r2ari.rendered.shop.topic','rendered-shop-topic'),('stp.application.id.znode','/stp/application_id'),('stp.shopping.delay.decrement','10'),('stp.shopping.delay.initial','1000'),('stp.shopping.delay.max','2500'),('stp.shopping.delay.multiplier','50'),('stp.shopping.delay.useZooKeeper','false'),('stp.shopping.delay.znode','/stp/shopping-read-delay'),('ud.resend.chunk.interval','300000'),('ud.url','http://dhscalqalglf01c.asp.dhisco.com:38080/apps/TransactionInterfaceV1_1/DispatcherServlet'),('usw.back.pressure.error.codes','SYS81,SYS82,SYS83,SYS84,SYS89,SYS90'),('usw.concurrent.consumers','5'),('usw.concurrent.consumers.max','15'),('usw.connection.concurrent.request.sessions','10'),('usw.connection.imqAddressList','10.215.50.50:10400'),('usw.connection.imqAddressListBehavior','RANDOM'),('usw.connection.imqDefaultPassword','guest'),('usw.connection.imqDefaultUsername','guest'),('usw.request.destination','UswOtaRqst_S2P'),('usw.request.timeout','100000'),('usw.response.destination','UswOtaResp_S2P'),('usw.rq.demand.partner.code','PW'),('usw.rq.header.gds','WB'),('usw.rq.header.iat','018612'),('usw.rq.header.type','A');
/*!40000 ALTER TABLE `shoppingEngine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `suppliers_config`
--

DROP TABLE IF EXISTS `suppliers_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `suppliers_config` (
  `brand` varchar(255) NOT NULL,
  `missing_data_treshold` int(11) DEFAULT NULL,
  `optimistic_approach` bit(1) DEFAULT NULL,
  `supports_ctd` bit(1) DEFAULT NULL,
  `supports_fplos` bit(1) DEFAULT NULL,
  PRIMARY KEY (`brand`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `suppliers_config`
--

LOCK TABLES `suppliers_config` WRITE;
/*!40000 ALTER TABLE `suppliers_config` DISABLE KEYS */;
INSERT INTO `suppliers_config` VALUES ('ZZ',3,'\0','\0',''),('HY',NULL,'\0','\0','\0'),('VS',NULL,'\0','',''),('XN',3,'\0','','');
/*!40000 ALTER TABLE `suppliers_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supply_rule`
--

DROP TABLE IF EXISTS `supply_rule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `supply_rule` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `is_shadowed` bit(1) NOT NULL,
  `updated_at` datetime NOT NULL,
  `brand_code` varchar(255) DEFAULT NULL,
  `input_value` varchar(255) DEFAULT NULL,
  `supply_rule_definition_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcui0yp27d2nea5hoybjm36lub` (`supply_rule_definition_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supply_rule`
--

LOCK TABLES `supply_rule` WRITE;
/*!40000 ALTER TABLE `supply_rule` DISABLE KEYS */;
INSERT INTO `supply_rule` VALUES (1,'2019-02-19 13:07:12','\0','2019-02-19 13:07:12','VS','20.0',1),(2,'2019-02-19 13:07:12','\0','2019-02-19 13:07:12','VS','0',2),(3,'2019-02-19 13:07:12','\0','2019-02-19 13:07:12','VS','0',3);
/*!40000 ALTER TABLE `supply_rule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supply_rule_definition`
--

DROP TABLE IF EXISTS `supply_rule_definition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `supply_rule_definition` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `is_shadowed` bit(1) NOT NULL,
  `updated_at` datetime NOT NULL,
  `input_type` varchar(255) DEFAULT NULL,
  `input_value` varchar(255) DEFAULT NULL,
  `is_generic` bit(1) DEFAULT NULL,
  `rule_code` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supply_rule_definition`
--

LOCK TABLES `supply_rule_definition` WRITE;
/*!40000 ALTER TABLE `supply_rule_definition` DISABLE KEYS */;
INSERT INTO `supply_rule_definition` VALUES (1,'2019-02-19 13:07:12','\0','2019-02-19 13:07:12','TAX_PERCENTAGE','0','\0','APPLY_TAX_RULE'),(2,'2019-02-19 13:07:12','\0','2019-02-19 13:07:12','SURCHARGE','0','\0','APPLY_TAX_RULE'),(3,'2019-02-19 13:07:12','\0','2019-02-19 13:07:12','BOOKING_LIMIT','0','\0','CLOSEOUT_ROOM_RULE');
/*!40000 ALTER TABLE `supply_rule_definition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topic`
--

DROP TABLE IF EXISTS `topic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `topic` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `name` varchar(255) NOT NULL,
  `is_shadowed` int(11) DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topic`
--

LOCK TABLES `topic` WRITE;
/*!40000 ALTER TABLE `topic` DISABLE KEYS */;
INSERT INTO `topic` VALUES (1,'2019-01-15 00:00:00','2019-01-15 00:00:00','pushcorejson',0);
/*!40000 ALTER TABLE `topic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topic_subscription`
--

DROP TABLE IF EXISTS `topic_subscription`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `topic_subscription` (
  `channel_id` bigint(20) NOT NULL,
  `topic_id` bigint(20) NOT NULL,
  PRIMARY KEY (`channel_id`,`topic_id`),
  KEY `FK8k96dll0dnwlvvn4bur5x1hk8` (`topic_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topic_subscription`
--

LOCK TABLES `topic_subscription` WRITE;
/*!40000 ALTER TABLE `topic_subscription` DISABLE KEYS */;
INSERT INTO `topic_subscription` VALUES (1,1),(2,1);
/*!40000 ALTER TABLE `topic_subscription` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-04-04  6:52:58
