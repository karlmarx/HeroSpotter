create table affiliations
(
    super_id smallint not null,
    org_id   smallint not null,
    primary key (super_id, org_id),
    constraint affiliations_ibfk_1
        foreign key (org_id) references organizations (id),
    constraint affiliations_ibfk_2
        foreign key (super_id) references supers (id)
);

create index org_id
    on affiliations (org_id);

INSERT INTO HeroSpotter.affiliations (super_id, org_id) VALUES (6, 1);
INSERT INTO HeroSpotter.affiliations (super_id, org_id) VALUES (1, 2);
INSERT INTO HeroSpotter.affiliations (super_id, org_id) VALUES (6, 2);
INSERT INTO HeroSpotter.affiliations (super_id, org_id) VALUES (7, 2);
INSERT INTO HeroSpotter.affiliations (super_id, org_id) VALUES (6, 3);
INSERT INTO HeroSpotter.affiliations (super_id, org_id) VALUES (7, 3);
INSERT INTO HeroSpotter.affiliations (super_id, org_id) VALUES (7, 4);
create table locations
(
    id        smallint auto_increment
        primary key,
    name      varchar(40)    null,
    address   varchar(200)   null,
    place_id  varchar(200)   null,
    latitude  decimal(10, 6) null,
    longitude decimal(10, 6) null
);

INSERT INTO HeroSpotter.locations (id, name, address, place_id, latitude, longitude) VALUES (2, 'Karl Marx Industries, LLC1', '2304 Woodbourne Avenue, Apt 3, Louisville, KY 40205', null, 0.000000, 0.000000);
INSERT INTO HeroSpotter.locations (id, name, address, place_id, latitude, longitude) VALUES (6, 'blah blah ', '1200 Story Avenue, Louisville, KY, USA', null, 0.000000, 0.000000);
INSERT INTO HeroSpotter.locations (id, name, address, place_id, latitude, longitude) VALUES (7, 'blah', '1600 Pennsylvania Ave, Jeffersonville, IN, USA', null, 0.000000, 0.000000);
INSERT INTO HeroSpotter.locations (id, name, address, place_id, latitude, longitude) VALUES (13, 'asdfa', 'ASDF, Charai, Borla, Union Park, Chembur, Mumbai, Maharashtra, India', '', 0.000000, 0.000000);
INSERT INTO HeroSpotter.locations (id, name, address, place_id, latitude, longitude) VALUES (18, '[unnamed location no. 401063]', '2343 Strathmoor Boulevard, Louisville, KY, USA', 'ChIJv4db0FsLaYgRNlbO6fZZFZs', 0.000000, 0.000000);
INSERT INTO HeroSpotter.locations (id, name, address, place_id, latitude, longitude) VALUES (19, '[unnamed location no. 351318]', '234 South Main Street, Ashley, OH, USA', '', 0.000000, 0.000000);
INSERT INTO HeroSpotter.locations (id, name, address, place_id, latitude, longitude) VALUES (21, 'askdjflkajdslkfa', '1026 South 4th Street, Louisville, KY, USA', 'ChIJQ6-6dFMNaYgRUXpggiuFXKY', 38.237794, -85.760343);
INSERT INTO HeroSpotter.locations (id, name, address, place_id, latitude, longitude) VALUES (22, '[unnamed location no. 568870]', 'Avenida Callao 1234, Buenos Aires, Argentina', 'ChIJg_IllL3KvJURavpO1lRgUPY', -34.594322, -58.393310);
INSERT INTO HeroSpotter.locations (id, name, address, place_id, latitude, longitude) VALUES (23, '[expletive]ing place', 'Avenida Providencia 1208, Providencia, Chile', 'ChIJHx-CmGLPYpYRHjQAJ-Jv3fQ', -33.428977, -70.621146);
INSERT INTO HeroSpotter.locations (id, name, address, place_id, latitude, longitude) VALUES (24, 'Vapor', '227 E Breckinridge St, Louisville, KY 40203, USA', 'ChIJGVPfnrRyaYgR2YGlyjZl6BQ', 38.241424, -85.750654);
create table organizations
(
    id           smallint auto_increment
        primary key,
    name         varchar(40)    not null,
    email        varchar(320)   null,
    url          varchar(2083)  null,
    phone_number varchar(20)    null,
    address      varchar(200)   null,
    description  text           null,
    place_id     varchar(200)   null,
    latitude     decimal(10, 6) null,
    longitude    decimal(10, 6) null
);

