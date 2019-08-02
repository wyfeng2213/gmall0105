-- auto Generated on 2019-08-02 16:47:30 
-- DROP TABLE IF EXISTS `pms_base_attr_info`; 
CREATE TABLE pms_base_attr_info(
    `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
    `attr_name` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'attrName',
    `catalog3_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'catalog3Id',
    `is_enabled` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'isEnabled',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'pms_base_attr_info';
