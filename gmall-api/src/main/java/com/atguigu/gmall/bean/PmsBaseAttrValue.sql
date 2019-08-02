-- auto Generated on 2019-08-02 16:45:36 
-- DROP TABLE IF EXISTS `pms_base_attr_value`; 
CREATE TABLE pms_base_attr_value(
    `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
    `value_name` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'valueName',
    `attr_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'attrId',
    `is_enabled` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'isEnabled',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'pms_base_attr_value';
