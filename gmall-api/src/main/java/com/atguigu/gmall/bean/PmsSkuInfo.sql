-- auto Generated on 2019-08-02 16:49:21 
-- DROP TABLE IF EXISTS `pms_sku_info`; 
CREATE TABLE pms_sku_info(
    `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
    `product_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'productId',
    `price` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'price',
    `sku_name` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'skuName',
    `sku_desc` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'skuDesc',
    `weight` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'weight',
    `tm_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'tmId',
    `catalog3_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'catalog3Id',
    `sku_default_img` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'skuDefaultImg',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'pms_sku_info';
