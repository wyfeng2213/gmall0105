-- auto Generated on 2019-08-02 16:45:03 
-- DROP TABLE IF EXISTS `oms_order_item`; 
CREATE TABLE oms_order_item(
    `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
    `order_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'orderId',
    `order_sn` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'orderSn',
    `product_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'productId',
    `product_pic` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'productPic',
    `product_name` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'productName',
    `product_brand` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'productBrand',
    `product_sn` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'productSn',
    `product_price` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'productPrice',
    `product_quantity` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'productQuantity',
    `product_sku_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'productSkuId',
    `product_sku_code` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'productSkuCode',
    `product_category_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'productCategoryId',
    `sp1` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'sp1',
    `sp2` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'sp2',
    `sp3` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'sp3',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'oms_order_item';
