-- -----------------------------------------------------
-- Table `order_master`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `order_master` ;

CREATE TABLE IF NOT EXISTS `order_master` (
 `id` BIGINT(10) NOT NULL AUTO_INCREMENT,
`order_uid` BIGINT(10) NOT NULL COMMENT 'A unique id assigned to each order',
`consumer_id` BIGINT(20) NOT NULL COMMENT 'This Id is read through Kafka, and the same is used within all OA specific micro services wherever applicable',
`store_id` BIGINT(20) NULL COMMENT 'Refers store',
`merchant_id` BIGINT(20) NOT NULL COMMENT 'Refers merchant',
`store_pickup_address_id` BIGINT(10) NULL COMMENT 'Pickup address id of the store',
`consumer_pickup_code` VARCHAR(8) NULL COMMENT 'Pickup code allotted to the order when it is ready',
`consumer_remarks` VARCHAR(255) NULL COMMENT 'Remark added by consumer',
`total_price` INT(10) NULL DEFAULT 0,
`total_item` INT(10) NULL DEFAULT 0,
`currency` VARCHAR(4) NULL,
`order_status` ENUM('NEW', 'CONFIRMED', 'PREPARING', 'REJECTED', 'CANCELLED', 'READYFORPICKUP', 'PICKEDUP', 'NOPICKUP', 'DELIVERED', 'EXPIRED') NULL DEFAULT 'NEW',
`payment_status` ENUM('UNPAID', 'PAID', 'REFUND', 'TRANSFERED', 'FAILEDPAYMENT', 'FAILEDREFUND') NULL DEFAULT 'UNPAID',
`order_ref_number` VARCHAR(30) NULL COMMENT 'Order reference number for BO/User reference',
`order_date` DATETIME NULL COMMENT 'Date of order',
`payment_id` BIGINT(10) NULL COMMENT 'Refers Payment',
`payment_ref_number` VARCHAR(50) NULL COMMENT 'Payment reference number',
`payment_date` DATETIME NULL COMMENT 'Date of payment',
`prepare_time` INT NULL,
`rejection_reason` VARCHAR(500) NULL,
`consumer_latitude` DECIMAL(10,6) NULL,
`consumer_longitude` DECIMAL(10,6) NULL,
`created_at` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
`updated_at` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (`id`),
UNIQUE INDEX `idx_order_uid` (`order_uid` ASC),
INDEX `idx_order_consumer` (`consumer_id` ASC, `store_id` ASC),
INDEX `idx_order_store` (`store_id` ASC),
INDEX `idx_order_merchant` (`merchant_id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `order_item`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `order_item` ;

CREATE TABLE IF NOT EXISTS `order_item` (
`id` BIGINT(10) NOT NULL AUTO_INCREMENT,
`order_id` BIGINT(10) NOT NULL,
`order_item_uid` BIGINT(10) NOT NULL,
`item_id` BIGINT(10) NOT NULL,
`item_store_map_id` BIGINT(10) NOT NULL,
`name` VARCHAR(100) NULL,
`price` INT(8) NULL,
`quantity` INT(8) NULL,
`image_url` JSON NULL,
`item_options` JSON NULL COMMENT 'JSON containing the list of options selected by consumer for the item, each with name, price and option group',
`created_at` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
`updated_at` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (`id`),
INDEX `idx_orderitem_orderid` (`order_id` ASC),
UNIQUE INDEX `idx_order_item_uid` (`order_item_uid` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `order_status_trails`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `order_status_trails` ;

CREATE TABLE IF NOT EXISTS `order_status_trails` (
`id` BIGINT(10) NOT NULL AUTO_INCREMENT,
`order_id` BIGINT(10) NOT NULL,
`order_status` ENUM('NEW', 'CONFIRMED', 'PREPARING', 'REJECTED', 'CANCELLED', 'READYFORPICKUP', 'PICKEDUP', 'NOPICKUP', 'DELIVERED', 'EXPIRED') NOT NULL,
`status_on` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
`type` ENUM('STORE', 'CONSUMER') NOT NULL,
PRIMARY KEY (`id`),
INDEX `idx_ordstatus_ordmaster` (`order_id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `order_pickup_code`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `order_pickup_code` ;

CREATE TABLE IF NOT EXISTS `order_pickup_code` (
`id` BIGINT(10) NOT NULL AUTO_INCREMENT,
`store_id` BIGINT(20) NOT NULL,
`current_value` INT(4) NOT NULL DEFAULT 0,
PRIMARY KEY (`id`))
ENGINE = InnoDB;
