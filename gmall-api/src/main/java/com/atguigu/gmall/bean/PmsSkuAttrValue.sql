-- auto Generated on 2019-08-02 16:49:13 
-- DROP TABLE IF EXISTS `pms_sku_attr_value`; 
CREATE TABLE pms_sku_attr_value(
    `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
    `attr_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'attrId',
    `value_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'valueId',
    `sku_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'skuId',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'pms_sku_attr_value';
