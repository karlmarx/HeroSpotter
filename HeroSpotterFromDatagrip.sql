DROP DATABASE IF EXISTS HeroSpotter;
CREATE DATABASE HeroSpotter;
USE HeroSpotter;

create table HeroSpotter.locations
(
    id       smallint auto_increment
        primary key,
    name     varchar(40)  null,
    address  varchar(200) null,
    place_id varchar(100) null
);

create table HeroSpotter.organizations
(
    id           smallint auto_increment
        primary key,
    name         varchar(40)   not null,
    email        varchar(320)  null,
    place_id    varchar(100) null,
    url          varchar(2083) null,
    phone_number varchar(20)   null,
    address      varchar(200)  null,
    description  text          null
);

create table HeroSpotter.powers
(
    id          smallint auto_increment
        primary key,
    power_name  varchar(40)          not null,
    description tinytext             null,
    is_unique   tinyint(1) default 0 null
);

create table HeroSpotter.supers
(
    id          smallint auto_increment
        primary key,
    name        varchar(40)          not null,
    is_villain  tinyint(1) default 0 null,
    description text                 null
);

create table HeroSpotter.affiliations
(
    super_id smallint not null,
    org_id   smallint not null,
    primary key (super_id, org_id),
    constraint affiliations_ibfk_1
        foreign key (org_id) references HeroSpotter.organizations (id),
    constraint affiliations_ibfk_2
        foreign key (super_id) references HeroSpotter.supers (id)
);

create index org_id
    on HeroSpotter.affiliations (org_id);

create table HeroSpotter.sightings
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
        foreign key (location_id) references HeroSpotter.locations (id),
    constraint sightings_supers_fk
        foreign key (super_id) references HeroSpotter.supers (id)
);

create index location_id
    on HeroSpotter.sightings (location_id);

create index super_id
    on HeroSpotter.sightings (super_id);

create table HeroSpotter.supers_powers
(
    super_id smallint not null,
    power_id smallint not null,
    primary key (super_id, power_id),
    constraint supers_powers_power_fk
        foreign key (power_id) references HeroSpotter.powers (id),
    constraint supers_powers_super_fk
        foreign key (super_id) references HeroSpotter.supers (id)
);

create index power_id
    on HeroSpotter.supers_powers (power_id);

