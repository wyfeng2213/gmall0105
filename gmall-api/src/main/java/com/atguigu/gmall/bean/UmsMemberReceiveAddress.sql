-- auto Generated on 2019-08-02 16:51:55 
-- DROP TABLE IF EXISTS `ums_member_receive_address`; 
CREATE TABLE ums_member_receive_address(
    `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
    `member_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'memberId',
    `name` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'name',
    `phone_number` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'phoneNumber',
    `default_status` INTEGER(12) NOT NULL DEFAULT -1 COMMENT 'defaultStatus',
    `post_code` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'postCode',
    `province` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'province',
    `city` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'city',
    `region` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'region',
    `detail_address` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'detailAddress',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'ums_member_receive_address';
