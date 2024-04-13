BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, brewer, brewery, brewery_beer;
--DROP TABLE IF EXISTS brewery_beer;
--DROP TABLE IF EXISTS brewery, brewery_beer;
--DROP TABLE IF EXISTS users;

CREATE TABLE users (
 	user_id SERIAL,
 	username varchar(50) NOT NULL UNIQUE,
 	password_hash varchar(200) NOT NULL,
 	role varchar(50) NOT NULL,
 	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE brewer (
  	username varchar(50) NOT NULL UNIQUE,
  	brewer_name varchar(50) NOT NULL UNIQUE,
  	CONSTRAINT PK_username PRIMARY KEY (username),
 	CONSTRAINT FK_username FOREIGN KEY (username) REFERENCES users (username)
);

CREATE TABLE brewery (
 	brewery_id SERIAL,
 	brewery_name varchar(100) NOT NULL UNIQUE,
 	brewer_name varchar(50) NOT NULL,
 	contact_information varchar(100),
 	street_address varchar(100),
 	city varchar(50),
 	state_code char(2),
 	postal_code varchar(10),
 	logo_filename varchar(100),
 	brewery_url varchar(200),
 	menu_url varchar(200),
 	ocba_info_url varchar(200),
 	map_url varchar(1000),
 	CONSTRAINT PK_brewery PRIMARY KEY (brewery_id),
 	CONSTRAINT FK_brewer_name FOREIGN KEY (brewer_name) REFERENCES brewer (brewer_name)
);

CREATE TABLE brewery_beer (
	brewery_beer_id SERIAL,
	brewery_id int NOT NULL,
	beer_name varchar(100) NOT NULL,
	beer_description varchar(100),
	beer_type varchar(50) NOT NULL,
	abv decimal(4,1) NOT NULL,
	num_ratings int NOT NULL CONSTRAINT DF_num_ratings DEFAULT(0),
	avg_rating decimal(4,2) CONSTRAINT DF_avg_ratings DEFAULT(0),
	last_active date NOT NULL,
	CONSTRAINT PK_brewery_beer PRIMARY KEY (brewery_beer_id),
	CONSTRAINT FK_brewery_id FOREIGN KEY (brewery_id) REFERENCES brewery (brewery_id)
);

COMMIT TRANSACTION;