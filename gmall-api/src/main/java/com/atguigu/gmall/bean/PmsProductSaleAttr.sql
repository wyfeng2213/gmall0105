-- auto Generated on 2019-08-02 16:48:33 
-- DROP TABLE IF EXISTS `pms_product_sale_attr`; 
CREATE TABLE pms_product_sale_attr(
    `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
    `product_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'productId',
    `sale_attr_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'saleAttrId',
    `sale_attr_name` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'saleAttrName',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'pms_product_sale_attr';


