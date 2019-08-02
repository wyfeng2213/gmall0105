-- auto Generated on 2019-08-02 16:49:31 
-- DROP TABLE IF EXISTS `pms_sku_sale_attr_value`; 
CREATE TABLE pms_sku_sale_attr_value(
    `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
    `sku_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'skuId',
    `sale_attr_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'saleAttrId',
    `sale_attr_value_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'saleAttrValueId',
    `sale_attr_name` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'saleAttrName',
    `sale_attr_value_name` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'saleAttrValueName',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'pms_sku_sale_attr_value';
