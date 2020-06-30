
-- -----------------------------------------------------
-- Schema iMarket ver 1.3 manual final
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS`iMarket`;
CREATE SCHEMA IF NOT EXISTS `iMarket` DEFAULT CHARACTER SET utf8 ;
USE iMarket ;
-- -----------------------------------------------------
-- Table `iMarket`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `iMarket`.`users` ;
CREATE TABLE IF NOT EXISTS `iMarket`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(150) NOT NULL,
  `role`  ENUM ('USER', 'ADMIN') NOT NULL DEFAULT 'USER',
  PRIMARY KEY (`id`))
;
-- -----------------------------------------------------
-- Table `iMarket`.`profiles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `iMarket`.`profiles` ;
CREATE TABLE IF NOT EXISTS `iMarket`.`profiles` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `date_of_birth` DATE NULL,
  `city` VARCHAR(45) NULL,
  `avatar` VARCHAR(45) NULL,
  `money_balance` INT NOT NULL DEFAULT 0,
  `user_rank` INT NOT NULL DEFAULT 0,
   CHECK (money_balance>=0),
   CHECK (user_rank>=0),
  PRIMARY KEY (`id`),
  FOREIGN KEY (`user_id`)
    REFERENCES `iMarket`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
;

-- -----------------------------------------------------
-- Table `imarket`.`coupons`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `imarket`.`coupons` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `discount` INT NOT NULL,
  `coupon_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_credit_cards_users1_idx` (`user_id` ASC) ,
  CONSTRAINT `fk_credit_cards_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `imarket`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
;

-- -----------------------------------------------------
-- Table `iMarket`.`advert_topics`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `iMarket`.`advert_topics` ;

CREATE TABLE IF NOT EXISTS `iMarket`.`advert_topics` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `topic_name` VARCHAR(45) NOT NULL,
  `topic_sub_name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
;

-- -----------------------------------------------------
-- Table `iMarket`.`advert_ranks`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `iMarket`.`advert_ranks` ;

CREATE TABLE IF NOT EXISTS `iMarket`.`advert_ranks` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `rank_name` ENUM ('USUAL', 'PRIOR', 'VIP') NOT NULL DEFAULT 'USUAL',
  `rank_price` INT NOT NULL  DEFAULT '0',
  CHECK (rank_price>=0),
  PRIMARY KEY (`id`))
;
-- -----------------------------------------------------
-- Table `iMarket`.`adverts`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `iMarket`.`adverts` ;

CREATE TABLE IF NOT EXISTS `iMarket`.`adverts` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `adv_topic_id` INT NOT NULL,
  `adv_type` ENUM ('BUY', 'SELL', 'RENT') NOT NULL DEFAULT 'SELL',
  `adv_rank_id` INT NOT NULL,
  `adv_text` VARCHAR(200) NOT NULL,
  `adv_price` INT NOT NULL,
  `adv_date` DATE NOT NULL,
  `adv_status` ENUM ('NEW', 'SOLD', 'DELETED') NOT NULL DEFAULT 'NEW',
  CHECK (adv_price>=0),
  PRIMARY KEY (`id`),
  INDEX `fk_adverts_users1_idx` (`user_id` ASC) ,
  INDEX `fk_adverts_advert_topics1_idx` (`adv_topic_id` ASC) ,
  INDEX `fk_adverts_advert_ranks1_idx` (`adv_rank_id` ASC) ,
  CONSTRAINT `fk_adverts_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `iMarket`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_adverts_advert_topics1`
    FOREIGN KEY (`adv_topic_id`)
    REFERENCES `iMarket`.`advert_topics` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
 CONSTRAINT `fk_adverts_advert_ranks1`
    FOREIGN KEY (`adv_rank_id`)
    REFERENCES `iMarket`.`advert_ranks` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
;
-- -----------------------------------------------------
-- Table `iMarket`.`comments`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `iMarket`.`comments` ;

CREATE TABLE IF NOT EXISTS `iMarket`.`comments` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `adv_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `comment_date` DATE NOT NULL,
  `comment_text` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_comments_adverts1_idx` (`adv_id` ASC) ,
  INDEX `fk_comments_users1_idx` (`user_id` ASC) ,
  CONSTRAINT `fk_comments_adverts1`
    FOREIGN KEY (`adv_id`)
    REFERENCES `iMarket`.`adverts` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_comments_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `iMarket`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
;
-- -----------------------------------------------------
-- Table `iMarket`.`sell_histories`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `iMarket`.`sell_histories` ;

CREATE TABLE IF NOT EXISTS `iMarket`.`sell_histories` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `buyer_id` INT NOT NULL,
  `seller_id` INT NOT NULL,
  `adv_id` INT NOT NULL,
  `sell_date` DATE NOT NULL,
  `sell_price` INT NOT NULL,
  `sell_status` ENUM ('PAID', 'SEND', 'RECEIVED', 'CANCELLED') NOT NULL DEFAULT 'PAID',
  `buyer_review` VARCHAR(45) NULL,
  CHECK (sell_price>=0),
  PRIMARY KEY (`id`),
  INDEX `fk_sell_histories_users1_idx` (`seller_id` ASC) ,
  INDEX `fk_sell_histories_users2_idx` (`buyer_id` ASC) ,
  INDEX `fk_sell_histories_adverts1_idx` (`adv_id` ASC) ,
  CONSTRAINT `fk_sell_histories_users1_idx`
    FOREIGN KEY (`seller_id`)
    REFERENCES `iMarket`.`users` (`id`)
   ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_sell_histories_users2_idx`
    FOREIGN KEY (`buyer_id`)
    REFERENCES `iMarket`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
CONSTRAINT `fk_sell_histories_adverts1_idx`
    FOREIGN KEY (`adv_id`)
    REFERENCES `iMarket`.`adverts` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
;
-- -----------------------------------------------------
-- Table `iMarket`.`debates`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `iMarket`.`debates` ;

CREATE TABLE IF NOT EXISTS `iMarket`.`debates` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `sell_history_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `debate_date` DATE NOT NULL,
  `debate_text` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_debates_users1_idx` (`user_id` ASC) ,
  INDEX `fk_debates_sell_histories1_idx` (`sell_history_id` ASC) ,
  CONSTRAINT `fk_debates_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `iMarket`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_debates_sell_histories1`
    FOREIGN KEY (`sell_history_id`)
    REFERENCES `iMarket`.`sell_histories` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
;

