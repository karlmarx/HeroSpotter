DROP DATABASE IF EXISTS HeroSpotter;
CREATE DATABASE HeroSpotter;
USE HeroSpotter;
CREATE TABLE supers(
    id SMALLINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(40) NOT NULL,
    is_villain boolean default 0,
    description TEXT
);
CREATE TABLE affiliations(
	super_id SMALLINT,
	org_id SMALLINT, 
	PRIMARY KEY (super_id, org_id)
);
CREATE TABLE organizations(
    id SMALLINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(40) NOT NULL,
    email VARCHAR(320),
    latitude DECIMAL(10,6),
    longitude DECIMAL(10,6),
    url VARCHAR(2083),
    phone_number VARCHAR(20),
        address VARCHAR(200),
    description TEXT
    );
CREATE TABLE sightings(
    id SMALLINT PRIMARY KEY AUTO_INCREMENT,
    location_id SMALLINT NOT NULL,
    super_id SMALLINT NOT NULL,
    date DATE NOT NULL,
    image_file MEDIUMBLOB,
    image_approved BOOLEAN default 0,
    reporter_name VARCHAR(40)
);
CREATE TABLE locations(
    id SMALLINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(40),
   	latitude DECIMAL(10,6) NOT NULL,
    longitude DECIMAL(10,6) NOT NULL,
    address VARCHAR(200)
    );
CREATE TABLE powers(
    id SMALLINT PRIMARY KEY AUTO_INCREMENT,
    power_name VARCHAR(40) NOT NULL,
    description TEXT,
   	is_unique BOOLEAN DEFAULT 0
);
CREATE TABLE supers_powers(
	super_id SMALLINT,
	power_id SMALLINT, 
	PRIMARY KEY (super_id, power_id)
);

ALTER TABLE sightings
ADD FOREIGN KEY (location_id) REFERENCES locations(id);
ALTER TABLE sightings
ADD FOREIGN KEY (super_id) REFERENCES supers(id);
ALTER TABLE affiliations
ADD FOREIGN KEY (org_id) REFERENCES organizations(id);
ALTER TABLE affiliations
ADD FOREIGN KEY (super_id) REFERENCES supers(id);
ALTER TABLE supers_powers
ADD FOREIGN KEY (power_id) REFERENCES powers(id);
ALTER TABLE supers_powers
ADD FOREIGN KEY (super_id) REFERENCES supers(id);
