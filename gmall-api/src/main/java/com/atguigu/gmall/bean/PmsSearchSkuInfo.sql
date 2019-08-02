-- auto Generated on 2019-08-02 16:49:07 
-- DROP TABLE IF EXISTS `pms_search_sku_info`; 
CREATE TABLE pms_search_sku_info(
    `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
    `sku_name` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'skuName',
    `sku_desc` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'skuDesc',
    `catalog3_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'catalog3Id',
    `price` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'price',
    `sku_default_img` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'skuDefaultImg',
    `hot_score` DECIMAL(14,4) NOT NULL DEFAULT 0 COMMENT 'hotScore',
    `product_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'productId',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'pms_search_sku_info';
