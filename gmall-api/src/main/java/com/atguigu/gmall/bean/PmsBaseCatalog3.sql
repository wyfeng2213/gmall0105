-- auto Generated on 2019-08-02 16:48:10 
-- DROP TABLE IF EXISTS `pms_base_catalog3`; 
CREATE TABLE pms_base_catalog3(
    `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
    `name` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'name',
    `catalog2_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'catalog2Id',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'pms_base_catalog3';




