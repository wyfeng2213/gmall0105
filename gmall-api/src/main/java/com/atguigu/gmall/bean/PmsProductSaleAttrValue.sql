-- auto Generated on 2019-08-02 16:52:21 
-- DROP TABLE IF EXISTS `pms_product_sale_attr_value`; 
CREATE TABLE pms_product_sale_attr_value(
    `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
    `product_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'productId',
    `sale_attr_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'saleAttrId',
    `sale_attr_value_name` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'saleAttrValueName',
    `is_checked` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'isChecked',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'pms_product_sale_attr_value';
