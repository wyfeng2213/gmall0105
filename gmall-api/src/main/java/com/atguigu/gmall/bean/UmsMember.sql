-- auto Generated on 2019-08-02 16:49:38 
-- DROP TABLE IF EXISTS `ums_member`; 
CREATE TABLE ums_member(
    `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
    `member_level_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '@GeneratedValue(strategy = GenerationType.IDENTITY)主键返回策略',
    `username` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'username',
    `password` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'password',
    `nickname` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'nickname',
    `phone` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'phone',
    `status` INTEGER(12) NOT NULL DEFAULT -1 COMMENT 'status',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'createTime',
    `icon` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'icon',
    `gender` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'gender',
    `birthday` DATETIME NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT 'birthday',
    `city` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'city',
    `job` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'job',
    `personalized_signature` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'personalizedSignature',
    `source_uid` BIGINT NOT NULL DEFAULT -1 COMMENT 'sourceUid',
    `source_type` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'sourceType',
    `integration` INTEGER(12) NOT NULL DEFAULT -1 COMMENT 'integration',
    `growth` INTEGER(12) NOT NULL DEFAULT -1 COMMENT 'growth',
    `luckey_count` INTEGER(12) NOT NULL DEFAULT -1 COMMENT 'luckeyCount',
    `history_integration` INTEGER(12) NOT NULL DEFAULT -1 COMMENT 'historyIntegration',
    `access_token` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'accessToken',
    `access_code` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'accessCode',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'ums_member';


