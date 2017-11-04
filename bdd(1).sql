CREATE DATABASE IF NOT EXISTS Sanglier;
USE Sanglier;

CREATE TABLE IF NOT EXISTS Account(
	id_user INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	pseudo CHAR(20) UNIQUE NOT NULL,
	password VARCHAR(255) NOT NULL,
	mail CHAR(50),
	zip_code INT,
	city CHAR(50),
	adress CHAR(50)
);

CREATE TABLE IF NOT EXISTS Sport(
	id_sport INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	name_sport char(20)
);

CREATE TABLE IF NOT EXISTS Team (
	id_team INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	id_sport INT NOT NULL,
	name_team CHAR(20),
	FOREIGN KEY (id_sport) REFERENCES Sport(id_sport)
);

CREATE TABLE IF NOT EXISTS Encounter(
	id_encounter INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	id_sport INT NOT NULL,
	id_team_1 INT NOT NULL,
	id_team_2 INT NOT NULL,
	score_team_1 INT NOT NULL,
	score_team_2 INT NOT NULL,
	state_encounter ENUM('Soon','Current', 'Over'),
	date_encounter DATETIME,
	FOREIGN KEY (id_sport) REFERENCES Sport(id_sport),
	FOREIGN KEY (id_team_1) REFERENCES Team(id_team),
	FOREIGN KEY (id_team_2) REFERENCES Team(id_team),
	CHECK (id_team_1!=id_team_2)
);

CREATE TABLE IF NOT EXISTS Service(
	id_service INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	name_service CHAR(20)
);

CREATE TABLE IF NOT EXISTS Bet(
	id_bet INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	id_encounter INT NOT NULL,
	id_service_1 INT NOT NULL,
	id_service_2 INT NOT NULL,
	id_user_1 INT NOT NULL,
	id_user_2 INT NOT NULL,
	state_bet ENUM('BEGIN', 'WAITING', 'VALID', 'OVER'),
	FOREIGN KEY (id_encounter) REFERENCES Encounter(id_encounter),
	FOREIGN KEY (id_service_1) REFERENCES Service(id_service),
	FOREIGN KEY (id_service_2) REFERENCES Service(id_service),
	FOREIGN KEY (id_user_1) REFERENCES Account(id_user),
	FOREIGN KEY (id_user_2) REFERENCES Account(id_user),
	CHECK (id_user_1!=id_user_2)

);
