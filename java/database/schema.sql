BEGIN TRANSACTION;

-- Drop the existing tables if they exist
DROP TABLE IF EXISTS users, favorites, profile, image;

-- Create users table
CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    username varchar(50) NOT NULL UNIQUE,
    password_hash varchar(200) NOT NULL,
    role varchar(50) NOT NULL
);

-- Create favorites table
CREATE TABLE favorites (
    favorite_id SERIAL PRIMARY KEY,
    user_id int NOT NULL,
    business_id varchar(100) NOT NULL,
    business_name varchar(100) NOT NULL,
    business_address1 varchar(100),
    business_address2 varchar(50),
    business_city varchar(100),
    business_state varchar(50),
    business_zipcode varchar(15),
    business_image varchar(255),
    business_url varchar(255),
    CONSTRAINT fk_favorites_users FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE
);

-- Create profile table
CREATE TABLE profile (
    profile_id SERIAL PRIMARY KEY,
    user_id int NOT NULL,
	first_name varchar(50) NOT NULL,
	last_name varchar(50) NOT NULL,
    birth_month varchar(9) NOT NULL,
    birth_day int NOT NULL,
    birth_year int NOT NULL,
    address1 varchar(150) NOT NULL,
    address2 varchar(50),
    city varchar(100) NOT NULL,
    state_abbr varchar(2) NOT NULL,
    zipcode varchar(5) NOT NULL,
	is_form_submitted BOOLEAN DEFAULT FALSE, -- Default value is false (form not submitted)
    CONSTRAINT fk_profile_users FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE
);

-- Create image table for storing profile images
CREATE TABLE image (
    image_id SERIAL PRIMARY KEY,
    user_id int NOT NULL,
	image_name varchar (100),
    image BYTEA,  -- Store the binary data of the profile image here
    CONSTRAINT fk_image_users FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE
);

-- Commit the transaction
COMMIT TRANSACTION;