DROP DATABASE IF EXISTS HeroSpotterTest;
CREATE DATABASE HeroSpotterTest;
USE HeroSpotterTest;

create table HeroSpotterTest.locations
(
    id       smallint auto_increment
        primary key,
    name     varchar(40)  null,
    latitude DECIMAL(10,6) null ,
    longitude DECIMAL(10,6) null,
    address  varchar(200) null,
    place_id varchar(100) null
);

create table HeroSpotterTest.organizations
(
    id           smallint auto_increment
        primary key,
    name         varchar(40)   not null,
        place_id    varchar(100) null,
latitude DECIMAL(10,6) null,
    longitude DECIMAL(10,6) null,
    email        varchar(320)  null,
    url          varchar(2083) null,
    phone_number varchar(20)   null,
    address      varchar(200)  null,
    description  text          null
);

create table HeroSpotterTest.powers
(
    id          smallint auto_increment
        primary key,
    power_name  varchar(40)          not null,
    description tinytext             null,
    is_unique   tinyint(1) default 0 null
);

create table HeroSpotterTest.supers
(
    id          smallint auto_increment
        primary key,
    name        varchar(40)          not null,
    is_villain  tinyint(1) default 0 null,
    description text                 null
);

create table HeroSpotterTest.affiliations
(
    super_id smallint not null,
    org_id   smallint not null,
    primary key (super_id, org_id),
    constraint affiliations_ibfk_1
        foreign key (org_id) references HeroSpotterTest.organizations (id),
    constraint affiliations_ibfk_2
        foreign key (super_id) references HeroSpotterTest.supers (id)
);

create index org_id
    on HeroSpotterTest.affiliations (org_id);

create table HeroSpotterTest.sightings
(
    id             smallint auto_increment
        primary key,
    location_id    smallint             not null,
    super_id       smallint             not null,
    date           date                 not null,
    image_file     mediumblob           null,
    image_approved tinyint(1) default 0 null,
    reporter_name  varchar(40)          null,
    constraint sightings_locations_fk
        foreign key (location_id) references HeroSpotterTest.locations (id),
    constraint sightings_supers_fk
        foreign key (super_id) references HeroSpotterTest.supers (id)
);

create index location_id
    on HeroSpotterTest.sightings (location_id);

create index super_id
    on HeroSpotterTest.sightings (super_id);

create table HeroSpotterTest.supers_powers
(
    super_id smallint not null,
    power_id smallint not null,
    primary key (super_id, power_id),
    constraint supers_powers_power_fk
        foreign key (power_id) references HeroSpotterTest.powers (id),
    constraint supers_powers_super_fk
        foreign key (super_id) references HeroSpotterTest.supers (id)
);

create index power_id
    on HeroSpotterTest.supers_powers (power_id);


