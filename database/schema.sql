BEGIN TRANSACTION;

-- Drop existing tables
DROP TABLE IF EXISTS users, favorites, profile, image;

-- Users
CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password_hash VARCHAR(200) NOT NULL,
    role VARCHAR(50) NOT NULL
);

-- Favorites
CREATE TABLE favorites (
    favorite_id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    business_id VARCHAR(100) NOT NULL,
    business_name VARCHAR(100) NOT NULL,
    business_address1 VARCHAR(100),
    business_address2 VARCHAR(50),
    business_city VARCHAR(100),
    business_state VARCHAR(50),
    business_zipcode VARCHAR(15),
    business_image VARCHAR(255),
    business_url VARCHAR(255),

    CONSTRAINT uq_favorites_user_business
        UNIQUE (user_id, business_id),

    CONSTRAINT fk_favorites_users
        FOREIGN KEY (user_id)
        REFERENCES users (user_id)
        ON DELETE CASCADE
);

-- Profile
CREATE TABLE profile (
    profile_id SERIAL PRIMARY KEY,
    user_id INT NOT NULL UNIQUE,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    birth_month VARCHAR(9) NOT NULL,
    birth_day INT NOT NULL,
    birth_year INT NOT NULL,
    address1 VARCHAR(150) NOT NULL,
    address2 VARCHAR(50),
    city VARCHAR(100) NOT NULL,
    state_abbr VARCHAR(2) NOT NULL,
    zipcode VARCHAR(5) NOT NULL,
    is_form_submitted BOOLEAN DEFAULT FALSE,

    CONSTRAINT fk_profile_users
        FOREIGN KEY (user_id)
        REFERENCES users (user_id)
        ON DELETE CASCADE
);

-- Profile Image
CREATE TABLE image (
    image_id SERIAL PRIMARY KEY,
    user_id INT NOT NULL UNIQUE,
    image_name VARCHAR(100),
    image BYTEA,

    CONSTRAINT fk_image_users
        FOREIGN KEY (user_id)
        REFERENCES users (user_id)
        ON DELETE CASCADE
);

COMMIT TRANSACTION;