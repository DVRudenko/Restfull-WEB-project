-- -----------------------------------------------------
-- Fill Data iMarket ver 1.2
-- -----------------------------------------------------

USE iMarket ;

-- -----------------------------------------------------
-- fill users
-- -----------------------------------------------------
INSERT INTO users (login, password, role) values ( 'admin','$2a$04$UoIv/26fwNnjmPB0D5JkI.rFkrUxUArF7zXfMz1cs4zEvgXfEBEiO','ROLE_ADMIN'); -- password - admin
INSERT INTO users (login, password, role) values ( 'user1','$2a$04$cvLPhDFUMgn7tkkEziFde./hIrax5WQcWWB/Dka.texOAUITKpEwm','ROLE_USER');	-- password - user1
INSERT INTO users (login, password, role) values ( 'user2','$2a$04$b1WxKsOUR7WktR0fI8Nnfe269Beuwz43Ja6CbmDvgIGhXYOOT5m0S','ROLE_USER');	-- password - user2
INSERT INTO users (login, password, role) values ( 'user3','$2a$04$/FgFUjmQGpideWx6cUrNqOYZc3FhA66VM9XbxJ5SDhajZPva2uLju','ROLE_USER');	-- password - user3
INSERT INTO users (login, password, role) values ( 'user4','$2a$04$nf6cUE9VgOoDGneG8H0kKOdz4O.76LNvHz5EWwb8LsQJQLTyJm71K','ROLE_USER');	-- password - user4
INSERT INTO users (login, password, role) values ( 'user5','$2a$04$CtTBQZAABC4FpjsHFg5Pdu5GBa4H2XH7HXMlh5nBS0ZFH1Lb9FEA2','ROLE_USER');	-- password - user5

-- -----------------------------------------------------
-- fill profiles
-- -----------------------------------------------------
INSERT INTO profiles (user_id, avatar, money_balance, user_rank) values ( 1,'Master of market', 0, 1000);		-- 1
INSERT INTO profiles (user_id, money_balance, user_rank) values ( 2,1000,10);									-- 2
INSERT INTO profiles (user_id, money_balance, user_rank) values ( 3,500,5);										-- 3
INSERT INTO profiles (user_id, money_balance, user_rank) values ( 4,800,15);									-- 4
INSERT INTO profiles (user_id, first_name, last_name, date_of_birth, city, avatar, money_balance, user_rank) 	-- 5
	values (5,'Vasiliy', 'Ivanov', '2000-03-02', 'Minsk', 'Partizan', 5000,20);
INSERT INTO profiles (user_id, first_name, last_name, date_of_birth, city, avatar, money_balance, user_rank) 	-- 6
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
INSERT INTO advert_topics (topic_name, topic_sub_name) values ( 'Clothes', 'Men');			-- 6
INSERT INTO advert_topics (topic_name, topic_sub_name) values ( 'Clothes', 'Women');
INSERT INTO advert_topics (topic_name, topic_sub_name) values ( 'Clothes', 'Children');
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
-- fill adverts
-- -----------------------------------------------------
-- user 2
INSERT INTO adverts (user_id, adv_topic_id, adv_type, adv_rank_id, adv_text, adv_price, adv_date, adv_status) 
	values ( 2, 3, 'SELL', 1,'Mobile iPhone X', 100, '2020-06-04', 'NEW'); -- 1
INSERT INTO adverts (user_id, adv_topic_id, adv_type, adv_rank_id, adv_text, adv_price, adv_date, adv_status) 
	values ( 2, 3, 'SELL', 1,'Mobile Nokia', 50, '2020-06-01', 'SOLD'); -- 2
INSERT INTO adverts (user_id, adv_topic_id, adv_type, adv_rank_id, adv_text, adv_price, adv_date, adv_status) 
	values ( 2, 3, 'SELL', 1,'Mobile Nokia 3110. Vintage !!!', 30, '2020-05-01', 'SOLD');   -- 3 
INSERT INTO adverts (user_id, adv_topic_id, adv_type, adv_rank_id, adv_text, adv_price, adv_date, adv_status) 
	values ( 2, 5, 'SELL', 2,'Laptop HP ', 50, '2020-06-05', 'NEW'); -- 3
-- user 3
INSERT INTO adverts (user_id, adv_topic_id, adv_type, adv_rank_id, adv_text, adv_price, adv_date, adv_status) 
	values ( 3, 1, 'SELL', 1,'Auto Audi-100 1998 y - 120 hp ', 2000, '2020-06-01', 'NEW');  -- 4  
INSERT INTO adverts (user_id, adv_topic_id, adv_type, adv_rank_id, adv_text, adv_price, adv_date, adv_status) 
	values ( 3, 1, 'SELL', 1,'VW Passat-B5 2001 y - 98 hp ', 2500, '2020-06-04', 'NEW');  -- 5
-- user 4
INSERT INTO adverts (user_id, adv_topic_id, adv_type, adv_rank_id, adv_text, adv_price, adv_date, adv_status) 
	values ( 4, 6, 'SELL', 1,'Nice Coat 42 size', 50, '2020-06-01', 'NEW');        -- 6
INSERT INTO adverts (user_id, adv_topic_id, adv_type, adv_rank_id, adv_text, adv_price, adv_date, adv_status) 
	values ( 4, 6, 'SELL', 1,'Jeans for men 32 sixe  ', 25, '2020-06-01', 'NEW');    -- 7
INSERT INTO adverts (user_id, adv_topic_id, adv_type, adv_rank_id, adv_text, adv_price, adv_date, adv_status) 
	values ( 4, 6, 'SELL', 1,'Jacket', 30, '2020-06-01', 'NEW');        -- 8
