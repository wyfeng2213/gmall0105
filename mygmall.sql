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


-- auto Generated on 2019-08-02 16:44:50
-- DROP TABLE IF EXISTS `oms_order`;
CREATE TABLE oms_order(
    `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
    `member_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'memberId',
    `coupon_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'couponId',
    `order_sn` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'orderSn',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'createTime',
    `member_username` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'memberUsername',
    `total_amount` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'totalAmount',
    `pay_amount` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'payAmount',
    `freight_amount` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'freightAmount',
    `promotion_amount` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'promotionAmount',
    `integration_amount` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'integrationAmount',
    `coupon_amount` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'couponAmount',
    `discount_amount` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'discountAmount',
    `pay_type` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'payType',
    `source_type` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'sourceType',
    `status` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'status',
    `order_type` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'orderType',
    `delivery_company` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'deliveryCompany',
    `delivery_sn` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'deliverySn',
    `auto_config_day` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'autoConfigDay',
    `integration` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'integration',
    `growth` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'growth',
    `promotion_info` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'promotionInfo',
    `bill_type` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'billType',
    `bill_header` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'billHeader',
    `bill_content` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'billContent',
    `bill_receiver_phone` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'bill_receiverPhone',
    `bill_receiver_email` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'billReceiverEmail',
    `receiver_name` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'receiverName',
    `receiver_phone` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'receiverPhone',
    `receiver_post_code` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'receiverPostCode',
    `receiver_province` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'receiverProvince',
    `receiver_city` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'receiverCity',
    `receiver_region` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'receiverRegion',
    `receiver_detail_address` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'receiverDetailAddress',
    `note` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'note',
    `confirm_status` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'confirmStatus',
    `delete_status` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'deleteStatus',
    `use_integration` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'useIntegration',
    `payment_time` DATETIME NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT 'paymentTime',
    `delivery_time` DATETIME NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT 'deliveryTime',
    `receive_time` DATETIME NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT 'receiveTime',
    `comment_time` DATETIME NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT 'commentTime',
    `modify_time` DATETIME NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT 'modifyTime',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'oms_order';


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


-- auto Generated on 2019-08-02 16:47:30
-- DROP TABLE IF EXISTS `pms_base_attr_info`;
CREATE TABLE pms_base_attr_info(
    `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
    `attr_name` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'attrName',
    `catalog3_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'catalog3Id',
    `is_enabled` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'isEnabled',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'pms_base_attr_info';

-- auto Generated on 2019-08-02 16:45:36
-- DROP TABLE IF EXISTS `pms_base_attr_value`;
CREATE TABLE pms_base_attr_value(
    `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
    `value_name` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'valueName',
    `attr_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'attrId',
    `is_enabled` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'isEnabled',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'pms_base_attr_value';


-- auto Generated on 2019-08-02 16:45:49
-- DROP TABLE IF EXISTS `pms_base_catalog1`;
CREATE TABLE pms_base_catalog1(
    `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
    `name` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'name',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'pms_base_catalog1';


-- auto Generated on 2019-08-02 16:48:01
-- DROP TABLE IF EXISTS `pms_base_catalog2`;
CREATE TABLE pms_base_catalog2(
    `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
    `name` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'name',
    `catalog1_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'catalog1Id',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'pms_base_catalog2';

-- auto Generated on 2019-08-02 16:48:10
-- DROP TABLE IF EXISTS `pms_base_catalog3`;
CREATE TABLE pms_base_catalog3(
    `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
    `name` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'name',
    `catalog2_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'catalog2Id',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'pms_base_catalog3';


-- auto Generated on 2019-08-02 16:50:48
-- DROP TABLE IF EXISTS `pms_product_image`;
CREATE TABLE pms_product_image(
    `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
    `product_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'productId',
    `img_name` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'imgName',
    `img_url` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'imgUrl',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'pms_product_image';


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


-- auto Generated on 2019-08-02 16:48:33
-- DROP TABLE IF EXISTS `pms_product_sale_attr`;
CREATE TABLE pms_product_sale_attr(
    `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
    `product_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'productId',
    `sale_attr_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'saleAttrId',
    `sale_attr_name` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'saleAttrName',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'pms_product_sale_attr';


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

-- auto Generated on 2019-08-02 16:53:54
-- DROP TABLE IF EXISTS `pms_search_param`;
CREATE TABLE pms_search_param(
    `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
    `catalog3_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'catalog3Id',
    `keyword` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'keyword',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'pms_search_param';

-- auto Generated on 2019-08-02 16:49:07
-- DROP TABLE IF EXISTS `pms_search_sku_info`;
CREATE TABLE pms_search_sku_info(
    `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
    `sku_name` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'skuName',
    `sku_desc` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'skuDesc',
    `catalog3_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'catalog3Id',
    `price` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'price',
    `sku_default_img` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'skuDefaultImg',
    `hot_score` DECIMAL(14,4) NOT NULL DEFAULT 0 COMMENT 'hotScore',
    `product_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'productId',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'pms_search_sku_info';

-- auto Generated on 2019-08-02 16:49:13
-- DROP TABLE IF EXISTS `pms_sku_attr_value`;
CREATE TABLE pms_sku_attr_value(
    `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
    `attr_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'attrId',
    `value_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'valueId',
    `sku_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'skuId',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'pms_sku_attr_value';

-- auto Generated on 2019-08-02 16:49:21
-- DROP TABLE IF EXISTS `pms_sku_info`;
CREATE TABLE pms_sku_info(
    `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
    `product_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'productId',
    `price` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'price',
    `sku_name` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'skuName',
    `sku_desc` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'skuDesc',
    `weight` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'weight',
    `tm_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'tmId',
    `catalog3_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'catalog3Id',
    `sku_default_img` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'skuDefaultImg',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'pms_sku_info';

-- auto Generated on 2019-08-02 16:49:31
-- DROP TABLE IF EXISTS `pms_sku_sale_attr_value`;
CREATE TABLE pms_sku_sale_attr_value(
    `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
    `sku_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'skuId',
    `sale_attr_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'saleAttrId',
    `sale_attr_value_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'saleAttrValueId',
    `sale_attr_name` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'saleAttrName',
    `sale_attr_value_name` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'saleAttrValueName',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'pms_sku_sale_attr_value';

-- auto Generated on 2019-08-02 16:49:38
-- DROP TABLE IF EXISTS `ums_member`;
CREATE TABLE ums_member(
    `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
    `member_level_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '@GeneratedValue(strategy = GenerationType.IDENTITY)主键返回策略',
    `username` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'username',
    `password` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'password',
    `nickname` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'nickname',
    `phone` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'phone',
    `status` INTEGER(12) NOT NULL DEFAULT -1 COMMENT 'status',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'createTime',
    `icon` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'icon',
    `gender` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'gender',
    `birthday` DATETIME NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT 'birthday',
    `city` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'city',
    `job` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'job',
    `personalized_signature` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'personalizedSignature',
    `source_uid` BIGINT NOT NULL DEFAULT -1 COMMENT 'sourceUid',
    `source_type` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'sourceType',
    `integration` INTEGER(12) NOT NULL DEFAULT -1 COMMENT 'integration',
    `growth` INTEGER(12) NOT NULL DEFAULT -1 COMMENT 'growth',
    `luckey_count` INTEGER(12) NOT NULL DEFAULT -1 COMMENT 'luckeyCount',
    `history_integration` INTEGER(12) NOT NULL DEFAULT -1 COMMENT 'historyIntegration',
    `access_token` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'accessToken',
    `access_code` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'accessCode',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'ums_member';

-- auto Generated on 2019-08-02 16:51:55
-- DROP TABLE IF EXISTS `ums_member_receive_address`;
CREATE TABLE ums_member_receive_address(
    `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
    `member_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'memberId',
    `name` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'name',
    `phone_number` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'phoneNumber',
    `default_status` INTEGER(12) NOT NULL DEFAULT -1 COMMENT 'defaultStatus',
    `post_code` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'postCode',
    `province` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'province',
    `city` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'city',
    `region` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'region',
    `detail_address` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'detailAddress',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'ums_member_receive_address';












