-- auto Generated on 2019-08-02 16:45:13 
-- DROP TABLE IF EXISTS `payment_info`; 
CREATE TABLE payment_info(
    `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
    `order_sn` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'orderSn',
    `order_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'orderId',
    `alipay_trade_no` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'alipayTradeNo',
    `total_amount` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'totalAmount',
    `subject` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'subject',
    `payment_status` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'paymentStatus',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'createTime',
    `confirm_time` DATETIME NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT 'confirmTime',
    `callback_content` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'callbackContent',
    `callback_time` DATETIME NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT 'callbackTime',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'payment_info';
