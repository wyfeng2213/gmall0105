-- auto Generated on 2019-08-02 16:50:48 
-- DROP TABLE IF EXISTS `pms_product_image`; 
CREATE TABLE pms_product_image(
    `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
    `product_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'productId',
    `img_name` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'imgName',
    `img_url` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'imgUrl',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'pms_product_image';
