-- -----------------------------------------------------
-- Fill Data iMarket ver 1.0 
-- -----------------------------------------------------

USE iMarket ;

-- -----------------------------------------------------
-- fill users
-- -----------------------------------------------------
INSERT INTO users (login, password, role) values ( 'admin','$2a$04$UoIv/26fwNnjmPB0D5JkI.rFkrUxUArF7zXfMz1cs4zEvgXfEBEiO','ADMIN'); -- password - admin
INSERT INTO users (login, password, role) values ( 'user1','pass1','USER');
INSERT INTO users (login, password, role) values ( 'user2','pass2','USER');
INSERT INTO users (login, password, role) values ( 'user3','pass3','USER');
INSERT INTO users (login, password, role) values ( 'user4','pass4','USER');
INSERT INTO users (login, password, role) values ( 'user5','pass5','USER');

-- -----------------------------------------------------
-- fill profile
-- -----------------------------------------------------
INSERT INTO profile (user_id, avatar, money_balance, user_rank) values ( 1,'Master of market', 0, 1000);		-- 1
INSERT INTO profile (user_id, money_balance, user_rank) values ( 2,1000,10);									-- 2
INSERT INTO profile (user_id, money_balance, user_rank) values ( 3,500,5);										-- 3
INSERT INTO profile (user_id, money_balance, user_rank) values ( 4,800,15);									    -- 4
INSERT INTO profile (user_id, first_name, last_name, date_of_birth, city, avatar, money_balance, user_rank) 	-- 5
	values (5,'Vasiliy', 'Ivanov', '2000-03-02', 'Minsk', 'Partizan', 5000,20);
INSERT INTO profile (user_id, first_name, last_name, date_of_birth, city, avatar, money_balance, user_rank) 	-- 6
	values (6,'John', 'Smith', '1990-01-01', 'London', 'Harry Potter', 2000,10);
-- -----------------------------------------------------
-- fill coupons
-- -----------------------------------------------------
INSERT INTO coupons (user_id, discount, coupon_name) values ( 2,50, 'New user');
INSERT INTO coupons (user_id, discount, coupon_name) values ( 2,10, 'Evroopt');
INSERT INTO coupons (user_id, discount, coupon_name) values ( 2,10, 'Belneftehim');
INSERT INTO coupons (user_id, discount, coupon_name) values ( 3,50, 'New user');
INSERT INTO coupons (user_id, discount, coupon_name) values ( 4,50, 'New user');
INSERT INTO coupons (user_id, discount, coupon_name) values ( 5,10, 'Belneftehim');
INSERT INTO coupons (user_id, discount, coupon_name) values ( 6,50, 'New user');
INSERT INTO coupons (user_id, discount, coupon_name) values ( 6,100, 'Lottery');
-- -----------------------------------------------------
-- fill advert_topics
-- -----------------------------------------------------
INSERT INTO advert_topics (topic_name) values ( 'Auto');			-- 1
INSERT INTO advert_topics (topic_name) values ( 'Real Estate');		-- 2
INSERT INTO advert_topics (topic_name) values ( 'Phones');			-- 3
INSERT INTO advert_topics (topic_name) values ( 'Appliances');		-- 4
INSERT INTO advert_topics (topic_name) values ( 'Electronics');		-- 5
INSERT INTO advert_topics (topic_name) values ( 'Clothes');			-- 6
INSERT INTO advert_topics (topic_name) values ( 'Pets');			-- 7
INSERT INTO advert_topics (topic_name) values ( 'Home');			-- 8
INSERT INTO advert_topics (topic_name) values ( 'Office');			-- 9
INSERT INTO advert_topics (topic_name) values ( 'Services');		-- 10
-- -----------------------------------------------------
-- fill advert_ranks
-- -----------------------------------------------------
INSERT INTO advert_ranks (rank_name, rank_price) values ( 'USUAL',0);
INSERT INTO advert_ranks (rank_name, rank_price) values ( 'PRIOR',5);
INSERT INTO advert_ranks (rank_name, rank_price) values ( 'VIP',20);
-- -----------------------------------------------------
-- fill advert
-- -----------------------------------------------------
-- user 2
INSERT INTO advert (user_id, adv_topic_id, adv_type, adv_rank_id, adv_text, adv_price, adv_date, adv_status)
	values ( 2, 3, 'SELL', 1,'Mobile iPhone X', 100, '2020-06-04', 'NEW');
