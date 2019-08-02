-- auto Generated on 2019-08-02 16:50:34 
-- DROP TABLE IF EXISTS `pms_product_info`; 
CREATE TABLE pms_product_info(
    `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
    `product_name` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'productName',
    `description` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'description',
    `catalog3_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'catalog3Id',
    `tm_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'tmId',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'pms_product_info';
