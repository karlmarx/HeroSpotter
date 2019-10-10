DROP DATABASE IF EXISTS HeroSpotter;
CREATE DATABASE HeroSpotter;
USE HeroSpotter;
CREATE TABLE supers(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL,
    heroVillain boolean default 0,
    description TEXT,
    picURL VARCHAR(50)
);
CREATE TABLE organizations(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(30),
    contactMethod VARCHAR(20),
    contactInfo VARCHAR(50),
    description TEXT
);
CREATE TABLE locations(
    id INT PRIMARY KEY AUTO_INCREMENT,
    coordinates POINT SRID 4326,
    name VARCHAR(30),
    address VARCHAR(75),
    description TEXT
);
CREATE TABLE characteristics(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(30),
    strengthWeakness CHAR(1),
    description TEXT
);
CREATE TABLE sightings(
    id INT PRIMARY KEY AUTO_INCREMENT,
    locationId INT NOT NULL,
    superhumanId INT NOT NULL,
    FOREIGN KEY (locationId) REFERENCES locations(id),
    FOREIGN KEY (superhumanId) REFERENCES superhumans(id)
);
CREATE TABLE abilities(
    id INT PRIMARY KEY AUTO_INCREMENT,
    characteristicsId INT NOT NULL,
    superhumanId INT NOT NULL,
    FOREIGN KEY (characteristicsId) REFERENCES characteristics(id),
    FOREIGN KEY (superhumanId) REFERENCES superhumans(id)
);
CREATE TABLE membership(
    id INT PRIMARY KEY AUTO_INCREMENT,
    organizationsId INT NOT NULL,
    superhumanId INT NOT NULL,
    FOREIGN KEY (organizationsId) REFERENCES organizations(id),
    FOREIGN KEY (superhumanId) REFERENCES superhumans(id)
); 