INSERT INTO adverts (user_id, adv_topic_id, adv_type, adv_rank_id, adv_text, adv_price, adv_date, adv_status) 
	values ( 4, 6, 'SELL', 1,'T shirt men 40 sixe  ', 10, '2020-06-01', 'NEW'); -- 9
INSERT INTO adverts (user_id, adv_topic_id, adv_type, adv_rank_id, adv_text, adv_price, adv_date, adv_status) 
	values ( 4, 6, 'SELL', 1,'Boots 39 size', 30, '2020-05-20', 'SOLD'); -- 10
INSERT INTO adverts (user_id, adv_topic_id, adv_type, adv_rank_id, adv_text, adv_price, adv_date, adv_status) 
	values ( 4, 6, 'SELL', 1,'Sneakers Adidas 43 size', 50, '2020-05-22', 'SOLD'); -- 11
INSERT INTO adverts (user_id, adv_topic_id, adv_type, adv_rank_id, adv_text, adv_price, adv_date, adv_status) 
	values ( 4, 6, 'SELL', 1,'Trousers 32 size', 20, '2020-05-23', 'SOLD'); -- 12
INSERT INTO adverts (user_id, adv_topic_id, adv_type, adv_rank_id, adv_text, adv_price, adv_date, adv_status) 
	values ( 4, 6, 'SELL', 1,'Sneakers Reebok 38 size', 40, '2020-05-24', 'SOLD'); -- 13
-- user 5
INSERT INTO adverts (user_id, adv_topic_id, adv_type, adv_rank_id, adv_text, adv_price, adv_date, adv_status) 
	values ( 5, 2, 'SELL', 2,'Nice house in Minsk 300 sq. m', 40000, '2020-06-01', 'NEW');
INSERT INTO adverts (user_id, adv_topic_id, adv_type, adv_rank_id, adv_text, adv_price, adv_date, adv_status) 
	values ( 5, 2, 'BUY', 1,'House in Grodno about 200 sq. m', 30000, '2020-06-01', 'NEW');
-- user 6
INSERT INTO adverts (user_id, adv_topic_id, adv_type, adv_rank_id, adv_text, adv_price, adv_date, adv_status) 
	values ( 6, 2, 'RENT', 3,'Rent a house in Grodno', 400, '2020-06-02', 'NEW');   
    
-- -----------------------------------------------------
-- fill comments
-- -----------------------------------------------------  
-- about adv N 1
INSERT INTO comments (adv_id, user_id, comment_date, comment_text) 
	values ( 1, 5, '2020-06-05', 'How long is it in use?');     
INSERT INTO comments (adv_id, user_id, comment_date, comment_text) 
	values ( 1, 2, '2020-06-06', 'For 3 years');
-- about adv N 2
INSERT INTO comments (adv_id, user_id, comment_date, comment_text) 
	values ( 2, 4, '2020-06-03', 'What is the phone model?');     
INSERT INTO comments (adv_id, user_id, comment_date, comment_text) 
	values ( 2, 2, '2020-06-04', 'Nokia 3310?'); 
INSERT INTO comments (adv_id, user_id, comment_date, comment_text) 
	values ( 2, 4, '2020-06-05', 'What is the phone condition?');
INSERT INTO comments (adv_id, user_id, comment_date, comment_text) 
	values ( 2, 2, '2020-06-06', 'Excelent!'); 
INSERT INTO comments (adv_id, user_id, comment_date, comment_text) 
	values ( 2, 4, '2020-06-07', 'Can offer you 30');
INSERT INTO comments (adv_id, user_id, comment_date, comment_text) 
	values ( 2, 2, '2020-06-08', 'OK'); 
    
-- -----------------------------------------------------
-- fill sell_history
-- -----------------------------------------------------  
-- about adv N 2; seller N 2,  buyer N 4
INSERT INTO sell_histories (buyer_id, seller_id, adv_id, sell_date, sell_price, sell_status, buyer_review) 
	values ( 4, 2, 2, '2020-06-09', 30, 'RECEIVED', 'Everything OK, good seller'); -- 1    
-- about adv N 3; seller N 2,  buyer N 6
INSERT INTO sell_histories (buyer_id, seller_id, adv_id, sell_date, sell_price, sell_status, buyer_review) 
	values ( 6, 2, 3, '2020-05-15', 25, 'SEND', 'Don''t receive yet'); -- 2
-- about adv N 10; seller N 4,  buyer N 3
INSERT INTO sell_histories (buyer_id, seller_id, adv_id, sell_date, sell_price, sell_status) 
	values ( 3, 4, 10, '2020-05-30', 30,'PAID'); -- 3 Paid only
-- about adv N 11; seller N 4,  buyer N 2
INSERT INTO sell_histories (buyer_id, seller_id, adv_id, sell_date, sell_price, sell_status, buyer_review) 
	values ( 2, 4, 11, '2020-05-28', 50,'RECEIVED', 'Good Sneakers'); -- 4 received

-- -----------------------------------------------------
-- fill debates
-- -----------------------------------------------------  
-- about sell_hist N 2; seller N 2,  buyer N 6
INSERT INTO debates (sell_history_id, user_id, debate_date, debate_text) 
	values ( 2, 6, '2020-05-17', 'Did you send my fone?'); -- 1    
INSERT INTO debates (sell_history_id, user_id, debate_date, debate_text) 
	values ( 2, 2, '2020-05-18', 'Not yet, sorry'); -- 2
INSERT INTO debates (sell_history_id, user_id, debate_date, debate_text) 
	values ( 2, 2, '2020-05-19', 'Already sent'); -- 3