INSERT INTO HeroSpotter.organizations (id, name, email, url, phone_number, address, description, place_id, latitude, longitude) VALUES (1, 'fourth international ', 'trotsky@gmail.com', '', '+3311123156461', '95700 Roissy-en-France, France', 'The Fourth International (FI) is a revolutionary socialist international organisation consisting of followers of Leon Trotsky, or Trotskyists, with the declared goal of helping the working class overthrow capitalism and work toward international communism. The Fourth International was established in France in 1938 as Trotsky and his supporters, having been expelled from the Soviet Union, considered the Third International or Comintern to have become lost to Stalinism and incapable of leading the international working class to political power.', '', 0.000000, 0.000000);
INSERT INTO HeroSpotter.organizations (id, name, email, url, phone_number, address, description, place_id, latitude, longitude) VALUES (2, 'Karl Marx Industries, LLC1', '5042021062karlmarx@gmail.com', 'http://www.karlmarxindustries.com,', 'unknown', '2304 Woodbourne Avenue, Apt 3, Louisville, KY 40205', 'Unicorn sed mustache dolore, meggings swag in small batch fixie cloud bread meditation aliqua bitters shoreditch tumeric. Ipsum magna dreamcatcher raw denim esse fugiat poutine vegan marfa gentrify quinoa retro. Dolore commodo kale chips, chicharrones ennui pug palo santo consectetur letterpress subway tile sunt hoodie. Food truck waistcoat freegan, neutra commodo in crucifix raw denim eu. Pabst blue bottle offal sint +1 cornhole pok pok. Chia PBR&B chicharrones selvage slow-carb, heirloom truffaut ut cred snackwave mixtape. Godard single-origin coffee kitsch tempor.', null, 0.000000, 0.000000);
INSERT INTO HeroSpotter.organizations (id, name, email, url, phone_number, address, description, place_id, latitude, longitude) VALUES (3, 'Karl Marx Industries, LLC2', '5042021062karlmarx@gmail.com', 'unknown', '+23546432135', '200 West Broadway, Louisville, KY, USA', 'Taiyaki listicle flexitarian, ipsum butcher duis pinterest ut et nulla. Fam biodiesel slow-carb commodo tofu, neutra aute banjo nulla paleo wayfarers chillwave hammock gastropub. Jianbing iceland taxidermy, blue bottle bespoke cardigan pickled single-origin coffee selfies pop-up eu franzen 90''s retro organic. Ea tofu pour-over, schlitz quis brooklyn minim cardigan gastropub hashtag paleo scenester whatever. Enim in blue bottle, art party eu artisan skateboard four loko mollit.

', '', 0.000000, 0.000000);
INSERT INTO HeroSpotter.organizations (id, name, email, url, phone_number, address, description, place_id, latitude, longitude) VALUES (4, 'the mouseketeers', 'unknown', 'unknown', 'unknown', 'unknown', 'Keffiyeh brunch try-hard, hell of sriracha ea tbh roof party dreamcatcher craft beer. Authentic raw denim salvia offal fingerstache ea lyft cupidatat pork belly enim ut meh kinfolk est coloring book. Mixtape heirloom offal bushwick dolore velit. Ut voluptate YOLO, cloud bread street art trust fund kale chips kickstarter aesthetic paleo. Pabst jean shorts echo park kogi palo santo, schlitz laborum selvage vegan. Lomo messenger bag pour-over man bun raclette, la croix anim art party blog mumblecore keytar direct trade irure godard tilde. Chambray YOLO unicorn fam, humblebrag hot chicken man braid pinterest prism organic pork belly irony flexitarian shaman.', null, 0.000000, 0.000000);
INSERT INTO HeroSpotter.organizations (id, name, email, url, phone_number, address, description, place_id, latitude, longitude) VALUES (5, 'Karl Marx Industries, LLC', '5042021062karlmarx@gmail.com', 'unknown', '5028886195', '123 East Main Street, Louisville, KY, USA', 'Cold-pressed fixie elit, lo-fi cliche put a bird on it leggings. Ea chia esse blog meggings fingerstache kinfolk asymmetrical letterpress hoodie. Small batch lorem marfa elit. Ullamco 8-bit ugh, adaptogen schlitz mollit hammock wolf crucifix af ramps tilde fam ipsum intelligentsia. Snackwave tofu mixtape migas tilde vaporware, fanny pack freegan disrupt. Culpa reprehenderit cupidatat hot chicken echo park unicorn id, ut sed neutra pariatur single-origin coffee nulla.

', null, 0.000000, 0.000000);
INSERT INTO HeroSpotter.organizations (id, name, email, url, phone_number, address, description, place_id, latitude, longitude) VALUES (6, 'NOw different', 'unknown@gmail.com', 'none listed', 'none listed', '123 East Main Street, Louisville, KY, USA', 'Intelligentsia laborum artisan banjo tofu godard. Trust fund eiusmod vegan cardigan shaman. Etsy master cleanse lo-fi messenger bag ramps, shabby chic actually gluten-free swag pabst kogi pork belly pitchfork. Labore quinoa magna, flannel man bun single-origin coffee readymade seitan fixie heirloom hammock tumeric poutine. Cray cloud bread keytar minim la croix intelligentsia nulla dreamcatcher retro asymmetrical banh mi wolf 8-bit live-edge id. IPhone voluptate try-hard vape cornhole, dolore duis magna heirloom. Occupy esse readymade pok pok occaecat chambray.', '', 0.000000, 0.000000);
INSERT INTO HeroSpotter.organizations (id, name, email, url, phone_number, address, description, place_id, latitude, longitude) VALUES (7, 'unicorns', 'unknown', 'unknown', 'unknown', '23', 'Chia deep v minim flexitarian shaman gentrify narwhal consectetur excepteur authentic schlitz bespoke. Mumblecore austin pickled iPhone pabst swag offal. Health goth next level taxidermy viral four dollar toast pariatur yr irony aliqua lomo direct trade deserunt. Ullamco et in messenger bag trust fund est fixie taxidermy food truck pok pok veniam edison bulb sunt mumblecore. Hot chicken organic succulents la croix taxidermy pinterest truffaut minim heirloom. Aesthetic green juice eiusmod, qui freegan retro helvetica letterpress chia nulla. Mollit food truck ut green juice cold-pressed locavore try-hard dolore tattooed kale chips kogi mlkshk quis.

', null, 0.000000, 0.000000);
INSERT INTO HeroSpotter.organizations (id, name, email, url, phone_number, address, description, place_id, latitude, longitude) VALUES (8, 'radical fairies', 'unknown', 'unknown', 'unknown', '123 East Main Street, Louisville, KY, USA', 'Hella gentrify plaid hell of vaporware vice portland magna, pitchfork fugiat typewriter ipsum. Ut ramps reprehenderit unicorn air plant. Subway tile 3 wolf moon man braid scenester keffiyeh, vice copper mug. Craft beer typewriter eu tote bag bicycle rights vegan polaroid neutra echo park. Literally man bun exercitation, meggings cold-pressed aliqua brooklyn veniam non tacos tote bag green juice photo booth id. Chicharrones vinyl meditation coloring book, in tumeric fixie waistcoat affogato. Lomo air plant forage deserunt, bicycle rights health goth occaecat.', null, 0.000000, 0.000000);
INSERT INTO HeroSpotter.organizations (id, name, email, url, phone_number, address, description, place_id, latitude, longitude) VALUES (9, 'blondes', 'asdf@gmail.com', 'unknown', 'unknown', 'SDF Building, GP Block, Sector V, Bidhannagar, Kolkata, West Bengal, India', 'Venmo distillery laborum reprehenderit DIY jean shorts swag occaecat iceland farm-to-table retro. Austin ut laboris kale chips succulents letterpress. Hot chicken fashion axe magna vaporware minim elit tilde irony selvage mumblecore consectetur. VHS deep v sriracha duis fam vegan laboris wayfarers ut hella. Vice synth typewriter paleo master cleanse. Bespoke adipisicing synth, bitters slow-carb lyft yuccie cold-pressed truffaut subway tile art party four dollar toast cliche you probably haven''t heard of them fashion axe. Ipsum selvage sed franzen kombucha godard meggings meh cred church-key neutra cronut seitan.', null, 0.000000, 0.000000);
INSERT INTO HeroSpotter.organizations (id, name, email, url, phone_number, address, description, place_id, latitude, longitude) VALUES (10, 'brunettes', '5042021062karlmarx@gmail.com', 'unknown', '5028886195', 'Werqanis, Kasımlı/Baykan/Siirt, Turkey', 'Tacos swag poke, non meditation cillum kale chips yr af seitan tofu blog hoodie man braid. Post-ironic craft beer put a bird on it bicycle rights proident, affogato air plant church-key ethical aute copper mug vegan. Pariatur labore flannel polaroid, fam vaporware yr DIY dolore veniam palo santo eiusmod. Aute knausgaard pug disrupt minim tacos la croix cliche venmo.', 'ChIJGXQ01k7rDEARHBgPsMVWq-c', 0.000000, 0.000000);
INSERT INTO HeroSpotter.organizations (id, name, email, url, phone_number, address, description, place_id, latitude, longitude) VALUES (11, 'qrfad', '', '', '', 'Air India GSD Complex, Indira Gandhi International Airport, Delhi, India', 'DIY aliqua microdosing hashtag sunt, squid ramps master cleanse enim. Fashion axe heirloom single-origin coffee coloring book offal ipsum do pork belly duis man bun succulents irure consequat. Mixtape truffaut humblebrag woke sunt. Fashion axe photo booth thundercats echo park subway tile tbh.', 'ChIJXU1uyIQbDTkR1DXrUBbnYRM', 0.000000, 0.000000);
INSERT INTO HeroSpotter.organizations (id, name, email, url, phone_number, address, description, place_id, latitude, longitude) VALUES (12, 'sdfg', 'sdfg@asdkfl.com', '', 'asdf', '1231234', 'Slow-carb mollit copper mug offal asymmetrical laborum blue bottle bespoke. Tilde ex excepteur ugh. Man bun chicharrones viral literally whatever synth bicycle rights venmo cred. Green juice pabst vinyl sunt paleo. Messenger bag cupidatat et mumblecore. Raclette selvage freegan banh mi. Tacos fashion axe four dollar toast hoodie crucifix mumblecore, consectetur pour-over.', '', 0.000000, 0.000000);
INSERT INTO HeroSpotter.organizations (id, name, email, url, phone_number, address, description, place_id, latitude, longitude) VALUES (13, 'shit', 'none listed', 'none listed', 'none listed', '2234 Dundee Road, Louisville, KY, USA', 'fuck this piece of shit', 'ChIJCTNBlKQMaYgRNFSjEnGGQaQ', 0.000000, 0.000000);
INSERT INTO HeroSpotter.organizations (id, name, email, url, phone_number, address, description, place_id, latitude, longitude) VALUES (16, 'asdf [expletive] [expletive] [expletive]', 'unknown@none.org', 'none listed', '1', 'Calle de Albarracín, 33, Madrid, Spain', 'Put a bird on it vexillologist live-edge sint trust fund, blue bottle proident tumeric anim shabby chic. Aliquip lorem kitsch actually vaporware ea celiac pabst tattooed deserunt. Vape dreamcatcher pabst single-origin coffee VHS. Artisan affogato migas irure enamel pin gastropub excepteur brunch austin marfa lyft cupidatat. Waistcoat beard selvage subway tile roof party chillwave food truck. Letterpress air plant 90''s, reprehenderit celiac hexagon heirloom ut swag nisi four loko lyft next level.', '', 40.432675, -3.631033);
create table powers
(
    id          smallint auto_increment
        primary key,
    power_name  varchar(40)          not null,
    description tinytext             null,
    is_unique   tinyint(1) default 0 null
);

INSERT INTO HeroSpotter.powers (id, power_name, description, is_unique) VALUES (2, 'Trotskyism', 'Retro migas gochujang hashtag dreamcatcher banh m', 0);
INSERT INTO HeroSpotter.powers (id, power_name, description, is_unique) VALUES (9, 'Great Leap Forward', 'Kale chips asymmetrical next level, mlkshk pok pok tumeric swag cred chambray marfa meditation mumblecore dreamcatcher banjo.', 1);
INSERT INTO HeroSpotter.powers (id, power_name, description, is_unique) VALUES (10, 'Cultural Revolution', 'VHS mumblecore. Cardigan listicle trust fund, you probably haven''t heard of them lumbersexual vegan paleo edison bulb migas banh mi distillery jean shorts chillwave humblebrag', 0);
INSERT INTO HeroSpotter.powers (id, power_name, description, is_unique) VALUES (11, 'Proletarian Internationalism', 'Shabby chic pop-up iPhone scenester freegan edison bulb tacos mixtape chia viral ethical cold-pressed activated charcoal. ', 0);
INSERT INTO HeroSpotter.powers (id, power_name, description, is_unique) VALUES (12, 'Monkey Handling', 'Actually helvetica tilde, taxidermy sriracha etsy church-key blog enamel pin freegan succulents disrupt small batch hot chicken tacos. ', 0);
create table sightings
(
    id             smallint auto_increment
        primary key,
    location_id    smallint             not null,
    super_id       smallint             not null,
    date           date                 not null,
    image_file     mediumblob           null,
    image_approved tinyint(1) default 0 null,
    reporter_name  varchar(40)          null,
    constraint sightings_ibfk_1
        foreign key (location_id) references locations (id),
    constraint sightings_ibfk_2
        foreign key (super_id) references supers (id)
);

create index location_id
    on sightings (location_id);

create index super_id
    on sightings (super_id);

INSERT INTO HeroSpotter.sightings (id, location_id, super_id, date, image_file, image_approved, reporter_name) VALUES (5, 6, 2, '2011-08-19', null, 0, null);
INSERT INTO HeroSpotter.sightings (id, location_id, super_id, date, image_file, image_approved, reporter_name) VALUES (6, 7, 3, '2011-08-19', null, 0, null);
INSERT INTO HeroSpotter.sightings (id, location_id, super_id, date, image_file, image_approved, reporter_name) VALUES (10, 21, 2, '2011-08-19', null, 0, '');
create table supers
(
    id          smallint auto_increment
        primary key,
    name        varchar(40)          not null,
    is_villain  tinyint(1) default 0 null,
    description text                 null
);

INSERT INTO HeroSpotter.supers (id, name, is_villain, description) VALUES (1, 'fsdf', 1, 'Four loko slow-carb 90''s, four dollar toast green juice la croix vaporware. Listicle 3 wolf moon butcher, kale chips meggings copper mug offal. Lyft iPhone fingerstache banjo. Brooklyn health goth pitchfork, sustainable DIY cloud bread poke tote bag church-key messenger bag. ');
INSERT INTO HeroSpotter.supers (id, name, is_villain, description) VALUES (2, 'Leon Trotsky', 0, 'YOLO vaporware keytar tofu, knausgaard stumptown roof party vexillologist cray ennui affogato. Before they sold out shabby chic swag vegan hexagon subway tile gochujang irony mlkshk butcher narwhal you probably haven''t heard of them 90''s gentrify vaporware. Shaman trust fund direct trade pinterest. ');
INSERT INTO HeroSpotter.supers (id, name, is_villain, description) VALUES (3, 'Mao ZeDong', 1, 'Banh mi pinterest art party hell of succulents. Lomo readymade knausgaard air plant prism swag deep v fingerstache. Fixie semiotics normcore venmo yuccie meditation dreamcatcher. ');
INSERT INTO HeroSpotter.supers (id, name, is_villain, description) VALUES (4, 'test', 0, 'asdfasdfkajsdfl;kadfs');
INSERT INTO HeroSpotter.supers (id, name, is_villain, description) VALUES (5, 'gs', 0, 'segf');
INSERT INTO HeroSpotter.supers (id, name, is_villain, description) VALUES (6, 'asdflk', 0, 'If you want to execute a Statement object many times, it will normally reduce execution time to use a PreparedStatement object instead. The main feature of a PreparedStatement object is that, unlike a Statement object, it is given an SQL statement when it is created. PROTECTS AGAINST SQL INJECTION ATTACKS');
INSERT INTO HeroSpotter.supers (id, name, is_villain, description) VALUES (7, '', 0, '');
create table supers_powers
(
    super_id smallint not null,
    power_id smallint not null,
    primary key (super_id, power_id),
    constraint supers_powers_ibfk_1
        foreign key (power_id) references powers (id),
    constraint supers_powers_ibfk_2
        foreign key (super_id) references supers (id)
);

create index power_id
    on supers_powers (power_id);

INSERT INTO HeroSpotter.supers_powers (super_id, power_id) VALUES (1, 2);
INSERT INTO HeroSpotter.supers_powers (super_id, power_id) VALUES (6, 2);
INSERT INTO HeroSpotter.supers_powers (super_id, power_id) VALUES (7, 2);
INSERT INTO HeroSpotter.supers_powers (super_id, power_id) VALUES (6, 9);
INSERT INTO HeroSpotter.supers_powers (super_id, power_id) VALUES (7, 9);
INSERT INTO HeroSpotter.supers_powers (super_id, power_id) VALUES (6, 10);
INSERT INTO HeroSpotter.supers_powers (super_id, power_id) VALUES (7, 10);
INSERT INTO HeroSpotter.supers_powers (super_id, power_id) VALUES (6, 11);