-- auto Generated on 2019-08-02 16:44:28 
-- DROP TABLE IF EXISTS `oms_cart_item`; 
CREATE TABLE oms_cart_item(
    `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
    `product_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'productId',
    `product_sku_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'productSkuId',
    `member_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'memberId',
    `quantity` DECIMAL(14,4) NOT NULL DEFAULT 0 COMMENT 'quantity',
    `price` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'price',
    `sp1` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'sp1',
    `sp2` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'sp2',
    `sp3` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'sp3',
    `product_pic` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'productPic',
    `product_name` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'productName',
    `product_sub_title` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'productSubTitle',
    `create_date` DATETIME NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT 'createDate',
    `delete_status` INTEGER(12) NOT NULL DEFAULT -1 COMMENT 'deleteStatus',
    `product_category_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'productCategoryId',
    `product_brand` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'productBrand',
    `product_sn` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'productSn',
    `product_attr` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'productAttr',
    `cart_all_price` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'cartAllPrice',
    `is_checked` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'isChecked',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'oms_cart_item';
