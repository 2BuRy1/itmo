CREATE TABLE space_ship(
ship_id INTEGER PRIMARY KEY,
material VARCHAR(40),
size INTEGER CHECK (size between 3000 and 10000) NOT NULL
name VARCHAR(30)
);

CREATE TABLE human(
human_id INTEGER PRIMARY KEY,
name VARCHAR(15),
age DATE,
height INTEGER CHECK (height between 150 and 200),
weight INTEGER CHECK (weight between 55 and 90),
ship_id INTEGER
);

CREATE TABLE planet(
planet_id INTEGER PRIMARY KEY,
name VARCHAR(20),
size INTEGER CHECK (size between 40 and 200) NOT NULL --В тысячах км
);

CREATE TABLE pilot(
pilot_id INTEGER PRIMARY KEY,
experinece INTEGER NOT NULL ,
rank VARCHAR(15),
human_id INTEGERб
planet_id INTEGER
);

CREATE TABLE researcher(
researcher_id INTEGER PRIMARY KEY,
experience INTEGER NOT NULL,
patience INTEGER CHECK (patience between 0 and 10),
human_id INTEGER
);

CREATE TABLE info(
info_id INTEGER PRIMARY KEY,
description text,
subject VARCHAR(40)
);

CREATE TABLE research_to_info(
researcher_id INTEGER,
info_id INTEGER
);

CREATE TABLE signal(
signal_id INTEGER PRIMARY KEY,
range INTEGER,
power INTEGER,
planet_id INTEGER
);

ALTER TABLE human ADD FOREIGN KEY (ship_id) REFERENCES space_ship (ship_id);

ALTER TABLE pilot ADD FOREIGN KEY (human_id) REFERENCES human (human_id);
ALTER TABLE pilot ADD FOREIGN KEY (planet_id) REFERENCES planet (planet_id);

ALTER TABLE researcher ADD FOREIGN KEY (human_id) REFERENCES human
(human_id);

ALTER TABLE signal ADD FOREIGN KEY (planet_id) REFERENCES planet (planet_id);

ALTER TABLE research_to_info ADD FOREIGN KEY (info_id) REFERENCES info
(info_id);
ALTER TABLE research_to_info ADD FOREIGN KEY (researcher_id) REFERENCES
researcher (researcher_id);

INSERT INTO space_ship VALUES (1, 'metal', 6000,'Планетарность');

INSERT INTO human
VALUES(1 , 'Bouman', '1999-09-12', 178, 73 , 1),
(2, 'Paul', '1994-04-28', 177, 82, 1),
(3, 'Kaminsky', '1997-05-23', 183, 88, 1),
(4, 'Hunter', '1987-11-24', 170, 72, 1),

(5, 'Whitehead', '1992-08-01' , 172, 68, 1);

INSERT INTO planet
VALUES(1, 'Jupiter', '157');

INSERT INTO pilot
VALUES(1, 20, 'Major', 1, 1),
(2, 13,'Officer', 1, 2);

INSERT INTO researcher VALUES
(1, 15, 9, 3),
(2,13, 7, 4),
(3, 20, 10 ,5);

INSERT INTO info VALUES
(1, 'Бег: 1км, Отжимания: 25х3, круговая тренировка с гантелями(3 раза)',
'тренировка'),
(2, 'Лечь и расслабить все мышцы, включая мышцы лица. Сон будет в течение 2х
лет', 'анабиоз');

INSERT INTO signal VALUES
(1, 1000, 20000, 1);

INSERT INTO research_to_info VALUES
(1 ,2),
(1, 2),
(2, 1),
(2, 2),
(3, 1),
(3, 2);