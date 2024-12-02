BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, favorites;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);
CREATE TABLE favorites (
	favorite_id SERIAL,
	user_id int NOT NULL,
	business_id varchar(100) NOT NULL,
	business_name varchar(50) NOT NULL,
	business_address varchar(100),
	business_website varchar(200),
	CONSTRAINT pk_favorites PRIMARY KEY (favorite_id),
	CONSTRAINT fk_favorites_users FOREIGN KEY (user_id) REFERENCES users (user_id)
);
COMMIT TRANSACTION;
