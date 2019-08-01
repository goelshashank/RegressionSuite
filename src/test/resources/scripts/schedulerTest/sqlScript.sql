drop database if exists appconfigtest;
create database if not exists appconfigtest;

use appconfigtest;

--
-- Table structure for table `amadeus_cache_config`
--

DROP TABLE IF EXISTS `amadeus_cache_config`;
CREATE TABLE `amadeus_cache_config` (
  `config_key` varchar(255) NOT NULL,
  `config_value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`config_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `amadeus_cache_config`
--

LOCK TABLES `amadeus_cache_config` WRITE;
INSERT INTO `amadeus_cache_config` VALUES ('amadeus.performance.matrix.topic','acn-matrix-topic'),('amadeus.retry.errorcodes','100,200'),('amadeus.shopping.engine.paging','Y'),('amadeus.skip.retry.errorcodes','300'),('amadeus.soap.message.header.from','DhiscoPush1'),('amadeus.soap.message.header.fromref.id','ProviderID123'),('amadeus.soap.message.header.service.id','OTA2007A'),('amadeus.soap.message.header.service.name','OTA_HotelAvailRS'),('amadeus.soap.message.header.to','1ASRHCR6D1'),('app.config.amadeus.endpoints','http://localhost:9300/ws/hotelAvailabilityRQ'),('app.config.amadeus.otatopic','stp-amadeus-responses'),('app.delimiter','\\|\\|'),('hdfs.kafka.connect.topic','acn-hdfs-topic'),('kafka.p2d.topic.name','p2d_updates_final'),('logging.config','classpath:log4j2-dev.xml'),('logging.level.com.dhisco.cache.amadeus.service','INFO'),('logging.level.org.springframework.ws','TRACE'),('spring.data.cassandra.contact-points','valdbsdevcas01a.asp.dhisco.com'),('spring.data.cassandra.keyspace-name','amadeuscache'),('spring.data.cassandra.port','9042'),('spring.kafka.consumer.auto-commit-interval','6000'),('spring.kafka.consumer.auto-offset-reset','earliest'),('spring.kafka.consumer.bootstrap-servers','valstpdevkaf01a.asp.dhisco.com:9092,valstpdevkaf01b.asp.dhisco.com:9092,valstpdevkaf01c.asp.dhisco.com:9092'),('spring.kafka.consumer.group-id','amadeus-cache');
UNLOCK TABLES;

--
-- Table structure for table `cassandra_config`
--

DROP TABLE IF EXISTS `cassandra_config`;
CREATE TABLE `cassandra_config` (
  `config_key` varchar(255) NOT NULL,
  `config_value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`config_key`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cassandra_config`
--

LOCK TABLES `cassandra_config` WRITE;
UNLOCK TABLES;

--
-- Table structure for table `channelprocessor`
--

DROP TABLE IF EXISTS `channelprocessor`;
CREATE TABLE `channelprocessor` (
  `config_key` varchar(255) NOT NULL,
  `config_value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`config_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `channelprocessor`
--

LOCK TABLES `channelprocessor` WRITE;
INSERT INTO `channelprocessor` VALUES ('max.connection.per.route','300'),('max.connection.total','300'),('retry.interval.initial','1000'),('retry.interval.periodic','60000'),('retry.short.window.count','5'),('rezgain_url','https://rzintghospidev.rategain.com/WSAPI/api/BOT/UpdateARI');
UNLOCK TABLES;

--
-- Table structure for table `global`
--

DROP TABLE IF EXISTS `global`;
CREATE TABLE `global` (
  `config_key` varchar(255) NOT NULL,
  `config_value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`config_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `global`
--

LOCK TABLES `global` WRITE;
INSERT INTO `global` VALUES ('cassandra.consistency.level','ALL'),('cassandra.max.requests.per.connection','32768'),('cassandra.nodes','valdbsdevcas01a.asp.dhisco.com'),('cassandra.speculative.delay.ms','500'),('cassandra.speculative.execs','3'),('kafka.bootstrap.servers','valstpdevkaf01a.asp.dhisco.com:9092,valstpdevkaf01b.asp.dhisco.com:9092,valstpdevkaf01c.asp.dhisco.com:9092'),('kafka.num.kafka.stream.threads','10'),('kafka.p2d.changes.topic.name','p2d_updates_final'),('kafka.producer.acks','all'),('kafka.producer.compression.type','snappy'),('kafka.producer.linger.ms','0'),('kafka.producer.retries','0'),('kafka.state.dir','/apps/test/logs/%s/%s/kafka-streams-%s'),('zookeeper.address','valstpdevzoo01a.asp.dhisco.com:2181,valstpdevzoo01b.asp.dhisco.com:2181,valstpdevzoo01c.asp.dhisco.com:2181');
UNLOCK TABLES;

--
-- Table structure for table `kafka_config`
--

DROP TABLE IF EXISTS `kafka_config`;
CREATE TABLE `kafka_config` (
  `config_key` varchar(255) NOT NULL,
  `config_value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`config_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `kafka_config`
--

LOCK TABLES `kafka_config` WRITE;
UNLOCK TABLES;

--
-- Table structure for table `key_value_properties_entity`
--

DROP TABLE IF EXISTS `key_value_properties_entity`;
CREATE TABLE `key_value_properties_entity` (
  `config_key` varchar(255) NOT NULL,
  `config_value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`config_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `key_value_properties_entity`
--

LOCK TABLES `key_value_properties_entity` WRITE;
UNLOCK TABLES;

--
-- Table structure for table `quartz_properties`
--

DROP TABLE IF EXISTS `quartz_properties`;
CREATE TABLE `quartz_properties` (
  `config_key` varchar(255) NOT NULL,
  `config_value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`config_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `quartz_properties`
--

LOCK TABLES `quartz_properties` WRITE;
UNLOCK TABLES;

--
-- Table structure for table `renderedtoari`
--

DROP TABLE IF EXISTS `renderedtoari`;
CREATE TABLE `renderedtoari` (
  `config_key` varchar(255) NOT NULL,
  `config_value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`config_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `renderedtoari`
--

LOCK TABLES `renderedtoari` WRITE;
INSERT INTO `renderedtoari` VALUES ('ari.interpreter.book.endpoint','http://localhost:8080/interpret/OTA_HotelBookingRuleNotifRQ'),('ari.interpreter.inventory.endpoint','http://localhost:8080/interpret/OTA_HotelInvCountNotifRQ'),('ari.interpreter.rate.endpoint','http://localhost:8080/interpret/OTA_HotelRateAmountNotifRQ'),('kafka.application.id','rendered-to-ari'),('kafka.client.id','rendered-to-ari-client'),('kafka.messages.compression.type','gzip'),('kafka.number.of.stream.threads','100'),('log4j.appender.app_appender','org.apache.log4j.DailyRollingFileAppender'),('log4j.appender.app_appender.Append','true'),('log4j.appender.app_appender.file','/apps/logs/%s/rendered-to-ari/renderedToAri.log'),('log4j.appender.app_appender.layout','org.apache.log4j.PatternLayout'),('log4j.appender.app_appender.layout.conversionPattern','%d{yyyy-MM-dd HH:mm:ss.SSS} [%c] [%p] [%t] %m%n'),('log4j.appender.console_appender','org.apache.log4j.ConsoleAppender'),('log4j.appender.console_appender.Append','TRUE'),('log4j.appender.console_appender.BufferedIO','FALSE'),('log4j.appender.console_appender.DatePattern','.yyyy-MM-dd'),('log4j.appender.console_appender.ImmediateFlush','TRUE'),('log4j.appender.console_appender.layout','org.apache.log4j.PatternLayout'),('log4j.appender.console_appender.layout.conversionPattern','%d{yyyy-MM-dd HH:mm:ss} | %-5p |  [%t] %c.%M (%F:%L) - %m%n'),('log4j.appender.console_appender.Target','System.out'),('log4j.appender.stats_appender','org.apache.log4j.DailyRollingFileAppender'),('log4j.appender.stats_appender.Append','TRUE'),('log4j.appender.stats_appender.BufferedIO','FALSE'),('log4j.appender.stats_appender.DatePattern','.yyyy-MM-dd'),('log4j.appender.stats_appender.file','/apps/logs/%s/rendered-to-ari/renderedToAri-stats.log'),('log4j.appender.stats_appender.ImmediateFlush','TRUE'),('log4j.appender.stats_appender.layout','org.apache.log4j.PatternLayout'),('log4j.appender.stats_appender.layout.conversionPattern','%d{yyyy-MM-dd HH:mm:ss.SSS} [%c] [%5p] [%t] [%x]  %m%n'),('log4j.appender.stdout','org.apache.log4j.ConsoleAppender'),('log4j.appender.stdout.layout','org.apache.log4j.PatternLayout'),('log4j.appender.stdout.Target','System.out'),('log4j.appender.trans_appender','org.apache.log4j.DailyRollingFileAppender'),('log4j.appender.trans_appender.Append','TRUE'),('log4j.appender.trans_appender.BufferedIO','TRUE'),('log4j.appender.trans_appender.DatePattern','.yyyy-MM-dd'),('log4j.appender.trans_appender.file','/apps/logs/%s/rendered-to-ari/renderedToAri-trans.log'),('log4j.appender.trans_appender.ImmediateFlush','FALSE'),('log4j.appender.trans_appender.layout','org.apache.log4j.PatternLayout'),('log4j.appender.trans_appender.layout.conversionPattern','%d{yyyy-MM-dd HH:mm:ss.SSS} [%c{1}] [%p] [%t] [%m]%n'),('log4j.rootLogger','INFO, app_appender,console_appender'),('max.products.in.one.push.core.message','2'),('push.core.adapter.name','push-core-adapter'),('push.core.adapter.resend.all.from.persistence','false'),('push.core.adapter.resend.from.persistence','false'),('push.interval','10'),('rendered.admin.topic','rendered-admin-topic'),('rendered.persistence.cassandra.keyspace','rendered2ari'),('rendered.shop.topic','rendered-shop-topic'),('test.adapter.name','test-adapter'),('test.adapter.resend.all.from.persistence','false'),('test.adapter.resend.from.persistence','false');
UNLOCK TABLES;

--
-- Table structure for table `scheduler`
--

DROP TABLE IF EXISTS `scheduler`;
CREATE TABLE `scheduler` (
  `config_key` varchar(255) NOT NULL,
  `config_value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`config_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `scheduler`
--

LOCK TABLES `scheduler` WRITE;
INSERT INTO `scheduler` VALUES ('cassandra.keyspace','stp'),('chunk.max.num.adults','2'),('chunk.max.num.checkin.dates','1'),('chunk.max.num.los','7'),('chunk.max.num.pids','1'),('chunk.max.num.rooms','2'),('chunking.config.name','standard'),('instance_id','AUTO'),('instance_name','ShoppingScheduler'),('jobStore_class','org.quartz.simpl.RAMJobStore'),('kafka.admin.topic.name','stp-admin-scheduler_test'),('kafka.application.id','scheduler'),('kafka.cds.topic.name','stp-cds'),('kafka.debezium.database.server.name','configdb'),('kafka.shopping.topic.name','stp-shopping_test'),('log4j.appender.app_appender','org.apache.log4j.DailyRollingFileAppender'),('log4j.appender.app_appender.Append','TRUE'),('log4j.appender.app_appender.BufferedIO','FALSE'),('log4j.appender.app_appender.DatePattern','.yyyy-MM-dd'),('log4j.appender.app_appender.file','/apps/test/logs/%s/%s/%s-%s.log'),('log4j.appender.app_appender.ImmediateFlush','TRUE'),('log4j.appender.app_appender.layout','org.apache.log4j.PatternLayout'),('log4j.appender.app_appender.layout.conversionPattern','%d{yyyy-MM-dd HH:mm:ss} | %-5p |  [%t] %c.%M (%F:%L) - %m%n'),('log4j.appender.app_appender.MaxBackupIndex','50'),('log4j.appender.app_appender.MaxFileSize','10MB'),('log4j.appender.console_appender','org.apache.log4j.ConsoleAppender'),('log4j.appender.console_appender.Append','TRUE'),('log4j.appender.console_appender.BufferedIO','FALSE'),('log4j.appender.console_appender.DatePattern','.yyyy-MM-dd'),('log4j.appender.console_appender.ImmediateFlush','TRUE'),('log4j.appender.console_appender.layout','org.apache.log4j.PatternLayout'),('log4j.appender.console_appender.layout.conversionPattern','%d{yyyy-MM-dd HH:mm:ss} | %-5p |  [%t] %c.%M (%F:%L) - %m%n'),('log4j.appender.console_appender.Target','System.out'),('log4j.appender.stats_appender','org.apache.log4j.DailyRollingFileAppender'),('log4j.appender.stats_appender.Append','TRUE'),('log4j.appender.stats_appender.BufferedIO','FALSE'),('log4j.appender.stats_appender.DatePattern','.yyyy-MM-dd'),('log4j.appender.stats_appender.file','/apps/test/logs/%s/%s/%s-%s-stats.log'),('log4j.appender.stats_appender.ImmediateFlush','TRUE'),('log4j.appender.stats_appender.layout','org.apache.log4j.PatternLayout'),('log4j.appender.stats_appender.layout.conversionPattern','%d{yyyy-MM-dd HH:mm:ss} | %-5p |  [%t] %c.%M (%F:%L) - %m%n'),('log4j.appender.trans_appender','org.apache.log4j.DailyRollingFileAppender'),('log4j.appender.trans_appender.Append','TRUE'),('log4j.appender.trans_appender.BufferedIO','TRUE'),('log4j.appender.trans_appender.DatePattern','.yyyy-MM-dd'),('log4j.appender.trans_appender.file','/apps/test/logs/%s/%s/%s-%s-trans.log'),('log4j.appender.trans_appender.ImmediateFlush','FALSE'),('log4j.appender.trans_appender.layout','org.apache.log4j.PatternLayout'),('log4j.appender.trans_appender.layout.conversionPattern','%d{yyyy-MM-dd HH:mm:ss} | %-5p |  [%t] %c.%M (%F:%L) - %m%n'),('log4j.rootLogger','DEBUG, app_appender, trans_appender, stats_appender,console_appender'),('max.shopping.interval','365'),('org.quartz.jobStore.class','org.quartz.simpl.RAMJobStore'),('org.quartz.jobStore.misfireThreshold','60000'),('org.quartz.plugin.triggHistory.class','org.quartz.plugins.history.LoggingJobHistoryPlugin'),('org.quartz.scheduler.instanceId','AUTO'),('org.quartz.scheduler.instanceName','ShoppingScheduler'),('org.quartz.threadPool.class','org.quartz.simpl.SimpleThreadPool'),('org.quartz.threadPool.threadCount','3'),('org.quartz.threadPool.threadPriority','5'),('plugins_triggerHistroryClass','org.quartz.plugins.history.LoggingJobHistoryPlugin'),('skip_update_check','TRUE'),('thread_pool_class','org.quartz.simpl.SimpleThreadPool'),('thread_pool_threadCount','3'),('thread_pool_threadPriority','5'),('app.log.dir.name','scheduler'),('app.log.file.name','scheduler'),('endpoints.shutdown.enabled', 'true'),('management.endpoint.restart.enabled', 'true'),('management.endpoint.shutdown.enabled', 'true'),('management.endpoints.web.exposure.include', '*');
UNLOCK TABLES;

--
-- Table structure for table `scheduler_common_config`
--

DROP TABLE IF EXISTS `scheduler_common_config`;
CREATE TABLE `scheduler_common_config` (
  `config_key` varchar(255) NOT NULL,
  `config_value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`config_key`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `scheduler_common_config`
--

LOCK TABLES `scheduler_common_config` WRITE;
UNLOCK TABLES;

--
-- Table structure for table `shoppingengine`
--

DROP TABLE IF EXISTS `shoppingengine`;
CREATE TABLE `shoppingengine` (
  `config_key` varchar(255) NOT NULL,
  `config_value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`config_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `shoppingengine`
--

LOCK TABLES `shoppingengine` WRITE;
INSERT INTO `shoppingengine` VALUES ('amadeus.adapter.url','http://localhost:29200/amadeus/s2p/se/http'),('cassandra.keyspace','stp'),('hcd.hotel.info.rest.service.url','http://dhscaluatglf02e.asp.dhisco.com:28080/hdc4stp/rest/content/hotelBasicInfo?brand={brand}&pid={pid}'),('hcd.rest.service.max.retries','3'),('hcd.room.descriptions.rest.service.url','http://dhscaluatglf02e.asp.dhisco.com:28080/hdc4stp/rest/content/roomDesc?brand={brand}&pid={pid}&rtcs={rtcs}'),('kafka.admin.topic.name','stp-admin-engine'),('kafka.amadeus.delimiter','||'),('kafka.amadeus.shopping.topic.name','stp-amadeus-responses'),('kafka.appender.ConversionPattern','%d{yyyy-MM-dd HH:mm:ss.SSS} [%c] [%5p] [%t] [%x]  (%F:%M:%L)  %m%n'),('kafka.appender.kafkaAppender','org.apache.kafka.log4jappender.KafkaLog4jAppender'),('kafka.appender.kafkaAppender.BrokerList','valstpdevkaf01a.asp.dhisco.com:9092,valstpdevkaf01b.asp.dhisco.com:9092,valstpdevkaf01c.asp.dhisco.com:9092'),('kafka.appender.kafkaAppender.syncSend','FALSE'),('kafka.appender.kafkaAppender.Topic','s2p-error-log'),('kafka.application.id','stp'),('kafka.client.id','stp-client'),('kafka.error.logger','INFO, kafkaAppender'),('kafka.num.stream.threads','10'),('kafka.resend.topic.name','stp-resend'),('kafka.shopping.topic.name','stp-shopping2'),('log4j.appender.app_appender','org.apache.log4j.DailyRollingFileAppender'),('log4j.appender.app_appender.Append','TRUE'),('log4j.appender.app_appender.BufferedIO','FALSE'),('log4j.appender.app_appender.DatePattern','.yyyy-MM-dd'),('log4j.appender.app_appender.file','/apps/logs/%s/shopping-engine/shoppingEngine.log'),('log4j.appender.app_appender.ImmediateFlush','TRUE'),('log4j.appender.app_appender.layout','org.apache.log4j.PatternLayout'),('log4j.appender.app_appender.layout.conversionPattern','%d{yyyy-MM-dd HH:mm:ss.SSS} [%c] [%p] [%t] %m%n'),('log4j.appender.console_appender','org.apache.log4j.ConsoleAppender'),('log4j.appender.console_appender.Append','TRUE'),('log4j.appender.console_appender.BufferedIO','FALSE'),('log4j.appender.console_appender.DatePattern','.yyyy-MM-dd'),('log4j.appender.console_appender.ImmediateFlush','TRUE'),('log4j.appender.console_appender.layout','org.apache.log4j.PatternLayout'),('log4j.appender.console_appender.layout.conversionPattern','%d{yyyy-MM-dd HH:mm:ss} | %-5p |  [%t] %c.%M (%F:%L) - %m%n'),('log4j.appender.console_appender.Target','System.out'),('log4j.appender.stats_appender','org.apache.log4j.DailyRollingFileAppender'),('log4j.appender.stats_appender.Append','TRUE'),('log4j.appender.stats_appender.BufferedIO','FALSE'),('log4j.appender.stats_appender.DatePattern','.yyyy-MM-dd'),('log4j.appender.stats_appender.file','/apps/logs/%s/shopping-engine/shoppingEngine-stats.log'),('log4j.appender.stats_appender.ImmediateFlush','TRUE'),('log4j.appender.stats_appender.layout','org.apache.log4j.PatternLayout'),('log4j.appender.stats_appender.layout.conversionPattern','%d{yyyy-MM-dd HH:mm:ss.SSS} [%c] [%5p] [%t] [%x]  %m%n'),('log4j.appender.trans_appender','org.apache.log4j.DailyRollingFileAppender'),('log4j.appender.trans_appender.Append','TRUE'),('log4j.appender.trans_appender.BufferedIO','TRUE'),('log4j.appender.trans_appender.DatePattern','.yyyy-MM-dd'),('log4j.appender.trans_appender.file','/apps/logs/%s/shopping-engine/shoppingEngine-trans.log'),('log4j.appender.trans_appender.ImmediateFlush','FALSE'),('log4j.appender.trans_appender.layout','org.apache.log4j.PatternLayout'),('log4j.appender.trans_appender.layout.conversionPattern','%d{yyyy-MM-dd HH:mm:ss.SSS} [%c{1}] [%p] [%t] [%m]%n'),('log4j.rootLogger','INFO, app_appender,console_appender'),('pid.service.url','http://dhscaluatglf02e.asp.dhisco.com:28080/hdc4stp/rest/content/allPids?brand={brand}'),('pw.board.type.matching.AI','ALLINC'),('pw.board.type.matching.BB','BRKFST|BRKBUF|CNTBRK|COMBRK|FEBBRK|BB|BP|CB'),('pw.board.type.matching.DO','DINNER|COMPDN'),('pw.board.type.matching.FB','FAP'),('pw.board.type.matching.HB','MAP'),('pw.board.type.matching.LO','LUNCH|COMPLN'),('pw.board.type.matching.RO','EP'),('pw.default.biggest.max.occ','7'),('pw.default.channel.group.code','PW default SGA code'),('pw.default.max.occupancy','2'),('pw.default.separator',';'),('pw.dp.cache.ttl.seconds','4320000'),('pw.error.message.ttl','172800'),('pw.json.rest.url','http://213.131.248.205:1253'),('pw.max.paying.person.age','255'),('pw.min.child.age','0'),('pw.min.paying.person.age','13'),('pw.rest.template.read.timeout','30000'),('pw.room.type.matching.regex.AP','(?i)Apartment'),('pw.room.type.matching.regex.DL','(?i)(Deluxe|Dlx|Premium|Luxury|VIP|Royal|Presidential|Majestic)'),('pw.room.type.matching.regex.DP','(?i)Duplex'),('pw.room.type.matching.regex.DR','(?i)(Double|Dbl|King|Kng|Queen|Qn)'),('pw.room.type.matching.regex.ER','(?i)(Economy|Small|Basic)'),('pw.room.type.matching.regex.FR','(?i)Family'),('pw.room.type.matching.regex.HA','(?i)(Accessible|Physical|Physically|Handicap|Handicapped|Mobility)'),('pw.room.type.matching.regex.JS','(?i)(Junior Suite|JR Suite|JrSuite)'),('pw.room.type.matching.regex.SP','(?i)(Superior|Sup|Executive|Exec|Concierge|Club|Lounge Access)'),('pw.room.type.matching.regex.SR','(?i)(1 Single|One Single|Sgl|1 Sgl|1 person)'),('pw.room.type.matching.regex.ST','(?i)Studio'),('pw.room.type.matching.regex.SU','(?i)(Suite|Bedroom)'),('pw.room.type.matching.regex.TR','(?i)Triple'),('pw.room.type.matching.regex.VI','(?i)Villa'),('pw.store.jsons','false'),('r2ari.raw.shop.topic','raw-shop-topic'),('r2ari.rendered.shop.topic','rendered-shop-topic'),('stp.application.id.znode','/stp/application_id'),('stp.shopping.delay.decrement','10'),('stp.shopping.delay.initial','1000'),('stp.shopping.delay.max','2500'),('stp.shopping.delay.multiplier','50'),('stp.shopping.delay.useZooKeeper','false'),('stp.shopping.delay.znode','/stp/shopping-read-delay'),('ud.resend.chunk.interval','300000'),('ud.url','http://dhscalqalglf01c.asp.dhisco.com:38080/apps/TransactionInterfaceV1_1/DispatcherServlet'),('usw.back.pressure.error.codes','SYS81,SYS82,SYS83,SYS84,SYS89,SYS90'),('usw.concurrent.consumers','5'),('usw.concurrent.consumers.max','15'),('usw.connection.concurrent.request.sessions','10'),('usw.connection.imqAddressList','10.215.50.50:10400'),('usw.connection.imqAddressListBehavior','RANDOM'),('usw.connection.imqDefaultPassword','guest'),('usw.connection.imqDefaultUsername','guest'),('usw.request.destination','UswOtaRqst_S2P'),('usw.request.timeout','100000'),('usw.response.destination','UswOtaResp_S2P'),('usw.rq.demand.partner.code','PW'),('usw.rq.header.gds','WB'),('usw.rq.header.iat','018612'),('usw.rq.header.type','A');
UNLOCK TABLES;

--
-- Table structure for table `supplyruleprocessor`
--

DROP TABLE IF EXISTS `supplyruleprocessor`;
CREATE TABLE `supplyruleprocessor` (
  `config_key` varchar(255) NOT NULL,
  `config_value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`config_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `supplyruleprocessor`
--

LOCK TABLES `supplyruleprocessor` WRITE;
INSERT INTO `supplyruleprocessor` VALUES ('test','test');
UNLOCK TABLES;

drop database if exists pushcoretest;
create database if not exists pushcoretest;

use pushcoretest;

--
-- Table structure for table `ari_rule_definition`
--

DROP TABLE IF EXISTS `ari_rule_definition`;

CREATE TABLE `ari_rule_definition` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  `is_shadowed` bit(1) DEFAULT b'0',
  `updated_at` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `default_value` varchar(255) DEFAULT NULL,
  `input_type` varchar(255) DEFAULT NULL,
  `rule_code` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ari_rule_def_rc_it` (`rule_code`,`input_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `ari_rule_definition`
--

LOCK TABLES `ari_rule_definition` WRITE;
UNLOCK TABLES;

--
-- Table structure for table `brand`
--

DROP TABLE IF EXISTS `brand`;

CREATE TABLE `brand` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  `is_shadowed` bit(1) DEFAULT b'0',
  `updated_at` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `consumer_count` int(11) NOT NULL DEFAULT 2,
  `code` varchar(255) NOT NULL,
  `topic` varchar(255) NOT NULL,
  `isShadowed` bit(1) DEFAULT b'0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_brand_code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4;


--
-- Dumping data for table `brand`
--

LOCK TABLES `brand` WRITE;

INSERT INTO `brand` VALUES (1,'2019-07-17 07:24:47','\0','2019-07-25 10:39:05',2,'VS','VS_Brand_1','\0'),(2,'2019-07-17 07:24:47','\0','2019-07-17 07:24:47',2,'S8','S8_Brand_1','\0'),(3,'2019-07-17 07:24:48','\0','2019-07-17 07:24:48',2,'PU','PU_Brand_1','\0'),(4,'2019-07-17 07:24:48','\0','2019-07-17 07:24:48',2,'MJ','MJ_Brand_1','\0'),(5,'2019-07-17 07:24:48','\0','2019-07-17 07:24:48',2,'AD','AD_Brand_1','\0'),(6,'2019-07-17 07:24:48','\0','2019-07-17 07:24:48',2,'M4','M4_Brand_1','\0'),(7,'2019-07-17 07:24:49','\0','2019-07-17 07:24:49',2,'WY','WY_Brand_1','\0'),(8,'2019-07-17 07:24:49','\0','2019-07-17 07:24:49',2,'XM','XM_Brand_1','\0'),(9,'2019-07-17 07:24:49','\0','2019-07-17 07:24:49',2,'XN','XN_Brand_1','\0'),(10,'2019-07-17 07:24:49','\0','2019-07-17 07:24:49',2,'Z1','Z1_Brand_1','\0'),(11,'2019-07-17 07:24:50','\0','2019-07-17 07:24:50',2,'Z2','Z2_Brand_1','\0'),(12,'2019-07-17 07:24:50','\0','2019-07-17 07:24:50',0,'HY','HY_Brand_1','\0'),(13,'2019-07-17 07:24:50','\0','2019-07-17 07:24:50',2,'XNX','XNX_Brand_1','\0'),(14,'2019-07-17 07:24:51','\0','2019-07-17 07:24:51',2,'ZZ','ZZ_Brand_1','\0');

UNLOCK TABLES;

--
-- Table structure for table `sga`
--

DROP TABLE IF EXISTS `sga`;

CREATE TABLE `sga` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `matching_qualifier` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_sga_nm` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `sga`
--

LOCK TABLES `sga` WRITE;
INSERT INTO `sga` VALUES (1,'XM','2019-07-17 07:24:54','2019-07-17 07:24:54','REQUESTED_AND_PUBLIC'),(2,'00','2019-07-17 07:24:54','2019-07-17 07:24:54','REQUESTED_AND_PUBLIC'),(3,'01','2019-07-17 07:24:54','2019-07-17 07:24:54','REQUESTED_AND_PUBLIC'),(4,'8Z','2019-07-17 07:24:54','2019-07-17 07:24:54','REQUESTED_AND_PUBLIC'),(5,'U6','2019-07-17 07:24:55','2019-07-17 07:24:55','REQUESTED_AND_PUBLIC'),(6,'7Z','2019-07-17 07:24:55','2019-07-17 07:24:55','REQUESTED_AND_PUBLIC'),(7,'0Z','2019-07-17 07:24:55','2019-07-17 07:24:55','REQUESTED_AND_PUBLIC');
UNLOCK TABLES;

--
-- Table structure for table `rezgain_update_attribute`
--

DROP TABLE IF EXISTS `rezgain_update_attribute`;

CREATE TABLE `rezgain_update_attribute` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  `is_shadowed` bit(1) DEFAULT b'0',
  `updated_at` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `attribute_id` int(11) NOT NULL,
  `attribute_name` varchar(255) NOT NULL,
  `attribute_type_id` int(11) NOT NULL,
  `attribute_type_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `rezgain_update_attribute`
--

LOCK TABLES `rezgain_update_attribute` WRITE;
UNLOCK TABLES;

--
-- Table structure for table `channel`
--
DROP TABLE IF EXISTS `channel`;

CREATE TABLE `channel` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  `is_shadowed` bit(1) DEFAULT b'0',
  `updated_at` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `app_instances` int(11) NOT NULL DEFAULT 0,
  `channel_concurrency` int(11) NOT NULL DEFAULT 0,
  `channel_hotel_concurrency` int(11) NOT NULL DEFAULT 0,
  `consumer_count` int(11) NOT NULL DEFAULT 1,
  `is_crs_hpc_mappings_allowed` tinyint(1) NOT NULL DEFAULT 1,
  `is_crs_icc_mappings_allowed` tinyint(1) NOT NULL DEFAULT 1,
  `is_crs_rpc_mappings_allowed` tinyint(1) NOT NULL DEFAULT 1,
  `is_public_rates_allowed` tinyint(1) NOT NULL DEFAULT 0,
  `name` varchar(255) NOT NULL,
  `topic` varchar(255) NOT NULL,
  `sga_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_channel_name` (`name`),
  KEY `sga_id` (`sga_id`),
  CONSTRAINT `channel_ibfk_1` FOREIGN KEY (`sga_id`) REFERENCES `sga` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `channel`
--

LOCK TABLES `channel` WRITE;
INSERT INTO `channel` VALUES (1,'2019-01-15 00:00:00','\0','2019-07-22 12:54:49',1,100,15,3,1,1,1,0,'MikiTravel','MikiTravel_1',1);

UNLOCK TABLES;


--
-- Table structure for table `brand_channel_rule`
--

DROP TABLE IF EXISTS `brand_channel_rule`;

CREATE TABLE `brand_channel_rule` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  `is_shadowed` bit(1) DEFAULT b'0',
  `updated_at` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `brand_id` bigint(20) DEFAULT NULL,
  `input_value` varchar(255) DEFAULT NULL,
  `ari_rule_definition_id` bigint(20) DEFAULT NULL,
  `channel_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_bcr_ari_rule_def_id` (`ari_rule_definition_id`),
  KEY `FK_bcr_channel_id` (`channel_id`),
  KEY `FK_bcr_brand_id` (`brand_id`),
  CONSTRAINT `FK_bcr_ari_rule_def_id` FOREIGN KEY (`ari_rule_definition_id`) REFERENCES `ari_rule_definition` (`id`),
  CONSTRAINT `FK_bcr_brand_id` FOREIGN KEY (`brand_id`) REFERENCES `brand` (`id`),
  CONSTRAINT `FK_bcr_channel_id` FOREIGN KEY (`channel_id`) REFERENCES `channel` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `brand_channel_rule`
--

LOCK TABLES `brand_channel_rule` WRITE;
UNLOCK TABLES;

--
-- Table structure for table `channel_credential`
--

DROP TABLE IF EXISTS `channel_credential`;

CREATE TABLE `channel_credential` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  `is_shadowed` bit(1) DEFAULT b'0',
  `updated_at` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `password` varchar(255) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `channel_credential`
--

LOCK TABLES `channel_credential` WRITE;
UNLOCK TABLES;

--
-- Table structure for table `channel_rezgain_supported_attributes`
--

DROP TABLE IF EXISTS `channel_rezgain_supported_attributes`;

CREATE TABLE `channel_rezgain_supported_attributes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  `is_shadowed` bit(1) DEFAULT b'0',
  `updated_at` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `channel_id` bigint(20) DEFAULT NULL,
  `rezgain_update_attribute_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_crsa_ch_id_ru_att_id` (`channel_id`,`rezgain_update_attribute_id`),
  KEY `FK_crsa_ru_attrib_id` (`rezgain_update_attribute_id`),
  CONSTRAINT `FK_crsa_ch_id` FOREIGN KEY (`channel_id`) REFERENCES `channel` (`id`),
  CONSTRAINT `FK_crsa_ru_attrib_id` FOREIGN KEY (`rezgain_update_attribute_id`) REFERENCES `rezgain_update_attribute` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `channel_rezgain_supported_attributes`
--

LOCK TABLES `channel_rezgain_supported_attributes` WRITE;
UNLOCK TABLES;

--
-- Table structure for table `channel_rule`
--

DROP TABLE IF EXISTS `channel_rule`;

CREATE TABLE `channel_rule` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  `is_shadowed` bit(1) DEFAULT b'0',
  `updated_at` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `input_value` varchar(255) DEFAULT NULL,
  `ari_rule_definition_id` bigint(20) DEFAULT NULL,
  `channel_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_cr_ari_rule_def_id` (`ari_rule_definition_id`),
  KEY `FK_cr_ch_id` (`channel_id`),
  CONSTRAINT `FK_cr_ari_rule_def_id` FOREIGN KEY (`ari_rule_definition_id`) REFERENCES `ari_rule_definition` (`id`),
  CONSTRAINT `FK_cr_ch_id` FOREIGN KEY (`channel_id`) REFERENCES `channel` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `channel_rule`
--

LOCK TABLES `channel_rule` WRITE;
UNLOCK TABLES;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;

CREATE TABLE `client` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_client_nm` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
INSERT INTO `client` VALUES (1,'AMADEUS','2019-07-17 07:24:58','2019-07-17 07:24:58'),(2,'PW','2019-07-17 07:24:58','2019-07-17 07:24:58'),(3,'R2ARI','2019-07-17 07:24:59','2019-07-17 07:24:59');
UNLOCK TABLES;

--
-- Table structure for table `client_switch_connector`
--

DROP TABLE IF EXISTS `client_switch_connector`;

CREATE TABLE `client_switch_connector` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `client_id` bigint(20) DEFAULT NULL,
  `connector` varchar(255) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `FK_csc_client_id` (`client_id`),
  CONSTRAINT `FK_csc_client_id` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `client_switch_connector`
--

LOCK TABLES `client_switch_connector` WRITE;
INSERT INTO `client_switch_connector` VALUES (1,1,'AMADEUS','2019-07-17 07:25:15','2019-07-17 07:25:15'),(2,2,'USW','2019-07-17 07:25:16','2019-07-17 07:25:16'),(3,3,'UD','2019-07-17 07:25:16','2019-07-17 07:25:16');
UNLOCK TABLES;

--
-- Table structure for table `groups`
--

DROP TABLE IF EXISTS `groups`;

CREATE TABLE `groups` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_gps_nm` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `groups`
--

LOCK TABLES `groups` WRITE;
INSERT INTO `groups` VALUES (1,'HYGP','2019-07-17 07:25:01','2019-07-17 07:25:01'),(2,'HLGP','2019-07-17 07:25:02','2019-07-17 07:25:02'),(3,'Accor','2019-07-17 08:02:16','2019-07-17 08:02:16');
UNLOCK TABLES;

--
-- Table structure for table `hotel_property`
--

DROP TABLE IF EXISTS `hotel_property`;

CREATE TABLE `hotel_property` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  `is_shadowed` bit(1) DEFAULT b'0',
  `updated_at` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `hotel_code` varchar(255) NOT NULL,
  `brand_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_hp_cd_bid` (`hotel_code`,`brand_id`),
  KEY `FK_hp_bid` (`brand_id`),
  CONSTRAINT `FK_hp_bid` FOREIGN KEY (`brand_id`) REFERENCES `brand` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `hotel_property`
--

LOCK TABLES `hotel_property` WRITE;
INSERT INTO `hotel_property` VALUES (1,'2019-07-17 07:25:17','\0','2019-07-22 14:23:30','3646',1),(2,'2019-07-17 07:25:17','\0','2019-07-17 07:25:17','1765',2),(3,'2019-07-17 07:25:18','\0','2019-07-17 07:25:18','3616',3),(4,'2019-07-17 07:25:18','\0','2019-07-17 07:25:18','6719',4),(5,'2019-07-17 07:25:18','\0','2019-07-17 07:25:18','2845',5),(6,'2019-07-17 07:25:18','\0','2019-07-17 07:25:18','0533',13),(7,'2019-07-17 07:25:19','\0','2019-07-17 07:25:19','9435',14),(8,'2019-07-17 07:25:19','\0','2019-07-17 07:25:19','CHIHRC',12);
UNLOCK TABLES;

--
-- Table structure for table `hotel_property_channel_mapping`
--

DROP TABLE IF EXISTS `hotel_property_channel_mapping`;

CREATE TABLE `hotel_property_channel_mapping` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  `is_shadowed` bit(1) DEFAULT b'0',
  `updated_at` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `channel_hotel_code` varchar(255) NOT NULL,
  `channel_id` bigint(20) DEFAULT NULL,
  `hotel_property_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_hpcm_hpid_chid` (`hotel_property_id`,`channel_id`),
  KEY `FK_hpcm_chid` (`channel_id`),
  CONSTRAINT `FK_hpcm_chid` FOREIGN KEY (`channel_id`) REFERENCES `channel` (`id`),
  CONSTRAINT `FK_hpcm_hpid` FOREIGN KEY (`hotel_property_id`) REFERENCES `hotel_property` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


--
-- Dumping data for table `hotel_property_channel_mapping`
--

LOCK TABLES `hotel_property_channel_mapping` WRITE;
UNLOCK TABLES;

--
-- Table structure for table `hotel_property_subscription`
--

DROP TABLE IF EXISTS `hotel_property_subscription`;

CREATE TABLE `hotel_property_subscription` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  `is_shadowed` bit(1) DEFAULT b'0',
  `updated_at` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `channel_id` bigint(20) DEFAULT NULL,
  `hotel_property_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_hps_chid_hpid` (`channel_id`,`hotel_property_id`),
  KEY `UK_hps_hpid` (`hotel_property_id`),
  CONSTRAINT `UK_hps_chid` FOREIGN KEY (`channel_id`) REFERENCES `channel` (`id`),
  CONSTRAINT `UK_hps_hpid` FOREIGN KEY (`hotel_property_id`) REFERENCES `hotel_property` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `hotel_property_subscription`
--

LOCK TABLES `hotel_property_subscription` WRITE;
UNLOCK TABLES;

--
-- Table structure for table `inventory_code`
--

DROP TABLE IF EXISTS `inventory_code`;

CREATE TABLE `inventory_code` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  `is_shadowed` bit(1) DEFAULT b'0',
  `updated_at` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `name` varchar(255) NOT NULL,
  `hotel_property_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ic_hpid` (`name`,`hotel_property_id`),
  KEY `FK_ic_hpid` (`hotel_property_id`),
  CONSTRAINT `FK_ic_hpid` FOREIGN KEY (`hotel_property_id`) REFERENCES `hotel_property` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `inventory_code`
--

LOCK TABLES `inventory_code` WRITE;
UNLOCK TABLES;

--
-- Table structure for table `inventory_code_channel_mapping`
--

DROP TABLE IF EXISTS `inventory_code_channel_mapping`;

CREATE TABLE `inventory_code_channel_mapping` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  `is_shadowed` bit(1) DEFAULT b'0',
  `updated_at` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `room_code` varchar(255) NOT NULL,
  `room_name` varchar(255) NOT NULL,
  `channel_id` bigint(20) DEFAULT NULL,
  `inventory_code_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_iccm_icid_chid` (`inventory_code_id`,`channel_id`),
  KEY `FK_iccm_chid` (`channel_id`),
  CONSTRAINT `FK_iccm_chid` FOREIGN KEY (`channel_id`) REFERENCES `channel` (`id`),
  CONSTRAINT `FK_iccm_icid` FOREIGN KEY (`inventory_code_id`) REFERENCES `inventory_code` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `inventory_code_channel_mapping`
--

LOCK TABLES `inventory_code_channel_mapping` WRITE;
UNLOCK TABLES;

--
-- Table structure for table `max_occ_for_rtc`
--

DROP TABLE IF EXISTS `max_occ_for_rtc`;

CREATE TABLE `max_occ_for_rtc` (
  `brand` varchar(255) NOT NULL,
  `rtc` varchar(255) NOT NULL,
  `maxocc` int(11) DEFAULT NULL,
  PRIMARY KEY (`brand`,`rtc`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `max_occ_for_rtc`
--

LOCK TABLES `max_occ_for_rtc` WRITE;
INSERT INTO `max_occ_for_rtc` VALUES ('ZZ','1DT',1),('ZZ','1VK',1),('ZZ','2BD',1),('ZZ','2BT',1),('ZZ','2DS',1),('ZZ','2DT',1),('ZZ','A1D',2),('ZZ','A1K',2),('ZZ','AKS',1),('ZZ','AZK',1),('ZZ','BLC',1),('ZZ','DXK',1),('ZZ','KNG',1),('ZZ','LFK',1),('ZZ','LLK',1),('ZZ','SAS',1),('ZZ','SPA',1),('ZZ','SPS',1),('ZZ','TER',1),('ZZ','TRR',1),('ZZ','TRV',1),('ZZ','TVW',1),('ZZ','VST',1),('ZZ','VWS',1);
UNLOCK TABLES;

--
-- Table structure for table `meal_plan_mapping`
--

DROP TABLE IF EXISTS `meal_plan_mapping`;

CREATE TABLE `meal_plan_mapping` (
  `amenity_code` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `meal_plan_type_code` int(11) DEFAULT NULL,
  PRIMARY KEY (`amenity_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `meal_plan_mapping`
--

LOCK TABLES `meal_plan_mapping` WRITE;
INSERT INTO `meal_plan_mapping` VALUES ('ALLINC','All inclusive',1),('AP','American',2),('BB','Bed & breakfast',3),('BP','Bermuda',16),('BRKBUF','Buffet breakfast',4),('BRKFST','Breakfast',19),('CB','Caribbean breakfast',5),('CNTBRK','Continental breakfast',6),('COMBRK','Continental breakfast',6),('COMPDN','Dinner',22),('COMPLN','Lunch',21),('CP','Continental breakfast',6),('DINNER','Dinner',22),('EP','European plan',8),('FAP','Full board',10),('FEBRK','English breakfast',7),('FULLBK','Full breakfast',11),('LUNCH','Lunch',21),('MAP','Half board/modified American plan',12);
UNLOCK TABLES;

--
-- Table structure for table `nrc`
--

DROP TABLE IF EXISTS `nrc`;

CREATE TABLE `nrc` (
  `client_id` bigint(20) NOT NULL,
  `rpc` varchar(255) NOT NULL,
  `sga_id` bigint(20) NOT NULL,
  `ccn` varchar(255) DEFAULT NULL,
  `rpi` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`client_id`,`rpc`,`sga_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `nrc`
--

LOCK TABLES `nrc` WRITE;
INSERT INTO `nrc` VALUES (1,'DA5',2,NULL,'N'),(1,'DLU',2,NULL,'N'),(2,'DA5',4,NULL,'N'),(2,'DER',4,NULL,'N'),(2,'DLU',4,NULL,'N'),(2,'PEG',4,NULL,'N'),(3,'1KD',1,NULL,'N'),(3,'1Z1',1,NULL,'N'),(3,'2KD',1,NULL,'N'),(3,'2MK',1,NULL,'N'),(3,'3KD',1,NULL,'N'),(3,'4MK',1,NULL,'N'),(3,'5MK',1,NULL,'N'),(3,'6MK',1,NULL,'N'),(3,'7MK',1,NULL,'N'),(3,'A0Z',1,NULL,'N'),(3,'A1C',1,NULL,'N'),(3,'DA5',1,NULL,'N'),(3,'DER',1,NULL,'N'),(3,'DLU',1,NULL,'N'),(3,'IHG',1,NULL,'N'),(3,'MK1',1,NULL,'N'),(3,'MK2',1,NULL,'N'),(3,'MK3',1,NULL,'N'),(3,'MK7',1,NULL,'N'),(3,'N39',1,NULL,'N'),(3,'ZZT',1,NULL,'N');
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;

CREATE TABLE `products` (
  `property_id` bigint(20) NOT NULL,
  `rpc` varchar(255) NOT NULL,
  `rtc` varchar(255) NOT NULL,
  `rrp` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`property_id`,`rpc`,`rtc`),
  KEY `FK_products_prop_id` (`property_id`),
  CONSTRAINT `FK_products_prop_id` FOREIGN KEY (`property_id`) REFERENCES `hotel_property` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
UNLOCK TABLES;

--
-- Table structure for table `property_channel_credential_mapping`
--

DROP TABLE IF EXISTS `property_channel_credential_mapping`;

CREATE TABLE `property_channel_credential_mapping` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  `is_shadowed` bit(1) DEFAULT b'0',
  `updated_at` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `channel_id` bigint(20) DEFAULT NULL,
  `channel_credential_id` bigint(20) DEFAULT NULL,
  `hotel_property_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_pccm_chid` (`channel_id`),
  KEY `FK_pccm_ch_credid` (`channel_credential_id`),
  KEY `FK_pccm_ch_hpid` (`hotel_property_id`),
  CONSTRAINT `FK_hccm_chid` FOREIGN KEY (`channel_id`) REFERENCES `channel` (`id`),
  CONSTRAINT `FK_pccm_ch_credid` FOREIGN KEY (`channel_credential_id`) REFERENCES `channel_credential` (`id`),
  CONSTRAINT `FK_pccm_ch_hpid` FOREIGN KEY (`hotel_property_id`) REFERENCES `hotel_property` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `property_channel_credential_mapping`
--

LOCK TABLES `property_channel_credential_mapping` WRITE;
UNLOCK TABLES;

--
-- Table structure for table `property_groups`
--

DROP TABLE IF EXISTS `property_groups`;

CREATE TABLE `property_groups` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` bigint(20) DEFAULT NULL,
  `property_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_pgps_gp_id` (`group_id`),
  KEY `FK_pgps_pid` (`property_id`),
  CONSTRAINT `FK_pgps_gp_id` FOREIGN KEY (`group_id`) REFERENCES `groups` (`id`),
  CONSTRAINT `FK_pgps_pid` FOREIGN KEY (`property_id`) REFERENCES `hotel_property` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `property_groups`
--

LOCK TABLES `property_groups` WRITE;
INSERT INTO `property_groups` VALUES (1,3,1),(2,3,2),(3,3,3),(4,3,4),(5,3,5);
UNLOCK TABLES;

--
-- Table structure for table `rate_plan`
--

DROP TABLE IF EXISTS `rate_plan`;

CREATE TABLE `rate_plan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  `is_shadowed` bit(1) DEFAULT b'0',
  `updated_at` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `name` varchar(255) NOT NULL,
  `hotel_property_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_rp_nm_hpid` (`name`,`hotel_property_id`),
  KEY `FK_rp_hpid` (`hotel_property_id`),
  CONSTRAINT `FK_rp_hpid` FOREIGN KEY (`hotel_property_id`) REFERENCES `hotel_property` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `rate_plan`
--

LOCK TABLES `rate_plan` WRITE;
UNLOCK TABLES;

--
-- Table structure for table `rate_plan_channel_mapping`
--

DROP TABLE IF EXISTS `rate_plan_channel_mapping`;

CREATE TABLE `rate_plan_channel_mapping` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  `is_shadowed` bit(1) DEFAULT b'0',
  `updated_at` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `rate_code` varchar(255) NOT NULL,
  `rate_name` varchar(255) NOT NULL,
  `channel_id` bigint(20) DEFAULT NULL,
  `rate_plan_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_rpcm_rt_pid_cid` (`rate_plan_id`,`channel_id`),
  KEY `FK_rpcm_ch_id` (`channel_id`),
  CONSTRAINT `FK_rpcm_ch_id` FOREIGN KEY (`channel_id`) REFERENCES `channel` (`id`),
  CONSTRAINT `FK_rpcm_rt_pid_id` FOREIGN KEY (`rate_plan_id`) REFERENCES `rate_plan` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `rate_plan_channel_mapping`
--

LOCK TABLES `rate_plan_channel_mapping` WRITE;
UNLOCK TABLES;

--
-- Table structure for table `rezgain_channel_mapping`
--

DROP TABLE IF EXISTS `rezgain_channel_mapping`;

CREATE TABLE `rezgain_channel_mapping` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  `is_shadowed` bit(1) DEFAULT b'0',
  `updated_at` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `password` varchar(255) DEFAULT NULL,
  `rezgain_channel_id` bigint(20) DEFAULT NULL,
  `rezgain_channel_name` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `channel_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_rcm_ch_id` (`channel_id`),
  CONSTRAINT `FK_rcm_ch_id` FOREIGN KEY (`channel_id`) REFERENCES `channel` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


--
-- Dumping data for table `rezgain_channel_mapping`
--

LOCK TABLES `rezgain_channel_mapping` WRITE;
UNLOCK TABLES;

--
-- Table structure for table `schedules`
--

DROP TABLE IF EXISTS `schedules`;

CREATE TABLE `schedules` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sga_id` bigint(20) DEFAULT NULL,
  `brand_id` bigint(20) DEFAULT NULL,
  `group_id` bigint(20) DEFAULT NULL,
  `start_offset` int(11) DEFAULT NULL,
  `end_offset` int(11) DEFAULT NULL,
  `los_start` int(11) DEFAULT NULL,
  `los_end` int(11) DEFAULT NULL,
  `shop_trigger` varchar(255) DEFAULT NULL,
  `trigger_start_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `trigger_end_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `scheduled_status` tinyint(1) NOT NULL DEFAULT 0,
  `client_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_sch_gp_id` (`group_id`),
  KEY `FK_sch_bid` (`brand_id`),
  KEY `FK_sch_pid` (`sga_id`),
  KEY `idx_sch_gp` (`group_id`,`brand_id`),
  KEY `idx_sch_brand` (`brand_id`),
  KEY `FK_sch_client_id` (`client_id`),
  CONSTRAINT `FK_sch_bid` FOREIGN KEY (`brand_id`) REFERENCES `brand` (`id`),
  CONSTRAINT `FK_sch_client_id` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`),
  CONSTRAINT `FK_sch_gp_id` FOREIGN KEY (`group_id`) REFERENCES `groups` (`id`),
  CONSTRAINT `FK_sch_pid` FOREIGN KEY (`sga_id`) REFERENCES `sga` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `schedules`
--

LOCK TABLES `schedules` WRITE;
UNLOCK TABLES;


--
-- Table structure for table `shop_profile`
--

DROP TABLE IF EXISTS `shop_profile`;

CREATE TABLE `shop_profile` (
  `brand_id` bigint(20) NOT NULL,
  `client_id` bigint(20) NOT NULL,
  `sga_id` bigint(20) NOT NULL,
  `maxlos` int(11) DEFAULT NULL,
  `maxnrcs` int(11) DEFAULT NULL,
  `maxocc` int(11) DEFAULT NULL,
  `maxrooms` int(11) DEFAULT NULL,
  `rolling_update_interval` int(11) DEFAULT NULL,
  PRIMARY KEY (`brand_id`,`client_id`,`sga_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `shop_profile`
--

LOCK TABLES `shop_profile` WRITE;
INSERT INTO `shop_profile` VALUES (1,3,1,7,15,2,2,0),(2,3,1,7,15,2,2,0),(3,3,1,7,15,2,2,0),(4,3,1,7,15,2,2,0),(5,3,1,7,15,2,2,0),(12,1,2,1,15,1,1,0);
UNLOCK TABLES;

--
-- Table structure for table `suppliers_config`
--

DROP TABLE IF EXISTS `suppliers_config`;

CREATE TABLE `suppliers_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `brand_id` bigint(20) NOT NULL,
  `missing_data_treshold` int(11) DEFAULT NULL,
  `optimistic_approach` bit(1) DEFAULT NULL,
  `supports_ctd` bit(1) DEFAULT NULL,
  `supports_fplos` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_sc_brand_id` (`brand_id`),
  CONSTRAINT `FK_sc_brand_id` FOREIGN KEY (`brand_id`) REFERENCES `brand` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `suppliers_config`
--

LOCK TABLES `suppliers_config` WRITE;
INSERT INTO `suppliers_config` VALUES (1,12,NULL,'\0','\0','\0'),(2,1,NULL,'\0','','\0'),(3,9,3,'\0','','\0'),(4,11,3,'\0','\0','\0');
UNLOCK TABLES;

--
-- Table structure for table `supply_rule`
--

DROP TABLE IF EXISTS `supply_rule`;

CREATE TABLE `supply_rule` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  `is_shadowed` bit(1) DEFAULT b'0',
  `updated_at` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `input_value` varchar(255) DEFAULT NULL,
  `ari_rule_definition_id` bigint(20) DEFAULT NULL,
  `brand_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_sr_ari_rule_def_id` (`ari_rule_definition_id`),
  KEY `FK_sr_brand_id` (`brand_id`),
  CONSTRAINT `FK_sr_ari_rule_def_id` FOREIGN KEY (`ari_rule_definition_id`) REFERENCES `ari_rule_definition` (`id`),
  CONSTRAINT `FK_sr_brand_id` FOREIGN KEY (`brand_id`) REFERENCES `brand` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `supply_rule`
--

LOCK TABLES `supply_rule` WRITE;
UNLOCK TABLES;

--
-- Table structure for table `ud_url_mapping`
--

DROP TABLE IF EXISTS `ud_url_mapping`;

CREATE TABLE `ud_url_mapping` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sga_id` bigint(20) DEFAULT NULL,
  `ud_url` varchar(255) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `FK_uum_sga_id` (`sga_id`),
  CONSTRAINT `FK_uum_sga_id` FOREIGN KEY (`sga_id`) REFERENCES `sga` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `ud_url_mapping`
--

LOCK TABLES `ud_url_mapping` WRITE;
INSERT INTO `ud_url_mapping` VALUES (1,3,'http://dhscalqalglf01c.asp.dhisco.com:38080/apps/TransactionInterfaceV1_1/DispatcherServlet','2019-07-17 07:29:33','2019-07-17 07:29:33'),(2,6,'http://dshu7z0av.dhisco.com:5454/apps/TransactionInterfaceV1_1/DispatcherServlet','2019-07-17 07:29:33','2019-07-17 07:29:33'),(3,5,'http://dshuu60av.dhisco.com:5454/apps/TransactionInterfaceV1_1/DispatcherServlet','2019-07-17 07:29:34','2019-07-17 07:29:34'),(4,1,'http://dshutn0av.dhisco.com:5454/apps/TransactionInterfaceV1_1/DispatcherServlet','2019-07-17 07:29:34','2019-07-17 07:29:34');
UNLOCK TABLES;