INSERT INTO advert (user_id, adv_topic_id, adv_type, adv_rank_id, adv_text, adv_price, adv_date, adv_status)
	values ( 2, 3, 'SELL', 1,'Mobile Nokia', 50, '2020-06-01', 'SOLD');
INSERT INTO advert (user_id, adv_topic_id, adv_type, adv_rank_id, adv_text, adv_price, adv_date, adv_status)
	values ( 2, 3, 'SELL', 1,'Mobile Nokia 3110', 30, '2020-05-01', 'SOLD');    
INSERT INTO advert (user_id, adv_topic_id, adv_type, adv_rank_id, adv_text, adv_price, adv_date, adv_status)
	values ( 2, 5, 'SELL', 2,'Laptop HP ', 50, '2020-06-05', 'NEW');
-- user 3
INSERT INTO advert (user_id, adv_topic_id, adv_type, adv_rank_id, adv_text, adv_price, adv_date, adv_status)
	values ( 3, 1, 'SELL', 1,'Auto Audi-100 1998 y - 120 hp ', 2000, '2020-06-01', 'NEW');    
INSERT INTO advert (user_id, adv_topic_id, adv_type, adv_rank_id, adv_text, adv_price, adv_date, adv_status)
	values ( 3, 1, 'SELL', 1,'VW Passat-B5 2001 y - 98 hp ', 2500, '2020-06-04', 'NEW'); 
-- user 4
INSERT INTO advert (user_id, adv_topic_id, adv_type, adv_rank_id, adv_text, adv_price, adv_date, adv_status)
	values ( 4, 6, 'SELL', 1,'Nice Coat 42 size', 50, '2020-06-01', 'NEW');        
INSERT INTO advert (user_id, adv_topic_id, adv_type, adv_rank_id, adv_text, adv_price, adv_date, adv_status)
	values ( 4, 6, 'SELL', 1,'Jeans for men 32 sixe  ', 25, '2020-06-01', 'NEW');    
INSERT INTO advert (user_id, adv_topic_id, adv_type, adv_rank_id, adv_text, adv_price, adv_date, adv_status)
	values ( 4, 6, 'SELL', 1,'Jacket', 30, '2020-06-01', 'NEW');        
INSERT INTO advert (user_id, adv_topic_id, adv_type, adv_rank_id, adv_text, adv_price, adv_date, adv_status)
	values ( 4, 6, 'SELL', 1,'T shirt men 40 sixe  ', 10, '2020-06-01', 'NEW');
INSERT INTO advert (user_id, adv_topic_id, adv_type, adv_rank_id, adv_text, adv_price, adv_date, adv_status)
	values ( 4, 6, 'SELL', 1,'Boots 39 size', 30, '2020-05-20', 'SOLD');
INSERT INTO advert (user_id, adv_topic_id, adv_type, adv_rank_id, adv_text, adv_price, adv_date, adv_status)
	values ( 4, 6, 'SELL', 1,'Sneakers Adidas 43 size', 50, '2020-05-22', 'SOLD');
INSERT INTO advert (user_id, adv_topic_id, adv_type, adv_rank_id, adv_text, adv_price, adv_date, adv_status)
	values ( 4, 6, 'SELL', 1,'Trousers 32 size', 20, '2020-05-23', 'SOLD');
INSERT INTO advert (user_id, adv_topic_id, adv_type, adv_rank_id, adv_text, adv_price, adv_date, adv_status)
	values ( 4, 6, 'SELL', 1,'Sneakers Reebok 38 size', 40, '2020-05-24', 'SOLD');
-- user 5
INSERT INTO advert (user_id, adv_topic_id, adv_type, adv_rank_id, adv_text, adv_price, adv_date, adv_status)
	values ( 5, 2, 'SELL', 2,'Nice house in Minsk 300 sq. m', 40000, '2020-06-01', 'NEW');
INSERT INTO advert (user_id, adv_topic_id, adv_type, adv_rank_id, adv_text, adv_price, adv_date, adv_status)
	values ( 5, 2, 'BUY', 1,'House in Grodno about 200 sq. m', 30000, '2020-06-01', 'NEW');
-- user 6
INSERT INTO advert (user_id, adv_topic_id, adv_type, adv_rank_id, adv_text, adv_price, adv_date, adv_status)
	values ( 6, 2, 'RENT', 3,'Rent a house in Grodno', 400, '2020-06-02', 'NEW');   