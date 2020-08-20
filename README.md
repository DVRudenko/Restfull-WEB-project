# RESTful-WEB-project
Educational RESTful web project without user interface.  Spring MVC. Java 8. MySQL. Spring JWTSecurity. Back-end only. API access from Postman

                                  Описание проекта
                                  Ver 1.3


Моя бизнес-логика. По объявлениям делаю что-то вроде kufar.by, а по продажам и диспутам как на aliexpress

1.	Есть пользователи, у которых есть профиль, роль (админ или user), рейтинг (число, которое растет или падает) и некая сумма денег на профиле (за которые продвигаются объявления и покупаются товары)
2.	У пользователя есть купоны, которые он получает где-то по акциям и он может «пополнить» ими свой счет в профиле.
3.	Объявления. Публикуются по темам (из отдельной БД), есть ранг объявления (из отдельной БД, поднять который можно за деньги) и цена (adv_price), которую просит автор. У объявлений могут быть комменты (или переписка).
4.	История продаж. Формируются когда покупатель и продавец договорились по цене (sell_price). Есть статус продажи (PAID, SEND, RECEIVED, CANCELLED). До тех пор пока покупатель не подтвердил получение товара, продавец денег не получит. Покупатель может оставить отзыв о продавце (влияет на его рейтинг) или отменить покупку.
5.	Диспуты. Ведутся только по проданному товару. Переписка между продавцом и покупателем в т.ч. по поводу возврат товара.
6.	Возврат товара через диспут. Товар поменяет статус на CANCELLED. Деньги продавцу не перечисляются, а возвращаются покупателю

Везде первичный ключ – id

                                          ОПИСАНИЕ БД
Таблица users
(тут храним логин, пароль и роль (юзер или админ) пользователя)

`id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
`role`  ENUM ('USER', 'ADMIN') NOT NULL DEFAULT 'USER',

Таблица profiles
(тут храним всю инфу о профиле пользователя, его балансе денег в системе и его рейтинг)

`id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `first_name` VARCHAR(45) NULL,
  `second_name` VARCHAR(45) NULL,
  `date_of_birth` DATE NULL,
  `city` VARCHAR(45) NULL,
  `avatar` VARCHAR(45) NULL,
  `money_balance` INT NOT NULL DEFAULT 1000, - пусть просто остаток денег
  `user_rank` INT NOT NULL DEFAULT 0,

Таблица coupons связь многие к одному с users
(тут храним инфу о купонах пользователя и размерах скидки, которые они дают) 

`id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
`discount` INT NOT NULL,
  `coupon_name` VARCHAR(45) NOT NULL,
Таблица advert_topics – связь один ко многим с adverts
(тут храним инфу о темах объявлений – редактирует только админ)

  `id` INT NOT NULL AUTO_INCREMENT,
`topic_name` VARCHAR(45) NULL,
`topic_sub_name` VARCHAR(45) NULL

Таблица advert_ranks – связь один ко многим с adverts
(тут храним инфу о рейтинге объявлений и стоимости проплаты для его повышения  – редактирует только админ)

  `id` INT NOT NULL AUTO_INCREMENT,
`rank_name` ENUM ('USUAL', 'PRIOR', 'VIP') NOT NULL DEFAULT 'USUAL',
  `rank_price` INT NOT NULL  DEFAULT '0',

Таблица adverts – связь многие к одному с users, advert_topics, advert_ranks
(тут храним инфу об объявлении, его рейтинге и статусе (актуально/продано/снято и т.п.)

`id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `adv_topic_id` INT NOT NULL,
  `adv_rank_id` INT NOT NULL,
  `adv_text` VARCHAR(200) NOT NULL,
  `adv_price` INT NOT NULL,
  `adv_date` DATE NOT NULL,
  `adv_status` ENUM ('NEW', 'SOLD', 'DELETED') NOT NULL DEFAULT 'NEW',

Таблица comments – связь многие к одному с users, adverts
(тут храним инфу о комментариях к объявлениям)

`id` INT NOT NULL,
  `adv_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `commeny_date` DATE NOT NULL,
  `comment_text` VARCHAR(200) NOT NULL,

Таблица sell_histories – связь многие к одному с users, adverts
(тут храним инфу об истории продаж и финальном отзыве пользователя)
Поле status – оплачено/отослано/получено/возврат

`id` INT NOT NULL AUTO_INCREMENT,
  `buyer_id` INT NOT NULL,
  `seller_id` INT NOT NULL,
  `adv_id` INT NOT NULL,
  `sell_date` DATE NOT NULL,
  `sell_price` INT NOT NULL,
  `sell_status` ENUM ('PAID', 'SEND', 'RECEIVED', 'CANCELLED') NOT NULL DEFAULT 'PAID',
  `buyer_review` VARCHAR(45) NULL,

Таблица debates – связь многие к одному с users, sell_histories
(тут храним инфу о диспутах после продаж товара)

`id` INT NOT NULL,
  `seller_id` INT NOT NULL,
  `buyer_id` INT NOT NULL,
  `sell_history_id` INT NOT NULL,
  `debate_date` DATE NOT NULL,
  `debate_text` VARCHAR(200) NOT NULL,
