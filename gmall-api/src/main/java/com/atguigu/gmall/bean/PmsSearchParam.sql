-- auto Generated on 2019-08-02 16:53:54 
-- DROP TABLE IF EXISTS `pms_search_param`; 
CREATE TABLE pms_search_param(
    `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
    `catalog3_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'catalog3Id',
    `keyword` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'keyword',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'pms_search_param';
