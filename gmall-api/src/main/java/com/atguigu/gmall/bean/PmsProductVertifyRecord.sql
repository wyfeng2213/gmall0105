-- auto Generated on 2019-08-02 16:48:51 
-- DROP TABLE IF EXISTS `pms_product_vertify_record`; 
CREATE TABLE pms_product_vertify_record(
    `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
    `product_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'productId',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'createTime',
    `vertify_man` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'vertifyMan',
    `status` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'status',
    `detail` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'detail',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'pms_product_vertify_record';


